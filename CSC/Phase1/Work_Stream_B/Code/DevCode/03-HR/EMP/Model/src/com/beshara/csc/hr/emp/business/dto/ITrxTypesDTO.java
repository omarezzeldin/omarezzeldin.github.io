package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.IBasicDTO;


public interface ITrxTypesDTO extends IBasicDTO {
    public Long getTrxtypeCode();

    public String getTrxtypeName();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setTrxtypeCode(Long trxtypeCode);

    public void setTrxtypeName(String trxtypeName);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

}
