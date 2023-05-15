package com.example.be.dto;
/**
 * Created by: VuLX
 * Date created: 01/04/2023
 */
public class ProgressStudentDto {
    private String studentCode;
    private String studentName;
    private String studentEmail;
    private String studentPhoneNumber;
    private String studentImg;
    private String studentClassName;

    public ProgressStudentDto(String studentCode, String studentName, String studentEmail, String studentPhoneNumber, String studentImg, String studentClassName) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentImg = studentImg;
        this.studentClassName = studentClassName;
    }

    public ProgressStudentDto() {
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public String getStudentImg() {
        return studentImg;
    }

    public void setStudentImg(String studentImg) {
        this.studentImg = studentImg;
    }
}
