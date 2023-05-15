package com.example.be.repository;

import com.example.be.dto.ITeamDto;
import com.example.be.dto.InstructorDTO;
import com.example.be.dto.teacher.TeacherDTO;
import com.example.be.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ITeamRepository extends JpaRepository<Team, Long> {
    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all team by name containing
     *
     * @Param: teamName, pageable
     */
    @Query(value = "" +
            "SELECT " +
            "team_id," +
            "team_name," +
            "member_of_team," +
            "teacher_id " +
            "FROM team " +
            "WHERE team_name LIKE CONCAT('%',:teamName,'%')",
            nativeQuery = true)
    Page<Team> findAll(@Param("teamName") String teamName, Pageable pageable);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find team by id
     *
     * @Param: id
     */
    @Query(value = "" +
            "SELECT " +
            "team_id," +
            "team_name," +
            "member_of_team," +
            "teacher_id "+
            "FROM team " +
            "WHERE team_id = :teamId",
            nativeQuery = true)
    Optional<Team> findById(@Param("teamId") Long id);

    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: save team
     *
     * @Param: teamName, projectId, teacherId
     */
    @Modifying
    @Query(value = "INSERT INTO `sprint1`.`team` (`member_of_team`, `team_name`) VALUES (:memberOfTeam,:teamName)",
            nativeQuery = true)
    @org.springframework.transaction.annotation.Transactional
    Long saveTeam(@Param("teamName") String teamName,
                  @Param("memberOfTeam") Integer memberOfTeam);


    /**
     * Create by: HauNN
     * Date create: 29/03/2023
     * Function: find all team by name
     *
     * @Param: teamName
     */
    @Query(value = "" +
            "SELECT " +
            "team_id," +
            "team_name," +
            "member_of_team," +
            "teacher_id " +
            "FROM team " +
            "WHERE team_name = LOWER(TRIM(REGEXP_REPLACE(:teamName, '\\s+', ' ')))", nativeQuery = true)
    Optional<Team> findByName(@Param("teamName") String teamName);

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: display list teacher with column teacher_name, registration status and paging
     *
     * @return get all list teacher
     * @param: pageable
     */
    @Query(value = "select " +
            "tc.teacher_id as teacherId, " +
            "tc.teacher_name as teacherName, " +
            "       (count(distinct t.team_id) -" +
            "       (select count(distinct t.team_id)" +
            "        from teacher as tc2 " +
            "                 left join team as t on t.teacher_id = tc2.teacher_id " +
            "                 left join project as p on p.team_id = t.team_id " +
            "                 left join progress_detail as pd on pd.project_id = p.project_id " +
            "        where tc2.teacher_id = tc.teacher_id and pd.stage_id = 4 and pd.progress_detail_percent = 100 and tc2.flag_delete = false) ) " +
            "as total " +
            "from teacher as tc " +
            "         left join team as t on t.teacher_id = tc.teacher_id " +
            "         left join project as p on p.team_id = t.team_id " +
            "         left join progress_detail as pd on pd.project_id = p.project_id " +
            "where (pd.stage_id is null or (pd.stage_id != 4 and pd.progress_detail_percent != 100)) and tc.flag_delete = false " +
            "group by tc.teacher_id ",
            countQuery = "select " +
                    "tc.teacher_id as teacherId, " +
                    "tc.teacher_name as teacherName, " +
                    "       (count(distinct t.team_id) -" +
                    "       (select count(distinct t.team_id)" +
                    "        from teacher as tc2 " +
                    "                 left join team as t on t.teacher_id = tc2.teacher_id " +
                    "                 left join project as p on p.team_id = t.team_id " +
                    "                 left join progress_detail as pd on pd.project_id = p.project_id " +
                    "        where tc2.teacher_id = tc.teacher_id and pd.stage_id = 4 and pd.progress_detail_percent = 100 and tc2.flag_delete = false) ) " +
                    "as total " +
                    "from teacher as tc " +
                    "         left join team as t on t.teacher_id = tc.teacher_id " +
                    "         left join project as p on p.team_id = t.team_id " +
                    "         left join progress_detail as pd on pd.project_id = p.project_id " +
                    "where (pd.stage_id is null or (pd.stage_id != 4 and pd.progress_detail_percent != 100)) and tc.flag_delete = false " +
                    "group by tc.teacher_id ",
            nativeQuery = true)
    Page<InstructorDTO> getAllInstructor(Pageable pageable);

    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: find team by id
     *
     * @return the team you are looking for
     * @param: teamId
     */
    @Query(value = "select t.team_id as teamId, t.member_of_team as memberOfTeam, " +
            "t.team_name as teamName, tc.teacher_id as teacherId, tc.teacher_name as teacherName " +
            "from team as t left join teacher as tc on t.teacher_id = tc.teacher_id " +
            "where team_id = :teamId ", nativeQuery = true)
    ITeamDto findTeamById(@Param("teamId") Long teamId);


    /**
     * Created by: DucND
     * Date create: 29/03/2023
     * Function: edit team,create teacher for group
     *
     * @param: teacherId and teamId
     * @result change instructor of team
     */
    @Transactional
    @Modifying
    @Query(value = "update team set teacher_id = :teacherId where team_id = :teamId", nativeQuery = true)
    void updateTeam(@Param("teacherId") Long teacherId, @Param("teamId") Long teamId);

    /**
     * Created by: DucND
     * Date create: 06/04/2023
     * Function: edit team,create teacher for group
     *
     * @param: studentEmail
     * @result change instructor of team
     */
    @Query(value = "select t.team_id as teamId, t.member_of_team as memberOfTeam, " +
            "t.team_name as teamName, tc.teacher_id as teacherId, tc.teacher_name as teacherName " +
            "from team as t " +
            "left join teacher as tc on t.teacher_id = tc.teacher_id " +
            "left join student as s on s.team_id = t.team_id " +
            "where s.flag_leader and s.student_email = :studentEmail ", nativeQuery = true)
    ITeamDto findTeamByAccount(@Param("studentEmail") String  studentEmail);
}
