package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import javax.ejb.Remote;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */
 
@Remote
public interface EmpMoiWsCriminalSession extends BasicSession {

    // Add your extra methods
    public Long getCriminalCount(IRequestInfoDTO ri , Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;
}
