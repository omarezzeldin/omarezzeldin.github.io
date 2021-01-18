package com.beshara.csc.hr.emp.business.dto;

import java.sql.Date;

public interface IEmpCandidateExtraDataDTO extends IEmpDTO{
    
    public void setCandidateCode(Long candidateCode);

    public Long getCandidateCode();

    public void setCandidateCodeSeq(Long candidateCodeSeq) ;

    public Long getCandidateCodeSeq() ;

    public void setExtDatTypeCode(Long extDatTypeCode);

    public Long getExtDatTypeCode() ;

    public void setValue(String value) ;

    public String getValue() ;

    public void setValueDesc(String valueDesc) ;

    public String getValueDesc() ;

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate() ;

    public void setStatus(Long status) ;
    ;

    public Long getStatus() ;

    public void setAuditStatus(Long auditStatus) ;
    

    public Long getAuditStatus() ;

    public void setTabrecSerial(Long tabrecSerial) ;

    public Long getTabrecSerial();

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO);

    public IEmpCandidatesDTO getEmpCandidatesDTO();

    public void setEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO empExtraDataTypesDTO) ;

    public IEmpExtraDataTypesDTO getEmpExtraDataTypesDTO() ;
}
