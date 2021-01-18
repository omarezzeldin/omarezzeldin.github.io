package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Timestamp;


public interface IRaiseDataDTO extends IBasicDTO {
    public Long getCivilId();

    public Long getFincadreCode();

    public Long getFingroupCode();

    public Long getFindegreeCode();

    public Long getFinraiseCode();

    public Long getDegreasonCode();

    public Timestamp getFromDate();

    public Long getValue();

    public String getCalcFunction();

    public void setCivilId(Long civilId);

    public void setFincadreCode(Long fincadreCode);

    public void setFingroupCode(Long fingroupCode);

    public void setFindegreeCode(Long findegreeCode);

    public void setFinraiseCode(Long finraiseCode);

    public void setDegreasonCode(Long degreasonCode);

    public void setFromDate(Timestamp fromDate);

    public void setValue(Long value);

    public void setCalcFunction(String calcFunction);

}
