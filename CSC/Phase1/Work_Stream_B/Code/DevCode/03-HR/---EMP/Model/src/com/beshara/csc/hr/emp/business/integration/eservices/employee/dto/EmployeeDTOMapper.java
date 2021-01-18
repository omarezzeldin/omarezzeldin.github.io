package com.beshara.csc.hr.emp.business.integration.eservices.employee.dto;


import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.inf.business.client.IHandicapStatusClient;
import com.beshara.csc.inf.business.client.IKwStreetsClient;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.client.KwStreetsClientImpl;
import com.beshara.csc.inf.business.dto.IKwMapDTO;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IHandicapStatusEntityKey;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.inf.business.entity.KwStreetsEntityKey;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.Date;

public class EmployeeDTOMapper {

    private EmployeeDTOMapper() {

    }

    /**
     * Gets the employee dto of CSC
     *
     * @param realCivilId the real civil id
     * @return the employees dto
     */
    public static IBasicDTO getEmployeesDTO(Long realCivilId) {
        try {
            return EmpClientFactory.getEmployeesClient().getByRealCivilId(realCivilId,
                                                                          CSCSecurityInfoHelper.getLoggedInMinistryCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the employee dto from the retrived employee dto from CSC
     *
     * @param employeesDTO the employees dto
     * @param reqEmp the req emp
     * @return the employee dto
     */
    public static EmployeeDTO getEmployeeDTO(IEmployeesDTO employeesDTO, EmployeeDTO reqEmp) {

        // Employee Civil Info
        reqEmp.setRealCivilId(employeesDTO.getRealCivilId());
        reqEmp.setCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
        reqEmp.setHireDate(employeesDTO.getHireDate());
        if (employeesDTO.getCitizensResidentsDTO() != null) {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
            // Employee Birth Date Info
            Date birthDate =
                new Date(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getBirthDate().getTime());
            reqEmp.setBirthDate(birthDate);
            // Employee Name Info
            reqEmp.setFirstName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFirstName());
            reqEmp.setSecondName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getSecondName());
            reqEmp.setThirdName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getThirdName());
            reqEmp.setFamilyName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFamilyName());
            reqEmp.setEmployeeFullName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFullName());
            reqEmp.setEnglishName(employeesDTO.getEngName());
            // Employee Contact Info
            reqEmp.setPhonesNo(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getPhonesNo());
            reqEmp.setMobileNo(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getMobileNo());
            reqEmp.setEMail(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getEMail());
            reqEmp.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());
            // Employee Gender Info
            reqEmp.setGentypeCode(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGentypeCode());
            if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGenderTypesDTO() != null) {
                reqEmp.setGentypeName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGenderTypesDTO().getName());
            }
            // Employee Nationality Info
            reqEmp.setNationalityCode(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getNationality());
            if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getCountriesDTO() != null) {
                reqEmp.setNationalityName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getCountriesDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
                reqEmp.setReligonName(kwtCitizensResidentsDTO.getReligionsDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
                reqEmp.setMaritalStatusName(kwtCitizensResidentsDTO.getMaritalStatusDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getKwMapDTO() != null) {
                if (kwtCitizensResidentsDTO.getKwMapDTO().getTreeLevel().equals(1L)) {
                    reqEmp.setCityCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                    reqEmp.setCityName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                }
                if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getKwMapDTO().getTreeLevel().equals(2L)) {
                    reqEmp.setCityCode(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getCode().getKey());
                    reqEmp.setCityName(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getName());
                    reqEmp.setStatusCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                    reqEmp.setStatusName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                }
                if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getKwMapDTO().getTreeLevel().equals(3L)) {
                    reqEmp.setCityCode(((IKwMapDTO)kwtCitizensResidentsDTO.getKwMapDTO().getParentObject()).getParentObject().getCode().getKey());
                    reqEmp.setCityName(((IKwMapDTO)kwtCitizensResidentsDTO.getKwMapDTO().getParentObject()).getParentObject().getName());
                    reqEmp.setStatusCode(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getCode().getKey());
                    reqEmp.setStatusName(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getName());
                    reqEmp.setPieceCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                    reqEmp.setPieceName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                }
            }
            reqEmp.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());

            reqEmp.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
            reqEmp.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
            reqEmp.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
            reqEmp.setSendSMSFlag(kwtCitizensResidentsDTO.getSendSmsFlag());    
            if (kwtCitizensResidentsDTO.getCapstatusCode() != null) {
                IHandicapStatusClient hcStatusClient = InfClientFactory.getHandicapStatusClient();
                IHandicapStatusEntityKey ek =
                    InfEntityKeyFactory.createHandicapStatusEntityKey(kwtCitizensResidentsDTO.getCapstatusCode());
                IBasicDTO capStatusDTO;
                try {
                    capStatusDTO = hcStatusClient.getById(ek);
                    reqEmp.setHealthStatus(capStatusDTO.getName());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
            }
        }
        // Employee Ministry and Work center Info
        if (employeesDTO.getJobDTO() != null) {
            reqEmp.setJobCode(((IJobsDTO)employeesDTO.getJobDTO()).getJobKey());
            reqEmp.setJobName(((IJobsDTO)employeesDTO.getJobDTO()).getName());
            reqEmp.setActivateJobDate(new Date(((IJobsDTO)employeesDTO.getJobDTO()).getActivateDate().getTime()));
            if (((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO() != null) {
                reqEmp.setCatCode(Long.valueOf(((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO().getFirstParent().getKey()));
                reqEmp.setCatName(((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO().getName());
            }
        }
        if (employeesDTO.getWorkCenterDTO() != null) {
            // Employee work center Info
            reqEmp.setWrkCode(((IWorkCentersEntityKey)((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getCode()).getWrkCode());
            reqEmp.setWrkName(((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getName());
            // Employee ministry Info
            reqEmp.setMinCode(((IWorkCentersEntityKey)((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getCode()).getMinCode());
            reqEmp.setMinName(((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getMinistriesDTO().getName());
        }
        if (employeesDTO.getHireStatusDTO() != null) {
            reqEmp.setHireStatusCode(Long.valueOf(employeesDTO.getHireStatusDTO().getCode().getKey()));
            reqEmp.setHireStatusName(employeesDTO.getHireStatusDTO().getName());
        }
        return reqEmp;
    }


    public static EmployeeDTO getEmployeeWithAdressDTO(IEmployeesDTO employeesDTO, EmployeeDTO reqEmp) {

        // Employee Civil Info
        reqEmp.setRealCivilId(employeesDTO.getRealCivilId());
        reqEmp.setCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
        reqEmp.setHireDate(employeesDTO.getHireDate());
        reqEmp.setStartWorkingDate(employeesDTO.getStartWorkingDate());
        reqEmp.setMinistryFileNo(employeesDTO.getMinistryFileNo());
        reqEmp.setCscFileNo(employeesDTO.getCscFileNo());
        reqEmp.setDateOfNextRaise(employeesDTO.getDateOfNextRaise());
        if (employeesDTO.getCitizensResidentsDTO() != null) {
            IKwtCitizensResidentsDTO kwtCitizensResidentsDTO =
                (IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO();
            // Employee Birth Date Info
            Date birthDate =
                new Date(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getBirthDate().getTime());
            reqEmp.setBirthDate(birthDate);
            // Employee Name Info
            reqEmp.setFirstName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFirstName());
            reqEmp.setSecondName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getSecondName());
            reqEmp.setThirdName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getThirdName());
            reqEmp.setFamilyName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFamilyName());
            reqEmp.setEmployeeFullName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFullName());
            reqEmp.setEnglishName(employeesDTO.getEngName());
            reqEmp.setSendSMSFlag(kwtCitizensResidentsDTO.getSendSmsFlag());
            // Employee Contact Info
            reqEmp.setPhonesNo(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getPhonesNo());
            reqEmp.setMobileNo(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getMobileNo());
            reqEmp.setEMail(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getEMail());
            reqEmp.setAddressInDetails(kwtCitizensResidentsDTO.getAddressInDetails());
            // Employee Gender Info
            reqEmp.setGentypeCode(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGentypeCode());
            if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGenderTypesDTO() != null) {
                reqEmp.setGentypeName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getGenderTypesDTO().getName());
            }
            // Employee Nationality Info
            reqEmp.setNationalityCode(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getNationality());
            if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getCountriesDTO() != null) {
                reqEmp.setNationalityName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getCountriesDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getReligionsDTO() != null) {
                reqEmp.setReligonName(kwtCitizensResidentsDTO.getReligionsDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getMaritalStatusDTO() != null) {
                reqEmp.setMaritalStatusName(kwtCitizensResidentsDTO.getMaritalStatusDTO().getName());
            }
            if (kwtCitizensResidentsDTO.getKwMapDTO() != null) {
                if (kwtCitizensResidentsDTO.getKwMapDTO().getTreeLevel().equals(1L)) {
                    reqEmp.setCityCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                    reqEmp.setCityName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                }
                if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getKwMapDTO().getTreeLevel().equals(2L)) {
                    reqEmp.setCityCode(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getCode().getKey());
                    reqEmp.setCityName(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getName());
                    reqEmp.setStatusCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                    reqEmp.setStatusName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                }
                if (((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getKwMapDTO().getTreeLevel().equals(3L)) {
                    reqEmp.setCityCode(((IKwMapDTO)kwtCitizensResidentsDTO.getKwMapDTO().getParentObject()).getParentObject().getCode().getKey());
                    reqEmp.setCityName(((IKwMapDTO)kwtCitizensResidentsDTO.getKwMapDTO().getParentObject()).getParentObject().getName());
                    reqEmp.setStatusCode(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getCode().getKey());
                    reqEmp.setStatusName(kwtCitizensResidentsDTO.getKwMapDTO().getParentObject().getName());
                    reqEmp.setPieceCode(kwtCitizensResidentsDTO.getKwMapDTO().getCode().getKey());
                    reqEmp.setPieceName(kwtCitizensResidentsDTO.getKwMapDTO().getName());
                }
            }
            reqEmp.setStreetCode(kwtCitizensResidentsDTO.getStreetCode());
            if (kwtCitizensResidentsDTO.getStreetCode() != null) {
                IKwStreetsClient KwStreetsClientImpl = new KwStreetsClientImpl();
                try {
                    IBasicDTO streetDTO =
                        KwStreetsClientImpl.getById(new KwStreetsEntityKey(kwtCitizensResidentsDTO.getStreetCode()));
                    reqEmp.setStreeName(streetDTO.getName());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
            }
            reqEmp.setBuildingNo(kwtCitizensResidentsDTO.getBuildingNo());
            reqEmp.setFloorNo(kwtCitizensResidentsDTO.getFloorNo());
            reqEmp.setFlatNo(kwtCitizensResidentsDTO.getFlatNo());
            if (kwtCitizensResidentsDTO.getCapstatusCode() != null) {
                IHandicapStatusClient hcStatusClient = InfClientFactory.getHandicapStatusClient();
                IHandicapStatusEntityKey ek =
                    InfEntityKeyFactory.createHandicapStatusEntityKey(kwtCitizensResidentsDTO.getCapstatusCode());
                IBasicDTO capStatusDTO;
                try {
                    capStatusDTO = hcStatusClient.getById(ek);
                    reqEmp.setHealthStatus(capStatusDTO.getName());
                } catch (DataBaseException e) {
                    e.printStackTrace();
                } catch (SharedApplicationException e) {
                    e.printStackTrace();
                }
            }
        }
        // Employee Ministry and Work center Info
        if (employeesDTO.getJobDTO() != null) {
            reqEmp.setJobCode(((IJobsDTO)employeesDTO.getJobDTO()).getJobKey());
            reqEmp.setJobName(((IJobsDTO)employeesDTO.getJobDTO()).getName());
            reqEmp.setActivateJobDate(new Date(((IJobsDTO)employeesDTO.getJobDTO()).getActivateDate().getTime()));
            if (((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO() != null) {
                reqEmp.setCatCode(Long.valueOf(((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO().getFirstParent().getKey()));
                reqEmp.setCatName(((IJobsDTO)employeesDTO.getJobDTO()).getCatsDTO().getName());
            }
        }
        if (employeesDTO.getWorkCenterDTO() != null) {
            // Employee work center Info
            reqEmp.setWrkCode(((IWorkCentersEntityKey)((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getCode()).getWrkCode());
            reqEmp.setWrkName(((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getName());
            // Employee ministry Info
            reqEmp.setMinCode(((IWorkCentersEntityKey)((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getCode()).getMinCode());
            reqEmp.setMinName(((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getMinistriesDTO().getName());
        }
        if (employeesDTO.getHireStatusDTO() != null) {
            reqEmp.setHireStatusCode(Long.valueOf(employeesDTO.getHireStatusDTO().getCode().getKey()));
            reqEmp.setHireStatusName(employeesDTO.getHireStatusDTO().getName());
        }
        return reqEmp;
    }

    public static EmployeeDTO getEmployeeSimpleDTO(IEmployeesDTO employeesDTO, EmployeeDTO reqEmp) {
        reqEmp.setRealCivilId(employeesDTO.getRealCivilId());
        reqEmp.setCivilId(((IEmployeesEntityKey)employeesDTO.getCode()).getCivilId());
        reqEmp.setEmpSignId(employeesDTO.getEmpSignId());
        if (employeesDTO.getCitizensResidentsDTO() != null) {
            // Employee Name Info
            reqEmp.setEmployeeFullName(((IKwtCitizensResidentsDTO)employeesDTO.getCitizensResidentsDTO()).getFullName());
            if (employeesDTO.getJobDTO() != null) {
                reqEmp.setJobCode(((IJobsDTO)employeesDTO.getJobDTO()).getJobKey());
                reqEmp.setJobName(employeesDTO.getJobDTO().getName());
            }
            if (employeesDTO.getWorkCenterDTO() != null) {
                // Employee work center Info
                reqEmp.setWrkCode(((IWorkCentersEntityKey)(employeesDTO.getWorkCenterDTO()).getCode()).getWrkCode());
                reqEmp.setWrkName(employeesDTO.getWorkCenterDTO().getName());
                // Employee ministry Info
                reqEmp.setMinCode(((IWorkCentersEntityKey)employeesDTO.getWorkCenterDTO().getCode()).getMinCode());
                reqEmp.setMinName(((IWorkCentersDTO)employeesDTO.getWorkCenterDTO()).getMinistriesDTO().getName());
            }
        }
        return reqEmp;
    }
}
