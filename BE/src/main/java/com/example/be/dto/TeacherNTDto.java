package com.example.be.dto;

public class TeacherNTDto {
    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     */
    private Long studentId;
    private String studentName;

    public TeacherNTDto() {
    }

    public TeacherNTDto(Long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
