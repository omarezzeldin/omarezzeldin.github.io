package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import com.beshara.csc.gn.grs.business.entity.ConditionsEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
 * @author       Alaa
 * @version      1.0
 * @since        16/02/2014
 */
@Entity
@NamedQueries( { @NamedQuery(name = "HireTypeConditionsEntity.findAll", 
                             query = "select o from HireTypeConditionsEntity o")
//        @NamedQuery(name = "HireTypeConditionsEntity.findNewId", query = "select MAX(o.serial) from HireTypeConditionsEntity o")
//        ,
//        @NamedQuery(name = "HireTypeConditionsEntity.listRelatedHireCondition", 
//                    query = 
//                    "select o from HireTypeConditionsEntity o where o.status = :status and o.hireTypesEntity.hirtypeCode =:hireTypeCode")
        } )
@Table(name = "HR_EMP_HIRE_TYPE_CONDITIONS")
@IdClass(IHireTypeConditionsEntityKey.class)
public class

HireTypeConditionsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


//    @Id
//    @Column(name = "HIRTYPE_CODE", nullable = false, insertable = false, 
//            updatable = false)
//    private Long hirtypeCode;
//    @Id
//    @Column(name = "SERIAL", nullable = false, 
//            updatable = false)
//    private Long serial;
    @Id
    @Column(name = "CONDITION_CODE", nullable = false , insertable = true , updatable = true )
        
    private Long ConditionCode;
    @Column(name = "STATUS", nullable = false)
    private Long status;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
//    @Column(name = "OPTIONAL_FLAG", nullable = false)
//    private Long optionFlag;
    @Column(name = "R_TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    
//    @ManyToOne
//    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypesEntity;
    
//    @ManyToOne
//    @JoinColumn(name = "CONDITION_CODE", referencedColumnName = "CONDITION_CODE")
    private ConditionsEntity conditionsEntity;

    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;

    /**
     * HireTypeProcedureEntity Default Constructor
     */
    public HireTypeConditionsEntity() {
    }

//    /**
//     * @return Long
//     */
//    public Long getHirtypeCode() {
//        return hirtypeCode;
//    }

   

    
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

//    /**
//     * @param hirtypeCode
//     */
//    public void setHirtypeCode(Long hirtypeCode) {
//        this.hirtypeCode = hirtypeCode;
//    }

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


    

    public void setHireTypesEntity(HireTypesEntity hireTypesEntity) {
        this.hireTypesEntity = hireTypesEntity;
    }

    public HireTypesEntity getHireTypesEntity() {
        return hireTypesEntity;
    }

   
    
//    /**
//     * @param OptionFlag
//     */
//    public void setOptionFlag(Long optionFlag) {
//        this.optionFlag = optionFlag;
//    }
//    /**
//     * @return Long
//     */
//    public Long getOptionFlag() {
//        return optionFlag;
//    }

//    public void setSerial(Long serial) {
//        this.serial = serial;
//    }
//
//    public Long getSerial() {
//        return serial;
//    }

    public void setConditionCode(Long ConditionCode) {
        this.ConditionCode = ConditionCode;
    }

    public Long getConditionCode() {
        return ConditionCode;
    }

    public void setConditionsEntity(ConditionsEntity conditionsEntity) {
        this.conditionsEntity = conditionsEntity;
    }

    public ConditionsEntity getConditionsEntity() {
        return conditionsEntity;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }
}
