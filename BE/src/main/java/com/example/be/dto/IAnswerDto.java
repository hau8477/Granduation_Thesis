package com.example.be.dto;

import java.time.LocalDateTime;

public interface IAnswerDto {
    Long getTeacherId();

    String getTeacherName();

    String getTeacherImg();

    LocalDateTime getDateTime();

    Long getAnswerId();

    String getAnswerContent();

    Long getQuestionId();

}
