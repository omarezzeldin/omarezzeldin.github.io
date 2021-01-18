package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IRaiseDataLogDTO;
import com.beshara.csc.hr.emp.business.entity.RaiseDataLogEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class RaiseDataLogDAO extends EmpBaseDAO {
    public RaiseDataLogDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new RaiseDataLogDAO();
    }

    @Override
    public List<IRaiseDataLogDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<RaiseDataLogEntity> list = EM().createNamedQuery("RaiseDataLogEntity.findAll").getResultList();
            for (RaiseDataLogEntity raiseDataLog : list) {
                arrayList.add(EmpDTOFactory.createRaiseDataLogDTO(raiseDataLog));
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
     * Create a New RaiseDataLogEntity * @param raiseDataLogDTO
     * @return IRaiseDataLogDTO
     */
    @Override
    public IRaiseDataLogDTO add(IBasicDTO raiseDataLogDTO1) throws DataBaseException, SharedApplicationException {
        try {
            RaiseDataLogEntity raiseDataLogEntity = new RaiseDataLogEntity();
            IRaiseDataLogDTO raiseDataLogDTO = (IRaiseDataLogDTO)raiseDataLogDTO1;
            raiseDataLogEntity.setCivilId(raiseDataLogDTO.getCivilId());
            raiseDataLogEntity.setFincadreCode(raiseDataLogDTO.getFincadreCode());
            raiseDataLogEntity.setFingroupCode(raiseDataLogDTO.getFingroupCode());
            raiseDataLogEntity.setFindegreeCode(raiseDataLogDTO.getFindegreeCode());
            raiseDataLogEntity.setFinraiseCode(raiseDataLogDTO.getFinraiseCode());
            raiseDataLogEntity.setDegreasonCode(raiseDataLogDTO.getDegreasonCode());
            raiseDataLogEntity.setFromDate(raiseDataLogDTO.getFromDate());
            raiseDataLogEntity.setUntilDate(raiseDataLogDTO.getUntilDate());
            raiseDataLogEntity.setValue(raiseDataLogDTO.getValue());
            raiseDataLogEntity.setCalcFunction(raiseDataLogDTO.getCalcFunction());
            doAdd(raiseDataLogEntity);
            // Set PK after creation
            return raiseDataLogDTO;
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
     * Update an Existing RaiseDataLogEntity * @param raiseDataLogDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO raiseDataLogDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRaiseDataLogDTO raiseDataLogDTO = (IRaiseDataLogDTO)raiseDataLogDTO1;
            RaiseDataLogEntity raiseDataLogEntity = EM().find(RaiseDataLogEntity.class, raiseDataLogDTO.getCode());
            raiseDataLogEntity.setCivilId(raiseDataLogDTO.getCivilId());
            raiseDataLogEntity.setFincadreCode(raiseDataLogDTO.getFincadreCode());
            raiseDataLogEntity.setFingroupCode(raiseDataLogDTO.getFingroupCode());
            raiseDataLogEntity.setFindegreeCode(raiseDataLogDTO.getFindegreeCode());
            raiseDataLogEntity.setFinraiseCode(raiseDataLogDTO.getFinraiseCode());
            raiseDataLogEntity.setDegreasonCode(raiseDataLogDTO.getDegreasonCode());
            raiseDataLogEntity.setFromDate(raiseDataLogDTO.getFromDate());
            raiseDataLogEntity.setUntilDate(raiseDataLogDTO.getUntilDate());
            raiseDataLogEntity.setValue(raiseDataLogDTO.getValue());
            raiseDataLogEntity.setCalcFunction(raiseDataLogDTO.getCalcFunction());
            doUpdate(raiseDataLogEntity);
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
     * Remove an Existing RaiseDataLogEntity * @param raiseDataLogDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO raiseDataLogDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRaiseDataLogDTO raiseDataLogDTO = (IRaiseDataLogDTO)raiseDataLogDTO1;
            RaiseDataLogEntity raiseDataLogEntity = EM().find(RaiseDataLogEntity.class, raiseDataLogDTO.getCode());
            if (raiseDataLogEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(raiseDataLogEntity);
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
     * Get RaiseDataLogEntity By Primary Key * @param id
     * @return IRaiseDataLogDTO
     */
    @Override
    public IRaiseDataLogDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            RaiseDataLogEntity raiseDataLogEntity = EM().find(RaiseDataLogEntity.class, id);
            if (raiseDataLogEntity == null) {
                throw new ItemNotFoundException();
            }
            IRaiseDataLogDTO raiseDataLogDTO = EmpDTOFactory.createRaiseDataLogDTO(raiseDataLogEntity);
            return raiseDataLogDTO;
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
    public Long queryRaiseDataLogEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("RaiseDataLogEntity.findNewId").getSingleResult();
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
}
