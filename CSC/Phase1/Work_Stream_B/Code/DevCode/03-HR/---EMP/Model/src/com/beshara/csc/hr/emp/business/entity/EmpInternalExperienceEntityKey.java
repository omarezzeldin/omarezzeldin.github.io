package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;


public class EmpInternalExperienceEntityKey extends EntityKey implements IEmpInternalExperienceEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long serial;


    public EmpInternalExperienceEntityKey() {
        super();
    }

    public EmpInternalExperienceEntityKey(Long serial) {
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
