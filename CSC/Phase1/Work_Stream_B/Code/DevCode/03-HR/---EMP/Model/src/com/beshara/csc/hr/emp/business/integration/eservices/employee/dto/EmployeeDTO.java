package com.beshara.csc.hr.emp.business.integration.eservices.employee.dto;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;


public class EmployeeDTO implements Serializable {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private Long civilId;
    private Long realCivilId;
    // Employee Name info
    private String firstName;
    private String secondName;
    private String thirdName;
    private String lastName;
    private String familyName;
    private String employeeFullName;
    private String englishName;
    // Birth Date info
    private Date birthDate;
    // Employee Contact info
    private String phonesNo;
    private String mobileNo;
    private String eMail;
    // Employee Gender info
    private Long gentypeCode;
    private String gentypeName;

    // Employee Nationality info
    private Long nationalityCode;
    private String nationalityName;

    // Employee ministry and work center info
    private Long minCode;
    private String minName;
    private String wrkCode;
    private Long hireStatusCode;
    private String hireStatusName;
    private String wrkName;
    private String jobCode;
    private String jobName;
    private Long catCode;
    private String catName;
    private String cityName;
    private String cityCode;
    private String statusName;
    private String statusCode;
    private String pieceName;
    private String pieceCode;
    private Long streetCode;
    private String groupCode;
    private Long flatNo;
    private Long floorNo;
    private String buildingNo;
    private String addressInDetails;
    private String healthStatus;
    private Date hireDate;
    private String qualName;
    private Date qualDate;
    private String ministryFileNo;
    private String cscFileNo;
    private String salCader;
    private String salGroup;
    private String salDegree;
    private Long salRaise;
    private Date firstHireDate;
    private String religonName;
    private String maritalStatusName;
    private Date activateJobDate;
    private Long salCaderCode;
    private Long salGroupCode;
    private Long salDegreeCode;
    private Long salRaiseCode;
    private String streeName;
    private Date startWorkingDate;
    private String accountNo;
    private String bankBranche;
    private String bankName;
    private Long bankId;
    private BigDecimal deductions;
    private BigDecimal merits;
    private BigDecimal actualSalary;
    private Date salDegreeDate;
    private Long salYears;
    private Long salMonthes;
    private Long salDays;
    private Long roleID;
    private Long userCode;
    private String userName;
    private Date dateOfNextRaise;
    private Long sendSMSFlag;
     private Long empSignId;      
    
    public EmployeeDTO() {
        super();
    }


    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setGentypeCode(Long gentypeCode) {
        this.gentypeCode = gentypeCode;
    }

    public Long getGentypeCode() {
        return gentypeCode;
    }

    public void setGentypeName(String gentypeName) {
        this.gentypeName = gentypeName;
    }

    public String getGentypeName() {
        return gentypeName;
    }

    public void setNationalityCode(Long nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public Long getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setMinName(String minName) {
        this.minName = minName;
    }

    public String getMinName() {
        return minName;
    }

    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    public String getWrkCode() {
        return wrkCode;
    }

    public void setWrkName(String wrkName) {
        this.wrkName = wrkName;
    }

    public String getWrkName() {
        return wrkName;
    }

    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    public String getPhonesNo() {
        return phonesNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getEMail() {
        return eMail;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public Long getCatCode() {
        return catCode;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    public Long getFloorNo() {
        return floorNo;
    }

    public void setFlatNo(Long flatNo) {
        this.flatNo = flatNo;
    }

    public Long getFlatNo() {
        return flatNo;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setPieceCode(String pieceCode) {
        this.pieceCode = pieceCode;
    }

    public String getPieceCode() {
        return pieceCode;
    }

    public void setAddressInDetails(String addressInDetails) {
        this.addressInDetails = addressInDetails;
    }

    public String getAddressInDetails() {
        return addressInDetails;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setQualName(String qualName) {
        this.qualName = qualName;
    }

    public String getQualName() {
        return qualName;
    }

    public void setQualDate(Date qualDate) {
        this.qualDate = qualDate;
    }

    public Date getQualDate() {
        return qualDate;
    }

    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    public void setCscFileNo(String cscFileNo) {
        this.cscFileNo = cscFileNo;
    }

    public String getCscFileNo() {
        return cscFileNo;
    }

    public void setSalCader(String salCader) {
        this.salCader = salCader;
    }

    public String getSalCader() {
        return salCader;
    }

    public void setSalGroup(String salGroup) {
        this.salGroup = salGroup;
    }

    public String getSalGroup() {
        return salGroup;
    }

    public void setSalDegree(String salDegree) {
        this.salDegree = salDegree;
    }

    public String getSalDegree() {
        return salDegree;
    }

    public void setSalRaise(Long salRaise) {
        this.salRaise = salRaise;
    }

    public Long getSalRaise() {
        return salRaise;
    }

    public void setFirstHireDate(Date firstHireDate) {
        this.firstHireDate = firstHireDate;
    }

    public Date getFirstHireDate() {
        return firstHireDate;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setBankBranche(String bankBranche) {
        this.bankBranche = bankBranche;
    }

    public String getBankBranche() {
        return bankBranche;
    }

    public void setReligonName(String religonName) {
        this.religonName = religonName;
    }

    public String getReligonName() {
        return religonName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setActivateJobDate(Date activateJobDate) {
        this.activateJobDate = activateJobDate;
    }

    public Date getActivateJobDate() {
        return activateJobDate;
    }

    public void setSalCaderCode(Long salCaderCode) {
        this.salCaderCode = salCaderCode;
    }

    public Long getSalCaderCode() {
        return salCaderCode;
    }

    public void setSalGroupCode(Long salGroupCode) {
        this.salGroupCode = salGroupCode;
    }

    public Long getSalGroupCode() {
        return salGroupCode;
    }

    public void setSalDegreeCode(Long salDegreeCode) {
        this.salDegreeCode = salDegreeCode;
    }

    public Long getSalDegreeCode() {
        return salDegreeCode;
    }

    public void setSalRaiseCode(Long salRaiseCode) {
        this.salRaiseCode = salRaiseCode;
    }

    public Long getSalRaiseCode() {
        return salRaiseCode;
    }

    public void setHireStatusCode(Long hireStatusCode) {
        this.hireStatusCode = hireStatusCode;
    }

    public Long getHireStatusCode() {
        return hireStatusCode;
    }

    public void setHireStatusName(String hireStatusName) {
        this.hireStatusName = hireStatusName;
    }

    public String getHireStatusName() {
        return hireStatusName;
    }

    public void setStreeName(String streeName) {
        this.streeName = streeName;
    }

    public String getStreeName() {
        return streeName;
    }

    public void setStartWorkingDate(Date startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public Date getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setMerits(BigDecimal merits) {
        this.merits = merits;
    }

    public BigDecimal getMerits() {
        return merits;
    }

    public void setActualSalary(BigDecimal actualSalary) {
        this.actualSalary = actualSalary;
    }

    public BigDecimal getActualSalary() {
        return actualSalary;
    }

    public void setSalDegreeDate(Date salDegreeDate) {
        this.salDegreeDate = salDegreeDate;
    }

    public Date getSalDegreeDate() {
        return salDegreeDate;
    }

    public void setSalYears(Long salYears) {
        this.salYears = salYears;
    }

    public Long getSalYears() {
        return salYears;
    }

    public void setSalMonthes(Long salMonthes) {
        this.salMonthes = salMonthes;
    }

    public Long getSalMonthes() {
        return salMonthes;
    }

    public void setSalDays(Long salDays) {
        this.salDays = salDays;
    }

    public Long getSalDays() {
        return salDays;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {
        return dateOfNextRaise;
    }


    public void setSendSMSFlag(Long sendSMSFlag) {
        this.sendSMSFlag = sendSMSFlag;
    }

    public Long getSendSMSFlag() {
        return sendSMSFlag;
    }
    public void setEmpSignId(Long empSignId){
        this.empSignId=empSignId;
    }

    public Long getEmpSignId(){
        return empSignId;
    }
}
