package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpWorkPermitsSession;
import com.beshara.csc.hr.emp.business.deploy.EmployeeExtraDataSession;
import com.beshara.csc.hr.emp.business.dto.IEmpWorkPermitsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.Collections;
import java.util.List;

public class EmpWorkPermitsClientImpl extends BaseClientImpl3 implements IEmpWorkPermitsClient {
    public EmpWorkPermitsClientImpl() {

    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpWorkPermitsSession.class;
    }

    @Override
    protected EmpWorkPermitsSession SESSION() {
        return (EmpWorkPermitsSession)super.SESSION();
    }

    public List<IEmpWorkPermitsDTO> getEmpWorkPermitsByCivilId(Long civilId) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            return SESSION().getEmpWorkPermitsByCivilId(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByMinCode(Long minCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().getAllEmpPermitsByMinCode(RI(), minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmpWorkPermitsDTO> getAllEmpPermitsByCivilAndMinCode(Long civilId,
                                                                      Long minCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return SESSION().getAllEmpPermitsByCivilAndMinCode(RI(), civilId, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


}


