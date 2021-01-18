package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class TrxTypesEntityKey extends EntityKey implements ITrxTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long trxtypeCode;
    public TrxTypesEntityKey() {        super();
    }    public TrxTypesEntityKey(Long trxtypeCode) {        super(new Object[] { trxtypeCode });
        this.trxtypeCode = trxtypeCode;
    }    public int hashCode() {        return super.hashCode();
    }    public Long getTrxtypeCode() {        return trxtypeCode;
    }}
