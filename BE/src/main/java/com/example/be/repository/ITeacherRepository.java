package com.example.be.repository;

import com.example.be.dto.ITeacherDto;
import com.example.be.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.be.dto.teacher.IEmailAndPhoneNumberDTO;
import com.example.be.dto.teacher.ITeacherDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public interface ITeacherRepository extends JpaRepository<Teacher, Long> {


    @Query(value = "select teacher_id as teacherId,teacher_img as teacherImg,teacher_name as teacherName, teacher_date_of_birth as teacherDateOfBirth,d.degree_id as degreeId,d.degree_name as degreeName, teacher_address as teacherAddress,teacher_gender as teacherGender, teacher_phone_number as teacherPhoneNumber, f.faculty_id as facultyId,f.faculty_name as facultyName, teacher_email  as teacherEmail from teacher join degree d on teacher.degree_id = d.degree_id join faculty f on teacher.faculty_id = f.faculty_id where teacher_id = :idTeacher",
            countQuery = "select teacher_id as teacherId,teacher_img as teacherImg,teacher_name as teacherName, teacher_date_of_birth as teacherDateOfBirth,d.degree_id as degreeId,d.degree_name as degreeName, teacher_address as teacherAddress,teacher_gender as teacherGender, teacher_phone_number as teacherPhoneNumber, f.faculty_id as facultyId,f.faculty_name as facultyName, teacher_email  as teacherEmail from teacher join degree d on teacher.degree_id = d.degree_id join faculty f on teacher.faculty_id = f.faculty_id where teacher_id = :idTeacher",
            nativeQuery = true)
    ITeacherDTO getTeacher(@Param("idTeacher") Long idTeacher);

    @Query(value = "select MAX(teacher_id) as teacherId from teacher",
            countQuery = "select MAX(teacher_id) as teacherId from teacher",
            nativeQuery = true)
    ITeacherDTO maxIdTeacher();

    @Transactional
    @Modifying
    @Query(value = "insert into teacher(teacher_name,teacher_date_of_birth,degree_id,teacher_address,teacher_gender,teacher_phone_number,faculty_id,teacher_email, teacher_code,teacher_img) VALUES (:teacherName, :teacherDateOfBirth, :idDegree, :teacherAddress, :teacherGender, :teacherPhoneNumber, :facultyId, :teacherEmail, :teacherCode, :teacherImg)",
            nativeQuery = true)
    void addTeacher(@Param("teacherName") String teacherName,
                    @Param("teacherDateOfBirth") String teacherDateOfBirth,
                    @Param("idDegree") int idDegree,
                    @Param("teacherAddress") String teacherAddress,
                    @Param("teacherGender") boolean teacherGender,
                    @Param("teacherPhoneNumber") String teacherPhoneNumber,
                    @Param("facultyId") int facultyId,
                    @Param("teacherEmail") String teacherEmail,
                    @Param("teacherCode") String teacherCode,
                    @Param("teacherImg") String teacherImg);

    @Transactional
    @Modifying
    @Query(value = "update teacher" +
            "   set teacher_name          = :teacherName," +
            "    teacher_date_of_birth = :teacherDateOfBirth," +
            "    degree_id             = :degreeId," +
            "    teacher_address       = :teacherAddress," +
            "    teacher_gender        = :teacherGender," +
            "    teacher_phone_number  = :teacherPhoneNumber," +
            "    faculty_id            = :facultyId," +
            "    teacher_email         = :teacherEmail," +
            "    teacher_img           = :teacherImg" +
            "    where teacher_id = :teacherId",
            nativeQuery = true)
    void updateTeacher(@Param("teacherName") String teacherName,
                       @Param("teacherDateOfBirth") String teacherDateOfBirth,
                       @Param("degreeId") int degreeId,
                       @Param("teacherAddress") String teacherAddress,
                       @Param("teacherGender") boolean teacherGender,
                       @Param("teacherPhoneNumber") String teacherPhoneNumber,
                       @Param("facultyId") int facultyId,
                       @Param("teacherEmail") String teacherEmail,
                       @Param("teacherImg") String teacherImg,
                       @Param("teacherId") Long teacherId);

    @Query(value = "select teacher_id as teacherId, teacher_email as teacherEmail, teacher_phone_number as teacherPhoneNumber from teacher",
            countQuery = "select teacher_id as teacherId, teacher_email as teacherEmail, teacher_phone_number as teacherPhoneNumber from teacher",
            nativeQuery = true)
    List<IEmailAndPhoneNumberDTO> getAllPhoneNumberAndEmail();

    /**
     * create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : show list has paging and search
     *
     * @param name
     * @param pageable
     * @return Page<ITeacherDto>
     */
    @Query(value = "select `teacher`.teacher_id as teacherId,`teacher`.teacher_name as teacherName\n" +
            ",`teacher`.teacher_code as teacherCode, `teacher`.teacher_img as teacherImg,`teacher`.teacher_phone_number as teacherPhoneNumber\n" +
            ",`teacher`.teacher_email as teacherEmail, `faculty`.faculty_name as faculty, `degree`.degree_name as degree from teacher\n" +
            "             join `faculty` on `faculty`.faculty_id = `teacher`.faculty_id\n" +
            "             join `degree` on `degree`.degree_id = `teacher`.degree_id\n" +
            "             where teacher.teacher_name like concat('%',:nameSearch,'%') and teacher.flag_delete=0", nativeQuery = true)
    Page<ITeacherDto> getAllTeacher(@Param("nameSearch") String name, Pageable pageable);

    Page<Teacher> findAllByTeacherNameContaining(String name, Pageable pageable);

    /**
     * create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : get teacher by id
     *
     * @param id
     * @return Optional<ITeacherDto>
     */
    @Query(value = "select `teacher`.teacher_id as teacherId,`teacher`.teacher_name as teacherName\n" +
            ",`teacher`.teacher_code as teacherCode, `teacher`.teacher_img as teacherImg,`teacher`.teacher_phone_number as teacherPhoneNumber\n" +
            ",`teacher`.teacher_email as teacherEmail,`teacher`.teacher_address as teacherAddress,`teacher`.teacher_date_of_birth as teacherDateOfBirth," +
            "`teacher`.teacher_gender as teacherGender, `faculty`.faculty_name as faculty, `degree`.degree_name as degree from teacher\n" +
            "             join `faculty` on `faculty`.faculty_id = `teacher`.faculty_id\n" +
            "             join `degree` on `degree`.degree_id = `teacher`.degree_id\n" +
            "where teacher.teacher_id = :id and teacher.flag_delete=0;", nativeQuery = true)
    Optional<ITeacherDto> findTeacherById(@Param("id") Long id);

    /**
     * create by : HungPV ,
     * Date Create : 29/03/2023
     * Function : delete teacher by id
     *
     * @param teacherId
     * @return void
     */
    @Transactional
    @Modifying
    @Query(value = "update teacher set teacher.flag_delete = 1 where teacher_id = :teacherId ", nativeQuery = true)
    void deleteTeacherById(@Param("teacherId") long teacherId);

    /**
     * Created by: TienP
     * Date: 29/03/2023
     * function: findTeacherByEmail
     */
    @Query(value = "select * from teacher where teacher_email= :email", nativeQuery = true)
    Teacher findTeacherByEmail(@Param("email") String email);


    /**
     * Created by:TienP
     * Date: 29/03/2023
     * function: updateTeacherRoleAdmin
     */
    @Modifying
    @Query(nativeQuery = true, value = " update teacher \n" +
            "set teacher_name= :teacherName,\n" +
            "    teacher_email= :email,\n" +
            "    teacher_phone_number= :phoneNumber,\n" +
            "    teacher_address= :teacherAddress \n" +
            " where teacher_id= :id")
    void updateTeacherRoleAdmin(
            @Param("teacherName") String teacherName,
            @Param("email") String email,
            @Param("phoneNumber") String phoneNumber,
            @Param("teacherAddress") String teacherAddress,
            @Param("id") Long id);

    Teacher findTeacherByTeacherId(Long id);
}
