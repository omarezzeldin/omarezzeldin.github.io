package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class ProcedureResultsEntityKey extends EntityKey implements IProcedureResultsEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long proresultCode;
    public ProcedureResultsEntityKey() {        super();
    }    public ProcedureResultsEntityKey(Long proresultCode) {        super(new Object[] { proresultCode });
        this.proresultCode = proresultCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getProresultCode() {        return proresultCode;
    }}
