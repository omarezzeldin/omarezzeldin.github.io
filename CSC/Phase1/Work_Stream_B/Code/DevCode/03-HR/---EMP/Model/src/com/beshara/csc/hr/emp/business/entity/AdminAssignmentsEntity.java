package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

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
 * This Class Manipulate the Persistence Methods of AdminAssignments Entity.
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
@NamedQueries( { @NamedQuery(name = "AdminAssignmentsEntity.findAll", 
                             query = "select o from AdminAssignmentsEntity o")
        , 
        @NamedQuery(name = "AdminAssignmentsEntity.findNewId", query = "select MAX(o.admassignmentSerial) from AdminAssignmentsEntity o")
        , 
        @NamedQuery(name = "AdminAssignmentsEntity.getTableRecordSerial", query ="select o.tabrecSerial from AdminAssignmentsEntity o WHERE o.admassignmentSerial=:admassignmentSerial")
       ,            
        
        @NamedQuery(name = "AdminAssignmentsEntity.getAllUsingStatusFlag", query = 
                    "select o from AdminAssignmentsEntity o WHERE o.assignStatusEntity.assstatusCode=:assstatusCode")
        } )
@Table(name = "HR_EMP_ADMIN_ASSIGNMENTS")
@IdClass(IAdminAssignmentsEntityKey.class)
public class

AdminAssignmentsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ADMASSIGNMENT_SERIAL", nullable = false)
    private Long admassignmentSerial;
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, 
            updatable = false)
    private Long civilId;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;
    @Column(name = "ASSTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long asstypeCode;
    @Column(name = "ASSREASON_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long assreasonCode;
    @Column(name = "ASSSTATUS_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long assstatusCode;
    @Column(name = "MIN_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long minCode;
    @Column(name = "WRK_CODE", nullable = false, insertable = true, 
            updatable = true)
    private String wrkCode;
    @Column(name = "JOB_CODE", nullable = false, insertable = true, 
            updatable = true)
    private String jobCode;
    @Column(name = "ELMGUIDE_CODE", nullable = false, insertable = true, 
            updatable = true)
    private Long elmguideCode;
    @Column(name = "DECMKRTYPE_CODE", nullable = false, insertable = true, 
            updatable = true)
    private Long decmkrtypeCode;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "ASSREASON_CODE", 
                referencedColumnName = "ASSREASON_CODE")
    private AssignReasonsEntity assignReasonsEntity;
    @ManyToOne
    @JoinColumn(name = "ASSSTATUS_CODE", 
                referencedColumnName = "ASSSTATUS_CODE")
    private AssignStatusEntity assignStatusEntity;
    @ManyToOne
    @JoinColumn(name = "ASSTYPE_CODE", referencedColumnName = "ASSTYPE_CODE")
    private AssignTypesEntity assignTypesEntity;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private EmployeesEntity employeesEntity;
//    @ManyToOne
//    @JoinColumn(name = "ELMGUIDE_CODE", referencedColumnName = "ELMGUIDE_CODE")
//    private SalElementGuidesEntity salElementGuidesEntity;
//    @ManyToOne
//    @JoinColumn(name = "DECMKRTYPE_CODE", 
//                referencedColumnName = "DECMKRTYPE_CODE")
//    private DecisionMakerTypesEntity decisionMakerTypesEntity;
//    @ManyToOne
//    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE")
//    private JobsEntity jobsEntity;
//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "WRK_CODE", 
//                                referencedColumnName = "WRK_CODE")
//            , @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE")
//            } )
//    private WorkCentersEntity workCentersEntity;


    /**
     * AdminAssignmentsEntity Default Constructor
     */
    public AdminAssignmentsEntity() {
    }


    /**
     * @return Long
     */
    public Long getAdmassignmentSerial() {
        return admassignmentSerial;
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
    public Long getAsstypeCode() {
        return asstypeCode;
    }

    /**
     * @return Long
     */
    public Long getAssreasonCode() {
        return assreasonCode;
    }

    /**
     * @return Long
     */
    public Long getAssstatusCode() {
        return assstatusCode;
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
    public String getJobCode() {
        return jobCode;
    }

    /**
     * @return Long
     */
    public Long getElmguideCode() {
        return elmguideCode;
    }

    /**
     * @return Long
     */
    public Long getDecmkrtypeCode() {
        return decmkrtypeCode;
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
     * @param admassignmentSerial
     */
    public void setAdmassignmentSerial(Long admassignmentSerial) {
        this.admassignmentSerial = admassignmentSerial;
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
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
     * @param asstypeCode
     */
    public void setAsstypeCode(Long asstypeCode) {
        this.asstypeCode = asstypeCode;
    }

    /**
     * @param assreasonCode
     */
    public void setAssreasonCode(Long assreasonCode) {
        this.assreasonCode = assreasonCode;
    }

    /**
     * @param assstatusCode
     */
    public void setAssstatusCode(Long assstatusCode) {
        this.assstatusCode = assstatusCode;
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
     * @param jobCode
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * @param elmguideCode
     */
    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    /**
     * @param decmkrtypeCode
     */
    public void setDecmkrtypeCode(Long decmkrtypeCode) {
        this.decmkrtypeCode = decmkrtypeCode;
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


    public void setAssignReasonsEntity(AssignReasonsEntity assignReasonsEntity) {
        this.assignReasonsEntity = assignReasonsEntity;
    }

    public AssignReasonsEntity getAssignReasonsEntity() {
        return assignReasonsEntity;
    }

    public void setAssignStatusEntity(AssignStatusEntity assignStatusEntity) {
        this.assignStatusEntity = assignStatusEntity;
    }

    public AssignStatusEntity getAssignStatusEntity() {
        return assignStatusEntity;
    }

    public void setAssignTypesEntity(AssignTypesEntity assignTypesEntity) {
        this.assignTypesEntity = assignTypesEntity;
    }

    public AssignTypesEntity getAssignTypesEntity() {
        return assignTypesEntity;
    }

    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }

//    public void setSalElementGuidesEntity(SalElementGuidesEntity salElementGuidesEntity) {
//        this.salElementGuidesEntity = salElementGuidesEntity;
//    }
//
//    public SalElementGuidesEntity getSalElementGuidesEntity() {
//        return salElementGuidesEntity;
//    }
//
//    public void setDecisionMakerTypesEntity(DecisionMakerTypesEntity decisionMakerTypesEntity) {
//        this.decisionMakerTypesEntity = decisionMakerTypesEntity;
//    }
//
//    public DecisionMakerTypesEntity getDecisionMakerTypesEntity() {
//        return decisionMakerTypesEntity;
//    }
//
//    public void setJobsEntity(JobsEntity jobsEntity) {
//        this.jobsEntity = jobsEntity;
//    }
//
//    public JobsEntity getJobsEntity() {
//        return jobsEntity;
//    }
//
//    public void setWorkCentersEntity(WorkCentersEntity workCentersEntity) {
//        this.workCentersEntity = workCentersEntity;
//    }
//
//    public WorkCentersEntity getWorkCentersEntity() {
//        return workCentersEntity;
//    }
}
