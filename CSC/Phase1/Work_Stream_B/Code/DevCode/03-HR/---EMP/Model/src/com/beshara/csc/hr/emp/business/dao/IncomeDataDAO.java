package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IIncomeDataDTO;
import com.beshara.csc.hr.emp.business.entity.IncomeDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class IncomeDataDAO extends EmpBaseDAO {

    public IncomeDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new IncomeDataDAO();
    }


    /**<code>select o from IncomeDataEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IIncomeDataDTO> getAll() throws DataBaseException, SharedApplicationException {

        try {
            ArrayList arrayList = new ArrayList();
            List<IncomeDataEntity> list = EM().createNamedQuery("IncomeDataEntity.findAll").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }

            for (IncomeDataEntity incomeData : list) {
                arrayList.add(EmpDTOFactory.createIncomeDataDTO(incomeData));
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
     * Create a New IncomeDataEntity * @param incomeDataDTO
     * @return IIncomeDataDTO
     */
    @Override
    public IIncomeDataDTO add(IBasicDTO incomeDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IncomeDataEntity incomeDataEntity = new IncomeDataEntity();
            IIncomeDataDTO incomeDataDTO = (IIncomeDataDTO)incomeDataDTO1;
           // incomeDataEntity.setCivilId(incomeDataDTO.getCivilId());
           Long civilId=findNewId();
            incomeDataEntity.setCivilId(civilId);
            incomeDataEntity.setCatincomeCode(incomeDataDTO.getCatincomeCode());
            incomeDataEntity.setIncomeCode(incomeDataDTO.getIncomeCode());
            incomeDataEntity.setInccatCode(incomeDataDTO.getInccatCode());
            incomeDataEntity.setFromDate(incomeDataDTO.getFromDate());
            incomeDataEntity.setValue(incomeDataDTO.getValue());
            incomeDataEntity.setCalcFunction(incomeDataDTO.getCalcFunction());
            //this.persistEntity(incomeDataEntity);
            doAdd(incomeDataEntity);
            incomeDataDTO.setCivilId(civilId);
            // Set PK after creation
            return incomeDataDTO;

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
     * Update an Existing IncomeDataEntity * @param incomeDataDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO incomeDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IIncomeDataDTO incomeDataDTO = (IIncomeDataDTO)incomeDataDTO1;
            IncomeDataEntity incomeDataEntity = EM().find(IncomeDataEntity.class, incomeDataDTO.getCode());
            incomeDataEntity.setCivilId(incomeDataDTO.getCivilId());
            incomeDataEntity.setCatincomeCode(incomeDataDTO.getCatincomeCode());
            incomeDataEntity.setIncomeCode(incomeDataDTO.getIncomeCode());
            incomeDataEntity.setInccatCode(incomeDataDTO.getInccatCode());
            incomeDataEntity.setFromDate(incomeDataDTO.getFromDate());
            incomeDataEntity.setValue(incomeDataDTO.getValue());
            incomeDataEntity.setCalcFunction(incomeDataDTO.getCalcFunction());
            
            //updated by Ismael for dataAudit requirements
            doUpdate(incomeDataEntity);
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
     * Remove an Existing IncomeDataEntity * @param incomeDataDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO incomeDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IIncomeDataDTO incomeDataDTO = (IIncomeDataDTO)incomeDataDTO1;
            IncomeDataEntity incomeDataEntity = EM().find(IncomeDataEntity.class, incomeDataDTO.getCode());
            if (incomeDataEntity == null) {
                throw new ItemNotFoundException();
            }
            // em.remove(incomeDataEntity);
            doRemove(incomeDataEntity);
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
     * Get IncomeDataEntity By Primary Key * @param id
     * @return IIncomeDataDTO
     */
    @Override
    public IIncomeDataDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {

        try {
            IncomeDataEntity incomeDataEntity = EM().find(IncomeDataEntity.class, id);
            if (incomeDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IIncomeDataDTO incomeDataDTO = EmpDTOFactory.createIncomeDataDTO(incomeDataEntity);
            return incomeDataDTO;

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
     * Search for IncomeDataEntity * <br> * @return List
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
            Long id = (Long)EM().createNamedQuery("IncomeDataEntity.findNewId").getSingleResult();
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


