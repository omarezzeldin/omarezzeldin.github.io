package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExternalExperienceEntity;

import java.sql.Timestamp;


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
public class EmpExternalExperienceDTO extends EmpDTO implements IEmpExternalExperienceDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;



    private String experienceDesc;
    private Timestamp fromDate;
    private Timestamp toDate;
    private IEmployeesDTO employeesDTO;
    private Long auditStatus;
    private Long tabrecSerial;


    /**
     * EmpExternalExperienceDTO Default Constructor
     */
    public EmpExternalExperienceDTO() {
        super();
    }

    /**
     * @param empExternalExperienceEntity
     */
    public EmpExternalExperienceDTO(EmpExternalExperienceEntity empExternalExperienceEntity) {
        this.setCode(EmpEntityKeyFactory.createEmpExternalExperienceEntityKey(empExternalExperienceEntity.getSerial()));
        this.setEmployeesDTO(new EmployeesDTO(empExternalExperienceEntity.getEmployeesEntity()));
        this.experienceDesc = empExternalExperienceEntity.getExperienceDesc();
        this.fromDate = empExternalExperienceEntity.getFromDate();
        this.toDate = empExternalExperienceEntity.getToDate();
        this.auditStatus = empExternalExperienceEntity.getAuditStatus();
        this.tabrecSerial = empExternalExperienceEntity.getTabrecSerial();
        this.setCreatedBy(empExternalExperienceEntity.getCreatedBy());
        this.setCreatedDate(empExternalExperienceEntity.getCreatedDate());
        this.setLastUpdatedBy(empExternalExperienceEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(empExternalExperienceEntity.getLastUpdatedDate());


    }


    /**
     * @return String
     */
    public String getExperienceDesc() {
        return experienceDesc;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getFromDate() {
        return fromDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getToDate() {
        return toDate;
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
     * @param experienceDesc
     */
    public void setExperienceDesc(String experienceDesc) {
        this.experienceDesc = experienceDesc;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @param toDate
     */
    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
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


    public void setEmployeesDTO(IEmployeesDTO employeesDTO) {
        this.employeesDTO = employeesDTO;
    }

    public IEmployeesDTO getEmployeesDTO() {
        return employeesDTO;
    }
}

