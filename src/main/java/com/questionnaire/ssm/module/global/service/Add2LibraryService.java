package com.questionnaire.ssm.module.global.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 郑晓辉 on 2017/4/3.
 * Description:
 */
public interface Add2LibraryService {


    @Transactional
    Questionnaire getSharingQesPaperFromDB(Long questionnaireId) throws Exception;

    @Transactional
    Long Add2PublicOrPrivateLibrary(Long questionnaireId, Questionnaire copyQesPaper) throws Exception;
}
