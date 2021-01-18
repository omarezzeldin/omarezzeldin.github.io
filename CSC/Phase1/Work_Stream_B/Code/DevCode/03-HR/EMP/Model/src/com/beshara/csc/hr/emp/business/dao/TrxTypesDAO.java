package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.ITrxTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.ITrxTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.TrxTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class TrxTypesDAO extends EmpBaseDAO {
    public TrxTypesDAO() {
        super();
    }
    
    @Override
    protected BaseDAO newInstance() {
        return new TrxTypesDAO();
    }
    
    /**<code>select o from TrxTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<ITrxTypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
        ArrayList arrayList = new ArrayList();
        List<TrxTypesEntity> list = EM().createNamedQuery("TrxTypesEntity.findAll").getResultList();
        for (TrxTypesEntity trxTypes : list) {
            arrayList.add(EmpDTOFactory.createTrxTypesDTO(trxTypes));
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
     * Create a New TrxTypesEntity * @param trxTypesDTO
     * @return ITrxTypesDTO
     */
     @Override
    public ITrxTypesDTO add(IBasicDTO trxTypesDTO1) throws DataBaseException, SharedApplicationException {
        try{
        TrxTypesEntity trxTypesEntity = new TrxTypesEntity();
        ITrxTypesDTO trxTypesDTO = (ITrxTypesDTO)trxTypesDTO1;
        trxTypesEntity.setTrxtypeCode(((ITrxTypesEntityKey)trxTypesDTO.getCode()).getTrxtypeCode());
        trxTypesEntity.setTrxtypeName(trxTypesDTO.getTrxtypeName());
        trxTypesEntity.setCreatedBy(trxTypesDTO.getCreatedBy());
        trxTypesEntity.setCreatedDate(trxTypesDTO.getCreatedDate());
        trxTypesEntity.setAuditStatus(trxTypesDTO.getAuditStatus());
        trxTypesEntity.setTabrecSerial(trxTypesDTO.getTabrecSerial());
        doAdd(trxTypesEntity);
        // Set PK after creation
        return trxTypesDTO;
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
     * Update an Existing TrxTypesEntity * @param trxTypesDTO
     * @return Boolean
     */
     @Override
    public Boolean update(IBasicDTO trxTypesDTO1) throws DataBaseException, SharedApplicationException  {
       
        try{ ITrxTypesDTO trxTypesDTO = (ITrxTypesDTO)trxTypesDTO1;
        TrxTypesEntity trxTypesEntity = EM().find(TrxTypesEntity.class, (ITrxTypesEntityKey)trxTypesDTO.getCode());
        trxTypesEntity.setTrxtypeCode(((ITrxTypesEntityKey)trxTypesDTO.getCode()).getTrxtypeCode());
        trxTypesEntity.setTrxtypeName(trxTypesDTO.getTrxtypeName());
        trxTypesEntity.setCreatedBy(trxTypesDTO.getCreatedBy());
        trxTypesEntity.setCreatedDate(trxTypesDTO.getCreatedDate());
        trxTypesEntity.setLastUpdatedBy(trxTypesDTO.getLastUpdatedBy());
        trxTypesEntity.setLastUpdatedDate(trxTypesDTO.getLastUpdatedDate());
        trxTypesEntity.setAuditStatus(trxTypesDTO.getAuditStatus());
        trxTypesEntity.setTabrecSerial(trxTypesDTO.getTabrecSerial());
            //updated by Ismael for dataAudit requirements
            doUpdate(trxTypesEntity);
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
     * Remove an Existing TrxTypesEntity * @param trxTypesDTO
     * @return Boolean
     */
     @Override
    public Boolean remove(IBasicDTO trxTypesDTO1)throws DataBaseException, SharedApplicationException {
        try{
        ITrxTypesDTO trxTypesDTO = (ITrxTypesDTO)trxTypesDTO1;
        TrxTypesEntity trxTypesEntity = EM().find(TrxTypesEntity.class, (ITrxTypesEntityKey)trxTypesDTO.getCode());
        if (trxTypesEntity == null) {
            throw new ItemNotFoundException();
        }
       doRemove(trxTypesEntity);
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
     * Get TrxTypesEntity By Primary Key * @param id
     * @return ITrxTypesDTO
     */
     @Override
    public ITrxTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
        ITrxTypesEntityKey id = (ITrxTypesEntityKey)id1;
        TrxTypesEntity trxTypesEntity = EM().find(TrxTypesEntity.class, id);
        if (trxTypesEntity == null) {
            throw new ItemNotFoundException();
        }
        ITrxTypesDTO trxTypesDTO = EmpDTOFactory.createTrxTypesDTO(trxTypesEntity);
        return trxTypesDTO;
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
     * Get the MaxId of AbilitiesEntity * <br> * @return Object
     */
    public Long queryTrxTypesEntityFindNewId() throws DataBaseException, SharedApplicationException  {
        try{
        Long id = (Long)EM().createNamedQuery("TrxTypesEntity.findNewId").getSingleResult();
        if (id != null) {
            return id;
        } else {
            return new Long(0);
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
     * H.Omar [B.Horse] 
     * @param code
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {

        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();

            try {
                list.add(getById(EmpEntityKeyFactory.createTrxTypesEntityKey((Long)code)));
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            }
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
    /**
     * H.Omar [B.Horse]
     * @param name
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {

        try {

            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from TrxTypesEntity o where ");
            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.trxtypeName", name));
            query.append(" order by o.trxtypeName");
            List<TrxTypesEntity> list = EM().createQuery(query.toString()).getResultList();

            for (TrxTypesEntity trxType : list) {
                arrayList.add(EmpDTOFactory.createTrxTypesDTO(trxType));
            }

            if (arrayList.size() == 0) {
                throw new FinderException();
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
