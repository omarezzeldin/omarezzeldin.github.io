package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateDocumentsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidateDocumentsEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IHireTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.hr.emp.business.shared.IEMPConstants;
import com.beshara.csc.inf.business.dto.IKwtCitizensResidentsDTO;
import com.beshara.csc.inf.business.entity.IGenderTypesEntityKey;
import com.beshara.csc.inf.business.entity.IInfDocumentTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.math.BigDecimal;

import java.rmi.RemoteException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


public class EmpCandidateDocumentsDAO extends EmpBaseDAO {
    public EmpCandidateDocumentsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpCandidateDocumentsDAO();
    }

    /**
     * Get the MaxId of AbilitiesEntity * <br> * @return Object
     */
    public Long findNewSerialforEmpCandidateDocuments(Long candidateCode,
                                                      Long candidateCodeSeq) throws DataBaseException,
                                                                                    SharedApplicationException {

        try {
            Long id =
                (Long)EM().createNamedQuery("EmpCandidateDocumentsEntity.findNewSerial").setParameter("candidateCode",
                                                                                                      candidateCode).setParameter("candidateCodeSeq",
                                                                                                                                  candidateCodeSeq).getSingleResult();
            if (id != null) {
                return (id + 1L);
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

    private Long getCnddocSerialCode(IEmpCandidatesEntityKey empCandidateCode,
                                     IEmpCandidateDocumentsDTO employeeDocumentsDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            Long cnddocSerial = null;
            if (employeeDocumentsDTO.getCode() != null) {
                if (((IEmpCandidateDocumentsEntityKey)employeeDocumentsDTO.getCode()).getCnddocSerial() != null) {
                    cnddocSerial = ((IEmpCandidateDocumentsEntityKey)employeeDocumentsDTO.getCode()).getCnddocSerial();
                } else {
                    cnddocSerial =
                            this.findNewSerialforEmpCandidateDocuments(empCandidateCode.getCandidateCode(), empCandidateCode.getCandidateCodeSeq());
                }
            } else {
                cnddocSerial =
                        this.findNewSerialforEmpCandidateDocuments(empCandidateCode.getCandidateCode(), empCandidateCode.getCandidateCodeSeq());
            }

            return cnddocSerial;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public IEmpCandidateDocumentsDTO add(IBasicDTO employeeDocumentsDTO1) throws DataBaseException,
                                                                                 SharedApplicationException {

        try {
            EmpCandidateDocumentsEntity employeeDocumentsEntity = new EmpCandidateDocumentsEntity();
            IEmpCandidateDocumentsDTO employeeDocumentsDTO = (IEmpCandidateDocumentsDTO)employeeDocumentsDTO1;
            if (employeeDocumentsDTO.getEmpCandidateDTO() != null) {
                EmpCandidatesEntity EmpCandidateEntity =
                    EM().find(EmpCandidatesEntity.class, employeeDocumentsDTO.getEmpCandidateDTO().getCode());
                if (EmpCandidateEntity == null)
                    throw new ItemNotFoundException();
                employeeDocumentsEntity.setEmpCandidatesEntity(EmpCandidateEntity);
            }
            employeeDocumentsEntity.setCnddocSerial(getCnddocSerialCode((IEmpCandidatesEntityKey)employeeDocumentsDTO.getEmpCandidateDTO().getCode(),
                                                                        employeeDocumentsDTO));
            if (employeeDocumentsDTO.getDocumentTypesDTO() != null) {
                // InfDocumentTypesEntity documentTypesEntity = em.find(InfDocumentTypesEntity.class, employeeDocumentsDTO.getDocumentTypesDTO().getCode());
                IInfDocumentTypesEntityKey docEk =
                    (IInfDocumentTypesEntityKey)employeeDocumentsDTO.getDocumentTypesDTO().getCode();
                if (docEk == null)
                    throw new ItemNotFoundException();
                employeeDocumentsEntity.setDoctypeCode(docEk.getDoctypeCode());
            }
            employeeDocumentsEntity.setDocStatus(employeeDocumentsDTO.getDocStatus());
            employeeDocumentsEntity.setCreatedBy(employeeDocumentsDTO.getCreatedBy());
            employeeDocumentsEntity.setCreatedDate(employeeDocumentsDTO.getCreatedDate());
            employeeDocumentsEntity.setAuditStatus(employeeDocumentsDTO.getAuditStatus());
            employeeDocumentsEntity.setTabrecSerial(employeeDocumentsDTO.getTabrecSerial());
            employeeDocumentsEntity.setAttachmentStatus(employeeDocumentsDTO.getAttachmentStatus());
            employeeDocumentsEntity.setComments(employeeDocumentsDTO.getComments());
            return EmpEntityConverter.getEmpCandidateDocumentsDTO((EmpCandidateDocumentsEntity)this.doAdd(employeeDocumentsEntity));
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
    public Boolean remove(IBasicDTO employeeDocumentsDTO1) throws DataBaseException, SharedApplicationException {
        try {
            IEmpCandidateDocumentsDTO employeeDocumentsDTO = (IEmpCandidateDocumentsDTO)employeeDocumentsDTO1;

            EmpCandidateDocumentsEntity employeeDocumentsEntity =
                EM().find(EmpCandidateDocumentsEntity.class, employeeDocumentsDTO.getCode());

            if (employeeDocumentsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(employeeDocumentsEntity);
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

    public Boolean update(IBasicDTO employeeDocumentsDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidateDocumentsDTO employeeDocumentsDTO = (IEmpCandidateDocumentsDTO)employeeDocumentsDTO1;
            EmpCandidateDocumentsEntity employeeDocumentsEntity =
                EM().find(EmpCandidateDocumentsEntity.class, employeeDocumentsDTO.getCode());

            if (employeeDocumentsDTO.getEmpCandidateDTO() != null) {
                EmpCandidatesEntity EmpCandidateEntity =
                    EM().find(EmpCandidatesEntity.class, employeeDocumentsDTO.getEmpCandidateDTO().getCode());
                if (EmpCandidateEntity == null)
                    throw new ItemNotFoundException();
                employeeDocumentsEntity.setEmpCandidatesEntity(EmpCandidateEntity);
            }
            if (employeeDocumentsDTO.getDocumentTypesDTO() != null) {
                // InfDocumentTypesEntity documentTypesEntity = em.find(InfDocumentTypesEntity.class, employeeDocumentsDTO.getDocumentTypesDTO().getCode());
                IInfDocumentTypesEntityKey docEk =
                    (IInfDocumentTypesEntityKey)employeeDocumentsDTO.getDocumentTypesDTO().getCode();
                if (docEk == null)
                    throw new ItemNotFoundException();
                employeeDocumentsEntity.setDoctypeCode(docEk.getDoctypeCode());
            }
            employeeDocumentsEntity.setDocStatus(employeeDocumentsDTO.getDocStatus());
            employeeDocumentsEntity.setCreatedBy(employeeDocumentsDTO.getCreatedBy());
            employeeDocumentsEntity.setCreatedDate(employeeDocumentsDTO.getCreatedDate());
            employeeDocumentsEntity.setAuditStatus(employeeDocumentsDTO.getAuditStatus());
            employeeDocumentsEntity.setTabrecSerial(employeeDocumentsDTO.getTabrecSerial());
            employeeDocumentsEntity.setAttachmentStatus(employeeDocumentsDTO.getAttachmentStatus());
            employeeDocumentsEntity.setComments(employeeDocumentsDTO.getComments());
            // updated by A.AGAMY for data audit
            doUpdate(employeeDocumentsEntity);
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
     * Get EmployeeDocumentsEntity By Primary Key * @param id
     * @return IEmployeeDocumentsDTO
     */
    public IEmpCandidateDocumentsDTO getById(IEntityKey id) throws DataBaseException, SharedApplicationException {

        try {
            EmpCandidateDocumentsEntity empCandidateDocumentsEntity =
                EM().find(EmpCandidateDocumentsEntity.class, (IEmpCandidateDocumentsEntityKey)id);
            if (empCandidateDocumentsEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpCandidateDocumentsDTO empCandidateDocumentsDTO =
                EmpDTOFactory.createEmpCandidateDocumentsDTO(empCandidateDocumentsEntity);
            return empCandidateDocumentsDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**<code>select o from EmpCandidateDocumentsEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IBasicDTO> getAll() throws DataBaseException, SharedApplicationException {

        try {
            ArrayList arrayList = new ArrayList();
            List<EmpCandidateDocumentsEntity> list =
                EM().createNamedQuery("EmpCandidateDocumentsEntity.findAll").getResultList();
            for (EmpCandidateDocumentsEntity candidateDocument : list) {
                arrayList.add(EmpDTOFactory.createEmpCandidateDocumentsDTO(candidateDocument));
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
     * list available entity * @param code
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> listAvailableEntities(IEntityKey code, IEntityKey hireType) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuilder ejbql =
                new StringBuilder("SELECT req FROM RequiredDocumentsEntity req WHERE req.doctypeCode=req.doctypeCode");
            if (key != null) {
                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;

                    ejbql.append(" AND ( req.nationalityType=" + nationalityType + " OR req.nationalityType=" +
                                 ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( req.genderType=" +
                                 empCandidatesEntity.getCitizensResidentsEntity().getGentypeCode() +
                                 " OR req.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                                 " ) AND req.hireTypesEntity.hirtypeCode=" +
                                 ((IHireTypesEntityKey)hireType).getHirtypeCode() +
                                 " AND req.documentTypesEntity.doctypeCode NOT IN ( SELECT emp.doctypeCode FROM EmpCandidateDocumentsEntity emp WHERE emp.empCandidatesEntity.candidateCode=" +
                                 empCandidatesEntity.getCandidateCode() +
                                 " AND emp.empCandidatesEntity.candidateCodeSeq=" + IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }
            System.out.println(ejbql.toString());
            Query query = EM().createQuery(ejbql.toString());
            System.out.println(ejbql.toString());
            List<RequiredDocumentsEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (RequiredDocumentsEntity entity : list) {
                listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
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
     * list available entity By KwtCitizenCode * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> listAvailableEntitiesByKwtCitizenCode(IBasicDTO paramDto,
                                                                 IEntityKey hireType) throws DataBaseException,
                                                                                             SharedApplicationException {
        try {
            IKwtCitizensResidentsDTO dto = (IKwtCitizensResidentsDTO)paramDto;
            Long genderType=dto.getGentypeCode()!=null? dto.getGentypeCode():((IGenderTypesEntityKey)dto.getGenderTypesDTO().getCode()).getGentypeCode();
            StringBuilder ejbql =
                new StringBuilder("SELECT req FROM RequiredDocumentsEntity req WHERE req.status=1 AND req.doctypeCode=req.doctypeCode");
            if (dto != null) {
                Long nationalityType =
                    dto.getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ? ISystemConstant.NATIONALITY_KUWAITI :
                    ISystemConstant.NATIONALITY_NON_KUWAITI;

                ejbql.append(" AND ( req.nationalityType=" + nationalityType + " OR req.nationalityType=" +
                             ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( req.genderType=" +
                             genderType + " OR req.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                             " ) AND req.hirtypeCode=" +
                             ((IHireTypesEntityKey)hireType).getHirtypeCode());

            }
            System.out.println("EmpCandidateDocumentsDAO.listAvailableEntitiesByKwtCitizenCode::" + ejbql.toString());
            Query query = EM().createQuery(ejbql.toString());
            List<RequiredDocumentsEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (RequiredDocumentsEntity entity : list) {
                listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
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
     * if COUNT(1) return 0 query retrun 1 else retrun 0
     * @param serial
     * @return
     * @throws RemoteException
     * @throws FinderException
     */
    public Long checkEmployeeRequiredDocumentsStatus(Long serial,Long candCode) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            Vector result = null;
            Long returnedNumber = ISystemConstant.VIRTUAL_VALUE;
            String query =
                "SELECT DECODE(COUNT(1),0,1,0) FROM HR_EMP_REQUIRED_DOCUMENTS RD,INF_KWT_CITIZENS_RESIDENTS E , HR_EMP_CANDIDATES C " +
                "WHERE C.ACTIVE_FLAG=1 and C.CIVIL_ID = E.CIVIL_ID AND RD.HIRTYPE_CODE = C.HIRTYPE_CODE AND RD.STATUS = 1 " +
                "AND RD.BASIC_STATUS = 1 AND E.CIVIL_ID = " + serial  + " AND C.CANDIDATE_CODE= " + candCode + " AND CNDSTATUS_CODE = 2 " +
                "AND ( (E.NATIONALITY = 101 AND RD.NATIONALITY_TYPE = 1) OR " +
                "(E.NATIONALITY <>101 AND RD.NATIONALITY_TYPE = 2) OR (RD.NATIONALITY_TYPE = 3)) " +
                "AND ( (GENTYPE_CODE = 1 AND RD.GENDER_TYPE = 1) OR " +
                "(GENTYPE_CODE = 2 AND RD.GENDER_TYPE = 2) OR (RD.GENDER_TYPE = 3)) " +
                "AND NOT EXISTS (SELECT 1 FROM HR_EMP_CANDIDATE_DOCUMENTS CD WHERE CD.DOCTYPE_CODE = RD.DOCTYPE_CODE " +
                "AND CD.CANDIDATE_CODE  = C.CANDIDATE_CODE AND CD.CANDIDATE_CODE_SEQ  = 1 " +
                "AND DOC_STATUS = 1 AND ((ATTACHMENT_REQUIRED = 1 AND ATTACHMENT_STATUS = 1 ) OR ATTACHMENT_REQUIRED = 0 ) )";
         //   System.out.println("EmployeeRequiredDocumentsStatus $$Kareem$$::" + query);
            result = (Vector)EM().createNativeQuery(query).getSingleResult();
            BigDecimal value = (BigDecimal)result.get(0);
            if (value != null) {
                returnedNumber = value.longValue();
            }
            return returnedNumber;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long checkBeforeDeleteDocument(Long docType, Long hireType) throws DataBaseException,
                                                                              SharedApplicationException {

        try {
            Long count = 0L;

            StringBuilder query = new StringBuilder("SELECT COUNT(1) ");
            query.append(" FROM HR_EMP_CANDIDATE_DOCUMENTS CD, HR_EMP_CANDIDATES C");
            query.append(" WHERE CD.CANDIDATE_CODE  = C.CANDIDATE_CODE");
            query.append(" AND CD.CANDIDATE_CODE_SEQ  = C.CANDIDATE_CODE_SEQ ");
            query.append(" AND CD.DOCTYPE_CODE =" + docType + " ");
            query.append("AND C.HIRTYPE_CODE = " + hireType + "  ");
            System.out.println(query);
            Vector v = (Vector)EM().createNativeQuery(query.toString()).getSingleResult();
            if (v != null && v.size() != 0) {
                count = ((BigDecimal)v.get(0)).longValue();

            }

            return count;
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
     * filter available entity * @param code
     * @param name
     * @return list
     * @throws RemoteException
     * @throws FinderException
     */
    public List<IBasicDTO> filterAvailableEntities(IEntityKey code, IEntityKey hireType,
                                                   String name) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuilder ejbql =
                new StringBuilder("SELECT req FROM RequiredDocumentsEntity req WHERE req.doctypeCode=req.doctypeCode");
            if (name != null && name.length() > 0) {
                //By MLotfy new search
                //ejbql.append(" AND req.documentTypesEntity.doctypeName LIKE '" + name + "'");
                ejbql.append(" AND (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("req.documentTypesEntity.doctypeName",
                                                                                 name) + " ) ");
            }
            if (key != null) {
                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);

                //            List<EmployeesEntity> list =
                //                em.createQuery("SELECT o FROM EmployeesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
                //                                                                                                          ((IEmployeesEntityKey)key).getCivilId()).getResultList();
                //            EmployeesEntity employeesEntity = list.get(0);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;
                    ejbql.append(" AND ( req.nationalityType=" + nationalityType + " OR req.nationalityType=" +
                                 ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( req.genderType=" +
                                 empCandidatesEntity.getCitizensResidentsEntity().getGentypeCode() +
                                 " OR req.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                                 " ) AND req.hireTypesEntity.hirtypeCode=" +
                                 ((IHireTypesEntityKey)hireType).getHirtypeCode() +
                                 " AND req.documentTypesEntity.doctypeCode NOT IN ( SELECT emp.doctypeCode FROM EmpCandidateDocumentsEntity emp WHERE emp.empCandidatesEntity.candidateCode=" +
                                 empCandidatesEntity.getCandidateCode() +
                                 " AND emp.empCandidatesEntity.candidateCodeSeq=" + IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }
            Query query = EM().createQuery(ejbql.toString());
            System.out.println(ejbql.toString());
            List<RequiredDocumentsEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (RequiredDocumentsEntity entity : list) {
                listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
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

    public List<IBasicDTO> filterAvailableEntitiesByCode(IEntityKey code, IEntityKey hireType,
                                                         Long docTypeCode) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            IEmpCandidatesEntityKey key = (IEmpCandidatesEntityKey)code;
            StringBuffer ejbql =
                new StringBuffer("SELECT req FROM RequiredDocumentsEntity req WHERE req.doctypeCode=req.doctypeCode");

            if (docTypeCode != null) {
                ejbql.append(" AND req.documentTypesEntity.doctypeCode = " + docTypeCode);
            }

            if (key != null) {
                EmpCandidatesEntity empCandidatesEntity = EM().find(EmpCandidatesEntity.class, key);

                //            List<EmployeesEntity> list =
                //                em.createQuery("SELECT o FROM EmpCandidatesEntity o WHERE o.civilId = :civilId").setParameter("civilId",
                //                                                                                                          ((IEmployeesEntityKey)key).getCivilId()).getResultList();
                //            EmployeesEntity employeesEntity = list.get(0);
                if (empCandidatesEntity != null) {
                    Long nationalityType =
                        empCandidatesEntity.getCitizensResidentsEntity().getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ?
                        ISystemConstant.NATIONALITY_KUWAITI : ISystemConstant.NATIONALITY_NON_KUWAITI;
                    ejbql.append(" AND ( req.nationalityType=" + nationalityType + " OR req.nationalityType=" +
                                 ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( req.genderType=" +
                                 empCandidatesEntity.getCitizensResidentsEntity().getGentypeCode() +
                                 " OR req.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                                 " ) AND req.hireTypesEntity.hirtypeCode=" +
                                 ((IHireTypesEntityKey)hireType).getHirtypeCode() +
                                 " AND req.documentTypesEntity.doctypeCode NOT IN ( SELECT emp.doctypeCode FROM EmpCandidateDocumentsEntity emp WHERE emp.empCandidatesEntity.candidateCode= " +
                                 empCandidatesEntity.getCandidateCode() +
                                 " AND emp.empCandidatesEntity.candidateCodeSeq=" + IEMPConstants.EMP_CND_SEQ + " ) ");
                }
            }

            Query query = EM().createQuery(ejbql.toString());

            List<RequiredDocumentsEntity> list = query.setHint("toplink.refresh", "true").getResultList();

            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }

            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (RequiredDocumentsEntity entity : list) {
                listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
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
     * search in filtered available list by code or name
     * set value for name or code and another set null
     * @param paramDto
     * @param hireType
     * @param name
     * @param docTypeCode
     * @return
     * @throws DataBaseException
     * @throws SharedApplicationException
     * @auther Kareem Sayed
     */
    public List<IBasicDTO> filterAvailableEntities(IBasicDTO paramDto, IEntityKey hireType, String name,
                                                   Long docTypeCode) throws DataBaseException,
                                                                            SharedApplicationException {

        try {
            IKwtCitizensResidentsDTO dto = (IKwtCitizensResidentsDTO)paramDto;
            StringBuilder ejbql = new StringBuilder("SELECT req FROM RequiredDocumentsEntity req ");
            if (name != null && name.length() > 0) {
                ejbql.append(" WHERE (" +
                             QueryConditionBuilder.getEjbqlSimilarCharsCondition("req.documentTypesEntity.doctypeName",
                                                                                 name) + " ) ");
            } else if (docTypeCode != null) {
                ejbql.append(" WHERE req.documentTypesEntity.doctypeCode = " + docTypeCode);
            }
            if (dto != null) {
                Long nationalityType =
                    dto.getNationality().equals(ISystemConstant.KUWAIT_NATIONALITY) ? ISystemConstant.NATIONALITY_KUWAITI :
                    ISystemConstant.NATIONALITY_NON_KUWAITI;

                ejbql.append(" AND ( req.nationalityType=" + nationalityType + " OR req.nationalityType=" +
                             ISystemConstant.NATIONALITY_NON_SPECIFIED + " ) AND ( req.genderType=" +
                             dto.getGentypeCode() + " OR req.genderType=" + ISystemConstant.GENDER_UNDEFINED +
                             " ) AND req.hireTypesEntity.hirtypeCode=" +
                             ((IHireTypesEntityKey)hireType).getHirtypeCode());
            }
            Query query = EM().createQuery(ejbql.toString());
            System.out.println("EmpCandidateDocumentsDAO.filterAvailableEntities::" + ejbql.toString());
            List<RequiredDocumentsEntity> list = query.setHint("toplink.refresh", "true").getResultList();
            if (list == null || list.size() == 0)
                throw new NoResultException();
            List<IBasicDTO> listDTO = new ArrayList<IBasicDTO>();
            for (RequiredDocumentsEntity entity : list) {
                listDTO.add(EmpDTOFactory.createRequiredDocumentsDTO(entity));
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

    public Long checkAboutAttachmentForCivil(Long civilId, Long docTypeCode) throws DataBaseException,
                                                                                    SharedApplicationException {

        try {
            Long count = 0L;

            StringBuilder query = new StringBuilder("SELECT COUNT(1) ");
            query.append(" from INF_PERSON_DOCUMENTS per , INF_PERSON_DOC_ATTACHMENTS atch");
            query.append(" where per.CIVIL_ID = atch.CIVIL_ID");
            query.append(" and per.EMPDOC_SERIAL = atch.EMPDOC_SERIAL");
            query.append(" and atch.STATUS = 1");
            query.append(" AND per.DOCTYPE_CODE =" + docTypeCode + " ");
            query.append(" AND per.CIVIL_ID = " + civilId + "  ");
            System.out.println(query);
            Vector v = (Vector)EM().createNativeQuery(query.toString()).getSingleResult();
            if (v != null && v.size() != 0) {
                count = ((BigDecimal)v.get(0)).longValue();

            }

            return count;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public List<IEmpCandidateDocumentsDTO> getByCandCode(IEntityKey candidateCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            Long candCode = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCode();
            Long candidateCodeSeq = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCodeSeq();
            Query query = EM().createNamedQuery("EmpCandidateDocumentsEntity.getByCndCode");
            query.setParameter("candidateCode", candCode);
            query.setParameter("candidateCodeSeq", candidateCodeSeq);

            List<EmpCandidateDocumentsEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            List<IEmpCandidateDocumentsDTO> resutListDTO = new ArrayList();
            for (EmpCandidateDocumentsEntity entity : list) {
                IEmpCandidateDocumentsDTO empCndSalaryElementsDTO =
                    EmpEntityConverter.getEmpCandidateDocumentsDTO(entity);
                resutListDTO.add(empCndSalaryElementsDTO);

            }
            return resutListDTO;
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
    
    
    public List<IEmpCandidateDocumentsDTO> getByCandCodeNew(IEntityKey candidateCode) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            Long candCode = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCode();
            Long candidateCodeSeq = ((IEmpCandidatesEntityKey)candidateCode).getCandidateCodeSeq();
            Query query = EM().createNamedQuery("EmpCandidateDocumentsEntity.getByCndCode");
            query.setParameter("candidateCode", candCode);
            query.setParameter("candidateCodeSeq", 1L);

            List<EmpCandidateDocumentsEntity> list = query.getResultList();
            if (list == null || list.size() == 0) {
                throw new NoResultException();
            }
            List<IEmpCandidateDocumentsDTO> resutListDTO = new ArrayList();
            for (EmpCandidateDocumentsEntity entity : list) {
                IEmpCandidateDocumentsDTO empCndSalaryElementsDTO =
                    EmpEntityConverter.getEmpCandidateDocumentsDTO(entity);
                resutListDTO.add(empCndSalaryElementsDTO);

            }
            return resutListDTO;
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

    public void addNewRecord(IEmpCandidateDocumentsDTO dto) throws DataBaseException,
                                                                           SharedApplicationException {

       
        Long cndDocSerial = 1L;
        Connection con = getConnectionForUpdate();
        try {
            Long empCandidateCode=((IEmpCandidatesEntityKey)dto.getEmpCandidateDTO().getCode()).getCandidateCode();
            cndDocSerial = findNewSerialforEmpCandidateDocuments(empCandidateCode, 1L);

            StringBuilder sqlQuery = new StringBuilder("");
            sqlQuery.append("insert into HR_EMP_CANDIDATE_DOCUMENTS (CANDIDATE_CODE,CANDIDATE_CODE_SEQ  ,CNDDOC_SERIAL,DOCTYPE_CODE  ,DOC_STATUS  ,ATTACHMENT_STATUS ) values (");

            sqlQuery.append( empCandidateCode + ", '1' , " + cndDocSerial + "," + dto.getDoctypeCode() + " , '1' , '1' )");
            PreparedStatement ps = con.prepareCall(sqlQuery.toString());
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }


    public boolean addedRecordBefore(IEmpCandidateDocumentsDTO dto) throws DataBaseException, SharedApplicationException {
        Long empCandidateCode=((IEmpCandidatesEntityKey)dto.getEmpCandidateDTO().getCode()).getCandidateCode();
        try {
            StringBuilder sql = new StringBuilder(" select * ");
            sql.append(" from HR_EMP_CANDIDATE_DOCUMENTS o ");
            sql.append(" where   o.CANDIDATE_CODE = " + empCandidateCode);
            sql.append(" AND o.CANDIDATE_CODE_SEQ=1 and   o.DOCTYPE_CODE = " + dto.getDoctypeCode());
            String query = sql.toString();

            List list = EM().createNativeQuery(query).getResultList();


            if (list.size() == 0)
                return false;
            else
                return true;

        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public boolean removeRecord(IEmpCandidateDocumentsDTO dto) throws DataBaseException, SharedApplicationException {
        Connection con = getConnectionForUpdate();
        Long empCandidateCode=((IEmpCandidatesEntityKey)dto.getEmpCandidateDTO().getCode()).getCandidateCode();
        try {
            StringBuilder sql = new StringBuilder("delete from HR_EMP_CANDIDATE_DOCUMENTS o ");
            sql.append(" where   o.CANDIDATE_CODE = " + empCandidateCode);
            sql.append(" AND o.CANDIDATE_CODE_SEQ=1 and   o.DOCTYPE_CODE = " + dto.getDoctypeCode());

            PreparedStatement ps = con.prepareCall(sql.toString());
            ps.executeUpdate();
            ps.close();
              return true;
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
