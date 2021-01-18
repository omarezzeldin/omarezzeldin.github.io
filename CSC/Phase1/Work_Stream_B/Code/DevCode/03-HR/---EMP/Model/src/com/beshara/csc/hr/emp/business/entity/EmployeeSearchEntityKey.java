package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmployeeSearchEntityKey extends EntityKey implements IEmployeeSearchEntityKey {


    @SuppressWarnings("compatibility:-7042676902511417941")
    private static final long serialVersionUID = 1L;

    private Long civilId;

    public EmployeeSearchEntityKey() {
        super();
    }

    public EmployeeSearchEntityKey(Long civilId) {
        super(new Object[] { civilId });
        this.civilId = civilId;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCivilId() {
        return civilId;
    }
}
