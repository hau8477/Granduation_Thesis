package com.example.be.controller;

import com.example.be.dto.ITeacherDto;
import com.example.be.dto.ITeamDto;
import com.example.be.dto.InstructorDTO;
import com.example.be.dto.TeamDTO;
import com.example.be.dto.teacher.TeacherDTO;
import com.example.be.model.Team;
import com.example.be.service.IStudentService;
import com.example.be.service.ITeacherService;
import com.example.be.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/teams")
public class TeamRestController {
    @Autowired
    private ITeamService teamService;

    @Autowired
    private IStudentService studentService;

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save team
     *
     * @return HttpStatus.OK if result is not error else return HttpStatus.EXPECTATION_FAILED
     * @Param: teamDTO, bindingResult
     */
    @PostMapping(value = "/save/{nameTeam}/{memberOfTeam}")
    public ResponseEntity<?> saveTeam(@PathVariable String nameTeam, @PathVariable Integer memberOfTeam) {

        Team team = new Team();
        team.setTeamName(nameTeam);
        team.setTeacher(null);
        team.setTeamId(null);
        team.setProject(null);
        team.setMemberOfTeam(memberOfTeam);

        Team savedTeam = this.teamService.saveTeam(team);
        if (savedTeam != null) {
            return new ResponseEntity<>(savedTeam, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find team by id
     *
     * @return HttpStatus.OK if result is not error else return HttpStatus.NO_CONTENT if result is empty
     * @Param: id
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<Team> findById(@PathVariable Long id) {
        Team team = this.teamService.findById(id);

        if (team != null) {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Autowired
    private ITeacherService teacherService;

    /**
     * Created by: DucND
     * Date create: 01/04/2023
     * Function: tạo api get teacher by id
     *
     * @return HttpStatus.NOT_FOUND if result is empty or HttpStatus.OK if in the data have record teachers.
     * @param: id
     */
    @GetMapping("/teacher/{id}")
    public ResponseEntity<ITeacherDto> getTeacherById(@PathVariable Long id) {
        Optional<ITeacherDto> categoryOptional = teacherService.findTeacherById(id);
        return categoryOptional.map(teacher -> new ResponseEntity<>(teacher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: tạo api get all and paging information teacher
     *
     * @return HttpStatus.NOT_FOUND if result is empty or HttpStatus.OK if in the data there are record teachers.
     * @param: pageable
     */
    @GetMapping("")
    public ResponseEntity<Page<InstructorDTO>> getAllInstructor(@PageableDefault(size = 5) Pageable pageable) {
        Page<InstructorDTO> instructorPage = teamService.getAllInstructor(pageable);
        if (instructorPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(instructorPage, HttpStatus.OK);
        }
    }

    /**
     * Created by: DucND
     * Date create: 06/04/2023
     * Function: create api find team by email
     *
     * @return HttpStatus.NOT_FOUND if result is null or HttpStatus.OK if there is a team with the teamId to look for.
     * @param: email
     */
    @GetMapping("/detail/team/{email}")
    public ResponseEntity<ITeamDto> getTeamByAccount(@PathVariable String email) {
        ITeamDto team = teamService.findTeamByAccount(email);
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: create api edit,add instructor team
     *
     * @return HttpStatus.NOT_FOUND if result team is null, not found or HttpStatus.OK if result is edit success.
     * @param: teamId, teamDto
     */
    @PatchMapping("/edit/{teamId}")
    public ResponseEntity<?> updateTeam(@RequestBody TeamDTO teamDto, @PathVariable Long teamId) {
        ITeamDto team = teamService.findTeamById(teamId);
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            teamService.updateTeam(teamDto.getTeacherId(), teamId);
            return new ResponseEntity<>(teamDto, HttpStatus.OK);
        }
    }

}
