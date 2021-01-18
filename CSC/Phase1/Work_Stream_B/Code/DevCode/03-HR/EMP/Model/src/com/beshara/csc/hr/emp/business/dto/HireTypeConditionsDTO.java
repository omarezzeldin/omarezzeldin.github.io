package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntityKey;

import java.sql.Date;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author A.Elmasry
 * @version 1.0
 * @since 17/02/2014
 */
public class HireTypeConditionsDTO extends EmpDTO implements IHireTypeConditionsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    //private Long hirtypeCode ;

    //private Long genderType ;
    private Long conditionCode;
    private Long status;
    private Long optionFlag;
    private Long auditStatus;
    private Long tabrecSerial;
    private Date fromDate;
    private Date untilDate;
    private IConditionsDTO conditionsDTO;
    private IBasicDTO hireTypeDTO;

    /**
     * RequiredDocumentsDTO Default Constructor */
    public HireTypeConditionsDTO() {
        super();
    }

    /**
     * @param requiredDocumentsEntity
     */
    public HireTypeConditionsDTO(HireTypeConditionsEntity hireTypeConditionsEntity) {
        this.setCode(new HireTypeConditionsEntityKey(hireTypeConditionsEntity.getConditionCode()));
          this.conditionCode = hireTypeConditionsEntity.getConditionCode();
        this.status = hireTypeConditionsEntity.getStatus();
     //   this.setOptionFlag(hireTypeConditionsEntity.getOptionFlag());
        this.setCreatedBy(hireTypeConditionsEntity.getCreatedBy());
        this.setCreatedDate(hireTypeConditionsEntity.getCreatedDate());
        this.setLastUpdatedBy(hireTypeConditionsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(hireTypeConditionsEntity.getLastUpdatedDate());
        this.auditStatus = hireTypeConditionsEntity.getAuditStatus();
        this.tabrecSerial = hireTypeConditionsEntity.getTabrecSerial();
        if (hireTypeConditionsEntity.getHireTypesEntity() != null) {
            hireTypeDTO = EmpDTOFactory.createHireTypesDTO(hireTypeConditionsEntity.getHireTypesEntity());
        }
    } //
    ///**
    //* @return Long
    //*/
    //public Long getHirtypeCode ( ) {
    // return hirtypeCode ;
    // }
    ///**
    //* @return Long
    //*/
    //public Long getDoctypeCode ( ) {
    // return doctypeCode ;
    // }
    // /**
    // * @return Long
    // */
    // public Long getGenderType ( ) {
    // return genderType ;
    // }

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
    } ///**
    //* @param hirtypeCode
    //*/
    //public void setHirtypeCode ( Long hirtypeCode ) {
    // this.hirtypeCode=hirtypeCode ;
    // }
    ///**
    //* @param doctypeCode
    //*/
    //public void setDoctypeCode ( Long doctypeCode ) {
    // this.doctypeCode=doctypeCode ;
    // }
    // /**
    // * @param genderType
    // */
    // public void setGenderType ( Long genderType ) {
    // this.genderType = genderType ;
    // }

    /**
     * @param basicStatus
     */
    public void setStatus(Long basicStatus) {
        this.status = basicStatus;
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

    public void setHireTypeDTO(IBasicDTO hireTypeDTO) {
        this.hireTypeDTO = hireTypeDTO;
    }

    public IBasicDTO getHireTypeDTO() {
        return hireTypeDTO;
    }

    //         public void setBasicStatusBoolean(boolean basicStatusBoolean) {        if (basicStatusBoolean) {            this.status = IEMPConstant.EMP_DOCUMENT_REQUIRED;
    //        } else {            this.status = IEMPConstant.EMP_DOCUMENT_NON_REQUIRED;
    //        }        this.basicStatusBoolean = basicStatusBoolean;
    //    }    public boolean isBasicStatusBoolean() {        return basicStatusBoolean;
    //    }


    public void setOptionFlag(Long optionFlag) {
        this.optionFlag = optionFlag;
    }

    public Long getOptionFlag() {
        return optionFlag;
    }

    public void setConditionCode(Long conditionCode) {
        this.conditionCode = conditionCode;
    }

    public Long getConditionCode() {
        return conditionCode;
    }

    @Override
    public void setTarget(IJoinCondTargetDTO target) {
        setHireTypeDTO((IHireTypesDTO)target);
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

    public void setConditionsDTO(IConditionsDTO conditionsDTO) {
        this.conditionsDTO = conditionsDTO;
    }

    public IConditionsDTO getConditionsDTO() {
        return conditionsDTO;
    }
}
