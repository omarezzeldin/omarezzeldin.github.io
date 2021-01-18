package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.deploy.EmployeeExtraDataCMTSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;


public class EmployeeExtraDataCMTClientImpl extends BaseClientImpl3 implements IEmployeeExtraDataCMTClient {

    public IBasicDTO addForEmployementCycleCMT(IBasicDTO employeesDTO1) throws RemoteException,
                                                                               SharedApplicationException,
                                                                               DataBaseException {

        try {
            return SESSION().addForEmployementCycleCMT(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public EmployeeExtraDataCMTClientImpl() {
        super();
    }

    protected Class<? extends BasicSession> getSessionInterface() {
        return EmployeeExtraDataCMTSession.class;
    }

    protected EmployeeExtraDataCMTSession SESSION() {
        return (EmployeeExtraDataCMTSession)super.SESSION();
    }

}
