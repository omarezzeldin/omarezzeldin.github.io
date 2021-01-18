package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 */
@Entity
@NamedQueries( { @NamedQuery(name = "HireTypeProcedureEntity.findAll",
                             query = "select o from HireTypeProcedureEntity o"),
                 @NamedQuery(name = "HireTypeProcedureEntity.findNewId",
                             query = "select MAX(o.hirtypeCode) from HireTypeProcedureEntity o"),

        @NamedQuery(name = "HireTypeProcedureEntity.getAvailableProcedures",
                    query = "select new com.beshara.csc.hr.emp.business.dto.HireProceduresDTO(o.hirprocedureCode, o.hirprocedureName) from  HireProceduresEntity o WHERE o.hirprocedureCode NOT IN (SELECT rd.hirTypeProcCode FROM HireTypeProcedureEntity rd WHERE rd.hireTypesEntity.hirtypeCode=:hireTypeCode )"),
        @NamedQuery(name = "HireTypeProcedureEntity.getAvailableProceduresWithoutCode",
                    query = "select new com.beshara.csc.hr.emp.business.dto.HireProceduresDTO(o.hirprocedureCode, o.hirprocedureName) from  HireProceduresEntity o "),
        @NamedQuery(name = "HireTypeProcedureEntity.filterAvailableProcedures",
                    query = "select new com.beshara.csc.hr.emp.business.dto.HireProceduresDTO(o.hirprocedureCode, o.hirprocedureName) from  HireProceduresEntity o WHERE (:name is null or o.hirprocedureName LIKE :name)  AND (:code is null or o.hirprocedureCode=:code) AND  o.hirprocedureCode NOT IN (SELECT rd.hirTypeProcCode FROM HireTypeProcedureEntity rd WHERE rd.hireTypesEntity.hirtypeCode=:hireTypeCode )"),
        @NamedQuery(name = "HireTypeProcedureEntity.listRelatedHireProcedures",
                    query = "select o from HireTypeProcedureEntity o where o.hireTypesEntity.hirtypeCode =:hireTypeCode"),
        @NamedQuery(name = "HireTypeProcedureEntity.getActiveAvailableProceduresWithoutCode",
                    query = "select new com.beshara.csc.hr.emp.business.dto.HireProceduresDTO(o.hirprocedureCode, o.hirprocedureName) from  HireProceduresEntity o WHERE o.status=1") })
@Table(name = "HR_EMP_HT_PROCEDURES")
@IdClass(IHireTypeProcedureEntityKey.class)

public class HireTypeProcedureEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HIRTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirtypeCode;
    @Id
    @Column(name = "HIRPROCEDURE_CODE", nullable = false, insertable = false, updatable = false)
    private Long hirTypeProcCode;
    @Column(name = "GENDER_TYPE", nullable = false)
    private Long genderType;
    @Column(name = "NATIONALITY_TYPE", nullable = false)
    private Long nationalityType;
    @Column(name = "STATUS", nullable = false)
    private Long status;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "PROCEDURE_ORDER", nullable = false)
    private Long procOrder;
    @Column(name = "OPTIONAL_FLAG", nullable = false)
    private Long optionFlag;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;


    @ManyToOne
    @JoinColumn(name = "HIRPROCEDURE_CODE", referencedColumnName = "HIRPROCEDURE_CODE")
    private HireProceduresEntity hireProcedureEntity;
    @ManyToOne
    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypesEntity;

    //    @ManyToOne
    //    @JoinColumn(name = "GENDER_TYPE", referencedColumnName = "GENTYPE_CODE")
    //    private GenderTypesEntity genderTypesEntity;

    /**
     * HireTypeProcedureEntity Default Constructor
     */
    public HireTypeProcedureEntity() {
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
    public Long getHirTypeProcCode() {
        return hirTypeProcCode;
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
    }


    /**
     * @param hirtypeCode
     */
    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    /**
     * @param HirtypeProcCode
     */
    public void setHirTypeProcCode(Long hirtypeProcCode) {
        this.hirTypeProcCode = hirtypeProcCode;
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


    public void setHireProcedureEntity(HireProceduresEntity hireProcedureEntity) {
        this.hireProcedureEntity = hireProcedureEntity;
    }

    public HireProceduresEntity getHireProcedureEntity() {
        return hireProcedureEntity;
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
    //
    //    public GenderTypesEntity getGenderTypesEntity() {
    //        return genderTypesEntity;
    //    }

    /**
     * @param ProcOrder
     */
    public void setProcOrder(Long procOrder) {
        this.procOrder = procOrder;
    }

    /**
     * @return Long
     */
    public Long getProcOrder() {
        return procOrder;
    }

    /**
     * @param OptionFlag
     */
    public void setOptionFlag(Long optionFlag) {
        this.optionFlag = optionFlag;
    }

    /**
     * @return Long
     */
    public Long getOptionFlag() {
        return optionFlag;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }
}
