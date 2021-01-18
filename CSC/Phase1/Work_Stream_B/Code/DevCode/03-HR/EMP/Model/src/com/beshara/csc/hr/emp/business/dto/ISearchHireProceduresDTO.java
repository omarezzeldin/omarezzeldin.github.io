package com.beshara.csc.hr.emp.business.dto;


public interface ISearchHireProceduresDTO extends IEmpDTO {
    public void setHirprocedureCode(Long hirprocedureCode);

    public Long getHirprocedureCode();

    public void setHirprocedureName(String hirprocedureName);

    public String getHirprocedureName();

    public void setMinCode(Long minCode);

    public Long getMinCode();

    public void setGenderType(Long genderType);

    public Long getGenderType();

    public void setNationalityType(Long nationalityType);

    public Long getNationalityType();

}
