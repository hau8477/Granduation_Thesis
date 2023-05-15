package com.example.be.service.Impl;

import com.example.be.dto.IMailQuesDto;
import com.example.be.dto.IQuestionDto;
import com.example.be.model.Question;
import com.example.be.repository.IQuestionRepository;
import com.example.be.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private IQuestionRepository iQuestionRepository;

    @Autowired
    private JavaMailSender emailSender;
    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : find all question
     *
     * @param pageable
     */
    @Override
    public Page<Question> findAll(Pageable pageable) {
        return iQuestionRepository.findAllBy(pageable);
    }

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : save question
     *
     * @param questionContent, questionTopic, dateTime, studentId
     * @param dateTime
     */
    @Override
    public void saveQuestion(String questionContent, String questionTopic, LocalDateTime dateTime, Long studentId) {
        iQuestionRepository.saveQuestion(questionContent, questionTopic, dateTime, studentId);
    }

    @Override
    public Question save(Question question) {
        return iQuestionRepository.save(question);
    }

    @Override
    public IMailQuesDto getMailQues(Long questionId) {
        return iQuestionRepository.getMailQues(questionId);
    }

    public void sendSimpleMessage(
            IMailQuesDto mailList, String subject, String text, long questionId) {

        String mail = mailList.getStudentEmail();
        if (mail != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ngochann.1603@gmail.com");
            message.setTo(mail);
            message.setSubject(subject);
            message.setText("Thắc mắc của bạn đã được trả lời.");
            emailSender.send(message);
        }
    }
}
