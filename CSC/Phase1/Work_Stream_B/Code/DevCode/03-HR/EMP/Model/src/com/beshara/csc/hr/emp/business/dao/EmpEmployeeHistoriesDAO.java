package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeHistoriesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEmployeeHistoriesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpEmployeeHistoriesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class EmpEmployeeHistoriesDAO extends EmpBaseDAO {
    public EmpEmployeeHistoriesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpEmployeeHistoriesDAO();
    }

    /**<code>select o from EmpEmployeeHistoriesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IEmpEmployeeHistoriesDTO> getAll() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<EmpEmployeeHistoriesEntity> list =
                EM().createNamedQuery("EmpEmployeeHistoriesEntity.findAll").getResultList();
            for (EmpEmployeeHistoriesEntity empEmployeeHistories : list) {
                arrayList.add(EmpDTOFactory.createEmpEmployeeHistoriesDTO(empEmployeeHistories));
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
     * Create a New EmpEmployeeHistoriesEntity * @param empEmployeeHistoriesDTO
     * @return IEmpEmployeeHistoriesDTO
     */
    @Override
    public IEmpEmployeeHistoriesDTO add(IBasicDTO empEmployeeHistoriesDTO1) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            EmpEmployeeHistoriesEntity empEmployeeHistoriesEntity = new EmpEmployeeHistoriesEntity();
            IEmpEmployeeHistoriesDTO empEmployeeHistoriesDTO = (IEmpEmployeeHistoriesDTO)empEmployeeHistoriesDTO1;
            empEmployeeHistoriesEntity.setSerial(empEmployeeHistoriesDTO.getSerial());
            empEmployeeHistoriesEntity.setCivilId(empEmployeeHistoriesDTO.getCivilId());
            empEmployeeHistoriesEntity.setWrkCode(empEmployeeHistoriesDTO.getWrkCode());
            empEmployeeHistoriesEntity.setMinCode(empEmployeeHistoriesDTO.getMinCode());
            empEmployeeHistoriesEntity.setJobCode(empEmployeeHistoriesDTO.getJobCode());
            empEmployeeHistoriesEntity.setTechJobCode(empEmployeeHistoriesDTO.getTechJobCode());
            empEmployeeHistoriesEntity.setFromDate(empEmployeeHistoriesDTO.getFromDate());
            empEmployeeHistoriesEntity.setUntilDate(empEmployeeHistoriesDTO.getUntilDate());
            empEmployeeHistoriesEntity.setAuditBy(empEmployeeHistoriesDTO.getAuditBy());
            empEmployeeHistoriesEntity.setAuditDate(empEmployeeHistoriesDTO.getAuditDate());
            empEmployeeHistoriesEntity.setPisFlag(empEmployeeHistoriesDTO.getPisFlag());
            empEmployeeHistoriesEntity.setPerFlag(empEmployeeHistoriesDTO.getPerFlag());
            empEmployeeHistoriesEntity.setTrxtypeCode(empEmployeeHistoriesDTO.getTrxtypeCode());
            empEmployeeHistoriesEntity.setAuditStatus(empEmployeeHistoriesDTO.getAuditStatus());
            empEmployeeHistoriesEntity.setTabrecSerial(empEmployeeHistoriesDTO.getTabrecSerial());
            empEmployeeHistoriesEntity.setCreatedBy(empEmployeeHistoriesDTO.getCreatedBy());
            empEmployeeHistoriesEntity.setCreatedDate(empEmployeeHistoriesDTO.getCreatedDate());
            doAdd(empEmployeeHistoriesEntity);
            // Set PK after creation
            return empEmployeeHistoriesDTO;
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
     * Update an Existing EmpEmployeeHistoriesEntity * @param empEmployeeHistoriesDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO empEmployeeHistoriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpEmployeeHistoriesDTO empEmployeeHistoriesDTO = (IEmpEmployeeHistoriesDTO)empEmployeeHistoriesDTO1;
            EmpEmployeeHistoriesEntity empEmployeeHistoriesEntity =
                EM().find(EmpEmployeeHistoriesEntity.class, (IEmpEmployeeHistoriesEntityKey)empEmployeeHistoriesDTO.getCode());
            empEmployeeHistoriesEntity.setSerial(empEmployeeHistoriesDTO.getSerial());
            empEmployeeHistoriesEntity.setCivilId(empEmployeeHistoriesDTO.getCivilId());
            empEmployeeHistoriesEntity.setWrkCode(empEmployeeHistoriesDTO.getWrkCode());
            empEmployeeHistoriesEntity.setMinCode(empEmployeeHistoriesDTO.getMinCode());
            empEmployeeHistoriesEntity.setJobCode(empEmployeeHistoriesDTO.getJobCode());
            empEmployeeHistoriesEntity.setTechJobCode(empEmployeeHistoriesDTO.getTechJobCode());
            empEmployeeHistoriesEntity.setFromDate(empEmployeeHistoriesDTO.getFromDate());
            empEmployeeHistoriesEntity.setUntilDate(empEmployeeHistoriesDTO.getUntilDate());
            empEmployeeHistoriesEntity.setAuditBy(empEmployeeHistoriesDTO.getAuditBy());
            empEmployeeHistoriesEntity.setAuditDate(empEmployeeHistoriesDTO.getAuditDate());
            empEmployeeHistoriesEntity.setPisFlag(empEmployeeHistoriesDTO.getPisFlag());
            empEmployeeHistoriesEntity.setPerFlag(empEmployeeHistoriesDTO.getPerFlag());
            empEmployeeHistoriesEntity.setTrxtypeCode(empEmployeeHistoriesDTO.getTrxtypeCode());
            empEmployeeHistoriesEntity.setAuditStatus(empEmployeeHistoriesDTO.getAuditStatus());
            empEmployeeHistoriesEntity.setTabrecSerial(empEmployeeHistoriesDTO.getTabrecSerial());
            empEmployeeHistoriesEntity.setCreatedBy(empEmployeeHistoriesDTO.getCreatedBy());
            empEmployeeHistoriesEntity.setCreatedDate(empEmployeeHistoriesDTO.getCreatedDate());
            empEmployeeHistoriesEntity.setLastUpdatedBy(empEmployeeHistoriesDTO.getLastUpdatedBy());
            empEmployeeHistoriesEntity.setLastUpdatedDate(empEmployeeHistoriesDTO.getLastUpdatedDate());
            doUpdate(empEmployeeHistoriesEntity);
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
     * Remove an Existing EmpEmployeeHistoriesEntity * @param empEmployeeHistoriesDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO empEmployeeHistoriesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpEmployeeHistoriesDTO empEmployeeHistoriesDTO = (IEmpEmployeeHistoriesDTO)empEmployeeHistoriesDTO1;
            EmpEmployeeHistoriesEntity empEmployeeHistoriesEntity =
                EM().find(EmpEmployeeHistoriesEntity.class, (IEmpEmployeeHistoriesEntityKey)empEmployeeHistoriesDTO.getCode());
            if (empEmployeeHistoriesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(empEmployeeHistoriesEntity);
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
     * Get EmpEmployeeHistoriesEntity By Primary Key * @param id
     * @return IEmpEmployeeHistoriesDTO
     */
    @Override
    public IEmpEmployeeHistoriesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpEmployeeHistoriesEntityKey id = (IEmpEmployeeHistoriesEntityKey)id1;
            EmpEmployeeHistoriesEntity empEmployeeHistoriesEntity = EM().find(EmpEmployeeHistoriesEntity.class, id);
            if (empEmployeeHistoriesEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpEmployeeHistoriesDTO empEmployeeHistoriesDTO =
                EmpDTOFactory.createEmpEmployeeHistoriesDTO(empEmployeeHistoriesEntity);
            return empEmployeeHistoriesDTO;
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
    public Long queryEmpEmployeeHistoriesEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpEmployeeHistoriesEntity.findNewId").getSingleResult();
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

    public List<IEmpEmployeeHistoriesDTO> getAllByCivilID(Long civilID, Long minCode) throws DataBaseException,
                                                                                             SharedApplicationException { // StringBuffer ejbql = new StringBuffer ( "SELECT o FROM EmpEmployeeHistoriesEntity o WHERE o.civilId like :civilID" ) ;
        // Long minCode = 13L ;
        try {
            StringBuffer ejbql =
                new StringBuffer("SELECT o FROM EmpEmployeeHistoriesEntity o WHERE o.civilId = :civilID AND o.minCode=:minCode");
            System.out.println("Query=" + ejbql.toString());
            Query query = EM().createQuery(ejbql.toString());
            if (civilID != null)
                query.setParameter("civilID", civilID);
            if (minCode != null)
                query.setParameter("minCode", minCode);
            List<EmpEmployeeHistoriesEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IEmpEmployeeHistoriesDTO> listDTO = new ArrayList<IEmpEmployeeHistoriesDTO>();
            for (EmpEmployeeHistoriesEntity entity :
                 list) { // listDTO.add ( EmpEntityConverter.getEmpEmployeeHistoriesDTO ( entity ) ) ;
                listDTO.add(EmpDTOFactory.createEmpEmployeeHistoriesDTO(entity));
            }
            return listDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> searchEmpEmployeeHistoriesDTO(IBasicDTO empEmployeeHistoriesDTO) throws DataBaseException,
                                                                                                   SharedApplicationException {
        try {
            IEmpEmployeeHistoriesDTO searchEmpEmployeeHistoriesDTO1 =
                (IEmpEmployeeHistoriesDTO)empEmployeeHistoriesDTO;
            StringBuffer ejbql =
                new StringBuffer("select o from EmpEmployeeHistoriesEntity o where o.serial=o.serial ");
            // if ( searchEmpEmployeeHistoriesDTO1.getCode ( ) != null )
            // ejbql.append ( " AND o.extdattypeCode = :extdattypeCode" ) ;
            if (searchEmpEmployeeHistoriesDTO1.getSerial() != null)
                ejbql.append(" AND o.serial = :serial");
            if (searchEmpEmployeeHistoriesDTO1.getCivilId() != null)
                ejbql.append(" AND o.civilId = :civilId");
            if (searchEmpEmployeeHistoriesDTO1.getWrkCode() != null &&
                !searchEmpEmployeeHistoriesDTO1.getWrkCode().equals(""))
                ejbql.append(" AND o.wrkCode = :wrkCode");
            if (searchEmpEmployeeHistoriesDTO1.getMinCode() != null)
                ejbql.append(" AND o.minCode = :minCode");
            if (searchEmpEmployeeHistoriesDTO1.getJobCode() != null &&
                !searchEmpEmployeeHistoriesDTO1.getJobCode().equals(""))
                ejbql.append(" AND o.jobCode like :jobCode");
            if (searchEmpEmployeeHistoriesDTO1.getTechJobCode() != null &&
                !searchEmpEmployeeHistoriesDTO1.getTechJobCode().equals(""))
                ejbql.append(" AND o.techJobCode = :techJobCode");
            if (searchEmpEmployeeHistoriesDTO1.getFromDate() != null &&
                searchEmpEmployeeHistoriesDTO1.getUntilDate() != null)
                ejbql.append(" AND o.fromDate BETWEEN :fromDate AND :untilDate AND o.untilDate BETWEEN :fromDate AND :untilDate ");
            else if (searchEmpEmployeeHistoriesDTO1.getFromDate() != null)
                ejbql.append(" AND o.fromDate >= :fromDate");
            else if (searchEmpEmployeeHistoriesDTO1.getUntilDate() != null)
                ejbql.append(" AND ( o.untilDate <= :untilDate OR o.untilDate is null ) ");
            System.out.println("Query=" + ejbql.toString());
            Query query = EM().createQuery(ejbql.toString());
            if (searchEmpEmployeeHistoriesDTO1.getSerial() != null)
                query.setParameter("serial", searchEmpEmployeeHistoriesDTO1.getSerial());
            if (searchEmpEmployeeHistoriesDTO1.getCivilId() != null)
                query.setParameter("civilId", searchEmpEmployeeHistoriesDTO1.getCivilId());
            if (searchEmpEmployeeHistoriesDTO1.getWrkCode() != null &&
                !searchEmpEmployeeHistoriesDTO1.getWrkCode().equals(""))
                query.setParameter("wrkCode", searchEmpEmployeeHistoriesDTO1.getWrkCode());
            if (searchEmpEmployeeHistoriesDTO1.getMinCode() != null)
                query.setParameter("minCode", searchEmpEmployeeHistoriesDTO1.getMinCode());
            if (searchEmpEmployeeHistoriesDTO1.getJobCode() != null &&
                !searchEmpEmployeeHistoriesDTO1.getJobCode().equals(""))
                query.setParameter("jobCode", searchEmpEmployeeHistoriesDTO1.getJobCode());
            if (searchEmpEmployeeHistoriesDTO1.getTechJobCode() != null &&
                !searchEmpEmployeeHistoriesDTO1.getTechJobCode().equals(""))
                query.setParameter("techJobCode", searchEmpEmployeeHistoriesDTO1.getTechJobCode());
            if (searchEmpEmployeeHistoriesDTO1.getFromDate() != null)
                query.setParameter("fromDate", searchEmpEmployeeHistoriesDTO1.getFromDate());
            if (searchEmpEmployeeHistoriesDTO1.getUntilDate() != null)
                query.setParameter("untilDate", searchEmpEmployeeHistoriesDTO1.getUntilDate());
            List<EmpEmployeeHistoriesEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmpEmployeeHistoriesEntity entity :
                 list) { // listDTO.add ( EmpEntityConverter.getEmpEmployeeHistoriesDTO ( entity ) ) ;
                listDTO.add(EmpDTOFactory.createEmpEmployeeHistoriesDTO(entity));
            }
            return listDTO;
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
     * get all data for certain employee
     * @param civilId
     * @return List
     *
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> getEmployeeDataByCivilID(Long civilId) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("EmpEmployeeHistoriesEntity.getEmployeeDataByCivilID");
            query.setParameter("civilId", civilId);

            List<EmpEmployeeHistoriesEntity> list = query.getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (EmpEmployeeHistoriesEntity entity :
                 list) { // listDTO.add ( EmpEntityConverter.getEmpEmployeeHistoriesDTO ( entity ) ) ;
                listDTO.add(EmpDTOFactory.createEmpEmployeeHistoriesDTO(entity));
            }
            return listDTO;
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
     * get first hire date for employee
     * @param civilId
     * @return Date
     * @throws RemoteException
     * @throws FinderException
     */
    public Date getFirstHireDate(Long civilId) throws DataBaseException, SharedApplicationException {
        /* try {
            Query query = EM().createNamedQuery("EmpEmployeeHistoriesEntity.getFirstHireDate");
            query.setParameter("civilId", civilId);

            Timestamp timeStampDate = (Timestamp)query.getSingleResult();
            if (timeStampDate == null)
                throw new ItemNotFoundException();

            long milliseconds = timeStampDate.getTime() + (timeStampDate.getNanos() / 1000000);
            return new Date(milliseconds);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        } */
        return null;
    }
}
