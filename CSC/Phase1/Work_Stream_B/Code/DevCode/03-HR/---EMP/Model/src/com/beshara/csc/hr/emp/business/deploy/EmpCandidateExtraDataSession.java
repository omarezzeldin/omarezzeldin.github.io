package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import javax.ejb.Remote;


@Remote
public interface EmpCandidateExtraDataSession extends BasicSession {
    
    public String extraDataValueForType(IRequestInfoDTO ri,Long candCode, Long candSeq, Long extraDataType) throws  RemoteException, DataBaseException,
                                                                                                    SharedApplicationException;
}
