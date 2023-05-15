package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String studentName;
    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String studentCode;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String studentDateOfBirth;
    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String studentEmail;
    @Column(columnDefinition = "varchar(50)", nullable = false, unique = true)
    private String studentPhoneNumber;
    @Column(columnDefinition = "bit(1)", nullable = false)
    private boolean studentGender;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String studentAddress;
    @Column(columnDefinition = "text", nullable = false)
    private String studentImg;
    @Column(columnDefinition = "bit(1)")
    private boolean flagDelete;
    @Column(columnDefinition = "bit(1)")
    private boolean flagLeader;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JsonIgnore
    private Account account;
    @ManyToOne
    @JoinColumn(name = "clazz_id", referencedColumnName = "clazz_id")
    @JsonIgnore
    private Clazz clazz;
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<StudentAnnouncement> studentAnnouncementSet;
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Question> questionSet;

    public boolean isFlagLeader() {
        return flagLeader;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public void setFlagLeader(boolean flagLeader) {
        this.flagLeader = flagLeader;
    }

    public Student() {
    }

    public Set<StudentAnnouncement> getStudentAnnouncementSet() {
        return studentAnnouncementSet;
    }

    public void setStudentAnnouncementSet(Set<StudentAnnouncement> studentAnnouncementSet) {
        this.studentAnnouncementSet = studentAnnouncementSet;
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

    public void setStudentDateOfBirth(String dateOfBirth) {
        this.studentDateOfBirth = dateOfBirth;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String email) {
        this.studentEmail = email;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String phoneNumber) {
        this.studentPhoneNumber = phoneNumber;
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

    public void setStudentImg(String img) {
        this.studentImg = img;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}
