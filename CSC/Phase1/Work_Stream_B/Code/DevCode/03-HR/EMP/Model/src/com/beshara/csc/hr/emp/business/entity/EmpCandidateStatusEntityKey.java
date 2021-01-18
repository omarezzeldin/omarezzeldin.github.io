package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmpCandidateStatusEntityKey extends EntityKey implements IEmpCandidateStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long cndstatusCode;

    public EmpCandidateStatusEntityKey() {
        super();
    }

    public EmpCandidateStatusEntityKey(Long cndstatusCode) {
        super(new Object[] { cndstatusCode });
        this.cndstatusCode = cndstatusCode;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getCndstatusCode() {
        return cndstatusCode;
    }
}
