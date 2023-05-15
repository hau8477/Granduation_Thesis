package com.example.be.controller;

import com.example.be.model.Announcement;
import com.example.be.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/announcements")
public class AnnouncementRestController {
    @Autowired
    private IAnnouncementService announcementService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Announcement>> findAll(@PathVariable Long studentId) {
        List<Announcement> announcements = this.announcementService.findAll(studentId);

        if (announcements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }
}
