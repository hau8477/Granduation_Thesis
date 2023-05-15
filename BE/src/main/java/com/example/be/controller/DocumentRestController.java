package com.example.be.controller;
import com.example.be.dto.DocumentDto;
import com.example.be.dto.IDocumentDto;
import com.example.be.model.Document;
import com.example.be.service.IDocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/document")
public class DocumentRestController {
    @Autowired
    private IDocumentService documentService;

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List DocumentDto
     * @param keySearch1
     * @param pageable
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus. OK if result is not error
     */
    @GetMapping("")
    public ResponseEntity<Page<IDocumentDto>> getAllAndSearchDocument(
            @RequestParam(defaultValue = "", required = false) String keySearch1,
            @PageableDefault(value = 5) Pageable pageable) {
        Page<IDocumentDto> documentDto = documentService.findAllDocumentDto(keySearch1, pageable);
        if (documentDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(documentDto, HttpStatus.OK);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: delete DocumentDto by id
     * @param id
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus. OK if result is not error
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Document> deleteDocument(@PathVariable("id") Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        documentService.removeDocument(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: find by id
     * @param id
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus. OK if result is not error
     */
    @GetMapping("/info/{id}")
    public ResponseEntity<Document> GetDocumentFindById(@PathVariable Long id) {
        IDocumentDto documentDto = documentService.getDocumentFindById(id);
        if (documentDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(documentDto, HttpStatus.OK);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: create Document
     * @param 'documentDescribe'
     * @param 'documentFile'
     * @param 'documentName'
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus. OK if result is not error
     */
//    @PostMapping("/create-document")
//    public ResponseEntity createDocument(@Valid @RequestBody DocumentDto documentDto, BindingResult bindingResult) {
//        documentDto.validate(documentDto,bindingResult);
//        if (documentDto == null || bindingResult.hasErrors()) {
//            return new ResponseEntity<>(bindingResult.getAllErrors(),HttpStatus.BAD_REQUEST);
//        }
//        documentService.addDocument(documentDto.getDocumentDescribe(),documentDto.getDocumentFile(),documentDto.getDocumentName());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    @PostMapping("/create-document")
    public ResponseEntity createDocument(@Valid @RequestBody DocumentDto documentDto, BindingResult bindingResult) {
        documentDto.validate(documentDto, bindingResult);
        if (documentDto == null || bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Document document = new Document();
        BeanUtils.copyProperties(documentDto, document);
        documentService.saveDocument(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
