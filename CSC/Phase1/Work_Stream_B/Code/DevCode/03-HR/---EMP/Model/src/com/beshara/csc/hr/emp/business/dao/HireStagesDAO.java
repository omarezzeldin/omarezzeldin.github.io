package com.beshara.csc.hr.emp.business.dao;

//import com.beshara.csc.hr.emp.business.dto.IDocumentTypesDTO;
//import com.beshara.csc.hr.emp.business.entity.DocumentTypesEntity;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;


public class HireStagesDAO extends EmpBaseDAO {
    public HireStagesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HireStagesDAO();
    }

    /**
     Modified By Ahmed Khaled
     */
    @Override
    public List<IHireStagesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HireStagesEntity> list =
                EM().createNamedQuery("HireStagesEntity.findAll").setHint("toplink.refresh",
                                                                             "true").getResultList();
            for (HireStagesEntity hireStages : list) {
                arrayList.add(EmpDTOFactory.createHireStagesDTO(hireStages));
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
    public Long findNewId() throws DataBaseException,SharedApplicationException {
        try{
            Long id = (Long)EM().createNamedQuery("HireStagesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
            }
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public IBasicDTO add(IBasicDTO hireStagesDTO1) throws DataBaseException,SharedApplicationException {
        
        try{
            HireStagesEntity hireStagesEntity = new HireStagesEntity();
            IHireStagesDTO hireStagesDTO = (IHireStagesDTO)hireStagesDTO1;
            
            Long hireStagesCode = findMaxId();
            hireStagesDTO.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(hireStagesCode));
            hireStagesEntity.setHirstageCode(hireStagesCode);
            
            hireStagesEntity.setHirstageName(hireStagesDTO.getName());
            hireStagesEntity.setCreatedBy(hireStagesDTO.getCreatedBy());
            hireStagesEntity.setCreatedDate(hireStagesDTO.getCreatedDate());
            hireStagesEntity.setAuditStatus(hireStagesDTO.getAuditStatus());
            hireStagesEntity.setTabrecSerial(hireStagesDTO.getTabrecSerial());
            hireStagesEntity.setFromDate(hireStagesDTO.getFromDate());
            hireStagesEntity.setUntilDate(hireStagesDTO.getUntilDate());
            hireStagesEntity.setStatus(hireStagesDTO.getStatus());
            // Set PK after creation
            return EmpDTOFactory.createHireStagesDTO((HireStagesEntity)this.doAdd(hireStagesEntity));   
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    public Boolean update(IBasicDTO hireStagesDTO1) throws DataBaseException,SharedApplicationException {
        
        try{
            IHireStagesDTO hireStagesDTO = (IHireStagesDTO)hireStagesDTO1;
            HireStagesEntity hireStagesEntity =
                EM().find(HireStagesEntity.class, (IHireStagesEntityKey)hireStagesDTO.getCode());
            //hireStagesEntity.setHirstageCode ( ( ( IHireStagesEntityKey ) hireStagesDTO.getCode ( ) ) .getHirstageCode ( ) ) ;
            hireStagesEntity.setHirstageName(hireStagesDTO.getName());
            hireStagesEntity.setCreatedBy(hireStagesDTO.getCreatedBy());
            hireStagesEntity.setCreatedDate(hireStagesDTO.getCreatedDate());
            hireStagesEntity.setLastUpdatedBy(hireStagesDTO.getLastUpdatedBy());
            hireStagesEntity.setLastUpdatedDate(hireStagesDTO.getLastUpdatedDate());
            hireStagesEntity.setAuditStatus(hireStagesDTO.getAuditStatus());
            hireStagesEntity.setTabrecSerial(hireStagesDTO.getTabrecSerial());
            hireStagesEntity.setFromDate(hireStagesDTO.getFromDate());
            hireStagesEntity.setUntilDate(hireStagesDTO.getUntilDate());
            hireStagesEntity.setStatus(hireStagesDTO.getStatus());
            //updated by Ismael for dataAudit requirements
            doUpdate(hireStagesEntity);
            return Boolean.TRUE;   
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public Boolean remove(IBasicDTO hireStagesDTO) throws DataBaseException,SharedApplicationException {
        try{
            HireStagesEntity hireStagesEntity =
                EM().find(HireStagesEntity.class, (IHireStagesEntityKey)hireStagesDTO.getCode());
            if (hireStagesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(hireStagesEntity);
            return Boolean.TRUE;   
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public IBasicDTO getById(IEntityKey id) throws DataBaseException,SharedApplicationException {
        try{
            HireStagesEntity hireStagesEntity = EM().find(HireStagesEntity.class, (IHireStagesEntityKey)id);
            if (hireStagesEntity == null) {
                throw new ItemNotFoundException();
            }
            IHireStagesDTO hireStagesDTO = EmpDTOFactory.createHireStagesDTO(hireStagesEntity);
            return hireStagesDTO;   
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public List<IBasicDTO> getCodeName() throws DataBaseException,SharedApplicationException {
        try{
            return EM().createNamedQuery("HireStagesEntity.getCodeName").getResultList();
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public List<IBasicDTO> searchByName(String name) throws DataBaseException,SharedApplicationException {

        //By MLotfy new search
        try{
            ArrayList arrayList = new ArrayList();

            StringBuilder query = new StringBuilder("select o from HireStagesEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirstageName", name));
            query.append(" order by o.hirstageName");

            List<HireStagesEntity> list = EM().createQuery(query.toString()).getResultList();

            for (HireStagesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireStagesDTO(hireTypes));
            }

            if (arrayList.size() == 0) {
                throw new NoResultException();
            }

            return arrayList;   
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException,SharedApplicationException {
        
        try{
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            try{
                list.add(getById(EmpEntityKeyFactory.createHireStagesEntityKey((Long)code)));
            }catch(ItemNotFoundException e){
                throw new NoResultException();
            }
            return list;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }

    }
}
