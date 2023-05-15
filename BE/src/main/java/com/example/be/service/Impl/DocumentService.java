package com.example.be.service.Impl;

import com.example.be.dto.IDocumentDto;
import com.example.be.model.Document;
import com.example.be.repository.IDocumentRepository;
import com.example.be.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DocumentService implements IDocumentService {
    @Autowired
    private IDocumentRepository documentRepository;

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List DocumentDto
     * @param keySearch1
     * @param pageable
     */
    @Override
    public Page<IDocumentDto> findAllDocumentDto(String keySearch1, Pageable pageable) {
        return documentRepository.findAllDocumentDto(keySearch1, pageable);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: delete DocumentDto by id
     * @param id
     */
    @Override
    public void removeDocument(Long id) {
        documentRepository.removeDocument(id);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: find by id
     * @param id
     */
    @Override
    public IDocumentDto getDocumentFindById(Long id) {
        return documentRepository.getDocumentFindById(id);
    }

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: create Document
     * @param 'documentDescribe'
     * @param 'documentFile'
     * @param 'documentName'
     */
    @Override
    public void addDocument(String documentDescribe, String documentFile, String documentName) {
        documentRepository.addDocument(documentDescribe,documentFile,documentName);
    }

    @Override
    public void saveDocument(Document documentDto) {
        documentRepository.save(documentDto);
    }
}
