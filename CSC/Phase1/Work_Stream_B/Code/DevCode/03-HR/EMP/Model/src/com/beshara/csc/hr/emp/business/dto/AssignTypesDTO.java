package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.ConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntity;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntityKey;

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
public class AssignTypesDTO extends EmpDTO implements IAssignTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private ConditionsDTO conditionsDTO;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * AssignTypesDTO Default Constructor */
    public AssignTypesDTO() {        super();
    }    public AssignTypesDTO(Long code, String name) {        setCode(new AssignTypesEntityKey(code));
        setName(name);
    }    /** 
     * @param assignTypesEntity 
     */
    public AssignTypesDTO(AssignTypesEntity assignTypesEntity) {        setCode(new AssignTypesEntityKey(assignTypesEntity.getAsstypeCode()));
        setName(assignTypesEntity.getAsstypeName());
        if (assignTypesEntity.getConditionCode() != null)  {
            IConditionsEntityKey cEk=GrsEntityKeyFactory.createConditionsEntityKey(assignTypesEntity.getConditionCode());
            //this.conditionsDTO =     new ConditionsDTO(assignTypesEntity.getConditionsEntity());
            try {
                this.conditionsDTO = (ConditionsDTO)GrsClientFactory.getConditionsClient().getById(cEk);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } 
        }
        this.setCreatedBy(assignTypesEntity.getCreatedBy());
        this.setCreatedDate(assignTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(assignTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(assignTypesEntity.getLastUpdatedDate());
        this.auditStatus = assignTypesEntity.getAuditStatus();
        this.tabrecSerial = assignTypesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }    public void setConditionsDTO(ConditionsDTO conditionsDTO) {        this.conditionsDTO = conditionsDTO;
    }    public ConditionsDTO getConditionsDTO() {        return conditionsDTO;
    }}
