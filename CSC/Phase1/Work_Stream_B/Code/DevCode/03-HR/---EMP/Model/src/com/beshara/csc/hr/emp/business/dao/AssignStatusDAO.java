package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAssignStatusDTO;
import com.beshara.csc.hr.emp.business.entity.AssignStatusEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IAssignStatusEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;


public class AssignStatusDAO extends EmpBaseDAO {
    public AssignStatusDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new AssignStatusDAO();
    }

    /**<code>select o from AssignStatusEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IAssignStatusDTO> getAll() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<AssignStatusEntity> list = EM().createNamedQuery("AssignStatusEntity.findAll").getResultList();
            for (AssignStatusEntity assignStatus : list) {
                arrayList.add(EmpDTOFactory.createAssignStatusDTO(assignStatus));
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
     * Create a New AssignStatusEntity * @param assignStatusDTO
     * @return IAssignStatusDTO
     */
    @Override
    public IAssignStatusDTO add(IBasicDTO assignStatusDTO1) throws DataBaseException, SharedApplicationException {
        AssignStatusEntity assignStatusEntity = new AssignStatusEntity();
        try {
            IAssignStatusDTO assignStatusDTO = (IAssignStatusDTO)assignStatusDTO1;
            assignStatusEntity.setAssstatusCode(((IAssignStatusEntityKey)assignStatusDTO.getCode()).getAssstatusCode());
            assignStatusEntity.setAssstatusName(assignStatusDTO.getName());
            assignStatusEntity.setCreatedBy(assignStatusDTO.getCreatedBy());
            assignStatusEntity.setCreatedDate(assignStatusDTO.getCreatedDate());
            assignStatusEntity.setAuditStatus(assignStatusDTO.getAuditStatus());
            assignStatusEntity.setTabrecSerial(assignStatusDTO.getTabrecSerial());
            doAdd(assignStatusEntity);
            // Set PK after creation
            return assignStatusDTO;
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
     * Update an Existing AssignStatusEntity * @param assignStatusDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO assignStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IAssignStatusDTO assignStatusDTO = (IAssignStatusDTO)assignStatusDTO1;
            AssignStatusEntity assignStatusEntity =
                EM().find(AssignStatusEntity.class, (IAssignStatusEntityKey)assignStatusDTO.getCode());
            assignStatusEntity.setAssstatusName(assignStatusDTO.getName());
            assignStatusEntity.setCreatedBy(assignStatusDTO.getCreatedBy());
            assignStatusEntity.setCreatedDate(assignStatusDTO.getCreatedDate());
            assignStatusEntity.setLastUpdatedBy(assignStatusDTO.getLastUpdatedBy());
            assignStatusEntity.setLastUpdatedDate(assignStatusDTO.getLastUpdatedDate());
            assignStatusEntity.setAuditStatus(assignStatusDTO.getAuditStatus());
            assignStatusEntity.setTabrecSerial(assignStatusDTO.getTabrecSerial());
            doUpdate(assignStatusEntity);
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
     * Remove an Existing AssignStatusEntity * @param assignStatusDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO assignStatusDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IAssignStatusDTO assignStatusDTO = (IAssignStatusDTO)assignStatusDTO1;
            AssignStatusEntity assignStatusEntity = EM().find(AssignStatusEntity.class, assignStatusDTO.getCode());
            if (assignStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(assignStatusEntity);
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
     * Get AssignStatusEntity By Primary Key * @param id
     * @return IAssignStatusDTO
     */
    public IAssignStatusDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IAssignStatusEntityKey id = (IAssignStatusEntityKey)id1;
            AssignStatusEntity assignStatusEntity = EM().find(AssignStatusEntity.class, id);
            if (assignStatusEntity == null) {
                throw new ItemNotFoundException();
            }
            IAssignStatusDTO assignStatusDTO = EmpDTOFactory.createAssignStatusDTO(assignStatusEntity);
            return assignStatusDTO;
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
    public Long queryAssignStatusEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("AssignStatusEntity.findNewId").getSingleResult();
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
     * get list of IAssignStatusDTO with code and name ordered by name * @return List of HireStagesEntity
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("AssignStatusEntity.getCodeName").getResultList();
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
     * list all AssignStatus match search name , the resulted list will be ordered by name * @param name
     * @return List of IAssignStatusDTO
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {

        //By MLotfy new search
        ArrayList arrayList = new ArrayList();
        try {
            StringBuffer query = new StringBuffer("select o from AssignStatusEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.assstatusName", name));

            query.append(" order by o.assstatusName");

            List<AssignStatusEntity> list = EM().createQuery(query.toString()).getResultList();

            for (AssignStatusEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createAssignStatusDTO(hireTypes));
            }

            if (arrayList.size() == 0) {
                throw new ItemNotFoundException();
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
     * list all AssignStatus match search code * @param code
     * @return List of IAssignStatusDTO
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(EmpEntityKeyFactory.createAssignStatusEntityKey((Long)code)));
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
