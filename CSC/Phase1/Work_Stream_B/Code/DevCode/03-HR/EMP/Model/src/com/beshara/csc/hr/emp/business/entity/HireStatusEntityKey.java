package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class HireStatusEntityKey extends EntityKey implements IHireStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirstatusCode;
    public HireStatusEntityKey() {        super();
    }    public HireStatusEntityKey(Long hirstatusCode) {        super(new Object[] { hirstatusCode });
        this.hirstatusCode = hirstatusCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHirstatusCode() {        return hirstatusCode;
    }}
