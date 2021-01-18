package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeeHchildCatsDTO;
import com.beshara.csc.hr.emp.business.entity.EmployeeHchildCatsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class EmployeeHchildCatsDAO extends EmpBaseDAO {
    public EmployeeHchildCatsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmployeeHchildCatsDAO();
    }

    @Override
    public List<IEmployeeHchildCatsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeeHchildCatsEntity> list =
                EM().createNamedQuery("EmployeeHchildCatsEntity.findAll").getResultList();
            for (EmployeeHchildCatsEntity employeeHchildCats : list) {
                arrayList.add(EmpDTOFactory.createEmployeeHchildCatsDTO(employeeHchildCats));
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
     * Create a New EmployeeHchildCatsEntity * @param employeeHchildCatsDTO
     * @return IEmployeeHchildCatsDTO
     */
    @Override
    public IEmployeeHchildCatsDTO add(IBasicDTO employeeHchildCatsDTO1) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            EmployeeHchildCatsEntity employeeHchildCatsEntity = new EmployeeHchildCatsEntity();
            IEmployeeHchildCatsDTO employeeHchildCatsDTO = (IEmployeeHchildCatsDTO)employeeHchildCatsDTO1;
            employeeHchildCatsEntity.setCivilId(employeeHchildCatsDTO.getCivilId());
            employeeHchildCatsEntity.setHchildsCatCode(employeeHchildCatsDTO.getHchildsCatCode());
            employeeHchildCatsEntity.setHchildsCatName(employeeHchildCatsDTO.getHchildsCatName());
            employeeHchildCatsEntity.setHfromDate(employeeHchildCatsDTO.getHfromDate());
            employeeHchildCatsEntity.setHrValue(employeeHchildCatsDTO.getHrValue());
            doAdd(employeeHchildCatsEntity);
            // Set PK after creation
            return employeeHchildCatsDTO;
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
     * Update an Existing EmployeeHchildCatsEntity * @param employeeHchildCatsDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO employeeHchildCatsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeeHchildCatsDTO employeeHchildCatsDTO = (IEmployeeHchildCatsDTO)employeeHchildCatsDTO1;
            EmployeeHchildCatsEntity employeeHchildCatsEntity =
                EM().find(EmployeeHchildCatsEntity.class, employeeHchildCatsDTO.getCode());
            employeeHchildCatsEntity.setCivilId(employeeHchildCatsDTO.getCivilId());
            employeeHchildCatsEntity.setHchildsCatCode(employeeHchildCatsDTO.getHchildsCatCode());
            employeeHchildCatsEntity.setHchildsCatName(employeeHchildCatsDTO.getHchildsCatName());
            employeeHchildCatsEntity.setHfromDate(employeeHchildCatsDTO.getHfromDate());
            employeeHchildCatsEntity.setHrValue(employeeHchildCatsDTO.getHrValue());
            doUpdate(employeeHchildCatsEntity);
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
     * Remove an Existing EmployeeHchildCatsEntity * @param employeeHchildCatsDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO employeeHchildCatsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmployeeHchildCatsDTO employeeHchildCatsDTO = (IEmployeeHchildCatsDTO)employeeHchildCatsDTO1;
            EmployeeHchildCatsEntity employeeHchildCatsEntity =
                EM().find(EmployeeHchildCatsEntity.class, employeeHchildCatsDTO.getCode());
            if (employeeHchildCatsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(employeeHchildCatsEntity);
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
     * Get EmployeeHchildCatsEntity By Primary Key * @param id
     * @return IEmployeeHchildCatsDTO
     */
    @Override
    public IEmployeeHchildCatsDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {
        try {
            EmployeeHchildCatsEntity employeeHchildCatsEntity = EM().find(EmployeeHchildCatsEntity.class, id);
            if (employeeHchildCatsEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeeHchildCatsDTO employeeHchildCatsDTO =
                EmpDTOFactory.createEmployeeHchildCatsDTO(employeeHchildCatsEntity);
            return employeeHchildCatsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("EmployeeHchildCatsEntity.findNewId");
            Long id = (Long)query.getSingleResult();
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
     * Get the MaxId of AbilitiesEntity * <br> * @return Object
     */
    public Long queryEmployeeHchildCatsEntityFindNewId() throws RemoteException {
        Long id = (Long)EM().createNamedQuery("EmployeeHchildCatsEntity.findNewId").getSingleResult();
        if (id != null) {
            return id;
        } else {
            return new Long(0);
        }
    }
}
