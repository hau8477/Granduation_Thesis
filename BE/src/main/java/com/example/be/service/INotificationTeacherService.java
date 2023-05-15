package com.example.be.service;

import com.example.be.model.NotificationTeacher;
import java.util.List;

public interface INotificationTeacherService {
    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get notification teacher list
     */
    List<NotificationTeacher> getAllNotificationTeacher();

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List NotificationTeacher
     *
     * @param 'notificationTeacherName'
     * @param 'notificationTeacherContent'
     */
    void addNotificationTeacher(String notificationTeacherTopic, String notificationTeacherContent);
}
