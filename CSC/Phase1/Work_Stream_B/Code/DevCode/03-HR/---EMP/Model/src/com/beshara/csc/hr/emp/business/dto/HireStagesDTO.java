package com.beshara.csc.hr.emp.business.dto;


import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntityKey;

import java.sql.Date;

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
public class HireStagesDTO extends EmpDTO implements IHireStagesDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    //private Long hirstageCode ;
    //private String hirstageName ;
    private Long auditStatus;
    private Long tabrecSerial;
    private Date fromDate;
    private Date untilDate;
    private Long status;

    /**
     * HireStagesDTO Default Constructor */
    public HireStagesDTO() {
        super();
    }

    /**
     * @param hireStagesEntity
     */
    public HireStagesDTO(HireStagesEntity hireStagesEntity) { //this.hirstageCode = hireStagesEntity.getHirstageCode ( ) ;
        //this.hirstageName = hireStagesEntity.getHirstageName ( ) ;
        this.setCode(new HireStagesEntityKey(hireStagesEntity.getHirstageCode()));
        this.setName(hireStagesEntity.getHirstageName());
        this.setCreatedBy(hireStagesEntity.getCreatedBy());
        this.setCreatedDate(hireStagesEntity.getCreatedDate());
        this.setLastUpdatedBy(hireStagesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(hireStagesEntity.getLastUpdatedDate());
        this.auditStatus = hireStagesEntity.getAuditStatus();
        this.tabrecSerial = hireStagesEntity.getTabrecSerial();
        this.setFromDate(hireStagesEntity.getFromDate());
        this.setUntilDate(hireStagesEntity.getUntilDate());
        this.setStatus(hireStagesEntity.getStatus());
    }

    public HireStagesDTO(Long code, String name) {
        this.setCode(new HireStagesEntityKey(code));
        this.setName(name);
    } ///**
    //* @return Long
    //*/
    //public Long getHirstageCode ( ) {
    // return hirstageCode ;
    // }
    ///**
    //* @return String
    //*/
    //public String getHirstageName ( ) {
    // return hirstageName ;
    // }

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
    } ///**
    //* @param hirstageCode
    //*/
    //public void setHirstageCode ( Long hirstageCode ) {
    // this.hirstageCode=hirstageCode ;
    // }
    ///**
    //* @param hirstageName
    //*/
    //public void setHirstageName ( String hirstageName ) {
    // this.hirstageName=hirstageName ;
    // }

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

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }
}
