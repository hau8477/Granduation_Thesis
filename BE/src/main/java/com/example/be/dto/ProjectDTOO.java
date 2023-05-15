package com.example.be.dto;

import com.example.be.model.ProgressReport;
import com.example.be.model.Team;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class ProjectDTOO {
    private Long projectId;
    @Size(max = 250, message = "Tên đề tài không được dài quá 250 kí tự")
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Phải nhập trường này")
    @Pattern(regexp = "^[A-Z][\\p{L}\\d\\s]*$",
            message =
                    "Tên đề tài không được chứa kí tự đặc biệt, " +
                            "chữ cái đầu tiên phải viết hoa")
    private String name;
    @Size(max = 2000, message = "Nội dung miêu tả không được dài quá 2000 kí tự")
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Phải nhập trường này")
    private String content;
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Phải nhập trường này")
    private String img;
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Phải nhập trường này")
    private String description;
    private Boolean projectStatus;
    private Set<ProgressReport> progressReportSet;
    private Team team;

    public ProjectDTOO(Long projectId, String name, String content, String img, String description, Boolean projectStatus, Set<ProgressReport> progressReportSet, Team team) {
        this.projectId = projectId;
        this.name = name;
        this.content = content;
        this.img = img;
        this.description = description;
        this.projectStatus = projectStatus;
        this.progressReportSet = progressReportSet;
        this.team = team;
    }

    public ProjectDTOO() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Boolean projectStatus) {
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
}
