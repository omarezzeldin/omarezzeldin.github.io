package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateDocumentsDAO;
import com.beshara.csc.hr.emp.business.dao.RequiredDocumentsDAO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
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
@Stateless(name = "RequiredDocumentsSession", mappedName = "Emp-Model-RequiredDocumentsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(RequiredDocumentsSession.class)
public class RequiredDocumentsSessionBean extends EmpBaseSessionBean implements RequiredDocumentsSession { //@PersistenceContext ( unitName = "Emp" )


    /**
     * JobsSession Default Constructor */
    public RequiredDocumentsSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return RequiredDocumentsEntity.class;
    }

    @Override
    protected RequiredDocumentsDAO DAO() {
        return (RequiredDocumentsDAO)super.DAO();
    }
    //    /**
    //     * Get all RequiredDocuments * @return List
    //     * @throws RemoteException
    //     * @throws SharedApplicationException
    //     * @throws DataBaseException
    //     */
    //    @over
    //    public List<IBasicDTO> getAll() throws RemoteException,
    //                                           SharedApplicationException,
    //                                           DataBaseException {
    //        try {
    //            return super.getAll();
    //        } catch (ItemNotFoundException e) {
    //            throw new NoResultException();
    //        }
    //    }

    //    /**
    //     * @param requiredDocumentsDTO
    //     * @return IRequiredDocumentsDTO
    //     */
    //    public IBasicDTO add(IBasicDTO requiredDocumentsDTO) throws RemoteException,
    //                                                                SharedApplicationException,
    //                                                                DataBaseException {
    //        return super.add(requiredDocumentsDTO);
    //    }

    //    /**
    //     * @param requiredDocumentsDTO
    //     * @return Boolean
    //     */
    //    public Boolean update(IBasicDTO requiredDocumentsDTO) throws RemoteException,
    //                                                                 SharedApplicationException,
    //                                                                 DataBaseException {
    //        return super.update(requiredDocumentsDTO);
    //    }

    //    /**
    //     * @param requiredDocumentsDTO
    //     * @return Boolean
    //     */
    //    public Boolean remove(IBasicDTO requiredDocumentsDTO) throws RemoteException,
    //                                                                 SharedApplicationException,
    //                                                                 DataBaseException {
    //        return super.remove(requiredDocumentsDTO);
    //    }

    //    /**
    //     * @param id
    //     * @return IRequiredDocumentsDTO
    //     */
    //    public IBasicDTO getById(IEntityKey id) throws RemoteException,
    //                                                   SharedApplicationException,
    //                                                   DataBaseException {
    //        return super.getById(id);
    //    }

    //    public List remove(List<IBasicDTO> list) throws RemoteException,
    //                                                    SharedApplicationException,
    //                                                    DataBaseException {
    //        return super.remove(list);
    //    }

    //    public List<IBasicDTO> search(IBasicDTO basicDTO) throws RemoteException,
    //                                                             SharedApplicationException,
    //                                                             DataBaseException {
    //        return super.search(basicDTO);
    //    }

    /**
     * Get all document types not related to Hire Type * @param hireTypesEntityKey
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> getAvailableDocuments(IRequestInfoDTO ri,
                                                            IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                  SharedApplicationException {

        try {
            return DAO().getAvailableDocuments(hireTypesEntityKey);
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

    public List<IInfDocumentTypesDTO> getAvailableDocuments(IRequestInfoDTO ri) throws DataBaseException,
                                                                                       SharedApplicationException {

        try {
            return DAO().getAvailableDocuments();
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

    /**
     * filter document types not related to specific Hire Type * @param hireTypeCode
     * @param name
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> filterAvailableDocuments(IRequestInfoDTO ri, IEntityKey hireTypeCode,
                                                               String name) throws DataBaseException,
                                                                                   SharedApplicationException {

        try {
            return DAO().filterAvailableDocuments(hireTypeCode, name, null);
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

    /**
     * filter document types not related to specific Hire Type * @param hireTypeCode
     * @param code
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> filterAvailableDocuments(IRequestInfoDTO ri, IEntityKey hireTypeCode,
                                                               Long code) throws DataBaseException,
                                                                                 SharedApplicationException {

        try {
            return DAO().filterAvailableDocuments(hireTypeCode, null, code);
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


    public Long checkBeforeDeleteDocument(IRequestInfoDTO ri, Long docType, Long hireType) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {

            EmpCandidateDocumentsDAO empCandidateDocumentsDAO =
                (EmpCandidateDocumentsDAO)newDAOInstance(EmpCandidateDocumentsEntity.class);

            return empCandidateDocumentsDAO.checkBeforeDeleteDocument(docType, hireType);
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

    public List<IBasicDTO> getDocumentsByHireType(IRequestInfoDTO ri, Long hireTypeCode) throws DataBaseException,
                                                                                                SharedApplicationException {

        try {
            return DAO().getDocumentsByHireType(hireTypeCode);
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

}
