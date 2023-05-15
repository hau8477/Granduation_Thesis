package com.example.be.repository;

import com.example.be.model.NotificationTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface INotificationTeacherRepository extends JpaRepository<NotificationTeacher, Long> {
    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get notification teacher list
     */
    @Query(value =
            "select * from notification_teacher" +
            " order by `notification_teacher`.notification_teacher_id desc ",
            nativeQuery = true)
    List<NotificationTeacher> getAllNotificationTeacher();

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List NotificationTeacher
     *
     * @param 'notificationTeacherName'
     * @param 'notificationTeacherContent'
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus. OK if result is not error
     */
    @Transactional
    @Modifying
    @Query(value =
            "insert into notification_teacher (notification_teacher_topic, notification_teacher_content, notification_teacher_time) " +
                    "    value (:notificationTeacherTopic,:notificationTeacherContent, :notificationTeacherTime);",
            nativeQuery = true)
    void addNotificationTeacher(@Param("notificationTeacherTopic") String notificationTeacherTopic,
                                @Param("notificationTeacherContent") String notificationTeacherContent,
                                @Param("notificationTeacherTime") String notificationTeacherTime);
}
