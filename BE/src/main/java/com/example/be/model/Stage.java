package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private int stageId;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String stageName;
    @OneToMany(mappedBy = "stage")
    @JsonIgnore
    private Set<ProgressReport> progressReportSet;

    public Stage() {
    }

    public Set<ProgressReport> getProgressReportSet() {
        return progressReportSet;
    }

    public void setProgressReportSet(Set<ProgressReport> progressReportSet) {
        this.progressReportSet = progressReportSet;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
