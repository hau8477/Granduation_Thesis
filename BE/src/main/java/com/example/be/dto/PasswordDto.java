package com.example.be.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Created by: TienP
 * Date created: 31/03/2023
 * Class: PasswordDto
 */
public class PasswordDto implements Validator {
    private String username;
    @NotBlank(message = "Không được để trống")
    private String oldPassword;

    @Min(value = 8, message = "không được ít hơn 8 kí tự")
    private String newPassword;
    @NotBlank(message = "Không được để trống")
    @Min(value = 8, message = "không được ít hơn 8 kí tự")
    private String passwordConfirm;

    public PasswordDto() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordDto passwordDto = (PasswordDto) target;
        String newPassword = passwordDto.getNewPassword();
        String passwordConfirm = passwordDto.getPasswordConfirm();
        if (!newPassword.equals(passwordConfirm)) {
            errors.rejectValue("passwordConfirm", "passwordError1", "Nhập lại mật khẩu mới trùng khớp");
        }
    }
}
