package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.locking.dto.ILockableItem;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.sal.business.dto.ISalDegreeReasonsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;

import java.sql.Date;


public class EmpCndSalaryElementsDTO extends EmpDTO implements IEmpCndSalaryElementsDTO, ILockableItem {

    @SuppressWarnings("compatibility:-4603507196067227774")
    private static final long serialVersionUID = 1L;

    private Long cndSalelmSerial;

    private Long candidateCode;

    private Long candidateCodeSeq;

    private Long elmguideCode;

    private Float elementValue;

    private Long elementRatio;

    private Date fromDate;

    private Date untilDate;

    private Long elmguideCodeEquv;

    private String cndSalelmComment;

    private Long degreasonCode;

    private Long auditStatus;

    private Long tabrecSerial;

    private IEmpCandidatesDTO empCandidatesDTO;

    private ISalDegreeReasonsDTO salDegreeReasonsDTO;

    private ISalElementGuidesDTO salElementGuidesDTO;

    private ISalElementGuidesDTO salEqElementGuidesDTO;

    public EmpCndSalaryElementsDTO() {
        super();
    }

    public EmpCndSalaryElementsDTO(EmpCndSalaryElementsEntity empCndSalaryElementsEntity) {
        EmpEntityConverter.getEmpCndSalaryElementsDTO(empCndSalaryElementsEntity);

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

    public void setEmpCandidatesDTO(IEmpCandidatesDTO empCandidatesDTO) {
        this.empCandidatesDTO = empCandidatesDTO;
    }

    public IEmpCandidatesDTO getEmpCandidatesDTO() {
        return empCandidatesDTO;
    }

    public void setSalDegreeReasonsDTO(ISalDegreeReasonsDTO salDegreeReasonsDTO) {
        this.salDegreeReasonsDTO = salDegreeReasonsDTO;
    }

    public ISalDegreeReasonsDTO getSalDegreeReasonsDTO() {
        return salDegreeReasonsDTO;
    }

    public void setSalElementGuidesDTO(ISalElementGuidesDTO salElementGuidesDTO) {
        this.salElementGuidesDTO = salElementGuidesDTO;
    }

    public ISalElementGuidesDTO getSalElementGuidesDTO() {
        return salElementGuidesDTO;
    }

    public void setSalEqElementGuidesDTO(ISalElementGuidesDTO salEqElementGuidesDTO) {
        this.salEqElementGuidesDTO = salEqElementGuidesDTO;
    }

    public ISalElementGuidesDTO getSalEqElementGuidesDTO() {
        return salEqElementGuidesDTO;
    }

    public String getLockEntity() {
        return "HR_EMP_CND_SALARY_ELEMENTS";
    }

    public String getLockId() {
        return tabrecSerial.toString();
    }


    public void setElementValue(Float elementValue) {
        this.elementValue = elementValue;
    }

    public Float getElementValue() {
        return elementValue;
    }
}
