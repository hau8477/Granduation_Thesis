package com.example.be.service;

import com.example.be.dto.IMailQuesDto;
import com.example.be.dto.IQuestionDto;
import com.example.be.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IQuestionService {
    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : find all question
     *
     * @param pageable
     */
    Page<Question> findAll(Pageable pageable);

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : save question
     *
     * @param questionContent, questionTopic, dateTime, studentId
     * @param dateTime
     */
    void saveQuestion(String questionContent, String questionTopic, LocalDateTime dateTime, Long studentId);
    Question save(Question question);

    IMailQuesDto getMailQues(Long questionId);
}
