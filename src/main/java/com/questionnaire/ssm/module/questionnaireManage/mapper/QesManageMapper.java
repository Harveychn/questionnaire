package com.questionnaire.ssm.module.questionnaireManage.mapper;

import com.questionnaire.ssm.module.questionnaireManage.pojo.ListQuestionnaireVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.ListTempDelQesPaperVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description: 问卷管理模块mapper
 * 1.查询全部用户问卷/查询单个用户的问卷信息
 */
public interface QesManageMapper {

    /**
     * 查询问卷信息
     *
     * @param userTel 用户账号，为null或“”则查询全部问卷，否则查询用户账号为 userTel 的问卷信息
     * @return
     * @throws Exception
     */
    List<ListQuestionnaireVO> listQuestionnaireInfoByUserTel(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询回收站问卷
     *
     * @param userTel 用户账号
     * @return
     * @throws Exception
     */
    List<ListTempDelQesPaperVO> listTempDelQesPaperByUserTel(@Param("userTel") String userTel) throws Exception;
}
