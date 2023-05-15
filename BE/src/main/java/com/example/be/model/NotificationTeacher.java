package com.example.be.model;
import javax.persistence.*;

@Entity
public class NotificationTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_teacher_id")
    private Long notificationTeacherId;
    @Column(columnDefinition = "text")
    private String notificationTeacherTopic;
    @Column(columnDefinition = "dateTime")
    private String notificationTeacherTime;
    @Column(columnDefinition = "text")
    private String notificationTeacherContent;
    @ManyToOne
    @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id")
    private Teacher teacher;

    public NotificationTeacher() {
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

    public void setNotificationTeacherTopic(String notificationTeacherName) {
        this.notificationTeacherTopic = notificationTeacherName;
    }

    public String getNotificationTeacherTime() {
        return notificationTeacherTime;
    }

    public void setNotificationTeacherTime(String notificationTeacherTime) {
        this.notificationTeacherTime = notificationTeacherTime;
    }

    public String getNotificationTeacherContent() {
        return notificationTeacherContent;
    }

    public void setNotificationTeacherContent(String notificationTeacherContent) {
        this.notificationTeacherContent = notificationTeacherContent;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
