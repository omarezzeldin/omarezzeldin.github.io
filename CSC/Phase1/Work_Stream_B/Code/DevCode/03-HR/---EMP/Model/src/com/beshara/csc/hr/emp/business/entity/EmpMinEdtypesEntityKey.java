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
 
public class EmpMinEdtypesEntityKey extends EntityKey implements IEmpMinEdtypesEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private Long minCode;
    private Long extdattypeCode;
    
    
    public EmpMinEdtypesEntityKey() {
        super();
    }

    public EmpMinEdtypesEntityKey( Long minCode,  Long extdattypeCode ) {
            super ( new Object[] {  minCode,  extdattypeCode  } ); 
    this.minCode = minCode;
        this.extdattypeCode = extdattypeCode;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public Long getMinCode() { 
   return minCode;
    }      
public Long getExtdattypeCode() { 
   return extdattypeCode;
    }      

}
