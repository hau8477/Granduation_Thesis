package com.example.be.controller;



import com.example.be.dto.ProgressReportDTO;
import com.example.be.model.ProgressDetail;
import com.example.be.model.ProgressReport;
import com.example.be.model.Project;
import com.example.be.model.Stage;
import com.example.be.service.IProgressDetailService;
import com.example.be.service.IProgressReportService;
import com.example.be.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/progress-reports")
public class ProgressReportRestController {

    @Autowired
    private IProgressReportService progressReportService;
    @Autowired
    private IProgressDetailService progressDetailService;
    @Autowired
    private IProjectService projectService;

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Save ProgressReport
     *
     * @return HttpStatus.CREATED if result is not error or HttpStatus.NOT_ACCEPTABLE if no content
     */
    @PostMapping("save/{projectId}/{stageId}")
    public ResponseEntity<?> saveProgressReport(@PathVariable Long projectId, @PathVariable int stageId, @Validated @RequestBody ProgressReportDTO progressReportDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.NOT_ACCEPTABLE);
        }

        Project project = projectService.findProjectById(projectId);

        progressReportDTO.setProject(project);

        Stage stageDTO = new Stage();
        if (stageId == 1) {
            stageDTO.setStageId(1);
            stageDTO.setStageName("Giai đoạn 1");
        }
        if (stageId == 2) {
            stageDTO.setStageId(2);
            stageDTO.setStageName("Giai đoạn 2");
        }
        if (stageId == 3) {
            stageDTO.setStageId(3);
            stageDTO.setStageName("Giai đoạn 3");
        }
        if (stageId == 4) {
            stageDTO.setStageId(4);
            stageDTO.setStageName("Giai đoạn 4");
        }
        progressReportDTO.setStage(stageDTO);
        ProgressReport progressReport = new ProgressReport();
        BeanUtils.copyProperties(progressReportDTO, progressReport);
        this.progressReportService.saveProgressReport(progressReport);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : find All ProgressReport
     *
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if no content
     */
    @GetMapping()
    public ResponseEntity<List<ProgressReport>> findAllProgressReport() {
        List<ProgressReport> progressReportList = progressReportService.findAllProgressReport();
        if (progressReportList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(progressReportList, HttpStatus.OK);
    }

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : find ProgressReport By progressReportId
     *
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if no content
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProgressReport> findProgressReportById(@PathVariable Long id) {
        ProgressReport progressReport = progressReportService.findProgressReportById(id);
        if (progressReport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(progressReport, HttpStatus.OK);
    }

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : find ProgressReport By projectId and stageId
     *
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if no content
     */

    @GetMapping("/history/{project_id}")
    public ResponseEntity<Page<ProgressReport>> findProgressReportByProjectIdAndStageId(@PathVariable("project_id") Long projectId, @RequestParam(defaultValue = "", required = false) String nameFileSearch,
                                                                                        @PageableDefault(page = 0, size = 4) Pageable pageable) {
        Page<ProgressReport> progressReportList = progressReportService.findAllProgressReportByProjectIdAndStageId(projectId, nameFileSearch, pageable);
        if (progressReportList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(progressReportList, HttpStatus.OK);
    }

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : find ProgressReport By projectId and stageId
     *
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if no content
     */
    @GetMapping("/maxPercent/{project_id}/{stage_id}")
    public ResponseEntity<ProgressReport> findProgressReportMaxPercentByProjectIdAndStageId(@PathVariable("project_id") Long projectId,
                                                                                            @PathVariable("stage_id") int stageId) {
        ProgressReport progressReport = progressReportService.findProgressReportMaxPercentByStageIdAndProjectId(projectId, stageId);
        if (progressReport == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(progressReport, HttpStatus.OK);
    }

    @GetMapping("/project/{project_id}")
    public ResponseEntity<Project> findProjectById(@PathVariable("project_id") Long projectId) {
        Project project = projectService.findProjectById(projectId);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * Created by: SyVT,
     * Date created : 06/04/2023
     * Function : findProgressDetailByStudentUser
     *
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if no content
     */
    @GetMapping("/detail")
    public ResponseEntity<ProgressDetail> findProgressDetailByStudentUser(@RequestParam String userName) {
        ProgressDetail progressDetail = progressDetailService.findProjectIdAnfStageId(userName);
        if (progressDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(progressDetail, HttpStatus.OK);
    }

}
