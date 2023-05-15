package com.example.be.service.Impl;

import com.example.be.dto.teacher.IDegreeDTO;
import com.example.be.repository.IDegreeRepository;
import com.example.be.service.IDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DegreeService implements IDegreeService {
    @Autowired
    private IDegreeRepository iDegreeRepository;

    @Override
    public List<IDegreeDTO> getAllDegree() {
        return iDegreeRepository.getAllDegree();
    }
}
