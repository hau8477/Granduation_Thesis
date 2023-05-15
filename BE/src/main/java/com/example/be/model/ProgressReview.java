package com.example.be.model;

import javax.persistence.*;

@Entity
public class ProgressReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressReviewId;
    @Column(columnDefinition = "varchar(255)",nullable = false)
    private String progressReviewTitle;
    @Column(columnDefinition = "text",nullable = false)
    private String progressReviewContent;
    @Column(nullable = false)
    private int progressReviewPercent;
    @Column(columnDefinition = "dateTime",nullable = false)
    private String progressReviewDateCreate;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;

    public ProgressReview() {
    }

    public Long getProgressReviewId() {
        return progressReviewId;
    }

    public void setProgressReviewId(Long progressReviewId) {
        this.progressReviewId = progressReviewId;
    }

    public String getProgressReviewTitle() {
        return progressReviewTitle;
    }

    public void setProgressReviewTitle(String progressReviewTitle) {
        this.progressReviewTitle = progressReviewTitle;
    }

    public String getProgressReviewContent() {
        return progressReviewContent;
    }

    public void setProgressReviewContent(String progressReviewContent) {
        this.progressReviewContent = progressReviewContent;
    }

    public int getProgressReviewPercent() {
        return progressReviewPercent;
    }

    public void setProgressReviewPercent(int progressReviewPercent) {
        this.progressReviewPercent = progressReviewPercent;
    }

    public String getProgressReviewDateCreate() {
        return progressReviewDateCreate;
    }

    public void setProgressReviewDateCreate(String progressReviewDateCreate) {
        this.progressReviewDateCreate = progressReviewDateCreate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
