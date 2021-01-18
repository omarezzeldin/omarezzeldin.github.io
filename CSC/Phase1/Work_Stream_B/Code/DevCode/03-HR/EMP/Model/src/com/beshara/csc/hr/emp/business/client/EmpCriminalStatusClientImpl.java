package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.hr.emp.business.dto.EmpCriminalStatusDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpCriminalStatusSession;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.deploy.BasicSession;
import java.util.Collection;
import java.util.List;

import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class EmpCriminalStatusClientImpl extends BaseClientImpl3 implements IEmpCriminalStatusClient {

    private EmpCriminalStatusSession empCriminalStatusSession;

    /**
     * @param EmpCriminalStatusSession
     */
    public EmpCriminalStatusClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpCriminalStatusSession.class;
    }

    @Override
    protected EmpCriminalStatusSession SESSION() {
        return (EmpCriminalStatusSession)super.SESSION();
    }

}
