package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Timestamp;


public interface IEmployeeNchildCatsDTO extends IBasicDTO {
    public Long getCivilId();

    public Long getNchildsCatCode();

    public String getNchildsCatName();

    public Timestamp getNfromDate();

    public Long getNrValue();

    public void setCivilId(Long civilId);

    public void setNchildsCatCode(Long nchildsCatCode);

    public void setNchildsCatName(String nchildsCatName);

    public void setNfromDate(Timestamp nfromDate);

    public void setNrValue(Long nrValue);

}
