package com.beshara.csc.hr.emp.business.dto;
public class SearchHireProceduresDTO extends EmpDTO implements ISearchHireProceduresDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirprocedureCode;
    private String hirprocedureName;
    private Long minCode;
    private Long genderType;
    private Long nationalityType;
    public SearchHireProceduresDTO() {    }    public void setHirprocedureCode(Long hirprocedureCode) {        this.hirprocedureCode = hirprocedureCode;
    }    public Long getHirprocedureCode() {        return hirprocedureCode;
    }    public void setHirprocedureName(String hirprocedureName) {        this.hirprocedureName = hirprocedureName;
    }    public String getHirprocedureName() {        return hirprocedureName;
    }    public void setMinCode(Long minCode) {        this.minCode = minCode;
    }    public Long getMinCode() {        return minCode;
    }    public void setGenderType(Long genderType) {        this.genderType = genderType;
    }    public Long getGenderType() {        return genderType;
    }    public void setNationalityType(Long nationalityType) {        this.nationalityType = nationalityType;
    }    public Long getNationalityType() {        return nationalityType;
    }}