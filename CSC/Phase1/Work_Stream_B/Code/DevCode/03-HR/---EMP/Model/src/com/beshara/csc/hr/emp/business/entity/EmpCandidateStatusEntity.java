package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "EmpCandidateStatusEntity.findAll",
                             query = "select o from EmpCandidateStatusEntity o") })
@Table(name = "HR_EMP_CANDIDATE_STATUS")
@IdClass(IEmpCandidateStatusEntityKey.class)
public class EmpCandidateStatusEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CNDSTATUS_CODE", nullable = false)
    private Long cndstatusCode;
    @Column(name = "CNDSTATUS_NAME", nullable = false, length = 400)
    private String cndstatusName;
    @Column(name = "TABREC_SERIAL")
    private Long tabrecSerial;
    @Column(name = "AUDIT_STATUS")
    private Long auditStatus;
    

    public EmpCandidateStatusEntity() {
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
