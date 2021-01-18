package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.IClientDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;

import java.util.List;


public interface IVacEmployeeSearchDTO extends IClientDTO {

    String getWorkCenters();

    String getWorkCenterNames();

    String getCivilIds();

    void setCivilIds(String civilIds);

   

    void setMinistryCode(Long ministryCode);

    Long getMinistryCode();

    void setDegreeCode(Long degreeCode);

    Long getDegreeCode();

    void setJobCode(String jobCode);

    String getJobCode();

    void setWorkCenterList(List<IBasicDTO> workCenterList);

    List<IBasicDTO> getWorkCenterList();
    
    public void setCivilId(String civilId);

    public String getCivilId() ;

    public void setEmpName(String empName) ;

    public String getEmpName() ;
    
    public void setGroupVac(boolean groupVac);

    public boolean isGroupVac();
    
    public void setList1(IBgtProgramsDTO list1);

    public IBgtProgramsDTO getList1();
}
