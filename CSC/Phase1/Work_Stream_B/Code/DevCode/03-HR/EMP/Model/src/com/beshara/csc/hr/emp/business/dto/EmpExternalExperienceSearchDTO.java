package com.beshara.csc.hr.emp.business.dto;

import java.math.BigDecimal;

import java.sql.Date;


public class EmpExternalExperienceSearchDTO extends EmpDTO implements IEmpExternalExperienceSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long civilId;
    private String experienceDesc;
    private BigDecimal serial;
    private Date fromDate;
    private Date untilDate;

    public EmpExternalExperienceSearchDTO() {
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setExperienceDesc(String experienceDesc) {
        this.experienceDesc = experienceDesc;
    }

    public String getExperienceDesc() {
        return experienceDesc;
    }

    public void setSerial(BigDecimal serial) {
        this.serial = serial;
    }

    public BigDecimal getSerial() {
        return serial;
    }
}
