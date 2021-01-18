package com.beshara.csc.hr.emp.business.dto;

import java.sql.Date;


public interface IHireStagesDTO extends IEmpDTO {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);
    
    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setStatus(Long status);

    public Long getStatus();
}
