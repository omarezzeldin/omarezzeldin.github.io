package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.ITreeDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;

import java.util.List;


public interface IHireTypesDTO extends ITreeDTO, IJoinCondTargetDTO  {
    public Long getAuditStatus();

    public Long getTabrecSerial();

    public void setAuditStatus(Long auditStatus);

    public void setTabrecSerial(Long tabrecSerial);

    public void setRequiredDocumentsDTOList(List<IRequiredDocumentsDTO> requiredDocumentsDTOList);

    public List<IRequiredDocumentsDTO> getRequiredDocumentsDTOList();


    public void setConditionsDTO(IConditionsDTO conditionsDTO);

    
//    public void setConditionsDTO(IConditionsDTO conditionsDTO);
//
//    public IConditionsDTO getConditionsDTO();
    
     public void setHireTypeProcedureDTOList(List<IHireTypeProcedureDTO> hireTypeProcedureDTOList);

     public List<IHireTypeProcedureDTO> getHireTypeProcedureDTOList();


    public IConditionsDTO getConditionsDTO();
    void setMinistriesDTO(IMinistriesDTO ministriesDTO);
    public IMinistriesDTO getMinistriesDTO();
    void setParentHireType(HireTypesDTO parentHireType) ;
    public HireTypesDTO getParentHireType();
    
    public void setDecisionMust(Long decisionMust) ;
    

    public Long getDecisionMust();
}
