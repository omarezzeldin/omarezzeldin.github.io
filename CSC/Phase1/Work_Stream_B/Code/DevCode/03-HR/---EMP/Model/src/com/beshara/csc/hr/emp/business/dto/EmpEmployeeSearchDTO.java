package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.paging.IPagingRequestDTO;

import java.sql.Date;

import java.util.List;


public class EmpEmployeeSearchDTO extends EmpDTO implements IEmpEmployeeSearchDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long realCivilId;
    private String empName;
    private String jobName;
    private Long empHireTypes;
    private Long empHireStage;
    private Long empHireStatus;
    private Long nationalityType;
    private Long genderType;
    private String workCenterCode;
    private String workCenterName;
    private Long jobCode;
    private Date startWorkingDate;
    private boolean hirestageNotEqualCanceldOrEmployment = false;
    private boolean employment;
    private String employmentStage;
    private Long ministryCode;
    //________________________CR HR 672_______________________________________//
    private Date hireDateFrom;
    private Date hireDateTO;
    private String ministryFileNo;
    private boolean kuwaiti;
    private Long workLevelCode;
    private Long bgtTypesCode;
    private String bgtProgramsCode;
    private Long kaderCode;
    private Long groupCode;
    private Long elmguideCode;
    private Long monthCode;
    private Long yearCode;
    private Long activeFlag;
    private boolean hasCalculatedMonSalary;
    private String payStatusCodes;
        
    private List<Long> empHireStatusList;
    private List<Long> empHireStagesList;

    private Long recordDescCode;

    private Long loginMinistry;

    private Long hireTypeFromDropList;

    private List<Long> execludedEmpHireStagesList;
    private IPagingRequestDTO pagingRequestDTO;
    private int filterType = 0;
    private boolean  relatedWorkCentersFlag;
    private boolean  inAllMinistries;
    private Long candidateCode;
    
    private boolean nosign;
    private Long noSignReasonCode;
    private boolean eosFlag;
    public EmpEmployeeSearchDTO() {
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setEmpHireTypes(Long empHireTypes) {
        this.empHireTypes = empHireTypes;
    }

    public Long getEmpHireTypes() {
        return empHireTypes;
    }

    public void setWorkCenterCode(String workCenterCode) {
        this.workCenterCode = workCenterCode;
    }

    public String getWorkCenterCode() {
        if (workCenterCode != null && workCenterCode.trim().length() == 0) {
            workCenterCode = null;
        }
        return workCenterCode;
    }

    public void setStartWorkingDate(Date startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public Date getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setEmpHireStage(Long empHireStage) {
        this.empHireStage = empHireStage;
    }

    public Long getEmpHireStage() {
        return empHireStage;
    }

    public void setNationalityType(Long nationalityType) {
        this.nationalityType = nationalityType;
    }

    public Long getNationalityType() {
        return nationalityType;
    }

    public void setGenderType(Long genderType) {
        this.genderType = genderType;
    }

    public Long getGenderType() {
        return genderType;
    }

    public void setHirestageNotEqualCanceldOrEmployment(boolean hirestageNotEqualCanceldOrEmployment) {
        this.hirestageNotEqualCanceldOrEmployment = hirestageNotEqualCanceldOrEmployment;
    }

    public boolean isHirestageNotEqualCanceldOrEmployment() {
        return hirestageNotEqualCanceldOrEmployment;
    }

    public void setWorkCenterName(String workCenterName) {
        this.workCenterName = workCenterName;
    }

    public String getWorkCenterName() {
        return workCenterName;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setEmpHireStatus(Long empHireStatus) {
        this.empHireStatus = empHireStatus;
    }

    public Long getEmpHireStatus() {
        return empHireStatus;
    }

    public void setEmployment(boolean employment) {
        this.employment = employment;
    }

    public boolean isEmployment() {
        return employment;
    }

    public void setEmploymentStage(String employmentStage) {
        this.employmentStage = employmentStage;
    }

    public String getEmploymentStage() {
        if (employmentStage != null && employmentStage.length() > 0)
            return employmentStage;
        return null;
    }

    public void setHireDateFrom(Date hireDateFrom) {
        this.hireDateFrom = hireDateFrom;
    }

    public Date getHireDateFrom() {
        return hireDateFrom;
    }

    public void setHireDateTO(Date hireDateTO) {
        this.hireDateTO = hireDateTO;
    }

    public Date getHireDateTO() {
        return hireDateTO;
    }

    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    public void setKuwaiti(boolean kuwaiti) {
        this.kuwaiti = kuwaiti;
    }

    public boolean isKuwaiti() {
        return kuwaiti;
    }

    public void setWorkLevelCode(Long workLevelCode) {
        this.workLevelCode = workLevelCode;
    }

    public Long getWorkLevelCode() {
        return workLevelCode;
    }

    public void setBgtTypesCode(Long bgtTypesCode) {
        this.bgtTypesCode = bgtTypesCode;
    }

    public Long getBgtTypesCode() {
        return bgtTypesCode;
    }

    public void setBgtProgramsCode(String bgtProgramsCode) {
        this.bgtProgramsCode = bgtProgramsCode;
    }

    public String getBgtProgramsCode() {
        if (bgtProgramsCode != null && bgtProgramsCode.trim().length() == 0) {
            bgtProgramsCode = null;
        }
        return bgtProgramsCode;
    }

    public void setKaderCode(Long kaderCode) {
        this.kaderCode = kaderCode;
    }

    public Long getKaderCode() {
        return kaderCode;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        return groupCode;
    }

    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    public Long getElmguideCode() {
        return elmguideCode;
    }

    public void setMonthCode(Long monthCode) {
        this.monthCode = monthCode;
    }

    public Long getMonthCode() {
        return monthCode;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public Long getYearCode() {
        return yearCode;
    }

    public void setHasCalculatedMonSalary(boolean hasCalculatedMonSalary) {
        this.hasCalculatedMonSalary = hasCalculatedMonSalary;
    }

    public boolean isHasCalculatedMonSalary() {
        return hasCalculatedMonSalary;
    }

    public void setEmpHireStatusList(List<Long> empHireStatusList) {
        this.empHireStatusList = empHireStatusList;
    }

    public List<Long> getEmpHireStatusList() {
        return empHireStatusList;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setRecordDescCode(Long recordDescCode) {
        this.recordDescCode = recordDescCode;
    }

    public Long getRecordDescCode() {
        return recordDescCode;
    }

    public void setEmpHireStagesList(List<Long> empHireStagesList) {
        this.empHireStagesList = empHireStagesList;
    }

    public List<Long> getEmpHireStagesList() {
        return empHireStagesList;
    }

    public void setJobCode(Long jobCode) {
        this.jobCode = jobCode;
    }

    public Long getJobCode() {
        return jobCode;
    }

    public void setLoginMinistry(Long loginMinistry) {
        this.loginMinistry = loginMinistry;
    }

    public Long getLoginMinistry() {
        return loginMinistry;
    }

    public void setHireTypeFromDropList(Long hireTypeFromDropList) {
        this.hireTypeFromDropList = hireTypeFromDropList;
    }

    public Long getHireTypeFromDropList() {
        return hireTypeFromDropList;
    }

    public void setExecludedEmpHireStagesList(List<Long> execludedEmpHireStagesList) {
        this.execludedEmpHireStagesList = execludedEmpHireStagesList;
    }

    public List<Long> getExecludedEmpHireStagesList() {
        return execludedEmpHireStagesList;
    }

    public void setPagingRequestDTO(IPagingRequestDTO pagingRequestDTO) {
        this.pagingRequestDTO = pagingRequestDTO;
    }

    public IPagingRequestDTO getPagingRequestDTO() {
        return pagingRequestDTO;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setPayStatusCodes(String payStatusCodes) {
        this.payStatusCodes = payStatusCodes;
    }

    public String getPayStatusCodes() {
        return payStatusCodes;
    }

    public void setRelatedWorkCentersFlag(boolean relatedWorkCentersFlag) {
        this.relatedWorkCentersFlag = relatedWorkCentersFlag;
    }

    public boolean isRelatedWorkCentersFlag() {
        return relatedWorkCentersFlag;
    }

    public void setInAllMinistries(boolean inAllMinistries) {
        this.inAllMinistries = inAllMinistries;
    }

    public boolean isInAllMinistries() {
        return inAllMinistries;
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setNosign(boolean nosign) {
        this.nosign = nosign;
    }

    public boolean isNosign() {
        return nosign;
    }

    public void setNoSignReasonCode(Long noSignReasonCode) {
        this.noSignReasonCode = noSignReasonCode;
    }

    public Long getNoSignReasonCode() {
        return noSignReasonCode;
    }

    public void setEosFlag(boolean eosFlag) {
        this.eosFlag = eosFlag;
    }

    public boolean isEosFlag() {
        return eosFlag;
    }
}
