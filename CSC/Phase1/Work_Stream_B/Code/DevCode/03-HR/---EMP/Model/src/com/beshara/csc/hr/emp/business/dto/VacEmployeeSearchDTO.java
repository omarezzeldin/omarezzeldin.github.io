package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.ClientDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;

import java.util.List;


public class VacEmployeeSearchDTO extends ClientDTO implements IVacEmployeeSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long ministryCode;
    private Long degreeCode;
    private String jobCode;
    private String civilIds;
    private String civilId;
    private String empName;
    private List<IBasicDTO> workCenterList;
    private boolean groupVac;
    private  IBgtProgramsDTO   list1 = BgtDTOFactory.createBgtProgramsDTO();
    public VacEmployeeSearchDTO() {
    }


    public String getWorkCenters() {
        
        StringBuffer workCenterCode = new StringBuffer("");
        String _workCenterCode = null;
        if(workCenterList!=null && workCenterList.size()>0){
            for(IBasicDTO wcDTO: workCenterList){
                IWorkCentersDTO _wcDTO = (IWorkCentersDTO)wcDTO;
                workCenterCode.append(((IWorkCentersEntityKey)_wcDTO.getCode()).getWrkCode()).append(",");    
            }
            _workCenterCode = workCenterCode.substring(0,workCenterCode.length()-1);
        }
        return _workCenterCode;
        
    }

    public String getWorkCenterNames() {
        
        StringBuffer workCenterName = new StringBuffer("");
        String _workCenterName ="";
        if(workCenterList!=null && workCenterList.size()>0){
            for(IBasicDTO wcDTO: workCenterList){
                IWorkCentersDTO _wcDTO = (IWorkCentersDTO)wcDTO;
                workCenterName.append(_wcDTO.getName()).append(" , ");    
            }
            _workCenterName = workCenterName.substring(0,workCenterName.length()-3);
        }
        return _workCenterName;
        
    }

    public void setMinistryCode(Long ministryCode) {
        this.ministryCode = ministryCode;
    }

    public Long getMinistryCode() {
        return ministryCode;
    }

    public void setDegreeCode(Long degreeCode) {
        this.degreeCode = degreeCode;
    }

    public Long getDegreeCode() {
        return degreeCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setWorkCenterList(List<IBasicDTO> workCenterList) {
        this.workCenterList = workCenterList;
    }

    public List<IBasicDTO> getWorkCenterList() {
        return workCenterList;
    }

   


    @Override
    public String getCivilIds() {
        return civilIds;
    }

    @Override
    public void setCivilIds(String civilIds) {
        this.civilIds = civilIds;
    }


    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getCivilId() {
        return civilId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setGroupVac(boolean groupVac) {
        this.groupVac = groupVac;
    }

    public boolean isGroupVac() {
        return groupVac;
    }

    public void setList1(IBgtProgramsDTO list1) {
        this.list1 = list1;
    }

    public IBgtProgramsDTO getList1() {
        return list1;
    }
}
