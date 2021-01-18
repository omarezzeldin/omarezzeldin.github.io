package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireProceduresDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.IHireProceduresEntityKey;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class HireProceduresDAO extends EmpBaseDAO {

    public HireProceduresDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HireProceduresDAO();
    }


    /**<code>select o from HireProceduresEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IHireProceduresDTO> getAll() throws DataBaseException, SharedApplicationException {

        try {
            ArrayList arrayList = new ArrayList();
            List<HireProceduresEntity> list =
                EM().createNamedQuery("HireProceduresEntity.findAll").setHint("toplink.refresh",
                                                                              "true").getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            for (HireProceduresEntity hireProcedures : list) {
                arrayList.add(EmpDTOFactory.createHireProceduresDTO(hireProcedures));
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
     * Create a New HireProceduresEntity * @param hireProceduresDTO1
     * @return IHireProceduresDTO
     */
    @Override
    public IHireProceduresDTO add(IBasicDTO hireProceduresDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HireProceduresEntity hireProceduresEntity = new HireProceduresEntity();
            IHireProceduresDTO hireProceduresDTO = (IHireProceduresDTO)hireProceduresDTO1;
            //hireProceduresEntity.setHirprocedureCode(((IHireProceduresEntityKey)hireProceduresDTO.getCode()).getHirprocedureCode());

            Long hirprocedureCode = findMaxId();
            hireProceduresEntity.setHirprocedureCode(hirprocedureCode);
            hireProceduresEntity.setHirprocedureName(hireProceduresDTO.getName());

            IMinistriesDTO ministry = hireProceduresDTO.getMinistriesDTO();
            if (ministry == null) {
                throw new InvalidDataEntryException();
            }
            IMinistriesEntityKey ek = (IMinistriesEntityKey)ministry.getCode();
            if (ek == null) {
                throw new InvalidDataEntryException();
            }
            Long minCode = ek.getMinCode();
            if (minCode == null) {
                throw new InvalidDataEntryException();
            }
            hireProceduresEntity.setMinCode(minCode);

            //            if (hireProceduresDTO.getMinistriesDTO() != null) {
            //                EmpMinistriesEntity ministriesEntity =
            //                    EM().find(EmpMinistriesEntity.class, (IMinistriesEntityKey)hireProceduresDTO.getMinistriesDTO().getCode());
            //                // IMinistriesEntityKey mEk = (IMinistriesEntityKey)hireProceduresDTO.getMinistriesDTO().getCode();
            //                if (ministriesEntity != null) {
            //                    // if (mEk.getMinCode() != null) {
            //                    hireProceduresEntity.setMinistriesEntity(ministriesEntity);
            //                    // hireProceduresEntity.setMinCode(mEk.getMinCode());
            //                } else {
            //                    throw new ItemNotFoundException();
            //                }
            //            }
            //        if (hireProceduresDTO.getGenderTypesDTO() != null) {
            //            GenderTypesEntity genderTypesEntity =
            //                em.find(GenderTypesEntity.class,
            //                        (IGenderTypesEntityKey)hireProceduresDTO.getGenderTypesDTO().getCode());
            //            if (genderTypesEntity != null) {
            //                hireProceduresEntity.setGenderTypesEntity(genderTypesEntity);
            //            } else {
            //                throw new FinderException();
            //            }
            //        }
            //        hireProceduresEntity.setNationalityType(hireProceduresDTO.getNationalityType());
            //this.persistEntity(hireProceduresEntity);
            hireProceduresEntity.setFromDate(hireProceduresDTO.getFromDate());
            hireProceduresEntity.setUntilDate(hireProceduresDTO.getUntilDate());
            hireProceduresEntity.setStatus(hireProceduresDTO.getStatus());
            doAdd(hireProceduresEntity);
            // Set PK after creation
            hireProceduresDTO.setCode(EmpEntityKeyFactory.createHireProceduresEntityKey(hirprocedureCode));
            return hireProceduresDTO;

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
     * Update an Existing HireProceduresEntity * @param hireProceduresDTO1
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO hireProceduresDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IHireProceduresDTO hireProceduresDTO = (IHireProceduresDTO)hireProceduresDTO1;
            HireProceduresEntity hireProceduresEntity =
                EM().find(HireProceduresEntity.class, (IHireProceduresEntityKey)hireProceduresDTO.getCode());
            hireProceduresEntity.setHirprocedureCode(((IHireProceduresEntityKey)hireProceduresDTO.getCode()).getHirprocedureCode());
            hireProceduresEntity.setHirprocedureName(hireProceduresDTO.getName());

            IMinistriesDTO ministry = hireProceduresDTO.getMinistriesDTO();
            if (ministry == null) {
                throw new InvalidDataEntryException();
            }
            IMinistriesEntityKey ek = (IMinistriesEntityKey)ministry.getCode();
            if (ek == null) {
                throw new InvalidDataEntryException();
            }
            Long minCode = ek.getMinCode();
            if (minCode == null) {
                throw new InvalidDataEntryException();
            }
            hireProceduresEntity.setMinCode(minCode);

            //            if (hireProceduresDTO.getMinistriesDTO() != null) {
            //                // MinistriesEntity ministriesEntity = em.find(MinistriesEntity.class, (IMinistriesEntityKey)hireProceduresDTO.getMinistriesDTO().getCode());
            //                IMinistriesEntityKey mEk = (IMinistriesEntityKey)hireProceduresDTO.getMinistriesDTO().getCode();
            //                // if (ministriesEntity != null) {
            //                if (mEk.getMinCode() != null) {
            //                    EmpMinistriesEntity ministriesEntity = EM().find(EmpMinistriesEntity.class, mEk);
            //                    hireProceduresEntity.setMinCode(mEk.getMinCode());
            //                    hireProceduresEntity.setMinistriesEntity(ministriesEntity);
            //                } else {
            //                    throw new FinderException();
            //                }
            //            }


            //        if (hireProceduresDTO.getGenderTypesDTO() != null) {
            //            GenderTypesEntity genderTypesEntity =
            //                em.find(GenderTypesEntity.class,
            //                        (IGenderTypesEntityKey)hireProceduresDTO.getGenderTypesDTO().getCode());
            //            if (genderTypesEntity != null) {
            //                hireProceduresEntity.setGenderTypesEntity(genderTypesEntity);
            //            } else {
            //                throw new FinderException();
            //            }
            //        }
            //        hireProceduresEntity.setNationalityType(hireProceduresDTO.getNationalityType());
            hireProceduresEntity.setCreatedBy(hireProceduresDTO.getCreatedBy());
            hireProceduresEntity.setCreatedDate(hireProceduresDTO.getCreatedDate());
            hireProceduresEntity.setLastUpdatedBy(hireProceduresDTO.getLastUpdatedBy());
            hireProceduresEntity.setLastUpdatedDate(hireProceduresDTO.getLastUpdatedDate());
            hireProceduresEntity.setAuditStatus(hireProceduresDTO.getAuditStatus());
            hireProceduresEntity.setTabrecSerial(hireProceduresDTO.getTabrecSerial());
            hireProceduresEntity.setFromDate(hireProceduresDTO.getFromDate());
            hireProceduresEntity.setUntilDate(hireProceduresDTO.getUntilDate());
            hireProceduresEntity.setStatus(hireProceduresDTO.getStatus());
            doUpdate(hireProceduresEntity);
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


    public IMinistriesDTO getMinById(long code) throws DataBaseException, SharedApplicationException {
        try {
            String query = "select * from NL_ORG_MINISTRIES m where m.MIN_CODE = " + code + "";
            System.out.println(query);
            IMinistriesDTO ministryDTO = null;

            java.util.Vector result = (java.util.Vector)EM().createNativeQuery(query).getSingleResult();


            return ministryDTO;

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
     * Remove an Existing HireProceduresEntity * @param hireProceduresDTO1
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO hireProceduresDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHireProceduresDTO hireProceduresDTO = (IHireProceduresDTO)hireProceduresDTO1;
            HireProceduresEntity hireProceduresEntity =
                EM().find(HireProceduresEntity.class, (IHireProceduresEntityKey)hireProceduresDTO.getCode());
            if (hireProceduresEntity == null) {
                throw new ItemNotFoundException();
            }
            //  em.remove(hireProceduresEntity);
            doRemove(hireProceduresEntity);
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
     * Get HireProceduresEntity By Primary Key * @param id1
     * @return IHireProceduresDTO
     */
    public IHireProceduresDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IHireProceduresEntityKey id = (IHireProceduresEntityKey)id1;
            HireProceduresEntity hireProceduresEntity = EM().find(HireProceduresEntity.class, id);
            if (hireProceduresEntity == null) {
                throw new ItemNotFoundException();
            }
            IHireProceduresDTO hireProceduresDTO = EmpDTOFactory.createHireProceduresDTO(hireProceduresEntity);
            return hireProceduresDTO;
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
     * Get all hire procedure match search criteria * @param searchDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> search(IBasicDTO searchDTO) throws DataBaseException, SharedApplicationException {

        try {
            ISearchHireProceduresDTO searchProceduresDTO = (ISearchHireProceduresDTO)searchDTO;
            StringBuffer ejbql =
                new StringBuffer("select o from HireProceduresEntity o where o.hirprocedureCode=o.hirprocedureCode ");
            List<IBasicDTO> resultList = new ArrayList<IBasicDTO>();
            if (searchProceduresDTO.getHirprocedureCode() != null) {
                ejbql.append(" AND o.hirprocedureCode =" + searchProceduresDTO.getHirprocedureCode());
            }
            if (searchProceduresDTO.getMinCode() != null) {
                ejbql.append(" AND o.ministriesEntity.minCode = " + searchProceduresDTO.getMinCode());
            }
            if (searchProceduresDTO.getHirprocedureName() != null) {

                //By MLotfy new search
                //ejbql.append(" AND o.hirprocedureName like '" + searchProceduresDTO.getHirprocedureName() + "' ");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirprocedureName", searchProceduresDTO.getHirprocedureName()) +
                             " ) ");
            }
            /*
        if (searchProceduresDTO.getGenderType() != null) {
            ejbql.append(" AND o.genderTypesEntity.gentypeCode = " +
                         searchProceduresDTO.getGenderType());
        }
        if (searchProceduresDTO.getNationalityType() != null) {
            ejbql.append(" AND o.nationalityType= " +
                         searchProceduresDTO.getNationalityType());
        }*/
            Query query = EM().createQuery(ejbql.toString());
            List<HireProceduresEntity> list = query.getResultList();
            List proceduresList = new ArrayList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            } else {
                for (HireProceduresEntity entity : list) {
                    proceduresList.add(EmpDTOFactory.createHireProceduresDTO(entity));
                }
            }
            return proceduresList;

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
            Long id = (Long)EM().createNamedQuery("HireProceduresEntity.findNewId").getSingleResult();
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
     * get all Hire procedures code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = EM().createNamedQuery("HireProceduresEntity.getCodeName").getResultList();
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


    /**
     * Get all procedures valid for an existing employee * @param genderType
     * @param nationalityType
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getValidHireProceduresForEmployee(Long genderType,
                                                             Long nationalityType) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {


            return EM().createNamedQuery("HireProceduresEntity.getEmployeeHireProcedures").getResultList();

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
     * Get all hire procedure match search name * @param name
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {

        try {

            //By MLotfy new search
            ArrayList arrayList = new ArrayList();

            StringBuffer query = new StringBuffer("select o from HireProceduresEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirprocedureName", name));

            List<HireProceduresEntity> list = EM().createQuery(query.toString()).getResultList();

            for (HireProceduresEntity hire : list) {
                arrayList.add(EmpDTOFactory.createHireProceduresDTO(hire));
            }

            if (arrayList.size() == 0) {
                throw new FinderException();
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
     * Get all hire procedure match search code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            try {
                list.add(getById(EmpEntityKeyFactory.createHireProceduresEntityKey((Long)code)));
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            }
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

    public List<IBasicDTO> getAllRelatedByHireProcdure(Long hireProcdureCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            StringBuilder queryStr = new StringBuilder("SELECT H.HIRTYPE_CODE , H.HIRTYPE_NAME ");
            queryStr.append("FROM HR_EMP_HT_PROCEDURES P , HR_EMP_HIRE_TYPES H ");
            queryStr.append("WHERE H.HIRTYPE_CODE = P.HIRTYPE_CODE ");
            queryStr.append("AND HIRPROCEDURE_CODE = " + hireProcdureCode);
            Query query = EM().createNativeQuery(queryStr.toString());
            List<Vector> resultlist = query.getResultList();
            if (resultlist == null || resultlist.size() == 0) {
                throw new NoResultException();
            }
            for (Vector row : resultlist) {
                IHireProceduresDTO dto = EmpDTOFactory.createHireProceduresDTO();
                dto.setCode(EmpEntityKeyFactory.createHireProceduresEntityKey(((BigDecimal)row.get(0)).longValue()));
                dto.setName((String)row.get(1));
                list.add(dto);
            }
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

    public boolean duplicatedHireProcdureName(String name, Long hireProcdureCode) throws DataBaseException,
                                                                                         SharedApplicationException {


        StringBuilder queryStr = new StringBuilder("SELECT count(*) ");
        queryStr.append("FROM HR_EMP_HIRE_PROCEDURES H  ");
        queryStr.append("WHERE H.HIRPROCEDURE_NAME = '" + name + "'");
        if (hireProcdureCode != null) {
            queryStr.append("AND HIRPROCEDURE_CODE != " + hireProcdureCode);
        }

        Query query = EM().createNativeQuery(queryStr.toString());
        Vector resultVector = (Vector)query.getSingleResult();
        int res = Integer.parseInt(resultVector.get(0).toString());
        if (res > 0) {
            return true;
        } else {
            return false;
        }


    }
}
