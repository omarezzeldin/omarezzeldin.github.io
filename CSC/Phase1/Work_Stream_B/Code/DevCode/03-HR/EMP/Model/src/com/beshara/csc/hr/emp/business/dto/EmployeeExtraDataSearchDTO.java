package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.paging.impl.PagingRequestDTO;

public class EmployeeExtraDataSearchDTO extends EmpDTO implements IEmployeeExtraDataSearchDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private String empName;
    private Long extraDataTypeCode;
    private String value;
    private String workCenterCode;
    private PagingRequestDTO pagingRequestDTO;
    
    public EmployeeExtraDataSearchDTO() {    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setExtraDataTypeCode(Long extraDataTypeCode) {
        this.extraDataTypeCode = extraDataTypeCode;
    }

    public Long getExtraDataTypeCode() {
        return extraDataTypeCode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setWorkCenterCode(String workCenterCode) {
        this.workCenterCode = workCenterCode;
    }

    public String getWorkCenterCode() {
        return workCenterCode;
    }
    
    public void setPagingRequestDTO(PagingRequestDTO pagingRequestDTO) {
        this.pagingRequestDTO = pagingRequestDTO;
    }

    public PagingRequestDTO getPagingRequestDTO() {
        return pagingRequestDTO;
    }
}
