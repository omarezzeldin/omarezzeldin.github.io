package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class HireTypeConditionsEntityKey extends EntityKey implements IHireTypeConditionsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long conditionCode;
//    private Long serial;

    public HireTypeConditionsEntityKey() {
        super();
    }

    public HireTypeConditionsEntityKey(Long conditionCode) {
        super(new Object[] { conditionCode});
        this.conditionCode = conditionCode;
//        this.serial = serial;
    }

    public int hashCode() {
        return super.hashCode();
    }


    public void setConditionCode(Long conditionCode) {
        this.conditionCode = conditionCode;
    }

    public Long getConditionCode() {
        return conditionCode;
    }
}
