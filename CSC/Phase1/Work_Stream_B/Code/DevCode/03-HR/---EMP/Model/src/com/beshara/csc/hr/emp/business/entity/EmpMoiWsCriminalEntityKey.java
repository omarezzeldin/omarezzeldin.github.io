package com.beshara.csc.hr.emp.business.entity;

import java.io.Serializable;
import com.beshara.base.entity.EntityKey;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
public class EmpMoiWsCriminalEntityKey extends EntityKey implements IEmpMoiWsCriminalEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long crmWsSerial;
    
    
    public EmpMoiWsCriminalEntityKey() {
        super();
    }

    public EmpMoiWsCriminalEntityKey( Long crmWsSerial ) {
            super ( new Object[] {  crmWsSerial  } ); 
    this.crmWsSerial = crmWsSerial;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getCrmWsSerial() { 
   return crmWsSerial;
    }      

}
