package com.beshara.csc.hr.emp.business.dto;

import java.sql.Date;

public class AssignmentsSearchDTO extends EmpDTO implements IAssignmentsSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private String name;
    private Long admassignmentSerial;
    private Long assreasonCode;
    private Long assstatusCode;
    private Date fromDate;
    private Date untilDate;
    public AssignmentsSearchDTO() {    }    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    public Long getCivilId() {        return civilId;
    }    public void setName(String name) {        this.name = name;
    }    public String getName() {        return name;
    }    public void setAdmassignmentSerial(Long admassignmentSerial) {        this.admassignmentSerial = admassignmentSerial;
    }    public Long getAdmassignmentSerial() {        return admassignmentSerial;
    }    public void setAssreasonCode(Long assreasonCode) {        this.assreasonCode = assreasonCode;
    }    public Long getAssreasonCode() {        return assreasonCode;
    }    public void setAssstatusCode(Long assstatusCode) {        this.assstatusCode = assstatusCode;
    }    public Long getAssstatusCode() {        return assstatusCode;
    }    public void setFromDate(Date fromDate) {        this.fromDate = fromDate;
    }    public Date getFromDate() {        return fromDate;
    }    public void setUntilDate(Date untilDate) {        this.untilDate = untilDate;
    }    public Date getUntilDate() {        return untilDate;
    }}
