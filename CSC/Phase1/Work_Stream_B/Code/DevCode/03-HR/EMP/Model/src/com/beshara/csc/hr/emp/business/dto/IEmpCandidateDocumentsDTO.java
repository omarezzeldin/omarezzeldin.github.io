package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;

import java.util.List;

public interface IEmpCandidateDocumentsDTO extends IEmpDTO{
    
    public void setCandidateCode(Long candidateCode) ;

    public Long getCandidateCode() ;

    public void setCandidateCodeSeq(Long candidateCodeSeq) ;

    public Long getCandidateCodeSeq() ;
    public void setCnddocSerial(Long cnddocSerial) ;

    public Long getCnddocSerial() ;

    public void setDoctypeCode(Long doctypeCode) ;

    public Long getDoctypeCode() ;

    public void setComments(String comments) ;

    public String getComments() ;
    public void setDocStatus(Long docStatus);

    public Long getDocStatus() ;

    public void setAttachmentStatus(Long attachmentStatus) ;

    public Long getAttachmentStatus() ;
    public void setAuditStatus(Long auditStatus) ;

    public Long getAuditStatus() ;
    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial() ;

    public void setEmpCandidateDTO(IEmpCandidatesDTO empCandidateDTO) ;

    public IEmpCandidatesDTO getEmpCandidateDTO() ;

    public void setDocumentTypesDTO(IInfDocumentTypesDTO documentTypesDTO) ;

    public IInfDocumentTypesDTO getDocumentTypesDTO() ;
    
    public void setDocStatusFlag(boolean docStatusFlag) ;

    public boolean isDocStatusFlag();

    public void setAttachmentStatusFlag(boolean attachmentStatusFlag);

    public boolean isAttachmentStatusFlag();
    public void setAttachmentStatusEnabled(boolean attachmentStatusFlag);

    public boolean isAttachmentStatusEnabled();
    
    public void setAttachmentsLst(List attachmentsLst);

    public List getAttachmentsLst();
}
