package com.questionnaire.ssm.module.notice.service.impl;

import com.questionnaire.ssm.module.global.enums.CodeForVOEnum;
import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.notice.mapper.NoticeMapper;
import com.questionnaire.ssm.module.notice.pojo.Notice;
import com.questionnaire.ssm.module.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 95884 on 2017/4/1.
 */
@Service
public class NoticeServiceImpl implements NoticeService{

    private NoticeMapper noticeMapper;
    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper){
        this.noticeMapper=noticeMapper;

    }
    @Override
    @Transactional
    public void insertNotice(Notice notice) throws Exception {
        UserValidationUtil.checkUserValid(logger);
        Date currentDate=new Date();
        String userTel=UserValidationUtil.getUserTel(logger);

        int insertResult=0;
        try {
            notice.setUserTel(userTel);
            notice.setNoticeCreateTime(currentDate);
            notice.setIsDone(true);
            notice.setNoticeLaunchTime(currentDate);
            notice.setNoticeUnitText("111");
            insertResult=noticeMapper.insertSelective(notice);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.NOTICE.getTableName());
        }
        if(insertResult!=1){
            logger.error(CodeForVOEnum.DB_INSERT_FAIL.getMessage()+"\n"+DBTableEnum.NOTICE.getTableName());
            throw new OperateDBException(CodeForVOEnum.DB_INSERT_FAIL, DBTableEnum.NOTICE.getTableName());
        }
    }

    @Override
    public List<Notice> listNoticeByUserTel(String userTel) throws Exception {
        List<Notice> notices=null;
        try{
            notices=noticeMapper.selectByUserTel(userTel);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR,"获取公告信息出现异常!");
        }
        return notices;
    }

    @Override
    public void deleteNotice(Long noticeId) throws Exception {
        UserValidationUtil.checkUserValid(logger);

        int deleteResult=0;
        try{
           deleteResult=noticeMapper.deleteByPrimaryKey(noticeId);
        }catch (Exception e){
           logger.error(e.getMessage());
           throw new OperateDBException(CodeForVOEnum.UNKNOWN_ERROR, DBTableEnum.NOTICE.getTableName());
        }
        if(deleteResult!=1){
            logger.error(CodeForVOEnum.DB_DELETE_FAIL.getMessage()+"\n"+DBTableEnum.NOTICE.getTableName());
            throw new OperateDBException(CodeForVOEnum.DB_DELETE_FAIL, DBTableEnum.NOTICE.getTableName());
        }
    }
}
