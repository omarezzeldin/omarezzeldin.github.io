package com.beshara.csc.hr.emp.business.entity.sal;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity

@Table(name = "HR_SAL_ELEMENT_GUIDES")

public class EmpSalElementGuidesEntity implements Serializable{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ELMGUIDE_CODE", nullable = false)
    private Long elmguideCode;
    @Column(name = "EMLGUIDE_NAME", nullable = false)
    private String emlguideName;
    @Column(name = "ELMTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long elmtypeCode;
    @Column(name = "ELMPERTYPE_CODE", nullable = false, insertable = false, updatable = false)
    private Long elmpertypeCode;


    //    @Column(name = "ELMGUIDE_CODE_BUDGET", nullable = true, insertable = false,
    //            updatable = false)
    //    private Long elmguideCodeBudget;
    @Column(name = "LEAF_FLAG_DEGREE", nullable = false)
    private Long leafFlagDegree;
    @Column(name = "TREE_LEVEL_DEGREE", nullable = false)
    private Long treeLevelDegree;
    @ManyToOne
    @JoinColumn(name = "ELMGUIDE_CODE_DEGREE", referencedColumnName = "ELMGUIDE_CODE", insertable = false,
                updatable = false)
    private EmpSalElementGuidesEntity salElementGuidesEntityDegree;


    @Column(name = "ELMGUIDE_CODE_DEGREE", nullable = true, insertable = true, updatable = true)
    private Long elmguideCodeDegree;


    /**
     * SalElementGuidesEntity Default Constructor
     */
    public EmpSalElementGuidesEntity() {
    }


    /**
     * @return Long
     */
    public Long getElmguideCode() {
        return elmguideCode;
    }

    /**
     * @return String
     */
    public String getEmlguideName() {
        return emlguideName;
    }

    /**
     * @return Long
     */
    public Long getElmtypeCode() {
        return elmtypeCode;
    }

    /**
     * @return Long
     */
    public Long getElmpertypeCode() {
        return elmpertypeCode;
    }


    /**
     * @return Long
     */
    public Long getElmguideCodeDegree() {
        return elmguideCodeDegree;
    }

    /**
     * @return Long
     */
    /* public Long getElmguideCodeBudget() {
        return elmguideCodeBudget;
    } */

    /**
     * @return Long
     */
    public Long getLeafFlagDegree() {
        return leafFlagDegree;
    }

    /**
     * @return Long
     */
    public Long getTreeLevelDegree() {
        return treeLevelDegree;
    }


    /**
     * @param elmguideCode
     */
    public void setElmguideCode(Long elmguideCode) {
        this.elmguideCode = elmguideCode;
    }

    /**
     * @param emlguideName
     */
    public void setEmlguideName(String emlguideName) {
        this.emlguideName = emlguideName;
    }

    /**
     * @param elmtypeCode
     */
    public void setElmtypeCode(Long elmtypeCode) {
        this.elmtypeCode = elmtypeCode;
    }

    /**
     * @param elmpertypeCode
     */
    public void setElmpertypeCode(Long elmpertypeCode) {
        this.elmpertypeCode = elmpertypeCode;
    }


    /**
     * @param elmguideCodeDegree
     */
    public void setElmguideCodeDegree(Long elmguideCodeDegree) {
        this.elmguideCodeDegree = elmguideCodeDegree;
    }

    /**
     * @param elmguideCodeBudget
     */
    /* public void setElmguideCodeBudget(Long elmguideCodeBudget) {
        this.elmguideCodeBudget = elmguideCodeBudget;
    } */

    /**
     * @param leafFlagDegree
     */
    public void setLeafFlagDegree(Long leafFlagDegree) {
        this.leafFlagDegree = leafFlagDegree;
    }

    /**
     * @param treeLevelDegree
     */
    public void setTreeLevelDegree(Long treeLevelDegree) {
        this.treeLevelDegree = treeLevelDegree;
    }


    public void setSalElementGuidesEntityDegree(EmpSalElementGuidesEntity salElementGuidesEntityDegree) {
        this.salElementGuidesEntityDegree = salElementGuidesEntityDegree;
    }

    public EmpSalElementGuidesEntity getSalElementGuidesEntityDegree() {
        return salElementGuidesEntityDegree;
    }


}
