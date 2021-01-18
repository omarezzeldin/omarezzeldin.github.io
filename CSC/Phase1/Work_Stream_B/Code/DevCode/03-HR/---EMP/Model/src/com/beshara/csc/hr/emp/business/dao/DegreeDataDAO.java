package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IDegreeDataDTO;
import com.beshara.csc.hr.emp.business.entity.DegreeDataEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class DegreeDataDAO extends EmpBaseDAO {

    public DegreeDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new DegreeDataDAO();
    }


    /**<code>select o from DegreeDataEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IDegreeDataDTO> getAll() throws DataBaseException, SharedApplicationException {

        try {
            ArrayList arrayList = new ArrayList();
            List<DegreeDataEntity> list = EM().createNamedQuery("DegreeDataEntity.findAll").getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }

            for (DegreeDataEntity degreeData : list) {
                arrayList.add(EmpDTOFactory.createDegreeDataDTO(degreeData));
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
     * Create a New DegreeDataEntity * @param degreeDataDTO
     * @return IDegreeDataDTO
     */
    @Override
    public IDegreeDataDTO add(IBasicDTO degreeDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            DegreeDataEntity degreeDataEntity = new DegreeDataEntity();
            IDegreeDataDTO degreeDataDTO = (IDegreeDataDTO)degreeDataDTO1;
            //degreeDataEntity.setCivilId(degreeDataDTO.getCivilId());
            Long civilId=findNewId();
            degreeDataEntity.setCivilId(civilId);
            degreeDataEntity.setFincadreCode(degreeDataDTO.getFincadreCode());
            degreeDataEntity.setFingroupCode(degreeDataDTO.getFingroupCode());
            degreeDataEntity.setFindegreeCode(degreeDataDTO.getFindegreeCode());
            degreeDataEntity.setDegreeDate(degreeDataDTO.getDegreeDate());
            degreeDataEntity.setStatus(degreeDataDTO.getStatus());
            // this.persistEntity(degreeDataEntity);
            doAdd(degreeDataEntity);
            degreeDataDTO.setCivilId(civilId);

            // Set PK after creation
            return degreeDataDTO;

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
     * Update an Existing DegreeDataEntity * @param degreeDataDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO degreeDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IDegreeDataDTO degreeDataDTO = (IDegreeDataDTO)degreeDataDTO1;
            DegreeDataEntity degreeDataEntity = EM().find(DegreeDataEntity.class, degreeDataDTO.getCode());
            degreeDataEntity.setCivilId(degreeDataDTO.getCivilId());
            degreeDataEntity.setFincadreCode(degreeDataDTO.getFincadreCode());
            degreeDataEntity.setFingroupCode(degreeDataDTO.getFingroupCode());
            degreeDataEntity.setFindegreeCode(degreeDataDTO.getFindegreeCode());
            degreeDataEntity.setDegreeDate(degreeDataDTO.getDegreeDate());
            degreeDataEntity.setStatus(degreeDataDTO.getStatus());
            // updated by A.AGAMY for data audit
            doUpdate(degreeDataEntity);
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
     * Remove an Existing DegreeDataEntity * @param degreeDataDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO degreeDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IDegreeDataDTO degreeDataDTO = (IDegreeDataDTO)degreeDataDTO1;
            DegreeDataEntity degreeDataEntity = EM().find(DegreeDataEntity.class, degreeDataDTO.getCode());
            if (degreeDataEntity == null) {
                throw new ItemNotFoundException();
            }
            //em.remove(degreeDataEntity);
            doRemove(degreeDataEntity);
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
     * Get DegreeDataEntity By Primary Key * @param id
     * @return IDegreeDataDTO
     */
    @Override
    public IDegreeDataDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {

        try {
            DegreeDataEntity degreeDataEntity = EM().find(DegreeDataEntity.class, id);
            if (degreeDataEntity == null) {
                throw new ItemNotFoundException();
            }
            IDegreeDataDTO degreeDataDTO = EmpDTOFactory.createDegreeDataDTO(degreeDataEntity);
            return degreeDataDTO;
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
     * Search for DegreeDataEntity * <br> * @return List
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

        Long id = (Long)EM().createNamedQuery("DegreeDataEntity.findNewId").getSingleResult();

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
