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
 * This Class Manipulate the Persistence Methods of AssignReasons Entity.
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
@NamedQueries( { @NamedQuery(name = "AssignReasonsEntity.findAll", 
                             query = "select o from AssignReasonsEntity o")
        , 
        @NamedQuery(name = "AssignReasonsEntity.findNewId", query = "select MAX(o.assreasonCode) from AssignReasonsEntity o")
        , 
        @NamedQuery(name = "AssignReasonsEntity.searchByName", query = "select o from AssignReasonsEntity o where o.assreasonName like :name order by o.assreasonName")
        , 
        @NamedQuery(name = "AssignReasonsEntity.getCodeName", query = "select new com.beshara.csc.hr.emp.business.dto.AssignReasonsDTO(o.assreasonCode,o.assreasonName) from AssignReasonsEntity o order by o.assreasonName")
        } )
@Table(name = "HR_EMP_ASSIGN_REASONS")
@IdClass(IAssignReasonsEntityKey.class)
public class

AssignReasonsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ASSREASON_CODE", nullable = false)
    private Long assreasonCode;
    @Column(name = "ASSREASON_NAME", nullable = false)
    private String assreasonName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * AssignReasonsEntity Default Constructor
     */
    public AssignReasonsEntity() {
    }


    /**
     * @return Long
     */
    public Long getAssreasonCode() {
        return assreasonCode;
    }

    /**
     * @return String
     */
    public String getAssreasonName() {
        return assreasonName;
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
     * @param assreasonCode
     */
    public void setAssreasonCode(Long assreasonCode) {
        this.assreasonCode = assreasonCode;
    }

    /**
     * @param assreasonName
     */
    public void setAssreasonName(String assreasonName) {
        this.assreasonName = assreasonName;
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
