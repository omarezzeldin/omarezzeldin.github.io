package com.beshara.csc.hr.emp.business.dto.shared;


import com.beshara.csc.hr.emp.business.dto.IEmpDTO;


public interface ISimpleEmployeesDTO extends IEmpDTO {
    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setRealCivilId(Long realCivilId);

    public Long getRealCivilId();

    public void setEmpName(String empName);

    public String getEmpName();

    public void setEmpHireStatus(Long empHireStatus);

    public Long getEmpHireStatus();

    public void setMinistryCode(Long ministryCode);

    public Long getMinistryCode();

    public void setKuwaiti(Boolean kuwaiti);

    public Boolean getKuwaiti();
    
    public void setActiveFlag(Long activeFlag);

    public Long getActiveFlag();
    
    public void setCapStatusCode(Long capStatusCode);

    public Long getCapStatusCode();
}
