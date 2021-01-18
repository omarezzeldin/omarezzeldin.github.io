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
 * This Class Manipulate the Persistence Methods of ProcedureResults Entity.
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
@NamedQueries( { @NamedQuery(name = "ProcedureResultsEntity.findAll", 
                             query = "select o from ProcedureResultsEntity o order by o.proresultCode")
        , 
        @NamedQuery(name = "ProcedureResultsEntity.findNewId", query = "select MAX(o.proresultCode) from ProcedureResultsEntity o")
        } )
@Table(name = "HR_EMP_PROCEDURE_RESULTS")
@IdClass(IProcedureResultsEntityKey.class)
public class

ProcedureResultsEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "PRORESULT_CODE", nullable = false)
    private Long proresultCode;
    @Column(name = "PRORESULT_NAME", nullable = false)
    private String proresultName;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;


    /**
     * ProcedureResultsEntity Default Constructor
     */
    public ProcedureResultsEntity() {
    }


    /**
     * @return Long
     */
    public Long getProresultCode() {
        return proresultCode;
    }

    /**
     * @return String
     */
    public String getProresultName() {
        return proresultName;
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
     * @param proresultCode
     */
    public void setProresultCode(Long proresultCode) {
        this.proresultCode = proresultCode;
    }

    /**
     * @param proresultName
     */
    public void setProresultName(String proresultName) {
        this.proresultName = proresultName;
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
