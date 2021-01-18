package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpMinEdtypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Remote
public interface EmpMinEdtypesSession extends BasicSession {

    // Add your extra methods
    public List<IEmpMinEdtypesDTO> getByMinCode(IRequestInfoDTO ri , Long minCode) throws RemoteException , DataBaseException, SharedApplicationException;
    
}
