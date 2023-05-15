package com.example.be.service;

import com.example.be.model.ProgressReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProgressReportService {
    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : FindAll
     */
    List<ProgressReport> findAllProgressReport();

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find All Progress Report By ProjectId And StageId
     */
    Page<ProgressReport> findAllProgressReportByProjectIdAndStageId( Long project_id, String nameFileSearch, Pageable pageable);

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find All Progress Report Max Percent By ProjectId And StageId
     */
    ProgressReport findProgressReportMaxPercentByStageIdAndProjectId(Long projectId, int stageId);

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : FindById
     */
    ProgressReport findProgressReportById(Long progressReportId);


    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Save
     */
    void saveProgressReport(ProgressReport progressReport);

}
