package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpExternalExperienceDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpExternalExperienceSearchDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExternalExperienceEntity;
import com.beshara.csc.hr.emp.business.entity.EmpExternalExperienceEntityKey;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class EmpExternalExperienceDAO extends EmpBaseDAO{
    public EmpExternalExperienceDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpExternalExperienceDAO();
    }
    
    /**<code>select o from EmpExternalExperienceEntity o</code>.
     * @return List
     */
    @Override
    public List<EmpExternalExperienceDTO> getAll() throws DataBaseException, SharedApplicationException  {
        try{
        ArrayList arrayList = new ArrayList();
        List<EmpExternalExperienceEntity> list =
            EM().createNamedQuery("EmpExternalExperienceEntity.findAll").getResultList();
        for (EmpExternalExperienceEntity empExternalExperience : list) {
            arrayList.add(new EmpExternalExperienceDTO(empExternalExperience));
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

    /**
     * Get the MaxId of AbilitiesEntity
     * <br>
     * @return Object
     */
    public BigDecimal findNewId() throws DataBaseException,SharedApplicationException {
        try{
        BigDecimal id =
            (BigDecimal)EM().createNamedQuery("EmpExternalExperienceEntity.findNewId").setHint("toplink.refresh",
                                                                                             "true").getSingleResult();
        if (id != null) {
            return BigDecimal.valueOf(id.longValue() +1L );
        } else {
            return BigDecimal.valueOf(1);
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
     * Create a New EmpExternalExperienceEntity
     * @param empExternalExperienceDTO
     * @return EmpExternalExperienceDTO
     */
    @Override
    public EmpExternalExperienceDTO add(IBasicDTO empExternalExperienceDTO1) throws DataBaseException, SharedApplicationException{
        try{
        EmpExternalExperienceEntity empExternalExperienceEntity = new EmpExternalExperienceEntity();

        EmpExternalExperienceDTO empExternalExperienceDTO = (EmpExternalExperienceDTO)empExternalExperienceDTO1;
        empExternalExperienceEntity.setSerial(((EmpExternalExperienceEntityKey)empExternalExperienceDTO.getCode()).getSerial());
        EmployeesEntity employeesEntity = null;
        employeesEntity =
                EM().find(EmployeesEntity.class, (IEmployeesEntityKey)(empExternalExperienceDTO.getEmployeesDTO().getCode()));
        if (employeesEntity == null) {
            throw new ItemNotFoundException();
        }
          BigDecimal code=BigDecimal.valueOf(findMaxId()); 
      empExternalExperienceEntity.setSerial(code);
        empExternalExperienceEntity.setEmployeesEntity(employeesEntity);
        empExternalExperienceEntity.setExperienceDesc(empExternalExperienceDTO.getExperienceDesc());
        empExternalExperienceEntity.setFromDate(empExternalExperienceDTO.getFromDate());
        empExternalExperienceEntity.setToDate(empExternalExperienceDTO.getToDate());
        empExternalExperienceEntity.setAuditStatus(empExternalExperienceDTO.getAuditStatus());
        empExternalExperienceEntity.setTabrecSerial(empExternalExperienceDTO.getTabrecSerial());
            
        empExternalExperienceEntity.setCreatedBy(empExternalExperienceDTO.getCreatedBy());
        empExternalExperienceEntity.setCreatedDate(empExternalExperienceDTO.getCreatedDate());


       doAdd(empExternalExperienceEntity);
            empExternalExperienceDTO.setCode(EmpEntityKeyFactory.createEmpExternalExperienceEntityKey(code));
        return empExternalExperienceDTO;
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
     * Update an Existing EmpExternalExperienceEntity
     * @param empExternalExperienceDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO empExternalExperienceDTO1) throws DataBaseException, SharedApplicationException{
        try{
        EmpExternalExperienceDTO empExternalExperienceDTO = (EmpExternalExperienceDTO)empExternalExperienceDTO1;

        EmpExternalExperienceEntity empExternalExperienceEntity =
            EM().find(EmpExternalExperienceEntity.class, (EmpExternalExperienceEntityKey)empExternalExperienceDTO.getCode());

        empExternalExperienceEntity.setSerial(((EmpExternalExperienceEntityKey)empExternalExperienceDTO.getCode()).getSerial());
        EmployeesEntity employeesEntity = null;
        employeesEntity =
                EM().find(EmployeesEntity.class, (IEmployeesEntityKey)(empExternalExperienceDTO.getEmployeesDTO().getCode()));
        if (employeesEntity == null) {
            throw new ItemNotFoundException();
        }
        empExternalExperienceEntity.setEmployeesEntity(employeesEntity);
        empExternalExperienceEntity.setExperienceDesc(empExternalExperienceDTO.getExperienceDesc());
        empExternalExperienceEntity.setFromDate(empExternalExperienceDTO.getFromDate());
        empExternalExperienceEntity.setToDate(empExternalExperienceDTO.getToDate());
        empExternalExperienceEntity.setAuditStatus(empExternalExperienceDTO.getAuditStatus());
        empExternalExperienceEntity.setTabrecSerial(empExternalExperienceDTO.getTabrecSerial());
        empExternalExperienceEntity.setCreatedBy(empExternalExperienceDTO.getCreatedBy());
        empExternalExperienceEntity.setCreatedDate(empExternalExperienceDTO.getCreatedDate());
        empExternalExperienceEntity.setLastUpdatedBy(empExternalExperienceDTO.getLastUpdatedBy());
        empExternalExperienceEntity.setLastUpdatedDate(empExternalExperienceDTO.getLastUpdatedDate());
doUpdate(empExternalExperienceEntity);
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
     * Remove an Existing EmpExternalExperienceEntity
     * @param empExternalExperienceDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO empExternalExperienceDTO1) throws DataBaseException, SharedApplicationException{

        EmpExternalExperienceDTO empExternalExperienceDTO = (EmpExternalExperienceDTO)empExternalExperienceDTO1;

        EmpExternalExperienceEntity empExternalExperienceEntity =
            EM().find(EmpExternalExperienceEntity.class, (EmpExternalExperienceEntityKey)empExternalExperienceDTO.getCode());

        if (empExternalExperienceEntity == null) {
            throw new ItemNotFoundException();
        }
       // EM().remove(empExternalExperienceEntity);
        doRemove(empExternalExperienceEntity);
        return Boolean.TRUE;
    }

    /**
     * Get EmpExternalExperienceEntity By Primary Key
     * @param id
     * @return EmpExternalExperienceDTO
     */
    @Override
    public EmpExternalExperienceDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException{
        try{
        EmpExternalExperienceEntityKey id = (EmpExternalExperienceEntityKey)id1;

        EmpExternalExperienceEntity empExternalExperienceEntity = EM().find(EmpExternalExperienceEntity.class, id);
        if (empExternalExperienceEntity == null) {
            throw new ItemNotFoundException();
        }
        EmpExternalExperienceDTO empExternalExperienceDTO = new EmpExternalExperienceDTO(empExternalExperienceEntity);
        return empExternalExperienceDTO;
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
     * Search for EmpExternalExperienceEntity
     * <br>
     * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException{
        try{
        IEmpExternalExperienceSearchDTO dto = (IEmpExternalExperienceSearchDTO)basicDTO;

        StringBuffer queryString =
            new StringBuffer("select DISTINCT o from EmpExternalExperienceEntity o where o.civilId = o.civilId ");

        if (dto.getCivilId() != null) {
            queryString.append(" AND o.civilId= :civilId");
        }
        if (dto.getExperienceDesc() != null && !dto.getExperienceDesc().equals("")) {

            //By MLotfy new search
            //queryString.append(" AND o.experienceDesc LIKE :experienceDesc");
            queryString.append(" AND (" +
                               QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.experienceDesc", dto.getExperienceDesc()) +
                               " ) ");
        }
        if (dto.getSerial() != null) {
            queryString.append(" AND o.serial= :serial");
        }

        if (dto.getFromDate() != null && dto.getUntilDate() == null) {
            queryString.append(" AND (o.fromDate >= '" + dto.getFromDate() + "' or (o.fromDate < '" +
                               dto.getFromDate() + "' and (o.toDate is null or o.toDate >= '" + dto.getFromDate() +
                               "')))");
        }
        if (dto.getFromDate() == null && dto.getUntilDate() != null) {
            queryString.append(" AND (o.fromDate <= '" + dto.getUntilDate() +
                               "' and (o.toDate is null or o.toDate >= '" + dto.getUntilDate() + "'))");
        }
        if (dto.getFromDate() != null && dto.getUntilDate() != null) {
            queryString.append(" AND (o.fromDate <= '" + dto.getFromDate() + "' " + "OR (o.fromDate > '" +
                               dto.getFromDate() + "' AND o.fromDate <= '" + dto.getUntilDate() + "')) " +
                               " AND (o.toDate is null " + "OR o.toDate >= '" + dto.getUntilDate() + "' " +
                               "OR(o.toDate >= '" + dto.getFromDate() + "' AND o.toDate <= '" + dto.getUntilDate() +
                               "' ))");

        }
        System.out.println("Query>>>>>>>>>" + queryString.toString());
        Query query = EM().createQuery(queryString.toString());

        if (dto.getCivilId() != null) {
            query.setParameter("civilId", dto.getCivilId());
        }

        // commented by MLotfy to apply new search(regex)
        /*if (dto.getExperienceDesc() != null &&
            !dto.getExperienceDesc().equals("")) {
            query.setParameter("experienceDesc", dto.getExperienceDesc());
        }*/

        if (dto.getSerial() != null) {
            query.setParameter("serial", dto.getSerial());
        }

        List<EmpExternalExperienceEntity> list = query.setHint("toplink.refresh", "true").getResultList();
        if (list == null || list.size() == 0)
            throw new ItemNotFoundException();
        List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
        for (EmpExternalExperienceEntity entity : list) {
            listDTO.add(new EmpExternalExperienceDTO(entity));
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


    public List<EmpExternalExperienceDTO> getAllByCivilId(Long civilId) throws DataBaseException, SharedApplicationException{
        try{
        ArrayList arrayList = new ArrayList();
        List<EmpExternalExperienceEntity> list = null;
        Query query = EM().createNamedQuery("EmpExternalExperienceEntity.getAllByCivilId");
        query.setParameter("civilId", civilId);
        list = query.getResultList();
        if (list == null || (list.size() == 0)) {
            throw new NoResultException();
        }
        for (EmpExternalExperienceEntity empExternalExperience : list) {
            arrayList.add(new EmpExternalExperienceDTO(empExternalExperience));
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
}
