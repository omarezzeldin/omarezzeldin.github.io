package com.beshara.csc.hr.emp.integration.business.emplist;


import com.beshara.base.dto.BasicDTO;

import java.sql.Date;


public class EmpIntgDTO extends BasicDTO implements IEmpIntgDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private String minName;
    private Long realCivilId;
    private String qulName;
    private String jobName;
    private String wrkName;
    private Date hireDate;
    
    public static EmpIntgDTO getInstance(){
        return new EmpIntgDTO();
    }
    
    public void setMinName(String minName) {
        this.minName = minName;
    }

    public String getMinName() {
        return minName;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setQulName(String qulName) {
        this.qulName = qulName;
    }

    public String getQulName() {
        return qulName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setWrkName(String wrkName) {
        this.wrkName = wrkName;
    }

    public String getWrkName() {
        return wrkName;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getHireDate() {
        return hireDate;
    }
}
