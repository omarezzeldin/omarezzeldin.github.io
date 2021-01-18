package com.beshara.csc.hr.emp.business.dto.shared;


import com.beshara.csc.hr.emp.business.dto.EmpDTO;


public class SimpleEmployeesDTO extends EmpDTO implements ISimpleEmployeesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long realCivilId;
    private String empName;
    private Long empHireStatus;
    private Long ministryCode;
    private Boolean kuwaiti;
    private Long activeFlag;
    private Long capStatusCode;
    
    public SimpleEmployeesDTO() {
    }
    
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpHireStatus(Long empHireStatus) {
        this.empHireStatus = empHireStatus;
    }

    public Long getEmpHireStatus() {
        return empHireStatus;
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setKuwaiti(Boolean kuwaiti) {
        this.kuwaiti = kuwaiti;
    }

    public Boolean getKuwaiti() {
        return kuwaiti;
    }

    public void setActiveFlag(Long activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Long getActiveFlag() {
        return activeFlag;
    }

    public void setCapStatusCode(Long capStatusCode) {
        this.capStatusCode = capStatusCode;
    }

    public Long getCapStatusCode() {
        return capStatusCode;
    }
}
