package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.BaseClientImpl2;
import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.deploy.IContextSession;
import com.beshara.csc.hr.emp.business.deploy.EmpCandidateStatusSession;
import com.beshara.csc.hr.emp.business.deploy.EmployeeExtraDataSession;


public class EmpCandidateStatusClientImpl extends BaseClientImpl3 implements IEmpCandidateStatusClient {

    public EmpCandidateStatusClientImpl() {
        super();
    }



    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpCandidateStatusSession.class;
    }

    @Override
    protected EmpCandidateStatusSession SESSION() {
        return (EmpCandidateStatusSession)super.SESSION();
    }

}


