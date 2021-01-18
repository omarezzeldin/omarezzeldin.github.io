package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpReasonTypesEntity;


/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers .
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha Fitiany    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */
public class EmpReasonTypesDTO extends EmpDTO implements IEmpReasonTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long auditStatus;
    private Long tabrecSerial;


    /**
     * EmpReasonTypesDTO Default Constructor
     */
    public EmpReasonTypesDTO() {
        super();
    }

    /**
     * @param empReasonTypesEntity
     */
    public EmpReasonTypesDTO(EmpReasonTypesEntity empReasonTypesEntity) {
        this.setCode(EmpEntityKeyFactory.createEmpReasonTypesEntityKey(empReasonTypesEntity.getRestypeCode()));
        this.setName(empReasonTypesEntity.getRestypeName());
        this.setCreatedBy(empReasonTypesEntity.getCreatedBy());
        this.setCreatedDate(empReasonTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(empReasonTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(empReasonTypesEntity.getLastUpdatedDate());
        this.auditStatus = empReasonTypesEntity.getAuditStatus();
        this.tabrecSerial = empReasonTypesEntity.getTabrecSerial();
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

    /**
     * @param auditStatus
     */
    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @param tabrecSerial
     */
    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }
}
