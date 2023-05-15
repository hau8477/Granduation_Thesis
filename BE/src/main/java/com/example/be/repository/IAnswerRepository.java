package com.example.be.repository;

import com.example.be.dto.IAnswerDto;
import com.example.be.dto.IMailAnsDto;
import com.example.be.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface IAnswerRepository extends JpaRepository<Answers, Long> {
    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : find all answer
     *
     * @param questionId
     */
    @Query(value = "select a.answer_content as answerContent, t.teacher_name as teacherName, t.teacher_img as teacherImg, a.date_time as dateTime" +
            " from answers as a " +
            "join teacher as t on a.teacher_id = t.teacher_id " +
            "join question as q on a.question_id = q.question_id" +
            " where q.question_id = :questionId order by a.date_time desc ", nativeQuery = true)
    List<IAnswerDto> findAll(@Param("questionId") Integer questionId);

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : save question
     *
     * @param answerContent, questionId, teacherId
     */
    @Modifying
    @Transactional
    @Query(value = "insert into answers(answer_content, question_id, teacher_id, date_time) value(:answerContent, :questionId, :teacherId, :dateTime)", nativeQuery = true)
    void saveAnswerByQuestion(@Param("answerContent") String answerContent,
                              @Param("questionId") Long questionId,
                              @Param("teacherId") Long teacherId,
                              @Param("dateTime")LocalDateTime dateTime);

    @Transactional
    @Query(value ="select  a.answer_id as answerId,t.teacher_id as teacherId, t.teacher_name as teacherName ,t.teacher_email as teacherEmail from `answers` as a join `teacher` t on a.teacher_id = t.teacher_id where a.answer_id = :answerId", nativeQuery = true)
    IMailAnsDto getMailAns(@Param("answerId") Long answerId);
}
