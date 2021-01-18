package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.hr.emp.business.entity.HireStatusEntity;
import com.beshara.csc.hr.emp.business.entity.HireStatusEntityKey;

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
public class HireStatusDTO extends EmpDTO implements IHireStatusDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

 // private Long hirstatusCode ; 
    //private String hirstatusName ; 
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * HireStatusDTO Default Constructor */
    public HireStatusDTO() {        super();
    }    /** 
     * @param hireStatusEntity 
     */
    public HireStatusDTO(HireStatusEntity hireStatusEntity) { // this.hirstatusCode = hireStatusEntity.getHirstatusCode ( ) ; 
        //this.hirstatusName = hireStatusEntity.getHirstatusName ( ) ; 
        this.setCode(new HireStatusEntityKey(hireStatusEntity.getHirstatusCode()));
        this.setName(hireStatusEntity.getHirstatusName());
        this.setCreatedBy(hireStatusEntity.getCreatedBy());
        this.setCreatedDate(hireStatusEntity.getCreatedDate());
        this.setLastUpdatedBy(hireStatusEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(hireStatusEntity.getLastUpdatedDate());
        this.auditStatus = hireStatusEntity.getAuditStatus();
        this.tabrecSerial = hireStatusEntity.getTabrecSerial();
    }    public HireStatusDTO(Long code, String name) {        this.setCode(new HireStatusEntityKey(code));
        this.setName(name);
    } ///** 
    //* @return Long 
    //*/ 
    //public Long getHirstatusCode ( ) { 
    // return hirstatusCode ; 
    // } 
    ///** 
    //* @return String 
    //*/ 
    //public String getHirstatusName ( ) { 
    // return hirstatusName ; 
    // } 
    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    } ///** 
    //* @param hirstatusCode 
    //*/ 
    //public void setHirstatusCode ( Long hirstatusCode ) { 
    // this.hirstatusCode=hirstatusCode ; 
    // } 
    ///** 
    //* @param hirstatusName 
    //*/ 
    //public void setHirstatusName ( String hirstatusName ) { 
    // this.hirstatusName=hirstatusName ; 
    // } 
    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
