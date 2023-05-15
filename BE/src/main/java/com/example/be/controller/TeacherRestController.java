package com.example.be.controller;

import com.example.be.dto.UserRoleAdminDto;
import com.example.be.dto.teacher.*;
import com.example.be.model.Degree;
import com.example.be.model.Faculty;
import com.example.be.model.Teacher;
import com.example.be.service.IAccountService;
import com.example.be.service.IDegreeService;
import com.example.be.service.IFacultyService;
import com.example.be.service.ITeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.example.be.dto.ITeacherDto;
import com.example.be.service.Impl.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/teachers")
public class TeacherRestController {
    @Autowired
    private IDegreeService iDegreeService;
    @Autowired
    private IFacultyService iFacultyService;
    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private IAccountService accountService;

    /**
     * Create by: TanNN
     * Date created: 31/03/2023
     * Function: getAllDegree
     *
     * @return HttpStatus.OK when the data is saved to the database, HttpStatus.BAD_REQUEST when an error occurs
     */
    @GetMapping("/degreeAll")
    public ResponseEntity getAllDegree() {
        List<IDegreeDTO> listDegree = iDegreeService.getAllDegree();
        if (listDegree == null) {
            return new ResponseEntity(listDegree, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(listDegree, HttpStatus.OK);
    }

    /**
     * Create by: TanNN
     * Date created: 31/03/2023
     * Function: getAllFaculty
     *
     * @return HttpStatus.OK when the data is saved to the database, HttpStatus.BAD_REQUEST when an error occurs
     */
    @GetMapping("/facultyAll")
    public ResponseEntity getAllFaculty() {
        List<IFacultyDTO> listDegree = iFacultyService.getAllFaculty();
        if (listDegree == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(listDegree, HttpStatus.OK);
    }

    /**
     * Create by: TanNN
     * Date created: 31/03/2023
     * Function: getTeacher
     *
     * @param id
     * @return HttpStatus.OK when the data is saved to the database, HttpStatus.BAD_REQUEST when an error occurs
     */
    @GetMapping("/detailTeacher/{id}")
    public ResponseEntity getTeacher(@PathVariable("id") Long id) {
        Teacher teacher = new Teacher();
        ITeacherDTO teacherDTO = iTeacherService.getTeacher(id);
        if (teacherDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        teacher.setTeacherId(teacherDTO.getTeacherId());
        teacher.setTeacherImg(teacherDTO.getTeacherImg());
        teacher.setTeacherName(teacherDTO.getTeacherName());
        teacher.setTeacherDateOfBirth(teacherDTO.getTeacherDateOfBirth());
        teacher.setDegree(new Degree(teacherDTO.getDegreeId(), teacherDTO.getDegreeName()));
        teacher.setTeacherAddress(teacherDTO.getTeacherAddress());

        if (teacherDTO.getTeacherGender()) {
            teacher.setTeacherGender(true);
        } else {
            teacher.setTeacherGender(false);
        }
        teacher.setTeacherPhoneNumber(teacherDTO.getTeacherPhoneNumber());
        teacher.setFaculty(new Faculty(teacherDTO.getFacultyId(), teacherDTO.getFacultyName()));
        teacher.setTeacherEmail(teacherDTO.getTeacherEmail());

        if (teacher.getTeacherId() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(teacher, HttpStatus.OK);
    }

    /**
     * Create by: TanNN
     * Date created: 31/03/2023
     * Function: createTeacher
     *
     * @param teacherDTO
     * @return HttpStatus.OK when the data is saved to the database, HttpStatus.BAD_REQUEST when an error occurs
     */
    @PostMapping("/createTeacher")
    public ResponseEntity<?> createTeacher(@Validated @RequestBody TeacherDTO teacherDTO, BindingResult bindingResult) {

        teacherDTO.checkValidateCreateTeacher(iTeacherService.findAll(), teacherDTO, bindingResult);
        System.out.println(iTeacherService.getAllPhoneNumberAndEmail());
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Teacher teacher = new Teacher();
        String teacherCode = "GV-00" + (iTeacherService.maxIdTeacher().getTeacherId() + 1);
        teacherDTO.setTeacherCode(teacherCode);
        BeanUtils.copyProperties(teacherDTO, teacher);
        iTeacherService.addTeacher(teacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: TanNN
     * Date created: 31/03/2023
     * Function: updateTeacher
     *
     * @param teacherDTO
     * @return HttpStatus.OK when the data is saved to the database, HttpStatus.BAD_REQUEST when an error occurs
     */
    @PatchMapping("/updateTeacher/{id}")
    public ResponseEntity updateTeacher(@PathVariable("id") Long id, @Validated @RequestBody TeacherDTO teacherDTO, BindingResult bindingResult) {
        teacherDTO.checkValidateUpdateTeacher(iTeacherService.findAll(), teacherDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        teacherDTO.setTeacherId(id);
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacherDTO, teacher);
        iTeacherService.updateTeacher(teacher);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAllPhoneNumberAndEmail")
    public ResponseEntity<?> listEmailAndPhoneNumber() {
        System.out.println(iTeacherService.getAllPhoneNumberAndEmail());
        List<Teacher> iEmailAndPhoneNumberDTOS = iTeacherService.findAll();

        return new ResponseEntity<>(iEmailAndPhoneNumberDTOS, HttpStatus.OK);
    }

    /**
     * create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : show list has paging and search
     *
     * @param name
     * @param pageable
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<Page<ITeacherDto>> getAllTeacher(@RequestParam(defaultValue = "", required = false) String name,
                                                           @PageableDefault(size = 4, page = 0) Pageable pageable) {
        Page<ITeacherDto> teacherPage = iTeacherService.getAllTeacher(name, pageable);
        if (teacherPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(teacherPage, HttpStatus.OK);
    }

    /**
     * create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : get teacher by id
     *
     * @param id
     * @return HttpStatus.NOT_FOUND if result is error or HttpStatus.OK if result is not error
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<ITeacherDto> getTeacherById(@PathVariable Long id) {
        Optional<ITeacherDto> categoryOptional = iTeacherService.findTeacherById(id);
        return categoryOptional.map(teacher -> new ResponseEntity<>(teacher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : delete teacher by id
     *
     * @param id
     * @return notFound if delete is error HttpStatus.OK if result is not error
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ITeacherDto> deleteTeacherById(@PathVariable Long id) {
        Optional<ITeacherDto> teacher = iTeacherService.findTeacherById(id);

        if (!teacher.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        iTeacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Created by: Phạm Tiến
     * Date: 29/03/2023
     * Function: find student by email
     * @Return: new ResponseEntity<>(HttpStatus.BAD_REQUEST) if result is error,
     * else new ResponseEntity<>(student, HttpStatus.OK)
     */

    @GetMapping("/detail/{email}")
    public ResponseEntity<Teacher> findTeacherByEmail(@PathVariable String email) {

        Teacher teacher = iTeacherService.findTeacherByEmail(email);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    /**
     * Created by: Phạm Tiến
     * Date: 29/03/2023
     * Function: update teacher with role admin
     * @Return: new ResponseEntity<>(HttpStatus.BAD_REQUEST) if result is error,
     * else new ResponseEntity<>(student, HttpStatus.OK)
     */
//    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping("/update-user-role-admin")
    public ResponseEntity<List<FieldError>> updateTeacherRoleAdmin(@RequestBody @Valid UserRoleAdminDto userRoleAdminDto,
                                                                   BindingResult bindingResult) {
        Teacher teacherUpdate = iTeacherService.getTeacherById(userRoleAdminDto.getTeacherId());
        if (teacherUpdate == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        } else {
            BeanUtils.copyProperties(userRoleAdminDto, teacherUpdate);
            iTeacherService.updateTeacherRoleAdmin(teacherUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
