package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.entity.bgt.EmpBgtProgramsEntity;
import com.beshara.csc.hr.emp.business.entity.bgt.EmpBgtTypesEntity;
import com.beshara.csc.hr.emp.business.entity.inf.EmpKwtCitizensResidentsEntity;
import com.beshara.csc.hr.emp.business.entity.job.EmpJobsEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpWorkCentersEntity;
import com.beshara.csc.hr.emp.business.entity.reg.EmpEmpDecisionsEntity;
import com.beshara.csc.hr.emp.business.entity.sal.EmpSalEmpSalaryElementsEntity;

import java.sql.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of Employees Entity.
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
 *//**
    * <b>Description:</b>
    * <br>&nbsp;&nbsp;&nbsp;
    * This Class Manipulate the Persistence Methods of Employees Entity.
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
@NamedQueries( { @NamedQuery(name = "EmployeesEntity.findAll",
                             query = "select o from EmployeesEntity o where o.hireStagesEntity.hirstageCode<>:hirstageCode"),
                 @NamedQuery(name = "EmployeesEntity.getAllEmployeeWaitingForHireDecision",
                             query = "select o from EmployeesEntity o where o.hireStagesEntity.hirstageCode=:hirstageCode"),
                 @NamedQuery(name = "EmployeesEntity.getAllEmployeeWithStageAndStatus",
                             query = "select o from EmployeesEntity o where o.hirstageCode=:hirstageCode AND o.hirstatusCode=:hirstatusCode order by o.citizensResidentsEntity.firstName, o.citizensResidentsEntity.secondName,o.citizensResidentsEntity.thirdName, o.citizensResidentsEntity.lastName,o.citizensResidentsEntity.familyName"),
                 @NamedQuery(name = "EmployeesEntity.getAllEmployeeWithStageAndMinCode",
                             query = "select o from EmployeesEntity o where o.hirstageCode=:hirstageCode AND o.minCode=:minCode "),
                 @NamedQuery(name = "EmployeesEntity.getFilterEmployeeWaitingForHireDecision",
                             query = "select o from EmployeesEntity o where o.hireStagesEntity.hirstageCode=:hirstageCode AND o.hirtypeCode=:hirtypeCode AND o.minCode=:minCode"),
                 @NamedQuery(name = "EmployeesEntity.findNewId",
                             query = "select MAX(o.civilId) from EmployeesEntity o"),
                 @NamedQuery(name = "EmployeesEntity.searchByHireType",
                             query = "select o from EmployeesEntity o where o.hirtypeCode = :hirtypeCode"),
                 @NamedQuery(name = "EmployeesEntity.searchByMainHireType",
                             query = "select o from EmployeesEntity o where o.hirstatusCode=:hirstatusCode AND o.hirstageCode <> :stageCancelNomination AND (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) AND o.hireTypesEntity.status = :hireTypeStatus AND o.activeFlag <> :activeFlag"),
                 @NamedQuery(name = "EmployeesEntity.getAllEmployeesWithActiveHireTypes",
                             query = "select o from EmployeesEntity o where o.hirstatusCode=:hirstatusCode AND o.hirstageCode <> :stageCancelNomination AND o.hireTypesEntity.status = :hireTypeStatus AND o.activeFlag <> :activeFlag"),
                 @NamedQuery(name = "EmployeesEntity.filterByHireType",
                             query = "select o from EmployeesEntity o where (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) and o.hirstatusCode = :hirstatusCode and  o.hirstageCode IN (:val1, :val2) "),
                 @NamedQuery(name = "EmployeesEntity.filterByAllHireTypes",
                             query = "select o from EmployeesEntity o where o.hirtypeCode in (:hiretype1, :hiretype2, :hiretype3) or o.hireTypesEntity.parentHireTypeCode in (:hiretype1, :hiretype2, :hiretype3) and  o.hirstatusCode = :hirstatusCode and  o.hirstageCode IN (:val1, :val2) "),
                 @NamedQuery(name = "EmployeesEntity.filterByHireTypeForEmpExecute",
                             query = "select o from EmployeesEntity o where (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) and o.hirstatusCode = :hirstatusCode and  o.hirstageCode =:hirstageCode"),
                 @NamedQuery(name = "EmployeesEntity.filterByAllHireTypesForHireExecute",
                             query = "select o from EmployeesEntity o where o.hirtypeCode in (:hiretype1, :hiretype2, :hiretype3) or o.hireTypesEntity.parentHireTypeCode in (:hiretype1, :hiretype2, :hiretype3) and o.hirstatusCode = :hirstatusCode and  o.hirstageCode =:hirstageCode"),
                 @NamedQuery(name = "EmployeesEntity.getEmployeeAndPayrollByElmType",
                             query = "select emp from EmployeesEntity emp inner join emp.salEmpSalaryElementsEntityList salElem where emp.civilId = :civilId and salElem.untilDate IS NULL and salElem.salElementGuidesEntity.elmtypeCode IN(:elmGuideType , :elmGuideType2)"),
                 @NamedQuery(name = "EmployeesEntity.findEmployeeRelatedToDecision",
                             query = "select o from EmployeesEntity o , IN (o.empDecisionsEntityList) empList where empList.decisionsEntity.decisionNumber=:decisionNumber AND empList.decisionsEntity.dectypeCode=:dectypeCode AND empList.decisionsEntity.decyearCode=:decyearCode",
                             hints = { @QueryHint(name = "toplink.refresh", value = "true") }),
                 @NamedQuery(name = "EmployeesEntity.findEmployeeNotRelatedToDecision",
                             query = "select o from EmployeesEntity o   WHERE o.civilId NOT IN (SELECT empList.civilId FROM  EmpEmpDecisionsEntity  empList where   empList.decisionsEntity.decisionNumber=:decisionNumber   AND empList.decisionsEntity.dectypeCode=:dectypeCode   AND empList.decisionsEntity.decyearCode=:decyearCode)"),
                 @NamedQuery(name = "EmployeesEntity.getEmployeeMinistry",
                             query = "select o.minCode from EmployeesEntity o where o.realCivilId = :realCivilId"),
                 @NamedQuery(name = "EmployeesEntity.isEmployeeExist",
                             query = "select o.civilId from EmployeesEntity o where o.civilId = :civilId"),
                 @NamedQuery(name = "EmployeesEntity.isMinistryFileNoExist",
                             query = "select o.ministryFileNo from EmployeesEntity o where o.ministryFileNo = :ministryFileNo"),
                 @NamedQuery(name = "EmployeesEntity.isEmployeeValidToEndService",
                             query = "select o from EmployeesEntity o where o.civilId = :civilId AND o.hireStatusEntity.hirstatusCode IN (:s1,:s2,:s3,:s4,:s5,:s6,:s7,:s8,:s9)"),
                 @NamedQuery(name = "EmployeesEntity.getEmployeesByMinistry",
                             query = "select o from EmployeesEntity o where o.minCode = :minCode"),
                 @NamedQuery(name = "EmployeesEntity.getCountByHireStage",
                             query = "select count(o.civilId) from EmployeesEntity o where o.hireStagesEntity.hirstageCode = :hirstageCode"),
                 @NamedQuery(name = "EmployeesEntity.getByRealCivilId",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId AND ( :minCode is null OR o.minCode = :minCode) AND o.activeFlag = :activeFlag"),
                 @NamedQuery(name = "EmployeesEntity.getBasicDataByRealCivilId",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId AND ( :minCode is null OR o.minCode = :minCode) AND ( :minCode is null AND o.hireStatusEntity.hirstatusCode IN (1,13)) AND o.activeFlag = :activeFlag"),
                 @NamedQuery(name = "EmployeesEntity.getCurrActiveEmpByRealCivilId",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId AND ( :minCode is null OR o.minCode = :minCode) AND (o.hireStatusEntity.hirstatusCode IN (1,13)) AND o.activeFlag = :activeFlag "),
                 @NamedQuery(name = "EmployeesEntity.getCurrActiveEmployeesByRealCivilId",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId "),
                 @NamedQuery(name = "EmployeesEntity.getByRealCivilIdAllMinistries",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId and o.hireStatusEntity.hirstatusCode IN (:s1,:s2,:s3,:s4,:s5,:s6,:s7,:s8,:s9) AND o.activeFlag = :activeFlag"),
                 @NamedQuery(name = "EmployeesEntity.getEmpCountForEndService",
                             query = "select count(o.civilId) from EmployeesEntity o where o.activeFlag = :activeFlag and o.realCivilId = :realCivilId and o.hireStatusEntity.hirstatusCode = :hireStatus and o.minCode = :minCode"),
                 @NamedQuery(name = "EmployeesEntity.CheckIfEmployeeIsExist",
                             query = "select o from EmployeesEntity o WHERE o.civilId = :civilId AND o.activeFlag = :activeFlag and o.hirstatusCode IN (:s1,:s2)"),
                 @NamedQuery(name = "EmployeesEntity.getMinCodeByCivil",
                             query = "select o.minCode from EmployeesEntity o WHERE o.civilId = :civilId AND o.activeFlag = :activeFlag"),
                 @NamedQuery(name = "EmployeesEntity.CheckIfEmployeeExists",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId AND o.activeFlag = :activeFlag and o.hirstatusCode IN (:s1,:s2)"),
               @NamedQuery(name = "EmployeesEntity.getByRealCivilIdAllMinistriesForAdc",
                             query = "select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId and o.hireStatusEntity.hirstatusCode IN (1,9,13,11) AND o.activeFlag = 1")
                 })
@Table(name = "HR_EMP_EMPLOYEES")
@IdClass(IEmployeesEntityKey.class)

public class EmployeesEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = true, updatable = true)
    private Long civilId;
    @Column(name = "MIN_CODE", nullable = false, insertable = true, updatable = true)
    private Long minCode;
    @Column(name = "WRK_CODE", nullable = false, insertable = true, updatable = true)
    private String wrkCode;
    @Column(name = "MINISTRY_FILE_NO", nullable = false)
    private String ministryFileNo;
    @Column(name = "CSC_FILE_NO", nullable = false)
    private String cscFileNo;
    @Column(name = "HIRE_DATE", nullable = false)
    private Date hireDate;
    @Column(name = "START_WORKING_DATE", nullable = false)
    private Date startWorkingDate;
    @Column(name = "END_JOB_DATE", nullable = true)
    private Date endJobDate;
    @Column(name = "HIRSTATUS_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirstatusCode;
    @Column(name = "HIRTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirtypeCode;
    @Column(name = "HIRSTAGE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirstageCode;
    @Column(name = "DATE_OF_NEXT_RAISE", nullable = false)
    private Date dateOfNextRaise;
    @Column(name = "TECH_JOB_CODE", nullable = true, insertable = true, updatable = true)
    private String techJobCode;
    @Column(name = "JOB_CODE", nullable = false, insertable = true, updatable = true)
    private String jobCode;
    @Column(name = "CANDIDATE_CODE", nullable = true, insertable = false, updatable = false)
    private Long candidateCode;
    @Column(name = "CANDIDATE_CODE_SEQ", nullable = true, insertable = false, updatable = false)
    private Long candidateCodeSeq;

    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true, insertable = false, updatable = false)
    private Long tabrecSerial;
    @Column(name = "SOCIAL_INSUR_NO", nullable = true)
    private String socialInsurNo;
    @Column(name = "REAL_CIVIL_ID", nullable = false, updatable = true, insertable = true)
    private Long realCivilId;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Long activeFlag;

    @Column(name = "RECORDDESC_CODE", nullable = true)
    private Long recordDescCode;

    @Column(name = "BGTTYPE_CODE", nullable = true, insertable = true, updatable = true)
    private Long bgttypeCode;
    @Column(name = "BGTPRG_CODE", nullable = true, insertable = true, updatable = true)
    private String bgtprgCode;

    @ManyToOne
    @JoinColumn(name = "HIRSTAGE_CODE", referencedColumnName = "HIRSTAGE_CODE")
    private HireStagesEntity hireStagesEntity;
    @ManyToOne
    @JoinColumn(name = "HIRSTATUS_CODE", referencedColumnName = "HIRSTATUS_CODE")
    private HireStatusEntity hireStatusEntity;
    @ManyToOne
    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypesEntity;

    @ManyToOne
    @JoinColumn(name = "REAL_CIVIL_ID", referencedColumnName = "CIVIL_ID", insertable = false, updatable = false)
    private EmpKwtCitizensResidentsEntity citizensResidentsEntity;
    @ManyToOne
    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE", insertable = false, updatable = false)
    private EmpJobsEntity jobsEntity;
    /*Added By Taha Abdul Mejid*/
    //    @ManyToOne
    //    @JoinColumns( { @JoinColumn(name = "BANK_CODE",
    //                                referencedColumnName = "BANK_CODE")
    //            ,
    //            @JoinColumn(name = "BNKBRANCH_CODE", referencedColumnName = "BNKBRANCH_CODE")
    //            } )
    //    private BankBranchesEntity bankBranchesEntity;
    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "WRK_CODE", referencedColumnName = "WRK_CODE", insertable = false,
                                updatable = false),
                    @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE", insertable = false,
                                updatable = false) })
    private EmpWorkCentersEntity workCentersEntity;
    @ManyToOne
    @JoinColumn(name = "TECH_JOB_CODE", referencedColumnName = "JOB_CODE", insertable = false, updatable = false)
    private EmpJobsEntity techJobsEntity;
    @OneToMany(mappedBy = "employeesEntity")
    private List<EmpEmpDecisionsEntity> empDecisionsEntityList;
    //    @OneToMany(mappedBy = "employeesEntity")
    //    private List<EmployeeDocumentsEntity> employeeDocumentsEntityList;
    //    @OneToMany(mappedBy = "employeesEntity")
    //    private List<EmployeeProceduresEntity> employeeProceduresEntityList;
    @OneToMany(mappedBy = "employeesEntity")
    private List<EmpSalEmpSalaryElementsEntity> salEmpSalaryElementsEntityList;
    /////////////////////////////////////////
    ///ADDED BY TAHA ABDUL MEJID @ 14/8/08
    @ManyToOne
    @JoinColumn(name = "BGTTYPE_CODE", referencedColumnName = "TYPE_CODE", insertable = false, updatable = false)
    private EmpBgtTypesEntity bgtTypesEntity;
    @ManyToOne
    @JoinColumn(name = "BGTPRG_CODE", referencedColumnName = "PRG_CODE", insertable = false, updatable = false)
    private EmpBgtProgramsEntity bgtProgramsEntity;

    @OneToMany(mappedBy = "employeesEntity")
    private List<EmployeeExtraDataEntity> employeeExtraDataEntityList;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "CANDIDATE_CODE", referencedColumnName = "CANDIDATE_CODE"),
                    @JoinColumn(name = "CANDIDATE_CODE_SEQ", referencedColumnName = "CANDIDATE_CODE_SEQ") })
    private EmpCandidatesEntity empCandidatesEntity;

    /**
     * EmployeesEntity Default Constructor
     */
    public EmployeesEntity() {
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
     * @return Long
     */
    public Long getHirstageCode() {
        return hirstageCode;
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
    //    public Long getBankCode() {
    //        return bankCode;
    //    }
    //
    //    /**
    //     * @return Long
    //     */
    //    public Long getBnkbranchCode() {
    //        return bnkbranchCode;
    //    }

    /**
     * @return String
     */
    //    public String getAccountNo() {
    //        return accountNo;
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
     * @param hirstageCode
     */
    public void setHirstageCode(Long hirstageCode) {
        this.hirstageCode = hirstageCode;
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
    //    public void setBankCode(Long bankCode) {
    //        this.bankCode = bankCode;
    //    }
    //
    //    /**
    //     * @param bnkbranchCode
    //     */
    //    public void setBnkbranchCode(Long bnkbranchCode) {
    //        this.bnkbranchCode = bnkbranchCode;
    //    }

    /**
     * @param accountNo
     */
    //    public void setAccountNo(String accountNo) {
    //        this.accountNo = accountNo;
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


    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setStartWorkingDate(Date startWorkingDate) {
        this.startWorkingDate = startWorkingDate;
    }

    public Date getStartWorkingDate() {
        return startWorkingDate;
    }

    public void setEndJobDate(Date endJobDate) {
        this.endJobDate = endJobDate;
    }

    public Date getEndJobDate() {
        return endJobDate;
    }

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    public void setHireStagesEntity(HireStagesEntity hireStagesEntity) {
        this.hireStagesEntity = hireStagesEntity;
    }

    public HireStagesEntity getHireStagesEntity() {
        return hireStagesEntity;
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

    public void setCitizensResidentsEntity(EmpKwtCitizensResidentsEntity citizensResidentsEntity) {
        this.citizensResidentsEntity = citizensResidentsEntity;
    }

    public EmpKwtCitizensResidentsEntity getCitizensResidentsEntity() {
        return citizensResidentsEntity;
    }

    public void setJobsEntity(EmpJobsEntity jobsEntity) {
        this.jobsEntity = jobsEntity;
    }

    public EmpJobsEntity getJobsEntity() {
        return jobsEntity;
    }

    public void setWorkCentersEntity(EmpWorkCentersEntity workCentersEntity) {
        this.workCentersEntity = workCentersEntity;
    }

    public EmpWorkCentersEntity getWorkCentersEntity() {
        return workCentersEntity;
    }

    public void setEmpDecisionsEntityList(List<EmpEmpDecisionsEntity> empDecisionsEntityList) {
        this.empDecisionsEntityList = empDecisionsEntityList;
    }

    public List<EmpEmpDecisionsEntity> getEmpDecisionsEntityList() {
        return empDecisionsEntityList;
    }

    //    public void setBankBranchesEntity(BankBranchesEntity bankBranchesEntity) {
    //        this.bankBranchesEntity = bankBranchesEntity;
    //    }
    //
    //    public BankBranchesEntity getBankBranchesEntity() {
    //        return bankBranchesEntity;
    //    }

    public void setTechJobsEntity(EmpJobsEntity techJobsEntity) {
        this.techJobsEntity = techJobsEntity;
    }

    public EmpJobsEntity getTechJobsEntity() {
        return techJobsEntity;
    }

    //    public void setEmployeeDocumentsEntityList(List<EmployeeDocumentsEntity> employeeDocumentsEntityList) {
    //        this.employeeDocumentsEntityList = employeeDocumentsEntityList;
    //    }
    //
    //    public List<EmployeeDocumentsEntity> getEmployeeDocumentsEntityList() {
    //        return employeeDocumentsEntityList;
    //    }
    //
    //    public void setEmployeeProceduresEntityList(List<EmployeeProceduresEntity> employeeProceduresEntityList) {
    //        this.employeeProceduresEntityList = employeeProceduresEntityList;
    //    }
    //
    //    public List<EmployeeProceduresEntity> getEmployeeProceduresEntityList() {
    //        return employeeProceduresEntityList;
    //    }

    public void setSalEmpSalaryElementsEntityList(List<EmpSalEmpSalaryElementsEntity> salEmpSalaryElementsEntityList) {
        this.salEmpSalaryElementsEntityList = salEmpSalaryElementsEntityList;
    }

    public List<EmpSalEmpSalaryElementsEntity> getSalEmpSalaryElementsEntityList() {
        return salEmpSalaryElementsEntityList;
    }

    public void setBgtTypesEntity(EmpBgtTypesEntity bgtTypesEntity) {
        this.bgtTypesEntity = bgtTypesEntity;
    }

    public EmpBgtTypesEntity getBgtTypesEntity() {
        return bgtTypesEntity;
    }

    public void setBgtProgramsEntity(EmpBgtProgramsEntity bgtProgramsEntity) {
        this.bgtProgramsEntity = bgtProgramsEntity;
    }

    public EmpBgtProgramsEntity getBgtProgramsEntity() {
        return bgtProgramsEntity;
    }

    public void setSocialInsurNo(String socialInsurNo) {
        this.socialInsurNo = socialInsurNo;
    }

    public String getSocialInsurNo() {
        return socialInsurNo;
    }

    public void setEmployeeExtraDataEntityList(List<EmployeeExtraDataEntity> employeeExtraDataEntityList) {
        this.employeeExtraDataEntityList = employeeExtraDataEntityList;
    }

    public List<EmployeeExtraDataEntity> getEmployeeExtraDataEntityList() {
        return employeeExtraDataEntityList;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setRecordDescCode(Long recordDescCode) {
        this.recordDescCode = recordDescCode;
    }

    public Long getRecordDescCode() {
        return recordDescCode;
    }

    public void setCandidateCode(Long candidateCode) {
        this.candidateCode = candidateCode;
    }

    public Long getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCodeSeq(Long candidateCodeSeq) {
        this.candidateCodeSeq = candidateCodeSeq;
    }

    public Long getCandidateCodeSeq() {
        return candidateCodeSeq;
    }

    public void setEmpCandidatesEntity(EmpCandidatesEntity empCandidatesEntity) {
        this.empCandidatesEntity = empCandidatesEntity;
    }

    public EmpCandidatesEntity getEmpCandidatesEntity() {
        return empCandidatesEntity;
    }

    public void setBgttypeCode(Long bgttypeCode) {
        this.bgttypeCode = bgttypeCode;
    }

    public Long getBgttypeCode() {
        return bgttypeCode;
    }

    public void setBgtprgCode(String bgtprgCode) {
        this.bgtprgCode = bgtprgCode;
    }

    public String getBgtprgCode() {
        return bgtprgCode;
    }
}
