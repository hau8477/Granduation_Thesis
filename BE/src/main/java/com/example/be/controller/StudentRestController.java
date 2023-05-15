package com.example.be.controller;

import com.example.be.dto.IStudentDTO;
import com.example.be.dto.StudentDto;
import com.example.be.dto.StudentDto1;
import com.example.be.dto.StudentInfo;
import com.example.be.model.Clazz;
import com.example.be.model.Student;
import com.example.be.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/students")
public class StudentRestController {
    @Autowired
    private IStudentService studentService;

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all student by name containing or code containing
     *
     * @return HttpStatus.OK if result is not error, return HttpStatus.NO_CONTENT if result is empty
     * @Param: searchStr, size, page
     */
    @GetMapping("/")
    public ResponseEntity<Page<Student>> findAllByNameOrStudentCode
    (@RequestParam String searchStr,
     @RequestParam int size,
     @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = this.studentService.findAllByNameOrStudentCode(searchStr, pageable);

        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all student by team id
     *
     * @return HttpStatus.OK if result is not error, return HttpStatus.NO_CONTENT if result is empty
     * @Param: teamId, size, page
     */
    @GetMapping("/team/{teamId}/{page}/{size}")
    public ResponseEntity<Page<Student>> findAllByTeamId
    (@PathVariable Long teamId,
     @PathVariable int page,
     @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = this.studentService.findAllByTeamId(teamId, pageable);

        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find student by id
     *
     * @return HttpStatus.OK if result is not error, return HttpStatus.NO_CONTENT if result is null
     * @Param: teamId, size, page
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
        Student student = this.studentService.findById(id);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: updateLeader
     *
     * @return HttpStatus.OK if result is not error, return HttpStatus.NO_CONTENT if result is null
     * @Param: studentId, teamId
     */
    @PostMapping("/updateLeader/{studentId}/{teamId}")
    public ResponseEntity<Student> updateLeaderteam(@PathVariable Long studentId,
                                                    @PathVariable Long teamId) {
        Student student = this.studentService.updateLeader(studentId, teamId);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: sendMailInviteTeam
     *
     * @Param: studentId, teamId
     */
    @PostMapping("/send-mail-invite-team/{teamId}")
    public ResponseEntity<?> sendMailInviteTeam(@RequestBody List<Student> students,
                                                @PathVariable Long teamId) {
        try {
            if (this.studentService.sendMailInviteTeam(students, "THÔNG BÁO", "", teamId)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "accept-join-group/{studentId}/{teamId}", method = RequestMethod.POST)
    public ResponseEntity<?> acceptJoinGroup(@PathVariable("studentId") Long studentId,
                                             @PathVariable("teamId") Long teamId) {

        if (this.studentService.updateMember(studentId, teamId) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "deny-join-group", method = RequestMethod.POST)
    public ResponseEntity<?> denyJoinGroup() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody StudentDto studentDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        String studentCode = "sv-00" + (studentService.maxIdStudent() + 1);

        System.out.println(studentCode);
        student.setStudentCode(studentCode);
        studentService.saveStudent(student);
//        studentService.addStudent(student.getStudentName(), studentCode,
//                student.getStudentDateOfBirth(), student.getStudentEmail(), student.getStudentPhoneNumber(),
//                student.isStudentGender(), student.getStudentAddress(), student.getStudentImg(), student.getClazz().getClazzId());

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findById(@PathVariable long studentId) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
    }

    @GetMapping("/getStd/{studentId}")
    public ResponseEntity<StudentDto> findStudentsById(@PathVariable long studentId) {
        StudentDto studentDto = new StudentDto();
        IStudentDTO student = studentService.findStudentsById(studentId);
        studentDto.setStudentId(student.getStudentId());
        studentDto.setStudentName(student.getStudentName());
        studentDto.setStudentDateOfBirth(student.getStudentDateOfBirth());
        studentDto.setStudentEmail(student.getStudentEmail());
        studentDto.setStudentPhoneNumber(student.getStudentPhoneNumber());
        studentDto.setStudentAddress(student.getStudentPhoneNumber());
        studentDto.setStudentGender(student.getStudentGender());
        studentDto.setStudentCode(student.getStudentCode());
        studentDto.setStudentImg(student.getStudentImg());
        Clazz clazz = new Clazz(student.getClazzId(), student.getClazzName());
        studentDto.setClazz(clazz);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/update/{studentId}")
    public ResponseEntity<Student> updateStudent(@Validated @RequestBody StudentDto studentDto, @PathVariable long studentId, BindingResult bindingResult) {
        Student student = new Student();
        IStudentDTO studentDTO = studentService.findStudentsById(studentId);
        if (studentDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            BeanUtils.copyProperties(studentDto, student);
            studentService.updateStudent(studentId, student);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Create by : VinhLD
     * Date create : 29/3/2023
     * Function: show list student
     *
     * @param nameSearch
     * @param pageable
     * @return HttpStatus.OK if connect to database return json list student or HttpStatus.NOT_FOUND if list student is empty
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<Page<StudentDto1>> getAllStudent(@RequestParam(value = "nameSearch", defaultValue = "") String nameSearch,
                                                           @PageableDefault(size = 4) Pageable pageable) {
        Page<StudentDto1> studentDtos = studentService.getStudentList(pageable, nameSearch);
        if (studentDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }

    /**
     * Create by : Vinh LD
     * Date create: 29/3/2023
     * Function: show the instructor's list of students
     *
     * @param nameSearch
     * @param pageable
     * @param teacherId
     * @return HttpStatus.OK if connect to database return json the instructor's list of students or HttpStatus.NOT_FOUND if the instructor's list of students is empty
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TEACHER')")
//    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_TEACHER')")
    @GetMapping("/list-id-teacher/{teacherId}")
    public ResponseEntity<Page<StudentInfo>> getStudentListIdTeacher(@RequestParam(value = "nameSearch", defaultValue = "") String nameSearch,
                                                                     @PageableDefault(size = 4) Pageable pageable,
                                                                     @PathVariable Long teacherId) {
        Page<StudentInfo> studentInfos = studentService.findAllStudent(pageable, nameSearch, teacherId);
        if (studentInfos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentInfos, HttpStatus.OK);
    }

    /**
     * Created by: TienP
     * Date: 29/03/2023
     * Function: findStudentByEmail(email)
     *
     * @Return: new ResponseEntity<>(HttpStatus.BAD_REQUEST) if result is error,
     * else new ResponseEntity<>(student, HttpStatus.OK)
     */
    @GetMapping("/details/{email}")
    public ResponseEntity<Student> findStudentByEmail(@PathVariable String email) {
        Student student = studentService.findStudentByEmail(email);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
