package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.common.shared.SharedUtils;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpCandidatesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmpExtraDataTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


public class EmpCandidateExtraDataDAO extends EmpBaseDAO {
    public EmpCandidateExtraDataDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpCandidateExtraDataDAO();
    }

    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpCandidateExtraDataEntity.findNewId").getSingleResult();
            if (id != null) {
                return id + 1;
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
     * Create a New EmpCandidateExtraDataEntity
     * @param empCandExtraDataDTO
     * @return empCandExtraDataDTO
     */
    public IEmpCandidateExtraDataDTO add(IBasicDTO empCandExtraDataDTO1) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            Long code = findMaxId();
            EmpCandidateExtraDataEntity empCandidateExtraDataEntity = new EmpCandidateExtraDataEntity();

            IEmpCandidateExtraDataDTO empCandExtraDataDTO = (IEmpCandidateExtraDataDTO)empCandExtraDataDTO1;
                if(empCandExtraDataDTO.getValue() !=null && !empCandExtraDataDTO.getValue().equals("-100")){
            IEmpCandidatesEntityKey key = null;
            try {
                key = ((IEmpCandidatesEntityKey)empCandExtraDataDTO.getEmpCandidatesDTO().getCode());
            } catch (Exception e) {
                e.printStackTrace();
            }


            //    Long candidateCode = key.getCandidateCode();
            //  Long candidateCodeSeq = key.getCandidateCodeSeq();
            Long candidateCode = 0L;
            Long candidateCodeSeq = 0L;

            if (empCandExtraDataDTO.getCandidateCode() != null) {
                candidateCode = empCandExtraDataDTO.getCandidateCode();
                candidateCodeSeq = empCandExtraDataDTO.getCandidateCodeSeq();
            } else if (key != null && key.getCandidateCode() != null) {
                candidateCode = key.getCandidateCode();
                candidateCodeSeq = key.getCandidateCodeSeq();
            }
            if (candidateCode == 0L)
                return null;

            //   empCandidateExtraDataEntity.setSerial(((IEmpCandidateExtraDataEntityKey)empCandExtraDataDTO.getCode()).getSerial());
           
            empCandidateExtraDataEntity.setSerial(code);
            empCandidateExtraDataEntity.setFromDate(SharedUtils.getSqlDate());
            empCandidateExtraDataEntity.setUntilDate(SharedUtils.getSqlDate());
            empCandidateExtraDataEntity.setValue(empCandExtraDataDTO.getValue());
            empCandidateExtraDataEntity.setCreatedBy(empCandExtraDataDTO.getCreatedBy());
            empCandidateExtraDataEntity.setCreatedDate(empCandExtraDataDTO.getCreatedDate());
            empCandidateExtraDataEntity.setAuditStatus(empCandExtraDataDTO.getAuditStatus());
            empCandidateExtraDataEntity.setTabrecSerial(empCandExtraDataDTO.getTabrecSerial());
            empCandidateExtraDataEntity.setValueDesc(empCandExtraDataDTO.getValueDesc());
            empCandidateExtraDataEntity.setStatus(1L);
            
            if (candidateCode != null) {
                List listcand =
                    EM().createQuery("SELECT o FROM EmpCandidatesEntity o WHERE o.candidateCode = :candidateCode AND o.candidateCodeSeq = :candidateCodeSeq").setParameter("candidateCode",
                                                                                                                                                                           candidateCode).setParameter("candidateCodeSeq",
                                                                                                                                                                                                       candidateCodeSeq).getResultList();
                if (listcand == null || listcand.size() == 0)
                    throw new ItemNotFoundException();
                empCandidateExtraDataEntity.setEmpCandidatesEntity((EmpCandidatesEntity)listcand.get(0));
            } else {
                throw new ItemNotFoundException();
            }

            if (empCandExtraDataDTO.getEmpExtraDataTypesDTO() != null) {
                Long extdattypeCode =
                    ((IEmpExtraDataTypesEntityKey)empCandExtraDataDTO.getEmpExtraDataTypesDTO().getCode()).getExtdattypeCode();
                List list =
                    EM().createQuery("SELECT o FROM EmpExtraDataTypesEntity o WHERE o.extdattypeCode = :extdattypeCode").setParameter("extdattypeCode",
                                                                                                                                      extdattypeCode).getResultList();
                if (list == null || list.size() == 0)
                    throw new ItemNotFoundException();
                empCandidateExtraDataEntity.setEmpExtraDataTypesEntity((EmpExtraDataTypesEntity)list.get(0));
            } else {
                throw new ItemNotFoundException();
            }

            this.doAdd(empCandidateExtraDataEntity);
            empCandExtraDataDTO.setCode(EmpEntityKeyFactory.createEmpCandidateExtraDataEntityKey(code));
            // Set PK after creation
            }  
            return empCandExtraDataDTO;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean update(IBasicDTO empCandExtraDataDTO1) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidateExtraDataDTO empCandExtraDataDTO = (IEmpCandidateExtraDataDTO)empCandExtraDataDTO1;
            IEntityKey empCndEntityKey = empCandExtraDataDTO.getCode();
            EmpCandidateExtraDataEntity empCandidateExtraDataEntity =
                EM().find(EmpCandidateExtraDataEntity.class, empCndEntityKey);
            empCandidateExtraDataEntity.setFromDate(empCandExtraDataDTO.getFromDate());
            empCandidateExtraDataEntity.setUntilDate(empCandExtraDataDTO.getUntilDate());
            
           
            
            empCandidateExtraDataEntity.setValueDesc(empCandExtraDataDTO.getValueDesc());
            
            String exDValue =
                extraDataValueForType(((IEmpCandidatesEntityKey)empCandExtraDataDTO.getEmpCandidatesDTO().getCode()).getCandidateCode(), ((IEmpCandidatesEntityKey)empCandExtraDataDTO.getEmpCandidatesDTO().getCode()).getCandidateCodeSeq(),
                                                  Long.valueOf(empCandExtraDataDTO.getEmpExtraDataTypesDTO().getCode().getKey()));
            if (empCandExtraDataDTO.getValue().equals(exDValue)){
                    empCandidateExtraDataEntity.setLastUpdatedBy(empCandExtraDataDTO.getLastUpdatedBy());
            }else{
                    empCandidateExtraDataEntity.setLastUpdatedBy(null);
                }
            if(empCandExtraDataDTO.getValue() != null && !empCandExtraDataDTO.getValue().equals(""))
            empCandidateExtraDataEntity.setValue(empCandExtraDataDTO.getValue());
            else
            empCandidateExtraDataEntity.setValue(".");    
            
            empCandidateExtraDataEntity.setAuditStatus(empCandExtraDataDTO.getAuditStatus());
            empCandidateExtraDataEntity.setTabrecSerial(empCandExtraDataDTO.getTabrecSerial());
            empCandidateExtraDataEntity.setStatus(empCandExtraDataDTO.getStatus());

            if (empCandExtraDataDTO.getEmpCandidatesDTO() != null) {
                EmpCandidatesEntity EmpCandidateEntity =
                    EM().find(EmpCandidatesEntity.class, empCandExtraDataDTO.getEmpCandidatesDTO().getCode());
                if (EmpCandidateEntity == null)
                    throw new ItemNotFoundException();
                empCandidateExtraDataEntity.setEmpCandidatesEntity(EmpCandidateEntity);
            }
            if (empCandExtraDataDTO.getEmpExtraDataTypesDTO() != null) {
                EmpExtraDataTypesEntity empExtraDataTypesEntity =
                    EM().find(EmpExtraDataTypesEntity.class, empCandExtraDataDTO.getEmpExtraDataTypesDTO().getCode());
                if (empExtraDataTypesEntity == null)
                    throw new ItemNotFoundException();
                empCandidateExtraDataEntity.setEmpExtraDataTypesEntity(empExtraDataTypesEntity);
            }
            // updated by A.AGAMY for data audit
            doUpdate(empCandidateExtraDataEntity);
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
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataForCivil(Long candCode, Long candSeq) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("EmpCandidateExtraDataEntity.getAllEmployeeExtraDataByCandCodeAndCandSeq").setParameter("candidateCode",
                                                                                                                              candCode).setParameter("candidateCodeSeq",
                                                                                                                                                     candSeq).getResultList();
            if (list != null && list.size() != 0) {
                EmpCandidateExtraDataEntity entity = null;
                entity = (EmpCandidateExtraDataEntity)list.get(list.size() - 1);
                return true;
            }
            return Boolean.FALSE;
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
     * check if extra data type value for specific civil id is active or not(1 >> true, else false)
     * @param civilId
     * @param extraDataTypeCode
     * @return Boolean
     * @throws RemoteException
     */
    public Boolean isActiveExtraDataForType(Long candCode, Long candSeq, Long extraDataType) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("EmpCandidateExtraDataEntity.getExtraDataByCandCodeAndCandSeqAndDatType").setParameter("candidateCode",
                                                                                                                             candCode).setParameter("candidateCodeSeq",
                                                                                                                                                    candSeq).setParameter("extDatTypeCode",
                                                                                                                                                                          extraDataType).getResultList();
            if (list != null && list.size() != 0) {
                EmpCandidateExtraDataEntity entity = null;
                entity = (EmpCandidateExtraDataEntity)list.get(list.size() - 1);
                return true;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    
    public String extraDataValueForType(Long candCode, Long candSeq, Long extraDataType) throws DataBaseException,
                                                                                                    SharedApplicationException {
        try {
            List list =
                EM().createNamedQuery("EmpCandidateExtraDataEntity.getExtraDataByCandCodeAndCandSeqAndDatType").setParameter("candidateCode",
                                                                                                                             candCode).setParameter("candidateCodeSeq",
                                                                                                                                                    candSeq).setParameter("extDatTypeCode",
                                                                                                                                                                          extraDataType).getResultList();
            if (list != null && list.size() != 0) {
                EmpCandidateExtraDataEntity entity = null;
                entity = (EmpCandidateExtraDataEntity)list.get(0);
                
                return entity.getValue();
            }
            else
            return "";
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    
    public Boolean remove(IBasicDTO empExtraDataDTO) throws DataBaseException, SharedApplicationException {

        try {
            IEmpCandidateExtraDataDTO empCandidateExtraDataDTO = (IEmpCandidateExtraDataDTO)empExtraDataDTO;
            EmpCandidateExtraDataEntity empCandidateExtraDataEntity = EM().find(EmpCandidateExtraDataEntity.class, empCandidateExtraDataDTO.getCode());
            if (empCandidateExtraDataEntity == null) {
                throw new ItemNotFoundException();
            };
            doRemove(empCandidateExtraDataEntity);
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

}
