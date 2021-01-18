package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateProceduresDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpReasonDataEntity;
import com.beshara.csc.hr.emp.business.entity.HireProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.ProcedureResultsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class EmpCandidateProceduresDAO extends EmpBaseDAO {
    public EmpCandidateProceduresDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpCandidateProceduresDAO();
    }

    /**
     * if COUNT(1) return 0 query retrun 1 else retrun 0
     * @param serial
     * @return
     */
    public Long checkEmployeeRequiredProceduresStatus(Long serial,Long candCode) throws DataBaseException,
                                                                          SharedApplicationException {
        Vector result = null;
        Long returnedNumber = ISystemConstant.VIRTUAL_VALUE;
        try {
            String query =
                "SELECT DECODE(COUNT(1),0,1,0) FROM HR_EMP_HT_PROCEDURES HP, INF_KWT_CITIZENS_RESIDENTS E  , HR_EMP_CANDIDATES C " +
                "WHERE C.ACTIVE_FLAG=1 and C.CIVIL_ID = E.CIVIL_ID AND HP.HIRTYPE_CODE = C.HIRTYPE_CODE AND HP.STATUS = 1 " +
                "AND E.CIVIL_ID = " + serial +" AND C.CANDIDATE_CODE= " + candCode + " AND CNDSTATUS_CODE = 2 " +
                "AND ( (E.NATIONALITY = 101 AND HP.NATIONALITY_TYPE = 1) OR (E.NATIONALITY <>101 AND HP.NATIONALITY_TYPE = 2) OR (HP.NATIONALITY_TYPE = 3)) " +
                "AND ( (GENTYPE_CODE = 1 AND HP.GENDER_TYPE = 1) OR (GENTYPE_CODE = 2 AND HP.GENDER_TYPE = 2) OR  (HP.GENDER_TYPE = 3)) " +
                "AND NOT EXISTS (SELECT 1 FROM HR_EMP_CANDIDATE_PROCEDURES CP WHERE CP.HIRPROCEDURE_CODE = HP.HIRPROCEDURE_CODE " +
                "AND CP.CANDIDATE_CODE  = C.CANDIDATE_CODE AND CP.CANDIDATE_CODE_SEQ  = 1 AND CP.PRORESULT_CODE IN (1,3) )";
//            System.out.println("EmployeeRequiredProceduresStatus $$Kareem$$::" + query);
            result = (Vector)EM().createNativeQuery(query).getSingleResult();
            BigDecimal value = (BigDecimal)result.get(0);
            if (value != null) {
                returnedNumber = value.longValue();
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
        return returnedNumber;
    }

    public Long checkBeforeDeleteProcedure(Long hireProcedure, Long hireType) throws DataBaseException,
                                                                                     SharedApplicationException {

        Long count = 0L;

        try {
            StringBuilder query = new StringBuilder("SELECT COUNT(1) ");
            query.append("FROM HR_EMP_CANDIDATE_PROCEDURES CP, HR_EMP_CANDIDATES C ");
            query.append("WHERE CP.CANDIDATE_CODE  = C.CANDIDATE_CODE ");
            query.append("AND CP.CANDIDATE_CODE_SEQ  = C.CANDIDATE_CODE_SEQ  ");
            query.append(" AND C.HIRTYPE_CODE = " + hireType + " ");
            query.append("AND CP.HIRPROCEDURE_CODE = " + hireProcedure + "  ");
            System.out.println(query);
            Vector v = (Vector)EM().createNativeQuery(query.toString()).getSingleResult();
            if (v != null && v.size() != 0) {
                count = ((BigDecimal)v.get(0)).longValue();

            }
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

        return count;

    }

    /**<code>select o from EmployeeProceduresEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IEmpCandidateProceduresDTO> getAll() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<EmpCandidateProceduresEntity> list =
                EM().createNamedQuery("EmpCandidateProceduresEntity.findAll").getResultList();
            for (EmpCandidateProceduresEntity empCandidateProcedures : list) {
                arrayList.add(EmpDTOFactory.createEmpCandidateProceduresDTO(empCandidateProcedures));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
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

    /**
     * Create a New EmployeeProceduresEntity * @param empCandidateProceduresDTO
     * @return IempCandidateProceduresDTO
     */
    public IEmpCandidateProceduresDTO add(IBasicDTO empCandidateProceduresDTO1) throws DataBaseException,
                                                                                       SharedApplicationException {
        try {
            EmpCandidateProceduresEntity empCandidateProceduresEntity = new EmpCandidateProceduresEntity();
            IEmpCandidateProceduresDTO empCandidateProceduresDTO =
                (IEmpCandidateProceduresDTO)empCandidateProceduresDTO1;
            if (empCandidateProceduresDTO.getEmpCandidatesDTO() != null) {
                EmpCandidatesEntity EmpCandidateEntity =
                    EM().find(EmpCandidatesEntity.class, empCandidateProceduresDTO.getEmpCandidatesDTO().getCode());
                if (EmpCandidateEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setEmpCandidatesEntity(EmpCandidateEntity);
            }
            if (empCandidateProceduresDTO.getHireProceduresDTO() != null) {
                HireProceduresEntity hireProceduresEntity =
                    EM().find(HireProceduresEntity.class, empCandidateProceduresDTO.getHireProceduresDTO().getCode());
                if (hireProceduresEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setHireProceduresEntity(hireProceduresEntity);
            }

            if (empCandidateProceduresDTO.getProcedureResultsDTO() != null) {
                ProcedureResultsEntity procedureResultsEntity =
                    EM().find(ProcedureResultsEntity.class, empCandidateProceduresDTO.getProcedureResultsDTO().getCode());
                if (procedureResultsEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setProcedureResultsEntity(procedureResultsEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (empCandidateProceduresDTO.getEmpReasonDataDTO() != null) {
                EmpReasonDataEntity empReasonDataEntity =
                    EM().find(EmpReasonDataEntity.class, empCandidateProceduresDTO.getEmpReasonDataDTO().getCode());
                if (empReasonDataEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setEmpReasonDataEntity(empReasonDataEntity);
            }
            empCandidateProceduresEntity.setProcDesc(empCandidateProceduresDTO.getProcDesc());
            empCandidateProceduresEntity.setResultDate(empCandidateProceduresDTO.getResultDate());
            if(empCandidateProceduresDTO.getSendDate()!=null)
            empCandidateProceduresEntity.setSendDate(empCandidateProceduresDTO.getSendDate());
            else{
                empCandidateProceduresEntity.setSendDate(new Date(Calendar.getInstance().getTime().getTime()));
            }
            empCandidateProceduresEntity.setAuditStatus(empCandidateProceduresDTO.getAuditStatus());
            empCandidateProceduresEntity.setTabrecSerial(empCandidateProceduresDTO.getTabrecSerial());
            empCandidateProceduresEntity.setPostponeDate(empCandidateProceduresDTO.getPostponeDate());
            empCandidateProceduresEntity.setCrmLetterDate(empCandidateProceduresDTO.getCrmLetterDate());
            empCandidateProceduresEntity.setCrmLetterNo(empCandidateProceduresDTO.getCrmLetterNo());
            empCandidateProceduresEntity.setCrmSheetNo(empCandidateProceduresDTO.getCrmSheetNo());
            empCandidateProceduresEntity.setCrmStatusCode(empCandidateProceduresDTO.getCrmStatusCode());
                
            return EmpEntityConverter.getEmpCandidateProceduresDTO((EmpCandidateProceduresEntity)this.doAdd(empCandidateProceduresEntity));
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        // Set PK after creation

    }

    /**
     * Update an Existing EmployeeProceduresEntity * @param empCandidateProceduresDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO empCandidateProceduresDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidateProceduresDTO empCandidateProceduresDTO =
                (IEmpCandidateProceduresDTO)empCandidateProceduresDTO1;
            EmpCandidateProceduresEntity empCandidateProceduresEntity =
                EM().find(EmpCandidateProceduresEntity.class, empCandidateProceduresDTO.getCode());
            if (empCandidateProceduresDTO.getEmpCandidatesDTO() != null) {
                EmpCandidatesEntity EmpCandidateEntity =
                    EM().find(EmpCandidatesEntity.class, empCandidateProceduresDTO.getEmpCandidatesDTO().getCode());
                if (EmpCandidateEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setEmpCandidatesEntity(EmpCandidateEntity);
            }
            if (empCandidateProceduresDTO.getHireProceduresDTO() != null) {
                HireProceduresEntity hireProceduresEntity =
                    EM().find(HireProceduresEntity.class, empCandidateProceduresDTO.getHireProceduresDTO().getCode());
                if (hireProceduresEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setHireProceduresEntity(hireProceduresEntity);
            }

            if (empCandidateProceduresDTO.getProcedureResultsDTO() != null) {
                ProcedureResultsEntity procedureResultsEntity =
                    EM().find(ProcedureResultsEntity.class, empCandidateProceduresDTO.getProcedureResultsDTO().getCode());
                if (procedureResultsEntity == null)
                    throw new ItemNotFoundException();
                empCandidateProceduresEntity.setProcedureResultsEntity(procedureResultsEntity);
            } else {
                throw new ItemNotFoundException();
            }
            if (empCandidateProceduresDTO.getEmpReasonDataDTO() != null) {
                if (empCandidateProceduresDTO.getEmpReasonDataDTO().getCode() != null) {
                    EmpReasonDataEntity empReasonDataEntity =
                        EM().find(EmpReasonDataEntity.class, empCandidateProceduresDTO.getEmpReasonDataDTO().getCode());
                    if (empReasonDataEntity == null)
                        throw new ItemNotFoundException();
                    empCandidateProceduresEntity.setEmpReasonDataEntity(empReasonDataEntity);
                } else {
                    empCandidateProceduresEntity.setEmpReasonDataEntity(null);
                }

            }
            empCandidateProceduresEntity.setProcDesc(empCandidateProceduresDTO.getProcDesc());
            empCandidateProceduresEntity.setResultDate(empCandidateProceduresDTO.getResultDate());
            if(empCandidateProceduresDTO.getSendDate()!=null)
            empCandidateProceduresEntity.setSendDate(empCandidateProceduresDTO.getSendDate());
            else{
                empCandidateProceduresEntity.setSendDate(new Date(Calendar.getInstance().getTime().getTime()));
            }
            empCandidateProceduresEntity.setAuditStatus(empCandidateProceduresDTO.getAuditStatus());
            empCandidateProceduresEntity.setTabrecSerial(empCandidateProceduresDTO.getTabrecSerial());
            empCandidateProceduresEntity.setPostponeDate(empCandidateProceduresDTO.getPostponeDate());
            empCandidateProceduresEntity.setCrmLetterDate(empCandidateProceduresDTO.getCrmLetterDate());
            empCandidateProceduresEntity.setCrmLetterNo(empCandidateProceduresDTO.getCrmLetterNo());
            empCandidateProceduresEntity.setCrmSheetNo(empCandidateProceduresDTO.getCrmSheetNo());
            empCandidateProceduresEntity.setCrmStatusCode(empCandidateProceduresDTO.getCrmStatusCode());

            // updated by A.AGAMY for data audit
            doUpdate(empCandidateProceduresEntity);
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
     * Remove an Existing EmployeeProceduresEntity * @param empCandidateProceduresDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO empCandidateProceduresDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidateProceduresDTO empCandidateProceduresDTO =
                (IEmpCandidateProceduresDTO)empCandidateProceduresDTO1;
            EmpCandidateProceduresEntity employeeProceduresEntity =
                EM().find(EmpCandidateProceduresEntity.class, empCandidateProceduresDTO.getCode());
            if (employeeProceduresEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(employeeProceduresEntity);
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
     * Get EmpCandidateProceduresEntity By Primary Key * @param id
     * @return IEmpCandidateProceduresDTO
     */
    public IEmpCandidateProceduresDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            EmpCandidateProceduresEntity employeeProceduresEntity = EM().find(EmpCandidateProceduresEntity.class, id);
            if (employeeProceduresEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpCandidateProceduresDTO empCandidateProceduresDTO =
                EmpEntityConverter.getEmpCandidateProceduresDTO(employeeProceduresEntity);
            return empCandidateProceduresDTO;
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
     * Search for EmployeeProceduresEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }

    /**
     * Get all available hire procedure to an employee * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> listAvailableEntities(IEntityKey code, IEntityKey hireType) throws DataBaseException,
                                                                                              SharedApplicationException {

        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuilder ejbql =
                new StringBuilder("select distinct o.hireProcedureEntity from HireTypeProcedureEntity o where ");
            if (key != null) {
                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;
                    ejbql.append("  ( o.nationalityType=" + nationalityType + " OR o.nationalityType=" +
                                 ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( o.genderType=" +
                                 empCandidatesEntity.getCitizensResidentsEntity().getGentypeCode() +
                                 " OR o.genderType=" + ISystemConstant.GENDER_UNDEFINED + " ) AND o.hirtypeCode=" +
                                 ((IHireTypesEntityKey)hireType).getHirtypeCode() +
                                 " AND o.hireProcedureEntity.hirprocedureCode NOT IN ( SELECT emp.hirProcedureCode FROM EmpCandidateProceduresEntity emp WHERE emp.empCandidatesEntity.candidateCode=" +
                                 key.getCandidateCode() + " AND emp.empCandidatesEntity.candidateCodeSeq=" +
                                 IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }

            System.out.println(ejbql);
            Query query = EM().createQuery(ejbql.toString());
            List<HireProceduresEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HireProceduresEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireProceduresDTO(entity));
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
     * Filter all available hire procedure to an employee * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, String name,
                                                   IEntityKey hireType) throws DataBaseException,
                                                                               SharedApplicationException {

        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuilder ejbql =
                new StringBuilder("select o from HireProceduresEntity o where o.hirprocedureCode=o.hirprocedureCode ");
            if (name != null && name.length() > 0) {
                //By MLotfy new search
                //ejbql.append(" AND o.hirprocedureName LIKE '" + name + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirprocedureName", name) + " ) ");
            }
            if (key != null) {

                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;
                    ejbql.append("  AND o.hirprocedureCode NOT IN ( SELECT emp.hirprocedureCode FROM EmpCandidateProceduresEntity emp WHERE emp.empCandidatesEntity.candidateCode=" +
                                 key.getCandidateCode() + " AND emp.empCandidatesEntity.candidateCodeSeq=" +
                                 IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }
            Query query = EM().createQuery(ejbql.toString());
            System.out.println(ejbql.toString());
            List<HireProceduresEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HireProceduresEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireProceduresDTO(entity));
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
     * Filter all available hire procedure to an employee * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, IEntityKey hireType,
                                                   String name) throws DataBaseException, SharedApplicationException {
        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuilder ejbql =
                new StringBuilder("select distinct o.hireProcedureEntity from HireTypeProcedureEntity o where ");
            if (name != null && name.length() > 0) {
                //By MLotfy new search
                //ejbql.append(" AND o.hirprocedureName LIKE '" + name + "'");
                ejbql.append("  (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hireProcedureEntity.hirprocedureName",
                                                                                 name) + " ) ");
            }
            if (key != null) {

                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;
                    ejbql.append(" AND ( o.nationalityType=" + nationalityType + " OR o.nationalityType=" +
                                 ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( o.genderType=" +
                                 empCandidatesEntity.getCitizensResidentsEntity().getGentypeCode() +
                                 " OR o.genderType=" + ISystemConstant.GENDER_UNDEFINED + " ) AND o.hirtypeCode=" +
                                 ((IHireTypesEntityKey)hireType).getHirtypeCode() +
                                 " AND o.hireProcedureEntity.hirprocedureCode NOT IN ( SELECT emp.hirProcedureCode FROM EmpCandidateProceduresEntity emp WHERE emp.empCandidatesEntity.candidateCode=" +
                                 key.getCandidateCode() + " AND emp.empCandidatesEntity.candidateCodeSeq=" +
                                 IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }

            System.out.println(ejbql);
            Query query = EM().createQuery(ejbql.toString());
            List<HireProceduresEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HireProceduresEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireProceduresDTO(entity));
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

    public List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, Long hirProcedureCode,
                                                         IEntityKey hireType) throws DataBaseException,
                                                                                     SharedApplicationException {

        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuffer ejbql =
                new StringBuffer("select distinct o.hireProcedureEntity from HireTypeProcedureEntity o where");
            if (hirProcedureCode != null) {
                ejbql.append(" o.hireProcedureEntity.hirprocedureCode = " + hirProcedureCode);
            }
            if (key != null) {

                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;
                    ejbql.append(" AND ( o.nationalityType=" + nationalityType + " OR o.nationalityType=" +
                                 ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( o.genderType=" +
                                 empCandidatesEntity.getCitizensResidentsEntity().getGentypeCode() +
                                 " OR o.genderType=" + ISystemConstant.GENDER_UNDEFINED + " ) AND o.hirtypeCode=" +
                                 ((IHireTypesEntityKey)hireType).getHirtypeCode() +
                                 " AND o.hireProcedureEntity.hirprocedureCode NOT IN ( SELECT emp.hirProcedureCode FROM EmpCandidateProceduresEntity emp WHERE emp.empCandidatesEntity.candidateCode=" +
                                 key.getCandidateCode() + " AND emp.empCandidatesEntity.candidateCodeSeq=" +
                                 IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }

            System.out.println(ejbql);
            Query query = EM().createQuery(ejbql.toString());
            List<HireProceduresEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HireProceduresEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireProceduresDTO(entity));
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
     * list available entity By KwtCitizenCode * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            IKwtCitizensResidentsDTO dto = (IKwtCitizensResidentsDTO)paramDto;
            Long genderType =
                dto.getGentypeCode() != null ? dto.getGentypeCode() : ((IGenderTypesEntityKey)dto.getGenderTypesDTO().getCode()).getGentypeCode();
            StringBuilder ejbql =
                new StringBuilder("select distinct o.hireProcedureEntity from HireTypeProcedureEntity o where o.status=1  ");
            if (dto != null) {
                Long nationalityType =
                    dto.getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ? ISystemConstant.NATIONALITY_KUWAITI :
                    ISystemConstant.NATIONALITY_NON_KUWAITI;
                ejbql.append(" AND ( o.nationalityType=" + nationalityType + " OR o.nationalityType=" +
                             ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( o.genderType=" + genderType +
                             " OR o.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                             " ) AND o.hireTypesEntity.hirtypeCode=" +
                             ((IHireTypesEntityKey)hireType).getHirtypeCode());
            }
            System.out.println("EmpCandidateProceduresDAO.listAvailableEntitiesByKwtCitizenCode::" + ejbql.toString());
            Query query = EM().createQuery(ejbql.toString());
            List<HireProceduresEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HireProceduresEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireProceduresDTO(entity));
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
     * search in filtered available list by code or name
     * set value for name or code and another set null
     * @param paramDto
     * @param hireType
     * @param name
     * @param hirProcedureCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @auther Kareem Sayed
     */
    public List<IBasicDTO> filterAvailableEntities(IBasicDTO paramDto, IEntityKey hireType, String name,
                                                   Long hirProcedureCode) throws DataBaseException,
                                                                                 SharedApplicationException {

        try {
            IKwtCitizensResidentsDTO dto = (IKwtCitizensResidentsDTO)paramDto;
            StringBuilder ejbql =
                new StringBuilder("select distinct o.hireProcedureEntity from HireTypeProcedureEntity o where ");
            if (name != null && name.length() > 0) {
                ejbql.append(" (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hireProcedureEntity.hirprocedureName",
                                                                                 name) + " ) ");
            } else if (hirProcedureCode != null) {
                ejbql.append(" o.hireProcedureEntity.hirprocedureCode = " + hirProcedureCode);
            }
            if (dto != null) {
                Long nationalityType =
                    dto.getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ? ISystemConstant.NATIONALITY_KUWAITI :
                    ISystemConstant.NATIONALITY_NON_KUWAITI;
                ejbql.append(" AND ( o.nationalityType=" + nationalityType + " OR o.nationalityType=" +
                             ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( o.genderType=" +
                             dto.getGentypeCode() + " OR o.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                             " ) AND  o.hireTypesEntity.hirtypeCode=" +
                             ((IHireTypesEntityKey)hireType).getHirtypeCode());
            }
            System.out.println("EmpCandidateProceduresDAO.filterAvailableEntities::" + ejbql.toString());
            Query query = EM().createQuery(ejbql.toString());
            List<HireProceduresEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (HireProceduresEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireProceduresDTO(entity));
            }
            return listDTO;
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

    public List<IEmpCandidateProceduresDTO> getByCandCode(IEntityKey candidateCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            Long candCode = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCode();
        //    Long candidateCodeSeq = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCodeSeq();
            Query query = EM().createNamedQuery("EmpCandidateProceduresEntity.getByCndCode");
            query.setParameter("candidateCode", candCode);
            //query.setParameter("candidateCodeSeq", candidateCodeSeq);

            List<EmpCandidateProceduresEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            List<IEmpCandidateProceduresDTO> resutListDTO = new ArrayList();
            for (EmpCandidateProceduresEntity entity : list) {
                IEmpCandidateProceduresDTO empCandidateProceduresDTO =
                    EmpEntityConverter.getEmpCandidateProceduresDTO(entity);
                resutListDTO.add(empCandidateProceduresDTO);

            }
            return resutListDTO;
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

    public List<IEmpCandidateProceduresDTO> getByCandCode(Long candidateCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            Query query =
                EM().createQuery("select o from EmpCandidateProceduresEntity o where o.candidateCode =" + candidateCode);

            List<EmpCandidateProceduresEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            List<IEmpCandidateProceduresDTO> resutListDTO = new ArrayList();
            for (EmpCandidateProceduresEntity entity : list) {
                IEmpCandidateProceduresDTO empCandidateProceduresDTO =
                    EmpEntityConverter.getEmpCandidateProceduresDTO(entity);
                resutListDTO.add(empCandidateProceduresDTO);

            }
            return resutListDTO;
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
}
