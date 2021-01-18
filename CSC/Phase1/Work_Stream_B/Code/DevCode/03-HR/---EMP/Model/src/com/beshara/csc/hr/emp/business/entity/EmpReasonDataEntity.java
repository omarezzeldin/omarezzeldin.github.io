package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of EmpReasonData Entity.
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
@NamedQueries( { @NamedQuery(name = "EmpReasonDataEntity.findAll", query = "select o from EmpReasonDataEntity o"),
                 @NamedQuery(name = "EmpReasonDataEntity.getMaxId",
                             query = "select MAX(o.resdatSerial) from EmpReasonDataEntity o where o.restypeCode = :restypeCode"),
                 @NamedQuery(name = "EmpReasonDataEntity.checkDublicateName",
                             query = "select o from EmpReasonDataEntity o where o.resdatDesc = :name"),
                 @NamedQuery(name = "EmpReasonDataEntity.checkDublicateNameAndType",
                             query = "select o from EmpReasonDataEntity o where o.restypeCode = :code and o.resdatDesc = :name  "),
                 @NamedQuery(name = "EmpReasonDataEntity.getByTypeCode",
                             query = "select o from EmpReasonDataEntity o where o.empReasonTypesEntity.restypeCode = :restypeCode order by o.resdatDesc") })
@Table(name = "HR_EMP_REASON_DATA")
@IdClass(IEmpReasonDataEntityKey.class)
public class EmpReasonDataEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "RESTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long restypeCode;
    @Id
    @Column(name = "RESDAT_SERIAL", nullable = false)
    private Long resdatSerial;
    @Column(name = "RESDAT_DESC", nullable = false)
    private String resdatDesc;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "RESTYPE_CODE", referencedColumnName = "RESTYPE_CODE")
    private EmpReasonTypesEntity empReasonTypesEntity;


    /**
     * EmpReasonDataEntity Default Constructor
     */
    public EmpReasonDataEntity() {
    }


    /**
     * @return Long
     */
    public Long getRestypeCode() {
        return restypeCode;
    }

    /**
     * @return Long
     */
    public Long getResdatSerial() {
        return resdatSerial;
    }

    /**
     * @return String
     */
    public String getResdatDesc() {
        return resdatDesc;
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
     * @param restypeCode
     */
    public void setRestypeCode(Long restypeCode) {
        this.restypeCode = restypeCode;
    }

    /**
     * @param resdatSerial
     */
    public void setResdatSerial(Long resdatSerial) {
        this.resdatSerial = resdatSerial;
    }

    /**
     * @param resdatDesc
     */
    public void setResdatDesc(String resdatDesc) {
        this.resdatDesc = resdatDesc;
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

    public void setEmpReasonTypesEntity(EmpReasonTypesEntity empReasonTypesEntity) {
        this.empReasonTypesEntity = empReasonTypesEntity;
    }

    public EmpReasonTypesEntity getEmpReasonTypesEntity() {
        return empReasonTypesEntity;
    }
}
