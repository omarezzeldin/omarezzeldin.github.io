package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
    @NamedQueries   ({ 
    @NamedQuery(name = "EmpWorkPermitsEntity.findAll",
                                 query = "select o from EmpWorkPermitsEntity o"),
    @NamedQuery(name = "EmpWorkPermitsEntity.findNewId",
                             query = "select MAX(o.serial) from EmpWorkPermitsEntity o"),
    
        @NamedQuery(name = "EmpWorkPermitsEntity.getEmpWorkPermitsByCivilId",
                                 query = "select o from EmpWorkPermitsEntity o where o.civilId = :civilId"),
    
                @NamedQuery(name = "EmpWorkPermitsEntity.getAllEmpPermitsByMinCode",
                                 query = "select o from EmpWorkPermitsEntity o where o.employeesEntity.minCode = :minCode and o.employeesEntity.activeFlag=1 and o.employeesEntity.hirstatusCode=1 "),
                    @NamedQuery(name = "EmpWorkPermitsEntity.getEmpWorkPermitsByCivilIdAndMinCode",
                                 query = "select o from EmpWorkPermitsEntity o where o.civilId = :civilId and o.minCode=:minCode")
    
    
    })

@Table(name = "HR_EMP_WORK_PERMITS")
@IdClass(IEmpWorkPermitsEntityKey.class)

public class EmpWorkPermitsEntity extends BasicEntity {
        
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "CIVIL_ID", nullable = false)
    private Long civilId;
    @Column(name = "MIN_CODE", nullable = false)
    private Long minCode;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @Column(name = "R_CIVIL_ID")
    private Long realCivilId;
    
    @ManyToOne
   @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID",insertable = false , updatable = false)
    private EmployeesEntity employeesEntity;
    
   
    public EmpWorkPermitsEntity() {
    }


    public void setSerial(Long serial) {
        this.serial = serial;
    }

    public Long getSerial() {
        return serial;
    }

    public void setCivilId(Long civilId) {
        this.civilId = civilId;
    }

    public Long getCivilId() {
        return civilId;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }

    public void setUntilDate(Date untilDate) {
        this.untilDate = untilDate;
    }

    public Date getUntilDate() {
        return untilDate;
    }

   
}
