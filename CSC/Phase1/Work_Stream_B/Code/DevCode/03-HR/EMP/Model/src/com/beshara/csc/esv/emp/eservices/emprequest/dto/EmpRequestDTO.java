package com.beshara.csc.esv.emp.eservices.emprequest.dto;

import java.io.Serializable;

import java.sql.Timestamp;


public class EmpRequestDTO implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private String addressInDetails;
    private Long buildingCode;
    private Long civilID;
    private String email;
    private String empName;
    private Long flatNo;
    private Long floorNo;
    private String govCode;
    private String mapCode;
    private String mobileNo;
    private String phonesNo;
    private Long realCivilID;
    private String refuseReason;
    private String regCode;
    private Timestamp requestDate;
    private Long requestNo;
    private Long requestType;
    private String returnReason;
    private Long statusCode;
    private Long streetCode;
    private Long sysIterID;
    private Long sysIterTotalsChild;
    private Long sysIterTrID;
    
    public EmpRequestDTO() {
        super();
    }

    public EmpRequestDTO(String addressInDetails, Long buildingCode, Long civilID, String email, String empName,
                         Long flatNo, Long floorNo, String govCode, String mapCode, String mobileNo, String phonesNo,
                         Long realCivilID, String refuseReason, String regCode, Timestamp requestDate,
                         Long requestNo, Long requestType, String returnReason, Long statusCode, Long streetCode,
                         Long sysIterID, Long sysIterTotalsChild, Long sysIterTrID) {
        super();
        this.addressInDetails = addressInDetails;
        this.buildingCode = buildingCode;
        this.civilID = civilID;
        this.email = email;
        this.empName = empName;
        this.flatNo = flatNo;
        this.floorNo = floorNo;
        this.govCode = govCode;
        this.mapCode = mapCode;
        this.mobileNo = mobileNo;
        this.phonesNo = phonesNo;
        this.realCivilID = realCivilID;
        this.refuseReason = refuseReason;
        this.regCode = regCode;
        this.requestDate = requestDate;
        this.requestNo = requestNo;
        this.requestType = requestType;
        this.returnReason = returnReason;
        this.statusCode = statusCode;
        this.streetCode = streetCode;
        this.sysIterID = sysIterID;
        this.sysIterTotalsChild = sysIterTotalsChild;
        this.sysIterTrID = sysIterTrID;
    }

    public void setAddressInDetails(String addressInDetails) {
        this.addressInDetails = addressInDetails;
    }

    public String getAddressInDetails() {
        return addressInDetails;
    }

    public void setBuildingCode(Long buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Long getBuildingCode() {
        return buildingCode;
    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setFlatNo(Long flatNo) {
        this.flatNo = flatNo;
    }

    public Long getFlatNo() {
        return flatNo;
    }

    public void setFloorNo(Long floorNo) {
        this.floorNo = floorNo;
    }

    public Long getFloorNo() {
        return floorNo;
    }

    public void setGovCode(String govCode) {
        this.govCode = govCode;
    }

    public String getGovCode() {
        return govCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setPhonesNo(String phonesNo) {
        this.phonesNo = phonesNo;
    }

    public String getPhonesNo() {
        return phonesNo;
    }

    public void setRealCivilID(Long realCivilID) {
        this.realCivilID = realCivilID;
    }

    public Long getRealCivilID() {
        return realCivilID;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestNo(Long requestNo) {
        this.requestNo = requestNo;
    }

    public Long getRequestNo() {
        return requestNo;
    }

    public void setRequestType(Long requestType) {
        this.requestType = requestType;
    }

    public Long getRequestType() {
        return requestType;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setSysIterID(Long sysIterID) {
        this.sysIterID = sysIterID;
    }

    public Long getSysIterID() {
        return sysIterID;
    }

    public void setSysIterTotalsChild(Long sysIterTotalsChild) {
        this.sysIterTotalsChild = sysIterTotalsChild;
    }

    public Long getSysIterTotalsChild() {
        return sysIterTotalsChild;
    }

    public void setSysIterTrID(Long sysIterTrID) {
        this.sysIterTrID = sysIterTrID;
    }

    public Long getSysIterTrID() {
        return sysIterTrID;
    }
}
