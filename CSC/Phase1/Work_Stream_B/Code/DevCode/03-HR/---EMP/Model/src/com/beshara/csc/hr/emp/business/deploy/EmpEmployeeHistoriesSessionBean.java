package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpEmployeeHistoriesDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeHistoriesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEmployeeHistoriesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "EmpEmployeeHistoriesSession", mappedName = "Emp-Model-EmpEmployeeHistoriesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpEmployeeHistoriesSessionBean extends EmpBaseSessionBean implements EmpEmployeeHistoriesSession { //@PersistenceContext ( unitName = "Emp" )


    /**
     * JobsSession Default Constructor */
    public EmpEmployeeHistoriesSessionBean() {
        super();
    }

    /**
     * get first hire date for employee
     * @param civilId
     * @return Date
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    private Date getFirstHireDate(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().getFirstHireDate(civilId);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    //**************************************************************************

    public List<IEmpEmployeeHistoriesDTO> getAllByCivilID(IRequestInfoDTO requestInfo, Long civilID,
                                                          Long minCode) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            return DAO().getAllByCivilID(civilID, minCode);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    public List<IBasicDTO> searchEmpEmployeeHistoriesDTO(IRequestInfoDTO requestInfo,
                                                         IBasicDTO empEmployeeHistoriesDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            return DAO().searchEmpEmployeeHistoriesDTO(empEmployeeHistoriesDTO);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }


    /**
     * get all data for certain employee
     * @param civilID
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getEmployeeDataByCivilID(IRequestInfoDTO requestInfo,
                                                    Long civilID) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return DAO().getEmployeeDataByCivilID(civilID);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    /**
     * get first hire date for employee
     * @param civilId
     * @return Date
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Date getFirstHireDate(IRequestInfoDTO requestInfo, Long civilId) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return DAO().getFirstHireDate(civilId);
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpEmployeeHistoriesEntity.class;
    }

    @Override
    protected EmpEmployeeHistoriesDAO DAO() {
        return (EmpEmployeeHistoriesDAO)super.DAO();
    }
}
