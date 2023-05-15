package com.example.be.dto;
/**
 * Created by: VuLX
 * Date created: 01/04/2023
 */

public class ProgressDto {
    private Long projectId;
    private String teamName;
    private String projectName;
    private int memberTotal;
    private Boolean status;

    public ProgressDto() {
    }

    public ProgressDto(Long projectId, String teamName, String projectName, int memberTotal, Boolean status) {
        this.projectId = projectId;
        this.teamName = teamName;
        this.projectName = projectName;
        this.memberTotal = memberTotal;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(int memberTotal) {
        this.memberTotal = memberTotal;
    }
}
