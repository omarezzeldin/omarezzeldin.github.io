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
@NamedQueries( { @NamedQuery(name = "EmpCndSalaryElementsEntity.findAll",
                             query = "select o from EmpCndSalaryElementsEntity o"),
                 @NamedQuery(name = "EmpCndSalaryElementsEntity.getByCndCode",
                             query = "SELECT o from EmpCndSalaryElementsEntity o where o.candidateCode=:candidateCode and o.candidateCodeSeq=:candidateCodeSeq"),
                 @NamedQuery(name = "EmpCndSalaryElementsEntity.findNewId",
                             query = "select MAX(o.cndSalelmSerial) from EmpCndSalaryElementsEntity o") })
@Table(name = "HR_EMP_CND_SALARY_ELEMENTS")
@IdClass(IEmpCndSalaryElementsEntityKey.class)
public class EmpCndSalaryElementsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CND_SALELM_SERIAL", nullable = false)
    private Long cndSalelmSerial;
    @Column(name = "CANDIDATE_CODE", nullable = false, insertable = false, updatable = false)
    private Long candidateCode;
    @Column(name = "CANDIDATE_CODE_SEQ", nullable = false, insertable = false, updatable = false)
    private Long candidateCodeSeq;
    @Column(name = "ELMGUIDE_CODE", insertable = true, updatable = true)
    private Long elmguideCode;
    @Column(name = "ELEMENT_VALUE")
    private Float elementValue;
    @Column(name = "ELEMENT_RATIO")
    private Long elementRatio;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE")
    private Date untilDate;
    @Column(name = "ELMGUIDE_CODE_EQUV", insertable = true, updatable = true)
    private Long elmguideCodeEquv;
    @Column(name = "CND_SALELM_COMMENT", length = 1000)
    private String cndSalelmComment;
    @Column(name = "DEGREASON_CODE", insertable = true, updatable = true)
    private Long degreasonCode;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "CANDIDATE_CODE", referencedColumnName = "CANDIDATE_CODE"),
                    @JoinColumn(name = "CANDIDATE_CODE_SEQ", referencedColumnName = "CANDIDATE_CODE_SEQ") })
    private EmpCandidatesEntity empCandidatesEntity;

//    @ManyToOne
//    @JoinColumn(name = "DEGREASON_CODE", referencedColumnName = "DEGREASON_CODE")
//    private SalDegreeReasonsEntity salDegreeReasonsEntity;

//    @ManyToOne
//    @JoinColumn(name = "ELMGUIDE_CODE", referencedColumnName = "ELMGUIDE_CODE")
//    private SalElementGuidesEntity salElementGuidesEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "ELMGUIDE_CODE_EQUV", referencedColumnName = "ELMGUIDE_CODE")
//    private SalElementGuidesEntity salEqElementGuidesEntity;

    public EmpCndSalaryElementsEntity() {
    }


    public void setCndSalelmSerial(Long cndSalelmSerial) {
        this.cndSalelmSerial = cndSalelmSerial;
    }

    public Long getCndSalelmSerial() {
        return cndSalelmSerial;
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

    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    public Long getElmguideCode() {
        return elmguideCode;
    }

   

    public void setElementRatio(Long elementRatio) {
        this.elementRatio = elementRatio;
    }

    public Long getElementRatio() {
        return elementRatio;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setElmguideCodeEquv(Long elmguideCodeEquv) {
        this.elmguideCodeEquv = elmguideCodeEquv;
    }

    public Long getElmguideCodeEquv() {
        return elmguideCodeEquv;
    }

    public void setCndSalelmComment(String cndSalelmComment) {
        this.cndSalelmComment = cndSalelmComment;
    }

    public String getCndSalelmComment() {
        return cndSalelmComment;
    }

    public void setDegreasonCode(Long degreasonCode) {
        this.degreasonCode = degreasonCode;
    }

    public Long getDegreasonCode() {
        return degreasonCode;
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

//    public void setSalDegreeReasonsEntity(SalDegreeReasonsEntity salDegreeReasonsEntity) {
//        this.salDegreeReasonsEntity = salDegreeReasonsEntity;
//    }
//
//    public SalDegreeReasonsEntity getSalDegreeReasonsEntity() {
//        return salDegreeReasonsEntity;
//    }
//
//    public void setSalElementGuidesEntity(SalElementGuidesEntity salElementGuidesEntity) {
//        this.salElementGuidesEntity = salElementGuidesEntity;
//    }
//
//    public SalElementGuidesEntity getSalElementGuidesEntity() {
//        return salElementGuidesEntity;
//    }
//
//    public void setSalEqElementGuidesEntity(SalElementGuidesEntity salEqElementGuidesEntity) {
//        this.salEqElementGuidesEntity = salEqElementGuidesEntity;
//    }
//
//    public SalElementGuidesEntity getSalEqElementGuidesEntity() {
//        return salEqElementGuidesEntity;
//    }

    public void setElementValue(Float elementValue) {
        this.elementValue = elementValue;
    }

    public Float getElementValue() {
        return elementValue;
    }
}
