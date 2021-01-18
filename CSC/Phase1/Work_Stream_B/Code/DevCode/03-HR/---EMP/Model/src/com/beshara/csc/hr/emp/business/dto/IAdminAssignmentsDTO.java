package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.inf.business.dto.IDecisionMakerTypesDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;

import java.sql.Date;


public interface IAdminAssignmentsDTO extends IEmpDTO {
    public Date getFromDate();

    public Date getUntilDate();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setFromDate(Date fromDate);

    public void setUntilDate(Date untilDate);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setAssignReasonsDTO(IAssignReasonsDTO assignReasonsDTO);

    public IAssignReasonsDTO getAssignReasonsDTO();

    public void setAssignStatusDTO(IAssignStatusDTO assignStatusDTO);

    public IAssignStatusDTO getAssignStatusDTO();

    public void setAssignTypesDTO(IAssignTypesDTO assignTypesDTO);

    public IAssignTypesDTO getAssignTypesDTO();

    public void setEmployeesDTO(IEmployeesDTO employeesDTO);

    public IEmployeesDTO getEmployeesDTO();

    public void setSalElementGuidesDTO(ISalElementGuidesDTO salElementGuidesDTO);

    public ISalElementGuidesDTO getSalElementGuidesDTO();

    public void setDecisionMakerTypesDTO(IDecisionMakerTypesDTO decisionMakerTypesDTO);

    public IDecisionMakerTypesDTO getDecisionMakerTypesDTO();

    public void setJobsDTO(IJobsDTO jobsDTO);

    public IJobsDTO getJobsDTO();

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO);

    public IWorkCentersDTO getWorkCentersDTO();

}
