package com.example.be.service;

import com.example.be.dto.ITeacherDto;
import com.example.be.dto.teacher.IEmailAndPhoneNumberDTO;
import com.example.be.dto.teacher.ITeacherDTO;
import com.example.be.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface ITeacherService {
    Optional<Teacher> findByID(Long id);
    ITeacherDTO getTeacher(Long idTeacher);

    ITeacherDTO maxIdTeacher();

    List<Teacher> findAll();

    void addTeacher(Teacher teacher);

    void updateTeacher(Teacher teacher);

    List<IEmailAndPhoneNumberDTO> getAllPhoneNumberAndEmail();

    /**
     *create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : show list has paging and search
     * @param name
     * @param pageable
     * @return Page<ITeacherDto>
     */
    Page<ITeacherDto> getAllTeacher(String name, Pageable pageable);

    Page<Teacher> getAllTeacher1(String name, Pageable pageable);

    /**
     *create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : get teacher by id
     * @param id
     * @return Optional<ITeacherDto>
     */

    Optional<ITeacherDto> findTeacherById(Long id);

    Teacher getTeacherById(Long id);

    /**
     *create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : delete teacher by id
     * @param id
     * @return void
     */
    void deleteTeacherById(long id);

    Teacher findTeacherByEmail(String email);

    void updateTeacherRoleAdmin(Teacher teacher);
}
