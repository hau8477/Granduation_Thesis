package com.example.be.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
/**
 * Created by: VuLX
 * Date created: 01/04/2023
 */

public class ProgressReviewDto {

    private Long progressReviewId;
    @NotEmpty(message = "Bạn phải nhập ô tiêu đề")
    @NotBlank(message = "Bạn phải nhập ô tiêu đề")
    @Size(min = 5)
    private String progressReviewTitle;
    @NotEmpty(message = "Bạn phải nhập ô tiêu đề")
    @NotBlank(message = "Bạn phải nhập ô tiêu đề")
    @Size(min = 5)

    private String progressReviewContent;


    private int progressReviewPercent;

    private String progressReviewDateCreate;

    private TeacherDTOO teacherDto;

    private ProjectDTO projectDto;

    public ProgressReviewDto() {
    }

    public ProgressReviewDto(Long progressReviewId, String progressReviewTitle, String progressReviewContent, int progressReviewPercent, String progressReviewDateCreate, TeacherDTOO teacherDto, ProjectDTO projectDto) {
        this.progressReviewId = progressReviewId;
        this.progressReviewTitle = progressReviewTitle;
        this.progressReviewContent = progressReviewContent;
        this.progressReviewPercent = progressReviewPercent;
        this.progressReviewDateCreate = progressReviewDateCreate;
        this.teacherDto = teacherDto;
        this.projectDto = projectDto;
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

    public TeacherDTOO getTeacherDto() {
        return teacherDto;
    }

    public void setTeacherDto(TeacherDTOO teacherDto) {
        this.teacherDto = teacherDto;
    }

    public ProjectDTO getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDTO projectDto) {
        this.projectDto = projectDto;
    }
}
