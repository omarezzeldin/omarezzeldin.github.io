package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;
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
 * This Class Manipulate the Persistence Methods of EmpInternalExperience Entity.
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
@NamedQueries( { @NamedQuery(name = "EmpInternalExperienceEntity.findAll", query = "select o from EmpInternalExperienceEntity o"), 
        @NamedQuery(name = "EmpInternalExperienceEntity.findNewId", query = "select MAX(o.serial) from EmpInternalExperienceEntity o"),
        @NamedQuery(name = "EmpInternalExperienceEntity.getAllByCivilIdAndMinCode", query = "select o from EmpInternalExperienceEntity o where o.civilId=:civilId and o.minCode=:minCode order by o.actionDate"),
        @NamedQuery(name = "EmpInternalExperienceEntity.getAllByCivilId", query = "select o from EmpInternalExperienceEntity o where o.civilId=:civilId order by o.actionDate")
        } )
@Table(name = "HR_EMP_INTERNAL_EXPERIENCE")
@IdClass(IEmpInternalExperienceEntityKey.class)
public class

EmpInternalExperienceEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "CIVIL_ID", nullable = true)
    private Long civilId;
    @Column(name = "MIN_CODE", nullable = true, insertable = true, updatable = true)
    private Long minCode;
    @Column(name = "ACTION_DATE", nullable = true)
    private Timestamp actionDate;
    @Column(name = "JOB_CODE", nullable = true, insertable = true, updatable = true)
    private String jobCode;
    @Column(name = "PIS_JOB_CODE", nullable = true)
    private String pisJobCode;
    @Column(name = "ELMGUIDE_CODE", nullable = true, insertable = true, updatable = true)
    private Long elmguideCode;
    @Column(name = "WRK_CODE", nullable = true, insertable = true, updatable = true)
    private String wrkCode;
    @Column(name = "PIS_WRK_CODE", nullable = true)
    private String pisWrkCode;
    @Column(name = "JOB_DETAIL", nullable = true)
    private String jobDetail;
    @Column(name = "TRXTYPE_CODE", nullable = true, insertable = false, updatable = false)
    private Long trxtypeCode;
    @Column(name = "REV_FLAG", nullable = true)
    private Long revFlag;
    @Column(name = "BGTTYPE_CODE", nullable = true, insertable = true, updatable = true)
    private Long bgttypeCode;
    @Column(name = "BGTPRG_CODE", nullable = true, insertable = true, updatable = true)
    private String bgtprgCode;
    @Column(name = "RECORD_SOURCE_FLAG", nullable = true)
    private Long recordSourceFlag;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
//    @ManyToOne
//    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE")
//    private JobsEntity jobsEntity;
 
//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "WRK_CODE", 
//                                referencedColumnName = "WRK_CODE")
//            , @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE")
//            } )
//    private WorkCentersEntity workCentersEntity;
//    @ManyToOne 
//    @JoinColumn(name="BGTPRG_CODE", referencedColumnName="PRG_CODE")
//    private BgtProgramsEntity bgtProgramsEntity;
//    @ManyToOne 
//    @JoinColumn(name="BGTTYPE_CODE", referencedColumnName="TYPE_CODE")
//    private BgtTypesEntity bgtTypesEntity;
    @ManyToOne 
    @JoinColumn(name="TRXTYPE_CODE", referencedColumnName="TRXTYPE_CODE")
    private TrxTypesEntity trxTypesEntity;
//    @ManyToOne
//    @JoinColumn(name = "ELMGUIDE_CODE", referencedColumnName = "ELMGUIDE_CODE")
//    private SalElementGuidesEntity salElementGuidesEntity;    
    
    //=================Start CSC-18251========================//
    @Column(name = "AOE_AUDIT_BY", nullable = true)
    private String aoeAuditBy;
    
    @Column(name = "AOE_AUDIT_DATE", nullable = true)
    private Date aoeAuditDate;
    
    @Column(name = "AOE_AUDIT_FLAG", nullable = true)
    private Long aoeAuditFlag;
    
    @Column(name = "AOE_APPROVED_BY", nullable = true)
    private String aoeApprovedBy;
    
    @Column(name = "AOE_APPROVED_DATE", nullable = true)
    private Date aoeApprovedDate;
    
    @Column(name = "AOE_APPROVED_FLAG", nullable = true)
    private Long aoeApprovedFlag;
    //=================End CSC-18251========================//

    /**
     * EmpInternalExperienceEntity Default Constructor
     */
    public EmpInternalExperienceEntity() {
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
     * @return Long
     */
    public Long getMinCode() {
        return minCode;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getActionDate() {
        return actionDate;
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
    public String getPisJobCode() {
        return pisJobCode;
    }

    /**
     * @return Long
     */
    public Long getElmguideCode() {
        return elmguideCode;
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
    public String getPisWrkCode() {
        return pisWrkCode;
    }

    /**
     * @return String
     */
    public String getJobDetail() {
        return jobDetail;
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
    public Long getRevFlag() {
        return revFlag;
    }

    /**
     * @return Long
     */
    public Long getBgttypeCode() {
        return bgttypeCode;
    }

    /**
     * @return String
     */
    public String getBgtprgCode() {
        return bgtprgCode;
    }

    /**
     * @return Long
     */
    public Long getRecordSourceFlag() {
        return recordSourceFlag;
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
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    /**
     * @param actionDate
     */
    public void setActionDate(Timestamp actionDate) {
        this.actionDate = actionDate;
    }

    /**
     * @param jobCode
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * @param pisJobCode
     */
    public void setPisJobCode(String pisJobCode) {
        this.pisJobCode = pisJobCode;
    }

    /**
     * @param elmguideCode
     */
    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    /**
     * @param wrkCode
     */
    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    /**
     * @param pisWrkCode
     */
    public void setPisWrkCode(String pisWrkCode) {
        this.pisWrkCode = pisWrkCode;
    }

    /**
     * @param jobDetail
     */
    public void setJobDetail(String jobDetail) {
        this.jobDetail = jobDetail;
    }

    /**
     * @param trxtypeCode
     */
    public void setTrxtypeCode(Long trxtypeCode) {
        this.trxtypeCode = trxtypeCode;
    }

    /**
     * @param revFlag
     */
    public void setRevFlag(Long revFlag) {
        this.revFlag = revFlag;
    }

    /**
     * @param bgttypeCode
     */
    public void setBgttypeCode(Long bgttypeCode) {
        this.bgttypeCode = bgttypeCode;
    }

    /**
     * @param bgtprgCode
     */
    public void setBgtprgCode(String bgtprgCode) {
        this.bgtprgCode = bgtprgCode;
    }

    /**
     * @param recordSourceFlag
     */
    public void setRecordSourceFlag(Long recordSourceFlag) {
        this.recordSourceFlag = recordSourceFlag;
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

//    public void setJobsEntity(JobsEntity jobsEntity) {
//        this.jobsEntity = jobsEntity;
//    }
//
//    public JobsEntity getJobsEntity() {
//        return jobsEntity;
//    }

//    public void setWorkCentersEntity(WorkCentersEntity workCentersEntity) {
//        this.workCentersEntity = workCentersEntity;
//    }
//
//    public WorkCentersEntity getWorkCentersEntity() {
//        return workCentersEntity;
//    }

//    public void setBgtProgramsEntity(BgtProgramsEntity bgtProgramsEntity) {
//        this.bgtProgramsEntity = bgtProgramsEntity;
//    }
//
//    public BgtProgramsEntity getBgtProgramsEntity() {
//        return bgtProgramsEntity;
//    }
//
//    public void setBgtTypesEntity(BgtTypesEntity bgtTypesEntity) {
//        this.bgtTypesEntity = bgtTypesEntity;
//    }
//
//    public BgtTypesEntity getBgtTypesEntity() {
//        return bgtTypesEntity;
//    }

    public void setTrxTypesEntity(TrxTypesEntity trxTypesEntity) {
        this.trxTypesEntity = trxTypesEntity;
    }

    public TrxTypesEntity getTrxTypesEntity() {
        return trxTypesEntity;
    }

//    public void setSalElementGuidesEntity(SalElementGuidesEntity salElementGuidesEntity) {
//        this.salElementGuidesEntity = salElementGuidesEntity;
//    }
//
//    public SalElementGuidesEntity getSalElementGuidesEntity() {
//        return salElementGuidesEntity;
//    }


//=================Start CSC-18251========================//
    public void setAoeAuditBy(String aoeAuditBy) {
        this.aoeAuditBy = aoeAuditBy;
    }

    public String getAoeAuditBy() {
        return aoeAuditBy;
    }

    public void setAoeAuditDate(Date aoeAuditDate) {
        this.aoeAuditDate = aoeAuditDate;
    }

    public Date getAoeAuditDate() {
        return aoeAuditDate;
    }

    public void setAoeAuditFlag(Long aoeAuditFlag) {
        this.aoeAuditFlag = aoeAuditFlag;
    }

    public Long getAoeAuditFlag() {
        return aoeAuditFlag;
    }

    public void setAoeApprovedBy(String aoeApprovedBy) {
        this.aoeApprovedBy = aoeApprovedBy;
    }

    public String getAoeApprovedBy() {
        return aoeApprovedBy;
    }

    public void setAoeApprovedDate(Date aoeApprovedDate) {
        this.aoeApprovedDate = aoeApprovedDate;
    }

    public Date getAoeApprovedDate() {
        return aoeApprovedDate;
    }

    public void setAoeApprovedFlag(Long aoeApprovedFlag) {
        this.aoeApprovedFlag = aoeApprovedFlag;
    }

    public Long getAoeApprovedFlag() {
        return aoeApprovedFlag;
    }
//=================End CSC-18251========================//
    
}
