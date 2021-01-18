package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;

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
public class HireTypeProcedureDTO extends EmpDTO implements IHireTypeProcedureDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    //private Long hirtypeCode ;
    //private Long doctypeCode ;
    private String genderType;
    private Long nationalityType;
    private Long status;
    private boolean statusBoolean;
    private Long procOrder;
    private Long optionFlag;
    //private boolean basicStatusBoolean;
    private Long auditStatus;
    private Long tabrecSerial;
    private IBasicDTO hireTypeDTO;
    private IBasicDTO hireProceduresDTO;
    // private IGenderTypesDTO genderTypesDTO;

    /**
     * HireTypeProcedureDTO Default Constructor */
    public HireTypeProcedureDTO() {
        super();
    }

    /**
     * @param RequiredDocumentsDTO
     */
    public HireTypeProcedureDTO(HireTypeProcedureEntity hireTypeProcedureEntity) { // this.hirtypeCode = requiredDocumentsEntity.getHirtypeCode ( ) ;
        //this.doctypeCode = requiredDocumentsEntity.getDoctypeCode ( ) ;
        this.setCode(new HireTypeProcedureEntityKey(hireTypeProcedureEntity.getHireTypesEntity().getHirtypeCode(),
                                                    hireTypeProcedureEntity.getHireProcedureEntity().getHirprocedureCode()));
        if (hireTypeProcedureEntity.getGenderType() != null) {
            this.setGenderType(hireTypeProcedureEntity.getGenderType().toString());
        }
        this.nationalityType = hireTypeProcedureEntity.getNationalityType();
        this.status = hireTypeProcedureEntity.getStatus();
        this.setName(hireTypeProcedureEntity.getHireProcedureEntity().getHirprocedureName());
        this.setProcOrder(hireTypeProcedureEntity.getProcOrder());
        this.setOptionFlag(hireTypeProcedureEntity.getOptionFlag());
        if (hireTypeProcedureEntity.getStatus().equals(IEMPConstants._EMP_ACTIVE_STATUS)) {
            this.statusBoolean = true;
        }
        this.setCreatedBy(hireTypeProcedureEntity.getCreatedBy());
        this.setCreatedDate(hireTypeProcedureEntity.getCreatedDate());
        this.setLastUpdatedBy(hireTypeProcedureEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(hireTypeProcedureEntity.getLastUpdatedDate());
        this.auditStatus = hireTypeProcedureEntity.getAuditStatus();
        this.tabrecSerial = hireTypeProcedureEntity.getTabrecSerial();
        //
        if (hireTypeProcedureEntity.getHireTypesEntity() != null) {
            hireTypeDTO = EmpDTOFactory.createHireTypesDTO(hireTypeProcedureEntity.getHireTypesEntity());
        } //
        if (hireTypeProcedureEntity.getHireProcedureEntity() != null) {
            hireProceduresDTO =
                    EmpDTOFactory.createHireProceduresDTO(hireTypeProcedureEntity.getHireProcedureEntity());
        }
    } ///**
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
    public Long getNationalityType() {
        return nationalityType;
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
     * @param nationalityType
     */
    public void setNationalityType(Long nationalityType) {
        this.nationalityType = nationalityType;
    }

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

    public void setHireProceduresDTO(IBasicDTO documentTypeDTO) {
        this.hireProceduresDTO = documentTypeDTO;
    }

    public IBasicDTO getHireProceduresDTO() {
        return hireProceduresDTO;
    }

//    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
//        this.genderTypesDTO = genderTypesDTO;
//    }
//
//    public IGenderTypesDTO getGenderTypesDTO() {
//        return genderTypesDTO;
//    }

//    public String getGenderTypesKey() {
//        if (this.genderTypesDTO != null) {
//            return (String)genderTypesDTO.getCode().getKey();
//        }
//        return null;
//    }

//    public void setGenderTypesKey(String key) {
//        if (key != null && key.length() > 0) {
//            GenderTypesDTO dto = new GenderTypesDTO();
//            dto.setCode(new GenderTypesEntityKey(Long.parseLong(key)));
//            this.genderTypesDTO = dto;
//        }
//    }

    //         public void setBasicStatusBoolean(boolean basicStatusBoolean) {        if (basicStatusBoolean) {            this.status = IEMPConstant.EMP_DOCUMENT_REQUIRED;
    //        } else {            this.status = IEMPConstant.EMP_DOCUMENT_NON_REQUIRED;
    //        }        this.basicStatusBoolean = basicStatusBoolean;
    //    }    public boolean isBasicStatusBoolean() {        return basicStatusBoolean;
    //    }

    public void setProcOrder(Long procedureOrder) {
        this.procOrder = procedureOrder;
    }

    public Long getProcOrder() {
        return procOrder;
    }

    public void setOptionFlag(Long optionFlag) {
        this.optionFlag = optionFlag;
    }

    public Long getOptionFlag() {
        return optionFlag;
    }

    public void setStatusBoolean(boolean statusBoolean) {
        if (statusBoolean) {
            this.status = IEMPConstants._EMP_ACTIVE_STATUS;
        } else {
            this.status = IEMPConstants._EMP_NOT_ACTIVE_STATUS;
        }
        this.statusBoolean = statusBoolean;
    }

    public boolean isStatusBoolean() {
        return statusBoolean;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }

    public String getGenderType() {
        return genderType;
    }
}
