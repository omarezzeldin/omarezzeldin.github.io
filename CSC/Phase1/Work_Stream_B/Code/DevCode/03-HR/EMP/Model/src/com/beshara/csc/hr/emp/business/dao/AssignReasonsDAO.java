package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAssignReasonsDTO;
import com.beshara.csc.hr.emp.business.entity.AssignReasonsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IAssignReasonsEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;


public class AssignReasonsDAO extends EmpBaseDAO {
    public AssignReasonsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new AssignReasonsDAO();
    }

    /**<code>select o from AssignReasonsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IAssignReasonsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<AssignReasonsEntity> list = EM().createNamedQuery("AssignReasonsEntity.findAll").getResultList();
            for (AssignReasonsEntity assignReasons : list) {
                arrayList.add(EmpDTOFactory.createAssignReasonsDTO(assignReasons));
            }
            if (arrayList.size() == 0)
                throw new ItemNotFoundException();
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
     * Create a New AssignReasonsEntity * @param assignReasonsDTO
     * @return IAssignReasonsDTO
     */
    @Override
    public IAssignReasonsDTO add(IBasicDTO assignReasonsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            AssignReasonsEntity assignReasonsEntity = new AssignReasonsEntity();
            IAssignReasonsDTO assignReasonsDTO = (IAssignReasonsDTO)assignReasonsDTO1;
            assignReasonsEntity.setAssreasonCode(((IAssignReasonsEntityKey)assignReasonsDTO.getCode()).getAssreasonCode());
            assignReasonsEntity.setAssreasonName(assignReasonsDTO.getName());
            assignReasonsEntity.setCreatedBy(assignReasonsDTO.getCreatedBy());
            assignReasonsEntity.setCreatedDate(assignReasonsDTO.getCreatedDate());
            assignReasonsEntity.setAuditStatus(assignReasonsDTO.getAuditStatus());
            assignReasonsEntity.setTabrecSerial(assignReasonsDTO.getTabrecSerial());
            doAdd(assignReasonsEntity);
            // Set PK after creation
            return assignReasonsDTO;
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
     * Update an Existing AssignReasonsEntity * @param assignReasonsDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO assignReasonsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IAssignReasonsDTO assignReasonsDTO = (IAssignReasonsDTO)assignReasonsDTO1;
            AssignReasonsEntity assignReasonsEntity =
                EM().find(AssignReasonsEntity.class, (IAssignReasonsEntityKey)assignReasonsDTO.getCode());
            assignReasonsEntity.setAssreasonName(assignReasonsDTO.getName());
            assignReasonsEntity.setCreatedBy(assignReasonsDTO.getCreatedBy());
            assignReasonsEntity.setCreatedDate(assignReasonsDTO.getCreatedDate());
            assignReasonsEntity.setLastUpdatedBy(assignReasonsDTO.getLastUpdatedBy());
            assignReasonsEntity.setLastUpdatedDate(assignReasonsDTO.getLastUpdatedDate());
            assignReasonsEntity.setAuditStatus(assignReasonsDTO.getAuditStatus());
            assignReasonsEntity.setTabrecSerial(assignReasonsDTO.getTabrecSerial());
            doUpdate(assignReasonsEntity);
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
     * Remove an Existing AssignReasonsEntity * @param assignReasonsDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO assignReasonsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IAssignReasonsDTO assignReasonsDTO = (IAssignReasonsDTO)assignReasonsDTO1;
            AssignReasonsEntity assignReasonsEntity =
                EM().find(AssignReasonsEntity.class, (IAssignReasonsEntityKey)assignReasonsDTO.getCode());
            if (assignReasonsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(assignReasonsEntity);
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
     * Get AssignReasonsEntity By Primary Key * @param id
     * @return IAssignReasonsDTO
     */
    @Override
    public IAssignReasonsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IAssignReasonsEntityKey id = (IAssignReasonsEntityKey)id1;
            AssignReasonsEntity assignReasonsEntity = EM().find(AssignReasonsEntity.class, id);
            if (assignReasonsEntity == null) {
                throw new ItemNotFoundException();
            }
            IAssignReasonsDTO assignReasonsDTO = EmpDTOFactory.createAssignReasonsDTO(assignReasonsEntity);
            return assignReasonsDTO;
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
    public Long queryAssignReasonsEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("AssignReasonsEntity.findNewId").getSingleResult();
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
     * get list of AssignReasons with code and name ordered by name * @return List of IAssignReasonsDTO
     * @throws RemoteException
     */

    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            return EM().createNamedQuery("AssignReasonsEntity.getCodeName").getResultList();
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
     * list all AssignReasons match search name , the resulted list will be ordered by name * @param name
     * @return List of IAssignReasonsDTO
     * @throws RemoteException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {

        //By MLotfy new search
        try {
            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from AssignReasonsEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.assreasonName", name));

            query.append(" order by o.assreasonName");

            List<AssignReasonsEntity> list = EM().createQuery(query.toString()).getResultList();

            for (AssignReasonsEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createAssignReasonsDTO(hireTypes));
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
     * list all AssignReasons match search code * @param code
     * @return List of IAssignReasonsDTO
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(EmpEntityKeyFactory.createAssignReasonsEntityKey((Long)code)));
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
