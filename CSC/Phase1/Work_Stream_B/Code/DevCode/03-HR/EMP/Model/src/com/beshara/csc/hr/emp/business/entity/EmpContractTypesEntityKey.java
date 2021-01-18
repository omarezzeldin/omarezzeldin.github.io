package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmpContractTypesEntityKey extends EntityKey implements IEmpContractTypesEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    private Long cntTypeCode;

    public EmpContractTypesEntityKey() {
        super();
    }

    public EmpContractTypesEntityKey(Long cntTypeCode) {
        super(new Object[] { cntTypeCode });
        this.cntTypeCode = cntTypeCode;
    }

    public int hashCode() {
        return super.hashCode();
    }


    public Long getCntTypeCode() {
        return cntTypeCode;
    }

}
