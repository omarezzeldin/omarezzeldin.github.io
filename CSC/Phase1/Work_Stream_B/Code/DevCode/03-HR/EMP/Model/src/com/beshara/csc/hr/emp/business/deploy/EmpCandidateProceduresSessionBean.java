package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateProceduresDAO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


@Stateless(name = "EmpCandidateProceduresSession", mappedName = "Emp-Model-EmpCandidateProceduresSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpCandidateProceduresSessionBean extends EmpBaseSessionBean implements EmpCandidateProceduresSession {

    public EmpCandidateProceduresSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCandidateProceduresEntity.class;
    }

    /**
     * list All available entities * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    @Override
    protected EmpCandidateProceduresDAO DAO() {
        return (EmpCandidateProceduresDAO)super.DAO();
    }


    /**
     * @return List
     */
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws SharedApplicationException, DataBaseException {
        return (List)DAO().getAll();
    }

    /**
     * @param employeeProceduresDTO
     * @return IEmployeeProceduresDTO
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO empCandidateProceduresDTO) throws SharedApplicationException,
                                                                                         DataBaseException {
        return DAO().add(empCandidateProceduresDTO);
    }

    /**
     * @param employeeProceduresDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean update(IRequestInfoDTO ri, IBasicDTO empCandidateProceduresDTO) throws SharedApplicationException,
                                                                                          DataBaseException {
        return DAO().update(empCandidateProceduresDTO);
    }

    /**
     * @param employeeProceduresDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean remove(IRequestInfoDTO ri, IBasicDTO empCandidateProceduresDTO) throws SharedApplicationException,
                                                                                          DataBaseException {
        return DAO().remove(empCandidateProceduresDTO);
    }

    /**
     * @param id
     * @return IEmployeeProceduresDTO
     */
    public IBasicDTO getById(IRequestInfoDTO ri, IEntityKey id) throws SharedApplicationException, DataBaseException {
        return DAO().getById(id);
    }
    //@Auditable
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> list) throws SharedApplicationException, DataBaseException {
        return super.remove(list);
    }

    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                 DataBaseException {
        return DAO().search(basicDTO);
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

        if (code != null && !(code instanceof IEmpCandidatesEntityKey))
            throw new InvalidDataEntryException();
        return DAO().listAvailableEntities(code, hireType);
    }

    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IRequestInfoDTO ri, IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().listAvailableEntitiesByKwtCitizenCode(paramDto, hireType);
    }

    /**
     * Filter All available entities Related to an employee * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO ri, IEntityKey code, String name,
                                                   IEntityKey hireType) throws DataBaseException,
                                                                               SharedApplicationException {

        if (code != null && !(code instanceof IEmpCandidatesEntityKey))
            throw new InvalidDataEntryException();
        return DAO().filterAvailableEntities(code, name, hireType);
    }

    /**
     * Filter All available entities Related to an employee * @param code
     * @return list
     * @throws SharedApplicationException
     * @throws RemoteException
     */
    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO ri, IEntityKey code, IEntityKey hireType,
                                                   String name) throws DataBaseException, SharedApplicationException {

        if (code != null && !(code instanceof IEmpCandidatesEntityKey))
            throw new InvalidDataEntryException();
        return DAO().filterAvailableEntities(code, hireType, name);
    }

    public List<IBasicDTO> filterAvailableEntitiesByCode(IRequestInfoDTO ri, IEntityKey code, Long hirProcedureCode,
                                                         IEntityKey hireType) throws DataBaseException,
                                                                                     SharedApplicationException {

        if (code != null && !(code instanceof IEmpCandidatesEntityKey))
            throw new InvalidDataEntryException();
        return DAO().filterAvailableEntitiesByCode(code, hirProcedureCode, hireType);
    }

    public List<IBasicDTO> filterAvailableEntities(IRequestInfoDTO requestInfo, IBasicDTO paramDto,
                                                   IEntityKey hireType, String name,
                                                   Long hirProcedureCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return DAO().filterAvailableEntities(paramDto, hireType, name, hirProcedureCode);
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
    public List<IEmpCandidateProceduresDTO> getByCandCode(IRequestInfoDTO requestInfo,Long candidateCode) throws DataBaseException,
                                                                                 SharedApplicationException{
        try {
            return DAO().getByCandCode(candidateCode);
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
