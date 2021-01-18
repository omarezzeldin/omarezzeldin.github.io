package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;


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
public class EmpExtraDataTypesDTO extends EmpDTO implements IEmpExtraDataTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private String extdattypeName;
    private Long status;
    private Long auditStatus;
    private Long tabrecSerial;
    private Long parameterCode;


    /**
     * EmpExtraDataTypesDTO Default Constructor
     */
    public EmpExtraDataTypesDTO() {
        super();
    }

    /**
     * @param empExtraDataTypesEntity
     */
    public EmpExtraDataTypesDTO(EmpExtraDataTypesEntity empExtraDataTypesEntity) {
        this.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(empExtraDataTypesEntity.getExtdattypeCode()));
        this.extdattypeName = empExtraDataTypesEntity.getExtdattypeName();
        this.status = empExtraDataTypesEntity.getStatus();
        setCreatedBy(empExtraDataTypesEntity.getCreatedBy());
        setCreatedDate(empExtraDataTypesEntity.getCreatedDate());
        setLastUpdatedBy(empExtraDataTypesEntity.getLastUpdatedBy());
        setLastUpdatedDate(empExtraDataTypesEntity.getLastUpdatedDate());
        this.auditStatus = empExtraDataTypesEntity.getAuditStatus();
        this.tabrecSerial = empExtraDataTypesEntity.getTabrecSerial();


    }

    /**
     * @return String
     */
    public String getExtdattypeName() {
        return extdattypeName;
    }

    /**
     * @return Long
     */
    public Long getStatus() {
        return status;
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
     * @param extdattypeName
     */
    public void setExtdattypeName(String extdattypeName) {
        this.extdattypeName = extdattypeName;
    }

    /**
     * @param status
     */
    public void setStatus(Long status) {
        this.status = status;
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


    public void setParameterCode(Long parameterCode) {
        this.parameterCode = parameterCode;
    }

    public Long getParameterCode() {
        return parameterCode;
    }
}

