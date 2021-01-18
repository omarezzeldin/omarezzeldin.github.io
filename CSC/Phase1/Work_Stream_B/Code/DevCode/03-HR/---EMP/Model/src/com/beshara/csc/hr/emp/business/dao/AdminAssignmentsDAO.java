package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignmentsSearchDTO;
import com.beshara.csc.hr.emp.business.entity.AdminAssignmentsEntity;
import com.beshara.csc.hr.emp.business.entity.AssignReasonsEntity;
import com.beshara.csc.hr.emp.business.entity.AssignStatusEntity;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.IAdminAssignmentsEntityKey;
import com.beshara.csc.hr.emp.business.entity.IAssignReasonsEntityKey;
import com.beshara.csc.hr.emp.business.entity.IAssignStatusEntityKey;
import com.beshara.csc.hr.emp.business.entity.IAssignTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.inf.business.entity.IDecisionMakerTypesEntityKey;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class AdminAssignmentsDAO extends EmpBaseDAO {
    public AdminAssignmentsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new AdminAssignmentsDAO();
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {

        try {
            Long id = (Long)EM().createNamedQuery("AdminAssignmentsEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
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

    public List<IAdminAssignmentsDTO> getAll() throws DataBaseException, SharedApplicationException,
                                                      NoResultException {
        try {
            ArrayList arrayList = new ArrayList();
            List<AdminAssignmentsEntity> list =
                EM().createNamedQuery("AdminAssignmentsEntity.findAll").getResultList();
            for (AdminAssignmentsEntity adminAssignments : list) {
                arrayList.add(EmpDTOFactory.createAdminAssignmentsDTO(adminAssignments));
            }
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else if (e instanceof NoResultException) {
                throw (NoResultException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IAdminAssignmentsDTO add(IBasicDTO adminAssignmentsDTO1) throws DataBaseException,
                                                                           SharedApplicationException,
                                                                           ItemNotFoundException {
        try {
            AdminAssignmentsEntity adminAssignmentsEntity = new AdminAssignmentsEntity();
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)adminAssignmentsDTO1;

            Long admAssignmentSerial = findMaxId();
            adminAssignmentsEntity.setAdmassignmentSerial(admAssignmentSerial);
            adminAssignmentsDTO.setCode(EmpEntityKeyFactory.createAdminAssignmentsEntityKey(admAssignmentSerial));

            if (adminAssignmentsDTO.getEmployeesDTO() !=
                null) { //EmployeesEntity entity = em.find ( EmployeesEntity.class , ( IEmployeesEntityKey ) adminAssignmentsDTO.getEmployeesDTO ( ) .getCode ( ) ) ;
                List<EmployeesEntity> list =
                    EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
                                                                                                                ((IEmployeesEntityKey)adminAssignmentsDTO.getEmployeesDTO().getCode()).getCivilId()).getResultList();
                if (list == null || list.size() == 0)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setEmployeesEntity(list.get(0));
            } else {
                throw new ItemNotFoundException();
            }
            adminAssignmentsEntity.setFromDate(adminAssignmentsDTO.getFromDate());
            adminAssignmentsEntity.setUntilDate(adminAssignmentsDTO.getUntilDate());
            if (adminAssignmentsDTO.getAssignTypesDTO() != null) {
                AssignTypesEntity entity =
                    EM().find(AssignTypesEntity.class, (IAssignTypesEntityKey)adminAssignmentsDTO.getAssignTypesDTO().getCode());
                if (entity == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setAssignTypesEntity(entity);
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getAssignReasonsDTO() != null) {
                AssignReasonsEntity entity =
                    EM().find(AssignReasonsEntity.class, (IAssignReasonsEntityKey)adminAssignmentsDTO.getAssignReasonsDTO().getCode());
                if (entity == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setAssignReasonsEntity(entity);
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getAssignStatusDTO() != null) {
                AssignStatusEntity entity =
                    EM().find(AssignStatusEntity.class, (IAssignStatusEntityKey)adminAssignmentsDTO.getAssignStatusDTO().getCode());
                if (entity == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setAssignStatusEntity(entity);
            } else {
                throw new FinderException();
            }
            if (adminAssignmentsDTO.getWorkCentersDTO() != null) {
                // WorkCentersEntity entity =em.find(WorkCentersEntity.class, (IWorkCentersEntityKey)adminAssignmentsDTO.getWorkCentersDTO().getCode());
                IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)adminAssignmentsDTO.getWorkCentersDTO().getCode();
                if (wEk.getMinCode() == null && wEk.getWrkCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setWrkCode(wEk.getWrkCode());
                adminAssignmentsEntity.setMinCode(wEk.getMinCode());
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getJobsDTO() != null) {
                //JobsEntity entity = em.find(JobsEntity.class, (IJobsEntityKey)adminAssignmentsDTO.getJobsDTO().getCode());
                IJobsEntityKey jEk = (IJobsEntityKey)adminAssignmentsDTO.getJobsDTO().getCode();
                if (jEk.getJobCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setJobCode(jEk.getJobCode());
            } else {
                throw new FinderException();
            }
            if (adminAssignmentsDTO.getSalElementGuidesDTO() != null) {
                //SalElementGuidesEntity entity = em.find(SalElementGuidesEntity.class, (ISalElementGuidesEntityKey)adminAssignmentsDTO.getSalElementGuidesDTO().getCode());
                ISalElementGuidesEntityKey salEk =
                    (ISalElementGuidesEntityKey)adminAssignmentsDTO.getSalElementGuidesDTO().getCode();
                if (salEk.getElmguideCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setElmguideCode(salEk.getElmguideCode());
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getDecisionMakerTypesDTO() != null) {
                //DecisionMakerTypesEntity entity = em.find(DecisionMakerTypesEntity.class, (IDecisionMakerTypesEntityKey)adminAssignmentsDTO.getDecisionMakerTypesDTO().getCode());
                IDecisionMakerTypesEntityKey dmEk =
                    (IDecisionMakerTypesEntityKey)adminAssignmentsDTO.getDecisionMakerTypesDTO().getCode();
                if (dmEk.getDecmkrtypeCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setDecmkrtypeCode(dmEk.getDecmkrtypeCode());
            }
            adminAssignmentsEntity.setCreatedBy(adminAssignmentsDTO.getCreatedBy());
            adminAssignmentsEntity.setCreatedDate(adminAssignmentsDTO.getCreatedDate());
            adminAssignmentsEntity.setAuditStatus(adminAssignmentsDTO.getAuditStatus());
            adminAssignmentsEntity.setTabrecSerial(adminAssignmentsDTO.getTabrecSerial());
            this.doAdd(adminAssignmentsEntity);
            // Set PK after creation
            return adminAssignmentsDTO;
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

    public Boolean update(IBasicDTO adminAssignmentsDTO1) throws DataBaseException, SharedApplicationException,
                                                                 ItemNotFoundException {
        try {
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)adminAssignmentsDTO1;
            AdminAssignmentsEntity adminAssignmentsEntity =
                EM().find(AdminAssignmentsEntity.class, (IAdminAssignmentsEntityKey)adminAssignmentsDTO.getCode());
            if (adminAssignmentsDTO.getEmployeesDTO() !=
                null) { //EmployeesEntity entity = em.find ( EmployeesEntity.class , ( IEmployeesEntityKey ) adminAssignmentsDTO.getEmployeesDTO ( ) .getCode ( ) ) ;
                List<EmployeesEntity> list =
                    EM().createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
                                                                                                                ((IEmployeesEntityKey)adminAssignmentsDTO.getEmployeesDTO().getCode()).getCivilId()).getResultList();
                if (list == null || list.size() == 0)
                    throw new FinderException();
                adminAssignmentsEntity.setEmployeesEntity(list.get(0));
            } else {
                throw new ItemNotFoundException();
            }
            adminAssignmentsEntity.setFromDate(adminAssignmentsDTO.getFromDate());
            adminAssignmentsEntity.setUntilDate(adminAssignmentsDTO.getUntilDate());
            if (adminAssignmentsDTO.getAssignTypesDTO() != null) {
                AssignTypesEntity entity =
                    EM().find(AssignTypesEntity.class, (IAssignTypesEntityKey)adminAssignmentsDTO.getAssignTypesDTO().getCode());
                if (entity == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setAssignTypesEntity(entity);
            } 
            if (adminAssignmentsDTO.getAssignReasonsDTO() != null) {
                AssignReasonsEntity entity =
                    EM().find(AssignReasonsEntity.class, (IAssignReasonsEntityKey)adminAssignmentsDTO.getAssignReasonsDTO().getCode());
                if (entity == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setAssignReasonsEntity(entity);
            }
            if (adminAssignmentsDTO.getAssignStatusDTO() != null) {
                AssignStatusEntity entity =
                    EM().find(AssignStatusEntity.class, (IAssignStatusEntityKey)adminAssignmentsDTO.getAssignStatusDTO().getCode());
                if (entity == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setAssignStatusEntity(entity);
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getWorkCentersDTO() != null) {
                // WorkCentersEntity entity =em.find(WorkCentersEntity.class, (IWorkCentersEntityKey)adminAssignmentsDTO.getWorkCentersDTO().getCode());
                IWorkCentersEntityKey wEk = (IWorkCentersEntityKey)adminAssignmentsDTO.getWorkCentersDTO().getCode();
                if (wEk.getMinCode() == null && wEk.getWrkCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setWrkCode(wEk.getWrkCode());
                adminAssignmentsEntity.setMinCode(wEk.getMinCode());
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getJobsDTO() != null) {
                //JobsEntity entity = em.find(JobsEntity.class, (IJobsEntityKey)adminAssignmentsDTO.getJobsDTO().getCode());
                IJobsEntityKey jEk = (IJobsEntityKey)adminAssignmentsDTO.getJobsDTO().getCode();
                if (jEk.getJobCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setJobCode(jEk.getJobCode());
            } else {
                throw new ItemNotFoundException();
            }
            if (adminAssignmentsDTO.getSalElementGuidesDTO() != null) {
                //SalElementGuidesEntity entity = em.find(SalElementGuidesEntity.class, (ISalElementGuidesEntityKey)adminAssignmentsDTO.getSalElementGuidesDTO().getCode());
                ISalElementGuidesEntityKey salEk =
                    (ISalElementGuidesEntityKey)adminAssignmentsDTO.getSalElementGuidesDTO().getCode();
                if (salEk.getElmguideCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setElmguideCode(salEk.getElmguideCode());
            } 
            if (adminAssignmentsDTO.getDecisionMakerTypesDTO() != null) {
                //DecisionMakerTypesEntity entity = em.find(DecisionMakerTypesEntity.class, (IDecisionMakerTypesEntityKey)adminAssignmentsDTO.getDecisionMakerTypesDTO().getCode());
                IDecisionMakerTypesEntityKey dmEk =
                    (IDecisionMakerTypesEntityKey)adminAssignmentsDTO.getDecisionMakerTypesDTO().getCode();
                if (dmEk.getDecmkrtypeCode() == null)
                    throw new ItemNotFoundException();
                adminAssignmentsEntity.setDecmkrtypeCode(dmEk.getDecmkrtypeCode());
            }
            adminAssignmentsEntity.setCreatedBy(adminAssignmentsDTO.getCreatedBy());
            adminAssignmentsEntity.setCreatedDate(adminAssignmentsDTO.getCreatedDate());
            adminAssignmentsEntity.setLastUpdatedBy(adminAssignmentsDTO.getLastUpdatedBy());
            adminAssignmentsEntity.setLastUpdatedDate(adminAssignmentsDTO.getLastUpdatedDate());
            adminAssignmentsEntity.setAuditStatus(adminAssignmentsDTO.getAuditStatus());
            adminAssignmentsEntity.setTabrecSerial(adminAssignmentsDTO.getTabrecSerial());
            // updated by A.AGAMY for data audit
            doUpdate(adminAssignmentsEntity);
            return Boolean.TRUE;
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

    public Boolean remove(IBasicDTO adminAssignmentsDTO1) throws DataBaseException, SharedApplicationException,
                                                                 ItemNotFoundException {
        try {
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)adminAssignmentsDTO1;
            AdminAssignmentsEntity adminAssignmentsEntity =
                EM().find(AdminAssignmentsEntity.class, (IAdminAssignmentsEntityKey)adminAssignmentsDTO.getCode());
            if (adminAssignmentsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(adminAssignmentsEntity);
            return Boolean.TRUE;
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

    public IAdminAssignmentsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException,
                                                               ItemNotFoundException {
        try {
            IAdminAssignmentsEntityKey id = (IAdminAssignmentsEntityKey)id1;
            AdminAssignmentsEntity adminAssignmentsEntity = EM().find(AdminAssignmentsEntity.class, id);
            if (adminAssignmentsEntity == null) {
                throw new ItemNotFoundException();
            }
            IAdminAssignmentsDTO adminAssignmentsDTO = EmpDTOFactory.createAdminAssignmentsDTO(adminAssignmentsEntity);
            return adminAssignmentsDTO;
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

    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException,
                                                             NoResultException {

        try {
            IAssignmentsSearchDTO dto = (IAssignmentsSearchDTO)basicDTO;
            StringBuilder ejbql =
                new StringBuilder("select o from AdminAssignmentsEntity o WHERE o.civilId=o.civilId");
            if (dto.getCivilId() != null)
                ejbql.append(" AND o.employeesEntity.civilId LIKE '" + dto.getCivilId() + "'");
            if (dto.getName() != null && !dto.getName().equals("")) {

                //By MLotfy new search

                //            ejbql.append(" AND o.employeesEntity.civilId IN ( Select kwt.civilId From KwtCitizensResidentsEntity kwt WHERE " +
                //                         "CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName ) LIKE '" +
                //                         dto.getName() + "' ) ");

                ejbql.append(" AND o.employeesEntity.civilId IN ( Select kwt.civilId From EmpKwtCitizensResidentsEntity kwt WHERE (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("CONCAT ( CONCAT ( CONCAT ( CONCAT ( kwt.firstName , ' ' ) , CONCAT ( kwt.secondName , ' ' ) ) , CONCAT ( CONCAT ( kwt.thirdName , ' ' ) , CONCAT ( kwt.lastName , ' ' ) ) ) , kwt.familyName )",
                                                                                 dto.getName()) + " )) ");
            }
            if (dto.getAdmassignmentSerial() != null)
                ejbql.append(" AND o.admassignmentSerial=" + dto.getAdmassignmentSerial() + " ");
            if (dto.getAssreasonCode() != null)
                ejbql.append(" AND o.assignReasonsEntity.assreasonCode=" + dto.getAssreasonCode() + " ");
            if (dto.getAssstatusCode() != null)
                ejbql.append(" AND o.assignStatusEntity.assstatusCode=" + dto.getAssstatusCode() + " ");

            if (dto.getFromDate() != null && dto.getUntilDate() != null) {
                /*ejbql.append(" AND (o.fromDate <= '" + dto.getFromDate() + "' " +
                             "OR (o.fromDate > '" + dto.getFromDate() +
                             "' AND o.fromDate <= '" + dto.getUntilDate() +
                             "')) " + "AND (o.untilDate is null " +
                             "OR o.untilDate >= '" + dto.getUntilDate() + "' " +
                             "OR(o.untilDate >= '" + dto.getFromDate() +
                             "' AND o.untilDate <= '" + dto.getUntilDate() +
                             "' ))");*/

                ejbql.append(" AND (" + "o.fromDate IS NOT NULL AND o.untilDate IS NOT NULL AND o.fromDate >= '" +
                             dto.getFromDate() + "' AND o.fromDate <= '" + dto.getUntilDate() +
                             "' AND o.untilDate >= '" + dto.getFromDate() + "' AND o.untilDate <= '" +
                             dto.getUntilDate() + "'" + ")");

            } else if (dto.getFromDate() != null && dto.getUntilDate() == null) {
                /*ejbql.append(" AND (o.fromDate >= '" + dto.getFromDate() +
                             "' or (o.fromDate < '" + dto.getFromDate() +
                             "' and (o.untilDate is null or o.untilDate >= '" +
                             dto.getFromDate() + "')))");*/

                ejbql.append(" AND (o.fromDate >= '" + dto.getFromDate() + "')");

            } else if (dto.getFromDate() == null && dto.getUntilDate() != null) {
                /*ejbql.append(" AND (o.fromDate <= '" + dto.getUntilDate() +
                             "' and (o.untilDate is null or o.untilDate >= '" +
                             dto.getUntilDate() + "'))");*/

                ejbql.append(" AND (o.untilDate IS NOT NULL AND o.untilDate <= '" + dto.getUntilDate() + "')");
            }


            List<AdminAssignmentsEntity> list = EM().createQuery(ejbql.toString()).getResultList();
            if (list == null || list.size() == 0)
                throw new FinderException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (AdminAssignmentsEntity entity : list) {
                listDTO.add(EmpDTOFactory.createAdminAssignmentsDTO(entity));
            }
            return listDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else if (e instanceof NoResultException) {
                throw (NoResultException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getAllUsingStatusFlag(Long statusFlag) throws DataBaseException, SharedApplicationException,
                                                                         ItemNotFoundException {

        try {
            ArrayList arrayList = new ArrayList();
            List<AdminAssignmentsEntity> list =
                EM().createNamedQuery("AdminAssignmentsEntity.getAllUsingStatusFlag").setParameter("assstatusCode",
                                                                                                   statusFlag).getResultList();
            for (AdminAssignmentsEntity adminAssignments : list) {
                arrayList.add(EmpDTOFactory.createAdminAssignmentsDTO(adminAssignments));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
            return arrayList;
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

    public Long getTableRecordSerial(IEntityKey id1) throws DataBaseException, SharedApplicationException {

        try {
            IAdminAssignmentsEntityKey key = (IAdminAssignmentsEntityKey)id1;
            Query query =
                EM().createNativeQuery("select TABREC_SERIAL from HR_EMP_ADMIN_ASSIGNMENTS  WHERE ADMASSIGNMENT_SERIAL = ?");
            query.setParameter("1", key.getAdmassignmentSerial());
            Vector result = (Vector)query.setHint("toplink.refresh", "true").getSingleResult();
            Long id = null;
            BigDecimal decimal = null;
            if (result != null && result.elementAt(0) != null) {
                decimal = (BigDecimal)result.elementAt(0);
                id = decimal.longValue();
            }
            System.out.println("AdminAssignmentsFacadeBean.getTableRecordSerial:End TableRecordSerial>>" + id);
            if (id != null) {
                return id;
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

    public Boolean getTableRecordSerial(Long serial) throws DataBaseException, SharedApplicationException {
        StringBuilder queryString = new StringBuilder();
        queryString.append(" UPDATE HR_EMP_ADMIN_ASSIGNMENTS");
        queryString.append(" SET ASSSTATUS_CODE = 2,");
        queryString.append("    UNTIL_DATE = CASE WHEN UNTIL_DATE IS NULL " +
                           "THEN TO_DATE(TO_CHAR(SYSDATE, 'dd/MM/yyyy'), 'dd/MM/yyyy') ELSE UNTIL_DATE END");
        queryString.append(" WHERE ADMASSIGNMENT_SERIAL = " + serial);
        return true;
    }


    public Boolean getPreviosAssignment(Long realCivilID) throws DataBaseException, SharedApplicationException {
        StringBuilder queryString = new StringBuilder();
        queryString.append(" select count(*) from HR_EMP_ADMIN_ASSIGNMENTS");
        queryString.append(" WHERE  R_CIVIL_ID = " + realCivilID);
        queryString.append(" and   ASSSTATUS_CODE = 1 ");
        System.out.println("query>>>>>>>>>>>> " + queryString);
        Query query = EM().createNativeQuery(queryString.toString());
        Vector result = (Vector)query.getSingleResult();
        if (result != null && ((BigDecimal)result.get(0)).intValue() != 0L)
            return true;
        else
            return false;

    }

    public Boolean checkConfilectedDateAssignment(Date assignmentDate, Long realCivilID) throws DataBaseException,
                                                                                                SharedApplicationException {
        StringBuilder queryString = new StringBuilder();
        queryString.append(" select count(*) from HR_EMP_ADMIN_ASSIGNMENTS");
        queryString.append(" WHERE  R_CIVIL_ID = " + realCivilID);
        queryString.append(" and  UNTIL_DATE  IS not NULL ");
        queryString.append(" and TO_DATE ('" + assignmentDate + "', 'YYYY-MM-DD') BETWEEN from_date AND until_date");
        System.out.println("query>>>>>>>>>>>> " + queryString);
        Query query = EM().createNativeQuery(queryString.toString());
        Vector result = (Vector)query.getSingleResult();
        if (result != null && ((BigDecimal)result.get(0)).intValue() != 0L)
            return true;
        else
            return false;

    }

    public Boolean checkConfilectedDateAssignmentExcludeCurrent(Date assignmentDate, Long realCivilID,
                                                                String currentAssignment) throws DataBaseException,
                                                                                                 SharedApplicationException {
        StringBuilder queryString = new StringBuilder();
        queryString.append(" select count(*) from HR_EMP_ADMIN_ASSIGNMENTS");
        queryString.append(" WHERE  R_CIVIL_ID = " + realCivilID);
        queryString.append(" and  UNTIL_DATE  IS not NULL ");
        queryString.append(" and ADMASSIGNMENT_SERIAL <> " + currentAssignment);
        queryString.append(" and TO_DATE ('" + assignmentDate + "', 'YYYY-MM-DD') BETWEEN from_date AND until_date");
        System.out.println("query>>>>>>>>>>>> " + queryString);
        Query query = EM().createNativeQuery(queryString.toString());
        Vector result = (Vector)query.getSingleResult();
        if (result != null && ((BigDecimal)result.get(0)).intValue() != 0L)
            return true;
        else
            return false;

    }
    
    
    public List<IAdminAssignmentsDTO> getAllData() throws DataBaseException, SharedApplicationException,
                                                      NoResultException {
        try {
            ArrayList arrayList = new ArrayList();
            List<AdminAssignmentsEntity> list =
                EM().createNamedQuery("AdminAssignmentsEntity.findAll").getResultList();
            for (AdminAssignmentsEntity adminAssignments : list) {
                arrayList.add(EmpEntityConverter.getAdminAssignmentsDTO(adminAssignments));
}
            if (arrayList.size() == 0)
                throw new NoResultException();
            return arrayList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else if (e instanceof NoResultException) {
                throw (NoResultException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
}
