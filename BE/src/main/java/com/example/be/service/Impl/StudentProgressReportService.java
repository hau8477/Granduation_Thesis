package com.example.be.service.Impl;

import com.example.be.dto.IStudentProgressReportDTO;

import com.example.be.repository.IProgressReportRepository;

import com.example.be.service.IStudentProgressReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProgressReportService implements IStudentProgressReportService {

    /**
     * Created by: SyVT,
     * Date created : 30/03/2023
     */
    @Autowired
    private IProgressReportRepository progressReportRepository;

    @Override
    public List<IStudentProgressReportDTO> findStudentProgressReportProjectId(Long project_id, int totalElement) {

        return progressReportRepository.findStudentProgressReportProjectId(project_id, totalElement);
    }

    @Override
    public List<IStudentProgressReportDTO> findStudentProgressReportProjectId(Long project_id) {

        return progressReportRepository.findStudentProgressReportProjectId(project_id);
    }

}
