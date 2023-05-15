package com.example.be.dto;


import java.time.LocalDateTime;

public interface IQuestionDto {
    Long getStudentId();

    String getStudentName();

    String getStudentImg();

    Long getQuestionId();

    String getQuestionTopic();

    String getQuestionContent();

    LocalDateTime getDateTime();
}
