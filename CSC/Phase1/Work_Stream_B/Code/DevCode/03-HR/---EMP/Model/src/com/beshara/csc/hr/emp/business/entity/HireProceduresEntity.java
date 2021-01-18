package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpMinistriesEntity;

import java.sql.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of HireProcedures Entity.
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
@NamedQueries( { @NamedQuery(name = "HireProceduresEntity.findAll", query = "select o from HireProceduresEntity o"),
                 @NamedQuery(name = "HireProceduresEntity.findNewId",
                             query = "select MAX(o.hirprocedureCode) from HireProceduresEntity o"),
                 @NamedQuery(name = "HireProceduresEntity.getCodeName",
                             query = "select new com.beshara.csc.hr.emp.business.dto.HireProceduresDTO(o.hirprocedureCode, o.hirprocedureName) from HireProceduresEntity o order by o.hirprocedureCode"),
                 @NamedQuery(name = "HireProceduresEntity.getEmployeeHireProcedures",
                             query = "select new com.beshara.csc.hr.emp.business.dto.HireProceduresDTO(o.hirprocedureCode, o.hirprocedureName) from HireProceduresEntity o "),

        @NamedQuery(name = "HireProceduresEntity.searchByName",
                    query = "select o from HireProceduresEntity o where o.hirprocedureName like :name") })
@Table(name = "HR_EMP_HIRE_PROCEDURES")
@IdClass(IHireProceduresEntityKey.class)

public class HireProceduresEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HIRPROCEDURE_CODE", nullable = false)
    private Long hirprocedureCode;
    @Column(name = "HIRPROCEDURE_NAME", nullable = false)
    private String hirprocedureName;
    @Column(name = "MIN_CODE", nullable = false, insertable = true, updatable = true)
    private Long minCode;
    //    @Column(name = "GENDER_TYPE", nullable = false, insertable = false,
    //            updatable = false)
    //    private Long genderType;
    //    @Column(name = "NATIONALITY_TYPE", nullable = false)
    //    private Long nationalityType;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;
    @ManyToOne
    @JoinColumn(name = "MIN_CODE", referencedColumnName = "MIN_CODE", insertable = false, updatable = false)
    private EmpMinistriesEntity ministriesEntity;


    @OneToMany(mappedBy = "hireProcedureEntity")
    private List<HireTypeProcedureEntity> hireTypeProcedureEntityList;


    //    @ManyToOne
    //    @JoinColumn(name = "GENDER_TYPE", referencedColumnName = "GENTYPE_CODE")
    //    private GenderTypesEntity genderTypesEntity;

    @Column(name = "FROM_DATE", nullable = true)
    private Date fromDate;

    @Column(name = "UNTIL_DATE", nullable = true)
    private Date untilDate;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    /**
     * HireProceduresEntity Default Constructor
     */
    public HireProceduresEntity() {
    }


    /**
     * @return Long
     */
    public Long getHirprocedureCode() {
        return hirprocedureCode;
    }

    /**
     * @return String
     */
    public String getHirprocedureName() {
        return hirprocedureName;
    }

    /**
     * @return Long
     */
    public Long getMinCode() {
        return minCode;
    }

    //    /**
    //     * @return Long
    //     */
    //    public Long getGenderType() {
    //        return genderType;
    //    }

    //    /**
    //     * @return Long
    //     */
    //    public Long getNationalityType() {
    //        return nationalityType;
    //    }

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
     * @param hirprocedureCode
     */
    public void setHirprocedureCode(Long hirprocedureCode) {
        this.hirprocedureCode = hirprocedureCode;
    }

    /**
     * @param hirprocedureName
     */
    public void setHirprocedureName(String hirprocedureName) {
        this.hirprocedureName = hirprocedureName;
    }

    /**
     * @param minCode
     */
    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    //    /**
    //     * @param genderType
    //     */
    //    public void setGenderType(Long genderType) {
    //        this.genderType = genderType;
    //    }

    //    /**
    //     * @param nationalityType
    //     */
    //    public void setNationalityType(Long nationalityType) {
    //        this.nationalityType = nationalityType;
    //    }

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


    public void setMinistriesEntity(EmpMinistriesEntity ministriesEntity) {
        this.ministriesEntity = ministriesEntity;
    }

    public EmpMinistriesEntity getMinistriesEntity() {
        return ministriesEntity;
    }


    public void setHireTypeProcedureEntityList(List<HireTypeProcedureEntity> hireTypeProcedureEntityList) {
        this.hireTypeProcedureEntityList = hireTypeProcedureEntityList;
    }

    public List<HireTypeProcedureEntity> getHireTypeProcedureEntityList() {
        return hireTypeProcedureEntityList;
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
