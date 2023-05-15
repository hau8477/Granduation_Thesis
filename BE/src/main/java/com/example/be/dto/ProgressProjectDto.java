package com.example.be.dto;
/**
 * Created by: VuLX
 * Date created: 01/04/2023
 */

public interface ProgressProjectDto {
    Long getProjectId();

    String getTeamName();

    String getProjectName();

    String getMemberTotal();

    Boolean getStatus();
}
