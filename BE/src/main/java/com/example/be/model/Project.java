package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;
    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String projectName;
    @Column(columnDefinition = "text", nullable = false)
    private String projectContent;
    @Column(columnDefinition = "text", nullable = false)
    private String projectImg;
    @Column(columnDefinition = "text", nullable = false)
    private String projectDescription;
    @Column(columnDefinition = "bit(1)")
    private Boolean projectStatus;
    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<ProgressReport> progressReportSet;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private Set<ProgressReview> progressReviews;

    public Project() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @JsonIgnore
    public Boolean isProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(boolean projectStatus) {
        this.projectStatus = projectStatus;
    }
    public Set<ProgressReport> getProgressReportSet() {
        return progressReportSet;
    }

    public void setProgressReportSet(Set<ProgressReport> progressReportSet) {
        this.progressReportSet = progressReportSet;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<ProgressReview> getProgressReviews() {
        return progressReviews;
    }

    public void setProgressReviews(Set<ProgressReview> progressReviews) {
        this.progressReviews = progressReviews;
    }

    public Boolean getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Boolean projectStatus) {
        this.projectStatus = projectStatus;
    }
}
