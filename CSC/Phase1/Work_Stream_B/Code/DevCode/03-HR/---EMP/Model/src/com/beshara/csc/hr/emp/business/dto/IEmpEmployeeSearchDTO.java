package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.paging.IPagingRequestDTO;

import java.sql.Date;

import java.util.List;


public interface IEmpEmployeeSearchDTO extends IEmpDTO {
    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setEmpName(String empName);

    public String getEmpName();

    public void setJobName(String jobName);

    public String getJobName();

    public void setEmpHireTypes(Long empHireTypes);

    public Long getEmpHireTypes();

    public void setWorkCenterCode(String workCenterCode);

    public String getWorkCenterCode();

    public void setStartWorkingDate(Date startWorkingDate);

    public Date getStartWorkingDate();

    public void setEmpHireStage(Long empHireStage);

    public Long getEmpHireStage();

    public void setNationalityType(Long nationalityType);

    public Long getNationalityType();

    public void setGenderType(Long genderType);

    public Long getGenderType();

    public void setHirestageNotEqualCanceldOrEmployment(boolean hirestageNotEqualCanceldOrEmployment);

    public boolean isHirestageNotEqualCanceldOrEmployment();

    public void setWorkCenterName(String workCenterName);

    public String getWorkCenterName();

    public void setMinistryCode(Long ministryCode);

    public Long getMinistryCode();

    public void setEmpHireStatus(Long empHireStatus);

    public Long getEmpHireStatus();

    public void setEmployment(boolean employment);

    public boolean isEmployment();

    public void setEmploymentStage(String employmentStage);

    public String getEmploymentStage();

    public void setHireDateFrom(Date hireDateFrom);

    public Date getHireDateFrom();

    public void setHireDateTO(Date hireDateTO);

    public Date getHireDateTO();

    public void setMinistryFileNo(String ministryFileNo);

    public String getMinistryFileNo();

    public void setKuwaiti(boolean kuwaiti);

    public boolean isKuwaiti();

    public void setWorkLevelCode(Long workLevelCode);

    public Long getWorkLevelCode();

    public void setBgtTypesCode(Long bgtTypesCode);

    public Long getBgtTypesCode();

    public void setBgtProgramsCode(String bgtProgramsCode);

    public String getBgtProgramsCode();

    public void setKaderCode(Long kaderCode);

    public Long getKaderCode();

    public void setGroupCode(Long groupCode);

    public Long getGroupCode();

    public void setElmguideCode(Long elmguideCode);

    public Long getElmguideCode();

    public void setMonthCode(Long monthCode);

    public Long getMonthCode();

    public void setYearCode(Long yearCode);

    public Long getYearCode();

    public void setHasCalculatedMonSalary(boolean hasCalculatedMonSalary);

    public boolean isHasCalculatedMonSalary();

    public void setEmpHireStatusList(List<Long> empHireStatusList);

    public List<Long> getEmpHireStatusList();

    void setActiveFlag(Long activeFlag);

    Long getActiveFlag();

    void setRecordDescCode(Long recordDescCode);

    Long getRecordDescCode();

    public void setEmpHireStagesList(List<Long> empHireStagesList);

    public List<Long> getEmpHireStagesList();

    public void setJobCode(Long jobCode);

    public Long getJobCode();

    public void setLoginMinistry(Long loginMinistry);

    public Long getLoginMinistry();

    public void setHireTypeFromDropList(Long hireTypeFromDropList);

    public Long getHireTypeFromDropList();

    public void setExecludedEmpHireStagesList(List<Long> execludedEmpHireStagesList);

    public List<Long> getExecludedEmpHireStagesList();

    public void setPagingRequestDTO(IPagingRequestDTO pagingRequestDTO);

    public IPagingRequestDTO getPagingRequestDTO();

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();
    
    public void setFilterType(int filterType) ;

    public int getFilterType() ;
    
    public void setPayStatusCodes(String payStatusCodes);

    public String getPayStatusCodes();
    
    public void setRelatedWorkCentersFlag(boolean relatedWorkCentersFlag);
    
    public boolean isRelatedWorkCentersFlag();
   
    public void setInAllMinistries(boolean inAllMinistries) ;

    public boolean isInAllMinistries();
    
    public void setCandidateCode(Long candidateCode);

    public Long getCandidateCode();
    
    public void setNosign(boolean nosign);

    public boolean isNosign();

    public void setNoSignReasonCode(Long noSignReasonCode);

    public Long getNoSignReasonCode();
    
    public void setEosFlag(boolean eosFlag);

    public boolean isEosFlag();
    
}
