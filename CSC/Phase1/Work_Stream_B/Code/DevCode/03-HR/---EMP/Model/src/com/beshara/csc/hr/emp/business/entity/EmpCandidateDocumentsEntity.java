package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "EmpCandidateDocumentsEntity.findAll",
                             query = "select o from EmpCandidateDocumentsEntity o"),
                 @NamedQuery(name = "EmpCandidateDocumentsEntity.getByCndCode",
                             query = "SELECT o from EmpCandidateDocumentsEntity o where o.candidateCode=:candidateCode and o.candidateCodeSeq=:candidateCodeSeq"),
                 @NamedQuery(name = "EmpCandidateDocumentsEntity.findNewSerial",
                             query = "select MAX(o.cnddocSerial) from EmpCandidateDocumentsEntity o where o.candidateCode=:candidateCode and o.candidateCodeSeq=:candidateCodeSeq") })
@Table(name = "HR_EMP_CANDIDATE_DOCUMENTS")
@IdClass(IEmpCandidateDocumentsEntityKey.class)
public class EmpCandidateDocumentsEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CANDIDATE_CODE", nullable = false, insertable = false, updatable = false)
    private Long candidateCode;
    @Id
    @Column(name = "CANDIDATE_CODE_SEQ", nullable = false, insertable = false, updatable = false)
    private Long candidateCodeSeq;
    @Id
    @Column(name = "CNDDOC_SERIAL", nullable = false)
    private Long cnddocSerial;
    @Column(name = "DOCTYPE_CODE", nullable = false, insertable = true, updatable = true)
    private Long doctypeCode;
    @Column(name = "COMMENTS", length = 1000)
    private String comments;
    @Column(name = "DOC_STATUS", nullable = false)
    private Long docStatus;
    @Column(name = "ATTACHMENT_STATUS", nullable = false)
    private Long attachmentStatus;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "CANDIDATE_CODE", referencedColumnName = "CANDIDATE_CODE"),
                    @JoinColumn(name = "CANDIDATE_CODE_SEQ", referencedColumnName = "CANDIDATE_CODE_SEQ") })
    private EmpCandidatesEntity empCandidatesEntity;

    //    @ManyToOne
    //    @JoinColumn(name = "DOCTYPE_CODE", referencedColumnName = "DOCTYPE_CODE")
    //    private InfDocumentTypesEntity documentTypesEntity;


    public EmpCandidateDocumentsEntity() {
    }

    public Long getAttachmentStatus() {
        return attachmentStatus;
    }

    public void setAttachmentStatus(Long attachmentStatus) {
        this.attachmentStatus = attachmentStatus;
    }


    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }

    public void setCandidateCodeSeq(Long candidateCodeSeq) {
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public Long getCnddocSerial() {
        return cnddocSerial;
    }

    public void setCnddocSerial(Long cnddocSerial) {
        this.cnddocSerial = cnddocSerial;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(Long docStatus) {
        this.docStatus = docStatus;
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public EmpCandidatesEntity getEmpCandidatesEntity() {
        return empCandidatesEntity;
    }

    public void setEmpCandidatesEntity(EmpCandidatesEntity empCandidatesEntity) {
        this.empCandidatesEntity = empCandidatesEntity;
    }

    //    public void setDocumentTypesEntity(InfDocumentTypesEntity documentTypesEntity) {
    //        this.documentTypesEntity = documentTypesEntity;
    //    }
    //
    //    public InfDocumentTypesEntity getDocumentTypesEntity() {
    //        return documentTypesEntity;
    //    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }
}
