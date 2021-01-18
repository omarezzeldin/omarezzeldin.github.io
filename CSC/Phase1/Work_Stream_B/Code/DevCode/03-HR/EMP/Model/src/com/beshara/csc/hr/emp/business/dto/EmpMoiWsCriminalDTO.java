package com.beshara.csc.hr.emp.business.dto;

import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.EmpMoiWsCriminalEntity;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
 
public class EmpMoiWsCriminalDTO extends EmpClientDTO implements IEmpMoiWsCriminalDTO{

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    
    private Long crmWsSerial;
private Long userCode;
private Long civilId;
private String crmstatusCode;
private Timestamp lastLoginDate;


    /**
     * EmpMoiWsCriminalDTO Default Constructor
     */
    public EmpMoiWsCriminalDTO() {
        super();
    }



/**
* @return Long
*/
public Long getCrmWsSerial() {
   return crmWsSerial;
}
/**
* @return Long
*/
public Long getUserCode() {
   return userCode;
}
/**
* @return Long
*/
public Long getCivilId() {
   return civilId;
}
/**
* @return String
*/
public String getCrmstatusCode() {
   return crmstatusCode;
}
/**
* @return Timestamp
*/
public Timestamp getLastLoginDate() {
   return lastLoginDate;
}

    
/**
* @param crmWsSerial
*/
public void setCrmWsSerial(Long crmWsSerial) {
   this.crmWsSerial=crmWsSerial;
}
/**
* @param userCode
*/
public void setUserCode(Long userCode) {
   this.userCode=userCode;
}
/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param crmstatusCode
*/
public void setCrmstatusCode(String crmstatusCode) {
   this.crmstatusCode=crmstatusCode;
}
/**
* @param lastLoginDate
*/
public void setLastLoginDate(Timestamp lastLoginDate) {
   this.lastLoginDate=lastLoginDate;
}

    
}

