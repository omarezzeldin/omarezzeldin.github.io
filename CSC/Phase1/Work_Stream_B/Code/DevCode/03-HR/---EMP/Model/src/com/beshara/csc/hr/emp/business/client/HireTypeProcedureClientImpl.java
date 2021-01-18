package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.deploy.HireTypeProcedureSession;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
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
public class HireTypeProcedureClientImpl extends BaseClientImpl3 implements IHireTypeProcedureClient {


    public HireTypeProcedureClientImpl() {
        super();
    }

    /**
     * get procedures not related to Hire Type * @param hireTypesEntityKey
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */

    public List<IHireProceduresDTO> getAvailableProcedures(IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            return SESSION().getAvailableProcedures(RI(), hireTypesEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IHireProceduresDTO> getAvailableProcedures() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAvailableProcedures(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IHireProceduresDTO> getActiveAvailableProcedures() throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().getActiveAvailableProcedures(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IHireProceduresDTO> filterAvailableProcedures(IEntityKey hireTypeCode,
                                                              String name) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().filterAvailableProcedures(RI(), hireTypeCode, name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IHireProceduresDTO> filterAvailableProcedures(IEntityKey hireTypeCode,
                                                              Long code) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().filterAvailableProcedures(RI(), hireTypeCode, code);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Long checkBeforeDeleteProcedure(Long hireProcedure, Long hireType) throws DataBaseException {
        try {
            return SESSION().checkBeforeDeleteProcedure(RI(), hireProcedure, hireType);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    protected Class<? extends BasicSession> getSessionInterface() {
        return HireTypeProcedureSession.class;
    }

    protected HireTypeProcedureSession SESSION() {
        return (HireTypeProcedureSession)super.SESSION();
    }
}
