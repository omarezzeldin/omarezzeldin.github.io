package com.beshara.csc.hr.emp.business.dto;


public interface IDocumentTypesDTO extends IEmpDTO {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
