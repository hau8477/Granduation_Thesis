package com.example.be.repository;


import com.example.be.dto.IStudentProgressReportDTO;
import com.example.be.model.ProgressReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IProgressReportRepository extends JpaRepository<ProgressReport, Long> {
    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find All Progress Report
     */
    @Query(value = "SELECT pr.* FROM progress_report as pr", nativeQuery = true)
    List<ProgressReport> findAllProgressReport();

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Save ProgressReport
     */
    @Modifying
    @Query(value = "INSERT INTO progress_report(progress_report_content,progress_report_file,progress_report_file_name,progress_report_time,project_id,stage_id) " +
            "VALUES (:progress_report_content,:progress_report_file,:progress_report_file_name,:progress_report_time,:project_id,:stage_id)",
            nativeQuery = true)
    void saveProgressReport(@Param("progress_report_content") String progress_report_content, @Param("progress_report_file") String progress_report_file,@Param("progress_report_file_name") String progress_report_file_name,
                            @Param("progress_report_time") String progress_report_time,
                            @Param("project_id") Long project_id,
                            @Param("stage_id") int stage_id);

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find ProgressReport By ProgressReportId
     */
    @Query(value = "SELECT pr.* FROM progress_report as pr WHERE progress_report_id = :progress_report_id", nativeQuery = true)
    ProgressReport findProgressReportById(@Param("progress_report_id") Long progress_report_id);

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find ProgressReport By ProjectId
     */
    @Query(value = "SELECT pr.* FROM `progress_report` as pr WHERE project_id =:project_id and progress_report_file_name like concat('%',:nameFileSearch,'%') ORDER BY progress_report_time DESC", nativeQuery = true)
    Page<ProgressReport> findProgressReportByStageIdAndProjectId(@Param("project_id") Long project_id,@Param("nameFileSearch")String nameFileSearch, Pageable pageable);  /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find ProgressReport MaxPercent By StageId And ProjectId
     */
    @Query(value = "SELECT pr.* FROM progress_report as pr " +
            "WHERE project_id =:project_id and stage_id =:stage_id ORDER BY progress_report_time DESC\n" +
            "LIMIT 1", nativeQuery = true)
    ProgressReport findProgressReportMaxPercentByStageIdAndProjectId(@Param("project_id") Long project_id, @Param("stage_id") int stage_id);


    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find StudentProgressReport By StageId And ProjectId
     */
    @Query(value = "SELECT DISTINCT pr.progress_report_content as progressReportContent, pr.progress_report_file as progressReportFile,pr.progress_report_file_name as progressReportFileName,pr.progress_report_time as progressReportTime,s.student_name as studentName,s.student_img as studentImg,sta.stage_name as stageName " +
            "FROM progress_report pr " +
            "join project p on pr.project_id = p.project_id " +
            "join team t on p.team_id = t.team_id " +
            "join student s on t.team_id = s.team_id " +
            "join stage sta on pr.stage_id = sta.stage_id " +
            "WHERE p.project_id = :project_id and s.flag_leader = true " +
            "ORDER BY pr.progress_report_time DESC " +
            "limit :totalElement", nativeQuery = true)
    List<IStudentProgressReportDTO> findStudentProgressReportProjectId(@Param("project_id") Long project_id, @Param("totalElement") int totalElement);


    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find StudentProgressReport By ProjectId
     */
    @Query(value = "SELECT DISTINCT pr.progress_report_content as progressReportContent ,pr.progress_report_file as progressReportFile,pr.progress_report_file_name as progressReportFileName,pr.progress_report_time as progressReportTime,s.student_name as studentName,s.student_img as studentImg,sta.stage_name as stageName " +
            "FROM progress_report pr " +
            "join project p on pr.project_id = p.project_id " +
            "join team t on p.team_id = t.team_id " +
            "join student s on t.team_id = s.team_id " +
            "join stage sta on pr.stage_id = sta.stage_id " +
            "WHERE p.project_id = :project_id and s.flag_leader = true " +
            "ORDER BY pr.progress_report_time DESC", nativeQuery = true)
    List<IStudentProgressReportDTO> findStudentProgressReportProjectId(@Param("project_id") Long project_id);

}
