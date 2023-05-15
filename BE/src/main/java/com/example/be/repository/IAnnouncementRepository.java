package com.example.be.repository;

import com.example.be.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query(value = "SELECT a.* FROM announcement as a " +
            "join student_announcement an on an.announcement_id = a.announcement_id " +
            "where an.student_id = :studentId", nativeQuery = true)
    List<Announcement> findAll(@Param("studentId") Long studentId);

}
