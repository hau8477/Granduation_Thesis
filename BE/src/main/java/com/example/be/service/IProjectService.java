package com.example.be.service;

import com.example.be.dto.ITeamDTOO;
import com.example.be.dto.ITopicDto;
import com.example.be.dto.ProgressProjectDto;
import com.example.be.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProjectService {
    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save project
     *
     * @Param: project
     */
    Project save(Project project);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by id project
     *
     * @Param: id
     */
    Project findById(Long id);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all by name project containing
     *
     * @Param: searchName
     */
    Page<Project> findAllByNameContaining(String searchName, Pageable pageable);

    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project list
     *
     * @param pageable, name
     */
    Page<Project> getAllProject(Pageable pageable, String name);

    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project by id
     *
     * @param projectId
     */
    Project getProjectById(Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */

    void updatePro(Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    void updatePro2(Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    Project findProById(Long projectId);

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    Page<ITopicDto> pagePro(Pageable pageable);

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */

    List<Project> findAll();

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    Project findByIdProject(Long projectId);

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    Project findProjectById(Long projectId);

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    List<Project> findProjectListEnableAndSetStatusIsTrue();

    /**
     * Created by: VuLX
     * Date created: 01/04/2023
     */
    ProgressProjectDto findByProjectId(Long projectId);

    List<Project> findProjectByStatusNotNull();

    ITeamDTOO findTeamDTOO(Long projectId);

}
