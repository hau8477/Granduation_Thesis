package com.example.be.repository;
import com.example.be.model.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface IClazzRepository extends JpaRepository<Clazz, Long> {
    @Query(value = "select * from clazz",nativeQuery = true)
    List<Clazz> findAll();

}
