package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.hr.emp.business.deploy.EmpMoiWsCriminalSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class EmpMoiWsCriminalClientImpl extends BaseClientImpl3 implements IEmpMoiWsCriminalClient {

    private EmpMoiWsCriminalSession empMoiWsCriminalSession;

    /**
     * @param EmpMoiWsCriminalSession
     */
    public EmpMoiWsCriminalClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpMoiWsCriminalSession.class;
    }

    @Override
    protected EmpMoiWsCriminalSession SESSION() {
        return (EmpMoiWsCriminalSession)super.SESSION();
    }
    
    public Long getCriminalCount(Long civilId) throws DataBaseException, SharedApplicationException{
        try{
            return SESSION().getCriminalCount(RI(),civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
