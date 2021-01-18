package com.beshara.csc.hr.emp.business.client;

import com.beshara.base.client.BaseClientImpl3;
import com.beshara.csc.hr.emp.business.dto.EmpMinEdtypesDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpMinEdtypesSession;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.hr.emp.business.dto.IEmpMinEdtypesDTO;

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

public class EmpMinEdtypesClientImpl extends BaseClientImpl3 implements IEmpMinEdtypesClient {

    private EmpMinEdtypesSession empMinEdtypesSession;

    /**
     * @param EmpMinEdtypesSession
     */
    public EmpMinEdtypesClientImpl() {
    }
	
	@Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpMinEdtypesSession.class;
    }

    @Override
    protected EmpMinEdtypesSession SESSION() {
        return (EmpMinEdtypesSession)super.SESSION();
    }


    public List<IEmpMinEdtypesDTO> getByMinCode(Long minCode) throws DataBaseException, SharedApplicationException{
        
            try {
                return SESSION().getByMinCode(RI(), minCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
            
        
        
        }
}
