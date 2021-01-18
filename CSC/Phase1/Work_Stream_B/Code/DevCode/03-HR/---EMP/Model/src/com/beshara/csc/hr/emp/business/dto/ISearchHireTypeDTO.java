package com.beshara.csc.hr.emp.business.dto;


public interface ISearchHireTypeDTO extends IEmpDTO {
    public void setHireTypeCode(Long hireTypeCode);

    public Long getHireTypeCode();

    public void setHireTypeName(String hireTypeName);

    public String getHireTypeName();

    public void setDocTypeName(String docTypeName);

    public String getDocTypeName();

    public void setDocTypeCode(Long docTypeCode);

    public Long getDocTypeCode();

}
