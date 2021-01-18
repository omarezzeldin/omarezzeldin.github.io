package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;


public class EmpReasonTypesEntityKey extends EntityKey implements IEmpReasonTypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


    private Long restypeCode;


    public EmpReasonTypesEntityKey() {
        super();
    }

    public EmpReasonTypesEntityKey(Long restypeCode) {
        super(new Object[] { restypeCode });
        this.restypeCode = restypeCode;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

}
