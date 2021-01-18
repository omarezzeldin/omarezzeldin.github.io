package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;

public class EmpWorkPermitsEntityKey extends EntityKey implements IEmpWorkPermitsEntityKey {
    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;
    private Long serial;
    
    public EmpWorkPermitsEntityKey() {
        super();
    }
    
    public EmpWorkPermitsEntityKey(Long serial) {
        super(new Object[] { serial });
        this.serial = serial;

    }
    
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public Long getSerial() {
        return serial;
    }
}
