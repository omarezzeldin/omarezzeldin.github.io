package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmployeesEntityKey extends EntityKey implements IEmployeesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    public EmployeesEntityKey() {        super();
    }    public EmployeesEntityKey(Long civilId) {        super(new Object[] { civilId });
        this.civilId = civilId;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getCivilId() {        return civilId;
    }}
