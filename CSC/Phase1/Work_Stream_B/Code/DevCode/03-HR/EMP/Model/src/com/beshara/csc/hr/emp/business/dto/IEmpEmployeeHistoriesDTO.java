package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.nl.job.business.dto.JobsDTO;
import com.beshara.csc.nl.org.business.dto.WorkCentersDTO;

import java.sql.Timestamp;

import java.util.List;


public interface IEmpEmployeeHistoriesDTO extends IEmpDTO {
    public Long getSerial();

    public Long getCivilId();

    public String getWrkCode();

    public Long getMinCode();

    public String getJobCode();

    public String getTechJobCode();

    public Timestamp getFromDate();

    public Timestamp getUntilDate();

    public Long getAuditBy();

    public Timestamp getAuditDate();

    public Long getPisFlag();

    public Long getPerFlag();

    public Long getTrxtypeCode();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setSerial(Long serial);

    public void setCivilId(Long civilId);

    public void setWrkCode(String wrkCode);

    public void setMinCode(Long minCode);

    public void setJobCode(String jobCode);

    public void setTechJobCode(String techJobCode);

    public void setFromDate(Timestamp fromDate);

    public void setUntilDate(Timestamp untilDate);

    public void setAuditBy(Long auditBy);

    public void setAuditDate(Timestamp auditDate);

    public void setPisFlag(Long pisFlag);

    public void setPerFlag(Long perFlag);

    public void setTrxtypeCode(Long trxtypeCode);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setWorkCentersDTO(WorkCentersDTO workCentersDTO);

    public WorkCentersDTO getWorkCentersDTO();

    public void setJobsDTO(JobsDTO jobsDTO);

    public JobsDTO getJobsDTO();

    public void setTechJobsDTO(JobsDTO techJobsDTO);

    public JobsDTO getTechJobsDTO();

    public void setWorkCentersDTOList(List<WorkCentersDTO> workCentersDTOList);

    public List<WorkCentersDTO> getWorkCentersDTOList();

    public void setJobsDTOList(List<JobsDTO> jobsDTOList);

    public List<JobsDTO> getJobsDTOList();

    public void setTechJobsDTOList(List<JobsDTO> techJobsDTOList);

    public List<JobsDTO> getTechJobsDTOList();

}
