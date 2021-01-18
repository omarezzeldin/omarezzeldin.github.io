package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.EntityKey;


public class EmpReasonDataEntityKey extends EntityKey implements IEmpReasonDataEntityKey {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    private Long restypeCode;
    private Long resdatSerial;


    public EmpReasonDataEntityKey() {
        super();
    }

    public EmpReasonDataEntityKey(Long restypeCode, Long resdatSerial) {
        super(new Object[] { restypeCode, resdatSerial });
        this.restypeCode = restypeCode;
        this.resdatSerial = resdatSerial;

    }

    public int hashCode() {
        return super.hashCode();
    }

    public Long getRestypeCode() {
        return restypeCode;
    }

    public Long getResdatSerial() {
        return resdatSerial;
    }

}
