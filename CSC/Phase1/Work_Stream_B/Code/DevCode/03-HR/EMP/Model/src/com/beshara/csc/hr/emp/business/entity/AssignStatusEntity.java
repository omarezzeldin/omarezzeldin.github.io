package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

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
 * This Class Manipulate the Persistence Methods of AssignStatus Entity.
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

( { @NamedQuery(name = "AssignStatusEntity.findAll", 
                query = "select o from AssignStatusEntity o")
        , 
        @NamedQuery(name = "AssignStatusEntity.findNewId", query = "select MAX(o.assstatusCode) from AssignStatusEntity o")
        , 
        @NamedQuery(name = "AssignStatusEntity.searchByName", query = "select o from AssignStatusEntity o where o.assstatusName like :name order by o.assstatusName")
        , 
        @NamedQuery(name = "AssignStatusEntity.getCodeName", query = "select new com.beshara.csc.hr.emp.business.dto.AssignStatusDTO(o.assstatusCode,o.assstatusName) from AssignStatusEntity o order by o.assstatusName")
        } )
@Table(name = "HR_EMP_ASSIGN_STATUS")
@IdClass(IAssignStatusEntityKey.class)
public class

AssignStatusEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ASSSTATUS_CODE", nullable = false)
    private Long assstatusCode;
    @Column(name = "ASSSTATUS_NAME", nullable = false)
    private String assstatusName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * AssignStatusEntity Default Constructor
     */
    public AssignStatusEntity() {
    }


    /**
     * @return Long
     */
    public Long getAssstatusCode() {
        return assstatusCode;
    }

    /**
     * @return String
     */
    public String getAssstatusName() {
        return assstatusName;
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
     * @param assstatusCode
     */
    public void setAssstatusCode(Long assstatusCode) {
        this.assstatusCode = assstatusCode;
    }

    /**
     * @param assstatusName
     */
    public void setAssstatusName(String assstatusName) {
        this.assstatusName = assstatusName;
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


}
