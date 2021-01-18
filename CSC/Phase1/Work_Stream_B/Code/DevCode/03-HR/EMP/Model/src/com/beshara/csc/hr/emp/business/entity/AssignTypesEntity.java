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
 * This Class Manipulate the Persistence Methods of AssignTypes Entity.
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

( { @NamedQuery(name = "AssignTypesEntity.findAll", 
                query = "select o from AssignTypesEntity o")
        , 
        @NamedQuery(name = "AssignTypesEntity.findNewId", query = "select MAX(o.asstypeCode) from AssignTypesEntity o")
        , 
        @NamedQuery(name = "AssignTypesEntity.searchByName", query = "select o from AssignTypesEntity o where o.asstypeName like :name order by o.asstypeName")
        , 
        @NamedQuery(name = "AssignTypesEntity.getCodeName", query = "select new com.beshara.csc.hr.emp.business.dto.AssignTypesDTO(o.asstypeCode,o.asstypeName) from AssignTypesEntity o order by o.asstypeName")
        } )
@Table(name = "HR_EMP_ASSIGN_TYPES")
@IdClass(IAssignTypesEntityKey.class)
public class

AssignTypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ASSTYPE_CODE", nullable = false)
    private Long asstypeCode;
    @Column(name = "ASSTYPE_NAME", nullable = false)
    private String asstypeName;
    @Column(name = "CONDITION_CODE", nullable = true, insertable = true, 
            updatable = true)
    private Long conditionCode;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
//    @ManyToOne
//    @JoinColumn(name = "CONDITION_CODE", 
//                referencedColumnName = "CONDITION_CODE")
//    private ConditionsEntity conditionsEntity;


    /**
     * AssignTypesEntity Default Constructor
     */
    public AssignTypesEntity() {
    }


    /**
     * @return Long
     */
    public Long getAsstypeCode() {
        return asstypeCode;
    }

    /**
     * @return String
     */
    public String getAsstypeName() {
        return asstypeName;
    }

    /**
     * @return Long
     */
    public Long getConditionCode() {
        return conditionCode;
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
     * @param asstypeCode
     */
    public void setAsstypeCode(Long asstypeCode) {
        this.asstypeCode = asstypeCode;
    }

    /**
     * @param asstypeName
     */
    public void setAsstypeName(String asstypeName) {
        this.asstypeName = asstypeName;
    }

    /**
     * @param conditionCode
     */
    public void setConditionCode(Long conditionCode) {
        this.conditionCode = conditionCode;
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


//    public void setConditionsEntity(ConditionsEntity conditionsEntity) {
//        this.conditionsEntity = conditionsEntity;
//    }
//
//    public ConditionsEntity getConditionsEntity() {
//        return conditionsEntity;
//    }
}
