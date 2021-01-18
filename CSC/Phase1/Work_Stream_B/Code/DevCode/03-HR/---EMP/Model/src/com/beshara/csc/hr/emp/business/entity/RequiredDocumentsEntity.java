package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.entity.inf.EmpInfDocumentTypesEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of RequiredDocuments Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 * 
 */
@Entity
@NamedQueries( { @NamedQuery(name = "RequiredDocumentsEntity.findAll", query = "select o from RequiredDocumentsEntity o"),
        @NamedQuery(name = "RequiredDocumentsEntity.findNewId", query = "select MAX(o.hirtypeCode) from RequiredDocumentsEntity o"),
        @NamedQuery(name = "RequiredDocumentsEntity.listRelatedRequiredDocuments", query = "select o from RequiredDocumentsEntity o where o.hireTypesEntity.hirtypeCode =:hireTypeCode"),
        @NamedQuery(name = "RequiredDocumentsEntity.getAvailableDocuments", query = "select o from  EmpInfDocumentTypesEntity o WHERE o.doctypeCode NOT IN (SELECT rd.doctypeCode FROM RequiredDocumentsEntity rd WHERE rd.hireTypesEntity.hirtypeCode=:hireTypeCode )"),
                 @NamedQuery(name = "RequiredDocumentsEntity.getAvailableDocumentsWithOutCode", query = "select o from  EmpInfDocumentTypesEntity o "),
        @NamedQuery(name = "RequiredDocumentsEntity.hireTypeDocuments", query = "select o from EmpInfDocumentTypesEntity o  where o.doctypeCode IN (SELECT rd.doctypeCode FROM RequiredDocumentsEntity rd WHERE rd.hireTypesEntity.hirtypeCode=:hireTypeCode AND (rd.genderType=:genderType OR rd.genderType=:genderTypeBoth) AND (rd.nationalityType=:nationalityType OR rd.nationalityType=:nationalityTypeBoth))"),
        @NamedQuery(name = "RequiredDocumentsEntity.checkRequired", query = "select COUNT(o.documentTypesEntity.doctypeCode) from RequiredDocumentsEntity o WHERE o.documentTypesEntity.doctypeCode=:doctypeCode  AND o.hireTypesEntity.hirtypeCode=:hirtypeCode AND o.basicStatus=:basicStatus"),
        @NamedQuery(name = "RequiredDocumentsEntity.filterAvailableDocuments", query = "select o from  EmpInfDocumentTypesEntity o where (:name is null or o.doctypeName LIKE :name) AND (:code is null or o.doctypeCode=:code) AND  o.doctypeCode NOT IN (SELECT rd.doctypeCode FROM RequiredDocumentsEntity rd WHERE rd.hireTypesEntity.hirtypeCode=:hireTypeCode )"),
        @NamedQuery(name = "RequiredDocumentsEntity.getValidDocumentsForEmployee", query = "SELECT reqDoc FROM RequiredDocumentsEntity reqDoc WHERE (reqDoc.nationalityType=:nationalityType OR reqDoc.nationalityType=:nationalityNotSpecified) AND (reqDoc.genderType=:genderType OR reqDoc.genderType=:genderUndefined) AND reqDoc.hireTypesEntity.hirtypeCode=:hirtypeCode"),
        @NamedQuery(name = "RequiredDocumentsEntity.getDocumentsByHireType", query = "SELECT reqDoc FROM RequiredDocumentsEntity reqDoc WHERE reqDoc.hireTypesEntity.hirtypeCode=:hirtypeCode")})
@Table(name = "HR_EMP_REQUIRED_DOCUMENTS")
@IdClass(IRequiredDocumentsEntityKey.class)
public class

RequiredDocumentsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HIRTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long hirtypeCode;
    @Id
    @Column(name = "DOCTYPE_CODE", nullable = false, insertable = true, 
            updatable = true)
    private Long doctypeCode;
    @Column(name = "GENDER_TYPE", nullable = false)
    private Long genderType;
    @Column(name = "NATIONALITY_TYPE", nullable = false)
    private Long nationalityType;
    @Column(name = "BASIC_STATUS", nullable = false)
    private Long basicStatus;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "STATUS", nullable = false)
    private Long status;
    @Column(name = "ATTACHMENT_REQUIRED", nullable = false)
    private Long attachmentRequired;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
//    @ManyToOne
//    @JoinColumn(name = "DOCTYPE_CODE", referencedColumnName = "DOCTYPE_CODE")
//    private DocumentTypesEntity documentTypesEntity;
    
    @ManyToOne
    @JoinColumn(name = "DOCTYPE_CODE", referencedColumnName = "DOCTYPE_CODE", insertable = false, updatable = false)
    private EmpInfDocumentTypesEntity documentTypesEntity;
    
    @ManyToOne
    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypesEntity;

//    @ManyToOne
//    @JoinColumn(name = "GENDER_TYPE", referencedColumnName = "GENTYPE_CODE")
//    private GenderTypesEntity genderTypesEntity;
    
  

    /**
     * RequiredDocumentsEntity Default Constructor
     */
    public RequiredDocumentsEntity() {
    }


    /**
     * @return Long
     */
    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    /**
     * @return Long
     */
    public Long getDoctypeCode() {
        return doctypeCode;
    }

    /**
     * @return Long
     */
    public Long getGenderType() {
        return genderType;
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
     * @param hirtypeCode
     */
    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    /**
     * @param doctypeCode
     */
    public void setDoctypeCode(Long doctypeCode) {
        this.doctypeCode = doctypeCode;
    }

    /**
     * @param genderType
     */
    public void setGenderType(Long genderType) {
        this.genderType = genderType;
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


    public void setDocumentTypesEntity(EmpInfDocumentTypesEntity documentTypesEntity) {
        this.documentTypesEntity = documentTypesEntity;
    }

    public EmpInfDocumentTypesEntity getDocumentTypesEntity() {
        return documentTypesEntity;
    }

    public void setHireTypesEntity(HireTypesEntity hireTypesEntity) {
        this.hireTypesEntity = hireTypesEntity;
    }

    public HireTypesEntity getHireTypesEntity() {
        return hireTypesEntity;
    }

//    public void setGenderTypesEntity(GenderTypesEntity genderTypesEntity) {
//        this.genderTypesEntity = genderTypesEntity;
//    }

//    public GenderTypesEntity getGenderTypesEntity() {
//        return genderTypesEntity;
//    }

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

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }
}
