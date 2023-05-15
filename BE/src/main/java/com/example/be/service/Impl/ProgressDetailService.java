package com.example.be.service.Impl;

import com.example.be.model.ProgressDetail;
import com.example.be.repository.IProgressDetailRepository;
import com.example.be.service.IProgressDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressDetailService implements IProgressDetailService {
    @Autowired
    private IProgressDetailRepository progressDetailRepository;


    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     * <p>
     * Function: find list progressDetail By projectId and date-create order by desc
     */
    @Override
    public List<ProgressDetail> findAllByProjectId(Long projectId) {
        return progressDetailRepository.findProgressDetailByIdAndOrderDateCreateDesc(projectId);
    }

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     * <p>
     * Function: find  progressDetail By projectId
     */
    @Override
    public ProgressDetail findProgressDetailByProjectId(Long projectId) {
        return progressDetailRepository.findProgressDetailByProjectId(projectId);
    }

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     * <p>
     * Function: find  progressDetail By projectId
     */
    @Override
    public ProgressDetail findById(Long progressDetailId) {
        return progressDetailRepository.findById(progressDetailId).orElse(null);
    }

    @Override
    public List<ProgressDetail> findProgressDetailAndStatusIsTrue() {
        return progressDetailRepository.findProgressDetailAndStatusIsTrue();
    }

    @Override
    public ProgressDetail findProgressDetailByProjectIdAndStageId(Long projectId, int stageId) {
        return progressDetailRepository.findProgressDetailByProjectIdAndStageId(projectId,stageId);
    }

    @Override
    public ProgressDetail findProjectIdAnfStageId(String userName) {
        return progressDetailRepository.findProjectIdAndStageIdByStudentAccount(userName);
    }
}
