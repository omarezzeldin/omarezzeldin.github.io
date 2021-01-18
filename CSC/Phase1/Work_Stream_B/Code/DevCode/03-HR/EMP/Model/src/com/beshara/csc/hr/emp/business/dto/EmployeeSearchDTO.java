package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;

import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;

import java.sql.Date;

import java.util.List;


public class EmployeeSearchDTO extends EmpDTO implements IEmployeeSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    /*set master code*/
    private IEntityKey masterCode;
    /*search by main data*/
    private Long civilId;
    private Long realCivilId;
    private Date birthDate;
    private Date endResidentDate;
    private String englishName;
    private String familyName;
    private String firstName;
    private Long genderTypeCode;
    private String lastName;
    private Long maritalStatusCode;
    private String mobileNo;
    private Long nationality;
    private String passportNo;
    private String phonesNo;
    private Long religionCode;
    private Long residentTypeCode;
    private String secondName;
    private String thirdName;
    private Long specialCaseTypeCode;
    private String mapCode;
    private Long capstatusCode;
    /*search by job data*/
    private String accountNo;
    private Long bankCode;
    private String bankbranchCode;
    private String cscFileNo;
    private Date hireDate;
    private String jobCode;
    private String ministryFileNo;
    private Date startWorkingDate;
    private Date endWorkingDate;
    private String techJobCode;
    private List empHireStages;
    private Long empHireStatus;
    private Long empHireTypes;
    private String workCenterCode;
    /////////////////// 
    private Long bgtTypesCode;
    private String bgtProgramsCode;
    ////Added this attribute to get all employment 
    private boolean employment;
    private Long ministryCode;
    private Long elmguideCode;
    private Boolean kuwaiti;
    //////////////////////////////// 
    //ADDED BY TAHA ABDUL MEJID @ 5/10/09 
    private Long caderCode;
    private Long groupCode;
    private Long degreeCode;
    // added by Suzan
    // 0 -> all
    // 1 -> has experience
    // 2 -> hasn't experience
    private int experienceCheck;
    
    private Long activeFlag;
    private List<Long> hireStatusList;

    private IPagingRequestDTO pagingRequestDTO;
    private int mangeId;
    private String fullEmpName ;
    private String jobName ;
    private String techJobName ;
    private Date fromDate;
    /*
     * added by ahmed farouk
     * dev-954
     * */
    private List<IWorkCentersDTO> workCentersList;
    private Boolean getAllByWorkCode;
    public EmployeeSearchDTO() {
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setEndResidentDate(Date endResidentDate) {
        this.endResidentDate = endResidentDate;
    }

    public Date getEndResidentDate() {
        return endResidentDate;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishName() {
        if (englishName != null && englishName.length() == 0) {
            englishName = null;
        }
        return englishName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        if (familyName != null && familyName.length() == 0) {
            familyName = null;
        }
        return familyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        if (firstName != null && firstName.length() == 0) {
            firstName = null;
        }
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        if (lastName != null && lastName.length() == 0) {
            lastName = null;
        }
        return lastName;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        if (mobileNo != null && mobileNo.length() == 0) {
            mobileNo = null;
        }
        return mobileNo;
    }

    public void setNationality(Long nationality) {
        this.nationality = nationality;
    }

    public Long getNationality() {
        return nationality;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getPassportNo() {
        if (passportNo != null && passportNo.length() == 0) {
            passportNo = null;
        }
        return passportNo;
    }

    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    public String getPhonesNo() {
        if (phonesNo != null && phonesNo.length() == 0) {
            phonesNo = null;
        }
        return phonesNo;
    }

    public void setReligionCode(Long religionCode) {
        this.religionCode = religionCode;
    }

    public Long getReligionCode() {
        return religionCode;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        if (secondName != null && secondName.length() == 0) {
            secondName = null;
        }
        return secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdName() {
        if (thirdName != null && thirdName.length() == 0) {
            thirdName = null;
        }
        return thirdName;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getMapCode() {
        if (mapCode != null && mapCode.equals("")) {
            this.setMapCode(null);
        }
        return mapCode;
    }

    public void setCapstatusCode(Long capstatusCode) {
        this.capstatusCode = capstatusCode;
    }

    public Long getCapstatusCode() {
        return capstatusCode;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        if (accountNo != null && accountNo.length() == 0) {
            accountNo = null;
        }
        return accountNo;
    }

    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }

    public Long getBankCode() {
        return bankCode;
    }

    public void setCscFileNo(String cscFileNo) {
        this.cscFileNo = cscFileNo;
    }

    public String getCscFileNo() {
        if (cscFileNo != null && cscFileNo.length() == 0) {
            cscFileNo = null;
        }
        return cscFileNo;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        if (jobCode != null && jobCode.length() == 0) {
            jobCode = null;
        }
        return jobCode;
    }

    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    public String getMinistryFileNo() {
        if (ministryFileNo != null && ministryFileNo.length() == 0) {
            ministryFileNo = null;
        }
        return ministryFileNo;
    }

    public void setStartWorkingDate(Date startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public Date getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setTechJobCode(String techJobCode) {
        this.techJobCode = techJobCode;
    }

    public String getTechJobCode() {
        if (techJobCode != null && techJobCode.length() == 0) {
            techJobCode = null;
        }
        return techJobCode;
    }

    public void setEmpHireStages(List empHireStages) {
        this.empHireStages = empHireStages;
    }


    public List getEmpHireStages() {
        return empHireStages;
    }

    public void setEmpHireStatus(Long empHireStatus) {
        this.empHireStatus = empHireStatus;
    }

    public Long getEmpHireStatus() {
        return empHireStatus;
    }

    public void setEmpHireTypes(Long empHireTypes) {
        this.empHireTypes = empHireTypes;
    }

    public Long getEmpHireTypes() {
        return empHireTypes;
    }

    public void setMasterCode(IEntityKey masterCode) {
        this.masterCode = masterCode;
    }

    public IEntityKey getMasterCode() {
        return masterCode;
    }

    public void setGenderTypeCode(Long genderTypeCode) {
        this.genderTypeCode = genderTypeCode;
    }

    public Long getGenderTypeCode() {
        return genderTypeCode;
    }

    public void setMaritalStatusCode(Long maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    public Long getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setResidentTypeCode(Long residentTypeCode) {
        this.residentTypeCode = residentTypeCode;
    }

    public Long getResidentTypeCode() {
        return residentTypeCode;
    }

    public void setSpecialCaseTypeCode(Long specialCaseTypeCode) {
        this.specialCaseTypeCode = specialCaseTypeCode;
    }

    public Long getSpecialCaseTypeCode() {
        return specialCaseTypeCode;
    }

    public void setBankbranchCode(String bankbranchCode) {
        this.bankbranchCode = bankbranchCode;
    }

    public String getBankbranchCode() {
        if (bankbranchCode != null && bankbranchCode.length() == 0) {
            bankbranchCode = null;
        }
        return bankbranchCode;
    }

    public void setWorkCenterCode(String workCenterCode) {
        this.workCenterCode = workCenterCode;
    }

    public String getWorkCenterCode() {
        if (workCenterCode != null && workCenterCode.length() == 0) {
            workCenterCode = null;
        }
        return workCenterCode;
    }

    public void setEndWorkingDate(Date endWorkingDate) {
        this.endWorkingDate = endWorkingDate;
    }

    public Date getEndWorkingDate() {
        return endWorkingDate;
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
        if (bgtProgramsCode != null && bgtProgramsCode.length() == 0) {
            bgtProgramsCode = null;
        }
        return bgtProgramsCode;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    public Long getElmguideCode() {
        return elmguideCode;
    }

    public void setEmployment(boolean employment) {
        this.employment = employment;
    }

    public boolean isEmployment() {
        return employment;
    }

    public void setKuwaiti(Boolean kuwaiti) {
        this.kuwaiti = kuwaiti;
    }

    public Boolean getKuwaiti() {
        return kuwaiti;
    }

    public void setCaderCode(Long caderCode) {
        this.caderCode = caderCode;
    }

    public Long getCaderCode() {
        return caderCode;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Long getGroupCode() {
        return groupCode;
    }

    public void setDegreeCode(Long degreeCode) {
        this.degreeCode = degreeCode;
    }

    public Long getDegreeCode() {
        return degreeCode;
    }

    public void setPagingRequestDTO(IPagingRequestDTO pagingRequestDTO) {
        this.pagingRequestDTO = pagingRequestDTO;
    }

    public IPagingRequestDTO getPagingRequestDTO() {
        return pagingRequestDTO;
    }


    public void setExperienceCheck(int experienceCheck) {
        this.experienceCheck = experienceCheck;
    }

    public int getExperienceCheck() {
        return experienceCheck;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setHireStatusList(List<Long> hireStatusList) {
        this.hireStatusList = hireStatusList;
    }

    public List<Long> getHireStatusList() {
        return hireStatusList;
    }

    public void setMangeId(int mangeId) {
        this.mangeId = mangeId;
    }

    public int getMangeId() {
        return mangeId;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setFullEmpName(String fullEmpName) {
        this.fullEmpName = fullEmpName;
}

    public String getFullEmpName() {
        return fullEmpName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setTechJobName(String techJobName) {
        this.techJobName = techJobName;
    }

    public String getTechJobName() {
        return techJobName;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

   

    public void setGetAllByWorkCode(Boolean getAllByWorkCode) {
        this.getAllByWorkCode = getAllByWorkCode;
    }

    public Boolean getGetAllByWorkCode() {
        return getAllByWorkCode;
    }

    public void setWorkCentersList(List<IWorkCentersDTO> workCentersList) {
        this.workCentersList = workCentersList;
    }

    public List<IWorkCentersDTO> getWorkCentersList() {
        return workCentersList;
    }
}
