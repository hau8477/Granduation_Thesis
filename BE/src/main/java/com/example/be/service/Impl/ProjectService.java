package com.example.be.service.Impl;

import com.example.be.dto.ITeamDTOO;
import com.example.be.dto.ITopicDto;
import com.example.be.dto.ProgressProjectDto;
import com.example.be.model.Project;
import com.example.be.repository.IProjectRepository;
import com.example.be.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private IProjectRepository projectRepository;

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save project
     *
     * @return  project if result is not error else return null
     * @Param: project
     */
    @Override
    public Project save(Project project) {
        Project projectOptional = this.projectRepository.findByName(project.getProjectName()).orElse(null);

        if (projectOptional != null) {
            return null;
        }

//        this.projectRepository.saveProject(
//                project.getProjectName(),
//                project.getProjectContent(),
//                project.getProjectDescription(),
//                project.getProjectImg(),
//                project.getTeam().getTeamId()
//        );

        return this.projectRepository.save(project);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by id project
     *
     * @return project if result is not error else return null
     * @Param: id
     */
    @Override
    public Project findById(Long id) {
        return this.projectRepository.findByProjectId(id).orElse(null);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all by name project containing
     *
     * @return list page project if result is not error else return null
     * @Param: searchName
     */
    @Override
    public Page<Project> findAllByNameContaining(String searchName, Pageable pageable) {
        return this.projectRepository.findAllByNameContaining(searchName, pageable);
    }

    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project by id
     *
     * @param pageable, name
     * @return project list
     */

    @Override
    public Page<Project> getAllProject(Pageable pageable, String name) {
        return this.projectRepository.getAllProject(pageable, name);
    }
    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project by id
     *
     * @param projectId
     * @return project
     */
    @Override
    public Project getProjectById(Long projectId) {
        return this.projectRepository.getProjectById(projectId);
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    @Override
    @Transactional
    public void updatePro(Long projectId) {
        projectRepository.updatePro(projectId);
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    @Override
    @Transactional
    public void updatePro2(Long projectId) {
        projectRepository.updatePro2(projectId);
    }

    /**
     * Created by: NuongHT
     * Date create: 29/03/2023
     */
    @Override
    public Project findProById(Long projectId) {
        return projectRepository.findProById(projectId);
    }

    @Override
    public Page<ITopicDto> pagePro(Pageable pageable) {
        return projectRepository.pagePro(pageable);
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findByIdProject(Long projectId) {
        return projectRepository.findByAId(projectId).orElse(null);
    }

    @Override
    public Project findProjectById(Long projectId) {
        return projectRepository.findByAId(projectId).orElse(null);
    }

    @Override
    public List<Project> findProjectListEnableAndSetStatusIsTrue() {
        return projectRepository.findProjectListEnable();
    }

    @Override
    public ProgressProjectDto findByProjectId(Long projectId) {
        return projectRepository.findProgressDtoByProjectId(projectId).orElse(null);
    }

    @Override
    public List<Project> findProjectByStatusNotNull() {
        return projectRepository.findAllAndStatusNotNull();
    }

    @Override
    public ITeamDTOO findTeamDTOO(Long projectId) {
        return projectRepository.findTeamDTOO(projectId);
    }
}
