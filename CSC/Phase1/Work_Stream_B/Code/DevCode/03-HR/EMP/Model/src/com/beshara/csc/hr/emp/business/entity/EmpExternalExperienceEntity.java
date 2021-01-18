package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.math.BigDecimal;

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
 * This Class Manipulate the Persistence Methods of EmpExternalExperience Entity.
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
@NamedQueries( { @NamedQuery(name = "EmpExternalExperienceEntity.findAll", 
                             query = 
                             "select o from EmpExternalExperienceEntity o")
        , 
        @NamedQuery(name = "EmpExternalExperienceEntity.findNewId", query = "select MAX(o.serial) from EmpExternalExperienceEntity o")
        , 
        @NamedQuery(name = "EmpExternalExperienceEntity.getAllByCivilId", query = "select o from EmpExternalExperienceEntity o where o.civilId = :civilId order by o.serial")
        } )
@Table(name = "HR_EMP_EXTERNAL_EXPERIENCE")
@IdClass(IEmpExternalExperienceEntityKey.class)
public class

EmpExternalExperienceEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL", nullable = false)
    private BigDecimal serial;
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, 
            updatable = false)
    private Long civilId;
    @Column(name = "EXPERIENCE_DESC", nullable = false)
    private String experienceDesc;
    @Column(name = "FROM_DATE", nullable = false)
    private Timestamp fromDate;
    @Column(name = "TO_DATE", nullable = true)
    private Timestamp toDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private EmployeesEntity employeesEntity;


    /**
     * EmpExternalExperienceEntity Default Constructor
     */
    public EmpExternalExperienceEntity() {
    }


   

    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    /**
     * @return String
     */
    public String getExperienceDesc() {
        return experienceDesc;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getFromDate() {
        return fromDate;
    }

    /**
     * @return Timestamp
     */
    public Timestamp getToDate() {
        return toDate;
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
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    /**
     * @param experienceDesc
     */
    public void setExperienceDesc(String experienceDesc) {
        this.experienceDesc = experienceDesc;
    }

    /**
     * @param fromDate
     */
    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @param toDate
     */
    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
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


    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }

    public void setSerial(BigDecimal serial) {
        this.serial = serial;
}

    public BigDecimal getSerial() {
        return serial;
    }
}
