package com.example.be.service;

import com.example.be.model.Announcement;

import java.util.List;

public interface IAnnouncementService {
    List<Announcement> findAll(Long studentId);
}
