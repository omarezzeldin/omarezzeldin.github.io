package com.beshara.csc.hr.emp.business.dto;

public interface IEmpCandidateStatusDTO extends IEmpDTO{
    public Long getCndstatusCode();

    public void setCndstatusCode(Long cndstatusCode);


    public String getCndstatusName();

    public void setCndstatusName(String cndstatusName);

    public Long getTabrecSerial();

    public void setTabrecSerial(Long tabrecSerial);

    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();
}

