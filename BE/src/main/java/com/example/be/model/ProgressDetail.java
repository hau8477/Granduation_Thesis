package com.example.be.model;




import javax.persistence.*;

@Entity
public class ProgressDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressDetailId;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String progressDetailName;
    private int progressDetailPercent;
    @Column(columnDefinition = "date", nullable = false)
    private String progressDateStart;
    @Column(columnDefinition = "date", nullable = false)
    private String progressDateEnd;
    private Boolean progressStatus;
    private Long projectId;

    private int stageId;

    public ProgressDetail() {
    }

    public ProgressDetail(String progressDetailName, int progressDetailPercent, String progressDateStart, String progressDateEnd, Boolean progressStatus, Long projectId, int stageId) {
        this.progressDetailName = progressDetailName;
        this.progressDetailPercent = progressDetailPercent;
        this.progressDateStart = progressDateStart;
        this.progressDateEnd = progressDateEnd;
        this.progressStatus = progressStatus;
        this.projectId = projectId;
        this.stageId = stageId;
    }

    public Boolean getProgressStatus() {
        return progressStatus;
    }

    public Long getProgressDetailId() {
        return progressDetailId;
    }

    public void setProgressDetailId(Long progressDetailId) {
        this.progressDetailId = progressDetailId;
    }

    public String getProgressDetailName() {
        return progressDetailName;
    }

    public void setProgressDetailName(String progressDetailName) {
        this.progressDetailName = progressDetailName;
    }

    public int getProgressDetailPercent() {
        return progressDetailPercent;
    }

    public void setProgressDetailPercent(int progressDetailPercent) {
        this.progressDetailPercent = progressDetailPercent;
    }

    public String getProgressDateStart() {
        return progressDateStart;
    }

    public void setProgressDateStart(String progressDateStart) {
        this.progressDateStart = progressDateStart;
    }

    public String getProgressDateEnd() {
        return progressDateEnd;
    }

    public void setProgressDateEnd(String progressDateEnd) {
        this.progressDateEnd = progressDateEnd;
    }

    public Boolean isProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(Boolean progressStatus) {
        this.progressStatus = progressStatus;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }
}
