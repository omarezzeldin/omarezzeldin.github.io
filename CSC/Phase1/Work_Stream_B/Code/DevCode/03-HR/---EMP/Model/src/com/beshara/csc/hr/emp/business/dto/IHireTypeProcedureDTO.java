package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.IBasicDTO;


public interface IHireTypeProcedureDTO extends IEmpDTO {

    public Long getNationalityType();

    public Long getStatus();
    
    public Long getProcOrder();
    
    public Long getOptionFlag();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setNationalityType(Long nationalityType);

    public void setStatus(Long basicStatus);
    
    public void setProcOrder(Long procOrder);
    
    public void setOptionFlag(Long OptionFlag);
    
    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setHireTypeDTO(IBasicDTO hireTypeDTO);

    public IBasicDTO getHireTypeDTO();

    public void setHireProceduresDTO(IBasicDTO hireProceduresDTO);

    public IBasicDTO getHireProceduresDTO();
    
    public void setGenderType(String genderType) ;

    public String getGenderType() ;


 //   public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);

 //   public IGenderTypesDTO getGenderTypesDTO();

  // public String getGenderTypesKey();

  //  public void setGenderTypesKey(String key);

//    public void setBasicStatusBoolean(boolean basicStatusBoolean);
//
//    public boolean isBasicStatusBoolean();
    
    
  
}
