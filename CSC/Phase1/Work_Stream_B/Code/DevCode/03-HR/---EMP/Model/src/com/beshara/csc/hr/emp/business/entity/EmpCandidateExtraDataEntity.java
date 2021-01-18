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
@NamedQueries( { @NamedQuery(name = "EmpCandidateExtraDataEntity.findAll",
                             query = "select o from EmpCandidateExtraDataEntity o"), 
        @NamedQuery(name = "EmpCandidateExtraDataEntity.findNewId", query = "select MAX(o.serial) from EmpCandidateExtraDataEntity o"),
                  @NamedQuery(name = "EmpCandidateExtraDataEntity.getAllEmployeeExtraDataByCandCodeAndCandSeq", query = "select o from EmpCandidateExtraDataEntity o where o.candidateCode = :candidateCode and o.candidateCodeSeq = :candidateCodeSeq" ),
                 @NamedQuery(name = "EmpCandidateExtraDataEntity.getExtraDataByCandCodeAndCandSeqAndDatType", query = "select o from EmpCandidateExtraDataEntity o where o.candidateCode = :candidateCode and o.candidateCodeSeq = :candidateCodeSeq and o.extDatTypeCode = :extDatTypeCode " )
                 })
@Table(name = "HR_EMP_CANDIDATE_EXTRA_DATA")
@IdClass(IEmpCandidateExtraDataEntityKey.class)
public class EmpCandidateExtraDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "CANDIDATE_CODE", nullable = false, insertable = false, updatable = false)
    private Long candidateCode;
    @Column(name = "CANDIDATE_CODE_SEQ", nullable = false, insertable = false, updatable = false)
    private Long candidateCodeSeq;
    @Column(name = "EXTDATTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long extDatTypeCode;
    @Column(name = "VALUE", nullable = false, length = 4000)
    private String value;
    @Column(name = "VALUE_DESC", nullable = true, length = 4000)
    private String valueDesc;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;
    @Column(name = "STATUS",nullable = false)
    private Long status;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "CANDIDATE_CODE", referencedColumnName = "CANDIDATE_CODE"),
                    @JoinColumn(name = "CANDIDATE_CODE_SEQ", referencedColumnName = "CANDIDATE_CODE_SEQ") })
    private EmpCandidatesEntity empCandidatesEntity;
    
    @ManyToOne
    @JoinColumn(name = "EXTDATTYPE_CODE", referencedColumnName = "EXTDATTYPE_CODE")
    private EmpExtraDataTypesEntity empExtraDataTypesEntity;

    public EmpCandidateExtraDataEntity() {
    }


    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getExtDatTypeCode() {
        return extDatTypeCode;
    }

    public void setExtDatTypeCode(Long extdattypeCode) {
        this.extDatTypeCode = extdattypeCode;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public EmpCandidatesEntity getEmpCandidatesEntity() {
        return empCandidatesEntity;
    }

    public void setEmpCandidatesEntity(EmpCandidatesEntity hrEmpCandidates5) {
        this.empCandidatesEntity = hrEmpCandidates5;
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

    public void setEmpExtraDataTypesEntity(EmpExtraDataTypesEntity empExtraDataTypesEntity) {
        this.empExtraDataTypesEntity = empExtraDataTypesEntity;
    }

    public EmpExtraDataTypesEntity getEmpExtraDataTypesEntity() {
        return empExtraDataTypesEntity;
    }
}
