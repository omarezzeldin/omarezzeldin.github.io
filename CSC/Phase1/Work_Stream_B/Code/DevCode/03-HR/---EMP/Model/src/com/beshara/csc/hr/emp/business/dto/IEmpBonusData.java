package com.beshara.csc.hr.emp.business.dto;

import java.math.BigDecimal;

import java.util.Date;

public interface IEmpBonusData {
    public void setRealCivilID(Long realCivilID);
    public Long getRealCivilID();
    public void setElemntValue(BigDecimal elemntValue);
    public BigDecimal getElemntValue();
    public void setRasiseVal(BigDecimal rasiseVal);
    public BigDecimal getRasiseVal();
    public void setFromDate(Date fromDate);
    public Date getFromDate();
    public void setUntilDate(Date untilDate);
    public Date getUntilDate();
    public void setElmGuideName(String elmGuideName);
    public String getElmGuideName();
}
