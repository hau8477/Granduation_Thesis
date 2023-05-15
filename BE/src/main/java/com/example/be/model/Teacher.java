package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String teacherCode;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String teacherName;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String teacherDateOfBirth;
    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String teacherEmail;
    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String teacherPhoneNumber;
    @Column(columnDefinition = "bit(1)", nullable = false)
    private boolean teacherGender;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String teacherAddress;
    @Column(columnDefinition = "text", nullable = false)
    private String teacherImg;
    @Column(columnDefinition = "bit(1)")
    private Boolean flagDelete = false;

    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "faculty_id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "degree_id", referencedColumnName = "degree_id")
    private Degree degree;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonIgnore
    private Account account;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<Team> team;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<NotificationTeacher> notificationTeacherSet;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<ProgressReview> progressReviews;

    @OneToOne(mappedBy = "teacher")
    private Clazz clazz;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<Document> documentSet;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private Set<Answers> answersSet;


    public Teacher() {
    }
    public Teacher(String teacherCode, String teacherName, String teacherDateOfBirth, String teacherEmail, String teacherPhoneNumber, boolean teacherGender, String teacherAddress, String teacherImg, Faculty faculty, Degree degree) {
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

    public Teacher(Long teacherId, String teacherCode, String teacherName, String teacherDateOfBirth, String teacherEmail, String teacherPhoneNumber, boolean teacherGender, String teacherAddress, String teacherImg, Faculty faculty, Degree degree) {
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

    public Set<Answers> getAnswersSet() {
        return answersSet;
    }

    public void setAnswersSet(Set<Answers> answersSet) {
        this.answersSet = answersSet;
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

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Team> getTeam() {
        return team;
    }

    public void setTeam(Set<Team> team) {
        this.team = team;
    }

    public Set<NotificationTeacher> getNotificationTeacherSet() {
        return notificationTeacherSet;
    }

    public void setNotificationTeacherSet(Set<NotificationTeacher> notificationTeacherSet) {
        this.notificationTeacherSet = notificationTeacherSet;
    }

    public Set<ProgressReview> getProgressReviews() {
        return progressReviews;
    }

    public void setProgressReviews(Set<ProgressReview> progressReviews) {
        this.progressReviews = progressReviews;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Set<Document> getDocumentSet() {
        return documentSet;
    }

    public void setDocumentSet(Set<Document> documentSet) {
        this.documentSet = documentSet;
    }
}

