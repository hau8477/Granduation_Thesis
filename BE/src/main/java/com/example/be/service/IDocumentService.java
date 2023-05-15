package com.example.be.service;

import com.example.be.dto.IDocumentDto;
import com.example.be.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDocumentService {
    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List DocumentDto
     * @param keySearch1
     * @param pageable
     */
    public Page<IDocumentDto> findAllDocumentDto(String keySearch1, Pageable pageable);

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: delete DocumentDto by id
     * @param id
     */
    void removeDocument(Long id);


    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: find by id
     * @param id
     */
    IDocumentDto getDocumentFindById(Long id);


    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: create Document
     * @param 'documentDescribe'
     * @param 'documentFile'
     * @param 'documentName'
     */
    void addDocument( String documentDescribe, String documentFile, String documentName);

    public void saveDocument(Document documentDto);
}
