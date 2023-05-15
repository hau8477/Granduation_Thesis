package com.example.be.service.Impl;

import com.example.be.model.Announcement;
import com.example.be.repository.IAnnouncementRepository;
import com.example.be.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService implements IAnnouncementService {
    @Autowired
    private IAnnouncementRepository announcementRepository;
    @Override
    public List<Announcement> findAll(Long studentId) {
        return this.announcementRepository.findAll(studentId);
    }
}
