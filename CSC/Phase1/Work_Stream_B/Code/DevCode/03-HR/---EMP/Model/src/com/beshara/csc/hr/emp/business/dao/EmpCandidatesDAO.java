package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.base.paging2.IPagingSortPropertyDTO;
import com.beshara.base.security.SecurityInfoHelper;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.sec.business.entity.IUsersEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.crs.business.entity.ICandidatePersonsEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.EmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.empenquiry.EmpReqEnquiryDTO;
import com.beshara.csc.hr.emp.business.dto.empenquiry.IEmpReqEnquiryDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.ParametersDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateStatusEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.SalElementInsertionException;
import com.beshara.csc.hr.emp.business.shared.exception.VacInsertionException;
import com.beshara.csc.hr.sal.business.dto.ISalEmpMovDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.org.business.client.IMinistriesClient;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;
import com.beshara.csc.sharedutils.business.util.constants.ISalConstants;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;

import oracle.jdbc.OracleTypes;


public class EmpCandidatesDAO extends EmpBaseDAO {
    public EmpCandidatesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpCandidatesDAO();
    }

    @Override
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidatesEntityKey id = (IEmpCandidatesEntityKey)id1;
            EmpCandidatesEntity empCandidateEntity = null;
            List<EmpCandidatesEntity> list =
                EM().createQuery("SELECT o FROM EmpCandidatesEntity o WHERE o.candidateCode = :candidateCode and o.candidateCodeSeq = :candidateCodeSeq").setHint("toplink.refresh",
                                                                                                                                                                  "true").setParameter("candidateCode",
                                                                                                                                                                                       id.getCandidateCode()).setParameter("candidateCodeSeq",
                                                                                                                                                                                                                           id.getCandidateCodeSeq()).getResultList();
            if (list != null && list.size() > 0) {
                empCandidateEntity = list.get(0);
            }

            if (empCandidateEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpCandidatesDTO empCandidateDTO = EmpEntityConverter.getEmpCandidatesDTO(empCandidateEntity);
            //Add EmployeeDocuments
            //            if (empCandidateEntity.getEmpCandidateDocumentsList() != null) {
            //                List<IBasicDTO> empCandidateDocumentsEntityList = new ArrayList<IBasicDTO>();
            //                for (EmpCandidateDocumentsEntity entity : empCandidateEntity.getEmpCandidateDocumentsList()) {
            //                    empCandidateDocumentsEntityList.add(EmpEntityConverter.getEmpCandidateDocumentsDTO(entity));
            //                }
            //                empCandidateDTO.setEmpCandidateDocumentsList(empCandidateDocumentsEntityList);
            //            }
            //            // Add EmployeeProcedures
            //            if (empCandidateEntity.getEmpCandidateProceduresList() != null) {
            //                List<IBasicDTO> empCandidateProceduresEntityList = new ArrayList<IBasicDTO>();
            //                for (EmpCandidateProceduresEntity entity : empCandidateEntity.getEmpCandidateProceduresList()) {
            //                    empCandidateProceduresEntityList.add(EmpEntityConverter.getEmpCandidateProceduresDTO(entity));
            //                }
            //                empCandidateDTO.setEmpCandidateProceduresList(empCandidateProceduresEntityList);
            //            }
            //            // Add Employee Salary
            //
            //            if (empCandidateEntity.getEmpCndSalaryElementsList() != null) {
            //
            //
            //                List<IEmpCndSalaryElementsDTO> empCndSalaryElementsDTOList = new ArrayList<IEmpCndSalaryElementsDTO>();
            //                for (EmpCndSalaryElementsEntity entity : empCandidateEntity.getEmpCndSalaryElementsList()) {
            //                    if (entity.getUntilDate() == null) {
            //                        //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
            //                        // need to test
            //                        empCndSalaryElementsDTOList.add(EmpEntityConverter.getEmpCndSalaryElementsDTO(entity));
            //                    }
            //                }
            //                empCandidateDTO.setEmpCndSalaryElementsList((List)empCndSalaryElementsDTOList);
            //            }
            //            //End of add list of document and procedure
            //
            //            // Add EmployeeExtraData
            //            List<EmpCandidateExtraDataEntity> empCandidateExtraDataEntityList =
            //                this.getAllEmployeeExtraDataByCandCodeAndCandSeq(id.getCandidateCode(), id.getCandidateCodeSeq());
            //            empCandidateDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            //            if (empCandidateExtraDataEntityList != null && !empCandidateExtraDataEntityList.isEmpty()) {
            //                List<IBasicDTO> empCandidateExtraDataDTOList = new ArrayList<IBasicDTO>();
            //                System.out.println("@#$ = " + empCandidateExtraDataDTOList.size());
            //                for (EmpCandidateExtraDataEntity entity : empCandidateExtraDataEntityList) {
            //                    empCandidateExtraDataDTOList.add(EmpEntityConverter.getEmpCandidateExtraDataDTO(entity));
            //                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
            //                        empCandidateDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
            //                        empCandidateDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
            //                        empCandidateDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
            //                        empCandidateDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
            //                        empCandidateDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());
            //
            //                    }
            //                }
            //                empCandidateDTO.setEmpCandidateExtraDataList((List)empCandidateExtraDataEntityList);
            //            }
            return empCandidateDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpCandidatesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else if (e instanceof ItemNotFoundException) {
                throw (ItemNotFoundException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmpCandidatesDTO addNewSeqForCandidate(IBasicDTO employeesDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        Long condCodeSeq = 1L;
        if (employeesDTO1 != null) {
            IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)employeesDTO1;
            if ((IEmpCandidatesEntityKey)empCandidatesDTO.getCode() == null) {
                Long condCode = findNewId();
                condCodeSeq = 1L;
                employeesDTO1.setCode(EmpEntityKeyFactory.createEmpCandidatesEntityKey(condCode, condCodeSeq));
            } else {
                condCodeSeq = ((IEmpCandidatesEntityKey)empCandidatesDTO.getCode()).getCandidateCodeSeq() + 1L;
            }


            EmpCandidatesEntity empCandidatesEntity = new EmpCandidatesEntity();
            try {
                empCandidatesEntity.setCandidateCode(((IEmpCandidatesEntityKey)empCandidatesDTO.getCode()).getCandidateCode());
                empCandidatesEntity.setCandidateCodeSeq(condCodeSeq);
                empCandidatesEntity.setMinistryFileNo(empCandidatesDTO.getMinistryFileNo());
                empCandidatesEntity.setCscFileNo(empCandidatesDTO.getCscFileNo());
                empCandidatesEntity.setCndStatusCode(empCandidatesDTO.getCndStatusCode());
                empCandidatesEntity.setHireDate(empCandidatesDTO.getHireDate());
                empCandidatesEntity.setApprovedDate(empCandidatesDTO.getApprovedDate());
                empCandidatesEntity.setStartWorkingDate(empCandidatesDTO.getStartWorkingDate());
                empCandidatesEntity.setDateOfNextRaise(empCandidatesDTO.getDateOfNextRaise());
                empCandidatesEntity.setTestPeriodDate(empCandidatesDTO.getTestPeriodDate());
                empCandidatesEntity.setActiveFlag(empCandidatesDTO.getActiveFlag());
                empCandidatesEntity.setTransferToEmpFlag(empCandidatesDTO.getTransferToEmpFlag());
                empCandidatesEntity.setSocialInsurNo(empCandidatesDTO.getSocialInsurNo());
                empCandidatesEntity.setCreatedBy(empCandidatesDTO.getCreatedBy());
                empCandidatesEntity.setCreatedDate(empCandidatesDTO.getCreatedDate());
                empCandidatesEntity.setAuditStatus(empCandidatesDTO.getAuditStatus());
               
                empCandidatesEntity.setSocialInsurNo(empCandidatesDTO.getSocialInsurNo());

                if (empCandidatesDTO.getWorkCentersDTO() != null) {
                    IWorkCentersDTO workCentersDTO = (IWorkCentersDTO)empCandidatesDTO.getWorkCentersDTO();
                    IWorkCentersEntityKey ek = (IWorkCentersEntityKey)workCentersDTO.getCode();
                    if (ek == null) {
                        throw new InvalidDataEntryException();
                    }
                    Long minCode = ek.getMinCode();
                    String wrkCode = ek.getWrkCode();
                    if (minCode == null || wrkCode == null || wrkCode.equals("")) {
                        throw new InvalidDataEntryException();
                    }
                    empCandidatesEntity.setMinCode(minCode);
                    empCandidatesEntity.setWrkCode(wrkCode);
                } else {
                    empCandidatesEntity.setMinCode(empCandidatesDTO.getMinCode());
                }


                //                if (empCandidatesDTO.getWorkCentersDTO() != null) {
                //                    EmpWorkCentersEntity workCentersEntity =
                //                        EM().find(EmpWorkCentersEntity.class, (empCandidatesDTO.getWorkCentersDTO().getCode()));
                //                    IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)empCandidatesDTO.getWorkCentersDTO().getCode();
                //                    if (workCentersEntity == null) {
                //                        throw new ItemNotFoundException();
                //                    }
                //                    empCandidatesEntity.setWorkCentersEntity(workCentersEntity);
                //                    empCandidatesEntity.setWrkCode(wEk.getWrkCode());
                //                    empCandidatesEntity.setMinCode(wEk.getMinCode());
                //                } else {
                //                    empCandidatesEntity.setMinCode(empCandidatesDTO.getMinCode());
                //                }

                if (empCandidatesDTO.getCandidatePersonsDTO() != null &&
                    empCandidatesDTO.getCandidatePersonsDTO().getCode() != null) {
                    // CandidatePersonsEntity candidatePersonsEntity = em.find(CandidatePersonsEntity.class, (empCandidatesDTO.getCandidatePersonsDTO().getCode()));
                    ICandidatePersonsEntityKey pEk =
                        (ICandidatePersonsEntityKey)empCandidatesDTO.getCandidatePersonsDTO().getCode();
                    if (pEk == null) {
                        throw new ItemNotFoundException();
                    } else {
                        empCandidatesEntity.setCivilId(pEk.getCivilId());
                        empCandidatesEntity.setCrsTrxCode(pEk.getTrxCode());
                        empCandidatesEntity.setCrsRegperiodCode(pEk.getRegperiodCode());
                    }
                }

                if (empCandidatesDTO.getUsersDTOApprovedDTO() != null) {
                    // UsersEntity usersEntityApprovedBy = em.find(UsersEntity.class, (empCandidatesDTO.getUsersDTOApprovedDTO().getCode()));
                    IUsersEntityKey uEk = (IUsersEntityKey)empCandidatesDTO.getUsersDTOApprovedDTO().getCode();
                    if (uEk == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setApprovedByUser(uEk.getUserCode());
                }

                if (empCandidatesDTO.getUsersDTOAuditBy() != null) {
                    // UsersEntity usersEntityAuditBy = em.find(UsersEntity.class, (empCandidatesDTO.getUsersDTOAuditBy().getCode()));
                    IUsersEntityKey uEk = (IUsersEntityKey)empCandidatesDTO.getUsersDTOAuditBy().getCode();
                    if (uEk == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setAuditByUser(uEk.getUserCode());
                }

                if (empCandidatesDTO.getEmpCandidateStatusDTO() != null) {
                    EmpCandidateStatusEntity empCandidateStatus =
                        EM().find(EmpCandidateStatusEntity.class, (empCandidatesDTO.getEmpCandidateStatusDTO().getCode()));
                    if (empCandidateStatus == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setEmpCandidateStatusEntity(empCandidateStatus);
                }

                if (empCandidatesDTO.getHireTypesDTO() != null) {
                    HireTypesEntity hireTypesEntity =
                        EM().find(HireTypesEntity.class, (empCandidatesDTO.getHireTypesDTO().getCode()));
                    if (hireTypesEntity == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setHireTypesEntity(hireTypesEntity);

                }

                if (empCandidatesDTO.getHireStagesDTO() != null) {
                    HireStagesEntity hireStagesEntity =
                        EM().find(HireStagesEntity.class, (empCandidatesDTO.getHireStagesDTO().getCode()));
                    if (hireStagesEntity == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setHireStagesEntity(hireStagesEntity);

                }

                if (empCandidatesDTO.getJobsDTO() != null) {
                    IJobsDTO jobsDTO = (IJobsDTO)empCandidatesDTO.getJobsDTO();
                    IJobsEntityKey ek = (IJobsEntityKey)jobsDTO.getCode();
                    if (ek == null) {
                        throw new InvalidDataEntryException();
                    }
                    String jobCode = ek.getJobCode();
                    if (jobCode == null) {
                        throw new InvalidDataEntryException();
                    }
                    empCandidatesEntity.setJobCode(jobCode);
                }

                //                if (empCandidatesDTO.getJobsDTO() != null) {
                //                    EmpJobsEntity jobsEntity =
                //                        EM().find(EmpJobsEntity.class, (empCandidatesDTO.getJobsDTO().getCode()));
                //                    // IJobsEntityKey jEk = (IJobsEntityKey)empCandidatesDTO.getJobsDTO().getCode();
                //                    if (jobsEntity == null) {
                //                        throw new ItemNotFoundException();
                //                    }
                //                    empCandidatesEntity.setJobsEntity(jobsEntity);
                //
                //                }
                if (empCandidatesDTO.getJobsDTOOtherJob() != null) {
                    IJobsDTO jobsDTOOtherDTO = (IJobsDTO)empCandidatesDTO.getJobsDTOOtherJob();
                    IJobsEntityKey ek = (IJobsEntityKey)jobsDTOOtherDTO.getCode();
                    if (ek == null) {
                        throw new InvalidDataEntryException();
                    }
                    String jobCode = ek.getJobCode();
                    if (jobCode == null) {
                        throw new InvalidDataEntryException();
                    }
                    empCandidatesEntity.setJobCodeOtherJob(jobCode);
                }
                //                if (empCandidatesDTO.getJobsDTOOtherJob() != null) {
                //                    EmpJobsEntity otherJobEntity =
                //                        EM().find(EmpJobsEntity.class, (empCandidatesDTO.getJobsDTOOtherJob().getCode()));
                //                    //IJobsEntityKey jEk = (IJobsEntityKey)empCandidatesDTO.getJobsDTOOtherJob().getCode();
                //
                //                    if (otherJobEntity == null) {
                //                        throw new ItemNotFoundException();
                //                    }
                //                    empCandidatesEntity.setJobsEntityOtherJob(otherJobEntity);
                //
                //                }
                if (empCandidatesDTO.getBgtProgramsDTO() != null) {
                    // BgtProgramsEntity bgtProgramsEntity = em.find(BgtProgramsEntity.class, (empCandidatesDTO.getBgtProgramsDTO().getCode()));
                    try {
                        IBgtProgramsEntityKey bgtEk =
                            (IBgtProgramsEntityKey)empCandidatesDTO.getBgtProgramsDTO().getCode();
                        if (bgtEk == null) {
                            throw new ItemNotFoundException();
                        }
                        empCandidatesEntity.setBgtprgCode(bgtEk.getPrgCode());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                if (empCandidatesDTO.getBgtTypesDTO() != null) {

                    IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)(empCandidatesDTO.getBgtTypesDTO().getCode());
                    if (bgtTEk == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setBgttypeCode(bgtTEk.getTypeCode());

                }


                IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                    (IKwtCitizensResidentsDTO)empCandidatesDTO.getCitizensResidentsDTO();
                if (kwtCitizensResidentsDTO == null) {
                    throw new InvalidDataEntryException();
                }
                IKwtCitizensResidentsEntityKey kwtCitizenKey =
                    (IKwtCitizensResidentsEntityKey)kwtCitizensResidentsDTO.getCode();
                if (kwtCitizenKey == null) {
                    throw new InvalidDataEntryException();
                }
                Long civilId = kwtCitizenKey.getCivilId();
                if (civilId == null) {
                    throw new InvalidDataEntryException();
                }
                empCandidatesEntity.setCivilId(civilId);

                /* if (empCandidatesDTO.getCitizensResidentsDTO() != null) {
                    EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity =
                        EM().find(EmpKwtCitizensResidentsEntity.class,
                                  (empCandidatesDTO.getCitizensResidentsDTO().getCode()));
                    // IKwtCitizensResidentsEntityKey kwEk = (IKwtCitizensResidentsEntityKey)empCandidatesDTO.getCitizensResidentsDTO().getCode();
                    if (kwtCitizensResidentsEntity == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setCitizensResidentsEntity(kwtCitizensResidentsEntity);
                }*/
            } catch (Exception e) {
                e = wrapIntoDataBaseException(e);
                if (e instanceof DataBaseException) {
                    throw (DataBaseException)e;
                } else {
                    throw (SharedApplicationException)e;
                }
            }
            IEmpCandidatesDTO addedDTO = EmpEntityConverter.getEmpCandidatesDTO(this.doAdd(empCandidatesEntity));
            return addedDTO;
        }
        return null;
    }

    public Boolean updateActiveFlag(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {


        IEmpCandidatesDTO employeesDTO = (IEmpCandidatesDTO)employeesDTO1;
        try {

            EmpCandidatesEntity employeesEntity = EM().find(EmpCandidatesEntity.class, employeesDTO.getCode());


            employeesEntity.setActiveFlag(employeesDTO.getActiveFlag());

            doUpdate(employeesEntity);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return Boolean.TRUE;
    }

    public Boolean updateActive(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {

        IEmpCandidatesDTO employeesDTO = (IEmpCandidatesDTO)employeesDTO1;
        try {
            String strquery = " Update HR_EMP_CANDIDATES  set ACTIVE_FLAG= " + employeesDTO.getActiveFlag();
            strquery =
                    strquery + " where CANDIDATE_CODE =" + ((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCode().toString();
            strquery =
                    strquery + " and CANDIDATE_CODE_SEQ =" + ((IEmpCandidatesEntityKey)employeesDTO.getCode()).getCandidateCodeSeq().toString();
            // updated by A.AGAMY for data audit
            //Query q = EM().createNativeQuery(strquery);
            Connection con = getConnectionForUpdate();
            PreparedStatement ps = con.prepareCall(strquery);
            ps.executeUpdate();

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return true;
    }

    @Override
    public Boolean update(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {

        IEmpCandidatesDTO empCandidatesDTO = (IEmpCandidatesDTO)employeesDTO1;
        try {
            EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, empCandidatesDTO.getCode());

            empCandidatesEntity.setMinistryFileNo(empCandidatesDTO.getMinistryFileNo());
            empCandidatesEntity.setCscFileNo(empCandidatesDTO.getCscFileNo());
            empCandidatesEntity.setHireDate(empCandidatesDTO.getHireDate());
            empCandidatesEntity.setApprovedDate(empCandidatesDTO.getApprovedDate());
            empCandidatesEntity.setStartWorkingDate(empCandidatesDTO.getStartWorkingDate());
            empCandidatesEntity.setDateOfNextRaise(empCandidatesDTO.getDateOfNextRaise());
            empCandidatesEntity.setTestPeriodDate(empCandidatesDTO.getTestPeriodDate());
            empCandidatesEntity.setActiveFlag(empCandidatesDTO.getActiveFlag());
            empCandidatesEntity.setTransferToEmpFlag(empCandidatesDTO.getTransferToEmpFlag());
            empCandidatesEntity.setSocialInsurNo(empCandidatesDTO.getSocialInsurNo());
            empCandidatesEntity.setCreatedBy(empCandidatesDTO.getCreatedBy());
            empCandidatesEntity.setCreatedDate(empCandidatesDTO.getCreatedDate());
            empCandidatesEntity.setAuditStatus(empCandidatesDTO.getAuditStatus());
         
            empCandidatesEntity.setSocialInsurNo(empCandidatesDTO.getSocialInsurNo());

            if (empCandidatesDTO.getWorkCentersDTO() != null) {
                IWorkCentersDTO workCentersDTO = (IWorkCentersDTO)empCandidatesDTO.getWorkCentersDTO();
                IWorkCentersEntityKey ek = (IWorkCentersEntityKey)workCentersDTO.getCode();
                if (ek == null) {
                    throw new InvalidDataEntryException();
                }
                Long minCode = ek.getMinCode();
                String wrkCode = ek.getWrkCode();
                if (minCode == null || wrkCode == null || wrkCode.equals("")) {
                    throw new InvalidDataEntryException();
                }
                empCandidatesEntity.setMinCode(minCode);
                empCandidatesEntity.setWrkCode(wrkCode);
            } else {
                empCandidatesEntity.setMinCode(empCandidatesDTO.getMinCode());
            }
            //            if (employeesDTO.getWorkCentersDTO() != null) {
            //                EmpWorkCentersEntity workCentersEntity =
            //                    EM().find(EmpWorkCentersEntity.class, (employeesDTO.getWorkCentersDTO().getCode()));
            //                //IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)employeesDTO.getWorkCentersDTO().getCode();
            //                if (workCentersEntity == null) {
            //                    throw new ItemNotFoundException();
            //                } else {
            //                    employeesEntity.setWorkCentersEntity(workCentersEntity);
            //                    employeesEntity.setWrkCode(workCentersEntity.getWrkCode());
            //                    employeesEntity.setMinCode(workCentersEntity.getMinCode());
            //                }
            //            }

            if (empCandidatesDTO.getCandidatePersonsDTO() != null &&
                empCandidatesDTO.getCandidatePersonsDTO().getCode() != null) {
                // CandidatePersonsEntity candidatePersonsEntity = em.find(CandidatePersonsEntity.class, (employeesDTO.getCandidatePersonsDTO().getCode()));
                ICandidatePersonsEntityKey pEk =
                    (ICandidatePersonsEntityKey)empCandidatesDTO.getCandidatePersonsDTO().getCode();
                if (pEk == null) {
                    throw new ItemNotFoundException();
                } else {
                    empCandidatesEntity.setCivilId(pEk.getCivilId());
                    empCandidatesEntity.setCrsTrxCode(pEk.getTrxCode());
                    empCandidatesEntity.setCrsRegperiodCode(pEk.getRegperiodCode());
                }

            }

            if (empCandidatesDTO.getUsersDTOApprovedDTO() != null) {
                //  UsersEntity usersEntityApprovedBy = em.find(UsersEntity.class, (employeesDTO.getUsersDTOApprovedDTO().getCode()));
                IUsersEntityKey uEk = (IUsersEntityKey)empCandidatesDTO.getUsersDTOApprovedDTO().getCode();
                if (uEk == null) {
                    throw new ItemNotFoundException();
                }
                empCandidatesEntity.setApprovedByUser(uEk.getUserCode());
            }

            if (empCandidatesDTO.getUsersDTOAuditBy() != null) {
                // UsersEntity usersEntityAuditBy = em.find(UsersEntity.class, (employeesDTO.getUsersDTOAuditBy().getCode()));
                IUsersEntityKey uEk = (IUsersEntityKey)empCandidatesDTO.getUsersDTOAuditBy().getCode();
                if (uEk == null) {
                    throw new ItemNotFoundException();
                }
                empCandidatesEntity.setAuditByUser(uEk.getUserCode());
            }

            if (empCandidatesDTO.getEmpCandidateStatusDTO() != null) {
                EmpCandidateStatusEntity empCandidateStatus =
                    EM().find(EmpCandidateStatusEntity.class, (empCandidatesDTO.getEmpCandidateStatusDTO().getCode()));
                if (empCandidateStatus == null) {
                    throw new ItemNotFoundException();
                }
                empCandidatesEntity.setEmpCandidateStatusEntity(empCandidateStatus);
            }

            if (empCandidatesDTO.getHireTypesDTO() != null) {
                HireTypesEntity hireTypesEntity =
                    EM().find(HireTypesEntity.class, (empCandidatesDTO.getHireTypesDTO().getCode()));
                if (hireTypesEntity == null) {
                    throw new ItemNotFoundException();
                }
                empCandidatesEntity.setHireTypesEntity(hireTypesEntity);

            }

            if (empCandidatesDTO.getHireStagesDTO() != null) {
                HireStagesEntity hireStagesEntity =
                    EM().find(HireStagesEntity.class, (empCandidatesDTO.getHireStagesDTO().getCode()));
                if (hireStagesEntity == null) {
                    throw new ItemNotFoundException();
                }
                empCandidatesEntity.setHireStagesEntity(hireStagesEntity);

            }
            if (empCandidatesDTO.getJobsDTO() != null) {
                IJobsDTO jobsDTO = (IJobsDTO)empCandidatesDTO.getJobsDTO();
                IJobsEntityKey ek = (IJobsEntityKey)jobsDTO.getCode();
                if (ek == null) {
                    throw new InvalidDataEntryException();
                }
                String jobCode = ek.getJobCode();
                if (jobCode == null) {
                    throw new InvalidDataEntryException();
                }
                empCandidatesEntity.setJobCode(jobCode);
            } else {
                empCandidatesEntity.setJobCode(null);
            }

            if (empCandidatesDTO.getJobsDTOOtherJob() != null) {
                IJobsDTO jobsDTOOtherDTO = (IJobsDTO)empCandidatesDTO.getJobsDTOOtherJob();
                IJobsEntityKey ek = (IJobsEntityKey)jobsDTOOtherDTO.getCode();
                if (ek == null) {
                    throw new InvalidDataEntryException();
                }
                String jobCode = ek.getJobCode();
                if (jobCode == null) {
                    throw new InvalidDataEntryException();
                }
                empCandidatesEntity.setJobCodeOtherJob(jobCode);
            }
            //            if (empCandidatesDTO.getJobsDTO() != null) {
            //                EmpJobsEntity jobsEntity = EM().find(EmpJobsEntity.class, (empCandidatesDTO.getJobsDTO().getCode()));
            //                // IJobsEntityKey jEk = (IJobsEntityKey)employeesDTO.getJobsDTO().getCode();
            //                if (jobsEntity == null) {
            //                    throw new ItemNotFoundException();
            //                }
            //                empCandidatesEntity.setJobsEntity(jobsEntity);
            //            }
            //            if (empCandidatesDTO.getJobsDTOOtherJob() != null) {
            //                EmpJobsEntity otherJobEntity =
            //                    EM().find(EmpJobsEntity.class, (empCandidatesDTO.getJobsDTOOtherJob().getCode()));
            //                //  IJobsEntityKey jEk = (IJobsEntityKey)employeesDTO.getJobsDTOOtherJob().getCode();
            //                if (otherJobEntity == null) {
            //                    throw new ItemNotFoundException();
            //                }
            //                empCandidatesEntity.setJobsEntityOtherJob(otherJobEntity);
            //
            //            }
            if (empCandidatesDTO.getBgtProgramsDTO() != null) {
                //    BgtProgramsEntity bgtProgramsEntity =   em.find(BgtProgramsEntity.class, (employeesDTO.getBgtProgramsDTO().getCode()));
                try {
                    IBgtProgramsEntityKey bgtEk =
                        (IBgtProgramsEntityKey)(empCandidatesDTO.getBgtProgramsDTO().getCode());
                    if (bgtEk == null) {
                        throw new ItemNotFoundException();
                    }
                    empCandidatesEntity.setBgtprgCode(bgtEk.getPrgCode());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //////////////////////////////////////
            if (empCandidatesDTO.getBgtTypesDTO() != null) {
                // BgtTypesEntity bgtTypesEntity =   em.find(BgtTypesEntity.class, (employeesDTO.getBgtTypesDTO().getCode()));
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)(empCandidatesDTO.getBgtTypesDTO().getCode());
                if (bgtTEk == null) {
                    throw new ItemNotFoundException();
                }
                empCandidatesEntity.setBgttypeCode(bgtTEk.getTypeCode());
            }else {
                empCandidatesEntity.setBgttypeCode(null);
            }
            doUpdate(empCandidatesEntity);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException, SharedApplicationException {

        try {
            List _ministryFileNo =
                EM().createNamedQuery("EmpCandidatesEntity.isMinistryFileNoExist").setParameter("ministryFileNo",
                                                                                                ministryFileNo).getResultList();
            if (_ministryFileNo != null && _ministryFileNo.size() > 0)
                return Boolean.TRUE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return Boolean.FALSE;
    }


    
    public Long getCountByHireStageAndMinistryWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                      SharedApplicationException {

        /////////////////////        
        
        //List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        Long listCount = 0L;
        
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            IPagingRequestDTO requestDTO = ((IPagingRequestDTO)searchDTO.getPagingRequestDTO());


            StringBuilder queryStr =
                new StringBuilder("SELECT HR_EMP_CANDIDATES.* FROM HR_EMP_CANDIDATES " + "INNER JOIN INF_KWT_CITIZENS_RESIDENTS " +
                                  "ON HR_EMP_CANDIDATES.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID " +
                                  "WHERE HR_EMP_CANDIDATES.HIRSTAGE_CODE IN ");
            if (searchDTO.getEmpHireStages() != null && searchDTO.getEmpHireStages().size() > 0) {
                queryStr.append("(");
                for (Object hireStageCode : searchDTO.getEmpHireStages()) {
                    queryStr.append(hireStageCode.toString());
                    queryStr.append(",");
                }
                queryStr = queryStr.deleteCharAt(queryStr.lastIndexOf(","));
                queryStr.append(")");
            }
            if (searchDTO.getEmpHireStatus() != null) {
                queryStr.append(" AND HR_EMP_CANDIDATES.CNDSTATUS_CODE=" + searchDTO.getEmpHireStatus() + "");
            }
            if (searchDTO.getEmpHireTypes() != null) {
                queryStr.append(" AND HR_EMP_CANDIDATES.HIRTYPE_CODE   in  ( select  HIRTYPE_CODE from HR_EMP_HIRE_TYPES where FIRST_PARENT =  " + searchDTO.getEmpHireTypes() + " ) ");
            }
            if (searchDTO.getActiveFlag() != null) {
                queryStr.append(" AND HR_EMP_CANDIDATES.ACTIVE_FLAG=" + searchDTO.getActiveFlag() + "");
            }
            if (searchDTO.getMinistryCode() != null) {
                queryStr.append(" and HR_EMP_CANDIDATES.min_code = " + searchDTO.getMinistryCode());
            }
            
            if(searchDTO.getEmpHireStages().get(0).equals("10") ){
                    queryStr.append(" AND( HR_EMP_CANDIDATES.CIVIL_ID not in (select k.CIVIL_ID from  INF_KWT_WORK_DATA k) ");
                    queryStr.append("and  HR_EMP_CANDIDATES.CANDIDATE_CODE not in ( SELECT CANDIDATE_CODE       FROM HR_EMP_CANDIDATE_EXTRA_DATA     WHERE   EXTDATTYPE_CODE = 336 and value = 1))");
                }
            
            if(searchDTO.getCivilId() !=null){
                    queryStr.append(" AND HR_EMP_CANDIDATES.CIVIL_ID= " + searchDTO.getCivilId());
                }
            if(searchDTO.getName() !=null && !searchDTO.getName().equals("")){
            queryStr.append(" AND HR_EMP_CANDIDATES.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   searchDTO.getName()) +
                           " ) ");
            
            }
           
            System.out.println(queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString(), EmpCandidatesEntity.class);            
            
            List<EmpCandidatesEntity> list = query.getResultList();
            for (EmpCandidatesEntity entity : list) {
                boolean ret = false;
                boolean exper=false;
                if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                        exper=isHasExper(entity.getCandidateCode());
                    } catch (Exception e) {
                    }
                    if (ret || exper) {
                        listCount++;
                        //listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                    }
                } else if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_NOT_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                        exper=isHasExper(entity.getCandidateCode());
                    } catch (Exception e) {
                    }
                    if (!ret || !exper) {
                        listCount++;
                        //listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                    }
                } else {
                    listCount++;
                    //listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                }
            }

            //            if (listDTO.size() == 0) {
            //                throw new NoResultException();
            //            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        //Long count = (long)listDTO.size();
        return listCount;

    }


    public boolean isHasExper(Long candidateCode) throws DataBaseException,
                                                                                                    SharedApplicationException{
        try{
        StringBuilder queryStr =
            new StringBuilder(" SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = ");
        queryStr.append(candidateCode);
        queryStr.append(" and CANDIDATE_CODE_SEQ = 1 ");
        queryStr.append(" AND EXTDATTYPE_CODE = 336  ");
        queryStr.append(" AND value =1 ");

        
            Query query = EM().createNativeQuery(queryStr.toString());
            Vector count = (Vector)query.getSingleResult();
            if ((BigDecimal)count.get(0) != null) {
              return true;
            }

            } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
            }
            
        return false;
        }
    /**
     * overloaded method that return CountByHireStageAndMinistryWithPaging
     * in case manageId=4 (Fatwa Manager)
     * add cases getAllEmpCandsRelatedBycontract & getAllEmpCandsRelatedByCRS on query
     * @param _searchDTO
     * @param manageId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @auther KareemSayed
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public Long getCountByHireStageAndMinistryWithPaging(IBasicDTO _searchDTO, int manageId) throws DataBaseException,
                                                                                                    SharedApplicationException {

        /////////////////////
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();

            StringBuilder ejbql = null;
            StringBuilder queryStr =
                new StringBuilder("SELECT empcand.* FROM HR_EMP_CANDIDATES empcand INNER JOIN INF_KWT_CITIZENS_RESIDENTS " +
                                  "ON empcand.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID " +
                                  "WHERE empcand.HIRSTAGE_CODE IN ");
            if (searchDTO.getEmpHireStages() != null && searchDTO.getEmpHireStages().size() > 0) {
                queryStr.append("(");
                for (Object hireStageCode : searchDTO.getEmpHireStages()) {
                    queryStr.append(hireStageCode.toString());
                    queryStr.append(",");
                }
                queryStr = queryStr.deleteCharAt(queryStr.lastIndexOf(","));
                queryStr.append(")");
            }
            if (searchDTO.getEmpHireStatus() != null) {
                queryStr.append(" AND empcand.CNDSTATUS_CODE=" + searchDTO.getEmpHireStatus() + "");
            }
            if (searchDTO.getActiveFlag() != null) {
                queryStr.append(" AND empcand.ACTIVE_FLAG=" + searchDTO.getActiveFlag() + "");
            }
            if (searchDTO.getMinistryCode() != null) {
                queryStr.append(" and empcand.min_code = " + searchDTO.getMinistryCode());
            }
            // this case for  getAllEmpCandsRelatedBycontract & getAllEmpCandsRelatedByCRS
            if (searchDTO.getEmpHireTypes() == null) {
                ejbql =
                        new StringBuilder("SELECT C.* " + " FROM HR_EMP_CND_SALARY_ELEMENTS S , HR_EMP_CANDIDATES C" + " WHERE S.CANDIDATE_CODE = C.CANDIDATE_CODE" +
                                          " AND S.CANDIDATE_CODE_SEQ = 1" + " AND C.ACTIVE_FLAG = 1" +
                                          " AND C.HIRSTAGE_CODE = " + searchDTO.getEmpHireStages().get(0));
                if (searchDTO.getMinistryCode() != null) {
                    ejbql.append(" AND MIN_CODE =" + searchDTO.getMinistryCode());
                }
                ejbql.append(" AND EXISTS(SELECT 1 " + " FROM HR_SAL_GUIDE_EXTRA_DATA X " +
                             " WHERE EXTDATTYPE_CODE = 6" + " AND X.ELMGUIDE_CODE = S.ELMGUIDE_CODE)");
            } else {
                ejbql =
                        new StringBuilder("select C.* from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C" + " WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE And CNDSTATUS_CODE = 2" +
                                          " AND C.ACTIVE_FLAG=1 " + "AND HIRSTAGE_CODE = " +
                                          searchDTO.getEmpHireStages().get(0));
                if (searchDTO.getMinistryCode() != null) {
                    ejbql.append(" AND MIN_CODE =" + searchDTO.getMinistryCode());
                }
                ejbql.append(" AND (H.HIRTYPE_CODE = 2 OR PARENT_HIRTYPE_CODE = 2) ");
            }
            queryStr.append(" AND exists(").append(ejbql).append("and empcand.CANDIDATE_CODE=c.CANDIDATE_CODE and empcand.CANDIDATE_CODE_SEQ=c.CANDIDATE_CODE_SEQ)");
            //TODO apply sorting there is no need for sort when calculate count
            //            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            //                requestDTO.getSortColumnList().size() > 0) {
            //                queryStr.append(" ORDER BY ");
            //                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
            //                    String column = (String)requestDTO.getSortColumnList().get(i);
            //                    queryStr.append(column);
            //                    if (!requestDTO.isSortAscending()) {
            //                        queryStr.append(" DESC");
            //                    }
            //                    if (i < requestDTO.getSortColumnList().size() - 1) {
            //                        queryStr.append(" , ");
            //                    }
            //                }
            //            }
            
            if(searchDTO.getCivilId() !=null){
                    queryStr.append(" AND empcand.CIVIL_ID= " + searchDTO.getCivilId());
                }
            if(searchDTO.getName() !=null && !searchDTO.getName().equals("")){
            queryStr.append(" AND empcand.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   searchDTO.getName()) +
                           " ) ");
            
            }
            System.out.println("EmpCandidatesFacadeBean.getCountByHireStageAndMinistryWithPaging = " +
                               queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString(), EmpCandidatesEntity.class);
            List<EmpCandidatesEntity> list = query.getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            for (EmpCandidatesEntity entity : list) {


                listDTO.add(new BasicDTO());


            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        Long count = (long)listDTO.size();
        return count;

    }

    /**
     * overloaded method that return getCountByHireStageAndMinistryWithPagingForCRS
     * in case manageId=4 (Fatwa Manager)
     * add cases getAllEmpCandsRelatedBycontract & getAllEmpCandsRelatedByCRS on query
     * @param _searchDTO
     * @param manageId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @auther KareemSayed
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public Long getCountByHireStageAndMinistryWithPagingForCRS(IBasicDTO _searchDTO,
                                                               int manageId) throws DataBaseException,
                                                                                    SharedApplicationException {

        /////////////////////
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();

            StringBuilder ejbql = null;
            StringBuilder queryStr =
                new StringBuilder("SELECT empcand.* FROM HR_EMP_CANDIDATES empcand INNER JOIN INF_KWT_CITIZENS_RESIDENTS " +
                                  "ON empcand.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID " +
                                  "WHERE empcand.HIRSTAGE_CODE IN ");
            if (searchDTO.getEmpHireStages() != null && searchDTO.getEmpHireStages().size() > 0) {
                queryStr.append("(");
                for (Object hireStageCode : searchDTO.getEmpHireStages()) {
                    queryStr.append(hireStageCode.toString());
                    queryStr.append(",");
                }
                queryStr = queryStr.deleteCharAt(queryStr.lastIndexOf(","));
                queryStr.append(")");
            }
            if (searchDTO.getEmpHireStatus() != null) {
                queryStr.append(" AND empcand.CNDSTATUS_CODE=" + searchDTO.getEmpHireStatus() + "");
            }
            if (searchDTO.getActiveFlag() != null) {
                queryStr.append(" AND empcand.ACTIVE_FLAG=" + searchDTO.getActiveFlag() + "");
            }
            if (searchDTO.getMinistryCode() != null) {
                queryStr.append(" and empcand.min_code = " + searchDTO.getMinistryCode());
            }
            ejbql =
                    new StringBuilder("select C.* from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C" + " WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE And CNDSTATUS_CODE = 2" +
                                      " AND C.ACTIVE_FLAG=1 " + "AND HIRSTAGE_CODE = " +
                                      searchDTO.getEmpHireStages().get(0));
            if (searchDTO.getMinistryCode() != null) {
                ejbql.append(" AND MIN_CODE =" + searchDTO.getMinistryCode());
            }
            ejbql.append(" AND (H.HIRTYPE_CODE = 2 OR PARENT_HIRTYPE_CODE = 2) ");
            queryStr.append(" AND exists(").append(ejbql).append("and empcand.CANDIDATE_CODE=c.CANDIDATE_CODE and empcand.CANDIDATE_CODE_SEQ=c.CANDIDATE_CODE_SEQ)");
            //TODO apply sorting  there is no need for sort when calculate count
            //            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            //                requestDTO.getSortColumnList().size() > 0) {
            //                queryStr.append(" ORDER BY ");
            //                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
            //                    String column = (String)requestDTO.getSortColumnList().get(i);
            //                    queryStr.append(column);
            //                    if (!requestDTO.isSortAscending()) {
            //                        queryStr.append(" DESC");
            //                    }
            //                    if (i < requestDTO.getSortColumnList().size() - 1) {
            //                        queryStr.append(" , ");
            //                    }
            //                }
            //            }

            if(searchDTO.getEmpHireStages().get(0).equals("10") ){
                    queryStr.append(" AND ( empcand.CIVIL_ID in (select k.CIVIL_ID from  INF_KWT_WORK_DATA k)");
                    queryStr.append("or empcand.CANDIDATE_CODE  in ( SELECT CANDIDATE_CODE       FROM HR_EMP_CANDIDATE_EXTRA_DATA     WHERE   EXTDATTYPE_CODE = 336 and value = 1))");
                }
            if(searchDTO.getCivilId() !=null){
                    queryStr.append(" AND empcand.CIVIL_ID= " + searchDTO.getCivilId());
                }
            if(searchDTO.getName() !=null && !searchDTO.getName().equals("")){
            queryStr.append(" AND empcand.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   searchDTO.getName()) +
                           " ) ");
           
            }
            System.out.println("EmpCandidatesFacadeBean.getCountByHireStageAndMinistryWithPagingForCRS = " +
                               queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString(), EmpCandidatesEntity.class);
            List<EmpCandidatesEntity> list = query.getResultList();
            if (list.size() == 0) {
                throw new NoResultException();
            }
            for (EmpCandidatesEntity entity : list) {
                if (entity.getHireStagesEntity().getHirstageCode().equals(Long.valueOf(IEMPConstants.HIRE_STAGE_JOB_NAME_REQUIRED_INPROGRESS))) {
                    listDTO.add(new BasicDTO());
                } else {
                    boolean ret = false;
                    boolean exper=false;
                    if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_EXPERIENCE) {
                        try {
                            ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                            exper=isHasExper(entity.getCandidateCode());
                        } catch (Exception e) {
                        }
                        if (ret || exper) {
                            listDTO.add(new BasicDTO());
                        }
                    } else if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_NOT_EXPERIENCE) {
                        try {
                            ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                            exper=isHasExper(entity.getCandidateCode());
                        } catch (Exception e) {
                        }
                        if (!ret || !exper) {
                            listDTO.add(new BasicDTO());
                        }
                    } else {
                        listDTO.add(new BasicDTO());
                    }
                }
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        Long count = (long)listDTO.size();
        return count;

    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getByHireStageWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                 SharedApplicationException {
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        List<IBasicDTO> listDTO1 = new ArrayList<IBasicDTO>();
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            IPagingRequestDTO requestDTO = ((IPagingRequestDTO)searchDTO.getPagingRequestDTO());


            StringBuilder queryStr = new StringBuilder("SELECT HR_EMP_CANDIDATES.* ,(SELECT VALUE       FROM HR_EMP_CANDIDATE_EXTRA_DATA     WHERE  HR_EMP_CANDIDATES.CANDIDATE_CODE = CANDIDATE_CODE     AND EXTDATTYPE_CODE = 326) as dVALUE , (SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = HR_EMP_CANDIDATES.CANDIDATE_CODE and CANDIDATE_CODE_SEQ = HR_EMP_CANDIDATES.CANDIDATE_CODE_SEQ  AND EXTDATTYPE_CODE =  14 ) as hasRevision  FROM HR_EMP_CANDIDATES ");
            queryStr.append(" , NL_ORG_MINISTRIES , INF_KWT_CITIZENS_RESIDENTS  ");
            queryStr.append(" where NL_ORG_MINISTRIES.MIN_CODE = HR_EMP_CANDIDATES.MIN_CODE ");
            queryStr.append(" and HR_EMP_CANDIDATES.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID ");
            queryStr.append(" and  HR_EMP_CANDIDATES.HIRSTAGE_CODE IN ");

            if (searchDTO.getEmpHireStages() != null && searchDTO.getEmpHireStages().size() > 0) {
                queryStr.append("(");
                for (Object hireStageCode : searchDTO.getEmpHireStages()) {
                    queryStr.append(hireStageCode.toString());
                    queryStr.append(",");
                }
                queryStr = queryStr.deleteCharAt(queryStr.lastIndexOf(","));
                queryStr.append(")");
            }
            if (searchDTO.getEmpHireStatus() != null) {
                queryStr.append(" AND HR_EMP_CANDIDATES.CNDSTATUS_CODE = " + searchDTO.getEmpHireStatus() + "");
            }
            if (searchDTO.getEmpHireTypes() != null) {
                queryStr.append(" AND HR_EMP_CANDIDATES.HIRTYPE_CODE   in  ( select  HIRTYPE_CODE from HR_EMP_HIRE_TYPES where FIRST_PARENT =  " + searchDTO.getEmpHireTypes() + " ) ");
            }
            if (searchDTO.getActiveFlag() != null) {
                queryStr.append(" AND HR_EMP_CANDIDATES.ACTIVE_FLAG = " + searchDTO.getActiveFlag() + "");
            }
            if (searchDTO.getMinistryCode() != null) {
                queryStr.append(" and HR_EMP_CANDIDATES.min_code = " + searchDTO.getMinistryCode());
            }
            
            if(searchDTO.getEmpHireStages().get(0).equals("10") ){
                    queryStr.append(" AND ( HR_EMP_CANDIDATES.CIVIL_ID not in (select k.CIVIL_ID from  INF_KWT_WORK_DATA k) ");
                    queryStr.append("and  HR_EMP_CANDIDATES.CANDIDATE_CODE not in ( SELECT CANDIDATE_CODE       FROM HR_EMP_CANDIDATE_EXTRA_DATA     WHERE   EXTDATTYPE_CODE = 336 and value = 1))");
                }
            
            if(searchDTO.getCivilId() !=null){
                    queryStr.append(" AND HR_EMP_CANDIDATES.CIVIL_ID= " + searchDTO.getCivilId());
                }
            if(searchDTO.getName() !=null && !searchDTO.getName().equals("")){
            queryStr.append(" AND HR_EMP_CANDIDATES.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   searchDTO.getName()) +
                           " ) ");
            
            }
            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                queryStr.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    if (column.equals("dVALUE")) {
                        queryStr.append("to_date(dVALUE , 'dd/mm/yyyy')");
                    } else
                        queryStr.append(column);
                    
                    if (!requestDTO.isSortAscending()) {
                        queryStr.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        queryStr.append(" , ");
                    }
                }
            }

            System.out.println(queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString(), EmpCandidatesEntity.class);
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            //em.createQuery("SELECT o FROM EmployeesEntity o").setHint("toplink.refresh", "true").getResultList();

            List<EmpCandidatesEntity> list = query.getResultList();
            for (EmpCandidatesEntity entity : list) {

                //            List<EmployeesEntity> empsList =
                //                em.createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setHint("toplink.refresh",
                //                                                                                                     "true").setParameter("civilId",
                //                                                                                                                          entity.getCivilId()).getResultList();
                //            EmployeesEntity employeesEntity = empsList.get(0);

                if (entity == null) {
                    throw new ItemNotFoundException();
                }
                boolean ret = false;
                boolean exper=false;
                if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                        exper=isHasExper(entity.getCandidateCode());
                    } catch (Exception e) {
                    }
                    if (ret || exper) {
                        listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                    }
                } else if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_NOT_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                        exper=isHasExper(entity.getCandidateCode());
                    } catch (Exception e) {
                    }
                    if (!ret || !exper) {
                        listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                    }
                } else {
                    listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                }
            }

            for (IBasicDTO empcand : listDTO) {
                IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
                IMinistriesDTO ministriesDTO = OrgDTOFactory.createMinistriesDTO();
                IMinistriesClient client = OrgClientFactory.getMinistriesClient();
                IMinistriesEntityKey eK =
                    OrgEntityKeyFactory.createMinistriesEntityKey(((IEmpCandidatesDTO)empcand).getMinCode());
                ministriesDTO = (IMinistriesDTO)client.getCodeNameByMinCode(((IEmpCandidatesDTO)empcand).getMinCode());
                workCentersDTO.setMinistriesDTO(ministriesDTO);

                ((IEmpCandidatesDTO)empcand).setWorkCentersDTO(workCentersDTO);
                empcand = ((IEmpCandidatesDTO)empcand);
                
                Long extraDataCount =
                    this.getExtraDataCount(((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCode(),((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCodeSeq(),14L);
                if (extraDataCount != null && extraDataCount >=1) {
                   ((IEmpCandidatesDTO) empcand).setHasRevision(true);
                }else{
                     ((IEmpCandidatesDTO) empcand).setHasRevision(false);
                    }
                ((IEmpCandidatesDTO)  empcand).setUserNameValue(getUserNameValue((IEmpCandidatesEntityKey)empcand.getCode() , 14L));
                
                listDTO1.add(empcand);
            }

            if (listDTO1.size() == 0) {
                throw new ItemNotFoundException();
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

        return listDTO1;

    }

    public List<IBasicDTO> filterByHireTypeWithMin(Long hirtypeCode, Long minCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        List<IEmpCandidatesDTO> arrayList = new ArrayList<IEmpCandidatesDTO>();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.filterByHireTypewithMin").setParameter("hirtypeCode",
                                                                                                  hirtypeCode).setParameter("cndStatusCode",
                                                                                                                            IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("activeFlag",
                                                                                                                                                                                  ISystemConstant.ACTIVE_FLAG).setParameter("val1",
                                                                                                                                                                                                                            IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("val2",
                                                                                                                                                                                                                                                                                               IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT).setParameter("minCode",
                                                                                                                                                                                                                                                                                                                                                         minCode).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                                                          "true").getResultList();
            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return (List)arrayList;
    }

    public List<IBasicDTO> filterByHireType(Long hirtypeCode) throws DataBaseException, SharedApplicationException {
        List<IEmpCandidatesDTO> arrayList = new ArrayList<IEmpCandidatesDTO>();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.filterByHireType").setParameter("hirtypeCode",
                                                                                           hirtypeCode).setParameter("cndStatusCode",
                                                                                                                     IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("activeFlag",
                                                                                                                                                                           ISystemConstant.ACTIVE_FLAG).setParameter("val1",
                                                                                                                                                                                                                     IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("val2",
                                                                                                                                                                                                                                                                                        IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                             "true").getResultList();
            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return (List)arrayList;
    }

    public List<IBasicDTO> filterByAllHireTypes() throws DataBaseException, SharedApplicationException {
        List<IEmpCandidatesDTO> arrayList = new ArrayList();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.filterByAllHireTypes").setParameter("hiretype1",
                                                                                               IEMPConstants._EMP_CENTERAL_HIRE_TYPE).setParameter("hiretype2",
                                                                                                                                                   IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY).setParameter("hiretype3",
                                                                                                                                                                                                           IEMPConstants._EMP_REEMPLOYEMENTS).setParameter("cndStatusCode",
                                                                                                                                                                                                                                                           IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("activeFlag",
                                                                                                                                                                                                                                                                                                                 ISystemConstant.ACTIVE_FLAG).setParameter("val1",
                                                                                                                                                                                                                                                                                                                                                           IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("val2",
                                                                                                                                                                                                                                                                                                                                                                                                                              IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   "true").getResultList();

            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return (List)arrayList;
    }

    public List<IBasicDTO> filterByAllHireTypesWithMin(Long minCode) throws DataBaseException,
                                                                            SharedApplicationException {
        List<IEmpCandidatesDTO> arrayList = new ArrayList();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.filterByAllHireTypesWithMin").setParameter("hiretype1",
                                                                                                      IEMPConstants._EMP_CENTERAL_HIRE_TYPE).setParameter("hiretype2",
                                                                                                                                                          IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY).setParameter("hiretype3",
                                                                                                                                                                                                                  IEMPConstants._EMP_REEMPLOYEMENTS).setParameter("cndStatusCode",
                                                                                                                                                                                                                                                                  IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("activeFlag",
                                                                                                                                                                                                                                                                                                                        ISystemConstant.ACTIVE_FLAG).setParameter("val1",
                                                                                                                                                                                                                                                                                                                                                                  IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("val2",
                                                                                                                                                                                                                                                                                                                                                                                                                                     IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT).setParameter("minCode",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               minCode).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                "true").getResultList();

            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return (List)arrayList;
    }

    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IEntityKey hireType) throws DataBaseException,
                                                                                               SharedApplicationException {
        List arrayList = new ArrayList<IBasicDTO>();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.getFilterEmployeeWaitingForHireDecision").setParameter("cndStatusCode",
                                                                                                                  IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("hirstageCode",
                                                                                                                                                                        IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("hirtypeCode",
                                                                                                                                                                                                                                         ((IHireTypesEntityKey)hireType).getHirtypeCode()).setParameter("activeFlag",
                                                                                                                                                                                                                                                                                                        ISystemConstant.ACTIVE_FLAG).getResultList();
            for (EmpCandidatesEntity employees : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(employees));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return arrayList;
    }


    public List<IBasicDTO> filterByHireTypeForHireExecute(Long hirtypeCode, Long loggedMin) throws DataBaseException,
                                                                                                   SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.filterByHireTypeForEmpExecute").setParameter("hirtypeCode",
                                                                                                        hirtypeCode).setParameter("cndStatusCode",
                                                                                                                                  IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("hirstageCode",
                                                                                                                                                                                        IEMPConstants._EMP_STAGE_WAITING_EXECUTION).setParameter("activeFlag",
                                                                                                                                                                                                                                                 ISystemConstant.ACTIVE_FLAG).setParameter("minCode",
                                                                                                                                                                                                                                                                                           CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest()).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                              "true").getResultList();
            System.out.println("hirtypeCode =" + hirtypeCode);
            System.out.println("cndStatusCode =" + IEMPConstants.EMP_CAND_STATUS_CANDITATE);
            System.out.println("hirstageCode =" + IEMPConstants._EMP_STAGE_WAITING_EXECUTION);
            System.out.println("activeFlag =" + ISystemConstant.ACTIVE_FLAG);

            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return arrayList;
    }

    public List<IBasicDTO> filterByAllHireTypesForHireExecute() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<EmpCandidatesEntity> list =
                EM().createNamedQuery("EmpCandidatesEntity.filterByAllHireTypesForHireExecute").setParameter("hiretype1",
                                                                                                             IEMPConstants._EMP_CENTERAL_HIRE_TYPE).setParameter("hiretype2",
                                                                                                                                                                 IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY).setParameter("hiretype3",
                                                                                                                                                                                                                         IEMPConstants._EMP_REEMPLOYEMENTS).setParameter("cndStatusCode",
                                                                                                                                                                                                                                                                         IEMPConstants.EMP_CAND_STATUS_CANDITATE).setParameter("hirstageCode",
                                                                                                                                                                                                                                                                                                                               IEMPConstants._EMP_STAGE_WAITING_EXECUTION).setParameter("activeFlag",
                                                                                                                                                                                                                                                                                                                                                                                        ISystemConstant.ACTIVE_FLAG).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                                                                                                             "true").getResultList();
            System.out.println("hiretype1 =" + IEMPConstants._EMP_CENTERAL_HIRE_TYPE + "hiretype" +
                               IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY + "hiretype3" +
                               IEMPConstants._EMP_REEMPLOYEMENTS + "       hirstatusCode=" +
                               IEMPConstants._EMP_STATUS_CANDIDATE + "hirstageCode" +
                               IEMPConstants._EMP_STAGE_WAITING_EXECUTION);
            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpDTOFactory.createEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return arrayList;
    }


    public List<IBasicDTO> getHireTypesAndParent(long hiretype) throws DataBaseException, SharedApplicationException {
        StringBuilder ejbql = null;
        //  EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();

        // Long hireType1 = employeeSearchDTO.getEmpHireTypes();
        ejbql = new StringBuilder("select o from HireTypesEntity o WHERE o.parentHireTypeCode=:hireType ");

        List<HireTypesEntity> list = null;
        if (ejbql != null)
            list = EM().createQuery(ejbql.toString()).setParameter("hireType", hiretype).getResultList();
        if (list == null || list.size() == 0)
            return listDTO;
        for (HireTypesEntity hireTypesEntity : list) {
            listDTO.add(EmpDTOFactory.createHireTypesDTO(hireTypesEntity));
        }

        return listDTO;
    }

    public List<IBasicDTO> simpleSearch(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();

        // Long hireType  employeeSearchDTO.getEmpHireTypes();

        try {
            ejbql =
                    new StringBuilder("select o from EmpCandidatesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

            if (employeeSearchDTO.getActiveFlag() != null) {
                ejbql.append(employeeSearchDTO.getActiveFlag());
            } else {
                ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
            }
            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND o.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");

            }
            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" AND o.minCode= " + employeeSearchDTO.getMinistryCode());
            }
            if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
                String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
            }
            if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName",
                                                                                 employeeSearchDTO.getWorkCenterName()) +
                             " ) ");


            ////////////////////////
            if (employeeSearchDTO.getEmpHireTypes() != null) {

                List<IBasicDTO> hireTypeList = getHireTypesAndParent(employeeSearchDTO.getEmpHireTypes());
                String StrHireCond = "";
                if (hireTypeList.size() > 0) {
                    StrHireCond = ((IHireTypesEntityKey)hireTypeList.get(0).getCode()).getHirtypeCode().toString();
                }
                for (int i = 1; i < hireTypeList.size(); i++) {
                    StrHireCond =
                            StrHireCond + "," + ((IHireTypesEntityKey)hireTypeList.get(i).getCode()).getHirtypeCode().toString();
                }
                if (hireTypeList.size() == 0) {
                    if (employeeSearchDTO.getEmpHireTypes() != null &&
                        !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE))
                        ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
                } else {
                    ejbql.append(" AND o.hireTypesEntity.hirtypeCode in (" + StrHireCond + "," +
                                 employeeSearchDTO.getEmpHireTypes() + ")");
                }


            }
            //////////////////////
            if (employeeSearchDTO.getStartWorkingDate() != null)
                ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");

            if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                             " ) ");

            //commented by mohamed sayed at 5:20 at 15-1-2015


            //            if (employeeSearchDTO.getEmpHireStage() != null &&
            //                !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            //                ejbql.append(" AND o.hirstageCode in(" + employeeSearchDTO.getEmpHireStage() + "");


            //added by mohamed sayed at 5:20 at 15-1-2015
            if (employeeSearchDTO.getEmpHireStagesList() != null) {
                StringBuffer stageCodeStr = new StringBuffer("");
                for (Long stage : employeeSearchDTO.getEmpHireStagesList()) {
                    stageCodeStr.append(stage + ",");
                }
                ejbql.append(" AND o.hirstageCode IN (" + stageCodeStr.substring(0, stageCodeStr.length() - 1) + ") ");

            }
            if (employeeSearchDTO.getEmpHireStage() != null &&
                !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE)) {
                ejbql.append(" AND o.hirstageCode in(" + employeeSearchDTO.getEmpHireStage() + ")");
            }


            if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
                ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                             " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION +
                             ")");
            if (employeeSearchDTO.getNationalityType() != null &&
                !employeeSearchDTO.getNationalityType().equals(ISystemConstant.VIRTUAL_VALUE))
                ejbql.append(" AND o.citizensResidentsEntity.nationality" +
                             ((employeeSearchDTO.getNationalityType().equals(ISystemConstant.NATIONALITY_KUWAITI)) ?
                              "=" + ISystemConstant.KUWAIT_NATIONALITY + "" :
                              ((employeeSearchDTO.getNationalityType().equals(ISystemConstant.NATIONALITY_NON_KUWAITI)) ?
                               "<>" + ISystemConstant.KUWAIT_NATIONALITY + "" : " IS NOT NULL") + "") + "");
            if (employeeSearchDTO.getGenderType() != null &&
                !employeeSearchDTO.getGenderType().equals(ISystemConstant.VIRTUAL_VALUE))
                ejbql.append(" AND o.citizensResidentsEntity.gentypeCode" +
                             ((employeeSearchDTO.getGenderType().equals(ISystemConstant.GENDER_UNDEFINED)) ?
                              " IS NOT NULL " : "=" + employeeSearchDTO.getGenderType()));
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuffer statusCodeStr = new StringBuffer("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.cndStatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");

            } else {
                if (employeeSearchDTO.getEmpHireStatus() != null)
                    ejbql.append(" AND o.cndStatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
                if (employeeSearchDTO.isEmployment())
                    ejbql.append(" AND o.cndStatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                                 IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION +
                                 "," + IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                                 IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM +
                                 "," + IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION +
                                 ") ");
            }
            if (employeeSearchDTO.getEmploymentStage() != null)
                ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage() + ")");
            //////////CH HR 762/////////////////////
            if (employeeSearchDTO.getHireDateFrom() != null)
                ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
            if (employeeSearchDTO.getHireDateTO() != null)
                ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
            if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
                ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");


            List<EmpCandidatesEntity> list = null;
            System.out.println("EmployeesFacadeBean.simpleSearch ::" + ejbql.toString());
            if (ejbql != null)
                list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
            for (EmpCandidatesEntity empCandidatesEntity : list) {
                listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return listDTO;
    }

    /**
     * get Candidates By selectedHireType and loginMin
     * @param basicDTO
     * @return
     * @auther Kareem Sayed
     */
    public List<IBasicDTO> completeDataSimpleSearch(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        StringBuilder ejbql = null;
        IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        IPagingRequestDTO requestDTO = employeeSearchDTO.getPagingRequestDTO();
        try {
            ejbql =
                    new StringBuilder("select C.* from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  " + "WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE   " +
                                      "And CNDSTATUS_CODE = 2   " + "AND HIRSTAGE_CODE NOT IN (3,4,7) " +
                                      "AND MIN_CODE = ?" + " AND ( H.HIRTYPE_CODE = ?" +
                                      " OR (PARENT_HIRTYPE_CODE = ?" +
                                      " AND EXISTS (SELECT 1 FROM HR_EMP_MIN_HIRE_TYPES MH WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE " +
                                      "AND MH.MIN_CODE = ?)) )  " + " AND H.STATUS=1 " + "AND C.ACTIVE_FLAG=1");
          
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);
                    ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }else{
                    ejbql.append(" ORDER BY C.CANDIDATE_CODE DESC");
                }
            List<EmpCandidatesEntity> list = null;
            Query q = EM().createNativeQuery(ejbql.toString(), EmpCandidatesEntity.class);
            q.setParameter(1, employeeSearchDTO.getMinistryCode());
            q.setParameter(2, employeeSearchDTO.getEmpHireTypes());
            q.setParameter(3, employeeSearchDTO.getEmpHireTypes());
            q.setParameter(4, employeeSearchDTO.getMinistryCode());
            System.out.println("EmpCandidatesFacadeBean.new complete simple Search ::::" + ejbql.toString());
          
            if (requestDTO != null) {
                q.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                q.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = q.getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
            for (EmpCandidatesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return listDTO;
    }


    public Long completeDataSimpleSearchCount(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        StringBuilder ejbql = null;
        IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        Vector list = null;
        try {
            ejbql =
                    new StringBuilder("select count(*) from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  " + "WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE   " +
                                      "And CNDSTATUS_CODE = 2   " + "AND HIRSTAGE_CODE NOT IN (3,4,7) " +
                                      "AND MIN_CODE = ?" + " AND ( H.HIRTYPE_CODE = ?" +
                                      " OR (PARENT_HIRTYPE_CODE = ?" +
                                      " AND EXISTS (SELECT 1 FROM HR_EMP_MIN_HIRE_TYPES MH WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE " +
                                      "AND MH.MIN_CODE = ?)) )  " + " AND H.STATUS=1 " + "AND C.ACTIVE_FLAG=1");
           
            Query q = EM().createNativeQuery(ejbql.toString());
            q.setParameter(1, employeeSearchDTO.getMinistryCode());
            q.setParameter(2, employeeSearchDTO.getEmpHireTypes());
            q.setParameter(3, employeeSearchDTO.getEmpHireTypes());
            q.setParameter(4, employeeSearchDTO.getMinistryCode());
            System.out.println("EmpCandidatesFacadeBean.new complete simple Search ::::" + ejbql.toString());
            list = (Vector)q.getSingleResult();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
          
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return new Long(((BigDecimal)list.get(0)).longValue());
    }
    /**
     * get Candidates By All HireType and loginMin
     * @param basicDTO
     * @return
     * @auther Kareem Sayed
     */
    public List<IBasicDTO> getAllCandidatesByAllHireType(IBasicDTO basicDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        StringBuilder ejbql = null;
        IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        IPagingRequestDTO requestDTO = employeeSearchDTO.getPagingRequestDTO();
        try {
            ejbql =
                    new StringBuilder("select C.* from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE AND C.HIRTYPE_CODE<>9" +
                                      "And CNDSTATUS_CODE = 2 AND C.ACTIVE_FLAG=1 AND HIRSTAGE_CODE NOT IN (3,4,7) AND MIN_CODE = ? " +
                                      " AND ( PARENT_HIRTYPE_CODE IS NULL   OR (PARENT_HIRTYPE_CODE IS NOT NULL AND EXISTS (SELECT 1  FROM HR_EMP_MIN_HIRE_TYPES MH " +
                                      "  WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE AND MH.MIN_CODE = ?))  )  ");
        
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);
                    ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }else{
                    ejbql.append(" ORDER BY C.CANDIDATE_CODE DESC");
                }
            List<EmpCandidatesEntity> list = null;
            Query q = EM().createNativeQuery(ejbql.toString(), EmpCandidatesEntity.class);
            q.setParameter(1, employeeSearchDTO.getMinistryCode());
            q.setParameter(2, employeeSearchDTO.getMinistryCode());
            System.out.println("EmpCandidatesFacadeBean.new complete simple Search ::::" + ejbql.toString());
          
            if (requestDTO != null) {
                q.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                q.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = q.getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();

            for (EmpCandidatesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

        return listDTO;
    }


    public Long getAllCandidatesByAllHireTypeCount(IBasicDTO basicDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        StringBuilder ejbql = null;
        IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        Vector list = null;

        try {
            ejbql =
                    new StringBuilder("select count(*) from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE AND C.HIRTYPE_CODE<>9" +
                                      "And CNDSTATUS_CODE = 2 AND C.ACTIVE_FLAG=1 AND HIRSTAGE_CODE NOT IN (3,4,7) AND MIN_CODE = ? " +
                                      " AND ( PARENT_HIRTYPE_CODE IS NULL   OR (PARENT_HIRTYPE_CODE IS NOT NULL AND EXISTS (SELECT 1  FROM HR_EMP_MIN_HIRE_TYPES MH " +
                                      "  WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE AND MH.MIN_CODE = ?))  )  ");
         
            Query q = EM().createNativeQuery(ejbql.toString());
            q.setParameter(1, employeeSearchDTO.getMinistryCode());
            q.setParameter(2, employeeSearchDTO.getMinistryCode());
            System.out.println("EmpCandidatesFacadeBean.new complete simple Search ::::" + ejbql.toString());
            list = (Vector)q.getSingleResult();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

        return new Long(((BigDecimal)list.get(0)).longValue());
    }
    /**
     * Get EmpCandidatesEntity By CandidatesCode && CandidatesSequance
     * @param id
     *  Dev Name :Ahmed Kamal
     * @return IEmpCandidatesDTO
     */
    public IEmpCandidatesDTO getByCndCodeAndSequnc(IEntityKey id1) throws DataBaseException,
                                                                          SharedApplicationException {

        IEmpCandidatesEntityKey id = (IEmpCandidatesEntityKey)id1;
        Long candCode = id.getCandidateCode();
        Long candCodeSeq = id.getCandidateCodeSeq();
        EmpCandidatesEntity empCandidatesEntity = null;
        EmpCandidatesEntity empCandidatesEntitySal = null;

        try {
            List<EmpCandidatesEntity> list =
                EM().createQuery("SELECT o FROM EmpCandidatesEntity o WHERE o.candidateCode = :candidateCode And o.candidateCodeSeq = :candidateCodeSeq").setHint("toplink.refresh",
                                                                                                                                                                  "true").setParameter("candidateCode",
                                                                                                                                                                                       candCode).setParameter("candidateCodeSeq",
                                                                                                                                                                                                              candCodeSeq).getResultList();

            List<EmpCandidatesEntity> listSal =
                EM().createQuery("SELECT o FROM EmpCandidatesEntity o WHERE o.candidateCode = :candidateCode And o.candidateCodeSeq = :candidateCodeSeq").setHint("toplink.refresh",
                                                                                                                                                                  "true").setParameter("candidateCode",
                                                                                                                                                                                       candCode).setParameter("candidateCodeSeq",
                                                                                                                                                                                                              1L).getResultList();
            if (list != null && list.size() > 0) {
                empCandidatesEntity = list.get(0);
            }
            if (listSal != null && listSal.size() > 0) {
                empCandidatesEntitySal = listSal.get(0);
            }


            if (empCandidatesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpCandidatesDTO empCandidatesDTO = EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity);

            if (empCandidatesEntity.getEmpCandidateDocumentsList() != null) {
                List<IBasicDTO> empCandidateDocumentsEntityList = new ArrayList<IBasicDTO>();
                for (EmpCandidateDocumentsEntity entity : empCandidatesEntity.getEmpCandidateDocumentsList()) {
                    empCandidateDocumentsEntityList.add(new EmpCandidateDocumentsDTO(entity));
                }
                empCandidatesDTO.setEmpCandidateDocumentsList(empCandidateDocumentsEntityList);
            }
            // Add EmployeeProcedures
            if (empCandidatesEntity.getEmpCandidateProceduresList() != null) {
                List<IBasicDTO> empcandidateProceduresEntityList = new ArrayList<IBasicDTO>();
                for (EmpCandidateProceduresEntity entity : empCandidatesEntity.getEmpCandidateProceduresList()) {
                    empcandidateProceduresEntityList.add(new EmpCandidateProceduresDTO(entity));
                }
                empCandidatesDTO.setEmpCandidateProceduresList(empcandidateProceduresEntityList);
            }
            // Add Employee Salary
            if (empCandidatesEntitySal.getEmpCndSalaryElementsList() != null) {


                List<IEmpCndSalaryElementsDTO> empCndSalaryElementsDTO = new ArrayList<IEmpCndSalaryElementsDTO>();
                for (EmpCndSalaryElementsEntity entity : empCandidatesEntitySal.getEmpCndSalaryElementsList()) {
                    if (entity.getUntilDate() == null) {
                        //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
                        empCndSalaryElementsDTO.add(EmpEntityConverter.getEmpCndSalaryElementsDTO(entity));
                    }
                }
                empCandidatesDTO.setEmpCndSalaryElementsList((List)empCndSalaryElementsDTO);
            }
            //End of add list of document and procedure

            // Add EmployeeExtraData
            List<EmpCandidateExtraDataEntity> empCandidateExtraDataEntity =
                this.getAllEmployeeExtraDataByCandCodeAndCandSeq(candCode, candCodeSeq);
            
            EmpCandidateExtraDataEntity empCandidateExtraDataExperEntity = getEmployeeExtraDataByCandCodeExper(candCode);
            if(empCandidateExtraDataExperEntity!=null){
                    empCandidateExtraDataEntity.add(empCandidateExtraDataExperEntity);
                }
            List<EmpCandidateExtraDataEntity> previousEmpCandidateExtraDataEntity=null;
            if(candCodeSeq > 1L){
                previousEmpCandidateExtraDataEntity =
                    this.getAllEmployeeExtraDataByCandCodeAndCandSeq(candCode, candCodeSeq-1);
            }
            
            empCandidatesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            empCandidatesDTO.setPreviousEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            
            if (empCandidateExtraDataEntity != null && !empCandidateExtraDataEntity.isEmpty()) {
                List<IBasicDTO> empCandidatesExtraDataDTOList = new ArrayList<IBasicDTO>();
                System.out.println("@#$ = " + empCandidateExtraDataEntity.size());
                for (EmpCandidateExtraDataEntity entity : empCandidateExtraDataEntity) {
                    empCandidatesExtraDataDTOList.add(EmpEntityConverter.getEmpCandidateExtraDataDTO(entity));
                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        empCandidatesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        empCandidatesDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        empCandidatesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        empCandidatesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        empCandidatesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());

                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(13L)) {
                        empCandidatesDTO.getEmpExtraDataValueDTO().setApprovedJob(entity.getValue());
                    }
                }
                empCandidatesDTO.setEmpCandidateExtraDataList(empCandidatesExtraDataDTOList);
            }
            
            if (previousEmpCandidateExtraDataEntity != null && !previousEmpCandidateExtraDataEntity.isEmpty()) {
                List<IBasicDTO> previousEmpCandidatesExtraDataDTOList = new ArrayList<IBasicDTO>();
                System.out.println("@#$ = " + previousEmpCandidateExtraDataEntity.size());
                for (EmpCandidateExtraDataEntity entity : previousEmpCandidateExtraDataEntity) {
                    previousEmpCandidatesExtraDataDTOList.add(EmpEntityConverter.getEmpCandidateExtraDataDTO(entity));
                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        empCandidatesDTO.getPreviousEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        empCandidatesDTO.getPreviousEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        empCandidatesDTO.getPreviousEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        empCandidatesDTO.getPreviousEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        empCandidatesDTO.getPreviousEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());

                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(13L)) {
                        empCandidatesDTO.getPreviousEmpExtraDataValueDTO().setApprovedJob(entity.getValue());
                    }
                }
                empCandidatesDTO.setPreviousEmpCandidateExtraDataList(previousEmpCandidatesExtraDataDTOList);
            }
            return empCandidatesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<EmpCandidateExtraDataEntity> getAllEmployeeExtraDataByCandCodeAndCandSeq(Long candCode,
                                                                                         Long candCodeSeq) throws DataBaseException,
                                                                                                                  SharedApplicationException {
        try {
            List<EmpCandidateExtraDataEntity> empCandidateExtraDataEntity =
                EM().createNamedQuery("EmpCandidateExtraDataEntity.getAllEmployeeExtraDataByCandCodeAndCandSeq").setParameter("candidateCode",
                                                                                                                              candCode).setParameter("candidateCodeSeq",
                                                                                                                                                     candCodeSeq).setHint("toplink.refresh",
                                                                                                                                                                          "true").getResultList();
            return empCandidateExtraDataEntity;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public EmpCandidateExtraDataEntity getEmployeeExtraDataByCandCodeExper(Long candCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            List<EmpCandidateExtraDataEntity> empCandidateExtraDataEntity =
                EM().createNamedQuery("EmpCandidateExtraDataEntity.getExtraDataByCandCodeAndCandSeqAndDatType").setParameter("candidateCode",
                                                                                                                             candCode).setParameter("candidateCodeSeq",
                                                                                                                                                    1L).setParameter("extDatTypeCode",
                                                                                                                                                                     336L).setHint("toplink.refresh",
                                                                                                                                                                                   "true").getResultList();
            if (empCandidateExtraDataEntity.size() != 0)
              return  empCandidateExtraDataEntity.get(0);


            return null;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmpCandidatesDTO> checkIfCitizenIsCandidate(Long civilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        Long cndStatusCode = 2L;
        Long activeFlag = 1L;
        try {
            List<IEmpCandidatesDTO> empCandidatesDTOList =
                EM().createNamedQuery("EmpCandidatesEntity.checkIfCitizenIsCandidate").setParameter("civilId",
                                                                                                    civilId).setParameter("cndStatusCode",
                                                                                                                          cndStatusCode).setParameter("activeFlag",
                                                                                                                                                      activeFlag).setHint("toplink.refresh",
                                                                                                                                                                          "true").getResultList();
            if (empCandidatesDTOList == null || empCandidatesDTOList.size() == 0) {
                throw new NoResultException();
            }
            return empCandidatesDTOList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * The method return boolean attribute
     * To Check if employee by dateType
     * @param civilId
     * @param extDatatype
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy M.abdelsabour
     * @updatedBy Kareem.Sayed
     */

     public boolean checkEmployeeByExtraDataType(Long civilId, Long extDatatype,Long candidateCode) throws DataBaseException,
                                                                                        SharedApplicationException {

         Long counter = 0L;
         StringBuilder query = new StringBuilder(" SELECT COUNT(1) ");
         query.append(" FROM HR_EMP_CND_SALARY_ELEMENTS S , HR_EMP_CANDIDATES C ");
         query.append(" WHERE S.CANDIDATE_CODE = " +candidateCode);
         query.append(" AND C.CIVIL_ID = " + civilId + " ");
         query.append(" AND C.ACTIVE_FLAG = 1");
         query.append(" AND EXISTS(SELECT 1");
         query.append(" FROM HR_SAL_GUIDE_EXTRA_DATA X ");
         query.append(" WHERE EXTDATTYPE_CODE = " + extDatatype);
         query.append(" AND X.ELMGUIDE_CODE = S.ELMGUIDE_CODE)");

         System.out.println(query);

         Vector v = (Vector)EM().createNativeQuery(query.toString()).getSingleResult();
         if (v != null && v.size() != 0) {
             counter = ((BigDecimal)v.get(0)).longValue();
         }
         if (counter > 0L) {
             return true;
         }
         return false;
     }
    
    /**
     * get EmpCandidates that related by التوظي�? المركزى
     * @param basicDTO
     * @return list from EmpCand
     * @throws RemoteException
     * @throws FinderException
     * @auther KareemSayed
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getAllEmpCandsRelatedByCRS(IBasicDTO basicDTO) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            IEmployeeSearchDTO employeeSearchDTO = (IEmployeeSearchDTO)basicDTO;
            IPagingRequestDTO requestDTO = employeeSearchDTO.getPagingRequestDTO();
            ejbql =
                    new StringBuilder("select C.*,(SELECT VALUE   FROM HR_EMP_CANDIDATE_EXTRA_DATA         WHERE  C.CANDIDATE_CODE = CANDIDATE_CODE AND EXTDATTYPE_CODE = 326) as dVALUE , (SELECT get_name(s.civil_id)   FROM HR_EMP_CANDIDATE_EXTRA_DATA v ,GN_SEC_USERS s  WHERE   C.CANDIDATE_CODE = v.CANDIDATE_CODE AND v.CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ and S.USER_CODE = v.LAST_UPDATED_BY  AND v.EXTDATTYPE_CODE = 13) AS userValue,   (SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = c.CANDIDATE_CODE and CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ  AND EXTDATTYPE_CODE =  14) as hasRevisionFatwa, (SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = c.CANDIDATE_CODE and CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ  AND EXTDATTYPE_CODE in (13,15)) as hasRevision from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C,INF_KWT_CITIZENS_RESIDENTS , NL_ORG_MINISTRIES  " +
                                      " WHERE C.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID and NL_ORG_MINISTRIES.MIN_CODE = C.MIN_CODE   and H.HIRTYPE_CODE = C.HIRTYPE_CODE And CNDSTATUS_CODE = 2" +
                                      " AND C.ACTIVE_FLAG=1 " + "AND HIRSTAGE_CODE = " +
                                      employeeSearchDTO.getEmpHireStages().get(0));
            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" AND C.MIN_CODE =" + employeeSearchDTO.getMinistryCode());
            }
            ejbql.append(" AND (H.HIRTYPE_CODE = 2 OR PARENT_HIRTYPE_CODE = 2) ");
            if(employeeSearchDTO.getEmpHireStages().get(0).equals("10") ){
                    ejbql.append(" AND ( C.CIVIL_ID in (select k.CIVIL_ID from  INF_KWT_WORK_DATA k)");
                    ejbql.append("or  C.CANDIDATE_CODE  in ( SELECT CANDIDATE_CODE       FROM HR_EMP_CANDIDATE_EXTRA_DATA     WHERE   EXTDATTYPE_CODE = 336 and value = 1))");
                }
            
            if(employeeSearchDTO.getCivilId() !=null){
                    ejbql.append(" AND C.CIVIL_ID= " + employeeSearchDTO.getCivilId());
                }
            
            if(employeeSearchDTO.getName() !=null && !employeeSearchDTO.getName().equals("")){
            ejbql.append(" AND c.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   employeeSearchDTO.getName()) +
                           " ) ");
            
            }
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);

                    if (column.equals("dVALUE")) {
                        ejbql.append("to_date(dVALUE , 'dd/mm/yyyy')");
                    } else
                        ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }
            List<EmpCandidatesEntity> list = null;
            Query q = EM().createNativeQuery(ejbql.toString(), EmpCandidatesEntity.class);
            if (requestDTO != null) {
                q.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                q.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            System.out.println("EmpCandidatesFacadeBean.getAllEmpCandsRelatedByCRS ::::" + ejbql.toString());
            list = q.getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmpCandidatesEntity entity : list) {
                IEmpCandidatesDTO empCandidatesDTO=EmpDTOFactory.createEmpCandidatesDTO();
          
                if (entity.getHireStagesEntity().getHirstageCode().equals(Long.valueOf(IEMPConstants.HIRE_STAGE_JOB_NAME_REQUIRED_INPROGRESS))) {
                    empCandidatesDTO=EmpEntityConverter.getEmpCandidatesDTO(entity);
                    listDTO.add(empCandidatesDTO);
                } else {
                    boolean ret = false;
                    boolean exper=false;
                    if (employeeSearchDTO.getExperienceCheck() == IEMPConstants.HAS_EXPERIENCE) {
                        try {
                            ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                            exper=isHasExper(entity.getCandidateCode());
                        } catch (Exception e) {
                        }
                        if (ret || exper) {
                            empCandidatesDTO=EmpEntityConverter.getEmpCandidatesDTO(entity);
                            listDTO.add(empCandidatesDTO);
                        }
                    } else if (employeeSearchDTO.getExperienceCheck() == IEMPConstants.HAS_NOT_EXPERIENCE) {
                        try {
                            ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getCivilId());
                            exper=isHasExper(entity.getCandidateCode());
                        } catch (Exception e) {
                        }
                        if (!ret || !exper) {
                            empCandidatesDTO=EmpEntityConverter.getEmpCandidatesDTO(entity);
                            listDTO.add(empCandidatesDTO);
                        }
                    } else {
                        empCandidatesDTO=EmpEntityConverter.getEmpCandidatesDTO(entity);
                        listDTO.add(empCandidatesDTO);
                    }
                }
                // Add EmployeeExtraData
                Long extraDataCount =
                    this.getExtraDataCountCrs(entity.getCandidateCode(),entity.getCandidateCodeSeq());
                if (extraDataCount != null && extraDataCount >=2) {
                    empCandidatesDTO.setHasRevision(true);
                }else{
                    empCandidatesDTO.setHasRevision(false);
                    }
                
                empCandidatesDTO.setUserNameValue(getUserNameValue((IEmpCandidatesEntityKey)empCandidatesDTO.getCode() , 13L));
                empCandidatesDTO.setUserNameValueFatwa(getUserNameValue((IEmpCandidatesEntityKey)empCandidatesDTO.getCode() , 14L));
            }
            List<IBasicDTO> listDTO1 = new ArrayList<IBasicDTO>();
            for (IBasicDTO empcand : listDTO) {
                IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
                IMinistriesDTO ministriesDTO = OrgDTOFactory.createMinistriesDTO();
                IMinistriesClient client = OrgClientFactory.getMinistriesClient();
                IMinistriesEntityKey eK =
                    OrgEntityKeyFactory.createMinistriesEntityKey(((IEmpCandidatesDTO)empcand).getMinCode());
                ministriesDTO = (IMinistriesDTO)client.getCodeNameByMinCode(((IEmpCandidatesDTO)empcand).getMinCode());
                workCentersDTO.setMinistriesDTO(ministriesDTO);

                ((IEmpCandidatesDTO)empcand).setWorkCentersDTO(workCentersDTO);
                empcand = ((IEmpCandidatesDTO)empcand);
                
                Long extraDataCountFatwa =
                    this.getExtraDataCount(((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCode(),((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCodeSeq(),14L);
                if (extraDataCountFatwa != null && extraDataCountFatwa >=1) {
                   ((IEmpCandidatesDTO) empcand).setHasRevisionFatwa(true);
                }else{
                     ((IEmpCandidatesDTO) empcand).setHasRevisionFatwa(false);
                    }
                
                listDTO1.add(empcand);
            }
            return listDTO1;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public String getUserNameValue(IEmpCandidatesEntityKey empEntKey , Long extraDataType) throws DataBaseException,
                                                                             SharedApplicationException {
        StringBuilder sb =
            new StringBuilder("SELECT get_name(s.civil_id)   FROM HR_EMP_CANDIDATE_EXTRA_DATA v ,GN_SEC_USERS s  WHERE   v.CANDIDATE_CODE = " +
                              empEntKey.getCandidateCode() + " AND v.CANDIDATE_CODE_SEQ = " +
                              empEntKey.getCandidateCodeSeq() +
                              " and S.USER_CODE = v.LAST_UPDATED_BY  AND v.EXTDATTYPE_CODE =  "+ extraDataType );

        Query query = EM().createNativeQuery(sb.toString());
        Vector elementValue=null;
        try{
         elementValue = (Vector)query.getSingleResult();
        }  catch(Exception e){
                return "";
            }
        String value = "";
        if (elementValue != null && elementValue.size() != 0) {
            value = elementValue.get(0).toString();
        }
        if (value != null)
            return value;
        return "";
    }
    


    /**
     * Get all Employees Candidates will related by contract
     * @param basicDTO
     * @return list from EmpCand
     * @throws RemoteException
     * @throws FinderException
     * @auther KareemSayed
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getAllEmpCandsRelatedBycontract(IBasicDTO basicDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            IEmployeeSearchDTO employeeSearchDTO = (IEmployeeSearchDTO)basicDTO;
            IPagingRequestDTO requestDTO = employeeSearchDTO.getPagingRequestDTO();
            ejbql =
                    new StringBuilder("SELECT C.* ,(SELECT VALUE FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE  C.CANDIDATE_CODE = CANDIDATE_CODE      AND EXTDATTYPE_CODE = 326) as dVALUE ,  (SELECT get_name(s.civil_id)   FROM HR_EMP_CANDIDATE_EXTRA_DATA v ,GN_SEC_USERS s  WHERE   C.CANDIDATE_CODE = v.CANDIDATE_CODE AND v.CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ and S.USER_CODE = v.LAST_UPDATED_BY  AND v.EXTDATTYPE_CODE = 13 and v.SERIAL = (SELECT max(SERIAL) from  HR_EMP_CANDIDATE_EXTRA_DATA  WHERE     C.CANDIDATE_CODE = v.CANDIDATE_CODE AND v.CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ AND v.EXTDATTYPE_CODE = 13) ) AS userValue,   (SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = c.CANDIDATE_CODE and CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ  AND EXTDATTYPE_CODE =  14) as hasRevisionFatwa, (SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = c.CANDIDATE_CODE and CANDIDATE_CODE_SEQ = c.CANDIDATE_CODE_SEQ  AND EXTDATTYPE_CODE = 13 ) as hasRevision " + " FROM HR_EMP_CND_SALARY_ELEMENTS S , HR_EMP_CANDIDATES C,INF_KWT_CITIZENS_RESIDENTS ,NL_ORG_MINISTRIES  " +
                                      " WHERE C.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID and NL_ORG_MINISTRIES.MIN_CODE = C.MIN_CODE  and S.CANDIDATE_CODE = C.CANDIDATE_CODE" +
                                      " AND C.ACTIVE_FLAG = 1" + " AND C.HIRSTAGE_CODE = " +
                                      employeeSearchDTO.getEmpHireStages().get(0));
            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" AND C.MIN_CODE =" + employeeSearchDTO.getMinistryCode());
            }
            ejbql.append(" AND EXISTS(SELECT 1 " + " FROM HR_SAL_GUIDE_EXTRA_DATA X " + " WHERE EXTDATTYPE_CODE = 6" +
                         " AND X.ELMGUIDE_CODE = S.ELMGUIDE_CODE)");
            
            
            if(employeeSearchDTO.getCivilId() !=null){
                    ejbql.append(" AND C.CIVIL_ID= " + employeeSearchDTO.getCivilId());
                }
            
            if(employeeSearchDTO.getName() !=null && !employeeSearchDTO.getName().equals("")){
            ejbql.append(" AND c.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   employeeSearchDTO.getName()) +
                           " ) ");
            
            }
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = requestDTO.getSortColumnList().get(i);
                    if (column.equals("dVALUE")) {
                        ejbql.append("to_date(dVALUE , 'dd/mm/yyyy')");
                    } else
                    ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }

            List<EmpCandidatesEntity> list = null;
            Query q = EM().createNativeQuery(ejbql.toString(), EmpCandidatesEntity.class);
            System.out.println("EmpCandidatesFacadeBean.getAllEmpCandsRelatedBycontract ::::" + ejbql.toString());
            if (requestDTO != null) {
                q.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                q.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = q.getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO1 = new ArrayList<IBasicDTO>();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmpCandidatesEntity entity : list) {
                listDTO1.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
                
            }
            for (IBasicDTO empcand : listDTO1) {
                IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
                IMinistriesDTO ministriesDTO = OrgDTOFactory.createMinistriesDTO();
                IMinistriesClient client = OrgClientFactory.getMinistriesClient();
                IMinistriesEntityKey eK =
                    OrgEntityKeyFactory.createMinistriesEntityKey(((IEmpCandidatesDTO)empcand).getMinCode());
                ministriesDTO = (IMinistriesDTO)client.getCodeNameByMinCode(((IEmpCandidatesDTO)empcand).getMinCode());
                workCentersDTO.setMinistriesDTO(ministriesDTO);

                ((IEmpCandidatesDTO)empcand).setWorkCentersDTO(workCentersDTO);
                empcand = ((IEmpCandidatesDTO)empcand);
                
                // Add EmployeeExtraData
                Long extraDataCount =
                    this.getExtraDataCount(((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCode(),((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCodeSeq(),13L);
                if (extraDataCount != null && extraDataCount >=1) {
                   ((IEmpCandidatesDTO) empcand).setHasRevision(true);
                }else{
                     ((IEmpCandidatesDTO) empcand).setHasRevision(false);
                    }
                
                Long extraDataCountFatwa =
                    this.getExtraDataCount(((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCode(),((IEmpCandidatesEntityKey)empcand.getCode()).getCandidateCodeSeq(),14L);
                if (extraDataCountFatwa != null && extraDataCountFatwa >=1) {
                   ((IEmpCandidatesDTO) empcand).setHasRevisionFatwa(true);
                }else{
                     ((IEmpCandidatesDTO) empcand).setHasRevisionFatwa(false);
                    }
              ((IEmpCandidatesDTO)  empcand).setUserNameValue(getUserNameValue((IEmpCandidatesEntityKey)empcand.getCode() , 13L));
                ((IEmpCandidatesDTO)  empcand).setUserNameValueFatwa(getUserNameValue((IEmpCandidatesEntityKey)empcand.getCode() , 14L));
                listDTO.add(empcand);
            }
            return listDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long countFileswithMinInCandidates(Long minCode, String minFileNo, Long civilId) throws DataBaseException,
                                                                                                   SharedApplicationException {
        StringBuilder st = new StringBuilder("SELECT COUNT(CIVIL_ID)");
        st.append(" FROM HR_EMP_CANDIDATES c");
        st.append(" WHERE MINISTRY_FILE_NO = '");
        st.append(minFileNo);
        st.append("' AND MIN_CODE = ");
        st.append(minCode);
        st.append(" AND c.CIVIL_ID<>");
        st.append(civilId);
        st.append(" AND CNDSTATUS_CODE = ");
        st.append(IEMPConstants.EMP_CANDIDATE_HIRE_TYPE);
        st.append(" AND ACTIVE_FLAG = ");
        st.append(ISystemConstant.ACTIVE_FLAG);
        System.out.println("countFileswithMinInCandidates :: " + st);
        Query q = EM().createNativeQuery(st.toString());

        Vector count = (Vector)q.getSingleResult();
        if (count != null && !count.isEmpty()) {
            return new Long(((BigDecimal)count.get(0)).longValue());
        }
        return null;
    }

    @Override
    protected int getSearchTotalRowsCount(IBasicDTO dto) throws DataBaseException, SharedApplicationException {
        try {
            IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)dto;
            Long hireType = employeeSearchDTO.getEmpHireTypes();
            Query query;
            if (((Long)0L).equals(hireType)) {
                String sql =
                    "select COUNT(*) from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE " +
                    "And CNDSTATUS_CODE = 2 AND C.ACTIVE_FLAG=1 AND HIRSTAGE_CODE NOT IN (3,4,5,7) AND MIN_CODE = ? " +
                    " AND ( PARENT_HIRTYPE_CODE IS NULL OR (PARENT_HIRTYPE_CODE IS NOT NULL AND EXISTS (SELECT 1  FROM HR_EMP_MIN_HIRE_TYPES MH " +
                    "  WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE AND MH.MIN_CODE = ?))  )  ";
                query = EM().createNativeQuery(sql);
                query.setParameter(1, employeeSearchDTO.getMinistryCode());
                query.setParameter(2, employeeSearchDTO.getMinistryCode());
            } else {
                String sql =
                    "select count(*) from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  " + "WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE   " +
                    "And CNDSTATUS_CODE = 2   " + "AND HIRSTAGE_CODE NOT IN (3,4,5,7) " + "AND MIN_CODE = ?" +
                    " AND ( H.HIRTYPE_CODE = ?" + " OR (PARENT_HIRTYPE_CODE = ?" +
                    " AND EXISTS (SELECT 1 FROM HR_EMP_MIN_HIRE_TYPES MH WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE " +
                    "AND MH.MIN_CODE = ?)) )  " + " AND H.STATUS=1 " + "AND C.ACTIVE_FLAG=1";
                query = EM().createNativeQuery(sql);
                query.setParameter(1, employeeSearchDTO.getMinistryCode());
                query.setParameter(2, employeeSearchDTO.getEmpHireTypes());
                query.setParameter(3, employeeSearchDTO.getEmpHireTypes());
                query.setParameter(4, employeeSearchDTO.getMinistryCode());
            }
            Vector result = (Vector)query.getSingleResult();
            if (result != null && result.size() == 1) {
                return Integer.valueOf(result.get(0).toString());
            }
            return 0;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    @Override
    protected List getSearchResult(int firstRow, int pageSize, List<IPagingSortPropertyDTO> sortProperties,
                                   IBasicDTO dto) throws DataBaseException, SharedApplicationException {
        try {
            IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)dto;
            Long hireType = employeeSearchDTO.getEmpHireTypes();
            Query query;
            if (((Long)0L).equals(hireType)) {
                String sql =
                    "select C.* from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE " +
                    "And CNDSTATUS_CODE = 2 AND C.ACTIVE_FLAG=1 AND HIRSTAGE_CODE NOT IN (3,4,5,7) AND MIN_CODE = ? " +
                    " AND ( PARENT_HIRTYPE_CODE IS NULL OR (PARENT_HIRTYPE_CODE IS NOT NULL AND EXISTS (SELECT 1  FROM HR_EMP_MIN_HIRE_TYPES MH " +
                    "  WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE AND MH.MIN_CODE = ?))  )  ";
                query = EM().createNativeQuery(sql, EmpCandidatesEntity.class);
                query.setParameter(1, employeeSearchDTO.getMinistryCode());
                query.setParameter(2, employeeSearchDTO.getMinistryCode());
            } else {
                String sql =
                    "select C.* from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  " + "WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE   " +
                    "And CNDSTATUS_CODE = 2   " + "AND HIRSTAGE_CODE NOT IN (3,4,5,7) " + "AND MIN_CODE = ?" +
                    " AND ( H.HIRTYPE_CODE = ?" + " OR (PARENT_HIRTYPE_CODE = ?" +
                    " AND EXISTS (SELECT 1 FROM HR_EMP_MIN_HIRE_TYPES MH WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE " +
                    "AND MH.MIN_CODE = ?)) )  " + " AND H.STATUS=1 " + "AND C.ACTIVE_FLAG=1";
                query = EM().createNativeQuery(sql, EmpCandidatesEntity.class);
                query.setParameter(1, employeeSearchDTO.getMinistryCode());
                query.setParameter(2, employeeSearchDTO.getEmpHireTypes());
                query.setParameter(3, employeeSearchDTO.getEmpHireTypes());
                query.setParameter(4, employeeSearchDTO.getMinistryCode());
            }
            query.setFirstResult(firstRow);
            query.setMaxResults(pageSize);
            List<EmpCandidatesEntity> entList = query.getResultList();
            if (entList == null || entList.size() <= 0) {
                throw new NoResultException();
            }
            List<IEmpCandidatesDTO> dtoList = new ArrayList<IEmpCandidatesDTO>();
            for (EmpCandidatesEntity ent : entList) {
                dtoList.add(EmpEntityConverter.getEmpCandidatesDTO(ent));
            }
            return (List)dtoList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /// added by M.abdelsabour to get All by all hire types شاشة تن�?يذ مباشرة العمل


    public List<IBasicDTO> filterByAllHireTypeForHireExecute(Long loggedMin) throws DataBaseException,
                                                                                    SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {

            StringBuilder sql = new StringBuilder(" select C.*  ");
            sql.append(" from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C  ");
            sql.append(" WHERE H.HIRTYPE_CODE = C.HIRTYPE_CODE   AND C.HIRTYPE_CODE <>9 ");
            sql.append("  And CNDSTATUS_CODE = 2 ");
            sql.append("  AND C.ACTIVE_FLAG = 1 ");
            sql.append("  AND HIRSTAGE_CODE = 7  ");
            sql.append("  AND MIN_CODE =  " + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + " ");
            sql.append("  AND (  ");
            sql.append("  PARENT_HIRTYPE_CODE IS NULL OR ( ");
            sql.append("  PARENT_HIRTYPE_CODE IS NOT NULL AND EXISTS ( ");
            sql.append("  SELECT 1   ");
            sql.append("  FROM HR_EMP_MIN_HIRE_TYPES MH  ");
            sql.append("  WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE AND MH.MIN_CODE = " + loggedMin + " )) ) ");

            System.out.println(sql);


            String s = sql.toString();
            Query q = EM().createNativeQuery(s, EmpCandidatesEntity.class);
            List<EmpCandidatesEntity> list = q.getResultList();


            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return arrayList;
    }


    // added by M.abdelsabour to perform search in page search by code or name

    public List<IBasicDTO> searchForHireExecute(Long loggedMin, Long civilId, String name,
                                                Long hireTypeKey) throws DataBaseException,
                                                                         SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {

            StringBuilder strsql = new StringBuilder("select c.* from hr_emp_candidates c join ");
            strsql.append("       basc_owner.inf_kwt_citizens_residents i ");
            strsql.append("       on c.civil_id=i.civil_id ");
            if (civilId != null) {
                strsql.append(" and C.CIVIL_ID = " + civilId + " ");
            }
            // search by name
            if (name != null && !name.equals("")) {

                strsql.append(" AND ( I.FIRST_NAME like  '%" + name + "%' OR ");
                strsql.append(" I.SECOND_NAME  like  '%" + name + "%' OR ");
                strsql.append(" I.THIRD_NAME like  '%" + name + "%' OR  ");
                strsql.append(" I.FAMILY_NAME like  '%" + name + "%' OR ");
                strsql.append(" I.Last_NAME like  '%" + name + "%' )");
            }
            strsql.append("   AND cndstatus_code = 2 ");
            strsql.append("   AND c.active_flag = 1 ");
            strsql.append("   AND hirstage_code = 7 ");
            strsql.append("  AND MIN_CODE =  " + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + " ");
            if (hireTypeKey != null && !hireTypeKey.equals(0L)) {
                strsql.append("   left join hr_emp_min_hire_types h ");
                strsql.append("   on h.hirtype_code=c.hirtype_code ");
                strsql.append("   left join hr_emp_hire_types hemp ");
                strsql.append("   on  hemp.hirtype_code=c.hirtype_code ");
                strsql.append("   where ");
                strsql.append("   (parent_hirtype_code IS NULL and  hemp.hirtype_code =" + hireTypeKey +
                              ") or  parent_hirtype_code =" + hireTypeKey);
            }
            //
            //
            //            StringBuilder sql = new StringBuilder(" select C.*  ");
            //            sql.append(" from HR_EMP_HIRE_TYPES H , HR_EMP_CANDIDATES C ,BASC_OWNER.INF_KWT_CITIZENS_RESIDENTS I where  C.CIVIL_ID =  I.CIVIL_ID   ");
            //
            //            // search by Code
            //            if (civilId != null) {
            //
            //                sql.append(" and C.CIVIL_ID = " + civilId + " ");
            //
            //
            //            }
            //
            //            // search by name
            //            if (name != null && !name.equals("")) {
            //
            //                sql.append(" AND ( I.FIRST_NAME like  '%" + name + "%' OR ");
            //                sql.append(" I.SECOND_NAME  like  '%" + name + "%' OR ");
            //                sql.append(" I.THIRD_NAME like  '%" + name + "%'OR  ");
            //                sql.append(" I.FAMILY_NAME like  '%" + name + "%' )");
            //
            //
            //
            //
            //            }
            //            sql.append("  AND H.HIRTYPE_CODE = C.HIRTYPE_CODE ");
            //            sql.append("  And CNDSTATUS_CODE = 2 ");
            //            sql.append("  AND C.ACTIVE_FLAG = 1 ");
            //            sql.append("  AND HIRSTAGE_CODE = 7  ");
            //            sql.append("  AND MIN_CODE =  " + loggedMin + " ");
            //
            //            if (hireTypeKey != null && !hireTypeKey.equals(0L)) {
            //                sql.append("  AND (  ");
            //                sql.append("  PARENT_HIRTYPE_CODE IS NULL OR ( ");
            //                sql.append("  PARENT_HIRTYPE_CODE IS NOT NULL AND EXISTS ( ");
            //                sql.append("  SELECT 1   ");
            //                sql.append("  FROM HR_EMP_MIN_HIRE_TYPES MH  ");
            //                sql.append("  WHERE  H.hirtype_code = mh.hirtype_code and H.HIRTYPE_CODE = " + hireTypeKey + "  AND MH.MIN_CODE = " + loggedMin + " )) ) ");
            //
            //
            //            }
            System.out.println(strsql);


            String s = strsql.toString();
            Query q = EM().createNativeQuery(s, EmpCandidatesEntity.class);
            List<EmpCandidatesEntity> list = q.getResultList();


            for (EmpCandidatesEntity empCandidatesEntity : list) {
                arrayList.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return arrayList;
    }


    // added by M.abdelsabour to apply search in add and follow employee screen

    public List<IBasicDTO> searchForSpecificEmployeeByLoggedMin(IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {
        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();

        try {
            ejbql =
                    new StringBuilder("select o from EmpCandidatesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

            ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);

            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND o.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");

            }
            if (employeeSearchDTO.getLoginMinistry() != null) {
                ejbql.append(" AND o.minCode= " + employeeSearchDTO.getLoginMinistry());
            }


            ////////////////////////
            if (employeeSearchDTO.getHireTypeFromDropList() != null &&
                !employeeSearchDTO.getHireTypeFromDropList().equals(0L)) {
                List<IBasicDTO> hireTypeList = getHireTypesAndParent(employeeSearchDTO.getHireTypeFromDropList());
                String StrHireCond = "";
                if (hireTypeList.size() > 0) {
                    StrHireCond = ((IHireTypesEntityKey)hireTypeList.get(0).getCode()).getHirtypeCode().toString();
                }
                for (int i = 1; i < hireTypeList.size(); i++) {
                    StrHireCond =
                            StrHireCond + "," + ((IHireTypesEntityKey)hireTypeList.get(i).getCode()).getHirtypeCode().toString();
                }
                if (hireTypeList.size() == 0) {
                    if (employeeSearchDTO.getHireTypeFromDropList() != null &&
                        !employeeSearchDTO.getHireTypeFromDropList().equals(ISystemConstant.VIRTUAL_VALUE))
                        ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" +
                                     employeeSearchDTO.getHireTypeFromDropList() + "");
                } else {
                    ejbql.append(" AND o.hireTypesEntity.hirtypeCode in (" + StrHireCond + "," +
                                 employeeSearchDTO.getHireTypeFromDropList() + ")");
                }
            }
            //////////////////////


            //added by mohamed sayed at 5:20 at 15-1-2015
            if (employeeSearchDTO.getExecludedEmpHireStagesList() != null) {
                StringBuffer stageCodeStr = new StringBuffer("");
                for (Long stage : employeeSearchDTO.getExecludedEmpHireStagesList()) {
                    stageCodeStr.append(stage + ",");
                }
                ejbql.append(" AND o.hirstageCode NOT IN (" + stageCodeStr.substring(0, stageCodeStr.length() - 1) +
                             ") ");

            }


            ejbql.append(" AND o.cndStatusCode = 2L ");


            //////////CH HR 762/////////////////////
            if (employeeSearchDTO.getHireDateFrom() != null)
                ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
            if (employeeSearchDTO.getHireDateTO() != null)
                ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");


            List<EmpCandidatesEntity> list = null;
            System.out.println("EmpCandidateDAO.searchForSpecificEmployeeByLoggedMin ::" + ejbql.toString());
            if (ejbql != null)
                list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();
            for (EmpCandidatesEntity empCandidatesEntity : list) {
                listDTO.add(EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity));
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return listDTO;
    }

    /**
     * Hany Omar
     * @param dto
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean addNewEmpVacBalance(IEmpCandidatesDTO dto) throws DataBaseException, SharedApplicationException {
        CallableStatement stm = null;
        String strResult = null;
        try {
            IEmployeesEntityKey empEK = (IEmployeesEntityKey)dto.getEmployeesDTO().getCode();
            final String QUERY = "begin ? := hr_vac_pack.add_new_emp_vac_balance (?,?,?);end;";
            // updated by A.AGAMY for data audit
            stm = getConnectionForUpdate().prepareCall(QUERY);
            stm.registerOutParameter(1, OracleTypes.VARCHAR);
            stm.setLong(2, empEK.getCivilId());
            stm.setLong(3, dto.getMinCode());
            stm.setDate(4, dto.getHireDate());

            System.out.println(QUERY);
            System.out.println("first param .... " + empEK.getCivilId());
            System.out.println("second param .... " + dto.getMinCode());
            System.out.println("third param .... " + dto.getHireDate());
            stm.executeUpdate();
            System.out.println(stm.getObject(1));
            strResult = stm.getObject(1) != null ? stm.getObject(1).toString() : "-l";
            System.out.println(strResult);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new DataBaseException(e.getMessage());
        } finally {
            close(stm);
        }
        if (strResult.equals("1"))
            return Boolean.TRUE;
        throw new VacInsertionException();
    }

    /**
     * Hany Omar
     * @param dto
     * @param elementGuide
     * @param eqDegreeCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */

    public Boolean executeFinanciaPackage(IEmpCandidatesDTO dto, Long elementGuide,
                                          Long eqDegreeCode) throws DataBaseException, SharedApplicationException {

        CallableStatement stm = null;
        String strResult = null;
        try {


            IEmployeesEntityKey empEK = (IEmployeesEntityKey)dto.getEmployeesDTO().getCode();
            final String QUERY =
                "begin ? := CSFN_OWNER.FINANCIAL_PACKAGE.SET_EMP_DEGREE(" + empEK.getCivilId() + "," + elementGuide +
                "," + "TO_DATE('" + dto.getStartWorkingDate() + "' ,'yyyy/MM/dd') ," + eqDegreeCode + "); end;";

            //            SimpleDateFormat date = new SimpleDateFormat("");
            stm = getConnectionForUpdate().prepareCall(QUERY);
            stm.registerOutParameter(1, OracleTypes.NUMBER);
            //            stm.setLong(2, empEK.getCivilId());
            //            stm.setLong(3, elementGuide);
            //            stm.setDate(4, dto.getHireDate());
            System.out.println(QUERY);
            //            if (eqDegreeCode != null) {
            //                stm.setLong(5, eqDegreeCode);
            //            }else{
            //                stm.setString(5, null);
            //            }
            stm.executeUpdate();
            strResult = stm.getObject(1).toString();
            System.out.println("function return  --------------->> " + strResult);
            if (strResult.equals("1"))
                return Boolean.TRUE;
            throw new SalElementInsertionException();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new DataBaseException(e.getMessage());
        } finally {
            close(stm);
        }

    }


    public String getElementValue(Long civilId) throws DataBaseException, SharedApplicationException {
        StringBuilder sb = new StringBuilder("SELECT  G.VALUE ");
        sb.append(" FROM HR_EMP_CANDIDATES C, HR_EMP_CND_SALARY_ELEMENTS E, HR_SAL_ELEMENT_GUIDES G ");
        sb.append(" WHERE C.CIVIL_ID = ");
        sb.append(civilId);
        sb.append(" AND C.CANDIDATE_CODE = E.CANDIDATE_CODE ");
        sb.append(" AND C.CANDIDATE_CODE_SEQ = E.CANDIDATE_CODE_SEQ ");
        sb.append(" AND E.ELMGUIDE_CODE = G.ELMGUIDE_CODE");

        Query query = EM().createNativeQuery(sb.toString());
        Vector elementValue = (Vector)query.getSingleResult();
        String value = "0";
        if (elementValue != null && elementValue.size() != 0) {
            value = ((BigDecimal)elementValue.get(0)).toString();
        }
        if (value != null)
            return value;
        return "0";
    }

    /**
     *
     * @param empCandDTOList
     * @return  List<IBasicDTO>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * author Mohamed Sayed
     */
    public List<IBasicDTO> getOtherCandByCivils(List<IBasicDTO> empCandDTOList) throws DataBaseException,
                                                                                       SharedApplicationException {
        String strCand;
        String strCivil;
        if (empCandDTOList.size() == 0)
            return null;
        strCand = ((IEmpCandidatesEntityKey)empCandDTOList.get(0).getCode()).getCandidateCode().toString();
        strCivil =
                ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)empCandDTOList.get(0)).getCitizensResidentsDTO()).getCivilId().toString() +
                "L";
        for (int i = 1; i < empCandDTOList.size(); i++) {
            strCand =
                    strCand + "," + ((IEmpCandidatesEntityKey)(empCandDTOList.get(i)).getCode()).getCandidateCode().toString();
            strCivil =
                    strCivil + "," + ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)empCandDTOList.get(i)).getCitizensResidentsDTO()).getCivilId().toString() +
                    "L";
        }
        try {

            String Strsql =
                "SELECT o FROM EmpCandidatesEntity o WHERE o.activeFlag=1 AND o.hirstageCode NOT IN (3,4,5,7) AND o.citizensResidentsEntity.civilId IN (" +
                strCivil + ")  AND o.candidateCode NOT IN (" + strCand + ") ";

            List<IBasicDTO> listResult = new ArrayList();
            List<EmpCandidatesEntity> list =
                EM().createQuery(Strsql).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0) {
                return listResult;
            }

            for (EmpCandidatesEntity empCandidatesEntity : list) {
                IEmpCandidatesDTO empCandidatesDTO = EmpEntityConverter.getEmpCandidatesDTO(empCandidatesEntity);
                listResult.add(empCandidatesDTO);
            }
            return listResult;
        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public boolean removeSeekerFromCrswhenHire(List<IBasicDTO> empCandDTOList) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            String strCivil, strExecQry;
            strCivil =
                    ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)empCandDTOList.get(0)).getCitizensResidentsDTO()).getCivilId().toString();
            for (int i = 1; i < empCandDTOList.size(); i++) {
                strCivil =
                        strCivil + "," + ((IKwtCitizensResidentsDTO)((IEmpCandidatesDTO)empCandDTOList.get(i)).getCitizensResidentsDTO()).getCivilId().toString();
            }
            strExecQry =
                    "UPDATE HR_CRS_JOB_SEEKERS " + "SET REGSTATUS_CODE = 3 " + "WHERE CIVIL_ID in ( " + "    SELECT CIVIL_ID " +
                    "    FROM HR_CRS_JOB_SEEKERS " + "    WHERE CIVIL_ID in ( " + strCivil + ")" +
                    "    AND REGSTATUS_CODE IN ( 0 , 1) " + ")";
            // updated by A.AGAMY for data audit
            //            EntityManagerImpl emImpl = (EntityManagerImpl)(EM()).getDelegate();
            //            UnitOfWorkImpl uofw = (UnitOfWorkImpl)emImpl.getActiveSession();
            //            ClientSession session = (ClientSession)uofw.getParent();
            Connection connection = getConnectionForUpdate(); //session.getAccessor().getConnection();
            CallableStatement cstmt = connection.prepareCall(strExecQry);
            System.out.println(strExecQry.toString());
            Boolean execute = cstmt.execute();

            return execute;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    public Boolean checkGrsConditions(Long conditionCode, Long civilID) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            //Connection connection = DBSharedUtils.getConnection();
            String strqry = " select GN_GRS_CHK_PAC.CHK_COND(" + conditionCode + "," + civilID + ") from dual ";
            Query query = EM().createNativeQuery(strqry);
            Object obj = query.getSingleResult();
            if (obj != null) {
                Vector v = (Vector)obj;
                int ret = Integer.parseInt(v.get(0).toString());
                if (ret == 1)
                    return true;
            }

            return false;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    // new added by M.abdelsabour to check employee ع بند الإستعانة

    public boolean checkEmployeeInNeed(Long civilId) throws DataBaseException, SharedApplicationException {

        ///    checkEmployeeInNeed
        Long counter = 0L;

        StringBuilder query = new StringBuilder(" SELECT COUNT(1) ");
        query.append(" FROM HR_EMP_CND_SALARY_ELEMENTS S , HR_EMP_CANDIDATES C ");
        query.append(" WHERE S.CANDIDATE_CODE = C.CANDIDATE_CODE ");
        //No need to check for the sequence as it may change with the state of the employee
        //Changed by Ayman
        //query.append(" AND S.CANDIDATE_CODE_SEQ = C.CANDIDATE_CODE_SEQ ");
        query.append(" AND C.CIVIL_ID = " + civilId + " ");
        query.append(" AND C.ACTIVE_FLAG = 1");
        query.append(" AND EXISTS(SELECT 1");
        query.append(" FROM HR_SAL_GUIDE_EXTRA_DATA X ");
        query.append(" WHERE EXTDATTYPE_CODE = 7 ");
        query.append(" AND X.ELMGUIDE_CODE = S.ELMGUIDE_CODE)");

        System.out.println(query);

        Vector v = (Vector)EM().createNativeQuery(query.toString()).getSingleResult();
        if (v != null && v.size() != 0) {
            counter = ((BigDecimal)v.get(0)).longValue();
        }
        if (counter > 0L) {
            return true;
        }
        return false;
    }


    public List getAllRequests(IEmployeeSearchDTO searchDTO) throws DataBaseException, SharedApplicationException {

        List<IBasicDTO> resultList = new ArrayList();

        StringBuilder sqlQuery = new StringBuilder("SELECT C.* ");
        sqlQuery.append(" FROM HR_EMP_CND_SALARY_ELEMENTS S, HR_EMP_CANDIDATES C ");
        sqlQuery.append(" WHERE S.CANDIDATE_CODE = C.CANDIDATE_CODE ");
        sqlQuery.append(" AND S.CANDIDATE_CODE_SEQ =  ");
        sqlQuery.append(IEMPConstants.CND_SEQ_ONE);
        sqlQuery.append(" AND C.ACTIVE_FLAG = ");
        sqlQuery.append(ISystemConstant.ACTIVE_FLAG);
        sqlQuery.append(" AND C.HIRSTAGE_CODE = ");
        sqlQuery.append(IEMPConstants.HIRE_STAGE_REVIEW_ORDER_IN_SELECTION_INPROGRESS);
        if (searchDTO.getMinistryCode() != null) {
            sqlQuery.append(" AND MIN_CODE = ");
            sqlQuery.append(searchDTO.getMinistryCode());
        }
        if (searchDTO.getCivilId() != null) {
            sqlQuery.append(" AND c.civil_id = ");
            sqlQuery.append(searchDTO.getCivilId());
        }
        if(searchDTO.getName() !=null && !searchDTO.getName().equals("")){
        sqlQuery.append(" AND c.CIVIL_ID IN (Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                       QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                               searchDTO.getName()) +
                       " ) ");
        
        }
        sqlQuery.append(" AND EXISTS ");
        sqlQuery.append("(SELECT 1 FROM HR_SAL_GUIDE_EXTRA_DATA X WHERE EXTDATTYPE_CODE = ");
        sqlQuery.append(IEMPConstants.ESTANA_TYPE);
        sqlQuery.append("AND X.ELMGUIDE_CODE = S.ELMGUIDE_CODE)");

        String finalQuery = sqlQuery.toString();
        System.out.println(finalQuery);

        Query query = EM().createNativeQuery(finalQuery, EmpCandidatesEntity.class);
        List<EmpCandidatesEntity> cndRequestsList = query.getResultList();

        for (EmpCandidatesEntity entity : cndRequestsList) {
            resultList.add(EmpEntityConverter.getEmpCandidatesDTO(entity));
        }
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (IBasicDTO empcand : resultList) {
            IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
            IMinistriesDTO ministriesDTO = OrgDTOFactory.createMinistriesDTO();
            IMinistriesClient client = OrgClientFactory.getMinistriesClient();
            IMinistriesEntityKey eK =
                OrgEntityKeyFactory.createMinistriesEntityKey(((IEmpCandidatesDTO)empcand).getMinCode());
            ministriesDTO = (IMinistriesDTO)client.getCodeNameByMinCode(((IEmpCandidatesDTO)empcand).getMinCode());
            workCentersDTO.setMinistriesDTO(ministriesDTO);

            ((IEmpCandidatesDTO)empcand).setWorkCentersDTO(workCentersDTO);
            empcand = ((IEmpCandidatesDTO)empcand);
            listDTO.add(empcand);
        }
        return listDTO;
    }

    public Long countCitizensRegisteredOnQual(Long qualificationKey) throws DataBaseException,
                                                                            SharedApplicationException {

        StringBuilder strQuery = new StringBuilder();

        strQuery.append(" SELECT COUNT(1) ");
        strQuery.append(" FROM HR_CRS_JOB_SEEKERS S , INF_PERSON_QUALIFICATIONS Q ");
        strQuery.append(" WHERE S.CIVIL_ID = Q.CIVIL_ID ");
        strQuery.append(" AND S.REGSTATUS_CODE = ");
        strQuery.append(IEMPConstants.REG_STATUS_ZERO);
        strQuery.append(" AND Q.QUALIFICATION_KEY = ");
        strQuery.append(qualificationKey);

        String finalQuery = strQuery.toString();
        System.out.println(finalQuery);

        Query query = EM().createNativeQuery(finalQuery);
        Vector vector = (Vector)query.getSingleResult();

        String result = vector.get(0).toString();
        Long count = Long.parseLong(result);
        return count;
    }
    //////////////////////////////////////////added by mohamed sayed at 13/10/2015 to call attendence identity -----------------------------------------------

    /**

     * @author Black Horse [m.sayed]
     * @since 13/10/2015
     * @Prameter minCode
     * @Prameter civilId
     * @parameter employmentDate
     */

    public Boolean reActiveEmpSignIdProcedure(Long minCode, Long civilId,
                                              Date employmentDate) throws DataBaseException,
                                                                          SharedApplicationException {

        final String SQL = "call HR_ATT_PROCESSES_PACK.REACTIVE_EMP_SIGN_IDEN(?,?,?)";
        System.out.println(SQL);
        CallableStatement stm = null;
        try {
            // updated by A.AGAMY for data audit
            Connection con = getConnectionForUpdate();
            stm = con.prepareCall(SQL);
            stm.setLong(1, minCode);
            stm.setLong(2, civilId);
            stm.setDate(3, employmentDate);
            stm.executeUpdate();

            System.out.println("min code" + minCode);
            System.out.println("CivilId" + civilId);
            System.out.println("employment Date " + employmentDate.toString());

            System.out.println("statment is" + SQL);

            return Boolean.TRUE;


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {

                throw (DataBaseException)e;

            } else {
                throw (SharedApplicationException)e;

            }
        } finally {
            BaseDAO.close(stm);
        }
    }

    public Long getCandidateCode(Long civilId) throws DataBaseException, SharedApplicationException {
        Long candidateCode = 0L;
        try {

            StringBuilder queryStr = new StringBuilder("select CANDIDATE_CODE\n" +
                    "from HR_EMP_CANDIDATES \n" +
                    "where  CNDSTATUS_CODE = 1\n" +
                    "and HIRSTAGE_CODE  = 3\n" +
                    "and ACTIVE_FLAG = 1\n" +
                    "and civil_id = ");
            queryStr.append(civilId);
            queryStr.append("and\n" +
                    "hire_date in(select MAX(HIRE_DATE)\n" +
                    "from HR_EMP_CANDIDATES \n" +
                    "where  CNDSTATUS_CODE = 1\n" +
                    "and HIRSTAGE_CODE  = 3\n" +
                    "and ACTIVE_FLAG = 1\n" +
                    "and civil_id =  ");
            queryStr.append(civilId);
            queryStr.append(")");


            Query query = EM().createNativeQuery(queryStr.toString());
            Vector count = (Vector)query.getSingleResult();
            if ((BigDecimal)count.get(0) != null) {
                candidateCode = ((BigDecimal)count.get(0)).longValue();
            }

            return candidateCode;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    
    
    public Long getHirDateCount(Long candidateCode) throws DataBaseException, SharedApplicationException {
      Long countResult = 1L;
        try {

            StringBuilder queryStr = new StringBuilder(" SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = ");
            queryStr.append(candidateCode);
            queryStr.append(" AND EXTDATTYPE_CODE = 325 AND VALUE = '1'  ");

            System.out.println("getHirDateCount >>>>>>>>>>> "+ queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString());
            Vector count = (Vector)query.getSingleResult();
            if ((BigDecimal)count.get(0) != null) {
                countResult = ((BigDecimal)count.get(0)).longValue();
            }

            return countResult;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public Long getExtraDataCount(Long candidateCode,Long candSeq,Long extraDataCode) throws DataBaseException, SharedApplicationException {
      Long countResult = 1L;
        try {

            StringBuilder queryStr = new StringBuilder(" SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = ");
            queryStr.append(candidateCode);
            queryStr.append( " and CANDIDATE_CODE_SEQ =" +candSeq);
            queryStr.append(" AND EXTDATTYPE_CODE =  " + extraDataCode );

            System.out.println("getHirDateCount >>>>>>>>>>> "+ queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString());
            Vector count = (Vector)query.getSingleResult();
            if ((BigDecimal)count.get(0) != null) {
                countResult = ((BigDecimal)count.get(0)).longValue();
            }

            return countResult;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public Long getExtraDataCountCrs(Long candidateCode,Long candSeq) throws DataBaseException, SharedApplicationException {
      Long countResult = 1L;
        try {

            StringBuilder queryStr = new StringBuilder(" SELECT COUNT(*) FROM HR_EMP_CANDIDATE_EXTRA_DATA WHERE CANDIDATE_CODE = ");
            queryStr.append(candidateCode);
            queryStr.append( " and CANDIDATE_CODE_SEQ =" +candSeq);
            queryStr.append(" AND EXTDATTYPE_CODE in (13,15)  ");

            System.out.println("getHirDateCount >>>>>>>>>>> "+ queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString());
            Vector count = (Vector)query.getSingleResult();
            if ((BigDecimal)count.get(0) != null) {
                countResult = ((BigDecimal)count.get(0)).longValue();
            }

            return countResult;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public List<IBasicDTO> getAllCandidateWithPaging(IBasicDTO searchDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            IEmpReqEnquiryDTO candSearchDTO = (IEmpReqEnquiryDTO)searchDTO;
            IPagingRequestDTO requestDTO = ((IPagingRequestDTO)candSearchDTO.getPagingRequestDTO());
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            StringBuilder queryStr =
                new StringBuilder("select * from ( SELECT CIVIL_ID , GET_NAME(CIVIL_ID), C.MIN_CODE ,M.MIN_NAME , C.HIRTYPE_CODE , HT.HIRTYPE_NAME, C.HIRSTAGE_CODE ,G.HIRSTAGE_NAME , C.TRANS_REQ_DATE" ); 
                queryStr.append(" , HR_EMP_PAC.GET_CANDIDATE_DEPT(CIVIL_ID) REQ_DEPT ,c.CANDIDATE_CODE ,c.CANDIDATE_CODE_SEQ" ); 
                queryStr.append(" FROM HR_EMP_CANDIDATES C , HR_EMP_HIRE_STAGES G , HR_EMP_HIRE_TYPES HT , NL_ORG_MINISTRIES M " ); 
                queryStr.append(" WHERE C.HIRSTAGE_CODE = G.HIRSTAGE_CODE " ); 
                queryStr.append(" AND C.HIRTYPE_CODE = HT.HIRTYPE_CODE " ); 
                queryStr.append(" AND C.MIN_CODE = M.MIN_CODE " ); 
                queryStr.append(" AND C.CNDSTATUS_CODE = 2 " ); 
                queryStr.append(" AND C.ACTIVE_FLAG = 1 " ); 
                if(candSearchDTO.getMinCode() != null){
                    queryStr.append(" AND C.MIN_CODE = ").append(candSearchDTO.getMinCode());
                }
            if(candSearchDTO.getRealCivilId() !=null){
                queryStr.append(" and c.civil_id = " + candSearchDTO.getRealCivilId());
            }
            
            if(candSearchDTO.getName() !=null && !candSearchDTO.getName().equals("")){
                queryStr.append(" and GET_NAME (CIVIL_ID) like '%" + candSearchDTO.getName() +"%'");
            }
                queryStr.append(" AND C.HIRSTAGE_CODE NOT IN (3,4,19)  ");
            
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {

                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    if (column != null) {
                        queryStr.append(" ORDER BY ");
                        queryStr.append(column);
                        if (!requestDTO.isSortAscending()) {
                            queryStr.append(" DESC");
                        }
                        if (i < requestDTO.getSortColumnList().size() - 1) {
                            queryStr.append(" , ");
                        }
                    }
                }
            }
            queryStr.append(" )");
            if(candSearchDTO.getConcernedMinCode() != null){
                queryStr.append(" where REQ_DEPT = ").append(candSearchDTO.getConcernedMinCode());
            }
            Query query = EM().createNativeQuery(queryStr.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            List<Vector> list = query.setHint("toplink.refresh", "true").getResultList();
            
            IEmpReqEnquiryDTO empReqEnquiryDTO = null;
            for (Vector row : list) {
                empReqEnquiryDTO = new EmpReqEnquiryDTO();
                empReqEnquiryDTO.setRealCivilId((row.get(0) != null) ? ((BigDecimal)row.get(0)).longValue() : null);
                empReqEnquiryDTO.setFullName((row.get(1) != null) ? ((String)row.get(1)) : null);
                empReqEnquiryDTO.setMinCode((row.get(2) != null) ? ((BigDecimal)row.get(2)).longValue() : null);
                empReqEnquiryDTO.setCandMinName((row.get(3) != null) ? ((String)row.get(3)) : null);
                empReqEnquiryDTO.setHireTypeName((row.get(5) != null) ? ((String)row.get(5)) : null);
                empReqEnquiryDTO.setConcernedMinCode((row.get(9) != null) ? ((BigDecimal)row.get(9)).longValue() : null);
                empReqEnquiryDTO.setHireStageName((row.get(7) != null) ? ((String)row.get(7)) : null);
                empReqEnquiryDTO.setRequestArrivalDate((row.get(8) != null) ? ((Timestamp)row.get(8)) : null);
               IEmpCandidatesEntityKey ek =EmpEntityKeyFactory.createEmpCandidatesEntityKey(((BigDecimal)row.get(10)).longValue(), ((BigDecimal)row.get(11)).longValue());
               empReqEnquiryDTO.setCode(ek);
                listDTO.add(empReqEnquiryDTO); 
           }
           return listDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public Long getCandidateCountWithPaging(IBasicDTO searchDTO) throws DataBaseException,SharedApplicationException{
        try{
                IEmpReqEnquiryDTO candSearchDTO = (IEmpReqEnquiryDTO)searchDTO;
                StringBuilder queryStr =
                new StringBuilder("SELECT COUNT(*) from ( SELECT CIVIL_ID , GET_NAME(CIVIL_ID), C.MIN_CODE ,M.MIN_NAME , C.HIRTYPE_CODE , HT.HIRTYPE_NAME, C.HIRSTAGE_CODE ,G.HIRSTAGE_NAME , C.TRANS_REQ_DATE" ); 
                queryStr.append(" , HR_EMP_PAC.GET_CANDIDATE_DEPT(CIVIL_ID) REQ_DEPT " ); 
                queryStr.append(" FROM HR_EMP_CANDIDATES C , HR_EMP_HIRE_STAGES G , HR_EMP_HIRE_TYPES HT , NL_ORG_MINISTRIES M " ); 
                queryStr.append(" WHERE C.HIRSTAGE_CODE = G.HIRSTAGE_CODE " ); 
                queryStr.append(" AND C.HIRTYPE_CODE = HT.HIRTYPE_CODE " ); 
                queryStr.append(" AND C.MIN_CODE = M.MIN_CODE " ); 
                queryStr.append(" AND C.CNDSTATUS_CODE = 2 " ); 
                queryStr.append(" AND C.ACTIVE_FLAG = 1 " ); 
                if(candSearchDTO.getMinCode() != null){
                    queryStr.append(" AND C.MIN_CODE = ").append(candSearchDTO.getMinCode());
                }
            if(candSearchDTO.getRealCivilId() !=null){
                queryStr.append(" and c.civil_id = " + candSearchDTO.getRealCivilId());
            }
                if(candSearchDTO.getName() !=null && !candSearchDTO.getName().equals("")){
                    queryStr.append(" and GET_NAME (CIVIL_ID) like '%" + candSearchDTO.getName() +"%'");
                }
                queryStr.append(" AND C.HIRSTAGE_CODE NOT IN (3,4,19) ) ");
                if(candSearchDTO.getConcernedMinCode() != null){
                    queryStr.append(" where REQ_DEPT = ").append(candSearchDTO.getConcernedMinCode());
                }
        
            Query query = EM().createNativeQuery(queryStr.toString());
            Vector row = (Vector)query.getSingleResult();
            if (row != null) {
              return ((BigDecimal)row.get(0)).longValue();
            }

            } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
            }
            
          return 0L;
        }
    
    public String getCandidateName(IBasicDTO searchDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            IEmpReqEnquiryDTO candSearchDTO = (IEmpReqEnquiryDTO)searchDTO;
            String fullName = "";
            StringBuilder queryStr =
                new StringBuilder("select  " ); 
                queryStr.append("   GET_NAME(CIVIL_ID) REQ_DEPT FROM HR_EMP_CANDIDATES C , HR_EMP_HIRE_STAGES G , HR_EMP_HIRE_TYPES HT , NL_ORG_MINISTRIES M " ); 
                queryStr.append(" WHERE C.HIRSTAGE_CODE = G.HIRSTAGE_CODE " ); 
                queryStr.append(" AND C.HIRTYPE_CODE = HT.HIRTYPE_CODE " ); 
                queryStr.append(" AND C.MIN_CODE = M.MIN_CODE " ); 
                queryStr.append(" AND C.CNDSTATUS_CODE = 2 " ); 
                queryStr.append(" AND C.ACTIVE_FLAG = 1 " ); 
                queryStr.append(" and c.civil_id = " + candSearchDTO.getRealCivilId());
                queryStr.append(" AND C.HIRSTAGE_CODE NOT IN (3,4,19)  ");
            
            Query query = EM().createNativeQuery(queryStr.toString());
            List<Vector> list = query.setHint("toplink.refresh", "true").getResultList();
            for (Vector row : list) {
                fullName =((row.get(0) != null) ? ((String)row.get(0)) : null);
           }
           return fullName;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
   
   
    public String getPreviosNotes(IBasicDTO empDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            IEmpCandidatesEntityKey empCanEntityKey = (IEmpCandidatesEntityKey)empDTO.getCode();
            String fullName = "";
            StringBuilder queryStr =
                new StringBuilder("SELECT VALUE   " ); 
                queryStr.append("   FROM HR_EMP_CANDIDATE_EXTRA_DATA E " ); 
                queryStr.append(" Where E.EXTDATTYPE_CODE = 6  " ); 
                queryStr.append(" AND E.CANDIDATE_CODE =  " +  empCanEntityKey.getCandidateCode()); 
                queryStr.append(" AND E.CANDIDATE_CODE_SEQ =  "  + (empCanEntityKey.getCandidateCodeSeq() - 1));
            System.out.println("PreviosNotes " +queryStr.toString() );
            Query query = EM().createNativeQuery(queryStr.toString());
            List<Vector> list = query.setHint("toplink.refresh", "true").getResultList();
            for (Vector row : list) {
                fullName =((row.get(0) != null) ? ((String)row.get(0)) : null);
           }
           return fullName;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public int insertIntoHrEmpInsuranceWebservLog(ParametersDTO dto) throws DataBaseException, SharedApplicationException {
                
               // Statement  stmt = null; 
                int res = 0;
                try { 
                StringBuilder query = new StringBuilder();
                query.append("Insert into HR_EMP_INSURANCE_WEBSRV_LOG\n" + 
                "   (SERIAL, CSCOPERATIONTYPE, CUSTOMERNAME, CUSTOMERIDENTITY, REGION, \n" + 
                "    ALLOTMENTNUMBER, AVENUENUMBER, STREET, HOUSENUMBER, FLOORNUMBER, \n" + 
                "    APARTMENTNUMBER, DATEOFBIRTH, NATIONALITY, DATEOFNATIONALITY, NATIONALITYNUMBER, \n" + 
                "    SUBJECT, MOBILENUMBER, ENROLLMENTDATE, DATEOFFIRSTREGISTRATION, JOB, \n" + 
                "    SALARY, SOCIALREFUND, COMPLEMENTARYREFUND, SEX, ADDRESSELECTRONICNUMBER, \n" + 
                "    EMPLOYERREGISTRATIONNUMBER, INSURANCETERMINATIONDATE, INSURANCETERMINATIONCAUSE, ABSENTDAYS, REQUESTID, \n" + 
                "    REQUESTTIME, RESULT, MESSAGEDESCRIPTION, LAST_OPERATION_DATE, TABLE_NAME, \n" + 
                "    TERMINATIONREASON,R_TABREC_SERIAL,CREATED_BY,CREATED_DATE,LAST_UPDATED_BY,LAST_UPDATED_DATE )\n" + 
                " Values(" );
                Long serial = getMaxHrEmpInsuranceWebservLog();
                System.out.println("nnnnnnnnn serial>>>>>"+serial);
                query.append(serial).append(",'");
                query.append(dto.getCscOperationType()).append("','");
                query.append(dto.getCustomerName()).append("',");
                query.append(dto.getCstomerIdentity()).append(",");
                if(dto.getRegion() != null){
                    query.append("'").append(dto.getRegion()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getAllotmentNumber() != null){
                    query.append("'").append(dto.getAllotmentNumber()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getAvenueNumber() != null){
                    query.append("'").append(dto.getAvenueNumber()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getStreet() != null){
                    query.append("'").append(dto.getStreet()).append("',");
                }else{
                    query.append(null+",");
                }    
                if(dto.getHouseNumber() != null){
                    query.append("'").append(dto.getHouseNumber()).append("',");
                }else{
                    query.append(null+",");
                }    
                if(dto.getFloorNumber() != null){
                    query.append("'").append(dto.getFloorNumber()).append("',");
                }else{
                    query.append(null+",");
                }  
                if(dto.getApartmentNumber() != null){
                    query.append("'").append(dto.getApartmentNumber()).append("',");
                }else{
                    query.append(null+",");
                }    
                if(dto.getDateOfBirth() != null){
                    query.append("TO_DATE ('"+dto.getDateOfBirth()).append("','yyyy-MM-dd'),'");//append(dto.getDateOfBirth()).
                }else{
                    query.append(null+",'");//.append(dto.getDateOfNationality())
                }    
                
                //query.append("'"+dto.getDateOfBirth()).append("','");   
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
                query.append(dto.getNationality()).append("',");
                if(dto.getDateOfNationality() != null){
                    query.append("TO_DATE ('"+dto.getDateOfNationality()).append("','yyyy-MM-dd'),");
                }else{
                    query.append(null+",");//.append(dto.getDateOfNationality())
                }
                
                query.append(dto.getNationalityNumber()).append(",'");
                query.append(dto.getSubject()).append("',");
                query.append(dto.getMobileNumber()).append(",");
                if(dto.getEnrollmentDate() != null){
                    query.append("TO_DATE ('"+dto.getEnrollmentDate()).append("','yyyy-MM-dd'),");//.append(dto.getEnrollmentDate())
                }else{
                    query.append(null+",");//.append(dto.getEnrollmentDate())
                }
                if(dto.getDateOfFirstRegistration() != null){
                    query.append("TO_DATE ('"+dto.getDateOfFirstRegistration()).append("','yyyy-MM-dd'),");//.append(dto.getDateOfFirstRegistration())
                }else{
                    query.append(null+",");//.append(dto.getDateOfFirstRegistration())
                }    
                query.append(dto.getJob()).append(",");
                query.append(dto.getSalary()).append(",");
                query.append(dto.getSocialRefund()).append(",");
                query.append(dto.getComplementaryRefund()).append(",'");
                query.append(dto.getSex()).append("',");
                query.append(dto.getAddressElectronicNumber()).append(",");
                query.append(dto.getEmployerRegistrationNumber()).append(",");
                if(dto.getInsuranceTerminationDate() != null ){
                    query.append("TO_DATE ('"+dto.getInsuranceTerminationDate()).append("','yyyy-MM-dd'),");//.append(dto.dto.getInsuranceTerminationDate())
                }else{
                    query.append(null+",");//.append(dto.dto.getInsuranceTerminationDate())
                }     
                if(dto.getInsuranceTerminationCause() != null){
                    query.append("'").append(dto.getInsuranceTerminationCause()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getAbsentDaysForEmp() !=null ){
                    query.append("'").append(dto.getAbsentDaysForEmp()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getRequestID() !=null ){
                    query.append("'").append(dto.getRequestID()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getRequestTime() !=null ){
                    query.append("'").append(dto.getRequestTime()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getResult() !=null ){
                    query.append("'").append(dto.getResult()).append("',");
                }else{
                    query.append(null+",");
                }
                if(dto.getMessageDescription() != null &&!"".equals(dto.getMessageDescription())){
                String s1=dto.getMessageDescription();
                    String replaceString=s1.replace("'","''");
                 query.append(" '").append(replaceString).append("',");
                }else{
                    query.append(null+",");
                }
                
                if(dto.getRequestDateTime() != null){
                    
                    query.append("TO_DATE ('"+df.format(dto.getRequestDateTime())).append("','yyyy-MM-dd HH24:mi:ss'),'");
                }else{
                    query.append("TO_DATE ('"+df.format(dto.getLastOperationDate())).append("','yyyy-MM-dd HH24:mi:ss'),'");
                }
                
                query.append(dto.getTableName()).append("',");
                if(dto.getTerminationReason() !=null ){
                    query.append(dto.getTerminationReason()).append(",");
                }else{
                    query.append(null+",");
                }
                query.append(dto.getRTableTabrecSerial() +" , ");
                Long userCode= CSCSecurityInfoHelper.getUserCodeFromRequest(); 
                query.append(userCode + " , ");    
                query.append(" SYSDATE,");
                query.append(userCode + " , ");    
                query.append(" SYSDATE )");
                    
                System.out.println("Query String >>>>>>>"+query.toString());
                Query queryInsert = EM().createNativeQuery(query.toString());
                res = queryInsert.executeUpdate();
                }catch(Exception e){
                    e.printStackTrace();
                }
                return res;
            } 
            
            public Long getMaxHrEmpInsuranceWebservLog() throws DataBaseException, SharedApplicationException { 
                StringBuilder strQry = new StringBuilder();
                strQry.append("select NVL(MAX(L.SERIAL), 0) from CSHR_OWNER.HR_EMP_INSURANCE_WEBSRV_LOG L ");
                Query query = EM().createNativeQuery(strQry.toString());
                Vector result = (Vector)query.setHint("toplink.refresh", "true").getSingleResult();
                 if(result != null && result.size() != 0) {
                    System.out.println(" max id" + result.get(0)); 
                    Long serial = Long.parseLong(result.get(0).toString())+1;
                     System.out.println(" max id" + serial); 
                    return serial;
                 }else{
                     return Long.valueOf(1);
                 }
            }
            
        public Double getElementGuideValue(Long elementGuideCode) {
               Double empRegNum =0.0;
               try{
                   StringBuilder strQry = new StringBuilder();
                   strQry.append("SELECT E.VALUE FROM HR_SAL_ELEMENT_GUIDES E WHERE E.ELMGUIDE_CODE =").append(elementGuideCode);
                   Query query = EM().createNativeQuery(strQry.toString());
                   Vector result = (Vector)query.getSingleResult();
                    empRegNum = Double.parseDouble(result.get(0).toString());
                   
               } catch (Exception e) {
                   e.printStackTrace();
               }
               return empRegNum;
           }
        
            public Integer getEmployerRegistrationNumber() {
                Integer empRegNum =0;
                try{
                    StringBuilder strQry = new StringBuilder();
                    strQry.append("SELECT EXTDAT_VALUE FROM NL_ORG_MIN_EXTRA_DATA M WHERE M.STATUS = 1" +
                                  " AND M.MIN_CODE = " + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + " AND M.EXTDATTYPE_CODE = 39");
                    Query query = EM().createNativeQuery(strQry.toString());
                    Vector result = (Vector)query.getSingleResult();
                     empRegNum = Integer.parseInt(result.get(0).toString());
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return empRegNum;
            }
        
        public Double getSocialRefund(Long realCivilId){
            Double elementGuideValue =0.0;
            try{
                StringBuilder strQry = new StringBuilder();
                strQry.append(" SELECT NVL(SUM(ELM.ELEMENT_VALUE),0) FROM HR_SAL_EMP_SALARY_ELEMENTS ELM, HR_SAL_ELEMENT_GUIDES G" ); 
                strQry.append(" WHERE ELM.ELMGUIDE_CODE = G.ELMGUIDE_CODE AND ELM.STATUS = 1"); 
                strQry.append(" AND ELM.CANCELLED_FLAG = 0 AND G.ELMTYPE_CODE IN (29, 42)");
                strQry.append(" AND TRUNC(SYSDATE) BETWEEN ELM.FROM_DATE AND NVL(ELM.UNTIL_DATE, SYSDATE)");
                strQry.append(" AND ELM.R_CIVIL_ID =").append(realCivilId);
                Query query = EM().createNativeQuery(strQry.toString());
                Vector result = (Vector)query.getSingleResult();
                if(result != null && result.size() != 0){
                    elementGuideValue = Double.parseDouble(result.get(0).toString());
                }
                 
            } catch (Exception e) {
                e.printStackTrace();
            }
            return elementGuideValue;
        }

    /** Start DEV-55  by A.Nour 31-03-2020 */
    public EmployeesDTO getLastEndedServiceEmpCivilId(Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            StringBuilder qryBuilder = new StringBuilder();
            qryBuilder.append(" SELECT CIVIL_ID , END_JOB_DATE FROM HR_EMP_EMPLOYEES emp WHERE REAL_CIVIL_ID = ");
            qryBuilder.append(realCivilId);
            qryBuilder.append(" AND HIRSTATUS_CODE = 9 ");
            qryBuilder.append(" ORDER BY HIRE_DATE DESC ");
            
            System.out.println("getLastEndedServiceEmpCivilId :: " + qryBuilder.toString());
            List<Vector> list = (List<Vector>)EM().createNativeQuery(qryBuilder.toString()).getResultList();

            if (list == null || list.size() == 0) {
                throw new SharedApplicationException("OldCivilNotExistForEmp:"+realCivilId);
            }
            
            Object obj = list.get(0);
            if (obj == null) {
                throw new SharedApplicationException("OldCivilNotExistForEmp:"+realCivilId);
            }

            Vector vector = (Vector)obj;
            if (vector.isEmpty() ) {
                throw new SharedApplicationException("OldCivilNotExistForEmp:"+realCivilId);
            }
            Long civilId = ((BigDecimal)vector.get(0)).longValue();
            Date endJobDate = new Date(((Timestamp)vector.get(1)).getTime());

            if (civilId == null || endJobDate == null) {
                throw new SharedApplicationException("OldCivilNotExistForEmp:"+realCivilId);
            }
            System.out.println("getLastEndedServiceEmpCivilId :: civilId = " + civilId+" , endJobDate = "+endJobDate);
            EmployeesDTO employeesDTO = new EmployeesDTO();
            employeesDTO.setCode(new EmployeesEntityKey(civilId) );
            employeesDTO.setEndJobDate(endJobDate);
            return employeesDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        } 
    }


    /** Start CSC-23925  by A.Nour 17-01-2019 */
     public Boolean updateSalEmpWifesForExternalMov(ISalEmpMovDTO salEmpMovDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
    //         PreparedStatement stmt = null;
    //         Connection connection = null;
         try {
    //             connection = getConnectionForUpdate();
             Long userCode = SecurityInfoHelper.getUserCodeFromRequest();
             StringBuffer updateQueryBuffer =
                 new StringBuffer(" UPDATE HR_SAL_EMP_WIFES SET CIVIL_ID =  "); 
             updateQueryBuffer.append(salEmpMovDTO.getCurrentEmpCivil() );
             updateQueryBuffer.append(" , LAST_UPDATED_DATE = sysDate , LAST_UPDATED_BY = ");
             updateQueryBuffer.append(userCode);
             updateQueryBuffer.append(" , MIN_CODE = ");
             updateQueryBuffer.append(salEmpMovDTO.getMinCode() );
             updateQueryBuffer.append(" WHERE CIVIL_ID = ");
             updateQueryBuffer.append(salEmpMovDTO.getOldEmpCivil() );

             String queryStr = updateQueryBuffer.toString();
    //             stmt = connection.prepareStatement(queryStr);
             System.out.println("updateSalEmpWifesForExternalMov queryStr = "+queryStr);
    //             int res = stmt.executeUpdate();

            Query query = EM().createNativeQuery(queryStr).setHint("toplink.refresh", "true");
            int res = query.executeUpdate();
             
             if (res > 0)
                 return Boolean.TRUE;
             else
                 return Boolean.FALSE;
         } catch (Exception e) {
             e = wrapIntoDataBaseException(e);
             if (e instanceof DataBaseException) {
                 throw (DataBaseException)e;
             } else {
                 throw (SharedApplicationException)e;
             }
    //         } finally {
    //             try { if (stmt != null) stmt.close(); } catch (SQLException sqle) { sqle.printStackTrace();}
         }
     }
    
    public Boolean updateSalEmpChildrenForExternalMov(ISalEmpMovDTO salEmpMovDTO) throws DataBaseException,
                                                                                              SharedApplicationException {
    //        PreparedStatement stmt = null;
    //        Connection connection = null;
        try {
    //            connection = getConnectionForUpdate();
            Long userCode = SecurityInfoHelper.getUserCodeFromRequest();
            StringBuffer updateQueryBuffer =
                new StringBuffer(" UPDATE HR_SAL_EMP_CHILDREN SET CIVIL_ID =  "); 
            updateQueryBuffer.append(salEmpMovDTO.getCurrentEmpCivil() );
            updateQueryBuffer.append(" , LAST_UPDATED_DATE = sysDate , LAST_UPDATED_BY = ");
            updateQueryBuffer.append(userCode);
            updateQueryBuffer.append(" , MIN_CODE = ");
            updateQueryBuffer.append(salEmpMovDTO.getMinCode() );
            updateQueryBuffer.append(" WHERE CIVIL_ID = ");
            updateQueryBuffer.append(salEmpMovDTO.getOldEmpCivil() );

            String queryStr = updateQueryBuffer.toString();
    //            stmt = connection.prepareStatement(queryStr);
            System.out.println("updateSalEmpChildrenForExternalMov queryStr = "+queryStr);
            
    //            int res = stmt.executeUpdate();

            Query query = EM().createNativeQuery(queryStr).setHint("toplink.refresh", "true");
            int res = query.executeUpdate();

            if (res > 0)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
    //        } finally {
    //            try { if (stmt != null) stmt.close(); } catch (SQLException sqle) { sqle.printStackTrace();}
        }
    }
    
    public Boolean copySalEmpSalElmsForChildrenRaiseForExternalMov(ISalEmpMovDTO salEmpMovDTO , int opType) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            Long userCode = SecurityInfoHelper.getUserCodeFromRequest();
            Date movDate = salEmpMovDTO.getMovDate();
            Date endServiceDate = salEmpMovDTO.getEndServiceDate();
            /** first copy elms start before mov date and ends after it */
            StringBuffer updateQueryBuffer =
                new StringBuffer(" INSERT INTO HR_SAL_EMP_SALARY_ELEMENTS (EMP_SAL_ELM_SERIAL, CIVIL_ID, ELMGUIDE_CODE, FROM_DATE, UNTIL_DATE , "); 
            updateQueryBuffer.append(" ELEMENT_VALUE, MERTYPE_CODE, MIN_CODE, R_CIVIL_ID, CREATED_BY, CREATED_DATE ,LAST_UPDATED_BY , LAST_UPDATED_DATE , EMP_SALELM_COMMENT) ");
            updateQueryBuffer.append(" select CSFN_OWNER.DEDUCT_SETT_SEQ.NEXTVAL AS EMP_SAL_ELM_SERIAL , ");
            updateQueryBuffer.append(salEmpMovDTO.getCurrentEmpCivil() );
            if(opType == 1) { /** moving operation */
                updateQueryBuffer.append(" , SESE.ELMGUIDE_CODE , ?  , SESE.UNTIL_DATE , SESE.ELEMENT_VALUE , SESE.MERTYPE_CODE , ");
            } else if(opType == 2) { /** re employying emp operation */
                updateQueryBuffer.append(" , SESE.ELMGUIDE_CODE , ?  , null , SESE.ELEMENT_VALUE , SESE.MERTYPE_CODE , ");
            }
            updateQueryBuffer.append(salEmpMovDTO.getMinCode() );
            updateQueryBuffer.append("  , SESE.R_CIVIL_ID ,  ");
            updateQueryBuffer.append(userCode);
            updateQueryBuffer.append("  , SYSDATE , ");
            updateQueryBuffer.append(userCode);
            updateQueryBuffer.append("  , SYSDATE  ,'");// Affected by Mov. Op. serial : 
            //updateQueryBuffer.append(salEmpMovDTO.getMovingSerial() );
            String comment = (salEmpMovDTO.getName() == null ? "" : salEmpMovDTO.getName() ) + (salEmpMovDTO.getMovingSerial() == null ? "" : salEmpMovDTO.getMovingSerial()  );
            updateQueryBuffer.append(comment);
            updateQueryBuffer.append("'");
            updateQueryBuffer.append(" FROM HR_SAL_EMP_SALARY_ELEMENTS  SESE INNER JOIN HR_SAL_ELEMENT_GUIDES GUID ON SESE.CIVIL_ID = ");
            updateQueryBuffer.append(salEmpMovDTO.getOldEmpCivil() );
            updateQueryBuffer.append(" AND GUID.ELMGUIDE_CODE = SESE.ELMGUIDE_CODE AND GUID.ELMTYPE_CODE IN ( ");
            updateQueryBuffer.append(ISalConstants.ELEMENT_GUIDE_TYPE_HEALTHY_CHILDREN_PROMOTION_CAT);
            updateQueryBuffer.append(" , ").append(ISalConstants.ELEMENT_GUIDE_TYPE_HANDICAPPED_CHILDREN_PROMOTION_CAT);
            if(opType == 1) { /** moving operation */
                updateQueryBuffer.append(" ) WHERE (SESE.FROM_DATE <= ? AND (SESE.UNTIL_DATE IS NULL OR SESE.UNTIL_DATE > ?) ) ");
                updateQueryBuffer.append(" AND SESE.CANCELLED_FLAG <> 1 ");
            } else if(opType == 2) { /** re employying emp operation */
                updateQueryBuffer.append(" ) WHERE (SESE.FROM_DATE <= ? AND (SESE.UNTIL_DATE IS NULL OR SESE.UNTIL_DATE = ?) ) ");
                updateQueryBuffer.append(" AND SESE.CANCELLED_FLAG <> 1 ");
            } 
            /** remove condition AND SESE.STATUS = 1 for old data */
            

            String queryStr = updateQueryBuffer.toString();
    //            stmt = connection.prepareStatement(queryStr);
    //            stmt.setDate(1, movDate);
    //            stmt.setDate(2, movDate);
    //            stmt.setDate(3, movDate);
            
            System.out.println("copySalEmpSalElmsForChildrenRaiseForExternalMov queryStr = "+queryStr);
            
    //            int res = stmt.executeUpdate();
            Query query = EM().createNativeQuery(queryStr).setHint("toplink.refresh", "true");
            query.setParameter(1, movDate);
            query.setParameter(2, movDate);
            if(opType == 1) { /** moving operation */
                query.setParameter(3, movDate);
            } else if(opType == 2) { /** re employying emp operation */
                query.setParameter(3, endServiceDate);
            }
            int res = query.executeUpdate();
            
    //            /** second copy elms start after mov */
            updateQueryBuffer =
                new StringBuffer(" INSERT INTO HR_SAL_EMP_SALARY_ELEMENTS (EMP_SAL_ELM_SERIAL, CIVIL_ID, ELMGUIDE_CODE, FROM_DATE, UNTIL_DATE , "); 
            updateQueryBuffer.append(" ELEMENT_VALUE, MERTYPE_CODE, MIN_CODE, R_CIVIL_ID, CREATED_BY, CREATED_DATE ,LAST_UPDATED_BY , LAST_UPDATED_DATE , EMP_SALELM_COMMENT) ");
            updateQueryBuffer.append(" select CSFN_OWNER.DEDUCT_SETT_SEQ.NEXTVAL AS EMP_SAL_ELM_SERIAL , ");
            updateQueryBuffer.append(salEmpMovDTO.getCurrentEmpCivil() );
            updateQueryBuffer.append(" , SESE.ELMGUIDE_CODE , SESE.FROM_DATE  , SESE.UNTIL_DATE , SESE.ELEMENT_VALUE , SESE.MERTYPE_CODE , ");
            updateQueryBuffer.append(salEmpMovDTO.getMinCode() );
            updateQueryBuffer.append("  , SESE.R_CIVIL_ID ,  ");
            updateQueryBuffer.append(userCode);
            updateQueryBuffer.append("  , SYSDATE , ");
            updateQueryBuffer.append(userCode);
            updateQueryBuffer.append("  , SYSDATE  ,'");// Affected by Mov. Op. serial : 
            //updateQueryBuffer.append(salEmpMovDTO.getMovingSerial() );
            updateQueryBuffer.append(comment);
            updateQueryBuffer.append("'");

            updateQueryBuffer.append(" FROM HR_SAL_EMP_SALARY_ELEMENTS  SESE INNER JOIN HR_SAL_ELEMENT_GUIDES GUID ON SESE.CIVIL_ID = ");
            updateQueryBuffer.append(salEmpMovDTO.getOldEmpCivil() );
            updateQueryBuffer.append(" AND GUID.ELMGUIDE_CODE = SESE.ELMGUIDE_CODE AND GUID.ELMTYPE_CODE IN ( ");
            updateQueryBuffer.append(ISalConstants.ELEMENT_GUIDE_TYPE_HEALTHY_CHILDREN_PROMOTION_CAT);
            updateQueryBuffer.append(" , ").append(ISalConstants.ELEMENT_GUIDE_TYPE_HANDICAPPED_CHILDREN_PROMOTION_CAT);
            updateQueryBuffer.append(" ) WHERE SESE.FROM_DATE > ? ");
            updateQueryBuffer.append(" AND SESE.CANCELLED_FLAG <> 1 ");

            queryStr = updateQueryBuffer.toString();
            
    //            stmt = connection.prepareStatement(queryStr);
    //            stmt.setDate(1, movDate);
    //            stmt.setDate(2, movDate);

            System.out.println("copySalEmpSalElmsForChildrenRaiseForExternalMov queryStr 2 = "+queryStr);
            
    //            int res2 = stmt.executeUpdate();

            query = EM().createNativeQuery(queryStr).setHint("toplink.refresh", "true");
            query.setParameter(1, movDate);
    //            query.setParameter(2, movDate);
            int res2 = query.executeUpdate();

            if (res + res2 > 0)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
    //        } finally {
    //            try { if (stmt != null) stmt.close(); } catch (SQLException sqle) { sqle.printStackTrace();}
        }
    }


    public Boolean chkWrkCenterStatus(Long minCode, String wrkCode) throws DataBaseException,
                                                                          SharedApplicationException {


    
        final String SQL = "begin ? :=  HR_ORG_INTERFACE_PACK.CHK_WRK_CENTER_STATUS(?,?);end;";
        System.out.println(SQL);
        CallableStatement stm = null;
        try {
            Connection con = getConnectionForUpdate();
            stm = con.prepareCall(SQL);
            stm.registerOutParameter(1, OracleTypes.NUMBER);
            stm.setLong(2, minCode);
            stm.setString(3, wrkCode);
            stm.executeUpdate();

            System.out.println("min code" + minCode);
            System.out.println("wrkCode" + wrkCode);

            System.out.println("statment is" + SQL);


            System.out.println(stm.getObject(1));
          String  strResult = stm.getObject(1) != null ? stm.getObject(1).toString() : "-l";
            System.out.println(strResult);
            
            if (strResult.equals("-1"))
                return Boolean.TRUE;


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {

                throw (DataBaseException)e;

            } else {
                throw (SharedApplicationException)e;

            }
        } finally {
            BaseDAO.close(stm);
        }
        return Boolean.FALSE;
    }
}
