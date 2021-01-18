package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;

import java.sql.Date;


public interface IHireProceduresDTO extends IEmpDTO {
    //    public Long getGenderType();

    //    public Long getNationalityType();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    //    public void setGenderType(Long genderType);

    //    public void setNationalityType(Long nationalityType);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO);

    public IMinistriesDTO getMinistriesDTO();

    //  public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);

    //   public IGenderTypesDTO getGenderTypesDTO();

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setStatus(Long status);

    public Long getStatus();

}
