package com.example.be.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;

public class  NotificationTeacherDto implements Validator {
    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     */
    private Long notificationTeacherId;
    //    @NotBlank(message = "Không được để trống, nhập chủ đề thông báo vào. ")
    private String notificationTeacherTopic;
    //    @NotBlank(message = "Không được để trống, nhập nội dung thông báo vào. ")
    private String notificationTeacherContent;
    private TeacherNTDto teacherNTDto;

    public NotificationTeacherDto() {
    }

    public NotificationTeacherDto(String notificationTeacherTopic, String notificationTeacherContent) {
        this.notificationTeacherTopic = notificationTeacherTopic;
        this.notificationTeacherContent = notificationTeacherContent;
    }

    public Long getNotificationTeacherId() {
        return notificationTeacherId;
    }

    public void setNotificationTeacherId(Long notificationTeacherId) {
        this.notificationTeacherId = notificationTeacherId;
    }

    public String getNotificationTeacherTopic() {
        return notificationTeacherTopic;
    }

    public void setNotificationTeacherTopic(String notificationTeacherTopic) {
        this.notificationTeacherTopic = notificationTeacherTopic;
    }

    public String getNotificationTeacherContent() {
        return notificationTeacherContent;
    }

    public void setNotificationTeacherContent(String notificationTeacherContent) {
        this.notificationTeacherContent = notificationTeacherContent;
    }

    public TeacherNTDto getTeacherNTDto() {
        return teacherNTDto;
    }

    public void setTeacherNTDto(TeacherNTDto teacherNTDto) {
        this.teacherNTDto = teacherNTDto;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        target = (NotificationTeacherDto) target;
        if (((NotificationTeacherDto) target).getNotificationTeacherTopic().equals("")) {
            errors.rejectValue("notificationTeacherTopic", "notificationTeacherTopic", "Không được để trống !");
        }
        if (((NotificationTeacherDto) target).getNotificationTeacherContent().equals("")){
            errors.rejectValue("notificationTeacherContent","notificationTeacherContent","Không được để trống !");
        }
    }
}
