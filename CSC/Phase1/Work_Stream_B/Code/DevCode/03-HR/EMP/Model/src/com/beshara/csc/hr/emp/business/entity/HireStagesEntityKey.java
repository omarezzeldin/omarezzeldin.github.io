package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class HireStagesEntityKey extends EntityKey implements IHireStagesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirstageCode;
    public HireStagesEntityKey() {        super();
    }    public HireStagesEntityKey(Long hirstageCode) {        super(new Object[] { hirstageCode });
        this.hirstageCode = hirstageCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHirstageCode() {        return hirstageCode;
    }}
