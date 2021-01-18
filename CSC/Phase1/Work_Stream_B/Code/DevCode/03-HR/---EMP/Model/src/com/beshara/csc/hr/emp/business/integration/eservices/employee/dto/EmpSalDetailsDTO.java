package com.beshara.csc.hr.emp.business.integration.eservices.employee.dto;

import java.io.Serializable;

import java.util.Date;

public class EmpSalDetailsDTO implements Serializable{
    @SuppressWarnings("compatibility:8829945462897504832")
    private static final long serialVersionUID = 1L;
    private Float elementValue;
    private String name;
    private Date fromDate;
    public EmpSalDetailsDTO() {
        super();
    }

    public void setElementValue(Float elementValue) {
        this.elementValue = elementValue;
    }

    public Float getElementValue() {
        return elementValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }
}
