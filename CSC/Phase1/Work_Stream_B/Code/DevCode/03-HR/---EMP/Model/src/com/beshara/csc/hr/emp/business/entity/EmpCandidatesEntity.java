package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.entity.inf.EmpKwtCitizensResidentsEntity;
import com.beshara.csc.hr.emp.business.entity.job.EmpJobsEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpWorkCentersEntity;

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
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "EmpCandidatesEntity.findAll", query = "select o from EmpCandidatesEntity o"),
                 @NamedQuery(name = "EmpCandidatesEntity.isMinistryFileNoExist",
                             query = "select o.ministryFileNo from EmpCandidatesEntity o where o.ministryFileNo = :ministryFileNo"),
                 @NamedQuery(name = "EmpCandidatesEntity.findNewId",
                             query = "select MAX(o.candidateCode) from EmpCandidatesEntity o"),
                 @NamedQuery(name = "EmpCandidatesEntity.filterByHireType",
                             query = "select o from EmpCandidatesEntity o where (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) and o.cndStatusCode = :cndStatusCode and o.activeFlag=:activeFlag and o.hirstageCode IN (:val1, :val2) "),
                                  @NamedQuery(name = "EmpCandidatesEntity.filterByHireTypewithMin",
                             query = "select o from EmpCandidatesEntity o where (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) and o.cndStatusCode = :cndStatusCode and o.minCode=:minCode and o.activeFlag=:activeFlag and o.hirstageCode IN (:val1, :val2) "),
                 @NamedQuery(name = "EmpCandidatesEntity.getFilterEmployeeWaitingForHireDecision",
                             query = "select o from EmpCandidatesEntity o where o.cndStatusCode = :cndStatusCode and o.hireStagesEntity.hirstageCode=:hirstageCode AND o.hirtypeCode=:hirtypeCode AND o.minCode=:minCode"),
                 @NamedQuery(name = "EmpCandidatesEntity.filterByAllHireTypes",
                             query = "select o from EmpCandidatesEntity o where (o.hirtypeCode in (:hiretype1, :hiretype2, :hiretype3) or o.hireTypesEntity.parentHireTypeCode in (:hiretype1, :hiretype2, :hiretype3)) and  o.cndStatusCode = :cndStatusCode and  o.activeFlag=:activeFlag and o.hirstageCode IN (:val1, :val2) "),
                                  @NamedQuery(name = "EmpCandidatesEntity.filterByAllHireTypesWithMin",
                             query = "select o from EmpCandidatesEntity o where (o.hirtypeCode in (:hiretype1, :hiretype2, :hiretype3) or o.hireTypesEntity.parentHireTypeCode in (:hiretype1, :hiretype2, :hiretype3)) and  o.cndStatusCode = :cndStatusCode and  o.minCode=:minCode and  o.activeFlag=:activeFlag and o.hirstageCode IN (:val1, :val2) "),
                 @NamedQuery(name = "EmpCandidatesEntity.filterByHireTypeForEmpExecute",
                             query = "select o from EmpCandidatesEntity o where (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) and o.cndStatusCode = :cndStatusCode and  o.hirstageCode =:hirstageCode and  o.activeFlag=:activeFlag and  o.minCode=:minCode"),
                 @NamedQuery(name = "EmpCandidatesEntity.checkIfCitizenIsCandidate",
                             query = "select o from EmpCandidatesEntity o where o.civilId=:civilId and o.cndStatusCode = :cndStatusCode and o.activeFlag=:activeFlag "),
                 @NamedQuery(name = "EmpCandidatesEntity.filterByAllHireTypesForHireExecute",
                             query = "select o from EmpCandidatesEntity o where (o.hirtypeCode in (:hiretype1, :hiretype2, :hiretype3) or o.hireTypesEntity.parentHireTypeCode in (:hiretype1, :hiretype2, :hiretype3)) and o.cndStatusCode = :cndStatusCode and  o.hirstageCode =:hirstageCode and o.activeFlag=:activeFlag ")
        //                 @NamedQuery(name = "EmpCandidatesEntity.searchByMainHireType", query = "select o from EmpCandidatesEntity o where o.cndStatusCode=:cndStatusCode AND o.hirstageCode <> :stageCancelNomination AND (o.hirtypeCode = :hirtypeCode or o.hireTypesEntity.parentHireTypeCode = :hirtypeCode) AND o.hireTypesEntity.status = :hireTypeStatus AND o.activeFlag <> :activeFlag")
        } )
@Table(name = "HR_EMP_CANDIDATES")
@IdClass(IEmpCandidatesEntityKey.class)
public class EmpCandidatesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CANDIDATE_CODE", nullable = false)
    private Long candidateCode;
    @Id
    @Column(name = "CANDIDATE_CODE_SEQ", nullable = false)
    private Long candidateCodeSeq;
    @Column(name = "CNDSTATUS_CODE", nullable = false, insertable = false, updatable = false)
    private Long cndStatusCode;
    @Column(name = "HIRTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirtypeCode;
    @Column(name = "HIRSTAGE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirstageCode;
    @Column(name = "MIN_CODE", insertable = true, updatable = true)
    private Long minCode;
    @Column(name = "WRK_CODE", length = 20, insertable = true, updatable = true)
    private String wrkCode;
    @Column(name = "CIVIL_ID", nullable = false, insertable = true, updatable = true)
    private Long civilId;
    @Column(name = "HIRE_DATE")
    private Date hireDate;
    @Column(name = "START_WORKING_DATE")
    private Date startWorkingDate;
    @Column(name = "DATE_OF_NEXT_RAISE")
    private Date dateOfNextRaise;
    @Column(name = "TEST_PERIOD_DATE")
    private Date testPeriodDate;
    @Column(name = "BGTPRG_CODE", length = 4, insertable = true, updatable = true)
    private String bgtprgCode;
    @Column(name = "BGTTYPE_CODE", insertable = true, updatable = true)
    private Long bgttypeCode;
    @Column(name = "JOB_CODE", length = 20, insertable = true, updatable = true)
    private String jobCode;
    @Column(name = "JOB_CODE_OTHER_JOB", length = 20, insertable = true, updatable = true)
    private String jobCodeOtherJob;
    @Column(name = "CSC_FILE_NO", length = 20)
    private String cscFileNo;
    @Column(name = "MINISTRY_FILE_NO", length = 20)
    private String ministryFileNo;
    @Column(name = "SOCIAL_INSUR_NO", length = 20)
    private String socialInsurNo;
    @Column(name = "CRS_TRX_CODE", insertable = true, updatable = true)
    private Long crsTrxCode;
    @Column(name = "CRS_REGPERIOD_CODE", insertable = true, updatable = true)
    private Long crsRegperiodCode;
    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Long activeFlag;
    @Column(name = "APPROVED_BY_USER")
    private Long approvedByUser;
    @Column(name = "APPROVED_DATE")
    private Date approvedDate;
    @Column(name = "AUDIT_BY_USER")
    private Long auditByUser;
    @Column(name = "TRANSFER_TO_EMP_FLAG", nullable = false)
    private Long transferToEmpFlag;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "TRANS_REQ_DATE")
    private Date transReqDate;


    @OneToMany(mappedBy = "empCandidatesEntity")
    private List<EmployeesEntity> employeesEntityList;
    @OneToMany(mappedBy = "empCandidatesEntity")
    private List<EmpCndSalaryElementsEntity> empCndSalaryElementsList;
    @OneToMany(mappedBy = "empCandidatesEntity")
    private List<EmpCandidateExtraDataEntity> empCandidateExtraDataList;
    @OneToMany(mappedBy = "empCandidatesEntity")
    private List<EmpCandidateDocumentsEntity> empCandidateDocumentsList;
    @OneToMany(mappedBy = "empCandidatesEntity")
    private List<EmpCandidateProceduresEntity> empCandidateProceduresList;
    @ManyToOne
    @JoinColumn(name = "CNDSTATUS_CODE", referencedColumnName = "CNDSTATUS_CODE")
    private EmpCandidateStatusEntity empCandidateStatusEntity;
//    @ManyToOne
//    @JoinColumn(name = "BGTPRG_CODE", referencedColumnName = "PRG_CODE")
//    private BgtProgramsEntity bgtProgramsEntity;
//    @ManyToOne
//    @JoinColumn(name = "BGTTYPE_CODE", referencedColumnName = "TYPE_CODE")
//    private BgtTypesEntity bgtTypesEntity;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID", insertable = false, updatable = false)
    private EmpKwtCitizensResidentsEntity citizensResidentsEntity;
//    @ManyToOne
//    @JoinColumns( { @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID", insertable = false,
//                                updatable = false),
//                    @JoinColumn(name = "CRS_TRX_CODE", referencedColumnName = "TRX_CODE"),
//                    @JoinColumn(name = "CRS_REGPERIOD_CODE", referencedColumnName = "REGPERIOD_CODE") })
//    private CandidatePersonsEntity candidatePersonsEntity;

//    @ManyToOne
//    @JoinColumn(name = "APPROVED_BY_USER", referencedColumnName = "USER_CODE")
//    private UsersEntity usersEntityApprovedBy;

//    @ManyToOne
//    @JoinColumn(name = "AUDIT_BY_USER", referencedColumnName = "USER_CODE")
//    private UsersEntity usersEntityAuditBy;

    @ManyToOne
    @JoinColumn(name = "HIRSTAGE_CODE", referencedColumnName = "HIRSTAGE_CODE")
    private HireStagesEntity hireStagesEntity;

    @ManyToOne
    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypesEntity;
//
    @ManyToOne
    @JoinColumn(name = "JOB_CODE_OTHER_JOB", referencedColumnName = "JOB_CODE",insertable = false,updatable = false)
    private EmpJobsEntity jobsEntityOtherJob;

    @ManyToOne
    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE", insertable = false, updatable = false)
    private EmpJobsEntity jobsEntity;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "WRK_CODE", referencedColumnName = "WRK_CODE", insertable = false, updatable = false),
                    @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE", insertable = false, updatable = false) })
    private EmpWorkCentersEntity workCentersEntity;

    public EmpCandidatesEntity() {
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

    public void setCndStatusCode(Long cndStatusCode) {
        this.cndStatusCode = cndStatusCode;
    }

    public Long getCndStatusCode() {
        return cndStatusCode;
    }

    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    public void setHirstageCode(Long hirstageCode) {
        this.hirstageCode = hirstageCode;
    }

    public Long getHirstageCode() {
        return hirstageCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setWrkCode(String wrkCode) {
        this.wrkCode = wrkCode;
    }

    public String getWrkCode() {
        return wrkCode;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
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

    public void setDateOfNextRaise(Date dateOfNextRaise) {
        this.dateOfNextRaise = dateOfNextRaise;
    }

    public Date getDateOfNextRaise() {
        return dateOfNextRaise;
    }

    public void setTestPeriodDate(Date testPeriodDate) {
        this.testPeriodDate = testPeriodDate;
    }

    public Date getTestPeriodDate() {
        return testPeriodDate;
    }

    public void setBgtprgCode(String bgtprgCode) {
        this.bgtprgCode = bgtprgCode;
    }

    public String getBgtprgCode() {
        return bgtprgCode;
    }

    public void setBgttypeCode(Long bgttypeCode) {
        this.bgttypeCode = bgttypeCode;
    }

    public Long getBgttypeCode() {
        return bgttypeCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCodeOtherJob(String jobCodeOtherJob) {
        this.jobCodeOtherJob = jobCodeOtherJob;
    }

    public String getJobCodeOtherJob() {
        return jobCodeOtherJob;
    }

    public void setCscFileNo(String cscFileNo) {
        this.cscFileNo = cscFileNo;
    }

    public String getCscFileNo() {
        return cscFileNo;
    }

    public void setMinistryFileNo(String ministryFileNo) {
        this.ministryFileNo = ministryFileNo;
    }

    public String getMinistryFileNo() {
        return ministryFileNo;
    }

    public void setSocialInsurNo(String socialInsurNo) {
        this.socialInsurNo = socialInsurNo;
    }

    public String getSocialInsurNo() {
        return socialInsurNo;
    }

    public void setCrsTrxCode(Long crsTrxCode) {
        this.crsTrxCode = crsTrxCode;
    }

    public Long getCrsTrxCode() {
        return crsTrxCode;
    }

    public void setCrsRegperiodCode(Long crsRegperiodCode) {
        this.crsRegperiodCode = crsRegperiodCode;
    }

    public Long getCrsRegperiodCode() {
        return crsRegperiodCode;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setApprovedByUser(Long approvedByUser) {
        this.approvedByUser = approvedByUser;
    }

    public Long getApprovedByUser() {
        return approvedByUser;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setAuditByUser(Long auditByUser) {
        this.auditByUser = auditByUser;
    }

    public Long getAuditByUser() {
        return auditByUser;
    }

    public void setTransferToEmpFlag(Long transferToEmpFlag) {
        this.transferToEmpFlag = transferToEmpFlag;
    }

    public Long getTransferToEmpFlag() {
        return transferToEmpFlag;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setEmployeesEntityList(List<EmployeesEntity> employeesEntityList) {
        this.employeesEntityList = employeesEntityList;
    }

    public List<EmployeesEntity> getEmployeesEntityList() {
        return employeesEntityList;
    }

    public void setEmpCndSalaryElementsList(List<EmpCndSalaryElementsEntity> empCndSalaryElementsList) {
        this.empCndSalaryElementsList = empCndSalaryElementsList;
    }

    public List<EmpCndSalaryElementsEntity> getEmpCndSalaryElementsList() {
        return empCndSalaryElementsList;
    }

    public void setEmpCandidateExtraDataList(List<EmpCandidateExtraDataEntity> empCandidateExtraDataList) {
        this.empCandidateExtraDataList = empCandidateExtraDataList;
    }

    public List<EmpCandidateExtraDataEntity> getEmpCandidateExtraDataList() {
        return empCandidateExtraDataList;
    }

    public void setEmpCandidateDocumentsList(List<EmpCandidateDocumentsEntity> empCandidateDocumentsList) {
        this.empCandidateDocumentsList = empCandidateDocumentsList;
    }

    public List<EmpCandidateDocumentsEntity> getEmpCandidateDocumentsList() {
        return empCandidateDocumentsList;
    }

    public void setEmpCandidateProceduresList(List<EmpCandidateProceduresEntity> empCandidateProceduresList) {
        this.empCandidateProceduresList = empCandidateProceduresList;
    }

    public List<EmpCandidateProceduresEntity> getEmpCandidateProceduresList() {
        return empCandidateProceduresList;
    }

    public void setEmpCandidateStatusEntity(EmpCandidateStatusEntity empCandidateStatusEntity) {
        this.empCandidateStatusEntity = empCandidateStatusEntity;
    }

    public EmpCandidateStatusEntity getEmpCandidateStatusEntity() {
        return empCandidateStatusEntity;
    }

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

    public void setCitizensResidentsEntity(EmpKwtCitizensResidentsEntity citizensResidentsEntity) {
        this.citizensResidentsEntity = citizensResidentsEntity;
    }

    public EmpKwtCitizensResidentsEntity getCitizensResidentsEntity() {
        return citizensResidentsEntity;
    }

//    public void setUsersEntityApprovedBy(UsersEntity usersEntityApprovedBy) {
//        this.usersEntityApprovedBy = usersEntityApprovedBy;
//    }
//
//    public UsersEntity getUsersEntityApprovedBy() {
//        return usersEntityApprovedBy;
//    }
//
//    public void setUsersEntityAuditBy(UsersEntity usersEntityAuditBy) {
//        this.usersEntityAuditBy = usersEntityAuditBy;
//    }
//
//    public UsersEntity getUsersEntityAuditBy() {
//        return usersEntityAuditBy;
//    }

    public void setHireStagesEntity(HireStagesEntity hireStagesEntity) {
        this.hireStagesEntity = hireStagesEntity;
    }

    public HireStagesEntity getHireStagesEntity() {
        return hireStagesEntity;
    }

    public void setHireTypesEntity(HireTypesEntity hireTypesEntity) {
        this.hireTypesEntity = hireTypesEntity;
    }

    public HireTypesEntity getHireTypesEntity() {
        return hireTypesEntity;
    }

    public void setJobsEntityOtherJob(EmpJobsEntity jobsEntityOtherJob) {
        this.jobsEntityOtherJob = jobsEntityOtherJob;
    }

    public EmpJobsEntity getJobsEntityOtherJob() {
        return jobsEntityOtherJob;
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

//    public void setCandidatePersonsEntity(CandidatePersonsEntity candidatePersonsEntity) {
//        this.candidatePersonsEntity = candidatePersonsEntity;
//    }
//
//    public CandidatePersonsEntity getCandidatePersonsEntity() {
//        return candidatePersonsEntity;
//    }

    public void setTransReqDate(Date transReqDate) {
        this.transReqDate = transReqDate;
    }

    public Date getTransReqDate() {
        return transReqDate;
    }
}
