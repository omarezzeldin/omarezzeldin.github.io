package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpReasonDataEntity;


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
public class EmpReasonDataDTO extends EmpDTO implements IEmpReasonDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long auditStatus;
    private Long tabrecSerial;
    private IBasicDTO empReasonTypesDTO;
    private Long empReasonTypesCode;

    /**
     * EmpReasonDataDTO Default Constructor
     */
    public EmpReasonDataDTO() {
        super();
    }

    /**
     * @param empReasonDataEntity
     */
    public EmpReasonDataDTO(EmpReasonDataEntity empReasonDataEntity) {
        this.setCode(EmpEntityKeyFactory.createEmpReasonDataEntityKey(empReasonDataEntity.getRestypeCode(), 
                                                                      empReasonDataEntity.getResdatSerial()));
        this.setName(empReasonDataEntity.getResdatDesc());
        this.setCreatedBy(empReasonDataEntity.getCreatedBy());
        this.setCreatedDate(empReasonDataEntity.getCreatedDate());
        this.setLastUpdatedBy(empReasonDataEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(empReasonDataEntity.getLastUpdatedDate());
        this.auditStatus = empReasonDataEntity.getAuditStatus();
        this.tabrecSerial = empReasonDataEntity.getTabrecSerial();
        
        if (empReasonDataEntity.getEmpReasonTypesEntity() != null) {
            empReasonTypesDTO = 
                    EmpDTOFactory.createEmpReasonTypesDTO(empReasonDataEntity.getEmpReasonTypesEntity());
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

    public void setEmpReasonTypesDTO(IBasicDTO empReasonTypesDTO) {
        this.empReasonTypesDTO = empReasonTypesDTO;
    }

    public IBasicDTO getEmpReasonTypesDTO() {
        return empReasonTypesDTO;
    }

    public void setEmpReasonTypesCode(Long empReasonTypesCode) {
        this.empReasonTypesCode = empReasonTypesCode;
    }

    public Long getEmpReasonTypesCode() {
        return empReasonTypesCode;
    }
}
