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
 * This Class Manipulate the Persistence Methods of EmpReasonTypes Entity.
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
@NamedQueries( { @NamedQuery(name = "EmpReasonTypesEntity.findAll", 
                             query = "select o from EmpReasonTypesEntity o")
        , 
        @NamedQuery(name = "EmpReasonTypesEntity.findNewId", query = "select MAX(o.restypeCode) from EmpReasonTypesEntity o")
        } )
@Table(name = "HR_EMP_REASON_TYPES")
@IdClass(IEmpReasonTypesEntityKey.class)
public class

EmpReasonTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "RESTYPE_CODE", nullable = false)
    private Long restypeCode;
    @Column(name = "RESTYPE_NAME", nullable = false)
    private String restypeName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * EmpReasonTypesEntity Default Constructor
     */
    public EmpReasonTypesEntity() {
    }


    /**
     * @return Long
     */
    public Long getRestypeCode() {
        return restypeCode;
    }

    /**
     * @return String
     */
    public String getRestypeName() {
        return restypeName;
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
     * @param restypeName
     */
    public void setRestypeName(String restypeName) {
        this.restypeName = restypeName;
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
