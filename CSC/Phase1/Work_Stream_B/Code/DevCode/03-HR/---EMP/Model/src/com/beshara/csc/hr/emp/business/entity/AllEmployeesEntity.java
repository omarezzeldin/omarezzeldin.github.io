package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of AllEmployees Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
@Entity
@NamedQueries( { @NamedQuery(name = "AllEmployeesEntity.findAll", 
                             query = "select o from AllEmployeesEntity o")
        , 
        @NamedQuery(name = "AllEmployeesEntity.findNewId", query = "select MAX(o.civilId) from AllEmployeesEntity o")
        } )
@Table(name = "HR_EMP_ALL_EMPLOYEES")
@IdClass(IAllEmployeesEntityKey.class)
public class

AllEmployeesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false,insertable = true,updatable = true)
    private Long civilId;
    @Id
    @Column(name = "TRX_DATETIME", nullable = false)
    private Timestamp trxDatetime;
    @Column(name = "MIN_CODE", nullable = false,insertable = true,updatable = true)
    private Long minCode;
    @Column(name = "WRK_CODE", nullable = false,insertable = true,updatable = true)
    private String wrkCode;
    @Column(name = "MINISTRY_FILE_NO", nullable = false)
    private String ministryFileNo;
    @Column(name = "CSC_FILE_NO", nullable = false)
    private String cscFileNo;
    @Column(name = "HIRE_DATE", nullable = false)
    private Timestamp hireDate;
    @Column(name = "START_WORKING_DATE", nullable = false)
    private Timestamp startWorkingDate;
    @Column(name = "END_JOB_DATE", nullable = true)
    private Timestamp endJobDate;
    @Column(name = "HIRSTATUS_CODE", nullable = false)
    private Long hirstatusCode;
    @Column(name = "HIRTYPE_CODE", nullable = false)
    private Long hirtypeCode;
    @Column(name = "DATE_OF_NEXT_RAISE", nullable = false)
    private Timestamp dateOfNextRaise;
    @Column(name = "TECH_JOB_CODE", nullable = true)
    private String techJobCode;
    @Column(name = "JOB_CODE", nullable = false,insertable = true,updatable = true)
    private String jobCode;
    @Column(name = "BANK_CODE", nullable = true,insertable = true,updatable = true)
    private Long bankCode;
    @Column(name = "BNKBRANCH_CODE", nullable = true,insertable = true,updatable = true)
    private Long bnkbranchCode;
    @Column(name = "ACCOUNT_NO", nullable = true)
    private String accountNo;
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Long activeFlag;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    
    @Column(name = "PRG_CODE", nullable = true,insertable = true,updatable = true)
    private String prgCode;
    
    @Column(name = "TYPE_CODE", nullable = true,insertable = true,updatable = true)
    private Long typeCode;
    
    
    
    @ManyToOne
    @JoinColumn(name = "HIRSTATUS_CODE", 
                referencedColumnName = "HIRSTATUS_CODE", insertable = false, 
                updatable = false)
    private HireStatusEntity hireStatusEntity;
    @ManyToOne
    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE", 
                insertable = false, updatable = false)
    private HireTypesEntity hireTypesEntity;
//    @OneToOne
//    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID", 
//                insertable = false, updatable = false)
//    private KwtCitizensResidentsEntity citizensResidentsEntity;
//    @ManyToOne
//    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE", 
//                insertable = true, updatable = true)
//    private JobsEntity jobsEntity;
    
    /*Added By Taha Abdul Mejid*/
//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "BANK_CODE", 
//                                referencedColumnName = "BANK_CODE", 
//                                insertable = false, updatable = false)
//            , 
//            @JoinColumn(name = "BNKBRANCH_CODE", referencedColumnName = "BNKBRANCH_CODE", 
//                        insertable = false, updatable = false)
//            } )
//    private BankBranchesEntity bankBranchesEntity;
    
//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "WRK_CODE", 
//                                referencedColumnName = "WRK_CODE", 
//                                insertable = false, updatable = false)
//            , 
//            @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE", insertable = 
//                        false, updatable = false)
//            } )
//    private WorkCentersEntity workCentersEntity;
//    @ManyToOne
//    @JoinColumn(name = "TECH_JOB_CODE", referencedColumnName = "JOB_CODE", 
//                insertable = false, updatable = false)
//    private JobsEntity techJobsEntity;
//    @ManyToOne
//    @JoinColumn(name = "BGTTYPE_CODE", referencedColumnName = "TYPE_CODE")
//   private BgtTypesEntity bgtTypesEntity;
//    @ManyToOne
//    @JoinColumn(name = "BGTPRG_CODE", referencedColumnName = "PRG_CODE")
//    private BgtProgramsEntity bgtProgramsEntity;

    /**
     * AllEmployeesEntity Default Constructor
     */
    public AllEmployeesEntity() {
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
     * @return Long
     */
    public Long getMinCode() {
        return minCode;
    }

    /**
     * @return String
     */
    public String getWrkCode() {
        return wrkCode;
    }

    /**
     * @return String
     */
    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    /**
     * @return String
     */
    public String getCscFileNo() {
        return cscFileNo;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getHireDate() {
        return hireDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getStartWorkingDate() {
        return startWorkingDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getEndJobDate() {
        return endJobDate;
    }

    /**
     * @return Long
     */
    public Long getHirstatusCode() {
        return hirstatusCode;
    }

    /**
     * @return Long
     */
    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    /**
     * @return String
     */
    public String getTechJobCode() {
        return techJobCode;
    }

    /**
     * @return String
     */
    public String getJobCode() {
        return jobCode;
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
    public Long getActiveFlag() {
        return activeFlag;
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
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     * @param wrkCode
     */
    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    /**
     * @param ministryFileNo
     */
    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    /**
     * @param cscFileNo
     */
    public void setCscFileNo(String cscFileNo) {
        this.cscFileNo = cscFileNo;
    }

    /**
     * @param hireDate
     */
    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @param startWorkingDate
     */
    public void setStartWorkingDate(Timestamp startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    /**
     * @param endJobDate
     */
    public void setEndJobDate(Timestamp endJobDate) {
        this.endJobDate = endJobDate;
    }

    /**
     * @param hirstatusCode
     */
    public void setHirstatusCode(Long hirstatusCode) {
        this.hirstatusCode = hirstatusCode;
    }

    /**
     * @param hirtypeCode
     */
    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    /**
     * @param dateOfNextRaise
     */
    public void setDateOfNextRaise(Timestamp dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    /**
     * @param techJobCode
     */
    public void setTechJobCode(String techJobCode) {
        this.techJobCode = techJobCode;
    }

    /**
     * @param jobCode
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
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
     * @param activeFlag
     */
    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
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

    public void setHireStatusEntity(HireStatusEntity hireStatusEntity) {
        this.hireStatusEntity = hireStatusEntity;
    }

    public HireStatusEntity getHireStatusEntity() {
        return hireStatusEntity;
    }

    public void setHireTypesEntity(HireTypesEntity hireTypesEntity) {
        this.hireTypesEntity = hireTypesEntity;
    }

    public HireTypesEntity getHireTypesEntity() {
        return hireTypesEntity;
    }
/*
    public void setCitizensResidentsEntity(KwtCitizensResidentsEntity citizensResidentsEntity) {
        this.citizensResidentsEntity = citizensResidentsEntity;
    }

    public KwtCitizensResidentsEntity getCitizensResidentsEntity() {
        return citizensResidentsEntity;
    }

    public void setJobsEntity(JobsEntity jobsEntity) {
        this.jobsEntity = jobsEntity;
    }

    public JobsEntity getJobsEntity() {
        return jobsEntity;
    }

    public void setBankBranchesEntity(BankBranchesEntity bankBranchesEntity) {
        this.bankBranchesEntity = bankBranchesEntity;
    }

    public BankBranchesEntity getBankBranchesEntity() {
        return bankBranchesEntity;
    }

    public void setWorkCentersEntity(WorkCentersEntity workCentersEntity) {
        this.workCentersEntity = workCentersEntity;
    }

    public WorkCentersEntity getWorkCentersEntity() {
        return workCentersEntity;
    }

    public void setTechJobsEntity(JobsEntity techJobsEntity) {
        this.techJobsEntity = techJobsEntity;
    }

    public JobsEntity getTechJobsEntity() {
        return techJobsEntity;
    }

    public void setBgtTypesEntity(BgtTypesEntity bgtTypesEntity) {
        this.bgtTypesEntity = bgtTypesEntity;
    }

    public BgtTypesEntity getBgtTypesEntity() {
        return bgtTypesEntity;
    }

    public void setBgtProgramsEntity(BgtProgramsEntity bgtProgramsEntity) {
        this.bgtProgramsEntity = bgtProgramsEntity;
    }

    public BgtProgramsEntity getBgtProgramsEntity() {
        return bgtProgramsEntity;
    }
*/

    public void setPrgCode(String prgCode) {
        this.prgCode = prgCode;
    }

    public String getPrgCode() {
        return prgCode;
    }

    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    public Long getTypeCode() {
        return typeCode;
    }
}
