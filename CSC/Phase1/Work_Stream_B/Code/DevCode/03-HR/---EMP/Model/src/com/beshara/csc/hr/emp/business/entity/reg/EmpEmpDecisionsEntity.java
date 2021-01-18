package com.beshara.csc.hr.emp.business.entity.reg;

//import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;


import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "NL_REG_EMP_DECISIONS")

public class EmpEmpDecisionsEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "DECTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long dectypeCode;
    @Id
    @Column(name = "DECYEAR_CODE", nullable = false, insertable = false, updatable = false)
    private Long decyearCode;
    @Id
    @Column(name = "DECISION_NUMBER", nullable = false, insertable = false, updatable = false)
    private Long decisionNumber;
    @Id
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, updatable = false)
    private Long civilId;


    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "DECISION_NUMBER", referencedColumnName = "DECISION_NUMBER"),
                    @JoinColumn(name = "DECYEAR_CODE", referencedColumnName = "DECYEAR_CODE"),
                    @JoinColumn(name = "DECTYPE_CODE", referencedColumnName = "DECTYPE_CODE") })
    private EmpRegDecisionsEntity decisionsEntity;

    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private EmployeesEntity employeesEntity;

    /**
     * EmpDecisionsEntity Default Constructor
     */
    public EmpEmpDecisionsEntity() {
    }


    /**
     * @return Long
     */
    public Long getDectypeCode() {
        return dectypeCode;
    }

    /**
     * @return Long
     */
    public Long getDecyearCode() {
        return decyearCode;
    }

    /**
     * @return Long
     */
    public Long getDecisionNumber() {
        return decisionNumber;
    }

    /**
     * @return Long
     */
    public Long getCivilId() {
        return civilId;
    }

    //    /**
    //     * @return String
    //     */
    //    public String getTableName() {
    //        return tableName;
    //    }
    //
    //    /**
    //     * @return Long
    //     */
    //    public Long getTabrecSerialRef() {
    //        return tabrecSerialRef;
    //    }


    /**
     * @param dectypeCode
     */
    public void setDectypeCode(Long dectypeCode) {
        this.dectypeCode = dectypeCode;
    }

    /**
     * @param decyearCode
     */
    public void setDecyearCode(Long decyearCode) {
        this.decyearCode = decyearCode;
    }

    /**
     * @param decisionNumber
     */
    public void setDecisionNumber(Long decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    /**
     * @param civilId
     */
    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    //    /**
    //     * @param tableName
    //     */
    //    public void setTableName(String tableName) {
    //        this.tableName = tableName;
    //    }
    //
    //    /**
    //     * @param tabrecSerialRef
    //     */
    //    public void setTabrecSerialRef(Long tabrecSerialRef) {
    //        this.tabrecSerialRef = tabrecSerialRef;
    //    }


    public void setDecisionsEntity(EmpRegDecisionsEntity decisionsEntity) {
        this.decisionsEntity = decisionsEntity;
    }

    public EmpRegDecisionsEntity getDecisionsEntity() {
        return decisionsEntity;
    }

    //    public void setEmployeesEntity(EmployeesEntity empEmployeesEntity) {
    //        this.employeesEntity = empEmployeesEntity;
    //    }
    //
    //    public EmployeesEntity getEmployeesEntity() {
    //        return employeesEntity;
    //    }


    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }
}
