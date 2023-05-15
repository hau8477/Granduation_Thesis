package com.example.be.service.Impl;


import com.example.be.model.ProgressReport;
import com.example.be.repository.IProgressReportRepository;
import com.example.be.service.IProgressReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressReportService implements IProgressReportService {

    /**
     * Created by: SyVT,
     * Date created : 30/03/2023
     */
    @Autowired
    private IProgressReportRepository progressReportRepository;

    @Override
    public List<ProgressReport> findAllProgressReport() {
        return progressReportRepository.findAllProgressReport();
    }

    @Override
    public Page<ProgressReport> findAllProgressReportByProjectIdAndStageId(Long project_id, String nameFileSearch, Pageable pageable) {
        return progressReportRepository.findProgressReportByStageIdAndProjectId(project_id,nameFileSearch,pageable);
    }


    @Override
    public ProgressReport findProgressReportMaxPercentByStageIdAndProjectId(Long projectId, int stageId) {
        return progressReportRepository.findProgressReportMaxPercentByStageIdAndProjectId(projectId,stageId);
    }

    @Override
    public ProgressReport findProgressReportById(Long progressReportId) {
        return progressReportRepository.findProgressReportById(progressReportId);
    }

    @Override
    public void saveProgressReport(ProgressReport progressReport) {
        progressReportRepository.save(progressReport);
    }


}
