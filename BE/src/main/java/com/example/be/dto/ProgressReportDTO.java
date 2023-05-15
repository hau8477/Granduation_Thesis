package com.example.be.dto;

import com.example.be.model.Project;
import com.example.be.model.Stage;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ProgressReportDTO {
    private int progressReportId;
    @NotEmpty(message = "Không được để trống !")
    @NotBlank(message = "Không được để trống !")
    @Length(min = 10, max = 200, message = "Ký tự không được nhỏ hơn 5 và vươt quá 200 ký tự")
    private String progressReportContent;
    @NotEmpty(message = "Không được để trống !")
    private String progressReportFile;


    private String progressReportTime;
    @NotEmpty(message = "Không được để trống !")
    @NotBlank(message = "Không được để trống !")
    @Length(min = 5, max = 200, message = "Ký tự không được nhỏ hơn 10 và vươt quá 200 ký tự")
    private String progressReportFileName;

    private Project project;
    private Stage stage;

    public ProgressReportDTO() {
    }

    public ProgressReportDTO(int progressReportId, String progressReportContent, String progressReportFile, String progressReportTime, String progressReportFileName, Project project, Stage stage) {
        this.progressReportId = progressReportId;
        this.progressReportContent = progressReportContent;
        this.progressReportFile = progressReportFile;
        this.progressReportTime = progressReportTime;
        this.progressReportFileName = progressReportFileName;
        this.project = project;
        this.stage = stage;
    }

    public int getProgressReportId() {
        return progressReportId;
    }

    public void setProgressReportId(int progressReportId) {
        this.progressReportId = progressReportId;
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

    public String getProgressReportTime() {
        return progressReportTime;
    }

    public void setProgressReportTime(String progressReportTime) {
        this.progressReportTime = progressReportTime;
    }

    public String getProgressReportFileName() {
        return progressReportFileName;
    }

    public void setProgressReportFileName(String progressReportFileName) {
        this.progressReportFileName = progressReportFileName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
