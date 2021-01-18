package com.beshara.csc.hr.emp.business.dto;


public interface IProcedureResultsDTO extends IEmpDTO {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
