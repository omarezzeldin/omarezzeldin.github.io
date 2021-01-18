package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;


public class EmpExtraDataTypesEntityKey extends EntityKey implements IEmpExtraDataTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long extdattypeCode;


    public EmpExtraDataTypesEntityKey() {
        super();
    }

    public EmpExtraDataTypesEntityKey(Long extdattypeCode) {
        super(new Object[] { extdattypeCode });
        this.extdattypeCode = extdattypeCode;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getExtdattypeCode() {
        return extdattypeCode;
    }

}
