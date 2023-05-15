package com.example.be.service.Impl;

import com.example.be.model.Clazz;
import com.example.be.repository.IClazzRepository;
import com.example.be.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private IClazzRepository clazzRepository;
    @Override
    public List<Clazz> findAll() {
        return clazzRepository.findAll();
    }
}
