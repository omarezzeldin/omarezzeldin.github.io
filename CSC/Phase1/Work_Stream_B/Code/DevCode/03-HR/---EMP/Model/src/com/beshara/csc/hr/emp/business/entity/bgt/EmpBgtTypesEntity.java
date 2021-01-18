package com.beshara.csc.hr.emp.business.entity.bgt;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "HR_BGT_TYPES")
public class EmpBgtTypesEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "TYPE_CODE", nullable = false)
    private Long typeCode;
    @Column(name = "TYPE_NAME", nullable = false)
    private String typeName;
    @Column(name = "CONDITION_CODE", nullable = true, insertable = false, updatable = false)
    private Long conditionCode;


    /**
     * BgtTypesEntity Default Constructor
     */
    public EmpBgtTypesEntity() {
    }

    /**
     * @return Long
     */
    public Long getTypeCode() {
        return typeCode;
    }

    /**
     * @return String
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @return Long
     */
    public Long getConditionCode() {
        return conditionCode;
    }


    /**
     * @param typeCode
     */
    public void setTypeCode(Long typeCode) {
        this.typeCode = typeCode;
    }

    /**
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @param conditionCode
     */
    public void setConditionCode(Long conditionCode) {
        this.conditionCode = conditionCode;
    }


}

