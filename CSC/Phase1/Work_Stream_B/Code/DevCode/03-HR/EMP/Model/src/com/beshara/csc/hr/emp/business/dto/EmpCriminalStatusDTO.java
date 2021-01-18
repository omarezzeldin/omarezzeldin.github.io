package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCriminalStatusEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class EmpCriminalStatusDTO extends EmpClientDTO implements IEmpCriminalStatusDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private String crmstatusCode;
private String name ; 


    /**
     * EmpCriminalStatusDTO Default Constructor
     */
    public EmpCriminalStatusDTO() {
        super();
    }



/**
* @return String
*/
public String getCrmstatusCode() {
   return crmstatusCode;
}
/**
* @return String 
*/
public String getName(){
   return name ;
}

    
/**
* @param crmstatusCode
*/
public void setCrmstatusCode(String crmstatusCode) {
   this.crmstatusCode=crmstatusCode;
}
/**
* @param name
*/
public void setName( String name ){
   this.name = name;
}

    
}

