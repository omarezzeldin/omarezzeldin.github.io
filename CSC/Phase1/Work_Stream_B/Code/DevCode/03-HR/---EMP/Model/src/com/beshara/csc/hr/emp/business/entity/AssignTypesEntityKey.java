package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class AssignTypesEntityKey extends EntityKey implements IAssignTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long asstypeCode;
    public AssignTypesEntityKey() {        super();
    }    public AssignTypesEntityKey(Long asstypeCode) {        super(new Object[] { asstypeCode });
        this.asstypeCode = asstypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getAsstypeCode() {        return asstypeCode;
    }}
