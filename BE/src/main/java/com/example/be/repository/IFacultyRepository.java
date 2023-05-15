package com.example.be.repository;

import com.example.be.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.be.dto.teacher.IFacultyDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value = "select faculty_id as facultyId,faculty_name as facultyName from faculty",
            countQuery = "select faculty_id as facultyId,faculty_name as facultyName from faculty",
            nativeQuery = true)
    List<IFacultyDTO> getAllFaculty();
}
