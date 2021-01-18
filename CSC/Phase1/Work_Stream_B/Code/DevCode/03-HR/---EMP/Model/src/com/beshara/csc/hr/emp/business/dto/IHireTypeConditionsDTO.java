package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondDTO;

import java.sql.Date;


public interface IHireTypeConditionsDTO extends IEmpDTO, IJoinCondDTO {

    public Long getConditionCode();

    public Long getStatus();

    public Long getOptionFlag();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setConditionCode(Long conditionCode);

    public void setStatus(Long basicStatus);


    public void setOptionFlag(Long OptionFlag);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setHireTypeDTO(IBasicDTO hireTypeDTO);

    public IBasicDTO getHireTypeDTO();

    public void setFromDate(Date fromDate);

    public Date getFromDate();

    public void setUntilDate(Date untilDate);

    public Date getUntilDate();

    public void setConditionsDTO(IConditionsDTO conditionsDTO);

    public IConditionsDTO getConditionsDTO();

}
