package com.example.be.service.Impl;


import com.example.be.model.ProgressReview;
import com.example.be.repository.IProgressReviewRepository;
import com.example.be.service.IProgressReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProgressReviewService implements IProgressReviewService {
    @Autowired
    private IProgressReviewRepository progressReviewRepository;

    /**
     * Created by: VuLX
     * Date created: 29/3/2023
     * Function: display list progress-review
     */
    @Override
    public List<ProgressReview> findAll() {
        return progressReviewRepository.findAll();
    }

    /**
     * Created by: VuLX
     * Date created: 29/3/2023
     * Function: display  progress-review-detail by id
     */

    @Override
    public ProgressReview findById(Long progressReviewId) {
        return progressReviewRepository.findById(progressReviewId).orElse(null);
    }

    /**
     * Created by: VuLX
     * Date created: 29/3/2023
     * Function: create progress-review
     */

    @Override
    public ProgressReview saveProgressReview(ProgressReview progressReview) {
        return progressReviewRepository.save(progressReview);
    }

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     * <p>
     * Function: find list progressReview By projectId
     */

    @Override
    public List<ProgressReview> findAllByProjectId(Long projectId) {
        return progressReviewRepository.findAllByProjectId(projectId);
    }


    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     * <p>
     * Function: find list progressReview By projectId and record
     */

    @Override
    public List<ProgressReview> findAllByProjectIdAndRecord(Long projectId, int record) {
        List<ProgressReview> progressReviews = progressReviewRepository.findAllByProjectId(projectId);
        List<ProgressReview> progressReviewsRecords = new ArrayList<>();
        int count = 0;
        for (ProgressReview progressReview : progressReviews) {
            if (record <= count) {
                break;
            }
            progressReviewsRecords.add(progressReview);
            count++;
        }
        return progressReviewsRecords;
    }

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     * <p>
     * Function: find size list progressReview By projectId and record
     */

    @Override
    public int findAllByProjectIdAndSize(Long projectId) {
        List<ProgressReview> progressReviews = progressReviewRepository.findAllByProjectId(projectId);
        return progressReviews.size();
    }
    /**
     * Created by: SyVT,
     * Date created : 30/03/2023
     * Function : findMaxPercentProgressReport
     */
    @Override
    public int findMaxPercentProgressReport(Long project_id, int stage_id) {
        return progressReviewRepository.findMaxPercentProgressReport(project_id,stage_id);
    }


}
