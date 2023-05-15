package com.example.be.repository;
import com.example.be.dto.IDocumentDto;
import com.example.be.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IDocumentRepository extends JpaRepository<Document, Long> {
    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: show List DocumentDto
     * @param keySearch1
     * @param pageable
     */
    @Query(value =
            "SELECT " +
                    "    `document`.document_id as documentId, " +
                    "    `document`.document_name as documentName, " +
                    "    `document`.document_describe as documentDescribe, " +
                    "    `document`.document_file as documentFile  " +
                    "FROM `document`  " +
                    "WHERE `document`.document_name LIKE CONCAT('%', :keySearch1, '%')  " +
                    "AND `document`.flag_delete = false" +
                    " order by `document`.document_id desc",
            countQuery =
                    "SELECT " +
                            "    `document`.document_id as documentId, " +
                            "    `document`.document_name as documentName, " +
                            "    `document`.document_describe as documentDescribe, " +
                            "    `document`.document_file as documentFile  " +
                            "FROM `document`  " +
                            "WHERE " +
                            "    `document`.document_name LIKE CONCAT('%', :keySearch1, '%')  " +
                            "    AND `document`.flag_delete = false" +
                            " order by `document`.document_id desc ",
            nativeQuery = true
    )
    public Page<IDocumentDto> findAllDocumentDto(@Param("keySearch1") String keySearch1, Pageable pageable);


    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: delete DocumentDto by id
     * @param id
     */
    @Transactional
    @Modifying
    @Query(value = "update document set flag_delete=true where document_id = :id",
            countQuery = "update document set flag_delete=true where document_id = :id"
            , nativeQuery = true)
    void removeDocument(@Param("id") Long id);


    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: find by id
     * @param id
     */
    @Query(value =
            "select " +
                    "document_id as documentId,\n" +
                    "       document_name as documentName,\n" +
                    "       document_describe as documentDescribe,\n" +
                    "       document_file as documentFile\n" +
                    "from document where document_id = :id ",
            countQuery =
                    "select document_id as documentId,\n" +
                            "       document_name as documentName,\n" +
                            "       document_describe as documentDescribe,\n" +
                            "       document_file as documentFile\n" +
                            "from document where document_id = :id",
            nativeQuery = true)
    IDocumentDto getDocumentFindById(@Param("id") Long id);

    /**
     * Create by: TuanNDN
     * Date created: 29/03/2023
     * Function: create Document
     * @param 'documentDescribe'
     * @param 'documentFile'
     * @param 'documentName'
     */
    @Transactional
    @Modifying
    @Query(value =
            "insert into document (document_describe, document_file, document_name) " +
                    "value (:documentDescribe,:documentFile,:documentName)",
            nativeQuery = true)
    void addDocument(@Param("documentDescribe") String documentDescribe,@Param("documentFile") String documentFile, @Param("documentName")String documentName);


}
