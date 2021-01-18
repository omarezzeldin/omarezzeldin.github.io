package com.beshara.csc.hr.emp.business.dto.pifsswebservice;

import java.io.Serializable;

import java.sql.Date;
import java.sql.Timestamp;


public class ParametersDTO implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Integer addressElectronicNumber ;
    private String allotmentNumber;
    private String apartmentNumber;
    private String avenueNumber;
    private Double complementaryRefund ;
    private String cscOperationType ;
    private String cstomerIdentity;
    private String customerName;
    private String dateOfBirth;
    private String dateOfFirstRegistration;
    private String dateOfNationality;
    private Integer employerRegistrationNumber;
    private String enrollmentDate;
    private String floorNumber; 
    private String houseNumber;
    private Integer job = 1013;
    private Integer mobileNumber; 
    private String nationality;
    private String nationalityNumber;
    private String region;
    private Double salary ; 
    private String sex;
    private Double socialRefund; 
    private String street; 
    private String subject; 
    private long serial;
    private Date insuranceTerminationDate;
    private String insuranceTerminationCause;
    private Long absentDaysForEmp;
    private String requestID;
    private String requestTime;
    private String result;
    private String messageDescription;
    private Date lastOperationDate;
    private String tableName;
    private long tableTabrecSerial;
    private Long rTableTabrecSerial;
    private Long terminationReason;
    private Timestamp requestDateTime;
    public void setAddressElectronicNumber(Integer addressElectronicNumber) {
        this.addressElectronicNumber = addressElectronicNumber;
    }

    public Integer getAddressElectronicNumber() {
        return addressElectronicNumber;
    }

    public void setAllotmentNumber(String allotmentNumber) {
        this.allotmentNumber = allotmentNumber;
    }

    public String getAllotmentNumber() {
        return allotmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setAvenueNumber(String avenueNumber) {
        this.avenueNumber = avenueNumber;
    }

    public String getAvenueNumber() {
        return avenueNumber;
    }

    public void setComplementaryRefund(Double complementaryRefund) {
        this.complementaryRefund = complementaryRefund;
    }

    public Double getComplementaryRefund() {
        return complementaryRefund;
    }

    public void setCscOperationType(String cscOperationType) {
        this.cscOperationType = cscOperationType;
    }

    public String getCscOperationType() {
        return cscOperationType;
    }

    public void setCstomerIdentity(String cstomerIdentity) {
        this.cstomerIdentity = cstomerIdentity;
    }

    public String getCstomerIdentity() {
        return cstomerIdentity;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfFirstRegistration(String dateOfFirstRegistration) {
        this.dateOfFirstRegistration = dateOfFirstRegistration;
    }

    public String getDateOfFirstRegistration() {
        return dateOfFirstRegistration;
    }

    public void setDateOfNationality(String dateOfNationality) {
        this.dateOfNationality = dateOfNationality;
    }

    public String getDateOfNationality() {
        return dateOfNationality;
    }

    public void setEmployerRegistrationNumber(Integer employerRegistrationNumber) {
        this.employerRegistrationNumber = employerRegistrationNumber;
    }

    public Integer getEmployerRegistrationNumber() {
        return employerRegistrationNumber;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setJob(Integer job) {
        this.job = job;
    }

    public Integer getJob() {
        return job;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationalityNumber(String nationalityNumber) {
        this.nationalityNumber = nationalityNumber;
    }

    public String getNationalityNumber() {
        return nationalityNumber;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSocialRefund(Double socialRefund) {
        this.socialRefund = socialRefund;
    }

    public Double getSocialRefund() {
        return socialRefund;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }

    public long getSerial() {
        return serial;
    }

    public void setInsuranceTerminationDate(Date insuranceTerminationDate) {
        this.insuranceTerminationDate = insuranceTerminationDate;
    }

    public Date getInsuranceTerminationDate() {
        return insuranceTerminationDate;
    }

    public void setInsuranceTerminationCause(String insuranceTerminationCause) {
        this.insuranceTerminationCause = insuranceTerminationCause;
    }

    public String getInsuranceTerminationCause() {
        return insuranceTerminationCause;
    }

    public void setAbsentDaysForEmp(Long absentDaysForEmp) {
        this.absentDaysForEmp = absentDaysForEmp;
    }

    public Long getAbsentDaysForEmp() {
        return absentDaysForEmp;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setMessageDescription(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getMessageDescription() {
        return messageDescription;
    }

    public void setLastOperationDate(Date lastOperationDate) {
        this.lastOperationDate = lastOperationDate;
    }

    public Date getLastOperationDate() {
        return lastOperationDate;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableTabrecSerial(long tableTabrecSerial) {
        this.tableTabrecSerial = tableTabrecSerial;
    }

    public long getTableTabrecSerial() {
        return tableTabrecSerial;
    }
    
    public String toJson(){
        StringBuilder requestString = new StringBuilder();
        requestString.append("{\n");
        requestString.append("\"addressElectronicNumber\":" + this.addressElectronicNumber + ",\n");
        requestString.append("\"allotmentNumber\":" + this.allotmentNumber + ",\n");
        requestString.append("\"apartmentNumber\":" + this.apartmentNumber + ",\n");
        requestString.append("\"avenueNumber\":" + this.avenueNumber + ",\n");
        requestString.append("\"complementaryRefund\":" + this.getComplementaryRefund() + ",\n");
        if (cscOperationType != null && !"".equals(cscOperationType)) {
            requestString.append("\"cscOperationType\":\"" + this.getCscOperationType() + "\",\n");
        }
        requestString.append("\"customerIdentity\":" + this.getCstomerIdentity() + ",\n");
        if (customerName != null && !"".equals(customerName)) {
            requestString.append("\"customerName\":\"" + this.getCustomerName() + "\",\n");
        }
        if (dateOfBirth != null && !"".equals(dateOfBirth)) {
            requestString.append("\"dateOfBirth\": \"" + this.getDateOfBirth() + "\",\n");
        }
        if (dateOfFirstRegistration != null && !"".equals(dateOfFirstRegistration)) {
            requestString.append("\"dateOfFirstRegistration\":\"" + this.getDateOfFirstRegistration() + "\",\n");
        }
        if (dateOfNationality != null && !"".equals(dateOfNationality)) {
            requestString.append("\"dateOfNationality\":\"" + this.getDateOfNationality() + "\",\n");
        }
        requestString.append("\"employerRegistrationNumber\": " + this.getEmployerRegistrationNumber() + ",\n");
        if (enrollmentDate != null && !"".equals(enrollmentDate)) {
            requestString.append("\"enrollmentDate\":\"" + this.getEnrollmentDate() + "\",\n");
        }
        requestString.append("\"floorNumber\":" + this.getFloorNumber() + ", \n");
        requestString.append("\"houseNumber\":" + this.getHouseNumber() + ",\n");
        requestString.append("\"job\":\"1013\",\n");
        requestString.append("\"mobileNumber\":" + this.getMobileNumber() + ", \n");
        if (nationality != null && !"".equals(nationality)) {
            requestString.append("\"nationality\":\"" + this.getNationality() + "\",\n");
        }
        if (nationalityNumber != null && !"".equals(nationalityNumber)) {
            requestString.append("\"nationalityNumber\":\"" + this.getNationalityNumber() + "\",\n");
        }
        if (this.region != null && !"".equals(this.region)) {
            requestString.append("\"region\":\"" + this.getRegion() + "\",\n");
        }
        if (this.salary != null && !"".equals(this.salary)) {
            requestString.append("\"salary\":\"" + this.getSalary() + "\", \n");
        }
        if (this.sex != null && !"".equals(this.sex)) {
            requestString.append("\"sex\": \"" + this.getSex() + "\",\n");
        }
        if (this.socialRefund != null && !"".equals(this.socialRefund)) {
            requestString.append("\"socialRefund\":\"" + this.getSocialRefund() + "\", \n");
        }
        if (this.street != null && !"".equals(this.street)) {
            requestString.append("\"street\":\"" + this.getStreet() + "\", \n");
        }
        if (this.subject != null && !"".equals(this.subject)) {
            requestString.append("\"subject\":\"" + this.getSubject() + "\"\n");
        }
        requestString.append("}");

        return requestString.toString();
    }
    
    public String toJsonTermination() {
        String json =   "{\n" + 
        "\"absentDays\": \""+ getAbsentDaysForEmp() +"\",\n" + 
        "\"cscOperationType\": \""+getCscOperationType()+"\",\n" + 
        "\"customerIdentity\": "+ getCstomerIdentity() +",\n" + 
        "\"customerName\": \""+ getCustomerName()+"\",\n" + 
        "\"insuranceTerminationCause\": \""+ getInsuranceTerminationCause() +"\",\n" + 
        "\"terminationReason\": \""+ getTerminationReason()+"\",\n" + 
        "\"insuranceTerminationDate\": \"" + getInsuranceTerminationDate() + "\"\n" + 
                        
        "}\n";
        return json;
    }

    public void setRTableTabrecSerial(Long rTableTabrecSerial) {
        this.rTableTabrecSerial = rTableTabrecSerial;
    }

    public Long getRTableTabrecSerial() {
        return rTableTabrecSerial;
    }

    public void setTerminationReason(Long terminationReason) {
        this.terminationReason = terminationReason;
    }

    public Long getTerminationReason() {
        return terminationReason;
    }

    public void setRequestDateTime(Timestamp requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public Timestamp getRequestDateTime() {
        return requestDateTime;
    }
}
