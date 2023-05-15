package com.example.be.service;

import com.example.be.dto.IAnswerDto;
import com.example.be.dto.IMailAnsDto;
import com.example.be.model.Answers;

import java.time.LocalDateTime;
import java.util.List;

public interface IAnswerService {
    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : find all answer
     *
     * @param questionId
     */
    List<IAnswerDto> findAll(Integer questionId);

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : save question
     *
     * @param answerContent, questionId, teacherId, dateTime
     */
    void save(String answerContent, Long questionId, Long teacherId, LocalDateTime dateTime);

    IMailAnsDto getMailAns(Long answerId);
}
