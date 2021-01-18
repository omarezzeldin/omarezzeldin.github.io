package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Timestamp;


public interface IDegreeDataDTO extends IBasicDTO {
    public Long getCivilId();

    public Long getFincadreCode();

    public Long getFingroupCode();

    public Long getFindegreeCode();

    public Timestamp getDegreeDate();

    public Long getStatus();

    public void setCivilId(Long civilId);

    public void setFincadreCode(Long fincadreCode);

    public void setFingroupCode(Long fingroupCode);

    public void setFindegreeCode(Long findegreeCode);

    public void setDegreeDate(Timestamp degreeDate);

    public void setStatus(Long status);

}
