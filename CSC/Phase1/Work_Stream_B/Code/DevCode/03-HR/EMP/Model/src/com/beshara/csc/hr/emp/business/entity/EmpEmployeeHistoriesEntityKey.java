package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmpEmployeeHistoriesEntityKey extends EntityKey implements IEmpEmployeeHistoriesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long serial;
    public EmpEmployeeHistoriesEntityKey() {        super();
    }    public EmpEmployeeHistoriesEntityKey(Long serial) {        super(new Object[] { serial });
        this.serial = serial;
    }    public int hashCode() {        return super.hashCode();
    }}
