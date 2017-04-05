package com.questionnaire.ssm.module.global.service;

import com.questionnaire.ssm.module.generated.pojo.Questionnaire;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郑晓辉 on 2017/4/3.
 * Description:
 */
public interface Add2LibraryService {


    @Transactional
    Questionnaire getSharingQesPaperFromDB(Long questionnaireId) throws Exception;

    @Transactional
    void Add2PublicOrPrivateLibrary(Long questionnaireId, Questionnaire copyQesPaper) throws Exception;
}
