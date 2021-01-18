package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntityKey;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;

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
public class RequiredDocumentsDTO extends EmpDTO implements IRequiredDocumentsDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    //private Long hirtypeCode ;
    //private Long doctypeCode ;
    //private Long genderType ;
    private Long nationalityType;
    private Long basicStatus;
    private boolean basicStatusBoolean;
    private Long status;
    private boolean statusBoolean;
    private Long auditStatus;
    private Long attachmentRequired;
    private boolean attachmentRequiredBoolean;
    private Long tabrecSerial;
    private IBasicDTO hireTypeDTO;
  //  private IBasicDTO documentTypeDTO;
  private IInfDocumentTypesDTO documentTypeDTO;
 //   private IGenderTypesDTO genderTypesDTO;
  
  private String genderType ;

    /**
     * RequiredDocumentsDTO Default Constructor */
    public RequiredDocumentsDTO() {
        super();
    }

    /**
     * @param requiredDocumentsEntity
     */
    public RequiredDocumentsDTO(RequiredDocumentsEntity requiredDocumentsEntity) { // this.hirtypeCode = requiredDocumentsEntity.getHirtypeCode ( ) ;
        //this.doctypeCode = requiredDocumentsEntity.getDoctypeCode ( ) ;
        this.setCode(new RequiredDocumentsEntityKey(requiredDocumentsEntity.getHireTypesEntity().getHirtypeCode(),
                                                    requiredDocumentsEntity.getDoctypeCode()));
//        if (requiredDocumentsEntity.getGenderTypesEntity() != null) {
//            this.setGenderTypesDTO(InfDTOFactory.createGenderTypesDTO(requiredDocumentsEntity.getGenderTypesEntity()));
//        }
        
        if(requiredDocumentsEntity.getGenderType() != null){
            this.setGenderType(requiredDocumentsEntity.getGenderType().toString());
        }
        this.nationalityType = requiredDocumentsEntity.getNationalityType();
        this.basicStatus = requiredDocumentsEntity.getBasicStatus();
        this.setStatus(requiredDocumentsEntity.getStatus());
        if (requiredDocumentsEntity.getBasicStatus().equals(IEMPConstant.EMP_DOCUMENT_REQUIRED)) {
            this.basicStatusBoolean = true;
        }
        if(requiredDocumentsEntity.getAttachmentRequired().equals(IEMPConstant.EMP_DOCUMENT_REQUIRED)){
            this.attachmentRequiredBoolean = true;
        }
        if(requiredDocumentsEntity.getStatus().equals(IEMPConstants._EMP_ACTIVE_STATUS)){
            this.statusBoolean = true;
        }
        this.setCreatedBy(requiredDocumentsEntity.getCreatedBy());
        
        this.setAttachmentRequired(requiredDocumentsEntity.getAttachmentRequired());
        this.setCreatedDate(requiredDocumentsEntity.getCreatedDate());
        this.setLastUpdatedBy(requiredDocumentsEntity.getLastUpdatedBy());
        this.setLastUpdatedDate(requiredDocumentsEntity.getLastUpdatedDate());
        this.auditStatus = requiredDocumentsEntity.getAuditStatus();
        this.tabrecSerial = requiredDocumentsEntity.getTabrecSerial();
        //
        if (requiredDocumentsEntity.getHireTypesEntity() != null) {
            hireTypeDTO = EmpDTOFactory.createHireTypesDTO(requiredDocumentsEntity.getHireTypesEntity());
        } //
       /* if (requiredDocumentsEntity.getDocumentTypesEntity() != null) {
            documentTypeDTO = InfDTOFactory.createInfDocumentTypesDTO(requiredDocumentsEntity.getDocumentTypesEntity());
        }*/
        
       if (requiredDocumentsEntity.getDoctypeCode() != null) {
            IInfDocumentTypesEntityKey ek=InfEntityKeyFactory.createInfDocumentTypesEntityKey(requiredDocumentsEntity.getDoctypeCode());
            try{
                   documentTypeDTO = (IInfDocumentTypesDTO)InfClientFactory.getInfDocumentTypesClient().getById(ek);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
               }
    } 

    /**
     * @return Long
     */
    public Long getNationalityType() {
        return nationalityType;
    }

    /**
     * @return Long
     */
    public Long getBasicStatus() {
        return basicStatus;
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
     * @param nationalityType
     */
    public void setNationalityType(Long nationalityType) {
        this.nationalityType = nationalityType;
    }

    /**
     * @param basicStatus
     */
    public void setBasicStatus(Long basicStatus) {
        this.basicStatus = basicStatus;
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

    public void setDocumentTypeDTO(IInfDocumentTypesDTO documentTypeDTO) {
        this.documentTypeDTO = documentTypeDTO;
    }

    public IInfDocumentTypesDTO getDocumentTypeDTO() {
        return documentTypeDTO;
    }

//    public void setGenderTypesDTO(IGenderTypesDTO genderTypesDTO) {
//        this.genderTypesDTO = genderTypesDTO;
//    }

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

    public void setBasicStatusBoolean(boolean basicStatusBoolean) {
        if (basicStatusBoolean) {
            this.basicStatus = IEMPConstant.EMP_DOCUMENT_REQUIRED;
        } else {
            this.basicStatus = IEMPConstant.EMP_DOCUMENT_NON_REQUIRED;
        }
        this.basicStatusBoolean = basicStatusBoolean;
    }

    public boolean isBasicStatusBoolean() {
        return basicStatusBoolean;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setAttachmentRequired(Long attachmentRequired) {
        this.attachmentRequired = attachmentRequired;
    }

    public Long getAttachmentRequired() {
        return attachmentRequired;
    }

    public void setAttachmentRequiredBoolean(boolean attachmentRequiredBoolean) {
        if(attachmentRequiredBoolean){
            this.attachmentRequired = IEMPConstant.EMP_DOCUMENT_REQUIRED ;
        }else{
            this.attachmentRequired = IEMPConstant.EMP_DOCUMENT_NON_REQUIRED ;
        }
        this.attachmentRequiredBoolean = attachmentRequiredBoolean;
    }

    public boolean isAttachmentRequiredBoolean() {
        return attachmentRequiredBoolean;
    }

    public void setStatusBoolean(boolean statusBoolean) {
        if(statusBoolean){
            this.status = IEMPConstants._EMP_ACTIVE_STATUS ;  
        }else{
            this.status = IEMPConstants._EMP_NOT_ACTIVE_STATUS ;
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
