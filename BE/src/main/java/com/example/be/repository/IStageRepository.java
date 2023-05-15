package com.example.be.repository;

import com.example.be.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStageRepository extends JpaRepository<Stage, Long> {
}
