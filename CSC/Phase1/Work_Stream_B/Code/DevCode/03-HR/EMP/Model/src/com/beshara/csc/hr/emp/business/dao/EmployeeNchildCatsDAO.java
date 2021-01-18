package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmployeeNchildCatsDTO;
import com.beshara.csc.hr.emp.business.entity.EmployeeNchildCatsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class EmployeeNchildCatsDAO extends EmpBaseDAO {

    public EmployeeNchildCatsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmployeeNchildCatsDAO();
    }


    /**<code>select o from EmployeeNchildCatsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IEmployeeNchildCatsDTO> getAll() throws DataBaseException, SharedApplicationException {

        try {
            ArrayList arrayList = new ArrayList();
            List<EmployeeNchildCatsEntity> list =
                EM().createNamedQuery("EmployeeNchildCatsEntity.findAll").getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            for (EmployeeNchildCatsEntity employeeNchildCats : list) {
                arrayList.add(EmpDTOFactory.createEmployeeNchildCatsDTO(employeeNchildCats));
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
     * Create a New EmployeeNchildCatsEntity * @param employeeNchildCatsDTO
     * @return IEmployeeNchildCatsDTO
     */
    @Override
    public IEmployeeNchildCatsDTO add(IBasicDTO employeeNchildCatsDTO1) throws DataBaseException,
                                                                               SharedApplicationException {

        try {
            EmployeeNchildCatsEntity employeeNchildCatsEntity = new EmployeeNchildCatsEntity();
            IEmployeeNchildCatsDTO employeeNchildCatsDTO = (IEmployeeNchildCatsDTO)employeeNchildCatsDTO1;
            //employeeNchildCatsEntity.setCivilId(employeeNchildCatsDTO.getCivilId());
            Long civilId=findNewId();
            employeeNchildCatsEntity.setCivilId(civilId);
            employeeNchildCatsEntity.setNchildsCatCode(employeeNchildCatsDTO.getNchildsCatCode());
            employeeNchildCatsEntity.setNchildsCatName(employeeNchildCatsDTO.getNchildsCatName());
            employeeNchildCatsEntity.setNfromDate(employeeNchildCatsDTO.getNfromDate());
            employeeNchildCatsEntity.setNrValue(employeeNchildCatsDTO.getNrValue());
            // this.persistEntity(employeeNchildCatsEntity);
            doAdd(employeeNchildCatsEntity);
            employeeNchildCatsDTO.setCivilId(civilId);
            // Set PK after creation
            return employeeNchildCatsDTO;

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
     * Update an Existing EmployeeNchildCatsEntity * @param employeeNchildCatsDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO employeeNchildCatsDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmployeeNchildCatsDTO employeeNchildCatsDTO = (IEmployeeNchildCatsDTO)employeeNchildCatsDTO1;
            EmployeeNchildCatsEntity employeeNchildCatsEntity =
                EM().find(EmployeeNchildCatsEntity.class, employeeNchildCatsDTO.getCode());
            employeeNchildCatsEntity.setCivilId(employeeNchildCatsDTO.getCivilId());
            employeeNchildCatsEntity.setNchildsCatCode(employeeNchildCatsDTO.getNchildsCatCode());
            employeeNchildCatsEntity.setNchildsCatName(employeeNchildCatsDTO.getNchildsCatName());
            employeeNchildCatsEntity.setNfromDate(employeeNchildCatsDTO.getNfromDate());
            employeeNchildCatsEntity.setNrValue(employeeNchildCatsDTO.getNrValue());
            doUpdate(employeeNchildCatsEntity);
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
     * Remove an Existing EmployeeNchildCatsEntity * @param employeeNchildCatsDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO employeeNchildCatsDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmployeeNchildCatsDTO employeeNchildCatsDTO = (IEmployeeNchildCatsDTO)employeeNchildCatsDTO1;
            EmployeeNchildCatsEntity employeeNchildCatsEntity =
                EM().find(EmployeeNchildCatsEntity.class, employeeNchildCatsDTO.getCode());
            if (employeeNchildCatsEntity == null) {
                throw new ItemNotFoundException();
            }
            // em.remove(employeeNchildCatsEntity);
            doRemove(employeeNchildCatsEntity);
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
     * Get EmployeeNchildCatsEntity By Primary Key * @param id
     * @return IEmployeeNchildCatsDTO
     */
    @Override
    public IEmployeeNchildCatsDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {

        try {
            EmployeeNchildCatsEntity employeeNchildCatsEntity = EM().find(EmployeeNchildCatsEntity.class, id);
            if (employeeNchildCatsEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmployeeNchildCatsDTO employeeNchildCatsDTO =
                EmpDTOFactory.createEmployeeNchildCatsDTO(employeeNchildCatsEntity);
            return employeeNchildCatsDTO;

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
     * Search for EmployeeNchildCatsEntity * <br> * @return List
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
            Long id = (Long)EM().createNamedQuery("EmployeeNchildCatsEntity.findNewId").getSingleResult();
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
