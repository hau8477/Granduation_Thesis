package com.example.be.service.Impl;

import com.example.be.dto.teacher.IFacultyDTO;
import com.example.be.repository.IFacultyRepository;
import com.example.be.service.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacultyService implements IFacultyService {
    @Autowired
    private IFacultyRepository iFacultyRepository;
    @Override
    public List<IFacultyDTO> getAllFaculty() {
        return iFacultyRepository.getAllFaculty();
    }
}
