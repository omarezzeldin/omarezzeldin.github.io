package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;
import java.sql.Timestamp;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public interface IEmpCriminalStatusEntityKey extends IEntityKey {
    
    public int hashCode();
    
    String getCrmstatusCode() ; 

}
