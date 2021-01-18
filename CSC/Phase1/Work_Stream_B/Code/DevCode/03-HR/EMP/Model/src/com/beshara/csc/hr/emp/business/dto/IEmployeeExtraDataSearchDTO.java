package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.paging.impl.PagingRequestDTO;

public interface IEmployeeExtraDataSearchDTO extends IEmpDTO {

    public void setCivilId(Long civilId);

    public Long getCivilId();

    public void setEmpName(String empName);

    public String getEmpName();
    public void setPagingRequestDTO(PagingRequestDTO pagingRequestDTO);

    public PagingRequestDTO getPagingRequestDTO();

    public void setExtraDataTypeCode(Long extraDataTypeCode);

    public Long getExtraDataTypeCode();

    public void setValue(String value);

    public String getValue();
    
    public void setWorkCenterCode(String workCenterCode);

    public String getWorkCenterCode();
}
