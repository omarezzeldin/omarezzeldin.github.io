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


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of EmployeeDocuments Entity.
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
@NamedQueries( { @NamedQuery(name = "EmployeeExtraDataEntity.findAll",
                             query = "select o from EmployeeExtraDataEntity o"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.findNewId",
                             query = "select MAX(o.serial) from EmployeeExtraDataEntity o"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getByEmpWorkCenter",
                             query = "select o from EmployeeExtraDataEntity o where o.employeesEntity.minCode = :minCode and o.employeesEntity.wrkCode = :wrkCode"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getByCivilIdAndExtraDataTypeCode",
                             query = "select o from EmployeeExtraDataEntity o where o.civilId = :civilId and o.extraDataTypeCode = :extraDataTypeCode"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getAllEmployeeExtraDataByCivilId",
                             query = "select o from EmployeeExtraDataEntity o where o.civilId = :civilId"),
                 
                 @NamedQuery(name = "EmployeeExtraDataEntity.getEmpExtraDataByRealCivilId",
                             query = "select o from EmployeeExtraDataEntity o where o.realCivilId = :realCivilId and o.extraDataTypeCode = 300"),
                 
                 @NamedQuery(name = "EmployeeExtraDataEntity.getEmployeeExtraDataForMov",
                             query = "select o from EmployeeExtraDataEntity o where o.status=:status and  o.civilId = :civilId"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getEmployeeExtraDataByCode",
                             query = "select o from EmployeeExtraDataEntity o where o.status=:status and  o.civilId = :civilId"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getOverlapedEmployeeExtraData",
                             query = "select count(o) from EmployeeExtraDataEntity o where o.civilId = :civilId and o.extraDataTypeCode = :extraDataTypeCode and o.status = :status and ( (o.fromDate = :fromDate) or (o.untilDate = :fromDate) or (:untilDate is not NULL and (o.untilDate = :untilDate or o.fromDate = :untilDate)) or (o.fromDate < :fromDate and o.untilDate is NULL) or (o.fromDate < :fromDate and o.untilDate is Not NULL and o.untilDate > :fromDate) or (:untilDate is not NULL and (o.fromDate < :untilDate and o.untilDate is NULL)) or (:untilDate is not NULL and (o.fromDate < :untilDate and o.untilDate is Not NULL and o.untilDate > :untilDate)) or (:untilDate is NULL and o.untilDate is NULL) or (:untilDate is NULL and o.untilDate is Not NULL and o.untilDate > :fromDate ) or (:untilDate is NOT NULL and o.untilDate is Not NULL and o.fromDate > :fromDate and o.untilDate < :untilDate) )"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getOverlapedEmployeeExtraDataForEdit",
                             query = "select count(o) from EmployeeExtraDataEntity o where o.civilId = :civilId and o.serial <> :serial and o.extraDataTypeCode = :extraDataTypeCode and o.status = :status and ( (o.fromDate = :fromDate) or (o.untilDate = :fromDate) or (:untilDate is not NULL and (o.untilDate = :untilDate or o.fromDate = :untilDate)) or (o.fromDate < :fromDate and o.untilDate is NULL) or (o.fromDate < :fromDate and o.untilDate is Not NULL and o.untilDate > :fromDate) or (:untilDate is not NULL and (o.fromDate < :untilDate and o.untilDate is NULL)) or (:untilDate is not NULL and (o.fromDate < :untilDate and o.untilDate is Not NULL and o.untilDate > :untilDate)) or (:untilDate is NULL and o.untilDate is NULL) or (:untilDate is NULL and o.untilDate is Not NULL and o.untilDate > :fromDate ) or (:untilDate is NOT NULL and o.untilDate is Not NULL and o.fromDate > :fromDate and o.untilDate < :untilDate) )"),
                 @NamedQuery(name = "EmployeeExtraDataEntity.getEmployeeExtraDataByName",
                             query = "select o from EmployeeExtraDataEntity o where o.civilId = :civilId and o.empExtraDataTypesEntity.extdattypeName like :typeName") })
@Table(name = "HR_EMP_EMPLOYEE_EXTRA_DATA")
@IdClass(IEmployeeExtraDataEntityKey.class)

public class EmployeeExtraDataEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "SERIAL", nullable = false)
    private Long serial;
    @Column(name = "CIVIL_ID", nullable = false, insertable = false, updatable = false)
    private Long civilId;
    @Column(name = "EXTDATTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long extraDataTypeCode;
    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;
    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;
    @Column(name = "VALUE", nullable = false)
    private String value;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true, insertable = false, updatable = false)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "CIVIL_ID", referencedColumnName = "CIVIL_ID")
    private EmployeesEntity employeesEntity;
    @ManyToOne
    @JoinColumn(name = "EXTDATTYPE_CODE", referencedColumnName = "EXTDATTYPE_CODE")
    private EmpExtraDataTypesEntity empExtraDataTypesEntity;

    @Column(name = "VALUE_DESC")
    private String valueDesc;

    @Column(name = "STATUS")
    private Long status = 1L;

    @Column(name = "MIN_CODE")
    private Long minCode;

    @Column(name = "R_CIVIL_ID")
    private Long realCivilId;
    
    @Column(name = "CSC_BOOK_NO")
    private String cscBookNo;
    
    @Column(name = "CSC_BOOK_DATE")
    private Date cscBookDate;
    
    @Column(name = "COMMENTS")
    private String comments;

    /**
     * EmployeeDocumentsEntity Default Constructor
     */
    public EmployeeExtraDataEntity() {
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

    public void setExtraDataTypeCode(Long extraDataTypeCode) {
        this.extraDataTypeCode = extraDataTypeCode;
    }

    public Long getExtraDataTypeCode() {
        return extraDataTypeCode;
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

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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

    public void setEmployeesEntity(EmployeesEntity employeesEntity) {
        this.employeesEntity = employeesEntity;
    }

    public EmployeesEntity getEmployeesEntity() {
        return employeesEntity;
    }

    public void setEmpExtraDataTypesEntity(EmpExtraDataTypesEntity empExtraDataTypesEntity) {
        this.empExtraDataTypesEntity = empExtraDataTypesEntity;
    }

    public EmpExtraDataTypesEntity getEmpExtraDataTypesEntity() {
        return empExtraDataTypesEntity;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setCscBookNo(String cscBookNo) {
        this.cscBookNo = cscBookNo;
    }

    public String getCscBookNo() {
        return cscBookNo;
    }

    public void setCscBookDate(Date cscBookDate) {
        this.cscBookDate = cscBookDate;
    }

    public Date getCscBookDate() {
        return cscBookDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }
}
