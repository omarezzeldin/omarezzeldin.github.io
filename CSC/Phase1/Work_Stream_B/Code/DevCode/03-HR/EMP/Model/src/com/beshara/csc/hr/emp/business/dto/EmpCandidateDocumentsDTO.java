package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;

import java.util.List;

public class EmpCandidateDocumentsDTO extends EmpDTO implements IEmpCandidateDocumentsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long candidateCode;

    private Long candidateCodeSeq;

    private Long cnddocSerial;

    private Long doctypeCode;

    private String comments;

    private Long docStatus;

    private Long attachmentStatus;

    private Long auditStatus;

    private Long tabrecSerial;

    private IEmpCandidatesDTO empCandidateDTO;


    private IInfDocumentTypesDTO documentTypesDTO;
    
    private boolean attachmentStatusFlag;
    
    private boolean docStatusFlag;
    
    private boolean attachmentStatusEnabled=true;
    
    private List attachmentsLst;
    
    public EmpCandidateDocumentsDTO() {
        super();
    }

    public EmpCandidateDocumentsDTO(EmpCandidateDocumentsEntity candidateDocumentsEntity) {
        EmpEntityConverter.getEmpCandidateDocumentsDTO(candidateDocumentsEntity);
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCodeSeq(Long candidateCodeSeq) {
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }

    public void setCnddocSerial(Long cnddocSerial) {
        this.cnddocSerial = cnddocSerial;
    }

    public Long getCnddocSerial() {
        return cnddocSerial;
    }

    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    public Long getDoctypeCode() {
        return doctypeCode;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setDocStatus(Long docStatus) {
        this.docStatus = docStatus;
    }

    public Long getDocStatus() {
        return docStatus;
    }

    public void setAttachmentStatus(Long attachmentStatus) {
        this.attachmentStatus = attachmentStatus;
    }

    public Long getAttachmentStatus() {
        return attachmentStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setEmpCandidateDTO(IEmpCandidatesDTO empCandidateDTO) {
        this.empCandidateDTO = empCandidateDTO;
    }

    public IEmpCandidatesDTO getEmpCandidateDTO() {
        return empCandidateDTO;
    }

    public void setDocumentTypesDTO(IInfDocumentTypesDTO documentTypesDTO) {
        this.documentTypesDTO = documentTypesDTO;
    }

    public IInfDocumentTypesDTO getDocumentTypesDTO() {
        return documentTypesDTO;
    }


//    public void setDocStatusFlag(boolean docStatusFlag) {
//        if (docStatusFlag) {
//            setDocStatus(new Long(1));
//        } else {
//            setDocStatus(new Long(0));
//        }
//    }
//
//    public boolean isDocStatusFlag() {
//        if ( docStatus != null && docStatus.intValue() == 1) {
//            return true;
//        }
//        return false;
//    
//    }
//
//    public void setAttachmentStatusFlag(boolean attachmentStatusFlag) {
//        if (attachmentStatusFlag) {
//            setAttachmentStatus(new Long(1));
//        } else {
//            setAttachmentStatus(new Long(0));
//        }
//    }
//
//    public boolean isAttachmentStatusFlag() {
//        if ( attachmentStatus != null && attachmentStatus.intValue() == 1) {
//            return true;
//        }
//        return false;
//    }


    public void setAttachmentStatusFlag(boolean attachmentStatusFlag) {
        if (attachmentStatusFlag) {
                    setAttachmentStatus(new Long(1));
                } else {
                    setAttachmentStatus(new Long(0));
                }
    }

    public boolean isAttachmentStatusFlag() {
        if ( attachmentStatus != null && attachmentStatus.intValue() == 1) {
                    return true;
                }
                return false;
    }
    
    public void setAttachmentStatusEnabled(boolean attachmentStatusEnabled) {
       this.attachmentStatusEnabled=attachmentStatusEnabled;
    }

    public boolean isAttachmentStatusEnabled() {
        return this.attachmentStatusEnabled;
    }
    
   

    public void setDocStatusFlag(boolean docStatusFlag) {
                if (docStatusFlag) {
                    setDocStatus(new Long(1));
                } else {
                    setDocStatus(new Long(0));
                }
    }

    public boolean isDocStatusFlag() {
                if ( docStatus != null && docStatus.intValue() == 1) {
                    return true;
                }
                return false;
    }

    public void setAttachmentsLst(List attachmentsLst) {
        this.attachmentsLst = attachmentsLst;
    }

    public List getAttachmentsLst() {
        return attachmentsLst;
    }
}
