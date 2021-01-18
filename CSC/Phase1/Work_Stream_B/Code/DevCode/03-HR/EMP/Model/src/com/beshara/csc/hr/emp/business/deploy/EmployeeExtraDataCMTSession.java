package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import javax.ejb.Remote;


@Remote
public interface EmployeeExtraDataCMTSession extends BasicSession {
    public IBasicDTO addForEmployementCycleCMT(IRequestInfoDTO reqInfo ,IBasicDTO employeeExtraDataDTO) throws RemoteException,
                                                                                                  DataBaseException,
                                                                                                  SharedApplicationException;



}
