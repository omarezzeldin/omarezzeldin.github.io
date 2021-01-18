package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateProceduresDAO;
import com.beshara.csc.hr.emp.business.dao.HireTypeProcedureDAO;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
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
@Stateless(name = "HireTypeProcedureSession", mappedName = "Emp-Model-HireTypeProcedureSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HireTypeProcedureSessionBean extends EmpBaseSessionBean implements HireTypeProcedureSession { //@PersistenceContext ( unitName = "Emp" )


    /**
     * JobsSession Default Constructor */
    public HireTypeProcedureSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HireTypeProcedureEntity.class;
    }

    @Override
    protected HireTypeProcedureDAO DAO() {
        return (HireTypeProcedureDAO)super.DAO();
    }

    /**
     * Get all Procedure not related to Hire Type * @param hireTypesEntityKey
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IHireProceduresDTO> getAvailableProcedures(IRequestInfoDTO ri,
                                                           IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                 SharedApplicationException {

        try {
            return DAO().getAvailableProcedures(hireTypesEntityKey);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
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

    public List<IHireProceduresDTO> getAvailableProcedures(IRequestInfoDTO ri) throws DataBaseException,
                                                                                      SharedApplicationException {

        try {
            return DAO().getAvailableProcedures();
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
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

    public List<IHireProceduresDTO> getActiveAvailableProcedures(IRequestInfoDTO ri) throws DataBaseException,
                                                                                            SharedApplicationException {

        try {
            return DAO().getActiveAvailableProcedures();
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
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

    public List<IHireProceduresDTO> filterAvailableProcedures(IRequestInfoDTO ri, IEntityKey hireTypeCode,
                                                              Long code) throws DataBaseException,
                                                                                SharedApplicationException {

        try {
            return DAO().filterAvailableProcedures(hireTypeCode, null, code);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
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

    public List<IHireProceduresDTO> filterAvailableProcedures(IRequestInfoDTO ri, IEntityKey hireTypeCode,
                                                              String name) throws DataBaseException,
                                                                                  SharedApplicationException {

        try {
            return DAO().filterAvailableProcedures(hireTypeCode, name, null);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
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

    public Long checkBeforeDeleteProcedure(IRequestInfoDTO ri, Long hireProcedure,
                                           Long hireType) throws DataBaseException, SharedApplicationException {
        EmpCandidateProceduresDAO empCanProDAO =
            (EmpCandidateProceduresDAO)newDAOInstance(EmpCandidateProceduresEntity.class);
        try {
            return empCanProDAO.checkBeforeDeleteProcedure(hireProcedure, hireType);
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


}
