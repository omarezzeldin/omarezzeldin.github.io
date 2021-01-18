package com.beshara.csc.hr.emp.business.dto;

import java.sql.Date;


public interface IAssignmentsSearchDTO extends IEmpDTO {
    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setName(String name);

    public String getName();

    public void setAdmassignmentSerial(Long admassignmentSerial);

    public Long getAdmassignmentSerial();

    public void setAssreasonCode(Long assreasonCode);

    public Long getAssreasonCode();

    public void setAssstatusCode(Long assstatusCode);

    public Long getAssstatusCode();

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

}
