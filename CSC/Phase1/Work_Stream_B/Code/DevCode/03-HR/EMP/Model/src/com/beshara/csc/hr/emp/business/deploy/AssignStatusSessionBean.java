package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.AssignStatusDAO;
import com.beshara.csc.hr.emp.business.entity.AssignStatusEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
@Stateless(name = "AssignStatusSession", mappedName = "Emp-Model-AssignStatusSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class AssignStatusSessionBean extends EmpBaseSessionBean implements AssignStatusSession { //@PersistenceContext ( unitName = "Emp" )


    /**
     * JobsSession Default Constructor */
    public AssignStatusSessionBean() {
        super();
    }


    public List<IBasicDTO> getCodeName(IRequestInfoDTO requestInfo) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return DAO().getCodeName();
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
     * Get all Assign status with code equal code parameter * @param code
     * @return List
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri,Object code) throws DataBaseException, SharedApplicationException {
        try {
            if (code instanceof Long) {
                try {
                    return DAO().searchByCode(code);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                }
            } else {
                throw new InvalidDataEntryException();
            }
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
        return AssignStatusEntity.class;
    }

    protected AssignStatusDAO DAO() {
        return (AssignStatusDAO)super.DAO();
    }
}
