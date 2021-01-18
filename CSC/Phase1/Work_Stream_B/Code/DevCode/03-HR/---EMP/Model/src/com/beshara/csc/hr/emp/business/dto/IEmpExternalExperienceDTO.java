package com.beshara.csc.hr.emp.business.dto;

import java.sql.Timestamp;


public interface IEmpExternalExperienceDTO extends IEmpDTO {

    public String getExperienceDesc();

    public Timestamp getFromDate();

    public Timestamp getToDate();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setExperienceDesc(String experienceDesc);

    public void setFromDate(Timestamp fromDate);

    public void setToDate(Timestamp toDate);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setEmployeesDTO(IEmployeesDTO employeesDTO);

    public IEmployeesDTO getEmployeesDTO();
}
