package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpCriminalStatusDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCriminalStatusEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IEmpCriminalStatusEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class EmpCriminalStatusDAO extends EmpBaseDAO {

    /**
     * EmpCriminalStatusDAO Default Constructor
     */
    public EmpCriminalStatusDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new EmpCriminalStatusDAO();
    }


    /**<code>select o from EmpCriminalStatusEntity o</code>.
     * @return List
     */
    public List<IEmpCriminalStatusDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpCriminalStatusEntity> list =
                EM().createNamedQuery("EmpCriminalStatusEntity.findAll").getResultList();
            for (EmpCriminalStatusEntity empCriminalStatus : list) {
                arrayList.add(EmpEntityConverter.getEmpCriminalStatusDTO(empCriminalStatus));

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
     * Create a New EmpCriminalStatusEntity
     * @param empCriminalStatusDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO empCriminalStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            EmpCriminalStatusEntity empCriminalStatusEntity = new EmpCriminalStatusEntity();

            IEmpCriminalStatusDTO empCriminalStatusDTO = (IEmpCriminalStatusDTO)empCriminalStatusDTO1;

            Long serialCode = findMaxId();
            empCriminalStatusEntity.setCrmstatusCode(serialCode+"");
            empCriminalStatusEntity.setName(empCriminalStatusDTO.getName());


            doAdd(empCriminalStatusEntity);
            IEmpCriminalStatusEntityKey ek =
                EmpEntityKeyFactory.createEmpCriminalStatusEntityKey(empCriminalStatusEntity.getCrmstatusCode());
            empCriminalStatusDTO.setCode(ek);

            // Set PK after creation
            return empCriminalStatusDTO;
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
     * Update an Existing EmpCriminalStatusEntity
     * @param empCriminalStatusDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO empCriminalStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpCriminalStatusDTO empCriminalStatusDTO = (IEmpCriminalStatusDTO)empCriminalStatusDTO1;

            EmpCriminalStatusEntity empCriminalStatusEntity =
                EM().find(EmpCriminalStatusEntity.class, (IEmpCriminalStatusEntityKey)empCriminalStatusDTO.getCode());

            empCriminalStatusEntity.setName(empCriminalStatusDTO.getName());

            doUpdate(empCriminalStatusEntity);
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
     * Remove an Existing EmpCriminalStatusEntity
     * @param empCriminalStatusDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO empCriminalStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpCriminalStatusDTO empCriminalStatusDTO = (IEmpCriminalStatusDTO)empCriminalStatusDTO1;

            EmpCriminalStatusEntity empCriminalStatusEntity =
                EM().find(EmpCriminalStatusEntity.class, (IEmpCriminalStatusEntityKey)empCriminalStatusDTO.getCode());

            if (empCriminalStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(empCriminalStatusEntity);
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
     * Get EmpCriminalStatusEntity By Primary Key
     * @param id
     * @return EmpCriminalStatusDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpCriminalStatusEntityKey id = (IEmpCriminalStatusEntityKey)id1;

            EmpCriminalStatusEntity empCriminalStatusEntity = EM().find(EmpCriminalStatusEntity.class, id);
            if (empCriminalStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpCriminalStatusDTO empCriminalStatusDTO =
                EmpEntityConverter.getEmpCriminalStatusDTO(empCriminalStatusEntity);
            return empCriminalStatusDTO;
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
     * Get the MaxId of EmpCriminalStatusEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpCriminalStatusEntity.findNewId").getSingleResult();
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
     * Get all by code equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpCriminalStatusEntity> list =
                EM().createNamedQuery("EmpCriminalStatusEntity.searchByCode").setParameter("code",
                                                                                           code).getResultList();
            for (EmpCriminalStatusEntity entity : list) {
                arrayList.add(EmpEntityConverter.getEmpCriminalStatusDTO(entity));
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
     * Get all by Name equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByName(String searchName) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpCriminalStatusEntity> list =
                EM().createNamedQuery("EmpCriminalStatusEntity.searchByName").setParameter("name",
                                                                                           "%" + searchName + "%").getResultList();
            for (EmpCriminalStatusEntity entity : list) {
                arrayList.add(EmpEntityConverter.getEmpCriminalStatusDTO(entity));
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


}
