package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class HireTypesEntityKey extends EntityKey implements IHireTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirtypeCode;
    public HireTypesEntityKey() {        super();
    }    public HireTypesEntityKey(Long hirtypeCode) {        super(new Object[] { hirtypeCode });
        this.hirtypeCode = hirtypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHirtypeCode() {        return hirtypeCode;
    }}
