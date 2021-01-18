package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.hr.emp.business.entity.AssignReasonsEntity;
import com.beshara.csc.hr.emp.business.entity.AssignReasonsEntityKey;

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
public class AssignReasonsDTO extends EmpDTO implements IAssignReasonsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * AssignReasonsDTO Default Constructor */
    public AssignReasonsDTO() {        super();
    }    public AssignReasonsDTO(Long code, String name) {        setCode(new AssignReasonsEntityKey(code));
        setName(name);
    }    /** 
     * @param assignReasonsEntity 
     */
    public AssignReasonsDTO(AssignReasonsEntity assignReasonsEntity) {        setCode(new AssignReasonsEntityKey(assignReasonsEntity.getAssreasonCode()));
        setName(assignReasonsEntity.getAssreasonName());
        this.setCreatedBy(assignReasonsEntity.getCreatedBy());
        this.setCreatedDate(assignReasonsEntity.getCreatedDate());
        this.setLastUpdatedBy(assignReasonsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(assignReasonsEntity.getLastUpdatedDate());
        this.auditStatus = assignReasonsEntity.getAuditStatus();
        this.tabrecSerial = assignReasonsEntity.getTabrecSerial();
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
    }}
