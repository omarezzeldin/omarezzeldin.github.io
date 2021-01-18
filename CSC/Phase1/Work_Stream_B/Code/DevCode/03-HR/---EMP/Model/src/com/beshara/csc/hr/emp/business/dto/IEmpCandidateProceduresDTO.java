package com.beshara.csc.hr.emp.business.dto;

import java.sql.Date;

public interface IEmpCandidateProceduresDTO extends IEmpDTO {


    public void setProcDesc(String procDesc);

    public String getProcDesc();

    public void setSendDate(Date sendDate);

    public Date getSendDate();

    public void setResultDate(Date resultDate);

    public Date getResultDate();

    public void setPostponeDate(Date postponeDate);

    public Date getPostponeDate();

    public void setAuditStatus(Long auditStatus);

    public Long getAuditStatus();

    public void setTabrecSerial(Long tabrecSerial);

    public Long getTabrecSerial();

    public void setEmpReasonDataDTO(IEmpReasonDataDTO empReasonDataDTO);

    public IEmpReasonDataDTO getEmpReasonDataDTO();

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO);

    public IEmpCandidatesDTO getEmpCandidatesDTO();

    public void setProcedureResultsDTO(IProcedureResultsDTO procedureResultsDTO);

    public IProcedureResultsDTO getProcedureResultsDTO();

    public void setHireProceduresDTO(IHireProceduresDTO hireProceduresDTO);

    public IHireProceduresDTO getHireProceduresDTO();

    public void setProcedureResultKey(String key);

    public String getProcedureResultKey();


    public void setEmpReasonDataKey(String key);

    public String getEmpReasonDataKey();

    public void setCrmStatusCode(String crmStatusCode);

    public String getCrmStatusCode();

    public void setCrmLetterNo(String crmLetterNo);

    public String getCrmLetterNo();

    public void setCrmLetterDate(Date crmLetterDate);

    public Date getCrmLetterDate();

    public void setCrmSheetNo(String crmSheetNo);

    public String getCrmSheetNo();
    
    public void setCrmChecked(boolean crmChecked);

    public boolean isCrmChecked();

}
