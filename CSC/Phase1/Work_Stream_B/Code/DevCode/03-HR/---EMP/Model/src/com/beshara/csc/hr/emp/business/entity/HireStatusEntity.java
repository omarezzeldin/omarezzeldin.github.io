package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of HireStatus Entity.
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
@NamedQueries

( { @NamedQuery(name = "HireStatusEntity.findAll", 
                query = "select o from HireStatusEntity o")
        , 
        @NamedQuery(name = "HireStatusEntity.findNewId", query = "select MAX(o.hirstatusCode) from HireStatusEntity o")
        , 
        @NamedQuery(name = "HireStatusEntity.searchByName", query = "select o from HireStatusEntity o where o.hirstatusName like :name order by o.hirstatusName")
        , 
        @NamedQuery(name = "HireStatusEntity.getCodeName", query = "select new com.beshara.csc.hr.emp.business.dto.HireStatusDTO(o.hirstatusCode,o.hirstatusName) from HireStatusEntity o order by o.hirstatusName")
        } )
@Table(name = "HR_EMP_HIRE_STATUS")
@IdClass(IHireStatusEntityKey.class)
public class

HireStatusEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HIRSTATUS_CODE", nullable = false)
    private Long hirstatusCode;
    @Column(name = "HIRSTATUS_NAME", nullable = false)
    private String hirstatusName;
     @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @OneToMany(mappedBy = "hireStatusEntity")
    private List<EmployeesEntity> employeesEntityList;


    /**
     * HireStatusEntity Default Constructor
     */
    public HireStatusEntity() {
    }


    /**
     * @return Long
     */
    public Long getHirstatusCode() {
        return hirstatusCode;
    }

    /**
     * @return String
     */
    public String getHirstatusName() {
        return hirstatusName;
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
     * @param hirstatusCode
     */
    public void setHirstatusCode(Long hirstatusCode) {
        this.hirstatusCode = hirstatusCode;
    }

    /**
     * @param hirstatusName
     */
    public void setHirstatusName(String hirstatusName) {
        this.hirstatusName = hirstatusName;
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
}
