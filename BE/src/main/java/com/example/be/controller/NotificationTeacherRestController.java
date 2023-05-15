package com.example.be.controller;

import com.example.be.dto.NotificationTeacherDto;
import com.example.be.model.NotificationTeacher;
import com.example.be.service.INotificationTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/notification-teachers")
public class NotificationTeacherRestController {
    @Autowired
    private INotificationTeacherService notificationTeacherService;
    /**
     * Created by: hoangNNH
     * Date created: 29/03/2023
     * Function: get project list
     *
     * @return HttpStatus.NO_CONTENT if result is empty or HttpStatus.OK if result is not empty
     */
    @GetMapping("")
    public ResponseEntity<List<NotificationTeacher>> getAllNotificationTeacher() {
        List<NotificationTeacher> notificationTeacherList = this.notificationTeacherService.getAllNotificationTeacher();
        if (notificationTeacherList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(notificationTeacherList, HttpStatus.OK);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List NotificationTeacher
     *
     * @param 'notificationTeacherName'
     * @param 'notificationTeacherContent'
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus. OK if result is not error
     */
    @PostMapping("/create-notification-teacher")
    public ResponseEntity<?> createNotificationTeacher(@RequestBody @Valid NotificationTeacherDto notificationTeacherDto,
                                                       BindingResult bindingResult) {
        notificationTeacherDto.validate(notificationTeacherDto,bindingResult);
        if (notificationTeacherDto == null || bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        notificationTeacherService.addNotificationTeacher(notificationTeacherDto.getNotificationTeacherTopic(), notificationTeacherDto.getNotificationTeacherContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
