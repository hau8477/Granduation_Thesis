package com.example.be.repository;

import com.example.be.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.be.dto.teacher.IDegreeDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IDegreeRepository extends JpaRepository<Degree, Long> {
    @Query(value = "select degree_id as degreeId, degree_name as degreeName from degree",
    countQuery = "select degree_id as degreeId, degree_name as degreeName from degree",
    nativeQuery = true)
    List<IDegreeDTO> getAllDegree();
}
