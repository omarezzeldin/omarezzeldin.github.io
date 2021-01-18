package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.EntityKey;

import java.math.BigDecimal;


public class EmpExternalExperienceEntityKey extends EntityKey implements IEmpExternalExperienceEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private BigDecimal serial;
    
    
    public EmpExternalExperienceEntityKey() {
        super();
    }

    public EmpExternalExperienceEntityKey( BigDecimal serial ) {
            super ( new Object[] {  serial  } ); 
    this.serial = serial;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }

    public BigDecimal getSerial() { 
   return serial;
    }      

}
