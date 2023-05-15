package com.example.be.service.Impl;

import com.example.be.dto.ITeamDto;
import com.example.be.dto.InstructorDTO;
import com.example.be.dto.teacher.TeacherDTO;
import com.example.be.model.Team;
import com.example.be.repository.ITeamRepository;
import com.example.be.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private ITeamRepository teamRepository;

    /**
     * <<<<<<< HEAD
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all team by name containing
     *
     * @return list page team if result is not error else return null
     * @Param: teamName, pageable
     */
    @Override
    public Page<Team> findAll(String teamName, Pageable pageable) {
        return this.teamRepository.findAll(teamName, pageable);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by team id
     *
     * @return team if result is not error else return null
     * @Param: id
     */
    @Override
    public Team findById(Long id) {
        return this.teamRepository.findById(id).orElse(null);
    }

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save team
     *
     * @return team if result is not error else return null
     * @Param: team
     */
    @Transactional
    @Override
    public Team saveTeam(Team team) {
        Team teamOptional = this.teamRepository.findByName(team.getTeamName()).orElse(null);

        if (teamOptional != null) {
            return null;
        }

        if (team.getTeacher() != null) {
            return null;
        }

        if (team.getProject() != null) {
            return null;
        }
        return this.teamRepository.save(team);

    }

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: get all teacher and paging
     *
     * @return list instructor and paging
     * @param: pageable
     */
    @Override
    public Page<InstructorDTO> getAllInstructor(Pageable pageable) {
        return teamRepository.getAllInstructor(pageable);
    }

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: find team by id
     *
     * @return the team you looking for
     * @param: teamId
     */
    @Override
    public ITeamDto findTeamById(Long teamId) {
        return teamRepository.findTeamById(teamId);
    }


    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: add or edit instructor for team
     *
     * @param: teacherId, teamId
     */
    @Override
    public void updateTeam(Long teacherId, Long teamId) {
        teamRepository.updateTeam(teacherId, teamId);
    }

    /**
     * Created by: DucND
     * Date create: 06/03/2023
     * Function: find team by email
     *
     * @return the team you looking for
     * @param: studentEmail
     */
    @Override
    public ITeamDto findTeamByAccount(String studentEmail) {
        return teamRepository.findTeamByAccount(studentEmail);
    }

}
