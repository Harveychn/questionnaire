package com.questionnaire.ssm.module.questionnaireManage.mapper;

import com.questionnaire.ssm.module.questionnaireManage.pojo.MyQesVO;
import com.questionnaire.ssm.module.questionnaireManage.pojo.TempDelQesPaperVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 郑晓辉 on 2017/3/27.
 * Description: 问卷管理模块mapper
 * 1.查询全部用户问卷/查询单个用户的问卷信息
 */
public interface QesManageMapper {

    /**
     * 查询 个人问卷 信息
     *
     * @param userTel 用户账号，为null或“”则查询全部问卷，否则查询用户账号为 userTel 的问卷信息
     * @return
     * @throws Exception
     */
    List<MyQesVO> listQuestionnaireInfoByUserTel(@Param("userTel") String userTel) throws Exception;

    /**
     * 查询 回收站问卷
     *
     * @param userTel 用户账号
     * @return
     * @throws Exception
     */
    List<TempDelQesPaperVO> listTempDelQesPaperByUserTel(@Param("userTel") String userTel) throws Exception;

    /**
     * 根据问卷id查询问卷题目id
     *
     * @param questionnaireIds
     * @return
     * @throws Exception
     */
    List<Long> listQuestionIdByQesPaperIds(List<Long> questionnaireIds) throws Exception;
}
