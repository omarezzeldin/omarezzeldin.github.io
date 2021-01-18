package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Date;
import java.sql.Timestamp;


public interface IEmployeeProceduresDTO extends IEmpDTO {
    public Long getProresultCode();

    public String getProcDesc();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setProresultCode(Long proresultCode);

    public void setProcDesc(String procDesc);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setProcedureResultsDTO(IBasicDTO procedureResultsDTO);

    public IBasicDTO getProcedureResultsDTO();

    public void setHireProcedureDTO(IBasicDTO hireProcedureDTO);

    public IBasicDTO getHireProcedureDTO();

    public void setSendDate(Date sendDate);

    public Date getSendDate();

    public void setResultDate(Date resultDate);

    public Date getResultDate();

    public void setEmployeesDTO(IBasicDTO employeesDTO);

    public IBasicDTO getEmployeesDTO();

    public String getProcedureResultKey();

    public void setProcedureResultKey(String key);

    public void setEmpReasonDataDTO(IBasicDTO empReasonDataDTO);

    public IBasicDTO getEmpReasonDataDTO();

    public String getEmpReasonDataKey();

    public void setEmpReasonDataKey(String key);

    public void setPostponeDate(Timestamp postponeDate);

    public Timestamp getPostponeDate();
}
