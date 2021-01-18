package com.beshara.csc.hr.emp.business.entity.sal;

//import com.beshara.csc.hr.sal.business.entity.SalEmpSalaryElementsEntityKey;


import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity


@Table(name = "HR_SAL_EMP_SALARY_ELEMENTS")

public class EmpSalEmpSalaryElementsEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = true, updatable = true)
    private Long civilId;
    @Id
    @Column(name = "ELMGUIDE_CODE", nullable = false, insertable = true, updatable = true)
    private Long elmguideCode;
    @Id
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;
    @Column(name = "STPREASON_CODE", nullable = true, insertable = false, updatable = false)
    private Long stpreasonCode;
    @Column(name = "DEGREASON_CODE", nullable = true, insertable = false, updatable = false)
    private Long degreasonCode;
    @Column(name = "INVESTIGATION_CODE", nullable = true)
    private Long investigationCode;
    @Column(name = "ELEMENT_VALUE", nullable = true)
    private Float elementValue;
    @Column(name = "ELEMENT_RATIO", nullable = false)
    private Long elementRatio;
    @Column(name = "NO_OF_INSTALLS", nullable = true)
    private Long noOfInstalls;
    //    @Column(name = "REQ_SERIAL", nullable = true)
    //    private Long reqSerial;

    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    @Column(name = "FIRST_INSTALL_DATE", nullable = true)
    private Date firstInstallDate;
    @Column(name = "INSTALL_MONTHLY_VALUE", nullable = true)
    private Double installMonthlyValue;

    @Column(name = "EQ_ELMGUIDE_CODE", nullable = true, insertable = false, updatable = false)
    private Long eqElmGuideCode;


    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID", insertable = false, updatable = false)
    private EmployeesEntity employeesEntity;

    @ManyToOne
    @JoinColumn(name = "ELMGUIDE_CODE", referencedColumnName = "ELMGUIDE_CODE", insertable = false, updatable = false)
    private EmpSalElementGuidesEntity salElementGuidesEntity;

    /**
     * EmpSalEmpSalaryElementsEntity Default Constructor
     */
    public EmpSalEmpSalaryElementsEntity() {
    }


    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return Long
     */
    public Long getElmguideCode() {
        return elmguideCode;
    }

    /**
     * @return Timestamp
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @return Timestamp
     */
    public Date getUntilDate() {
        return untilDate;
    }

    /**
     * @return Long
     */
    public Long getStpreasonCode() {
        return stpreasonCode;
    }

    /**
     * @return Long
     */
    public Long getDegreasonCode() {
        return degreasonCode;
    }

    /**
     * @return Long
     */
    public Long getInvestigationCode() {
        return investigationCode;
    }

    /**
     * @return Long
     */
    public Float getElementValue() {
        return elementValue;
    }

    /**
     * @return Long
     */
    public Long getElementRatio() {
        return elementRatio;
    }

    /**
     * @return Long
     */
    public Long getNoOfInstalls() {
        return noOfInstalls;
    }

    //    /**
    //     * @return Long
    //     */
    //    public Long getReqSerial() {
    //        return reqSerial;
    //    }


    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }


    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param elmguideCode
     */
    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @param untilDate
     */
    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    /**
     * @param stpreasonCode
     */
    public void setStpreasonCode(Long stpreasonCode) {
        this.stpreasonCode = stpreasonCode;
    }

    /**
     * @param degreasonCode
     */
    public void setDegreasonCode(Long degreasonCode) {
        this.degreasonCode = degreasonCode;
    }

    /**
     * @param investigationCode
     */
    public void setInvestigationCode(Long investigationCode) {
        this.investigationCode = investigationCode;
    }

    /**
     * @param elementValue
     */
    public void setElementValue(Float elementValue) {
        this.elementValue = elementValue;
    }

    /**
     * @param elementRatio
     */
    public void setElementRatio(Long elementRatio) {
        this.elementRatio = elementRatio;
    }

    /**
     * @param noOfInstalls
     */
    public void setNoOfInstalls(Long noOfInstalls) {
        this.noOfInstalls = noOfInstalls;
    }

    //    /**
    //     * @param reqSerial
    //     */
    //    public void setReqSerial(Long reqSerial) {
    //        this.reqSerial = reqSerial;
    //    }


    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }


    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }


    public void setFirstInstallDate(Date firstInstallDate) {
        this.firstInstallDate = firstInstallDate;
    }

    public Date getFirstInstallDate() {
        return firstInstallDate;
    }

    public void setInstallMonthlyValue(Double installMonthlyValue) {
        this.installMonthlyValue = installMonthlyValue;
    }

    public Double getInstallMonthlyValue() {
        return installMonthlyValue;
    }

    public void setEqElmGuideCode(Long eqElmGuideCode) {
        this.eqElmGuideCode = eqElmGuideCode;
    }

    public Long getEqElmGuideCode() {
        return eqElmGuideCode;
    }


    public void setSalElementGuidesEntity(EmpSalElementGuidesEntity salElementGuidesEntity) {
        this.salElementGuidesEntity = salElementGuidesEntity;
    }

    public EmpSalElementGuidesEntity getSalElementGuidesEntity() {
        return salElementGuidesEntity;
    }
}
