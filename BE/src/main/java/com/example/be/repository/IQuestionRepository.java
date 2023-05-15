package com.example.be.repository;

import com.example.be.dto.IMailQuesDto;
import com.example.be.dto.IQuestionDto;
import com.example.be.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface IQuestionRepository extends JpaRepository<Question, Long> {
    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : find all question
     *
     * @param pageable
     */
//    @Query(value = "select s.student_id as studentId, s.student_name as studentName, s.student_img as studentImg, + st.question_content as questionContent, st.question_topic as questionTopic, st.question_id as questionId,  + st.date_time as dateTime from `question` st join `student` s on s.student_id = st.student_id order by date_time desc"
//            , nativeQuery = true)
    @Query(value = "select * from question  order by date_time desc",nativeQuery = true)
    Page<Question> findAllBy(Pageable pageable);

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : save question
     *
     * @param questionContent, questionTopic, datetime, studentId
     */
    @Modifying
    @Transactional
    @Query(value = "insert into question(question_content, question_topic, date_time, student_id) value(:questionContent, :questionTopic, :dateTime, :studentId)", nativeQuery = true)
    void saveQuestion(@Param("questionContent") String questionContent,
                      @Param("questionTopic") String questionTopic,
                      @Param("dateTime") LocalDateTime dateTime,
                      @Param("studentId") Long studentId);



    @Transactional
    @Query(value = "select s.student_id as studentId,q.question_id as questionId, s.student_email as studentEmail from `question` as q join `student` as s on s.student_id = q.student_id  where q.question_id = :questionId", nativeQuery = true)
    IMailQuesDto getMailQues(@Param("questionId") Long questionId);
}
