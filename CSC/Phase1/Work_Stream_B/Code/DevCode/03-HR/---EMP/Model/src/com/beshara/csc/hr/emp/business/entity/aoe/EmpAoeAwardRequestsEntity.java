package com.beshara.csc.hr.emp.business.entity.aoe;


import com.beshara.base.entity.BasicEntity;

import com.beshara.csc.hr.emp.business.entity.inf.EmpKwtCitizensResidentsEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name = "HR_AOE_AWARD_REQUESTS")
public class EmpAoeAwardRequestsEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "AWDREQUEST_SERIAL", nullable = false)
    private Long awdrequestSerial;
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "TRX_DATETIME", nullable = false)
    private Timestamp trxDatetime;
    @Column(name = "REQUEST_DATE", nullable = false)
    private Timestamp requestDate;
    @Column(name = "AWDENTTYPE_CODE", nullable = false)
    private Long awdenttypeCode;
    @Column(name = "AWDSTATUS_CODE", nullable = false)
    private Long awdstatusCode;
    @Column(name = "INITIATOR_CODE", nullable = false)
    private Long initiatorCode;
    @Column(name = "BANK_CODE", nullable = true)
    private Long bankCode;
    @Column(name = "BNKBRANCH_CODE", nullable = true)
    private Long bnkbranchCode;
    @Column(name = "ACCOUNT_NO", nullable = true)
    private String accountNo;
    @Column(name = "PAYMETHOD_CODE", nullable = true)
    private Long paymethodCode;
    @Column(name = "PAYSTATUS_CODE", nullable = true)
    private Long paystatusCode;
    @Column(name = "REQ_SERIAL", nullable = true)
    private Long reqSerial;
    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @Column(name = "MIN_CODE", nullable = false)
    private Long minCode;
    @Column(name = "REMARKS", nullable = true)
    private String remarks;
    @Column(name = "CHECK_ACCOUNT_NO", nullable = true)
    private String checkAccountNo;
    @Column(name = "CHECK_DATE", nullable = true)
    private Timestamp checkDate;
    @Column(name = "CHECK_BANK_CODE", nullable = true)
    private Long checkBankCode;
    @Column(name = "CHECK_BNKBRANCH_CODE", nullable = true)
    private Long checkBnkbranchCode;
    @Column(name = "CHECK_DATE_RECEIVE", nullable = true)
    private Timestamp checkDateReceive;
    @Column(name = "FIRST_CHECK_VALUE", nullable = true)
    private Long firstCheckValue;
    @Column(name = "SECOND_CHECK_NO", nullable = true)
    private String secondCheckNo;
    @Column(name = "SECOND_CHECK_DATE", nullable = true)
    private Timestamp secondCheckDate;
    @Column(name = "SECOND_CHECK_DATE_REC", nullable = true)
    private Timestamp secondCheckDateRec;
    @Column(name = "SECOND_CHECK_VALUE", nullable = true)
    private Long secondCheckValue;
    @Column(name = "CANAWDREASON_CODE", nullable = true)
    private Long canawdreasonCode;
    
//    @ManyToOne
//    @JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID", insertable = false, updatable = false)
//    private EmpKwtCitizensResidentsEntity empKwtCitizensResidentsEntity;
    //@ManyToOne
    //@JoinColumn(name="CHECK_BANK_CODE", referencedColumnName="BANK_CODE")
    //private AoeBranchesEntity aoeBranchesEntity;
    //@ManyToOne
    //@JoinColumn(name="BNKBRANCH_CODE", referencedColumnName="BNKBRANCH_CODE")
    //private AoeBranchesEntity aoeBranchesEntity;
    //@ManyToOne
    //@JoinColumn(name="CHECK_BNKBRANCH_CODE", referencedColumnName="BNKBRANCH_CODE")
    //private AoeBranchesEntity aoeBranchesEntity;
    //@ManyToOne
    //@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
    //private AoeCitizensResidentsEntity aoeCitizensResidentsEntity;
    //@ManyToOne
    //@JoinColumn(name="PAYMETHOD_CODE", referencedColumnName="PAYMETHOD_CODE")
    //private AoePaymentMethodsEntity aoePaymentMethodsEntity;
    //@ManyToOne
    //@JoinColumn(name="PAYSTATUS_CODE", referencedColumnName="PAYSTATUS_CODE")
    //private AoePaymentStatusEntity aoePaymentStatusEntity;
    //@ManyToOne
    //@JoinColumn(name="AWDENTTYPE_CODE", referencedColumnName="AWDENTTYPE_CODE")
    //private AoeAwardEntryTypesEntity aoeAwardEntryTypesEntity;
    //@ManyToOne
    //@JoinColumn(name="AWDSTATUS_CODE", referencedColumnName="AWDSTATUS_CODE")
    //private AoeAwardStatusEntity aoeAwardStatusEntity;
    //@ManyToOne
    //@JoinColumn(name="CANAWDREASON_CODE", referencedColumnName="CANAWDREASON_CODE")
    //private AoeCancelAwardReasonsEntity aoeCancelAwardReasonsEntity;
    //@ManyToOne
    //@JoinColumn(name="INITIATOR_CODE", referencedColumnName="INITIATOR_CODE")
    //private AoeInitiatorsEntity aoeInitiatorsEntity;


    /**
     * EmpAoeAwardRequestsEntity Default Constructor
     */
    public EmpAoeAwardRequestsEntity() {
    }


    /**
     * @return Long
     */
    public Long getAwdrequestSerial() {
        return awdrequestSerial;
    }

    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getTrxDatetime() {
        return trxDatetime;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getRequestDate() {
        return requestDate;
    }

    /**
     * @return Long
     */
    public Long getAwdenttypeCode() {
        return awdenttypeCode;
    }

    /**
     * @return Long
     */
    public Long getAwdstatusCode() {
        return awdstatusCode;
    }

    /**
     * @return Long
     */
    public Long getInitiatorCode() {
        return initiatorCode;
    }

    /**
     * @return Long
     */
    public Long getBankCode() {
        return bankCode;
    }

    /**
     * @return Long
     */
    public Long getBnkbranchCode() {
        return bnkbranchCode;
    }

    /**
     * @return String
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @return Long
     */
    public Long getPaymethodCode() {
        return paymethodCode;
    }

    /**
     * @return Long
     */
    public Long getPaystatusCode() {
        return paystatusCode;
    }

    /**
     * @return Long
     */
    public Long getReqSerial() {
        return reqSerial;
    }

    /**
     * @return Long
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @return Long
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

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
     * @return Long
     */
    public Long getMinCode() {
        return minCode;
    }

    /**
     * @return String
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @return String
     */
    public String getCheckAccountNo() {
        return checkAccountNo;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCheckDate() {
        return checkDate;
    }

    /**
     * @return Long
     */
    public Long getCheckBankCode() {
        return checkBankCode;
    }

    /**
     * @return Long
     */
    public Long getCheckBnkbranchCode() {
        return checkBnkbranchCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCheckDateReceive() {
        return checkDateReceive;
    }

    /**
     * @return Long
     */
    public Long getFirstCheckValue() {
        return firstCheckValue;
    }

    /**
     * @return String
     */
    public String getSecondCheckNo() {
        return secondCheckNo;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getSecondCheckDate() {
        return secondCheckDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getSecondCheckDateRec() {
        return secondCheckDateRec;
    }

    /**
     * @return Long
     */
    public Long getSecondCheckValue() {
        return secondCheckValue;
    }

    /**
     * @return Long
     */
    public Long getCanawdreasonCode() {
        return canawdreasonCode;
    }


    /**
     * @param awdrequestSerial
     */
    public void setAwdrequestSerial(Long awdrequestSerial) {
        this.awdrequestSerial = awdrequestSerial;
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param trxDatetime
     */
    public void setTrxDatetime(Timestamp trxDatetime) {
        this.trxDatetime = trxDatetime;
    }

    /**
     * @param requestDate
     */
    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @param awdenttypeCode
     */
    public void setAwdenttypeCode(Long awdenttypeCode) {
        this.awdenttypeCode = awdenttypeCode;
    }

    /**
     * @param awdstatusCode
     */
    public void setAwdstatusCode(Long awdstatusCode) {
        this.awdstatusCode = awdstatusCode;
    }

    /**
     * @param initiatorCode
     */
    public void setInitiatorCode(Long initiatorCode) {
        this.initiatorCode = initiatorCode;
    }

    /**
     * @param bankCode
     */
    public void setBankCode(Long bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * @param bnkbranchCode
     */
    public void setBnkbranchCode(Long bnkbranchCode) {
        this.bnkbranchCode = bnkbranchCode;
    }

    /**
     * @param accountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @param paymethodCode
     */
    public void setPaymethodCode(Long paymethodCode) {
        this.paymethodCode = paymethodCode;
    }

    /**
     * @param paystatusCode
     */
    public void setPaystatusCode(Long paystatusCode) {
        this.paystatusCode = paystatusCode;
    }

    /**
     * @param reqSerial
     */
    public void setReqSerial(Long reqSerial) {
        this.reqSerial = reqSerial;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

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

    /**
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @param checkAccountNo
     */
    public void setCheckAccountNo(String checkAccountNo) {
        this.checkAccountNo = checkAccountNo;
    }

    /**
     * @param checkDate
     */
    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * @param checkBankCode
     */
    public void setCheckBankCode(Long checkBankCode) {
        this.checkBankCode = checkBankCode;
    }

    /**
     * @param checkBnkbranchCode
     */
    public void setCheckBnkbranchCode(Long checkBnkbranchCode) {
        this.checkBnkbranchCode = checkBnkbranchCode;
    }

    /**
     * @param checkDateReceive
     */
    public void setCheckDateReceive(Timestamp checkDateReceive) {
        this.checkDateReceive = checkDateReceive;
    }

    /**
     * @param firstCheckValue
     */
    public void setFirstCheckValue(Long firstCheckValue) {
        this.firstCheckValue = firstCheckValue;
    }

    /**
     * @param secondCheckNo
     */
    public void setSecondCheckNo(String secondCheckNo) {
        this.secondCheckNo = secondCheckNo;
    }

    /**
     * @param secondCheckDate
     */
    public void setSecondCheckDate(Timestamp secondCheckDate) {
        this.secondCheckDate = secondCheckDate;
    }

    /**
     * @param secondCheckDateRec
     */
    public void setSecondCheckDateRec(Timestamp secondCheckDateRec) {
        this.secondCheckDateRec = secondCheckDateRec;
    }

    /**
     * @param secondCheckValue
     */
    public void setSecondCheckValue(Long secondCheckValue) {
        this.secondCheckValue = secondCheckValue;
    }

    /**
     * @param canawdreasonCode
     */
    public void setCanawdreasonCode(Long canawdreasonCode) {
        this.canawdreasonCode = canawdreasonCode;
    }

//
//    public void setEmpKwtCitizensResidentsEntity(EmpKwtCitizensResidentsEntity empKwtCitizensResidentsEntity) {
//        this.empKwtCitizensResidentsEntity = empKwtCitizensResidentsEntity;
//    }
//
//    public EmpKwtCitizensResidentsEntity getEmpKwtCitizensResidentsEntity() {
//        return empKwtCitizensResidentsEntity;
//    }
}
