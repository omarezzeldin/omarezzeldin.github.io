package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.entity.IEntityKey;

import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;

import java.sql.Date;

import java.util.List;


public interface IEmployeeSearchDTO extends IEmpDTO {

    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();

    public void setBirthDate(Date birthDate);

    public Date getBirthDate();

    public void setEndResidentDate(Date endResidentDate);

    public Date getEndResidentDate();

    public void setEnglishName(String englishName);

    public String getEnglishName();

    public void setFamilyName(String familyName);

    public String getFamilyName();

    public void setFirstName(String firstName);

    public String getFirstName();

    public void setLastName(String lastName);

    public String getLastName();

    public void setMobileNo(String mobileNo);

    public String getMobileNo();

    public void setNationality(Long nationality);

    public Long getNationality();

    public void setPassportNo(String passportNo);

    public String getPassportNo();

    public void setPhonesNo(String phonesNo);

    public String getPhonesNo();

    public void setReligionCode(Long religionCode);

    public Long getReligionCode();

    public void setSecondName(String secondName);

    public String getSecondName();

    public void setThirdName(String thirdName);

    public String getThirdName();

    public void setMapCode(String mapCode);

    public String getMapCode();

    public void setCapstatusCode(Long capstatusCode);

    public Long getCapstatusCode();

    public void setAccountNo(String accountNo);

    public String getAccountNo();

    public void setBankCode(Long bankCode);

    public Long getBankCode();

    public void setCscFileNo(String cscFileNo);

    public String getCscFileNo();

    public void setHireDate(Date hireDate);

    public Date getHireDate();

    public void setJobCode(String jobCode);

    public String getJobCode();

    public void setMinistryFileNo(String ministryFileNo);

    public String getMinistryFileNo();

    public void setStartWorkingDate(Date startWorkingDate);

    public Date getStartWorkingDate();

    public void setTechJobCode(String techJobCode);

    public String getTechJobCode();

    public void setEmpHireStages(List empHireStages);

    public List getEmpHireStages();

    public void setEmpHireStatus(Long empHireStatus);

    public Long getEmpHireStatus();

    public void setEmpHireTypes(Long empHireTypes);

    public Long getEmpHireTypes();

    public void setMasterCode(IEntityKey masterCode);

    public IEntityKey getMasterCode();

    public void setGenderTypeCode(Long genderTypeCode);

    public Long getGenderTypeCode();

    public void setMaritalStatusCode(Long maritalStatusCode);

    public Long getMaritalStatusCode();

    public void setResidentTypeCode(Long residentTypeCode);

    public Long getResidentTypeCode();

    public void setSpecialCaseTypeCode(Long specialCaseTypeCode);

    public Long getSpecialCaseTypeCode();

    public void setBankbranchCode(String bankbranchCode);

    public String getBankbranchCode();

    public void setWorkCenterCode(String workCenterCode);

    public String getWorkCenterCode();

    public void setEndWorkingDate(Date endWorkingDate);

    public Date getEndWorkingDate();

    public void setBgtTypesCode(Long bgtTypesCode);

    public Long getBgtTypesCode();

    public void setBgtProgramsCode(String bgtProgramsCode);

    public String getBgtProgramsCode();

    public void setMinistryCode(Long ministryCode);

    public Long getMinistryCode();

    public void setElmguideCode(Long elmguideCode);

    public Long getElmguideCode();

    public void setEmployment(boolean employment);

    public boolean isEmployment();

    public void setKuwaiti(Boolean kuwaiti);

    public Boolean getKuwaiti();

    public void setCaderCode(Long caderCode);

    public Long getCaderCode();

    public void setGroupCode(Long groupCode);

    public Long getGroupCode();

    public void setDegreeCode(Long degreeCode);

    public Long getDegreeCode();

    void setPagingRequestDTO(com.beshara.base.paging.IPagingRequestDTO pagingRequestDTO);

    com.beshara.base.paging.IPagingRequestDTO getPagingRequestDTO();

    public void setExperienceCheck(int experienceCheck);

    public int getExperienceCheck();

    public void setActiveFlag(Long activeFlag);

    public Long getActiveFlag();

    public void setHireStatusList(List<Long> hireStatusList);

    public List<Long> getHireStatusList();

    public void setMangeId(int mangeId);

    public int getMangeId();

    public void setFullEmpName(String fullEmpName);

    public String getFullEmpName();

    public void setJobName(String jobName);

    public String getJobName();
    
    public void setTechJobName(String techJobName);

    public String getTechJobName();
    
    public void setFromDate(Date fromDate);

    public Date getFromDate() ;
    
    public void setWorkCentersList(List<IWorkCentersDTO> workCentersList) ;

    public List<IWorkCentersDTO> getWorkCentersList() ;

    public void setGetAllByWorkCode(Boolean getAllByWorkCode) ;
    
    public Boolean getGetAllByWorkCode() ;
}
