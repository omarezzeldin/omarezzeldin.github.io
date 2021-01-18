package com.beshara.csc.hr.emp.business.dto;

import java.math.BigDecimal;

/*
 * @author msayed at 14-5-2020
 * @notes for webservice for mobile
 */
public interface IEmployeeDTOService {

    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();

    public void setFirstName(String firstName);

    public String getFirstName();

    public void setSecondName(String secondName);

    public String getSecondName();

    public void setThirdName(String thirdName);

    public String getThirdName();

    public void setLastName(String lastName);

    public String getLastName();

    public void setFamilyName(String familyName);

    public String getFamilyName();

    public void setEmployeeFullName(String employeeFullName);

    public String getEmployeeFullName();

    public void setEnglishName(String englishName);

    public String getEnglishName();

    public void setBirthDate(java.util.Date birthDate);

    public java.util.Date getBirthDate();

    public void setPhonesNo(String phonesNo);

    public String getPhonesNo();

    public void setMobileNo(String mobileNo);

    public String getMobileNo();

    public void setEMail(String eMail);

    public String getEMail();

    public void setGentypeCode(Long gentypeCode);

    public Long getGentypeCode();

    public void setGentypeName(String gentypeName);

    public String getGentypeName();

    public void setNationalityCode(Long nationalityCode);

    public Long getNationalityCode();

    public void setNationalityName(String nationalityName);

    public String getNationalityName();

    public void setMinCode(Long minCode);

    public Long getMinCode();

    public void setMinName(String minName);

    public String getMinName();

    public void setWrkCode(String wrkCode);

    public String getWrkCode();

    public void setHireStatusCode(Long hireStatusCode);

    public Long getHireStatusCode();

    public void setHireStatusName(String hireStatusName);

    public String getHireStatusName();

    public void setWrkName(String wrkName);

    public String getWrkName();

    public void setJobCode(String jobCode);

    public String getJobCode();

    public void setJobName(String jobName);

    public String getJobName();

    public void setCatCode(Long catCode);

    public Long getCatCode();

    public void setCatName(String catName);

    public String getCatName();

    public void setCityName(String cityName);

    public String getCityName();

    public void setCityCode(String cityCode);

    public String getCityCode();

    public void setStatusName(String statusName);

    public String getStatusName();

    public void setStatusCode(String statusCode);

    public String getStatusCode();

    public void setPieceName(String pieceName);

    public String getPieceName();

    public void setPieceCode(String pieceCode);

    public String getPieceCode();

    public void setStreetCode(Long streetCode);

    public Long getStreetCode();

    public void setGroupCode(String groupCode);

    public String getGroupCode();

    public void setFlatNo(Long flatNo);

    public Long getFlatNo();

    public void setFloorNo(Long floorNo);

    public Long getFloorNo();

    public void setBuildingNo(String buildingNo);

    public String getBuildingNo();

    public void setAddressInDetails(String addressInDetails);

    public String getAddressInDetails();

    public void setHealthStatus(String healthStatus);

    public String getHealthStatus();

    public void setHireDate(java.util.Date hireDate);

    public java.util.Date getHireDate();

    public void setQualName(String qualName);

    public String getQualName();

    public void setQualDate(java.util.Date qualDate);

    public java.util.Date getQualDate();

    public void setMinistryFileNo(String ministryFileNo);

    public String getMinistryFileNo();

    public void setCscFileNo(String cscFileNo);

    public String getCscFileNo();

    public void setSalCader(String salCader);

    public String getSalCader();

    public void setSalGroup(String salGroup);

    public String getSalGroup();

    public void setSalDegree(String salDegree);

    public String getSalDegree();

    public void setSalRaise(Long salRaise);

    public Long getSalRaise();

    public void setFirstHireDate(java.util.Date firstHireDate);

    public java.util.Date getFirstHireDate();

    public void setReligonName(String religonName);

    public String getReligonName();

    public void setMaritalStatusName(String maritalStatusName);

    public String getMaritalStatusName();

    public void setActivateJobDate(java.util.Date activateJobDate);

    public java.util.Date getActivateJobDate();

    public void setSalCaderCode(Long salCaderCode);

    public Long getSalCaderCode();

    public void setSalGroupCode(Long salGroupCode);

    public Long getSalGroupCode();

    public void setSalDegreeCode(Long salDegreeCode);

    public Long getSalDegreeCode();

    public void setSalRaiseCode(Long salRaiseCode);

    public Long getSalRaiseCode();

    public void setStreeName(String streeName);

    public String getStreeName();

    public void setStartWorkingDate(java.util.Date startWorkingDate);

    public java.util.Date getStartWorkingDate();

    public void setAccountNo(String accountNo);

    public String getAccountNo();

    public void setBankBranche(String bankBranche);

    public String getBankBranche();

    public void setBankName(String bankName);

    public String getBankName();

    public void setBankId(Long bankId);

    public Long getBankId();

    public void setDeductions(BigDecimal deductions);

    public BigDecimal getDeductions();

    public void setMerits(BigDecimal merits);

    public BigDecimal getMerits();

    public void setActualSalary(BigDecimal actualSalary);

    public BigDecimal getActualSalary();

    public void setSalDegreeDate(java.util.Date salDegreeDate);

    public java.util.Date getSalDegreeDate();

    public void setSalYears(Long salYears);

    public Long getSalYears();

    public void setSalMonthes(Long salMonthes);

    public Long getSalMonthes();

    public void setSalDays(Long salDays);

    public Long getSalDays();

    public void setRoleID(Long roleID);

    public Long getRoleID();

    public void setUserCode(Long userCode);

    public Long getUserCode();

    public void setUserName(String userName);

    public String getUserName();

    public void setDateOfNextRaise(java.util.Date dateOfNextRaise);

    public java.util.Date getDateOfNextRaise();

    public void setSendSMSFlag(Long sendSMSFlag);

    public Long getSendSMSFlag();

    public void setEmpSignId(Long empSignId);

    public Long getEmpSignId();


}
