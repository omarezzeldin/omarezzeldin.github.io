package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypeProcedureDTO;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IHireProceduresEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypeProcedureEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IRequiredDocumentsEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class HireTypeProcedureDAO extends EmpBaseDAO {
    public HireTypeProcedureDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new HireTypeProcedureDAO();
    }

    /**<code>select o from HireTypeProcedureDTOEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IHireTypeProcedureDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<HireTypeProcedureEntity> list =
                EM().createNamedQuery("HireTypeProcedureEntity.findAll").setHint("toplink.refresh",
                                                                                 "true").getResultList();
            for (HireTypeProcedureEntity hireTypeProcedureEntity : list) {
                arrayList.add(EmpDTOFactory.createHireTypeProcedureDTO(hireTypeProcedureEntity));
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

    //<<<<<<< HireTypeProcedureFacadeBean.java

    /**
     * Create a New hireTypeProcedureEntity * @param requiredDocumentsDTO
     * @return IRequiredDocumentsDTO
     */
    @Override
    public IHireTypeProcedureDTO add(IBasicDTO hireTypeProcedureDTO1) throws DataBaseException,
                                                                             SharedApplicationException {

        try {
            IHireTypeProcedureDTO hireTypeProcedureDTO = (IHireTypeProcedureDTO)hireTypeProcedureDTO1;
            HireTypeProcedureEntity hireTypeProcedureEntity = new HireTypeProcedureEntity();
            HireTypesEntity hireTypesEntity = null;

            if (hireTypeProcedureDTO.getHireTypeDTO() != null) {
                hireTypesEntity =
                        EM().find(HireTypesEntity.class, (IHireTypesEntityKey)hireTypeProcedureDTO.getHireTypeDTO().getCode());
            }
            if (hireTypesEntity != null) {
                hireTypeProcedureEntity.setHireTypesEntity(hireTypesEntity);
            }
            HireProceduresEntity hireProceduresEntity = null;
            if (hireTypeProcedureDTO.getHireProceduresDTO() != null) {
                hireProceduresEntity =
                        EM().find(HireProceduresEntity.class, (IHireProceduresEntityKey)hireTypeProcedureDTO.getHireProceduresDTO().getCode());
            }
            if (hireProceduresEntity != null) {
                hireTypeProcedureEntity.setHireProcedureEntity(hireProceduresEntity);
            }
            if (hireTypeProcedureDTO.getGenderType() != null) {
                hireTypeProcedureEntity.setGenderType(Long.parseLong(hireTypeProcedureDTO.getGenderType()));
            }
            hireTypeProcedureEntity.setNationalityType(hireTypeProcedureDTO.getNationalityType());
            hireTypeProcedureEntity.setCreatedBy(hireTypeProcedureDTO.getCreatedBy());
            hireTypeProcedureEntity.setCreatedDate(hireTypeProcedureDTO.getCreatedDate());
            hireTypeProcedureEntity.setAuditStatus(hireTypeProcedureDTO.getAuditStatus());
            hireTypeProcedureEntity.setTabrecSerial(hireTypeProcedureDTO.getTabrecSerial());
            hireTypeProcedureEntity.setOptionFlag(IEMPConstants.EMP_HIRE_TYPE_CONDITION_NOT_OPTIONAL);
            hireTypeProcedureEntity.setProcOrder(IEMPConstants.EMP_PROCEDURE_ORDER_FIRST);
            hireTypeProcedureEntity.setStatus(ISystemConstant.VALID_FLAG);
            hireTypeProcedureEntity.setFromDate(SharedUtils.getSqlDate());

            doAdd(hireTypeProcedureEntity);
            // Set PK after creation
            return hireTypeProcedureDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    //<<<<<<< HireTypeProcedureFacadeBean.java

    /**
     * Update an Existing HireTypeProcedureEntity * @param requiredDocumentsDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO hireTypeProcedureDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IHireTypeProcedureDTO hireTypeProcedureDTO = (IHireTypeProcedureDTO)hireTypeProcedureDTO1;
            HireTypeProcedureEntity hireTypeProcedureEntity =
                EM().find(HireTypeProcedureEntity.class, (IHireTypeProcedureEntityKey)hireTypeProcedureDTO.getCode());

            HireTypesEntity hireTypesEntity = null;
            if (hireTypeProcedureDTO.getHireTypeDTO() != null) {
                hireTypesEntity =
                        EM().find(HireTypesEntity.class, (IHireTypesEntityKey)hireTypeProcedureDTO.getHireTypeDTO().getCode());
            }
            if (hireTypesEntity != null) {
                hireTypeProcedureEntity.setHireTypesEntity(hireTypesEntity);
            }
            HireProceduresEntity hireProceduresEntity = null;
            if (hireTypeProcedureEntity.getHireProcedureEntity() != null) {
                hireProceduresEntity =
                        EM().find(HireProceduresEntity.class, (IHireProceduresEntityKey)hireTypeProcedureDTO.getHireProceduresDTO().getCode());
            }
            if (hireProceduresEntity != null) {
                hireTypeProcedureEntity.setHireProcedureEntity(hireProceduresEntity);
            }
            if (hireTypeProcedureDTO.getGenderType() != null) {
                hireTypeProcedureEntity.setGenderType(Long.parseLong(hireTypeProcedureDTO.getGenderType()));

            }
            hireTypeProcedureEntity.setNationalityType(hireTypeProcedureDTO.getNationalityType());
            hireTypeProcedureEntity.setCreatedBy(hireTypeProcedureDTO.getCreatedBy());
            hireTypeProcedureEntity.setCreatedDate(hireTypeProcedureDTO.getCreatedDate());
            hireTypeProcedureEntity.setAuditStatus(hireTypeProcedureDTO.getAuditStatus());
            hireTypeProcedureEntity.setTabrecSerial(hireTypeProcedureDTO.getTabrecSerial());
            hireTypeProcedureEntity.setOptionFlag(IEMPConstants.EMP_HIRE_TYPE_CONDITION_NOT_OPTIONAL);
            hireTypeProcedureEntity.setProcOrder(IEMPConstants.EMP_PROCEDURE_ORDER_FIRST);
            hireTypeProcedureEntity.setStatus(hireTypeProcedureDTO.getStatus());
            hireTypeProcedureEntity.setFromDate(SharedUtils.getSqlDate());
            doUpdate(hireTypeProcedureEntity);
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
     * Remove an Existing RequiredDocumentsEntity * @param requiredDocumentsDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO hireTypeProcedureDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IHireTypeProcedureDTO requiredDocumentsDTO = (IHireTypeProcedureDTO)hireTypeProcedureDTO1;
            HireTypeProcedureEntity hireTypeProcedureEntity =
                EM().find(HireTypeProcedureEntity.class, requiredDocumentsDTO.getCode());
            if (hireTypeProcedureEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(hireTypeProcedureEntity);
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
     * Get RequiredDocumentsEntity By Primary Key * @param id
     * @return IRequiredDocumentsDTO
     */
    @Override
    public IRequiredDocumentsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try {
            IRequiredDocumentsEntityKey id = (IRequiredDocumentsEntityKey)id1;
            RequiredDocumentsEntity requiredDocumentsEntity = EM().find(RequiredDocumentsEntity.class, id);
            if (requiredDocumentsEntity == null) {
                throw new ItemNotFoundException();
            }
            IRequiredDocumentsDTO requiredDocumentsDTO =
                EmpDTOFactory.createRequiredDocumentsDTO(requiredDocumentsEntity);
            return requiredDocumentsDTO;
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
            Query query = EM().createNamedQuery("HireTypeProcedureEntity.findNewId");
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
    public Long queryHireTypeProcedureEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("HireTypeProcedureEntity.findNewId").getSingleResult();
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
     * get procedure  not related to Hire Type * @param hireTypeCode
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IHireProceduresDTO> getAvailableProcedures(IEntityKey hireTypeCode) throws DataBaseException,
                                                                                           SharedApplicationException {
        try {
            if (hireTypeCode == null || ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode() == null)
                hireTypeCode = EmpEntityKeyFactory.createHireTypesEntityKey(ISystemConstant.VIRTUAL_VALUE);
            List<IHireProceduresDTO> list =
                EM().createNamedQuery("HireTypeProcedureEntity.getAvailableProcedures").setParameter("hireTypeCode",
                                                                                                     ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode()).setHint("toplink.refresh",
                                                                                                                                                                   "true").getResultList();
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

    public List<IHireProceduresDTO> getAvailableProcedures() throws DataBaseException, SharedApplicationException {
        try {

            List<IHireProceduresDTO> list =
                EM().createNamedQuery("HireTypeProcedureEntity.getAvailableProceduresWithoutCode").setHint("toplink.refresh",
                                                                                                           "true").getResultList();
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

    public List<IHireProceduresDTO> getActiveAvailableProcedures() throws DataBaseException,
                                                                          SharedApplicationException {
        try {

            List<IHireProceduresDTO> list =
                EM().createNamedQuery("HireTypeProcedureEntity.getActiveAvailableProceduresWithoutCode").setHint("toplink.refresh",
                                                                                                                 "true").getResultList();
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

    public List<IHireProceduresDTO> filterAvailableProcedures(IEntityKey hireTypeCode, String name,
                                                              Long code) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            if (hireTypeCode == null || ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode() == null) {
                hireTypeCode = EmpEntityKeyFactory.createHireTypesEntityKey(ISystemConstant.VIRTUAL_VALUE);
            }


            Long hTypeCode = ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode();


            List<IHireProceduresDTO> list =
                EM().createNamedQuery("HireTypeProcedureEntity.filterAvailableProcedures").setParameter("hireTypeCode",
                                                                                                        hTypeCode).setParameter("name",
                                                                                                                                name).setParameter("code",
                                                                                                                                                   code).getResultList();

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
     * check if this document required or not * @param hireTypeCode
     * @param documentTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean checkRequired(Long hireTypeCode, Long hireProcedureCode) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            Long result = new Long(0);
            result =
                    (Long)EM().createNamedQuery("HireTypeProcedureEntity.checkRequired").setParameter("hirtypeCode", hireTypeCode).setParameter("doctypeCode",
                                                                                                                                                hireProcedureCode).setParameter("basicStatus",
                                                                                                                                                                                IEMPConstant.EMP_DOCUMENT_REQUIRED).getSingleResult();
            if (result.longValue() > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
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
