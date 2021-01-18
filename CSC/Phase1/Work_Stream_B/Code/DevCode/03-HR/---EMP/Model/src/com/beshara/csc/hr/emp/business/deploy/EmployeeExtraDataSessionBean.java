package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.dao.DAOFactoryUtil;
//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.paging.impl.PagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.EmployeeExtraDataDAO;
import com.beshara.csc.hr.emp.business.dao.EmployeesDAO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataSearchDTO;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.shared.EmpUtils;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.fil.MaxNoOfRecordsRequiredException;
import com.beshara.csc.sharedutils.business.exception.fil.PageNumRequiredException;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents the Business Object Wrapper (as Session Bean ) for Business Component publishing.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author     Beshara Group
 * @author     Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "EmployeeExtraDataSession", mappedName = "Emp-Model-EmployeeExtraDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote( { EmployeeExtraDataSession.class, EmployeeExtraDataCMTSession.class })
public class EmployeeExtraDataSessionBean extends EmpBaseSessionBean implements EmployeeExtraDataSession,
                                                                                EmployeeExtraDataCMTSession {


    /**
     * JobsSession Default Constructor
     */
    public EmployeeExtraDataSessionBean() {
        super();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmployeeExtraDataEntity.class;
    }

    @Override
    protected EmployeeExtraDataDAO DAO() {
        return (EmployeeExtraDataDAO)super.DAO();
    }
    //@Auditable
    public IBasicDTO addForEmployementCycleCMT(IRequestInfoDTO ri,
                                               IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return DAO().addForEmployementCycleCMT(employeeExtraDataDTO);
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
     * Get all employees main data in a given work center for VAC (sick vac)
     * @param entityKey
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getByWorkCenterForSickVac(IRequestInfoDTO ri,
                                                     IEntityKey entityKey) throws DataBaseException,
                                                                                  SharedApplicationException {

        try {
            if (entityKey != null && !(entityKey instanceof IWorkCentersEntityKey))
                throw new InvalidDataEntryException();
            return DAO().getByWorkCenterForSickVac(entityKey);
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


    public List<IBasicDTO> getEmpExtraDataByWorkCenterForSickVac(IRequestInfoDTO ri,
                                                                 IEntityKey entityKey) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            if (entityKey != null && !(entityKey instanceof IWorkCentersEntityKey))
                throw new InvalidDataEntryException();
            return DAO().getEmpExtraDataByWorkCenterForSickVac(entityKey);
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

    public List<IBasicDTO> getEmpExtraDataByWorkCentersListForSickVac(IRequestInfoDTO ri, List<IWorkCentersDTO> list,
                                                                      Long minCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            EmployeesDAO empDAO = (EmployeesDAO)DAOFactoryUtil.getInstance(EmployeesEntity.class);
            return DAO().getEmpExtraDataByWorkCentersListForSickVac(minCode, list, empDAO.getStatusForHire());
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

    public IPagingResponseDTO getEmpExtraDataByWorkCentersListForSickVacPaging(IRequestInfoDTO ri,
                                                                               List<IWorkCentersDTO> list,
                                                                               Long minCode,
                                                                               IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                                    SharedApplicationException {
        try {

            EmployeesDAO empDAO = (EmployeesDAO)DAOFactoryUtil.getInstance(EmployeesEntity.class);
            List<IBasicDTO> listResult;
            IPagingResponseDTO responseDTO = null;

            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();

            if (requestDTO != null) {
                Long pageNum = requestDTO.getPageNum();
                Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                if (pageNum != null) {
                    if (maxNoOfRecords != null) {
                        requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                    } else {
                        throw new MaxNoOfRecordsRequiredException();
                    }
                } else {
                    throw new PageNumRequiredException();
                }

                responseDTO = new PagingResponseDTO(); //TODO AKAMAL

                if (requestDTO.isCountRequired()) {
                    responseDTO.setCount(DAO().getEmpExtraDataByWorkCentersCountForSickVacPaging(minCode, list,
                                                                                                 hireStatus));
                }
                List<IBasicDTO> result = null;

                result = DAO().getEmpExtraDataByWorkCentersListForSickVacPaging(minCode, list, hireStatus, requestDTO);
                responseDTO.setBasicDTOList(result);
                responseDTO.setRequestDTO(requestDTO);

            }


            return responseDTO;

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
     * Update emp extra data VALUE ONLY used by VAC (sick vac)
     * @param employeeExtraDataDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean updateExtraDataValue(IRequestInfoDTO ri, IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {


        return DAO().updateExtraDataValue(employeeExtraDataDTO);

    }

    /**
     * Search for EmployeeExtraDataEntity  used by VAC (sick vac)
     * <br>
     * @return List
     */
    public List<IBasicDTO> searchForSickVac(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {

        try {
            return DAO().searchForSickVac(basicDTO);
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

    public IPagingResponseDTO searchForSickVacInWorkCentersList(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                                                List<IWorkCentersDTO> wcList,
                                                                Long minCode) throws DataBaseException,
                                                                                     SharedApplicationException {


        try {
            IEmployeeExtraDataSearchDTO searchDTO = (IEmployeeExtraDataSearchDTO)basicDTO;
            EmployeesDAO empDAO = (EmployeesDAO)DAOFactoryUtil.getInstance(EmployeesEntity.class);

            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();

            IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
            IPagingResponseDTO responseDTO = null;
            if (requestDTO != null) {
                Long pageNum = requestDTO.getPageNum();
                Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                if (pageNum != null) {
                    if (maxNoOfRecords != null) {
                        requestDTO.setFirstRowNumber((pageNum - 1) * maxNoOfRecords);
                    }
                }
                responseDTO = new PagingResponseDTO();
                if (requestDTO.isCountRequired()) {
                    responseDTO.setCount(DAO().getCountSearchForSickVacInWorkCentersList(basicDTO, wcList, minCode,
                                                                                         hireStatus));
                }
                List<IBasicDTO> result = null;
                try {
                    result = DAO().searchForSickVacInWorkCentersList(basicDTO, wcList, minCode, hireStatus);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                } catch (Throwable e) {
                    e.printStackTrace();
                    throw new SharedApplicationException();
                }
                responseDTO.setBasicDTOList(result);
                responseDTO.setRequestDTO(requestDTO);
            }


            return responseDTO;

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
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataType(IRequestInfoDTO ri, Long civilId,
                                         Long extraDataTypeCode) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().isActiveExtraDataType(civilId, extraDataTypeCode);
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

    public List<IEmployeeExtraDataDTO> getAllEmployeeExtraDataByCivilId(IRequestInfoDTO ri,
                                                                        Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getAllEmployeeExtraDataByCivilId(civilId);
    }

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByCivilId(IRequestInfoDTO ri,
                                                                        Long civilId) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getEmployeeExtraDataByCivilId(civilId);
    }

    public IBasicDTO getEmployeeExtraDataForMov(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                         SharedApplicationException {
        return DAO().getEmployeeExtraDataForMov(civilId);

    }
    //@Auditable
    public Boolean updateEmpExtraDataForMov(IRequestInfoDTO ri,
                                            IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException {
        return DAO().updateEmpExtraDataForMov(employeeExtraDataDTO);
    }
    //@Auditable
    public int updateStatusByMovingDate(IRequestInfoDTO ri, Long realCivilId,
                                        Date movingDate) throws DataBaseException, SharedApplicationException {
        return DAO().updateStatusByMovingDate(realCivilId, movingDate);
    }

    public Boolean isDateOverlaped(IRequestInfoDTO ri, IBasicDTO _dto) throws DataBaseException,
                                                                              SharedApplicationException {
        return DAO().isDateOverlaped(_dto);
    }

    public IEmployeeExtraDataDTO getByExtraDataTypeAndCivilId(IRequestInfoDTO ri, Long civilId,
                                                              Long extraDataTypeCode) throws DataBaseException,
                                                                                             SharedApplicationException {
        return DAO().getByExtraDataTypeAndCivilId(civilId, extraDataTypeCode);
    }
    //@Auditable
    public Boolean updateEmpExtraDataForMer(IRequestInfoDTO ri,
                                            IBasicDTO employeeExtraDataDTO) throws DataBaseException,
                                                                                   SharedApplicationException {

        try {
            if(DAO().isDateOverlapedForEdit(employeeExtraDataDTO) ) {
                throw new SharedApplicationException("DateOverlapedForEdit");
            }
            return DAO().updateEmpExtraDataForMer(employeeExtraDataDTO);
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

    public List<IEmployeeExtraDataDTO> getEmployeeExtraDataByName(IRequestInfoDTO ri, Long civilId,
                                                                  String typeName) throws DataBaseException,
                                                                                          SharedApplicationException {

        return DAO().getEmployeeExtraDataByName(civilId, typeName);

    }

    public IEmployeeExtraDataDTO getEmpExtraDataByRealCivilId(IRequestInfoDTO ri,
                                                              Long realCivilId) throws DataBaseException,
                                                                                       SharedApplicationException {
        return DAO().getEmpExtraDataByRealCivilId(realCivilId);
    }
    
    public List<IBasicDTO> getByCivilIdAndExtraDataTypeCodeLst(IRequestInfoDTO ri, Long civilId,
                                                               List<Long> extraDataTypeCodeLst,
                                                               Long status) throws DataBaseException,
                                                                                          SharedApplicationException {
        StringBuilder codeLst = new StringBuilder("");
        for(Long code : extraDataTypeCodeLst){
            codeLst.append("," + code);
        }
        if(!codeLst.toString().isEmpty())
            codeLst = codeLst.deleteCharAt(0);
        return DAO().getByCivilIdAndExtraDataTypeCodeLst(civilId, codeLst.toString(), status);

    }

    public String getNationalityNumberByCivilId(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                         SharedApplicationException {
        return DAO().getNationalityNumberByCivilId(civilId);
    }
    
    public Date getFirstHireDateFromSocialInsurance (IRequestInfoDTO ri , Long civilId ) throws DataBaseException,SharedApplicationException
    {
        
        return DAO().getFirstHireDateFromSocialInsurance(civilId);
        }

}
