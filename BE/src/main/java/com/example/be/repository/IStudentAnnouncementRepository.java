package com.example.be.repository;

import com.example.be.model.StudentAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentAnnouncementRepository extends JpaRepository<StudentAnnouncement, Long> {
}
