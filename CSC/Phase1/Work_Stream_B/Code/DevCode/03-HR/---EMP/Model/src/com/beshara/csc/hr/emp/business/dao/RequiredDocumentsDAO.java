package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IRequiredDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IRequiredDocumentsEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.inf.EmpInfDocumentTypesEntity;
import com.beshara.csc.inf.business.client.InfClientFactory;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.inf.business.dto.InfDTOFactory;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.inf.business.entity.InfDocumentTypesEntity;
import com.beshara.csc.inf.business.entity.InfEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;


public class RequiredDocumentsDAO extends EmpBaseDAO {
    public RequiredDocumentsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new RequiredDocumentsDAO();
    }

    /**<code>select o from RequiredDocumentsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    @Override
    public List<IRequiredDocumentsDTO> getAll() throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<RequiredDocumentsEntity> list =
                EM().createNamedQuery("RequiredDocumentsEntity.findAll").getResultList();
            for (RequiredDocumentsEntity requiredDocuments : list) {
                arrayList.add(EmpDTOFactory.createRequiredDocumentsDTO(requiredDocuments));
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
     * Create a New RequiredDocumentsEntity * @param requiredDocumentsDTO
     * @return IRequiredDocumentsDTO
     */
    @Override
    public IRequiredDocumentsDTO add(IBasicDTO requiredDocumentsDTO1) throws DataBaseException,
                                                                             SharedApplicationException {
        try {
            RequiredDocumentsEntity requiredDocumentsEntity = new RequiredDocumentsEntity();
            IRequiredDocumentsDTO requiredDocumentsDTO = (IRequiredDocumentsDTO)requiredDocumentsDTO1;
            HireTypesEntity hireTypesEntity = null;
            if (requiredDocumentsDTO.getHireTypeDTO() != null) {
                hireTypesEntity =
                        EM().find(HireTypesEntity.class, (IHireTypesEntityKey)requiredDocumentsDTO.getHireTypeDTO().getCode());
            }
            if (hireTypesEntity != null) {
                requiredDocumentsEntity.setHireTypesEntity(hireTypesEntity);
            }
            IInfDocumentTypesDTO documentTypesDTO = requiredDocumentsDTO.getDocumentTypeDTO();
            if (documentTypesDTO == null) {
                throw new InvalidDataEntryException();
            }
            IInfDocumentTypesEntityKey infDockey = (IInfDocumentTypesEntityKey)documentTypesDTO.getCode();
            if (infDockey == null) {
                throw new InvalidDataEntryException();
            }
            Long doctypeCode = infDockey.getDoctypeCode();
            if (doctypeCode == null) {
                throw new InvalidDataEntryException();
            }
            requiredDocumentsEntity.setDoctypeCode(doctypeCode);
            if (requiredDocumentsDTO.getGenderType() != null) {
                requiredDocumentsEntity.setGenderType(Long.parseLong(requiredDocumentsDTO.getGenderType()));

            }

            requiredDocumentsEntity.setNationalityType(requiredDocumentsDTO.getNationalityType());
            requiredDocumentsEntity.setBasicStatus(requiredDocumentsDTO.getBasicStatus());
            requiredDocumentsEntity.setAttachmentRequired(requiredDocumentsDTO.getAttachmentRequired());
            requiredDocumentsEntity.setCreatedBy(requiredDocumentsDTO.getCreatedBy());
            requiredDocumentsEntity.setCreatedDate(requiredDocumentsDTO.getCreatedDate());
            requiredDocumentsEntity.setAuditStatus(requiredDocumentsDTO.getAuditStatus());
            requiredDocumentsEntity.setTabrecSerial(requiredDocumentsDTO.getTabrecSerial());
            requiredDocumentsEntity.setStatus(ISystemConstant.VALID_FLAG);
            requiredDocumentsEntity.setFromDate(SharedUtils.getSqlDate());
            doAdd(requiredDocumentsEntity);
            // Set PK after creation
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

    /**
     * Update an Existing RequiredDocumentsEntity * @param requiredDocumentsDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO requiredDocumentsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRequiredDocumentsDTO requiredDocumentsDTO = (IRequiredDocumentsDTO)requiredDocumentsDTO1;
            RequiredDocumentsEntity requiredDocumentsEntity =
                EM().find(RequiredDocumentsEntity.class, requiredDocumentsDTO.getCode());
            HireTypesEntity hireTypesEntity = null;
            if (requiredDocumentsDTO.getHireTypeDTO() != null) {
                hireTypesEntity = EM().find(HireTypesEntity.class, requiredDocumentsDTO.getHireTypeDTO().getCode());
            }
            if (hireTypesEntity != null) {
                requiredDocumentsEntity.setHireTypesEntity(hireTypesEntity);
            }

            if (requiredDocumentsDTO.getDocumentTypeDTO() != null) {
                IInfDocumentTypesDTO infDocumentTypesDTO = requiredDocumentsDTO.getDocumentTypeDTO();
                IInfDocumentTypesEntityKey ek = (IInfDocumentTypesEntityKey)infDocumentTypesDTO.getCode();
                if (ek == null) {
                    throw new InvalidDataEntryException();
                }
                Long doctypeCode = ek.getDoctypeCode();
                if (doctypeCode == null) {
                    throw new InvalidDataEntryException();
                }
                requiredDocumentsEntity.setDoctypeCode(doctypeCode);
            }
            if (requiredDocumentsDTO.getGenderType() != null) {
                requiredDocumentsEntity.setGenderType(Long.parseLong(requiredDocumentsDTO.getGenderType()));
            }
            requiredDocumentsEntity.setNationalityType(requiredDocumentsDTO.getNationalityType());
            requiredDocumentsEntity.setBasicStatus(requiredDocumentsDTO.getBasicStatus());
            requiredDocumentsEntity.setAttachmentRequired(requiredDocumentsDTO.getAttachmentRequired());
            requiredDocumentsEntity.setCreatedBy(requiredDocumentsDTO.getCreatedBy());
            requiredDocumentsEntity.setCreatedDate(requiredDocumentsDTO.getCreatedDate());
            requiredDocumentsEntity.setAuditStatus(requiredDocumentsDTO.getAuditStatus());
            requiredDocumentsEntity.setTabrecSerial(requiredDocumentsDTO.getTabrecSerial());
            requiredDocumentsEntity.setStatus(requiredDocumentsDTO.getStatus());
            requiredDocumentsEntity.setFromDate(SharedUtils.getSqlDate());

            //updated by Ismael for dataAudit requirements
            doUpdate(requiredDocumentsEntity);
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
    public Boolean remove(IBasicDTO requiredDocumentsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IRequiredDocumentsDTO requiredDocumentsDTO = (IRequiredDocumentsDTO)requiredDocumentsDTO1;
            RequiredDocumentsEntity requiredDocumentsEntity =
                EM().find(RequiredDocumentsEntity.class, (IRequiredDocumentsEntityKey)requiredDocumentsDTO.getCode());
            if (requiredDocumentsEntity == null) {
                throw new ItemNotFoundException();
            }
            //updated by Ismael for dataAudit requirements
            //            EM().remove(requiredDocumentsEntity);
            doRemove(requiredDocumentsEntity);
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

    /**
     * Search for RequiredDocumentsEntity * <br> * @return List
     */
    @Override
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
    public Long queryRequiredDocumentsEntityFindNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("RequiredDocumentsEntity.findNewId").getSingleResult();
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
     * get document types not related to Hire Type * @param hireTypeCode
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IInfDocumentTypesDTO> getAvailableDocuments(IEntityKey hireTypeCode) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            if (hireTypeCode == null || ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode() == null)
                hireTypeCode = EmpEntityKeyFactory.createHireTypesEntityKey(ISystemConstant.VIRTUAL_VALUE);

            List<EmpInfDocumentTypesEntity> list =
                EM().createNamedQuery("RequiredDocumentsEntity.getAvailableDocuments").setParameter("hireTypeCode",
                                                                                                    ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode()).setHint("toplink.refresh",
                                                                                                                                                                  "true").getResultList();
            List<IInfDocumentTypesDTO> listDTO = new ArrayList<IInfDocumentTypesDTO>();

            for (int i = 0; i < list.size(); i++) {

                IInfDocumentTypesEntityKey infDocEntityKey =
                    InfEntityKeyFactory.createInfDocumentTypesEntityKey(list.get(i).getDoctypeCode());

                IInfDocumentTypesDTO dto =
                    (IInfDocumentTypesDTO)InfClientFactory.getInfDocumentTypesClient().getById(infDocEntityKey);

                listDTO.add(dto);
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


    public List<IInfDocumentTypesDTO> getAvailableDocuments() throws DataBaseException, SharedApplicationException {
        try {


            List<EmpInfDocumentTypesEntity> list =
                EM().createNamedQuery("RequiredDocumentsEntity.getAvailableDocumentsWithOutCode").setHint("toplink.refresh",
                                                                                                          "true").getResultList();
            List<IInfDocumentTypesDTO> listDTO = new ArrayList<IInfDocumentTypesDTO>();

            for (int i = 0; i < list.size(); i++) {

                IInfDocumentTypesEntityKey infDocEntityKey =
                    InfEntityKeyFactory.createInfDocumentTypesEntityKey(list.get(i).getDoctypeCode());

                IInfDocumentTypesDTO dto =
                    (IInfDocumentTypesDTO)InfClientFactory.getInfDocumentTypesClient().getById(infDocEntityKey);

                listDTO.add(dto);
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
     * check if this document required or not * @param hireTypeCode
     * @param documentTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean checkRequired(Long hireTypeCode, Long documentTypeCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            Long result = new Long(0);
            result =
                    (Long)EM().createNamedQuery("RequiredDocumentsEntity.checkRequired").setParameter("hirtypeCode", hireTypeCode).setParameter("doctypeCode",
                                                                                                                                                documentTypeCode).setParameter("basicStatus",
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

    /**
     * filter document types not related to Hire Type * @param hireTypeCode
     * @param name
     * @param code
     * @return List
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IInfDocumentTypesDTO> filterAvailableDocuments(IEntityKey hireTypeCode, String name,
                                                               Long code) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            if (hireTypeCode == null || ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode() == null) {
                hireTypeCode = EmpEntityKeyFactory.createHireTypesEntityKey(ISystemConstant.VIRTUAL_VALUE);
            }

            /*List<IDocumentTypesDTO> list =
            em.createNamedQuery("RequiredDocumentsEntity.filterAvailableDocuments").setParameter("hireTypeCode",
                                                                                                 ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode()).setParameter("name",
                                                                                                                                                                                             "true").getResultList();
        return list;*/

            //By MLotfy new search


            Long hTypeCode = ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode();

            List<InfDocumentTypesEntity> list =
                EM().createNamedQuery("RequiredDocumentsEntity.filterAvailableDocuments").setParameter("hireTypeCode",
                                                                                                       hTypeCode).setParameter("name",
                                                                                                                               name).setParameter("code",
                                                                                                                                                  code).getResultList();


            /*

        StringBuffer query = new StringBuffer("select o from RequiredDocumentsEntity o where ");

        query.append(" o.documentTypesEntity.doctypeCode NOT IN (SELECT rd.documentTypesEntity.doctypeCode FROM RequiredDocumentsEntity rd WHERE rd.hireTypesEntity.hirtypeCode = " +
                     ((IHireTypesEntityKey)hireTypeCode).getHirtypeCode() + " ) ");

        if (name != null) {
            query.append(" AND (");
            query.append(QueryConditionBuilder.getEjbqlSimilarCharsCondition("o.documentTypesEntity.doctypeName",
                                                                             name));
            query.append(" )");
        }

        if (code != null) {
            query.append(" AND o.documentTypesEntity.doctypeCode = " + code);
        }

        List<RequiredDocumentsEntity> list = em.createQuery(query.toString()).getResultList();

        */

            List<IInfDocumentTypesDTO> listDTO = new ArrayList<IInfDocumentTypesDTO>();
            for (InfDocumentTypesEntity entity : list) {
                listDTO.add(InfDTOFactory.createInfDocumentTypesDTO(entity));
                //  break;
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

    public List<IRequiredDocumentsDTO> getValidDocumentsForEmployee(Long genderType, Long nationalityType,
                                                                    IHireTypesEntityKey hireTypeKey) throws DataBaseException,
                                                                                                            SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("RequiredDocumentsEntity.getValidDocumentsForEmployee");

            query.setParameter("nationalityType", nationalityType);
            query.setParameter("nationalityNotSpecified", ISystemConstant.NATIONALITY_NON_SPECIFIED);

            query.setParameter("genderType", genderType);
            query.setParameter("genderUndefined", ISystemConstant.GENDER_UNDEFINED);

            query.setParameter("hirtypeCode", hireTypeKey.getHirtypeCode());

            List<RequiredDocumentsEntity> list = query.getResultList();

            List<IRequiredDocumentsDTO> listDTO = new ArrayList<IRequiredDocumentsDTO>();
            if (!(list == null || list.size() == 0)) {
                for (RequiredDocumentsEntity entity : list) {
                    listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
                }
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

    public List<IBasicDTO> getDocumentsByHireType(Long hireTypeCode) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            Query query = EM().createNamedQuery("RequiredDocumentsEntity.getDocumentsByHireType");
            query.setParameter("hirtypeCode", hireTypeCode);

            List<RequiredDocumentsEntity> list = query.getResultList();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            if (!(list == null || list.size() == 0)) {
                for (RequiredDocumentsEntity entity : list) {
                    listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
                }
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


}
