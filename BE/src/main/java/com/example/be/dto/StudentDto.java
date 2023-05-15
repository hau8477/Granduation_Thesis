package com.example.be.dto;

import com.example.be.model.Clazz;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;

public class StudentDto implements Validator {
    private  Long studentId;

    @NotBlank(message = "không được để trống")
    private String studentName;

    @NotBlank(message = "không được để trống")
    private String studentCode;

    @NotBlank(message = "không được để trống")
    private String studentDateOfBirth;

    @NotBlank(message = "không được để trống")
    private String studentEmail;

    @NotBlank(message = "không được để trống")
    private String studentPhoneNumber;

//    @NotBlank(message = "không được để trống")
    private boolean studentGender;

    @NotBlank(message = "không được để trống")
    private String studentAddress;

    @NotBlank(message = "không được để trống")
    private String studentImg;

//    @NotBlank(message = "không được để trống")
    private Clazz clazz;


    public StudentDto() {
    }

    public StudentDto(Long studentId, String studentName, String studentCode, String studentDateOfBirth, String studentEmail, String studentPhoneNumber, boolean studentGender, String studentAddress, String studentImg, Clazz clazz) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCode = studentCode;
        this.studentDateOfBirth = studentDateOfBirth;
        this.studentEmail = studentEmail;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentGender = studentGender;
        this.studentAddress = studentAddress;
        this.studentImg = studentImg;
        this.clazz = clazz;
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentDateOfBirth() {
        return studentDateOfBirth;
    }

    public void setStudentDateOfBirth(String studentDateOfBirth) {
        this.studentDateOfBirth = studentDateOfBirth;
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

    public boolean isStudentGender() {
        return studentGender;
    }

    public void setStudentGender(boolean studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentImg() {
        return studentImg;
    }

    public void setStudentImg(String studentImg) {
        this.studentImg = studentImg;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
