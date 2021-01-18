package com.beshara.csc.hr.emp.integration.business.emplist;


import com.beshara.base.dto.IBasicDTO;

import java.sql.Date;

public interface IEmpIntgDTO extends IBasicDTO{
    public void setMinName(String minName) ;

    public String getMinName() ;

    public void setRealCivilId(Long realCivilId) ;

    public Long getRealCivilId() ;

    public void setQulName(String qulName) ;

    public String getQulName() ;

    public void setJobName(String jobName) ;

    public String getJobName() ;

    public void setWrkName(String wrkName) ;

    public String getWrkName() ;

    public void setHireDate(Date hireDate) ;

    public Date getHireDate() ;
}
