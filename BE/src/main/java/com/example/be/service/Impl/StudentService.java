package com.example.be.service.Impl;

import com.example.be.dto.*;
import com.example.be.model.*;
import com.example.be.repository.*;
import com.example.be.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private IProjectRepository iProjectRepository;

    @Autowired
    private ITeamRepository teamRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IAnnouncementRepository announcementRepository;
    @Autowired
    private IStudentAnnouncementRepository studentAnnouncementRepository;
    @Autowired
    private IAccountRoleRepository accountRoleRepository;

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all student by name containing or code containing
     *
     * @return list page student if result is not error else return null
     * @Param: searchStr, pageable
     */
    @Override
    public Page<Student> findAllByNameOrStudentCode(String searchStr, Pageable pageable) {
        return this.studentRepository.findAllByNameOrStudentCode(searchStr, pageable);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all student by team id
     *
     * @return list page student if result is not error else return null
     * @Param: teamId, pageable
     */
    @Override
    public Page<Student> findAllByTeamId(Long teamId, Pageable pageable) {
        return this.studentRepository.findAllByTeamId(teamId, pageable);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find student by id
     *
     * @return student if result is not null else return null
     * @Param: id
     */
    @Override
    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: updateLeader team
     *
     * @return student or null
     * @Param: studentId, teamId
     */
    @Override
    public Student updateLeader(Long studentId, Long teamId) {
        Student student = this.studentRepository.findById(studentId).orElse(null);
        Team team = this.teamRepository.findById(teamId).orElse(null);

        if (student == null || team == null || student.getTeam() != null) {
            return null;
        }

        student.setTeam(team);
        student.setFlagLeader(true);
        return this.studentRepository.save(student);
    }

    @Override
    public Student updateMember(Long studentId, Long teamId) {
        Student student = this.studentRepository.findById(studentId).orElse(null);
        Team team = this.teamRepository.findById(teamId).orElse(null);


        if (student == null || team == null || student.getTeam() != null) {
            return null;
        }

        student.setTeam(team);
        return this.studentRepository.save(student);
    }


    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: sendMailInviteTeam
     *
     * @Param: studentId, teamId
     */
    public boolean sendMailInviteTeam(List<Student> students, String subject, String text, Long teamId) throws MessagingException {
        Team team = this.teamRepository.findById(teamId).orElse(null);
        if (team == null) {
            return false;
        }
        LocalDateTime now = LocalDateTime.now();

        Announcement announcement = new Announcement();
        announcement.setAnnouncementName(subject);
        announcement.setAnnouncementContent("Bạn có thư mời tham gia nhóm " + team.getTeamName());
        announcement.setAnnouncementTime("" + now);
        announcement.setAttach("" + teamId);

        Announcement announcementNew = this.announcementRepository.save(announcement);

        for (Student student : students) {
            String mail = student.getStudentEmail();
            if (mail == null) {
                return false;
            }

            StudentAnnouncement studentAnnouncement = new StudentAnnouncement();
            studentAnnouncement.setStudent(student);
            studentAnnouncement.setAnnouncement(announcementNew);
            this.studentAnnouncementRepository.save(studentAnnouncement);

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("hau8477@gmail.com");
            helper.setTo(mail);
            helper.setSubject(subject);

            String htmlContent = "Xin chào <b>" + student.getStudentName() + "!</b> Bạn có thư mời tham gia nhóm <b>" +
                    team.getTeamName() + ".</b> Vui lòng truy cập vào trang Quản lý đề tài khoa CNTT để biết thêm chi tiết." +
                    " http://localhost:4200/";

            String signature = "<br><br><b>Thanks and best regards!</b>. <br><b>Địa chỉ:</b> 123 Nguyễn Văn Linh, Đà Nẵng" +
                    "<br><b>Email:</b> codegym@gmail.com<br><b>Điện thoại:</b> +012 345 67890,<br><b>Codegym</b>";
            htmlContent += signature;

            helper.setText(htmlContent, true);

            emailSender.send(message);
        }
        return true;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    @Override
    public void addStudent(String studentName, String studentCode,
                           String studentDateOfBirth, String studentEmail,
                           String studentPhoneNumber, boolean studentGender,
                           String studentAddress, String studentImg, Long clazzId) {
        Account accountNew = new Account();
        accountNew.setUsername(studentEmail);
        accountNew.setPassword("$2y$12$gnE2.7QQxbey9VLhkotlh.GCiU/ozj25mIghi4LVGs4uVEdh4OkfW");
        Account accountSave = this.accountRepository.save(accountNew);
        studentRepository.addStudent(studentName, studentCode, studentDateOfBirth, studentEmail, studentPhoneNumber, studentGender, studentAddress, studentImg, clazzId);
    }

    @Override
    public IStudentDTO findStudentsById(long studentId) {
        return studentRepository.findStudentById(studentId);
    }

    @Override
    public void updateStudent(long studentId, Student student) {
        studentRepository.updateStudent(student.getStudentName(), student.getStudentCode(),
                student.getStudentDateOfBirth(), student.getStudentEmail(),
                student.getStudentPhoneNumber(), student.isStudentGender(), student.getStudentAddress(),
                student.getStudentImg(), student.getClazz().getClazzId(), studentId);
    }

    @Override
    public Long maxIdStudent() {
        return studentRepository.getStudentId();
    }

    /**
     * Create by : VinhLD
     * Date create 29/03/2023
     * Function: show list student
     *
     * @param pageable
     * @param nameSearch
     * @return json list student
     */
    @Override
    public Page<StudentDto1> getStudentList(Pageable pageable, String nameSearch) {
        return studentRepository.getStudentList(pageable, nameSearch);
    }

    @Override
    public List<IMailStudentDto> getInfomation(Long teamId) {
        return studentRepository.getInfomation(teamId);
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    public String getTeam(long projectId) {
        // Thực hiện truy vấn cơ sở dữ liệu để lấy thông tin về đề tài
        Project project = iProjectRepository.findProById(projectId);
        if (project == null) {
            return "hi";
        } else {
            return project.getTeam().getTeamName();
        }
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    public String getProjectTitle(long projectId) {
        // Thực hiện truy vấn cơ sở dữ liệu để lấy thông tin về đề tài
        Project project = iProjectRepository.findProById(projectId);
        if (project != null) {
            // Trả về tên của đề tài
            return project.getProjectName();
        } else {
            return null;
        }
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    public void sendSimpleMessage(
            List<IMailStudentDto> mailList, String subject, String text, long projectId) {
        String[] arrayEmail = new String[mailList.size()];
        for (int i = 0; i < mailList.size(); i++) {
            arrayEmail[i] = mailList.get(i).getEmail();
        }
        String projectTitle = getProjectTitle(projectId);
        String teamName = getTeam(projectId);
        if (projectTitle != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ngochann.1603@gmail.com");
            message.setTo(arrayEmail);
            message.setSubject(subject);
            message.setText("Xin chào các thành viên" + teamName
                    + "\nĐề tài " + projectTitle + "của các bạn"
                    + "\nquá ok được duyệt.");
            emailSender.send(message);
        }
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    public void sendSimpleMessage2(
            List<IMailStudentDto> mailList, String subject, String text, long projectId) {
        String[] arrayEmail = new String[mailList.size()];
        for (int i = 0; i < mailList.size(); i++) {
            arrayEmail[i] = mailList.get(i).getEmail();
        }
        String projectTitle = getProjectTitle(projectId);
        String teamName = getTeam(projectId);
        if (projectTitle != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ngochann.1603@gmail.com");
            message.setTo(arrayEmail);
            message.setSubject(subject);
            message.setText("Xin chào các thành viên " + teamName
                    + "\nĐề tài " + projectTitle + "của các bạn"
                    + "\nbị từ chối duyệt. ");
            emailSender.send(message);
        }
    }

    /**
     * Create by : VinhLD
     * Date create 29/03/2023
     * Function: show the instructor's list of students
     *
     * @param pageable
     * @param nameSearch
     * @param idTeacher
     * @return json the instructor's list of students
     */
    @Override
    public Page<StudentInfo> findAllStudent(Pageable pageable, String nameSearch, Long idTeacher) {
        return studentRepository.findAllStudent(pageable, nameSearch, idTeacher);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return this.studentRepository.findStudentByEmail(email);
    }

    @Override
    public Student saveStudent(Student student) {
        Account accountNew = new Account();
        accountNew.setUsername(student.getStudentEmail());
        accountNew.setPassword("$2y$12$gnE2.7QQxbey9VLhkotlh.GCiU/ozj25mIghi4LVGs4uVEdh4OkfW");
        Account account = accountRepository.save(accountNew);

        student.setAccount(accountNew);
        student.setFlagDelete(false);
        student.setFlagLeader(false);

        Role role = new Role();
        role.setRoleId(2);

        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(account);
        accountRole.setRole(role);
        this.accountRoleRepository.save(accountRole);

        return studentRepository.save(student);
    }
}
