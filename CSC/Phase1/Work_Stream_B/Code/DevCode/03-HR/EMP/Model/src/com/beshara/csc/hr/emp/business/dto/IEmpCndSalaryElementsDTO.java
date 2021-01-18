package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.sal.business.dto.ISalDegreeReasonsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;

import java.sql.Date;

public interface IEmpCndSalaryElementsDTO  extends IEmpDTO  {
    
    public void setCndSalelmSerial(Long cndSalelmSerial) ;

    public Long getCndSalelmSerial() ;

    public void setCandidateCode(Long candidateCode);

    public Long getCandidateCode() ;

    public void setCandidateCodeSeq(Long candidateCodeSeq) ;

    public Long getCandidateCodeSeq() ;

    public void setElmguideCode(Long elmguideCode) ;

    public Long getElmguideCode() ;

    public void setElementValue(Float elementValue);

    public Float getElementValue();

    public void setElementRatio(Long elementRatio) ;

    public Long getElementRatio();

    public void setFromDate(Date fromDate);

    public Date getFromDate() ;

    public void setUntilDate(Date untilDate) ;

    public Date getUntilDate() ;

    public void setElmguideCodeEquv(Long elmguideCodeEquv) ;

    public Long getElmguideCodeEquv() ;

    public void setCndSalelmComment(String cndSalelmComment) ;

    public String getCndSalelmComment() ;

    public void setDegreasonCode(Long degreasonCode) ;

    public Long getDegreasonCode() ;

    public void setAuditStatus(Long auditStatus) ;

    public Long getAuditStatus() ;

    public void setTabrecSerial(Long tabrecSerial) ;

    public Long getTabrecSerial() ;

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO) ;

    public IEmpCandidatesDTO getEmpCandidatesDTO() ;

    public void setSalDegreeReasonsDTO(ISalDegreeReasonsDTO salDegreeReasonsDTO) ;

    public ISalDegreeReasonsDTO getSalDegreeReasonsDTO() ;

    public void setSalElementGuidesDTO(ISalElementGuidesDTO salElementGuidesDTO) ;

    public ISalElementGuidesDTO getSalElementGuidesDTO() ;

    public void setSalEqElementGuidesDTO(ISalElementGuidesDTO salEqElementGuidesDTO) ;

    public ISalElementGuidesDTO getSalEqElementGuidesDTO() ;

   
}
