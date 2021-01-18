package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateStatusEntity;


public class EmpCandidateStatusDTO extends EmpDTO implements IEmpCandidateStatusDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long cndstatusCode;
    private String cndstatusName;
    private Long tabrecSerial;
    private Long auditStatus;

    public EmpCandidateStatusDTO() {
        super();
    }
   
    public EmpCandidateStatusDTO(EmpCandidateStatusEntity empCandidateStatusEntity) {
     EmpEntityConverter.getEmpCandidateStatusDTO(empCandidateStatusEntity);

     
    }
    public Long getCndstatusCode() {
        return cndstatusCode;
    }

    public void setCndstatusCode(Long cndstatusCode) {
        this.cndstatusCode = cndstatusCode;
    }

    public String getCndstatusName() {
        return cndstatusName;
    }

    public void setCndstatusName(String cndstatusName) {
        this.cndstatusName = cndstatusName;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }
}
