package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.EntityKey;

import java.sql.Timestamp;

public class AllEmployeesEntityKey extends EntityKey implements IAllEmployeesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Timestamp trxDatetime;

    public AllEmployeesEntityKey() {
        super();
    }

    public AllEmployeesEntityKey(Long civilId, Timestamp trxDatetime) {
        super(new Object[] { civilId, trxDatetime });
        this.civilId = civilId;
        this.trxDatetime = trxDatetime;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCivilId() {
        return civilId;
    }

    public Timestamp getTrxDatetime() {
        return trxDatetime;
    }
}
