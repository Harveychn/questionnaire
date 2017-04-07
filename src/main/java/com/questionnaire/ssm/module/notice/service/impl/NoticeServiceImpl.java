package com.questionnaire.ssm.module.notice.service.impl;

import com.questionnaire.ssm.module.global.enums.DBTableEnum;
import com.questionnaire.ssm.module.global.enums.OperateDBEnum;
import com.questionnaire.ssm.module.global.enums.UserActionEnum;
import com.questionnaire.ssm.module.global.exception.OperateDBException;
import com.questionnaire.ssm.module.global.util.UserValidationUtil;
import com.questionnaire.ssm.module.notice.mapper.NoticeMapper;
import com.questionnaire.ssm.module.notice.mapper.RecordOperateNoticeMapper;
import com.questionnaire.ssm.module.notice.pojo.Notice;
import com.questionnaire.ssm.module.notice.pojo.RecordOperateNotice;
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
    private RecordOperateNoticeMapper recordOperateNoticeMapper;
    private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper,
                             RecordOperateNoticeMapper recordOperateNoticeMapper){
        this.noticeMapper=noticeMapper;
        this.recordOperateNoticeMapper=recordOperateNoticeMapper;;

    }
    @Override
    @Transactional
    public void insertNotice(Notice notice) throws Exception {
        UserValidationUtil.checkUserValid(logger);
        Date currentDate=new Date();
        String userTel=UserValidationUtil.getUserTel(logger);

        int insertResult=0;
        try {
            insertResult=noticeMapper.insertSelective(notice);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(OperateDBEnum.UNKNOWN_ERROR, DBTableEnum.NOTICE.getTableName());
        }
        if(insertResult!=1){
            logger.error(OperateDBEnum.INSERT_FAIL.getMessage()+"\n"+DBTableEnum.NOTICE.getTableName());
            throw new OperateDBException(OperateDBEnum.INSERT_FAIL, DBTableEnum.NOTICE.getTableName());
        }

        /*用户操作问卷记录保存*/
        RecordOperateNotice recordOperateNotice=new RecordOperateNotice();
        recordOperateNotice.setOperateDate(currentDate);
        recordOperateNotice.setUserTel(userTel);
        recordOperateNotice.setNoticeId(notice.getNoticeId());
        recordOperateNotice.setAction(String.valueOf(UserActionEnum.INSERT_ACTION.getCode()));

        int result=0;
        try {
            result=recordOperateNoticeMapper.insertSelective(recordOperateNotice);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(OperateDBEnum.UNKNOWN_ERROR,DBTableEnum.RECORD_OPERATE_NOTICE.getTableName());
        }
        if (result!=1){
            throw new OperateDBException(OperateDBEnum.INSERT_FAIL,DBTableEnum.RECORD_OPERATE_NOTICE.getTableName());
        }
    }

    @Override
    public List<Notice> listNotice() throws Exception {
        List<Notice> notices=null;
        try{
            notices=noticeMapper.selectAll();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new OperateDBException(OperateDBEnum.UNKNOWN_ERROR,"获取公告信息出现异常!");
        }
        return notices;
    }
}
