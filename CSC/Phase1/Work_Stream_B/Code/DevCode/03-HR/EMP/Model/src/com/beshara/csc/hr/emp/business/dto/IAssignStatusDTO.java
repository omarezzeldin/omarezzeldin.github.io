package com.beshara.csc.hr.emp.business.dto;


public interface IAssignStatusDTO extends IEmpDTO {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
