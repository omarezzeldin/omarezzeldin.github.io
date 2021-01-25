package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.datafiltration.IDfDTO;
import com.beshara.base.dto.BasicDTO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.EntityKey;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingRequestDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.base.security.ICategoryInfo;
import com.beshara.csc.base.security.IMinistryInfo;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.gn.sec.business.client.IUsersClient;
import com.beshara.csc.gn.sec.business.client.SecClientFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpBonusDataDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmpFinicialDataDTO;
import com.beshara.csc.hr.emp.business.dto.EmpSearchRecodDesc;
import com.beshara.csc.hr.emp.business.dto.EmpStatusForSalDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTOSer;
import com.beshara.csc.hr.emp.business.dto.IEmpBonusData;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpFinicialData;
import com.beshara.csc.hr.emp.business.dto.IEmployeeDTOService;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IVacEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.shared.ISimpleEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.shared.SimpleEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.hr.emp.business.entity.HireStatusEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.sal.EmpSalEmpSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.EmpUtils;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.hr.emp.business.shared.exception.EmployeeNotHiredException;
import com.beshara.csc.hr.mov.business.dto.IMovMovingRequestsDTO;
import com.beshara.csc.hr.mov.business.dto.MovDTOFactory;
import com.beshara.csc.hr.mov.business.entity.IMovMovingRequestsEntityKey;
import com.beshara.csc.hr.mov.business.entity.MovMovingRequestsEntityKey;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalDegreeReasonsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalStopReasonsDTO;
import com.beshara.csc.hr.sal.business.dto.SalDTOFactory;
import com.beshara.csc.hr.sal.business.dto.SalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.hr.vac.business.dto.IVacEmployeeVacationsDTO;
import com.beshara.csc.hr.vac.business.dto.IVacGroupVacationDTO;
import com.beshara.csc.hr.vac.business.dto.VacEmployeeVacationsDTO;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.nl.job.business.dto.IJobSearchCriteriaDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.MinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.nl.reg.business.entity.DecisionsEntityKey;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.FinderException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;

import oracle.jdbc.OracleTypes;

import oracle.toplink.essentials.internal.ejb.cmp3.EntityManagerImpl;
import oracle.toplink.essentials.internal.sessions.UnitOfWorkImpl;
import oracle.toplink.essentials.threetier.ClientSession;

import utils.system;


public class EmployeesDAO extends EmpBaseDAO {
    private static final Long DEFAULT_AUDIT_STATUS = 0L;

    public EmployeesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmployeesDAO();
    }

    /**<code>select o from EmployeesEntity o</code>.
     * @return List
     */
    @Override
    public List<IEmployeesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeesEntity> list =
                EM().createNamedQuery("EmployeesEntity.findAll").setParameter("hirstageCode", IEMPConstant.EMP_STAGE_CANCEL_NOMINATION).getResultList();
            for (EmployeesEntity employees : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
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
    public Boolean remove(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;

            EmployeesEntity employeesEntity =
                EM().find(EmployeesEntity.class, (EmployeesEntityKey)employeesDTO.getCode());

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(employeesEntity);
            return Boolean.TRUE;
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
     * Create a New EmployeesEntity
     * @param employeesDTO
     * @return IEmployeesDTO
     */
    @Override
    public IEmployeesDTO add(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = new EmployeesEntity();
            IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;

            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
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
            employeesEntity.setRealCivilId(employeesDTO.getRealCivilId());

            if (employeesDTO.getCode() != null) {
                employeesEntity.setCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
            } else {
                employeesEntity.setCivilId(findMaxId());
            }

            employeesEntity.setMinistryFileNo(employeesDTO.getMinistryFileNo());
            employeesEntity.setCscFileNo(employeesDTO.getCscFileNo());
            employeesEntity.setHireDate(employeesDTO.getHireDate());
            employeesEntity.setStartWorkingDate(employeesDTO.getStartWorkingDate());
            employeesEntity.setEndJobDate(employeesDTO.getEndJobDate());
            employeesEntity.setDateOfNextRaise(employeesDTO.getDateOfNextRaise());

            employeesEntity.setActiveFlag(employeesDTO.getActiveFlag());
            employeesEntity.setRecordDescCode(employeesDTO.getRecordDescCode());

            employeesEntity.setCreatedBy(employeesDTO.getCreatedBy());
            employeesEntity.setCreatedDate(employeesDTO.getCreatedDate());
            employeesEntity.setAuditStatus(employeesDTO.getAuditStatus());
            employeesEntity.setTabrecSerial(employeesDTO.getTabrecSerial());
            employeesEntity.setSocialInsurNo(employeesDTO.getSocialInsurNo());

            if (employeesDTO.getEmpCandidatesDTO() != null) {
                EmpCandidatesEntity empCandidatesEntity =
                    EM().find(EmpCandidatesEntity.class, (employeesDTO.getEmpCandidatesDTO().getCode()));
                if (empCandidatesEntity == null) {
                    System.out.println("empCandidatesEntity is NULL");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setEmpCandidatesEntity(empCandidatesEntity);
                }
            }
            //            else {
            //                System.out.println("empCandidatesEntity is NULL");
            //                throw new ItemNotFoundException();
            //            }

            if (employeesDTO.getWorkCenterDTO() != null) {
                // WorkCentersEntity workCentersEntity = EM().find(WorkCentersEntity.class, (employeesDTO.getWorkCenterDTO().getCode()));
                IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode();
                if (wEk == null) {
                    System.out.println("WorkCentersEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    //employeesEntity.setWorkCentersEntity(workCentersEntity);
                    employeesEntity.setWrkCode(wEk.getWrkCode());
                    employeesEntity.setMinCode(wEk.getMinCode());

                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireStatusDTO() != null) {
                HireStatusEntity hireStatusEntity =
                    EM().find(HireStatusEntity.class, (employeesDTO.getHireStatusDTO().getCode()));
                if (hireStatusEntity == null) {
                    System.out.println("hireStatusEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStatusEntity(hireStatusEntity);
                }
            } else {
                System.out.println("hireStatusEntity is NULL ");
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireTypeDTO() != null) {
                HireTypesEntity hireTypesEntity =
                    EM().find(HireTypesEntity.class, (employeesDTO.getHireTypeDTO().getCode()));
                if (hireTypesEntity == null) {
                    System.out.println("hireTypesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireTypesEntity(hireTypesEntity);
                }
            } else {
                System.out.println("hireTypesEntity is NULL ");
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireStageDTO() != null) {
                HireStagesEntity hireStagesEntity =
                    EM().find(HireStagesEntity.class, (employeesDTO.getHireStageDTO().getCode()));
                if (hireStagesEntity == null) {
                    System.out.println("hireStagesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStagesEntity(hireStagesEntity);
                }
            } else {
                System.out.println("hireStagesEntity is NULL ");
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getJobDTO() != null) {
                // JobsEntity jobsEntity = EM().find(JobsEntity.class, (employeesDTO.getJobDTO().getCode()));
                IJobsEntityKey jEk = (IJobsEntityKey)employeesDTO.getJobDTO().getCode();
                //  if (jobsEntity == null) {
                if (jEk.getJobCode() == null) {
                    System.out.println("JobsEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setJobCode(jEk.getJobCode());
                }
            } else {
                System.out.println("JobsEntity is NULL ");
                throw new ItemNotFoundException();
            }
            if (employeesDTO.getTechJobsDTO() != null) {
                IJobsEntityKey teckJobEntityKey = (IJobsEntityKey)employeesDTO.getTechJobsDTO().getCode();
                employeesEntity.setTechJobCode(teckJobEntityKey.getJobCode());
            }

            if (employeesDTO.getBgtProgramsDTO() != null) {
                //BgtProgramsEntity bgtProgramsEntity = EM().find(BgtProgramsEntity.class, (employeesDTO.getBgtProgramsDTO().getCode()));
                IBgtProgramsEntityKey pEk = (IBgtProgramsEntityKey)employeesDTO.getBgtProgramsDTO().getCode();
                //            if (employeesDTO.getBgtProgramsDTO() == null) {
                if (pEk.getPrgCode() == null) {
                    System.out.println("bgtProgramsEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgtprgCode(pEk.getPrgCode());
                }
            } else {
                System.out.println("bgtProgramsEntity is NULL ");
                throw new ItemNotFoundException();
            }
            //////////////////////////////////////
            if (employeesDTO.getBgtTypesDTO() != null) {
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)employeesDTO.getBgtTypesDTO().getCode();
                if (bgtTEk.getTypeCode() == null) {
                    System.out.println("bgtTypesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgttypeCode(bgtTEk.getTypeCode());
                }
            }


            if (employeesDTO.getBgtTypesDTO() != null) {
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)employeesDTO.getBgtTypesDTO().getCode();
                if (bgtTEk.getTypeCode() == null) {
                    System.out.println("bgtTypesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgttypeCode(bgtTEk.getTypeCode());
                }
            }
            employeesEntity = doAdd(employeesEntity);
            EM().refresh(employeesEntity);
            System.out.println("EmployeesDAO add employeesEntity.getCivilId() = "+employeesEntity.getCivilId()+" , employeesEntity.getRealCivilId() = "+employeesEntity.getRealCivilId() );
            employeesDTO.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
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


    /**
     * Update an Existing EmployeesEntity with the given BGT Type OR Job
     * @param civilId
     * @param endJobDate
     * @param employeeHireStatus
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    public Boolean updateEmployeeForPRM(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
            EmployeesEntity employeesEntity = EM().find(EmployeesEntity.class, employeesDTO.getCode());
            if (employeesEntity == null)
                throw new ItemNotFoundException();
            if (employeesDTO.getJobDTO() != null) {
                IJobsEntityKey jEk = (IJobsEntityKey)employeesDTO.getJobDTO().getCode();
                if (jEk.getJobCode() == null) {
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setJobCode(jEk.getJobCode());
                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getBgtTypesDTO() != null) {
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)employeesDTO.getBgtTypesDTO().getCode();
                if (bgtTEk.getTypeCode() == null) {
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgttypeCode(bgtTEk.getTypeCode());
                }
            }
            if(employeesDTO.getTechJobKey() != null && !"".equals(employeesDTO.getTechJobKey())){
                employeesEntity.setTechJobCode(employeesDTO.getTechJobKey());
            }

            doUpdate(employeesEntity);
            return Boolean.TRUE;

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
     * @author Msayed
     * @since 8-9-2015
     * @param empKey
     * @param employeeHireStatus
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean updateEmployeeStaus(IEntityKey empKey, IEntityKey employeeHireStatus) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {

            EmployeesEntity employeesEntity = EM().find(EmployeesEntity.class, empKey);
            if (employeesEntity == null)
                throw new ItemNotFoundException();
            HireStatusEntity hireStatusEntity = EM().find(HireStatusEntity.class, (employeeHireStatus));
            if (hireStatusEntity == null) {
                System.out.println("hireStatusEntity is NULL ");
                throw new ItemNotFoundException();
            } else {
                employeesEntity.setHireStatusEntity(hireStatusEntity);
            }
            doUpdate(employeesEntity);
            return Boolean.TRUE;

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
     * Update an Existing EmployeesEntity
     * @param employeesDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO employeesDTO1) throws DataBaseException, SharedApplicationException {

        IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
        try {
            EmployeesEntity employeesEntity =
                EM().find(EmployeesEntity.class, (EmployeesEntityKey)employeesDTO.getCode());


            //        List<EmployeesEntity> list =
            //            EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
            //                                                                                                      ((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId()).getResultList();
            //        if (list == null || list.size() == 0)
            //            throw new FinderException();
            //        EmployeesEntity employeesEntity = list.get(0);

            employeesEntity.setMinistryFileNo(employeesDTO.getMinistryFileNo());
            employeesEntity.setCscFileNo(employeesDTO.getCscFileNo());
            employeesEntity.setHireDate(employeesDTO.getHireDate());
            employeesEntity.setStartWorkingDate(employeesDTO.getStartWorkingDate());
            employeesEntity.setEndJobDate(employeesDTO.getEndJobDate());
            employeesEntity.setDateOfNextRaise(employeesDTO.getDateOfNextRaise());
            //employeesEntity.setTechJobCode(employeesDTO.getTechJobCode());
            //employeesEntity.setAccountNo(employeesDTO.getAccountNo());

            employeesEntity.setActiveFlag(employeesDTO.getActiveFlag());
            employeesEntity.setRecordDescCode(employeesDTO.getRecordDescCode());

            employeesEntity.setCreatedBy(employeesDTO.getCreatedBy());
            employeesEntity.setCreatedDate(employeesDTO.getCreatedDate());
            employeesEntity.setAuditStatus(employeesDTO.getAuditStatus());
            employeesEntity.setTabrecSerial(employeesDTO.getTabrecSerial());
            employeesEntity.setSocialInsurNo(employeesDTO.getSocialInsurNo());
            if (employeesDTO.getWorkCenterDTO() != null) {
                //  WorkCentersEntity workCentersEntity = EM().find(WorkCentersEntity.class, (IWorkCentersEntityKey)(employeesDTO.getWorkCenterDTO().getCode()));
                IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode();
                // if (workCentersEntity == null) {
                if (wEk == null) {
                    throw new ItemNotFoundException();
                } else {
                    //employeesEntity.setWorkCentersEntity(workCentersEntity);
                    employeesEntity.setMinCode(wEk.getMinCode());
                    employeesEntity.setWrkCode(wEk.getWrkCode());
                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireStatusDTO() != null) {
                HireStatusEntity hireStatusEntity =
                    EM().find(HireStatusEntity.class, (IHireStatusEntityKey)(employeesDTO.getHireStatusDTO().getCode()));
                if (hireStatusEntity == null) {
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStatusEntity(hireStatusEntity);
                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireTypeDTO() != null) {
                HireTypesEntity hireTypesEntity =
                    EM().find(HireTypesEntity.class, (IHireTypesEntityKey)(employeesDTO.getHireTypeDTO().getCode()));
                if (hireTypesEntity == null) {
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireTypesEntity(hireTypesEntity);
                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireStageDTO() != null) {
                HireStagesEntity hireStagesEntity =
                    EM().find(HireStagesEntity.class, (IHireStagesEntityKey)(employeesDTO.getHireStageDTO().getCode()));
                if (hireStagesEntity == null) {
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStagesEntity(hireStagesEntity);
                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getJobDTO() != null) {
                //  JobsEntity jobsEntity = EM().find(JobsEntity.class, (IJobsEntityKey)(employeesDTO.getJobDTO().getCode()));
                IJobsEntityKey jEk = (IJobsEntityKey)employeesDTO.getJobDTO().getCode();
                // if (jobsEntity == null) {
                if (jEk.getJobCode() == null) {
                    throw new ItemNotFoundException();
                } else {
                    // employeesEntity.setJobsEntity(jobsEntity);
                    employeesEntity.setJobCode(jEk.getJobCode());
                }
            } else {
                throw new ItemNotFoundException();
            }
            //            if (employeesDTO.getTechJobsDTO() != null) {
            //                JobsEntity techJobsEntity =
            //                    EM().find(JobsEntity.class, (IJobsEntityKey)(employeesDTO.getTechJobsDTO().getCode()));
            //                if (techJobsEntity == null) {
            //                    throw new FinderException();
            //                } else {
            //                    employeesEntity.setTechJobsEntity(techJobsEntity);
            //                }
            //            } else {
            //                employeesEntity.setTechJobsEntity(null);
            //            }

            if (employeesDTO.getTechJobsDTO() != null) {
                IJobsEntityKey teckJobEntityKey = (IJobsEntityKey)employeesDTO.getTechJobsDTO().getCode();
                employeesEntity.setTechJobCode(teckJobEntityKey.getJobCode());
            }
            //        if (employeesDTO.getBankBrancheDTO() != null) {
            //            BankBranchesEntity bankBranchesEntity =
            //                EM().find(BankBranchesEntity.class,
            //                        (IBankBranchesEntityKey)(employeesDTO.getBankBrancheDTO().getCode()));
            //            if (bankBranchesEntity == null) {
            //                throw new FinderException();
            //            } else {
            //                employeesEntity.setBankBranchesEntity(bankBranchesEntity);
            //            }
            //        } else {
            //            employeesEntity.setBankBranchesEntity(null);
            //        }
            //////////////////////////////////////
            if (employeesDTO.getBgtProgramsDTO() != null) {
                //BgtProgramsEntity bgtProgramsEntity = EM().find(BgtProgramsEntity.class, (IBgtProgramsEntityKey)(employeesDTO.getBgtProgramsDTO().getCode()));
                IBgtProgramsEntityKey bgtPEk = (IBgtProgramsEntityKey)employeesDTO.getBgtProgramsDTO().getCode();
                //if (bgtProgramsEntity == null) {
                if (bgtPEk.getPrgCode() == null) {
                    throw new ItemNotFoundException();
                } else {
                    //employeesEntity.setBgtProgramsEntity(bgtProgramsEntity);
                    employeesEntity.setBgtprgCode(bgtPEk.getPrgCode());
                }
            } else {
                throw new ItemNotFoundException();
            }
            //////////////////////////////////////
            if (employeesDTO.getBgtTypesDTO() != null) {
                // BgtTypesEntity bgtTypesEntity = EM().find(BgtTypesEntity.class, (IBgtTypesEntityKey)(employeesDTO.getBgtTypesDTO().getCode()));
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)employeesDTO.getBgtTypesDTO().getCode();
                //  if (bgtTypesEntity == null) {
                if (bgtTEk.getTypeCode() == null) {
                    throw new ItemNotFoundException();
                } else {
                    //employeesEntity.setBgtTypesEntity(bgtTypesEntity);
                    employeesEntity.setBgttypeCode(bgtTEk.getTypeCode());
                }
            } else {
                throw new ItemNotFoundException();
            }
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


    /**
     * Get EmployeesEntity By Primary Key
     * @param id
     * @return IEmployeesDTO
     */
    public IEmployeesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeesEntityKey id = (IEmployeesEntityKey)id1;
            EmployeesEntity employeesEntity = null;
            StringBuilder queryStr = new StringBuilder("SELECT o FROM EmployeesEntity o WHERE o.civilId = ?1");

            //          commented By M.abdelsabour to apply new DataFilteration
            //            //added by Technical Team [m.sayed] at 31-3-2016
            //            // stroy ID CSC-17343  work Center data filter
            //            String wrkcode = initWrkcenterTree();
            //            if (wrkcode != null && !wrkcode.isEmpty()) {
            //                queryStr.append(" and o.wrkCode in( " + wrkcode + ")");
            //            }

            List<EmployeesEntity> list =
                EM().createQuery(queryStr.toString()).setHint("toplink.refresh", "true").setParameter(1,
                                                                                                      id.getCivilId()).getResultList();


            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
            /*Added List Of Document and Procedure*/
            //Added By Taha Abdul Mejid
            //Add EmployeeDocuments
            //            if (employeesEntity.getEmployeeDocumentsEntityList() != null) {
            //                List<IBasicDTO> employeeDocumentsEntityList = new ArrayList<IBasicDTO>();
            //                for (EmployeeDocumentsEntity entity : employeesEntity.getEmployeeDocumentsEntityList()) {
            //                    employeeDocumentsEntityList.add(new EmployeeDocumentsDTO(entity));
            //                }
            //                employeesDTO.setEmployeeDocumentsDTOList(employeeDocumentsEntityList);
            //            }
            //            // Add EmployeeProcedures
            //            if (employeesEntity.getEmployeeProceduresEntityList() != null) {
            //                List<IBasicDTO> employeeProceduresEntityList = new ArrayList<IBasicDTO>();
            //                for (EmployeeProceduresEntity entity : employeesEntity.getEmployeeProceduresEntityList()) {
            //                    employeeProceduresEntityList.add(new EmployeeProceduresDTO(entity));
            //                }
            //                employeesDTO.setEmployeeProceduresDTOList(employeeProceduresEntityList);
            //            }
            // Add Employee Salary
            //            if (employeesEntity.getSalEmpSalaryElementsEntityList() != null) {
            //
            //
            //                List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList = new ArrayList<ISalEmpSalaryElementsDTO>();
            //                for (EmpSalEmpSalaryElementsEntity entity : employeesEntity.getSalEmpSalaryElementsEntityList()) {
            //                    if (entity.getUntilDate() == null) {
            //                        //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
            //                        empSalaryElementsDTOList.add(getSalEmpSalaryElementsDTOFromEntity(entity));
            //                    }
            //                }
            //                employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
            //            }
            //            //End of add list of document and procedure
            //
            //            // Add EmployeeExtraData
            //            List<EmployeeExtraDataEntity> employeeExtraDataEntityList =
            //                this.getAllEmployeeExtraDataByCivilId(id.getCivilId());
            //            employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            //            if (employeeExtraDataEntityList != null && !employeeExtraDataEntityList.isEmpty()) {
            //                List<IBasicDTO> employeeExtraDataDTOList = new ArrayList<IBasicDTO>();
            //                System.out.println("@#$ = " + employeeExtraDataEntityList.size());
            //                for (EmployeeExtraDataEntity entity : employeeExtraDataEntityList) {
            //                    employeeExtraDataDTOList.add(EmpDTOFactory.createEmployeeExtraDataDTO(entity));
            //                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());
            //
            //                    }
            //                }
            //                employeesDTO.setEmployeeExtraDataDTOList(employeeExtraDataDTOList);
            //            }
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

    /**
     * @author Black Horse [m.sayed]
     * @since 20-8-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     */

    public IEmployeesDTO getByRealCivilIdAndHireStatus(Long realCivilId) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId  ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                /// commented By M.abdelsabour to apply new DataFilteration Sol
                //added by B.Horse Team  at 31-3-2016
                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in( " + wrkcode + ")");
                //                }


                queryStr.append(" and o.activeFlag=:activeflag ");

                list =
EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                            IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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


    /**
     * @author Black Horse
     * @since 7-01-2016
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     * Emp_With_Status_END_Service=Long.valueOf(9)//منتهى خدمته
     */

    public IEmployeesDTO getByRealCivilIdAndHireStatusForReg(Long realCivilId) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATIONREG();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId  ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                /// commented By M.abdelsabour to apply new Data Filteration Sol
                //                //added by B.Horse Team at 31-3-2016
                //                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in( " + wrkcode + ")");
                //                }

                queryStr.append(" and o.activeFlag=:activeflag ");
                list =
EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                            IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

    /**
     * @author Black Horse [m.sayed]
     * @since 20-8-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     */

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMov(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId   ");
                if (minCode > -1L) {
                    queryStr.append("AND ( :minCode is null OR o.minCode = :minCode)");
                }
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                /// commented By M.abdelsabour to apply new DataFilteration Sol
                //                //added by Technical Team [m.sayed] at 31-3-2016
                //                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
                //                }
                Query query;
                System.out.println("A.Kahled :: " + queryStr.toString());
                System.out.println("realCivilId : " + realCivilId);
                System.out.println("min = " + minCode);

                query =
                        EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                                                    IEMPConstants._EMP_ACTIVE_STATUS);
                if (minCode > -1L) {
                    query.setParameter("minCode", minCode);
                }
                System.out.println(queryStr.toString());
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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


    /**
     * @author Black Horse [m.sayed]
     * @since 20-8-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     */

    public IEmployeesDTO getByRealCivilIdForMovWithoutFilteration(Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId   ");
                if (minCode > -1L) {
                    queryStr.append("AND ( :minCode is null OR o.minCode = :minCode)");
                }
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                Query query;

                System.out.println("A.Kahled :: " + queryStr.toString());
                System.out.println("realCivilId : " + realCivilId);

                query =
                        EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                                                    IEMPConstants._EMP_ACTIVE_STATUS);
                if (minCode > -1L) {
                    query.setParameter("minCode", minCode);
                }
                System.out.println(queryStr.toString());
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

    /**
     * @author Black Horse [m.sayed]
     * @since 14-12-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode but return basic data only and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     */

    public IEmployeesDTO getByRealCivilIdAndHireStatusForMovSimple(Long realCivilId,
                                                                   Long minCode) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId   ");
                if (minCode > -1L) {
                    queryStr.append("AND ( :minCode is null OR o.minCode = :minCode)");
                }
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                Query query;

                System.out.println("A.Kahled :: " + queryStr.toString());
                System.out.println("getByRealCivilIdAndHireStatusForMovSimple realCivilId : " + realCivilId);

                query =
                        EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                                                    IEMPConstants._EMP_ACTIVE_STATUS);
                if (minCode > -1L) {
                    query.setParameter("minCode", minCode);
                }
                System.out.println(query.toString());
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new EmployeeNotHiredException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity, true);


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

    /**
     * @author Black Horse
     * @since 20-8-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     * HR_EMP_REASON_TYPES_TERMINATE_EXT_DLG = Long.valueOf(9); //منتهي خدمه
     */

    public IEmployeesDTO getEmpForMov(Long realCivilId, Long minCode) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION();

            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId   ");
                if (minCode > -1L) {
                    queryStr.append("AND ( :minCode is null OR o.minCode = :minCode)");
                }
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + " , " +
                                IEMPConstants.HR_EMP_REASON_TYPES_TERMINATE_EXT_DLG + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");

                /// commented By M.abdelsabour to apply new Data Filteration Sol
                //                //added by Technical Team [m.sayed] at 31-3-2016
                //                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in( " + wrkcode + ")");
                //                }
                Query query;
                query =
                        EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                                                    IEMPConstants._EMP_ACTIVE_STATUS);
                if (minCode > -1L) {
                    query.setParameter("minCode", minCode);
                }
                System.out.println(query.toString());
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

    /**
     * @author Black Horse [m.sayed]
     * @since 20-8-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode and also check about the following status :
     * EMP_STATUS_EMPLOYMENT = Long.valueOf(1); //معين
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     * EMP_STATUS_DELEGATION_OUT_TO_IT = Long.valueOf(11); //منتدب خارجيا�? اليها
     * EMP_STATUS_FREEZING = Long.valueOf(13); //مجمد
     * EMP_STATUS_DELEGATION_TO_OUT = Long.valueOf(4); //معار الى جهه خارجيه
     */

    public IEmployeesDTO getByRealCivilIdAndHireStatus(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHire();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId  AND ( :minCode is null OR o.minCode = :minCode)  ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                //                /// commented By M.abdelsabour to apply new DataFilteration Sol
                //                //added by Technical Team [m.sayed] at 31-3-2016
                //                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
                //                }
                list =
EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                            IEMPConstants._EMP_ACTIVE_STATUS).setParameter("minCode",
                                                                                                                                           minCode).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

    // method to get employee by real civil id , logged in ministry code and activeFlag
    //added by I.Omar

    public IEmployeesDTO getByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                list =
EM().createNamedQuery("EmployeesEntity.getByRealCivilId").setHint("toplink.refresh", "true").setParameter("realCivilId",
                                                                                                          realCivilId).setParameter("minCode",
                                                                                                                                    minCode).setParameter("activeFlag",
                                                                                                                                                          IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            //            if (!EmpUtils.checkWrkCenterFound(CSCSecurityInfoHelper.getGroupCodeFromRequest(), employeesEntity.getWrkCode())){
            //                throw new ItemNotFoundException();
            //            }
            IEmployeesDTO employeesDTO = EmpEntityConverter.getSimpleEmployeesDTO(employeesEntity);

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

    public IEmployeesDTO getByRealCivilIdForEsrv(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                        SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                list =
EM().createNamedQuery("EmployeesEntity.getByRealCivilId").setHint("toplink.refresh", "true").setParameter("realCivilId",
                                                                                                          realCivilId).setParameter("minCode",
                                                                                                                                    minCode).setParameter("activeFlag",
                                                                                                                                                          IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);

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

    public IEmployeesDTO getCivilByRealCivil(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                list =
EM().createNamedQuery("EmployeesEntity.getByRealCivilId").setHint("toplink.refresh", "true").setParameter("realCivilId",
                                                                                                          realCivilId).setParameter("minCode",
                                                                                                                                    minCode).setParameter("activeFlag",
                                                                                                                                                          IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            //            if (!EmpUtils.checkWrkCenterFound(CSCSecurityInfoHelper.getGroupCodeFromRequest(), employeesEntity.getWrkCode())){
            //                throw new ItemNotFoundException();
            //            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTOCivilRealCivil(employeesEntity);

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

    public IEmployeesDTO getSimpleEmployeeByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                list =
EM().createNamedQuery("EmployeesEntity.getByRealCivilId").setHint("toplink.refresh", "true").setParameter("realCivilId",
                                                                                                          realCivilId).setParameter("minCode",
                                                                                                                                    minCode).setParameter("activeFlag",
                                                                                                                                                          IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOCodeName(employeesEntity);

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

    /*
 * get hired hemployee in All ministries with
 * real_Civil_ID
 * &
 * hireStatus in(s1...s9) parameters
 * &
 * active_flag=1
 */

    public IEmployeesDTO getByRealCivilIdAllMinistries(Long realCivilId) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                list =
EM().createNamedQuery("EmployeesEntity.getByRealCivilIdAllMinistries").setHint("toplink.refresh",
                                                                               "true").setParameter("realCivilId",
                                                                                                    realCivilId).setParameter("s1",
                                                                                                                              IEMPConstant.EMP_STATUS_EMPLOYMENT).setParameter("s2",
                                                                                                                                                                               IEMPConstant.EMP_STATUS_MOVING).setParameter("s3",
                                                                                                                                                                                                                            IEMPConstant.EMP_STATUS_DELEGATION).setParameter("s4",
                                                                                                                                                                                                                                                                             IEMPConstant.EMP_STATUS_LOANINIG).setParameter("s5",
                                                                                                                                                                                                                                                                                                                            IEMPConstant.EMP_STATUS_MISSION).setParameter("s6",
                                                                                                                                                                                                                                                                                                                                                                          IEMPConstant.EMP_STATUS_GRANT).setParameter("s7",
                                                                                                                                                                                                                                                                                                                                                                                                                      IEMPConstant.EMP_STATUS_VACATION).setParameter("s8",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                     IEMPConstant.EMP_STATUS_PRISONER_LOST).setParameter("s9",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM).setParameter("activeFlag",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
            // Add Employee Salary
            if (employeesEntity.getSalEmpSalaryElementsEntityList() != null) {
                List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList = new ArrayList<ISalEmpSalaryElementsDTO>();
                for (EmpSalEmpSalaryElementsEntity entity : employeesEntity.getSalEmpSalaryElementsEntityList()) {
                    if (entity.getUntilDate() == null) {
                        //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
                        empSalaryElementsDTOList.add(getSalEmpSalaryElementsDTOFromEntity(entity));
                    }
                }
                employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
            }
            //End of add list of document and procedure

            // Add EmployeeExtraData
            if (employeesEntity.getEmployeeExtraDataEntityList() != null &&
                !employeesEntity.getEmployeeExtraDataEntityList().isEmpty()) {
                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
                List<IBasicDTO> employeeExtraDataEntityList = new ArrayList<IBasicDTO>();
                for (EmployeeExtraDataEntity entity : employeesEntity.getEmployeeExtraDataEntityList()) {
                    employeeExtraDataEntityList.add(new EmployeeExtraDataDTO(entity));
                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());

                    }
                }
                employeesDTO.setEmployeeExtraDataDTOList(employeeExtraDataEntityList);
            }

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

    //added by mohamed sayed at 13-5-2015 for new status and serve

    public IEmployeesDTO getByRealCivilIdAllMinByRecordDesc(Long realCivilId) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            String hireStatus = EmpUtils.getStatusForHire();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus +
                                ")  and o.recordDescCode=1 ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                /// commented By M.abdelsabour to apply new DataFilteration Sol
                //                //added by Technical Team [m.sayed] at 31-3-2016
                //                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in(" + wrkcode + ")");
                //                }


                list =
EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                            IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
            // Add Employee Salary
            if (employeesEntity.getSalEmpSalaryElementsEntityList() != null) {
                List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList = new ArrayList<ISalEmpSalaryElementsDTO>();
                for (EmpSalEmpSalaryElementsEntity entity : employeesEntity.getSalEmpSalaryElementsEntityList()) {
                    if (entity.getUntilDate() == null) {
                        //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
                        empSalaryElementsDTOList.add(getSalEmpSalaryElementsDTOFromEntity(entity));
                    }
                }
                employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
            }
            //End of add list of document and procedure

            // Add EmployeeExtraData
            if (employeesEntity.getEmployeeExtraDataEntityList() != null &&
                !employeesEntity.getEmployeeExtraDataEntityList().isEmpty()) {
                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
                List<IBasicDTO> employeeExtraDataEntityList = new ArrayList<IBasicDTO>();
                for (EmployeeExtraDataEntity entity : employeesEntity.getEmployeeExtraDataEntityList()) {
                    employeeExtraDataEntityList.add(new EmployeeExtraDataDTO(entity));
                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());

                    }
                }
                employeesDTO.setEmployeeExtraDataDTOList(employeeExtraDataEntityList);
            }

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


    public List<IBasicDTO> getEmpByEmpSearch(EmpSearchRecodDesc searchDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {

            List<EmployeesEntity> list = null;
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();

            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.activeFlag=:activeflag and o.recordDescCode=1  ");
                if (searchDTO.getRealCivilID() != null) {
                    queryStr.append(" and o.realCivilId = :realCivilId ");
                }
                if (searchDTO.getCivilID() != null) {
                    queryStr.append(" and o.civilId = :civilId ");
                }
                if (searchDTO.getHireStatus() != null && searchDTO.getHireStatus() != "") {
                    queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + searchDTO.getHireStatus() + ")");
                }
                Query query = EM().createQuery(queryStr.toString());
                query.setParameter("activeflag", IEMPConstants._EMP_ACTIVE_STATUS);
                if (searchDTO.getRealCivilID() != null) {
                    query.setParameter("realCivilId", searchDTO.getRealCivilID());
                }
                if (searchDTO.getCivilID() != null) {
                    query.setParameter("civilId", searchDTO.getCivilID());
                }
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }
            for (EmployeesEntity employeesEntity : list) {
                //if (list != null && list.size() > 0) {
                //  employeesEntity = list.get(0);
                //}

                if (employeesEntity == null) {
                    throw new ItemNotFoundException();
                }
                IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
                // Add Employee Salary
                if (employeesEntity.getSalEmpSalaryElementsEntityList() != null) {
                    List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList =
                        new ArrayList<ISalEmpSalaryElementsDTO>();
                    for (EmpSalEmpSalaryElementsEntity entity : employeesEntity.getSalEmpSalaryElementsEntityList()) {
                        if (entity.getUntilDate() == null) {
                            //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
                            empSalaryElementsDTOList.add(getSalEmpSalaryElementsDTOFromEntity(entity));
                        }
                    }
                    employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
                }
                //End of add list of document and procedure

                // Add EmployeeExtraData
                if (employeesEntity.getEmployeeExtraDataEntityList() != null &&
                    !employeesEntity.getEmployeeExtraDataEntityList().isEmpty()) {
                    employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
                    List<IBasicDTO> employeeExtraDataEntityList = new ArrayList<IBasicDTO>();
                    for (EmployeeExtraDataEntity entity : employeesEntity.getEmployeeExtraDataEntityList()) {
                        employeeExtraDataEntityList.add(new EmployeeExtraDataDTO(entity));
                        if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
                            employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
                        } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
                            employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
                        } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
                            employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
                        } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
                            employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
                        } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
                            employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());

                        }
                    }
                    employeesDTO.setEmployeeExtraDataDTOList(employeeExtraDataEntityList);
                }
                listDTO.add(employeesDTO);
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

    public IEmployeesDTO getCurrentEmpFinancialData(IEntityKey id1) throws DataBaseException,
                                                                           SharedApplicationException {

        IEmployeesEntityKey id = (IEmployeesEntityKey)id1;

        StringBuilder queryStr = new StringBuilder("SELECT o FROM EmployeesEntity o WHERE o.civilId = ?1");
        /// commented By M.abdelsabour to apply new DataFilteration Sol
        //        //added by Technical Team [m.sayed] at 31-3-2016
        //        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            queryStr.append(" and o.wrkCode in(" + wrkcode + ")");
        //        }
        List<EmployeesEntity> list =
            EM().createQuery(queryStr.toString()).setHint("toplink.refresh", "true").setParameter(1,
                                                                                                  id.getCivilId()).getResultList();
        if (list == null || list.size() == 0)
            throw new ItemNotFoundException();

        EmployeesEntity employeesEntity = list.get(0);

        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
        // Add Employee Salary
        if (employeesEntity.getSalEmpSalaryElementsEntityList() != null) {
            List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList = new ArrayList<ISalEmpSalaryElementsDTO>();


            for (EmpSalEmpSalaryElementsEntity entity : employeesEntity.getSalEmpSalaryElementsEntityList()) {
                if (entity.getUntilDate() == null &&
                    entity.getSalElementGuidesEntity().getElmtypeCode().equals(ISalConstants.ELEMENT_GUIDE_TYPE_RAISE)) {
                    //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
                    empSalaryElementsDTOList.add(getSalEmpSalaryElementsDTOFromEntity(entity));
                }
            }
            employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
        }

        return employeesDTO;
    }

    /**
     * Get all employee match search criteria
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        EmployeeSearchDTO employeeSearchDTO = (EmployeeSearchDTO)basicDTO;
        StringBuilder ejbql = new StringBuilder("select o from EmployeesEntity o ");
        if (employeeSearchDTO.getCaderCode() != null || employeeSearchDTO.getGroupCode() != null ||
            employeeSearchDTO.getDegreeCode() != null) {
            ejbql.append(" join o.salEmpSalaryElementsEntityList empSalElementsList ");
        }
        ejbql.append(" WHERE o.civilId=o.civilId ");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId='" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getRealCivilId() != null)
            ejbql.append(" AND  o.realCivilId='" + employeeSearchDTO.getRealCivilId() + "'");
        if (employeeSearchDTO.getFirstName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.firstName LIKE '" +
            //                         employeeSearchDTO.getFirstName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.firstName",
                                                                             employeeSearchDTO.getFirstName()) +
                         " ) ");

        if (employeeSearchDTO.getSecondName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.secondName LIKE '" +
            //                         employeeSearchDTO.getSecondName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.secondName",
                                                                             employeeSearchDTO.getSecondName()) +
                         " ) ");
        if (employeeSearchDTO.getThirdName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.thirdName LIKE '" +
            //                         employeeSearchDTO.getThirdName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.thirdName",
                                                                             employeeSearchDTO.getThirdName()) +
                         " ) ");
        if (employeeSearchDTO.getLastName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.lastName LIKE '" +
            //                         employeeSearchDTO.getLastName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.lastName",
                                                                             employeeSearchDTO.getLastName()) + " ) ");
        if (employeeSearchDTO.getFamilyName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.familyName LIKE '" +
            //                         employeeSearchDTO.getFamilyName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.familyName",
                                                                             employeeSearchDTO.getFamilyName()) +
                         " ) ");
        if (employeeSearchDTO.getEnglishName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.englishName LIKE '" +
            //                         employeeSearchDTO.getEnglishName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.englishName",
                                                                             employeeSearchDTO.getEnglishName()) +
                         " ) ");
        if (employeeSearchDTO.getBirthDate() != null)
            ejbql.append(" AND o.citizensResidentsEntity.birthDate='" + employeeSearchDTO.getBirthDate() + "'");
        if (employeeSearchDTO.getGenderTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.gentypeCode=" + employeeSearchDTO.getGenderTypeCode() + "");
        if (employeeSearchDTO.getMaritalStatusCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.marstatusCode=" + employeeSearchDTO.getMaritalStatusCode() +
                         "");
        if (employeeSearchDTO.getPhonesNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
        if (employeeSearchDTO.getMobileNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
        if (employeeSearchDTO.getReligionCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.religionCode=" + employeeSearchDTO.getReligionCode() + "");

        if (employeeSearchDTO.getNationality() != null)
            ejbql.append(" AND o.citizensResidentsEntity.nationality=" + employeeSearchDTO.getNationality() + "");
        if (employeeSearchDTO.getKuwaiti() != null) {
            if (employeeSearchDTO.getKuwaiti()) {
                ejbql.append(" AND o.citizensResidentsEntity.nationality=" + ISystemConstant.KUWAIT_NATIONALITY + "");
            } else {
                ejbql.append(" AND o.citizensResidentsEntity.nationality<>" + ISystemConstant.KUWAIT_NATIONALITY + "");
            }
        }
        if (employeeSearchDTO.getResidentTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.restypeCode=" + employeeSearchDTO.getResidentTypeCode() + "");
        if (employeeSearchDTO.getEndResidentDate() != null)
            ejbql.append(" AND o.citizensResidentsEntity.endResidentDate='" + employeeSearchDTO.getEndResidentDate() +
                         "'");
        if (employeeSearchDTO.getMapCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.mapCode='" + employeeSearchDTO.getMapCode() + "'");
        if (employeeSearchDTO.getCapstatusCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.capstatusCode=" + employeeSearchDTO.getCapstatusCode() + "");
        if (employeeSearchDTO.getSpecialCaseTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.spccsetypeCode=" +
                         employeeSearchDTO.getSpecialCaseTypeCode() + "");
        if (employeeSearchDTO.getPassportNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.passportNo LIKE '" + employeeSearchDTO.getPassportNo() + "'");
        /*Search by job criteria*/
        if (employeeSearchDTO.getBankbranchCode() != null && !employeeSearchDTO.getBankbranchCode().equals("")) {
            String[] str = employeeSearchDTO.getBankbranchCode().split("\\*");
            ejbql.append(" AND o.bnkbranchCode=" + Long.parseLong(str[1]) + " AND o.bankCode=" +
                         Long.parseLong(str[0]) + "");
        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        if (employeeSearchDTO.getTechJobCode() != null)
            ejbql.append(" AND o.techJobCode='" + employeeSearchDTO.getTechJobCode() + "'");
        if (employeeSearchDTO.getJobCode() != null)
            ejbql.append(" AND o.jobCode LIKE '" + employeeSearchDTO.getJobCode() + "'");
        if (employeeSearchDTO.getAccountNo() != null)
            ejbql.append(" AND o.accountNo LIKE '" + employeeSearchDTO.getAccountNo() + "'");
        if (employeeSearchDTO.getCscFileNo() != null)
            ejbql.append(" AND o.cscFileNo LIKE '" + employeeSearchDTO.getCscFileNo() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEndWorkingDate() != null)
            ejbql.append(" AND o.endJobDate='" + employeeSearchDTO.getEndWorkingDate() + "'");
        if (employeeSearchDTO.getHireDate() != null)
            ejbql.append(" AND o.hireDate='" + employeeSearchDTO.getHireDate() + "'");
        if (employeeSearchDTO.getEmpHireStages() != null)
            ejbql.append(" AND o.hireStagesEntity.hirstageCode=" + employeeSearchDTO.getEmpHireStages() + "");
        if (employeeSearchDTO.getEmpHireStatus() != null)
            ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
        if (employeeSearchDTO.isEmployment())
            ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                         IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                         IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
                         IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                         IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");
        if (employeeSearchDTO.getEmpHireTypes() != null)
            ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
        ////////////////////////////////
        //ADDED BY TAHA ABDUL MEJID @ 17/8/08
        if (employeeSearchDTO.getBgtTypesCode() != null)
            ejbql.append(" AND o.bgtTypesEntity.typeCode=" + employeeSearchDTO.getBgtTypesCode() + "");
        if (employeeSearchDTO.getBgtProgramsCode() != null)
            ejbql.append(" AND o.bgtProgramsEntity.prgCode LIKE '" + employeeSearchDTO.getBgtProgramsCode() + "'");
        ////////////////////////////////
        //ADDED BY TAHA ABDUL MEJID @ 5/10/09
        if (employeeSearchDTO.getDegreeCode() != null)
            ejbql.append(" AND (empSalElementsList.salElementGuidesEntity.elmguideCodeDegree = " +
                         employeeSearchDTO.getDegreeCode() + " and empSalElementsList.untilDate is null ) ");
        else if (employeeSearchDTO.getGroupCode() != null)
            ejbql.append(" AND (empSalElementsList.salElementGuidesEntity.salElementGuidesEntityDegree.elmguideCodeDegree = " +
                         employeeSearchDTO.getGroupCode() + " and empSalElementsList.untilDate is null ) ");
        else if (employeeSearchDTO.getCaderCode() != null)
            ejbql.append(" AND (empSalElementsList.salElementGuidesEntity.salElementGuidesEntityDegree.salElementGuidesEntityDegree.elmguideCodeDegree = " +
                         employeeSearchDTO.getCaderCode() + " and empSalElementsList.untilDate is null ) ");
        if (employeeSearchDTO.getMasterCode() != null &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecisionNumber() == null) &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecyearCode() == null) &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDectypeCode() == null))

            ejbql.append(" AND o.civilId NOT IN ( SELECT empList.civilId FROM  EmpDecisionsEntity  empList where " +
                         "  empList.decisionsEntity.decisionNumber=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecisionNumber() +
                         "  AND empList.decisionsEntity.dectypeCode=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDectypeCode() +
                         "  AND empList.decisionsEntity.decyearCode=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecyearCode() + ")");
        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO(entity);
            dto.setChecked(employeeSearchDTO.getChecked());
            listDTO.add(dto);
        }
        return listDTO;
    }

    /**
     * Get all employee match search criteria
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> simpleSearch(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        StringBuffer ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        //        employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        ejbql = new StringBuffer("select o from EmployeesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(employeeSearchDTO.getActiveFlag());
        } else {
            ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
        }
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        //        if (employeeSearchDTO.getMinistryCode() != null) {
        //            ejbql.append(" AND o.minCode= " + employeeSearchDTO.getMinistryCode());
        //        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }


        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null)
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            if (employeeSearchDTO.isEmployment())
                //commented by mohamed sayed at 25-8-2015
                //                ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                //                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION +
                //                             "," + IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                //                             IEMPConstants.EMP_STATUS_FREEZING + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
                //                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                //                             IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");
                //added by mohamed sayed at 25-8-2015
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHireWithoutDELEGATION() + ")");
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

        if (employeeSearchDTO.getMinistryCode() != null && !employeeSearchDTO.getMinistryCode().equals(-100L)) {
            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
        }
        //updated by A.Nour 02-23-2016 because it raise null pointer exception if security down
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        String wrkcode = "";

        if (employeeSearchDTO.getFilterType() == 1) {
            //apply data filtration for work center related to logged in group
            wrkcode = getWrkcenterDataFilter(false);
        }
        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //        else if (employeeSearchDTO.getFilterType() == 0) {
        //            //apply data filtration for un structured work center if logged in group one of un structured work centers
        //            wrkcode = initWrkcenterTree();
        //        }
        if (wrkcode != null && !wrkcode.isEmpty()) {
            ejbql.append(" and o.wrkCode in( " + wrkcode + ")");
        }

        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        //        else if (employeeSearchDTO.getMinistryCode() != -100L) {
        //            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        //        }
        List<EmployeesEntity> list = null;
        System.out.println("EmployeesDAO.simpleSearch ejbql.toString() = " + ejbql.toString());
        if (ejbql != null)
            list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        Long startTime = System.currentTimeMillis();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getSimpleEmployeesDTO(entity));
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("Hany-Test.........time spent .................................... >> " +
                           (endTime - startTime) / 1000 + "sec");
        return listDTO;
    }

    /**
     * Get all employee match search criteria
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> simpleSearchWithPaging(IBasicDTO basicDTO,
                                                  IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                       SharedApplicationException {


        List<EmployeesEntity> list = null;

        list = buildSearchQueryWithPaging(basicDTO, requestDTO);
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;
    }

    /**
     * Get all employee match search criteria
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> simpleSearchEmpWithPaging(IBasicDTO basicDTO,
                                                     IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                          SharedApplicationException {


        List<EmployeesEntity> list = null;
        list = buildSimpleSearchEmpQueryWithPaging(basicDTO, requestDTO);
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity, true));
        }
        return listDTO;
    }

    public Long simpleSearchCountWithPaging(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return buildSearchCountQueryWithPaging(basicDTO);
    }


    public Long simpleSearchEmpCountWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        return buildSearchEmpCountQueryWithPaging(basicDTO);
    }


    /**
     * Get all employee match search criteria
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> simpleSearchBasic(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildSearchQuery(basicDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }


    public List<IBasicDTO> simpleSearchFullData(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        List<EmployeesEntity> list = null;
        list = buildSearchQuery(basicDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;

    }

    public List<IBasicDTO> simpleSearchAllMinistriesFullData(IBasicDTO basicDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        List<EmployeesEntity> list = null;
        list = buildSearchQueryForAllMinistries(basicDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;

    }

    /**
     * Get all employee match search criteria
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> simpleSearchBasicWithPaging(IBasicDTO basicDTO,
                                                       IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                            SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildSearchQueryWithPaging(basicDTO, requestDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }

    public List<IBasicDTO> simpleSearchBasicWithPagingAtt(IBasicDTO basicDTO,
                                                       IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                            SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildSearchQueryWithPagingAtt(basicDTO, requestDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }

    public Long simpleSearchCountBasicWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        return buildSearchCountQueryWithPaging(basicDTO);
    }
    public Long simpleSearchCountBasicWithPagingAtt(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        return buildSearchCountQueryWithPagingAtt(basicDTO);
    }

    private List<EmployeesEntity> buildSearchQuery(IBasicDTO basicDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId= '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
        //        if (employeeSearchDTO.getEmpHireStatusList() != null) {
        //            StringBuilder statusCodeStr = new StringBuilder("");
        //            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
        //                statusCodeStr.append(status + ",");
        //            }
        //            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");
        //
        //        } else {
        //
        //            if (employeeSearchDTO.isEmployment()) {
        //                ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
        //                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION +
        //                             "," + IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
        //                             IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM +
        //                             "," + IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION +
        //                             ") ");
        //            } else {
        //                if (employeeSearchDTO.getEmpHireStatus() != null)
        //                    ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
        //            }
        //        }
        if (employeeSearchDTO.getEmpHireStatusList() != null) {
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment()) {
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
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


            List<EmployeesEntity> list = null;

            System.out.println("EmployeesDAO.buildSearchQuery :: " + ejbql.toString());
            if (ejbql != null)
                list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        }
        return null;
    }

    private List<EmployeesEntity> buildSearchQueryWithPaging(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {




        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

             if(employeeSearchDTO.getKaderCode()!= null && employeeSearchDTO.getKaderCode().equals(348l)){
                 return  getSearchEmp(basicDTO, requestDTO);
             }else{
        //        if (employeeSearchDTO.getMinistryCode() != null) {
        //            if (employeeSearchDTO.getMinistryCode() == -100L) {
        //            } else {
        //                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //            }
        //        } else {
        //            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //        }
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //        else {
        //            //added by Technical Team [m.sayed] at 31-3-2016
        //            // stroy ID CSC-17343  work Center data filter
        //            String wrkcode = initWrkcenterTree();
        //            if (wrkcode != null && !wrkcode.isEmpty()) {
        //                ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //            }
        //        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        //        else{
        //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        //            }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else if(employeeSearchDTO.isEosFlag()){
                    ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                                IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                                IEMPConstants.EMP_STATUS_FREEZING + "," + IEMPConstants.EMP_STATUS_END_JOB +")");
             }  else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
            if (employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
        }

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        if (employeeSearchDTO.getRecordDescCode() != null) {
            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
        }
        if (employeeSearchDTO.getEmploymentStage() != null)
            ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage().toString() + ")");
        //////////CH HR 762/////////////////////
        if (employeeSearchDTO.getHireDateFrom() != null)
            ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
        if (employeeSearchDTO.getHireDateTO() != null)
            ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");


        ejbql.append(" and o.recordDescCode = 1 ");
        if(employeeSearchDTO.isInAllMinistries()){
                    // APPEND NOTHING ---to get employees regardless the min code
        }else if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
                ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
                System.out.println("EmpDAO :: MinCode :: Test :: Hany Omar" + employeeSearchDTO.getMinistryCode());
        }


//        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
//            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
//            System.out.println("EmpDAO :: MinCode :: Test :: Hany Omar" + employeeSearchDTO.getMinistryCode());
//        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        //edited by Hany Omar [B.Horse]
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }
        // Added By MLotfy, apply data level sequrity
        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        if (excludedWorkCentersStr != null) {
            ejbql.append(excludedWorkCentersStr);
        }

        List<EmployeesEntity> list = null;
        System.out.println("Full Search Query :: TEST :" + ejbql.toString());
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }

        Query query = null;

        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();

        }
        System.out.println("buildSearchQueryWithPaging :: list.size() : " + ( list == null ? " NULL " :  list.size() )
                           +" , employeeSearchDTO.getCivilId() = "+ employeeSearchDTO.getCivilId()
                           +" , employeeSearchDTO.getEmpName() = "+employeeSearchDTO.getEmpName() );
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;
             }


    }

    private List<EmployeesEntity> buildSearchQueryWithPagingAtt(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        //        if (employeeSearchDTO.getMinistryCode() != null) {
        //            if (employeeSearchDTO.getMinistryCode() == -100L) {
        //            } else {
        //                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //            }
        //        } else {
        //            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //        }
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //        else {
        //            //added by Technical Team [m.sayed] at 31-3-2016
        //            // stroy ID CSC-17343  work Center data filter
        //            String wrkcode = initWrkcenterTree();
        //            if (wrkcode != null && !wrkcode.isEmpty()) {
        //                ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //            }
        //        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        //        else{
        //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        //            }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
            if (employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
        }

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        if (employeeSearchDTO.getRecordDescCode() != null) {
            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
        }
        if (employeeSearchDTO.getEmploymentStage() != null)
            ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage().toString() + ")");
        //////////CH HR 762/////////////////////
        if (employeeSearchDTO.getHireDateFrom() != null)
            ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
        if (employeeSearchDTO.getHireDateTO() != null)
            ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");


      //  ejbql.append(" and o.recordDescCode = 1 ");
        if(employeeSearchDTO.isInAllMinistries()){
                    // APPEND NOTHING ---to get employees regardless the min code
        }else if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
                ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
                System.out.println("EmpDAO :: MinCode :: Test :: Hany Omar" + employeeSearchDTO.getMinistryCode());
        }


    //        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
    //            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
    //            System.out.println("EmpDAO :: MinCode :: Test :: Hany Omar" + employeeSearchDTO.getMinistryCode());
    //        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        //edited by Hany Omar [B.Horse]
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }
        // Added By MLotfy, apply data level sequrity
        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        if (excludedWorkCentersStr != null) {
            ejbql.append(excludedWorkCentersStr);
        }

        List<EmployeesEntity> list = null;
        System.out.println("Full Search Query :: TEST :" + ejbql.toString());
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }

        Query query = null;

        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();

        }
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;


    }

    private List<EmployeesEntity> buildSearchQueryForAllMinistries(IBasicDTO basicDTO) throws DataBaseException,
                                                                                              SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //        else {
        //            //added by Technical Team [m.sayed] at 31-3-2016
        //            // stroy ID CSC-17343  work Center data filter
        //            String wrkcode = initWrkcenterTree();
        //            if (wrkcode != null && !wrkcode.isEmpty()) {
        //                ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //            }
        //        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        //        else{
        //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        //            }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        }  else if(employeeSearchDTO.isEosFlag()){
                    ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                                IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                                IEMPConstants.EMP_STATUS_FREEZING + "," + IEMPConstants.EMP_STATUS_END_JOB +")");
             }  else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
            if (employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
        }

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        if (employeeSearchDTO.getRecordDescCode() != null) {
            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
        }
        if (employeeSearchDTO.getEmploymentStage() != null)
            ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage().toString() + ")");
        //////////CH HR 762/////////////////////
        if (employeeSearchDTO.getHireDateFrom() != null)
            ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
        if (employeeSearchDTO.getHireDateTO() != null)
            ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
            ejbql.append(" and o.minCode = " + employeeSearchDTO.getMinistryCode() + "");
        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }
        // Added By MLotfy, apply data level sequrity
        //        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        //        if (excludedWorkCentersStr != null) {
        //            ejbql.append(excludedWorkCentersStr);
        //        }

        List<EmployeesEntity> list = null;

        System.out.println("EmployeesDAO.buildSearchQueryForAllMinistries :: " + ejbql.toString());
        if (ejbql != null)
            list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;


    }

    private List<EmployeesEntity> buildSimpleSearchEmpQueryWithPaging(IBasicDTO basicDTO,
                                                                      IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                           SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        if (employeeSearchDTO.getMinistryCode() != null) {
            if (employeeSearchDTO.getMinistryCode() == -100L) {
            } else {
                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
            }
        } else {
            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        }
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        //        if (employeeSearchDTO.getCivilId() != null)
        //            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        //        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
        //            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
        //            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
        //            //                         employeeSearchDTO.getEmpName() + "')");
        //            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
        //                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
        //                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        //
        //        }
        //        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
        //            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
        //            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        //        }
        //        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))
        //
        //            //By MLotfy new search
        //            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
        //            ejbql.append(" AND (" +
        //                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
        //                         " ) ");
        //
        //        if (employeeSearchDTO.getStartWorkingDate() != null)
        //            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        //        else {
        //            ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        //        }
        //        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))
        //
        //            //By MLotfy new search
        //            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
        //            ejbql.append(" AND (" +
        //                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
        //                         " ) ");
        //
        //        if (employeeSearchDTO.getEmpHireStage() != null &&
        //            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
        //            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        //        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
        //            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
        //                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
        //        if (employeeSearchDTO.getNationalityType() != null &&
        //            !employeeSearchDTO.getNationalityType().equals(ISystemConstant.VIRTUAL_VALUE))
        //            ejbql.append(" AND o.citizensResidentsEntity.nationality" +
        //                         ((employeeSearchDTO.getNationalityType().equals(ISystemConstant.NATIONALITY_KUWAITI)) ?
        //                          "=" + ISystemConstant.KUWAIT_NATIONALITY + "" :
        //                          ((employeeSearchDTO.getNationalityType().equals(ISystemConstant.NATIONALITY_NON_KUWAITI)) ?
        //                           "<>" + ISystemConstant.KUWAIT_NATIONALITY + "" : " IS NOT NULL") + "") + "");
        //        if (employeeSearchDTO.getGenderType() != null &&
        //            !employeeSearchDTO.getGenderType().equals(ISystemConstant.VIRTUAL_VALUE))
        //            ejbql.append(" AND o.citizensResidentsEntity.gentypeCode" +
        //                         ((employeeSearchDTO.getGenderType().equals(ISystemConstant.GENDER_UNDEFINED)) ?
        //                          " IS NOT NULL " : "=" + employeeSearchDTO.getGenderType()));
        //        if (employeeSearchDTO.getEmpHireStatusList() != null) {
        //            StringBuilder statusCodeStr = new StringBuilder("");
        //            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
        //                statusCodeStr.append(status + ",");
        //            }
        //            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");
        //
        //        } else {
        //            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
        //                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
        //
        //
        //            if (employeeSearchDTO.isEmployment())
        //                //commented by
        //                //                ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
        //                //                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION +
        //                //                             "," + IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_GRANT +
        //                //                             "," + IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
        //                //                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
        //                //                             IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + "," +
        //                //                             IEMPConstants.EMP_STATUS_FREEZING + ") ");
        //                //add by  26/8/2015
        //                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHireWithoutDELEGATION() + " )");
        //        }

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        }
        //    /// commented By M.abdelsabour to apply new DataFilteration Sol
        //        //added by Technical Team [m.sayed] at 31-3-2016
        //        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        //        if (employeeSearchDTO.getRecordDescCode() != null) {
        //            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
        //        }
        //        if (employeeSearchDTO.getEmploymentStage() != null)
        //            ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage().toString() + ")");
        //////////CH HR 762/////////////////////
        if (employeeSearchDTO.getHireDateFrom() != null)
            ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
        if (employeeSearchDTO.getHireDateTO() != null)
            ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
            ejbql.append(" and o.minCode = " + employeeSearchDTO.getMinistryCode() + "");
        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }

        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "," +
                     IEMPConstants.EMP_STATUS_FREEZING + ")   ");
        // Added By MLotfy, apply data level sequrity
        //        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        //        if (excludedWorkCentersStr != null) {
        //            ejbql.append(excludedWorkCentersStr);
        //        }

        List<EmployeesEntity> list = null;
        System.out.println("EmployeesDAO.buildSimpleSearchEmpQueryWithPaging :: " + ejbql.toString());
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }

        Query query = null;

        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();

        }
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;


    }


    private Long buildSearchCountQueryWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        if(employeeSearchDTO.getKaderCode()!= null && employeeSearchDTO.getKaderCode().equals(348l)){
            return  getSearchEmpCount(basicDTO);
        }else{

            //        if (employeeSearchDTO.getMinistryCode() != null) {
            //            if (employeeSearchDTO.getMinistryCode() == -100L) {
            //            } else {
            //                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
            //            }
            //        } else {
            //            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
            //        }
            ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
                //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
                //                         employeeSearchDTO.getEmpName() + "')");
                ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) + " ) ");

            }
            if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
                String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
            }
            if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                             " ) ");

            if (employeeSearchDTO.getStartWorkingDate() != null)
                ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
            if (employeeSearchDTO.getEmpHireTypes() != null &&
                !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
                ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
                ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
            }
            //        else{
            //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
            //            }
            if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                             " ) ");

            if (employeeSearchDTO.getEmpHireStage() != null &&
                !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
                ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
            if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
                ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                             " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

            } else if(employeeSearchDTO.isEosFlag()){
                    ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                                IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                                IEMPConstants.EMP_STATUS_FREEZING + "," + IEMPConstants.EMP_STATUS_END_JOB +")");
             }  else {
                if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                    ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
                //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
                if (employeeSearchDTO.isEmployment())
                    ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
            }
            //added by tech.Team[m.sayed] at 2-12-2015         CSC-9091
            if (employeeSearchDTO.getActiveFlag() != null) {
                ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
            } else {
                ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
            }
            if (employeeSearchDTO.getRecordDescCode() != null) {
                ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
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
                if(employeeSearchDTO.isInAllMinistries()){
                            // APPEND NOTHING ---to get employees regardless the min code
                }else if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
                ejbql.append(" and o.minCode = " + employeeSearchDTO.getMinistryCode() + "");
            }
            //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
            //edited by Hany Omar [B.Horse]
            else {
                ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
            }

            /// commented By M.abdelsabour to apply new DataFilteration Sol
            //        //added by Technical Team [m.sayed] at 31-3-2016
            //        // stroy ID CSC-17343  work Center data filter
            //        String wrkcode = initWrkcenterTree();
            //        if (wrkcode != null && !wrkcode.isEmpty()) {
            //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
            //        }
            // Added By MLotfy, apply data level sequrity
            String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
            if (excludedWorkCentersStr != null) {
                ejbql.append(excludedWorkCentersStr);
            }

             ejbql.append(" and  o.recordDescCode =1 ");
            System.out.println("EmployeesDAO.buildSearchCountQueryWithPaging :: " + ejbql.toString());


            Query query = EM().createQuery(ejbql.toString());

            return (Long)query.getSingleResult();

        }


    }


    private Long buildSearchCountQueryWithPagingAtt(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

        //        if (employeeSearchDTO.getMinistryCode() != null) {
        //            if (employeeSearchDTO.getMinistryCode() == -100L) {
        //            } else {
        //                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //            }
        //        } else {
        //            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //        }
        ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        //        else{
        //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        //            }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
            if (employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
        }
        //added by tech.Team[m.sayed] at 2-12-2015         CSC-9091
        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        if (employeeSearchDTO.getRecordDescCode() != null) {
            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
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
            if(employeeSearchDTO.isInAllMinistries()){
                        // APPEND NOTHING ---to get employees regardless the min code
            }else if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
            ejbql.append(" and o.minCode = " + employeeSearchDTO.getMinistryCode() + "");
        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        //edited by Hany Omar [B.Horse]
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }

        /// commented By M.abdelsabour to apply new DataFilteration Sol
        //        //added by Technical Team [m.sayed] at 31-3-2016
        //        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        // Added By MLotfy, apply data level sequrity
        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        if (excludedWorkCentersStr != null) {
            ejbql.append(excludedWorkCentersStr);
        }

        // ejbql.append(" and  o.recordDescCode =1 ");
        System.out.println("EmployeesDAO.buildSearchCountQueryWithPaging :: " + ejbql.toString());


        Query query = EM().createQuery(ejbql.toString());

        return (Long)query.getSingleResult();


    }
    private Long buildSearchEmpCountQueryWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

        //        if (employeeSearchDTO.getMinistryCode() != null) {
        //            if (employeeSearchDTO.getMinistryCode() == -100L) {
        //            } else {
        //                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //            }
        //        } else {
        //            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        //        }
        ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }

        // commented By M.abdelsabour to apply Data Filteration Sol
        //added by B.Horse Team  at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in( " + wrkcode + ")");
        //        }


        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        } else {
            ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment()) {
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            } else {
                ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "," +
                             IEMPConstants.EMP_STATUS_FREEZING + ")   ");
            }
            //commented by Tech.Team[m.sayed] according to Integration team request 2-12-2015
            //            if (employeeSearchDTO.isEmployment())
            //                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
        }
        //commented by tech.Team[m.sayed] at 2-12-2015  CSC-9091
        //        if (employeeSearchDTO.getActiveFlag() != null) {
        //            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        //        }
        //added by tech.Team[m.sayed] at 2-12-2015         CSC-9091
        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        //        if (employeeSearchDTO.getRecordDescCode() != null) {
        //            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
        //        }

        if (employeeSearchDTO.getEmploymentStage() != null)
            ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage() + ")");
        //////////CH HR 762/////////////////////
        if (employeeSearchDTO.getHireDateFrom() != null)
            ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
        if (employeeSearchDTO.getHireDateTO() != null)
            ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
            ejbql.append(" and o.minCode = " + employeeSearchDTO.getMinistryCode() + "");
        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }


        // Added By MLotfy, apply data level sequrity
        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        if (excludedWorkCentersStr != null) {
            ejbql.append(excludedWorkCentersStr);
        }


        System.out.println("EmployeesDAO.buildSearchEmpCountQueryWithPaging :: " + ejbql.toString());


        Query query = EM().createQuery(ejbql.toString());

        return (Long)query.getSingleResult();


    }

    /**
     * search hired employees and return list of employees whose civilIDs are not in the
     * @param basicDTO  the IEmployeesDTO search criteria DTO
     * @param List salEmpSalaryElementsDTOList the list of employees to be not included in the search result set
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchFilterEmployee(IBasicDTO basicDTO,
                                                List<IBasicDTO> salEmpSalaryElementsDTOList) throws DataBaseException,
                                                                                                    SharedApplicationException {
        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }

        if (salEmpSalaryElementsDTOList != null && salEmpSalaryElementsDTOList.size() > 0) {

            for (int i = 0; i < salEmpSalaryElementsDTOList.size(); i++) {

                ejbql.append("AND o.civilId <> '" +
                             ((IEmployeesEntityKey)(((SalEmpSalaryElementsDTO)salEmpSalaryElementsDTOList.get(i)).getEmployeesDTO()).getCode()).getCivilId() +
                             "' ");
            }
        }

        if (employeeSearchDTO.isEmployment())
            ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                         IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                         IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                         IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                         IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");


        List<EmployeesEntity> list = null;
        System.out.println(ejbql.toString());
        if (ejbql != null)
            list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;
    }

    /**
     * Get the MaxId of AbilitiesEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmployeesEntity.findNewId").getSingleResult();
            if (id != null) {
                return id + 1;
            } else {
                return new Long(1);
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> search(Long hirtypeCode) throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.searchByHireType").setParameter("hirtypeCode",
                                                                                   hirtypeCode).getResultList();
        for (EmployeesEntity employeesEntity : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * Hany Omar 16/2/2014
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     */

    public List<IBasicDTO> filterByHireType(Long hirtypeCode) throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.filterByHireType").setParameter("hirtypeCode",
                                                                                   hirtypeCode).setParameter("hirstatusCode",
                                                                                                             IEMPConstants._EMP_STATUS_CANDIDATE).setParameter("val1",
                                                                                                                                                               IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("val2",
                                                                                                                                                                                                                                  IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                       "true").getResultList();

        for (EmployeesEntity employeesEntity : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * Hany Omar 18/2/2014
     * @param hireTypeList
     * @return
     * @throws RemoteException
     * @throws FinderException
     */

    public List<IBasicDTO> filterByAllHireTypes() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.filterByAllHireTypes").setParameter("hiretype1",
                                                                                       IEMPConstants._EMP_CENTERAL_HIRE_TYPE).setParameter("hiretype2",
                                                                                                                                           IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY).setParameter("hiretype3",
                                                                                                                                                                                                   IEMPConstants._EMP_REEMPLOYEMENTS).setParameter("hirstatusCode",
                                                                                                                                                                                                                                                   IEMPConstants._EMP_STATUS_CANDIDATE).setParameter("val1",
                                                                                                                                                                                                                                                                                                     IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("val2",
                                                                                                                                                                                                                                                                                                                                                                        IEMPConstants._EMP_STAGE_WAITING_EMPLOYMENT).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                                                                                                             "true").getResultList();

        for (EmployeesEntity employeesEntity : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * Get all employee copmleted their documents,procedures and waiting for hire decision
     * @return list of employee
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getAllEmployeeWaitingForHireDecision() throws DataBaseException,
                                                                         SharedApplicationException {
        List arrayList = new ArrayList<IBasicDTO>();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.getAllEmployeeWaitingForHireDecision").setHint("toplink.refresh",
                                                                                                  "true").setParameter("hirstageCode",
                                                                                                                       IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION).getResultList();
        for (EmployeesEntity employees : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * Filter employee  waiting for hire decision depend on hire type
     * @return list of employee
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IEntityKey hireType) throws DataBaseException,
                                                                                               SharedApplicationException {
        List arrayList = new ArrayList<IBasicDTO>();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.getFilterEmployeeWaitingForHireDecision").setParameter("hirstatusCode",
                                                                                                          IEMPConstant.EMP_STATUS_CANDIDATE).setParameter("hirstageCode",
                                                                                                                                                          IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT_DECISION).setParameter("hirtypeCode",
                                                                                                                                                                                                                           ((IHireTypesEntityKey)hireType).getHirtypeCode()).getResultList();
        for (EmployeesEntity employees : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * check if employee can hire in this type
     * @param conditionCode
     * @param citizenCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean checkGRSCondition(IEntityKey conditionCode, IEntityKey citizenCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        Vector result =
            (Vector)EM().createNativeQuery("SELECT GN_GRS_CHK_COND(" + ((IConditionsEntityKey)conditionCode).getConditionCode() +
                                           "," + ((IKwtCitizensResidentsEntityKey)citizenCode).getCivilId() +
                                           ") FROM DUAL").getSingleResult();
        if (result.elementAt(0).equals(new BigDecimal(1)))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    ///////////////////////////////////////////////////////////


    /**
     * Set Transaction log variable
     * @throws RemoteException
     */
    public void setTrxLogVar() throws DataBaseException, SharedApplicationException {
        try {
            EntityManagerImpl emImpl = (EntityManagerImpl)(EM()).getDelegate();
            UnitOfWorkImpl uofw = (UnitOfWorkImpl)emImpl.getActiveSession();
            ClientSession session = (ClientSession)uofw.getParent();
            Connection connection = session.getAccessor().getConnection();
            String nativSql = "{call HR_EMP_KEEP_LOG.SET_TRX_LOG_VAR}";
            CallableStatement cstmt = connection.prepareCall(nativSql);
            cstmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * Release Transaction log variable
     * @throws RemoteException
     */
    public void releaseTrxLogVar() throws DataBaseException, SharedApplicationException {


        try {
            // updated by A.AGAMY
            //            EntityManagerImpl emImpl = (EntityManagerImpl)(EM()).getDelegate();
            //            UnitOfWorkImpl uofw = (UnitOfWorkImpl)emImpl.getActiveSession();
            //            ClientSession session = (ClientSession)uofw.getParent();
            Connection connection = getConnection(); //session.getAccessor().getConnection();
            String nativSql = "{call HR_EMP_KEEP_LOG.RELEASE_TRX_LOG_VAR}";
            CallableStatement cstmt = connection.prepareCall(nativSql);
            cstmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }

    }

    /**
     * Add keep log
     * @throws RemoteException
     */
    public void keepLog(Long civilId, Long trxType, Date applayDate, Long tabRecSerial) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            // updated by A.AGAMY
            //            EntityManagerImpl emImpl = (EntityManagerImpl)(EM()).getDelegate();
            //            UnitOfWorkImpl uofw = (UnitOfWorkImpl)emImpl.getActiveSession();
            //            ClientSession session = (ClientSession)uofw.getParent();
            Connection connection = getConnection(); //session.getAccessor().getConnection();
            String nativSql = "{call HR_EMP_KEEP_LOG.KEEP_LOG(?,?,?,?)}";
            CallableStatement cstmt = connection.prepareCall(nativSql);
            cstmt.setLong(1, civilId);
            cstmt.setLong(2, trxType);
            cstmt.setDate(3, applayDate);
            cstmt.setLong(4, tabRecSerial == null ? 0L : tabRecSerial);
            cstmt.execute();
            connection.close();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    /**
     * get employee info with his salary element info (valid), for specific type
     * @param civilId
     * @param elmGuideType
     * @return BasicDTO
     * @throws RemoteException
     * @throws FinderException
     * @author Amir Nasr
     */
    public IBasicDTO getEmployeeInfoByElmType(Long civilId, Long elmGuideType, Long elmGuideType2 ) throws DataBaseException,
                                                                                      SharedApplicationException {
        Query query = EM().createNamedQuery("EmployeesEntity.getEmployeeAndPayrollByElmType");
        query.setParameter("civilId", civilId);
        query.setParameter("elmGuideType", elmGuideType);
        query.setParameter("elmGuideType2", elmGuideType2);

        List<EmployeesEntity> list = query.getResultList();
        if (list == null || list.size() == 0)
            throw new ItemNotFoundException();

        EmployeesEntity employeesEntity = list.get(0);

        //        if (!EmpUtils.checkWrkCenterFound(CSCSecurityInfoHelper.getGroupCodeFromRequest(), employeesEntity.getWrkCode())){
        //            throw new ItemNotFoundException();
        //        }

        IEmployeesDTO employeeDTO = EmpEntityConverter.getEmployeesDTOForEmpHelper(list.get(0));
        //            EmpDTOFactory.createEmployeesDTO(list.get(0));

        if (list.get(0).getSalEmpSalaryElementsEntityList() == null ||
            list.get(0).getSalEmpSalaryElementsEntityList().size() == 0)
            throw new ItemNotFoundException();

        //        List<SalEmpSalaryElementsDTO> empSalList =
        //            new ArrayList<SalEmpSalaryElementsDTO>();
        //        empSalList.add(new SalEmpSalaryElementsDTO(list.get(0).getSalEmpSalaryElementsEntityList().get(0)));
        //        employeeDTO.setSalEmpSalaryElementsDTOList(empSalList);

        return employeeDTO;
    }

    /**
     * find if an employee exists in a certain ministry
     * @param civilId
     * @param minCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeInMinistry(Long civilId, Long minCode) throws DataBaseException,
                                                                           SharedApplicationException {

        Long result =
            (Long)EM().createNamedQuery("EmployeesEntity.getEmployeeMinistry").setParameter("realCivilId", civilId).getSingleResult();
        if (result != null && result.equals(minCode))
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * check if  employee exists
     * @param civilId
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeExist(Long civilId) throws DataBaseException, SharedApplicationException {
        List _civilId =
            EM().createNamedQuery("EmployeesEntity.getEmployeeMinistry").setParameter("realCivilId", civilId).getResultList();
        if (_civilId != null && _civilId.size() > 0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * check if an minFileNo exists
     * @param ministryFileNo
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException, SharedApplicationException {

        List _ministryFileNo =
            EM().createNamedQuery("EmployeesEntity.isMinistryFileNoExist").setParameter("ministryFileNo",
                                                                                        ministryFileNo).getResultList();
        if (_ministryFileNo != null && _ministryFileNo.size() > 0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * check if employee valid to add request to end his/her service
     * @param civilId
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isEmployeeValidToEndService(Long civilId) throws DataBaseException, SharedApplicationException {
        List list =
            EM().createNamedQuery("EmployeesEntity.isEmployeeValidToEndService").setParameter("civilId", civilId).setParameter("s1",
                                                                                                                               IEMPConstant.EMP_STATUS_EMPLOYMENT).setParameter("s2",
                                                                                                                                                                                IEMPConstant.EMP_STATUS_MOVING).setParameter("s3",
                                                                                                                                                                                                                             IEMPConstant.EMP_STATUS_DELEGATION).setParameter("s4",
                                                                                                                                                                                                                                                                              IEMPConstant.EMP_STATUS_LOANINIG).setParameter("s5",
                                                                                                                                                                                                                                                                                                                             IEMPConstant.EMP_STATUS_MISSION).setParameter("s6",
                                                                                                                                                                                                                                                                                                                                                                           IEMPConstant.EMP_STATUS_GRANT).setParameter("s7",
                                                                                                                                                                                                                                                                                                                                                                                                                       IEMPConstant.EMP_STATUS_VACATION).setParameter("s8",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                      IEMPConstant.EMP_STATUS_PRISONER_LOST).setParameter("s9",
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM).getResultList();
        if (list != null && list.size() > 0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    /**
     * search employees to be assigned to evaluator (Apraisal Module) in a specific ministry
     * @param evaluatorCivilId
     * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchEmployeesForEvaluator(Long evaluatorCivilId,
                                                       IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        IEmployeeSearchDTO employeeSearchDTO = (IEmployeeSearchDTO)basicDTO;

        String hireStatusStr =
            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstant.EMP_STATUS_MOVING + "," + IEMPConstant.EMP_STATUS_DELEGATION +
            "," + IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
            IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
            IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION;

        StringBuilder ejbql = new StringBuilder("select o from EmployeesEntity o ");

        if (employeeSearchDTO.getElmguideCode() != null) {
            ejbql.append("join o.salEmpSalaryElementsEntityList empSalElementsList ");
        }
        ejbql.append(" WHERE o.minCode =" + employeeSearchDTO.getMinistryCode());
        if (evaluatorCivilId != null) {
            ejbql.append(" and o.civilId <>'" + evaluatorCivilId + "'");

        }
        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId='" + employeeSearchDTO.getCivilId() + "'");

        if (employeeSearchDTO.getRealCivilId() != null)
            ejbql.append(" AND  o.realCivilId='" + employeeSearchDTO.getRealCivilId() + "'");
        if (employeeSearchDTO.getFirstName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.firstName LIKE '" +
            //                         employeeSearchDTO.getFirstName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.firstName",
                                                                             employeeSearchDTO.getFirstName()) +
                         " ) ");

        if (employeeSearchDTO.getSecondName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.secondName LIKE '" +
            //                         employeeSearchDTO.getSecondName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.secondName",
                                                                             employeeSearchDTO.getSecondName()) +
                         " ) ");
        if (employeeSearchDTO.getThirdName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.thirdName LIKE '" +
            //                         employeeSearchDTO.getThirdName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.thirdName",
                                                                             employeeSearchDTO.getThirdName()) +
                         " ) ");
        if (employeeSearchDTO.getLastName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.lastName LIKE '" +
            //                         employeeSearchDTO.getLastName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.lastName",
                                                                             employeeSearchDTO.getLastName()) + " ) ");
        if (employeeSearchDTO.getFamilyName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.familyName LIKE '" +
            //                         employeeSearchDTO.getFamilyName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.familyName",
                                                                             employeeSearchDTO.getFamilyName()) +
                         " ) ");
        if (employeeSearchDTO.getEnglishName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.englishName LIKE '" +
            //                         employeeSearchDTO.getEnglishName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.englishName",
                                                                             employeeSearchDTO.getEnglishName()) +
                         " ) ");
        if (employeeSearchDTO.getBirthDate() != null)
            ejbql.append(" AND o.citizensResidentsEntity.birthDate='" + employeeSearchDTO.getBirthDate() + "'");
        if (employeeSearchDTO.getGenderTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.gentypeCode=" + employeeSearchDTO.getGenderTypeCode() + "");
        if (employeeSearchDTO.getMaritalStatusCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.marstatusCode=" + employeeSearchDTO.getMaritalStatusCode() +
                         "");
        if (employeeSearchDTO.getPhonesNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
        if (employeeSearchDTO.getMobileNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
        if (employeeSearchDTO.getReligionCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.religionCode=" + employeeSearchDTO.getReligionCode() + "");
        if (employeeSearchDTO.getNationality() != null)
            ejbql.append(" AND o.citizensResidentsEntity.nationality=" + employeeSearchDTO.getNationality() + "");
        if (employeeSearchDTO.getResidentTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.restypeCode=" + employeeSearchDTO.getResidentTypeCode() + "");
        if (employeeSearchDTO.getEndResidentDate() != null)
            ejbql.append(" AND o.citizensResidentsEntity.endResidentDate='" + employeeSearchDTO.getEndResidentDate() +
                         "'");
        if (employeeSearchDTO.getMapCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.mapCode='" + employeeSearchDTO.getMapCode() + "'");
        if (employeeSearchDTO.getCapstatusCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.capstatusCode=" + employeeSearchDTO.getCapstatusCode() + "");
        if (employeeSearchDTO.getSpecialCaseTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.spccsetypeCode=" +
                         employeeSearchDTO.getSpecialCaseTypeCode() + "");
        if (employeeSearchDTO.getPassportNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.passportNo LIKE '" + employeeSearchDTO.getPassportNo() + "'");
        /*Search by job criteria*/
        if (employeeSearchDTO.getBankbranchCode() != null && !employeeSearchDTO.getBankbranchCode().equals("")) {
            String[] str = employeeSearchDTO.getBankbranchCode().split("\\*");
            ejbql.append(" AND o.bnkbranchCode=" + Long.parseLong(str[1]) + " AND o.bankCode=" +
                         Long.parseLong(str[0]) + "");
        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        if (employeeSearchDTO.getTechJobCode() != null)
            ejbql.append(" AND o.techJobCode='" + employeeSearchDTO.getTechJobCode() + "'");
        if (employeeSearchDTO.getJobCode() != null)
            ejbql.append(" AND o.jobCode LIKE '" + employeeSearchDTO.getJobCode() + "'");
        if (employeeSearchDTO.getAccountNo() != null)
            ejbql.append(" AND o.accountNo LIKE '" + employeeSearchDTO.getAccountNo() + "'");
        if (employeeSearchDTO.getCscFileNo() != null)
            ejbql.append(" AND o.cscFileNo LIKE '" + employeeSearchDTO.getCscFileNo() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEndWorkingDate() != null)
            ejbql.append(" AND o.endJobDate='" + employeeSearchDTO.getEndWorkingDate() + "'");
        if (employeeSearchDTO.getHireDate() != null)
            ejbql.append(" AND o.hireDate='" + employeeSearchDTO.getHireDate() + "'");
        if (employeeSearchDTO.getEmpHireStages() != null)
            ejbql.append(" AND o.hireStagesEntity.hirstageCode=" + employeeSearchDTO.getEmpHireStages() + "");
        if (employeeSearchDTO.getEmpHireStatus() != null)
            ejbql.append(" AND o.hireStatusEntity.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
        if (employeeSearchDTO.getEmpHireTypes() != null)
            ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
        ////////////////////////////////
        //ADDED BY TAHA ABDUL MEJID @ 17/8/08
        if (employeeSearchDTO.getBgtTypesCode() != null)
            ejbql.append(" AND o.bgtTypesEntity.typeCode=" + employeeSearchDTO.getBgtTypesCode() + "");
        if (employeeSearchDTO.getBgtProgramsCode() != null)
            ejbql.append(" AND o.bgtProgramsEntity.prgCode LIKE '" + employeeSearchDTO.getBgtProgramsCode() + "'");

        if (employeeSearchDTO.getElmguideCode() != null)
            ejbql.append(" AND empSalElementsList.elmguideCode = " + employeeSearchDTO.getElmguideCode() +
                         "and (empSalElementsList.untilDate is null or empSalElementsList.untilDate > '" +
                         SharedUtils.getCurrentDate() + "')");

        if (employeeSearchDTO.getMasterCode() != null &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecisionNumber() == null) &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecyearCode() == null) &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDectypeCode() == null))

            ejbql.append(" AND o.civilId NOT IN ( SELECT empList.civilId FROM  EmpDecisionsEntity  empList where " +
                         "  empList.decisionsEntity.decisionNumber=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecisionNumber() +
                         "  AND empList.decisionsEntity.dectypeCode=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDectypeCode() +
                         "  AND empList.decisionsEntity.decyearCode=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecyearCode() + ")");
        System.out.println(ejbql.toString());
        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;
    }

    /**
     * search employees to be assigned to evaluator (Apraisal Module) by civilid and ministry
     * @param evaluatorCivilId
     * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchEmployeesForEvaluatorByCivilId(Long evaluatorCivilId,
                                                                IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {


        StringBuilder ejbql = null;


        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId = o.civilId ");
        IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        if (evaluatorCivilId != null) {
            ejbql.append(" and  o.civilId <> '" + evaluatorCivilId + "' ");
        }
        String hireStatusStr =
            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstant.EMP_STATUS_MOVING + "," + IEMPConstant.EMP_STATUS_DELEGATION +
            "," + IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
            IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
            IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION;

        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");

        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }
        if (employeeSearchDTO.getMinistryCode() != null) {

            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode());

        }
        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");

        List<EmployeesEntity> list = null;
        System.out.println(ejbql.toString());
        if (ejbql != null)
            list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;
    }

    /**
     * search employees to be assigned to evaluator (Apraisal Module) by civilName and ministry
     * @param evaluatorCivilId
     * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchEmployeesForEvaluatorByName(Long evaluatorCivilId,
                                                             IBasicDTO basicDTO) throws DataBaseException,
                                                                                        SharedApplicationException {
        StringBuilder ejbql = null;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId = o.civilId ");
        if (evaluatorCivilId != null) {
            ejbql.append(" and  o.civilId <> '" + evaluatorCivilId + "' ");
        }

        IEmpEmployeeSearchDTO employeeSearchDTO = (IEmpEmployeeSearchDTO)basicDTO;

        String hireStatusStr =
            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstant.EMP_STATUS_MOVING + "," + IEMPConstant.EMP_STATUS_DELEGATION +
            "," + IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
            IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
            IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION;
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
            //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
            //                         employeeSearchDTO.getEmpName() + "')");
            ejbql.append(" AND o.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }
        if (employeeSearchDTO.getMinistryCode() != null) {

            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode());

        }

        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");

        List<EmployeesEntity> list = null;
        System.out.println(ejbql.toString());
        if (ejbql != null)
            list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        return listDTO;
    }

    /**
     * Get all match given hireStatusCode And hireStageCode
     * @param hireStatusCode
     * @param hireStageCode
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getAllEmployee(Long hireStatusCode, Long hireStageCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        List arrayList = new ArrayList<IBasicDTO>();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.getAllEmployeeWithStageAndStatus").setHint("toplink.refresh",
                                                                                              "true").setParameter("hirstatusCode",
                                                                                                                   hireStatusCode).setParameter("hirstageCode",
                                                                                                                                                hireStageCode).getResultList();
        for (EmployeesEntity employees : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * Get all employees in a given ministry
     * @param minCode
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getEmployeesByMinistry(Long minCode) throws DataBaseException, SharedApplicationException {
        List arrayList = new ArrayList<IBasicDTO>();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.getEmployeesByMinistry").setParameter("minCode",
                                                                                         minCode).getResultList();
        for (EmployeesEntity employees : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    public List<IBasicDTO> getAllEmployeeByStageAndMinistry(Long minCode,
                                                            String selectedStage) throws DataBaseException,
                                                                                         SharedApplicationException {
        List arrayList = new ArrayList<IBasicDTO>();
        List<EmployeesEntity> list =
            EM().createNamedQuery("EmployeesEntity.getAllEmployeeWithStageAndMinCode").setParameter("minCode",
                                                                                                    minCode).setParameter("",
                                                                                                                          selectedStage).getResultList();
        for (EmployeesEntity employees : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
        }
        if (arrayList.size() == 0)
            throw new NoResultException();
        return arrayList;
    }

    /**
     * check if employee is hired
     * @param employeesEntityKey
     * @return Boolean
     * @throws RemoteException
     * @throws FinderException
     */
    public Boolean isEmployeeHired(IEntityKey employeesEntityKey) throws DataBaseException,
                                                                         SharedApplicationException {
        Long civilId = ((IEmployeesEntityKey)employeesEntityKey).getCivilId();
        //commented by mohamed sayed at 4-3-2015 and replaced by generic method to status
        //        String hireStatusStr =
        //            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," +
        //            IEMPConstant.EMP_STATUS_DELEGATION + "," + IEMPConstant.EMP_STATUS_GRANT + "," +
        //            IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
        //            IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," + IEMPConstant.EMP_STATUS_PRISONER_LOST + "," +
        //            IEMPConstant.EMP_STATUS_VACATION + "," + IEMPConstant.EMP_STATUS_MOVING + "," +
        //            IEMPConstants.EMP_STATUS_FREEZED;
        //commented by mohamed sayed at 20-8-2015 to call new status function
        //String hireStatusStr = getStatusForHire();
        //added by mohamed sayed at 20-8-2015 to call new function centeralized contains 4 status for hired
        String hireStatusStr = EmpUtils.getStatusForHire();
        StringBuilder queryStr =
            new StringBuilder("select o from EmployeesEntity o WHERE o.civilId like '" + civilId + "' ");
        queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");
        //added by mohamed sayed according to request from mah zain at 4-3-2015
        queryStr.append(" and o.activeFlag=1 ");
        /// commented By M.abdelsabour to apply new DataFilteration Sol CSC-21713
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        List<EmployeesEntity> list = EM().createQuery(queryStr.toString()).getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }
        return Boolean.TRUE;
    }

    public Boolean isEmployeeHired(Long realCivilId, Long minCode) throws DataBaseException,
                                                                          SharedApplicationException {
        //commented by mohamed sayed at 20-8-2015 to call new status function
        //String hireStatusStr = getStatusForHire();
        //added by mohamed sayed at 20-8-2015 to call new function centeralized contains 4 status for hired
        String hireStatusStr = EmpUtils.getStatusForHire();
        StringBuilder queryStr =
            new StringBuilder("select o.realCivilId from EmployeesEntity o where o.realCivilId = :realCivilId  and o.minCode = :minCode");
        queryStr.append(" and o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");
        // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }

        System.out.println("isEmployeeHired queryStr = " + queryStr + "\n , realCivilId = " + realCivilId +
                           " , minCode = " + minCode);
        List<EmployeesEntity> list =

            EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("minCode",
                                                                                                        minCode).getResultList();
        if (list == null || list.size() == 0) {
            throw new ItemNotFoundException();
        }
        return Boolean.TRUE;
    }
    //added by Mohamed Sayed at 14-5-2015 to get emp is hired or not by realCivil in all ministries

    public Boolean isEmployeeHiredWithRecordDesc(Long realCivilId) throws DataBaseException,
                                                                          SharedApplicationException {
        String hireStatusStr = EmpUtils.getStatusForHire();
        StringBuilder queryStr =
            new StringBuilder("select o.realCivilId from EmployeesEntity o where o.realCivilId = :realCivilId and recordDescCode=1  ");
        queryStr.append(" and o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");
        List<EmployeesEntity> list =
            EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).getResultList();
        if (list == null || list.size() == 0) {
            throw new ItemNotFoundException();
        }
        return Boolean.TRUE;
    }

    public Boolean isEmployeeHiredForVac(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                SharedApplicationException {

        //commented by mohamed sayed at 20-8-2015 to call new status function
        //String hireStatusStr = getStatusForHire();
        //added by mohamed sayed at 20-8-2015 to call new function centeralized contains 4 status for hired
        String hireStatusStr = EmpUtils.getStatusForHireWithoutDELEGATION();

        StringBuilder queryStr =
            new StringBuilder("select o.realCivilId from EmployeesEntity o where o.activeFlag=1 and o.realCivilId = :realCivilId  and o.minCode = :minCode");
        queryStr.append(" and o.hireStatusEntity.hirstatusCode IN (" + hireStatusStr + ") ");
        List<EmployeesEntity> list =
            EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("minCode",
                                                                                                        minCode).getResultList();
        if (list == null || list.size() == 0) {
            throw new ItemNotFoundException();
        }
        return Boolean.TRUE;
    }
    //________________________________________Sort by name to use in move _______________________________________________||

    /**
     * Get all employee match search criteria and sorted by full name
     * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchEmployee(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        IEmployeeSearchDTO employeeSearchDTO = (IEmployeeSearchDTO)basicDTO;
        StringBuilder ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId='" + employeeSearchDTO.getCivilId() + "'");

        if (employeeSearchDTO.getRealCivilId() != null)
            ejbql.append(" AND  o.realCivilId='" + employeeSearchDTO.getRealCivilId() + "'");

        if (employeeSearchDTO.getFirstName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.firstName LIKE '" +
            //                         employeeSearchDTO.getFirstName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.firstName",
                                                                             employeeSearchDTO.getFirstName()) +
                         " ) ");

        if (employeeSearchDTO.getSecondName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.secondName LIKE '" +
            //                         employeeSearchDTO.getSecondName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.secondName",
                                                                             employeeSearchDTO.getSecondName()) +
                         " ) ");
        if (employeeSearchDTO.getThirdName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.thirdName LIKE '" +
            //                         employeeSearchDTO.getThirdName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.thirdName",
                                                                             employeeSearchDTO.getThirdName()) +
                         " ) ");
        if (employeeSearchDTO.getLastName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.lastName LIKE '" +
            //                         employeeSearchDTO.getLastName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.lastName",
                                                                             employeeSearchDTO.getLastName()) + " ) ");
        if (employeeSearchDTO.getFamilyName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.familyName LIKE '" +
            //                         employeeSearchDTO.getFamilyName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.familyName",
                                                                             employeeSearchDTO.getFamilyName()) +
                         " ) ");
        if (employeeSearchDTO.getEnglishName() != null)
            //            ejbql.append(" AND o.citizensResidentsEntity.englishName LIKE '" +
            //                         employeeSearchDTO.getEnglishName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.englishName",
                                                                             employeeSearchDTO.getEnglishName()) +
                         " ) ");
        if (employeeSearchDTO.getBirthDate() != null)
            ejbql.append(" AND o.citizensResidentsEntity.birthDate='" + employeeSearchDTO.getBirthDate() + "'");
        if (employeeSearchDTO.getGenderTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.gentypeCode=" + employeeSearchDTO.getGenderTypeCode() + "");
        if (employeeSearchDTO.getMaritalStatusCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.marstatusCode=" + employeeSearchDTO.getMaritalStatusCode() +
                         "");
        if (employeeSearchDTO.getPhonesNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
        if (employeeSearchDTO.getMobileNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
        if (employeeSearchDTO.getReligionCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.religionCode=" + employeeSearchDTO.getReligionCode() + "");
        if (employeeSearchDTO.getNationality() != null)
            ejbql.append(" AND o.citizensResidentsEntity.nationality=" + employeeSearchDTO.getNationality() + "");
        if (employeeSearchDTO.getResidentTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.restypeCode=" + employeeSearchDTO.getResidentTypeCode() + "");
        if (employeeSearchDTO.getEndResidentDate() != null)
            ejbql.append(" AND o.citizensResidentsEntity.endResidentDate='" + employeeSearchDTO.getEndResidentDate() +
                         "'");
        if (employeeSearchDTO.getMapCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.mapCode='" + employeeSearchDTO.getMapCode() + "'");
        if (employeeSearchDTO.getCapstatusCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.capstatusCode=" + employeeSearchDTO.getCapstatusCode() + "");
        if (employeeSearchDTO.getSpecialCaseTypeCode() != null)
            ejbql.append(" AND o.citizensResidentsEntity.spccsetypeCode=" +
                         employeeSearchDTO.getSpecialCaseTypeCode() + "");
        if (employeeSearchDTO.getPassportNo() != null)
            ejbql.append(" AND o.citizensResidentsEntity.passportNo LIKE '" + employeeSearchDTO.getPassportNo() + "'");
        /*Search by job criteria*/
        if (employeeSearchDTO.getBankbranchCode() != null && !employeeSearchDTO.getBankbranchCode().equals("")) {
            String[] str = employeeSearchDTO.getBankbranchCode().split("\\*");
            ejbql.append(" AND o.bnkbranchCode=" + Long.parseLong(str[1]) + " AND o.bankCode=" +
                         Long.parseLong(str[0]) + "");
        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        if (employeeSearchDTO.getTechJobCode() != null)
            ejbql.append(" AND o.techJobCode='" + employeeSearchDTO.getTechJobCode() + "'");
        if (employeeSearchDTO.getJobCode() != null)
            ejbql.append(" AND o.jobCode LIKE '" + employeeSearchDTO.getJobCode() + "'");
        if (employeeSearchDTO.getAccountNo() != null)
            ejbql.append(" AND o.accountNo LIKE '" + employeeSearchDTO.getAccountNo() + "'");
        if (employeeSearchDTO.getCscFileNo() != null)
            ejbql.append(" AND o.cscFileNo LIKE '" + employeeSearchDTO.getCscFileNo() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEndWorkingDate() != null)
            ejbql.append(" AND o.endJobDate='" + employeeSearchDTO.getEndWorkingDate() + "'");
        if (employeeSearchDTO.getHireDate() != null)
            ejbql.append(" AND o.hireDate='" + employeeSearchDTO.getHireDate() + "'");
        if (employeeSearchDTO.getEmpHireStages() != null)
            ejbql.append(" AND o.hireStagesEntity.hirstageCode=" + employeeSearchDTO.getEmpHireStages() + "");
        if (employeeSearchDTO.getEmpHireStatus() != null)
            ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
        if (employeeSearchDTO.isEmployment())
            //    ejbql.append(" AND o.hirstatusCode IN (" + getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
            ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() +
                         ")"); //added by mohamed sayed at 20-8-2015

        //            ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
        //                         IEMPConstant.EMP_STATUS_MOVING + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
        //                         IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
        //                         IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
        //                         IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");
        if (employeeSearchDTO.getEmpHireTypes() != null)
            ejbql.append(" AND o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
        ////////////////////////////////
        //ADDED BY TAHA ABDUL MEJID @ 17/8/08
        if (employeeSearchDTO.getBgtTypesCode() != null)
            ejbql.append(" AND o.bgtTypesEntity.typeCode=" + employeeSearchDTO.getBgtTypesCode() + "");
        if (employeeSearchDTO.getBgtProgramsCode() != null)
            ejbql.append(" AND o.bgtProgramsEntity.prgCode LIKE '" + employeeSearchDTO.getBgtProgramsCode() + "'");


        if (employeeSearchDTO.getMasterCode() != null &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecisionNumber() == null) &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecyearCode() == null) &&
            !(((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDectypeCode() == null))

            ejbql.append(" AND o.civilId NOT IN ( SELECT empList.civilId FROM  EmpDecisionsEntity  empList where " +
                         "  empList.decisionsEntity.decisionNumber=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecisionNumber() +
                         "  AND empList.decisionsEntity.dectypeCode=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDectypeCode() +
                         "  AND empList.decisionsEntity.decyearCode=" +
                         ((DecisionsEntityKey)employeeSearchDTO.getMasterCode()).getDecyearCode() + ")");

        ejbql.append(" ORDER BY o.citizensResidentsEntity.firstName,o.citizensResidentsEntity.secondName,o.citizensResidentsEntity.thirdName" +
                     ",o.citizensResidentsEntity.lastName,o.citizensResidentsEntity.familyName");
        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO(entity);
            dto.setChecked(employeeSearchDTO.getChecked());
            listDTO.add(dto);
        }
        return listDTO;
    }


    /**
     * Get all employees match search criteria and sorted by full name for sal cal
     * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchEmployeeForSalCalc(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        Long virtualValueLong = ISystemConstant.VIRTUAL_VALUE;
        String virtualValueString = String.valueOf(ISystemConstant.VIRTUAL_VALUE);
        String salaryDate = "";

        IEmpEmployeeSearchDTO searchDTO = (IEmpEmployeeSearchDTO)basicDTO;

        if (searchDTO.getMonthCode() != null && searchDTO.getYearCode() != null) {
            salaryDate = "01/" + searchDTO.getMonthCode() + "/" + searchDTO.getYearCode();
        }
        StringBuilder query =
            new StringBuilder("SELECT E.CIVIL_ID, E.REAL_CIVIL_ID, GET_NAME (E.REAL_CIVIL_ID) EMP_NAME,F.CADER_CODE , F.CADER_NAME ,F.GROUP_CODE , F.GROUP_NAME , F.DEGREE_CODE , F.DEGREE_NAME ");
        query.append(" FROM HR_EMP_EMPLOYEES E, hr_emp_sal_fin_degrees F ");
        if (searchDTO.getNationalityType() != null && !searchDTO.getNationalityType().equals(-1L)) {
            query.append(", INF_KWT_CITIZENS_RESIDENTS citi ");
        }
        query.append(" WHERE ");
        if (searchDTO.getNationalityType() != null && !searchDTO.getNationalityType().equals(-1L)) {
            query.append(" E.REAL_CIVIL_ID = CITI.CIVIL_ID  ");
            if (searchDTO.getNationalityType().equals(IEMPConstants.EMP_NATIONALITY_KWT)) {
                query.append(" AND CITI.NATIONALITY =  ").append(IEMPConstants.EMP_NATIONALITY_KWT).append(" AND ");
            } else if (!searchDTO.getNationalityType().equals(IEMPConstants.EMP_NATIONALITY_KWT)) {
                query.append(" AND CITI.NATIONALITY <>  ").append(IEMPConstants.EMP_NATIONALITY_KWT).append(" AND ");
            }
        }
        query.append(" E.CIVIL_ID = F.CIVIL_ID AND E.MIN_CODE = F.MIN_CODE ");
        // query.append(" AND (F.UNTIL_DATE IS NULL OR F.UNTIL_DATE >= SYSDATE) ");
        query.append(" AND(TO_DATE ('" + salaryDate + "','dd/MM/yyyy') >= F.FROM_DATE ");
        query.append(" AND (   F.UNTIL_DATE IS NULL OR TO_DATE ('" + salaryDate +
                     "', 'dd/MM/yyyy') <= F.UNTIL_DATE)) ");
        query.append(" AND E.MIN_CODE = ");
        query.append(searchDTO.getMinistryCode());
        query.append("AND E.HIRSTATUS_CODE =1 ");
        //        query.append("AND E.HIRSTATUS_CODE IN (");
        //        query.append(EmpUtils.getStatusForHire() + ")");
        query.append(" AND E.ACTIVE_FLAG = ");
        query.append(ISystemConstant.ACTIVE_FLAG);
        if (searchDTO.getCivilId() != null) {
            query.append(" AND E.CIVIL_ID = " + searchDTO.getCivilId() + " ");
        }

        if (searchDTO.getWorkCenterCode() != null && !searchDTO.getWorkCenterCode().equals(virtualValueString)) {
            if (!searchDTO.isRelatedWorkCentersFlag())
                query.append(" AND E.WRK_CODE = '" + searchDTO.getWorkCenterCode() + "' ");
            else
                query.append(" AND E.WRK_CODE IN (SELECT WC.WRK_CODE FROM NL_ORG_WORK_CENTERS WC  WHERE WC.MIN_CODE = " +
                             searchDTO.getMinistryCode() + " START WITH (WC.WRK_CODE = '" +
                             searchDTO.getWorkCenterCode() + "')  CONNECT BY (PRIOR WC.WRK_CODE = WC.PARENT_WRK)) ");
        }
        //===================WORK CENTER SOLUTION=======================
        // commented By M.abdelsabour to apply new Data Filteration Sol CSC-21713
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            query.append(" and E.WRK_CODE in (" + wrkcode + ")");
        //        }

        IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
        if (dfDTO != null) {
            String wrkCodeIn = dfDTO.getDfInNative();
            String wrkCodeNotIn = dfDTO.getDfNotInNative();
            if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                query.append(" and E.WRK_CODE in(");
                query.append(wrkCodeIn);
                query.append(")");
            }
            if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                query.append(" and E.WRK_CODE Not in(");
                query.append(wrkCodeNotIn);
                query.append(")");
            }
        }

        if (searchDTO.getBgtProgramsCode() != null && !searchDTO.getBgtProgramsCode().equals(virtualValueString)) {
            query.append(" AND E.BGTPRG_CODE = '" + searchDTO.getBgtProgramsCode() + "' ");
        }

        if (searchDTO.getBgtTypesCode() != null && !searchDTO.getBgtTypesCode().equals(virtualValueLong)) {
            query.append(" AND E.BGTTYPE_CODE = " + searchDTO.getBgtTypesCode() + " ");
        }

        if (searchDTO.getKaderCode() != null && !searchDTO.getKaderCode().equals(virtualValueLong)) {
            query.append(" AND F.CADER_CODE = " + searchDTO.getKaderCode() + " ");
        }

        if (searchDTO.getGroupCode() != null && !searchDTO.getGroupCode().equals(virtualValueLong)) {
            query.append(" AND F.GROUP_CODE = " + searchDTO.getGroupCode() + " ");
        }

        if (searchDTO.getElmguideCode() != null && !searchDTO.getElmguideCode().equals(virtualValueLong)) {
            query.append(" AND F.DEGREE_CODE = " + searchDTO.getElmguideCode() + " ");
        }
        if (searchDTO.isHasCalculatedMonSalary()) { // cancel calculations
            query.append(" AND EXISTS (SELECT 1 FROM HR_SAL_EMP_MON_SALARIES S ");
            query.append(" WHERE S.CIVIL_ID = E.REAL_CIVIL_ID ");
            query.append(" AND S.PAYTYPE_CODE = 1 "); // now identify that the selected row is salary row by moshir "tempory used "
            if (searchDTO.getPayStatusCodes() != null && !searchDTO.getPayStatusCodes().trim().isEmpty()) {
                query.append(" AND S.PAYSTATUS_CODE IN (");
                query.append(searchDTO.getPayStatusCodes());
                query.append(" ) ");
            }
            query.append(" AND S.YEAR_CODE  = " + searchDTO.getYearCode() + " ");
            query.append(" AND S.SALARY_MONTH = " + searchDTO.getMonthCode() + ") ");

        }


        System.out.println("searchEmployeeForSalCalc query = \n" +
                query.toString());

        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list = q.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        for (Vector row : list) {
            IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
            dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((BigDecimal)row.get(0)).longValue()));
            dto.setRealCivilId(((BigDecimal)row.get(1)).longValue());
            dto.setName((String)row.get(2));
            dto.setSalEmpSalaryElementsDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
            ISalElementGuidesDTO salElmGuide = SalDTOFactory.createSalElementGuidesDTO();
            salElmGuide.setCaderCode(row.get(3).toString());
            salElmGuide.setCaderName(row.get(4).toString());
            salElmGuide.setGroupCode(row.get(5).toString());
            salElmGuide.setGroupName(row.get(6).toString());
            salElmGuide.setDegreeCode(row.get(7).toString());
            salElmGuide.setDegreeName(row.get(8).toString());
            dto.getSalEmpSalaryElementsDTO().setSalElementGuidesDTO(salElmGuide);
            arrayList.add(dto);
        }

        return arrayList;

    }


    private SalEmpSalaryElementsDTO getSalEmpSalaryElementsDTOFromEntity(EmpSalEmpSalaryElementsEntity salEmpSalaryElementsEntity) {

        SalEmpSalaryElementsDTO dto = new SalEmpSalaryElementsDTO();

        dto.setCode(SalEntityKeyFactory.createSalEmpSalaryElementsEntityKey(salEmpSalaryElementsEntity.getCivilId(),
                                                                            salEmpSalaryElementsEntity.getSalElementGuidesEntity().getElmguideCode(),
                                                                            salEmpSalaryElementsEntity.getFromDate()));

        if (salEmpSalaryElementsEntity.getEmployeesEntity() != null) {
            dto.setEmployeesDTO(EmpDTOFactory.createEmployeesDTO(salEmpSalaryElementsEntity.getEmployeesEntity()));
        }

        if (salEmpSalaryElementsEntity.getSalElementGuidesEntity() != null) {
            //dto.setSalElementGuidesDTO(new SalElementGuidesDTO(salEmpSalaryElementsEntity.getSalElementGuidesEntity()));
            ISalElementGuidesEntityKey ek =
                SalEntityKeyFactory.createSalElementGuidesEntityKey(salEmpSalaryElementsEntity.getElmguideCode());
            try {
                dto.setSalElementGuidesDTO((ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(ek));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        dto.setFromDate(salEmpSalaryElementsEntity.getFromDate());
        dto.setUntilDate(salEmpSalaryElementsEntity.getUntilDate());

        if (salEmpSalaryElementsEntity.getStpreasonCode() != null) {
            //dto.setSalStopReasonsDTO(new SalStopReasonsDTO(salEmpSalaryElementsEntity.getSalStopReasonsEntity()));
            dto.setSalStopReasonsDTO(getSalaryStopReasonDTO(salEmpSalaryElementsEntity.getStpreasonCode()));
        }

        if (salEmpSalaryElementsEntity.getEqElmGuideCode() != null) {
            //dto.setSalEqElementGuidesDTO(new SalElementGuidesDTO(salEmpSalaryElementsEntity.getSalEqElementGuidesEntity()));
            dto.setSalEqElementGuidesDTO(getgetEqElmGuideDTO(salEmpSalaryElementsEntity.getEqElmGuideCode()));
        }

        if (salEmpSalaryElementsEntity.getDegreasonCode() != null) {
            //dto.setSalDegreeReasonsDTO(new SalDegreeReasonsDTO(salEmpSalaryElementsEntity.getSalDegreeReasonsEntity()));
            dto.setSalDegreeReasonsDTO(getDegreasonDTO(salEmpSalaryElementsEntity.getDegreasonCode()));
        }

        dto.setInvestigationCode(salEmpSalaryElementsEntity.getInvestigationCode());
        dto.setElementValue(salEmpSalaryElementsEntity.getElementValue());
        dto.setElementRatio(salEmpSalaryElementsEntity.getElementRatio());
        dto.setNoOfInstalls(salEmpSalaryElementsEntity.getNoOfInstalls());
        //        dto.setReqSerial(salEmpSalaryElementsEntity.getReqSerial());
        //        dto.setCreatedBy(salEmpSalaryElementsEntity.getCreatedBy());
        //        dto.setCreatedDate(salEmpSalaryElementsEntity.getCreatedDate());
        //        dto.setLastUpdatedBy(salEmpSalaryElementsEntity.getLastUpdatedBy());
        //        dto.setLastUpdatedDate(salEmpSalaryElementsEntity.getLastUpdatedDate());
        dto.setAuditStatus(salEmpSalaryElementsEntity.getAuditStatus());
        dto.setTabrecSerial(salEmpSalaryElementsEntity.getTabrecSerial());
        dto.setFirstInstallDate(salEmpSalaryElementsEntity.getFirstInstallDate());
        dto.setInstallMonthlyValue(salEmpSalaryElementsEntity.getInstallMonthlyValue());

        return dto;

    }


    public List<IBasicDTO> searchEmployeeForSalPayment(IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {

        Long virtualValueLong = ISystemConstant.VIRTUAL_VALUE;
        String virtualValueString = String.valueOf(ISystemConstant.VIRTUAL_VALUE);

        IEmpEmployeeSearchDTO searchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        StringBuilder query = new StringBuilder("SELECT DISTINCT HR_EMP_EMPLOYEES.CIVIL_ID,  ");
        query.append("NVL( GET_NAME(HR_EMP_EMPLOYEES.CIVIL_ID), 'Unknown.') EMP_NAME ");
        query.append("FROM HR_EMP_EMPLOYEES , INF_KWT_CITIZENS_RESIDENTS , HR_SAL_EMP_SALARY_ELEMENTS ");
        query.append("WHERE  HR_SAL_EMP_SALARY_ELEMENTS.CIVIL_ID = HR_EMP_EMPLOYEES.CIVIL_ID ");
        query.append("AND HR_EMP_EMPLOYEES.CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID ");
        query.append("AND HR_EMP_EMPLOYEES.MIN_CODE = " + searchDTO.getMinistryCode() + " ");

        query.append("AND HR_EMP_EMPLOYEES.HIRSTATUS_CODE IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                     IEMPConstant.EMP_STATUS_MOVING + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                     IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
                     IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_PRISONER_LOST + "," +
                     IEMPConstant.EMP_STATUS_VACATION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + ") ");

        if (searchDTO.getCivilId() != null) {
            query.append("AND HR_EMP_EMPLOYEES.CIVIL_ID = " + searchDTO.getCivilId() + " ");
        }

        if (searchDTO.isKuwaiti()) {
            query.append("AND INF_KWT_CITIZENS_RESIDENTS.NATIONALITY = " + ISystemConstant.KUWAIT_NATIONALITY + " ");
        } else {
            query.append("AND INF_KWT_CITIZENS_RESIDENTS.NATIONALITY != " + ISystemConstant.KUWAIT_NATIONALITY + " ");
        }
        if (searchDTO.getBgtProgramsCode() != null && !searchDTO.getBgtProgramsCode().equals(virtualValueString)) {
            query.append("AND HR_EMP_EMPLOYEES.BGTPRG_CODE = '" + searchDTO.getBgtProgramsCode() + "' ");
        }
        if (searchDTO.getBgtTypesCode() != null && !searchDTO.getBgtTypesCode().equals(virtualValueLong)) {
            query.append("AND HR_EMP_EMPLOYEES.BGTTYPE_CODE = " + searchDTO.getBgtTypesCode() + " ");
        }
        if (searchDTO.getWorkCenterCode() != null && !searchDTO.getWorkCenterCode().equals(virtualValueString)) {
            query.append("AND HR_EMP_EMPLOYEES.WRK_CODE = '" + searchDTO.getWorkCenterCode() + "' ");
        } else if ((searchDTO.getWorkCenterCode() == null ||
                    searchDTO.getWorkCenterCode().equals(virtualValueString)) &&
                   searchDTO.getWorkLevelCode() != null && !searchDTO.getWorkLevelCode().equals(virtualValueLong)) {
            query.append("AND EXISTS (SELECT 1 FROM NL_ORG_WORK_CENTERS ");
            query.append("WHERE NL_ORG_WORK_CENTERS.WRKLEVEL_CODE = " + searchDTO.getWorkLevelCode() + " ");
            query.append("AND NL_ORG_WORK_CENTERS.WRK_CODE = HR_EMP_EMPLOYEES.WRK_CODE) ");
        }
        if (searchDTO.getElmguideCode() != null && !searchDTO.getElmguideCode().equals(virtualValueLong)) {
            query.append("AND EXISTS (SELECT 1 FROM HR_SAL_ELEMENT_GUIDES ");
            query.append("WHERE HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE_DEGREE = " + searchDTO.getElmguideCode() + " ");
            query.append("AND HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE = HR_SAL_EMP_SALARY_ELEMENTS.ELMGUIDE_CODE) ");
        } else if ((searchDTO.getElmguideCode() == null || searchDTO.getElmguideCode().equals(virtualValueLong)) &&
                   searchDTO.getGroupCode() != null && !searchDTO.getGroupCode().equals(virtualValueLong)) {
            query.append("AND EXISTS (SELECT 1 FROM HR_SAL_ELEMENT_GUIDES ");
            query.append("WHERE HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE = HR_SAL_EMP_SALARY_ELEMENTS.ELMGUIDE_CODE ");
            query.append("AND HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE_DEGREE IN ");
            query.append("(SELECT HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE FROM HR_SAL_ELEMENT_GUIDES ");
            query.append("WHERE HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE_DEGREE = " + searchDTO.getGroupCode() + ")) ");
        } else if ((searchDTO.getGroupCode() == null || searchDTO.getGroupCode().equals(virtualValueLong)) &&
                   searchDTO.getKaderCode() != null && !searchDTO.getKaderCode().equals(virtualValueLong)) {
            query.append("AND EXISTS (SELECT 1 FROM HR_SAL_ELEMENT_GUIDES ");
            query.append("WHERE HR_SAL_ELEMENT_GUIDES.FIRST_PARENT_DEGREE = " + searchDTO.getKaderCode() + " ");
            query.append("AND HR_SAL_ELEMENT_GUIDES.ELMGUIDE_CODE =HR_SAL_EMP_SALARY_ELEMENTS.ELMGUIDE_CODE) ");
        }
        query.append("ORDER BY EMP_NAME");

        System.out.println(query.toString());

        Query q = EM().createNativeQuery(query.toString(), EmployeesEntity.class);
        List list = q.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        for (Object row : list) {
            arrayList.add(EmpDTOFactory.createEmployeesDTO((EmployeesEntity)row));
        }

        return arrayList;
    }


    /**
     * Update an Existing EmployeesEntity with the given hire status and end job date
     * @param civilId
     * @param endJobDate
     * @param employeeHireStatus
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    public Boolean updateEmployeeDataForMov(Long civilId, Long employeeHireStatus,
                                            Date endJobDate) throws DataBaseException, SharedApplicationException {
        try {
            EmployeesEntity employeesEntity =
                EM().find(EmployeesEntity.class, EmpEntityKeyFactory.createEmployeesEntityKey(civilId));

            if (employeesEntity == null)
                throw new ItemNotFoundException();

            if (employeeHireStatus != null) {
                HireStatusEntity hireStatusEntity =
                    EM().find(HireStatusEntity.class, EmpEntityKeyFactory.createHireStatusEntityKey(employeeHireStatus));
                if (hireStatusEntity == null) {
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStatusEntity(hireStatusEntity);
                }

                employeesEntity.setEndJobDate(endJobDate);
                doUpdate(employeesEntity);
                return Boolean.TRUE;
            }
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


    public Long executeRequiredProceduresForHireEmployee(Long civilId) throws DataBaseException,
                                                                              SharedApplicationException {

        Long returnedNumber = 0L;
        String functionName = "HR_HIRE_INTERFACE_PACK.HIRE_MODULE_INTEGRATION";
        try {
            // updated by A.AGAMY for data audit
            //            EntityManagerImpl emImpl = (EntityManagerImpl)(EM()).getDelegate();
            //            UnitOfWorkImpl uofw = (UnitOfWorkImpl)emImpl.getActiveSession();
            //            ClientSession session = (ClientSession)uofw.getParent();
            Connection connection = getConnectionForUpdate(); //session.getAccessor().getConnection();
            String nativQuery = "{? = call " + functionName + " (?,?,?)}";
            CallableStatement cstmt = connection.prepareCall(nativQuery);
            cstmt.registerOutParameter(1, OracleTypes.NUMBER);
            cstmt.setLong(2, civilId);
            cstmt.setLong(3, CSCSecurityInfoHelper.getUserCodeFromRequest());
            cstmt.setLong(4, DEFAULT_AUDIT_STATUS);
            cstmt.executeUpdate();
            BigDecimal bigDecimalResult = (BigDecimal)cstmt.getObject(1);
            if (bigDecimalResult != null) {
                returnedNumber = bigDecimalResult.longValue();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnedNumber;
    }


    public List<IBasicDTO> getEmployeByCivilId(Long civilID) throws DataBaseException, SharedApplicationException {


        StringBuilder ejbql = null;
        String s = civilID.toString();


        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId = :civilID ");
        // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in( " + wrkcode + ")");
        //        }
        Query query = EM().createQuery(ejbql.toString());
        query.setHint("toplink.refresh", "true");
        query.setParameter("civilID", civilID);

        List<EmployeesEntity> list = query.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }

        //        EmployeesEntity employeesEntity = (EmployeesEntity)list.get(0);
        //        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
        //        employeesDTO.setHireDate(employeesEntity.getHireDate());
        //        employeesDTO.setWorkCenterKey(employeesEntity.getWrkCode());
        //        employeesDTO.setMinistryFileNo(employeesEntity.getMinistryFileNo());

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        //        listDTO.add(employeesDTO);
        for (EmployeesEntity entity :
             list) { // listDTO.add ( EmpEntityConverter.getEmpEmployeeHistoriesDTO ( entity ) ) ;
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }


        return listDTO;
    }

    /**
     * return EmployeeDTO civilId and KwtCitizensResidentsDTO fullName with auditing attributes
     * @param civilID
     * @return
     * @throws RemoteException
     * @throws FinderException
     * @author Ashraf Gaber 19/01/2011
     */
    public IBasicDTO getEmployeeDTOCodeName(Long civilID) throws DataBaseException, SharedApplicationException {


        StringBuilder ejbql = null;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId = :civilID ");
        // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in( " + wrkcode + ")");
        //        }
        Query query = EM().createQuery(ejbql.toString());
        query.setHint("toplink.refresh", "true");
        query.setParameter("civilID", civilID);

        List<EmployeesEntity> list = query.getResultList();
        if (list == null || list.size() == 0) {
            throw new ItemNotFoundException();
        }

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO.get(0);
    }


    public IBasicDTO getEmployeeDTOCodeNameWithoutFilteration(Long civilID) throws DataBaseException, SharedApplicationException {


        StringBuilder ejbql = null;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId = :civilID ");

        Query query = EM().createQuery(ejbql.toString());
        query.setHint("toplink.refresh", "true");
        query.setParameter("civilID", civilID);

        List<EmployeesEntity> list = query.getResultList();
        if (list == null || list.size() == 0) {
            throw new ItemNotFoundException();
        }

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO.get(0);
    }

    /**
     * get employee service period from hire date untill now
     * @param civilId
     * @return Array{days, months, years}
     * @throws RemoteException
     */
    public Long[] getEmpServicePeriod(Long civilId) throws DataBaseException, SharedApplicationException {

        Long resultData[] = { 0L, 0L, 0L }; //days,months,years
        try {
            // Connection connection = DBSharedUtils.getConnection();
            EntityManagerImpl emImpl = (EntityManagerImpl)(EM()).getDelegate();
            UnitOfWorkImpl uofw = (UnitOfWorkImpl)emImpl.getActiveSession();
            ClientSession session = (ClientSession)uofw.getParent();
            Connection connection = session.getAccessor().getConnection();
            String sqlStr = " { ? = call HR_HIRE_INTERFACE_PACK.GET_EMP_NET_PERIODS ( ? , ? , ? , ? , ? ) } ";
            CallableStatement cstmt = connection.prepareCall(sqlStr);
            cstmt.registerOutParameter(1, OracleTypes.NUMBER);
            cstmt.setLong(2, civilId);
            cstmt.setDate(3, SharedUtils.getCurrentDate());
            cstmt.registerOutParameter(4, OracleTypes.NUMBER);
            cstmt.registerOutParameter(5, OracleTypes.NUMBER);
            cstmt.registerOutParameter(6, OracleTypes.NUMBER);
            cstmt.execute();
            BigDecimal result = (BigDecimal)cstmt.getObject(1);
            System.out.println("sheka::Employees::getEmpServicePeriod >> " + result);
            if (result != null && result.intValue() == 1) {
                BigDecimal days = (BigDecimal)cstmt.getObject(4);
                BigDecimal months = (BigDecimal)cstmt.getObject(5);
                BigDecimal years = (BigDecimal)cstmt.getObject(6);
                resultData[0] = days == null ? 0L : days.longValue();
                resultData[1] = months == null ? 0L : months.longValue();
                resultData[2] = years == null ? 0L : years.longValue();
                System.out.println(days + ":" + months + ":" + years);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    //**************************************************************************************************************************************************

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List<IBasicDTO> getByHireStageWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                 SharedApplicationException {
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            IPagingRequestDTO requestDTO = ((IPagingRequestDTO)searchDTO.getPagingRequestDTO());


            StringBuilder queryStr =
                new StringBuilder("SELECT HR_EMP_EMPLOYEES.* FROM HR_EMP_EMPLOYEES " + "INNER JOIN INF_KWT_CITIZENS_RESIDENTS " +
                                  "ON HR_EMP_EMPLOYEES.REAL_CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID " +
                                  "WHERE HR_EMP_EMPLOYEES.HIRSTAGE_CODE IN ");
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
                queryStr.append(" AND hr_emp_employees.HIRSTATUS_CODE=" + searchDTO.getEmpHireStatus() + "");
            }
            if (searchDTO.getEmpHireTypes() != null) {
                queryStr.append(" AND hr_emp_employees.HIRTYPE_CODE=" + searchDTO.getEmpHireTypes() + "");
            }
            if (searchDTO.getActiveFlag() != null) {
                queryStr.append(" AND hr_emp_employees.ACTIVE_FLAG=" + searchDTO.getActiveFlag() + "");
            }
            if (searchDTO.getMinistryCode() != null) {
                queryStr.append(" and hr_emp_employees.min_code = " + searchDTO.getMinistryCode());
            }
            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                queryStr.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    queryStr.append(column);
                    if (!requestDTO.isSortAscending()) {
                        queryStr.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        queryStr.append(" , ");
                    }
                }
            }

            System.out.println("KAMAL = " + queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString(), EmployeesEntity.class);
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            //EM().createQuery("SELECT o FROM EmployeesEntity o").setHint("toplink.refresh", "true").getResultList();

            List<EmployeesEntity> list = query.getResultList();
            for (EmployeesEntity entity : list) {

                //            List<EmployeesEntity> empsList =
                //                EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setHint("toplink.refresh",
                //                                                                                                     "true").setParameter("civilId",
                //                                                                                                                          entity.getCivilId()).getResultList();
                //            EmployeesEntity employeesEntity = empsList.get(0);

                if (entity == null) {
                    throw new FinderException();
                }
                boolean ret = false;

                if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getRealCivilId());
                    } catch (Exception e) {
                    }
                    if (ret) {
                        listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
                    }
                } else if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_NOT_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getRealCivilId());
                    } catch (Exception e) {
                    }
                    if (!ret) {
                        listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
                    }
                } else {
                    listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
                }
            }

            if (listDTO.size() == 0) {
                throw new FinderException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return listDTO;

    }

    //    public List<IBasicDTO> getByHireStageWithPaging(IBasicDTO _searchDTO) throws RemoteException, FinderException {
    //        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
    //        try{
    //        IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;
    //
    //        IPagingRequestDTO requestDTO = searchDTO.getPagingRequestDTO();
    //
    //
    //            StringBuilder queryStr =
    //                new StringBuilder("SELECT HR_EMP_EMPLOYEES.* , DECODE((SELECT COUNT(1) FROM INF_KWT_WORK_DATA W WHERE HR_EMP_EMPLOYEES.REAL_CIVIL_ID = W.CIVIL_ID),0,0,1) EXP " +
    //                                  "FROM HR_EMP_EMPLOYEES " +
    //                                  "WHERE HR_EMP_EMPLOYEES.HIRTYPE_CODE = ("+IEMPConstant.EMP_HIRE_TYPE_CSC.intValue()+") " +
    //                                  "AND HR_EMP_EMPLOYEES.HIRSTAGE_CODE IN ");
    //                    if (searchDTO.getEmpHireStages() != null && searchDTO.getEmpHireStages().size() > 0) {
    //                    queryStr.append("(");
    //                    for (Object hireStageCode:searchDTO.getEmpHireStages()) {
    //                        queryStr.append(hireStageCode.toString());
    //                        queryStr.append(",");
    //                    }
    //                    queryStr=queryStr.deleteCharAt(queryStr.lastIndexOf(","));
    //                    queryStr.append(")");
    //                }
    //
    //                if(searchDTO.getMinistryCode() !=null ){
    //                        queryStr.append(" and hr_emp_employees.min_code = " +searchDTO.getMinistryCode());
    //                    }
    //            //TODO apply sorting
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
    //
    //            System.out.println("KAMAL = "+queryStr.toString());
    //            Query query = EM().createNativeQuery(queryStr.toString());
    //        if (requestDTO != null) {
    //            query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
    //            query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
    //        }
    //
    //        //EM().createQuery("SELECT o FROM EmployeesEntity o").setHint("toplink.refresh", "true").getResultList();
    //
    //        List list = query.getResultList();
    //            System.out.println(list);
    //        for (Object entity : list) {
    //
    //            //            List<EmployeesEntity> empsList =
    //            //                EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setHint("toplink.refresh",
    //            //                                                                                                     "true").setParameter("civilId",
    //            //                                                                                                                          entity.getCivilId()).getResultList();
    //            //            EmployeesEntity employeesEntity = empsList.get(0);
    //
    //            if (entity == null) {
    //                throw new FinderException();
    //            }
    //            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
    //        }
    //
    //        if (listDTO.size() == 0) {
    //            throw new FinderException();
    //        }
    //        }catch(Exception ex ){
    //            ex.printStackTrace();
    //
    //        }
    //
    //        return listDTO;
    //
    //    }

    public Long getCountByHireStageWithPaging(List hireStage) throws DataBaseException, SharedApplicationException {

        StringBuilder ejbql =
            new StringBuilder("select count(o.civilId) from EmployeesEntity o where o.hireStagesEntity.hirstageCode IN ");
        if (hireStage != null && hireStage.size() > 0) {
            ejbql.append("(");
            for (Object hireStageCode : hireStage) {
                ejbql.append(hireStageCode.toString());
                ejbql.append(",");
            }
            ejbql = ejbql.deleteCharAt(ejbql.lastIndexOf(","));
            ejbql.append(")");
        }
        System.out.println("EmployeesFacadeBean.getCountByHireStageWithPaging ::KAMAL:: " + ejbql.toString());

        Query query = EM().createQuery(ejbql.toString());

        return (Long)query.getSingleResult();
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public Long getCountByHireStageAndMinistryWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                      SharedApplicationException {

        /////////////////////
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            IPagingRequestDTO requestDTO = ((IPagingRequestDTO)searchDTO.getPagingRequestDTO());


            StringBuilder queryStr =
                new StringBuilder("SELECT HR_EMP_EMPLOYEES.* FROM HR_EMP_EMPLOYEES " + "INNER JOIN INF_KWT_CITIZENS_RESIDENTS " +
                                  "ON HR_EMP_EMPLOYEES.REAL_CIVIL_ID = INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID " +
                                  "WHERE HR_EMP_EMPLOYEES.HIRSTAGE_CODE IN ");
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
                queryStr.append(" AND hr_emp_employees.HIRSTATUS_CODE=" + searchDTO.getEmpHireStatus() + "");
            }
            if (searchDTO.getEmpHireTypes() != null) {
                queryStr.append(" AND hr_emp_employees.HIRTYPE_CODE=" + searchDTO.getEmpHireTypes() + "");
            }
            if (searchDTO.getActiveFlag() != null) {
                queryStr.append(" AND hr_emp_employees.ACTIVE_FLAG=" + searchDTO.getActiveFlag() + "");
            }
            if (searchDTO.getMinistryCode() != null) {
                queryStr.append(" and hr_emp_employees.min_code = " + searchDTO.getMinistryCode());
            }
            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                queryStr.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    queryStr.append(column);
                    if (!requestDTO.isSortAscending()) {
                        queryStr.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        queryStr.append(" , ");
                    }
                }
            }

            System.out.println("KAMAL = " + queryStr.toString());
            Query query = EM().createNativeQuery(queryStr.toString(), EmployeesEntity.class);


            //EM().createQuery("SELECT o FROM EmployeesEntity o").setHint("toplink.refresh", "true").getResultList();

            List<EmployeesEntity> list = query.getResultList();
            for (EmployeesEntity entity : list) {

                //            List<EmployeesEntity> empsList =
                //                EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setHint("toplink.refresh",
                //                                                                                                     "true").setParameter("civilId",
                //                                                                                                                          entity.getCivilId()).getResultList();
                //            EmployeesEntity employeesEntity = empsList.get(0);

                if (entity == null) {
                    throw new ItemNotFoundException();
                }
                boolean ret = false;

                if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getRealCivilId());
                    } catch (Exception e) {
                    }
                    if (ret) {
                        listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
                    }
                } else if (searchDTO.getExperienceCheck() == IEMPConstants.HAS_NOT_EXPERIENCE) {
                    try {
                        ret = InfClientFactory.getKwtWorkDataClient().isEmpHasExperience(entity.getRealCivilId());
                    } catch (Exception e) {
                    }
                    if (!ret) {
                        listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
                    }
                } else {
                    listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
                }
            }

            if (listDTO.size() == 0) {
                throw new ItemNotFoundException();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        Long count = (long)listDTO.size();
        return count;

    }

    public List<IBasicDTO> searchWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                         SharedApplicationException {
        IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

        IPagingRequestDTO requestDTO = ((IPagingRequestDTO)searchDTO.getPagingRequestDTO());

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();

        StringBuilder queryStr = new StringBuilder("SELECT HR_EMP_EMPLOYEES.CIVIL_ID FROM HR_EMP_EMPLOYEES ");

        if ((searchDTO.getFirstName() != null) || (searchDTO.getSecondName() != null) ||
            (searchDTO.getThirdName() != null) || (searchDTO.getFamilyName() != null) ||
            (searchDTO.getEnglishName() != null)) {

            queryStr.append(" INNER JOIN INF_KWT_CITIZENS_RESIDENTS ON INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID = HR_EMP_EMPLOYEES.CIVIL_ID ");
        }

        queryStr.append(" WHERE HR_EMP_EMPLOYEES.CIVIL_ID = HR_EMP_EMPLOYEES.CIVIL_ID ");

        if (searchDTO.getCivilId() != null)
            queryStr.append(" AND  HR_EMP_EMPLOYEES.CIVIL_ID='" + searchDTO.getCivilId() + "'");
        if (searchDTO.getFirstName() != null) {
            queryStr.append(" AND (" +
                            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME",
                                                                                    searchDTO.getFirstName()) + " ) ");
        }
        if (searchDTO.getSecondName() != null) {
            queryStr.append(" AND (" +
                            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME",
                                                                                    searchDTO.getSecondName()) +
                            " ) ");
        }
        if (searchDTO.getThirdName() != null) {
            queryStr.append(" AND (" +
                            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME",
                                                                                    searchDTO.getThirdName()) + " ) ");
        }
        if (searchDTO.getLastName() != null) {
            queryStr.append(" AND (" +
                            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.LAST_NAME",
                                                                                    searchDTO.getLastName()) + " ) ");
        }
        if (searchDTO.getFamilyName() != null) {
            queryStr.append(" AND (" +
                            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME",
                                                                                    searchDTO.getFamilyName()) +
                            " ) ");
        }
        if (searchDTO.getEnglishName() != null) {
            queryStr.append(" AND (" +
                            QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.ENGLISH_NAME",
                                                                                    searchDTO.getEnglishName()) +
                            " ) ");
        }

        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            queryStr.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                queryStr.append(column);
                if (!requestDTO.isSortAscending()) {
                    queryStr.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    queryStr.append(" , ");
                }
            }
        }

        Query query = EM().createNativeQuery(queryStr.toString(), EmployeesEntity.class);
        if (requestDTO != null) {
            query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
            query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
        }

        List<EmployeesEntity> list = query.getResultList();
        for (EmployeesEntity entity : list) {

            List<EmployeesEntity> empsList =
                EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setHint("toplink.refresh",
                                                                                                       "true").setParameter("civilId",
                                                                                                                            entity.getCivilId()).getResultList();
            EmployeesEntity employeesEntity = empsList.get(0);

            if (employeesEntity == null) {
                throw new NoResultException();
            }
            listDTO.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
        }

        if (listDTO.size() == 0) {
            throw new NoResultException();
        }

        return listDTO;
    }

    public Long getSearchCount(IBasicDTO _searchDTO) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)_searchDTO;

            StringBuilder queryStr = new StringBuilder("SELECT COUNT(*) FROM HR_EMP_EMPLOYEES ");

            if ((searchDTO.getFirstName() != null) || (searchDTO.getSecondName() != null) ||
                (searchDTO.getThirdName() != null) || (searchDTO.getFamilyName() != null) ||
                (searchDTO.getEnglishName() != null)) {

                queryStr.append(" INNER JOIN INF_KWT_CITIZENS_RESIDENTS ON INF_KWT_CITIZENS_RESIDENTS.CIVIL_ID = HR_EMP_EMPLOYEES.CIVIL_ID ");
            }

            queryStr.append(" WHERE HR_EMP_EMPLOYEES.CIVIL_ID = HR_EMP_EMPLOYEES.CIVIL_ID ");

            if (searchDTO.getCivilId() != null)
                queryStr.append(" AND  HR_EMP_EMPLOYEES.CIVIL_ID='" + searchDTO.getCivilId() + "'");
            if (searchDTO.getFirstName() != null) {
                queryStr.append(" AND (" +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.FIRST_NAME",
                                                                                        searchDTO.getFirstName()) +
                                " ) ");
            }
            if (searchDTO.getSecondName() != null) {
                queryStr.append(" AND (" +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.SECOND_NAME",
                                                                                        searchDTO.getSecondName()) +
                                " ) ");
            }
            if (searchDTO.getThirdName() != null) {
                queryStr.append(" AND (" +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.THIRD_NAME",
                                                                                        searchDTO.getThirdName()) +
                                " ) ");
            }
            if (searchDTO.getLastName() != null) {
                queryStr.append(" AND (" +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.LAST_NAME",
                                                                                        searchDTO.getLastName()) +
                                " ) ");
            }
            if (searchDTO.getFamilyName() != null) {
                queryStr.append(" AND (" +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.FAMILY_NAME",
                                                                                        searchDTO.getFamilyName()) +
                                " ) ");
            }
            if (searchDTO.getEnglishName() != null) {
                queryStr.append(" AND (" +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("INF_KWT_CITIZENS_RESIDENTS.ENGLISH_NAME",
                                                                                        searchDTO.getEnglishName()) +
                                " ) ");
            }

            Vector vector = (Vector)EM().createNativeQuery(queryStr.toString()).getSingleResult();
            if (vector != null && !vector.isEmpty()) {
                return new Long(((BigDecimal)vector.get(0)).intValue());
            }

            return 0L;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public void refresh() throws DataBaseException {
        try {
            EM().createQuery("SELECT o FROM EmployeesEntity o").setHint("toplink.refresh", "true").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;

            }
        }
    }

    public List<IBasicDTO> searchByCivilAndName(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        StringBuffer ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuffer("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND Exists( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE kwt.civilId=o.realCivilId AND " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }

        // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        System.out.println(ejbql);

        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() <= 0) {
            throw new NoResultException();
        }
        List listDTO = new ArrayList();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;
    }

    public List<IBasicDTO> searchByMainHireType(Long hirtypeCode) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeesEntity> list =
                EM().createNamedQuery("EmployeesEntity.searchByMainHireType").setParameter("hirstatusCode",
                                                                                           IEMPConstant.EMP_STATUS_CANDIDATE).setParameter("hirtypeCode",
                                                                                                                                           hirtypeCode).setParameter("stageCancelNomination",
                                                                                                                                                                     IEMPConstant.EMP_STAGE_CANCEL_NOMINATION).setParameter("hireTypeStatus",
                                                                                                                                                                                                                            IEMPConstants.EMP_HIRE_TYPE_ACTIVE_STATUS).
                // 27/02/2014
                setParameter("activeFlag", IEMPConstants.EMP_CANCEL_Candidate).setHint("toplink.refresh",
                                                                                       "true").getResultList();

            for (EmployeesEntity employeesEntity : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAllEmployeesWithActiveHireTypes() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeesEntity> list =
                EM().createNamedQuery("EmployeesEntity.getAllEmployeesWithActiveHireTypes").setParameter("hirstatusCode",
                                                                                                         IEMPConstant.EMP_STATUS_CANDIDATE).setParameter("stageCancelNomination",
                                                                                                                                                         IEMPConstant.EMP_STAGE_CANCEL_NOMINATION).setParameter("hireTypeStatus",
                                                                                                                                                                                                                IEMPConstants.EMP_HIRE_TYPE_ACTIVE_STATUS).
                // 27/02/2014
                setParameter("activeFlag", IEMPConstants.EMP_CANCEL_Candidate).setHint("toplink.refresh",
                                                                                       "true").getResultList();
            for (EmployeesEntity employeesEntity : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> filterByHireTypeForHireExecute(Long hirtypeCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeesEntity> list =
                EM().createNamedQuery("EmployeesEntity.filterByHireTypeForEmpExecute").setParameter("hirtypeCode",
                                                                                                    hirtypeCode).setParameter("hirstatusCode",
                                                                                                                              IEMPConstants._EMP_STATUS_CANDIDATE).setParameter("hirstageCode",
                                                                                                                                                                                IEMPConstants._EMP_STAGE_WAITING_EXECUTION).setHint("toplink.refresh",
                                                                                                                                                                                                                                    "true").getResultList();
            for (EmployeesEntity employeesEntity : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> filterByAllHireTypesForHireExecute() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeesEntity> list =
                EM().createNamedQuery("EmployeesEntity.filterByAllHireTypesForHireExecute").setParameter("hiretype1",
                                                                                                         IEMPConstants._EMP_CENTERAL_HIRE_TYPE).setParameter("hiretype2",
                                                                                                                                                             IEMPConstants._EMP_CANDIDATE_FOR_MINISTRY).setParameter("hiretype3",
                                                                                                                                                                                                                     IEMPConstants._EMP_REEMPLOYEMENTS).setParameter("hirstatusCode",
                                                                                                                                                                                                                                                                     IEMPConstants._EMP_STATUS_CANDIDATE).setParameter("hirstageCode",
                                                                                                                                                                                                                                                                                                                       IEMPConstants._EMP_STAGE_WAITING_EXECUTION).setHint("toplink.refresh",
                                                                                                                                                                                                                                                                                                                                                                           "true").getResultList();
            for (EmployeesEntity employeesEntity : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long checkEmployeeRequiredDocumentsStatus(Long serial) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            Vector result = null;
            Long returnedNumber = ISystemConstant.VIRTUAL_VALUE;
            String query =
                "SELECT DECODE(COUNT(1),0,1,0) " + "FROM HR_EMP_REQUIRED_DOCUMENTS RD,HR_EMP_EMPLOYEES E " + "WHERE RD.HIRTYPE_CODE = E.HIRTYPE_CODE " +
                "AND RD.STATUS = 1 " + "AND RD.BASIC_STATUS = 1 " +
                "AND ( (BASC_OWNER.HR_INF_INTERFACE_PACK.IS_CIVIL_KW(E.CIVIL_ID) = 1 AND RD.NATIONALITY_TYPE = 1) OR  " +
                "(BASC_OWNER.HR_INF_INTERFACE_PACK.IS_CIVIL_KW(E.CIVIL_ID) = 0 AND RD.NATIONALITY_TYPE = 2) OR " +
                "(RD.NATIONALITY_TYPE = 3)) " +
                "AND ( (BASC_OWNER.HR_INF_INTERFACE_PACK.GET_CIVIL_GENDER(E.CIVIL_ID) = 1 AND RD.GENDER_TYPE = 1) OR " +
                "(BASC_OWNER.HR_INF_INTERFACE_PACK.GET_CIVIL_GENDER(E.CIVIL_ID) = 2 AND RD.GENDER_TYPE = 2) OR " +
                "(RD.GENDER_TYPE = 3)) " + "AND NOT EXISTS (SELECT 1 " + "FROM HR_EMP_EMPLOYEE_DOCUMENTS ED " +
                "WHERE ED.DOCTYPE_CODE = RD.DOCTYPE_CODE " + "AND ED.CIVIL_ID  = E.CIVIL_ID " +
                "AND ((ATTACHMENT_REQUIRED = 1 AND ATTACHMENT_FILE IS NOT NULL ) OR ATTACHMENT_REQUIRED = 0 ) ) " +
                "AND E.CIVIL_ID = " + serial;

            result = (Vector)EM().createNativeQuery(query).getSingleResult();
            BigDecimal value = (BigDecimal)result.get(0);
            if (value != null) {
                returnedNumber = value.longValue();
            }
            return returnedNumber;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long checkEmployeeRequiredProceduresStatus(Long serial) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            Vector result = null;
            Long returnedNumber = ISystemConstant.VIRTUAL_VALUE;
            String query =
                "SELECT DECODE(COUNT(1),0,1,0) " + "FROM HR_EMP_HT_PROCEDURES HP,HR_EMP_EMPLOYEES E " + "WHERE HP.HIRTYPE_CODE = E.HIRTYPE_CODE " +
                "AND HP.STATUS = 1 " +
                "AND ( (BASC_OWNER.HR_INF_INTERFACE_PACK.IS_CIVIL_KW(E.CIVIL_ID) = 1 AND HP.NATIONALITY_TYPE = 1) OR " +
                "(BASC_OWNER.HR_INF_INTERFACE_PACK.IS_CIVIL_KW(E.CIVIL_ID) = 0 AND HP.NATIONALITY_TYPE = 2) OR " +
                "(HP.NATIONALITY_TYPE = 3)) " +
                "AND ( (BASC_OWNER.HR_INF_INTERFACE_PACK.GET_CIVIL_GENDER(E.CIVIL_ID) = 1 AND HP.GENDER_TYPE = 1) OR " +
                "(BASC_OWNER.HR_INF_INTERFACE_PACK.GET_CIVIL_GENDER(E.CIVIL_ID) = 2 AND HP.GENDER_TYPE = 2) OR " +
                "(HP.GENDER_TYPE = 3)) " + "AND NOT EXISTS (SELECT 1 " + "FROM HR_EMP_EMPLOYEE_PROCEDURES EP " +
                "WHERE EP.HIRPROCEDURE_CODE = HP.HIRPROCEDURE_CODE " + "AND EP.CIVIL_ID  = E.CIVIL_ID " +
                "AND EP.PRORESULT_CODE IN (1,3) ) " + "AND E.CIVIL_ID = " + serial;

            result = (Vector)EM().createNativeQuery(query).getSingleResult();
            BigDecimal value = (BigDecimal)result.get(0);
            if (value != null) {
                returnedNumber = value.longValue();
            }
            return returnedNumber;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> filterByHireTypeForHireExecuteWithPaging(Long hirtypeCode,
                                                                    IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                         SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            StringBuilder ejbql = null;

            ejbql =
                    new StringBuilder("select o from EmployeesEntity o where o.hirtypeCode =:hirtypeCode and o.hirstatusCode = :hirstatusCode and  o.hirstageCode =:hirstageCode");


            Query query = EM().createQuery(ejbql.toString());
            query.setParameter("hirtypeCode", hirtypeCode).setParameter("hirstatusCode",
                                                                        IEMPConstants._EMP_STATUS_CANDIDATE).setParameter("hirstageCode",
                                                                                                                          IEMPConstants._EMP_STAGE_WAITING_EXECUTION).setHint("toplink.refresh",
                                                                                                                                                                              "true");
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            List<EmployeesEntity> list = query.getResultList();
            for (EmployeesEntity employeesEntity : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employeesEntity));
            }

            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
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
     * return hire status 1 = hired
     * @param civilID
     * @return
     * @throws RemoteException
     * @author Dina Abd El-Rahim 06/03/2014
     **/
    public Long executeCheckEmpHireStatus(Long civilId) throws DataBaseException, SharedApplicationException {
        CallableStatement stm = null;
        try {
            final String QUERY = "{? = call HR_EMP_PAC.CHECK_EMP_HIRE(?)}";
            Connection con = getConnection();
            stm = con.prepareCall(QUERY);
            stm.registerOutParameter(1, OracleTypes.NUMBER);
            stm.setLong(2, civilId);
            stm.execute();
            BigDecimal bigDecimalResult = (BigDecimal)stm.getObject(1);
            return (bigDecimalResult == null) ? 0L : bigDecimalResult.longValue();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        } finally {
            close(stm);
        }
    }


    public Long isJobHasEmployees(Long jobCode) throws DataBaseException, SharedApplicationException {
        try {
            StringBuilder sql = new StringBuilder("SELECT count(*) ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            sql.append("WHERE JOB_CODE=" + jobCode + " ");


            Long count = null;

            Vector v = (Vector)EM().createNativeQuery(sql.toString()).getSingleResult();
            if (!v.isEmpty()) {
                count = Long.parseLong(v.get(0).toString());
            }
            return count;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    private String getExcludedWorkCentersAsCommaSeparatedStr() {
        StringBuilder workCentersSB = new StringBuilder();
        List<String> excludedWorkCenters =
            CSCSecurityInfoHelper.getExcludedWorkCentersByMinistryFromRequest(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());

        if (excludedWorkCenters != null) {

            workCentersSB.append("AND o.wrkCode NOT IN( ");
            for (String workCenter : excludedWorkCenters) {
                workCentersSB.append("'").append(workCenter).append("'").append(",");
            }

            String excludedWorkCentersAsCommaSeparatedStr =
                workCentersSB.toString().substring(0, workCentersSB.length() - 1);

            return excludedWorkCentersAsCommaSeparatedStr + " )";
        }

        return null;
    }

    public List<IBasicDTO> simpleSearchVacEmpWithPaging(IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            list = buildSearchQueryEmpVacWithPaging(basicDTO, requestDTO);
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getSimpleEmployeesDTO(entity));
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


    //    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)

    public List<IBasicDTO> searchVacEmpGetAll(IBasicDTO basicDTO) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return searchQueryEmpVac(basicDTO);
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

    public List<IBasicDTO> searchVacEmp(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            //            List<EmployeesEntity> list = null;
            //            list = buildSearchQueryEmpVac(basicDTO);
            //            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            //            int c = 0;
            //            for (EmployeesEntity entity : list) {
            //                c++;
            //                System.out.println("Error civil no:============" + c);
            //                listDTO.add(EmpDTOFactory.createEmployeesDTO(entity, true));
            //
            //            }
            //            return listDTO;
            return buildSearchQueryEmpVacDTO(basicDTO);
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


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public List searchVacEmpEnhanced(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            List list = null;
            list = buildSearchQueryEmpVacEnhanced(basicDTO);
            //            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            //            for (EmployeesEntity entity : list) {
            //                listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
            //            }
            // return listDTO;
            return list;
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

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeesEntity> buildSearchQueryEmpVac(IBasicDTO basicDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            Query query = null;
            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT * ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where active_flag= " + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in ( " +
                    //     getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")"); //added by mohamed sayed at 20-8-2015
            sql.append("AND MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());
            if (vacEmpSearchDTO.getWorkCenters() != null) {
                sql.append(" AND WRK_CODE IN(");
                sql.append(vacEmpSearchDTO.getWorkCenters());
                sql.append(")");
            }
            sql.append(" AND JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }

            if (vacEmpSearchDTO.getCivilId() != null)
                sql.append(" AND  REAL_CIVIL_ID = '" + vacEmpSearchDTO.getCivilId() + "'");
            if (vacEmpSearchDTO.getEmpName() != null && !vacEmpSearchDTO.getEmpName().equals("")) {
                sql.append(" AND REAL_CIVIL_ID IN ( Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   vacEmpSearchDTO.getEmpName()) +
                           " ) ");
            }

            System.out.print("sql>> " + sql.toString());
            query = EM().createNativeQuery(sql.toString(), EmployeesEntity.class);


            System.out.println(sql.toString());
            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    private List<IBasicDTO> buildSearchQueryEmpVacDTO(IBasicDTO basicDTO) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            List<IBasicDTO> dtoList = new ArrayList();
            Query query = null;
            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql =
                new StringBuilder("SELECT  Emp.civil_id, Emp.HIRSTATUS_CODE,Emp.MIN_CODE,Emp.WRK_CODE,Emp.JOB_CODE,Emp.REAL_CIVIL_ID,CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwtc.first_name , ' ' ) , CONCAT ( kwtc.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwtc.third_name , ' ' ) , CONCAT ( kwtc.last_name , ' ' ) ) ) , kwtc.family_name ),kwtc.nationality ");
            sql.append(" FROM HR_EMP_EMPLOYEES Emp, inf_kwt_citizens_residents kwtc ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where Emp.real_civil_ID = kwtc.civil_Id AND Emp.active_flag= " + ISystemConstant.ACTIVE_FLAG +
                    //    " and Emp.HIRSTATUS_CODE in ( " + getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    " and Emp.HIRSTATUS_CODE in ( " + EmpUtils.getStatusForHireWithoutDELEGATION() +
                    ")"); //added by mohamed sayed at 20-8-2015
                    sql.append("AND Emp.MIN_CODE =");
                    sql.append(vacEmpSearchDTO.getMinistryCode());
                    IBgtProgramsDTO level =vacEmpSearchDTO.getList1();
                    if (level.getCode() != null && (vacEmpSearchDTO.getWorkCenters() ==null || vacEmpSearchDTO.getWorkCenters().equals(""))) {
                                   sql.append(" and BGTPRG_CODE =" +
                                                level.getCode().getKey());
                               } else {
                                   if (vacEmpSearchDTO.getWorkCenters() != null) {
                                       sql.append(" AND WRK_CODE IN(");
                                       sql.append(vacEmpSearchDTO.getWorkCenters());
                                       sql.append(")");
                                   }
                               }
            sql.append(" AND Emp.JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", Emp.JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("Emp.CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }

            if (vacEmpSearchDTO.getCivilId() != null)
                sql.append(" AND  Emp.REAL_CIVIL_ID = '" + vacEmpSearchDTO.getCivilId() + "'");
            if (vacEmpSearchDTO.getEmpName() != null && !vacEmpSearchDTO.getEmpName().equals("")) {
                sql.append(" AND Emp.REAL_CIVIL_ID IN ( Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   vacEmpSearchDTO.getEmpName()) +
                           " ) ");
            }

            System.out.print("sql>> " + sql.toString());
            //query = EM().createNativeQuery(sql.toString(), EmployeesEntity.class);


            System.out.println(sql.toString());
            //list = query.getResultList();
            Query q = EM().createNativeQuery(sql.toString()).setHint("toplink.refresh", "true");
            List<Vector> listV = q.getResultList();


            if (listV == null || listV.size() == 0)
                throw new NoResultException();

            for (int i = 0; i < listV.size(); i++) {

                dtoList.add((EmployeesDTO)createEmpDTO(listV.get(i)));
            }

            return dtoList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    private List<IBasicDTO> searchQueryEmpVac(IBasicDTO basicDTO) throws DataBaseException,
                                                                         SharedApplicationException {
        try {

            List<IBasicDTO> dtoList = new ArrayList();
            Query query = null;
            IVacEmployeeSearchDTO vacEmpSearchDTO = ((IVacGroupVacationDTO)basicDTO).getVacEmployeesSearchDTO();

            StringBuilder sql =
                new StringBuilder("SELECT  Emp.civil_id, Emp.HIRSTATUS_CODE,Emp.MIN_CODE,Emp.WRK_CODE,Emp.JOB_CODE,Emp.REAL_CIVIL_ID,CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwtc.first_name , ' ' ) , CONCAT ( kwtc.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwtc.third_name , ' ' ) , CONCAT ( kwtc.last_name , ' ' ) ) ) , kwtc.family_name ),kwtc.nationality ");
            sql.append(" FROM HR_EMP_EMPLOYEES Emp, inf_kwt_citizens_residents kwtc ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where Emp.real_civil_ID = kwtc.civil_Id AND Emp.active_flag= " + ISystemConstant.ACTIVE_FLAG +
                    //    " and Emp.HIRSTATUS_CODE in ( " + getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    " and Emp.HIRSTATUS_CODE in ( " + EmpUtils.getStatusForHireWithoutDELEGATION() +
                    ")"); //added by mohamed sayed at 20-8-2015
            sql.append("AND Emp.MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());
            IBgtProgramsDTO level =vacEmpSearchDTO.getList1();
//            System.out.println(level.getCode().getKey());
            if (level.getCode() != null && (vacEmpSearchDTO.getWorkCenters() ==null || vacEmpSearchDTO.getWorkCenters().equals(""))) {
                           sql.append(" and BGTPRG_CODE =" +
                                        level.getCode().getKey());
                       } else {
                           if (vacEmpSearchDTO.getWorkCenters() != null) {
                               sql.append(" AND WRK_CODE IN(");
                               sql.append(vacEmpSearchDTO.getWorkCenters());
                               sql.append(")");
                           }
                       }
            sql.append(" AND Emp.JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", Emp.JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("Emp.CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }

            if (vacEmpSearchDTO.getCivilId() != null)
                sql.append(" AND  Emp.REAL_CIVIL_ID = '" + vacEmpSearchDTO.getCivilId() + "'");
            if (vacEmpSearchDTO.getEmpName() != null && !vacEmpSearchDTO.getEmpName().equals("")) {
                sql.append(" AND Emp.REAL_CIVIL_ID IN ( Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   vacEmpSearchDTO.getEmpName()) +
                           " ) ");
            }

            System.out.print("sql>> " + sql.toString());
            //query = EM().createNativeQuery(sql.toString(), EmployeesEntity.class);


            System.out.println(sql.toString());
            //list = query.getResultList();
            Query q = EM().createNativeQuery(sql.toString()).setHint("toplink.refresh", "true");
            List<Vector> listV = q.getResultList();


            if (listV == null || listV.size() == 0)
                throw new NoResultException();
            IEmployeesDTO DTO;
            IVacEmployeeVacationsDTO vacEmployeeVacationsDTO;
            IVacGroupVacationDTO vacGroupVacationDTO = (IVacGroupVacationDTO)basicDTO;
            for (int i = 0; i < listV.size(); i++) {
                vacEmployeeVacationsDTO = new VacEmployeeVacationsDTO();
                DTO = createEmpDTO(listV.get(i));
                vacEmployeeVacationsDTO.setEmployeesDTO(DTO);
                /////////////////////////////////////////
                vacEmployeeVacationsDTO.setVacVacationTypesDTO(vacGroupVacationDTO.getVacVacationTypesDTO());
                vacEmployeeVacationsDTO.setFromDate(vacGroupVacationDTO.getFromDate());
                vacEmployeeVacationsDTO.setUntilDate(vacGroupVacationDTO.getUntilDate());
                vacEmployeeVacationsDTO.setVacReasonTxt(vacGroupVacationDTO.getVacReasonTxt());
                vacEmployeeVacationsDTO.setVacNotes(vacGroupVacationDTO.getVacNotes());
                vacEmployeeVacationsDTO.setVacationPeriod(vacGroupVacationDTO.getVacationPeriod());
                vacEmployeeVacationsDTO.setRequestDatetime(vacGroupVacationDTO.getRequestDatetime());
                vacEmployeeVacationsDTO.setRequiredSalary(vacGroupVacationDTO.getRequiredSalary());
                /////////////////////////////////
                dtoList.add(vacEmployeeVacationsDTO);

            }

            return dtoList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    private IEmployeesDTO createEmpDTO(Vector v) {
        return new EmployeesDTO(Long.parseLong(v.get(0).toString()), Long.parseLong(v.get(1).toString()),
                                Long.parseLong(v.get(2).toString()),
                                (v.get(2).toString().concat("*").concat(v.get(3).toString())), v.get(4).toString(),
                                Long.parseLong(v.get(5).toString()), v.get(6).toString(),
                                Long.parseLong(v.get(7).toString()));
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List buildSearchQueryEmpVacEnhanced(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            Query query = null;
            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT civil_id ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in ( " +
                    //        getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")"); // added by mohamed sayed at 20-8-2015
            sql.append("AND MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());
            if (vacEmpSearchDTO.getWorkCenters() != null) {
                sql.append(" AND WRK_CODE IN(");
                sql.append(vacEmpSearchDTO.getWorkCenters());
                sql.append(")");
            }
            sql.append(" AND JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }

            if (vacEmpSearchDTO.getCivilId() != null)
                sql.append(" AND  REAL_CIVIL_ID = '" + vacEmpSearchDTO.getCivilId() + "'");
            if (vacEmpSearchDTO.getEmpName() != null && !vacEmpSearchDTO.getEmpName().equals("")) {
                sql.append(" AND REAL_CIVIL_ID IN ( Select kwt.civil_id From inf_kwt_citizens_residents kwt WHERE " +
                           QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_name , ' ' ) , CONCAT ( kwt.second_name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_name , ' ' ) , CONCAT ( kwt.last_name , ' ' ) ) ) , kwt.family_name )",
                                                                                   vacEmpSearchDTO.getEmpName()) +
                           " ) ");
            }

            System.out.print("sql>> " + sql.toString());
            query = EM().createNativeQuery(sql.toString());


            System.out.println(sql.toString());
            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    public Long simpleSearchCountVacWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return buildSearchCountVacQueryWithPaging(basicDTO);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long simpleSearchCountVacWithPaging(IBasicDTO basicDTO,IBgtProgramsDTO level) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return buildSearchCountVacQueryWithPaging(basicDTO,level);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeesEntity> buildSearchQueryEmpVacWithPaging(IBasicDTO basicDTO,
                                                                   IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                        SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            Query query = null;
            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT * ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in ( " +
                    //  getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")"); //added by mohamed sayed at 20-8-2015
            sql.append("AND MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());
            if (vacEmpSearchDTO.getWorkCenters() != null) {
                sql.append(" AND WRK_CODE IN(");
                sql.append(vacEmpSearchDTO.getWorkCenters());
                sql.append(")");
            }
            sql.append(" AND JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }


            // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
            //added by B.Horse Team  at 31-3-2016
            // stroy ID CSC-17343  work Center data filter
            //            String wrkcode = initWrkcenterTree();
            //            if (wrkcode != null && !wrkcode.isEmpty()) {
            //                sql.append(" and WRK_CODE in( " + wrkcode + ")");
            //            }

            // apply native sol for Data Filteration By M.abdelsabour CSC-21713
            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and wrk_code in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and wrk_code Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }


            query = EM().createNativeQuery(sql.toString(), EmployeesEntity.class);
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            System.out.println(sql.toString());
            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeesEntity> buildSearchQueryEmpVacWithPaging(IBasicDTO basicDTO,IBgtProgramsDTO level,
                                                                   IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                        SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            Query query = null;
            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT * ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            sql.append(" where active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in ( " +
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")");
            sql.append(" AND MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());

            if (level.getCode() != null && (vacEmpSearchDTO.getWorkCenters() ==null || vacEmpSearchDTO.getWorkCenters().equals(""))) {
                sql.append(" and BGTPRG_CODE =" +
                             level.getCode().getKey());
            } else {
                if (vacEmpSearchDTO.getWorkCenters() != null) {
                    sql.append(" AND WRK_CODE IN(");
                    sql.append(vacEmpSearchDTO.getWorkCenters());
                    sql.append(")");
                }
            }
            sql.append(" AND JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }


            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and wrk_code in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and wrk_code Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }


            query = EM().createNativeQuery(sql.toString(), EmployeesEntity.class);
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            System.out.println(sql.toString());
            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private Long buildSearchCountVacQueryWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {

            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT count(CIVIL_ID) ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in ( " +
                    //  getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")"); //added by mohamed sayed at 20-8-2015
            sql.append(" AND MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());
            if (vacEmpSearchDTO.getWorkCenters() != null) {
                sql.append(" AND WRK_CODE IN(");
                sql.append(vacEmpSearchDTO.getWorkCenters());
                sql.append(")");
            }
            sql.append(" AND JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }

            // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
            //added by B.Horse Team  at 31-3-2016
            // stroy ID CSC-17343  work Center data filter
            //            String wrkcode = initWrkcenterTree();
            //            if (wrkcode != null && !wrkcode.isEmpty()) {
            //                sql.append(" and WRK_CODE in( " + wrkcode + ")");
            //            }

            // apply native sol for Data Filteration By M.abdelsabour CSC-21713
            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and wrk_code in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and wrk_code Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }

            Long count = null;

            Vector v = (Vector)EM().createNativeQuery(sql.toString()).getSingleResult();
            if (!v.isEmpty()) {
                count = Long.parseLong(v.get(0).toString());
            }
            if (count == 0) {
                throw new ItemNotFoundException();
            }

            return count;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private Long buildSearchCountVacQueryWithPaging(IBasicDTO basicDTO,IBgtProgramsDTO level) throws DataBaseException,
                                                                               SharedApplicationException {
        try {

            IVacEmployeeSearchDTO vacEmpSearchDTO = (IVacEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT count(CIVIL_ID) ");
            sql.append("FROM HR_EMP_EMPLOYEES ");
            sql.append(" where active_flag=" + ISystemConstant.ACTIVE_FLAG + " and HIRSTATUS_CODE in ( " +
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")");
            sql.append(" AND MIN_CODE =");
            sql.append(vacEmpSearchDTO.getMinistryCode());
            if (level.getCode() != null && (vacEmpSearchDTO.getWorkCenters() ==null || vacEmpSearchDTO.getWorkCenters().equals(""))) {
                sql.append(" and BGTPRG_CODE =" +
                             level.getCode().getKey());
            } else {
                if (vacEmpSearchDTO.getWorkCenters() != null) {
                    sql.append(" AND WRK_CODE IN(");
                    sql.append(vacEmpSearchDTO.getWorkCenters());
                    sql.append(")");
                }

            }

            sql.append(" AND JOB_CODE = NVL(");
            sql.append(vacEmpSearchDTO.getJobCode());
            sql.append(", JOB_CODE)");

            if (vacEmpSearchDTO.getDegreeCode() != null) {
                sql.append(" AND hr_emp_pac.get_emp_degree(REAL_CIVIL_ID) =");
                sql.append(vacEmpSearchDTO.getDegreeCode());
            }
            if (vacEmpSearchDTO.getCivilIds() != null) {
                sql.append(" AND ");
                sql.append("CIVIL_ID NOT IN (");
                sql.append(vacEmpSearchDTO.getCivilIds());
                sql.append(" )");
            }
            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and wrk_code in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and wrk_code Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }

            Long count = null;
System.out.println(sql.toString());
            Vector v = (Vector)EM().createNativeQuery(sql.toString()).getSingleResult();
            if (!v.isEmpty()) {
                count = Long.parseLong(v.get(0).toString());
            }
            if (count == 0) {
                throw new ItemNotFoundException();
            }

            return count;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    private List<EmployeeExtraDataEntity> getAllEmployeeExtraDataByCivilId(Long civilId) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            return EM().createNamedQuery("EmployeeExtraDataEntity.getAllEmployeeExtraDataByCivilId").setParameter("civilId",
                                                                                                                  civilId).setHint("toplink.refresh",
                                                                                                                                   "true").getResultList();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> completeDataSimpleSearch(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

            ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId ");

            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
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

            // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
            //added by Technical Team [m.sayed] at 31-3-2016
            // stroy ID CSC-17343  work Center data filter
            //            String wrkcode = initWrkcenterTree();
            //            if (wrkcode != null && !wrkcode.isEmpty()) {
            //                ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
            //            }
            if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName",
                                                                                 employeeSearchDTO.getWorkCenterName()) +
                             " ) ");

            if (employeeSearchDTO.getStartWorkingDate() != null)
                ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");


            if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                             " ) ");


            //////////CH HR 762/////////////////////
            if (employeeSearchDTO.getHireDateFrom() != null)
                ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");


            ejbql.append(" And o.hirstatusCode=");
            ejbql.append(IEMPConstant.EMP_STATUS_CANDIDATE);
            ejbql.append(" AND o.hirstageCode <>");
            ejbql.append(IEMPConstant.EMP_STAGE_CANCEL_NOMINATION);
            ejbql.append(" AND (o.hirtypeCode = ");
            ejbql.append(employeeSearchDTO.getEmpHireTypes());
            ejbql.append(" or o.hireTypesEntity.parentHireTypeCode =");
            ejbql.append(employeeSearchDTO.getEmpHireTypes());
            ejbql.append(" ) AND o.hireTypesEntity.status =");
            ejbql.append(IEMPConstants.EMP_HIRE_TYPE_ACTIVE_STATUS);
            ejbql.append(" AND o.activeFlag <>");
            ejbql.append(IEMPConstants.EMP_CANCEL_Candidate);


            List<EmployeesEntity> list = null;
            System.out.println("EmployeesFacadeBean.new complete simple Search ::::" + ejbql.toString());
            if (ejbql != null)
                list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
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

    public List<IBasicDTO> getAllEmployeesByPremittedMinistries(IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

            if (employeeSearchDTO.getActiveFlag() != null) {
                ejbql.append(employeeSearchDTO.getActiveFlag());
            } else {
                ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
            }
            if (employeeSearchDTO.getCivilId() != null) {
                ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
            }
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE ");
                ejbql.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");

            }

            List categories = CSCSecurityInfoHelper.getCategoriesFromRequest();
            if (!(categories == null || categories.size() == 0)) {
                ejbql.append(" AND o.minCode in (");
                for (Object obj : categories) {
                    ICategoryInfo cat = (ICategoryInfo)obj;
                    for (IMinistryInfo min : cat.getMinistries()) {
                        ejbql.append(min.getCode());
                        ejbql.append(",");
                    }
                }
                ejbql.replace(ejbql.length() - 1, ejbql.length(), "");
                ejbql.append(")");
            }

            List<EmployeesEntity> list = null;
            System.out.println("EmployeesFacadeBean.getAllEmployeesByPremittedMinistries ::hisham::" +
                               ejbql.toString());
            if (ejbql != null)
                list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
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

    public List<IBasicDTO> filterAvailableEntitiesUsingPaging(IBasicDTO employeeSearchDTO1,
                                                              IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            StringBuilder ejbql =
                new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId=o.realCivilId");
            HashMap<String, Object> queryParameters = new HashMap<String, Object>();
            IEmployeeSearchDTO employeeSearchDTO = (IEmployeeSearchDTO)employeeSearchDTO1;
            if (employeeSearchDTO.getCivilId() != null) {
                ejbql.append(" AND o.realCivilId= :realCivilId");
                queryParameters.put("realCivilId", employeeSearchDTO.getCivilId());
            }
            if (employeeSearchDTO.getFirstName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.firstName",
                                                                        employeeSearchDTO.getFirstName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getSecondName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.secondName",
                                                                        employeeSearchDTO.getSecondName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getThirdName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.thirdName",
                                                                        employeeSearchDTO.getThirdName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getLastName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.lastName",
                                                                        employeeSearchDTO.getLastName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getFamilyName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.familyName",
                                                                        employeeSearchDTO.getFamilyName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }

            if (employeeSearchDTO.getEnglishName() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.englishName LIKE '" + employeeSearchDTO.getEnglishName() +
                             "'");
            }
            if (employeeSearchDTO.getBirthDate() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.birthDate= :birthDate");
                queryParameters.put("birthDate", employeeSearchDTO.getBirthDate());
            }
            if (employeeSearchDTO.getGenderTypeCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.gentypeCode= :gentypeCode");
                queryParameters.put("gentypeCode", employeeSearchDTO.getGenderTypeCode());
            }
            if (employeeSearchDTO.getMaritalStatusCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.mltstatusCode= :mltstatusCode");
                queryParameters.put("mltstatusCode", employeeSearchDTO.getMaritalStatusCode());
            }
            if (employeeSearchDTO.getPhonesNo() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
            }
            if (employeeSearchDTO.getMobileNo() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
            }
            if (employeeSearchDTO.getReligionCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.religionCode= :religionCode");
                queryParameters.put("religionCode", employeeSearchDTO.getReligionCode());
            }
            if (employeeSearchDTO.getResidentTypeCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.restypeCode= :restypeCode");
                queryParameters.put("restypeCode", employeeSearchDTO.getResidentTypeCode());
            }
            if (employeeSearchDTO.getEndResidentDate() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.endResidentDate= :endResidentDate");
                queryParameters.put("endResidentDate", employeeSearchDTO.getEndResidentDate());
            }
            if (employeeSearchDTO.getMapCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.mapCode= :mapCode");
                queryParameters.put("mapCode", employeeSearchDTO.getMapCode());
            }
            if (employeeSearchDTO.getCapstatusCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.capstatusCode= :capstatusCode");
                queryParameters.put("capstatusCode", employeeSearchDTO.getCapstatusCode());
            }
            if (employeeSearchDTO.getSpecialCaseTypeCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.spccsetypeCode= :spccsetypeCode");
                queryParameters.put("spccsetypeCode", employeeSearchDTO.getSpecialCaseTypeCode());
            }

            if (employeeSearchDTO.getPassportNo() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.passportNo LIKE '" + employeeSearchDTO.getPassportNo() +
                             "'");
            }
            if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
                String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.minCode= :minCode");
                queryParameters.put("minCode", Long.parseLong(str[0]));
                ejbql.append(" AND o.wrkCode= :wrkCode");
                queryParameters.put("wrkCode", str[1]);
            }
            if (employeeSearchDTO.getMinistryCode() == null) {
                List categories = CSCSecurityInfoHelper.getCategoriesFromRequest();
                if (!(categories == null || categories.size() == 0)) {
                    ejbql.append(" AND o.minCode in (");
                    for (Object obj : categories) {
                        ICategoryInfo cat = (ICategoryInfo)obj;
                        for (IMinistryInfo min : cat.getMinistries()) {
                            ejbql.append(min.getCode());
                            ejbql.append(",");
                        }
                    }
                    ejbql.replace(ejbql.length() - 1, ejbql.length(), "");
                    ejbql.append(")");
                }
            }
            if (employeeSearchDTO.getTechJobCode() != null) {
                ejbql.append(" AND o.techJobCode= :techJobCode");
                queryParameters.put("techJobCode", employeeSearchDTO.getTechJobCode());
            }
            if (employeeSearchDTO.getJobCode() != null) {
                ejbql.append(" AND o.jobCode LIKE '" + employeeSearchDTO.getJobCode() + "'");
            }
            if (employeeSearchDTO.getCscFileNo() != null) {
                ejbql.append(" AND o.cscFileNo LIKE '" + employeeSearchDTO.getCscFileNo() + "'");
            }
            if (employeeSearchDTO.getMinistryFileNo() != null) {
                ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
            }
            if (employeeSearchDTO.getStartWorkingDate() != null) {
                ejbql.append(" AND o.startWorkingDate= :startWorkingDate");
                queryParameters.put("startWorkingDate", employeeSearchDTO.getStartWorkingDate());
            }
            if (employeeSearchDTO.getEndWorkingDate() != null) {
                ejbql.append(" AND o.endJobDate= :endJobDate");
                queryParameters.put("endJobDate", employeeSearchDTO.getEndWorkingDate());
            }
            if (employeeSearchDTO.getHireDate() != null) {
                ejbql.append(" AND o.hireDate= :hireDate");
                queryParameters.put("hireDate", employeeSearchDTO.getHireDate());
            }
            if (employeeSearchDTO.getEmpHireStages() != null) {
                ejbql.append(" AND o.hireStagesEntity.hirstageCode= :hirstageCode");
                queryParameters.put("hirstageCode", employeeSearchDTO.getEmpHireStages());
            }
            if (employeeSearchDTO.getEmpHireStatus() != null) {
                ejbql.append(" AND o.hireStatusEntity.hirstatusCode= :hirstatusCode");
                queryParameters.put("hirstatusCode", employeeSearchDTO.getEmpHireStatus());
            } else {
                if (employeeSearchDTO.getHireStatusList() != null) {
                    ejbql.append(" AND o.hireStatusEntity.hirstatusCode in (");
                    for (Long code : employeeSearchDTO.getHireStatusList()) {
                        ejbql.append(code);
                        ejbql.append(",");
                    }
                    ejbql.replace(ejbql.length() - 1, ejbql.length(), "");
                    ejbql.append(")");
                }
            }
            if (employeeSearchDTO.getEmpHireTypes() != null) {
                ejbql.append(" AND o.hireTypesEntity.hirtypeCode= :hirtypeCode");
                queryParameters.put("hirtypeCode", employeeSearchDTO.getEmpHireTypes());
            }

            if (employeeSearchDTO.getBgtTypesCode() != null) {
                ejbql.append(" AND o.bgtTypesEntity.typeCode= :typeCode");
                queryParameters.put("typeCode", employeeSearchDTO.getBgtTypesCode());
            }


            if (employeeSearchDTO.getCaderCode() != null) {
                if (employeeSearchDTO.getGroupCode() != null) {
                    if (employeeSearchDTO.getDegreeCode() != null) {
                        ejbql.append(" AND exists(select elm from EmpSalEmpSalaryElementsEntity elm where elm.elmguideCode= :elmguideCode");
                        queryParameters.put("elmguideCode", employeeSearchDTO.getDegreeCode());
                    } else {
                        ejbql.append(" AND exists(select elm from EmpSalEmpSalaryElementsEntity elm where elm.elmguideCode= :elmguideCode");
                        queryParameters.put("elmguideCode", employeeSearchDTO.getGroupCode());
                    }
                } else {
                    ejbql.append(" AND exists(select elm from EmpSalEmpSalaryElementsEntity elm where elm.elmguideCode= :elmguideCode");
                    queryParameters.put("elmguideCode", employeeSearchDTO.getCaderCode());
                }
                ejbql.append(" AND elm.civilId= o.civilId ");
                Date sysDate = new Date(new java.util.Date().getTime());
                ejbql.append(" AND elm.fromDate= :fromDate");
                queryParameters.put("fromDate", sysDate);
                ejbql.append(" AND elm.untilDate IS NULL OR elm.untilDate >= CURRENT_DATE ");
                ejbql.append(") ");
            }

            if (employeeSearchDTO.getBankCode() != null) {
                ejbql.append(" AND exists(select bnk from EmpPersonBankAccountsEntity bnk where bnk.bankCode= :bankCode");
                queryParameters.put("bankCode", employeeSearchDTO.getBankCode());
                if (employeeSearchDTO.getBankbranchCode() != null) {
                    String[] str = employeeSearchDTO.getBankbranchCode().split("\\*");
                    ejbql.append(" AND bnk.bnkbranchCode= :bnkbranchCode");
                    queryParameters.put("bnkbranchCode", Long.parseLong(str[1]));
                }
                ejbql.append(" AND bnk.civilId= o.realCivilId ");
                ejbql.append(" AND bnk.status=1) ");
            } else if (employeeSearchDTO.getAccountNo() != null) {
                ejbql.append(" AND exists(select bnk from EmpPersonBankAccountsEntity bnk where bnk.accountNo= :accountNo ");
                queryParameters.put("accountNo", employeeSearchDTO.getAccountNo());
                ejbql.append(" AND bnk.civilId= o.realCivilId ");
                ejbql.append(" AND bnk.status=1) ");
            }
            List<EmployeesEntity> list = null;

            Query query = null;

            if (ejbql != null) {
                try {
                    query = EM().createQuery(ejbql.toString());
                    if (queryParameters.size() != 0) {
                        for (Map.Entry<String, Object> param : queryParameters.entrySet()) {
                            query.setParameter(param.getKey(), param.getValue());
                        }
                    }
                } catch (Exception e) {
                    // TODO: Add catch code
                    e.printStackTrace();
                }
                if (requestDTO != null) {
                    query.setFirstResult(((requestDTO.getPageNum().intValue()) - 1) *
                                         requestDTO.getMaxNoOfRecords().intValue());
                    query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
                }
            }
            list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(new EmployeesDTO(entity));
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

    public Long filterAvailableEntitiesUsingPagingCount(IBasicDTO employeeSearchDTO1) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            StringBuilder ejbql =
                new StringBuilder("select count(o) from EmployeesEntity o WHERE o.realCivilId=o.realCivilId");
            HashMap<String, Object> queryParameters = new HashMap<String, Object>();
            IEmployeeSearchDTO employeeSearchDTO = (IEmployeeSearchDTO)employeeSearchDTO1;
            if (employeeSearchDTO.getCivilId() != null) {
                ejbql.append(" AND o.realCivilId= :realCivilId");
                queryParameters.put("realCivilId", employeeSearchDTO.getCivilId());
            }
            if (employeeSearchDTO.getFirstName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.firstName",
                                                                        employeeSearchDTO.getFirstName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getSecondName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.secondName",
                                                                        employeeSearchDTO.getSecondName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getThirdName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.thirdName",
                                                                        employeeSearchDTO.getThirdName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getLastName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.lastName",
                                                                        employeeSearchDTO.getLastName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }
            if (employeeSearchDTO.getFamilyName() != null) {
                String condition =
                    QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.citizensResidentsEntity.familyName",
                                                                        employeeSearchDTO.getFamilyName());
                ejbql.append(" AND ");
                ejbql.append(condition);
            }

            if (employeeSearchDTO.getEnglishName() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.englishName LIKE '" + employeeSearchDTO.getEnglishName() +
                             "'");
            }
            if (employeeSearchDTO.getBirthDate() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.birthDate= :birthDate");
                queryParameters.put("birthDate", employeeSearchDTO.getBirthDate());
            }
            if (employeeSearchDTO.getGenderTypeCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.gentypeCode= :gentypeCode");
                queryParameters.put("gentypeCode", employeeSearchDTO.getGenderTypeCode());
            }
            if (employeeSearchDTO.getMaritalStatusCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.mltstatusCode= :mltstatusCode");
                queryParameters.put("mltstatusCode", employeeSearchDTO.getMaritalStatusCode());
            }
            if (employeeSearchDTO.getPhonesNo() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.phonesNo LIKE '" + employeeSearchDTO.getPhonesNo() + "'");
            }
            if (employeeSearchDTO.getMobileNo() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.mobileNo LIKE '" + employeeSearchDTO.getMobileNo() + "'");
            }
            if (employeeSearchDTO.getReligionCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.religionCode= :religionCode");
                queryParameters.put("religionCode", employeeSearchDTO.getReligionCode());
            }
            if (employeeSearchDTO.getResidentTypeCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.restypeCode= :restypeCode");
                queryParameters.put("restypeCode", employeeSearchDTO.getResidentTypeCode());
            }
            if (employeeSearchDTO.getEndResidentDate() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.endResidentDate= :endResidentDate");
                queryParameters.put("endResidentDate", employeeSearchDTO.getEndResidentDate());
            }
            if (employeeSearchDTO.getMapCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.mapCode= :mapCode");
                queryParameters.put("mapCode", employeeSearchDTO.getMapCode());
            }
            if (employeeSearchDTO.getCapstatusCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.capstatusCode= :capstatusCode");
                queryParameters.put("capstatusCode", employeeSearchDTO.getCapstatusCode());
            }
            if (employeeSearchDTO.getSpecialCaseTypeCode() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.spccsetypeCode= :spccsetypeCode");
                queryParameters.put("spccsetypeCode", employeeSearchDTO.getSpecialCaseTypeCode());
            }

            if (employeeSearchDTO.getPassportNo() != null) {
                ejbql.append(" AND o.citizensResidentsEntity.passportNo LIKE '" + employeeSearchDTO.getPassportNo() +
                             "'");
            }
            if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
                String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.minCode= :minCode");
                queryParameters.put("minCode", Long.parseLong(str[0]));
                ejbql.append(" AND o.wrkCode= :wrkCode");
                queryParameters.put("wrkCode", str[1]);
            }
            if (employeeSearchDTO.getTechJobCode() != null) {
                ejbql.append(" AND o.techJobCode= :techJobCode");
                queryParameters.put("techJobCode", employeeSearchDTO.getTechJobCode());
            }
            if (employeeSearchDTO.getJobCode() != null) {
                ejbql.append(" AND o.jobCode LIKE '" + employeeSearchDTO.getJobCode() + "'");
            }
            if (employeeSearchDTO.getCscFileNo() != null) {
                ejbql.append(" AND o.cscFileNo LIKE '" + employeeSearchDTO.getCscFileNo() + "'");
            }
            if (employeeSearchDTO.getMinistryFileNo() != null) {
                ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
            }
            if (employeeSearchDTO.getStartWorkingDate() != null) {
                ejbql.append(" AND o.startWorkingDate= :startWorkingDate");
                queryParameters.put("startWorkingDate", employeeSearchDTO.getStartWorkingDate());
            }
            if (employeeSearchDTO.getEndWorkingDate() != null) {
                ejbql.append(" AND o.endJobDate= :endJobDate");
                queryParameters.put("endJobDate", employeeSearchDTO.getEndWorkingDate());
            }
            if (employeeSearchDTO.getHireDate() != null) {
                ejbql.append(" AND o.hireDate= :hireDate");
                queryParameters.put("hireDate", employeeSearchDTO.getHireDate());
            }
            if (employeeSearchDTO.getEmpHireStages() != null) {
                ejbql.append(" AND o.hireStagesEntity.hirstageCode= :hirstageCode");
                queryParameters.put("hirstageCode", employeeSearchDTO.getEmpHireStages());
            }
            if (employeeSearchDTO.getEmpHireStatus() != null) {
                ejbql.append(" AND o.hireStatusEntity.hirstatusCode= :hirstatusCode");
                queryParameters.put("hirstatusCode", employeeSearchDTO.getEmpHireStatus());
            }
            if (employeeSearchDTO.getEmpHireTypes() != null) {
                ejbql.append(" AND o.hireTypesEntity.hirtypeCode= :hirtypeCode");
                queryParameters.put("hirtypeCode", employeeSearchDTO.getEmpHireTypes());
            }

            if (employeeSearchDTO.getBgtTypesCode() != null) {
                ejbql.append(" AND o.bgtTypesEntity.typeCode= :typeCode");
                queryParameters.put("typeCode", employeeSearchDTO.getBgtTypesCode());
            }


            if (employeeSearchDTO.getCaderCode() != null) {
                if (employeeSearchDTO.getGroupCode() != null) {
                    if (employeeSearchDTO.getDegreeCode() != null) {
                        ejbql.append(" AND exists(select elm from EmpSalEmpSalaryElementsEntity elm where elm.elmguideCode= :elmguideCode");
                        queryParameters.put("elmguideCode", employeeSearchDTO.getDegreeCode());
                    } else {
                        ejbql.append(" AND exists(select elm from EmpSalEmpSalaryElementsEntity elm where elm.elmguideCode= :elmguideCode");
                        queryParameters.put("elmguideCode", employeeSearchDTO.getGroupCode());
                    }
                } else {
                    ejbql.append(" AND exists(select elm from EmpSalEmpSalaryElementsEntity elm where elm.elmguideCode= :elmguideCode");
                    queryParameters.put("elmguideCode", employeeSearchDTO.getCaderCode());
                }
                ejbql.append(" AND elm.civilId= o.civilId ");
                Date sysDate = new Date(new java.util.Date().getTime());
                ejbql.append(" AND elm.fromDate= :fromDate");
                queryParameters.put("fromDate", sysDate);
                ejbql.append(" AND elm.untilDate IS NULL OR elm.untilDate >= CURRENT_DATE ");
                ejbql.append(") ");
            }

            if (employeeSearchDTO.getBankCode() != null) {
                ejbql.append(" AND exists(select bnk from EmpPersonBankAccountsEntity bnk where bnk.bankCode= :bankCode");
                queryParameters.put("bankCode", employeeSearchDTO.getBankCode());
                if (employeeSearchDTO.getBankbranchCode() != null) {
                    String[] str = employeeSearchDTO.getBankbranchCode().split("\\*");
                    ejbql.append(" AND bnk.bnkbranchCode= :bnkbranchCode");
                    queryParameters.put("bnkbranchCode", Long.parseLong(str[1]));
                }
                ejbql.append(" AND bnk.civilId= o.realCivilId ");
                ejbql.append(" AND bnk.status=1) ");
            } else if (employeeSearchDTO.getAccountNo() != null) {
                ejbql.append(" AND exists(select bnk from EmpPersonBankAccountsEntity bnk where bnk.accountNo= :accountNo ");
                queryParameters.put("accountNo", employeeSearchDTO.getAccountNo());
                ejbql.append(" AND bnk.civilId= o.realCivilId ");
                ejbql.append(" AND bnk.status=1) ");
            }
            Query query = null;

            if (ejbql != null) {
                try {
                    query = EM().createQuery(ejbql.toString());
                    if (queryParameters.size() != 0) {
                        for (Map.Entry<String, Object> param : queryParameters.entrySet()) {
                            query.setParameter(param.getKey(), param.getValue());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Long totalSize = (Long)query.getSingleResult();
            if (totalSize == null)
                throw new ItemNotFoundException();
            return totalSize;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmployeesDTO> CheckIfEmployeeIsExist(Long civilId) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            Long activeFlag = 1L;
            List<IEmployeesDTO> employeesDTOList =
                EM().createNamedQuery("EmployeesEntity.CheckIfEmployeeIsExist").setParameter("civilId",
                                                                                             civilId).setParameter("s1",
                                                                                                                   1L).setParameter("s2",
                                                                                                                                    13L).setParameter("activeFlag",
                                                                                                                                                      activeFlag).setHint("toplink.refresh",
                                                                                                                                                                          "true").getResultList();
            //        if (employeesDTOList == null || employeesDTOList.size() == 0)
            //            throw new FinderException();
            return employeesDTOList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    public Long getMinCodeByCivil(Long civilId) throws DataBaseException, SharedApplicationException {
        try {
            Long activeFlag = 1L;
            Long minCode =
                (Long)EM().createNamedQuery("EmployeesEntity.getMinCodeByCivil").setParameter("civilId", civilId).setParameter("activeFlag",
                                                                                                                               activeFlag).getSingleResult();
            return minCode;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    private ISalStopReasonsDTO getSalaryStopReasonDTO(Long salStopRsnCode) {

        try {
            return (ISalStopReasonsDTO)SalClientFactory.getSalStopReasonsClient().getById(SalEntityKeyFactory.createSalStopReasonsEntityKey(salStopRsnCode));
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    private ISalElementGuidesDTO getgetEqElmGuideDTO(Long eqElmGuideCode) {
        try {
            return (ISalElementGuidesDTO)SalClientFactory.getSalElementGuidesClient().getById(SalEntityKeyFactory.createSalElementGuidesEntityKey(eqElmGuideCode));
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    private ISalDegreeReasonsDTO getDegreasonDTO(Long degreasonCode) {
        try {
            return (ISalDegreeReasonsDTO)SalClientFactory.getSalDegreeReasonsClient().getById(SalEntityKeyFactory.createSalDegreeReasonsEntityKey(degreasonCode));
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get all employees match search criteria and have changes at social raise (childern,wifes.....) used by Mer Module
     * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchMerVariableEmployee(IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        Long virtualValueLong = ISystemConstant.VIRTUAL_VALUE;
        String virtualValueString = String.valueOf(ISystemConstant.VIRTUAL_VALUE);
        int MALE_GENDER_AGE = 24;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currMonth = sdf.format(cal.getTime());

        IEmpEmployeeSearchDTO searchDTO = (IEmpEmployeeSearchDTO)basicDTO;
        StringBuilder query = new StringBuilder(" WITH SubQuery1 AS ( ");

        query.append(" SELECT /*+ MATERIALIZE */ DISTINCT E.CIVIL_ID, E.REAL_CIVIL_ID, GET_NAME (E.REAL_CIVIL_ID) EMP_NAME,F.CADER_CODE , F.CADER_NAME ,F.GROUP_CODE , F.GROUP_NAME , F.DEGREE_CODE , F.DEGREE_NAME ");
        query.append(" , F.FROM_DATE FROM_DATE, F.UNTIL_DATE UNTIL_DATE ");
        query.append(" FROM HR_EMP_EMPLOYEES E, hr_emp_sal_fin_degrees F ,HR_SAL_EMP_CHILDREN child left join HR_EMP_EMPLOYEES empSon on CHILD.CHILD_CIVIL_ID = EMPSON.REAL_CIVIL_ID ");
        query.append(",INF_KWT_CITIZENS_RESIDENTS inf WHERE E.CIVIL_ID = F.CIVIL_ID AND E.MIN_CODE = F.MIN_CODE ");
        query.append(" AND E.CIVIL_ID = child.CIVIL_ID ");

        query.append(" AND E.CIVIL_ID= child.CIVIL_ID ");
        query.append(" AND child.CHILD_CIVIL_ID = inf.CIVIL_ID AND child.STATUS=1");
        query.append(" AND ( (inf.GENTYPE_CODE= " + ISystemConstant.GENDER_MALE); //MALE GENDER TYPE
        query.append(" AND MONTHS_BETWEEN(sysdate,inf.BIRTH_DATE)/12 >= " + MALE_GENDER_AGE +
                     ")"); //Age of gender child greater than or equal 24 year
        query.append(" OR  EMPSON.HIRSTATUS_CODE in (" + EmpUtils.getStatusForHire() + ")");
        query.append(")");

        query.append(" AND E.MIN_CODE =  " + searchDTO.getMinistryCode());
        query.append(" AND E.HIRSTATUS_CODE in (" + EmpUtils.getStatusForHire() + ") ");

        // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            query.append(" and E.wrk_code in (" + wrkcode + ")");
        //        }
        IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
        if (dfDTO != null) {
            String wrkCodeIn = dfDTO.getDfInNative();
            String wrkCodeNotIn = dfDTO.getDfNotInNative();
            if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                query.append(" and E.wrk_code in(");
                query.append(wrkCodeIn);
                query.append(")");
            }
            if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                query.append(" and E.wrk_code Not in(");
                query.append(wrkCodeNotIn);
                query.append(")");
            }
        }

        /*
           query.append("AND E.HIRSTATUS_CODE = ");
           query.append(IEMPConstant.EMP_STATUS_EMPLOYMENT);
           query.append(" AND E.ACTIVE_FLAG = ");
           query.append(ISystemConstant.ACTIVE_FLAG);

           if (searchDTO.getCivilId() != null) {
               query.append(" AND E.CIVIL_ID = " + searchDTO.getCivilId() + " ");
           }
           */


        if (searchDTO.getWorkCenterCode() != null && !searchDTO.getWorkCenterCode().equals(virtualValueString)) {
            query.append(" AND E.WRK_CODE = '" + searchDTO.getWorkCenterCode() + "' ");
        }

        if (searchDTO.getBgtProgramsCode() != null && !searchDTO.getBgtProgramsCode().equals(virtualValueString)) {
            query.append(" AND E.BGTPRG_CODE = '" + searchDTO.getBgtProgramsCode() + "' ");
        }

        if (searchDTO.getBgtTypesCode() != null && !searchDTO.getBgtTypesCode().equals(virtualValueLong)) {
            query.append(" AND E.BGTTYPE_CODE = " + searchDTO.getBgtTypesCode() + " ");
        }

        if (searchDTO.getKaderCode() != null && !searchDTO.getKaderCode().equals(virtualValueLong)) {
            query.append(" AND F.CADER_CODE = " + searchDTO.getKaderCode() + " ");
        }

        if (searchDTO.getGroupCode() != null && !searchDTO.getGroupCode().equals(virtualValueLong)) {
            query.append(" AND F.GROUP_CODE = " + searchDTO.getGroupCode() + " ");
        }

        if (searchDTO.getElmguideCode() != null && !searchDTO.getElmguideCode().equals(virtualValueLong)) {
            query.append(" AND F.DEGREE_CODE = " + searchDTO.getElmguideCode() + " ");
        }
        query.append(" ) SELECT * FROM SubQuery1  WHERE 1=1  ");


        query.append(" AND (TO_DATE ('");
        query.append(currMonth);
        query.append("', 'dd/MM/yyyy') >= FROM_DATE ");
        query.append(" AND (   UNTIL_DATE IS NULL OR TO_DATE ('");
        query.append(currMonth);
        query.append("', 'dd/MM/yyyy') <= UNTIL_DATE)) ");
        System.out.println("searchMerVariableEmployee:" + query.toString());

        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list = q.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        for (Vector row : list) {
            IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
            dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(((BigDecimal)row.get(0)).longValue()));
            dto.setRealCivilId(((BigDecimal)row.get(1)).longValue());
            dto.setName((String)row.get(2));
            dto.setSalEmpSalaryElementsDTO(SalDTOFactory.createSalEmpSalaryElementsDTO());
            ISalElementGuidesDTO salElmGuide = SalDTOFactory.createSalElementGuidesDTO();
            salElmGuide.setCaderCode(row.get(3).toString());
            salElmGuide.setCaderName(row.get(4).toString());
            salElmGuide.setGroupCode(row.get(5).toString());
            salElmGuide.setGroupName(row.get(6).toString());
            salElmGuide.setDegreeCode(row.get(7).toString());
            salElmGuide.setDegreeName(row.get(8).toString());
            dto.getSalEmpSalaryElementsDTO().setSalElementGuidesDTO(salElmGuide);
            arrayList.add(dto);
        }
        return arrayList;
    }

    public Long countFilesWithMinInEmployee(Long minCode, String minFileNo, Long civilId) throws DataBaseException,
                                                                                                 SharedApplicationException {
        String hireStatusStr =
            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstants.EMP_STATUS_FREEZED + "," + IEMPConstants.HR_EMP_REASON_TYPES_TERMINATE_EXT_DLG;
        StringBuilder st = new StringBuilder("SELECT COUNT(CIVIL_ID)");
        st.append(" FROM HR_EMP_EMPLOYEES e");
        st.append(" WHERE MINISTRY_FILE_NO = '");
        st.append(minFileNo);
        st.append("' AND MIN_CODE = ");
        st.append(minCode);
        st.append(" AND e.REAL_CIVIL_ID <> ");
        st.append(civilId);
        st.append(" AND HIRSTATUS_CODE IN (" + hireStatusStr + ")");
        System.out.println("countFilesWithMinInEmployee :: " + st);
        Query q = EM().createNativeQuery(st.toString());

        Vector count = (Vector)q.getSingleResult();
        if (count != null && !count.isEmpty()) {
            return new Long(((BigDecimal)count.get(0)).longValue());
        }
        return null;
    }

    public Long countFilesNoWithMinAndEmployeeForMov(Long minCode, String minFileNo,
                                                     Long civilId) throws DataBaseException,
                                                                          SharedApplicationException {
        String hireStatusStr =
            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstants.EMP_STATUS_FREEZED + "," + IEMPConstants.HR_EMP_REASON_TYPES_TERMINATE_EXT_DLG;
        StringBuilder st = new StringBuilder("SELECT COUNT(CIVIL_ID)");
        st.append(" FROM HR_EMP_EMPLOYEES e");
        st.append(" WHERE MINISTRY_FILE_NO = ?1 ");
        st.append(" AND MIN_CODE = ?2 ");
        st.append(" AND e.REAL_CIVIL_ID<> ?3");

        st.append(" AND HIRSTATUS_CODE IN (" + hireStatusStr + ")");
        System.out.println("countFilesWithMinInEmployee :: " + st);
        Query q = EM().createNativeQuery(st.toString());
        q.setParameter(1, minFileNo);
        q.setParameter(2, minCode);
        q.setParameter(3, civilId);
        Vector count = (Vector)q.getSingleResult();
        if (count != null && !count.isEmpty()) {
            return new Long(((BigDecimal)count.get(0)).longValue());
        }
        return null;
    }


    public Long countEmployeesByJobAndMin(String jobCode, Long minCode) throws DataBaseException,
                                                                               SharedApplicationException {
        StringBuffer ejbql = null;

        ejbql =
                new StringBuffer("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

        ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
        ejbql.append(" AND o.minCode= ");
        ejbql.append(minCode);
        ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                     IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                     IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                     IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                     IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");
        ejbql.append(" AND o.jobCode LIKE '");
        ejbql.append(jobCode);
        ejbql.append("'");

        System.out.println("EmployeesFacadeBean.searchByJobAndMin ::" + ejbql.toString());
        Query query = EM().createQuery(ejbql.toString());
        return (Long)query.getSingleResult();

    }

    public Long countHiredEmployeesByMinWrkCode(Long minCode, String wrkCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        StringBuffer ejbql = null;

        ejbql = new StringBuffer("select count(o.civilId) from EmployeesEntity o WHERE  o.activeFlag = ");

        ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
        ejbql.append(" AND o.minCode= ");
        ejbql.append(minCode);
        ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                     IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_DELEGATION + "," +
                     IEMPConstant.EMP_STATUS_GRANT + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                     IEMPConstant.EMP_STATUS_MISSION + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," +
                     IEMPConstant.EMP_STATUS_PRISONER_LOST + "," + IEMPConstant.EMP_STATUS_VACATION + ") ");
        ejbql.append(" AND o.wrkCode = '");
        ejbql.append(wrkCode);
        ejbql.append("'");

        Query query = EM().createQuery(ejbql.toString());
        return (Long)query.getSingleResult();
    }
    /// added by Mohamed sayed at 4-3-2015 for getting  all status for hire employees

    public String getStatusForHire() {
        StringBuilder strHire = new StringBuilder(IEMPConstant.EMP_STATUS_EMPLOYMENT.toString());
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_DELEGATION);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_GRANT);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_LOANINIG);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_MISSION);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_PRISONER_LOST);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_VACATION);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_MOVING);
        strHire.append(",");
        strHire.append(IEMPConstant.EMP_STATUS_VACATION);
        strHire.append(",");
        strHire.append(IEMPConstants.EMP_STATUS_FREEZED);

        return strHire.toString();

        //        String hireStatusStr =
        //            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," +
        //            IEMPConstant.EMP_STATUS_DELEGATION + "," + IEMPConstant.EMP_STATUS_GRANT + "," +
        //            IEMPConstant.EMP_STATUS_LOANINIG + "," + IEMPConstant.EMP_STATUS_MISSION + "," +
        //            IEMPConstant.EMP_STATUS_DELEGATION_OUT_FROM + "," + IEMPConstant.EMP_STATUS_PRISONER_LOST + "," +
        //            IEMPConstant.EMP_STATUS_VACATION + "," + IEMPConstant.EMP_STATUS_MOVING + "," +
        //            IEMPConstants.EMP_STATUS_FREEZED;
        //return hireStatusStr;
    }

    public IEmployeesDTO addEmployeeForMovOnly(IBasicDTO employeesDTO1) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = new EmployeesEntity();
            IEmployeesDTO employeesDTO = (IEmployeesDTO)employeesDTO1;
            Long civilId = findMaxId();
            IEmployeesEntityKey ek = new EmployeesEntityKey(civilId);
            employeesDTO.setCode(ek);
            employeesEntity.setRealCivilId(employeesDTO.getRealCivilId());
            employeesEntity.setCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
            employeesEntity.setMinistryFileNo(employeesDTO.getMinistryFileNo());
            employeesEntity.setCscFileNo(employeesDTO.getCscFileNo());
            employeesEntity.setHireDate(employeesDTO.getHireDate());
            employeesEntity.setStartWorkingDate(employeesDTO.getStartWorkingDate());
            employeesEntity.setEndJobDate(employeesDTO.getEndJobDate());
            employeesEntity.setDateOfNextRaise(employeesDTO.getDateOfNextRaise());

            employeesEntity.setActiveFlag(employeesDTO.getActiveFlag());
            employeesEntity.setRecordDescCode(employeesDTO.getRecordDescCode());

            employeesEntity.setCreatedBy(employeesDTO.getCreatedBy());
            employeesEntity.setCreatedDate(employeesDTO.getCreatedDate());
            employeesEntity.setAuditStatus(employeesDTO.getAuditStatus());
            employeesEntity.setTabrecSerial(employeesDTO.getTabrecSerial());
            employeesEntity.setSocialInsurNo(employeesDTO.getSocialInsurNo());

            if (employeesDTO.getWorkCenterDTO() != null) {
                IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode();
                if (wEk == null) {
                    System.out.println("WorkCentersEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setWrkCode(wEk.getWrkCode());
                    employeesEntity.setMinCode(wEk.getMinCode());

                }
            } else {
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireStatusDTO() != null) {
                HireStatusEntity hireStatusEntity =
                    EM().find(HireStatusEntity.class, (employeesDTO.getHireStatusDTO().getCode()));
                if (hireStatusEntity == null) {
                    System.out.println("hireStatusEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStatusEntity(hireStatusEntity);
                }
            } else {
                System.out.println("hireStatusEntity is NULL ");
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireTypeDTO() != null) {
                HireTypesEntity hireTypesEntity =
                    EM().find(HireTypesEntity.class, (employeesDTO.getHireTypeDTO().getCode()));
                if (hireTypesEntity == null) {
                    System.out.println("hireTypesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireTypesEntity(hireTypesEntity);
                }
            } else {
                System.out.println("hireTypesEntity is NULL ");
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getHireStageDTO() != null) {
                HireStagesEntity hireStagesEntity =
                    EM().find(HireStagesEntity.class, (employeesDTO.getHireStageDTO().getCode()));
                if (hireStagesEntity == null) {
                    System.out.println("hireStagesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setHireStagesEntity(hireStagesEntity);
                }
            } else {
                System.out.println("hireStagesEntity is NULL ");
                throw new ItemNotFoundException();
            }

            if (employeesDTO.getJobDTO() != null) {
                // JobsEntity jobsEntity = EM().find(JobsEntity.class, (employeesDTO.getJobDTO().getCode()));
                IJobsEntityKey jEk = (IJobsEntityKey)employeesDTO.getJobDTO().getCode();
                //  if (jobsEntity == null) {
                if (jEk.getJobCode() == null) {
                    System.out.println("JobsEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setJobCode(jEk.getJobCode());
                }
            } else {
                System.out.println("JobsEntity is NULL ");
                throw new ItemNotFoundException();
            }
            if (employeesDTO.getTechJobsDTO() != null) {
                IJobsEntityKey teckJobEntityKey = (IJobsEntityKey)employeesDTO.getTechJobsDTO().getCode();
                employeesEntity.setTechJobCode(teckJobEntityKey.getJobCode());
            }

            if (employeesDTO.getBgtProgramsDTO() != null) {
                //BgtProgramsEntity bgtProgramsEntity = EM().find(BgtProgramsEntity.class, (employeesDTO.getBgtProgramsDTO().getCode()));
                IBgtProgramsEntityKey pEk = (IBgtProgramsEntityKey)employeesDTO.getBgtProgramsDTO().getCode();
                //            if (employeesDTO.getBgtProgramsDTO() == null) {
                if (pEk.getPrgCode() == null) {
                    System.out.println("bgtProgramsEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgtprgCode(pEk.getPrgCode());
                }
            } else {
                System.out.println("bgtProgramsEntity is NULL ");
                throw new ItemNotFoundException();
            }
            //////////////////////////////////////
            if (employeesDTO.getBgtTypesDTO() != null) {
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)employeesDTO.getBgtTypesDTO().getCode();
                if (bgtTEk.getTypeCode() == null) {
                    System.out.println("bgtTypesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgttypeCode(bgtTEk.getTypeCode());
                }
            }


            if (employeesDTO.getBgtTypesDTO() != null) {
                IBgtTypesEntityKey bgtTEk = (IBgtTypesEntityKey)employeesDTO.getBgtTypesDTO().getCode();
                if (bgtTEk.getTypeCode() == null) {
                    System.out.println("bgtTypesEntity is NULL ");
                    throw new ItemNotFoundException();
                } else {
                    employeesEntity.setBgttypeCode(bgtTEk.getTypeCode());
                }
            }
            //            EmpDTOFactory.createEmployeesDTO((EmployeesEntity)this.doAdd(employeesEntity));

            doAdd(employeesEntity);
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

    public List<IMinistriesDTO> getMinistriesListByEvalCivilId(Long evalCivilId,
                                                               List<IMinistryInfo> minList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            List<IMinistriesDTO> ministryList = new ArrayList<IMinistriesDTO>();
            MinistriesEntityKey minEK = null;

            StringBuilder query = new StringBuilder("SELECT o.MIN_CODE");
            query.append(" FROM HR_EMP_EMPLOYEES o WHERE");
            query.append(" o.ACTIVE_FLAG = 1 ");
            query.append(" And o.REAL_CIVIL_ID = " + evalCivilId);
            if (minList != null && minList.size() > 0) {
                query.append(" AND o.MIN_CODE IN ( ");
                for (int i = 0; i < minList.size(); i++) {
                    if (i == (minList.size() - 1)) {
                        query.append(minList.get(i).getCode() + " ) ");
                    } else {
                        query.append(minList.get(i).getCode() + " , ");
                    }
                }
            }
            //query.append(" AND o.status =1");
            Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
            Vector vector = (Vector)q.getResultList();

            if (vector != null && !vector.isEmpty()) {
                for (int i = 0; i < vector.size(); i++) {
                    BigDecimal bg = (BigDecimal)((Vector)vector.get(i)).get(0);
                    minEK = (MinistriesEntityKey)OrgEntityKeyFactory.createMinistriesEntityKey(bg.longValue());
                    ministryList.add((IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(minEK));
                }

            }
            return ministryList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    public List<IMinistriesDTO> getMinistriesListByEvalCivilIdNew(Long evalCivilId,
                                                               List<IMinistriesDTO> minList) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            List<IMinistriesDTO> ministryList = new ArrayList<IMinistriesDTO>();
            MinistriesEntityKey minEK = null;

            StringBuilder query = new StringBuilder("SELECT o.MIN_CODE");
            query.append(" FROM HR_EMP_EMPLOYEES o WHERE");
            query.append(" o.ACTIVE_FLAG = 1 ");
            query.append(" And o.REAL_CIVIL_ID = " + evalCivilId);
            if (minList != null && minList.size() > 0) {
                query.append(" AND o.MIN_CODE IN ( ");
                for (int i = 0; i < minList.size(); i++) {
                    if (i == (minList.size() - 1)) {
                        query.append(minList.get(i).getCode().getKey() + " ) ");
                    } else {
                        query.append(minList.get(i).getCode().getKey() + " , ");
                    }
                }
            }
            //query.append(" AND o.status =1");
            Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
            Vector vector = (Vector)q.getResultList();

            if (vector != null && !vector.isEmpty()) {
                for (int i = 0; i < vector.size(); i++) {
                    BigDecimal bg = (BigDecimal)((Vector)vector.get(i)).get(0);
                    minEK = (MinistriesEntityKey)OrgEntityKeyFactory.createMinistriesEntityKey(bg.longValue());
                    ministryList.add((IMinistriesDTO)OrgClientFactory.getMinistriesClient().getById(minEK));
                }

            }
            return ministryList;
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
     * method to get employee by real civil id , logged in ministry code and activeFlag and recordDescCode
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Aly Nour
     * @since 19/08/2015
     */

    public IEmployeesDTO getCurrActiveEmpByRealCivilId(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                Query query = EM().createNamedQuery("EmployeesEntity.getCurrActiveEmpByRealCivilId");
                query.setParameter("realCivilId", realCivilId);
                query.setParameter("minCode", minCode);
                query.setParameter("activeFlag", IEMPConstants._EMP_ACTIVE_STATUS);
                /** commented by A.Nour as agreed with Mosher we will ignore recordDescCode */
                //query.setParameter("recordDescCode", IEMPConstants._EMP_ACTIVE_STATUS);
                query.setHint("toplink.refresh", "true");
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }

            //            if (!EmpUtils.checkWrkCenterFound(CSCSecurityInfoHelper.getGroupCodeFromRequest(), employeesEntity.getWrkCode())){
            //                throw new ItemNotFoundException();
            //            }

            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
            // Add Employee Salary
            //            if (employeesEntity.getSalEmpSalaryElementsEntityList() != null) {
            //                List<ISalEmpSalaryElementsDTO> empSalaryElementsDTOList = new ArrayList<ISalEmpSalaryElementsDTO>();
            //                for (EmpSalEmpSalaryElementsEntity entity : employeesEntity.getSalEmpSalaryElementsEntityList()) {
            //                    if (entity.getUntilDate() == null) {
            //                        //empSalaryElementsDTOList.add(new SalEmpSalaryElementsDTO(entity));
            //                        empSalaryElementsDTOList.add(getSalEmpSalaryElementsDTOFromEntity(entity));
            //                    }
            //                }
            //                employeesDTO.setSalEmpSalaryElementsDTOList(empSalaryElementsDTOList);
            //            }
            //End of add list of document and procedure

            // Add EmployeeExtraData
            //            if (employeesEntity.getEmployeeExtraDataEntityList() != null) {
            //                employeesDTO.setEmpExtraDataValueDTO(EmpDTOFactory.createEmpExtraDataValueDTO());
            //                List<IBasicDTO> employeeExtraDataEntityList = new ArrayList<IBasicDTO>();
            //                for (EmployeeExtraDataEntity entity : employeesEntity.getEmployeeExtraDataEntityList()) {
            //                    employeeExtraDataEntityList.add(new EmployeeExtraDataDTO(entity));
            //                    if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_CANDIDATE_JOB_BY_MIN)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setSuggestedJobCode(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_MIN)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setMinistryNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_SELECTION_DEPT)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setSelectionDeptNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_ARRANGMENT_DEPT)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setArrangmentDeptNotes(entity.getValue());
            //                    } else if (entity.getEmpExtraDataTypesEntity().getExtdattypeCode().equals(IEMPConstant.EXT_DATA_TYPE_NOTES_BY_FATWA_DEPT)) {
            //                        employeesDTO.getEmpExtraDataValueDTO().setFatwaDeptNotes(entity.getValue());
            //
            //                    }
            //                }
            //                employeesDTO.setEmployeeExtraDataDTOList(employeeExtraDataEntityList);
            //            }

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

    /**
     * @author Black Horse [E.Saber]
     * @since 15/09/2015
     * @param realCivilId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public int updateActiveFlagByRealCivilId(Long realCivilId, Long activeFlag) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            StringBuilder st = new StringBuilder("UPDATE HR_EMP_EMPLOYEES");
            st.append(" SET ACTIVE_FLAG = ");
            st.append(activeFlag);
            st.append(" WHERE REAL_CIVIL_ID = ");
            st.append(realCivilId);
            System.out.println("updateActiveFlagByRealCivilId :: " + st);
            // updated by A.AGAMY for data audit
            //Query query = EM().createNativeQuery(st.toString());
            Connection con = getConnectionForUpdate();
            PreparedStatement ps = con.prepareCall(st.toString());
            return ps.executeUpdate();
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
     * @author Black Horse [E.Saber]
     * @since 15/09/2015
     * @param realCivilId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public int updateWorkCodeByRealCivilId(Long realCivilId, String workCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            StringBuilder st = new StringBuilder("UPDATE HR_EMP_EMPLOYEES");
            st.append(" SET WRK_CODE = '" + workCode + "'");
            st.append(" WHERE REAL_CIVIL_ID = ");
            st.append(realCivilId);
            st.append(" and ACTIVE_FLAG = 1  and RECORDDESC_CODE =1");

            System.out.println("updateWorkCodeByRealCivilId :: " + st);

            Connection con = getConnectionForUpdate();
            PreparedStatement ps = con.prepareCall(st.toString());
            return ps.executeUpdate();
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
     * method to get active employees by real civil id and activeFlag
     * @param realCivilId
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @author Aly Nour
     * @since 17/09/2015
     */

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId ");

                // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
                //added by Technical Team [m.sayed] at 31-3-2016
                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
                //                }
                Query query = EM().createQuery(queryStr.toString());

                query.setParameter("realCivilId", realCivilId);
                //query.setParameter("activeFlag", IEMPConstants._EMP_ACTIVE_STATUS);
                query.setHint("toplink.refresh", "true");
                list = query.getResultList();
                System.out.println("getCurrActiveEmployeesByRealCivilId realCivilId = "+realCivilId+"  , list.size() = "+(list == null ? " NULL " : list.size() ) );
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }
            ArrayList arrayList = new ArrayList();
            for (EmployeesEntity empEntity : list) {
                arrayList.add(EmpEntityConverter.getEmployeesDTOForEmpHelper(empEntity));
            }
            System.out.println("getCurrActiveEmployeesByRealCivilId arrayList.size() = "+(arrayList == null ? " NULL " : arrayList.size() ) );
            if (arrayList.size() == 0)
                throw new NoResultException();

            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmployeesDTO> getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration(Long realCivilId) throws DataBaseException,
                                                                                                                  SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId ");
                //added by Technical Team [m.sayed] at 31-3-2016
                // stroy ID CSC-17343  work Center data filter
                /*String wrkcode = initWrkcenterTree();
                if (wrkcode != null && !wrkcode.isEmpty()) {
                    queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
                }*/
                Query query = EM().createQuery(queryStr.toString());

                query.setParameter("realCivilId", realCivilId);
                //query.setParameter("activeFlag", IEMPConstants._EMP_ACTIVE_STATUS);
                query.setHint("toplink.refresh", "true");
                list = query.getResultList();
                System.out.println("getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration list.size() = "+(list == null ? " NULL " : list.size() ) );
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }
            ArrayList arrayList = new ArrayList();
            for (EmployeesEntity empEntity : list) {
                arrayList.add(EmpEntityConverter.getEmployeesDTOForEmpHelper(empEntity));
            }
            System.out.println("getCurrActiveEmployeesByRealCivilIdWithoutDataFilteration arrayList.size() = "+(arrayList == null ? " NULL " : arrayList.size() ) );
            if (arrayList.size() == 0)
                throw new NoResultException();

            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmployeesDTO> getEmpByRealCivilId(Long realCivilId) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId ");
                Query query = EM().createQuery(queryStr.toString());

                query.setParameter("realCivilId", realCivilId);
                query.setHint("toplink.refresh", "true");
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }
            ArrayList arrayList = new ArrayList();
            for (EmployeesEntity employees : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();

            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long countHireDateContradiction(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        IEmployeesDTO employeesDTO = (IEmployeesDTO)basicDTO;

        String hireStatusStr = EmpUtils.getStatusForHireWithoutDELEGATION();
        StringBuilder st = new StringBuilder("SELECT COUNT(CIVIL_ID)");
        st.append(" FROM HR_EMP_EMPLOYEES e");
        st.append(" WHERE e.REAL_CIVIL_ID = ");
        st.append(employeesDTO.getRealCivilId());
        st.append(" AND e.HIRE_DATE >= TO_DATE('" + employeesDTO.getHireDate() + "','yyyy/MM/dd')");

        System.out.println("countHireDateContradiction :: " + st);
        Query q = EM().createNativeQuery(st.toString());

        Vector count = (Vector)q.getSingleResult();
        if (count != null && !count.isEmpty()) {
            return new Long(((BigDecimal)count.get(0)).longValue());
        }
        return null;
    }


    /////////////////// added By M.abdelsabour //////////////////

    /*
    * new added by M.abelsabour
    * to handle listing for execute group employees
    * */

    public Long simpleSearchCountMovWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return buildSearchCountMovQueryWithPaging(basicDTO);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private Long buildSearchCountMovQueryWithPaging(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {

            IEmployeeSearchDTO movEmpSearchDTO = (IEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT count(o.CIVIL_ID) ");
            sql.append("FROM HR_EMP_EMPLOYEES o ");
            //sql.append("WHERE hr_emp_pac.check_emp_hire(REAL_CIVIL_ID) = 1 ");
            sql.append(" where o.active_flag=" + ISystemConstant.ACTIVE_FLAG + " and o.HIRSTATUS_CODE in ( " +
                    //  getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")"); //added by mohamed sayed at 20-8-2015

            if (movEmpSearchDTO.getWorkCenterCode() != null) {

                sql.append(" AND o.WRK_CODE='" + movEmpSearchDTO.getWorkCenterCode() + "'");

            }

/*
 * dev-954
 * added by ahmed farouk 
 * at 17/09/2020
 * */
            if (movEmpSearchDTO.getWorkCentersList() != null && movEmpSearchDTO.getWorkCentersList().size()> 0 
                && !movEmpSearchDTO.getGetAllByWorkCode()) {
                            sql.append("  AND o.WRK_CODE in (  ");
                            for (IBasicDTO DTO : movEmpSearchDTO.getWorkCentersList()) {
                                IWorkCentersDTO newDto = (IWorkCentersDTO)DTO;
                                IWorkCentersEntityKey keyDto = (IWorkCentersEntityKey)newDto.getCode();

                                sql.append("'" + keyDto.getWrkCode() + "',");
                            }
                            sql.deleteCharAt(sql.length() - 1);
                            sql.append(")");
                } else if (movEmpSearchDTO.getWorkCentersList() != null && movEmpSearchDTO.getWorkCentersList().size()> 0 
                           && movEmpSearchDTO.getGetAllByWorkCode()) {
                                sql.append("  AND o.WRK_CODE in (  ");
                                sql.append(" SELECT wrkparent.wrk_Code  ");
                                sql.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
                                sql.append("            START WITH wrkparent.wrk_Code in ( " );
                                for (IBasicDTO DTO : movEmpSearchDTO.getWorkCentersList()) {
                                    IWorkCentersDTO newDto = (IWorkCentersDTO)DTO;
                                    IWorkCentersEntityKey keyDto = (IWorkCentersEntityKey)newDto.getCode();

                                    sql.append("'" + keyDto.getWrkCode() + "',");
                                }
                                sql.deleteCharAt(sql.length() - 1);
                                sql.append(")");
                                sql.append("  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ) ");
                            }
            ///=============================  end of dev-954   ===========
            if (movEmpSearchDTO.getMinistryCode() != null) {

                sql.append(" AND o.MIN_CODE= " + movEmpSearchDTO.getMinistryCode() + " ");

            }


            System.out.println(sql.toString());
            Long count = null;

            Vector v = (Vector)EM().createNativeQuery(sql.toString()).getSingleResult();
            if (!v.isEmpty()) {
                count = Long.parseLong(v.get(0).toString());
            }
            if (count == 0) {
                throw new ItemNotFoundException();
            }

            return count;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }


    public List<IBasicDTO> simpleSearchMovEmpWithPaging(IBasicDTO basicDTO,
                                                        IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            list = buildSearchQueryEmpMovWithPaging(basicDTO, requestDTO);
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getSimpleEmployeesDTOForMov(entity));

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

    //    public List<IBasicDTO> simpleSearchMovEmpWithPagingAllChecked(IBasicDTO basicDTO, IPagingRequestDTO requestDTO,
    //                                                                  List<String> unCheckedList) throws DataBaseException,
    //                                                                                                     SharedApplicationException {
    //        try {
    //            List<EmployeesEntity> list = null;
    //            list = buildSearchQueryEmpMovWithPaging(basicDTO, requestDTO);
    //            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
    //            for (EmployeesEntity entity : list) {
    //                String resultcode = String.valueOf(entity.getCivilId());
    //                IEmployeesDTO dto = EmpEntityConverter.getSimpleEmployeesDTOForMov(entity);
    //                if (unCheckedList != null && unCheckedList.size() > 0 && isUnChecked(resultcode, unCheckedList)) {
    //                    dto.setChecked(false);
    //                }else{
    //                    dto.setChecked(true);
    //                }
    //                listDTO.add(dto);
    //            }
    //            return listDTO;
    //        } catch (Exception e) {
    //            e = wrapIntoDataBaseException(e);
    //            if (e instanceof DataBaseException) {
    //                throw (DataBaseException)e;
    //            } else {
    //                throw (SharedApplicationException)e;
    //            }
    //        }
    //    }


    public List<IBasicDTO> simpleSearchMovEmpWithPagingAllChecked(IBasicDTO basicDTO, IPagingRequestDTO requestDTO,
                                                                  List<String> unCheckedList) throws DataBaseException,
                                                                                                     SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            list = buildSearchQueryEmpMovWithPaging(basicDTO, requestDTO);
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();

            IEmployeesDTO empDTO;
            IMovMovingRequestsDTO movDTo;
            IMovMovingRequestsEntityKey key;
            for (EmployeesEntity entity : list) {

                empDTO = EmpEntityConverter.getSimpleEmployeesDTOForMovByJob(entity);
                movDTo = MovDTOFactory.createMovMovingRequestsDTO();
                key = new MovMovingRequestsEntityKey(entity.getCivilId());
                movDTo.setCode(key);
                String resultcode = String.valueOf(entity.getCivilId());
                if (unCheckedList != null && unCheckedList.size() > 0 && isUnChecked(resultcode, unCheckedList)) {
                    empDTO.setChecked(false);
                } else {
                    empDTO.setChecked(true);
                }
                movDTo.setEmployeesDTO(empDTO);
                listDTO.add(movDTo);
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

    private boolean isUnChecked(String resultcode, List<String> unCheckedList) {
        boolean unCheck = false;
        for (String code : unCheckedList) {
            if (resultcode.equals(code)) {
                unCheck = true;
                break;
            }
        }
        return unCheck;
    }

    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    private List<EmployeesEntity> buildSearchQueryEmpMovWithPaging(IBasicDTO basicDTO,
                                                                   IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                        SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            Query query = null;
            IEmployeeSearchDTO movEmpSearchDTO = (IEmployeeSearchDTO)basicDTO;

            StringBuilder sql = new StringBuilder("SELECT * ");
            sql.append("FROM HR_EMP_EMPLOYEES o  , INF_KWT_CITIZENS_RESIDENTS  inf ");

            sql.append(" where  inf.CIVIL_ID = o.REAL_CIVIL_ID and  o.active_flag=" + ISystemConstant.ACTIVE_FLAG +
                       " and o.HIRSTATUS_CODE in ( " +
                    //  getStatusForHire() + ")"); //commented by mohamed sayed at 20-8-2015
                    EmpUtils.getStatusForHireWithoutDELEGATION() + ")"); //added by mohamed sayed at 20-8-2015

            if (movEmpSearchDTO.getWorkCenterCode() != null) {

                sql.append(" AND o.WRK_CODE='" + movEmpSearchDTO.getWorkCenterCode() + "'");

            }
            
            /*
             * dev-954
             * by ahmed farouk
             * 17/09/2020
             * */

            if (movEmpSearchDTO.getWorkCentersList() != null && movEmpSearchDTO.getWorkCentersList().size()> 0 
                && !movEmpSearchDTO.getGetAllByWorkCode()) {
                            sql.append("  AND o.WRK_CODE in (  ");
                            for (IBasicDTO DTO : movEmpSearchDTO.getWorkCentersList()) {
                                IWorkCentersDTO newDto = (IWorkCentersDTO)DTO;
                                IWorkCentersEntityKey keyDto = (IWorkCentersEntityKey)newDto.getCode();

                                sql.append("'" + keyDto.getWrkCode() + "',");
                            }
                            sql.deleteCharAt(sql.length() - 1);
                            sql.append(")");
                } else if (movEmpSearchDTO.getWorkCentersList() != null && movEmpSearchDTO.getWorkCentersList().size()> 0 
                           && movEmpSearchDTO.getGetAllByWorkCode()) {
                                sql.append("  AND o.WRK_CODE in (  ");
                                sql.append(" SELECT wrkparent.wrk_Code  ");
                                sql.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
                                sql.append("            START WITH wrkparent.wrk_Code in ( " );
                                for (IBasicDTO DTO : movEmpSearchDTO.getWorkCentersList()) {
                                    IWorkCentersDTO newDto = (IWorkCentersDTO)DTO;
                                    IWorkCentersEntityKey keyDto = (IWorkCentersEntityKey)newDto.getCode();

                                    sql.append("'" + keyDto.getWrkCode() + "',");
                                }
                                sql.deleteCharAt(sql.length() - 1);
                                sql.append(")");
                                sql.append("  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ) ");
                            }
            
        ////----------------------  end of dev-954 ---------------------------------------------------------------------------
            if (movEmpSearchDTO.getMinistryCode() != null) {

                sql.append(" AND o.MIN_CODE= " + movEmpSearchDTO.getMinistryCode() + " ");

            }

            // commented By M.abdelsabour For applying new Data Filteration Sol  CSC-21713
            //added by Technical Team [m.sayed] at 31-3-2016
            // stroy ID CSC-17343  work Center data filter
            //            String wrkcode = initWrkcenterTree();
            //            if (wrkcode != null && !wrkcode.equals("")) {
            //                sql.append(" and o.wrk_Code in (" + wrkcode + ") ");
            //            }

            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    sql.append(" and o.wrk_Code in(");
                    sql.append(wrkCodeIn);
                    sql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    sql.append(" and o.wrk_Code Not in(");
                    sql.append(wrkCodeNotIn);
                    sql.append(")");
                }
            }


            System.out.println(sql.toString());

            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                sql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    sql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        sql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        sql.append(" , ");
                    }
                }
            }
          System.out.println("query is :----------------------------" + sql.toString());
            query = EM().createNativeQuery(sql.toString(), EmployeesEntity.class);
            
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }

            list = query.getResultList();


            if (list == null || list.size() == 0)
                throw new NoResultException();

            return list;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }


    }

    ///////////////// ended By M.abdelsabour /////////////////////


    /**
     * new method added by M.abdelsabour to update
     * active flag for employee while execute employment
     */
    public Boolean updateEmployeeBeforeExecuteEmployment(Long realCivilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        CallableStatement stm = null;
        try {

            StringBuilder queryString = new StringBuilder(" update  HR_EMP_EMPLOYEES ");
            queryString.append(" set ACTIVE_FLAG = " + IEMPConstants._EMP_NOT_ACTIVE_STATUS + " ");
            queryString.append(" where REAL_CIVIL_ID = " + realCivilId + " ");

            System.out.println(queryString.toString());
            // updated by A.AGAMY for data audit
            Connection con = getConnectionForUpdate();
            stm = con.prepareCall(queryString.toString());
            stm.executeUpdate();
            return Boolean.TRUE;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {

                throw (SharedApplicationException)e;

            }
        }

        finally {
            BaseDAO.close(stm);
        }
    }


    /*
     * addded by M.abdelsabour to check there ias an employee has specific hire status with active flag
     * used in employement
     * */

    public Boolean checkExsistWithEndServiceBeforeEmployment(Long activeFlag, Long realCivilId, Long hireStatus,
                                                             Long minCode) throws DataBaseException,
                                                                                  SharedApplicationException {

        try {
            Query query = EM().createNamedQuery("EmployeesEntity.getEmpCountForEndService");

            query.setParameter("activeFlag", activeFlag);
            query.setParameter("realCivilId", realCivilId);
            query.setParameter("hireStatus", hireStatus);
            query.setParameter("minCode", minCode);


            int count = ((Number)query.getSingleResult()).intValue();

            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }


        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

    }

    public int updateActiveFlagForDelegation(Long lastActiveFlag, Long newActiveFlag, Long hireStatusCode,
                                             Date hireDate, Long minCode) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            StringBuilder st = new StringBuilder("UPDATE HR_EMP_EMPLOYEES");
            st.append(" SET ACTIVE_FLAG = ");
            st.append(newActiveFlag);
            st.append(" WHERE ACTIVE_FLAG = ");
            st.append(lastActiveFlag);
            st.append(" AND HIRSTATUS_CODE = ");
            st.append(hireStatusCode);
            st.append(" AND HIRE_DATE = to_date('");
            st.append(hireDate);
            st.append(" ','yyyy/MM/dd') AND MIN_CODE = ");
            st.append(minCode);
            System.out.println("updateActiveFlagForDelegation :: " + st);
            // updated by A.AGAMY for data audit
            //Query query = EM().createNativeQuery(st.toString());
            Connection con = getConnectionForUpdate();
            PreparedStatement stm = con.prepareCall(st.toString());
            return stm.executeUpdate();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public int updateActiveFlagForDelegationWithRCivil(Long lastActiveFlag, Long newActiveFlag, Long hireStatusCode,
                                             Date hireDate, Long minCode, Long realCivilId) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            StringBuilder st = new StringBuilder("UPDATE HR_EMP_EMPLOYEES");
            st.append(" SET ACTIVE_FLAG = ");
            st.append(newActiveFlag);
            st.append(" WHERE ACTIVE_FLAG = ");
            st.append(lastActiveFlag);
            st.append(" AND HIRSTATUS_CODE = ");
            st.append(hireStatusCode);
            st.append(" AND HIRE_DATE = to_date('");
            st.append(hireDate);
            st.append(" ','yyyy/MM/dd') AND MIN_CODE = ");
            st.append(minCode);
            st.append(" AND REAL_CIVIL_ID = ");
            st.append(realCivilId);
            System.out.println("updateActiveFlagForDelegationWithRCivil :: " + st);
            // updated by A.AGAMY for data audit
            //Query query = EM().createNativeQuery(st.toString());
            Connection con = getConnectionForUpdate();
            PreparedStatement stm = con.prepareCall(st.toString());
            return stm.executeUpdate();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean isEmpIndebt(Long realCivilId) throws DataBaseException, SharedApplicationException {
        StringBuilder sql = new StringBuilder("SELECT COUNT(1) ");
        sql.append(" FROM HR_EOS_SAVE_FIN_HEADERS F ,HR_EOS_REQUESTS E");
        sql.append(" WHERE F.REQUEST_SERIAL = E.REQUEST_SERIAL");
        sql.append(" AND F.BALANCE_VALUE > 0");
        sql.append(" AND EXISTS ( SELECT 1");
        sql.append(" FROM HR_EMP_EMPLOYEES EMP");
        sql.append(" WHERE EMP.CIVIL_ID = E.CIVIL_ID");
        sql.append(" AND EMP.CIVIL_ID = " + realCivilId + " )");
        System.out.println("isEmpIndebt :: -----> " + sql.toString());
        Vector v = (Vector)EM().createNativeQuery(sql.toString()).getSingleResult();
        Long count = Long.parseLong(v.get(0).toString());
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * return all hired employees which not in specific job level ( used in JOB-MODULE )
     * @param jobSearchCriteriaDTO , conditionMap
     * @return List<IEmployeesDTO>
     * @author I.Omar 15/10/2015
     **/
    public List<IEmployeesDTO> getAllHiredEmployeesByJobLevelsAndType(IJobSearchCriteriaDTO jobSearchCriteriaDTO,
                                                                      Map<String, Long> conditionMap) throws DataBaseException,
                                                                                                             SharedApplicationException {
        try {
            List<IEmployeesDTO> resultList = new ArrayList();

            String minCode = jobSearchCriteriaDTO.getMinCode().toString();
            String typeCode = jobSearchCriteriaDTO.getTypesCode().toString();
            String catCode = jobSearchCriteriaDTO.getCategoryCode().toString();
            String levelCode = jobSearchCriteriaDTO.getLevelsCode().toString();

            String conditionCode = null;
            String tabRecSerial = null;

            if (conditionMap != null && conditionMap.size() != 0) {
                conditionCode = conditionMap.get("conditionCode").toString();
                tabRecSerial = conditionMap.get("tabrecSerial").toString();
            }

            StringBuilder query =
                new StringBuilder("SELECT * FROM HR_EMP_EMPLOYEES E WHERE E.HIRSTATUS_CODE in (1) AND E.ACTIVE_FLAG = 1 ");

            query.append(" AND E.MIN_CODE =  ").append(minCode);
            if (conditionCode != null) {
                query.append(" AND GN_GRS_CHK_PAC.CHK_COND('" + conditionCode + "', E.CIVIL_ID ,1,'','A','" +
                             tabRecSerial + "') = 1");
            }
            query.append(" AND  E.JOB_CODE IN (");
            query.append(" SELECT J.JOB_CODE  FROM NL_JOB_JOBS J  ");
            query.append(" WHERE J.TYPE_CODE = ").append(typeCode);
            query.append(" AND J.CAT_CODE = ").append(catCode);
            query.append(" AND  J.LEVEL_CODE IN (SELECT  DISTINCT CL.LEVEL_CODE FROM NL_JOB_CAT_LEVELS CL ");
            query.append(" WHERE  CL.TYPE_CODE = ").append(typeCode);
            query.append(" AND CL.CAT_CODE = ").append(catCode);
            query.append(" AND LEVEL_CODE <> ").append(levelCode);
            query.append(" AND  LEVEL_CODE IN (SELECT LEVEL_CODE FROM NL_JOB_LEVELS  L ");
            query.append(" START WITH  LEVEL_CODE = ").append(levelCode);
            query.append(" CONNECT BY (PRIOR   LEVEL_CODE = PARENT_LEVEL))))");

            System.out.println(query.toString());

            List list =
                EM().createNativeQuery(query.toString(), EmployeesEntity.class).setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0) {
                return new ArrayList();
            }

            for (Object row : list) {
                IEmployeesDTO empDto = EmpDTOFactory.createEmployeesDTO();

                empDto = EmpDTOFactory.createEmployeesDTO(((EmployeesEntity)row), true);

                if (((EmployeesEntity)row).getWorkCentersEntity() != null) {
                    empDto.setWorkCenterDTO(OrgDTOFactory.createWorkCentersDTO());
                    IWorkCentersEntityKey ek =
                        OrgEntityKeyFactory.createWorkCentersEntityKey(((EmployeesEntity)row).getMinCode(),
                                                                       ((EmployeesEntity)row).getWorkCentersEntity().getWrkCode());
                    empDto.getWorkCenterDTO().setCode(ek);
                    empDto.getWorkCenterDTO().setName(((EmployeesEntity)row).getWorkCentersEntity().getWrkName());
                }
                if (((EmployeesEntity)row).getJobsEntity() != null) {
                    empDto.setJobDTO(JobDTOFactory.createJobsDTO());
                    IJobsEntityKey ek =
                        JobEntityKeyFactory.createJobsEntityKey(((EmployeesEntity)row).getJobsEntity().getJobCode());
                    empDto.getJobDTO().setCode(ek);
                    empDto.getJobDTO().setName(((EmployeesEntity)row).getJobsEntity().getJobName());
                }
                resultList.add(empDto);
            }

            return resultList;
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

    //    public List<IBasicDTO> simpleSearchMovEmpWithPaging(IBasicDTO basicDTO,
    //                                                        IPagingRequestDTO requestDTO) throws DataBaseException,
    //                                                                                             SharedApplicationException {
    //        try {
    //            List<EmployeesEntity> list = null;
    //            list = buildSearchQueryEmpMovWithPaging(basicDTO, requestDTO);
    //            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
    //            IEmployeesDTO empDTO;
    //            IMovMovingRequestsDTO movDTo;
    //
    //
    //            for (EmployeesEntity entity : list) {
    //
    //               empDTO= EmpEntityConverter.getSimpleEmployeesDTOForMov(entity) ;
    //                movDTo=MovDTOFactory.createMovMovingRequestsDTO();
    //                movDTo.setEmployeesDTO(empDTO);
    //                listDTO.add(movDTo);
    //
    //            }
    //            return listDTO;
    //        } catch (Exception e) {
    //            e = wrapIntoDataBaseException(e);
    //            if (e instanceof DataBaseException) {
    //                throw (DataBaseException)e;
    //            } else {
    //                throw (SharedApplicationException)e;
    //            }
    //        }
    //    }

    /**
     * GetAllEmploiess For All Login Minstries From Request
     *
     */
    public List<IBasicDTO> getAllEmployeesByForLoginMinistries(IBasicDTO basicDTO,
                                                               IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

            if (employeeSearchDTO.getActiveFlag() != null) {
                ejbql.append(employeeSearchDTO.getActiveFlag());
            } else {
                ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
            }
            if (employeeSearchDTO.getCivilId() != null) {
                ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
            }
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE ");
                ejbql.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");

            }
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.hirstatusCode not IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");

            } else {
                if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                    ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            }
            List categories = CSCSecurityInfoHelper.getCategoriesFromRequest();
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
            if (!(categories == null || categories.size() == 0)) {
                ejbql.append(" AND o.minCode in (");
                for (Object obj : categories) {
                    ICategoryInfo cat = (ICategoryInfo)obj;
                    for (IMinistryInfo min : cat.getMinistries()) {
                        ejbql.append(min.getCode());
                        ejbql.append(",");
                    }
                    System.out.println("getAllEmployeesByForLoginMinistries cat.getCode() = " + cat.getCode() +
                                       " \n ejbql = " + ejbql);
                }
                ejbql.replace(ejbql.length() - 1, ejbql.length(), "");
                ejbql.append(")");
            }

            List<EmployeesEntity> list = null;
            System.out.println("EmployeesFacadeBean.getAllEmployeesByPremittedMinistries ::hisham::" +
                               ejbql.toString());
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }

            Query query = null;

            if (ejbql != null) {
                query = EM().createQuery(ejbql.toString());
                if (requestDTO != null) {
                    query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                    query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
                }
                list = query.getResultList();

            }
            if (list == null || list.size() == 0)
                throw new NoResultException();

            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
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

    /**
     * Get Count  Emploiess For All Login Minstries From Request
     *
     */
    public Long getCountAllEmployeesByForLoginMinistries(IBasicDTO basicDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            ejbql =
                    new StringBuilder("select  count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId AND o.activeFlag = ");

            if (employeeSearchDTO.getActiveFlag() != null) {
                ejbql.append(employeeSearchDTO.getActiveFlag());
            } else {
                ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
            }
            if (employeeSearchDTO.getCivilId() != null) {
                ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
            }
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE ");
                ejbql.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");

            }
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.hirstatusCode not IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");

            } else {
                if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                    ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            }
            List categories = CSCSecurityInfoHelper.getCategoriesFromRequest();
            if (!(categories == null || categories.size() == 0)) {
                ejbql.append(" AND o.minCode in (");
                for (Object obj : categories) {
                    ICategoryInfo cat = (ICategoryInfo)obj;
                    for (IMinistryInfo min : cat.getMinistries()) {
                        ejbql.append(min.getCode());
                        ejbql.append(",");
                    }
                }
                ejbql.replace(ejbql.length() - 1, ejbql.length(), "");
                ejbql.append(")");
            }

            Query query = EM().createQuery(ejbql.toString());

            return (Long)query.getSingleResult();
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
     * @author GEEKS [a.kamal]
     * @since 27-12-2015
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode and EmpHireStatusList :
     */
    public IEmployeesDTO getEmpCodeNameByRealCivilId(IBasicDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            StringBuilder ejbql = null;
            ejbql = new StringBuilder("select o from EmployeesEntity o WHERE   ");
            ejbql.append("  o.realCivilId = '").append(employeeSearchDTO.getRealCivilId()).append("'");
            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" and  o.minCode = ").append(employeeSearchDTO.getMinistryCode());
            }
            //updated by A.Nour 02-23-2016 because it raise null pointer exception if security down
            else if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != null) {
                ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
            }
            ejbql.append(" and o.activeFlag =  ").append(IEMPConstants._EMP_ACTIVE_STATUS);
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.hirstatusCode  IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");
            } else if(employeeSearchDTO.isEosFlag()){
                ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                            IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                            IEMPConstants.EMP_STATUS_FREEZING + "," + IEMPConstants.EMP_STATUS_END_JOB +")");
            } else {
                 ejbql.append(" AND o.hirstatusCode IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                             IEMPConstants.EMP_STATUS_FREEZING + ")");
            }

            //added by Technical Team [m.sayed] at 31-3-2016
            // stroy ID CSC-17343  work Center data filter
            String wrkcode = "";
            if (employeeSearchDTO.getFilterType() == 1) {
                wrkcode = getWrkcenterDataFilter(false);
            }
            /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
            //            else if (employeeSearchDTO.getFilterType() == 0) {
            //                wrkcode = initWrkcenterTree();
            //            }
            // added by E.Saber[B.H] 25-04-2016 in order to handle more than 1000 record in select
            if (wrkcode != null && !wrkcode.isEmpty()) {
                String[] spltr = wrkcode.split(",");
                StringBuilder temp;
                int indexer = 0, count = (int)Math.ceil((double)(spltr.length) / 1000);
                if (count == 1) {
                    ejbql.append(" and o.wrkCode in( " + wrkcode + ")");
                } else {
                    while (count > 0) {
                        temp = new StringBuilder("");
                        for (int i = 0; i < 1000 && indexer < spltr.length; i++, indexer++) {
                            temp.append(spltr[indexer] + ",");
                        }
                        temp.replace(temp.length() - 1, temp.length(), "");
                        if (count == (int)Math.ceil((double)(spltr.length) / 1000))
                            ejbql.append(" and ( o.wrkCode in( " + temp + ")");
                        else
                            ejbql.append(" or o.wrkCode in( " + temp + ")");
                        count--;
                    }
                    ejbql.append(" ) ");
                }
            }
            /// end ///
            System.out.println("getEmpCodeNameByRealCivilId ejbql.toString() = " + ejbql.toString());
            list = EM().createQuery(ejbql.toString()).getResultList();
            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            } else {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOCodeName(employeesEntity);
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


    public IEmployeesDTO getByRealCivilIdForReg(Long realCivilId) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATIONREG();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId  ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
                //added by B.Horse Team  at 31-3-2016
                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in( " + wrkcode + ")");
                //                }


                queryStr.append(" and o.activeFlag=:activeflag ");
                list =
EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                            IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }


            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTORCivilName(employeesEntity);
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

    /** Search Employees using the MinistryCode EmpName Or realCivilID
     * For CER Module
     * @input ISimpleEmployeesDTO
     * @author Amr Abdel Halim
     * @since 13-MARCH-2016
     * return List
     * */

    public List<IBasicDTO> searchByCivilAndNameUsingMinCode(IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        StringBuffer ejbql = null;
        SimpleEmployeesDTO employeeSearchDTO = (SimpleEmployeesDTO)basicDTO;
        if (employeeSearchDTO.getMinistryCode() == null || employeeSearchDTO.getEmpHireStatus() == null) {
            return null;
        }
        Long minCode = employeeSearchDTO.getMinistryCode();
        ejbql = new StringBuffer("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        ejbql.append(" AND  o.minCode = '" + minCode + "'");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getRealCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getRealCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND Exists( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE kwt.civilId=o.realCivilId AND " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }

        if (employeeSearchDTO.getKuwaiti() != null) {
            if (employeeSearchDTO.getKuwaiti()) {
                ejbql.append(" AND o.citizensResidentsEntity.nationality=" + ISystemConstant.KUWAIT_NATIONALITY + "");
            } else {
                ejbql.append(" AND o.citizensResidentsEntity.nationality<>" + ISystemConstant.KUWAIT_NATIONALITY + "");
            }
        }
        // check the HireStatus
        if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_EMPLOYMENT)) {
            ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
            ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
        } else if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_END_JOB)) {
            ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_END_JOB + "'");
            ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
        } else if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO)) {
            ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
            ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_NOT_ACTIVE_STATUS + "'");
            ejbql.append(" AND  o.realCivilId  IN  ( select e.realCivilId from EmployeesEntity e WHERE e.minCode <> '" +
                         minCode + "'");
            ejbql.append(" AND  e.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
            ejbql.append(" AND  e.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
            ejbql.append(")");
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        ejbql.append(" order by o.civilId desc");
        System.out.println(ejbql);

        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() <= 0) {
            throw new NoResultException();
        }
        List listDTO = new ArrayList();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getSimpleEmployeesDTOForCER(entity));
        }
        return listDTO;
    }

    /** Search Employees using the MinistryCode EmpName Or realCivilID and activeFlag = 0 to get employee finish work in this ministry (montahi khadamato)
     * For CER Module
     * @input ISimpleEmployeesDTO
     * @author Amr Abdel Halim
     * @Edit by A.Elmasry
     * @since 06-JAN-2018
     * return List
     * */

    public List<IBasicDTO> searchByCivilAndNameUsingMinCodeForCER(IBasicDTO basicDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
        StringBuffer ejbql = null;
        SimpleEmployeesDTO employeeSearchDTO = (SimpleEmployeesDTO)basicDTO;
        if (employeeSearchDTO.getMinistryCode() == null || employeeSearchDTO.getEmpHireStatus() == null) {
            return null;
        }
        Long minCode = employeeSearchDTO.getMinistryCode();
        ejbql = new StringBuffer("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        ejbql.append(" AND  o.minCode = '" + minCode + "'");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getRealCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getRealCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND Exists( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE kwt.civilId=o.realCivilId AND " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }

        if (employeeSearchDTO.getKuwaiti() != null) {
            if (employeeSearchDTO.getKuwaiti()) {
                ejbql.append(" AND o.citizensResidentsEntity.nationality=" + ISystemConstant.KUWAIT_NATIONALITY + "");
            } else {
                ejbql.append(" AND o.citizensResidentsEntity.nationality<>" + ISystemConstant.KUWAIT_NATIONALITY + "");
            }
        }
        // check the HireStatus
        if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_EMPLOYMENT)) {
            ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
            ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
        } else if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_END_JOB)) {
            ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_END_JOB + "'");
            //ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
        } else if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO)) {
            ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
            ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_NOT_ACTIVE_STATUS + "'");
            ejbql.append(" AND  o.realCivilId  IN  ( select e.realCivilId from EmployeesEntity e WHERE e.minCode <> '" +
                         minCode + "'");
            ejbql.append(" AND  e.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
            ejbql.append(" AND  e.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
            ejbql.append(")");
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        ejbql.append(" order by o.civilId desc");
        System.out.println("search for emp for Cer Module query >>>>>>>>>"+ejbql);

        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() <= 0) {
            throw new NoResultException();
        }
        List listDTO = new ArrayList();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getSimpleEmployeesDTOForCER(entity));
        }
        return listDTO;
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 30-3-2016
     * @param groupCode
     * @param userCode
     * @param realCivilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if group code or user code granted or revoked for such real civil
     */
    public boolean checkFilterDataForEmp(Long groupCode, Long userCode, Long realCivilID) throws DataBaseException,
                                                                                                 SharedApplicationException {


        StringBuilder query =
            new StringBuilder("select  nvl(FILTER_GROUP_CODE,0) as group_Code,nvl(FILTER_USER_CODE,0) as user_code,nvl(MASTER_GRANT_REVOKE,0) as Granted from ");
        query.append("(");
        query.append(" SELECT real_civil_id,1 as id  ");
        query.append(" FROM  SEC_FILTERED_EMPLOYEE_DET ");
        query.append(" WHERE     real_civil_ID =" + realCivilID.toString());
        query.append(" )q1");
        query.append(" left join ");
        query.append("  ( select FILTER_GROUP_CODE,FILTER_USER_CODE,MASTER_GRANT_REVOKE,1 as id  ");
        query.append(" from SEC_FILTERED_EMP_MASTER ");
        query.append(" where (FILTER_GROUP_CODE = " + groupCode.toString() + " OR FILTER_USER_CODE = " +
                     userCode.toString() + ") ");
        query.append(")");
        query.append(" q2 ");
        query.append(" on q1.id=q2.id ");
        query.append(" order by user_code desc ");
        System.out.println(query.toString());

        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        //        q.setParameter("realCivilID", realCivilID);
        //        q.setParameter("groupCode", groupCode);
        //        q.setParameter("userCode", userCode);


        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() == 0) {
            return true;
        }
        Long userret = 0L;
        Long grpret = 0L;
        boolean isGranted = true;
        for (Vector row : list) {
            grpret = ((BigDecimal)row.get(0)).longValue();
            userret = ((BigDecimal)row.get(1)).longValue();
            //both of them not granted
            if (grpret == 0L && userret == 0L) {
                return false;
            }
            //if user found
            if (userret > 0L) {
                if (((BigDecimal)row.get(2)).longValue() == 2L)
                    return false;
            }
            //if group found
            if (((BigDecimal)row.get(2)).longValue() == 2L) {
                isGranted = false;
            } else {
                isGranted = true;
            }
        }

        return isGranted;

    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 31-3-2016
     * @return String
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if all workcenters tree for 02133
     */

    public String FilterDataByWrkCenter(Long groupCode) throws DataBaseException, SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder(" select wrk_Code from ( ");
        query.append("  SELECT wrkparent.wrk_Code ");
        query.append(" FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append(" START WITH wrkparent.wrk_code ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("' CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ) ");
        query.append(" where exists(   select wrk_code  from GN_SEC_GROUP_WORK_CENTERS ");
        query.append(" where   group_code=" + groupCode);
        query.append(" and   wrk_code in( ");
        query.append(" SELECT wrkparent.wrk_Code ");
        query.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append("            START WITH wrkparent.wrk_code ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("'  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ");
        query.append("      ))");

        System.out.println("FilterDataByWrkCenter query = " + query.toString());

        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");

        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder allWrkCodes = new StringBuilder("");
        for (Vector row : list) {
            allWrkCodes.append("'" + row.get(0).toString() + "',");
        }
        allWrkCodes.replace(allWrkCodes.length() - 1, allWrkCodes.length(), "");
        return allWrkCodes.toString();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return String
     * CSC-17343 
     * check if all workcenters tree for 02133
     */


    private String initWrkcenterTree() {
        System.out.println(" start initWrkcenterTree");
        try {
            System.out.println(" 1- " + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest().toString());
            //if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != 13L) {
            if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() == null ||
                !(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest()).equals(13L)) {
                return "";
            }
            System.out.println(" middle initWrkcenterTree");

            return FilterDataByWrkCenter(CSCSecurityInfoHelper.getGroupCodeFromRequest());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            ;
        }
        return "";
    }


    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return String
     * CSC-17343 
     * check if all workcenters tree for 02133
     */
    public String applyWrkcenterTreeFilter() throws DataBaseException, SharedApplicationException {
        System.out.println(" start applyWrkcenterTreeFilter");
        try {
            //            if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() == null) {
            if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() == null ||
                !(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest()).equals(13L)) {
                return "";
            }
            System.out.println(" 1- " + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest().toString());

            if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != 13L) {
                return "";
            }

            return FilterDataByWrkCenter(CSCSecurityInfoHelper.getGroupCodeFromRequest());
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all workcenters tree for 02133
     */

    public HashMap<String, String> getworkCenterTree() throws DataBaseException, SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder(" SELECT wrkparent.wrk_Code  ");
        query.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append("            START WITH wrkparent.PARENT_WRK ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("'  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ");

        System.out.println(query.toString());
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        HashMap<String, String> wrkCenters = new HashMap<String, String>();
        wrkCenters.put(IEMPConstants.WRK_CENTER_ISOLATED, IEMPConstants.WRK_CENTER_ISOLATED);
        for (Vector row : list) {
            wrkCenters.put(row.get(0).toString(), row.get(0).toString());
        }
        return wrkCenters;

    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all Employees in workcenters tree for 02133
     */

    public HashMap<Long, String> getEmployeesworkCenterTree() throws DataBaseException, SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder(" select real_Civil_id,Civil_id from hr_emp_employees where  active_flag=1 ");
        query.append(" and   wrk_code in( select '" + IEMPConstants.WRK_CENTER_ISOLATED +
                     "' from dual union   SELECT wrkparent.wrk_Code  ");
        query.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append("            START WITH wrkparent.PARENT_WRK = '" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("'  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK )");

        System.out.println(query.toString());
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        HashMap<Long, String> empMap = new HashMap<Long, String>();

        for (Vector row : list) {
            empMap.put(((BigDecimal)row.get(0)).longValue(), row.get(1).toString());
        }
        return empMap;

    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 1-4-2016
     * @return  HashMap<String,String>
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get all groups in workcenters tree for 02133
     */

    public HashMap<Long, String> getGroupsworkCenterTree() throws DataBaseException, SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder(" select group_code,wrk_code  from GN_SEC_GROUP_WORK_CENTERS where   ");
        query.append("    wrk_code in( select '" + IEMPConstants.WRK_CENTER_ISOLATED +
                     "' from dual union  SELECT wrkparent.wrk_Code  ");
        query.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append("            START WITH wrkparent.PARENT_WRK = '" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("'  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK )");

        System.out.println(query.toString());
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        HashMap<Long, String> groupMap = new HashMap<Long, String>();

        for (Vector row : list) {
            groupMap.put(((BigDecimal)row.get(0)).longValue(), row.get(1).toString());
        }
        return groupMap;

    }


    /**
     * @author TechnicalTeam[M.sayed]
     * @since 31-3-2016
     * @return String
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if all workcenters tree for 02133
     */

    public List<String> filterDataByWrkCenterList() throws DataBaseException, SharedApplicationException {

        Long groupCode = CSCSecurityInfoHelper.getGroupCodeFromRequest();
        StringBuilder query;
        query = new StringBuilder(" select wrk_Code from ( ");
        query.append("  SELECT wrkparent.wrk_Code ");
        query.append(" FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append(" START WITH wrkparent.PARENT_WRK ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("' CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ) ");
        query.append(" where exists(   select wrk_code  from GN_SEC_GROUP_WORK_CENTERS ");
        query.append(" where group_code=" + groupCode);
        query.append(" and   wrk_code in( ");
        query.append(" SELECT wrkparent.wrk_Code ");
        query.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append("            START WITH wrkparent.wrk_code = '" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("'  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ");
        query.append("      ))");
        System.out.println(query.toString());
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");


        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return null;
        }
        List<String> retlist = new ArrayList<String>();
        retlist.add(IEMPConstants.WRK_CENTER_ISOLATED);
        for (Vector row : list) {
            retlist.add(row.get(0).toString());
        }
        return retlist;

    }

    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 12-4-2016
     * @return String
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if all workcenters tree for any passed secGroup
     */

    private String getDataByWrkCenterDF(Long groupCode, boolean isNative) throws DataBaseException,
                                                                                 SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder("");
        query.append("select wrk_code from NL_ORG_WORK_CENTERS  ");
        query.append(" start with PARENT_WRK in( SELECT wrk_code ");
        query.append(" FROM GN_SEC_GROUP_WORK_CENTERS ");
        query.append("  WHERE     group_code = " + groupCode);
        query.append("  ) ");
        query.append("   connect by prior wrk_code =PARENT_WRK ");
        query.append(" union ");
        query.append("  SELECT wrk_code ");
        query.append("    FROM GN_SEC_GROUP_WORK_CENTERS ");
        query.append("   WHERE     group_code = " + groupCode);
        // query.append(")  ");

        System.out.println(query.toString());

        if (isNative) {
            return "(" + query.toString() + ")";
        }
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }

        StringBuilder allWrkCodes = new StringBuilder("");
        for (Vector row : list) {
            allWrkCodes.append("'" + row.get(0).toString() + "',");
        }
        allWrkCodes.replace(allWrkCodes.length() - 1, allWrkCodes.length(), "");
        return allWrkCodes.toString();
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 12-4-2016
     * @param isNative
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * get  all workcenters tree for any group
     * return string of comma separated values of wrk codes  in case u pass native return qry for inner
     */

    public String getWrkcenterDataFilter(boolean isNative) throws DataBaseException, SharedApplicationException {
        try {
            Long groupCode = CSCSecurityInfoHelper.getGroupCodeFromRequest();
            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
            System.out.println("getWrkcenterDataFilter " + groupCode.toString());
            System.out.println("getWrkcenterDataFilter " + minCode.toString());
            if (minCode == null) {
                return "";
            }
            if (groupCode == 0L) {
                return "";
            }
            return getDataByWrkCenterDFAtt(minCode, groupCode, isNative);
        } catch (DataBaseException e) {
            e.printStackTrace();
        } catch (SharedApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return "";
        }
        return "";
    }


    /**
     * @author TechnicalTeam[M.sayed]
     * @since 17-4-2016
     * @return String
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if all workcenters tree for any passed secGroup
     */

    private String getDataByWrkCenterDFAtt(Long minCode, Long groupCode, boolean isNative) throws DataBaseException,
                                                                                                  SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder("");
        query.append("select wrk_code from nl_org_work_centers ");
        query.append(" start with wrk_code in ( ");
        query.append("select distinct case when firstID=1 then wrkcode1 else wrkcode2 end as wrkCode from ");
        query.append("   ( ");
        query.append(" SELECT WC.WRK_CODE as wrkcode1,1 as firstID ");
        query.append(" FROM NL_ORG_WORK_CENTERS WC ");
        query.append(" WHERE WC.MIN_CODE = " + minCode + "   AND WC.WRKLEVEL_CODE = 5 ");
        query.append(" START WITH (WC.WRK_CODE IN (SELECT wrk_code ");
        query.append(" FROM GN_SEC_GROUP_WORK_CENTERS ");
        query.append(" WHERE group_code =" + groupCode);
        query.append("  )) CONNECT BY PRIOR WC.PARENT_WRK =WC.WRK_CODE ");
        query.append(" )query1 ");
        query.append(" full outer join ");
        query.append(" ( ");
        query.append(" SELECT wrk_code as wrkcode2,1 as secondID  ");
        query.append(" FROM NL_ORG_WORK_CENTERS ");
        query.append(" START WITH wrk_code IN (SELECT wrk_code ");
        query.append(" FROM GN_SEC_GROUP_WORK_CENTERS ");
        query.append(" WHERE group_code = " + groupCode + ") ");
        query.append(" CONNECT BY PRIOR wrk_code = PARENT_WRK ");
        query.append(" )query2 ");
        query.append(" on query1.firstID=query2.secondID ");
        query.append(" ) ");
        query.append(" CONNECT BY PRIOR wrk_code = PARENT_WRK ");

        System.out.println(query.toString());

        if (isNative) {
            return "(" + query.toString() + ")";
        }
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }

        StringBuilder allWrkCodes = new StringBuilder("");
        for (Vector row : list) {
            allWrkCodes.append("'" + row.get(0).toString() + "',");
        }
        allWrkCodes.replace(allWrkCodes.length() - 1, allWrkCodes.length(), "");
        return allWrkCodes.toString();
    }


    /**
     * @param realCivilId
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * Hirestatus in (1,9,13)
     * activeflag=1
     * @createdBy Kareem.Sayed
     */
    public List<IEmployeesDTO> getByRealCivilForFTQ(Long realCivilId, String name) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            String hireStatus = "1,9,13";
            StringBuilder queryStr = new StringBuilder("select o from EmployeesEntity o ");
            if (realCivilId != null) {
                queryStr.append(" WHERE  o.realCivilId= '" + realCivilId + "'");
            } else if (name != null && !name.equals("")) {
                queryStr.append(" WHERE o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                                QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                    name) + " ) ");

            }
            queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ") ");
            queryStr.append(" and o.activeFlag=1 ");
            System.out.println("getByRealCivilForFTQ :: " + queryStr.toString());
            list = EM().createQuery(queryStr.toString()).getResultList();
            List<IEmployeesDTO> listDTO = new ArrayList<IEmployeesDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getEmployeesDTOForFTQ(entity));
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
    /**
     * @author m.sayed
     * @since   20-6-2018
     * @param realCivilId
     * @param name
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IEmployeesDTO> getByRealCivilForFTQWithDF(Long realCivilId, String name) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            String hireStatus = "1,9,13";
            StringBuilder queryStr = new StringBuilder("select * from hr_emp_employees emp ");
            if (realCivilId != null) {
                queryStr.append(" WHERE  emp.real_civil_id= '" + realCivilId + "'");
            } else if (name != null && !name.equals("")) {
                queryStr.append(" WHERE emp.real_civil_id IN ( Select kwt.civil_Id From INF_KWT_CITIZENS_RESIDENTS kwt WHERE " +
                                QueryConditionBuilder.getNativeSqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_Name , ' ' ) , CONCAT ( kwt.second_Name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_Name , ' ' ) , CONCAT ( kwt.last_Name , ' ' ) ) ) , kwt.family_Name )",
                                                                                    name) + " ) ");

            }
            queryStr.append(" AND emp.HIRSTATUS_CODE IN (" + hireStatus + ") ");
            queryStr.append(" and emp.active_Flag=1 ");
            queryStr.append(applyDfString());
            System.out.println("getByRealCivilForFTQ :: " + queryStr.toString());
            list = EM().createNativeQuery(queryStr.toString(),EmployeesEntity.class).getResultList();
            List<IEmployeesDTO> listDTO = new ArrayList<IEmployeesDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getEmployeesDTOForFTQ(entity));
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
    private String applyDfString(){
        StringBuilder queryStr = new StringBuilder(" and (min_Code in (");
        queryStr.append(" select min_Code from GN_SEC_USER_MIN_FILTERS ");
        queryStr.append(" where user_Code = ");
        queryStr.append(CSCSecurityInfoHelper.getUserCodeFromRequest());
        queryStr.append(" and group_Code= ");
        queryStr.append(CSCSecurityInfoHelper.getGroupCodeFromRequest());
        queryStr.append(" and nvl(valid_from_date,sysdate) <=sysdate ");
        queryStr.append(" and nvl(valid_to_date,sysdate)>=sysdate ");
        queryStr.append(" and wrk_Code is null ) ");
        queryStr.append(" or wrk_code in ( ");
        queryStr.append(" select distinct wrk_code from nl_org_work_centers ");
        queryStr.append(" start with wrk_Code in ( ");
        queryStr.append(" select wrk_Code from GN_SEC_USER_MIN_FILTERS ");
        queryStr.append(" where user_Code = ");
        queryStr.append(CSCSecurityInfoHelper.getUserCodeFromRequest());
        queryStr.append(" and group_Code= ");
        queryStr.append(CSCSecurityInfoHelper.getGroupCodeFromRequest());
        queryStr.append(" and nvl(valid_from_date,sysdate) <=sysdate ");
        queryStr.append(" and nvl(valid_to_date,sysdate)>=sysdate ");
        queryStr.append(" and wrk_Code is not null ) ");
        queryStr.append(" connect by prior wrk_Code=parent_wrk ))");
        queryStr.append(" and wrk_code not in (");
        queryStr.append(" select distinct wrk_code from nl_org_work_centers ");
        queryStr.append(" start with wrk_Code in (");
        queryStr.append(" select except_wrk_Code from GN_SEC_USER_MIN_FILTERS ");
        queryStr.append(" where user_Code = ");
        queryStr.append(CSCSecurityInfoHelper.getUserCodeFromRequest());
        queryStr.append(" and group_Code= ");
        queryStr.append(CSCSecurityInfoHelper.getGroupCodeFromRequest());
        queryStr.append(" and nvl(valid_from_date,sysdate) <=sysdate ");
        queryStr.append(" and nvl(valid_to_date,sysdate)>=sysdate ");
        queryStr.append(" and except_wrk_Code is not null) ");
        queryStr.append(" connect by prior wrk_Code=parent_wrk) ");
        return queryStr.toString();

    }
    public List<IBasicDTO> simpleSearchForAOE(IBasicDTO basicDTO) throws DataBaseException,
                                                                         SharedApplicationException {
        StringBuffer ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
        ejbql =
                new StringBuffer("select o.* from HR_EMP_EMPLOYEES o WHERE o.civil_Id=o.civil_Id AND o.active_Flag = ");

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(employeeSearchDTO.getActiveFlag());
        } else {
            ejbql.append(IEMPConstants._EMP_ACTIVE_STATUS);
        }

        ejbql.append(" AND o.real_Civil_Id IN ( Select req.civil_Id From HR_AOE_AWARD_REQUESTS req ) ");

        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.real_Civil_Id = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {

            ejbql.append(" AND o.real_Civil_Id IN ( Select kwt.civil_Id From INF_KWT_CITIZENS_RESIDENTS kwt WHERE  " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_Name , ' ' ) , CONCAT ( kwt.second_Name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_Name , ' ' ) , CONCAT ( kwt.last_Name , ' ' ) ) ) , kwt.family_Name )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");


        }

        if (employeeSearchDTO.getMinistryCode() != null && !employeeSearchDTO.getMinistryCode().equals(-100L)) {
            ejbql.append(" AND o.min_Code=" + employeeSearchDTO.getMinistryCode() + "");
        }
        //updated by A.Nour 02-23-2016 because it raise null pointer exception if security down
        else if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != null) {
            ejbql.append(" AND o.min_Code=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        String wrkcode = "";

        if (employeeSearchDTO.getFilterType() == 1) {
            //apply data filtration for work center related to logged in group
            wrkcode = getWrkcenterDataFilter(false);
            if (wrkcode != null && !wrkcode.isEmpty()) {
                ejbql.append(" and o.wrk_Code in( " + wrkcode + ")");
            }
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //        else if (employeeSearchDTO.getFilterType() == 0) {
        //            //apply data filtration for un structured work center if logged in group one of un structured work centers
        //            wrkcode = initWrkcenterTree();
        //        }
        else if (employeeSearchDTO.getFilterType() == 0) {
            IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
            if (dfDTO != null) {
                String wrkCodeIn = dfDTO.getDfInNative();
                String wrkCodeNotIn = dfDTO.getDfNotInNative();
                if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                    ejbql.append(" and o.wrk_code in(");
                    ejbql.append(wrkCodeIn);
                    ejbql.append(")");
                }
                if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                    ejbql.append(" and o.wrk_code Not in(");
                    ejbql.append(wrkCodeNotIn);
                    ejbql.append(")");
                }
            }
        }


        List<EmployeesEntity> list = null;
        System.out.println("EmployeesDAO.simpleSearch ejbql.toString() = " + ejbql.toString());
        if (ejbql != null) {
            Query q = EM().createNativeQuery(ejbql.toString(), EmployeesEntity.class);
            list = q.getResultList();
        }
        if (list == null || list.size() == 0)
            throw new NoResultException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        Long startTime = System.currentTimeMillis();
        for (EmployeesEntity entity : list) {
            //            EmployeesEntity entity = (EmployeesEntity)row;
            listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("Test.........time spent .................................... >> " +
                           (endTime - startTime) / 1000 + "sec");
        return listDTO;
    }

    ///////////////////////////////////////////////Data Filtration for vac //////////////////////////////////////

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 18-5-2016
     * @return String
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if all workcenters tree for 02133
     */

    public String getDataByWrkCenter(String colName) throws DataBaseException, SharedApplicationException {
        Long groupCode = CSCSecurityInfoHelper.getGroupCodeFromRequest();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
        System.out.println("getWrkcenterDataFilter " + groupCode.toString());
        System.out.println("getWrkcenterDataFilter " + minCode.toString());
        if (minCode == null) {
            return "";
        }
        if (groupCode == 0L) {
            return "";
        }
        StringBuilder query;
        query = new StringBuilder(" select wrk_Code from ( ");
        query.append("  SELECT wrkparent.wrk_Code ");
        query.append(" FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append(" START WITH wrkparent.wrk_code ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("' CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ) ");
        query.append(" where exists(   select wrk_code  from GN_SEC_GROUP_WORK_CENTERS ");
        query.append(" where   group_code=" + groupCode);
        query.append(" and   wrk_code in( ");
        query.append(" SELECT wrkparent.wrk_Code ");
        query.append("  FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append("            START WITH wrkparent.wrk_code ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("'  CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ");
        query.append("      ))");

        System.out.println(query.toString());


        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");


        List<Vector> list;
        StringBuilder allWrkCodes = new StringBuilder("");
        String strCriteria = "";
        list = q.getResultList();
        String wrkCodes = "";
        if (list == null || list.size() == 0) {
            wrkCodes = getHierDataByWrkCenter();
            strCriteria = colName + " Not in (" + wrkCodes + ")";
        } else {
            for (Vector row : list) {
                allWrkCodes.append("'" + row.get(0).toString() + "',");
            }
            allWrkCodes.replace(allWrkCodes.length() - 1, allWrkCodes.length(), "");
            strCriteria = colName + "  in (" + allWrkCodes.toString() + ")";
        }
        return strCriteria;
    }

    /**
     * @author TechnicalTeam[M.sayed]
     * @since 18-5-2016
     * @return String
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CSC-17343 
     * check if all workcenters tree for 02133
     */
    private String getHierDataByWrkCenter() throws DataBaseException, SharedApplicationException {

        StringBuilder query;
        query = new StringBuilder(" select wrk_Code from ( ");
        query.append("  SELECT wrkparent.wrk_Code ");
        query.append(" FROM NL_ORG_WORK_CENTERS wrkparent ");
        query.append(" START WITH wrkparent.wrk_code ='" + IEMPConstants.WRK_CENTER_ISOLATED);
        query.append("' CONNECT BY PRIOR wrkparent.wrk_code = wrkparent.PARENT_WRK ) ");

        System.out.println(query.toString());

        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder allWrkCodes = new StringBuilder("");
        for (Vector row : list) {
            allWrkCodes.append("'" + row.get(0).toString() + "',");
        }
        allWrkCodes.replace(allWrkCodes.length() - 1, allWrkCodes.length(), "");
        return allWrkCodes.toString();
    }

    //////////////////////////////////////////////////////Eservice//////////////////////////////////////////////

    public EmployeesDTO getEmpMainInfoBySecurity(String user_code, String user_name) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            EmployeesDTO employee = new EmployeesDTO();
            if (user_code != null || user_name != null) {
                StringBuilder query =
                    new StringBuilder("select emp.real_civil_id, emp.min_code, inf.E_MAIL , inf.MOBILE_NO from hr_emp_employees emp ,inf_kwt_citizens_residents inf" +
                                      " where emp.real_civil_id = inf.civil_id and  emp.real_civil_id in ( ");
                query.append("select G.CIVIL_ID from gn_sec_users G where 1=1 ");
                if (user_code != null) {
                    query.append(" and lower(G.user_code) = lower('" + user_code + "')");
                }
                if (user_name != null) {
                    query.append(" and lower(G.user_name) = lower('" + user_name + "')");
                }
                query.append(" AND G.LOCKED = 0 AND G.USER_DELETED = 0 )");
                query.append(" and emp.active_Flag = " + IEMPConstants._EMP_ACTIVE_STATUS);
                query.append(" AND EMP.HIRSTATUS_CODE IN (1,13,11) ");
                query.append(" ORDER BY emp.HIRE_DATE DESC");
                System.out.println("getEmpMainInfoBySecurity >>> Query >>> " + query.toString() );
                Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
                List<Vector> list;
                list = q.getResultList();

                for (Vector row : list) {
                    KwtCitizensResidentsDTO kwtCitizensResidentsDTO = new KwtCitizensResidentsDTO();
                    if (row.get(0) != null) {
                        employee.setRealCivilId(Long.valueOf(row.get(0).toString()));
                    }
                    if (row.get(1) != null) {
                        employee.setMinCode(Long.valueOf(row.get(1).toString()));
                    }
                    if (row.get(2) != null) {
                        kwtCitizensResidentsDTO.setEMail(row.get(2).toString());
                    }
                    if (row.get(3) != null) {
                        kwtCitizensResidentsDTO.setMobileNo(row.get(3).toString());
                    }
                    employee.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
                    break;
                }
            }
            return employee;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IEmployeesDTO> getEmployeesByRealCivilId_eos(Long realCivilId) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId order by o.startWorkingDate");

                /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
                //added by Technical Team [m.sayed] at 31-3-2016
                // stroy ID CSC-17343  work Center data filter
                //                String wrkcode = initWrkcenterTree();
                //                if (wrkcode != null && !wrkcode.isEmpty()) {
                //                    queryStr.append(" and o.wrkCode in (" + wrkcode + ")");
                //                }
                Query query = EM().createQuery(queryStr.toString());

                query.setParameter("realCivilId", realCivilId);
                //query.setParameter("activeFlag", IEMPConstants._EMP_ACTIVE_STATUS);
                query.setHint("toplink.refresh", "true");
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }
            ArrayList arrayList = new ArrayList();
            for (EmployeesEntity employees : list) {
                arrayList.add(EmpDTOFactory.createEmployeesDTO(employees));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();

            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /** Search Employees using realCivilID   Or EmpName
     * For RTS Module
     * @input ISimpleEmployeesDTO
     * @author A.Elmasry
     * @since 22-Jun-2016
     * return List
     * */

    public List<IBasicDTO> searchByCivilIdAndName(IBasicDTO basicDTO) throws DataBaseException,
                                                                             SharedApplicationException {
        StringBuilder ejbql = null;
        SimpleEmployeesDTO employeeSearchDTO = (SimpleEmployeesDTO)basicDTO;

        Long minCode = employeeSearchDTO.getMinistryCode();
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (minCode != null && !"".equals(minCode)) {
            ejbql.append(" AND  o.minCode = '" + minCode + "'");
        }
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.civilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getRealCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getRealCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND Exists( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE kwt.civilId=o.realCivilId AND " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }

        if (employeeSearchDTO.getKuwaiti() != null) {
            if (employeeSearchDTO.getKuwaiti()) {
                ejbql.append(" AND o.citizensResidentsEntity.nationality=" + ISystemConstant.KUWAIT_NATIONALITY + "");
            } else {
                ejbql.append(" AND o.citizensResidentsEntity.nationality<>" + ISystemConstant.KUWAIT_NATIONALITY + "");
            }
        }
        // check the HireStatus
        if (employeeSearchDTO.getEmpHireStatus() != null) {
            if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_EMPLOYMENT)) {
                ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
                ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
            } else if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_END_JOB)) {
                ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_END_JOB + "'");
                ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
            } else if (employeeSearchDTO.getEmpHireStatus().equals(IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO)) {
                ejbql.append(" AND  o.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
                ejbql.append(" AND  o.activeFlag = '" + IEMPConstants._EMP_NOT_ACTIVE_STATUS + "'");
                ejbql.append(" AND  o.realCivilId  IN  ( select e.realCivilId from EmployeesEntity e WHERE e.minCode <> '" +
                             minCode + "'");
                ejbql.append(" AND  e.hirstatusCode = '" + IEMPConstants.EMP_STATUS_EMPLOYMENT + "'");
                ejbql.append(" AND  e.activeFlag = '" + IEMPConstants._EMP_ACTIVE_STATUS + "'");
                ejbql.append(")");
            }
        }

        System.out.println(ejbql);

        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() <= 0) {
            throw new NoResultException();
        }
        List listDTO = new ArrayList();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getSimpleEmployeesDTOForCER(entity));
        }
        return listDTO;
    }


    public String getUserName(Long userCode) throws DataBaseException, SharedApplicationException {
        try {
            String userName = "";
            if (userCode != null) {
                StringBuilder queryStr =
                    new StringBuilder("SELECT KWT.FIRST_NAME ||' '|| KWT.SECOND_NAME ||' '||KWT.THIRD_NAME||' '||KWT.LAST_NAME||' '||KWT.FAMILY_NAME  FROM GN_SEC_USERS USERS INNER JOIN INF_KWT_CITIZENS_RESIDENTS KWT  ");
                queryStr.append(" ON USERS.CIVIL_ID=KWT.CIVIL_ID");
                queryStr.append(" WHERE USERS.USER_CODE=");
                queryStr.append(userCode);
                Query query = EM().createNativeQuery(queryStr.toString());
                List<Vector> list = query.getResultList();
                if (list != null && list.size() > 0) {
                    Vector object = list.get(0);
                    if (object.get(0) != null) {
                        userName = object.get(0).toString();
                    }

                }
            }
            return userName;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long getCandidateCode(Long civilId) throws DataBaseException, SharedApplicationException {
        Long candidateCode = 0L;
        try {

            StringBuilder queryStr =
                new StringBuilder("select CANDIDATE_CODE from HR_EMP_EMPLOYEES e where  civil_id =");

            queryStr.append(civilId);
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


    /**
     *Added By M.abdelsabour
     * B.H Team
     * For ADC Module
     */

    public Long getCountOfAllEmployeesInMin(IBasicDTO dtoParm) throws DataBaseException, SharedApplicationException {

        IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)dtoParm;
        String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION_OUT_ADC();

        StringBuilder queryStr = new StringBuilder("select count(1)");


        ////////////////////////////////////from part /////////////////////////////////////////
        queryStr.append(" from HR_EMP_EMPLOYEES emp inner join  NL_JOB_JOBS jobs on emp.JOB_CODE = jobs.JOB_CODE ");
        queryStr.append(" left JOIN NL_JOB_JOBS jobs1 ON emp.tech_JOB_CODE = jobs1.JOB_CODE ");
        queryStr.append(" inner join   NL_ORG_MINISTRIES org on emp.MIN_CODE= org.min_code ");
        queryStr.append(" inner join INF_KWT_CITIZENS_RESIDENTS inf  ");
        queryStr.append(" on emp.REAL_CIVIL_ID = inf.CIVIL_ID ");
        queryStr.append(" where emp.ACTIVE_FLAG = " + ISystemConstant.ACTIVE_FLAG);
        // call hire_status
        if (hireStatus != null && !hireStatus.equals("")) {
            queryStr.append(" and emp.HIRSTATUS_CODE in (" + hireStatus + ") ");
        }
        // ask abour min_code
        if (searchDTO.getMinistryCode() != null) {
            queryStr.append(" and emp.min_code= " + searchDTO.getMinistryCode());
        }
        // ask abour real_civil
        if (searchDTO.getRealCivilId() != null) {
            queryStr.append(" and emp.REAL_CIVIL_ID= " + searchDTO.getRealCivilId());
        }

        // ask about job_code
        if (searchDTO.getJobCode() != null) {
            queryStr.append(" and emp.JOB_CODE = " + searchDTO.getJobCode());
        }
        if (searchDTO.getTechJobCode() != null) {
            queryStr.append(" and emp.tech_JOB_CODE = " + searchDTO.getTechJobCode());
        }
        IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
        if (dfDTO != null) {
            String wrkCodeIn = dfDTO.getDfInNative();
            String wrkCodeNotIn = dfDTO.getDfNotInNative();
            if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                queryStr.append(" and emp.WRK_CODE in(");
                queryStr.append(wrkCodeIn);
                queryStr.append(")");
            }
            if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                queryStr.append(" and emp.WRK_CODE Not in(");
                queryStr.append(wrkCodeNotIn);
                queryStr.append(")");
            }
        }
        Long counter = null;
        Vector v = (Vector)EM().createNativeQuery(queryStr.toString()).getSingleResult();
        if (v != null && v.size() != 0) {
            return counter = ((BigDecimal)v.get(0)).longValue();
        }
        return 0L;

    }

    /**
     *Added By M.abdelsabour
     * B.H Team
     * For ADC Module
     */

    public List<IBasicDTO> getAllEmployeesInMinWithPaging(IBasicDTO dtoParm) throws DataBaseException,
                                                                                    SharedApplicationException {
        IEmployeeSearchDTO searchDTO = (IEmployeeSearchDTO)dtoParm;
        IPagingRequestDTO requestDTO = (searchDTO.getPagingRequestDTO());
        String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION_OUT_ADC();

        StringBuilder queryStr = new StringBuilder("select emp.CIVIL_ID , emp.REAL_CIVIL_ID,");
        queryStr.append(" CONCAT ( CONCAT ( CONCAT ( CONCAT ( inf.first_name , ' ' ) , ");
        queryStr.append(" CONCAT ( inf.second_name , ' ' ) ) , CONCAT ( CONCAT ( inf.third_name , ' ' ) ,  ");
        queryStr.append(" CONCAT ( inf.last_name , ' ' ) ) ) , inf.family_name ) name, emp.JOB_CODE, jobs.JOB_NAME, emp.tech_JOB_CODE,  jobs1.JOB_NAME  ,  org.MIN_CODE , emp.HIRSTATUS_CODE");

        ////////////////////////////////////from part /////////////////////////////////////////
        queryStr.append(" from HR_EMP_EMPLOYEES emp inner join  NL_JOB_JOBS jobs on emp.JOB_CODE = jobs.JOB_CODE ");
        queryStr.append(" left JOIN NL_JOB_JOBS jobs1 ON emp.tech_JOB_CODE = jobs1.JOB_CODE ");
        queryStr.append(" inner join   NL_ORG_MINISTRIES org on emp.MIN_CODE= org.min_code ");
        queryStr.append(" inner join INF_KWT_CITIZENS_RESIDENTS inf  ");
        queryStr.append(" on emp.REAL_CIVIL_ID = inf.CIVIL_ID ");
        queryStr.append(" where emp.ACTIVE_FLAG = " + ISystemConstant.ACTIVE_FLAG);
        // call hire_status
        if (hireStatus != null && !hireStatus.equals("")) {
            queryStr.append(" and emp.HIRSTATUS_CODE in (" + hireStatus + ") ");
        }
        // ask abour min_code
        if (searchDTO.getMinistryCode() != null) {
            queryStr.append(" and emp.min_code= " + searchDTO.getMinistryCode());
        }
        // ask abour real_civil
        if (searchDTO.getRealCivilId() != null) {
            queryStr.append(" and emp.REAL_CIVIL_ID= " + searchDTO.getRealCivilId());
        }

        // ask about job_code
        if (searchDTO.getJobCode() != null) {
            queryStr.append(" and emp.JOB_CODE = " + searchDTO.getJobCode());
        }
        if (searchDTO.getTechJobCode() != null) {
            queryStr.append(" and emp.tech_JOB_CODE = " + searchDTO.getTechJobCode());
        }
        IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
        if (dfDTO != null) {
            String wrkCodeIn = dfDTO.getDfInNative();
            String wrkCodeNotIn = dfDTO.getDfNotInNative();
            if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                queryStr.append(" and emp.WRK_CODE in(");
                queryStr.append(wrkCodeIn);
                queryStr.append(")");
            }
            if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                queryStr.append(" and emp.WRK_CODE Not in(");
                queryStr.append(wrkCodeNotIn);
                queryStr.append(")");
            }
        }

        ////////////////////////////////////sort part /////////////////////////////////////////
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            queryStr.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                queryStr.append(column);
                if (!requestDTO.isSortAscending()) {
                    queryStr.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    queryStr.append(" , ");
                }
            }
        }


        System.out.println("Query By M.abelsabour In EMP" + queryStr.toString());

        Query query = EM().createNativeQuery(queryStr.toString());
        if (requestDTO != null) {
            query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
            query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
        }
        List<Vector> list;
        list = query.getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();

        query.setHint("toplink.refresh", "true");

        List<IBasicDTO> arryList = new ArrayList<IBasicDTO>();
        IEmployeeSearchDTO dto;

        Long code = null;
        Long realCivilId = null;
        String fullEmpName = null;
        String jobCode = null;
        String jobName = null;
        Long minCode = null;
        Long hireStatusCode;
        for (Vector obj : list) {
            dto = new EmployeeSearchDTO();
            code = Long.parseLong(obj.elementAt(0).toString());
            dto.setCode(EmpEntityKeyFactory.createEmployeeSearchEntityKey(code));
            realCivilId = Long.parseLong(obj.elementAt(1).toString());
            dto.setRealCivilId(realCivilId);
            fullEmpName = (String)obj.elementAt(2);
            dto.setFullEmpName(fullEmpName);
            jobCode = (String)obj.elementAt(3);
            dto.setJobCode(jobCode);
            jobName = (String)obj.elementAt(4);
            dto.setJobName(jobName);
            jobCode = (String)obj.elementAt(5);
            dto.setTechJobCode(jobCode);
            jobName = (String)obj.elementAt(6);
            dto.setTechJobName(jobName);
            minCode = Long.parseLong(obj.elementAt(7).toString());
            dto.setMinistryCode(minCode);
            hireStatusCode = Long.parseLong(obj.elementAt(8).toString());
            dto.setEmpHireStatus(hireStatusCode);
            dto.setActiveFlag(ISystemConstant.ACTIVE_FLAG);
            arryList.add(dto);
        }

        return arryList;
    }

    /**
     * updateJobForADC
     * @param basicDTOList
     * @param jobCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @createdBy Black Hourse[E.Saber]
     */
    public Boolean updateJobCodeForADC(List<IBasicDTO> basicDTOList, String jobCode) throws DataBaseException,
                                                                                            SharedApplicationException {
        CallableStatement stm = null;
        try {
            Boolean validToUpdate = false;
            StringBuilder queryString = new StringBuilder("BEGIN ");
            for (IBasicDTO basicDTO : basicDTOList) {
                EmployeeSearchDTO employeeSearchDTO = (EmployeeSearchDTO)basicDTO;
                if (employeeSearchDTO.getActiveFlag().equals(1L)) {
                    queryString.append(" UPDATE HR_EMP_EMPLOYEES ");
                    queryString.append(" SET JOB_CODE = " + jobCode + " ");
                    queryString.append(" WHERE MIN_CODE = " + employeeSearchDTO.getMinistryCode().toString() + " ");
                    queryString.append(" AND JOB_CODE = " + employeeSearchDTO.getJobCode() + " ");
                    queryString.append(" AND CIVIL_ID = " + employeeSearchDTO.getCode().getKey() + " ; ");
                    if (!validToUpdate)
                        validToUpdate = true;
                }
            }
            queryString.append(" END;");
            System.out.println(queryString.toString());
            if (validToUpdate) {
                Connection con = getConnectionForUpdate();
                stm = con.prepareCall(queryString.toString());
                stm.executeUpdate();
            }
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
    //////////////////////////////////////////////// ESRV ///////////////////////////////////////////

    public List<IBasicDTO> getAllEmployeeswithContract(Long minCode) throws DataBaseException,
                                                                            SharedApplicationException {

        StringBuilder queryStr =
            new StringBuilder("SELECT EMP.REAL_CIVIL_ID,EMP.HIRE_DATE,BASC_OWNER.GET_NAME( EMP.REAL_CIVIL_ID ) FULLNAME");
        queryStr.append(" FROM HR_EMP_EMPLOYEES EMP WHERE EMP.MIN_CODE = " + minCode);
        queryStr.append(" AND (SYSDATE-EMP.HIRE_DATE >=365) AND EMP.CIVIL_ID IN  ");
        queryStr.append(" (SELECT SAL.CIVIL_ID FROM    HR_SAL_EMP_SALARY_ELEMENTS SAL , HR_SAL_ELEMENT_GUIDES ELM , HR_SAL_GUIDE_EXTRA_DATA EX");
        queryStr.append(" WHERE SAL.ELMGUIDE_CODE = ELM.ELMGUIDE_CODE ");
        queryStr.append(" AND ELM.ELMTYPE_CODE in (13,14) AND SAL.UNTIL_DATE IS NULL AND SAL.STATUS = 1 AND SAL.ELMGUIDE_CODE = EX.ELMGUIDE_CODE AND  EX.EXTDATTYPE_CODE = 6) ");

        System.out.println("getAllEmployeeswithContract In EMP" + queryStr.toString());

        Query query = EM().createNativeQuery(queryStr.toString());
        List<Vector> list;
        list = query.getResultList();
        if (list == null || list.size() == 0)
            throw new NoResultException();

        query.setHint("toplink.refresh", "true");

        List<IBasicDTO> arryList = new ArrayList<IBasicDTO>();
        IEmployeesDTO dto;

        Long realCivilId = null;
        String fullEmpName = null;
        Date hireStatusDate;
        for (Vector obj : list) {
            dto = new EmployeesDTO();
            realCivilId = Long.parseLong(obj.elementAt(0).toString());
            dto.setRealCivilId(realCivilId);
            hireStatusDate = new Date(((java.sql.Timestamp)obj.elementAt(1)).getTime());
            dto.setHireDate(hireStatusDate);
            fullEmpName = (String)obj.elementAt(2);
            dto.setEmployeeName(fullEmpName);
            arryList.add(dto);
        }

        return arryList;
    }

    public List<IBasicDTO> simpleSearchGroupVacEmpWithPaging(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            list = buildSearchQueryEmpVacWithPaging(basicDTO, requestDTO);
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpDTOFactory.createEmployeesDTO(entity, true));
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

    public List<IBasicDTO> simpleSearchGroupVacEmpWithPaging(IBasicDTO basicDTO,IBgtProgramsDTO level,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            list = buildSearchQueryEmpVacWithPaging(basicDTO,level, requestDTO);
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpDTOFactory.createEmployeesDTO(entity, true));
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


    public Long searchEmpWithoutValidationsCount(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        return buildSearchCountQuery(basicDTO);
    }

    private Long buildSearchCountQuery(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        System.out.println("SearchEmpWithoutValidationsCount ::" + ejbql.toString());


        Query query = EM().createQuery(ejbql.toString());

        return (Long)query.getSingleResult();


    }


    public List<IBasicDTO> searchEmpWithoutValidationsQuery(IBasicDTO basicDTO,
                                                            IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                 SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildsearchEmpWithoutValidationsWithPaging(basicDTO, requestDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }


    private List<EmployeesEntity> buildsearchEmpWithoutValidationsWithPaging(IBasicDTO basicDTO,
                                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                                  SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        List<EmployeesEntity> list = null;
        System.out.println("searchEmpWithoutValidationsQuery:: " + ejbql.toString());
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }

        Query query = null;

        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();

        }
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;


    }

    public IEmployeesDTO getEmployeeSimpleDTOForESRV(EmployeesDTO emp) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            System.out.println("getEmployeeSimpleDTOForESRVgetEmployeeSimpleDTOForESRVgetEmployeeSimpleDTOForESRV");
            StringBuilder ejbql = null;
            ejbql =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.activeFlag = " + IEMPConstants._EMP_ACTIVE_STATUS);
            if (emp.getRealCivilId() != null) {
                ejbql.append(" AND  o.realCivilId = '" + emp.getRealCivilId() + "'");
            }
            if (emp.getMinCode() != null) {
                ejbql.append(" AND  o.minCode = " + emp.getMinCode());
            }
            if (emp.getWorkCenterKey() != null) {
                ejbql.append(" and o.wrkCode in( " + emp.getWorkCenterKey() + ")");
            }
            Query query = EM().createQuery(ejbql.toString());
            query.setHint("toplink.refresh", "true");
            List<EmployeesEntity> list;
            list = query.getResultList();
            List<IEmployeesDTO> listDTO = new ArrayList<IEmployeesDTO>();
            //            for (EmployeesEntity entity : list) {
            if (list != null && list.size() > 0) {
                listDTO.add(EmpEntityConverter.getEmployeesDTOForESRV(list.get(0)));
            }
            //            }
            if (listDTO != null && listDTO.size() > 0) {
                return listDTO.get(0);
            } else {
                return new EmployeesDTO();
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public String getUserNameByRole(Long roleId, Long realCivil, Long minCode) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            StringBuilder query;
            query = new StringBuilder(" SELECT user_name FROM GN_SEC_USERS WHERE  1 = 1 and LOCKED =0 and USER_DELETED=0 ");
            query.append("  AND (INSTR ( (SELECT    ',' ");
            query.append(" || BASC_OWNER.APPROVAL_MAKER_PAC.GET_APR_MKR_USRS ( " + roleId + "," + realCivil + ")");
            query.append("|| ',' FROM DUAL), ',' || user_code || ',', 1, 1) > 0)");

            System.out.println("EmployeeDAO.getRoleUserName :: " + query.toString());


            Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");


            List<Vector> list;
            list = q.getResultList();
            if (list == null || list.size() <= 0) {
                return "";
            }
            StringBuilder allUserName = new StringBuilder("");
            for (Vector row : list) {
                String userStr = row.get(0).toString();
                boolean isUserExists = false;
                IUsersClient usersClient = SecClientFactory.getUsersClient();
                isUserExists = usersClient.checkUserExist(userStr);
                System.out.println("EmployeeDAO.isUserExists" + isUserExists);
                if (!userStr.startsWith("Err") || isUserExists)
                    allUserName.append(row.get(0).toString() + ",");
            }
            allUserName.replace(allUserName.length() - 1, allUserName.length(), "");
            return allUserName.toString();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public String getUserNamesHighgerRole(Long roleId, String userNames, Long minCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            StringBuilder allUserName = new StringBuilder(userNames + ",");
            String[] userNamesArr = userNames.split(",");
            List<String> userNamesList = Arrays.asList(userNamesArr);
            if (userNamesList != null && userNamesList.size() > 0) {
                String undersecretary = getUndersecretary(userNamesList.get(0));
                for (String user : userNamesList) {
                    String userName = getHighgerUserNamesByUsernameAndRole(roleId, user);
                    if (!userName.equals(undersecretary)) {
                        allUserName = allUserName.append(userName);
                    } else {
                        allUserName = allUserName.append(user);
                    }
                }
            }
            if (allUserName.length() > 1) {
                allUserName.replace(allUserName.length() - 1, allUserName.length(), "");
            }
            return allUserName.toString();
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    private String getHighgerUserNamesByUsernameAndRole(Long roleId, String userName) throws DataBaseException,
                                                                                             SharedApplicationException {
        StringBuilder query;
        query = new StringBuilder(" SELECT user_name FROM GN_SEC_USERS WHERE  1 = 1 and LOCKED =0 and USER_DELETED=0 ");
        query.append("  AND (INSTR ( (SELECT    ',' ");
        query.append(" || BASC_OWNER.APPROVAL_MAKER_PAC.GET_APR_MKR_USRS ( " + roleId + ",");
        query.append("(SELECT s2.CIVIL_ID FROM GN_SEC_USERS s2 WHERE s2.LOCKED =0 and s2.USER_DELETED=0 and s2.USER_NAME LIKE '" + userName + "'))");
        query.append("|| ',' FROM DUAL), ',' || user_code || ',', 1, 1) > 0)");

        System.out.println("EmployeeDAO.getHighgerUserNamesByUsernameAndRole :: " + query.toString());


        Query q = EM().createNativeQuery(query.toString());


        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder allUserName = new StringBuilder("");
        for (Vector row : list) {
            String userStr = row.get(0).toString();
            if (!userStr.startsWith("Err"))
                allUserName.append(row.get(0).toString() + ",");
        }
        return allUserName.toString();
    }

    //ESRV

    private String getUndersecretary(String userName) throws DataBaseException, SharedApplicationException {
        StringBuilder query;
        query =
                new StringBuilder("select  BASC_OWNER.APPROVAL_MAKER_PAC.GET_APR_MKR_USRS (2, (SELECT s2.CIVIL_ID FROM GN_SEC_USERS s2 WHERE s2.LOCKED =0 and s2.USER_DELETED=0 and s2.USER_NAME LIKE ");
        query.append("'" + userName + "'))  FROM DUAL");
        System.out.println("EmployeeDAO.getUndersecretary :: " + query.toString());
        Query q = EM().createNativeQuery(query.toString());
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder allUserName = new StringBuilder("");
        for (Vector row : list) {
            String userStr = row.get(0).toString();
            if (!userStr.startsWith("Err"))
                allUserName.append(row.get(0).toString() + ",");
        }
        return allUserName.toString();
    }

    /**
     * @author Black Horse
     * @since 16-11-2016
     * @param realCivilId
     * @param minCode
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID and mincode and also check about the following status :
     * EMP_STATUS_DELEGATION_OUT_TO_IT = Long.valueOf(11); //منتدب خارجيا�? اليها
     */

    public IEmployeesDTO getByRealCivilIdAndHireStatusForExternalMov(Long realCivilId,
                                                                     Long minCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId  " +
                                      "AND ( :minCode is null OR o.minCode = :minCode)  ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode = (" +
                                IEMPConstants.EMP_STATUS_DELEGATION_OUT_TO_IT + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                System.out.println("getByRealCivilIdAndHireStatusForExternalMov ::" + queryStr.toString());
                System.out.println("realCivilId :" + realCivilId);
                System.out.println("minCode :" + minCode);
                list =
EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                            IEMPConstants._EMP_ACTIVE_STATUS).setParameter("minCode",
                                                                                                                                           minCode).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

    private StringBuilder buildSearchEmpWithHireStatusQuery(IBasicDTO basicDTO,
                                                            boolean isCount) throws DataBaseException,
                                                                                    SharedApplicationException {

        StringBuilder ejbql = new StringBuilder();
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        if (isCount) {
            ejbql.append("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
        } else {
            ejbql.append("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        }
        ejbql.append(" AND o.activeFlag =" + IEMPConstants._EMP_ACTIVE_STATUS);
        if (employeeSearchDTO.getLoginMinistry() != null) {
            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getLoginMinistry());
        }
        if (employeeSearchDTO.getEmpHireStatusList() != null) {
            StringBuilder hireStatusCodes = new StringBuilder();
            for (Long code : employeeSearchDTO.getEmpHireStatusList()) {
                hireStatusCodes.append(code);
                hireStatusCodes.append(",");
            }
            if (!hireStatusCodes.toString().isEmpty()) {
                hireStatusCodes = hireStatusCodes.deleteCharAt(hireStatusCodes.length() - 1);
                ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN ( " + hireStatusCodes + ") ");
            }
        }
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        System.out.println("buildSearchEmpWithHireStatusQuery :: " + ejbql.toString());
        return ejbql;
    }

    public Long searchEmpWithHireStatusCount(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        StringBuilder queryStr = buildSearchEmpWithHireStatusQuery(basicDTO, true);
        Query query = EM().createQuery(queryStr.toString());
        return (Long)query.getSingleResult();
    }

    public List<IBasicDTO> searchEmpWithHireStatusData(IBasicDTO basicDTO,
                                                       IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        StringBuilder ejbql = buildSearchEmpWithHireStatusQuery(basicDTO, false);
        List<EmployeesEntity> list = null;
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }
        Query query = null;
        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();
        }
        if (list == null || list.size() == 0)
            throw new NoResultException();

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;
    }

    public Long simpleSearchCountBasicWithPagingADC(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        return buildSearchCountQueryWithPagingADC(basicDTO);
    }

    private Long buildSearchCountQueryWithPagingADC(IBasicDTO basicDTO) throws DataBaseException,
                                                                               SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            if (employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHireWithoutDELEGATION_OUT_ADC() +
                             " )");
        }
        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        if (employeeSearchDTO.getRecordDescCode() != null) {
            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
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
        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        //edited by Hany Omar [B.Horse]
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }

        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        //        String wrkcode = initWrkcenterTree();
        //        if (wrkcode != null && !wrkcode.isEmpty()) {
        //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //        }
        // Added By MLotfy, apply data level sequrity
        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        if (excludedWorkCentersStr != null) {
            ejbql.append(excludedWorkCentersStr);
        }


        System.out.println("EmpSimpleSearch ::" + ejbql.toString());


        Query query = EM().createQuery(ejbql.toString());

        return (Long)query.getSingleResult();


    }

    public List<IBasicDTO> simpleSearchBasicWithPagingADC(IBasicDTO basicDTO,
                                                          IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                               SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildSearchQueryWithPagingADC(basicDTO, requestDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }

    private List<EmployeesEntity> buildSearchQueryWithPagingADC(IBasicDTO basicDTO,
                                                                IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                     SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }
        if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
            String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
            ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
        }
        /// commented By M.abdelsabour for applying new Data Filteration CSC-21713
        //        else {
        //            //added by Technical Team [m.sayed] at 31-3-2016
        //            // stroy ID CSC-17343  work Center data filter
        //            String wrkcode = initWrkcenterTree();
        //            if (wrkcode != null && !wrkcode.isEmpty()) {
        //                ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        //            }
        //        }
        if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                         " ) ");

        if (employeeSearchDTO.getStartWorkingDate() != null)
            ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
        if (employeeSearchDTO.getEmpHireTypes() != null &&
            !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
            ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
            ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
        }
        //        else{
        //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
        //            }
        if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

            //By MLotfy new search
            //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
            ejbql.append(" AND (" +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                         " ) ");

        if (employeeSearchDTO.getEmpHireStage() != null &&
            !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
            ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
        if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
            ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                         " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
            StringBuilder statusCodeStr = new StringBuilder("");
            for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                statusCodeStr.append(status + ",");
            }
            ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");

        } else {
            if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
            //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
            if (employeeSearchDTO.isEmployment())
                ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHireWithoutDELEGATION_OUT_ADC() +
                             " )");
        }

        if (employeeSearchDTO.getActiveFlag() != null) {
            ejbql.append(" AND o.activeFlag=" + employeeSearchDTO.getActiveFlag() + "");
        } else {
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
        }
        if (employeeSearchDTO.getRecordDescCode() != null) {
            ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
        }
        if (employeeSearchDTO.getEmploymentStage() != null)
            ejbql.append(" AND o.hirstageCode IN (" + employeeSearchDTO.getEmploymentStage().toString() + ")");
        //////////CH HR 762/////////////////////
        if (employeeSearchDTO.getHireDateFrom() != null)
            ejbql.append(" AND o.hireDate >='" + employeeSearchDTO.getHireDateFrom() + "'");
        if (employeeSearchDTO.getHireDateTO() != null)
            ejbql.append(" AND o.hireDate <='" + employeeSearchDTO.getHireDateTO() + "'");
        if (employeeSearchDTO.getMinistryFileNo() != null && employeeSearchDTO.getMinistryFileNo().length() > 0)
            ejbql.append(" AND o.ministryFileNo LIKE '" + employeeSearchDTO.getMinistryFileNo() + "'");
        if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
            ejbql.append(" AND o.minCode=" + employeeSearchDTO.getMinistryCode() + "");
            System.out.println("EmpDAO :: MinCode :: Test :: Hany Omar" + employeeSearchDTO.getMinistryCode());
        }
        //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
        //edited by Hany Omar [B.Horse]
        else {
            ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
        }
        // Added By MLotfy, apply data level sequrity
        String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
        if (excludedWorkCentersStr != null) {
            ejbql.append(excludedWorkCentersStr);
        }

        List<EmployeesEntity> list = null;
        System.out.println("Full Search Query :: TEST :" + ejbql.toString());
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }

        Query query = null;

        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();

        }
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;


    }

    public IEmployeesDTO getByRealCivilIdForADCWithoutFilteration(Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION_OUT_ADC();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId   ");
                if (minCode > -1L) {
                    queryStr.append("AND ( :minCode is null OR o.minCode = :minCode)");
                }
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                Query query;

                System.out.println("A.Kahled :: " + queryStr.toString());
                System.out.println("realCivilId : " + realCivilId);

                query =
                        EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                                                    IEMPConstants._EMP_ACTIVE_STATUS);
                if (minCode > -1L) {
                    query.setParameter("minCode", minCode);
                }
                System.out.println(queryStr.toString());
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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


    public IEmployeesDTO getByRealCivilIdForADCEmployees(Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //getting status from centeralized function
            String hireStatus = EmpUtils.getStatusForHireWithoutDELEGATION_OUT_ADC();
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE  o.realCivilId = :realCivilId   ");
                if (minCode > -1L) {
                    queryStr.append("AND ( :minCode is null OR o.minCode = :minCode)");
                }
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (1) ");
                queryStr.append(" and o.activeFlag=:activeflag ");
                Query query;

                System.out.println("A.Kahled :: " + queryStr.toString());
                System.out.println("realCivilId : " + realCivilId);

                query =
                        EM().createQuery(queryStr.toString()).setParameter("realCivilId", realCivilId).setParameter("activeflag",
                                                                                                                    IEMPConstants._EMP_ACTIVE_STATUS);
                if (minCode > -1L) {
                    query.setParameter("minCode", minCode);
                }
                System.out.println(queryStr.toString());
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

    public List<EmployeesDTO> getAllUserNameByRole(Long roleId, Long realCivil, Long minCode,
                                                   List<String> userCodes) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            List<EmployeesDTO> empListDTO = new ArrayList<EmployeesDTO>();
            StringBuilder query;
            query = new StringBuilder(" SELECT U.USER_NAME , U.USER_CODE, U.CIVIL_ID,  ");
            query.append(" CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_Name , ' ' ) , CONCAT ( kwt.second_Name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_Name , ' ' ) , CONCAT ( kwt.last_Name , ' ' ) ) ) , kwt.family_Name ) as empName");
            query.append(" FROM GN_SEC_USERS U , INF_KWT_CITIZENS_RESIDENTS kwt WHERE  1 = 1");
            query.append(" AND U.CIVIL_ID = kwt.CIVIL_ID ");
            query.append(" AND (INSTR ( (SELECT    ',' ");
            query.append(" || BASC_OWNER.APPROVAL_MAKER_PAC.GET_APR_MKR_USRS ( " + roleId + "," + realCivil + ")");
            query.append("|| ',' FROM DUAL), ',' || U.USER_CODE || ',', 1, 1) > 0)");
            if (userCodes != null && userCodes.size() > 0) {
                query.append(" AND U.USER_CODE NOT IN ( ");
                for (String usercode : userCodes) {
                    query.append(usercode);
                    query.append(",");
                }
                if (query.charAt(query.length() - 1) == ',') {
                    query = query.replace(query.length() - 1, query.length(), "");
                }
                query.append(")");
            }
            System.out.println("EmployeeDAO.getAllUserNameByRole :: " + query.toString());
            System.out.println("EmployeeDAO.getAllUserNameByRole BEFOR :: " + query.toString());
            Connection connection = getConnection();
            CallableStatement cstmt = connection.prepareCall(query.toString());
            ResultSet rows = cstmt.executeQuery();
            if (rows == null) {
                System.out.println("EmployeeDAO.getAllUserNameByRole rows NULL:: ");
                return new ArrayList<EmployeesDTO>();
            }
            while (rows.next()) {
                EmployeesDTO empDTO = new EmployeesDTO();
                empDTO.setUserName(rows.getString(1));
                if (rows.getString(2) != null)
                    empDTO.setUserCode(Long.valueOf(rows.getString(2)));
                if (rows.getString(3) != null)
                    empDTO.setRealCivilId(Long.valueOf(rows.getString(3)));
                empDTO.setEmployeeName(rows.getString(4));
                empListDTO.add(empDTO);

                System.out.println("EmployeeDAO.getAllUserNameByRole 22222:: " + empListDTO.size());

            }
            return empListDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    /*public List<IBasicDTO> searchByCivilAndNameAllMinistries(IBasicDTO basicDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        StringBuffer ejbql = null;
        String hireStatus = EmpUtils.getStatusForHire();
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuffer("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");
        ejbql.append(" and o.activeFlag=" + IEMPConstants._EMP_ACTIVE_STATUS);
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND Exists( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE kwt.civilId=o.realCivilId AND " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");
        }
        //added by Technical Team [m.sayed] at 31-3-2016
        // stroy ID CSC-17343  work Center data filter
        String wrkcode = initWrkcenterTree();
        if (wrkcode != null && !wrkcode.isEmpty()) {
            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
        }
        System.out.println(ejbql);

        List<EmployeesEntity> list = EM().createQuery(ejbql.toString()).getResultList();
        if (list == null || list.size() <= 0) {
            throw new NoResultException();
        }
        List listDTO = new ArrayList();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;
    }*/

    public List<IBasicDTO> searchByCivilAndNameAllMinistries(IBasicDTO basicDTO,
                                                             Boolean mokfaaShamla) throws DataBaseException,
                                                                                          SharedApplicationException {
        IEmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        try {
            StringBuilder ejbql = new StringBuilder("SELECT EMP.* ");
            ejbql.append(" FROM HR_EMP_EMPLOYEES EMP  ");
            ejbql.append(" WHERE 1=1 ");
            ejbql.append(" AND EMP.HIRSTATUS_CODE IN (");
            ejbql.append(EmpUtils.getStatusForHire() + ")");
            ejbql.append(" AND EMP.ACTIVE_FLAG =" + IEMPConstants._EMP_ACTIVE_STATUS);
            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND  EMP.REAL_CIVIL_ID = " + employeeSearchDTO.getCivilId());

            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" AND Exists( Select kwt.CIVIL_ID From INF_KWT_CITIZENS_RESIDENTS kwt WHERE kwt.CIVIL_ID=EMP.REAL_CIVIL_ID AND " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.FIRST_NAME , ' ' ) , CONCAT ( kwt.SECOND_NAME , ' ' ) ) , CONCAT ( CONCAT ( kwt.THIRD_NAME , ' ' ) , CONCAT ( kwt.LAST_NAME , ' ' ) ) ) , kwt.FAMILY_NAME )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");
            }

            if (mokfaaShamla)
                ejbql.append(" AND EMP.CIVIL_ID IN (SELECT SALELM.CIVIL_ID FROM  HR_SAL_EMP_SALARY_ELEMENTS SALELM INNER JOIN HR_SAL_ELEMENT_GUIDES SALG ON SALELM.ELMGUIDE_CODE=SALG.ELMGUIDE_CODE WHERE EMP.CIVIL_ID=SALELM.CIVIL_ID AND EMP.MIN_CODE=SALELM.MIN_CODE AND SALG.ELMTYPE_CODE=13 AND SALG.SAL_BY_DECESION_FLAG=1 AND TRUNC(SYSDATE) BETWEEN SALELM.FROM_DATE AND NVL(SALELM.UNTIL_DATE,SYSDATE) ) ");


            System.out.println("searchByCivilAndNameAllMinistries :" + ejbql.toString());
            Query query = EM().createNativeQuery(ejbql.toString(), EmployeesEntity.class);


            List list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (Object row : list) {
                arrayList.add(EmpEntityConverter.getSimpleEmployeesDTOForMov((EmployeesEntity)row));
            }

            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /** Search Employees using the realCivilID or Name
     * For CER Module
     * @input realCivilId, name
     * @author Amr Abdel Halim
     * @since 26-FEB-2017
     * return List
     * */
    public List<ISimpleEmployeesDTO> getByRealCivilForCER(Long realCivilId, String name) throws DataBaseException,
                                                                                                SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            StringBuilder queryStr = new StringBuilder("select o from EmployeesEntity o ");
            if (realCivilId != null) {
                queryStr.append(" WHERE  o.realCivilId= '" + realCivilId + "'");
            } else if (name != null && !name.equals("")) {
                queryStr.append(" WHERE o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                                QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                    name) + " ) ");

            }
            queryStr.append(" order by o.civilId desc");
            System.out.println("getByRealCivilForCER :: " + queryStr.toString());
            list = EM().createQuery(queryStr.toString()).getResultList();
            List<ISimpleEmployeesDTO> listDTO = new ArrayList<ISimpleEmployeesDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getSimpleEmployeesDTOForCER(entity));
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

    /////////////////////start ftq part /////////////////////

    public Long searchEmpWithPagingForFTQCount(IBasicDTO basicDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        return buildSearchCountFTQQuery(basicDTO);
    }

    private Long buildSearchCountFTQQuery(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {

        String hireStatus = "1,9,13";
        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }


        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ") ");
        ejbql.append(" and o.activeFlag=1 ");
        System.out.println("getByRealCivilForFTQ :: " + ejbql.toString());
        System.out.println("buildSearchCountFTQQuery ::M.abdelsabour::" + ejbql.toString());


        Query query = EM().createQuery(ejbql.toString());

        return (Long)query.getSingleResult();


    }

    public List<IBasicDTO> searchEmpWithPagingForFTQ(IBasicDTO basicDTO,
                                                     IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                          SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildSearchEmpWithPagingForFTQ(basicDTO, requestDTO);

        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }


    private List<EmployeesEntity> buildSearchEmpWithPagingForFTQ(IBasicDTO basicDTO,
                                                                 IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                      SharedApplicationException {
        String hireStatus = "1,9,13";
        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        ejbql = new StringBuilder("select o from EmployeesEntity o WHERE o.civilId=o.civilId");
        if (employeeSearchDTO.getCivilId() != null)
            ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
        if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
            ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                         QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                             employeeSearchDTO.getEmpName()) + " ) ");

        }


        ejbql.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ") ");
        ejbql.append(" and o.activeFlag=1 ");


        List<EmployeesEntity> list = null;
        System.out.println("searchEmpWithoutValidationsQuery:: M.abdelsabour :" + ejbql.toString());
        //TODO apply sorting
        if (requestDTO != null && requestDTO.getSortColumnList() != null &&
            requestDTO.getSortColumnList().size() > 0) {
            ejbql.append(" ORDER BY ");
            for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                String column = (String)requestDTO.getSortColumnList().get(i);
                ejbql.append(column);
                if (!requestDTO.isSortAscending()) {
                    ejbql.append(" DESC");
                }
                if (i < requestDTO.getSortColumnList().size() - 1) {
                    ejbql.append(" , ");
                }
            }
        }

        Query query = null;

        if (ejbql != null) {
            query = EM().createQuery(ejbql.toString());
            if (requestDTO != null) {
                query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
            }
            list = query.getResultList();

        }
        if (list == null || list.size() == 0)
            throw new NoResultException();

        return list;


    }

    /////////////////////end ftq part /////////////////////

    public IEmployeesDTO getByRealCivilIdBasicData(Long realCivilId, Long minCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            try {
                list =
EM().createNamedQuery("EmployeesEntity.getByRealCivilId").setHint("toplink.refresh", "true").setParameter("realCivilId",
                                                                                                          realCivilId).setParameter("minCode",
                                                                                                                                    minCode).setParameter("activeFlag",
                                                                                                                                                          IEMPConstants._EMP_ACTIVE_STATUS).getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }

            //IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTORCivilName(employeesEntity);
            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOCodeName2(employeesEntity);

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

    public IEmployeesDTO getByRealCivilIdBasicDataAndMinistryName(Long realCivilId,
                                                                  Long minCode) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            //String hireStatus = EmpUtils.getStatusForHire();
            try {
                Query query = EM().createNamedQuery("EmployeesEntity.getBasicDataByRealCivilId");
                query.setParameter("realCivilId" , realCivilId);
                query.setParameter("minCode" , minCode);
                query.setParameter("activeFlag" , IEMPConstants._EMP_ACTIVE_STATUS);
                query.setHint("toplink.refresh" , "true");
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }

            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            }

            if (employeesEntity == null) {
                throw new ItemNotFoundException();
            }

            //IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTORCivilName(employeesEntity);
            IEmployeesDTO employeesDTO = EmpEntityConverter.getBasicEmployeesDTOWithMinistry(employeesEntity);

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


    public Long countFilesNoWithMinAndEmployeeForADC(Long minCode, String minFileNo,
                                                     Long civilId) throws DataBaseException,
                                                                          SharedApplicationException {
        String hireStatusStr =
            IEMPConstant.EMP_STATUS_EMPLOYMENT + "," + IEMPConstants.EMP_STATUS_FREEZED + "," + IEMPConstants.HR_EMP_REASON_TYPES_TERMINATE_EXT_DLG;
        StringBuilder st = new StringBuilder("SELECT real_CIVIL_ID");
        st.append(" FROM HR_EMP_EMPLOYEES e");
        st.append(" WHERE MINISTRY_FILE_NO = ?1 ");
        st.append(" AND MIN_CODE = ?2 ");
        st.append(" AND e.REAL_CIVIL_ID<> ?3");

        st.append(" AND HIRSTATUS_CODE IN (" + hireStatusStr + ")");
        System.out.println("countFilesWithMinInEmployee :: " + st);
        Query q = EM().createNativeQuery(st.toString());
        q.setParameter(1, minFileNo);
        q.setParameter(2, minCode);
        q.setParameter(3, civilId);
        Vector count = (Vector)q.getSingleResult();
        if (count != null && !count.isEmpty()) {
            return new Long(((BigDecimal)count.get(0)).longValue());
        }
        return null;
    }

    ////////////////////////////////////////10/07/2017 for Att change request special permission////////////////////////////////////

    /**
     * @author B.H Team[M.Abdelsabour & E.Saber]
     * @since 23-7-2016
     * @param workCenter
     * @param isRelated
     * @param isNative
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * CASE 1: return all civils related to corresponding work center as native query embedded.
     * CASE 2: return string of comma separated values of civils.
     */

    public String getCivilsRelatedToWorkCenter(String workCenter, boolean isRelated,
                                               boolean isNative) throws DataBaseException, SharedApplicationException {

        Long groupCode = CSCSecurityInfoHelper.getGroupCodeFromRequest();
        Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
        System.out.println("getCivilsRelatedToWorkCenter " + groupCode.toString());
        System.out.println("getCivilsRelatedToWorkCenter " + minCode.toString());
        if (workCenter == null || workCenter.isEmpty()) {
            workCenter =
                    " (SELECT wrk_code" + " FROM GN_SEC_GROUP_WORK_CENTERS" + " WHERE group_code = " + groupCode + ") ";
            isRelated = true;
        } else {
            workCenter = "'" + workCenter + "'";
        }
        StringBuilder query = new StringBuilder("");
        query.append("SELECT emp.civil_id");
        query.append("  FROM hr_emp_employees emp");
        query.append(" WHERE emp.active_flag = 1");
        query.append("   AND emp.hirstatus_code IN (1, 13)");
        query.append("     and (  exists( select 1");
        query.append("            from hr_mov_moving_requests mov");
        query.append("            WHERE emp.civil_id = mov.civil_id");
        query.append("            AND mov.movtype_code IN (select MOVTYPE_CODE from HR_MOV_MOVING_TYPES T where  T.FIRST_PARENT = 8 )");
        query.append("            AND mov.leaf_flag = 1");
        query.append("            AND mov.min_code = " + minCode);
        query.append("            AND mov.reqstatus_code = 7         ");
        //            query.append("      AND SYSDATE BETWEEN mov.moving_Date AND mov.until_Date");
        //            query.append("     AND SYSDATE BETWEEN mov.moving_Date AND mov.until_Date");
        if (!isRelated) {
            query.append("           AND mov.wrk_code = " + workCenter);
        } else {
            query.append("          AND mov.WRK_CODE IN (    SELECT wrk_code ");
            query.append("                                   FROM nl_org_work_centers ");
            query.append("                             START WITH wrk_code IN ");
            query.append("                                           (SELECT DISTINCT ");
            query.append("                                                   CASE WHEN firstID = 1 THEN wrkcode1 ELSE wrkcode2 END ");
            query.append("                                                      AS wrkCode ");
            query.append("                                              FROM    (    SELECT WC.WRK_CODE AS wrkcode1, 1 AS firstID ");
            query.append("                                                             FROM NL_ORG_WORK_CENTERS WC ");
            query.append("                                                            WHERE WC.MIN_CODE = " + minCode +
                         " AND WC.WRKLEVEL_CODE = 5 ");
            query.append("                                                       START WITH (WC.WRK_CODE IN ");
            query.append("                                                                      (SELECT wrk_code ");
            query.append("                                                                         FROM GN_SEC_GROUP_WORK_CENTERS ");
            query.append("                                                                        WHERE group_code = " +
                         groupCode + ")) ");
            query.append("                                                       CONNECT BY PRIOR WC.PARENT_WRK = WC.WRK_CODE) query1 ");
            query.append("                                                   FULL OUTER JOIN ");
            query.append("                                                      (    SELECT wrk_code AS wrkcode2, 1 AS secondID ");
            query.append("                                                             FROM NL_ORG_WORK_CENTERS ");
            query.append("                                                       START WITH wrk_code IN ");
            query.append("                                                                     (SELECT wrk_code ");
            query.append("                                                                        FROM GN_SEC_GROUP_WORK_CENTERS ");
            query.append("                                                                       WHERE group_code = " +
                         groupCode + ") ");
            query.append("                                                       CONNECT BY PRIOR wrk_code = PARENT_WRK) query2 ");
            query.append("                                                   ON query1.firstID = query2.secondID) ");
            query.append("                             CONNECT BY PRIOR wrk_code = PARENT_WRK) ");
        }
        query.append("              AND emp.wrk_code <> mov.wrk_code)");
        query.append("        OR(emp.min_code = " + minCode);
        if (!isRelated) {
            query.append("        AND emp.wrk_code = " + workCenter);
        } else {
            query.append("          AND EMP.WRK_CODE IN (    SELECT wrk_code ");
            query.append("                                   FROM nl_org_work_centers ");
            query.append("                             START WITH wrk_code IN ");
            query.append("                                           (SELECT DISTINCT ");
            query.append("                                                   CASE WHEN firstID = 1 THEN wrkcode1 ELSE wrkcode2 END ");
            query.append("                                                      AS wrkCode ");
            query.append("                                              FROM    (    SELECT WC.WRK_CODE AS wrkcode1, 1 AS firstID ");
            query.append("                                                             FROM NL_ORG_WORK_CENTERS WC ");
            query.append("                                                            WHERE WC.MIN_CODE = " + minCode +
                         " AND WC.WRKLEVEL_CODE = 5 ");
            query.append("                                                       START WITH (WC.WRK_CODE IN ");
            query.append("                                                                      (SELECT wrk_code ");
            query.append("                                                                         FROM GN_SEC_GROUP_WORK_CENTERS ");
            query.append("                                                                        WHERE group_code = " +
                         groupCode + ")) ");
            query.append("                                                       CONNECT BY PRIOR WC.PARENT_WRK = WC.WRK_CODE) query1 ");
            query.append("                                                   FULL OUTER JOIN ");
            query.append("                                                      (    SELECT wrk_code AS wrkcode2, 1 AS secondID ");
            query.append("                                                             FROM NL_ORG_WORK_CENTERS ");
            query.append("                                                       START WITH wrk_code IN ");
            query.append("                                                                     (SELECT wrk_code ");
            query.append("                                                                        FROM GN_SEC_GROUP_WORK_CENTERS ");
            query.append("                                                                       WHERE group_code = " +
                         groupCode + ") ");
            query.append("                                                       CONNECT BY PRIOR wrk_code = PARENT_WRK) query2 ");
            query.append("                                                   ON query1.firstID = query2.secondID) ");
            query.append("                             CONNECT BY PRIOR wrk_code = PARENT_WRK) ");
        }
        query.append("        )");
        query.append("      ) ");
        System.out.println(query.toString());
        if (isNative) {
            return "(" + query.toString() + ")";
        }
        Query q = EM().createNativeQuery(query.toString()).setHint("toplink.refresh", "true");
        List<Vector> list;
        list = q.getResultList();
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder allCivils = new StringBuilder("");
        for (Vector row : list) {
            allCivils.append(" " + row.get(0).toString() + " ,");
        }
        allCivils = allCivils.replace(allCivils.length() - 1, allCivils.length(), "");
        return allCivils.toString();

    }


    /**
     * @author B.H [M.abdelsabour]
     * @since 10-07-2017
     * @param IBasicDTO basicDTO
     * @return IEmployeesDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID Used in Att :
     */
    public Long getCountEmpCodeNameForAttSpecialPermission(IBasicDTO basicDTO) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            StringBuilder ejbql = null;
            ejbql = new StringBuilder("select count(o.civil_Id) from hr_emp_employees o WHERE  1 = 1 ");
            if (employeeSearchDTO.getRealCivilId() != null) {
                ejbql.append(" and o.real_Civil_Id = ").append(employeeSearchDTO.getRealCivilId());

            }

            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" and o.real_Civil_Id IN ( Select kwt.civil_Id From INF_KWT_CITIZENS_RESIDENTS kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_Name , ' ' ) , CONCAT ( kwt.second_Name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_Name , ' ' ) , CONCAT ( kwt.last_Name , ' ' ) ) ) , kwt.family_Name )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");
            }

            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" and  o.min_Code = ").append(employeeSearchDTO.getMinistryCode());
            }
            //updated by A.Nour 02-23-2016 because it raise null pointer exception if security down
            else if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != null) {
                ejbql.append(" AND o.min_Code=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
            }
            ejbql.append(" and o.active_Flag =  ").append(IEMPConstants._EMP_ACTIVE_STATUS);
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.HIRSTATUS_CODE  IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");
            } else {
                ejbql.append(" AND o.HIRSTATUS_CODE IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                             IEMPConstants.EMP_STATUS_FREEZING + ")");
            }


            String civils = "";

            civils = getCivilsRelatedToWorkCenter(null, true, true);

            if (civils != null && !civils.isEmpty()) {
                String[] spltr = civils.split(",");
                StringBuilder temp;
                int indexer = 0, count = (int)Math.ceil((double)(spltr.length) / 1000);
                if (count == 1) {
                    ejbql.append(" and o.civil_Id in( " + civils + ")");
                } else {
                    while (count > 0) {
                        temp = new StringBuilder("");
                        for (int i = 0; i < 1000 && indexer < spltr.length; i++, indexer++) {
                            temp.append(spltr[indexer] + ",");
                        }
                        temp.replace(temp.length() - 1, temp.length(), "");
                        if (count == (int)Math.ceil((double)(spltr.length) / 1000))
                            ejbql.append(" and ( o.civil_Id in( " + temp + ")");
                        else
                            ejbql.append(" or o.civil_Id in( " + temp + ")");
                        count--;
                    }
                    ejbql.append(" ) ");
                }
            }

            System.out.println("getCountEmpCodeNameForAttSpecialPermission ejbql.toString() = " + ejbql.toString());
            Vector vector = (Vector)EM().createNativeQuery(ejbql.toString()).getSingleResult();
            if (vector != null && !vector.isEmpty()) {
                return new Long(((BigDecimal)vector.get(0)).intValue());
            }

            return 0L;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public List<IBasicDTO> getEmpCodeNameForAttSpecialPermission(IBasicDTO basicDTO,
                                                                 IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                      SharedApplicationException {
        try {
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;

            List<EmployeesEntity> list = null;
            StringBuilder ejbql = null;
            ejbql = new StringBuilder("select * from hr_emp_employees o WHERE  1 = 1 ");
            if (employeeSearchDTO.getRealCivilId() != null) {
                ejbql.append(" and o.real_Civil_Id = ").append(employeeSearchDTO.getRealCivilId());
            }

            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                ejbql.append(" and o.real_Civil_Id IN ( Select kwt.civil_Id From INF_KWT_CITIZENS_RESIDENTS kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.first_Name , ' ' ) , CONCAT ( kwt.second_Name , ' ' ) ) , CONCAT ( CONCAT ( kwt.third_Name , ' ' ) , CONCAT ( kwt.last_Name , ' ' ) ) ) , kwt.family_Name )",
                                                                                 employeeSearchDTO.getEmpName()) +
                             " ) ");
            }
            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" and  o.min_Code = ").append(employeeSearchDTO.getMinistryCode());
            }
            //updated by A.Nour 02-23-2016 because it raise null pointer exception if security down
            else if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != null) {
                ejbql.append(" AND o.min_Code=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
            }
            ejbql.append(" and o.active_Flag =  ").append(IEMPConstants._EMP_ACTIVE_STATUS);
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.HIRSTATUS_CODE  IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");
            } else {
                ejbql.append(" AND o.HIRSTATUS_CODE IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                             IEMPConstants.EMP_STATUS_FREEZING + ")");
            }


            String civils = "";
            civils = getCivilsRelatedToWorkCenter(null, true, true);

            if (civils != null && !civils.isEmpty()) {
                String[] spltr = civils.split(",");
                StringBuilder temp;
                int indexer = 0, count = (int)Math.ceil((double)(spltr.length) / 1000);
                if (count == 1) {
                    ejbql.append(" and o.civil_Id in( " + civils + ")");
                } else {
                    while (count > 0) {
                        temp = new StringBuilder("");
                        for (int i = 0; i < 1000 && indexer < spltr.length; i++, indexer++) {
                            temp.append(spltr[indexer] + ",");
                        }
                        temp.replace(temp.length() - 1, temp.length(), "");
                        if (count == (int)Math.ceil((double)(spltr.length) / 1000))
                            ejbql.append(" and ( o.civil_Id in( " + temp + ")");
                        else
                            ejbql.append(" or o.civil_Id in( " + temp + ")");
                        count--;
                    }
                    ejbql.append(" ) ");
                }
            }

            System.out.println("getEmpCodeNameForAttSpecialPermission :" + ejbql.toString());
            //TODO apply sorting
            if (requestDTO != null && requestDTO.getSortColumnList() != null &&
                requestDTO.getSortColumnList().size() > 0) {
                ejbql.append(" ORDER BY ");
                for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                    String column = (String)requestDTO.getSortColumnList().get(i);
                    ejbql.append(column);
                    if (!requestDTO.isSortAscending()) {
                        ejbql.append(" DESC");
                    }
                    if (i < requestDTO.getSortColumnList().size() - 1) {
                        ejbql.append(" , ");
                    }
                }
            }

            Query query = null;

            if (ejbql != null) {
                query = EM().createNativeQuery(ejbql.toString(), EmployeesEntity.class);
                if (requestDTO != null) {
                    query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                    query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
                }
                list = query.getResultList();

            }
            if (list == null || list.size() == 0)
                throw new NoResultException();


            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmployeesEntity entity : list) {
                listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
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


    /**
     * @author B.H [M.abdelsabour]
     * @since 20-07-2017
     * @param IBasicDTO basicDTO
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee by realcivilID
     */
    public IBasicDTO getEmpCodeNameByRealCivilIdForAtt(IBasicDTO basicDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
            EmployeesEntity employeesEntity = null;
            List<EmployeesEntity> list = null;
            StringBuilder ejbql = null;
            ejbql = new StringBuilder("select * from hr_emp_employees o WHERE  1 = 1 ");
            if (employeeSearchDTO.getRealCivilId() != null) {
                ejbql.append(" and o.REAL_CIVIL_ID = ").append(employeeSearchDTO.getRealCivilId());

            }
            if (employeeSearchDTO.getMinistryCode() != null) {
                ejbql.append(" and  o.min_Code = ").append(employeeSearchDTO.getMinistryCode());
            }
            //updated by A.Nour 02-23-2016 because it raise null pointer exception if security down
            else if (CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() != null) {
                ejbql.append(" AND o.min_Code=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
            }
            ejbql.append(" and o.active_Flag =  ").append(IEMPConstants._EMP_ACTIVE_STATUS);
            if (employeeSearchDTO.getEmpHireStatusList() != null) {
                StringBuilder statusCodeStr = new StringBuilder("");
                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
                    statusCodeStr.append(status + ",");
                }
                ejbql.append(" AND o.HIRSTATUS_CODE  IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) +
                             ") ");
            } else {
                ejbql.append(" AND o.HIRSTATUS_CODE IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                             IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + "," +
                             IEMPConstants.EMP_STATUS_FREEZING + ")");
            }


            String civils = "";
            //civils = getCivilsRelatedToWorkCenter(null, true, true);
            Long groupCode = checkExistByGroupCode();
            if(groupCode != null){
                //TODO
                IDfDTO dfDTO = CSCSecurityInfoHelper.getDfUserDTO();
                if (dfDTO != null) {
                    String wrkCodeIn = dfDTO.getDfInNative();
                    String wrkCodeNotIn = dfDTO.getDfNotInNative();
                    if (wrkCodeIn != null && wrkCodeIn.length() > 0) {
                        ejbql.append(" and o.WRK_CODE in(");
                        ejbql.append(wrkCodeIn);
                        ejbql.append(")");
                    }
                    if (wrkCodeNotIn != null && wrkCodeNotIn.length() > 0) {
                        ejbql.append(" and o.WRK_CODE Not in(");
                        ejbql.append(wrkCodeNotIn);
                        ejbql.append(")");
                    }
                }
            }else{
                civils = getCivilsRelatedToWorkCenter(null, true, true);
            }
            if (civils != null && !civils.isEmpty()) {
                String[] spltr = civils.split(",");
                StringBuilder temp;
                int indexer = 0, count = (int)Math.ceil((double)(spltr.length) / 1000);
                if (count == 1) {
                    ejbql.append(" and o.civil_Id in( " + civils + ")");
                } else {
                    while (count > 0) {
                        temp = new StringBuilder("");
                        for (int i = 0; i < 1000 && indexer < spltr.length; i++, indexer++) {
                            temp.append(spltr[indexer] + ",");
                        }
                        temp.replace(temp.length() - 1, temp.length(), "");
                        if (count == (int)Math.ceil((double)(spltr.length) / 1000))
                            ejbql.append(" and ( o.civil_Id in( " + temp + ")");
                        else
                            ejbql.append(" or o.civil_Id in( " + temp + ")");
                        count--;
                    }
                    ejbql.append(" ) ");
                }
            }
            /// end ///
            System.out.println("getEmpCodeNameByRealCivilId ejbql.toString() = " + ejbql.toString());
            list = EM().createNativeQuery(ejbql.toString(), EmployeesEntity.class).getResultList();
            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            } else {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOCodeName(employeesEntity);
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


    /**
     * @author B.H [M.abdelsabour]
     * @since 09-10-2017
     * @param Long realCivilId
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @note to get employee First HireDate by realcivilID
     */


    public Date getEmpFirstHireDate(Long realCivilId) throws DataBaseException, SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            ejbql = new StringBuilder(" ");
            ejbql.append(" SELECT MIN(FROM_DATE) ");
            ejbql.append(" FROM INF_KWT_WORK_DATA W ");
            ejbql.append(" WHERE CIVIL_ID = " + realCivilId);
            ejbql.append(" AND W.TRXTYPE_CODE NOT IN (80,90,280,290)  ");
            ejbql.append(" AND EXISTS (SELECT 1 FROM NL_ORG_MINISTRIES M , NL_ORG_CATS C WHERE C.CAT_CODE = M.CAT_CODE AND M.MIN_CODE = W.MIN_CODE AND C.GOV_FLAG = 1 )  ");

            Query query = EM().createNativeQuery(ejbql.toString());
            if (query.getSingleResult() != null) {
                Vector vector = (Vector)query.getSingleResult();
                Timestamp stamp = (Timestamp)vector.get(0);
                Date date = new Date(stamp.getTime());
                return date;
            } else {
                return null;
            }
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
     * added By M.abdelsabour
     * addTestEvalForNewEmp
     * @param Long minCode
     * @param Long civilId
     * @param String workCode
     * @param Date hireDate
     * @return Long
     * @throws DataBaseException
     * @throws SharedApplicationException
     */

    public Long addTestEvalForNewEmp(Long minCode, Long civilId, String workCode,
                                     Date hireDate) throws DataBaseException, SharedApplicationException {
        CallableStatement stm = null;
        try {

            StringBuilder queryString = new StringBuilder();
            Connection con = getConnectionForUpdate();
            queryString.append("  DECLARE  ");
            queryString.append("  V_OUT number :=0 ;");

            queryString.append("  BEGIN  ");


            queryString.append(" V_OUT :=  HR_APR_SETUP_PACK.ADD_TEST_EVAL_FOR_NEW_EMP (   ");

            queryString.append(minCode + " , ");
            queryString.append(civilId + " , ");
            queryString.append(" '" + workCode + "'  ");
            queryString.append(" , TO_DATE ( '" + hireDate + "' , 'yyyy/mm/dd' )  ");
            queryString.append(" ) ; ");
           // queryString.append(" select V_OUT from dual ;  ");
            queryString.append("   ? := V_OUT; ");
            queryString.append(" end ;  ");

            System.out.println("query  " + queryString.toString());

            stm = con.prepareCall(queryString.toString());

            stm.registerOutParameter(1, Types.INTEGER);

            stm.execute();

            System.out.println(" HR_APR_SETUP_PACK.ADD_TEST_EVAL_FOR_NEW_EMP result " + stm.getLong(1));

            return stm.getLong(1);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
            }finally{
                BaseDAO.close(stm);
            }


    }

    /* CSC-23011 by A.Nour 2018-05-09  */
    public IEmployeesDTO getEmployeesByRealCivilIdAndMinForBankWebService(Long realCivilId,
                                                                          Long minCode , String hireStatusListCommaSeparated) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            List<EmployeesEntity> list = null;
            String hireStatus = (hireStatusListCommaSeparated == null)? "1" : hireStatusListCommaSeparated;//EmpUtils.getStatusForHireWithoutDELEGATION();//"1,4,13"
            try {
                StringBuilder queryStr =
                    new StringBuilder("select o from EmployeesEntity o WHERE o.realCivilId = :realCivilId AND (:minCode IS NULL OR o.minCode = :minCode) ");
                queryStr.append(" AND o.activeFlag = :activeFlag ");
                queryStr.append(" AND o.hireStatusEntity.hirstatusCode IN (" + hireStatus + ")   ");

                Query query = EM().createQuery(queryStr.toString());

                query.setParameter("realCivilId", realCivilId);
                query.setParameter("minCode", minCode);
                query.setParameter("activeFlag", IEMPConstants._EMP_ACTIVE_STATUS);
                query.setHint("toplink.refresh", "true");
                list = query.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ItemNotFoundException();
            }
            if (list == null || list.isEmpty()) {
                throw new ItemNotFoundException();
            }

            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOForEmpHelper(list.get(0));

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




    public String getEmpCodeNameForContractEmp(Long  civilID, Long minCode) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            ejbql = new StringBuilder("SELECT  KWT.FIRST_NAME ||' '|| KWT.SECOND_NAME ||' '||KWT.THIRD_NAME||' '||KWT.LAST_NAME||' '||KWT.FAMILY_NAME  from HR_SAL_EMP_SALARY_ELEMENTS SAL , HR_SAL_ELEMENT_GUIDES ELM ,hr_emp_employees o ,INF_KWT_CITIZENS_RESIDENTS KWT");
                ejbql.append(" where ELM.SAL_BY_DECESION_FLAG= ").append(IEMPConstants._EMP_ACTIVE_STATUS);



            ejbql.append(" and SAL.ELMGUIDE_CODE = ELM.ELMGUIDE_CODE  and O.CIVIL_ID= SAL.civil_id and KWT.CIVIL_ID=O.REAL_CIVIL_ID AND SAL.MIN_CODE =").append(minCode);
            ejbql.append(" and o.real_Civil_Id = ").append(civilID);
            ejbql.append(" AND o.HIRSTATUS_CODE IN (" + IEMPConstant.EMP_STATUS_EMPLOYMENT + "," +
                         IEMPConstant.EMP_STATUS_DELEGATION_OUT_TO + "," + IEMPConstant.EMP_STATUS_LOANINIG + ")");
            ejbql.append(" and o.active_Flag =  ").append(IEMPConstants._EMP_ACTIVE_STATUS);


            System.out.println("getEmpCodeNameForContractEmp >>>>>>>> " + ejbql.toString());
             List<Vector>  list = EM().createNativeQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0) {
               return null;
            }
            else{
                return (String)list.get(0).toString().subSequence(1, list.get(0).toString().length() -1);
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IBasicDTO getEmpCurrentJob(Long  realCivilID) throws DataBaseException,
                                                                                              SharedApplicationException {
        IBasicDTO jobDto=null;
        try {
            if (realCivilID!=null){

                StringBuilder ejbql = null;
                ejbql = new StringBuilder("SELECT X.JOB_CODE,Y.JOB_NAME\n" );
                ejbql.append("  FROM HR_EMP_EMPLOYEES x, NL_job_jobs y\n" );
                ejbql.append(" WHERE     \n" );
                ejbql.append("       X.REAL_CIVIL_ID="+realCivilID+"\n" );
                ejbql.append("       AND X.ACTIVE_FLAG = 1        \n" );
                ejbql.append("       AND X.HIRSTATUS_CODE = 1\n" );
                ejbql.append("       AND X.HIRSTAGE_CODE = 3\n" );
                ejbql.append("       AND X.RECORDDESC_CODE = 1\n" );
                ejbql.append("       AND X.JOB_CODE=Y.JOB_CODE");
                Vector result = (Vector)EM().createNativeQuery(ejbql.toString()).getSingleResult();
                if (result != null || result.size() > 0) {
                    jobDto=new BasicDTO();
                    jobDto.setCode(new EntityKey(result.get(0)) );
                    jobDto.setName((String)result.get(1));
                }
                return jobDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
//            e = wrapIntoDataBaseException(e);
//            if (e instanceof DataBaseException) {
//                throw (DataBaseException)e;
//            } else {
//                throw (SharedApplicationException)e;
//            }
        }
        return jobDto;
    }
    public Long checkIfEmployeeExistsCount(Long realCivilId) throws DataBaseException, SharedApplicationException {
      Long countResult = 1L;
        try {

            StringBuilder queryStr = new StringBuilder(" SELECT COUNT(1) FROM HR_EMP_EMPLOYEES E Where E.ACTIVE_FLAG = 1 AND E.HIRSTATUS_CODE IN (1,13) AND E.REAL_CIVIL_ID =  ");
            queryStr.append(realCivilId);

            System.out.println("checkIfEmployeeExistsCount >>>>>>>>>>> "+ queryStr.toString());
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
    public List<IEmployeesDTO> checkIfEmployeeExists(Long realCivilId) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            Long activeFlag = 1L;
            List<IEmployeesDTO> employeesDTOList =
                EM().createNamedQuery("EmployeesEntity.CheckIfEmployeeExists").setParameter("realCivilId",
                                                                                            realCivilId).setParameter("s1",
                                                                                                                      1L).setParameter("s2",
                                                                                                                                       13L).setParameter("activeFlag",
                                                                                                                                                         activeFlag).setHint("toplink.refresh",
                                                                                                                                                                             "true").getResultList();

            return employeesDTOList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public boolean updateNextRaiseDate(Date nextRaiseDate , String civilId) throws DataBaseException,
                                                                           SharedApplicationException {


        Connection con = getConnectionForUpdate();
        try {


            StringBuilder sqlQuery = new StringBuilder("");
            sqlQuery.append("update HR_EMP_EMPLOYEES set DATE_OF_NEXT_RAISE = to_date('");

            sqlQuery.append(nextRaiseDate.toString()+"' , 'yyyy-mm-dd')");
            sqlQuery.append(" where CIVIL_ID = ");
            sqlQuery.append(civilId);
            PreparedStatement ps = con.prepareCall(sqlQuery.toString());
            ps.executeUpdate();
            ps.close();


            return true;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

        public IEmployeesDTO searchByCivilIdEstana(IBasicDTO basicDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
            try {
                EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
                EmployeesEntity employeesEntity = null;
                List<EmployeesEntity> list = null;
                StringBuilder ejbql = null;
                ejbql = new StringBuilder("SELECT E.* FROM HR_SAL_EMP_SALARY_ELEMENTS S , HR_EMP_EMPLOYEES E WHERE S.CIVIL_ID = E.CIVIL_ID AND E.ACTIVE_FLAG = 1 AND E.HIRSTATUS_CODE IN (1,13) AND E.REAL_CIVIL_ID = '").append(employeeSearchDTO.getRealCivilId()).append("'");
                ejbql.append(" and  E.min_Code = ").append(employeeSearchDTO.getMinistryCode());
                ejbql.append(" AND EXISTS(SELECT 1 FROM HR_SAL_GUIDE_EXTRA_DATA X WHERE EXTDATTYPE_CODE = 7  AND X.ELMGUIDE_CODE = S.ELMGUIDE_CODE)  ");




                System.out.println("searchByCivilIdEstana ejbql.toString() = " + ejbql.toString());
                list = EM().createNativeQuery(ejbql.toString(),EmployeesEntity.class).getResultList();
                if (list != null && list.size() > 0) {
                    employeesEntity = list.get(0);
                } else {
                    throw new ItemNotFoundException();
                }
                IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOCodeName(employeesEntity);
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

    public Long checkExistByGroupCode()  {
        Long groupCode = CSCSecurityInfoHelper.getGroupCodeFromRequest();
        Long value=null;
        try{
            StringBuilder ejbql =
                new StringBuilder(" select group_Code from gn_sec_groups where GROUP_FIRST_PARENT<>91 and group_code=").append(groupCode);
            System.out.println(" queryString>>>>>>>>>>>>> " + ejbql.toString());
            Query query = EM().createNativeQuery(ejbql.toString());
            Vector row = (Vector)query.getSingleResult();
            value  = Long.valueOf(row.get(0).toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        return value;
    }

    /* get hired hemployee in All ministries For Adc with
    * real_Civil_ID
    * active_flag=1
    */

       public IEmployeesDTO getByRealCivilIdAllMinistriesForAdc(Long realCivilId) throws DataBaseException,
                                                                                   SharedApplicationException {
           try {
               EmployeesEntity employeesEntity = null;
               List<EmployeesEntity> list = null;
               try {
                   list =
    EM().createNamedQuery("EmployeesEntity.getByRealCivilIdAllMinistriesForAdc").setHint("toplink.refresh",
                                                                                  "true").setParameter("realCivilId",
                                                                                                       realCivilId).getResultList();
               } catch (Exception e) {
                   e.printStackTrace();
                   throw new ItemNotFoundException();
               }

               if (list != null && list.size() > 0) {
                   employeesEntity = list.get(0);
               }

               if (employeesEntity == null) {
                   throw new ItemNotFoundException();
               }
               IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO(employeesEntity);
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

       public Long checkWCHaveSubWC(String workCode, Long minCode) throws DataBaseException,
                                                                                SharedApplicationException {
           Long countResult = 0L;
             try {

                 StringBuilder queryStr = new StringBuilder(" SELECT COUNT(1) FROM NL_ORG_WORK_CENTERS C Where min_code = ");
                 queryStr.append(minCode).append(" and center_status = 9");
                 queryStr.append(" connect by prior MIN_CODE =PARENT_MIN and prior wrk_code =PARENT_WRK ");
                 queryStr.append(" start with wrk_code ='").append(workCode).append("'");
                 System.out.println("checkIfWCHaveSWC >>>>>>>>>>> "+ queryStr.toString());
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

    public IBasicDTO getSubWCCodeName(Long realCivilId, Long minCode) throws DataBaseException,
                                                                             SharedApplicationException {
        IBasicDTO dto = new BasicDTO();
          try {

              StringBuilder queryStr = new StringBuilder("SELECT E.VALUE ,WC.WRK_NAME from HR_EMP_EMPLOYEE_EXTRA_DATA E , NL_ORG_WORK_CENTERS WC ");
              queryStr.append(" where WC.WRK_CODE = E.VALUE");
              queryStr.append(" and E.R_CIVIL_ID = ").append(realCivilId);
              queryStr.append(" and E.MIN_CODE =").append(minCode);
              queryStr.append(" and EXTDATTYPE_CODE =346 and UNTIL_DATE is null ");
              System.out.println("checkIfWCHaveSWC >>>>>>>>>>> "+ queryStr.toString());
              Query query = EM().createNativeQuery(queryStr.toString());
              Vector vector = (Vector)query.getResultList().get(0);
              if(vector != null){
                  if (vector.get(0) != null) {
                      IEntityKey ek = new EntityKey(vector.get(0).toString());
                      dto.setCode(ek) ;
                  }
                  if (vector.get(1) != null) {
                      dto.setName(vector.get(1).toString()) ;
                  }
              }
              return dto;

          } catch (Exception e) {
              e = wrapIntoDataBaseException(e);
              if (e instanceof DataBaseException) {
                  throw (DataBaseException)e;
              } else {
                  throw (SharedApplicationException)e;
              }
          }
    }



    public Long getSearchEmpCount(IBasicDTO basicDTO) throws DataBaseException,
                                                                             SharedApplicationException {

        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
          Long count =0l;
          try {

              StringBuilder queryStr = new StringBuilder("SELECT count(o.CIVIL_ID)   FROM HR_EMP_EMPLOYEES o WHERE O.CIVIL_ID =O.CIVIL_ID ");
              if (employeeSearchDTO.getCivilId() != null){
                  queryStr.append(" AND  o.REAL_CIVIL_ID = '" + employeeSearchDTO.getCivilId() + "'");
              }
              if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
              queryStr.append("AND O.REAL_CIVIL_ID IN    (SELECT KWT.CIVIL_ID     FROM INF_KWT_CITIZENS_RESIDENTS  kwt      WHERE (CONCAT (  CONCAT ( CONCAT (CONCAT (KWT.FIRST_NAME, ' '), CONCAT (KWT.SECOND_NAME, ' ')), CONCAT (CONCAT (KWT.THIRD_NAME, ' '),  CONCAT (KWT.LAST_NAME, ' '))),   KWT.FAMILY_NAME) LIKE  '%");
              queryStr.append(employeeSearchDTO.getEmpName()+"%' ))");
                      }
              queryStr.append("AND O.HIRSTATUS_CODE IN (1, 4, 11, 13) AND O.ACTIVE_FLAG = 1 AND O.RECORDDESC_CODE = 1 AND O.MIN_CODE = "+CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest()+" And  FINANCIAL_PACKAGE.GET_EMP_CADR(O.REAL_CIVIL_ID ,O.MIN_CODE) <> 348");


              System.out.println("getSearchEmpCount >>>>>>>>>>> "+ queryStr.toString());
              Query query = EM().createNativeQuery(queryStr.toString());
              Vector vector = (Vector)query.getResultList().get(0);
              if(vector != null){
                  if (vector.get(0) != null) {
                      count= Long.parseLong(vector.get(0).toString()) ;
                  }

              }
              return count;

          } catch (Exception e) {
              e = wrapIntoDataBaseException(e);
              if (e instanceof DataBaseException) {
                  throw (DataBaseException)e;
              } else {
                  throw (SharedApplicationException)e;
              }
          }
    }

    public List<EmployeesEntity> getSearchEmp(IBasicDTO basicDTO,  IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                             SharedApplicationException {
        List<EmployeesEntity> list=null;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
          Long count =0l;              Query query = null;
          try {

              StringBuilder queryStr = new StringBuilder("SELECT *   FROM HR_EMP_EMPLOYEES o WHERE O.CIVIL_ID =O.CIVIL_ID ");
              if (employeeSearchDTO.getCivilId() != null){
                  queryStr.append(" AND  o.REAL_CIVIL_ID = '" + employeeSearchDTO.getCivilId() + "'");
              }
              if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
              queryStr.append(" AND  O.REAL_CIVIL_ID IN    (SELECT KWT.CIVIL_ID     FROM INF_KWT_CITIZENS_RESIDENTS  kwt      WHERE (CONCAT (  CONCAT ( CONCAT (CONCAT (KWT.FIRST_NAME, ' '), CONCAT (KWT.SECOND_NAME, ' ')), CONCAT (CONCAT (KWT.THIRD_NAME, ' '),  CONCAT (KWT.LAST_NAME, ' '))),   KWT.FAMILY_NAME) LIKE  '%");
              queryStr.append(employeeSearchDTO.getEmpName()+"%' ))");
                      }
              queryStr.append(" AND O.HIRSTATUS_CODE IN (1, 11) AND O.ACTIVE_FLAG = 1 and (SELECT CSNL_OWNER.HR_JOB_INTERFACE_PACK.GET_CAT_CODE(J.CAT_CODE, 2) FROM CSNL_OWNER.NL_JOB_JOBS J WHERE J.JOB_CODE=E.JOB_CODE)=131");

              if (requestDTO != null && requestDTO.getSortColumnList() != null &&  requestDTO.getSortColumnList().size() > 0) {
                          queryStr.append(" ORDER BY ");
                          for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                              String column = (String)requestDTO.getSortColumnList().get(i);
                              queryStr.append(column);
                              if (!requestDTO.isSortAscending()) {
                                  queryStr.append(" DESC");
                              }
                              if (i < requestDTO.getSortColumnList().size() - 1) {
                                  queryStr.append(" , ");
                              }
                          }
                 }
                     if (queryStr != null) {
                         query = EM().createNativeQuery(queryStr.toString());
                         if (requestDTO != null) {
                             query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                             query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
                         }
                         System.out.println("getSearchEmp ==> "+queryStr.toString());
                         list = EM().createNativeQuery(queryStr.toString(),EmployeesEntity.class).getResultList();

                     }
                     if (list == null || list.size() == 0)
                         throw new NoResultException();


//              for (EmployeesEntity entity : list) {
//                  listDTO.add(EmpDTOFactory.createEmployeesDTO(entity));
//              }
              return list;

          } catch (Exception e) {
              e = wrapIntoDataBaseException(e);
              if (e instanceof DataBaseException) {
                  throw (DataBaseException)e;
              } else {
                  throw (SharedApplicationException)e;
              }
          }
    }



    public EmpStatusForSalDTO getEmpStatusAndHireOrEndServiceDateForSal(long realCivilId) throws DataBaseException,
                                                                            SharedApplicationException {
        EmpStatusForSalDTO dto=new EmpStatusForSalDTO();
          try {

            Connection connection = getConnectionForUpdate(); //session.getAccessor().getConnection();
            String nativQuery = "{ call fin_add_package.get_employee_details(?,?,?) }";
            CallableStatement cstmt = connection.prepareCall(nativQuery);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);
            cstmt.setLong(1, realCivilId);
            cstmt.setLong(2, CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());

            cstmt.execute();
            ResultSet resultSet = (ResultSet) cstmt.getObject(3);
            while (resultSet.next()) {
                dto.setEmpStatus(resultSet.getString("HIRE_STATUS"));
                dto.setEmpqurDate(resultSet.getDate("QRY_DATE"));
               System.out.println("HIRE_STATUS ==> "+resultSet.getString("HIRE_STATUS"));
                System.out.println("QRY_DATE ==> "+resultSet.getDate("QRY_DATE"));

                     }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
              }

        return dto;
    }

    public Long simpleSearchCountBasicWithPagingForLeaders(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        return buildSearchCountQueryWithPagingForLeaders(basicDTO);
    }
    
    private Long buildSearchCountQueryWithPagingForLeaders(IBasicDTO basicDTO) throws DataBaseException,
                                                                            SharedApplicationException {

        StringBuilder ejbql = null;
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
        if(employeeSearchDTO.getKaderCode()!= null && employeeSearchDTO.getKaderCode().equals(348l)){
            return  getSearchEmpCount(basicDTO);
        }else{

            //        if (employeeSearchDTO.getMinistryCode() != null) {
            //            if (employeeSearchDTO.getMinistryCode() == -100L) {
            //            } else {
            //                employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
            //            }
            //        } else {
            //            employeeSearchDTO.setMinistryCode(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
            //        }
            ejbql = new StringBuilder("select count(o.civilId) from EmployeesEntity o WHERE o.civilId=o.civilId");
            if (employeeSearchDTO.getCivilId() != null)
                ejbql.append(" AND  o.realCivilId = '" + employeeSearchDTO.getCivilId() + "'");
            if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
                //            ejbql.append(" AND o.civilId IN (Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
                //                         "CONCAT(CONCAT(CONCAT(CONCAT(kwt.firstName,' '),CONCAT(kwt.secondName,' ')),CONCAT(CONCAT(kwt.thirdName,' '),CONCAT(kwt.lastName,' '))),kwt.familyName) LIKE '" +
                //                         employeeSearchDTO.getEmpName() + "')");
                ejbql.append(" AND o.realCivilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE " +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 employeeSearchDTO.getEmpName()) + " ) ");

            }
            if (employeeSearchDTO.getWorkCenterCode() != null && !employeeSearchDTO.getWorkCenterCode().equals("")) {
                String[] str = employeeSearchDTO.getWorkCenterCode().split("\\*");
                ejbql.append(" AND o.minCode=" + Long.parseLong(str[0]) + " AND o.wrkCode='" + str[1] + "'");
            }
            if (employeeSearchDTO.getWorkCenterName() != null && !employeeSearchDTO.getWorkCenterName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.workCentersEntity.wrkName LIKE '" + employeeSearchDTO.getWorkCenterName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.workCentersEntity.wrkName", employeeSearchDTO.getWorkCenterName()) +
                             " ) ");

            if (employeeSearchDTO.getStartWorkingDate() != null)
                ejbql.append(" AND o.startWorkingDate='" + employeeSearchDTO.getStartWorkingDate() + "'");
            if (employeeSearchDTO.getEmpHireTypes() != null &&
                !employeeSearchDTO.getEmpHireTypes().equals(ISystemConstant.VIRTUAL_VALUE)) {
                ejbql.append(" AND (o.hireTypesEntity.hirtypeCode=" + employeeSearchDTO.getEmpHireTypes() + "");
                ejbql.append(" OR o.hireTypesEntity.parentHireTypeCode=" + employeeSearchDTO.getEmpHireTypes() + ")");
            }
            //        else{
            //                ejbql.append(" AND (o.hireTypesEntity.parentHireTypeCode in (2,3,4) OR o.hireTypesEntity.hirtypeCode in (2,3,4))");
            //            }
            if (employeeSearchDTO.getJobName() != null && !employeeSearchDTO.getJobName().equals(""))

                //By MLotfy new search
                //ejbql.append(" AND o.jobsEntity.jobName LIKE '" + employeeSearchDTO.getJobName() + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.jobsEntity.jobName", employeeSearchDTO.getJobName()) +
                             " ) ");

            if (employeeSearchDTO.getEmpHireStage() != null &&
                !employeeSearchDTO.getEmpHireStage().equals(ISystemConstant.VIRTUAL_VALUE))
                ejbql.append(" AND o.hirstageCode=" + employeeSearchDTO.getEmpHireStage() + "");
            if (employeeSearchDTO.isHirestageNotEqualCanceldOrEmployment() == true)
                ejbql.append(" AND (o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_EMPLOYMENT +
                             " AND o.hireStagesEntity.hirstageCode<>" + IEMPConstant.EMP_STAGE_CANCEL_NOMINATION + ")");
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
    //            if (employeeSearchDTO.getEmpHireStatusList() != null) {
    //                StringBuilder statusCodeStr = new StringBuilder("");
    //                for (Long status : employeeSearchDTO.getEmpHireStatusList()) {
    //                    statusCodeStr.append(status + ",");
    //                }
    //                ejbql.append(" AND o.hirstatusCode IN (" + statusCodeStr.substring(0, statusCodeStr.length() - 1) + ") ");
    //
    //            } else {
    //                if (employeeSearchDTO.getEmpHireStatus() != null && !employeeSearchDTO.isEmployment())
    //                    ejbql.append(" AND o.hirstatusCode=" + employeeSearchDTO.getEmpHireStatus() + "");
    //                //uncommented by Tech.team[H.Omar & m.sayed] according to integration team request 2-12-2015
    //                if (employeeSearchDTO.isEmployment())
    //                    ejbql.append(" AND o.hirstatusCode IN (" + EmpUtils.getStatusForHire() + " )");
    //            }
            //added by tech.Team[m.sayed] at 2-12-2015         CSC-9091
            ejbql.append(" AND o.hirstatusCode in (1,11)");
            ejbql.append(" AND o.activeFlag=" + ISystemConstant.ACTIVE_FLAG);
            
           
            if (employeeSearchDTO.getRecordDescCode() != null) {
                ejbql.append(" AND o.recordDescCode=" + employeeSearchDTO.getRecordDescCode().toString() + "");
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
                if(employeeSearchDTO.isInAllMinistries()){
                            // APPEND NOTHING ---to get employees regardless the min code
                }else if (employeeSearchDTO.getMinistryCode() != null && employeeSearchDTO.getMinistryCode() != -100L) {
                ejbql.append(" and o.minCode = " + employeeSearchDTO.getMinistryCode() + "");
            }
            //added by Tech.Team [m.sayed] According to Integration Team Request 2-12-2015
            //edited by Hany Omar [B.Horse]
            else {
                ejbql.append(" AND o.minCode=" + CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest() + "");
            }

            /// commented By M.abdelsabour to apply new DataFilteration Sol
            //        //added by Technical Team [m.sayed] at 31-3-2016
            //        // stroy ID CSC-17343  work Center data filter
            //        String wrkcode = initWrkcenterTree();
            //        if (wrkcode != null && !wrkcode.isEmpty()) {
            //            ejbql.append(" and o.wrkCode in (" + wrkcode + ")");
            //        }
            // Added By MLotfy, apply data level sequrity
            String excludedWorkCentersStr = getExcludedWorkCentersAsCommaSeparatedStr();
            if (excludedWorkCentersStr != null) {
                ejbql.append(excludedWorkCentersStr);
            }

             ejbql.append(" and  o.recordDescCode =1 ");
            System.out.println("EmployeesDAO.buildSearchCountQueryWithPaging :: " + ejbql.toString());


            Query query = EM().createQuery(ejbql.toString());

            return (Long)query.getSingleResult();
    
        }


    }
    
    public List<IBasicDTO> simpleSearchBasicWithPagingForLeaders(IBasicDTO basicDTO,
                                                       IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                            SharedApplicationException {

        List<EmployeesEntity> list = null;
        list = buildSearchQueryWithPagingForLeaders(basicDTO, requestDTO);
             System.out.println(" buildSearchQueryWithPagingForLeaders size"+list.size());
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmployeesEntity entity : list) {
            listDTO.add(EmpEntityConverter.getEmployeesDTOCodeName(entity));
        }
        return listDTO;

    }
    
    private List<EmployeesEntity> buildSearchQueryWithPagingForLeaders(IBasicDTO basicDTO,
                                                             IPagingRequestDTO requestDTO) throws DataBaseException,
                                                                                                  SharedApplicationException {

        List<EmployeesEntity> list=null;
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
          Long count =0l;           
          Query query = null;
          try {
              
              StringBuilder queryStr = new StringBuilder("SELECT *   FROM HR_EMP_EMPLOYEES o WHERE O.CIVIL_ID =O.CIVIL_ID ");
              if (employeeSearchDTO.getCivilId() != null){
                  queryStr.append(" AND  o.REAL_CIVIL_ID = '" + employeeSearchDTO.getCivilId() + "'");
              } 
              if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
              queryStr.append(" AND  O.REAL_CIVIL_ID IN    (SELECT KWT.CIVIL_ID     FROM INF_KWT_CITIZENS_RESIDENTS  kwt      WHERE (CONCAT (  CONCAT ( CONCAT (CONCAT (KWT.FIRST_NAME, ' '), CONCAT (KWT.SECOND_NAME, ' ')), CONCAT (CONCAT (KWT.THIRD_NAME, ' '),  CONCAT (KWT.LAST_NAME, ' '))),   KWT.FAMILY_NAME) LIKE  '%");
              queryStr.append(employeeSearchDTO.getEmpName()+"%' ))");
                      }
              queryStr.append(" AND O.HIRSTATUS_CODE IN (1, 11) AND O.ACTIVE_FLAG = 1 and (SELECT CSNL_OWNER.HR_JOB_INTERFACE_PACK.GET_CAT_CODE(J.CAT_CODE, 2) FROM CSNL_OWNER.NL_JOB_JOBS J WHERE J.JOB_CODE=O.JOB_CODE)=131");
              queryStr.append(" AND O.MIN_CODE =");
              queryStr.append(employeeSearchDTO.getMinistryCode());
              System.out.println("getSearchEmpforleader ==> "+queryStr.toString());
              if (requestDTO != null && requestDTO.getSortColumnList() != null &&  requestDTO.getSortColumnList().size() > 0) {
                          queryStr.append(" ORDER BY ");
                          for (int i = 0; i < requestDTO.getSortColumnList().size(); i++) {
                              String column = (String)requestDTO.getSortColumnList().get(i);
                              queryStr.append(column);
                              if (!requestDTO.isSortAscending()) {
                                  queryStr.append(" DESC");
                              }
                              if (i < requestDTO.getSortColumnList().size() - 1) {
                                  queryStr.append(" , ");
                              }
                          }
                 }
                     if (queryStr != null) {
                         query = EM().createNativeQuery(queryStr.toString());
                         if (requestDTO != null) {
                             query.setFirstResult(requestDTO.getFirstRowNumber().intValue());
                             query.setMaxResults(requestDTO.getMaxNoOfRecords().intValue());
                         }
                         System.out.println("getSearchEmpforleader2 ==> "+queryStr.toString());
                         list = EM().createNativeQuery(queryStr.toString(),EmployeesEntity.class).getResultList();

                     }
                     if (list == null || list.size() == 0)
                         throw new NoResultException();
              
              return list;

          } catch (Exception e) {
              e = wrapIntoDataBaseException(e);
              if (e instanceof DataBaseException) {
                  throw (DataBaseException)e;
              } else {
                  throw (SharedApplicationException)e;
              }
          }
         

    }
    public IEmployeesDTO getEmpCodeNameByRealCivilIdForLeaders(EmpEmployeeSearchDTO basicDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        EmployeesEntity employeesEntity = null;
        List<EmployeesEntity> list = null;
            EmpEmployeeSearchDTO employeeSearchDTO = (EmpEmployeeSearchDTO)basicDTO;
              try {

                  StringBuilder queryStr = new StringBuilder("SELECT *   FROM HR_EMP_EMPLOYEES o WHERE O.CIVIL_ID =O.CIVIL_ID ");
                  if (employeeSearchDTO.getRealCivilId() != null){
                      queryStr.append(" AND  o.REAL_CIVIL_ID = '" + employeeSearchDTO.getRealCivilId() + "'");
                  }
//                  if (employeeSearchDTO.getEmpName() != null && !employeeSearchDTO.getEmpName().equals("")) {
//                  queryStr.append(" AND  O.REAL_CIVIL_ID IN    (SELECT KWT.CIVIL_ID     FROM INF_KWT_CITIZENS_RESIDENTS  kwt      WHERE (CONCAT (  CONCAT ( CONCAT (CONCAT (KWT.FIRST_NAME, ' '), CONCAT (KWT.SECOND_NAME, ' ')), CONCAT (CONCAT (KWT.THIRD_NAME, ' '),  CONCAT (KWT.LAST_NAME, ' '))),   KWT.FAMILY_NAME) LIKE  '%");
//                  queryStr.append(employeeSearchDTO.getEmpName()+"%' ))");
//                          }
                  queryStr.append(" AND O.HIRSTATUS_CODE IN (1, 11) AND O.ACTIVE_FLAG = 1 and (SELECT CSNL_OWNER.HR_JOB_INTERFACE_PACK.GET_CAT_CODE(J.CAT_CODE, 2) FROM CSNL_OWNER.NL_JOB_JOBS J WHERE J.JOB_CODE=O.JOB_CODE)=131");
                  queryStr.append(" AND O.MIN_CODE =");
                  queryStr.append(employeeSearchDTO.getMinistryCode());
            /// end ///
            System.out.println("getcodenameforleader ejbql.toString() = " + queryStr.toString());
            list = EM().createNativeQuery(queryStr.toString(), EmployeesEntity.class).getResultList();
            if (list != null && list.size() > 0) {
                employeesEntity = list.get(0);
            } else {
                throw new ItemNotFoundException();
            }
            IEmployeesDTO employeesDTO = EmpEntityConverter.getEmployeesDTOCodeName(employeesEntity);
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

    public IEmployeesDTO getByCivilIdForPRM(Long civilId) throws DataBaseException, SharedApplicationException {
        IEmployeesDTO empDTO = EmpDTOFactory.createEmployeesDTO();
        try {

            List<EmployeesEntity> list = null;
            StringBuilder queryStr = new StringBuilder("select o from EmployeesEntity o ");
            if (civilId != null) {
                queryStr.append(" WHERE  o.civilId= '" + civilId + "'");
            }
            System.out.println("getByRealCivilForPRM :: " + queryStr.toString());
            list = EM().createQuery(queryStr.toString()).getResultList();
            if(list != null && list.size() >0){
                empDTO = EmpEntityConverter.getEmployeesDTOForPRM(list.get(0));
            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return empDTO;
    }

/**
 *@author msayed
 * @since 14-5-2020
 * @get data for mobile webserivce
 * @param realCivilID
 * @return
 * @throws DataBaseException
 * @throws SharedApplicationException
 */
public IEmployeeDTOService getAllEmployeeDataForProfileMob(Long  realCivilID) throws DataBaseException, SharedApplicationException {
    try {

        StringBuilder queryStr = new StringBuilder("Select * from (SELECT GET_NAME (E.REAL_CIVIL_ID) EMP_NAME,");
        queryStr.append( "       E.REAL_CIVIL_ID," );
         queryStr.append( "      R.RELIGION_NAME ,");
         queryStr.append( "       HR_INF_INTERFACE_PACK.GET_GENDER_DESC (I.GENTYPE_CODE) GENDER_DESC,");
         queryStr.append( "  HR_INF_INTERFACE_PACK.GET_NAT_DESC (I.NATIONALITY, I.GENTYPE_CODE)    NAT_DESC,");
         queryStr.append( "  M.MARSTATUS_NAME," );
        queryStr.append( "to_char( I.BIRTH_DATE,'dd/MM/yyyy'), " );
        queryStr.append( " GET_LK_DESC ('NL_QUL_QUALIFICATIONS',");
        queryStr.append( "  'QUALIFICATION_KEY',");
         queryStr.append( " 'QUALIFICATION_NAME',");
         queryStr.append( " HR_EMP_PAC.GET_EMP_LAST_QUL (E.REAL_CIVIL_ID))");
         queryStr.append( " QUAL_DESC,");
         queryStr.append( "to_char( HR_EMP_PAC.GET_EMP_QUAL_DATE (E.REAL_CIVIL_ID),'dd/MM/yyyy') HR_QUAL_DATE,");
         queryStr.append( "  I.MOBILE_NO,   ");
        queryStr.append( "    I.E_MAIL,");
        //queryStr.append( "  DECODE(I.SEND_SMS_FLAG,0,'لا يتم استقبال رسائل',1,'يتم استقبال رسائل') SEND_SMS_FLAG,");
        queryStr.append( "  I.SEND_SMS_FLAG,");
        queryStr.append( " I.BUILDING_NO,");
       queryStr.append( " I.FLOOR_NO,");
       queryStr.append( " I.FLAT_NO,");
       queryStr.append( " P.MAP_NAME cityName, ");
       queryStr.append( "     (SELECT X.MAP_NAME");
        queryStr.append( "      FROM INF_KW_MAP X ");
       queryStr.append( "  WHERE X.MAP_CODE = P.FIRST_PARENT) Gov_Name");
        queryStr.append(" ,min_name,wrk_Name ,MINISTRY_FILE_NO ");
        queryStr.append(" ,to_char(hire_Date,'dd/MM/yyyy') ,to_char(E.START_WORKING_DATE,'dd/MM/yyyy')");
        queryStr.append(" ,to_char(E.DATE_OF_NEXT_RAISE,'dd/MM/yyyy'),job_name  ");
        //queryStr.append("  to_char(D.DEGREE_DATE,'dd/MM/yyyy'),G.EMLGUIDE_NAME DEGREE_NAME ");
        queryStr.append( " FROM HR_EMP_EMPLOYEES E,");
        queryStr.append( "    INF_KWT_CITIZENS_RESIDENTS I, INF_RELIGIONS R , INF_MARITAL_STATUS M , INF_KW_MAP P,");
        queryStr.append(" nl_org_work_centers wrk, nl_org_ministries org,nl_job_jobs  ");
        //queryStr.append( " ,HR_EMP_DEGREE_DATA D, HR_SAL_ELEMENT_GUIDES G  ")
        queryStr.append( " WHERE     I.CIVIL_ID = E.REAL_CIVIL_ID ");
        queryStr.append( "    AND E.REAL_CIVIL_ID =  ");
        queryStr.append(realCivilID);
        queryStr.append( " AND E.ACTIVE_FLAG = 1 ");
        queryStr.append( "    AND E.HIRSTATUS_CODE IN ( 1)");
        queryStr.append( "  AND I.RELIGION_CODE = R.RELIGION_CODE");
        queryStr.append( " AND I.MARSTATUS_CODE = M.MARSTATUS_CODE ");
        queryStr.append( "  AND I.MAP_CODE = P.MAP_CODE(+)   ");
        queryStr.append(" and E.WRK_CODE=WRK.WRK_CODE and e.min_Code=ORG.MIN_CODE   and E.JOB_CODE=nl_job_JOBS.JOB_CODE ");
        queryStr.append(") q1  join ");
        queryStr.append(" ( WITH SubQuery1 AS ( ");
        queryStr.append(" SELECT /*+ MATERIALIZE */ to_char(D.DEGREE_DATE,'dd/MM/yyyy') as DEGREE_DATE,D.FINDEGREE_CODE   ,E.REAL_CIVIL_ID ");
        queryStr.append(" FROM HR_EMP_EMPLOYEES E, HR_EMP_DEGREE_DATA D ");
        queryStr.append(" WHERE E.CIVIL_ID = D.CIVIL_ID  AND D.STATUS = 1 AND E.HIRSTATUS_CODE = 1");
        queryStr.append(" AND E.ACTIVE_FLAG = 1 AND E.REAL_CIVIL_ID = ");
        queryStr.append(realCivilID);
        queryStr.append(" ) SELECT S.DEGREE_DATE , G.EMLGUIDE_NAME  ,REAL_CIVIL_ID ");
        queryStr.append(" FROM SubQuery1  S ,HR_SAL_ELEMENT_GUIDES G  ");
        queryStr.append(" WHERE G.ELMGUIDE_CODE = S.FINDEGREE_CODE ");
        queryStr.append(" )q2  on q1.real_civil_id=q2.real_civil_id ");
     //   queryStr.append(" and E.CIVIL_ID = D.CIVIL_ID  AND G.ELMGUIDE_CODE = D.FINDEGREE_CODE ");
       // queryStr.append(" AND D.STATUS = 1  ");
        queryStr.append("");
      System.out.println(" webservice qry" +queryStr.toString());
      //  String string = (" webservice qry" +queryStr.toString());
        Vector vector = (Vector)EM().createNativeQuery(queryStr.toString()).getSingleResult();
        if (vector != null && !vector.isEmpty()) {
            return getempData (vector);
        }
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

    /**
     *@author msayed
     * @since 28-6-2020
     * @get data for mobile webserivce
     * @param realCivilID
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public IEmployeeDTOService getAllEmployeeDataForProfileVW(Long  realCivilID) throws DataBaseException, SharedApplicationException {
        try {

            StringBuilder queryStr = new StringBuilder("select * from EMP_PROFILE_DATA where real_civil_id=");
            queryStr.append(realCivilID);
            System.out.println(" webservice qry" +queryStr.toString());
            //  String string = (" webservice qry" +queryStr.toString());
              Vector vector = (Vector)EM().createNativeQuery(queryStr.toString()).getSingleResult();
              if (vector != null && !vector.isEmpty()) {
                  return getempData (vector);
              }
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
private IEmployeeDTOService getempData(Vector empData){

    IEmployeeDTOService dto = new EmployeesDTOSer();
    dto.setEmployeeFullName(empData.get(0).toString()); //emp_name
    dto.setRealCivilId(Long.getLong( empData.get(1).toString())); //REAL_CIVIL_ID
    if (empData.get(2)!=null){
     dto.setReligonName(empData.get(2).toString()); //RELIGION_NAME
     }else{
         dto.setReligonName("");
     }
    if (empData.get(3)!=null){
     dto.setGentypeName(empData.get(3).toString()); //GENDER_DESC
     }else{
         dto.setGentypeName("");
     }
    if (empData.get(4)!=null){
         dto.setNationalityName(empData.get(4).toString()); //NAT_DESC
    }else{
             dto.setNationalityName("");
    }
    if (empData.get(5)!=null){
        dto.setMaritalStatusName(empData.get(5).toString()); //MARSTATUS_NAME
    }else{
        dto.setMaritalStatusName("");
    }
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    try{
        dto.setBirthDate(df.parse(empData.get(6).toString())); //BIRTH_DATE
    } catch (ParseException e) {
            e.printStackTrace();
        }catch(Exception e){
                    e.printStackTrace();
        }
    if (empData.get(7)!=null){
         dto.setQualName(empData.get(7).toString()); //QUAL_DESC
    }else{
        dto.setQualName("");
    }
    try{
        dto.setQualDate(df.parse(empData.get(8).toString())); //QUAL_Date
    } catch (ParseException e) {
            e.printStackTrace();
        }catch(Exception e){
                    e.printStackTrace();
        }
    if (empData.get(9)!=null){
        dto.setMobileNo(empData.get(9).toString()); //MOBILE_NO
    }else{
        dto.setMobileNo("");
    }
    if (empData.get(10)!=null){
     dto.setEMail(empData.get(10).toString()); //email
     }else{
         dto.setEMail("");
     }
    if (empData.get(11)!=null){
    dto.setSendSMSFlag(Long.parseLong(empData.get(11).toString())); //SEND_SMS_FLAG
    }else{
        dto.setSendSMSFlag(null);
    }
    if (empData.get(12)!=null){
     dto.setBuildingNo(empData.get(12).toString()); //BUILDING_NO
     }else{
         dto.setBuildingNo(null);
     }
    if (empData.get(13)!=null){
    dto.setFloorNo(Long.parseLong(empData.get(13).toString())); //FLOOR_NO
    }else{
        dto.setFloorNo(null);
    }
    if (empData.get(14)!=null){
         dto.setFlatNo(Long.parseLong(empData.get(14).toString())); //FLAT_NO
    }else{
        dto.setFlatNo(null);
    }
    if (empData.get(15)!=null){
        dto.setCityName(empData.get(15).toString()); //cityName
        }else{
            dto.setCityName("");
        }
    if (empData.get(16)!=null){
        dto.setStatusName(empData.get(16).toString()); //Gov_Name
        }else{
            dto.setStatusName("");
        }
    if (empData.get(17)!=null){
        dto.setMinName(empData.get(17).toString()); //min_name
        }else{
            dto.setMinName("");
        }
    if (empData.get(18)!=null){
        dto.setWrkName(empData.get(18).toString()); //wrk_name
    }else{
        dto.setWrkName("");
    }
    if (empData.get(19)!=null){
        dto.setMinistryFileNo(empData.get(19).toString()); //setMinistryFileNo
    }else{
        dto.setMinistryFileNo("");
    }
    try{
        dto.setHireDate(df.parse(empData.get(20).toString())); //hire_Date
    } catch (ParseException e) {
            e.printStackTrace();
        }catch(Exception e){
                    e.printStackTrace();
        }
    try{
        dto.setStartWorkingDate(df.parse(empData.get(21).toString())); //START_WORKING_DATE
    } catch (ParseException e) {
            e.printStackTrace();
    }catch(Exception e){
                e.printStackTrace();
    }
    try{
        dto.setDateOfNextRaise(df.parse(empData.get(22).toString())); //date_of_next
    } catch (ParseException e) {
            e.printStackTrace();
        }catch(Exception e){
                    e.printStackTrace();
        }
    if (empData.get(23)!=null){
        dto.setJobName(empData.get(23).toString()); ////job_name
    }else{
        dto.setJobName("");
    }
    try{
        dto.setSalDegreeDate(df.parse(empData.get(24).toString())); //DEGREE_DATE
    } catch (ParseException e) {
            e.printStackTrace();
        }catch(Exception e){
                    e.printStackTrace();
        }

    if (empData.get(25)!=null){
        dto.setSalDegree(empData.get(25).toString()); ////degree_name
    }else{
        dto.setSalDegree("");
    }
    return dto;
}
/**
     *@auhtor msayed
     * @since 14-5-2020
     * @param realCivilID
     * @param yearCode
     * @param monthCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
public IEmpFinicialData getMertisAndDeducts(Long realCivilID,Long yearCode,Long monthCode)throws DataBaseException, SharedApplicationException {
    try {
        StringBuilder queryStr=new StringBuilder(" SELECT CIVIL_ID, ");
         queryStr.append("  ACCOUNT_CHEK_NO,");
         queryStr.append("   NVL (SUM (VALUE), 0) ACTUAL_SAL,");
         queryStr.append("     BANK_CODE,");
         queryStr.append("     BANK_NAME,");
         queryStr.append("      BNKBRANCH_CODE,");
         queryStr.append("   BNKBRANCH_NAME,");
         queryStr.append(" NVL (SUM (MER_SUM), 0) MERITS,");
         queryStr.append("    NVL (SUM (DED_SUM), 0) DEDUCTIONS,min_code ");
         queryStr.append(" FROM (SELECT M.CIVIL_ID,");
         queryStr.append("         M.ACCOUNT_CHEK_NO,");
         queryStr.append("         M.VALUE,");
         queryStr.append("        B.BANK_CODE,");
         queryStr.append("       B.BANK_NAME,");
         queryStr.append("          BR.BNKBRANCH_CODE,");
         queryStr.append("           BR.BNKBRANCH_NAME,");
         queryStr.append("           (SELECT SUM (PM.VALUE)");
         queryStr.append("            FROM HR_SAL_EMP_PAYSLIPS PM,");
         queryStr.append("                 (SELECT G.ELMGUIDE_CODE, G.EMLGUIDE_NAME ");
         queryStr.append("                 FROM HR_SAL_ELEMENT_GUIDES G ");
         queryStr.append("                  WHERE G.ELMGUIDE_CODE_DEGREE <> 2) AM,");
         queryStr.append("                 HR_SAL_ELEMENT_GUIDES GM ");
         queryStr.append("            WHERE     M.CIVIL_ID = PM.CIVIL_ID ");
         queryStr.append("                 AND M.YEAR_CODE = PM.YEAR_CODE ");
         queryStr.append("                 AND M.SALARY_MONTH = PM.SALARY_MONTH ");
         queryStr.append("                 AND M.SERIAL = PM.SALARY_MONTH_SERIAL ");
         queryStr.append("                  AND GM.ELMGUIDE_CODE_DEGREE = AM.ELMGUIDE_CODE ");
         queryStr.append("                  AND PM.ELMGUIDE_CODE = GM.ELMGUIDE_CODE)");
         queryStr.append("              MER_SUM, ");
         queryStr.append("           (SELECT SUM (PD.VALUE)");
         queryStr.append("            FROM HR_SAL_EMP_PAYSLIPS PD,");
         queryStr.append("                 (SELECT G.ELMGUIDE_CODE, G.EMLGUIDE_NAME");
         queryStr.append("                  FROM HR_SAL_ELEMENT_GUIDES G ");
         queryStr.append("                  WHERE G.ELMGUIDE_CODE_DEGREE = 2) AD,");
         queryStr.append("                 HR_SAL_ELEMENT_GUIDES GD ");
         queryStr.append("           WHERE     M.CIVIL_ID = PD.CIVIL_ID ");
         queryStr.append("                  AND M.YEAR_CODE = PD.YEAR_CODE ");
         queryStr.append("                 AND M.SALARY_MONTH = PD.SALARY_MONTH ");
         queryStr.append("                  AND M.SERIAL = PD.SALARY_MONTH_SERIAL ");
         queryStr.append("                 AND GD.ELMGUIDE_CODE_DEGREE = AD.ELMGUIDE_CODE ");
         queryStr.append("                  AND PD.ELMGUIDE_CODE = GD.ELMGUIDE_CODE) ");
         queryStr.append("                  DED_SUM,m.min_Code ");
         queryStr.append("     FROM HR_SAL_EMP_MON_SALARIES M, INF_BANKS B, INF_BANK_BRANCHES BR ");
         queryStr.append("    WHERE     M.BANK_CODE = B.BANK_CODE ");
         queryStr.append("         AND M.BNKBRANCH_CODE = BR.BNKBRANCH_CODE ");
         queryStr.append("         AND M.BANK_CODE = BR.BANK_CODE ");
         queryStr.append("         AND M.PAYTYPE_CODE = 1 ");
         queryStr.append("         AND M.PAYMETHOD_CODE = 3 ");
         queryStr.append("         AND M.CIVIL_ID = ");
         queryStr.append(realCivilID);
         queryStr.append("         AND M.YEAR_CODE =");
         queryStr.append(yearCode);
         queryStr.append("          AND M.SALARY_MONTH = ");
         queryStr.append(monthCode);
         queryStr.append("   )");
         queryStr.append("   GROUP BY CIVIL_ID,");
         queryStr.append("       ACCOUNT_CHEK_NO,");
         queryStr.append("       BANK_CODE,");
         queryStr.append("       BANK_NAME,");
         queryStr.append("       BNKBRANCH_CODE,");
         queryStr.append("       BNKBRANCH_NAME,min_Code");
         System.out.println(" webservice qry" +queryStr.toString());
           //  String string = (" webservice qry" +queryStr.toString());
         Vector vector = (Vector)EM().createNativeQuery(queryStr.toString()).getSingleResult();
         if (vector != null && !vector.isEmpty()) {
             return getempFinData (vector,yearCode,monthCode);
         }
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

    /**
         *@auhtor msayed
         * @since 28-6-2020
         * @param realCivilID
         * @param yearCode
         * @param monthCode
         * @return
         * @throws DataBaseException
         * @throws SharedApplicationException
         */
    public IEmpFinicialData getMertisAndDeductsVw(Long realCivilID,Long yearCode,Long monthCode)throws DataBaseException, SharedApplicationException {
        try {
            StringBuilder queryStr=new StringBuilder("select * from EMP_FINANCIAL_DATA where REAL_CIVIL_ID=" );
            queryStr.append(realCivilID);
            queryStr.append(" and YEAR_CODE=");
            queryStr.append(yearCode);
            queryStr.append(" and SALARY_MONTH=");
            queryStr.append(monthCode);
            System.out.println(" webservice qry" +queryStr.toString());
      //  String string = (" webservice qry" +queryStr.toString());
         Vector vector = (Vector)EM().createNativeQuery(queryStr.toString()).getSingleResult();
    if (vector != null && !vector.isEmpty()) {
        return getempFinData (vector,yearCode,monthCode);
    }
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

/**
     *@author msayed
     * @since 14-5-2020
     * @param empData
     * @param salYear
     * @param salMonth
     * @return
     */
    private IEmpFinicialData getempFinData(Vector empData,Long salYear,Long salMonth){

        IEmpFinicialData dto = new EmpFinicialDataDTO();
        dto.setRealCivilID(Long.getLong(empData.get(0).toString())); //REAL_CIVIL_ID
        if (empData.get(1)!=null){
            dto.setAccountCheckNo(  empData.get(1).toString()); //ACCOUNT_CHEK_NO
        }else{
            dto.setAccountCheckNo( "");
        }
        if (empData.get(2)!=null){
             dto.setActualSalary( new BigDecimal( empData.get(2).toString())); //ACTUAL_SAL
         }else{
             dto.setActualSalary(null);
         }
        if (empData.get(3)!=null){
            dto.setBankCode( Long.parseLong( empData.get(3).toString())); //BANK_CODE
         }else{
             dto.setBankCode(null);
         }
        if (empData.get(4)!=null){
            dto.setBankName(  empData.get(4).toString()); //BANK_NAME
        }else{
            dto.setBankName( "");
        }
        if (empData.get(5)!=null){
            dto.setBankBranchCode( Long.parseLong( empData.get(5).toString())); //Branch_CODE
         }else{
             dto.setBankBranchCode(null);
         }
        if (empData.get(6)!=null){
            dto.setBankBranchName(  empData.get(6).toString()); //branch_NAME
        }else{
            dto.setBankBranchName("");
        }

        if (empData.get(7)!=null){
             dto.setTotalMertis( new BigDecimal( empData.get(7).toString())); //MERITS
         }else{
             dto.setTotalMertis(null);
         }

        if (empData.get(8)!=null){
             dto.setTotalDeducts( new BigDecimal( empData.get(8).toString())); //DEDUCTIONS
         }else{
             dto.setTotalDeducts(null);
         }
        if (empData.get(9)!=null){
            dto.setMinCode( Long.parseLong( empData.get(9).toString())); //min_Code
         }else{
             dto.setMinCode(null);
         }
        dto.setSalYear(salYear );
        dto.setSalMonth(salMonth );
        return dto;
    }
    /**
         *@auhtor msayed
         * @since 28-6-2020
         * @param realCivilID
         * @return
         * @throws DataBaseException
         * @throws SharedApplicationException
         */
    public List<IEmpBonusData> getEmpBonusData(Long realCivilID)throws DataBaseException, SharedApplicationException {
        try {
            List<IEmpBonusData> CSCEmpBounsDTOList = new ArrayList<IEmpBonusData >(0);
            StringBuilder queryStr=new StringBuilder("select * from MV_HR_SAL_EMP_SALARY_ELEMENTS where R_civil_id=" );
            queryStr.append(realCivilID);          
            System.out.println(" webservice qry" +queryStr.toString());
            List<Vector> listV = EM().createNativeQuery(queryStr.toString()).getResultList();
                   if (listV == null || listV.size() == 0)
                       throw new NoResultException();
                   for (int i = 0; i < listV.size(); i++) {
                       CSCEmpBounsDTOList.add(getempBounsData(listV.get(i)));
                   }
         return CSCEmpBounsDTOList;
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
         * @author msayed
         * @since 3-7-2020
         * @param empData
         * @return
         */
        private IEmpBonusData getempBounsData(Vector empData){

            IEmpBonusData dto = new EmpBonusDataDTO();
            dto.setRealCivilID(Long.getLong(empData.get(0).toString())); //R_CIVIL_ID
            if (empData.get(1)!=null){
                dto.setElemntValue( new BigDecimal( empData.get(1).toString())); //ELEMENT_VALUE
            }else{
                dto.setElemntValue( null);
            }
            if (empData.get(2)!=null){
                 dto.setRasiseVal( new BigDecimal( empData.get(2).toString())); //RAISE_VAL
            }else{
                 dto.setRasiseVal( null);
            }
           DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try{
                dto.setFromDate(df.parse(empData.get(3).toString())); //FROM_DATE
            } catch (ParseException e) {
                    e.printStackTrace();
            }catch(Exception e){
                            e.printStackTrace();
            }
            try{
                dto.setFromDate(df.parse(empData.get(4).toString())); //Until_DATE
            } catch (ParseException e) {
                    e.printStackTrace();
                }catch(Exception e){
                            e.printStackTrace();
                }
            
            if (empData.get(5)!=null){
                 dto.setElmGuideName( empData.get(5).toString()); //EMLGUIDE_NAME
            }else{
                 dto.setElmGuideName( "");
            } 
            return dto;
      }
         
     public int checkEmpEosFinReason(Long civil)throws DataBaseException, SharedApplicationException {
         try {
     
             StringBuilder queryStr=new StringBuilder("select e.FINREASON_CODE from HR_EOS_REQUESTS e  where e.CIVIL_ID=" );
             queryStr.append(civil);          
             System.out.println(" checkEmpEosFinReason qry" +queryStr.toString());
             List<Vector> listV = EM().createNativeQuery(queryStr.toString()).getResultList();
             if (listV == null || listV.size() == 0){
                 return 0;
             }else{
                 return Integer.parseInt(listV.get(0).get(0).toString() ) ;
             }
                         
                    
         } catch (Exception e) {
             e = wrapIntoDataBaseException(e);
             if (e instanceof DataBaseException) {
                 throw (DataBaseException)e;
             } else {
                 throw (SharedApplicationException)e;
             }
         }
     }
     public Long getOldCivil(Long minCode,Long realCivil)throws DataBaseException, SharedApplicationException {
         try {
     
             StringBuilder queryStr=new StringBuilder("SELECT EMP.CIVIL_ID FROM HR_EMP_EMPLOYEES EMP WHERE EMP.MIN_CODE="+minCode+" AND EMP.HIRSTATUS_CODE=9 AND EMP.REAL_CIVIL_ID=" );
             queryStr.append(realCivil);          
             System.out.println(" getOldCivil qry" +queryStr.toString());
             List<Vector> listV = EM().createNativeQuery(queryStr.toString()).getResultList();
             if (listV == null || listV.size() == 0){
                 return null;
             }else{
                 return Long.parseLong(listV.get(0).get(0).toString()) ;
             }
                         
                    
         } catch (Exception e) {
             e = wrapIntoDataBaseException(e);
             if (e instanceof DataBaseException) {
                 throw (DataBaseException)e;
             } else {
                 throw (SharedApplicationException)e;
             }
         }
     }
 }

