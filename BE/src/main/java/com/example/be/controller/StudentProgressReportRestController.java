package com.example.be.controller;

import com.example.be.dto.IStudentProgressReportDTO;
import com.example.be.service.Impl.StudentProgressReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api/studentProgressReport")
public class StudentProgressReportRestController {
    @Autowired
    private StudentProgressReportService studentProgressReportService;

    /**
     * Created by: SyVT,
     * Date created : 29/03/2023
     * Function : Find Student ProgressReport By progressReportId
     *
     * @return HttpStatus.OK if result is not error or HttpStatus.NO_CONTENT if no content
     */
    @GetMapping("/api/studentProgressReport/{id}/{totalElement}")
    public ResponseEntity<List<IStudentProgressReportDTO>> findStudentProgressReportById(@PathVariable Long id, @PathVariable("totalElement") Integer totalElement) {

        List<IStudentProgressReportDTO> studentProgressReportList = studentProgressReportService.findStudentProgressReportProjectId(id, totalElement);
        if (studentProgressReportList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentProgressReportList, HttpStatus.OK);
    }

    @GetMapping("/api/studentProgressReport/{id}")
    public ResponseEntity<List<IStudentProgressReportDTO>> findStudentProgressReportById(@PathVariable Long id) {

        List<IStudentProgressReportDTO> studentProgressReportList = studentProgressReportService.findStudentProgressReportProjectId(id);
        if (studentProgressReportList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentProgressReportList, HttpStatus.OK);
    }
}
