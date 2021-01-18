package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.TreeDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;

import java.util.List;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class HireTypesDTO extends TreeDTO implements IHireTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    // private Long hirtypeCode ;
    //private String hirtypeName ;
    private Long auditStatus;
    private Long tabrecSerial;

    private HireTypesDTO parentHireType;

    private IMinistriesDTO ministriesDTO;
    private IConditionsDTO conditionsDTO;
    private List<IRequiredDocumentsDTO> requiredDocumentsDTOList;
    private List<IHireTypeProcedureDTO> hireTypeProcedureDTOList;
    private IJoinCondDTO jcNewCond;
    private boolean isLeaf;
    private Long decisionMust=0L;
    /**
     * HireTypesDTO Default Constructor */
    public HireTypesDTO() {
        super();
    }

    public HireTypesDTO(Long code, String name) {
        this.setCode(new HireTypesEntityKey(code));
        this.setName(name);
    }

    /**
     * @param hireTypesEntity
     */
    public HireTypesDTO(HireTypesEntity hireTypesEntity) { 
        this.setCode(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypesEntity.getHirtypeCode()));
        this.setName(hireTypesEntity.getHirtypeName());
        this.setCreatedBy(hireTypesEntity.getCreatedBy());
        this.setCreatedDate(hireTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(hireTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(hireTypesEntity.getLastUpdatedDate());
        this.auditStatus = hireTypesEntity.getAuditStatus();
        this.tabrecSerial = hireTypesEntity.getTabrecSerial();
        this.setTreeLevel(hireTypesEntity.getTreeLevel());
        this.setFirstParent(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypesEntity.getFirstParent()));
        this.setLeafFlag(hireTypesEntity.getLeafFlag());
        this.setDecisionMust(hireTypesEntity.getDecisionFlag());
        if (hireTypesEntity.getHireTypeEntity() != null) {
            HireTypesDTO parent = (HireTypesDTO)EmpDTOFactory.createHireTypesDTO(hireTypesEntity.getHireTypeEntity());
            this.setParentCode(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypesEntity.getHireTypeEntity().getHirtypeCode()));
            this.setParentObject(parent);
            this.setParentHireType(parent);
        }
    }

    /**
     * @return Long
     */
    public Long getAuditStatus() {
        return auditStatus;
    }

    /**
     * @return Long
     */
    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public void setRequiredDocumentsDTOList(List<IRequiredDocumentsDTO> requiredDocumentsDTOList) {
        this.requiredDocumentsDTOList = requiredDocumentsDTOList;
    }

    public List<IRequiredDocumentsDTO> getRequiredDocumentsDTOList() {
        return requiredDocumentsDTOList;
    }

    public void setConditionsDTO(IConditionsDTO conditionsDTO) {
        this.conditionsDTO = conditionsDTO;
    }

    public IConditionsDTO getConditionsDTO() {
        return conditionsDTO;
    }

    public void setMinistriesDTO(IMinistriesDTO ministriesDTO) {
        this.ministriesDTO = ministriesDTO;
    }

    public IMinistriesDTO getMinistriesDTO() {
        return ministriesDTO;
    }

    public void setHireTypeProcedureDTOList(List<IHireTypeProcedureDTO> hireTypeProcedureDTOList) {
        this.hireTypeProcedureDTOList = hireTypeProcedureDTOList;
    }

    public List<IHireTypeProcedureDTO> getHireTypeProcedureDTOList() {
        return hireTypeProcedureDTOList;
    }


    public void setParentHireType(HireTypesDTO parentHireType) {
        this.parentHireType = parentHireType;
    }

    public HireTypesDTO getParentHireType() {
        return parentHireType;
    }

    public String getConcatenatedName() {
        return (parentHireType == null) ? getName() : parentHireType.getName() + "/" + getName();
    }

    public void setDecisionMust(Long decisionMust) {
        this.decisionMust = decisionMust;
    }

    public Long getDecisionMust() {
        return decisionMust;
    }

    @Override
    public void setJCNewCond(IJoinCondDTO jcNewCond) {
        this.jcNewCond = jcNewCond;
    }

    @Override
    public IJoinCondDTO getJCNewCond() {
        return jcNewCond;
    }

    @Override
    public String getJCTableName() {
        return IEMPConstants.TABLE_HR_EMP_HIRE_TYPES;
    }
}
