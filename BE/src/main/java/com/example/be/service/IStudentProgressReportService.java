package com.example.be.service;

import com.example.be.dto.IStudentProgressReportDTO;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IStudentProgressReportService {
    List<IStudentProgressReportDTO> findStudentProgressReportProjectId(Long project_id, int totalElement);
    List<IStudentProgressReportDTO> findStudentProgressReportProjectId(Long project_id);
}
