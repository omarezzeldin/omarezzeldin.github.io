package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;

import java.sql.Date;

public class EmpCandidateExtraDataDTO extends EmpDTO implements IEmpCandidateExtraDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private Long candidateCode;
    private Long candidateCodeSeq;
    private Long extDatTypeCode;
    private String value;
    private String valueDesc;
    private Date fromDate;
    private Date untilDate;
    private Long status;
    private Long auditStatus;
    private Long tabrecSerial;
    private IEmpCandidatesDTO empCandidatesDTO;
    private IEmpExtraDataTypesDTO empExtraDataTypesDTO;

    public EmpCandidateExtraDataDTO() {
        super();
    }
    
    public EmpCandidateExtraDataDTO(EmpCandidateExtraDataEntity empCandidateExtraDataEntity) {
        EmpEntityConverter.getEmpCandidateExtraDataDTO(empCandidateExtraDataEntity);
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

    public void setExtDatTypeCode(Long extDatTypeCode) {
        this.extDatTypeCode = extDatTypeCode;
    }

    public Long getExtDatTypeCode() {
        return extDatTypeCode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public String getValueDesc() {
        return valueDesc;
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

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
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

    public void setEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO empExtraDataTypesDTO) {
        this.empExtraDataTypesDTO = empExtraDataTypesDTO;
    }

    public IEmpExtraDataTypesDTO getEmpExtraDataTypesDTO() {
        return empExtraDataTypesDTO;
    }
}
