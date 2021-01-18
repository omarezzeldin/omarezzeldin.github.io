package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.TrxTypesEntity;

import java.sql.Timestamp;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class TrxTypesDTO extends BasicDTO implements ITrxTypesDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long trxtypeCode;
    private String trxtypeName;
    private Long createdBy;
    private Timestamp createdDate;
    private Long lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private Long auditStatus;
    private Long tabrecSerial;
    /** 
     * TrxTypesDTO Default Constructor */
    public TrxTypesDTO() {        super();
    }    /** 
     * @param trxTypesEntity 
     */
    public TrxTypesDTO(TrxTypesEntity trxTypesEntity) {        
        this.setCode(EmpEntityKeyFactory.createTrxTypesEntityKey(trxTypesEntity.getTrxtypeCode()));
        this.setName(trxTypesEntity.getTrxtypeName());
        this.trxtypeCode = trxTypesEntity.getTrxtypeCode();
        this.trxtypeName = trxTypesEntity.getTrxtypeName();
        this.setCreatedBy(trxTypesEntity.getCreatedBy());
        this.setCreatedDate(trxTypesEntity.getCreatedDate());
        this.setLastUpdatedBy(trxTypesEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(trxTypesEntity.getLastUpdatedDate());
        this.auditStatus = trxTypesEntity.getAuditStatus();
        this.tabrecSerial = trxTypesEntity.getTabrecSerial();
    }    /** 
     * @return Long 
     */
    public Long getTrxtypeCode() {        return trxtypeCode;
    }    /** 
     * @return String 
     */
    public String getTrxtypeName() {        return trxtypeName;
    }    /** 
     * @return Long 
     */
    public Long getAuditStatus() {        return auditStatus;
    }    /** 
     * @return Long 
     */
    public Long getTabrecSerial() {        return tabrecSerial;
    }    /** 
     * @param trxtypeCode 
     */
    public void setTrxtypeCode(Long trxtypeCode) {        this.trxtypeCode = trxtypeCode;
    }    /** 
     * @param trxtypeName 
     */
    public void setTrxtypeName(String trxtypeName) {        this.trxtypeName = trxtypeName;
    }    /** 
     * @param auditStatus 
     */
    public void setAuditStatus(Long auditStatus) {        this.auditStatus = auditStatus;
    }    /** 
     * @param tabrecSerial 
     */
    public void setTabrecSerial(Long tabrecSerial) {        this.tabrecSerial = tabrecSerial;
    }}
