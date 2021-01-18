package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAssignTypesDTO;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IAssignTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;


public class AssignTypesDAO extends EmpBaseDAO {
    public AssignTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new AssignTypesDAO();
    }
    
    /**<code>select o from AssignTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IAssignTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        
        ArrayList arrayList = new ArrayList();
        try{
            List<AssignTypesEntity> list = EM().createNamedQuery("AssignTypesEntity.findAll").getResultList();
            for (AssignTypesEntity assignTypes : list) {
                arrayList.add(EmpDTOFactory.createAssignTypesDTO(assignTypes));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }

        return arrayList;
    }
    
    /**
     * Get the MaxId of AbilitiesEntity * <br> * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        
        try{
            Long id = (Long)EM().createNamedQuery("AssignTypesEntity.findNewId").getSingleResult();
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
    
    /**
     * Create a New AssignTypesEntity * @param assignTypesDTO
     * @return IAssignTypesDTO
     */
    public IAssignTypesDTO add(IBasicDTO assignTypesDTO1) throws DataBaseException, SharedApplicationException {
        
        try{
            AssignTypesEntity assignTypesEntity = new AssignTypesEntity();
            IAssignTypesDTO assignTypesDTO = (IAssignTypesDTO)assignTypesDTO1;
            Long assignTypeCode = findMaxId();
            assignTypesEntity.setAsstypeCode(assignTypeCode);
            
            assignTypesDTO.setCode(EmpEntityKeyFactory.createAssignTypesEntityKey(assignTypeCode));
            
            //assignTypesEntity.setAsstypeCode(((IAssignTypesEntityKey)assignTypesDTO.getCode()).getAsstypeCode());
            assignTypesEntity.setAsstypeName(assignTypesDTO.getName());
            if (assignTypesDTO.getConditionsDTO() != null) {
               // ConditionsEntity conditionsEntity =em.find(ConditionsEntity.class, (IConditionsEntityKey)assignTypesDTO.getConditionsDTO().getCode());
               IConditionsEntityKey cEk = (IConditionsEntityKey)assignTypesDTO.getConditionsDTO().getCode();
                if (cEk == null)
                    throw new ItemNotFoundException();
                assignTypesEntity.setConditionCode(cEk.getConditionCode());
            }
            assignTypesEntity.setCreatedBy(assignTypesDTO.getCreatedBy());
            assignTypesEntity.setCreatedDate(assignTypesDTO.getCreatedDate());
            assignTypesEntity.setAuditStatus(assignTypesDTO.getAuditStatus());
            assignTypesEntity.setTabrecSerial(assignTypesDTO.getTabrecSerial());
            this.doAdd(assignTypesEntity);
            // Set PK after creation
            return assignTypesDTO;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    /**
     * Update an Existing AssignTypesEntity * @param assignTypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO assignTypesDTO1) throws DataBaseException, SharedApplicationException{
        
        try{
            IAssignTypesDTO assignTypesDTO = (IAssignTypesDTO)assignTypesDTO1;
            AssignTypesEntity assignTypesEntity =
                EM().find(AssignTypesEntity.class, (IAssignTypesEntityKey)assignTypesDTO.getCode());
            assignTypesEntity.setAsstypeName(assignTypesDTO.getName());
            if (assignTypesDTO.getConditionsDTO() != null) {
               // ConditionsEntity conditionsEntity = em.find(ConditionsEntity.class, (IConditionsEntityKey)assignTypesDTO.getConditionsDTO().getCode());
               IConditionsEntityKey cEk = (IConditionsEntityKey)assignTypesDTO.getConditionsDTO().getCode();
                if (cEk == null)
                    throw new ItemNotFoundException();
                assignTypesEntity.setConditionCode(cEk.getConditionCode());
            } else {
                assignTypesEntity.setConditionCode(null);
            }
            assignTypesEntity.setCreatedBy(assignTypesDTO.getCreatedBy());
            assignTypesEntity.setCreatedDate(assignTypesDTO.getCreatedDate());
            assignTypesEntity.setLastUpdatedBy(assignTypesDTO.getLastUpdatedBy());
            assignTypesEntity.setLastUpdatedDate(assignTypesDTO.getLastUpdatedDate());
            assignTypesEntity.setAuditStatus(assignTypesDTO.getAuditStatus());
            assignTypesEntity.setTabrecSerial(assignTypesDTO.getTabrecSerial());
            // updated by A.AGAMY for data audit
            doUpdate(assignTypesEntity);
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
    
    /**
     * Remove an Existing AssignTypesEntity * @param assignTypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO assignTypesDTO1) throws DataBaseException, SharedApplicationException{
        
        try {
            IAssignTypesDTO assignTypesDTO = (IAssignTypesDTO)assignTypesDTO1;
            AssignTypesEntity assignTypesEntity =
                EM().find(AssignTypesEntity.class, (IAssignTypesEntityKey)assignTypesDTO.getCode());
            if (assignTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(assignTypesEntity);
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
    
    /**
     * Get AssignTypesEntity By Primary Key * @param id
     * @return IAssignTypesDTO
     */
    public IAssignTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException{
        
        try{
            IAssignTypesEntityKey id = (IAssignTypesEntityKey)id1;
            AssignTypesEntity assignTypesEntity = EM().find(AssignTypesEntity.class, id);
            if (assignTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IAssignTypesDTO assignTypesDTO = EmpDTOFactory.createAssignTypesDTO(assignTypesEntity);
            return assignTypesDTO;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    /**
     * Search for AssignTypesEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }
    
    /**
     * get list of AssignTypes with code and name ordered by name * @return List of IAssignTypesDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException,NoResultException {
        return EM().createNamedQuery("AssignTypesEntity.getCodeName").getResultList();
    }
    
    /**
     * list all AssignTypes match search name , the resulted list will be ordered by name * @param name
     * @return List of IAssignTypesDTO
     * @throws RemoteException
     */
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException{

        //By MLotfy new search
        ArrayList arrayList = new ArrayList();

        try{
            StringBuilder query = new StringBuilder("select o from AssignTypesEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.asstypeName", name));

            query.append(" order by o.asstypeName");

            List<AssignTypesEntity> list = EM().createQuery(query.toString()).getResultList();

            for (AssignTypesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createAssignTypesDTO(hireTypes));
            }

            if (arrayList.size() == 0) {
                throw new NoResultException();
            }
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }

        return arrayList;
    }
    
    /**
     * list all AssignTypes match search code * @param code
     * @return List of IAssignTypesDTO
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException{
        
        List<IBasicDTO> list = new ArrayList<IBasicDTO>();
        
        try{
            list.add(getById(EmpEntityKeyFactory.createAssignTypesEntityKey((Long)code)));
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
        return list;
    }
}
