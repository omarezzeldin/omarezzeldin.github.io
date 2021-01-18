package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;

import java.sql.Date;


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
public class EmployeeExtraDataDTO extends EmpDTO implements IEmployeeExtraDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Date fromDate;
    private Date untilDate;
    private String value;
    private Long auditStatus;
    private Long tabrecSerial;
    private IBasicDTO employeesDTO;
    private IBasicDTO empExtraDataTypesDTO;
    private String valueDesc;
    private Long status;
    private Long minCode;
    private Long realCivilId;
    private String cscBookNo;
    private Date cscBookDate;
    private String comments;

    /**
     * EmployeeExtraDataDTO Default Constructor
     */
    public EmployeeExtraDataDTO() {
        super();
    }

    /**
     * @param employeeExtraDataEntity
     */
    public EmployeeExtraDataDTO(EmployeeExtraDataEntity employeeExtraDataEntity) {
        this.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(employeeExtraDataEntity.getSerial()));
        //        this.civilId = employeeExtraDataEntity.getCivilId();
        //        this.extraDataTypeCode = employeeExtraDataEntity.getExtraDataTypeCode();
        this.fromDate = employeeExtraDataEntity.getFromDate();
        this.untilDate = employeeExtraDataEntity.getUntilDate();
        this.value = employeeExtraDataEntity.getValue();
        setCreatedBy(employeeExtraDataEntity.getCreatedBy());
        setCreatedDate(employeeExtraDataEntity.getCreatedDate());
        setLastUpdatedBy(employeeExtraDataEntity.getLastUpdatedBy());
        setLastUpdatedDate(employeeExtraDataEntity.getLastUpdatedDate());
        this.auditStatus = employeeExtraDataEntity.getAuditStatus();
        this.tabrecSerial = employeeExtraDataEntity.getTabrecSerial();

        if (employeeExtraDataEntity.getEmployeesEntity() != null) {
            employeesDTO = 
                    EmpDTOFactory.createEmployeesDTO(employeeExtraDataEntity.getEmployeesEntity());
        }
        if (employeeExtraDataEntity.getEmpExtraDataTypesEntity() != null) {
            empExtraDataTypesDTO = 
                    EmpDTOFactory.createEmpExtraDataTypesDTO(employeeExtraDataEntity.getEmpExtraDataTypesEntity());
        }
        this.status = employeeExtraDataEntity.getStatus();
        this.valueDesc = employeeExtraDataEntity.getValueDesc();
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setEmployeesDTO(IBasicDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IBasicDTO getEmployeesDTO() {
        return employeesDTO;
    }

    public void setEmpExtraDataTypesDTO(IBasicDTO empExtraDataTypesDTO) {
        this.empExtraDataTypesDTO = empExtraDataTypesDTO;
    }

    public IBasicDTO getEmpExtraDataTypesDTO() {
        return empExtraDataTypesDTO;
    }


    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setCscBookNo(String cscBookNo) {
        this.cscBookNo = cscBookNo;
    }

    public String getCscBookNo() {
        return cscBookNo;
    }

    public void setCscBookDate(Date cscBookDate) {
        this.cscBookDate = cscBookDate;
    }

    public Date getCscBookDate() {
        return cscBookDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }
}

