package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class AssignStatusEntityKey extends EntityKey implements IAssignStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long assstatusCode;
    public AssignStatusEntityKey() {        super();
    }    public AssignStatusEntityKey(Long assstatusCode) {        super(new Object[] { assstatusCode });
        this.assstatusCode = assstatusCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getAssstatusCode() {        return assstatusCode;
    }}
