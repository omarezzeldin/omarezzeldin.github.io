package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;


public interface IRequiredDocumentsDTO extends IEmpDTO {

    public Long getNationalityType();

    public Long getBasicStatus();

    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setNationalityType(Long nationalityType);

    public void setBasicStatus(Long basicStatus);

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setHireTypeDTO(IBasicDTO hireTypeDTO);

    public IBasicDTO getHireTypeDTO();

    public void setDocumentTypeDTO(IInfDocumentTypesDTO documentTypeDTO);

    public IInfDocumentTypesDTO getDocumentTypeDTO();

//    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO);
//
//    public IGenderTypesDTO getGenderTypesDTO();
//
//    public String getGenderTypesKey();
//
//    public void setGenderTypesKey(String key);

    public void setBasicStatusBoolean(boolean basicStatusBoolean);

    public boolean isBasicStatusBoolean();
    
    public void setStatus(Long status) ;

    public Long getStatus() ;

    public void setAttachmentRequired(Long attachmentRequired);
    
    public Long getAttachmentRequired();
    
    public void setGenderType(String genderType) ;

    public String getGenderType() ;
    
    public void setAttachmentRequiredBoolean(boolean attachmentRequiredBoolean);

    public boolean isAttachmentRequiredBoolean();

}
