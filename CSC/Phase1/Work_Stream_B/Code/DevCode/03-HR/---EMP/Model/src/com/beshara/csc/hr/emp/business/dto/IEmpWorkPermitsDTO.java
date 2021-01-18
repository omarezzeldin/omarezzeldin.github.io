package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.IBasicDTO;

import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;

import java.sql.Date;

public interface IEmpWorkPermitsDTO extends IEmpDTO {
    
    
    
    
    public void setCivilId(Long civilId) ;

    public Long getCivilId() ;

    public void setFromDate(Date fromDate) ;

    public Date getFromDate() ;

    public void setUntilDate(Date untilDate) ;

    public Date getUntilDate() ;

    public void setAuditStatus(Long auditStatus) ;

    public Long getAuditStatus() ;

    public void setTabrecSerial(Long tabrecSerial) ;

    public Long getTabrecSerial() ;

    public void setEmployeesDTO(IEmployeesDTO employeesDTO) ;

    public IEmployeesDTO getEmployeesDTO() ;

    public void setMinCode(Long minCode) ;

    public Long getMinCode() ;

    public void setRealCivilId(Long realCivilId) ;

    public Long getRealCivilId() ;
    
    public void setMinistryDTO(IMinistriesDTO ministryDTO) ;

    public IMinistriesDTO getMinistryDTO();
    
    
}
