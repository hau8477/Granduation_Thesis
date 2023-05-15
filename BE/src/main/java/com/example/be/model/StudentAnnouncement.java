package com.example.be.model;

import javax.persistence.*;

@Entity
public class StudentAnnouncement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_announcement_id")
    private Long studentAnnouncementId;
    @ManyToOne
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "announcement_id",referencedColumnName = "announcement_id")
    private Announcement announcement;

    public Long getStudentAnnouncementId() {
        return studentAnnouncementId;
    }

    public void setStudentAnnouncementId(Long studentAnnouncementId) {
        this.studentAnnouncementId = studentAnnouncementId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }

    public StudentAnnouncement() {
    }
}
