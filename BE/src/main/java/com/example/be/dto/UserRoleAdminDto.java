package com.example.be.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;

/**
 * Created by: TienP
 * Date created: 31/03/2023
 * Class: UserRoleAdminDto
 */

public class UserRoleAdminDto implements Validator {
    private Long teacherId;
    @NotBlank(message = "Không được để trống hoặc chỉ chứa toàn kí tự trắng")
    @Pattern(regexp = "^[\\p{L}\\s]+$",
            message = "Tên không chứa kí tự đặc biệt và số")

    @Size(min = 3,max = 50, message = "Tên từ 3 đến 50 kí tự")
    private String teacherName;
    @NotBlank(message = "Không được để trống")
    @Email(message = "Không đúng định dạng email")
    private String teacherEmail;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(090[0-9]{7}|093[0-9]{7}|097[0-9]{7})$",
            message = "Số điện thoại phải gồm 10 số 090xxxxxxx, 093xxxxxxx, 097xxxxxxx")
    private String teacherPhoneNumber;
    @NotBlank(message = "Không được để trống")
    @Size(min = 5,max = 75, message = "Địa chỉ từ 5 đến 75 kí tự")
    private String teacherAddress;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }

    public UserRoleAdminDto() {
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }
}
