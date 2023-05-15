package com.example.be.controller;

import com.example.be.model.Clazz;
import com.example.be.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/clazz")
public class ClazzRestController {
    @Autowired
    private IClazzService clazzService;
    @GetMapping("")
    public ResponseEntity<List<Clazz>> findAll(){
        List<Clazz> clazzList = clazzService.findAll();
        if (clazzList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clazzList,HttpStatus.OK);
    }
}
