package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class HireProceduresEntityKey extends EntityKey implements IHireProceduresEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirprocedureCode;
    public HireProceduresEntityKey() {        super();
    }    public HireProceduresEntityKey(Long hirprocedureCode) {        super(new Object[] { hirprocedureCode });
        this.hirprocedureCode = hirprocedureCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHirprocedureCode() {        return hirprocedureCode;
    }}
