package com.beshara.csc.hr.emp.business.dto;

import com.beshara.csc.hr.emp.business.entity.ProcedureResultsEntity;
import com.beshara.csc.hr.emp.business.entity.ProcedureResultsEntityKey;

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
public class ProcedureResultsDTO extends EmpDTO implements IProcedureResultsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

 // private Long proresultCode ; 
    //private String proresultName ; 
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * ProcedureResultsDTO Default Constructor */
    public ProcedureResultsDTO() {        super();
    }    /** 
     * @param procedureResultsEntity 
     */
    public ProcedureResultsDTO(ProcedureResultsEntity procedureResultsEntity) { // this.proresultCode = procedureResultsEntity.getProresultCode ( ) ; 
        //this.proresultName = procedureResultsEntity.getProresultName ( ) ; 
        this.setCode(new ProcedureResultsEntityKey(procedureResultsEntity.getProresultCode()));
        this.setName(procedureResultsEntity.getProresultName());
        this.setCreatedBy(procedureResultsEntity.getCreatedBy());
        this.setCreatedDate(procedureResultsEntity.getCreatedDate());
        this.setLastUpdatedBy(procedureResultsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(procedureResultsEntity.getLastUpdatedDate());
        this.auditStatus = procedureResultsEntity.getAuditStatus();
        this.tabrecSerial = procedureResultsEntity.getTabrecSerial();
    } ///** 
    //* @return Long 
    //*/ 
    //public Long getProresultCode ( ) { 
    // return proresultCode ; 
    // } 
    ///** 
    //* @return String 
    //*/ 
    //public String getProresultName ( ) { 
    // return proresultName ; 
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
    //* @param proresultCode 
    //*/ 
    //public void setProresultCode ( Long proresultCode ) { 
    // this.proresultCode=proresultCode ; 
    // } 
    ///** 
    //* @param proresultName 
    //*/ 
    //public void setProresultName ( String proresultName ) { 
    // this.proresultName=proresultName ; 
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
