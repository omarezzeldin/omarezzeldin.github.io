package com.beshara.csc.hr.emp.business.dto;

public class EmpSearchRecodDesc extends EmpDTO {
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private Long realCivilID;
    private Long civilID;
    private String hireStatus;
    
    public EmpSearchRecodDesc() {
        super();
    }

    public void setRealCivilID(Long realCivilID) {
        this.realCivilID = realCivilID;
    }

    public Long getRealCivilID() {
        return realCivilID;
    }

    public void setCivilID(Long civilID) {
        this.civilID = civilID;
    }

    public Long getCivilID() {
        return civilID;
    }

       public void setHireStatus(String hireStatus) {
        this.hireStatus = hireStatus;
    }

    public String getHireStatus() {
        return hireStatus;
    }
}
