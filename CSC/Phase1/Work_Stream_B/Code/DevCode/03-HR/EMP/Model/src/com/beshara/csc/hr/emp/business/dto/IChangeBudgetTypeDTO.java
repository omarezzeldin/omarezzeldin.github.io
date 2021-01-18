package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;

import java.sql.Date;

import java.util.List;


public interface IChangeBudgetTypeDTO extends IEmpDTO {
    public void setEmployeesDTOList(List<IEmployeesDTO> employeesDTOList);

    public List<IEmployeesDTO> getEmployeesDTOList();

    public void setApplyDate(Date applyDate);

    public Date getApplyDate();

    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO);

    public IBgtTypesDTO getBgtTypesDTO();

}
