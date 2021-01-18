
package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Date;

import java.util.List;

import javax.persistence.CascadeType;
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
 * This Class Manipulate the Persistence Methods of HireTypes Entity.
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
@NamedQueries( { @NamedQuery(name = "HireTypesEntity.findAll",
                             query = "select o from HireTypesEntity o where o.status <> :nonActive"),
                 @NamedQuery(name = "HireTypesEntity.getFirstLevelHireTypes",
                             query = "select o from HireTypesEntity o where o.treeLevel = :treeLevel AND o.status = :status"),
                 @NamedQuery(name = "HireTypesEntity.findNewId",
                             query = "select MAX(o.hirtypeCode) from HireTypesEntity o"),
                 @NamedQuery(name = "HireTypesEntity.getCodeName",
                             query = "select new com.beshara.csc.hr.emp.business.dto.HireTypesDTO(o.hirtypeCode, o.hirtypeName) from HireTypesEntity o order by o.hirtypeName"),
                 @NamedQuery(name = "HireTypesEntity.searchByName",
                             query = "select o from HireTypesEntity o where o.hirtypeName like :name"),
                 @NamedQuery(name = "HireTypesEntity.getByTreeLevelHireTypes",
                             query = "select o from HireTypesEntity o where o.treeLevel = :treeLevel and (o.status = 1 or o.hirtypeCode = 9)") })
@Table(name = "HR_EMP_HIRE_TYPES")
@IdClass(IHireTypesEntityKey.class)

public class HireTypesEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "HIRTYPE_CODE", nullable = false)
    private Long hirtypeCode;
    @Column(name = "HIRTYPE_NAME", nullable = false)
    private String hirtypeName;
    @Column(name = "TREE_LEVEL", nullable = false)
    private Long treeLevel;
    @Column(name = "AUDIT_STATUS", nullable = true)
    private Long auditStatus;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    @Column(name = "STATUS", nullable = false)
    private Long status;


    //@Column(name="CONDITION_CODE", nullable=true ,insertable=false,updatable=false )
    //private Long conditionCode;
    //@ManyToOne
    //@JoinColumn(name = "CONDITION_CODE",  referencedColumnName = "CONDITION_CODE")
    //private ConditionsEntity conditionsEntity;
    @OneToMany(mappedBy = "hireTypesEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<RequiredDocumentsEntity> requiredDocumentsEntity;


    @OneToMany(mappedBy = "hireTypesEntity",
               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.ALL })
    private List<HireTypesMinistriesEntity> hireTypesMinistriesList;

    //    @OneToMany(mappedBy = "hireTypesEntity",
    //               cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<HireTypeConditionsEntity> hireTypesConditionsList;
    @OneToMany(mappedBy = "hireTypesEntity", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    private List<HireTypeProcedureEntity> hireTypeProcedureEntity;


    @Column(name = "FIRST_PARENT", nullable = false)
    private Long firstParent;

    @Column(name = "LEAF_FLAG", nullable = false)
    private Long leafFlag;


    @Column(name = "DECISION_FLAG", nullable = false)
    private Long decisionFlag;
    @Column(name = "PARENT_HIRTYPE_CODE", nullable = true, insertable = false, updatable = false)
    private Long parentHireTypeCode;

    @ManyToOne
    @JoinColumn(name = "PARENT_HIRTYPE_CODE", referencedColumnName = "HIRTYPE_CODE")
    private HireTypesEntity hireTypeEntity;

    @Column(name = "FROM_DATE", nullable = false)
    private Date fromDate;


    /**
     * HireTypesEntity Default Constructor
     */
    public HireTypesEntity() {

    }


    /**
     * @return Long
     */
    public Long getHirtypeCode() {
        return hirtypeCode;
    }

    /**
     * @return String
     */
    public String getHirtypeName() {
        return hirtypeName;
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
     * @param hirtypeCode
     */
    public void setHirtypeCode(Long hirtypeCode) {
        this.hirtypeCode = hirtypeCode;
    }

    /**
     * @param hirtypeName
     */
    public void setHirtypeName(String hirtypeName) {
        this.hirtypeName = hirtypeName;
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


    public void setRequiredDocumentsEntity(List<RequiredDocumentsEntity> requiredDocumentsEntity) {
        this.requiredDocumentsEntity = requiredDocumentsEntity;
    }

    public List<RequiredDocumentsEntity> getRequiredDocumentsEntity() {
        return requiredDocumentsEntity;
    }

    //    public void setConditionCode(Long conditionCode) {
    //        this.conditionCode = conditionCode;
    //    }

    //    public Long getConditionCode() {
    //        return conditionCode;
    //    }

    //    public void setConditionsEntity(ConditionsEntity conditionsEntity) {
    //        this.conditionsEntity = conditionsEntity;
    //    }
    //
    //    public ConditionsEntity getConditionsEntity() {
    //        return conditionsEntity;
    //    }


    public void setHireTypesMinistriesList(List<HireTypesMinistriesEntity> hireTypesMinistriesList) {
        this.hireTypesMinistriesList = hireTypesMinistriesList;
    }

    public List<HireTypesMinistriesEntity> getHireTypesMinistriesList() {
        return hireTypesMinistriesList;
    }


    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setFirstParent(Long firstParent) {
        this.firstParent = firstParent;
    }

    public Long getFirstParent() {
        return firstParent;
    }

    public void setLeafFlag(Long leafFlag) {
        this.leafFlag = leafFlag;
    }

    public Long getLeafFlag() {
        return leafFlag;
    }

    public void setParentHireTypeCode(Long parentHireTypeCode) {
        this.parentHireTypeCode = parentHireTypeCode;
    }

    public Long getParentHireTypeCode() {
        return parentHireTypeCode;
    }

    public void setHireTypeEntity(HireTypesEntity hireTypeEntity) {
        this.hireTypeEntity = hireTypeEntity;
    }

    public HireTypesEntity getHireTypeEntity() {
        return hireTypeEntity;
    }


    public void setHireTypesConditionsList(List<HireTypeConditionsEntity> hireTypesConditionsList) {
        this.hireTypesConditionsList = hireTypesConditionsList;
    }

    public List<HireTypeConditionsEntity> getHireTypesConditionsList() {
        return hireTypesConditionsList;
    }

    public void setHireTypeProcedureEntity(List<HireTypeProcedureEntity> hireTypeProcedureEntity) {
        this.hireTypeProcedureEntity = hireTypeProcedureEntity;
    }

    public List<HireTypeProcedureEntity> getHireTypeProcedureEntity() {
        return hireTypeProcedureEntity;
    }


    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }


    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setDecisionFlag(Long decisionFlag) {
        this.decisionFlag = decisionFlag;
    }

    public Long getDecisionFlag() {
        return decisionFlag;
    }
}
