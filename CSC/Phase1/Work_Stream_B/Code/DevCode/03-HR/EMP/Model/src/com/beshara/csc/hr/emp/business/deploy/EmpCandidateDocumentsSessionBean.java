package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateDocumentsDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
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


@Stateless(name = "EmpCandidateDocumentsSession", mappedName = "Emp-Model-EmpCandidateDocumentsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpCandidateDocumentsSessionBean extends EmpBaseSessionBean implements EmpCandidateDocumentsSession {


    /**
     * EmpCandidateDocumentsSession Default Constructor */
    public EmpCandidateDocumentsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCandidateDocumentsEntity.class;
    }

    @Override
    protected EmpCandidateDocumentsDAO DAO() {
        return (EmpCandidateDocumentsDAO)super.DAO();
    }

    /**
     * list All available entities Related to an employee * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> listAvailableEntities(IRequestInfoDTO ri, IEntityKey code,
                                                 IEntityKey hireType) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            if (code != null && !(code instanceof IEmployeesEntityKey))
                throw new InvalidDataEntryException();
            return DAO().listAvailableEntities(code, hireType);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IRequestInfoDTO requestInfo, IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            return DAO().listAvailableEntitiesByKwtCitizenCode(paramDto, hireType);
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
     * Filter All available entities Related to an employee * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO ri, IEntityKey code, IEntityKey hireType,
                                                   String name) throws DataBaseException, SharedApplicationException {

        try {
            if (code != null && !(code instanceof IEmployeesEntityKey))
                throw new InvalidDataEntryException();
            return DAO().filterAvailableEntities(code, hireType, name);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> filterAvailableEntitiesByCode(IRequestInfoDTO ri, IEntityKey code, IEntityKey hireType,
                                                         Long docTypeCode) throws DataBaseException,
                                                                                  SharedApplicationException {

        try {
            if (code != null && !(code instanceof IEmployeesEntityKey))
                throw new InvalidDataEntryException();
            return DAO().filterAvailableEntitiesByCode(code, hireType, docTypeCode);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO requestInfo, IBasicDTO paramDto,
                                                   IEntityKey hireType, String name,
                                                   Long docTypeCode) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return DAO().filterAvailableEntities(paramDto, hireType, name, docTypeCode);
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

    public Long checkAboutAttachmentForCivil(IRequestInfoDTO requestInfo, Long civilId,
                                             Long docTypeCode) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().checkAboutAttachmentForCivil(civilId, docTypeCode);
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
    //@Auditable
    public void addNewRecord(IRequestInfoDTO requestInfo, IEmpCandidateDocumentsDTO dto) throws DataBaseException,
                                                                                                SharedApplicationException {
        DAO().addNewRecord(dto);
    }

    //@Auditable
    public boolean addedRecordBefore(IRequestInfoDTO requestInfo, IEmpCandidateDocumentsDTO dto) throws DataBaseException,
                                                                                                SharedApplicationException {
   return  DAO().addedRecordBefore(dto);
    }
    //@Auditable
    public boolean removeRecord(IRequestInfoDTO requestInfo,IEmpCandidateDocumentsDTO dto) throws DataBaseException, SharedApplicationException {
            return  DAO().removeRecord(dto);
        }

    public List<IEmpCandidateDocumentsDTO> getByCandCodeNew(IRequestInfoDTO requestInfo,
                                                         IEntityKey candidateCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().getByCandCodeNew(candidateCode);
    }
}
