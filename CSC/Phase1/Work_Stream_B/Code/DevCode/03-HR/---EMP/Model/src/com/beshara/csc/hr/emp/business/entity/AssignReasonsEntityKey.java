package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class AssignReasonsEntityKey extends EntityKey implements IAssignReasonsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long assreasonCode;
    public AssignReasonsEntityKey() {        super();
    }    public AssignReasonsEntityKey(Long assreasonCode) {        super(new Object[] { assreasonCode });
        this.assreasonCode = assreasonCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getAssreasonCode() {        return assreasonCode;
    }}
