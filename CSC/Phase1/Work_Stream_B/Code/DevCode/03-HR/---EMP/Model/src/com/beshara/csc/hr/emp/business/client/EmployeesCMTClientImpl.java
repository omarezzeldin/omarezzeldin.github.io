package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.deploy.EmployeesSession;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Date;


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
public class EmployeesCMTClientImpl extends BaseClientImpl3 implements IEmployeesCMTClient {

    /** 
     * @param contextSession 
     */
    public EmployeesCMTClientImpl() {
        super();
    }

    public Boolean updateEmployeeDataForMov(Long civilId, 
                                            Long employeeHireStatus, 
                                            Date endJobDate) throws DataBaseException, 
                                                                    SharedApplicationException {
        try {
            return SESSION().updateEmployeeDataForMov(RI(), 
                                                             civilId, 
                                                             employeeHireStatus, 
                                                             endJobDate);
        }  catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws DataBaseException, 
                                                                           SharedApplicationException {
        try {
            return SESSION().addEmployeeForMovOnly(RI(), 
                                                          employeesDTO1);
        }  catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException {
        try {
            return SESSION().isMinistryFileNoExist(RI(), 
                                                          ministryFileNo);
        }  catch (SharedApplicationException e) {
            throw new RuntimeException( e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean isEmployeeExist(Long civilId) throws DataBaseException {
        try {
            return SESSION().isEmployeeExist(RI(), civilId);
        }  catch (SharedApplicationException e) {
            throw new RuntimeException( e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Boolean executeStartWorkCMT(IBasicDTO employeesDTO1) throws SharedApplicationException, 
                                                                       DataBaseException {
        try {
            return SESSION().executeStartWorkCMT(RI(), 
                                                        employeesDTO1);
        }  catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO updateEmpCMT(IBasicDTO employeesDTO1) throws SharedApplicationException, 
                                                                  DataBaseException {
        try {
            return SESSION().updateEmpCMT(RI(), 
                                                 employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    protected Class<? extends BasicSession> getSessionInterface() {
        return EmployeesSession.class;
    }
    
    protected EmployeesSession SESSION() {
        return (EmployeesSession)super.SESSION();

    }
    
    public IBasicDTO addEmployeeForExecuteMovOnly(IBasicDTO employeesDTO1) throws DataBaseException, 
                                                                           SharedApplicationException {
        try {
            return SESSION().addEmployeeForExecuteMovOnly(RI(), 
                                                          employeesDTO1);
        }  catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
   
 
}
