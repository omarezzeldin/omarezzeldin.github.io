package com.beshara.csc.hr.emp.business.dto;

import java.math.BigDecimal;

import java.sql.Date;


public interface IEmpExternalExperienceSearchDTO extends IEmpDTO {
    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setExperienceDesc(String experienceDesc);

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public String getExperienceDesc();

    public BigDecimal getSerial();

    public void setSerial(BigDecimal serial);

}
