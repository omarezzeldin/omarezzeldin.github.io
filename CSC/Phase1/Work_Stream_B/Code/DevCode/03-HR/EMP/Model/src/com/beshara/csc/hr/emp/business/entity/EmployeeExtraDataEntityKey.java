package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;


public class EmployeeExtraDataEntityKey extends EntityKey implements IEmployeeExtraDataEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long serial;


    public EmployeeExtraDataEntityKey() {
        super();
    }

    public EmployeeExtraDataEntityKey(Long serial) {
        super(new Object[] { serial });
        this.serial = serial;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getSerial() {
        return serial;
    }

}
