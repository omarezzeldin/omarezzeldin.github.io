package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of HireStages Entity.
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
 *//**
    * <b>Description:</b>
    * <br>&nbsp;&nbsp;&nbsp;
    * This Class Manipulate the Persistence Methods of HireStages Entity.
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
@NamedQueries( { @NamedQuery(name = "HireStagesEntity.findAll", 
                             query = "select o from HireStagesEntity o")
        , 
        @NamedQuery(name = "HireStagesEntity.findNewId", query = "select MAX(o.hirstageCode) from HireStagesEntity o")
        , 
        @NamedQuery(name = "HireStagesEntity.searchByName", query = "select o from HireStagesEntity o where o.hirstageName like :name order by o.hirstageName")
        , 
        @NamedQuery(name = "HireStagesEntity.getCodeName", query = "select new com.beshara.csc.hr.emp.business.dto.HireStagesDTO(o.hirstageCode,o.hirstageName) from HireStagesEntity o order by o.hirstageName")
        } )
@Table(name = "HR_EMP_HIRE_STAGES")
@IdClass(IHireStagesEntityKey.class)
public class

HireStagesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HIRSTAGE_CODE", nullable = false)
    private Long hirstageCode;
    @Column(name = "HIRSTAGE_NAME", nullable = false)
    private String hirstageName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @OneToMany(mappedBy = "hireStagesEntity")
    private List<EmployeesEntity> employeesEntityList;
    
    @Column(name = "FROM_DATE", nullable = true)
    private Date fromDate;

    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    /**
     * HireStagesEntity Default Constructor
     */
    public HireStagesEntity() {
    }


    /**
     * @return Long
     */
    public Long getHirstageCode() {
        return hirstageCode;
    }

    /**
     * @return String
     */
    public String getHirstageName() {
        return hirstageName;
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
     * @param hirstageCode
     */
    public void setHirstageCode(Long hirstageCode) {
        this.hirstageCode = hirstageCode;
    }

    /**
     * @param hirstageName
     */
    public void setHirstageName(String hirstageName) {
        this.hirstageName = hirstageName;
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


    public void setEmployeesEntityList(List<EmployeesEntity> employeesEntityList) {
        this.employeesEntityList = employeesEntityList;
    }

    public List<EmployeesEntity> getEmployeesEntityList() {
        return employeesEntityList;
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

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }
}
