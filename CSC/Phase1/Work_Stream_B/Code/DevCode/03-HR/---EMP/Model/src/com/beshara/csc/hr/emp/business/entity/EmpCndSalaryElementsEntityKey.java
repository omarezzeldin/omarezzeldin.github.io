package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmpCndSalaryElementsEntityKey extends EntityKey implements IEmpCndSalaryElementsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cndSalelmSerial;

    public EmpCndSalaryElementsEntityKey() {
        super();
    }

    public EmpCndSalaryElementsEntityKey(Long cndSalelmSerial) {
        super(new Object[] { cndSalelmSerial });
        this.cndSalelmSerial = cndSalelmSerial;
    }

    public void setCndSalelmSerial(Long cndSalelmSerial) {
        this.cndSalelmSerial = cndSalelmSerial;
    }

    public Long getCndSalelmSerial() {
        return cndSalelmSerial;
    }

    public int hashCode() {
        return super.hashCode();
    }
}
