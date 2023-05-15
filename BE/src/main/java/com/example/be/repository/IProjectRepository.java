package com.example.be.repository;

import com.example.be.dto.ITeamDTOO;
import com.example.be.dto.ITopicDto;
import com.example.be.dto.ProgressProjectDto;
import com.example.be.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import com.example.be.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IProjectRepository extends JpaRepository<Project, Long> {
    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save project
     *
     * @Param: name, content, description, img
     */

    @Modifying
    @Query(value = "" +
            "INSERT INTO project " +
            "(project_name, project_content, project_description, project_status, project_img, team_id)" +
            " VALUES (:projectName,:projectContent ,:projectDescription ,0 ,:projectImg,:teamId );",
            nativeQuery = true)
    void saveProject(@Param("projectName") String projectName,
                     @Param("projectContent") String projectContent,
                     @Param("projectDescription") String projectDescription,
                     @Param("projectImg") String projectImg,
                     @Param("teamId") Long teamId);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by id project
     *
     * @Param: id
     */
    @Query(value = "" +
            "SELECT " +
            "project_id," +
            "project_name," +
            "project_content," +
            "project_img," +
            "project_description," +
            "project_status," +
            "team_id " +
            "FROM project " +
            "WHERE project_id = :projectId", nativeQuery = true)
    Optional<Project> findById(@Param("projectId") Long id);

    Optional<Project> findByProjectId(Long id);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by name project
     *
     * @Param: name
     */
    @Query(value = "" +
            "SELECT " +
            "project_id," +
            "project_name," +
            "project_content," +
            "project_img," +
            "project_description," +
            "project_status," +
            "team_id " +
            "FROM project " +
            "WHERE project_name = LOWER(TRIM(REGEXP_REPLACE(:projectName, '\\s+', ' ')))", nativeQuery = true)
    Optional<Project> findByName(@Param("projectName") String name);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by name containing project
     *
     * @Param: project, pageable
     */
    @Query(value = "" +
            "SELECT " +
            "project_id," +
            "project_name," +
            "project_content," +
            "project_img," +
            "project_description," +
            "project_status," +
            "team_id " +
            "FROM project " +
            "WHERE project_name LIKE CONCAT('%',:searchName,'%')", nativeQuery = true)
    Page<Project> findAllByNameContaining(@Param("searchName") String searchName, Pageable pageable);

    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project list
     *
     * @param pageable, name
     */
    @Query(value = "select * from `project` " +
            "where `project_name` like concat('%', :name, '%')" +
            "and `project_status` = true ", nativeQuery = true)
    Page<Project> getAllProject(Pageable pageable, @Param("name") String name);
    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project by id
     *
     * @param projectId
     */
    @Query(value = "select * from project where project_id = :projectId", nativeQuery = true)
    Project getProjectById(@Param("projectId") Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */

    @Modifying
    @Query(value = "update `project` set project_status = true where project_Id = :projectId",nativeQuery = true)
    void updatePro(@Param("projectId") Long projectId);
    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */

    @Modifying
    @Query(value = "update `project` set project_status = false where project_Id = :projectId",nativeQuery = true)
    void updatePro2(@Param("projectId") Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */

    @Query(value = "select * from `project` where project_id = :projectId", nativeQuery = true)
    Project findProById(@Param("projectId") Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    @Query(value = "select p.project_id as projectId, t.team_name as teamName, p.project_name as projectName,p.project_description as projectDescription,p.project_status as projectStatus from `project` as p join team t on p.team_id = t.team_id order by  p.project_id desc", countQuery = "select p.project_id as projectId, t.team_name as teamName, p.project_name as projectName,p.project_description as projectDescription,p.project_status as projectStatus from `project` as p join team t on p.team_id = t.team_id", nativeQuery = true)
    Page<ITopicDto> pagePro(Pageable pageable);

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    @Query(value = "select * from project where project.project_status = true and project_id = :id", nativeQuery = true)
    Project findProjectEnable(@Param("id") Long projectId);

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */

    @Query(value = "select p.* from project p left join progress_detail pd on p.project_id = pd.project_id where p.project_status = true and pd.project_id is null ", nativeQuery = true)
    List<Project> findProjectListEnable();

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    @Transactional

    @Query(value = "select * from project where project_id = :id", nativeQuery = true)
    Optional<Project> findByAId(@Param("id") Long projectId);

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */

    @Query(value = "select p.project_id as projectId, p.project_name as projectName," +
            " t.team_name as teamName, t.member_of_team as memberTotal, " +
            "p.project_status as status  from project p" +
            " join team t on p.team_id = t.team_id where p.project_id = :id", nativeQuery = true)
    Optional<ProgressProjectDto> findProgressDtoByProjectId(@Param("id") Long projectId);

    /**
     * Created by: VuLX
     */
    @Query(value = "SELECT * FROM project where project_status is not null order by project_id desc ",nativeQuery = true)
    List<Project> findAllAndStatusNotNull();

    /**
     * Created by: VuLX
     */
    @Query(value = "select t.team_id as teamId, t.member_of_team as memberOfTeam,t.team_name as teamName\n" +
            " from project p join team t on p.team_id\n" +
            " = t.team_id where p.project_id = :id",nativeQuery = true)
    ITeamDTOO findTeamDTOO(@Param("id") Long projectId);
}
