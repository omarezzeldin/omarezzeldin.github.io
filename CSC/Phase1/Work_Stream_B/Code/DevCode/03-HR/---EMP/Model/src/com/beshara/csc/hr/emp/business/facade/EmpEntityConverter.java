package com.beshara.csc.hr.emp.business.facade;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.IParameterValuesDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.gn.grs.business.entity.IParametersEntityKey;
import com.beshara.csc.hr.att.business.client.EmpRealSignClientImpl;
import com.beshara.csc.hr.att.business.client.IEmpRealSignClient;
import com.beshara.csc.hr.bgt.business.client.BgtClientFactory;
import com.beshara.csc.hr.bgt.business.dto.BgtDTOFactory;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtTypesDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.crs.business.dto.CrsDTOFactory;
import com.beshara.csc.hr.crs.business.dto.ICandidatePersonsDTO;
import com.beshara.csc.hr.crs.business.entity.CrsEntityKeyFactory;
import com.beshara.csc.hr.crs.business.entity.ICandidatePersonsEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.AssignStatusDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpInternalExperienceDTO;
import com.beshara.csc.hr.emp.business.dto.EmployeesDTOSer;
import com.beshara.csc.hr.emp.business.dto.HireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCndSalaryElementsDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCriminalStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpMinEdtypesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpMoiWsCriminalDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpReasonDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeDTOService;
import com.beshara.csc.hr.emp.business.dto.IEmployeeExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypeConditionsDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.shared.ISimpleEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.AdminAssignmentsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateStatusEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCriminalStatusEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpInternalExperienceEntity;
import com.beshara.csc.hr.emp.business.entity.EmpMinEdtypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpMoiWsCriminalEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCriminalStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpMinEdtypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpMoiWsCriminalEntityKey;
import com.beshara.csc.hr.emp.business.entity.bgt.EmpBgtProgramsEntity;
import com.beshara.csc.hr.emp.business.entity.bgt.EmpBgtTypesEntity;
import com.beshara.csc.hr.emp.business.entity.inf.EmpKwtCitizensResidentsEntity;
import com.beshara.csc.hr.emp.business.entity.job.EmpJobsEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpCatsEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpMinistriesEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpWorkCentersEntity;
import com.beshara.csc.hr.sal.business.client.SalClientFactory;
import com.beshara.csc.hr.sal.business.dto.ISalDegreeReasonsDTO;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.ISalEmpSalaryElementsDTO;
import com.beshara.csc.hr.sal.business.dto.SalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.ISalDegreeReasonsEntityKey;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.GenderCountryDTO;
import com.beshara.csc.inf.business.dto.GenderTypesDTO;
import com.beshara.csc.inf.business.dto.ICountriesDTO;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.KwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.dto.MaritalStatusDTO;
import com.beshara.csc.inf.business.entity.GenderCountryEntityKey;
import com.beshara.csc.inf.business.entity.GenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.ICountriesEntityKey;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.IKwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwtCitizensResidentsEntityKey;
import com.beshara.csc.inf.business.entity.MaritalStatusEntityKey;
import com.beshara.csc.nl.job.business.client.JobClientFactory;
import com.beshara.csc.nl.job.business.dto.ICatsDTO;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobDTOFactory;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.OrgDTOFactory;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.sql.Date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Vector;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Contains All Entity to DTO Mapping.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Taha El-Fitiany   03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Ashraf Gaber      19-JAN-2011     Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */

public final class EmpEntityConverter {
    public EmpEntityConverter() {
    }

    public static IEmployeesDTO getEmployeesDTOCodeName(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOCodeName(dto, employeesEntity);
        return dto;
    }

    public static IEmployeesDTO getEmployeesDTOCodeName2(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOCodeName2(dto, employeesEntity);
        return dto;
    }

    public static IEmployeesDTO getEmployeesDTORCivilName(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTORCivilName(dto, employeesEntity);
        return dto;
    }

    public static IEmployeesDTO getSimpleEmployeesDTO(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillSimpleEmployeesDTO(dto, employeesEntity);
        return dto;
    }

    private static void fillSimpleEmployeesDTO(IEmployeesDTO iEmployeesDTO, EmployeesEntity employeesEntity) {

        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        iEmployeesDTO.setCode(employeesEnitityKey);
        iEmployeesDTO.setRealCivilId(employeesEntity.getRealCivilId());

        if (employeesEntity.getCivilId() != null) {
            try {
                IKwtCitizensResidentsEntityKey kEk =
                    InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(employeesEntity.getRealCivilId());
                iEmployeesDTO.setCitizensResidentsDTO(InfClientFactory.getKwtCitizensResidentsClient().getSimpleKwtCitizensResidentsById(kEk));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        iEmployeesDTO.setMinCode(employeesEntity.getMinCode());


        if (employeesEntity.getWrkCode() != null && employeesEntity.getMinCode() != null) {
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                iEmployeesDTO.setWorkCenterDTO(OrgClientFactory.getWorkCentersClient().getByIdSimple(wEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        iEmployeesDTO.setMinistryFileNo(employeesEntity.getMinistryFileNo());
        iEmployeesDTO.setCscFileNo(employeesEntity.getCscFileNo());
        iEmployeesDTO.setHireDate(employeesEntity.getHireDate());
        iEmployeesDTO.setStartWorkingDate(employeesEntity.getStartWorkingDate());
        iEmployeesDTO.setEndJobDate(employeesEntity.getEndJobDate());

        if (employeesEntity.getHireStatusEntity() != null) {
            iEmployeesDTO.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO(employeesEntity.getHireStatusEntity()));
        }

        if (employeesEntity.getHireTypesEntity() != null) {
            iEmployeesDTO.setHireTypeDTO(EmpDTOFactory.createHireTypesDTO(employeesEntity.getHireTypesEntity().getHirtypeCode(),
                                                                          employeesEntity.getHireTypesEntity().getHirtypeName()));
        }


        if (employeesEntity.getHireStagesEntity() != null) {
            iEmployeesDTO.setHireStageDTO(EmpDTOFactory.createHireStagesDTO(employeesEntity.getHireStagesEntity()));
        }

        iEmployeesDTO.setDateOfNextRaise(employeesEntity.getDateOfNextRaise());

        if (employeesEntity.getJobCode() != null) {
            iEmployeesDTO.setJobDTO(getJob(employeesEntity.getJobCode()));
        }

        if (employeesEntity.getTechJobCode() != null) {
            iEmployeesDTO.setTechJobsDTO(getJob(employeesEntity.getTechJobCode()));
        }

        iEmployeesDTO.setCreatedBy(employeesEntity.getCreatedBy());
        iEmployeesDTO.setCreatedDate(employeesEntity.getCreatedDate());
        iEmployeesDTO.setLastUpdatedBy(employeesEntity.getLastUpdatedBy());
        iEmployeesDTO.setLastUpdatedDate(employeesEntity.getLastUpdatedDate());
        iEmployeesDTO.setAuditStatus(employeesEntity.getAuditStatus());
        iEmployeesDTO.setActiveFlag(employeesEntity.getActiveFlag());
        iEmployeesDTO.setRecordDescCode(employeesEntity.getRecordDescCode());
        iEmployeesDTO.setTabrecSerial(employeesEntity.getTabrecSerial());
        iEmployeesDTO.setSocialInsurNo(employeesEntity.getSocialInsurNo());

        if (employeesEntity.getBgtprgCode() != null) {
            iEmployeesDTO.setBgtProgramsDTO(getBGTProgramDTO(employeesEntity.getBgtprgCode()));
        }
        if (employeesEntity.getBgttypeCode() != null) {
            iEmployeesDTO.setBgtTypesDTO(getBGTTypDTO(employeesEntity.getBgttypeCode()));
        }

    }

    private static IBgtTypesDTO getBGTTypDTO(Long bgtCode) {

        IBgtTypesEntityKey bgtTEk = BgtEntityKeyFactory.createBgtTypesEntityKey(bgtCode);
        try {
            return (IBgtTypesDTO)BgtClientFactory.getBgtTypesClient().getById(bgtTEk);
        } catch (Exception e) {
            return null;
        }

    }

    private static IBasicDTO getJob(String jobCode) {
        try {
            return JobClientFactory.getJobsClient().getSimpleJobById(JobEntityKeyFactory.createJobsEntityKey(jobCode));
        } catch (Exception e) {
            return null;
        }
    }

    private static IBgtProgramsDTO getBGTProgramDTO(String bgtCode) {

        IBgtProgramsEntityKey bgtEk = BgtEntityKeyFactory.createBgtProgramsEntityKey(bgtCode);
        try {
            return (IBgtProgramsDTO)BgtClientFactory.getBgtProgramsClient().getById(bgtEk);
        } catch (Exception e) {
            return null;
        }

    }

    public static void fillEmployeesDTORCivilName(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        dto.setCitizensResidentsDTO(getKwtCitizensResidentsDTOCodeNameOnly(employeesEntity.getRealCivilId()));
    }

    public static void fillEmployeesDTOCodeName(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        dto.setCitizensResidentsDTO(getKwtCitizensResidentsDTOCodeNameOnly(employeesEntity.getRealCivilId()));
        dto.setAuditStatus(employeesEntity.getAuditStatus());
        dto.setTabrecSerial(employeesEntity.getTabrecSerial());
        dto.setMinCode(employeesEntity.getMinCode());
        if (employeesEntity.getHireStatusEntity() != null) {
            dto.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO((employeesEntity.getHireStatusEntity()).getHirstatusCode(),
                                                                   (employeesEntity.getHireStatusEntity()).getHirstatusName()));
        }
        if (employeesEntity.getJobCode() != null) {
            try {
                IBasicDTO jobDTO;
                jobDTO =
                        JobClientFactory.getJobsClient().getSimpleJobById(JobEntityKeyFactory.createJobsEntityKey(employeesEntity.getJobCode()));
                dto.setJobDTO(jobDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setUserInfoData(dto, employeesEntity);
    }

    public static void fillEmployeesDTOCodeName2(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        //dto.setCitizensResidentsDTO(getKwtCitizensResidentsDTOCodeNameOnly(employeesEntity.getRealCivilId()));
        if (employeesEntity.getCitizensResidentsEntity() != null) {
            EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                new KwtCitizensResidentsDTO(employeesEntity.getRealCivilId(),
                                            kwtCitizensResidentsEntity.getFirstName(),
                                            kwtCitizensResidentsEntity.getSecondName(),
                                            kwtCitizensResidentsEntity.getThirdName(),
                                            kwtCitizensResidentsEntity.getLastName(),
                                            kwtCitizensResidentsEntity.getFamilyName());
            kwtCitizensResidentsDTO.setName(kwtCitizensResidentsDTO.getFullName());
            dto.setCitizensResidentsDTO(kwtCitizensResidentsDTO);
        }
        dto.setAuditStatus(employeesEntity.getAuditStatus());
        dto.setTabrecSerial(employeesEntity.getTabrecSerial());
        dto.setMinCode(employeesEntity.getMinCode());
        //        if (employeesEntity.getHireStatusEntity() != null) {
        //            dto.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO((employeesEntity.getHireStatusEntity()).getHirstatusCode(),
        //                                                                   (employeesEntity.getHireStatusEntity()).getHirstatusName()));
        //        }
        setUserInfoData(dto, employeesEntity);
    }

    public static IEmpCandidateStatusDTO getEmpCandidateStatusDTO(EmpCandidateStatusEntity empCandidateStatusEntity) {
        IEmpCandidateStatusDTO dto = EmpDTOFactory.createEmpCandidateStatusDTO();
        fillEmpCandidateStatusDTO(dto, empCandidateStatusEntity);
        return dto;
    }

    public static void fillEmpCandidateStatusDTO(IEmpCandidateStatusDTO dto,
                                                 EmpCandidateStatusEntity empCandidateStatusEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmpCandidateStatusEntityKey(empCandidateStatusEntity.getCndstatusCode()));
        dto.setName(empCandidateStatusEntity.getCndstatusName());
        dto.setAuditStatus(empCandidateStatusEntity.getAuditStatus());
        dto.setTabrecSerial(empCandidateStatusEntity.getTabrecSerial());

    }

    public static IEmployeeExtraDataDTO getEmployeeExtraDataDTO(EmployeeExtraDataEntity employeeExtraDataEntity) {
        IEmployeeExtraDataDTO dto = EmpDTOFactory.createEmployeeExtraDataDTO();
        fillEmployeeExtraDataDTO(dto, employeeExtraDataEntity);
        return dto;
    }

    public static void fillEmployeeExtraDataDTO(IEmployeeExtraDataDTO dto,
                                                EmployeeExtraDataEntity employeeExtraDataEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(employeeExtraDataEntity.getSerial()));
        dto.setFromDate(employeeExtraDataEntity.getFromDate());
        dto.setUntilDate(employeeExtraDataEntity.getUntilDate());
        dto.setValue(employeeExtraDataEntity.getValue());
        dto.setValue(employeeExtraDataEntity.getValue());
        dto.setCscBookNo(employeeExtraDataEntity.getCscBookNo());
        dto.setCscBookDate(employeeExtraDataEntity.getCscBookDate());
        dto.setComments(employeeExtraDataEntity.getComments());
        
        if (employeeExtraDataEntity.getEmployeesEntity() != null) {
            dto.setEmployeesDTO(EmpEntityConverter.getEmployeesDTOCodeName(employeeExtraDataEntity.getEmployeesEntity()));
        }
        if (employeeExtraDataEntity.getEmpExtraDataTypesEntity() != null) {
            dto.setEmpExtraDataTypesDTO(EmpEntityConverter.getEmployeeExtraDataTypesDTO(employeeExtraDataEntity.getEmpExtraDataTypesEntity()));
        }
    }

    public static IEmployeeExtraDataDTO getEmployeeExtraDataDTO2(EmployeeExtraDataEntity employeeExtraDataEntity) {
        IEmployeeExtraDataDTO dto = EmpDTOFactory.createEmployeeExtraDataDTO();
        fillEmployeeExtraDataDTO2(dto, employeeExtraDataEntity);
        return dto;
    }

    public static void fillEmployeeExtraDataDTO2(IEmployeeExtraDataDTO dto,
                                                EmployeeExtraDataEntity employeeExtraDataEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeeExtraDataEntityKey(employeeExtraDataEntity.getSerial()));
        dto.setFromDate(employeeExtraDataEntity.getFromDate());
        dto.setUntilDate(employeeExtraDataEntity.getUntilDate());
        dto.setValue(employeeExtraDataEntity.getValue());
        dto.setValueDesc(employeeExtraDataEntity.getValueDesc());
        dto.setStatus(employeeExtraDataEntity.getStatus());
        dto.setTabrecSerial(employeeExtraDataEntity.getTabrecSerial());
        dto.setCscBookNo(employeeExtraDataEntity.getCscBookNo());
        dto.setCscBookDate(employeeExtraDataEntity.getCscBookDate());
        dto.setComments(employeeExtraDataEntity.getComments());
        
       
        /*if (employeeExtraDataEntity.getEmployeesEntity() != null) {
            dto.setEmployeesDTO(EmpEntityConverter.getEmployeesDTOCodeName(employeeExtraDataEntity.getEmployeesEntity()));
        }*/
        if (employeeExtraDataEntity.getEmpExtraDataTypesEntity() != null) {
            dto.setEmpExtraDataTypesDTO(EmpEntityConverter.getEmployeeExtraDataTypesDTO(employeeExtraDataEntity.getEmpExtraDataTypesEntity()));
            if(employeeExtraDataEntity.getEmpExtraDataTypesEntity().getParameterCode() != null){
                
                IParametersEntityKey parametersEntityKey =
                    GrsEntityKeyFactory.createParametersEntityKey(employeeExtraDataEntity.getEmpExtraDataTypesEntity().getParameterCode());
                try {
                    IBasicDTO parametersDTO=GrsClientFactory.getParametersClient().getById(parametersEntityKey);
                    List<IBasicDTO> parameterValuesList =
                        GrsClientFactory.getParametersClient().getParameterValues(parametersDTO);
                    if(parameterValuesList!=null && parameterValuesList.size()>0){
                        for(IBasicDTO parameterValueDTO : parameterValuesList){
                            IParameterValuesDTO parameterValuesDTO = (IParameterValuesDTO)parameterValueDTO;
                            if(parameterValuesDTO.getStrCode().equals(employeeExtraDataEntity.getValue())){
                                dto.setValueDesc(parameterValuesDTO.getName());
                                break;
                            }
                        }
                    }
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("tab1 >>>>>>> " + dto.getTabrecSerial());
    }

    public static IEmpExtraDataTypesDTO getEmployeeExtraDataTypesDTO(EmpExtraDataTypesEntity empExtraDataTypesEntity) {
        IEmpExtraDataTypesDTO dto = EmpDTOFactory.createEmpExtraDataTypesDTO();
        fillEmpExtraDataTypesDTO(dto, empExtraDataTypesEntity);
        return dto;
    }

    public static void fillEmpExtraDataTypesDTO(IEmpExtraDataTypesDTO dto,
                                                EmpExtraDataTypesEntity empExtraDataTypesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmpExtraDataTypesEntityKey(empExtraDataTypesEntity.getExtdattypeCode()));
        dto.setExtdattypeName(empExtraDataTypesEntity.getExtdattypeName());
        dto.setParameterCode(empExtraDataTypesEntity.getParameterCode() );
        dto.setTabrecSerial(empExtraDataTypesEntity.getTabrecSerial());
        System.out.println("fillEmpExtraDataTypesDTO tab2 >>>>>>> " + dto.getTabrecSerial());

    }

    protected static IBasicDTO getKwtCitizensResidentsDTO(Long civilId) {
        try {
            return InfClientFactory.getKwtCitizensResidentsClient().getCitizenInformation(civilId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /************ Inner Methods Created by Ashraf Gaber ***********************/

    protected static IBasicDTO getKwtCitizensResidentsDTOCodeNameOnly(Long civilId) {
        try {
            return InfClientFactory.getKwtCitizensResidentsClient().getCitizenCodeName(civilId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static IBasicDTO getEmpReasonDataDTO(Long restypeCode, Long resdatSerial) {
        try {
            return EmpClientFactory.getEmpReasonDataClient().getById(EmpEntityKeyFactory.createEmpReasonDataEntityKey(restypeCode,
                                                                                                                      resdatSerial));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    protected static IBasicDTO getAttendTypeSystemsDTO(Long atttypsystemCode){
    //        try {
    //            return AttClientFactory.getAttendTypeSystemsClient().getById(AttEntityKeyFactory.createAttendTypeSystemsEntityKey(atttypsystemCode));
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return null;
    //    }

    protected static IBasicDTO getHolidayTypesDTO(Long holtypeCode) {
        try {
            return InfClientFactory.getHolidayTypesClient().getById(InfEntityKeyFactory.createHolidayTypesEntityKey(holtypeCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static IBasicDTO getWeekDaysDTO(Long daynum) {
        try {
            return InfClientFactory.getWeekDaysClient().getById(InfEntityKeyFactory.createWeekDaysEntityKey(daynum));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static IBasicDTO getSalElementGuidesDTO(Long elmguideCode) {
        try {
            return SalClientFactory.getSalElementGuidesClient().getById(SalEntityKeyFactory.createSalElementGuidesEntityKey(elmguideCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static IBasicDTO getWorkCentersDTO(Long minCode, String wrkCode) {
        try {
            return OrgClientFactory.getWorkCentersClient().getById(OrgEntityKeyFactory.createWorkCentersEntityKey(minCode,
                                                                                                                  wrkCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static IBasicDTO getConditionsDTO(Long conditionCode) {
        try {
            return GrsClientFactory.getConditionsClient().getById(GrsEntityKeyFactory.createConditionsEntityKey(conditionCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void setUserInfoData(IBasicDTO dto, BasicEntity entity) {
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastUpdatedBy(entity.getLastUpdatedBy());
        dto.setLastUpdatedDate(entity.getLastUpdatedDate());
    }

    public static IEmpCandidateDocumentsDTO getEmpCandidateDocumentsDTO(EmpCandidateDocumentsEntity candidateDocumentsEntity) {
        IEmpCandidateDocumentsDTO dto = EmpDTOFactory.createEmpCandidateDocumentsDTO();
        fillEmpCandidateDocumentsDTO(dto, candidateDocumentsEntity);
        return dto;
    }

    public static void fillEmpCandidateDocumentsDTO(IEmpCandidateDocumentsDTO dto,
                                                    EmpCandidateDocumentsEntity candidateDocumentsEntity) {

        dto.setCode(EmpEntityKeyFactory.createEmpCandidateDocumentsEntityKey(candidateDocumentsEntity.getCandidateCode(),
                                                                             candidateDocumentsEntity.getCandidateCodeSeq(),
                                                                             candidateDocumentsEntity.getCnddocSerial()));
        dto.setComments(candidateDocumentsEntity.getComments());
        dto.setDocStatus(candidateDocumentsEntity.getDocStatus());
        dto.setAttachmentStatus(candidateDocumentsEntity.getAttachmentStatus());
        dto.setAuditStatus(candidateDocumentsEntity.getAuditStatus());
        dto.setTabrecSerial(candidateDocumentsEntity.getTabrecSerial());
        if (candidateDocumentsEntity.getEmpCandidatesEntity() != null) {
            dto.setEmpCandidateDTO(EmpEntityConverter.getEmpCandidatesDTO(candidateDocumentsEntity.getEmpCandidatesEntity()));
        }
        if (candidateDocumentsEntity.getDoctypeCode() != null) {
            IInfDocumentTypesEntityKey dEk =
                InfEntityKeyFactory.createInfDocumentTypesEntityKey(candidateDocumentsEntity.getDoctypeCode());
            //dto.setDocumentTypesDTO(InfDTOFactory.createInfDocumentTypesDTO(candidateDocumentsEntity.getDocumentTypesEntity()));
            try {
                dto.setDocumentTypesDTO((IInfDocumentTypesDTO)InfClientFactory.getInfDocumentTypesClient().getById(dEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static IEmpCandidateExtraDataDTO getEmpCandidateExtraDataDTO(EmpCandidateExtraDataEntity empCandidateExtraDataEntity) {
        IEmpCandidateExtraDataDTO dto = EmpDTOFactory.createEmpCandidateExtraDataDTO();
        fillEmpCandidateExtraDataDTO(dto, empCandidateExtraDataEntity);
        return dto;
    }

    public static void fillEmpCandidateExtraDataDTO(IEmpCandidateExtraDataDTO dto,
                                                    EmpCandidateExtraDataEntity empCandidateExtraDataEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmpCandidateExtraDataEntityKey(empCandidateExtraDataEntity.getSerial()));
        dto.setValue(empCandidateExtraDataEntity.getValue());
        dto.setValueDesc(empCandidateExtraDataEntity.getValueDesc());
        dto.setFromDate(empCandidateExtraDataEntity.getFromDate());
        dto.setUntilDate(empCandidateExtraDataEntity.getUntilDate());
        dto.setStatus(empCandidateExtraDataEntity.getStatus());
        if (empCandidateExtraDataEntity.getEmpCandidatesEntity() != null) {
            dto.setEmpCandidatesDTO(EmpEntityConverter.getEmpCandidatesDTO(empCandidateExtraDataEntity.getEmpCandidatesEntity()));
        }
        if (empCandidateExtraDataEntity.getEmpExtraDataTypesEntity() != null) {
            dto.setEmpExtraDataTypesDTO(EmpEntityConverter.getEmployeeExtraDataTypesDTO(empCandidateExtraDataEntity.getEmpExtraDataTypesEntity()));
        }
        setUserInfoData(dto, empCandidateExtraDataEntity);
    }

    public static IEmpCandidateProceduresDTO getEmpCandidateProceduresDTO(EmpCandidateProceduresEntity empCandidateProceduresEntity) {
        IEmpCandidateProceduresDTO dto = EmpDTOFactory.createEmpCandidateProceduresDTO();
        fillEmpCandidateProceduresDTO(dto, empCandidateProceduresEntity);
        return dto;
    }

    public static void fillEmpCandidateProceduresDTO(IEmpCandidateProceduresDTO dto,
                                                     EmpCandidateProceduresEntity empCandidateProceduresEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmpCandidateProceduresEntityKey(empCandidateProceduresEntity.getCandidateCode(),
                                                                              empCandidateProceduresEntity.getCandidateCodeSeq(),
                                                                              empCandidateProceduresEntity.getHirProcedureCode()));
        dto.setProcDesc(empCandidateProceduresEntity.getProcDesc());
        dto.setSendDate(empCandidateProceduresEntity.getSendDate());
        dto.setResultDate(empCandidateProceduresEntity.getResultDate());
        dto.setPostponeDate(empCandidateProceduresEntity.getPostponeDate());
        dto.setAuditStatus(empCandidateProceduresEntity.getAuditStatus());
        dto.setTabrecSerial(empCandidateProceduresEntity.getTabrecSerial());
        dto.setCrmLetterDate(empCandidateProceduresEntity.getCrmLetterDate());
        dto.setCrmLetterNo(empCandidateProceduresEntity.getCrmLetterNo());
        dto.setCrmSheetNo(empCandidateProceduresEntity.getCrmSheetNo());
        dto.setCrmStatusCode(empCandidateProceduresEntity.getCrmStatusCode());
        
        if (empCandidateProceduresEntity.getEmpReasonDataEntity() != null) {
            dto.setEmpReasonDataDTO((IEmpReasonDataDTO)EmpEntityConverter.getEmpReasonDataDTO(empCandidateProceduresEntity.getEmpReasonDataEntity().getRestypeCode(),
                                                                                              empCandidateProceduresEntity.getEmpReasonDataEntity().getResdatSerial()));
        }
        if (empCandidateProceduresEntity.getEmpCandidatesEntity() != null) {
            dto.setEmpCandidatesDTO(EmpEntityConverter.getEmpCandidatesDTO(empCandidateProceduresEntity.getEmpCandidatesEntity()));
        }
        if (empCandidateProceduresEntity.getProcedureResultsEntity() != null) {
            dto.setProcedureResultsDTO(EmpDTOFactory.createProcedureResultsDTO(empCandidateProceduresEntity.getProcedureResultsEntity()));
        }
        if (empCandidateProceduresEntity.getHireProceduresEntity() != null) {
            dto.setHireProceduresDTO(EmpDTOFactory.createHireProceduresDTO(empCandidateProceduresEntity.getHireProceduresEntity()));
        }
    }

    public static IEmpCndSalaryElementsDTO getEmpCndSalaryElementsDTO(EmpCndSalaryElementsEntity empCndSalaryElementsDTO) {
        IEmpCndSalaryElementsDTO dto = EmpDTOFactory.createEmpCndSalaryElementsDTO();
        fillEmpCndSalaryElementsDTO(dto, empCndSalaryElementsDTO);
        return dto;
    }

    public static void fillEmpCndSalaryElementsDTO(IEmpCndSalaryElementsDTO dto,
                                                   EmpCndSalaryElementsEntity empCndSalaryElementsEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmpCndSalaryElementsEntityKey(empCndSalaryElementsEntity.getCndSalelmSerial()));

        dto.setElementValue(empCndSalaryElementsEntity.getElementValue());
        dto.setElementRatio(empCndSalaryElementsEntity.getElementRatio());
        dto.setFromDate(empCndSalaryElementsEntity.getFromDate());
        dto.setUntilDate(empCndSalaryElementsEntity.getUntilDate());
        dto.setCndSalelmComment(empCndSalaryElementsEntity.getCndSalelmComment());
        dto.setAuditStatus(empCndSalaryElementsEntity.getAuditStatus());
        dto.setTabrecSerial(empCndSalaryElementsEntity.getTabrecSerial());
        if (empCndSalaryElementsEntity.getEmpCandidatesEntity() != null) {
            dto.setEmpCandidatesDTO(EmpEntityConverter.getEmpCandidatesDTO(empCndSalaryElementsEntity.getEmpCandidatesEntity()));
        }
        try {
            if (empCndSalaryElementsEntity.getDegreasonCode() != null) {
                ISalDegreeReasonsEntityKey salDEk =
                    SalEntityKeyFactory.createSalDegreeReasonsEntityKey(empCndSalaryElementsEntity.getDegreasonCode());
                dto.setSalDegreeReasonsDTO((ISalDegreeReasonsDTO)SalClientFactory.getSalDegreeReasonsClient().getById(salDEk));
                //dto.setSalDegreeReasonsDTO(SalEntityConverter.getSalDegreeReasonsDTO(empCndSalaryElementsEntity.getSalDegreeReasonsEntity()));
            }
            if (empCndSalaryElementsEntity.getElmguideCode() != null) {
                ISalElementGuidesEntityKey elmgEk =
                    SalEntityKeyFactory.createSalElementGuidesEntityKey(empCndSalaryElementsEntity.getElmguideCode());
                IBasicDTO elementGuideDTO = null;
                try {
                    elementGuideDTO = SalClientFactory.getSalElementGuidesClient().getByCode(elmgEk);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (elementGuideDTO == null) {
                    elementGuideDTO = new SalElementGuidesDTO();
                    elementGuideDTO.setCode(elmgEk);
                }

                dto.setSalElementGuidesDTO((ISalElementGuidesDTO)elementGuideDTO);
                dto.setElmguideCode(empCndSalaryElementsEntity.getElmguideCode());
                //dto.setSalElementGuidesDTO(SalEntityConverter.getSalElementGuidesDTO(empCndSalaryElementsEntity.getSalElementGuidesEntity()));
            }
            if (empCndSalaryElementsEntity.getElmguideCodeEquv() != null) {
                ISalElementGuidesEntityKey elmgEk =
                    SalEntityKeyFactory.createSalElementGuidesEntityKey(empCndSalaryElementsEntity.getElmguideCodeEquv());
                IBasicDTO bscDTO = SalClientFactory.getSalElementGuidesClient().getByCode(elmgEk);
                dto.setSalEqElementGuidesDTO((ISalElementGuidesDTO)bscDTO);
                dto.setElmguideCodeEquv(empCndSalaryElementsEntity.getElmguideCodeEquv());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static IEmpCandidatesDTO getEmpCandidatesDTO(EmpCandidatesEntity empCandidatesDTO) {
        IEmpCandidatesDTO dto = EmpDTOFactory.createEmpCandidatesDTO();
        fillEmpCandidatesDTO(dto, empCandidatesDTO);
        return dto;
    }

    public static void fillEmpCandidatesDTO(IEmpCandidatesDTO dto, EmpCandidatesEntity empCandidatesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmpCandidatesEntityKey(empCandidatesEntity.getCandidateCode(),
                                                                     empCandidatesEntity.getCandidateCodeSeq()));
        dto.setHireDate(empCandidatesEntity.getHireDate());
        dto.setStartWorkingDate(empCandidatesEntity.getStartWorkingDate());
        dto.setDateOfNextRaise(empCandidatesEntity.getDateOfNextRaise());   
        dto.setTestPeriodDate(empCandidatesEntity.getTestPeriodDate());
        dto.setCscFileNo(empCandidatesEntity.getCscFileNo());
        dto.setCscFileNo(empCandidatesEntity.getCscFileNo());
        dto.setMinistryFileNo(empCandidatesEntity.getMinistryFileNo());
        dto.setCscFileNo(empCandidatesEntity.getCscFileNo());
        dto.setSocialInsurNo(empCandidatesEntity.getSocialInsurNo());
        dto.setActiveFlag(empCandidatesEntity.getActiveFlag());
        dto.setApprovedByUser(empCandidatesEntity.getApprovedByUser());
        dto.setApprovedDate(empCandidatesEntity.getApprovedDate());
        dto.setAuditByUser(empCandidatesEntity.getAuditByUser());
        dto.setTransferToEmpFlag(empCandidatesEntity.getTransferToEmpFlag());
        dto.setTabrecSerial(empCandidatesEntity.getTabrecSerial());
        dto.setTabrecSerial(empCandidatesEntity.getTabrecSerial());
        dto.setAuditStatus(empCandidatesEntity.getAuditStatus());
        dto.setMinCode(empCandidatesEntity.getMinCode());
        dto.setTransReqDate(empCandidatesEntity.getTransReqDate());

        if (empCandidatesEntity.getEmpCandidateStatusEntity() != null) {
            dto.setEmpCandidateStatusDTO(EmpEntityConverter.getEmpCandidateStatusDTO(empCandidatesEntity.getEmpCandidateStatusEntity()));
        }

        if (empCandidatesEntity.getBgtprgCode() != null) {
            IBgtProgramsEntityKey bgtEk =
                BgtEntityKeyFactory.createBgtProgramsEntityKey(empCandidatesEntity.getBgtprgCode());
            try {
                dto.setBgtProgramsDTO(BgtClientFactory.getBgtProgramsClient().getById(bgtEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //dto.setBgtProgramsDTO(BgtDTOFactory.createBgtProgramsDTO(empCandidatesEntity.getBgtProgramsEntity()));
        }

        if (empCandidatesEntity.getBgttypeCode() != null) {
            IBgtTypesEntityKey bgtTEk =
                BgtEntityKeyFactory.createBgtTypesEntityKey(empCandidatesEntity.getBgttypeCode());
            try {
                dto.setBgtTypesDTO(BgtClientFactory.getBgtTypesClient().getById(bgtTEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (empCandidatesEntity.getCivilId() != null) {
            IKwtCitizensResidentsEntityKey kEk =
                InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(empCandidatesEntity.getCivilId());
            try {
                dto.setCitizensResidentsDTO(InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidents(kEk));
                if (((IKwtCitizensResidentsDTO)dto.getCitizensResidentsDTO()).getNoQual() != null &&
                    ((IKwtCitizensResidentsDTO)dto.getCitizensResidentsDTO()).getNoQual() == 1) {
                    dto.setWitoutQualFlag(true);

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (empCandidatesEntity.getCivilId() != null && empCandidatesEntity.getCrsTrxCode() != null &&
            empCandidatesEntity.getCrsRegperiodCode() != null) {
            ICandidatePersonsEntityKey cEk =
                CrsEntityKeyFactory.createCandidatePersonsEntityKey(empCandidatesEntity.getCivilId(),
                                                                    empCandidatesEntity.getCrsRegperiodCode(),
                                                                    empCandidatesEntity.getCrsTrxCode());
            //                dto.setCandidatePersonsDTO(CrsClientFactory.getCandidatePersonsClient().getById(cEk));
            ICandidatePersonsDTO candidatePersonsDTO = CrsDTOFactory.createCandidatePersonsDTO();
            candidatePersonsDTO.setCode(cEk);
            dto.setCandidatePersonsDTO(candidatePersonsDTO);
        }

        if (empCandidatesEntity.getApprovedByUser() != null) {
            dto.setApprovedByUser(empCandidatesEntity.getApprovedByUser());
        }

        if (empCandidatesEntity.getAuditByUser() != null) {
            dto.setAuditByUser(empCandidatesEntity.getAuditByUser());
        }

        if (empCandidatesEntity.getHireStagesEntity() != null) {
            dto.setHireStagesDTO(EmpDTOFactory.createHireStagesDTO(empCandidatesEntity.getHireStagesEntity()));
        }

        if (empCandidatesEntity.getHireTypesEntity() != null) {
            dto.setHireTypesDTO(EmpDTOFactory.createHireTypesDTO(empCandidatesEntity.getHireTypesEntity()));
        }

        if (empCandidatesEntity.getJobCode() != null) {
            //            IJobsEntityKey jEk = JobEntityKeyFactory.createJobsEntityKey(empCandidatesEntity.getJobCode());
            try {
                dto.setJobsDTO(JobClientFactory.getJobsClient().getJobCodeName(empCandidatesEntity.getJobCode()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // dto.setJobsDTO(JobEntityConverter.getJobsDTO(empCandidatesEntity.getJobsEntity()));
        }
        

        if (empCandidatesEntity.getWrkCode() != null && empCandidatesEntity.getMinCode() != null) {
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(empCandidatesEntity.getMinCode(),
                                                               empCandidatesEntity.getWrkCode());
            try {
                IBasicDTO workdto = OrgClientFactory.getWorkCentersClient().getCodeNameWrkCenter(wEk);
                dto.setWorkCentersDTO(workdto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //dto.setWorkCentersDTO(OrgEntityConverter.getWorkCentersDTO(empCandidatesEntity.getWorkCentersEntity()));
        }

        try {
            dto.setExtraDataValue(EmpClientFactory.getEmpCandidateExtraDataClient().extraDataValueForType(empCandidatesEntity.getCandidateCode(), 1L, 326L));
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        
        setUserInfoData(dto, empCandidatesEntity);
    }

    public static IHireTypeConditionsDTO getHireTypeConditionsDTO(HireTypeConditionsEntity hireTypeConditionsEntity) {
        IHireTypeConditionsDTO dto = EmpDTOFactory.createHireTypeConditionsDTO();
        fillEmployeesDTOCodeName(dto, hireTypeConditionsEntity);
        return dto;
    }

    public static void fillEmployeesDTOCodeName(IHireTypeConditionsDTO dto,
                                                HireTypeConditionsEntity hireTypeConditionsEntity) {
        dto.setCode(EmpEntityKeyFactory.createHireTypeConditionsEntityKey(hireTypeConditionsEntity.getConditionCode()));
        dto.setFromDate(hireTypeConditionsEntity.getFromDate());
        dto.setStatus(hireTypeConditionsEntity.getStatus());
        dto.setAuditStatus(hireTypeConditionsEntity.getAuditStatus());
        dto.setTabrecSerial(hireTypeConditionsEntity.getTabrecSerial());
        if (hireTypeConditionsEntity.getHireTypesEntity() != null) {
            dto.setHireTypeDTO(EmpDTOFactory.createHireTypesDTO(hireTypeConditionsEntity.getHireTypesEntity()));
        }
        //if (hireTypeConditionsEntity.getConditionsEntity() != null) {
        if (hireTypeConditionsEntity.getConditionCode() != null) {
            IConditionsEntityKey condEK =
                GrsEntityKeyFactory.createConditionsEntityKey(hireTypeConditionsEntity.getConditionCode());
            try {
                dto.setConditionsDTO((IConditionsDTO)GrsClientFactory.getConditionsClient().getById(condEK));
                //dto.setConditionsDTO(GrsEntityConverter.getConditionsDTO(hireTypeConditionsEntity.getConditionsEntity()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static IHireTypesDTO getHireTypesDTO(HireTypesEntity hireTypesEntity) {
        IHireTypesDTO dto = EmpDTOFactory.createHireTypesDTO();
        fillHireTypesDTO(dto, hireTypesEntity);
        return dto;
    }

    public static void fillHireTypesDTO(IHireTypesDTO dto, HireTypesEntity hireTypesEntity) {
        dto.setCode(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypesEntity.getHirtypeCode()));
        dto.setName(hireTypesEntity.getHirtypeName());
        dto.setCreatedBy(hireTypesEntity.getCreatedBy());
        dto.setCreatedDate(hireTypesEntity.getCreatedDate());
        dto.setLastUpdatedBy(hireTypesEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(hireTypesEntity.getLastUpdatedDate());
        dto.setAuditStatus(hireTypesEntity.getAuditStatus());
        dto.setTabrecSerial(hireTypesEntity.getTabrecSerial());
        dto.setTreeLevel(hireTypesEntity.getTreeLevel());
        dto.setFirstParent(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypesEntity.getFirstParent()));
        dto.setLeafFlag(hireTypesEntity.getLeafFlag());
        dto.setDecisionMust(hireTypesEntity.getDecisionFlag());
        if (hireTypesEntity.getHireTypeEntity() != null) {
            HireTypesDTO parent = (HireTypesDTO)EmpDTOFactory.createHireTypesDTO(hireTypesEntity.getHireTypeEntity());
            dto.setParentCode(EmpEntityKeyFactory.createHireTypesEntityKey(hireTypesEntity.getHireTypeEntity().getHirtypeCode()));
            dto.setParentObject(parent);
            dto.setParentHireType(parent);
        }
    }


    public static IEmployeesDTO getSimpleEmployeesDTOForMov(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillSimpleEmployeesDTOForMov(dto, employeesEntity);
        return dto;
    }

    public static void fillSimpleEmployeesDTOForMov(IEmployeesDTO dto, EmployeesEntity employeesEntity) {

        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        dto.setCode(employeesEnitityKey);
        dto.setRealCivilId(employeesEntity.getRealCivilId());

        if (employeesEntity.getCitizensResidentsEntity() != null) {
            EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
            IKwtCitizensResidentsDTO citizensResidentsDTO = new KwtCitizensResidentsDTO();

            citizensResidentsDTO.setCode(new KwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));

            String fullCivilName =
                kwtCitizensResidentsEntity.getFirstName() + " " + kwtCitizensResidentsEntity.getSecondName() + " " +
                kwtCitizensResidentsEntity.getThirdName() + " " + kwtCitizensResidentsEntity.getLastName();
            if (kwtCitizensResidentsEntity.getFamilyName() != null) {
                fullCivilName = fullCivilName + " " + kwtCitizensResidentsEntity.getFamilyName();
            }
            citizensResidentsDTO.setFullNameColumn(fullCivilName);
            //citizensResidentsDTO.setName(fullCivilName);
            dto.setCitizensResidentsDTO(citizensResidentsDTO);

        }

        dto.setMinistryFileNo(employeesEntity.getMinistryFileNo());
        dto.setHireDate(employeesEntity.getHireDate());


    }

    public static IEmployeesDTO getSimpleEmployeesDTOForMovByJob(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOForMovByJob(dto, employeesEntity);
        return dto;
    }

    public static void fillEmployeesDTOForMovByJob(IEmployeesDTO dto, EmployeesEntity employeesEntity) {

        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        dto.setCode(employeesEnitityKey);
        dto.setRealCivilId(employeesEntity.getRealCivilId());

        if (employeesEntity.getCitizensResidentsEntity() != null) {
            EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();
            IKwtCitizensResidentsDTO citizensResidentsDTO = new KwtCitizensResidentsDTO();

            citizensResidentsDTO.setCode(new KwtCitizensResidentsEntityKey(kwtCitizensResidentsEntity.getCivilId()));

            String fullCivilName =
                kwtCitizensResidentsEntity.getFirstName() + " " + kwtCitizensResidentsEntity.getSecondName() + " " +
                kwtCitizensResidentsEntity.getThirdName() + " " + kwtCitizensResidentsEntity.getLastName();
            if (kwtCitizensResidentsEntity.getFamilyName() != null) {
                fullCivilName = fullCivilName + " " + kwtCitizensResidentsEntity.getFamilyName();
            }
            citizensResidentsDTO.setFullNameColumn(fullCivilName);
            //citizensResidentsDTO.setName(fullCivilName);
            dto.setCitizensResidentsDTO(citizensResidentsDTO);

        }

        dto.setMinistryFileNo(employeesEntity.getMinistryFileNo());
        dto.setHireDate(employeesEntity.getHireDate());
        IBasicDTO jobDTO;
        if (employeesEntity.getJobCode() != null) {
            try {
                jobDTO =
                        JobClientFactory.getJobsClient().getById(JobEntityKeyFactory.createJobsEntityKey(employeesEntity.getJobCode()));
                dto.setJobDTO(jobDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        IBasicDTO bgtTypesDTO;
        if (employeesEntity.getBgttypeCode() != null) {
            IBgtTypesEntityKey bgtTEk = BgtEntityKeyFactory.createBgtTypesEntityKey(employeesEntity.getBgttypeCode());
            try {
                bgtTypesDTO = BgtClientFactory.getBgtTypesClient().getById(bgtTEk);
                dto.setBgtTypesDTO((IBgtTypesDTO)bgtTypesDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (employeesEntity.getWrkCode() != null && employeesEntity.getMinCode() != null) {
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                dto.setWorkCenterDTO(OrgClientFactory.getWorkCentersClient().getById(wEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static ISimpleEmployeesDTO getSimpleEmployeesDTOForCER(EmployeesEntity employeesEntity) {
        ISimpleEmployeesDTO dto = EmpDTOFactory.createSimpleEmployeesDTO();
        fillSimpleEmployeesDTOForCER(dto, employeesEntity);
        return dto;
    }

    public static void fillSimpleEmployeesDTOForCER(ISimpleEmployeesDTO dto, EmployeesEntity employeesEntity) {

        Long civilId = employeesEntity.getCivilId();
        IEntityKey employeesEnitityKey = EmpEntityKeyFactory.createEmployeesEntityKey(civilId);
        dto.setCode(employeesEnitityKey);
        dto.setRealCivilId(employeesEntity.getRealCivilId());

        if (employeesEntity.getCitizensResidentsEntity() != null) {
            EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity = employeesEntity.getCitizensResidentsEntity();

            String fullCivilName =
                kwtCitizensResidentsEntity.getFirstName() + " " + kwtCitizensResidentsEntity.getSecondName() + " " +
                kwtCitizensResidentsEntity.getThirdName() + " " + kwtCitizensResidentsEntity.getLastName();
            if (kwtCitizensResidentsEntity.getFamilyName() != null) {
                fullCivilName = fullCivilName + " " + kwtCitizensResidentsEntity.getFamilyName();
            }
            dto.setName(fullCivilName);
            dto.setCapStatusCode(kwtCitizensResidentsEntity.getCapstatusCode());
            if (kwtCitizensResidentsEntity.getNationality() != null) {
                if (kwtCitizensResidentsEntity.getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY)) {
                    dto.setKuwaiti(true);
                } else {
                    dto.setKuwaiti(false);
                }
            }
        }
        
        dto.setMinistryCode(employeesEntity.getMinCode());
        if (employeesEntity.getHireStatusEntity() != null) {
            dto.setEmpHireStatus(employeesEntity.getHireStatusEntity().getHirstatusCode());
        }
        dto.setActiveFlag(employeesEntity.getActiveFlag());

    }

    public static IEmployeesDTO getEmployeesDTOForFTQ(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOForFTQ(dto, employeesEntity);
        return dto;
    }

    public static void fillEmployeesDTOForFTQ(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        dto.setAuditStatus(employeesEntity.getAuditStatus());
        dto.setTabrecSerial(employeesEntity.getTabrecSerial());
        dto.setMinCode(employeesEntity.getMinCode());
        dto.setMinistriesDTO((IMinistriesDTO)getMinistriesDTO(employeesEntity.getMinCode()));
        dto.setMinistryFileNo(employeesEntity.getMinistryFileNo());
        dto.setHireDate(employeesEntity.getHireDate());
        dto.setEndJobDate(employeesEntity.getEndJobDate());
        if (employeesEntity.getHireStatusEntity() != null) {
            dto.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO((employeesEntity.getHireStatusEntity()).getHirstatusCode(),
                                                                   (employeesEntity.getHireStatusEntity()).getHirstatusName()));
        }
        IKwtCitizensResidentsEntityKey kEk =
            InfEntityKeyFactory.createKwtCitizensResidentsEntityKey(employeesEntity.getRealCivilId());
        try {
            dto.setCitizensResidentsDTO(InfClientFactory.getKwtCitizensResidentsClient().getKwtCitizensResidents(kEk));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (employeesEntity.getWrkCode() != null && employeesEntity.getMinCode() != null) {
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                IBasicDTO workdto = OrgClientFactory.getWorkCentersClient().getCodeNameWrkCenter(wEk);
                dto.setWorkCenterDTO(workdto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (employeesEntity.getJobCode() != null) {
            try {
                IBasicDTO jobDTO;
                jobDTO =
                        JobClientFactory.getJobsClient().getSimpleJobById(JobEntityKeyFactory.createJobsEntityKey(employeesEntity.getJobCode()));
                dto.setJobDTO(jobDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setUserInfoData(dto, employeesEntity);
    }
    
    public static IEmployeesDTO getEmployeesDTOForPRM(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOForPRM(dto, employeesEntity);
        return dto;
    }

    public static void fillEmployeesDTOForPRM(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        dto.setMinCode(employeesEntity.getMinCode());
        dto.setMinistryFileNo(employeesEntity.getMinistryFileNo());
        dto.setCscFileNo(employeesEntity.getCscFileNo());
        if (employeesEntity.getWrkCode() != null && employeesEntity.getMinCode() != null) {
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                IBasicDTO workdto = OrgClientFactory.getWorkCentersClient().getCodeNameWrkCenter(wEk);
                dto.setWorkCenterDTO(workdto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        dto.setSalEmpSalaryElementsDTO(getPayrollInfo(employeesEntity.getRealCivilId()));
    }
    public static ISalEmpSalaryElementsDTO getPayrollInfo(Long civilId)  {
        ISalEmpSalaryElementsDTO salEmpSalaryElementsDTO = null;
        try {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<    getPayrollInfo >>>>>>>>>>>>>>>>>>>>>>  civilId : "+civilId);
            salEmpSalaryElementsDTO =
                    (ISalEmpSalaryElementsDTO)SalClientFactory.getSalEmpSalaryElementsClient().getEmpRaiseByCivilAndType(civilId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return salEmpSalaryElementsDTO;
    }
    
    protected static IBasicDTO getMinistriesDTO(Long minCode) {
        try {
            return OrgClientFactory.getMinistriesClient().getSimpleMinistryDTO(minCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IEmpInternalExperienceDTO getEmpInternalExperienceDTO_ForAOE(EmpInternalExperienceEntity empInternalExperienceEntity) {
        IEmpInternalExperienceDTO empInternalExperienceDTO = new EmpInternalExperienceDTO();
        fillEmpInternalExperienceDTO_ForAOE(empInternalExperienceDTO, empInternalExperienceEntity);
        return empInternalExperienceDTO;
    }

    public static IEmpInternalExperienceDTO fillEmpInternalExperienceDTO_ForAOE(IEmpInternalExperienceDTO empInternalExperienceDTO,
                                                                                EmpInternalExperienceEntity empInternalExperienceEntity) {
        empInternalExperienceDTO.setCode(EmpEntityKeyFactory.createEmpInternalExperienceEntityKey(empInternalExperienceEntity.getSerial()));
        empInternalExperienceDTO.setCivilId(empInternalExperienceEntity.getCivilId());
        empInternalExperienceDTO.setActionDate(empInternalExperienceEntity.getActionDate());
        empInternalExperienceDTO.setPisJobCode(empInternalExperienceEntity.getPisJobCode());
        empInternalExperienceDTO.setPisWrkCode(empInternalExperienceEntity.getPisWrkCode());
        empInternalExperienceDTO.setJobDetail(empInternalExperienceEntity.getJobDetail());
        empInternalExperienceDTO.setRevFlag(empInternalExperienceEntity.getRevFlag());
        empInternalExperienceDTO.setRecordSourceFlag(empInternalExperienceEntity.getRecordSourceFlag());
        empInternalExperienceDTO.setAuditStatus(empInternalExperienceEntity.getAuditStatus());
        empInternalExperienceDTO.setTabrecSerial(empInternalExperienceEntity.getTabrecSerial());
        empInternalExperienceDTO.setCreatedBy(empInternalExperienceEntity.getCreatedBy());
        empInternalExperienceDTO.setCreatedDate(empInternalExperienceEntity.getCreatedDate());
        empInternalExperienceDTO.setLastUpdatedBy(empInternalExperienceEntity.getLastUpdatedBy());
        empInternalExperienceDTO.setLastUpdatedDate(empInternalExperienceEntity.getLastUpdatedDate());

        empInternalExperienceDTO.setAoeAuditFlag(empInternalExperienceEntity.getAoeAuditFlag());
        empInternalExperienceDTO.setAoeAuditDate(empInternalExperienceEntity.getAoeAuditDate());
        empInternalExperienceDTO.setAoeAuditBy(empInternalExperienceEntity.getAoeAuditBy());
        empInternalExperienceDTO.setAoeApprovedFlag(empInternalExperienceEntity.getAoeApprovedFlag());
        empInternalExperienceDTO.setAoeApprovedDate(empInternalExperienceEntity.getAoeApprovedDate());
        empInternalExperienceDTO.setAoeApprovedBy(empInternalExperienceEntity.getAoeApprovedBy());
        if (empInternalExperienceEntity.getAoeApprovedBy() != null) {

            try {
                empInternalExperienceDTO.setAoeApprovedBy_name(EmpClientFactory.getEmployeesClient().getUserName(Long.valueOf(empInternalExperienceEntity.getAoeApprovedBy())));
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }

        if (empInternalExperienceEntity.getAoeAuditBy() != null) {

            try {
                empInternalExperienceDTO.setAoeAuditBy_name(EmpClientFactory.getEmployeesClient().getUserName(Long.valueOf(empInternalExperienceEntity.getAoeAuditBy())));
            } catch (DataBaseException e) {
            } catch (SharedApplicationException e) {
            }
        }
        try {
            if (empInternalExperienceEntity.getJobCode() != null) {
                IJobsEntityKey jobEk =
                    JobEntityKeyFactory.createJobsEntityKey(empInternalExperienceEntity.getJobCode());
                empInternalExperienceDTO.setJobsDTO(JobClientFactory.getJobsClient().getById(jobEk));
            }
            if (empInternalExperienceEntity.getMinCode() != null) {
                IMinistriesEntityKey minEk =
                    OrgEntityKeyFactory.createMinistriesEntityKey(empInternalExperienceEntity.getMinCode());
                empInternalExperienceDTO.setMinDTO(OrgClientFactory.getMinistriesClient().getById(minEk));
            }
            if (empInternalExperienceEntity.getMinCode() != null && empInternalExperienceEntity.getWrkCode() != null) {
                IWorkCentersEntityKey wrkEk =
                    OrgEntityKeyFactory.createWorkCentersEntityKey(empInternalExperienceEntity.getMinCode(),
                                                                   empInternalExperienceEntity.getWrkCode());
                empInternalExperienceDTO.setWorkCenterDTO(OrgClientFactory.getWorkCentersClient().getById(wrkEk));
            }
            //        if (empInternalExperienceEntity.getBgttypeCode() != null) {
            //            IBgtTypesEntityKey bgtTypeEk=BgtEntityKeyFactory.createBgtTypesEntityKey(empInternalExperienceEntity.getBgttypeCode());
            //            empInternalExperienceDTO.setBgtTypesDTO(BgtClientFactory.getBgtTypesClient().getById(bgtTypeEk) );
            //        }
            if (empInternalExperienceEntity.getBgtprgCode() != null) {
                IBgtProgramsEntityKey pEk =
                    BgtEntityKeyFactory.createBgtProgramsEntityKey(empInternalExperienceEntity.getBgtprgCode());
                empInternalExperienceDTO.setBgtProgramsDTO(BgtClientFactory.getBgtProgramsClient().getById(pEk));
            }
            if (empInternalExperienceEntity.getTrxTypesEntity() != null) {
                empInternalExperienceDTO.setTrxTypesDTO(EmpDTOFactory.createTrxTypesDTO(empInternalExperienceEntity.getTrxTypesEntity()));
            }
            if (empInternalExperienceEntity.getElmguideCode() != null) {
                ISalElementGuidesEntityKey salEk =
                    SalEntityKeyFactory.createSalElementGuidesEntityKey(empInternalExperienceEntity.getElmguideCode());
                empInternalExperienceDTO.setSalElementGuidesDTO(SalClientFactory.getSalElementGuidesClient().getById(salEk));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return empInternalExperienceDTO;
    }

    //ESRV

    public static IEmployeesDTO getEmployeesDTOForESRV(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOForESRV(dto, employeesEntity);
        return dto;
    }

    public static void fillEmployeesDTOForESRV(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        System.out.println("fillEmployeesDTOForESRVfillEmployeesDTOForESRV");
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        dto.setCitizensResidentsDTO(getKwtCitizensResidentsDTOCodeNameOnly(employeesEntity.getRealCivilId()));
        dto.setAuditStatus(employeesEntity.getAuditStatus());
        dto.setTabrecSerial(employeesEntity.getTabrecSerial());
        dto.setMinCode(employeesEntity.getMinCode());
        System.out.println("fillEmployeesDTOForESRVfillEmployeesDTOForESRV" + employeesEntity.getJobCode());
        if (employeesEntity.getJobCode() != null) {
            try {
                IBasicDTO jobDTO;
                jobDTO =
                        JobClientFactory.getJobsClient().getById(JobEntityKeyFactory.createJobsEntityKey(employeesEntity.getJobCode()));
                dto.setJobDTO(jobDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("fillEmployeesDTOForESRVfillEmployeesDTOForESRV" + employeesEntity.getWrkCode());
        System.out.println("fillEmployeesDTOForESRVfillEmployeesDTOForESRV" + employeesEntity.getMinCode());
        if (employeesEntity.getWrkCode() != null && employeesEntity.getMinCode() != null) {
            IWorkCentersEntityKey wEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(employeesEntity.getMinCode(), employeesEntity.getWrkCode());
            try {
                dto.setWorkCenterDTO(OrgClientFactory.getWorkCentersClient().getById(wEk));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Long CurrentSystemDateInMillis = System.currentTimeMillis();
        Date currentDate = new Date(CurrentSystemDateInMillis);
        Long civilId = employeesEntity.getCivilId();
        
        IEmpRealSignClient empSignClient = new EmpRealSignClientImpl();
        Long empSignFound=null;

        try {
            empSignFound = empSignClient.getEmpSignId(employeesEntity.getMinCode(),
                                                         civilId, currentDate);
            dto.setEmpSignId(empSignFound);
        } catch (DataBaseException e) {
        } catch (SharedApplicationException e) {
        }
        setUserInfoData(dto, employeesEntity);
    }

    public static IEmployeesDTO getEmployeesDTOForEmpHelper(EmployeesEntity employeesEntity) {
        IEmployeesDTO employeesDTO = EmpDTOFactory.createEmployeesDTO();
        fillEmployeesDTOForEmpHelper(employeesDTO, employeesEntity);
        return employeesDTO;
    }

    public static void fillEmployeesDTOForEmpHelper(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        System.out.println(" EmpEntityConverter  fillEmployeesDTOForEmpHelper ");
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        //dto.setCitizensResidentsDTO(getKwtCitizensResidentsDTOCodeNameOnly(employeesEntity.getRealCivilId()));
        if (employeesEntity.getCitizensResidentsEntity() != null) {
            dto.setCitizensResidentsDTO(getKwtCitizensResidentsDTO(employeesEntity.getCitizensResidentsEntity()));
        }
        dto.setMinistryFileNo(employeesEntity.getMinistryFileNo());
        dto.setCscFileNo(employeesEntity.getCscFileNo());
        dto.setHireDate(employeesEntity.getHireDate());
        dto.setStartWorkingDate(employeesEntity.getStartWorkingDate());
        dto.setEndJobDate(employeesEntity.getEndJobDate());
        dto.setAuditStatus(employeesEntity.getAuditStatus());
        dto.setMinCode(employeesEntity.getMinCode());
        dto.setDateOfNextRaise(employeesEntity.getDateOfNextRaise());
        dto.setActiveFlag(employeesEntity.getActiveFlag());
        dto.setRecordDescCode(employeesEntity.getRecordDescCode());
        dto.setTabrecSerial(employeesEntity.getTabrecSerial());
        dto.setSocialInsurNo(employeesEntity.getSocialInsurNo());
        if (employeesEntity.getHireStatusEntity() != null) {
            dto.setHireStatusDTO(EmpDTOFactory.createHireStatusDTO((employeesEntity.getHireStatusEntity()).getHirstatusCode(),
                                                                   (employeesEntity.getHireStatusEntity()).getHirstatusName()));
        }
        if (employeesEntity.getJobsEntity() != null) {
            EmpJobsEntity jobEntity = employeesEntity.getJobsEntity();
            IJobsDTO jobDTO = JobDTOFactory.createJobsDTO(jobEntity.getJobCode(), jobEntity.getJobName());
            jobDTO.setJobKey(jobEntity.getJobKey());
            if (jobEntity.getCatCode() != null) {
                jobDTO.setCatsDTO((ICatsDTO)getCat(jobEntity.getCatCode()));
            }
            dto.setJobDTO(jobDTO);
        }
        if (employeesEntity.getWorkCentersEntity() != null) {
            EmpWorkCentersEntity workCentersEntity = employeesEntity.getWorkCentersEntity();
            dto.setWorkCenterDTO(getWorkCentersDTO(workCentersEntity));
        }
        if (employeesEntity.getTechJobsEntity() != null) {
            EmpJobsEntity tech_jobEntity = employeesEntity.getTechJobsEntity();
            IJobsDTO tech_jobDTO =
                JobDTOFactory.createJobsDTO(tech_jobEntity.getJobCode(), tech_jobEntity.getJobName());
            tech_jobDTO.setJobKey(tech_jobEntity.getJobKey());
            if (tech_jobEntity.getCatCode() != null) {
                tech_jobDTO.setCatsDTO((ICatsDTO)getCat(tech_jobEntity.getCatCode()));
            }
            dto.setTechJobsDTO(tech_jobDTO);
        }
        if (employeesEntity.getBgtTypesEntity() != null) {
            EmpBgtTypesEntity bgtTypesEntity = employeesEntity.getBgtTypesEntity();
            dto.setBgtTypesDTO(BgtDTOFactory.createBgtTypesDTO(bgtTypesEntity.getTypeCode(),
                                                               bgtTypesEntity.getTypeName()));
        }
        if (employeesEntity.getBgtProgramsEntity() != null) {
            EmpBgtProgramsEntity bgtProgramsEntity = employeesEntity.getBgtProgramsEntity();
            dto.setBgtProgramsDTO(BgtDTOFactory.createBgtProgramsDTO(bgtProgramsEntity.getPrgCode(),
                                                                     bgtProgramsEntity.getPrgName()));
        }
        setUserInfoData(dto, employeesEntity);
    }

    public static IKwtCitizensResidentsDTO getKwtCitizensResidentsDTO(EmpKwtCitizensResidentsEntity kwtCitizensResidentsEntity) {
        IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
            new KwtCitizensResidentsDTO(kwtCitizensResidentsEntity.getCivilId(),
                                        kwtCitizensResidentsEntity.getFirstName(),
                                        kwtCitizensResidentsEntity.getSecondName(),
                                        kwtCitizensResidentsEntity.getThirdName(),
                                        kwtCitizensResidentsEntity.getLastName(),
                                        kwtCitizensResidentsEntity.getFamilyName());
        kwtCitizensResidentsDTO.setName(kwtCitizensResidentsDTO.getFullName());
        kwtCitizensResidentsDTO.setCivilId(kwtCitizensResidentsEntity.getCivilId());
        kwtCitizensResidentsDTO.setGentypeCode(kwtCitizensResidentsEntity.getGentypeCode());
        kwtCitizensResidentsDTO.setNationality(kwtCitizensResidentsEntity.getNationality());
        MaritalStatusDTO maritalStatusDTO = new MaritalStatusDTO();
        if (kwtCitizensResidentsEntity.getMaritalStatusEntity() != null) {
            maritalStatusDTO.setCode(new MaritalStatusEntityKey(kwtCitizensResidentsEntity.getMaritalStatusEntity().getMarstatusCode()));
            maritalStatusDTO.setName(kwtCitizensResidentsEntity.getMaritalStatusEntity().getMarStatusName());
        }
        kwtCitizensResidentsDTO.setMaritalStatusDTO(maritalStatusDTO);
        GenderTypesDTO genderTypesDTO = new GenderTypesDTO();
        if (kwtCitizensResidentsEntity.getGenderTypesEntity() != null) {
            genderTypesDTO.setCode(new GenderTypesEntityKey(kwtCitizensResidentsEntity.getGenderTypesEntity().getGentypeCode()));
            genderTypesDTO.setName(kwtCitizensResidentsEntity.getGenderTypesEntity().getGentypeName());
            genderTypesDTO.setGentypeCode(kwtCitizensResidentsEntity.getGenderTypesEntity().getGentypeCode());
        }
        kwtCitizensResidentsDTO.setGenderTypesDTO(genderTypesDTO);
        kwtCitizensResidentsDTO.setGentypeCode(kwtCitizensResidentsEntity.getGentypeCode());
        kwtCitizensResidentsDTO.setBirthDate(kwtCitizensResidentsEntity.getBirthDate());
        GenderCountryDTO genderCountryDTO = new GenderCountryDTO();
        if (kwtCitizensResidentsEntity.getCountriesEntity() != null) {
            genderCountryDTO.setCode(new GenderCountryEntityKey(kwtCitizensResidentsEntity.getCountriesEntity().getGentypeCode(),
                                                                kwtCitizensResidentsEntity.getCountriesEntity().getCntryCode()));
            genderCountryDTO.setName(kwtCitizensResidentsEntity.getCountriesEntity().getGencntryName());
        } else if (kwtCitizensResidentsEntity.getGentypeCode().equals(3L)) {
            try {
                ICountriesEntityKey countriesEntityKey =
                    InfEntityKeyFactory.createCountriesEntityKey(kwtCitizensResidentsEntity.getNationality());
                ICountriesDTO countryDTO =
                    (ICountriesDTO)InfClientFactory.getCountriesClient().getCodeName(countriesEntityKey);
                genderCountryDTO.setCode(new GenderCountryEntityKey(3L, countriesEntityKey.getCntryCode()));
                genderCountryDTO.setName(countryDTO.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        kwtCitizensResidentsDTO.setCountriesDTO(genderCountryDTO);
        return kwtCitizensResidentsDTO;
    }

    public static IBasicDTO getCat(Long catCode) {
        try {
            return JobClientFactory.getCatsClient().getById(JobEntityKeyFactory.createCatsEntityKey(catCode));
        } catch (Exception e) {
            return null;
        }
    }

    public static IEmployeesDTO getBasicEmployeesDTOWithMinistry(EmployeesEntity employeesEntity) {
        IEmployeesDTO dto = EmpDTOFactory.createEmployeesDTO();
        fillBasicEmployeesDTOWithMinistry(dto, employeesEntity);
        return dto;
    }

    public static void fillBasicEmployeesDTOWithMinistry(IEmployeesDTO dto, EmployeesEntity employeesEntity) {
        dto.setCode(EmpEntityKeyFactory.createEmployeesEntityKey(employeesEntity.getCivilId()));
        dto.setRealCivilId(employeesEntity.getRealCivilId());
        dto.setAuditStatus(employeesEntity.getAuditStatus());
        dto.setTabrecSerial(employeesEntity.getTabrecSerial());
        dto.setMinCode(employeesEntity.getMinCode());
        if (employeesEntity.getMinCode() != null) {
            try {
                IBasicDTO ministriesDTO =
                    OrgClientFactory.getMinistriesClient().getCodeNameByMinCode(employeesEntity.getMinCode());
                IWorkCentersDTO workCentersDTO = OrgDTOFactory.createWorkCentersDTO();
                workCentersDTO.setMinistriesDTO((IMinistriesDTO)ministriesDTO);
                dto.setWorkCenterDTO(workCentersDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        setUserInfoData(dto, employeesEntity);
    }

    public static IWorkCentersDTO getWorkCentersDTO(EmpWorkCentersEntity workCentersEntity) {
        IWorkCentersDTO workCentersDTO =
            OrgDTOFactory.createWorkCentersDTO(workCentersEntity.getMinCode(), workCentersEntity.getWrkCode(),
                                               workCentersEntity.getWrkName());
        if (workCentersEntity.getMinistriesEntity() != null) {
            workCentersDTO.setMinistriesDTO(getMinistriesDTO(workCentersEntity.getMinistriesEntity()));
        }
        return workCentersDTO;
    }

    public static IMinistriesDTO getMinistriesDTO(EmpMinistriesEntity ministriesEntity) {
        IMinistriesDTO ministriesDTO =
            OrgDTOFactory.createMinistriesDTO(ministriesEntity.getMinCode(), ministriesEntity.getMinName());
        if (ministriesEntity.getCatsEntity() != null) {
            ministriesDTO.setCatsDTO(getCatsDTO(ministriesEntity.getCatsEntity()));
        }
        return ministriesDTO;
    }

    public static com.beshara.csc.nl.org.business.dto.ICatsDTO getCatsDTO(EmpCatsEntity catsEntity) {
        com.beshara.csc.nl.org.business.dto.ICatsDTO catsDTO =
            OrgDTOFactory.createCatsDTO(catsEntity.getCatCode(), catsEntity.getCatName());
        return catsDTO;
    }


    public static IAdminAssignmentsDTO getAdminAssignmentsDTO(AdminAssignmentsEntity adminAssignmentsEntity) {
        IAdminAssignmentsDTO dto = EmpDTOFactory.createAdminAssignmentsDTO();
        fillAdminAssignmentsDTO(dto, adminAssignmentsEntity);
        return dto;
}

    public static void fillAdminAssignmentsDTO(IAdminAssignmentsDTO dto,
                                               AdminAssignmentsEntity adminAssignmentsEntity) {
        dto.setCode(EmpEntityKeyFactory.createAdminAssignmentsEntityKey(adminAssignmentsEntity.getAdmassignmentSerial()));
        dto.setFromDate(adminAssignmentsEntity.getFromDate());
        dto.setUntilDate(adminAssignmentsEntity.getUntilDate());
        dto.setCreatedBy(adminAssignmentsEntity.getCreatedBy());
        dto.setCreatedDate(adminAssignmentsEntity.getCreatedDate());
        dto.setLastUpdatedBy(adminAssignmentsEntity.getLastUpdatedBy());
        dto.setLastUpdatedDate(adminAssignmentsEntity.getLastUpdatedDate());
        dto.setAuditStatus(adminAssignmentsEntity.getAuditStatus());
        dto.setTabrecSerial(adminAssignmentsEntity.getTabrecSerial());
        dto.setEmployeesDTO(EmpEntityConverter.getEmployeesDTOCodeName2(adminAssignmentsEntity.getEmployeesEntity()));
        try {
            IWorkCentersEntityKey wrkEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(adminAssignmentsEntity.getEmployeesEntity().getMinCode(),
                                                               adminAssignmentsEntity.getEmployeesEntity().getWrkCode());
            dto.getEmployeesDTO().setJobDTO(JobClientFactory.getJobsClient().getJobCodeName(adminAssignmentsEntity.getEmployeesEntity().getJobCode()));
            dto.getEmployeesDTO().setWorkCenterDTO(OrgClientFactory.getWorkCentersClient().getCodeNameWrkCenter(wrkEk));

            dto.setAssignStatusDTO(new AssignStatusDTO(adminAssignmentsEntity.getAssignStatusEntity()));
            dto.setJobsDTO(JobClientFactory.getJobsClient().getJobCodeName(adminAssignmentsEntity.getJobCode()));
            IWorkCentersEntityKey workEk =
                OrgEntityKeyFactory.createWorkCentersEntityKey(adminAssignmentsEntity.getMinCode(),
                                                               adminAssignmentsEntity.getWrkCode());
            dto.setWorkCentersDTO((IWorkCentersDTO)OrgClientFactory.getWorkCentersClient().getCodeNameWrkCenter(workEk));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static IEmpCriminalStatusDTO getEmpCriminalStatusDTO(EmpCriminalStatusEntity empCriminalStatusEntity) throws DataBaseException,
                                                                                                                        SharedApplicationException {

        IEmpCriminalStatusDTO dto = EmpDTOFactory.createEmpCriminalStatusDTO();
        fillEmpCriminalStatusDTO(dto, empCriminalStatusEntity);
        return dto;
    }

    public static void fillEmpCriminalStatusDTO(IEmpCriminalStatusDTO dto,
                                                EmpCriminalStatusEntity empCriminalStatusEntity) {

        IEmpCriminalStatusEntityKey ek =
            EmpEntityKeyFactory.createEmpCriminalStatusEntityKey(empCriminalStatusEntity.getCrmstatusCode());

        dto.setCode(ek);
        dto.setCrmstatusCode(empCriminalStatusEntity.getCrmstatusCode());
        dto.setName(empCriminalStatusEntity.getName());


    }

    public static IEmpMoiWsCriminalDTO getEmpMoiWsCriminalDTO(EmpMoiWsCriminalEntity empMoiWsCriminalEntity) throws DataBaseException,
                                                                                                                    SharedApplicationException {

        IEmpMoiWsCriminalDTO dto = EmpDTOFactory.createEmpMoiWsCriminalDTO();
        fillEmpMoiWsCriminalDTO(dto, empMoiWsCriminalEntity);
        return dto;
    }

    public static void fillEmpMoiWsCriminalDTO(IEmpMoiWsCriminalDTO dto,
                                               EmpMoiWsCriminalEntity empMoiWsCriminalEntity) {

        IEmpMoiWsCriminalEntityKey ek =
            EmpEntityKeyFactory.createEmpMoiWsCriminalEntityKey(empMoiWsCriminalEntity.getCrmWsSerial());

        dto.setCode(ek);
        dto.setCrmWsSerial(empMoiWsCriminalEntity.getCrmWsSerial());
        dto.setUserCode(empMoiWsCriminalEntity.getUserCode());
        dto.setCivilId(empMoiWsCriminalEntity.getCivilId());
        dto.setCrmstatusCode(empMoiWsCriminalEntity.getCrmstatusCode());
        dto.setLastLoginDate(empMoiWsCriminalEntity.getLastLoginDate());


    }

    public static IEmpMinEdtypesDTO getEmpMinEdtypesDTO(EmpMinEdtypesEntity empMinEdtypesEntity) throws DataBaseException,SharedApplicationException {
    
    IEmpMinEdtypesDTO dto = EmpDTOFactory.createEmpMinEdtypesDTO();
    fillEmpMinEdtypesDTO(dto, empMinEdtypesEntity);
    return dto; 
    }
    
    public static void fillEmpMinEdtypesDTO (IEmpMinEdtypesDTO dto, EmpMinEdtypesEntity empMinEdtypesEntity)  {
    
    IEmpMinEdtypesEntityKey ek =  EmpEntityKeyFactory.createEmpMinEdtypesEntityKey(empMinEdtypesEntity.getMinCode(),empMinEdtypesEntity.getExtdattypeCode());
    
    dto.setCode(ek);
    dto.setMinCode ( empMinEdtypesEntity.getMinCode()); 
    dto.setExtdattypeCode ( empMinEdtypesEntity.getExtdattypeCode()); 
    dto.setMinName(empMinEdtypesEntity.getEmpMinistriesEntity().getMinName());
    dto.setEmpExtraDataTypesDTO(getEmployeeExtraDataTypesDTO(empMinEdtypesEntity.getEmpExtraDataTypesEntity()));
     }
    
    

}