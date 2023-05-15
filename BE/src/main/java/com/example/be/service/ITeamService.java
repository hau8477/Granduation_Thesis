package com.example.be.service;

import com.example.be.dto.ITeamDto;
import com.example.be.dto.InstructorDTO;
import com.example.be.dto.teacher.TeacherDTO;
import com.example.be.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITeamService {
    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all team by name containing
     *
     * @Param: teamName, pageable
     */
    Page<Team> findAll(String teamName, Pageable pageable);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find by team id
     *
     * @Param: id
     */
    Team findById(Long id);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save team
     *
     * @Param: team
     */
    Team saveTeam(Team team);

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: paging list teacher
     */
    Page<InstructorDTO> getAllInstructor(Pageable pageable);

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: find team by id
     */
    ITeamDto findTeamById(Long teamId);

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: edit team with teacher
     */
    void updateTeam(Long teacherId, Long teamId);

    /**
     * Created by: DucND
     * Date create: 06/03/2023
     * Function: edit team with studentEmail
     */
    ITeamDto findTeamByAccount(String studentEmail);

}
