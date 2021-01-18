package com.beshara.csc.hr.emp.business.entity.reg;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "NL_REG_DECISIONS")
public class EmpRegDecisionsEntity implements Serializable {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "DECISION_NUMBER", nullable = false)
    private Long decisionNumber;

    @Id
    @Column(name = "DECTYPE_CODE", nullable = false, insertable = true, updatable = true)
    private Long dectypeCode;
    @Id
    @Column(name = "DECYEAR_CODE", nullable = false)
    private Long decyearCode;


    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "PARENT_DECISION_NUMBER", referencedColumnName = "DECISION_NUMBER",
                                insertable = false, updatable = false),
                    @JoinColumn(name = "PARENT_DECISION_YEAR", referencedColumnName = "DECYEAR_CODE",
                                insertable = false, updatable = false),
                    @JoinColumn(name = "PARENT_DECISION_TYPE", referencedColumnName = "DECTYPE_CODE",
                                insertable = false, updatable = false) })
    private EmpRegDecisionsEntity decisionsEntity;


    public EmpRegDecisionsEntity() {
    }


    public void setDecisionNumber(Long decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public Long getDecisionNumber() {
        return decisionNumber;
    }


    public void setDectypeCode(Long dectypeCode) {
        this.dectypeCode = dectypeCode;
    }

    public Long getDectypeCode() {
        return dectypeCode;
    }

    public void setDecyearCode(Long decyearCode) {
        this.decyearCode = decyearCode;
    }

    public Long getDecyearCode() {
        return decyearCode;
    }


    public void setDecisionsEntity(EmpRegDecisionsEntity decisionsEntity) {
        this.decisionsEntity = decisionsEntity;
    }

    public EmpRegDecisionsEntity getDecisionsEntity() {
        return decisionsEntity;
    }


    //    public void setYearsEntity(YearsEntity yearsEntity) {
    //        this.yearsEntity = yearsEntity;
    //    }
    //
    //    public YearsEntity getYearsEntity() {
    //        return yearsEntity;
    //    }


    //    public void setDecisionMakerTypesEntity(DecisionMakerTypesEntity decisionMakerTypesEntity) {
    //        this.decisionMakerTypesEntity = decisionMakerTypesEntity;
    //    }
    //
    //    public DecisionMakerTypesEntity getDecisionMakerTypesEntity() {
    //        return decisionMakerTypesEntity;
    //    }


    //    public void setUserCode(Long userCode) {
    //        this.userCode = userCode;
    //    }
    //
    //    public Long getUserCode() {
    //        return userCode;
    //    }
    //


    //    public void setMinistriesEntity(MinistriesEntity ministriesEntity) {
    //        this.ministriesEntity = ministriesEntity;
    //    }
    //
    //    public MinistriesEntity getMinistriesEntity() {
    //        return ministriesEntity;
    //    }


}
