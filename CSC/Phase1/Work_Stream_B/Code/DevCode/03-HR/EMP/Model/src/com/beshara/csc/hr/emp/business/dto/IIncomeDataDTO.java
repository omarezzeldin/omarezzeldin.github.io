package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Timestamp;


public interface IIncomeDataDTO extends IBasicDTO {
    public Long getCivilId();

    public Long getCatincomeCode();

    public Long getIncomeCode();

    public Long getInccatCode();

    public Timestamp getFromDate();

    public Long getValue();

    public String getCalcFunction();

    public void setCivilId(Long civilId);

    public void setCatincomeCode(Long catincomeCode);

    public void setIncomeCode(Long incomeCode);

    public void setInccatCode(Long inccatCode);

    public void setFromDate(Timestamp fromDate);

    public void setValue(Long value);

    public void setCalcFunction(String calcFunction);

}
