package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.dto.ConditionsDTO;
import com.beshara.csc.gn.grs.business.dto.IConditionsDTO;
import com.beshara.csc.gn.grs.business.entity.GrsEntityKeyFactory;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireTypeProcedureDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.dto.ISearchHireTypeDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesMinistriesEntity;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.nl.org.business.client.OrgClientFactory;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.MinistriesDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class HireTypesDAO extends EmpBaseDAO {
    public HireTypesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HireTypesDAO();
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("HireTypesEntity.findNewId");
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
    public Long queryHireTypesEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("HireTypesEntity.findNewId").getSingleResult();
            if (id != null) {
                return ++id;
            } else {
                return new Long(1);
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
    private Long getConditionNewSerial() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("HireTypeConditionsEntity.findNewId").getSingleResult();
            if (id != null) {
                return ++id;
            } else {
                return new Long(1);
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

    /**<code>select o from HireTypesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IHireTypesDTO> getAll() throws DataBaseException, SharedApplicationException {

        try {
            Query query = EM().createNamedQuery("HireTypesEntity.findAll");
            query.setParameter("nonActive", IEMPConstants._EMP_NOT_ACTIVE_STATUS);
            List<HireTypesEntity> list = query.getResultList();

            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            ArrayList arrayList = new ArrayList();
            for (HireTypesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireTypesDTO(hireTypes));
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

    public List<Long> getHireTyperecserialByHireType(Long hiretype) throws DataBaseException,
                                                                           SharedApplicationException {

        try {
            Query query =
                EM().createQuery("select o.tabrecSerial from HireTypesEntity o where o.hirtypeCode=:hiretype ");
            query.setParameter("hiretype", hiretype);
            List<Long> list = query.getResultList();
            List<Long> arrayList = new ArrayList();
            if (list == null || list.size() <= 0) {
                return arrayList;
            }
            for (Long tabrecSerial : list) {
                arrayList.add(tabrecSerial);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    /**<code>select * from hr_emp_hire_types IoI<I/IcIoIdIeI>I.I
     * @return sorted List of hire types where hire type not from CRS
     * @author I.Omar
     */
    public List<IHireTypesDTO> getAllSorted() throws DataBaseException, SharedApplicationException {

        ArrayList arrayList = new ArrayList();
        try {
            List<HireTypesEntity> list =
                EM().createNativeQuery("SELECT * FROM hr_emp_hire_types where (PARENT_HIRTYPE_CODE <> 2 or PARENT_HIRTYPE_CODE is null) and (HIRTYPE_CODE <> 2) Start with parent_hirtype_code is null connect by prior hirtype_code  = PARENT_HIRTYPE_CODE",
                                       HireTypesEntity.class).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (HireTypesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireTypesDTO(hireTypes));
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
     * @return List
     */
    public List<IBasicDTO> getFirstLevelHireTypes() throws DataBaseException, SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<HireTypesEntity> list =
                EM().createNamedQuery("HireTypesEntity.getFirstLevelHireTypes").setParameter("treeLevel",
                                                                                             IEMPConstants.EMP_HIRE_TYPE_TREE_LEVEL_ONE).setParameter("status",
                                                                                                                                                      IEMPConstants.EMP_HIRE_TYPE_ACTIVE_STATUS).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (HireTypesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireTypesDTO(hireTypes));
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

    public boolean duplicatedHireTypesName(IBasicDTO hireTypesDTO1, boolean doEdit) throws DataBaseException,
                                                                                           SharedApplicationException {

        HireTypesEntity hireTypesEntity = new HireTypesEntity();
        IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;

        StringBuilder queryStr = new StringBuilder("SELECT count(*) ");
        queryStr.append("FROM HR_EMP_HIRE_TYPES H  ");
        queryStr.append("WHERE H.HIRTYPE_NAME = '" + hireTypesDTO.getName() + "' ");
        queryStr.append("AND H.PARENT_HIRTYPE_CODE = '" + hireTypesDTO.getParentHireType().getCode().getKey() + "' ");
        queryStr.append("AND H.STATUS = " + IEMPConstants.EMP_ACTIVE_FLAG);

        if (hireTypesDTO.getCode() != null && doEdit) {
            queryStr.append(" AND H.HIRTYPE_CODE != " + hireTypesDTO.getCode().getKey());
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

    /**
     * Create a New HireTypesEntity * @param hireTypesDTO
     * @return IHireTypesDTO
     */
    @Override
    public IHireTypesDTO add(IBasicDTO hireTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            HireTypesEntity hireTypesEntity = new HireTypesEntity();
            IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;

            hireTypesEntity.setHirtypeCode(findMaxId());
            hireTypesEntity.setHirtypeName(hireTypesDTO.getName());
            hireTypesEntity.setCreatedBy(hireTypesDTO.getCreatedBy());
            hireTypesEntity.setCreatedDate(hireTypesDTO.getCreatedDate());
            hireTypesEntity.setAuditStatus(hireTypesDTO.getAuditStatus());
            // hireTypesEntity.setTabrecSerial(hireTypesDTO.getTabrecSerial()); // tabrec serial never setted from view

            hireTypesEntity.setLeafFlag(hireTypesDTO.getLeafFlag());
            hireTypesEntity.setFirstParent(((IHireTypesEntityKey)hireTypesDTO.getFirstParent()).getHirtypeCode());
            hireTypesEntity.setTreeLevel(hireTypesDTO.getTreeLevel());
            hireTypesEntity.setDecisionFlag(hireTypesDTO.getDecisionMust());
            if (hireTypesDTO.getMinistriesDTO() != null) {
                IMinistriesEntityKey mEk = (IMinistriesEntityKey)hireTypesDTO.getMinistriesDTO().getCode();
                if (mEk.getMinCode() == null)
                    throw new ItemNotFoundException();
                HireTypesMinistriesEntity hireTypesMinistriesEntity = new HireTypesMinistriesEntity();
                hireTypesMinistriesEntity.setMinCode(mEk.getMinCode());
                hireTypesMinistriesEntity.setHireTypesEntity(hireTypesEntity);
                List<HireTypesMinistriesEntity> hireTypesMinistriesList = new ArrayList<HireTypesMinistriesEntity>();
                hireTypesMinistriesList.add(hireTypesMinistriesEntity);
                hireTypesEntity.setHireTypesMinistriesList(hireTypesMinistriesList);

            }
            if (hireTypesDTO.getParentHireType() != null) {
                HireTypesEntity parentEntity =
                    EM().find(HireTypesEntity.class, hireTypesDTO.getParentHireType().getCode());
                hireTypesEntity.setHireTypeEntity(parentEntity);
            }

            hireTypesEntity.setFromDate(SharedUtils.getSqlDate());
            hireTypesEntity.setStatus(ISystemConstant.VALID_FLAG);

            hireTypesDTO = EmpEntityConverter.getHireTypesDTO(doAdd(hireTypesEntity));

            return hireTypesDTO;
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
     * Update an Existing HireTypesEntity * @param hireTypesDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO hireTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;
            HireTypesEntity hireTypesEntity =
                EM().find(HireTypesEntity.class, (IHireTypesEntityKey)hireTypesDTO.getCode());
            hireTypesEntity.setHirtypeCode(((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode());
            hireTypesEntity.setHirtypeName(hireTypesDTO.getName());
            hireTypesEntity.setLastUpdatedBy(hireTypesDTO.getLastUpdatedBy());
            hireTypesEntity.setLastUpdatedDate(hireTypesDTO.getLastUpdatedDate());
            hireTypesEntity.setAuditStatus(hireTypesDTO.getAuditStatus());
            //hireTypesEntity.setTabrecSerial(hireTypesDTO.getTabrecSerial()); // tabrec serial never setted from view
            hireTypesEntity.setLeafFlag(hireTypesDTO.getLeafFlag());
            hireTypesEntity.setFirstParent(((IHireTypesEntityKey)hireTypesDTO.getFirstParent()).getHirtypeCode());
            hireTypesEntity.setTreeLevel(hireTypesDTO.getTreeLevel());
            hireTypesEntity.setDecisionFlag(hireTypesDTO.getDecisionMust());
            Long oldMinistryCode = null;
            if (hireTypesEntity.getHireTypesMinistriesList() != null &&
                !hireTypesEntity.getHireTypesMinistriesList().isEmpty()) {
                //oldMinistryCode = hireTypesEntity.getHireTypesMinistriesList().get(0).getMinistriesEntity().getMinCode();
                oldMinistryCode = hireTypesEntity.getHireTypesMinistriesList().get(0).getMinCode();
            }

            if (hireTypesDTO.getMinistriesDTO() != null &&
                !((IMinistriesEntityKey)hireTypesDTO.getMinistriesDTO().getCode()).getMinCode().equals(oldMinistryCode)) {

                //MinistriesEntity ministriesEntity = EM().find(MinistriesEntity.class, hireTypesDTO.getMinistriesDTO().getCode());
                IMinistriesEntityKey mEk = (IMinistriesEntityKey)hireTypesDTO.getMinistriesDTO().getCode();
                //            if (ministriesEntity == null)
                //                throw new NoResultException();
                if (oldMinistryCode != null) {
                    HireTypesMinistriesEntity hireTypesMinistriesEntity = new HireTypesMinistriesEntity();
                    // hireTypesMinistriesEntity.setMinistriesEntity(ministriesEntity);
                    hireTypesMinistriesEntity.setMinCode(mEk.getMinCode());
                    hireTypesMinistriesEntity.setHireTypesEntity(hireTypesEntity);
                    List<HireTypesMinistriesEntity> hireTypesMinistriesList =
                        hireTypesEntity.getHireTypesMinistriesList();
                    EM().remove(hireTypesMinistriesList.remove(0));
                    hireTypesMinistriesList.add(hireTypesMinistriesEntity);

                } else {
                    HireTypesMinistriesEntity hireTypesMinistriesEntity = new HireTypesMinistriesEntity();
                    //                    hireTypesMinistriesEntity.setMinistriesEntity(ministriesEntity);
                    hireTypesMinistriesEntity.setMinCode(mEk.getMinCode());
                    hireTypesMinistriesEntity.setHireTypesEntity(hireTypesEntity);
                    List<HireTypesMinistriesEntity> hireTypesMinistriesList =
                        new ArrayList<HireTypesMinistriesEntity>();
                    hireTypesMinistriesList.add(hireTypesMinistriesEntity);
                    hireTypesEntity.setHireTypesMinistriesList(hireTypesMinistriesList);
                }
            }

            hireTypesEntity.setFromDate(SharedUtils.getSqlDate());
            hireTypesEntity.setStatus(ISystemConstant.VALID_FLAG);
            doUpdate(hireTypesEntity);
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
     * Remove an Existing HireTypesEntity * @param hireTypesDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO hireTypesDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHireTypesDTO hireTypesDTO = (IHireTypesDTO)hireTypesDTO1;
            HireTypesEntity hireTypesEntity = EM().find(HireTypesEntity.class, hireTypesDTO.getCode());
            if (hireTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(hireTypesEntity);
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

    @Override
    public IHireTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IHireTypesEntityKey id = (IHireTypesEntityKey)id1;
            HireTypesEntity hireTypesEntity = EM().find(HireTypesEntity.class, id);
            if (hireTypesEntity == null) {
                throw new ItemNotFoundException();
            }
            IHireTypesDTO hireTypesDTO = EmpDTOFactory.createHireTypesDTO(hireTypesEntity);
            List<IRequiredDocumentsDTO> requiredDocumentsDTOList =
                getRelatedRequiredDocuments(((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode());
            if (requiredDocumentsDTOList != null) {
                hireTypesDTO.setRequiredDocumentsDTOList(requiredDocumentsDTOList);
            }
            List<IHireTypeProcedureDTO> hireTypeProcedureDTOList =
                getRelatedHireTypeProcedures(((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode());
            if (hireTypeProcedureDTOList != null) {
                hireTypesDTO.setHireTypeProcedureDTOList(hireTypeProcedureDTOList);
            }
            Long hireCode = ((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode();
            try {
                if (hireCode > 0) {
                    ConditionsDTO conditionsDTO = (ConditionsDTO)getRelatedCondition(hireCode);
                    if (conditionsDTO != null) {
                        hireTypesDTO.setConditionsDTO(conditionsDTO);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            MinistriesDTO ministriesDTO =
                (MinistriesDTO)getRelatedMinistry(((IHireTypesEntityKey)hireTypesDTO.getCode()).getHirtypeCode());
            if (ministriesDTO != null) {
                hireTypesDTO.setMinistriesDTO(ministriesDTO);
            }

            return hireTypesDTO;
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
     * get Related HireTypeProcedures * @param hireTypeCode
     * @return list
     * @throws RemoteException
     */
    private List<IHireTypeProcedureDTO> getRelatedHireTypeProcedures(Long hireTypeCode) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            List<HireTypeProcedureEntity> list =
                EM().createNamedQuery("HireTypeProcedureEntity.listRelatedHireProcedures").setParameter("hireTypeCode",
                                                                                                        hireTypeCode).setHint("toplink.refresh",
                                                                                                                              "true").getResultList();
            List<IHireTypeProcedureDTO> dtoList = new ArrayList<IHireTypeProcedureDTO>();
            for (HireTypeProcedureEntity entity : list) {
                dtoList.add(EmpDTOFactory.createHireTypeProcedureDTO(entity));
            }
            return dtoList;
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
     * get Related Condition * @param hireTypeCode
     * @return list
     * @throws RemoteException
     */
    private IConditionsDTO getRelatedCondition(Long hireTypeCode) throws DataBaseException,
                                                                         SharedApplicationException {

        try {


            StringBuilder stringQuery = new StringBuilder(" select CO.* ");
            stringQuery.append(" From GN_GRS_COND_RELATIONS CO, HR_EMP_HIRE_TYPES HT");
            stringQuery.append(" where CO.R_TABREC_SERIAL = HT.TABREC_SERIAL ");
            stringQuery.append(" AND CO.STATUS =  ");
            stringQuery.append(IEMPConstants.EMP_HIRE_TYPE_ACTIVE_STATUS);
            stringQuery.append(" AND HT.HIRTYPE_CODE = ");
            stringQuery.append(hireTypeCode);
            List<HireTypeConditionsEntity> hireTypeConditionEntityList =
                EM().createNativeQuery(stringQuery.toString(), HireTypeConditionsEntity.class).getResultList();
            ConditionsDTO conditionDTO = null;
            if (hireTypeConditionEntityList != null && hireTypeConditionEntityList.size() > 0) {
                // conditionDTO = (ConditionsDTO)GrsDTOFactory.createConditionsDTO(((HireTypeConditionsEntity)hireTypeConditionEntityList.get(0)).getConditionsEntity());
                IConditionsEntityKey condEK =
                    GrsEntityKeyFactory.createConditionsEntityKey(hireTypeConditionEntityList.get(0).getConditionCode());
                try {
                    conditionDTO = (ConditionsDTO)GrsClientFactory.getConditionsClient().getById(condEK);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            return conditionDTO;
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
     * get Related Condition * @param hireTypeCode
     * @return list
     * @throws RemoteException
     */
    private IMinistriesDTO getRelatedMinistry(Long hireTypeCode) throws DataBaseException, SharedApplicationException {

        try {
            List<HireTypesMinistriesEntity> hireTypesMinistriesEntityList =
                EM().createNamedQuery("HireTypesMinistriesEntity.listRelatedMinistry").setParameter("hireTypeCode",
                                                                                                    hireTypeCode).setHint("toplink.refresh",
                                                                                                                          "true").getResultList();


            MinistriesDTO ministriesDTO = null;
            if (hireTypesMinistriesEntityList != null && hireTypesMinistriesEntityList.size() > 0) {

                IMinistriesEntityKey mEK =
                    OrgEntityKeyFactory.createMinistriesEntityKey(hireTypesMinistriesEntityList.get(0).getMinCode());
                try {
                    ministriesDTO = (MinistriesDTO)OrgClientFactory.getMinistriesClient().getById(mEK);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //ministriesDTO =(MinistriesDTO)OrgDTOFactory.createMinistriesDTO(((HireTypesMinistriesEntity)hireTypesMinistriesEntityList.get(0)).getMinistriesEntity());

            }

            return ministriesDTO;
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
     * Gat all match search criteria * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            ISearchHireTypeDTO searchHireTypeDTO = (ISearchHireTypeDTO)basicDTO;
            StringBuilder ejbql =
                new StringBuilder("select o from HireTypesEntity o where o.hirtypeCode=o.hirtypeCode ");
            if (searchHireTypeDTO.getHireTypeCode() != null) {
                ejbql.append(" AND o.hirtypeCode =" + searchHireTypeDTO.getHireTypeCode());
            }
            if (searchHireTypeDTO.getHireTypeName() != null && searchHireTypeDTO.getHireTypeName().length() > 0) {

                //By MLotfy new search(regex)
                //ejbql.append(" AND o.hirtypeName like '" + searchHireTypeDTO.getHireTypeName() + "' ");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirtypeName", searchHireTypeDTO.getHireTypeName()) +
                             " ) ");
            }
            if ((searchHireTypeDTO.getDocTypeName() != null && searchHireTypeDTO.getDocTypeName().length() > 0) ||
                searchHireTypeDTO.getDocTypeCode() != null) {
                ejbql.append(" AND o.hirtypeCode IN ( SELECT rd.hirtypeCode FROM RequiredDocumentsEntity rd where rd.hirtypeCode=rd.hirtypeCode ");
                if (searchHireTypeDTO.getDocTypeName() != null && searchHireTypeDTO.getDocTypeName().length() > 0) {

                    //By MLotfy new search(regex)
                    //ejbql.append(" AND rd.documentTypesEntity.doctypeName like '" + searchHireTypeDTO.getDocTypeName() + "' ");
                    ejbql.append(" AND (" +
                                 QueryConditionBuilder.getEjbqlSimilarCharsCondition("rd.documentTypesEntity.doctypeName",
                                                                                     searchHireTypeDTO.getDocTypeName()) +
                                 " ) ");
                }
                if (searchHireTypeDTO.getDocTypeCode() != null) {
                    ejbql.append(" AND rd.documentTypesEntity.doctypeCode=" + searchHireTypeDTO.getDocTypeCode() + "");
                }
                ejbql.append(" ) ");
            }
            Query query = EM().createQuery(ejbql.toString());
            List<HireTypesEntity> list = query.getResultList();
            List<IBasicDTO> resultList = new ArrayList<IBasicDTO>();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            } else {
                for (HireTypesEntity entity : list) {
                    resultList.add(EmpDTOFactory.createHireTypesDTO(entity));
                }
            }
            return resultList;
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
     * get Related Required Documents * @param hireTypeCode
     * @return list
     * @throws RemoteException
     */
    private List<IRequiredDocumentsDTO> getRelatedRequiredDocuments(Long hireTypeCode) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            List<RequiredDocumentsEntity> list =
                EM().createNamedQuery("RequiredDocumentsEntity.listRelatedRequiredDocuments").setParameter("hireTypeCode",
                                                                                                           hireTypeCode).setHint("toplink.refresh",
                                                                                                                                 "true").getResultList();
            List<IRequiredDocumentsDTO> dtoList = new ArrayList<IRequiredDocumentsDTO>();
            for (RequiredDocumentsEntity entity : list) {
                dtoList.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
            }
            return dtoList;
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
     * Get all document for an existing hire type and valid for an employee * @param hireTypeCode
     * @param genderType
     * @param nationalityType
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getValidDocumentsForEmployee(Long hireTypeCode, Long genderType,
                                                        Long nationalityType) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return EM().createNamedQuery("RequiredDocumentsEntity.hireTypeDocuments").setParameter("hireTypeCode",
                                                                                                   hireTypeCode).setParameter("genderType",
                                                                                                                              genderType).setParameter("genderTypeBoth",
                                                                                                                                                       ISystemConstant.GENDER_UNDEFINED).setParameter("nationalityType",
                                                                                                                                                                                                      nationalityType).setParameter("nationalityTypeBoth",
                                                                                                                                                                                                                                    ISystemConstant.NATIONALITY_NON_SPECIFIED).getResultList();
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
     * get code and name for all Hire Types * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list =
                (List<IBasicDTO>)EM().createNamedQuery("HireTypesEntity.getCodeName").getResultList();
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
     * Get all hire types match search name * @param name
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByName(String name) throws DataBaseException, SharedApplicationException {
        //By MLotfy new search
        try {
            ArrayList arrayList = new ArrayList();

            StringBuilder query = new StringBuilder("select o from HireTypesEntity o where ");

            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.hirtypeName", name));

            List<HireTypesEntity> list = EM().createQuery(query.toString()).getResultList();

            for (HireTypesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireTypesDTO(hireTypes));
            }

            if (arrayList.size() == 0) {
                throw new NoResultException();
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
     * Get all hire types match search code * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    @Override
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            List<IBasicDTO> list = new ArrayList<IBasicDTO>();
            list.add(getById(EmpEntityKeyFactory.createHireTypesEntityKey((Long)code)));
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

    public List<IHireTypesDTO> getRelatedByCenterTwzef(Long parentHireType, Long minCode) throws DataBaseException,
                                                                                                 SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            ejbql =
                    new StringBuilder("select H.* from HR_EMP_HIRE_TYPES H " + "WHERE  ( H.HIRTYPE_CODE = ? OR (PARENT_HIRTYPE_CODE = ? AND EXISTS (SELECT 1 FROM HR_EMP_MIN_HIRE_TYPES MH " +
                                      " WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE " + " AND MH.MIN_CODE = ?))) ");
            List<HireTypesEntity> list = null;
            Query q = EM().createNativeQuery(ejbql.toString(), HireTypesEntity.class);
            q.setParameter(1, parentHireType);
            q.setParameter(2, parentHireType);
            q.setParameter(3, minCode);
            System.out.println("HireTypesFacadeBean.getRelatedByCenterTwzef ::::" + ejbql.toString());
            list = q.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            List<IHireTypesDTO> listDTO = new ArrayList<IHireTypesDTO>();
            for (HireTypesEntity entity : list) {
                listDTO.add(EmpDTOFactory.createHireTypesDTO(entity));
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

    public List<IHireTypesDTO> getEmpHireTypesAdd(Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            StringBuilder ejbql = null;
            ejbql = new StringBuilder("SELECT * FROM HR_EMP_HIRE_TYPES H ");
            ejbql.append("WHERE H.HIRTYPE_CODE IN (3,4) OR ");
            ejbql.append("( ");
            ejbql.append(" PARENT_HIRTYPE_CODE IN (3,4) ");
            ejbql.append(" AND EXISTS (SELECT 1 FROM HR_EMP_MIN_HIRE_TYPES MH WHERE H.HIRTYPE_CODE = MH.HIRTYPE_CODE AND MH.MIN_CODE = ");
            ejbql.append(CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest());
            ejbql.append(") )");

            List<HireTypesEntity> list = null;

            Query q = EM().createNativeQuery(ejbql.toString(), HireTypesEntity.class);

            System.out.println("HireTypesDAO.getEmpHireTypesAdd : " + ejbql.toString());

            list = q.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }

            List<IHireTypesDTO> hireTypeDTOList = new ArrayList<IHireTypesDTO>();
            for (HireTypesEntity entity : list) {
                hireTypeDTOList.add(EmpDTOFactory.createHireTypesDTO(entity));
            }
            return hireTypeDTOList;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IBasicDTO> getByTreeLevelHireTypes(Long treeLevel) throws DataBaseException,
                                                                          SharedApplicationException {
        ArrayList arrayList = new ArrayList();
        try {
            List<HireTypesEntity> list =
                EM().createNamedQuery("HireTypesEntity.getByTreeLevelHireTypes").setParameter("treeLevel",
                                                                                              treeLevel).getResultList();
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
            for (HireTypesEntity hireTypes : list) {
                arrayList.add(EmpDTOFactory.createHireTypesDTO(hireTypes));
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
}
