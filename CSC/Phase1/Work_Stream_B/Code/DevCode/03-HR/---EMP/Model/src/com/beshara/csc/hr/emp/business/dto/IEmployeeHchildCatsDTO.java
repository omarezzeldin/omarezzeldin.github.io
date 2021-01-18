package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Timestamp;


public interface IEmployeeHchildCatsDTO extends IBasicDTO {
    public Long getCivilId();

    public Long getHchildsCatCode();

    public String getHchildsCatName();

    public Timestamp getHfromDate();

    public Long getHrValue();

    public void setCivilId(Long civilId);

    public void setHchildsCatCode(Long hchildsCatCode);

    public void setHchildsCatName(String hchildsCatName);

    public void setHfromDate(Timestamp hfromDate);

    public void setHrValue(Long hrValue);

}
