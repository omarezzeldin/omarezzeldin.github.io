package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

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
 * This Class Manipulate the Persistence Methods of EduLevelGenSepcs Entity.
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
 * @author       H.Ahmed
 * @version      1.0
 * @since        03/09/2014
 */
@Entity
@NamedQueries( { @NamedQuery(name = "HireTypesMinistriesEntity.findAll", 
                             query = "select o from HireTypesMinistriesEntity o")
        , 
        @NamedQuery(name = "HireTypesMinistriesEntity.findNewId", query = "select MAX(o.minCode) from HireTypesMinistriesEntity o")
 ,
 @NamedQuery(name = "HireTypesMinistriesEntity.listRelatedMinistry", 
             query = 
             "select o from HireTypesMinistriesEntity o where o.hireTypesEntity.hirtypeCode =:hireTypeCode") 
                 
        } )
@Table(name = "HR_EMP_MIN_HIRE_TYPES")
@IdClass(IHireTypesMinistriesEntityKey.class)
public class HireTypesMinistriesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   
    @Id
    @Column(name = "HIRTYPE_CODE", nullable = false, insertable = false, 
            updatable = false)
    private Long hirtypeCode;
    
    @Id
    @Column(name = "MIN_CODE", nullable = false, insertable = true, 
            updatable = true)
    private Long minCode;
    

    @Column(name = "CREATED_BY", nullable = true)
    private Long createdBy;
    @Column(name = "CREATED_DATE", nullable = true)
    private Timestamp createdDate;
    @Column(name = "LAST_UPDATED_BY", nullable = true)
    private Long lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE", nullable = true)
    private Timestamp lastUpdatedDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    @ManyToOne
    @JoinColumn(name = "HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypesEntity;
    
//    @ManyToOne
//    @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE")
//    private MinistriesEntity ministriesEntity;


    public HireTypesMinistriesEntity() {
    }
    
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @return Long
     */
    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
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
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @param lastUpdatedDate
     */
    public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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


    

    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setHireTypesEntity(HireTypesEntity hireTypesEntity) {
        this.hireTypesEntity = hireTypesEntity;
    }

    public HireTypesEntity getHireTypesEntity() {
        return hireTypesEntity;
    }

//    public void setMinistriesEntity(MinistriesEntity ministriesEntity) {
//        this.ministriesEntity = ministriesEntity;
//    }
//
//    public MinistriesEntity getMinistriesEntity() {
//        return ministriesEntity;
//    }
}
