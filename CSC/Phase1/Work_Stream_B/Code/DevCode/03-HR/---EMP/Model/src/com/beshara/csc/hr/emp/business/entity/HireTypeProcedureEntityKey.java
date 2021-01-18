package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class HireTypeProcedureEntityKey extends EntityKey implements IHireTypeProcedureEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long hirtypeCode;
    private Long hirTypeProcCode;
    public HireTypeProcedureEntityKey() {        super();
    }    public HireTypeProcedureEntityKey(Long hirtypeCode, Long hirTypeProcCode) {        super(new Object[] { hirtypeCode, hirTypeProcCode });
        this.hirtypeCode = hirtypeCode;
        this.hirTypeProcCode = hirTypeProcCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getHirtypeCode() {        return hirtypeCode;
    }    public Long getHirTypeProcCode() {        return hirTypeProcCode;
    }}
