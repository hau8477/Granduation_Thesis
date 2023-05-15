package com.example.be.service.Impl;

import com.example.be.dto.ProgressDto;
import com.example.be.dto.ProgressProjectDto;
import com.example.be.dto.ProgressStudentDto;
import com.example.be.model.Project;
import com.example.be.model.Student;
import com.example.be.model.Team;
import com.example.be.service.IProgressService;
import com.example.be.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class ProgressService implements IProgressService {
    @Autowired
    private IProjectService projectService;


    /**
     * Created by: VuLX
     * Date created: 29/3/2023
     * Function: find list-progress
     */

    @Override
    public List<ProgressDto> findAll() {
        List<Project> projectList = projectService.findProjectByStatusNotNull();
        List<ProgressDto> progressDtos = new ArrayList<>();
        for (Project project : projectList) {
            progressDtos.add(new ProgressDto(project.getProjectId(), project.getTeam().getTeamName(), project.getProjectName(), project.getTeam().getMemberOfTeam(), project.isProjectStatus()));
        }
        return progressDtos;
    }

    /**
     * Created by: VuLX
     * Date created: 29/3/2023
     * <p>
     * Function: find list progress-student by projectId
     */

    @Override
    public List<ProgressStudentDto> findAllStudentOfTeam(Long projectId) {
        List<ProgressStudentDto> progressStudentDtos = new ArrayList<>();
        Project project = projectService.findProjectById(projectId);
        if (project == null) {
            return null;
        }
        Team team = project.getTeam();
        Set<Student> studentSet = team.getStudentSet();
        for (Student student : studentSet) {
            progressStudentDtos.add(new ProgressStudentDto(student.getStudentCode(), student.getStudentName(), student.getStudentEmail(), student.getStudentPhoneNumber(), student.getStudentImg(), student.getClazz().getClazzName()));
        }
        return progressStudentDtos;
    }

    /**
     * Created by: VuLX
     * Date created: 31/3/2023
     * Function: find  progress-detail by projectId
     */
    @Override
    public ProgressProjectDto findByProjectId(Long projectId) {
        return projectService.findByProjectId(projectId);
    }

}
