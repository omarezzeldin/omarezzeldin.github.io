package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpEmployeeHistoriesSession;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeHistoriesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.Date;
import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class EmpEmployeeHistoriesClientImpl extends BaseClientImpl3 implements IEmpEmployeeHistoriesClient {

    /**
     * @param EmpEmployeeHistoriesSession
     */
    public EmpEmployeeHistoriesClientImpl() {
        super();
    }

    public List<IEmpEmployeeHistoriesDTO> getAllByCivilID(Long civilID, Long minCode) throws DataBaseException
                                                                                            {
        try {
            return SESSION().getAllByCivilID(RI(), civilID, minCode);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(); 
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> searchEmpEmployeeHistoriesDTO(IBasicDTO empEmployeeHistoriesDTO) throws DataBaseException{
        try {
            return SESSION().searchEmpEmployeeHistoriesDTO(RI(), empEmployeeHistoriesDTO);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(); 
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get all data for certain employee
     * @param civilID
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getEmployeeDataByCivilID(Long civilID) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().getEmployeeDataByCivilID(RI(), civilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get first hire date for employee
     * @param civilId
     * @return Date
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Date getFirstHireDate(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getFirstHireDate(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpEmployeeHistoriesSession.class;
    }

    protected EmpEmployeeHistoriesSession SESSION() {
        return (EmpEmployeeHistoriesSession)super.SESSION();
    }
}
