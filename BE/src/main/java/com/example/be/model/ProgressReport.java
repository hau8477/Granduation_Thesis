package com.example.be.model;

import javax.persistence.*;

@Entity
public class ProgressReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_report_id")
    private Long progressReportId;
    @Column(columnDefinition = "text", nullable = false)
    private String progressReportContent;
    @Column(columnDefinition = "dateTime", nullable = false)
    private String progressReportTime;
    @Column(columnDefinition = "text", nullable = false)
    private String progressReportFile;

    @Column(columnDefinition = "text", nullable = false)
    private String progressReportFileName;
    @ManyToOne
    @JoinColumn(name = "stage_id" , referencedColumnName = "stage_id")
    private Stage stage;
    @JoinColumn(name = "project_id" , referencedColumnName = "project_id")
    @ManyToOne
    private Project project;

    public ProgressReport() {
    }

    public Long getProgressReportId() {
        return progressReportId;
    }

    public void setProgressReportId(Long progressReportId) {
        this.progressReportId = progressReportId;
    }

    public String getProgressReportTime() {
        return progressReportTime;
    }

    public void setProgressReportTime(String progressReportTime) {
        this.progressReportTime = progressReportTime;
    }

    public String getProgressReportContent() {
        return progressReportContent;
    }

    public void setProgressReportContent(String progressReportContent) {
        this.progressReportContent = progressReportContent;
    }

    public String getProgressReportFile() {
        return progressReportFile;
    }

    public void setProgressReportFile(String progressReportFile) {
        this.progressReportFile = progressReportFile;
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProgressReportFileName() {
        return progressReportFileName;
    }

    public void setProgressReportFileName(String progressReportFileName) {
        this.progressReportFileName = progressReportFileName;
    }
}
