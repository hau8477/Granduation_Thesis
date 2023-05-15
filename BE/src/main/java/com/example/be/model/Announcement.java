package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long announcementId;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String announcementName;
    @Column(columnDefinition = "text", nullable = false)
    private String announcementContent;
    @Column(columnDefinition = "text", nullable = false)
    private String announcementTime;
    @OneToMany(mappedBy = "announcement")
    @JsonIgnore
    private Set<StudentAnnouncement> studentAnnouncementSet;

    private String attach;

    public Announcement() {
    }


    public Set<StudentAnnouncement> getStudentAnnouncementSet() {
        return studentAnnouncementSet;
    }

    public void setStudentAnnouncementSet(Set<StudentAnnouncement> studentAnnouncementSet) {
        this.studentAnnouncementSet = studentAnnouncementSet;
    }

    public String getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(String announcementTime) {
        this.announcementTime = announcementTime;
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public void setAnnouncementName(String announcementName) {
        this.announcementName = announcementName;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
