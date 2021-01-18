package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IRaiseDataDTO;
import com.beshara.csc.hr.emp.business.entity.RaiseDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class RaiseDataDAO extends EmpBaseDAO {
    public RaiseDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new RaiseDataDAO();
    }

    /**<code>select o from RaiseDataEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IRaiseDataDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<RaiseDataEntity> list = EM().createNamedQuery("RaiseDataEntity.findAll").getResultList();
            for (RaiseDataEntity raiseData : list) {
                arrayList.add(EmpDTOFactory.createRaiseDataDTO(raiseData));
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
     * Create a New RaiseDataEntity * @param raiseDataDTO
     * @return IRaiseDataDTO
     */
    @Override
    public IRaiseDataDTO add(IBasicDTO raiseDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            RaiseDataEntity raiseDataEntity = new RaiseDataEntity();
            IRaiseDataDTO raiseDataDTO = (IRaiseDataDTO)raiseDataDTO1;
            raiseDataEntity.setCivilId(raiseDataDTO.getCivilId());
            raiseDataEntity.setFincadreCode(raiseDataDTO.getFincadreCode());
            raiseDataEntity.setFingroupCode(raiseDataDTO.getFingroupCode());
            raiseDataEntity.setFindegreeCode(raiseDataDTO.getFindegreeCode());
            raiseDataEntity.setFinraiseCode(raiseDataDTO.getFinraiseCode());
            raiseDataEntity.setDegreasonCode(raiseDataDTO.getDegreasonCode());
            raiseDataEntity.setFromDate(raiseDataDTO.getFromDate());
            raiseDataEntity.setValue(raiseDataDTO.getValue());
            raiseDataEntity.setCalcFunction(raiseDataDTO.getCalcFunction());
            doAdd(raiseDataEntity);
            // Set PK after creation
            return raiseDataDTO;
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
     * Update an Existing RaiseDataEntity * @param raiseDataDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO raiseDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRaiseDataDTO raiseDataDTO = (IRaiseDataDTO)raiseDataDTO1;
            RaiseDataEntity raiseDataEntity = EM().find(RaiseDataEntity.class, raiseDataDTO.getCode());
            raiseDataEntity.setCivilId(raiseDataDTO.getCivilId());
            raiseDataEntity.setFincadreCode(raiseDataDTO.getFincadreCode());
            raiseDataEntity.setFingroupCode(raiseDataDTO.getFingroupCode());
            raiseDataEntity.setFindegreeCode(raiseDataDTO.getFindegreeCode());
            raiseDataEntity.setFinraiseCode(raiseDataDTO.getFinraiseCode());
            raiseDataEntity.setDegreasonCode(raiseDataDTO.getDegreasonCode());
            raiseDataEntity.setFromDate(raiseDataDTO.getFromDate());
            raiseDataEntity.setValue(raiseDataDTO.getValue());
            raiseDataEntity.setCalcFunction(raiseDataDTO.getCalcFunction());
            //updated by Ismael for dataAudit requirements
            doUpdate(raiseDataEntity);
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
     * Remove an Existing RaiseDataEntity * @param raiseDataDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO raiseDataDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRaiseDataDTO raiseDataDTO = (IRaiseDataDTO)raiseDataDTO1;
            RaiseDataEntity raiseDataEntity = EM().find(RaiseDataEntity.class, raiseDataDTO.getCode());
            if (raiseDataEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(raiseDataEntity);
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
     * Get RaiseDataEntity By Primary Key * @param id
     * @return IRaiseDataDTO
     */
    @Override
    public IRaiseDataDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            RaiseDataEntity raiseDataEntity = EM().find(RaiseDataEntity.class, id);
            if (raiseDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IRaiseDataDTO raiseDataDTO = EmpDTOFactory.createRaiseDataDTO(raiseDataEntity);
            return raiseDataDTO;
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
    public Long queryRaiseDataEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("RaiseDataEntity.findNewId").getSingleResult();
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
