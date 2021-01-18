package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of EmpEmployeeHistories Entity.
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
@NamedQueries( { @NamedQuery(name = "EmpEmployeeHistoriesEntity.findAll", 
                             query = 
                             "select o from EmpEmployeeHistoriesEntity o")
        , 
        @NamedQuery(name = "EmpEmployeeHistoriesEntity.findNewId", query = "select MAX(o.serial) from EmpEmployeeHistoriesEntity o"),
        @NamedQuery(name = "EmpEmployeeHistoriesEntity.getEmployeeDataByCivilID", query ="SELECT o FROM EmpEmployeeHistoriesEntity o WHERE o.civilId = :civilId"),
        @NamedQuery(name = "EmpEmployeeHistoriesEntity.getFirstHireDate", query ="SELECT min(o.fromDate) from  EmpEmployeeHistoriesEntity o WHERE o.civilId = :civilId")
        } )
@Table(name = "HR_EMP_EMPLOYEE_HISTORIES")
@IdClass(IEmpEmployeeHistoriesEntityKey.class)
public class EmpEmployeeHistoriesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "WRK_CODE", nullable = true, insertable = true, 
            updatable = true)
    private String wrkCode;
    @Column(name = "MIN_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long minCode;
    @Column(name = "JOB_CODE", nullable = false, insertable = true, 
            updatable = true)
    private String jobCode;
    @Column(name = "TECH_JOB_CODE", nullable = true, insertable = true, 
            updatable = true)
    private String techJobCode;
    @Column(name = "FROM_DATE", nullable = false)
    private Timestamp fromDate;
    @Column(name = "UNTIL_DATE", nullable = true)
    private Timestamp untilDate;
    @Column(name = "AUDIT_BY", nullable = true)
    private Long auditBy;
    @Column(name = "AUDIT_DATE", nullable = true)
    private Timestamp auditDate;
    @Column(name = "PIS_FLAG", nullable = false)
    private Long pisFlag;
    @Column(name = "PER_FLAG", nullable = false)
    private Long perFlag;
    @Column(name = "TRXTYPE_CODE", nullable = true)
    private Long trxtypeCode;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @Column(name = "CREATED_BY", nullable = true)
    private String createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private String lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    //@ManyToOne 
    //@JoinColumn(name="AUDIT_BY", referencedColumnName="USER_CODE")
    //private EmpUsersEntity empUsersEntity;
    //@ManyToOne 
    //@JoinColumn(name="TRXTYPE_CODE", referencedColumnName="TRXTYPE_CODE")
    //private EmpTrxTypesEntity empTrxTypesEntity;
    //@ManyToOne 
    //@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
    //private EmpCitizensResidentsEntity empCitizensResidentsEntity;
    //@ManyToOne 
    //@JoinColumn(name="JOB_CODE", referencedColumnName="JOB_CODE")
    //private EmpJobsEntity empJobsEntity;
    //@ManyToOne 
    //@JoinColumn(name="TECH_JOB_CODE", referencedColumnName="JOB_CODE")
    //private EmpJobsEntity empJobsEntity;
    //@ManyToOne 
    //@JoinColumn(name="WRK_CODE", referencedColumnName="WRK_CODE")
    //private EmpWorkCentersEntity empWorkCentersEntity;
    //@ManyToOne 
    //@JoinColumn(name="MIN_CODE", referencedColumnName="MIN_CODE")
    //private EmpWorkCentersEntity empWorkCentersEntity;
//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "WRK_CODE", 
//                                referencedColumnName = "WRK_CODE")
//            , @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE")
//            } )
//    private WorkCentersEntity workCentersEntity;

//    @ManyToOne
//    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE")
//    private JobsEntity jobsEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "TECH_JOB_CODE", referencedColumnName = "JOB_CODE")
//    private JobsEntity techJobsEntity;


    /**
     * EmpEmployeeHistoriesEntity Default Constructor
     */
    public EmpEmployeeHistoriesEntity() {
    }


    /**
     * @return Long
     */
    public Long getSerial() {
        return serial;
    }

    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return String
     */
    public String getWrkCode() {
        return wrkCode;
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
    public String getJobCode() {
        return jobCode;
    }

    /**
     * @return String
     */
    public String getTechJobCode() {
        return techJobCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getFromDate() {
        return fromDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getUntilDate() {
        return untilDate;
    }

    /**
     * @return Long
     */
    public Long getAuditBy() {
        return auditBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getAuditDate() {
        return auditDate;
    }

    /**
     * @return Long
     */
    public Long getPisFlag() {
        return pisFlag;
    }

    /**
     * @return Long
     */
    public Long getPerFlag() {
        return perFlag;
    }

    /**
     * @return Long
     */
    public Long getTrxtypeCode() {
        return trxtypeCode;
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
     * @param serial
     */
    public void setSerial(Long serial) {
        this.serial = serial;
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param wrkCode
     */
    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    /**
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     * @param jobCode
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * @param techJobCode
     */
    public void setTechJobCode(String techJobCode) {
        this.techJobCode = techJobCode;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @param untilDate
     */
    public void setUntilDate(Timestamp untilDate) {
        this.untilDate = untilDate;
    }

    /**
     * @param auditBy
     */
    public void setAuditBy(Long auditBy) {
        this.auditBy = auditBy;
    }

    /**
     * @param auditDate
     */
    public void setAuditDate(Timestamp auditDate) {
        this.auditDate = auditDate;
    }

    /**
     * @param pisFlag
     */
    public void setPisFlag(Long pisFlag) {
        this.pisFlag = pisFlag;
    }

    /**
     * @param perFlag
     */
    public void setPerFlag(Long perFlag) {
        this.perFlag = perFlag;
    }

    /**
     * @param trxtypeCode
     */
    public void setTrxtypeCode(Long trxtypeCode) {
        this.trxtypeCode = trxtypeCode;
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


//    public void setWorkCentersEntity(WorkCentersEntity workCentersEntity) {
//        this.workCentersEntity = workCentersEntity;
//    }
//
//    public WorkCentersEntity getWorkCentersEntity() {
//        return workCentersEntity;
//    }

//    public void setJobsEntity(JobsEntity jobsEntity) {
//        this.jobsEntity = jobsEntity;
//    }
//
//    public JobsEntity getJobsEntity() {
//        return jobsEntity;
//    }
//
//    public void setTechJobsEntity(JobsEntity techJobsEntity) {
//        this.techJobsEntity = techJobsEntity;
//    }
//
//    public JobsEntity getTechJobsEntity() {
//        return techJobsEntity;
//    }
}
