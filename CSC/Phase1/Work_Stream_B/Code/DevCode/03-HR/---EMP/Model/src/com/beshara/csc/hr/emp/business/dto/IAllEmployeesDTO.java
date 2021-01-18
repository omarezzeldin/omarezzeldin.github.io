package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.nl.bnk.business.dto.BankBranchesDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;

import java.sql.Timestamp;


public interface IAllEmployeesDTO extends IEmpDTO {
    public Long getCivilId();

    public Timestamp getTrxDatetime();

    public Long getMinCode();

    public String getWrkCode();

    public String getMinistryFileNo();

    public String getCscFileNo();

    public Timestamp getHireDate();

    public Timestamp getStartWorkingDate();

    public Timestamp getEndJobDate();

    public Long getHirstatusCode();

    public Long getHirtypeCode();

    public Timestamp getDateOfNextRaise();

    public String getTechJobCode();

    public String getJobCode();

    public Long getBankCode();

    public Long getBnkbranchCode();

    public String getAccountNo();

    public Long getActiveFlag();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setCivilId(Long civilId);

    public void setTrxDatetime(Timestamp trxDatetime);

    public void setMinCode(Long minCode);

    public void setWrkCode(String wrkCode);

    public void setMinistryFileNo(String ministryFileNo);

    public void setCscFileNo(String cscFileNo);

    public void setHireDate(Timestamp hireDate);

    public void setStartWorkingDate(Timestamp startWorkingDate);

    public void setEndJobDate(Timestamp endJobDate);

    public void setHirstatusCode(Long hirstatusCode);

    public void setHirtypeCode(Long hirtypeCode);

    public void setDateOfNextRaise(Timestamp dateOfNextRaise);

    public void setTechJobCode(String techJobCode);

    public void setJobCode(String jobCode);

    public void setBankCode(Long bankCode);

    public void setBnkbranchCode(Long bnkbranchCode);

    public void setAccountNo(String accountNo);

    public void setActiveFlag(Long activeFlag);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setHireStatusDTO(IHireStatusDTO hireStatusDTO);

    public IHireStatusDTO getHireStatusDTO();

    public void setHireTypesDTO(IHireTypesDTO hireTypesDTO);

    public IHireTypesDTO getHireTypesDTO();

    public void setCitizensResidentsDTO(IKwtCitizensResidentsDTO citizensResidentsDTO);

    public IKwtCitizensResidentsDTO getCitizensResidentsDTO();

    public void setJobsDTO(IJobsDTO jobsDTO);

    public IJobsDTO getJobsDTO();

    public void setBankBranchesDTO(BankBranchesDTO bankBranchesDTO);

    public BankBranchesDTO getBankBranchesDTO();

    public void setWorkCentersDTO(IWorkCentersDTO workCentersDTO);

    public IWorkCentersDTO getWorkCentersDTO();

    public void setTechJobsDTO(IJobsDTO techJobsDTO);

    public IJobsDTO getTechJobsDTO();

    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO);

    public IBgtTypesDTO getBgtTypesDTO();

    public void setBgtProgramsDTO(IBgtProgramsDTO bgtProgramsDTO);

    public IBgtProgramsDTO getBgtProgramsDTO();

}
