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
 
public class EmpCriminalStatusEntityKey extends EntityKey implements IEmpCriminalStatusEntityKey {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;
    
    private String crmstatusCode;
    
    
    public EmpCriminalStatusEntityKey() {
        super();
    }

    public EmpCriminalStatusEntityKey( String crmstatusCode ) {
            super ( new Object[] {  crmstatusCode  } ); 
    this.crmstatusCode = crmstatusCode;
        
    }
    
    public int hashCode(){
        return super.hashCode();
    }
    
    public String getCrmstatusCode() { 
   return crmstatusCode;
    }      

}
