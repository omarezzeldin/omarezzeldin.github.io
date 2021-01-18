package com.beshara.csc.hr.emp.business.dto;
public class SearchHireTypeDTO extends EmpDTO implements ISearchHireTypeDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hireTypeCode;
    private String hireTypeName;
    private String docTypeName;
    private Long docTypeCode;
    public SearchHireTypeDTO() {    }    public void setHireTypeCode(Long hireTypeCode) {        this.hireTypeCode = hireTypeCode;
    }    public Long getHireTypeCode() {        return hireTypeCode;
    }    public void setHireTypeName(String hireTypeName) {        this.hireTypeName = hireTypeName;
    }    public String getHireTypeName() {        return hireTypeName;
    }    public void setDocTypeName(String docTypeName) {        this.docTypeName = docTypeName;
    }    public String getDocTypeName() {        return docTypeName;
    }    public void setDocTypeCode(Long docTypeCode) {        this.docTypeCode = docTypeCode;
    }    public Long getDocTypeCode() {        return docTypeCode;
    }}