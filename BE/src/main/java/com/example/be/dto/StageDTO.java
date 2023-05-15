package com.example.be.dto;


public class StageDTO {

    private int stageId;

    private String stageName;



    public StageDTO(int stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;

    }

    public StageDTO() {
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
