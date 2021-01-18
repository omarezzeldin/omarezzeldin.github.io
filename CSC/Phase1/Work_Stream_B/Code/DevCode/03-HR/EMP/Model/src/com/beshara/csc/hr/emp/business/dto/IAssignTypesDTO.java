package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.gn.grs.business.dto.ConditionsDTO;


public interface IAssignTypesDTO extends IEmpDTO {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setConditionsDTO(ConditionsDTO conditionsDTO);

    public ConditionsDTO getConditionsDTO();

}
