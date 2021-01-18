package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireStatusEntity;
import com.beshara.csc.hr.emp.business.entity.IHireStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class HireStatusDAO extends EmpBaseDAO {

    public HireStatusDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HireStatusDAO();
    }


    /**<code>select o from HireStatusEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IHireStatusDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HireStatusEntity> list = EM().createNamedQuery("HireStatusEntity.findAll").getResultList();

            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            for (HireStatusEntity hireStatus : list) {
                arrayList.add(EmpDTOFactory.createHireStatusDTO(hireStatus));
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
     * Create a New HireStatusEntity * @param hireStatusDTO
     * @return IHireStatusDTO
     */
    @Override
    public IBasicDTO add(IBasicDTO hireStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HireStatusEntity hireStatusEntity = new HireStatusEntity();
            IHireStatusDTO hireStatusDTO = (IHireStatusDTO)hireStatusDTO1;
            //hireStatusEntity.setHirstatusCode(((IHireStatusEntityKey)hireStatusDTO.getCode()).getHirstatusCode());
            Long hireStatusCode=findMaxId();
            hireStatusEntity.setHirstatusCode(hireStatusCode);
            hireStatusEntity.setHirstatusName(hireStatusDTO.getName());
            hireStatusEntity.setCreatedBy(hireStatusDTO.getCreatedBy());
            hireStatusEntity.setCreatedDate(hireStatusDTO.getCreatedDate());
            hireStatusEntity.setAuditStatus(hireStatusDTO.getAuditStatus());
            hireStatusEntity.setTabrecSerial(hireStatusDTO.getTabrecSerial());
            // Set PK after creation

            // this.persistEntity(hireStatusEntity));
            doAdd(hireStatusEntity);
            
            hireStatusDTO.setCode(EmpEntityKeyFactory.createHireStatusEntityKey(hireStatusCode));
            return EmpDTOFactory.createHireStatusDTO(hireStatusEntity);

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
     * Update an Existing HireStatusEntity * @param hireStatusDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO hireStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHireStatusDTO hireStatusDTO = (IHireStatusDTO)hireStatusDTO1;
            HireStatusEntity hireStatusEntity =
                EM().find(HireStatusEntity.class, (IHireStatusEntityKey)hireStatusDTO.getCode());
            //hireStatusEntity.setHirstatusCode ( ( ( IHireStatusEntityKey ) hireStatusDTO.getCode ( ) ) .getHirstatusCode ( ) ) ;
            hireStatusEntity.setHirstatusName(hireStatusDTO.getName());
            hireStatusEntity.setCreatedBy(hireStatusDTO.getCreatedBy());
            hireStatusEntity.setCreatedDate(hireStatusDTO.getCreatedDate());
            hireStatusEntity.setLastUpdatedBy(hireStatusDTO.getLastUpdatedBy());
            hireStatusEntity.setLastUpdatedDate(hireStatusDTO.getLastUpdatedDate());
            hireStatusEntity.setAuditStatus(hireStatusDTO.getAuditStatus());
            hireStatusEntity.setTabrecSerial(hireStatusDTO.getTabrecSerial());
            //updated by Ismael for dataAudit requirements
            doUpdate(hireStatusEntity);
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
     * Remove an Existing HireStatusEntity * @param hireStatusDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO hireStatusDTO) throws DataBaseException, SharedApplicationException {

        try {
            HireStatusEntity hireStatusEntity =
                EM().find(HireStatusEntity.class, (IHireStatusEntityKey)hireStatusDTO.getCode());
            if (hireStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            //  em.remove(hireStatusEntity);
            doRemove(hireStatusEntity);
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
     * Get HireStatusEntity By Primary Key * @param id
     * @return IHireStatusDTO
     */
    @Override
    public IHireStatusDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            HireStatusEntity hireStatusEntity = EM().find(HireStatusEntity.class, (IHireStatusEntityKey)id);
            if (hireStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            IHireStatusDTO hireStatusDTO = EmpDTOFactory.createHireStatusDTO(hireStatusEntity);
            return hireStatusDTO;

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
     * Search for HireStatusEntity * <br> * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return super.search(basicDTO);
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
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("HireStatusEntity.findNewId").getSingleResult();
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


    /**
     * get list of Hire status with code and name ordered by name * @return List of HireStatusEntity
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("HireStatusEntity.getCodeName").getResultList();

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
     * list all HireStatus match search name , the resulted list will be ordered by name * @param name
     * @return List of Entity
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {

        try {

            //By MLotfy new search
            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from HireStatusEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirstatusName", name));

            query.append(" order by o.hirstatusName");

            List<HireStatusEntity> list = EM().createQuery(query.toString()).getResultList();

            for (HireStatusEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireStatusDTO(hireTypes));
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


    /**
     * list all HireStatus match search name * @param code
     * @return List of HireStatus

     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {

        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            
            try{
            list.add(getById(EmpEntityKeyFactory.createHireStatusEntityKey((Long)code)));
            }catch(ItemNotFoundException e){
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


}
