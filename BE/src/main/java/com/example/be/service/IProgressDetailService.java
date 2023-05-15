package com.example.be.service;

import com.example.be.model.ProgressDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProgressDetailService {
    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    List<ProgressDetail>findAllByProjectId(Long projectId);
    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    ProgressDetail findProgressDetailByProjectId(Long projectId);
    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    ProgressDetail findById(Long progressDetailId);

    List<ProgressDetail> findProgressDetailAndStatusIsTrue();
    ProgressDetail findProgressDetailByProjectIdAndStageId( Long projectId, int stageId);

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Save
     */
    ProgressDetail findProjectIdAnfStageId(String userName);
}
