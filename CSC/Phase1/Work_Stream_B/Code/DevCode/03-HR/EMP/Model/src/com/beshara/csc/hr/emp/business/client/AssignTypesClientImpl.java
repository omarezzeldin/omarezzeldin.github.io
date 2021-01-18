package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.deploy.AssignTypesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class AssignTypesClientImpl extends BaseClientImpl3 implements IAssignTypesClient {


    public AssignTypesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return AssignTypesSession.class;
    }

    protected AssignTypesSession SESSION() {
        return (AssignTypesSession)super.SESSION();
    }

    /**
     * @return List
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException {
        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
}
