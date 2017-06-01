package com.questionnaire.ssm.module.notice.service.impl;

import com.questionnaire.ssm.module.global.service.UnitService;
import com.questionnaire.ssm.module.notice.enums.NoticeForCurUserActionEnum;
import com.questionnaire.ssm.module.notice.mapper.Notice2MeMapper;
import com.questionnaire.ssm.module.notice.pojo.NoticeForCurUserDTO;
import com.questionnaire.ssm.module.notice.pojo.NoticeForCurUserVO;
import com.questionnaire.ssm.module.notice.service.Notice2MeService;
import com.questionnaire.ssm.module.notice.util.NoticeVODOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 郑晓辉 on 2017/6/1.
 * Description:
 */
@Service
public class Notice2MeServiceImpl implements Notice2MeService {

    @Override
    public List<NoticeForCurUserVO> getReleasedNoticeForCurrentUser(String curUserAccount, NoticeForCurUserActionEnum actionEnum) throws Exception {
        List<NoticeForCurUserDTO> allNoticeInfoReleased = notice2MeMapper.listAllNoticeForUserReleased();
        if (allNoticeInfoReleased.size() <= 0) {
            return null;
        }
        //当前在线用户所在单位ID
        String curUserUnitId = String.valueOf(unitService.getUnitIdByUserTel(curUserAccount));

        List<NoticeForCurUserVO> resultList = null;
        switch (actionEnum) {
            case GET_ALL_RELEASED_NOTICE://获取全部
                resultList = getNotice2CurUserByRecordNum(curUserUnitId, allNoticeInfoReleased, allNoticeInfoReleased.size());
                break;
            case GET_TOP_5_RELEASED_NOTICE://获取最新的5条
                if (allNoticeInfoReleased.size() > 5) {
                    resultList = getNotice2CurUserByRecordNum(curUserUnitId,
                            allNoticeInfoReleased, 5);
                } else {
                    resultList = getNotice2CurUserByRecordNum(curUserUnitId,
                            allNoticeInfoReleased, allNoticeInfoReleased.size());
                }
                break;
            default:
                return null;
        }
        return resultList;
    }

    /**
     * 处理得到当前用户的公告信息
     *
     * @param curUserUnitId
     * @param originNoticeDOList
     * @param needRecordLength
     * @return
     * @throws Exception
     */
    private List<NoticeForCurUserVO> getNotice2CurUserByRecordNum(String curUserUnitId, List<NoticeForCurUserDTO> originNoticeDOList, int needRecordLength) throws Exception {
        List<NoticeForCurUserVO> resultList = new ArrayList<>();
        NoticeForCurUserVO resultVOItem = null;
        String[] unitIdArray = null;
        NoticeForCurUserDTO noticeForCurUserDTO = null;
        for (int i = 0; i < needRecordLength; i++) {
            noticeForCurUserDTO = originNoticeDOList.get(i);
            unitIdArray = noticeForCurUserDTO.getObjectUnitText().split("\\|\\|");
            for (String notice2UnitId : unitIdArray) {
                //当前公告单位Id 不是 当前用户所在的单位
                if (!notice2UnitId.equals(curUserUnitId)) {
                    continue;
                }
                //当前公告单位Id 是 当前用户所在的单位
                resultVOItem = NoticeVODOUtil.toNoticeForUserVO(noticeForCurUserDTO);
                resultList.add(resultVOItem);
                break;
            }
        }
        return resultList;
    }

    private Notice2MeMapper notice2MeMapper;
    private UnitService unitService;

    @Autowired
    public Notice2MeServiceImpl(Notice2MeMapper notice2MeMapper,
                                UnitService unitService) {
        this.notice2MeMapper = notice2MeMapper;
        this.unitService = unitService;
    }
}
