package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.deploy.EmpCndSalaryElementsSession;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class EmpCndSalaryElementsClientImpl extends BaseClientImpl3 implements IEmpCndSalaryElementsClient {
    public EmpCndSalaryElementsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends EmpCndSalaryElementsSession> getSessionInterface() {
        return EmpCndSalaryElementsSession.class;
    }

    @Override
    protected EmpCndSalaryElementsSession SESSION() {
        return (EmpCndSalaryElementsSession)super.SESSION();
    }

    public IEmpCndSalaryElementsDTO getByCandCode(IEntityKey candidateCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().getByCandCode(RI(), candidateCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IEmpCandidateExtraDataDTO> getBounsForCandidae(IEmpCandidatesEntityKey ek) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {
            return SESSION().getBounsForCandidae(RI(), ek);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }
}
