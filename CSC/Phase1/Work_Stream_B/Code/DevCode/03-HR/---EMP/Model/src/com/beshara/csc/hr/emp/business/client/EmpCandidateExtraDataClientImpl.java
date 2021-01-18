package com.beshara.csc.hr.emp.business.client;



import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;

import com.beshara.csc.hr.emp.business.deploy.EmpCandidateExtraDataSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


public class EmpCandidateExtraDataClientImpl extends BaseClientImpl3 implements IEmpCandidateExtraDataClient {
    public EmpCandidateExtraDataClientImpl() {
        super();
    }
    @Override
        protected Class<? extends BasicSession> getSessionInterface() {
            return EmpCandidateExtraDataSession.class;
        }
    @Override
    protected EmpCandidateExtraDataSession SESSION(){
       return (EmpCandidateExtraDataSession)super.SESSION();
    }
    
    public String extraDataValueForType(Long candCode, Long candSeq, Long extraDataType) throws DataBaseException,
    SharedApplicationException{
        
            try {
                return SESSION().extraDataValueForType(RI(), candCode,candSeq,extraDataType);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }
    
}
