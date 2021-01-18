package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;


public interface IEmployeeExtraDataCMTClient extends IBasicClient {
    public IBasicDTO addForEmployementCycleCMT(IBasicDTO allEmployeesDTO) throws RemoteException, 
                                                           SharedApplicationException, 
                                                           DataBaseException;
}
