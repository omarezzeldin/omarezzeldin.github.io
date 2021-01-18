package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;

import java.sql.Date;

import java.util.List;


public class ChangeBudgetTypeDTO extends EmpDTO implements IChangeBudgetTypeDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private List<IEmployeesDTO> employeesDTOList;
    private Date applyDate;
    private IBgtTypesDTO bgtTypesDTO;
    public ChangeBudgetTypeDTO() {    }    public void setEmployeesDTOList(List<IEmployeesDTO> employeesDTOList) {        this.employeesDTOList = employeesDTOList;
    }    public List<IEmployeesDTO> getEmployeesDTOList() {        return employeesDTOList;
    }    public void setApplyDate(Date applyDate) {        this.applyDate = applyDate;
    }    public Date getApplyDate() {        return applyDate;
    }    public void setBgtTypesDTO(IBgtTypesDTO bgtTypesDTO) {        this.bgtTypesDTO = bgtTypesDTO;
    }    public IBgtTypesDTO getBgtTypesDTO() {        return bgtTypesDTO;
    }}
