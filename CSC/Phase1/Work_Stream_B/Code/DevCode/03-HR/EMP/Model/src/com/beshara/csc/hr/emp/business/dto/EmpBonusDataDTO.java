package com.beshara.csc.hr.emp.business.dto;

import java.math.BigDecimal;

import java.util.Date;

public class EmpBonusDataDTO implements IEmpBonusData{
    private static final long serialVersionUID = 1L;
    private Long realCivilID;
    private BigDecimal elemntValue;
    private BigDecimal rasiseVal;
    private Date fromDate;
    private Date untilDate;
    private String elmGuideName;
    public EmpBonusDataDTO() {
        super();
    }

    public void setRealCivilID(Long realCivilID) {
        this.realCivilID = realCivilID;
    }

    public Long getRealCivilID() {
        return realCivilID;
    }

    public void setElemntValue(BigDecimal elemntValue) {
        this.elemntValue = elemntValue;
    }

    public BigDecimal getElemntValue() {
        return elemntValue;
    }

    public void setRasiseVal(BigDecimal rasiseVal) {
        this.rasiseVal = rasiseVal;
    }

    public BigDecimal getRasiseVal() {
        return rasiseVal;
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

    public void setElmGuideName(String elmGuideName) {
        this.elmGuideName = elmGuideName;
    }

    public String getElmGuideName() {
        return elmGuideName;
    }
}
