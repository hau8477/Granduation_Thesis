package com.example.be.service.Impl;

import com.example.be.dto.IAnswerDto;
import com.example.be.dto.IMailAnsDto;
import com.example.be.model.Answers;
import com.example.be.repository.IAnswerRepository;
import com.example.be.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private IAnswerRepository iAnswerRepository;
    @Autowired
    private JavaMailSender emailSender;

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : find all answer
     *
     * @param questionId
     */
    @Override
    public List<IAnswerDto> findAll(Integer questionId) {
        return iAnswerRepository.findAll(questionId);
    }

    /**
     * Created by: LanTTN,
     * Date created : 30/03/2023
     * Function : save question
     *
     * @param answerContent, questionId, teacherId, dateTime
     */
    @Override
    public void save(String answerContent, Long questionId, Long teacherId, LocalDateTime dateTime) {
        iAnswerRepository.saveAnswerByQuestion(answerContent, questionId, teacherId, dateTime);
    }

    @Override
    public IMailAnsDto getMailAns(Long answerId) {
        return iAnswerRepository.getMailAns(answerId);
    }

    public void sendSimpleMail(
            IMailAnsDto mailList, String subject, String text, long answerId) {

        String mail = mailList.getTeacherEmail();
        if (mail != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ngochann.1603@gmail.com");
            message.setTo(mail);
            message.setSubject(subject);
            message.setText("Xin chào," +
                    " Giáo viên " +
                    "của bạn đã được trả lời.");
            emailSender.send(message);
        }
    }
}
