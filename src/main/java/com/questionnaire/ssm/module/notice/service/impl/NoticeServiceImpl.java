package com.questionnaire.ssm.module.notice.service.impl;

import com.questionnaire.ssm.module.generated.mapper.NoticeMapper;
import com.questionnaire.ssm.module.generated.pojo.NoticeWithBLOBs;
import com.questionnaire.ssm.module.generated.pojo.Unit;
import com.questionnaire.ssm.module.global.constant.CONSTANT;
import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.service.UnitService;
import com.questionnaire.ssm.module.notice.mapper.NoticeManageMapper;
import com.questionnaire.ssm.module.notice.pojo.*;
import com.questionnaire.ssm.module.notice.service.NoticeService;
import com.questionnaire.ssm.module.notice.util.NoticeVODOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 95884 on 2017/4/1.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Override
    @Transactional
    public void insertNotice(String userTel, CreateNoticeVO createNoticeVO) throws Exception {
        NoticeWithBLOBs noticeWithBLOBs = NoticeVODOUtil.toNoticeDO(createNoticeVO);
        //创建用户
        noticeWithBLOBs.setUserTel(userTel);
        //创建时间
        noticeWithBLOBs.setNoticeCreateTime(new Date());
        //是否创建完成
        noticeWithBLOBs.setIsDone((byte) 1);
        try {
            noticeMapper.insertSelective(noticeWithBLOBs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.NOTICE.getTableName());
        }
    }

    @Override
    public List<ListMyNoticeInfoVO> listNoticeByUserTel(String userTel) throws Exception {
        List<ListMyNoticeInfoVO> noticeInfoVOList = new ArrayList<>();
        List<ListMyNoticeDTO> noticeDTOList = null;
        try {
            noticeDTOList = noticeManageMapper.listNoticeByUserTel(userTel);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, "获取公告信息出现异常!");
        }
        //查询单位名
        String[] unitIdArray = null;
        ListMyNoticeInfoVO listMyNoticeInfoVO = null;
        List<String> noticeUnitNameList = null;
        for (ListMyNoticeDTO listMyNoticeDTO : noticeDTOList) {
            unitIdArray = listMyNoticeDTO.getNoticeUnitText().split("\\|\\|");
            //拷贝公告视图中数据信息
            listMyNoticeInfoVO = NoticeInfoDTO2VO(listMyNoticeDTO);
            noticeUnitNameList = new ArrayList<>();
            Unit unit = null;
            for (String unitIdString : unitIdArray) {
                unit = unitService.getUnitInfoByUnitId(Long.valueOf(unitIdString));
                if (unit == null) {
                    noticeUnitNameList.add(CONSTANT.getNoSuchUnitTip());
                    continue;
                }
                noticeUnitNameList.add(unit.getUnitName());
            }
            listMyNoticeInfoVO.setNoticeUnitName(noticeUnitNameList);
            try {
                noticeInfoVOList.add(listMyNoticeInfoVO);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return noticeInfoVOList;
    }

    @Override
    @Transactional
    public void deleteNotice(Long noticeId) throws Exception {
        try {
            noticeMapper.deleteByPrimaryKey(noticeId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.NOTICE.getTableName());
        }
    }

    /**
     * 组织视图数据
     *
     * @param listMyNoticeDTO
     * @return
     * @throws Exception
     */
    private ListMyNoticeInfoVO NoticeInfoDTO2VO(ListMyNoticeDTO listMyNoticeDTO) throws Exception {
        ListMyNoticeInfoVO listMyNoticeInfoVO = new ListMyNoticeInfoVO();
        //设置公告id
        listMyNoticeInfoVO.setNoticeId(listMyNoticeDTO.getNoticeId());
        //设置公告标题
        if (listMyNoticeDTO.getNoticeTitle() != null) {
            listMyNoticeInfoVO.setNoticeTitle(listMyNoticeDTO.getNoticeTitle());
        }
        //设置公告内容
        if (listMyNoticeDTO.getNoticeContext() != null) {
            listMyNoticeInfoVO.setNoticeContext(listMyNoticeDTO.getNoticeContext());
        }
        //设置公告创建时间
        if (listMyNoticeDTO.getNoticeCreateTime() != null) {
            listMyNoticeInfoVO.setNoticeCreateTime(listMyNoticeDTO.getNoticeCreateTime());
        }
        //设置公告的发布日期
        if (listMyNoticeDTO.getNoticeLaunchDate() != null) {
            listMyNoticeInfoVO.setNoticeLaunchDate(listMyNoticeDTO.getNoticeLaunchDate());
        }
        return listMyNoticeInfoVO;
    }

    private NoticeManageMapper noticeManageMapper;
    private NoticeMapper noticeMapper;
    private UnitService unitService;
    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    public NoticeServiceImpl(NoticeManageMapper noticeManageMapper,
                             NoticeMapper noticeMapper,
                             UnitService unitService) {
        this.noticeManageMapper = noticeManageMapper;
        this.noticeMapper = noticeMapper;
        this.unitService = unitService;
    }
}
