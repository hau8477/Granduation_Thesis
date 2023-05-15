package com.example.be.dto;

import com.example.be.model.Degree;
import com.example.be.model.Faculty;
import com.example.be.model.Teacher;
import org.springframework.validation.Errors;

import java.util.List;

public class TeacherDTO {
    private Long teacherId;
    private String teacherCode;
    private String teacherName;
    private String teacherDateOfBirth;
    private String teacherEmail;
    private String teacherPhoneNumber;
    private boolean teacherGender;
    private String teacherAddress;
    private String teacherImg;
    private Faculty faculty;
    private Degree degree;

    public TeacherDTO(String teacherCode, String teacherName, String teacherDateOfBirth, String teacherEmail, String teacherPhoneNumber, boolean teacherGender, String teacherAddress, String teacherImg, Faculty faculty, Degree degree) {
        this.teacherCode = teacherCode;
        this.teacherName = teacherName;
        this.teacherDateOfBirth = teacherDateOfBirth;
        this.teacherEmail = teacherEmail;
        this.teacherPhoneNumber = teacherPhoneNumber;
        this.teacherGender = teacherGender;
        this.teacherAddress = teacherAddress;
        this.teacherImg = teacherImg;
        this.faculty = faculty;
        this.degree = degree;
    }

    public TeacherDTO(Long teacherId, String teacherCode, String teacherName, String teacherDateOfBirth, String teacherEmail, String teacherPhoneNumber, boolean teacherGender, String teacherAddress, String teacherImg, Faculty faculty, Degree degree) {
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.teacherName = teacherName;
        this.teacherDateOfBirth = teacherDateOfBirth;
        this.teacherEmail = teacherEmail;
        this.teacherPhoneNumber = teacherPhoneNumber;
        this.teacherGender = teacherGender;
        this.teacherAddress = teacherAddress;
        this.teacherImg = teacherImg;
        this.faculty = faculty;
        this.degree = degree;
    }

    public TeacherDTO() {
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherDateOfBirth() {
        return teacherDateOfBirth;
    }

    public void setTeacherDateOfBirth(String teacherDateOfBirth) {
        this.teacherDateOfBirth = teacherDateOfBirth;
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

    public boolean isTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(boolean teacherGender) {
        this.teacherGender = teacherGender;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherImg() {
        return teacherImg;
    }

    public void setTeacherImg(String teacherImg) {
        this.teacherImg = teacherImg;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public void checkValidateCreateTeacher(List<Teacher> list, TeacherDTO teacherDTO, Errors errors) {
        if (teacherDTO.getTeacherName().matches("")) {
            errors.rejectValue("teacherName", "teacherName", "Tên giáo viên không được để trống");
        } else if (!teacherDTO.getTeacherName().matches("^(\\p{Lu}[\\p{L}]*\\s?)+$")) {
            errors.rejectValue("teacherName", "teacherName", "Tên giáo viên sai định dạng");
        } else {
            int minLength = teacherDTO.getTeacherName().length();
            int maxLength = teacherDTO.getTeacherName().length();
            if (minLength <= 1 || maxLength >= 50) {
                errors.rejectValue("teacherName", "teacherName", "Tên giáo viên từ 1->50 kí tự");
            }
        }


        if (teacherDTO.getTeacherDateOfBirth().matches("")) {
            errors.rejectValue("teacherDateOfBirth", "teacherDateOfBirth", "Ngày sinh không được để trống");
        }

        if (teacherDTO.getDegree().getDegreeName().equals("")) {
            errors.rejectValue("degree", "degree", "Học vị không được để trống");
        }

        if (teacherDTO.getTeacherAddress().matches("")) {
            errors.rejectValue("teacherAddress", "teacherAddress", "Địa chỉ không được để trống");
        }else {
            int minLength = teacherDTO.getTeacherAddress().length();
            int maxLength = teacherDTO.getTeacherAddress().length();
            if (minLength <= 1 || maxLength >= 50) {
                errors.rejectValue("teacherAddress", "teacherAddress", "Địa chỉ từ 1->50 kí tự");
            }
        }

        if (teacherDTO.getTeacherPhoneNumber().matches("")) {
            errors.rejectValue("teacherPhoneNumber", "teacherPhoneNumber", "Số điện thoại không được để trống");
        } else if (!teacherDTO.getTeacherPhoneNumber().matches("^(09|08)\\d{8}$|^(\\+84)\\d{9}$  ")) {
            errors.rejectValue("teacherPhoneNumber", "teacherPhoneNumber", "Số điện thoại bắt đầu bằng 0 và phải có 10 số");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (teacherDTO.getTeacherPhoneNumber().equals(list.get(i).getTeacherPhoneNumber())) {
                    errors.rejectValue("teacherPhoneNumber", "teacherPhoneNumber", "Số điện thoại đã được sử dụng");
                    break;
                }
            }
        }

        if (teacherDTO.getFaculty().getFacultyName().equals("")) {
            errors.rejectValue("faculty", "faculty", "Học vị không được để trống");
        }

        if (teacherDTO.getTeacherEmail().matches("")) {
            errors.rejectValue("teacherEmail", "teacherEmail", "Email không được để trống");
        } else if (!teacherDTO.getTeacherEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            errors.rejectValue("teacherEmail", "teacherEmail", "Email sai định dạng");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (teacherDTO.getTeacherEmail().equals(list.get(i).getTeacherEmail())) {
                    errors.rejectValue("teacherEmail", "teacherEmail", "Email đã được sử dụng");
                    break;
                }
            }
        }
    }

    public void checkValidateUpdateTeacher(List<Teacher> list, TeacherDTO teacherDTO, Errors errors) {
        if (teacherDTO.getTeacherName().matches("")) {
            errors.rejectValue("teacherName", "teacherName", "Tên giáo viên không được để trống");
        }else if (!teacherDTO.getTeacherName().matches("^(\\p{Lu}[\\p{L}]*\\s?)+$")) {
            errors.rejectValue("teacherName", "teacherName", "Tên giáo viên sai định dạng");
        } else {
            int minLength = teacherDTO.getTeacherName().length();
            int maxLength = teacherDTO.getTeacherName().length();
            if (minLength <= 1 || maxLength >= 50) {
                errors.rejectValue("teacherName", "teacherName", "Tên giáo viên từ 1->50 kí tự");
            }
        }

        if (teacherDTO.getTeacherDateOfBirth().matches("")) {
            errors.rejectValue("teacherDateOfBirth", "teacherDateOfBirth", "Ngày sinh không được để trống");
        }

        if (teacherDTO.getDegree().getDegreeName().matches("")) {
            errors.rejectValue("degree", "degree", "Học vị không được để trống");
        }

        if (teacherDTO.getTeacherAddress().matches("")) {
            errors.rejectValue("teacherAddress", "teacherAddress", "Địa chỉ không được để trống");
        }else {
            int minLength = teacherDTO.getTeacherAddress().length();
            int maxLength = teacherDTO.getTeacherAddress().length();
            if (minLength <= 1 || maxLength >= 50) {
                errors.rejectValue("teacherAddress", "teacherAddress", "Địa chỉ từ 1->50 kí tự");
            }
        }

        if (teacherDTO.getTeacherPhoneNumber().matches("")) {
            errors.rejectValue("teacherPhoneNumber", "teacherPhoneNumber", "Số điện thoại không được để trống");
        } else if (!teacherDTO.getTeacherPhoneNumber().matches("^(09|08)\\d{8}$|^(\\+84)\\d{9}$")) {
            errors.rejectValue("teacherPhoneNumber", "teacherPhoneNumber", "Số điện thoại bắt đầu bằng 0 và phải có 10 số");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (teacherDTO.getTeacherId().equals(list.get(i).getTeacherId())) {
                    continue;
                }
                if (teacherDTO.getTeacherPhoneNumber().equals(list.get(i).getTeacherPhoneNumber())) {
                    errors.rejectValue("teacherPhoneNumber", "teacherPhoneNumber", "Số điện thoại đã được sử dụng");
                    break;
                }
            }

        }
        if (teacherDTO.getFaculty().getFacultyName().equals("")) {
            errors.rejectValue("faculty", "faculty", "Khoa không được để trống");
        }

        if (teacherDTO.getTeacherEmail().matches("")) {
            errors.rejectValue("teacherEmail", "teacherEmail", "Email không được để trống");
        } else if (!teacherDTO.getTeacherEmail().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            errors.rejectValue("teacherEmail", "teacherEmail", "Email sai định dạng");
        } else {

            for (int i = 0; i < list.size(); i++) {
                if (teacherDTO.getTeacherId().equals(list.get(i).getTeacherId())) {
                    continue;
                }
                if (teacherDTO.getTeacherEmail().equals(list.get(i).getTeacherEmail())) {
                    errors.rejectValue("teacherEmail", "teacherEmail", "Email đã được sử dụng");
                    break;
                }
            }
        }
    }
}