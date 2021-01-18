package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dao.HireTypeProcedureDAO;
import com.beshara.csc.hr.emp.business.dao.HireTypesDAO;
import com.beshara.csc.hr.emp.business.dao.RequiredDocumentsDAO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypeProcedureDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IHireProceduresEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.rmi.RemoteException;

import java.util.ArrayList;
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
@Stateless(name = "HireTypesSession", mappedName = "Emp-Model-HireTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HireTypesSessionBean extends EmpBaseSessionBean implements HireTypesSession { //@PersistenceContext ( unitName = "Emp" )


    /**
     * JobsSession Default Constructor */
    public HireTypesSessionBean() {
    }

    @Override
    protected HireTypesDAO DAO() {
        return (HireTypesDAO)super.DAO();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HireTypesEntity.class;
    }

    /**
     * Add new HireTypes * @param hireTypesDTO1
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO hireTypesDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;
        if (DAO().duplicatedHireTypesName(hireTypesDTO, false)) {
            throw new InvalidDataEntryException();
        }
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)newDAOInstance(RequiredDocumentsEntity.class);
        HireTypeProcedureDAO hireTypeProcedureDAO =
            (HireTypeProcedureDAO)newDAOInstance(HireTypeProcedureEntity.class);
        try {
            IHireTypesDTO addedHireType = DAO().add(hireTypesDTO);
            if (hireTypesDTO.getRequiredDocumentsDTOList() != null) {
                for (IRequiredDocumentsDTO reqDocDTO : hireTypesDTO.getRequiredDocumentsDTOList()) {
                    reqDocDTO.setHireTypeDTO(addedHireType);
                    reqDocDTO.setCode(EmpEntityKeyFactory.createRequiredDocumentsEntityKey(((IHireTypesEntityKey)addedHireType.getCode()).getHirtypeCode(),
                                                                                           ((IInfDocumentTypesEntityKey)reqDocDTO.getDocumentTypeDTO().getCode()).getDoctypeCode()));
                    if (reqDocDTO.getBasicStatus() == null) {
                        reqDocDTO.setBasicStatus(0L);
                    }
                    if (reqDocDTO.getAttachmentRequired() == null) {
                        reqDocDTO.setAttachmentRequired(0L);
                    }
                    requiredDocumentsDAO.add(reqDocDTO);
                }
            }
            if (hireTypesDTO.getHireTypeProcedureDTOList() != null) {
                for (IHireTypeProcedureDTO hirtypprocDTO : hireTypesDTO.getHireTypeProcedureDTOList()) {
                    hirtypprocDTO.setHireTypeDTO(addedHireType);
                    hirtypprocDTO.setCode(EmpEntityKeyFactory.createHireTypeProcedureEntityKey(((IHireTypesEntityKey)addedHireType.getCode()).getHirtypeCode(),
                                                                                               ((IHireProceduresEntityKey)hirtypprocDTO.getHireProceduresDTO().getCode()).getHirprocedureCode()));
                    hireTypeProcedureDAO.add(hirtypprocDTO);
                }
            }
            return addedHireType;
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
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
     * update an exist HireTypes * @param hireTypesDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public Boolean update(IRequestInfoDTO ri, IBasicDTO hireTypesDTO1) throws DataBaseException,
                                                                              SharedApplicationException {

        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;
        if (hireTypesDTO != null && hireTypesDTO.getParentHireType() != null) {
            if (DAO().duplicatedHireTypesName(hireTypesDTO, true)) {
                throw new InvalidDataEntryException();
            }
        }
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)newDAOInstance(RequiredDocumentsEntity.class);
        HireTypeProcedureDAO hireTypeProcedureDAO =
            (HireTypeProcedureDAO)newDAOInstance(HireTypeProcedureEntity.class);
        try {
            DAO().update(hireTypesDTO);
            if (hireTypesDTO.getRequiredDocumentsDTOList() != null) {
                for (IRequiredDocumentsDTO reqDocDTO : hireTypesDTO.getRequiredDocumentsDTOList()) {
                    reqDocDTO.setHireTypeDTO(hireTypesDTO);
                    if (reqDocDTO.getStatusFlag() != null &&
                        reqDocDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        reqDocDTO.setCode(EmpEntityKeyFactory.createRequiredDocumentsEntityKey(((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode(),
                                                                                               ((IInfDocumentTypesEntityKey)reqDocDTO.getDocumentTypeDTO().getCode()).getDoctypeCode()));
                        reqDocDTO.setHireTypeDTO(hireTypesDTO);
                        if (reqDocDTO.getBasicStatus() == null) {
                            reqDocDTO.setBasicStatus(0L);
                        }
                        if (reqDocDTO.getAttachmentRequired() == null) {
                            reqDocDTO.setAttachmentRequired(0L);
                        }
                        requiredDocumentsDAO.add(reqDocDTO);
                    } else if (reqDocDTO.getStatusFlag() != null &&
                               reqDocDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                        requiredDocumentsDAO.remove(reqDocDTO);
                    } else {
                        requiredDocumentsDAO.update(reqDocDTO);
                    }
                }
            }
            if (hireTypesDTO.getHireTypeProcedureDTOList() != null) {
                for (IHireTypeProcedureDTO hirProcDTO : hireTypesDTO.getHireTypeProcedureDTOList()) {
                    hirProcDTO.setHireTypeDTO(hireTypesDTO);
                    if (hirProcDTO.getStatusFlag() != null &&
                        hirProcDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        hirProcDTO.setCode(EmpEntityKeyFactory.createHireTypeProcedureEntityKey(((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode(),
                                                                                                ((IHireProceduresEntityKey)hirProcDTO.getHireProceduresDTO().getCode()).getHirprocedureCode()));
                        hirProcDTO.setHireTypeDTO(hireTypesDTO);
                        hireTypeProcedureDAO.add(hirProcDTO);

                    } else if (hirProcDTO.getStatusFlag() != null &&
                               hirProcDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                        hireTypeProcedureDAO.remove(hirProcDTO);
                    } else {
                        hireTypeProcedureDAO.update(hirProcDTO);

                    }
                }
            }
            return Boolean.TRUE;
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
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
     * Remove an exist HireTypes * @param hireTypesDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public Boolean remove(IRequestInfoDTO ri, IBasicDTO hireTypesDTO1) throws DataBaseException,
                                                                              SharedApplicationException {
        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)newDAOInstance(RequiredDocumentsEntity.class);
        try {
            if (hireTypesDTO.getRequiredDocumentsDTOList() != null) {
                for (IRequiredDocumentsDTO reqDocDTO : hireTypesDTO.getRequiredDocumentsDTOList()) {
                    requiredDocumentsDAO.remove(reqDocDTO);
                }
            }
            return DAO().remove(hireTypesDTO);
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
     * Remove list of type hireTypesDTO * @param hireTypesDTOlist
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public List remove(IRequestInfoDTO ri, List<IBasicDTO> hireTypesDTOlist) throws DataBaseException,
                                                                                    SharedApplicationException {
        boolean transactionBegun = isTransactionBegun();
        try {
            if (transactionBegun) {
                suspendTransaction();
            }
            List resultList = new ArrayList();
            for (IBasicDTO hireTypesDTO : hireTypesDTOlist) {
                try {
                    remove(hireTypesDTO);
                    commitTransaction();
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO = (ResultDTO)createDeletedResult(hireTypesDTO);
                    resultList.add(resultDTO);
                } catch (ItemNotFoundException e) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO = (ResultDTO)createNotDeletedResult(hireTypesDTO, e);
                    resultList.add(resultDTO);
                    rollbackTransaction();
                } catch (SharedApplicationException e) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO = (ResultDTO)createNotDeletedResult(hireTypesDTO, e);
                    resultList.add(resultDTO);
                    rollbackTransaction();
                } catch (TransactionException e) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO = (ResultDTO)createNotDeletedResult(hireTypesDTO, e);
                    resultList.add(resultDTO);
                }
            }
            return resultList;
        } finally {
            if (transactionBegun) {
                resumeTransaction();
            }
        }
    }

    /**
     * Get all Hire Types code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
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
     * Get all First Level Hire Types code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getFirstLevelHireTypes(IRequestInfoDTO ri) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            return DAO().getFirstLevelHireTypes();
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
     * Get all hireTypes match search code * @param code
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri, Object code) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            if (!(code instanceof Long))
                throw new InvalidDataEntryException();
            return DAO().searchByCode(code);
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
     * Get all document for an existing hire type and valid for an employee * @param hireTypeCode
     * @param genderType
     * @param nationalityType
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getValidDocumentsForEmployee(IRequestInfoDTO ri, Long hireTypeCode, Long genderType,
                                                        Long nationalityType) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return DAO().getValidDocumentsForEmployee(hireTypeCode, genderType, nationalityType);
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
     * to update employee stage * @param hireTypeCode
     * @param genderType
     * @param nationalityType
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
     //@Auditable
    private void updateEmployeeStage(IRequestInfoDTO ri, Long hireTypeCode, Long genderType,
                                     Long nationalityType) throws DataBaseException, SharedApplicationException {
        IEmpEmployeeSearchDTO search = EmpDTOFactory.createEmpEmployeeSearchDTO();
        search.setEmpHireTypes(hireTypeCode);
        search.setNationalityType(nationalityType);
        search.setGenderType(genderType);
        search.setHirestageNotEqualCanceldOrEmployment(true);
        try {
            for (IBasicDTO basicEmployee : EmpClientFactory.getEmployeesClient().simpleSearch(search)) {
                IEmployeesDTO dto = (IEmployeesDTO)basicEmployee;
                IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
                Long oldStage = ((IHireStagesEntityKey)dto.getHireStageDTO().getCode()).getHirstageCode();
                if (oldStage.equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT)) {
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_COMPLETE_PROC));
                    dto.setHireStageDTO(stage);
                }
                if (oldStage.equals(IEMPConstant.EMP_STAGE_COMPLETE_DOC)) { //set default stage
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_DEFAULT));
                    dto.setHireStageDTO(stage);
                }
                EmpClientFactory.getEmployeesClient().update(dto);
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

    public List<IHireTypesDTO> getAllSorted(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().getAllSorted();
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

    public List<IHireTypesDTO> getRelatedByCenterTwzef(IRequestInfoDTO ri, Long parentHireType,
                                                       Long minCode) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return DAO().getRelatedByCenterTwzef(parentHireType, minCode);
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

    public List<IHireTypesDTO> getEmpHireTypesAdd(IRequestInfoDTO ri, Long minCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            return DAO().getEmpHireTypesAdd(minCode);
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
    public IBasicDTO joinCondition(IRequestInfoDTO ri,
                                   IJoinCondTargetDTO finishReasonDTO) throws SharedApplicationException,
                                                                              DataBaseException {

        try {
            HireTypeConditionsHelper jcHelper =
                HireTypeConditionsHelper.getInstanceForJoin(finishReasonDTO, IEMPConstants.TRX_HR_EMP_HIRE_TYPES);
            jcHelper.execute();
            return finishReasonDTO;
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
    public IBasicDTO cancelCondition(IRequestInfoDTO ri,
                                     IJoinCondTargetDTO finishReasonDTO) throws SharedApplicationException,
                                                                                DataBaseException {

        try {
            HireTypeConditionsHelper jcHelper = HireTypeConditionsHelper.getInstanceForCancel(finishReasonDTO);
            jcHelper.execute();
            return finishReasonDTO;
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

    public List<Long> getHireTyperecserialByHireType(IRequestInfoDTO ri, Long hiretype) throws DataBaseException,
                                                                                               SharedApplicationException {
        return DAO().getHireTyperecserialByHireType(hiretype);
    }

    public List<IBasicDTO> getByTreeLevelHireTypes(IRequestInfoDTO ri, Long treeLevel) throws DataBaseException,
                                                                                              SharedApplicationException {
        return DAO().getByTreeLevelHireTypes(treeLevel);
    }
}
