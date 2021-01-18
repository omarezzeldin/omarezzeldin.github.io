package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpMinEdtypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpMinEdtypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpMinEdtypesEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class EmpMinEdtypesDAO extends EmpBaseDAO {

    /**
     * EmpMinEdtypesDAO Default Constructor
     */
    public EmpMinEdtypesDAO() {
        super();
    }


    @Override
    protected BaseDAO newInstance() {
        return new EmpMinEdtypesDAO();
    }


    /**<code>select o from EmpMinEdtypesEntity o</code>.
     * @return List
     */
    public List<IEmpMinEdtypesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpMinEdtypesEntity> list = EM().createNamedQuery("EmpMinEdtypesEntity.findAll").getResultList();
            for (EmpMinEdtypesEntity empMinEdtypes : list) {
                arrayList.add(EmpEntityConverter.getEmpMinEdtypesDTO(empMinEdtypes));

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

    public List<IEmpMinEdtypesDTO> getByMinCode(Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpMinEdtypesEntity> list = EM().createNamedQuery("EmpMinEdtypesEntity.getByMinCode")
                .setParameter("minCode", minCode) .getResultList();
            for (EmpMinEdtypesEntity empMinEdtypes : list) {
                arrayList.add(EmpEntityConverter.getEmpMinEdtypesDTO(empMinEdtypes));

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
     * Create a New EmpMinEdtypesEntity
     * @param empMinEdtypesDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO empMinEdtypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            EmpMinEdtypesEntity empMinEdtypesEntity = new EmpMinEdtypesEntity();

            IEmpMinEdtypesDTO empMinEdtypesDTO = (IEmpMinEdtypesDTO)empMinEdtypesDTO1;

            empMinEdtypesEntity.setMinCode(empMinEdtypesDTO.getMinCode());
            empMinEdtypesEntity.setExtdattypeCode(empMinEdtypesDTO.getExtdattypeCode());


            doAdd(empMinEdtypesEntity);
            IEmpMinEdtypesEntityKey ek =
                EmpEntityKeyFactory.createEmpMinEdtypesEntityKey(empMinEdtypesEntity.getMinCode(),
                                                                 empMinEdtypesEntity.getExtdattypeCode());
            empMinEdtypesDTO.setCode(ek);

            // Set PK after creation
            return empMinEdtypesDTO;
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
     * Update an Existing EmpMinEdtypesEntity
     * @param empMinEdtypesDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO empMinEdtypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpMinEdtypesDTO empMinEdtypesDTO = (IEmpMinEdtypesDTO)empMinEdtypesDTO1;

            EmpMinEdtypesEntity empMinEdtypesEntity =
                EM().find(EmpMinEdtypesEntity.class, (IEmpMinEdtypesEntityKey)empMinEdtypesDTO.getCode());


            doUpdate(empMinEdtypesEntity);
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
     * Remove an Existing EmpMinEdtypesEntity
     * @param empMinEdtypesDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO empMinEdtypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpMinEdtypesDTO empMinEdtypesDTO = (IEmpMinEdtypesDTO)empMinEdtypesDTO1;

            EmpMinEdtypesEntity empMinEdtypesEntity =
                EM().find(EmpMinEdtypesEntity.class, (IEmpMinEdtypesEntityKey)empMinEdtypesDTO.getCode());

            if (empMinEdtypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(empMinEdtypesEntity);
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
     * Get EmpMinEdtypesEntity By Primary Key
     * @param id
     * @return EmpMinEdtypesDTO
     */
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpMinEdtypesEntityKey id = (IEmpMinEdtypesEntityKey)id1;

            EmpMinEdtypesEntity empMinEdtypesEntity = EM().find(EmpMinEdtypesEntity.class, id);
            if (empMinEdtypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpMinEdtypesDTO empMinEdtypesDTO = EmpEntityConverter.getEmpMinEdtypesDTO(empMinEdtypesEntity);
            return empMinEdtypesDTO;
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
     * Get the MaxId of EmpMinEdtypesEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpMinEdtypesEntity.findNewId").getSingleResult();
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


}
