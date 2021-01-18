package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;


import com.beshara.base.client.ClientFactoryUtil;
import com.beshara.base.config.ConfigManager;
import com.beshara.base.dao.DAOFactoryUtil;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.paging.impl.PagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.CriminalEvidenceBindingQSPortClient;
import com.beshara.csc.gn.sec.business.client.IUsersClient;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.hr.aoe.business.client.AoeClientFactory;
import com.beshara.csc.hr.att.business.client.EmpSignIdenClientImpl;
import com.beshara.csc.hr.att.business.client.IEmpSignIdenClient;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.crs.business.client.CrsClientFactory;
import com.beshara.csc.hr.crs.business.dto.ICandidatePersonsDTO;
import com.beshara.csc.hr.crs.business.dto.IJobSeekersDTO;
import com.beshara.csc.hr.crs.business.entity.CrsEntityKeyFactory;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateDocumentsDAO;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateExtraDataDAO;
import com.beshara.csc.hr.emp.business.dao.EmpCandidateProceduresDAO;
import com.beshara.csc.hr.emp.business.dao.EmpCandidatesDAO;
import com.beshara.csc.hr.emp.business.dao.EmpCndSalaryElementsDAO;
import com.beshara.csc.hr.emp.business.dao.EmployeeExtraDataDAO;
import com.beshara.csc.hr.emp.business.dao.EmployeesDAO;
import com.beshara.csc.hr.emp.business.dao.HireStagesDAO;
import com.beshara.csc.hr.emp.business.dao.HireTypesDAO;
import com.beshara.csc.hr.emp.business.dao.RequiredDocumentsDAO;
import com.beshara.csc.hr.emp.business.dto.EmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateParent;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataValueDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.ITrxTypesDTO;
import com.beshara.csc.hr.emp.business.dto.empenquiry.IEmpReqEnquiryDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.MessageHeader;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.ParametersDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.RegisterReturnType;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.TerminationReturnType;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IProcedureResultsEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.EmpUtils;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeHiredBeforeException;
import com.beshara.csc.hr.emp.business.shared.exception.ExperienceInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.ExtraDataInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.HireDateRequiredException;
import com.beshara.csc.hr.emp.business.shared.exception.SalElementInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.VacInsertionException;
import com.beshara.csc.hr.sal.business.client.ISalEmpMonSalariesClient;
import com.beshara.csc.hr.sal.business.client.ISalEmpSalaryElementsClient;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMovDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpMovDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.vac.business.client.VacClientFactory;
import com.beshara.csc.inf.business.client.IKwtWorkDataClient;
import com.beshara.csc.inf.business.client.IPersonDocAttachemntsClient;
import com.beshara.csc.inf.business.client.IPersonQualificationsClient;
import com.beshara.csc.inf.business.client.IPersonsInformationCMTClient;
import com.beshara.csc.inf.business.client.IPersonsInformationClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataDTO;
import com.beshara.csc.inf.business.dto.IKwtWorkDataTreeDTO;
import com.beshara.csc.inf.business.dto.IPersonsInformationDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.dto.PersonDocAttachemntsDTO;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidCivilIdOnConditionException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.fil.MaxNoOfRecordsRequiredException;
import com.beshara.csc.sharedutils.business.exception.fil.PageNumRequiredException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.rmi.RemoteException;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


@Stateless(name = "EmpCandidatesSession", mappedName = "Emp-Model-EmpCandidatesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(EmpCandidatesSession.class)
public class EmpCandidatesSessionBean extends EmpBaseSessionBean implements EmpCandidatesSession {

    private EmpCndSalaryElementsDAO empCndSalElmDAO =
        (EmpCndSalaryElementsDAO)DAOFactoryUtil.getInstance(EmpCndSalaryElementsEntity.class);

    private EmpCandidateProceduresDAO empCndProceduresDAO =
        (EmpCandidateProceduresDAO)DAOFactoryUtil.getInstance(EmpCandidateProceduresEntity.class);

    private EmpCandidateDocumentsDAO empCndDocumentsDAO =
        (EmpCandidateDocumentsDAO)DAOFactoryUtil.getInstance(EmpCandidateDocumentsEntity.class);

    private EmployeeExtraDataDAO employeeExtraDataDAO =
        (EmployeeExtraDataDAO)DAOFactoryUtil.getInstance(EmployeeExtraDataEntity.class);

    private ISalEmpSalaryElementsClient salEmpSalaryElementsClient = SalClientFactory.getSalEmpSalaryElementsClient();

    //        private SalEmpSalaryElementsDAO salEmpSalaryElementsDAO =
    //           (SalEmpSalaryElementsDAO)DAOFactoryUtil.getInstance(SalEmpSalaryElementsEntity.class);

    private EmployeesDAO employeesDAO = (EmployeesDAO)DAOFactoryUtil.getInstance(EmployeesEntity.class);

    /**
     * JobsSession Default Constructor */
    public EmpCandidatesSessionBean() {
    }

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     */
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpCandidatesEntity.class;
    }

    @Override
    protected EmpCandidatesDAO DAO() {
        return (EmpCandidatesDAO)super.DAO();
    }

    /**
     **********if you need empcandidate only without lists's reation use Dao.getById direct
     * TODO remove commented Code from Dao and remove method from session
     * @param ri
     * @param id1
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IBasicDTO getById(IRequestInfoDTO ri, IEntityKey id1) throws DataBaseException, SharedApplicationException {
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)DAO().getById(id1);
        IEmpCandidatesEntityKey empCandidatekey = (IEmpCandidatesEntityKey)empCandidateDTO.getCode();

        //Add EmployeeDocuments
        try {
            List<IEmpCandidateDocumentsDTO> empCandidateDocumentsDTOList =
                empCndDocumentsDAO.getByCandCode(empCandidatekey);
            empCandidateDTO.setEmpCandidateDocumentsList((List)empCandidateDocumentsDTOList);
        } catch (Exception e) {
            empCandidateDTO.setEmpCandidateDocumentsList(new ArrayList());
        }

        // Add EmployeeProcedures
        try {
            List<IEmpCandidateProceduresDTO> empCandidateProceduresList =
                empCndProceduresDAO.getByCandCode(empCandidatekey);
            empCandidateDTO.setEmpCandidateProceduresList((List)empCandidateProceduresList);
        } catch (Exception e) {
            empCandidateDTO.setEmpCandidateProceduresList(new ArrayList());
        }

        // Add Employee Salary
        try {
            IEmpCndSalaryElementsDTO empCndSalaryElementsDto = empCndSalElmDAO.getByCandCode(empCandidatekey);
            List<IEmpCndSalaryElementsDTO> list = new ArrayList();
            if (empCndSalaryElementsDto.getUntilDate() == null) {
                list.add(empCndSalaryElementsDto);
                empCandidateDTO.setEmpCndSalaryElementsList((List)list);
            } else {
                empCandidateDTO.setEmpCndSalaryElementsList(new ArrayList());
            }
        } catch (Exception e) {
            empCandidateDTO.setEmpCndSalaryElementsList(new ArrayList());
        }

        // Add EmployeeExtraData
        try {
            List<EmpCandidateExtraDataEntity> empCandidateExtraDataEntityList =
                DAO().getAllEmployeeExtraDataByCandCodeAndCandSeq(empCandidatekey.getCandidateCode(),
                                                                  empCandidatekey.getCandidateCodeSeq());

            empCandidateDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            if (empCandidateExtraDataEntityList != null && !empCandidateExtraDataEntityList.isEmpty()) {
                List<IBasicDTO> empCandidateExtraDataDTOList = new ArrayList<IBasicDTO>();
                System.out.println("@#$ = " + empCandidateExtraDataDTOList.size());
                for (EmpCandidateExtraDataEntity entity : empCandidateExtraDataEntityList) {
                    empCandidateExtraDataDTOList.add(EmpEntityConverter.getEmpCandidateExtraDataDTO(entity));
                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        empCandidateDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        empCandidateDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        empCandidateDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        empCandidateDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        empCandidateDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());

                    }
                }
                empCandidateDTO.setEmpCandidateExtraDataList((List)empCandidateExtraDataEntityList);
            }
        } catch (Exception e) {
            empCandidateDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            empCandidateDTO.setEmpCandidateExtraDataList(new ArrayList());
        }
        return empCandidateDTO;

    }

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     */
    public Boolean isMinistryFileNoExist(IRequestInfoDTO ri, String ministryFileNo) throws DataBaseException,
                                                                                           SharedApplicationException {

        return DAO().isMinistryFileNoExist(ministryFileNo);

    }

    public List<IBasicDTO> filterByHireTypeForHireExecute(IRequestInfoDTO ri, Long hirtypeCode,
                                                          Long loggedMin) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return DAO().filterByHireTypeForHireExecute(hirtypeCode, loggedMin);
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> filterByAllHireTypesForHireExecute(IRequestInfoDTO ri) throws DataBaseException,
                                                                                         SharedApplicationException {
        try {
            return DAO().filterByAllHireTypesForHireExecute();
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    public IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO ri,
                                                       IBasicDTO _searchDTO) throws DataBaseException,
                                                                                    SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        try {
            if (_searchDTO != null) {
                IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;
                IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
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

                    responseDTO = new PagingResponseDTO();

                    if (requestDTO.isCountRequired()) {
                        responseDTO.setCount(DAO().getCountByHireStageAndMinistryWithPaging(searchDTO));
                    }

                    List<IBasicDTO> result = null;
                    try {
                        result = DAO().getByHireStageWithPaging(searchDTO);
                    } catch (ItemNotFoundException e) {
                        throw new NoResultException();
                    }

                    responseDTO.setBasicDTOList(result);
                    responseDTO.setRequestDTO(requestDTO);
                }
            }

            return responseDTO;
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
     * getBy HirStage specific for Fatwa manaageId=4
     * @param ri
     * @param _searchDTO
     * @param manageId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO ri, IBasicDTO _searchDTO,
                                                       int manageId) throws DataBaseException,
                                                                            SharedApplicationException {

        IPagingResponseDTO responseDTO = null;
        try {
            if (_searchDTO != null) {
                IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;
                IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
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

                    responseDTO = new PagingResponseDTO();

                    if (requestDTO.isCountRequired()) {
                        if (searchDTO.getEmpHireTypes() != null &&
                            searchDTO.getEmpHireTypes().equals(IEMPConstants._EMP_CENTERAL_HIRE_TYPE)) {
                            responseDTO.setCount(DAO().getCountByHireStageAndMinistryWithPagingForCRS(searchDTO,
                                                                                                      manageId));
                        } else {
                            responseDTO.setCount(DAO().getCountByHireStageAndMinistryWithPaging(searchDTO, manageId));
                        }
                    }

                    List<IBasicDTO> result = null;
                    try {
                        // manageId represent pageId in presentation
                        if (searchDTO.getEmpHireTypes() != null &&
                            searchDTO.getEmpHireTypes().equals(IEMPConstants._EMP_CENTERAL_HIRE_TYPE)) {
                            result = DAO().getAllEmpCandsRelatedByCRS(searchDTO);
                        } else {
                            result = DAO().getAllEmpCandsRelatedBycontract(searchDTO);
                        }
                    } catch (ItemNotFoundException e) {
                        throw new NoResultException();
                    }

                    responseDTO.setBasicDTOList(result);
                    responseDTO.setRequestDTO(requestDTO);
                }
            }

            return responseDTO;
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

    public List<IBasicDTO> filterByHireType(IRequestInfoDTO ri, Long hirtypeCode) throws DataBaseException,
                                                                                         SharedApplicationException {

        return DAO().filterByHireType(hirtypeCode);
    }

    public List<IBasicDTO> filterByHireTypeWithMin(IRequestInfoDTO ri, Long hirtypeCode,
                                                   Long minCode) throws DataBaseException, SharedApplicationException {

        return DAO().filterByHireTypeWithMin(hirtypeCode, minCode);
    }

    public List<IBasicDTO> filterByAllHireTypes(IRequestInfoDTO ri) throws DataBaseException,
                                                                           SharedApplicationException {

        return DAO().filterByAllHireTypes();
    }

    public List<IBasicDTO> filterByAllHireTypesWithMin(IRequestInfoDTO ri, Long minCode) throws DataBaseException,
                                                                                                SharedApplicationException {

        return DAO().filterByAllHireTypesWithMin(minCode);
    }


    public List<IBasicDTO> getFilterEmpWaitingForHireDecWithDiffStage(IRequestInfoDTO ri,
                                                                      IBasicDTO empEmployeeSearchDTO1) throws DataBaseException,
                                                                                                              SharedApplicationException {

        try {
            IEmpEmployeeSearchDTO dto = (IEmpEmployeeSearchDTO)empEmployeeSearchDTO1;


            dto.setEmpHireStatus(IEMPConstants.EMP_CAND_STATUS_CANDITATE);

            return DAO().simpleSearch(dto);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
                                                                   IBasicDTO empEmployeeSearchDTO1) throws DataBaseException,
                                                                                                           SharedApplicationException {

        try {
            IEmpEmployeeSearchDTO dto = (IEmpEmployeeSearchDTO)empEmployeeSearchDTO1;
            dto.setEmpHireStage(IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION);
            dto.setEmpHireStatus(IEMPConstants.EMP_CAND_STATUS_CANDITATE);
            return DAO().simpleSearch(dto);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        }
    }

    /**
     * Search employee to complete employee's date * @param employeeSearchDTO
     * @return list of employee
     * @throws SharedApplicationException
     */
//    public List<IBasicDTO> searchEmployeeToCompleteData(IRequestInfoDTO ri,
//                                                        IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
//                                                                                               SharedApplicationException {
//        List<IEmpCandidatesDTO> employeesList;
//        boolean tempDocStatus = false;
//        boolean tempProcStatus = false;
//
//        try {
//            IEmpEmployeeSearchDTO _dto = (IEmpEmployeeSearchDTO)empEmployeeSearchDTO;
//            if (_dto.getEmpHireTypes().equals(0L)) {
//                employeesList = (List)DAO().getAllCandidatesByAllHireType(_dto);
//            } else {
//                employeesList = (List)DAO().completeDataSimpleSearch(_dto);
//            }
//            for (IEmpCandidatesDTO dto : employeesList) {
//                Long serial = ((IKwtCitizensResidentsEntityKey)dto.getCitizensResidentsDTO().getCode()).getCivilId();
//                tempDocStatus = checkEmployeeRequiredDocumentsStatus(serial);
//                tempProcStatus = checkEmployeeRequiredProceduresStatus(serial);
//                System.out.println("civilID=" + serial + "DocStatus=" + tempDocStatus);
//                System.out.println("civilID=" + serial + "ProcStatus=" + tempProcStatus);
//                dto.setDocumentsStatus(tempDocStatus);
//                dto.setProceduresStatus(tempProcStatus);
//            }
//
//            return (List)employeesList;
//
//        } catch (ItemNotFoundException e) {
//            throw new NoResultException();
//        }
//    }
    
    
    public IPagingResponseDTO searchEmployeeToCompleteData(IRequestInfoDTO ri, IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                              SharedApplicationException {

        IEmpEmployeeSearchDTO searchDTO = (IEmpEmployeeSearchDTO)empEmployeeSearchDTO;
        IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
        IPagingResponseDTO responseDTO = null;
        boolean tempDocStatus = false;
        boolean tempProcStatus = false;
            List<IBasicDTO> result = null;
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

            responseDTO = new PagingResponseDTO();
          
            if (searchDTO.getEmpHireTypes().equals(0L)) {
            if (requestDTO.isCountRequired()) {
                responseDTO.setCount(DAO().getAllCandidatesByAllHireTypeCount(searchDTO));
            }

           
            try {
                result = DAO().getAllCandidatesByAllHireType(searchDTO);
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            } catch (Throwable e) {
                e.printStackTrace();
                throw new SharedApplicationException();
            }

            } else {
                if (requestDTO.isCountRequired()) {
                    responseDTO.setCount(DAO().completeDataSimpleSearchCount(searchDTO));
                }

              
                try {
                    result = DAO().completeDataSimpleSearch(searchDTO);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                } catch (Throwable e) {
                    e.printStackTrace();
                    throw new SharedApplicationException();
                }
            }
            List<IEmpCandidatesDTO> employeesList = (List)result;
            for (IEmpCandidatesDTO dto : employeesList) {
                            Long serial = ((IKwtCitizensResidentsEntityKey)dto.getCitizensResidentsDTO().getCode()).getCivilId();
                            Long candCode=((IEmpCandidatesEntityKey)dto.getCode()).getCandidateCode();
                            tempDocStatus = checkEmployeeRequiredDocumentsStatus(serial,candCode);
                            tempProcStatus = checkEmployeeRequiredProceduresStatus(serial,candCode);
                            System.out.println("civilID=" + serial + "DocStatus=" + tempDocStatus);
                            System.out.println("civilID=" + serial + "ProcStatus=" + tempProcStatus);
                            dto.setDocumentsStatus(tempDocStatus);
                            dto.setProceduresStatus(tempProcStatus);
                        }
            responseDTO.setBasicDTOList(result);
            responseDTO.setRequestDTO(requestDTO);
        }

        return responseDTO;
    }

    private boolean checkEmployeeRequiredDocumentsStatus(Long serial , Long candCode) throws DataBaseException,
                                                                             SharedApplicationException {

        boolean status = false;
        Long result = ISystemConstant.VIRTUAL_VALUE;
        EmpCandidateDocumentsDAO empCandidateDocumentsDAO =
            (EmpCandidateDocumentsDAO)super.newDAOInstance(EmpCandidateDocumentsEntity.class);

        result = empCandidateDocumentsDAO.checkEmployeeRequiredDocumentsStatus(serial,candCode);

        return (result == IEMPConstant.EMP_DOCUMENT_COMPLETED);
    }

    private boolean checkEmployeeRequiredProceduresStatus(Long serial,Long candCode) throws DataBaseException,
                                                                              SharedApplicationException {
        boolean status = false;
        Long result = ISystemConstant.VIRTUAL_VALUE;
        EmpCandidateProceduresDAO empCandidateProceduresDAO =
            (EmpCandidateProceduresDAO)super.newDAOInstance(EmpCandidateProceduresEntity.class);
        result = empCandidateProceduresDAO.checkEmployeeRequiredProceduresStatus(serial,candCode);

        return (result == IEMPConstant.EMP_RESULT_GOOD);
    }

    public IEmpCandidatesDTO getByCndCodeAndSequnc(IRequestInfoDTO ri, IEntityKey id1) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return DAO().getByCndCodeAndSequnc(id1);
        } catch (ItemNotFoundException e) {
            throw new ItemNotFoundException();
        }
    }

    /**
     * update EmpCand CancelCandidate
     * @param employeesDTO1
     * @return
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public IBasicDTO updateEmpCancelCandidate(IRequestInfoDTO ri,
                                              IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                              DataBaseException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO employeesDTO = (IEmpCandidatesDTO)employeesDTO1;
        try {
            // update candidatePersonsDTO
            if (employeesDTO.getCandidatePersonsDTO() != null) {
                ICandidatePersonsDTO candidatePersonsDTO = (ICandidatePersonsDTO)employeesDTO.getCandidatePersonsDTO();
                candidatePersonsDTO.getCandidateStatusDTO().setCode(CrsEntityKeyFactory.createCandidateStatusEntityKey(3L));
                CrsClientFactory.getCandidatePersonsClient().update(candidatePersonsDTO);

                // jobSeekersDTO
                IJobSeekersDTO jobSeekersDTO = (IJobSeekersDTO)candidatePersonsDTO.getJobSeekersDTO();
                jobSeekersDTO.getRegStatusDTO().setCode(CrsEntityKeyFactory.createRegStatusEntityKey(3L));
                CrsClientFactory.getJobSeekersClient().update(jobSeekersDTO);
            }
            // update empCandidatesDTO
            DAO().update(employeesDTO);
            return employeesDTO;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }

    }
    //@Auditable
    public IBasicDTO updateEmpCMT(IRequestInfoDTO ri, IBasicDTO empCandidateDTO1) throws DataBaseException,
                                                                                         SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        System.out.println("Employee.updateEmpCMT::Start");
        try {

            IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)empCandidateDTO1;
            EmpCandidateExtraDataDAO empCandidateExtraDataDAO =
                (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);

            if (empCandidateDTO.getEmpCandidateExtraDataList() != null &&
                empCandidateDTO.getEmpCandidateExtraDataList().size() > 0) {

                for (IBasicDTO empCandidatesExtraData : empCandidateDTO.getEmpCandidateExtraDataList()) {
                    IEmpCandidateExtraDataDTO empCandidateExtraDataDTO =
                        (IEmpCandidateExtraDataDTO)empCandidatesExtraData;
                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getFatwaDeptNotes());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getSuggestedJobCode());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getSelectionDeptNotes());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getMinistryNotes());
                    if (empCandidateExtraDataDTO.getStatusFlag() != null &&
                        empCandidateExtraDataDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {

                        empCandidateExtraDataDAO.add(empCandidatesExtraData);
                    } else {
                        Long extraType =
                            ((EmpExtraDataTypesEntityKey)empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                        if (extraType.equals(13L)) {
                            String exDValue =
                                empCandidateExtraDataDAO.extraDataValueForType(((IEmpCandidatesEntityKey)empCandidateExtraDataDTO.getEmpCandidatesDTO().getCode()).getCandidateCode(),
                                                                               ((IEmpCandidatesEntityKey)empCandidateExtraDataDTO.getEmpCandidatesDTO().getCode()).getCandidateCodeSeq(),
                                                                               extraType);
                            if (!empCandidateExtraDataDTO.getValue().equals(exDValue))
                                empCandidateExtraDataDAO.update(empCandidatesExtraData);
                        } else
                            empCandidateExtraDataDAO.update(empCandidatesExtraData);
                    }

                }
            }


            System.out.println("Employee.update::Start");
            DAO().update(empCandidateDTO);
            System.out.println("Employee.update::End");

            return empCandidateDTO;

        } catch (DataBaseException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DataBaseException(e.getMessage());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            throw e;
        }


    }
    
    
    
    //@Auditable
    public IBasicDTO updateEmpData(IRequestInfoDTO ri, IBasicDTO empCandidateDTO1) throws DataBaseException,
                                                                                         SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        System.out.println("Employee.updateEmpData::Start");
        try {

            IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)empCandidateDTO1;
            EmpCandidateExtraDataDAO empCandidateExtraDataDAO =
                (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);

            if (empCandidateDTO.getEmpCandidateExtraDataList() != null &&
                empCandidateDTO.getEmpCandidateExtraDataList().size() > 0) {

                for (IBasicDTO empCandidatesExtraData : empCandidateDTO.getEmpCandidateExtraDataList()) {
                    IEmpCandidateExtraDataDTO empCandidateExtraDataDTO =
                        (IEmpCandidateExtraDataDTO)empCandidatesExtraData;
                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getFatwaDeptNotes());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getSuggestedJobCode());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getSelectionDeptNotes());

                    if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN.toString())))
                        empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getMinistryNotes());
                 
                
      
                    if (empCandidateExtraDataDTO.getStatusFlag() != null &&
                        empCandidateExtraDataDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        if (empCandidateExtraDataDTO.getValue().equals("Del")) 
                            System.out.println("No Add");
                            else
                        empCandidateExtraDataDAO.add(empCandidatesExtraData);
                    } else {
                        Long extraType =
                            ((EmpExtraDataTypesEntityKey)empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                        if (extraType.equals(13L)) {
                            String exDValue =
                                empCandidateExtraDataDAO.extraDataValueForType(((IEmpCandidatesEntityKey)empCandidateExtraDataDTO.getEmpCandidatesDTO().getCode()).getCandidateCode(),
                                                                               ((IEmpCandidatesEntityKey)empCandidateExtraDataDTO.getEmpCandidatesDTO().getCode()).getCandidateCodeSeq(),
                                                                               extraType);
                            if (empCandidateExtraDataDTO.getValue().equals("Del")) {
                                empCandidateExtraDataDAO.remove(empCandidateExtraDataDTO);
                            }else{
                              if (!empCandidateExtraDataDTO.getValue().equals(exDValue))
                                 empCandidateExtraDataDAO.update(empCandidateExtraDataDTO);
                            }
                        } else
                            empCandidateExtraDataDAO.update(empCandidatesExtraData);
                    }

                }
            }


            System.out.println("Employee.update::Start");
            DAO().update(empCandidateDTO);
            System.out.println("Employee.update::End");

            return empCandidateDTO;

        } catch (DataBaseException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw new DataBaseException(e.getMessage());
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            throw e;
        }


    }
    //@Auditable
    public IBasicDTO addNewSeqForCandidate(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO1) throws DataBaseException,
                                                                                                   SharedApplicationException {
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)empCandidatesDTO1;
        EmpCandidateExtraDataDAO empCandidateExtraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);

        //  DAO().update(empCandidateDTO);
        empCandidateDTO.setHireStageKey(IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS);
        empCandidateDTO = DAO().addNewSeqForCandidate(empCandidateDTO);

        if (empCandidateDTO.getEmpCandidateExtraDataList() != null &&
            empCandidateDTO.getEmpCandidateExtraDataList().size() > 0) {

            for (IBasicDTO empCandidatesExtraData : empCandidateDTO.getEmpCandidateExtraDataList()) {
                IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = (IEmpCandidateExtraDataDTO)empCandidatesExtraData;
                empCandidateExtraDataDTO.setEmpCandidatesDTO(empCandidateDTO);
                //                if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT.toString())))
                //                    empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getArrangmentDeptNotes());
                //
                //                if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT.toString())))
                //                    empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getFatwaDeptNotes());

                if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN.toString())))
                    empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getSuggestedJobCode());

                //                if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT.toString())))
                //                    empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getSelectionDeptNotes());

                if ((empCandidateExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN.toString())))
                    empCandidateExtraDataDTO.setValue(empCandidateDTO.getEmpExtraDataValueDTO().getMinistryNotes());

                //                if (empCandidateExtraDataDTO.getStatusFlag() != null &&
                //                    empCandidateExtraDataDTO.getStatusFlag().equals(ISystemConstant.ADDED_LAST_ITEM)) {
                //                    empCandidateExtraDataDAO.add(empCandidatesExtraData);
                //                }
            }
        }

        return empCandidateDTO;
    }
    //@Auditable
    public IBasicDTO AdoptionOfFinal(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO1) throws DataBaseException,
                                                                                             SharedApplicationException {
        IEmpCandidatesDTO empCandidateDTO = (IEmpCandidatesDTO)empCandidatesDTO1;
        EmpCndSalaryElementsDAO empCndSalaryElementsDAO =
            (EmpCndSalaryElementsDAO)super.newDAOInstance(EmpCndSalaryElementsEntity.class);

        if (empCandidateDTO.getHireDate() != null && !empCandidateDTO.getHireDate().equals("")) {
            Long civil_Id = Long.valueOf(empCandidateDTO.getCitizensResidentsDTO().getCode().getKey());
            Long candCode=((IEmpCandidatesEntityKey)empCandidateDTO.getCode()).getCandidateCode();
            boolean tempDocStatus = checkEmployeeRequiredDocumentsStatus(civil_Id,candCode);
            boolean tempProcStatus = checkEmployeeRequiredProceduresStatus(civil_Id,candCode);
            if ( // comented by M.abdelsabour due to PO CSC-21932 mpCandidateDTO.getMinistryFileNo() != null && !empCandidateDTO.getMinistryFileNo().equals("") &&
                empCandidateDTO.getWorkCentersDTO() != null && empCandidateDTO.getWorkCentersDTO().getCode() != null &&
                !empCandidateDTO.getWorkCentersDTO().getCode().equals("") && tempDocStatus && tempProcStatus) {

                empCandidateDTO.setHireStageKey(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS);
                //                throw new BeenAccreditationPleaseReviewTheDataCandidate();
            } else {
                empCandidateDTO.setHireStageKey(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS);
                //                throw new BeenAccreditationPleaseCompleteTheDataCandidate();
            }
            IEmpCandidatesDTO empCandDTO = empCandidateDTO;
            if (DAO().update(empCandidateDTO)) {
                IEmpCandidatesEntityKey keycode =
                    new EmpCandidatesEntityKey(((EmpCandidatesEntityKey)empCandidateDTO.getCode()).getCandidateCode(),
                                               1l);
                empCandDTO.setCode(keycode);
                ISalElementGuidesDTO salElmntGuideDTO = SalDTOFactory.createSalElementGuidesDTO();
                salElmntGuideDTO.setCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(empCandidateDTO.getAcceptedRaiseCode()));
                IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
                empCndSalaryElementsDTO.setElementRatio(IEMPConstants._SALARY_ELEMENT_RATIO);
                empCndSalaryElementsDTO.setFromDate(empCandidateDTO.getHireDate());
                empCndSalaryElementsDTO.setSalElementGuidesDTO(salElmntGuideDTO);
                empCndSalaryElementsDTO.setEmpCandidatesDTO(empCandDTO);
                empCndSalaryElementsDAO.add(empCndSalaryElementsDTO);
            }
        } else {
            throw new HireDateRequiredException();
        }

        return empCandidateDTO;

    }

    private boolean checkcompleteData(IEmpCandidatesDTO employeesDTO) throws SharedApplicationException,
                                                                             DataBaseException {
        boolean tempDocStatus = false;
        boolean tempProcStatus = false;
        Long serial = ((IKwtCitizensResidentsEntityKey)employeesDTO.getCitizensResidentsDTO().getCode()).getCivilId();
        Long candCode=((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCode();
        tempDocStatus = checkEmployeeRequiredDocumentsStatus(serial,candCode);
        tempProcStatus = checkEmployeeRequiredProceduresStatus(serial,candCode);
        if (tempDocStatus && tempProcStatus &&
            (employeesDTO.getWorkCentersDTO() != null && employeesDTO.getWorkCentersDTO().getCode() != null) &&
            employeesDTO.getJobsDTO() != null &&
            (employeesDTO.getBgtTypesDTO() != null && employeesDTO.getBgtTypesDTO().getCode() != null) &&
            employeesDTO.getEmpCndSalaryElementsList() != null &&
            employeesDTO.getEmpCndSalaryElementsList().size() > 0) {
            return true;
        }
        return false;
    }
    //@Auditable
    public IBasicDTO updateEmpCandCMT(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                                          DataBaseException {
        System.out.println("EmployeeCandidate.updateEmpCMT:::Start--->");
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidateParent empCandidateParent = (IEmpCandidateParent)employeesDTO1;
        IEmpCandidatesDTO employeesDTO = empCandidateParent.getSelectedEmpCandidatesDTO();
        /** business required EmpCandidatesDTO that contain candSeq=1 to save in Documents and Procedure**/
        IEmpCandidatesDTO firstEmpCandidatesDTO = empCandidateParent.getFirstEmpCandidatesDTO();

        EmpCandidateProceduresDAO empCandidateProceduresDAO =
            (EmpCandidateProceduresDAO)super.newDAOInstance(EmpCandidateProceduresEntity.class);
        EmpCandidateDocumentsDAO empCandidateDocumentsDAO =
            (EmpCandidateDocumentsDAO)super.newDAOInstance(EmpCandidateDocumentsEntity.class);
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)super.newDAOInstance(RequiredDocumentsEntity.class);

        HireTypesDAO hireTypesDAO = (HireTypesDAO)super.newDAOInstance(HireTypesEntity.class);
        IEmpCandidatesDTO oldEmp = (IEmpCandidatesDTO)this.getById(ri, employeesDTO.getCode());

        System.out.println("Start / KwtCitizensResidentsClient().update");
        if (employeesDTO.isWitoutQualFlag())
            ((IKwtCitizensResidentsDTO) employeesDTO.getCitizensResidentsDTO()).setNoQual(1L);
        else
            ((IKwtCitizensResidentsDTO) employeesDTO.getCitizensResidentsDTO()).setNoQual(null);
        InfClientFactory.getKwtCitizensResidentsClient().update(employeesDTO.getCitizensResidentsDTO());
        System.out.println("End / KwtCitizensResidentsClient().update");
        /*Check if Start work date greater than hire date*/
        //this.checkHireAndStartWorkDate(employeesDTO);

        //set date of next raise
        //  employeesDTO = this.addDateOfNextRaise(employeesDTO);

        //used to update stage to complete document
        boolean documentStatus = true;

        //used to update stage to complete procedure
        boolean procedureStatus = true;
        try {
            List<IEmpCndSalaryElementsDTO> empCndSalElmList =
                (List)firstEmpCandidatesDTO.getEmpCndSalaryElementsList();
            if (empCndSalElmList != null) {
                for (IEmpCndSalaryElementsDTO dto : empCndSalElmList) {
                    if (dto.getStatusFlag() != null && dto.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        dto.setEmpCandidatesDTO(firstEmpCandidatesDTO);
                        System.out.println("Start empCndSalElmDAO.add");
                        empCndSalElmDAO.add(dto);
                        System.out.println("End empCndSalElmDAO.add");
                    } else if (dto.getStatusFlag() != null &&
                               dto.getStatusFlag().equals(ISystemConstant.ADDED_LAST_ITEM)) {
                        System.out.println("Start empCndSalElmDAO.updat");
                        empCndSalElmDAO.updat(dto);
                        System.out.println("End empCndSalElmDAO.updat");
                    }
                }
            }

            if (employeesDTO.getPersonsInformationDTOList() != null) {
                List<IPersonsInformationDTO> personsInformationDTOList = employeesDTO.getPersonsInformationDTOList();
                if (personsInformationDTOList != null) {
                    IPersonsInformationCMTClient personsInformationClient =
                        InfClientFactory.getPersonsInformationCMTClient();
                    IPersonQualificationsClient personQualificationsClient =InfClientFactory.getPersonQualificationsClient();
                    for (IPersonsInformationDTO dto : personsInformationDTOList) {
                        System.out.println("Start personsInformationClient.addCMT");
                        if (dto.getStatusFlag() != null && dto.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        personsInformationClient.addCMT(dto);
                        }else{
                            if(dto.getPersonQualificationsDTO() != null){
                             personQualificationsClient.updateCurrentQual(dto.getPersonQualificationsDTO());
                            }
                            }
                        System.out.println("End personsInformationClient.addCMT");
                    }
                }
            }
                if (employeesDTO.getDeletedPersonsInformationDTOList() != null) {
                
                    IPersonsInformationClient client = ClientFactoryUtil.getInstance(IPersonsInformationClient.class);
                    IPersonQualificationsClient qualificationClient  = ClientFactoryUtil.getInstance(IPersonQualificationsClient.class);
                    for (IPersonsInformationDTO dto : employeesDTO.getDeletedPersonsInformationDTOList()) {
                    if (dto.getPersonQualificationsDTO() != null)
                        qualificationClient.removePersonQual(dto.getPersonQualificationsDTO());
                    client.remove(dto);
                    }
                }
            List<IKwtWorkDataTreeDTO> kwtWorkDataTreeDTOList = employeesDTO.getKwtWorkDataTreeDTOList();
            if (kwtWorkDataTreeDTOList != null) {
                IKwtWorkDataClient kwtWorkDataClient = InfClientFactory.getKwtWorkDataClient();
                for (IKwtWorkDataTreeDTO dto : kwtWorkDataTreeDTOList) {
                    System.out.println("Start kwtWorkDataClient.addKwtWorkDataTreeDTO");
                    kwtWorkDataClient.addKwtWorkDataTreeDTO(dto);
                    System.out.println("End kwtWorkDataClient.addKwtWorkDataTreeDTO");
                }
            }
            ////////////////////////////////////////////////////////////////
            //get old hire type code
            //            System.out.println("Employee.getById::Start");


            Long hireTypeCode =
                ((IHireTypesEntityKey)firstEmpCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode();

            System.out.println("Employee.updateEmployeeDocumentsCMT::Start");
            //TODO by i.omar-review
            documentStatus = updateEmployeeDocumentsCMT(oldEmp, firstEmpCandidatesDTO);
            System.out.println("Employee.updateEmployeeDocumentsCMT::End");

            System.out.println("Employee.updateEmployeeProcedureCMT::Start");
            //TODO by i.omar-review
            procedureStatus = updateEmployeeProcedureCMT(firstEmpCandidatesDTO);
            System.out.println("Employee.updateEmployeeProcedureCMT::End");

            //check for added procedure or add required document
            List<IBasicDTO> availableDocumentList = null;
            List<IBasicDTO> availableProcedureList = null;

            try {
                //get list of document and procedure
                //using try and catch here to void item not found from listAvailableEntities method
                availableDocumentList =
                        empCandidateDocumentsDAO.listAvailableEntities(firstEmpCandidatesDTO.getCode(), firstEmpCandidatesDTO.getHireTypesDTO().getCode());
            } catch (NoResultException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                availableProcedureList =
                        empCandidateProceduresDAO.listAvailableEntities(firstEmpCandidatesDTO.getCode(),
                                                                        firstEmpCandidatesDTO.getHireTypesDTO().getCode());
            } catch (NoResultException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            //TODO by i.omar-review

            if (availableDocumentList != null && availableDocumentList.size() > 0) {
                for (IBasicDTO reqDocument : availableDocumentList) {
                    if (requiredDocumentsDAO.checkRequired(hireTypeCode,
                                                           (((IInfDocumentTypesEntityKey)(((IRequiredDocumentsDTO)reqDocument).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE)) {
                        documentStatus = false;
                        break;
                    }
                }
            }

            if (availableProcedureList != null && availableProcedureList.size() > 0) {
                procedureStatus = false;
            }

            //update employee stage
            Long hireStageCode = ((IHireStagesEntityKey)employeesDTO.getHireStagesDTO().getCode()).getHirstageCode();
            System.out.println("Stageeeeeeeeeeeee>>>>>>>>>>" + hireStageCode);
            IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
            Long code = null;
            // check about complete Data to Set HirStage
            if (this.checkcompleteData(employeesDTO)) {
                code = IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION;
                if (employeesDTO.isMinExcepted()) {
                    IEmpCndSalaryElementsDTO empCndSalaryElementsDTO =
                        (IEmpCndSalaryElementsDTO)empCndSalElmList.get(0);
                    if (empCndSalaryElementsDTO.getElementValue() != null &&
                        empCndSalaryElementsDTO.getElmguideCodeEquv() != null) {
                        code = IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION;
                }else{
                        code = IEMPConstant.EMP_STAGE_DEFAULT;
                }
                }
                stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(code));
            } else {
                if (hireStageCode.equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION)) {
                    code = IEMPConstant.EMP_STAGE_DEFAULT;
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(code));
                } else {
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(hireStageCode));
                }
            }
            employeesDTO.setDocumentsStatus(documentStatus);
            employeesDTO.setProceduresStatus(procedureStatus);
            employeesDTO.setHireStagesDTO(stage);
            System.out.println("Start empCnd.update");
            if (DAO().update(employeesDTO)) {
                //to use HirtTypeDTO in Presentation
                IEntityKey hirTypeCode =
                    EmpEntityKeyFactory.createHireTypesEntityKey(Long.valueOf(employeesDTO.getHireTypesDTO().getCode().getKey()));
                employeesDTO.setHireTypesDTO(hireTypesDAO.getById(hirTypeCode));
                System.out.println("End empCnd.update");
                System.out.println("EmployeeCandidate.updateEmpCMT:::End--->");
                return employeesDTO;
            } else {
                return null;
            }

        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }
    //@Auditable
    private boolean updateEmployeeProcedureCMT(IEmpCandidatesDTO employeesDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        boolean procedureStatus = true;
        EmpCandidateProceduresDAO empCandidateProceduresDAO =
            (EmpCandidateProceduresDAO)super.newDAOInstance(EmpCandidateProceduresEntity.class);
        //update list of employee procedure
        if (employeesDTO.getEmpCandidateProceduresList() != null) {
            for (IBasicDTO employeeProceduresDTO : employeesDTO.getEmpCandidateProceduresList()) {
                if (((IEmpCandidateProceduresDTO)employeeProceduresDTO).getProcedureResultsDTO() != null) {
                    if (employeeProceduresDTO.getStatusFlag() == null) {
                        if (!validateEmpProcedureStatus((IEmpCandidateProceduresDTO)employeeProceduresDTO))
                            procedureStatus = false;
                        empCandidateProceduresDAO.update(employeeProceduresDTO);
                    } else if (employeeProceduresDTO.getStatusFlag() != null &&
                               employeeProceduresDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        ((IEmpCandidateProceduresDTO)employeeProceduresDTO).setEmpCandidatesDTO(employeesDTO);
                        //check if any procedure result not done then set procedure= false
                        if (!validateEmpProcedureStatus((IEmpCandidateProceduresDTO)employeeProceduresDTO))
                            procedureStatus = false;
                        empCandidateProceduresDAO.add(employeeProceduresDTO);
                    } else if (employeeProceduresDTO.getStatusFlag() != null &&
                               employeeProceduresDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                        empCandidateProceduresDAO.remove(employeeProceduresDTO);
                    }
                }
            }
        }
        return procedureStatus;
    }

    /**
     * validate employee procedure hireStageName code if good OR suspended return true else false
     * suspended and good will be the same handling REC057
     * by Ashraf Gaber
     * @param employeeProceduresDTO
     * @return boolean
     */
    private boolean validateEmpProcedureStatus(IEmpCandidateProceduresDTO employeeProceduresDTO) {
        Long procedureCode = 0L;
        if (employeeProceduresDTO.getProcedureResultsDTO() != null &&
            employeeProceduresDTO.getProcedureResultsDTO().getCode() != null) {
            procedureCode =
                    ((IProcedureResultsEntityKey)(employeeProceduresDTO.getProcedureResultsDTO().getCode())).getProresultCode();
        }
        if (procedureCode.equals(IEMPConstant.EMP_RESULT_GOOD) ||
            procedureCode.equals(IEMPConstant.EMP_RESULT_SUSPENDED)) {
            return true;
        }
        return false;
    }

    /**
     * check start work date and hire date * @param employeesDTO
     * @return Boolean
     * @throws InvalidDataEntryException
     */
    private Boolean checkHireAndStartWorkDate(IEmpCandidatesDTO employeesDTO) throws InvalidDataEntryException {
        if (employeesDTO.getHireDate() != null && employeesDTO.getStartWorkingDate() != null &&
            SharedUtils.compareDatesOnly(employeesDTO.getHireDate(), employeesDTO.getStartWorkingDate()) > 0) {
            throw new InvalidDataEntryException();
        }
        return Boolean.TRUE;
    }

    /**
     * Add SalEmployee Salary * @param employeesDTO
     * @return IBasicDTO
     * @throws InvalidDataEntryException
     * @throws ItemNotFoundException
     */
     //@Auditable
    private IEmpCandidatesDTO addDateOfNextRaise(IEmpCandidatesDTO employeesDTO) throws InvalidDataEntryException {
        if (employeesDTO.getHireDate() != null) {
            employeesDTO.setDateOfNextRaise(calculateNextRaiseDate(employeesDTO.getHireDate()));
        }
        return employeesDTO;
    }

    private Date calculateNextRaiseDate(Date hireDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(hireDate);
        String sqlMonth = "";

        if (cal.get(Calendar.MONTH) == 0 && cal.get(Calendar.DAY_OF_MONTH) == 1) {
            sqlMonth = "1";
            cal.add(Calendar.YEAR, 1);
        } else if (cal.get(Calendar.MONTH) < 6 ||
                   (cal.get(Calendar.MONTH) == 6 && cal.get(Calendar.DAY_OF_MONTH) == 1)) {
            sqlMonth = "7";
            cal.add(Calendar.YEAR, 1);
        } else {
            sqlMonth = "1";
            cal.add(Calendar.YEAR, 2);
        }
        return Date.valueOf("" + cal.get(Calendar.YEAR) + "-0" + sqlMonth + "-01");
    }
    //@Auditable
    private void addDocumentAttachments(IBasicDTO dto) throws DataBaseException, SharedApplicationException {
        IPersonDocAttachemntsClient personDocAttachemntsClient =
            ClientFactoryUtil.getInstance(IPersonDocAttachemntsClient.class);

        IEmpCandidateDocumentsDTO empCandidateDocumentsDTO = (IEmpCandidateDocumentsDTO)dto;
        if (empCandidateDocumentsDTO.getAttachmentsLst() != null) {
            for (Object basic : empCandidateDocumentsDTO.getAttachmentsLst()) {
                PersonDocAttachemntsDTO docDTO = (PersonDocAttachemntsDTO)basic;
                if (docDTO.getStatusFlag() != null && docDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                    personDocAttachemntsClient.addPersonDocAttachment(docDTO);
                } else if (docDTO.getStatusFlag() != null &&
                           docDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                    personDocAttachemntsClient.remove(docDTO);
                }
            }
        }
    }
    //@Auditable
    private boolean updateEmployeeDocumentsCMT(IEmpCandidatesDTO oldEmp,
                                               IEmpCandidatesDTO employeesDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        boolean documentStatus = true;
        Map<String, IBasicDTO> empDocMap = null;
        Long oldHireTypeCode = null;
        if (oldEmp != null) {
            oldHireTypeCode = ((IHireTypesEntityKey)oldEmp.getHireTypesDTO().getCode()).getHirtypeCode();
        }

        Long hireTypeCode = ((IHireTypesEntityKey)employeesDTO.getHireTypesDTO().getCode()).getHirtypeCode();
        EmpCandidateDocumentsDAO empCandidateDocumentsDAO =
            (EmpCandidateDocumentsDAO)super.newDAOInstance(EmpCandidateDocumentsEntity.class);
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)super.newDAOInstance(RequiredDocumentsEntity.class);

        if (oldHireTypeCode != null && !(oldHireTypeCode.equals(hireTypeCode))) {
            empDocMap = this.initDocMap(oldEmp);
        }
        try {
            //update list of employee documents
            if (employeesDTO.getEmpCandidateDocumentsList() != null) {
                for (IBasicDTO employeeDocumentsDTO : employeesDTO.getEmpCandidateDocumentsList()) {
                    if (((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getDocStatus() == null) {
                        ((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).setDocStatus(0L);
                    }
                    if (((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getAttachmentStatus() == null) {
                        ((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).setAttachmentStatus(0L);
                    }

                    if (employeeDocumentsDTO.getStatusFlag() == null) {
                        //Check if that document is required for that hire type
                        //and set document status
                        if (((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getDocStatus() != null &&
                            !((((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getDocStatus()).equals(IEMPConstant.EMP_DOCUMENT_COMPLETED)) &&
                            requiredDocumentsDAO.checkRequired(hireTypeCode,
                                                               (((IInfDocumentTypesEntityKey)(((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getDocumentTypesDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE))
                            documentStatus = false;
                        empCandidateDocumentsDAO.update(employeeDocumentsDTO);
                    } else if (employeeDocumentsDTO.getStatusFlag() != null &&
                               employeeDocumentsDTO.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                        ((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).setEmpCandidateDTO(employeesDTO);
                        //Check if that document is required for that hire type
                        //and set document status
                        if (!IEMPConstant.EMP_DOCUMENT_COMPLETED.equals(((((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getDocStatus()))))
                            if (requiredDocumentsDAO.checkRequired(hireTypeCode,
                                                                   (((IInfDocumentTypesEntityKey)(((IEmpCandidateDocumentsDTO)employeeDocumentsDTO).getDocumentTypesDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE)) {
                                documentStatus = false;
                            }
                        Long serial =
                            empCandidateDocumentsDAO.findNewSerialforEmpCandidateDocuments(((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCode(),
                                                                                           ((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCodeSeq());
                        employeeDocumentsDTO.setCode(EmpEntityKeyFactory.createEmpCandidateDocumentsEntityKey(((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCode(),
                                                                                                              ((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCodeSeq(),
                                                                                                              serial));
                        if (empDocMap == null) {
                            empCandidateDocumentsDAO.add(employeeDocumentsDTO);
                        } else if (empDocMap.get(employeeDocumentsDTO.getCode().getKey()) == null) {
                            empCandidateDocumentsDAO.add(employeeDocumentsDTO);

                        } else {
                            empDocMap.remove(employeeDocumentsDTO.getCode().getKey());
                        }
                    } else if (employeeDocumentsDTO != null && employeeDocumentsDTO.getStatusFlag() != null &&
                               employeeDocumentsDTO.getStatusFlag().equals(ISystemConstant.DELEDTED_ITEM)) {
                        empCandidateDocumentsDAO.remove(employeeDocumentsDTO);
                    }
                    addDocumentAttachments(employeeDocumentsDTO);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //        remove old doc list
        if (oldHireTypeCode != null && !(oldHireTypeCode.equals(hireTypeCode)))
            this.removeOldDecouments((HashMap)empDocMap);

        return documentStatus;
    }

    /**
     * remove old decouments if user change hire type * @param map
     * @throws ItemNotFoundException
     */
     //@Auditable
    private void removeOldDecouments(HashMap map) throws DataBaseException, SharedApplicationException {
        EmpCandidateDocumentsDAO empCandidateDocumentsDAO =
            (EmpCandidateDocumentsDAO)super.newDAOInstance(EmpCandidateDocumentsEntity.class);
        if (!map.isEmpty()) {
            Iterator iterate = map.values().iterator();
            while (iterate.hasNext()) {
                empCandidateDocumentsDAO.remove((IBasicDTO)iterate.next());
            }
        }
    }

    /**
     * Set old documents in map to delete it later * @param oldEmp
     * @return Map<String , BasicDTO>
     */
    private Map<String, IBasicDTO> initDocMap(IEmpCandidatesDTO oldEmp) {
        Map<String, IBasicDTO> docMap = new HashMap<String, IBasicDTO>();
        if (oldEmp.getEmpCandidateDocumentsList() != null)
            for (IBasicDTO empDocDTO : oldEmp.getEmpCandidateDocumentsList()) {
                docMap.put(empDocDTO.getCode().getKey(), empDocDTO);
            }
        return docMap;
    }


    public boolean checkEmployeeByExtraDataType(IRequestInfoDTO ri, Long civilId,
                                                Long extDatatype,Long candidateCode) throws DataBaseException,
                                                                         SharedApplicationException {
        return DAO().checkEmployeeByExtraDataType(civilId, extDatatype,candidateCode);
    }
    //@Auditable
    public boolean suggestNewJob(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
                                 IBasicDTO extraDataDTO) throws DataBaseException, SharedApplicationException {
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        IEmployeeExtraDataDTO extraDTo = (IEmployeeExtraDataDTO)extraDataDTO;
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        boolean datafound =
            extraDataDAO.isActiveExtraDataForCivil(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode(),
                                                   ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
        if (datafound) {
            DTO.setActiveFlag(0L);
            DAO().update(DTO);
            DTO.setActiveFlag(1L);
            DTO.setHirstageCode(9L);
            DTO = (IEmpCandidatesDTO)addNewSeqForCandidate(ri, DTO);
        } else {
            DTO.setHirstageCode(9L);
            update(ri, DTO);
        }
        extraDataDAO.add(extraDTo);
        return true;
    }


    //first case
    //@Auditable
    public IBasicDTO proceedContractPersonData(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
                                               IBasicDTO extraDataDTO) throws DataBaseException,
                                                                              SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        IEmpExtraDataValueDTO extraDTo = (IEmpExtraDataValueDTO)extraDataDTO;
        IEmpCandidateExtraDataDTO newDTO;
        IEmpExtraDataTypesDTO extraDataTypeDto;
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        boolean datafound = true;
        if (Integer.parseInt(((IEmpCandidatesDTO)empCandidatesDTO).getHireStagesDTO().getCode().getKey()) != 16) {
            datafound =
                    extraDataDAO.isActiveExtraDataForCivil(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode(),
                                                           ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
        }
        DTO.setJobsDTO(null);
        if (datafound) {
            DTO.setActiveFlag(0L);
            DAO().updateActiveFlag(DTO);

            DTO.setActiveFlag(1L);

            DTO.setHirstageCode(9L);
            DTO = (IEmpCandidatesDTO)addNewSeqForCandidate(ri, DTO);
        } else {
            //DTO.setHirstageCode(9L);
            IHireStagesEntityKey key = new HireStagesEntityKey((9L));
            DTO.getHireStagesDTO().setCode(key);
            DAO().update(DTO);
        }

        String jobcode = "";
        if (extraDTo.getSuggestedJobCode() != null) {
            jobcode = extraDTo.getSuggestedJobCode().trim();
        }
        if (!jobcode.equals("")) {
            Long code = extraDataDAO.findMaxId();
            EmployeeExtraDataEntityKey extraKey = new EmployeeExtraDataEntityKey(code);
            newDTO = new EmpCandidateExtraDataDTO();
            newDTO.setCode(extraKey);
            EmpExtraDataTypesEntityKey typekey = new EmpExtraDataTypesEntityKey(4L);
            extraDataTypeDto = new EmpExtraDataTypesDTO();
            extraDataTypeDto.setCode(typekey);
            newDTO.setEmpExtraDataTypesDTO(extraDataTypeDto);
            newDTO.setValue(extraDTo.getSuggestedJobCode());
            newDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
            newDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
            extraDataDAO.add(newDTO);
        }

        String ministryNotes = "";
        if (extraDTo.getMinistryNotes().trim() != null) {
            ministryNotes = extraDTo.getMinistryNotes().trim();
        }
        if (!ministryNotes.trim().equals("")) {
            Long code = extraDataDAO.findMaxId();
            EmployeeExtraDataEntityKey extraKey = new EmployeeExtraDataEntityKey(code);
            newDTO = new EmpCandidateExtraDataDTO();
            newDTO.setCode(extraKey);
            EmpExtraDataTypesEntityKey typekey = new EmpExtraDataTypesEntityKey(5L);
            extraDataTypeDto = new EmpExtraDataTypesDTO();
            extraDataTypeDto.setCode(typekey);
            newDTO.setEmpExtraDataTypesDTO(extraDataTypeDto);
            newDTO.setValue(extraDTo.getMinistryNotes());
            newDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
            newDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
            extraDataDAO.add(newDTO);
        }

        String suggestedTotalReward = "";
        if (extraDTo.getSuggestedTotalReward().trim() != null) {
            suggestedTotalReward = extraDTo.getSuggestedTotalReward().trim();
        }
        if (!suggestedTotalReward.equals("")) {
            Long code = extraDataDAO.findMaxId();
            EmployeeExtraDataEntityKey extraKey = new EmployeeExtraDataEntityKey(code);
            newDTO = new EmpCandidateExtraDataDTO();
            newDTO.setCode(extraKey);
            EmpExtraDataTypesEntityKey typekey = new EmpExtraDataTypesEntityKey(17L);
            extraDataTypeDto = new EmpExtraDataTypesDTO();
            extraDataTypeDto.setCode(typekey);
            newDTO.setEmpExtraDataTypesDTO(extraDataTypeDto);
            newDTO.setValue(suggestedTotalReward);
            newDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
            newDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
            extraDataDAO.add(newDTO);
        }
        if(!datafound){
        Long code = extraDataDAO.findMaxId();
        EmployeeExtraDataEntityKey extraKey = new EmployeeExtraDataEntityKey(code);
        newDTO = new EmpCandidateExtraDataDTO();
        newDTO.setCode(extraKey);
        EmpExtraDataTypesEntityKey typekey = new EmpExtraDataTypesEntityKey(326L);
        extraDataTypeDto = new EmpExtraDataTypesDTO();
        extraDataTypeDto.setCode(typekey);
        newDTO.setEmpExtraDataTypesDTO(extraDataTypeDto);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        java.util.Date date = new java.util.Date();  
        newDTO.setValue(formatter.format(date));
        newDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
        newDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
        extraDataDAO.add(newDTO);
            
        }
        

        return DTO;
    }

    //third case
    //@Auditable
    public IBasicDTO applyJobSuggestion(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        EmpCndSalaryElementsDAO cndsalaryDAO =
            (EmpCndSalaryElementsDAO)super.newDAOInstance(EmpCndSalaryElementsEntity.class);
        if (DTO.getApprovedJobsDTO()!=null && DTO.getApprovedJobsDTO().getCode() != null)
           DTO.setJobsDTO(DTO.getApprovedJobsDTO());
        if (checkcompleteData(DTO)) {
            IHireStagesEntityKey key = new HireStagesEntityKey((2L));
            DTO.getHireStagesDTO().setCode(key);
        } else {
            IHireStagesEntityKey key = new HireStagesEntityKey((1L));
            DTO.getHireStagesDTO().setCode(key);
        }
        DAO().update(DTO);
        IEmpCndSalaryElementsDTO cndSalaryElementDTo =
            ((IEmpCndSalaryElementsDTO)DTO.getEmpCndSalaryElementsList().get(0));
        cndSalaryElementDTo.setElmguideCodeEquv(Long.parseLong(cndSalaryElementDTo.getSalEqElementGuidesDTO().getCode().getKey()));
        cndsalaryDAO.updateElmguideCodeEquv(cndSalaryElementDTo);
        return DTO;
    }

    ////////////////////////////////////////CRS Functions//////////////////////////////////////////////////////////////////////

    //@Auditable
    public IBasicDTO proceedCRSPersonData(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
                                          List<IEmpCandidateExtraDataDTO> extraDataDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        boolean datafound = true;
        if (Integer.parseInt(((IEmpCandidatesDTO)empCandidatesDTO).getHireStagesDTO().getCode().getKey()) != 16) {
            datafound =
                    extraDataDAO.isActiveExtraDataForCivil(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode(),
                                                           ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
        }
        DTO.setJobsDTO(null);

        if (datafound) {
            DTO.setActiveFlag(0L);
            DAO().updateActiveFlag(DTO);

            DTO.setActiveFlag(1L);

            DTO.setHirstageCode(9L);
            DTO = (IEmpCandidatesDTO)addNewSeqForCandidate(ri, DTO);
        } else {

            IHireStagesEntityKey key = new HireStagesEntityKey((9L));
            DTO.getHireStagesDTO().setCode(key);
            DAO().update(DTO);
        }
        if (extraDataDTO != null) {
            for (IEmpCandidateExtraDataDTO newDTO : extraDataDTO) {

                Long extraType =
                    ((EmpExtraDataTypesEntityKey)newDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(5L) && !newDTO.getValue().trim().equals("")) {
                    newDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
                    newDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
                    extraDataDAO.add(newDTO);
                }
                if (extraType.equals(4L) || extraType.equals(12L) ||  (extraType.equals(326L) && !datafound)) {
                    newDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
                    newDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
                    extraDataDAO.add(newDTO);

                }
            }
        }
        return DTO;
    }

    //@Auditable
    public IBasicDTO applyCRSSuggestion(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        try {
            IEmpCndSalaryElementsDTO empCndSalaryElementsDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
            ISalElementGuidesDTO salElmntGuideDTO = SalDTOFactory.createSalElementGuidesDTO();
            salElmntGuideDTO.setCode(SalEntityKeyFactory.createSalElementGuidesEntityKey(DTO.getAcceptedRaiseCode()));
            empCndSalaryElementsDTO = empCndSalElmDAO.getByCandCodeForCRS(DTO.getCode());
            if (empCndSalaryElementsDTO == null) {
                empCndSalaryElementsDTO = EmpDTOFactory.createEmpCndSalaryElementsDTO();
                empCndSalaryElementsDTO.setElementRatio(IEMPConstants._SALARY_ELEMENT_RATIO);
                if (DTO.getHireDate() == null) {
                    Calendar cal = Calendar.getInstance();
                    Date date = new Date(cal.getTimeInMillis());
                    empCndSalaryElementsDTO.setFromDate(date);
                } else {
                    empCndSalaryElementsDTO.setFromDate(DTO.getHireDate());
                }
                empCndSalaryElementsDTO.setSalElementGuidesDTO(salElmntGuideDTO);
                IEmpCandidatesEntityKey oldKeycode = ((EmpCandidatesEntityKey)DTO.getCode());
                IEmpCandidatesEntityKey keycode =
                    new EmpCandidatesEntityKey(((EmpCandidatesEntityKey)DTO.getCode()).getCandidateCode(), 1l);
                DTO.setCode(keycode);
                empCndSalaryElementsDTO.setEmpCandidatesDTO(DTO);
                empCndSalElmDAO.add(empCndSalaryElementsDTO);
                DTO.setCode(oldKeycode);
            } else {
                empCndSalaryElementsDTO.setSalElementGuidesDTO(salElmntGuideDTO);
                empCndSalElmDAO.updateElementGuide(empCndSalaryElementsDTO);
            }
            if (DTO.getEmpCndSalaryElementsList() == null) {
                DTO.setEmpCndSalaryElementsList(new ArrayList());
            }
            DTO.getEmpCndSalaryElementsList().add(empCndSalaryElementsDTO);
            if (DTO.getApprovedJobsDTO()!=null && DTO.getApprovedJobsDTO().getCode() != null)
                DTO.setJobsDTO(DTO.getApprovedJobsDTO());
            
            if (checkcompleteData(DTO)) {
                IHireStagesEntityKey key =
                    new HireStagesEntityKey(Long.valueOf(IEMPConstants.HIRE_STAGE_RELEASE_DECISION_INPROGRESS));
                DTO.getHireStagesDTO().setCode(key);
            } else {
                IHireStagesEntityKey key =
                    new HireStagesEntityKey(Long.valueOf(IEMPConstants.HIRE_STAGE_COMPLETING_INFO_INPROGRESS));
                DTO.getHireStagesDTO().setCode(key);
            }
            DAO().update(DTO);
            return DTO;
        } catch (ItemNotFoundException ex) {
            rollbackTransaction();
            return AdoptionOfFinal(ri, DTO);
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }


    }
    /////////////////////////////////////////////////ادارة ال�?توى/////////////////////////////////////////////////////////////
    //@Auditable
    public boolean approveFinancialDegreeFatwa(IRequestInfoDTO ri,
                                               IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
    
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {

                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(8L) || extraType.equals(14L)) {
                    extraDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
                    extraDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
                    if(extraDataDAO.isActiveExtraDataForType(extraDTO.getCandidateCode(), extraDTO.getCandidateCodeSeq(), extraType)){
                            extraDataDAO.update(extraDTO);
                    }else{
                    
                    extraDataDAO.add(extraDTO);
                    }
                }
            }
        }
        return true;

    }
    //@Auditable
    public boolean fromFatwaToChoiceDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                                SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((16L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                extraDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
                extraDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                    if(   extraDataDAO.isActiveExtraDataForType(extraDTO.getCandidateCode(), extraDTO.getCandidateCodeSeq(), extraType)){
                    extraDataDAO.update(extraDTO);
                    } else
                    extraDataDAO.add(extraDTO);
            }
        }
        return true;

    }

    //@Auditable
    public boolean approveJobOrderDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                              SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((10L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {

                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(7L) || extraType.equals(13L) || extraType.equals(15L)|| extraType.equals(345L)) {
                    extraDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
                    extraDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
                    if(extraDataDAO.isActiveExtraDataForType(extraDTO.getCandidateCode(), extraDTO.getCandidateCodeSeq(), extraType)){
                        if (extraType.equals(13L)) {
                            String exDValue =
                                extraDataDAO.extraDataValueForType(extraDTO.getCandidateCode(), extraDTO.getCandidateCodeSeq(),
                                                                   extraType);
                            
                            
                            if (extraDTO.getValue().equals("Del")) {
                                extraDataDAO.remove(extraDTO);
                            }else{
                              if (!extraDTO.getValue().equals(exDValue))
                                 extraDataDAO.update(extraDTO);
                            }
                        } else
                            extraDataDAO.update(extraDTO);
                    }else{
                        if (extraDTO.getValue().equals("Del")) {
                        System.out.println("No Add");
                        } else {
                    extraDataDAO.add(extraDTO);
                        }
                    }
                }
            }
        }
        return true;

    }
    //@Auditable
    public boolean saveJobOrderDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                              SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {

                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(7L) || extraType.equals(13L) || extraType.equals(15L) || extraType.equals(345L)) {
                    extraDTO.setCandidateCode(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode());
                    extraDTO.setCandidateCodeSeq(((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq());
                   
                    if(extraDataDAO.isActiveExtraDataForType(extraDTO.getCandidateCode(), extraDTO.getCandidateCodeSeq(), extraType)){

                        if (extraType.equals(13L)) {
                            String exDValue =
                                extraDataDAO.extraDataValueForType(extraDTO.getCandidateCode(), extraDTO.getCandidateCodeSeq(),
                                                                   extraType);
                          
                            if (extraDTO.getValue().equals("Del")) {
                                extraDataDAO.remove(extraDTO);
                            }else{
                              if (!extraDTO.getValue().equals(exDValue))
                                 extraDataDAO.update(extraDTO);
                            }
                         
                        } else
                            extraDataDAO.update(extraDTO);
                    }else{
                        if (extraDTO.getValue().equals("Del")) {
                        System.out.println("No Add");
                        } else {
                            extraDataDAO.add(extraDTO);
                        }
                    }
                }
            }
        }
        return true;

    }
    //@Auditable
    public boolean fromOrderToChoiceDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                                SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((14L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(7L)) {
                Long candCode = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode();
                Long candSeq = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq();
                    
                    if (extraDataDAO.isActiveExtraDataForType(candCode, candSeq, 7L)) {
                        extraDataDAO.update(extraDTO);
                    } else {
                    extraDTO.setCandidateCode(candCode);
                    extraDTO.setCandidateCodeSeq(candSeq);
                    extraDataDAO.add(extraDTO);
                    return true;
                }
            }
            }
        }
        return true;

    }

    ///////////////////////////////////Choice Department////////////////////////////////////
    //1-جاري مراجعة الطلب بالديوان hireStage=9

    //redirect to order and insert notes or update it if found
    //@Auditable
    public boolean fromChoiceToOrder(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((11L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
            Long candCode = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode();
                if(extraType.equals(336L)){
                        if (extraDataDAO.isActiveExtraDataForType(candCode, 1L, 336L)) {
                            extraDataDAO.update(extraDTO);
                        } else {
                       
                        extraDTO.setCandidateCode(candCode);
                        extraDTO.setCandidateCodeSeq(1L);
                        extraDataDAO.add(extraDTO);
                    }
                }
              else if (extraType.equals(6L)) {
                    Long candSeq = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq();
                    if (extraDataDAO.isActiveExtraDataForType(candCode, candSeq, 6L)) {
                        extraDataDAO.update(extraDTO);
                    } else {
                        extraDTO.setCandidateCode(candCode);
                        extraDTO.setCandidateCodeSeq(candSeq);
                        extraDataDAO.add(extraDTO);
                    }
                    return true;
                }

            }
        }
        return true;
    }

    //@Auditable
    public boolean fromChoiceToFatwa(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((10L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(6L)) {
                    Long candCode = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode();
                    Long candSeq = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq();
                    if (extraDataDAO.isActiveExtraDataForType(candCode, candSeq, 6L)) {
                        extraDataDAO.update(extraDTO);
                    } else {
                        extraDTO.setCandidateCode(candCode);
                        extraDTO.setCandidateCodeSeq(candSeq);
                        extraDataDAO.add(extraDTO);
                    }
                    return true;
                }

            }
        }
        return true;
    }


    //رد الطلب من ادارة الاختيار

    //redirect to order and insert notes or update it if found
    //@Auditable
    public boolean returnFromChoiceToOrg(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                                SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        Long hireStage = ((HireStagesEntityKey)DTO.getHireStagesDTO().getCode()).getHirstageCode();
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((12L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);

        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(6L)) {
                    Long candCode = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode();
                    Long candSeq = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq();
                    if (hireStage == 16L) {
                        HireStagesDAO StagesDAO = (HireStagesDAO)super.newDAOInstance(HireStagesEntity.class);
                        IHireStagesEntityKey datatypekey = new HireStagesEntityKey((16L));
                        IHireStagesDTO dataTypeDTO = (IHireStagesDTO)StagesDAO.getById(datatypekey);
                        //extraDTO.setValueDesc("تم رد الطلب من ادارة توصي�? الوظائ�?");
                        extraDTO.setValueDesc(dataTypeDTO.getName());
                    }
                    if (hireStage == 14L) {
                        HireStagesDAO StagesDAO = (HireStagesDAO)super.newDAOInstance(HireStagesEntity.class);
                        IHireStagesEntityKey datatypekey = new HireStagesEntityKey((14L));
                        IHireStagesDTO dataTypeDTO = (IHireStagesDTO)StagesDAO.getById(datatypekey);
                        //extraDTO.setValueDesc("تم رد الطلب من ادارة توصي�? الوظائ�?");
                        extraDTO.setValueDesc(dataTypeDTO.getName());
                    }
                    extraDTO.setCandidateCode(candCode);
                    extraDTO.setCandidateCodeSeq(candSeq);
                    if (extraDataDAO.isActiveExtraDataForType(candCode, candSeq, 6L)) {
                        extraDataDAO.update(extraDTO);
                    } else {
                        extraDataDAO.add(extraDTO);
                    }
                }

            }
        }
        return true;
    }
    //@Auditable
    public boolean approveDataChoiceDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                                SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);

        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        Long hireStage = ((HireStagesEntityKey)DTO.getHireStagesDTO().getCode()).getHirstageCode();
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        IHireStagesEntityKey key = new HireStagesEntityKey((15L));
        DTO.getHireStagesDTO().setCode(key);
        DAO().update(DTO);
        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long extraType =
                    ((EmpExtraDataTypesEntityKey)extraDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                if (extraType.equals(6L) && !extraDTO.getValue().equals("")) {
                    Long candCode = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode();
                    Long candSeq = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq();
                    extraDTO.setCandidateCode(candCode);
                    extraDTO.setCandidateCodeSeq(candSeq);
                    if (extraDataDAO.isActiveExtraDataForType(candCode, candSeq, 6L)) {
                        extraDataDAO.update(extraDTO);
                    } else {
                        extraDataDAO.add(extraDTO);
                    }
                }
                if (hireStage == 10) {
                    if (extraType.equals(6L) && !extraDTO.getValue().equals("")) {
                        extraDataDAO.add(extraDTO);
                    }
                }

            }
        }
        return true;
    }
    // to do delegate to client
    //@Auditable
    public boolean approveDataByCivilService(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);

        IEmpCandidatesDTO DTO = (IEmpCandidatesDTO)empCandidatesDTO;
        List<IBasicDTO> extraDataDTO = DTO.getEmpCandidateExtraDataList();
        EmpCandidateExtraDataDAO extraDataDAO =
            (EmpCandidateExtraDataDAO)super.newDAOInstance(EmpCandidateExtraDataEntity.class);
        DTO.setJobsDTO(null);
        DAO().update(DTO);
        if (extraDataDTO != null) {
            for (IBasicDTO newDTO : extraDataDTO) {
                IEmpCandidateExtraDataDTO extraDTO = (IEmpCandidateExtraDataDTO)newDTO;
                Long candCode = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCode();
                Long candSeq = ((IEmpCandidatesEntityKey)DTO.getCode()).getCandidateCodeSeq();
                extraDTO.setCandidateCode(candCode);
                extraDTO.setCandidateCodeSeq(candSeq);
                Long statusFlag = extraDTO.getStatusFlag();
                if (statusFlag != null && statusFlag.equals(ISystemConstant.ADDED_LAST_ITEM)) {
                    extraDataDAO.update(extraDTO);
                } else if (statusFlag != null && statusFlag.equals(ISystemConstant.ADDED_ITEM)) {
                    extraDataDAO.add(extraDTO);
                }

            }
        }
        return true;
    }

    public List<IEmpCandidatesDTO> checkIfCitizenIsCandidate(IRequestInfoDTO ri,
                                                             Long civilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return DAO().checkIfCitizenIsCandidate(civilId);
        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *  Hany Omar saveCandidateInfo
     *  return empCandidatesDTO
     *  param empCandidatesDTO
     *  28/12/2014
     */
     //@Auditable
    public IBasicDTO addCandidateInfo(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO1) throws DataBaseException,
                                                                                              SharedApplicationException {
        System.out.println("EmployeeCandidate.addCandidateInfo:::Start--->");
        initRiForBsnLog("em/HRM", ri);
        RequiredDocumentsDAO requiredDocumentsDAO =
            (RequiredDocumentsDAO)super.newDAOInstance(RequiredDocumentsEntity.class);
        try {
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)empCandidatesDTO1;
            IKwtCitizensResidentsDTO dtoInf=null;
            try{
            
             dtoInf = (IKwtCitizensResidentsDTO)InfClientFactory.getKwtCitizensResidentsClient().getCitizenCodeName(Long.valueOf(empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey()));
            } catch(Exception e) {
                
                }
            if (empCandidatesDTO.isWitoutQualFlag())
                ((IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).setNoQual(1L);
            else
                ((IKwtCitizensResidentsDTO) empCandidatesDTO.getCitizensResidentsDTO()).setNoQual(null);
            
            if (dtoInf == null) {
                System.out.println("Start / KwtCitizensResidentsClient().add");
                if(( (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).getActiveFlag() == null ){
                        ( (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).setActiveFlag(ISystemConstant.ACTIVE_FLAG);
                    }
                InfClientFactory.getKwtCitizensResidentsClient().add(empCandidatesDTO.getCitizensResidentsDTO());
                System.out.println("End / KwtCitizensResidentsClient().add");
            } else {
                System.out.println("Start / KwtCitizensResidentsClient().update");
                if(( (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).getActiveFlag() == null ){
                        ( (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).setActiveFlag(ISystemConstant.ACTIVE_FLAG);
                    }
                InfClientFactory.getKwtCitizensResidentsClient().update(empCandidatesDTO.getCitizensResidentsDTO());
                System.out.println("End / KwtCitizensResidentsClient().update");
            }
            
            empCandidatesDTO.setTransferToEmpFlag(0L);
            System.out.println("Start / EmpCandidatesDTO.addNewSeqForCandidate");
            IEmpCandidatesDTO savedEmpCandidatesDTO = DAO().addNewSeqForCandidate(empCandidatesDTO);
            System.out.println("End / EmpCandidatesDTO.addNewSeqForCandidate");

            savedEmpCandidatesDTO.setEmpCandidateDocumentsList(empCandidatesDTO.getEmpCandidateDocumentsList());
            savedEmpCandidatesDTO.setEmpCandidateProceduresList(empCandidatesDTO.getEmpCandidateProceduresList());
            savedEmpCandidatesDTO.setEmpCndSalaryElementsList(empCandidatesDTO.getEmpCndSalaryElementsList());

            List<IBasicDTO> empCndSalElmList = savedEmpCandidatesDTO.getEmpCndSalaryElementsList();
            if (empCndSalElmList != null) {

                for (IBasicDTO dto : empCndSalElmList) {
                    ((IEmpCndSalaryElementsDTO)dto).setEmpCandidatesDTO(savedEmpCandidatesDTO);
                    System.out.println("Start / empCndSalElmDAO.add");
                    empCndSalElmDAO.add(dto);
                    System.out.println("End / empCndSalElmDAO.add");
                }
            }
            //used to update stage to complete document
            boolean documentStatus = true;

            //used to update stage to complete procedure
            boolean procedureStatus = true;

            if (empCandidatesDTO.getPersonsInformationDTOList() != null) {
                        List<IPersonsInformationDTO> personsInformationDTOList = empCandidatesDTO.getPersonsInformationDTOList();
                        if (personsInformationDTOList != null) {
                            IPersonsInformationCMTClient personsInformationClient =
                                InfClientFactory.getPersonsInformationCMTClient();
                            IPersonQualificationsClient personQualificationsClient =InfClientFactory.getPersonQualificationsClient();
                            for (IPersonsInformationDTO dto : personsInformationDTOList) {
                                System.out.println("Start personsInformationClient.addCMT");
                                if (dto.getStatusFlag() != null && dto.getStatusFlag().equals(ISystemConstant.ADDED_ITEM)) {
                                personsInformationClient.addCMT(dto);
                                }else{
                                    if(dto.getPersonQualificationsDTO() != null){
                                     personQualificationsClient.updateCurrentQual(dto.getPersonQualificationsDTO());
                                    }
                                    }
                                System.out.println("End personsInformationClient.addCMT");
                            }
                        }
                    }
                        if (empCandidatesDTO.getDeletedPersonsInformationDTOList() != null) {
                        
                            IPersonsInformationClient client = ClientFactoryUtil.getInstance(IPersonsInformationClient.class);
                            IPersonQualificationsClient qualificationClient  = ClientFactoryUtil.getInstance(IPersonQualificationsClient.class);
                            for (IPersonsInformationDTO dto : empCandidatesDTO.getDeletedPersonsInformationDTOList()) {
                            if (dto.getPersonQualificationsDTO() != null)
                                qualificationClient.removePersonQual(dto.getPersonQualificationsDTO());
                            client.remove(dto);
                            }
                        }

            List<IKwtWorkDataTreeDTO> kwtWorkDataTreeDTOList = empCandidatesDTO.getKwtWorkDataTreeDTOList();
            if (kwtWorkDataTreeDTOList != null) {
                IKwtWorkDataClient kwtWorkDataClient = InfClientFactory.getKwtWorkDataClient();
                for (IKwtWorkDataTreeDTO dto : kwtWorkDataTreeDTOList) {
                    System.out.println("Start / kwtWorkDataClient.addKwtWorkDataTreeDTO");
                    kwtWorkDataClient.addKwtWorkDataTreeDTO(dto);
                    System.out.println("End / kwtWorkDataClient.addKwtWorkDataTreeDTO");
                }
            }
            //            IEmpCandidatesDTO oldEmp = savedEmpCandidatesDTO;

            Long hireTypeCode =
                ((IHireTypesEntityKey)savedEmpCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode();

            System.out.println("Employee.updateEmployeeDocumentsCMT::Start");
            documentStatus = updateEmployeeDocumentsCMT(null, savedEmpCandidatesDTO);
            System.out.println("Employee.updateEmployeeDocumentsCMT::End");

            System.out.println("Employee.updateEmployeeProcedureCMT::Start");
            procedureStatus = updateEmployeeProcedureCMT(savedEmpCandidatesDTO);
            System.out.println("Employee.updateEmployeeProcedureCMT::End");

            //check for added procedure or add required document
            List<IBasicDTO> availableDocumentList = null;
            List<IBasicDTO> availableProcedureList = null;

            try {
                //get list of document and procedure
                //using try and catch here to void item not found from listAvailableEntities method
                availableDocumentList =
                        empCndDocumentsDAO.listAvailableEntities(savedEmpCandidatesDTO.getCode(), savedEmpCandidatesDTO.getHireTypesDTO().getCode());
            } catch (NoResultException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                availableProcedureList =
                        empCndProceduresDAO.listAvailableEntities(savedEmpCandidatesDTO.getCode(), savedEmpCandidatesDTO.getHireTypesDTO().getCode());
            } catch (NoResultException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (availableDocumentList != null && availableDocumentList.size() > 0) {
                for (IBasicDTO reqDocument : availableDocumentList) {
                    if (requiredDocumentsDAO.checkRequired(hireTypeCode,
                                                           (((IInfDocumentTypesEntityKey)(((IRequiredDocumentsDTO)reqDocument).getDocumentTypeDTO()).getCode()).getDoctypeCode())).equals(Boolean.TRUE)) {
                        documentStatus = false;
                        break;
                    }
                }
            }

            if (availableProcedureList != null && availableProcedureList.size() > 0) {
                procedureStatus = false;
            }

            //update employee stage
            Long hireStageCode =
                ((IHireStagesEntityKey)empCandidatesDTO.getHireStagesDTO().getCode()).getHirstageCode();
            System.out.println("Stageeeeeeeeeeeee>>>>>>>>>>" + hireStageCode);
            IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
            Long code = null;
            // check about complete Data to Set HirStage
            if (this.checkcompleteData(empCandidatesDTO)) {
                code = IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION;
                if (empCandidatesDTO.isMinExcepted()) {
                    IEmpCndSalaryElementsDTO empCndSalaryElementsDTO =
                        (IEmpCndSalaryElementsDTO)empCndSalElmList.get(0);
                    if (empCndSalaryElementsDTO.getElementValue() != null &&
                        empCndSalaryElementsDTO.getElmguideCodeEquv() != null) {
                        code = IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION;
                }else{
                        code = IEMPConstant.EMP_STAGE_DEFAULT;
                }
                }
                stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(code));
            } else {
                if (hireStageCode.equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION)) {
                    code = IEMPConstant.EMP_STAGE_DEFAULT;
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(code));
                } else {
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(hireStageCode));
                }
            }
            savedEmpCandidatesDTO.setDocumentsStatus(documentStatus);
            savedEmpCandidatesDTO.setProceduresStatus(procedureStatus);
            savedEmpCandidatesDTO.setHireStagesDTO(stage);
            System.out.println("Start / EmployeeCandidate().update");
            DAO().update(savedEmpCandidatesDTO);
            System.out.println("End / EmployeeCandidate().update");
            System.out.println("EmployeeCandidate.addCandidateInfo:::End--->");
            return savedEmpCandidatesDTO;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    /**
     * return 1 if there are fileNumberRedundant in emp, 2 if there are fileNumberRedundant in candidates , else 0
     * @param minCode
     * @param minFileNo
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @auther HanyOmar,KareemSayed
     */
    public int isFilNumRedundant(IRequestInfoDTO ri, Long minCode, String minFileNo,
                                 Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Long countForEmp = employeesDAO.countFilesWithMinInEmployee(minCode, minFileNo, civilId);
            Long countForCandidate = DAO().countFileswithMinInCandidates(minCode, minFileNo, civilId);
            if (countForEmp > 0) {
                return 1;
            } else if (countForCandidate > 0) {
                return 2;
            } else {
                return 0;
            }
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }


    public List<IBasicDTO> filterByAllHireTypeForHireExecute(IRequestInfoDTO ri,
                                                             Long loggedMin) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            return DAO().filterByAllHireTypeForHireExecute(loggedMin);
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    public List<IBasicDTO> searchForHireExecute(IRequestInfoDTO ri, Long loggedMin, Long civilId, String name,
                                                Long hireTypeKey) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return DAO().searchForHireExecute(loggedMin, civilId, name, hireTypeKey);
        } catch (NoResultException e) {
            throw new NoResultException();
        }

    }

    //////////////////////////////////////////////تن�?يذ واصدار قرار////////////////////////////////


    //////////////////////////////////////////////تن�?يذ واصدار قرار////////////////////////////////
    //اذا كان احد المرشحين المختارين حالته "جاري تن�?يذ التعيين" تظهر شاشة وبها المرشحين

    //يتم �?حص اذا كان المرشح له ترشيح آخر �?ي شاشة اضا�?ة ومتابعة المرشحين يظهر رسالة "المرشح لديه ترشيح اخر يجب الغاؤه اولا" ويتم ايقا�? العملية

    public List<IBasicDTO> getOtherCandByCivils(IRequestInfoDTO ri,
                                                List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                          SharedApplicationException {
        return DAO().getOtherCandByCivils(empCandidatesList);
    }

    public List<IBasicDTO> checkDecisionHire(IRequestInfoDTO ri,
                                             List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                       SharedApplicationException {
        List<IBasicDTO> resList = new ArrayList();
        for (IBasicDTO DTO : empCandidatesList) {

            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)DTO;
            if (((IHireStagesEntityKey)empCandidatesDTO.getHireStagesDTO().getCode()).getHirstageCode() == 5L) {
                resList.add(empCandidatesDTO);
            }
        }
        return resList;
    }
    //تن�?يذ ربط القرار
    //@Auditable
    public boolean applyDecisionForHire(IRequestInfoDTO ri,
                                        List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                  SharedApplicationException {
        for (IBasicDTO DTO : empCandidatesList) {
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)DTO;
            IHireStagesEntityKey key = new HireStagesEntityKey((5L));
            empCandidatesDTO.getHireStagesDTO().setCode(key);
            DAO().update(empCandidatesDTO);
            DAO().removeSeekerFromCrswhenHire(empCandidatesList);
        }
        return true;
    }
    //اذا كان احد المرشحين المختارين حالته "جاري اصدار قرار التعيين" ونوع التعيين للمرشح الزامي ان يتم ربط المرشح بقرار

    public List<IBasicDTO> checkApplyHire(IRequestInfoDTO ri,
                                          List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                    SharedApplicationException {
        List<IBasicDTO> resList = new ArrayList();
        for (IBasicDTO DTO : empCandidatesList) {
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)DTO;
            if (((IHireStagesEntityKey)empCandidatesDTO.getHireStagesDTO().getCode()).getHirstageCode() == 2L &&
                ((IHireTypesDTO)empCandidatesDTO.getHireTypesDTO()).getDecisionMust() == 1L) {
                resList.add(empCandidatesDTO);
            }
        }
        return resList;
    }

    //تن�?يذ التعيين
    //@Auditable
    public boolean applyHire(IRequestInfoDTO ri, List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                           SharedApplicationException {
        for (IBasicDTO DTO : empCandidatesList) {
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)DTO;
            IHireStagesEntityKey key = new HireStagesEntityKey((7L));
            empCandidatesDTO.getHireStagesDTO().setCode(key);
            DAO().update(empCandidatesDTO);
            DAO().removeSeekerFromCrswhenHire(empCandidatesList);
        }
        return true;
    }

    public List<IBasicDTO> checkConditions(IRequestInfoDTO ri,
                                           List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                     SharedApplicationException {
        List<IBasicDTO> NotSuccessCnd = new ArrayList();
        for (IBasicDTO DTO : empCandidatesList) {
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)DTO;
            Long hiretype = ((IHireTypesEntityKey)empCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode();
            HireTypesDAO hireTypeDAO = (HireTypesDAO)DAOFactoryUtil.getInstance(HireTypesEntity.class);
            List<Long> tabrecSerial = hireTypeDAO.getHireTyperecserialByHireType(hiretype);
            Boolean condPassed = false;
            //       try {
            EmpUtils util = new EmpUtils();
            condPassed =
                    util.testConditionFromGrs(((IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).getCivilId(),
                                              tabrecSerial.get(0));
            if (!condPassed) {
                NotSuccessCnd.add(empCandidatesDTO);
            }
            //   List<IBasicDTO> lstConditions =
            //     GrsClientFactory.getConditionRelationsClient().getAllByRelTabrecSerial(tabrecSerial);
            //                for (IBasicDTO condition : lstConditions) {
            //                    Long status = ((IConditionRelationsDTO)condition).getStatus();
            //                    if (!status.equals(0L)) {
            //                        String strcondition =
            //                            condition.getCode().getKey().substring(0, condition.getCode().getKey().lastIndexOf("*"));
            //                        try {
            //                            if (!DAO().checkGrsConditions(Long.valueOf(strcondition),
            //                                                          ((IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO()).getCivilId())) {
            //                                NotSuccessCnd.add(empCandidatesDTO);
            //                                break;
            //                            }
            //                        } catch (Exception e) {
            //                            e.printStackTrace();
            //                            NotSuccessCnd.add(empCandidatesDTO);
            //                        }
            //                    }
            //                }
            //            } catch (NoResultException e) {
            //                e.printStackTrace();
            //            }
        }
        return NotSuccessCnd;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////


    public List<IBasicDTO> searchForSpecificEmployeeByLoggedMin(IRequestInfoDTO ri,
                                                                IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {


        try {

            IEmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            List<Long> execludedHireStage = new ArrayList<Long>();
            execludedHireStage.add(3L);
            execludedHireStage.add(4L);
            execludedHireStage.add(5L);
            execludedHireStage.add(7L);
            employeeSearchDTO.setExecludedEmpHireStagesList(execludedHireStage);

            //  return DAO().searchForSpecificEmployeeByLoggedMin(employeeSearchDTO );


            ///////////////////////////////////////// new ////////////////////

            List<IEmpCandidatesDTO> employeesList;
            boolean tempDocStatus = false;
            boolean tempProcStatus = false;

            try {

                employeesList = (List)DAO().searchForSpecificEmployeeByLoggedMin(employeeSearchDTO);

                for (IEmpCandidatesDTO dto : employeesList) {
                    Long serial =
                        ((IKwtCitizensResidentsEntityKey)dto.getCitizensResidentsDTO().getCode()).getCivilId();
                    Long candCode=((IEmpCandidatesEntityKey)dto.getCode()).getCandidateCode();
                    tempDocStatus = checkEmployeeRequiredDocumentsStatus(serial,candCode);
                    tempProcStatus = checkEmployeeRequiredProceduresStatus(serial,candCode);
                    System.out.println("civilID=" + serial + "DocStatus=" + tempDocStatus);
                    System.out.println("civilID=" + serial + "ProcStatus=" + tempProcStatus);
                    dto.setDocumentsStatus(tempDocStatus);
                    dto.setProceduresStatus(tempProcStatus);
                }

                return (List)employeesList;

            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            }


            //////////////////////////////////////// new /////////////////////////

        } catch (NoResultException e) {
            throw new NoResultException();
        }
    }

    /**
     * Hany Omar
     * Execute Employement
     * @param ri
     * @param dto
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
     //@Auditable
    public Boolean executeEmployment(IRequestInfoDTO ri, IBasicDTO dto) throws DataBaseException,
                                                                               SharedApplicationException {
        initRiForBsnLog("em/HRM", ri);
        IEmpCandidatesDTO empCandidatesDTO = null;
        if (dto.getCode() != null) {
            empCandidatesDTO = (IEmpCandidatesDTO)dto;
        }
            
        try {
            // to do By M.abdelsabour ////////
            Long realCivilId = Long.valueOf(empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey());
            Long minCode = empCandidatesDTO.getMinCode();

            Long empCount = employeesDAO.checkIfEmployeeExistsCount(realCivilId);
                if (empCount != null && empCount > 0) {
                    throw new EmployeeHiredBeforeException();
                }
        

            // call to check this person already employee with hire status 9
            boolean empWithEndServiceInSameMin =
                employeesDAO.checkExsistWithEndServiceBeforeEmployment(IEMPConstants._EMP_ACTIVE_STATUS, realCivilId,
                                                                       IEMPConstants.Emp_With_Status_END_Service,
                                                                       minCode);

            // call to close active flag
            employeesDAO.updateEmployeeBeforeExecuteEmployment(realCivilId);

            /////////// end by M.abdelsabour ///////////
            /// Acroding to ENG.zain change request point 26 the last step is update EMP Candidate
            //            updateEmpCandidate(empCandidatesDTO);
            //            updateEmpCndSalElements(empCandidatesDTO);
            IEmployeesDTO oldEmp=null;
            Long civil = employeesDAO.getOldCivil(minCode, realCivilId);
            if(civil!=null){
             oldEmp = EmpDTOFactory.createEmployeesDTO();
            IEntityKey empKey = EmpEntityKeyFactory.createEmployeesEntityKey(civil);
            oldEmp.setCode(empKey);
            oldEmp.setRealCivilId(realCivilId);
            oldEmp.setMinCode(minCode);
            }
           // IEmployeesDTO oldEmp=empCandidatesDTO.getEmployeesDTO();
            IEmployeesDTO employeesDTO = addEmployee(empCandidatesDTO);

            //  add empAoeReward
            try {
                AoeClientFactory.getAoeAwardRequestsClient().addEmpAoeRequest(employeesDTO);
            } catch (InvalidCivilIdOnConditionException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }


            empCandidatesDTO.setEmployeesDTO(employeesDTO);

            addEmpExtraData(empCandidatesDTO);
            IEmpCndSalaryElementsDTO empCndSalElementDTO = getEmpCndSalaryElementDTO(empCandidatesDTO);
            
            // start add emp sal data
            addSalEmpSalaryElement(empCandidatesDTO ,empCndSalElementDTO);

//            try{
                Long hireTypeCode = empCandidatesDTO.getHirtypeCode();
                if(hireTypeCode == null )
                    hireTypeCode = empCandidatesDTO.getHireTypeKey() == null ? null : Long.parseLong(empCandidatesDTO.getHireTypeKey() );
                if(hireTypeCode == null )
                    hireTypeCode = (empCandidatesDTO.getHireTypesDTO() == null || empCandidatesDTO.getHireTypesDTO().getCode() == null )? null : ((IHireTypesEntityKey)empCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode();
                
                System.out.println("EmpCandidatesSessionBean executeEmployment hireTypeCode = "+hireTypeCode);
                if(hireTypeCode.equals(4L)){
                    IEmpEmployeeSearchDTO empEmployeeSearchDTO = new EmpEmployeeSearchDTO();
                    Long newCivilId = ((EmployeesEntityKey)employeesDTO.getCode() ).getCivilId();
                    empEmployeeSearchDTO.setCivilId(newCivilId);
                    empEmployeeSearchDTO.setMinistryCode(empCandidatesDTO.getMinCode() );
                    empEmployeeSearchDTO.setRealCivilId(realCivilId);
                    empEmployeeSearchDTO.setCandidateCode( ((IEmpCandidatesEntityKey)empCandidatesDTO.getCode() ).getCandidateCode() );
                    empEmployeeSearchDTO.setHireDateFrom(empCandidatesDTO.getHireDate() );
                    ISalEmpSalaryElementsClient salEmpSalaryElementsClient = SalClientFactory.getSalEmpSalaryElementsClient();
                    getOldSocialAndChildrenRaisesForReEmployeedEmp(ri, empEmployeeSearchDTO);
                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            
            // end add emp sal data
            addExperiences(empCandidatesDTO);
            addNewEmpVacBalance(ri, empCandidatesDTO);
            //add balance for years after hire year till now
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(empCandidatesDTO.getHireDate());
            int hireYear = calendar.get(Calendar.YEAR);
            Calendar now = Calendar.getInstance();
            int currentYear = now.get(Calendar.YEAR);

            boolean sameYear = hireYear == currentYear;
            if (!sameYear) {
                int yearCount = currentYear - hireYear;
                for (int i = 1; i <= yearCount; i++) {
                    VacClientFactory.getVacEmployeeBalancesClient().addAnnualSingleEmployeeBalance(employeesDTO, null,
                                                                                                   currentYear);
                    currentYear--;
                }
            }

            ////////////// start with M.abdelsabour //////
            if (empWithEndServiceInSameMin) {
                reActiveEmpSignIdProcedure(employeesDTO);
            }
            /////////////// end with M.abdelsabour ///////////
            updateEmpCandidate(empCandidatesDTO);
            updateEmpCndSalElements(empCandidatesDTO);

            // to DO by M.abdelsabour CSC-22310
            addSecUser(employeesDTO);
            addTestEvalForNewEmp(employeesDTO);
            
            /************************************start prepare dto for calling web service**************************************/
                IKwtCitizensResidentsDTO infDTO = (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO(); 
                System.out.println("begin calling register web service for civilId = "+infDTO.getCivilId());
                if(IEMPConstants.EMP_NATIONALITY_KWT.equals(infDTO.getNationality())) {
                    try{ 
                        ISalElementGuidesEntityKey salEK =
                                            (ISalElementGuidesEntityKey)(empCndSalElementDTO.getSalElementGuidesDTO()).getCode();
                        Long elmguideCode = salEK.getElmguideCode();
                        Double salary = DAO().getElementGuideValue(elmguideCode); 
                        ParametersDTO parametersDTO = fillParametersDTO(empCandidatesDTO ,infDTO);               
                        parametersDTO.setSalary(salary);//= 1200.0;
                        parametersDTO.setRequestDateTime(SharedUtils.getCurrentTimestamp());
                        RegisterReturnType registerReturnType = register(parametersDTO); 
                        if(registerReturnType != null){
                            parametersDTO = updateDTOWithWebServiceResult(parametersDTO , registerReturnType); 
                            parametersDTO.setSex(infDTO.getGenderTypesDTO().getGentypeName());
                            parametersDTO.setTableName("HR_EMP_CANDIDATES");
                            parametersDTO.setRTableTabrecSerial(empCandidatesDTO.getTabrecSerial());
                            DAO().insertIntoHrEmpInsuranceWebservLog(parametersDTO);
                        }
                        System.out.println("end calling register web service for civilId = "+infDTO.getCivilId());
                    }catch(Exception e){
                        e.printStackTrace();
                    }  
               }
                      
           /************************************ end prepare dto for calling web service**************************************/  
            try{
                if(oldEmp!=null){
                  Long oldCivilId = ((EmployeesEntityKey)oldEmp.getCode() ).getCivilId();
                int checkEmpEosFinReason = employeesDAO.checkEmpEosFinReason(oldCivilId);
                if(checkEmpEosFinReason==40){
              ISalEmpMonSalariesClient salClient = SalClientFactory.getSalEmpMonSalariesClient();
              DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
              String movDate = dateFormat.format(employeesDTO.getStartWorkingDate());  
              salClient.copyEmpDeduction(oldEmp, employeesDTO,movDate );
                      }
            }
              }catch(Exception e){
                  System.out.println("Exception  In Emp App copyEmpDeduction");
                  e.printStackTrace();
              }
        } catch (TransactionException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
        return Boolean.TRUE;
    }
    
    public ParametersDTO fillParametersDTO(IEmpCandidatesDTO empCandidatesDTO ,IKwtCitizensResidentsDTO infDTO ){
                ParametersDTO dto = new ParametersDTO();
                if(empCandidatesDTO.getHireTypesDTO() != null){
                    String hireTypeCode = empCandidatesDTO.getHireTypesDTO().getCode()!= null?
                                                empCandidatesDTO.getHireTypesDTO().getCode().getKey():null;
                        if("4".equals(hireTypeCode)){
                            dto.setCscOperationType("ReEnrollment");
                        }else{
                            dto.setCscOperationType("Registration");
                        }
                    
                }
                String fullName = infDTO.getFirstName()+","+infDTO.getSecondName()+","+infDTO.getThirdName()+","+infDTO.getLastName();
                dto.setCustomerName(fullName); //= "Wala,AbdElgaleel,Abdallah,AlabdElgaleel";
                String civilId  = infDTO.getCivilId().toString();
                dto.setCstomerIdentity(civilId); //= "284081601218";
                dto.setRegion(null); //= "Kuwait";
                dto.setAllotmentNumber(null); //= "7008";
                dto.setAvenueNumber(null); //= "35";
                dto.setStreet(infDTO.getStreetCode() != null ?infDTO.getStreetCode()+"":null); //= "88";                                      
                dto.setFloorNumber(infDTO.getFloorNo()!= null ?infDTO.getFloorNo()+"":null); //= "5";
                dto.setHouseNumber(infDTO.getBuildingNo()!= null ?infDTO.getBuildingNo()+"":null); //= "22";    
                dto.setApartmentNumber(infDTO.getFlatNo()!= null ?infDTO.getFlatNo()+"":null);// = "65";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if(infDTO.getBirthDate() != null){
                    String string  = dateFormat.format(infDTO.getBirthDate() );
                    dto.setDateOfBirth(string); // = "1984-08-16";                                           
                }
                dto.setNationality(infDTO.getNationality()+""); //= "Kuwaiti" 
                if(infDTO.getNationalityDate() !=null){
                    dto.setDateOfNationality(dateFormat.format(infDTO.getNationalityDate()));// = "1992-05-25";                                           
                }
                dto.setNationalityNumber(civilId); //= "284081601218" ;                                           
                dto.setSubject("subject"); //= "subject";
                Integer mobileNo = infDTO.getMobileNo() != null?Integer.valueOf(infDTO.getMobileNo()):null;
                dto.setMobileNumber(mobileNo); //= 94435022;
                if(empCandidatesDTO.getStartWorkingDate() != null){
                    String startWorkingDate = dateFormat.format(empCandidatesDTO.getStartWorkingDate());
                    dto.setEnrollmentDate(startWorkingDate); //= "2012-07-11";                           
                    dto.setDateOfFirstRegistration(startWorkingDate);// = "2015-10-22";
                }
               // Integer jobCode = empCandidatesDTO.getJobCode() != null ? Integer.valueOf(empCandidatesDTO.getJobCode()):null;
                dto.setJob(1013); //= 1013;                             
                Double socialRefund =DAO().getSocialRefund(Long.valueOf(civilId));
                dto.setSocialRefund(socialRefund != null && !socialRefund.equals(0.0)?socialRefund :0.0);// = 150.0;    
                dto.setComplementaryRefund(null); //= 500.0;   
                if(infDTO.getGenderTypesDTO() != null){
                    String genderName = infDTO.getGenderTypesDTO().getGentypeCode().equals(1L)?"FEMALE":"MALE";
                    dto.setSex(genderName); //= "MALE";                                         
                }
                
                dto.setAddressElectronicNumber(null); //= 85001;
                dto.setEmployerRegistrationNumber(DAO().getEmployerRegistrationNumber()); //= 258; 
                return dto;
                
            }
    
            public ParametersDTO updateTerminateDToWithWebServiceResult(ParametersDTO dto , TerminationReturnType registerReturnType){
                dto.setResult(registerReturnType.isResult()+"");
                String candName = dto.getCustomerName().replaceAll(",", " ");
                dto.setCustomerName(candName);
                if(registerReturnType.getHeader() != null){
                    dto.setMessageDescription(registerReturnType.getHeader().getTitleAR());    
                }else{
                    dto.setMessageDescription(registerReturnType.getMessageDescription());
                }
                
                MessageHeader messageHeader = registerReturnType.getMessageHeader();
                if(messageHeader != null){
                    dto.setRequestID(messageHeader.getRequestID());//"oci4561821262");
                    if(messageHeader.getRequestTime() != null){
                     dto.setRequestTime(convertStringToTimeStamp(messageHeader.getRequestTime()));//"20191218122459482"));
                    }
                }
                dto.setLastOperationDate(SharedUtils.getCurrentDate());
                return dto; 
            }
            
            public ParametersDTO updateDTOWithWebServiceResult(ParametersDTO dto , RegisterReturnType registerReturnType){
                dto.setResult(registerReturnType.isResult()+"");
                String candName = dto.getCustomerName().replaceAll(",", " ");
                dto.setCustomerName(candName);
                if(registerReturnType.getHeader() != null){
                    dto.setMessageDescription(registerReturnType.getHeader().getTitleAR());    
                }else{
                    dto.setMessageDescription(registerReturnType.getMessageDescription());
                }
                
                MessageHeader messageHeader = registerReturnType.getMessageHeader();
                if(messageHeader != null){
                    dto.setRequestID(messageHeader.getRequestID());//"oci4561821262");
                    if(messageHeader.getRequestTime() != null){
                     dto.setRequestTime(convertStringToTimeStamp(messageHeader.getRequestTime()));//"20191218122459482"));
                    }
                }
                
                return dto; 
            }
            public static String convertStringToTimeStamp(String dateAsString) {
                 String dateTimeAsString = null;
                
        
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                java.util.Date dayDate = new java.util.Date();
                try {
                    dayDate = sdf.parse(dateAsString);
                    
                } catch (ParseException e) {
                    e.printStackTrace();
                    return null;
                }
                Timestamp ts = new Timestamp(dayDate.getTime());
                dateTimeAsString = ts.toString();
                return dateTimeAsString;
                
            }
            private RegisterReturnType register(ParametersDTO dto) throws ClientProtocolException, IOException  {
                   
                   try {
                        
                        HttpParams my_httpParams = new BasicHttpParams();
                        HttpConnectionParams.setConnectionTimeout(my_httpParams, 60000);
                        HttpConnectionParams.setSoTimeout(my_httpParams, 60000);
                        DefaultHttpClient httpClient = new DefaultHttpClient(my_httpParams);
                        httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler
                                                                     (0, false));
                        String registerURL="";
                        ConfigManager cfg = ConfigManager.getInstance();
                        String production = cfg.getParam("glb:IsProduction", true);
                        if("true".equals(production)){
                            registerURL= IEMPConstants.REGISTER_URL;
                        }else{
                            registerURL= IEMPConstants.REGISTER_URL_TEST;
                        }
                       System.out.println("registerURL========="+registerURL);
                        HttpPost postRequest = new HttpPost(registerURL);
                        postRequest.addHeader("accept", "application/json");
                         String basic =  basic(IEMPConstants.USER_NAME, IEMPConstants.PASSWORD) ;
                        postRequest.addHeader(HttpHeaders.AUTHORIZATION,  basic );
                        HttpEntity entity = new StringEntity(dto.toJson(), "application/json", "utf-8");
                        postRequest.setEntity(entity);
                        HttpResponse response = httpClient.execute(postRequest);
                        System.out.println("json request >>>>>>"+dto.toJson());  
                        if (response.getStatusLine().getStatusCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : "
                                    + response.getStatusLine().getStatusCode());
                        }

                        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()),"utf-8"));

                        String output;
                        StringBuilder json = new StringBuilder("");
                        System.out.println("Output from Server .... \n");
                        while ((output = br.readLine()) != null) {
                            System.out.println(output);
                            json.append(output);
                        }
                         
                        Gson gson = new Gson();
                        RegisterReturnType ret = gson.fromJson(json.toString(), RegisterReturnType.class);
                        System.out.println(ret);
                        httpClient.getConnectionManager().shutdown();
                        return ret;
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }
                     
            }
            
            private static String basic(String userName, String password) {
               try {
                   String usernameAndPassword = userName + ":" + password;
                   byte[] bytes = usernameAndPassword.getBytes("ISO-8859-1");
                    String encoded = new String(Base64.encodeBase64(bytes)); 
                   return "Basic " + encoded;
               } catch (UnsupportedEncodingException var5) {
                   throw new AssertionError();
               }
           }

        public IEmpCndSalaryElementsDTO getEmpCndSalaryElementDTO(IEmpCandidatesDTO dto) throws SalElementInsertionException {
            IEmpCndSalaryElementsDTO empCndSalElementDTO = null;
            try {
                empCndSalElementDTO = empCndSalElmDAO.getByCandCode(dto.getCode());
                return empCndSalElementDTO;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("failed to add new sal element");
                throw new SalElementInsertionException();
            } 
        }
        
        public boolean addSalEmpSalaryElement(IEmpCandidatesDTO dto ,IEmpCndSalaryElementsDTO empCndSalElementDTO) throws SalElementInsertionException {
            ISalEmpSalaryElementsDTO SalElementDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();

            String civilId = null;
            Long elmguideCode = null;
            Long eqDegreeCode = null;
            try {
                SalElementDTO.setEmployeesDTO(dto.getEmployeesDTO());
                ISalElementGuidesEntityKey salEK =
                    (ISalElementGuidesEntityKey)(empCndSalElementDTO.getSalElementGuidesDTO()).getCode();
                elmguideCode = salEK.getElmguideCode();
                eqDegreeCode = empCndSalElementDTO.getElmguideCodeEquv();

                DAO().executeFinanciaPackage(dto, elmguideCode, eqDegreeCode);

                System.out.println("Done");
                return true;
            } catch (DataBaseException e) {
                e.printStackTrace();
                System.out.println("failed to add new sal element");
                throw new SalElementInsertionException();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                System.out.println("failed to add new sal element");
                throw new SalElementInsertionException();
            }
        }

        public  TerminationReturnType terminate(IRequestInfoDTO ri, ParametersDTO dto) throws DataBaseException,
                                                                                                SharedApplicationException{
            TerminationReturnType ret = new TerminationReturnType();
            Timestamp requestDateTime = null;
            try {
                HttpParams my_httpParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(my_httpParams, 60000);
                HttpConnectionParams.setSoTimeout(my_httpParams, 60000);
                DefaultHttpClient httpClient = new DefaultHttpClient(my_httpParams);
                
                httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler
                                                             (0, false));
               System.out.println("object path to web service civilId>>" + dto.getCstomerIdentity()+" absentDays>>>"+dto.getAbsentDaysForEmp()+"    date>>>>> "+dto.getInsuranceTerminationDate()+"    erminationReason>>>>>"+dto.getTerminationReason());
                String terminateURL="";
                ConfigManager cfg = ConfigManager.getInstance();
                String production = cfg.getParam("glb:IsProduction", true);
                if("true".equals(production)){
                    terminateURL= IEMPConstants.TERMINATE_URL;
                }else{
                    terminateURL= IEMPConstants.TERMINATE_URL_TEST;
                }
                System.out.println("terminateURL========="+terminateURL);
                requestDateTime = SharedUtils.getCurrentTimestamp();
                HttpPost postRequest = new HttpPost(terminateURL);
                postRequest.addHeader("accept", "application/json");
                String basic =  basic(IEMPConstants.USER_NAME, IEMPConstants.PASSWORD) ;
                postRequest.addHeader(HttpHeaders.AUTHORIZATION,  basic );
                HttpEntity entity = new StringEntity(dto.toJsonTermination(), "application/json", "utf-8");
                postRequest.setEntity(entity);
                HttpResponse response = httpClient.execute(postRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent()),"utf-8"));

                String output;
                StringBuilder json = new StringBuilder("");
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                    json.append(output);
                }
                 
                Gson gson = new Gson();
                 ret = gson.fromJson(json.toString(), TerminationReturnType.class);
                
                httpClient.getConnectionManager().shutdown();
                
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if (e instanceof DataBaseException) {
                    throw (DataBaseException)e;
                } else {
                    throw (SharedApplicationException)e;
                }
            }
            System.out.println("web service result msgDesc>>>>>>>>"+ret.getMessageDescription());
            dto = updateTerminateDToWithWebServiceResult(dto , ret);
            try{
                dto.setRequestDateTime(requestDateTime);
                DAO().insertIntoHrEmpInsuranceWebservLog(dto);
            }catch(Exception e){
                e.printStackTrace();
            }
            return ret;
        }

    //@Auditable
    public void reActiveEmpSignIdProcedure(IEmployeesDTO employeesDTO) {

        Long newCivilId = Long.valueOf(employeesDTO.getCode().getKey());
        Date startWorkDate = employeesDTO.getStartWorkingDate();
        Long minCode = ((IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode()).getMinCode();
        //commneted by m.sayed at 13/10/2015 according to look up problems in attendence
        IEmpSignIdenClient empSignIdenClient = new EmpSignIdenClientImpl();
        try {
            empSignIdenClient.reActiveEmpSignIdProcedure(minCode, newCivilId, startWorkDate);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //@Auditable
    public void addSecUser(IEmployeesDTO employeesDTO) {
        System.out.println(" start add Sec User");
        Long realCivilId = employeesDTO.getRealCivilId();
        String workCode = ((IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode()).getWrkCode();
        IUsersClient usr = SecClientFactory.getInstance().getUsersClient();
        Boolean res;
        try {
            res = usr.addUser(realCivilId, workCode, ISystemConstant.ACTIVE_FLAG);
            System.out.println(" add User Result in sec " + res);
        } catch (Throwable e) {
            System.out.println("falid add sec User");
            e.printStackTrace();
        }
        System.out.println("end add Sec User");


    }
    //@Auditable
    public void addTestEvalForNewEmp(IEmployeesDTO employeesDTO) {
        System.out.println(" start  addTestEvalForNewEmp");
        Long civilId = Long.valueOf(employeesDTO.getCode().getKey());
        Long minCode = ((IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode()).getMinCode();
        String workCode = ((IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode()).getWrkCode();
        Date hireDate = employeesDTO.getHireDate();
        try {
            employeesDAO.addTestEvalForNewEmp(minCode, civilId, workCode, hireDate);
        } catch (Exception e) {
            System.out.println(" faild  addTestEvalForNewEmp");
            e.printStackTrace();
        }
        System.out.println(" end  addTestEvalForNewEmp");


    }


    /**
     * Hany Omar
     * validate child have a raise for his father :D
     * @param ri
     * @param cndDTO
     * @return
     * @throws DataBaseException
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public boolean validateChildRaise(IRequestInfoDTO ri, IEmpCandidatesDTO cndDTO) throws DataBaseException,
                                                                                           RemoteException,
                                                                                           SharedApplicationException {
        Long count = null;
        Long civilId = null;
        civilId = cndDTO.getCivilId();
        count = SalClientFactory.getSalEmpChildrenClient().checkEmpChildernExistAndValid(civilId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hany Omar
     * update hireStageName to employee
     * @param empCandidatesDTO
     * @return
     */
     //@Auditable
    private boolean updateEmpCandidate(IEmpCandidatesDTO empCandidatesDTO) {

        IHireStagesDTO hireStagesDTO = EmpDTOFactory.createHireStagesDTO();
        IHireStagesEntityKey key = EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_STARTING_WORK);
        hireStagesDTO.setCode(key);

        empCandidatesDTO.setHireStagesDTO(hireStagesDTO);

        IEmpCandidateStatusDTO empCandidateStatusDTO = EmpDTOFactory.createEmpCandidateStatusDTO();
        IEntityKey cndStatusEK =
            EmpEntityKeyFactory.createEmpCandidateStatusEntityKey(IEMPConstants.EMP_STATUS_EMPLOYMENT);
        empCandidateStatusDTO.setCode(cndStatusEK);
        empCandidatesDTO.setEmpCandidateStatusDTO(empCandidateStatusDTO);

        empCandidatesDTO.setTransferToEmpFlag(ISystemConstant.ACTIVE_FLAG);

        try {
            DAO().update(empCandidatesDTO);
            return true;
        } catch (DataBaseException e) {
            e.printStackTrace();
            return false;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            return false;
        }
    }

    //@Auditable
    private boolean updateEmpCndSalElements(IEmpCandidatesDTO empCandidatesDTO) {

        IEmpCndSalaryElementsDTO empCndSalElementDTO = null;
        try {
            empCndSalElementDTO = empCndSalElmDAO.getByCandCode(empCandidatesDTO.getCode());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        empCndSalElementDTO.setFromDate(empCandidatesDTO.getStartWorkingDate());

        try {
            empCndSalElmDAO.updat(empCndSalElementDTO);
            return true;
        } catch (DataBaseException e) {
            e.printStackTrace();
            return false;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            return false;
        }
    }

    //@Auditable
    private IEmployeesDTO addEmployee(IEmpCandidatesDTO empCandidatesDTO) {
        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
        Long civilId = null;
        String rCivilId = null;
        try {
            civilId = employeesDAO.findMaxId();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }

        IEntityKey empKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        employeesDTO.setCode(empKey);

        rCivilId = empCandidatesDTO.getCitizensResidentsDTO().getCode().getKey();
        IEntityKey key = InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(Long.valueOf(rCivilId));
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        kwtCitizensResidentsDTO.setCode(key);

        employeesDTO.setCitizensResidentsDTO(kwtCitizensResidentsDTO);

        Long minCode = empCandidatesDTO.getMinCode();
        String wrkCntrCode = ((IWorkCentersEntityKey)(empCandidatesDTO.getWorkCentersDTO()).getCode()).getWrkCode();

        employeesDTO.setMinCode(minCode);
        IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
        workCentersDTO.setCode(OrgEntityKeyFactory.createWorkCentersEntityKey(minCode, wrkCntrCode));


        employeesDTO.setWorkCenterDTO(workCentersDTO);
        employeesDTO.setMinistryFileNo(empCandidatesDTO.getMinistryFileNo());
        employeesDTO.setHireDate(empCandidatesDTO.getHireDate());
        employeesDTO.setStartWorkingDate(empCandidatesDTO.getStartWorkingDate());

        IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO();
        IHireStatusEntityKey hireStatusEntityKey =
            EmpEntityKeyFactory.createHireStatusEntityKey(IEMPConstants.EMP_STATUS_EMPLOYMENT);
        hireStatusDTO.setCode(hireStatusEntityKey);
        employeesDTO.setHireStatusDTO(hireStatusDTO);

        Long hireTypeCode = ((IHireTypesEntityKey)empCandidatesDTO.getHireTypesDTO().getCode()).getHirtypeCode();
        IHireTypesDTO hireTypeDTO = EmpDTOFactory.createHireTypesDTO();
        hireTypeDTO.setCode(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypeCode));

        employeesDTO.setHireTypeDTO(hireTypeDTO);

        IHireStagesDTO hireStagesDTO = EmpDTOFactory.createHireStagesDTO();
        hireStagesDTO.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_STARTING_WORK));

        employeesDTO.setHireStageDTO(hireStagesDTO);
        employeesDTO.setDateOfNextRaise(empCandidatesDTO.getDateOfNextRaise());

        IJobsDTO jobsDTO = JobDTOFactory.createJobsDTO();
        //        String jobCode = ((IJobsEntityKey)(empCandidatesDTO.getJobsDTO()).getCode()).getJobCode();
        jobsDTO.setCode((empCandidatesDTO.getJobsDTO()).getCode());
        employeesDTO.setJobDTO(jobsDTO);

        //TO DO TechJobsDTO
        Long bgtTypeCode = ((IBgtTypesEntityKey)empCandidatesDTO.getBgtTypesDTO().getCode()).getTypeCode();
        IBgtTypesDTO bgtTypesDTO = BgtDTOFactory.createBgtTypesDTO();
        bgtTypesDTO.setCode(BgtEntityKeyFactory.createBgtTypesEntityKey(bgtTypeCode));
        employeesDTO.setBgtTypesDTO(bgtTypesDTO);


        IBgtProgramsDTO bgtPrgDTO = BgtDTOFactory.createBgtProgramsDTO();
        String bgtPrgCode = ((IBgtProgramsEntityKey)empCandidatesDTO.getBgtProgramsDTO().getCode()).getPrgCode();
        bgtPrgDTO.setCode(BgtEntityKeyFactory.createBgtProgramsEntityKey(bgtPrgCode));
        employeesDTO.setBgtProgramsDTO(bgtPrgDTO);

        employeesDTO.setRealCivilId(Long.valueOf(rCivilId));
        employeesDTO.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
        employeesDTO.setRecordDescCode(ISystemConstant.ACTIVE_FLAG);

        employeesDTO.setEmpCandidatesDTO(empCandidatesDTO);

        try {
            System.out.println("addEmployee civilId = "+civilId+" , rCivilId = "+rCivilId);
            //IEmployeesClient employeesClient = EmpClientFactory.getEmployeesClient();
            employeesDAO.add(employeesDTO);
            System.out.println("addEmployee Done civilId = "+civilId);
            return employeesDTO;
        } catch (DataBaseException e) {
            e.printStackTrace();
            System.out.println("failed to add new emp");
            return null;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            System.out.println("failed to add new emp");
            return null;
        }

    }

    //@Auditable
    private boolean addEmpExtraData(IEmpCandidatesDTO empCandidatesDTO) throws ExtraDataInsertionException {
        IEmployeeExtraDataDTO employeeExtraDataDTO = EmpDTOFactory.createEmployeeExtraDataDTO();
        Long serial = null;
        try {
            serial = employeeExtraDataDAO.findMaxId();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }
        employeeExtraDataDTO.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(serial));

        IEmpExtraDataTypesDTO empExtraDataTypesDTO = EmpDTOFactory.createEmpExtraDataTypesDTO();
        IEntityKey key = EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(IEMPConstants.TRANSFERE_TYPE_EXTRA_DATA);
        empExtraDataTypesDTO.setCode(key);

        employeeExtraDataDTO.setEmpExtraDataTypesDTO(empExtraDataTypesDTO);

        employeeExtraDataDTO.setFromDate(empCandidatesDTO.getHireDate());

        employeeExtraDataDTO.setValue(IEMPConstants.EXTRA_DATA_VALUE_ONE);

        employeeExtraDataDTO.setEmployeesDTO(empCandidatesDTO.getEmployeesDTO());

        try {
            employeeExtraDataDAO.add(employeeExtraDataDTO);
        } catch (DataBaseException e) {
            e.printStackTrace();
            System.out.println("failed to add new extra data");
            throw new ExtraDataInsertionException();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            System.out.println("failed to add new extra data");
            throw new ExtraDataInsertionException();
        }
        return true;
    }
    //@Auditable
    public boolean addSalEmpSalaryElement(IEmpCandidatesDTO dto) throws SalElementInsertionException {
        ISalEmpSalaryElementsDTO SalElementDTO = SalDTOFactory.createSalEmpSalaryElementsDTO();

        String civilId = null;
        Long elmguideCode = null;
        Long eqDegreeCode = null;
        IEmpCndSalaryElementsDTO empCndSalElementDTO = null;
        try {
            civilId = dto.getCitizensResidentsDTO().getCode().getKey();
            empCndSalElementDTO = empCndSalElmDAO.getByCandCode(dto.getCode());

            SalElementDTO.setEmployeesDTO(dto.getEmployeesDTO());
            ISalElementGuidesEntityKey salEK =
                (ISalElementGuidesEntityKey)(empCndSalElementDTO.getSalElementGuidesDTO()).getCode();
            elmguideCode = salEK.getElmguideCode();
            eqDegreeCode = empCndSalElementDTO.getElmguideCodeEquv();

            DAO().executeFinanciaPackage(dto, elmguideCode, eqDegreeCode);

            System.out.println("Done");
            return true;
        } catch (DataBaseException e) {
            e.printStackTrace();
            System.out.println("failed to add new sal element");
            throw new SalElementInsertionException();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            System.out.println("failed to add new sal element");
            throw new SalElementInsertionException();
        }
    }
    //@Auditable
    public void addNewEmpVacBalance(IRequestInfoDTO ri, IEmpCandidatesDTO dto) throws VacInsertionException {
        boolean isExecuted = true;
        try {
            isExecuted = DAO().addNewEmpVacBalance(dto);
            if (isExecuted) {
                System.out.println("pack_vac executed successfully");
            } else {
                System.out.println("pack_vac didn't executed yet");
                throw new VacInsertionException();
            }
        } catch (DataBaseException e) {
            e.printStackTrace();
            System.out.println("pack_vac failed");
            throw new VacInsertionException();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            System.out.println("pack_vac failed");
            throw new VacInsertionException();
        }
    }
    //@Auditable
    private IKwtWorkDataDTO addExperiences(IEmpCandidatesDTO iEmpCandidatesDTO) throws ExperienceInsertionException {

        IKwtWorkDataClient kwtWorkDataClient = InfClientFactory.getKwtWorkDataClient();
        IKwtWorkDataDTO kwtWorkDataDTO = InfDTOFactory.createKwtWorkDataDTO();

        Long serial = null;
        try {
            serial = kwtWorkDataClient.findNewId();
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        }

        kwtWorkDataDTO.setCode(InfEntityKeyFactory.createKwtWorkDataEntityKey(serial));

        IMinistriesDTO ministriesDTO = OrgDTOFactory.createMinistriesDTO();
        Long minCode = iEmpCandidatesDTO.getMinCode();
        IEntityKey minKey = OrgEntityKeyFactory.createMinistriesEntityKey(minCode);
        ministriesDTO.setCode(minKey);

        kwtWorkDataDTO.setMinistriesDTO(ministriesDTO);
        kwtWorkDataDTO.setFirstParent(minCode);
        kwtWorkDataDTO.setTreeLevel(ISystemConstant.ACTIVE_FLAG);
        kwtWorkDataDTO.setLeafFlag(ISystemConstant.ACTIVE_FLAG);

        IKwtCitizensResidentsDTO citizenResidentDTO = InfDTOFactory.createKwtCitizensResidentsDTO();
        IEntityKey key = iEmpCandidatesDTO.getCitizensResidentsDTO().getCode();
        Long civilId = ((IKwtCitizensResidentsEntityKey)key).getCivilId();
        IEntityKey citizenResidentKey = InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(civilId);
        citizenResidentDTO.setCode(citizenResidentKey);
        kwtWorkDataDTO.setKwtCitizensResidentsDTO(citizenResidentDTO);

        ITrxTypesDTO trxTypesDTO = EmpDTOFactory.createTrxTypesDTO();
        IEntityKey trxTypesEK = EmpEntityKeyFactory.createTrxTypesEntityKey(IEMPConstants.TRXTYPE_30);
        trxTypesDTO.setCode(trxTypesEK);
        kwtWorkDataDTO.setTrxTypesDTO(trxTypesDTO);

        IJobsDTO jobsDTO = JobDTOFactory.createJobsDTO();
        jobsDTO.setCode((iEmpCandidatesDTO.getJobsDTO()).getCode());
        kwtWorkDataDTO.setJobsDTO(jobsDTO);


        IEntityKey workCenterKey = iEmpCandidatesDTO.getWorkCentersDTO().getCode();
        String wrkCntrCode = ((IWorkCentersEntityKey)workCenterKey).getWrkCode();
        IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
        workCentersDTO.setCode(OrgEntityKeyFactory.createWorkCentersEntityKey(minCode, wrkCntrCode));
        kwtWorkDataDTO.setWorkCentersDTO(workCentersDTO);

        kwtWorkDataDTO.setFromDate(iEmpCandidatesDTO.getHireDate());
        //default values till revisit PO
        kwtWorkDataDTO.setActualExpYears(0L);
        kwtWorkDataDTO.setActualExpMonths(0L);
        kwtWorkDataDTO.setActualExpDays(0L);

        kwtWorkDataDTO.setPerFlag(1L);
        kwtWorkDataDTO.setPisFlag(1L);

        try {
            kwtWorkDataClient.add(kwtWorkDataDTO);
        } catch (DataBaseException e) {
            System.out.println("addExperiences() method inside --- > EmpCandidatesSessionBean");
            throw new ExperienceInsertionException();
        } catch (SharedApplicationException e) {
            System.out.println("addExperiences() method inside --- > EmpCandidatesSessionBean");
            throw new ExperienceInsertionException();
        }
        return kwtWorkDataDTO;
    }


    public boolean checkEmployeeInNeed(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().checkEmployeeInNeed(civilId);
    }


    public List getAllRequests(IRequestInfoDTO ri, IEmployeeSearchDTO searchDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().getAllRequests(searchDTO);

    }

    public Long countCitizensRegisteredOnQual(IRequestInfoDTO ri, Long qualificationKey) throws DataBaseException,
                                                                                                SharedApplicationException {
        return DAO().countCitizensRegisteredOnQual(qualificationKey);
    }
    //@Auditable
    public Boolean executeFinanciaPackage(IRequestInfoDTO ri, IEmpCandidatesDTO dto, Long elementGuide,
                                          Long eqDegreeCode) throws DataBaseException, SharedApplicationException {
        return DAO().executeFinanciaPackage(dto, elementGuide, eqDegreeCode);
    }

    public Long getCandidateCode(IRequestInfoDTO ri, Long civilId) throws DataBaseException,
                                                                          SharedApplicationException {
        return DAO().getCandidateCode(civilId);
    }


    public String queryCrimnalStatus(IRequestInfoDTO ri, Long realCivil) throws DataBaseException,
                                                                                SharedApplicationException {

        CriminalEvidenceBindingQSPortClient client = new CriminalEvidenceBindingQSPortClient();
        String value = client.crmWebService(realCivil);
        System.out.println("A.Khaled>>>>>>>>>>>web service return value =" + value);
        return value;
    }
    
    
    public Long getHirDateCount(IRequestInfoDTO ri,Long candidateCode) throws DataBaseException, SharedApplicationException{
        
            return DAO().getHirDateCount(candidateCode);
        }
    
    public IPagingResponseDTO getAllCandidateDataWithPaging(IRequestInfoDTO requestInfoDTO,
                                                         IBasicDTO _searchDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        List<IBasicDTO> result = null;
        IPagingResponseDTO responseDTO = new PagingResponseDTO();
        IPagingRequestDTO requestDTO = null;
        if (_searchDTO != null) {
            IEmpReqEnquiryDTO searchDTO = (IEmpReqEnquiryDTO)_searchDTO;
            requestDTO = (IPagingRequestDTO)searchDTO.getPagingRequestDTO();
            if (requestDTO != null) {
                Long pageNum = requestDTO.getPageNum();
                // get current page with pageNum:index
                Long maxNoOfRecords = requestDTO.getMaxNoOfRecords();
                if (pageNum != null) {
                    if (maxNoOfRecords != null) {
                        Long firstRow = (pageNum * maxNoOfRecords) - maxNoOfRecords;
                        requestDTO.setFirstRowNumber(firstRow);
                        if (firstRow % maxNoOfRecords != 0) {
                            requestDTO.setFirstRowNumber(firstRow + 1);
                        }

                    } else {
                        throw new MaxNoOfRecordsRequiredException();
                    }
                } else {
                    throw new PageNumRequiredException();
                }
                if (requestDTO.isCountRequired()) {
                    Long count = DAO().getCandidateCountWithPaging(searchDTO);
                    responseDTO.setCount(count);
                }
                try {
                    result = DAO().getAllCandidateWithPaging(searchDTO);
                } catch (ItemNotFoundException e) {
                    throw new NoResultException();
                }
                responseDTO.setBasicDTOList(result);
                responseDTO.setRequestDTO(requestDTO);
            }
        }
        return responseDTO;
    }
    
    public String getCandidateName(IRequestInfoDTO ri, IBasicDTO searchDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().getCandidateName(searchDTO);
    }

    public String getPreviosNotes(IRequestInfoDTO ri, IBasicDTO empDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        return DAO().getPreviosNotes(empDTO);
    }

    /** Start DEV-55  by A.Nour 31-03-2020 */
    //@Auditable
    public Boolean getOldSocialAndChildrenRaisesForReEmployeedEmp(IRequestInfoDTO iRequestInfoDTO,
                                             IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            Long martialStatus = ISalConstants.ELEMENT_GUIDE_TYPE_SOCIAL_RAISE;
            IEmpEmployeeSearchDTO searchDTO = (IEmpEmployeeSearchDTO)basicDTO;
            Long civilId = searchDTO.getCivilId();
            Long minCode = searchDTO.getMinistryCode();
    //            boolean isOverallRewardFlag = false;
            Long realCivilId = searchDTO.getRealCivilId();

            EmployeesDTO employeesDTO = DAO().getLastEndedServiceEmpCivilId(realCivilId);
            Long oldCivilId = ((EmployeesEntityKey)employeesDTO.getCode() ).getCivilId();
            Date endJobDate = employeesDTO.getEndJobDate();
            ISalEmpMovDTO salEmpMovDTO = new SalEmpMovDTO();
            salEmpMovDTO.setCurrentEmpCivil(civilId);
            salEmpMovDTO.setOldEmpCivil(oldCivilId);
            salEmpMovDTO.setMinCode(minCode);
            salEmpMovDTO.setMovDate(searchDTO.getHireDateFrom() );
            salEmpMovDTO.setRealCivil(realCivilId);
            salEmpMovDTO.setMovingSerial(searchDTO.getCandidateCode() );
            salEmpMovDTO.setName("reEmp EMP Candidate Code : ");
            salEmpMovDTO.setEndServiceDate( endJobDate);

            System.out.println("SalEmpSalaryElementsSessionBean getOldSocialAndChildrenRaisesForReEmployeedEmp civilId = "
                               +civilId +" , realCivilId = "+realCivilId+" , minCode =  = "+minCode+" , oldCivilId = "+oldCivilId +" , endJobDate = "+endJobDate
                               +" , searchDTO.getCandidateCode() = "+searchDTO.getCandidateCode()+" , searchDTO.getHireDateFrom() = "+searchDTO.getHireDateFrom());

            /** this part for male emp only */
            DAO().updateSalEmpWifesForExternalMov(salEmpMovDTO);
                    
            /** this part for male and female emp */
            DAO().updateSalEmpChildrenForExternalMov(salEmpMovDTO);
            DAO().copySalEmpSalElmsForChildrenRaiseForExternalMov(salEmpMovDTO, 2);

            return true;
        } catch (SharedApplicationException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } catch (DataBaseException e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        }
    }
    
    public Boolean chkWrkCenterStatus(IRequestInfoDTO iRequestInfoDTO,Long minCode, String wrkCode) throws DataBaseException,
    SharedApplicationException {
        
        return DAO().chkWrkCenterStatus(minCode, wrkCode);
        }
    
}
