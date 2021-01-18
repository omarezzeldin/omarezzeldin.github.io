package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "EmpCandidateProceduresEntity.findAll",
                             query = "select o from EmpCandidateProceduresEntity o"),
                 @NamedQuery(name = "EmpCandidateProceduresEntity.getByCndCode",
                             query = "SELECT o from EmpCandidateProceduresEntity o where o.candidateCode=:candidateCode") })
@Table(name = "HR_EMP_CANDIDATE_PROCEDURES")
@IdClass(IEmpCandidateProceduresEntityKey.class)
public class EmpCandidateProceduresEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CANDIDATE_CODE", nullable = false, insertable = false, updatable = false)
    private Long candidateCode;
    @Id
    @Column(name = "CANDIDATE_CODE_SEQ", nullable = false, insertable = false, updatable = false)
    private Long candidateCodeSeq;
    @Id
    @Column(name = "HIRPROCEDURE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirProcedureCode;
    @Column(name = "PROC_DESC", length = 400)
    private String procDesc;
    @Column(name = "RESTYPE_CODE", insertable = false, updatable = false)
    private Long restypeCode;
    @Column(name = "PRORESULT_CODE", nullable = false, insertable = false, updatable = false)
    private Long proresultCode;
    @Column(name = "SEND_DATE", nullable = false)
    private Date sendDate;
    @Column(name = "RESULT_DATE")
    private Date resultDate;
    @Column(name = "POSTPONE_DATE")
    private Date postponeDate;
    @Column(name = "RESDAT_SERIAL", insertable = false, updatable = false)
    private Long resdatSerial;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Column(name = "CRMSTATUS_CODE")
    private String crmStatusCode;
    @Column(name = "CRM_LETTER_NO")
    private String crmLetterNo;
    @Column(name = "CRM_LETTER_DATE")
    private Date crmLetterDate;
    @Column(name = "CRM_SHEET_NO")
    private String crmSheetNo;


    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "RESTYPE_CODE", referencedColumnName = "RESTYPE_CODE"),
                    @JoinColumn(name = "RESDAT_SERIAL", referencedColumnName = "RESDAT_SERIAL") })
    private EmpReasonDataEntity empReasonDataEntity;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "CANDIDATE_CODE", referencedColumnName = "CANDIDATE_CODE"),
                    @JoinColumn(name = "CANDIDATE_CODE_SEQ", referencedColumnName = "CANDIDATE_CODE_SEQ") })
    private EmpCandidatesEntity empCandidatesEntity;

    @ManyToOne
    @JoinColumn(name = "PRORESULT_CODE", referencedColumnName = "PRORESULT_CODE")
    private ProcedureResultsEntity procedureResultsEntity;

    @ManyToOne
    @JoinColumn(name = "HIRPROCEDURE_CODE", referencedColumnName = "HIRPROCEDURE_CODE")
    private HireProceduresEntity hireProceduresEntity;


    public EmpCandidateProceduresEntity() {
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

    public void setHirProcedureCode(Long hirProcedureCode) {
        this.hirProcedureCode = hirProcedureCode;
    }

    public Long getHirProcedureCode() {
        return hirProcedureCode;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public void setProresultCode(Long proresultCode) {
        this.proresultCode = proresultCode;
    }

    public Long getProresultCode() {
        return proresultCode;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setPostponeDate(Date postponeDate) {
        this.postponeDate = postponeDate;
    }

    public Date getPostponeDate() {
        return postponeDate;
    }

    public void setResdatSerial(Long resdatSerial) {
        this.resdatSerial = resdatSerial;
    }

    public Long getResdatSerial() {
        return resdatSerial;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setEmpCandidatesEntity(EmpCandidatesEntity empCandidatesEntity) {
        this.empCandidatesEntity = empCandidatesEntity;
    }

    public EmpCandidatesEntity getEmpCandidatesEntity() {
        return empCandidatesEntity;
    }

    public void setEmpReasonDataEntity(EmpReasonDataEntity empReasonDataEntity) {
        this.empReasonDataEntity = empReasonDataEntity;
    }

    public EmpReasonDataEntity getEmpReasonDataEntity() {
        return empReasonDataEntity;
    }

    public void setProcedureResultsEntity(ProcedureResultsEntity procedureResultsEntity) {
        this.procedureResultsEntity = procedureResultsEntity;
    }

    public ProcedureResultsEntity getProcedureResultsEntity() {
        return procedureResultsEntity;
    }

    public void setHireProceduresEntity(HireProceduresEntity hireProceduresEntity) {
        this.hireProceduresEntity = hireProceduresEntity;
    }

    public HireProceduresEntity getHireProceduresEntity() {
        return hireProceduresEntity;
    }

    public void setCrmLetterDate(Date crmLetterDate) {
        this.crmLetterDate = crmLetterDate;
    }

    public Date getCrmLetterDate() {
        return crmLetterDate;
    }

    public void setCrmStatusCode(String crmStatusCode) {
        this.crmStatusCode = crmStatusCode;
    }

    public String getCrmStatusCode() {
        return crmStatusCode;
    }

    public void setCrmLetterNo(String crmLetterNo) {
        this.crmLetterNo = crmLetterNo;
    }

    public String getCrmLetterNo() {
        return crmLetterNo;
    }

    public void setCrmSheetNo(String crmSheetNo) {
        this.crmSheetNo = crmSheetNo;
    }

    public String getCrmSheetNo() {
        return crmSheetNo;
    }
}
