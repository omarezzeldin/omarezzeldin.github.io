package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.bgt.business.dto.BgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.dto.IBgtProgramsDTO;
import com.beshara.csc.hr.bgt.business.entity.BgtEntityKeyFactory;
import com.beshara.csc.hr.bgt.business.entity.IBgtProgramsEntityKey;
import com.beshara.csc.hr.bgt.business.entity.IBgtTypesEntityKey;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpInternalExperienceDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpInternalExperienceDTO;
import com.beshara.csc.hr.emp.business.dto.ITrxTypesDTO;
import com.beshara.csc.hr.emp.business.dto.TrxTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpInternalExperienceEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpInternalExperienceEntityKey;
import com.beshara.csc.hr.emp.business.entity.ITrxTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.TrxTypesEntity;
import com.beshara.csc.hr.sal.business.dto.ISalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.dto.SalElementGuidesDTO;
import com.beshara.csc.hr.sal.business.entity.ISalElementGuidesEntityKey;
import com.beshara.csc.hr.sal.business.entity.SalEntityKeyFactory;
import com.beshara.csc.nl.job.business.dto.IJobsDTO;
import com.beshara.csc.nl.job.business.dto.JobsDTO;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.job.business.entity.JobEntityKeyFactory;
import com.beshara.csc.nl.org.business.dto.CatsDTO;
import com.beshara.csc.nl.org.business.dto.ICatsDTO;
import com.beshara.csc.nl.org.business.dto.IMinistriesDTO;
import com.beshara.csc.nl.org.business.dto.IWorkCentersDTO;
import com.beshara.csc.nl.org.business.dto.MinistriesDTO;
import com.beshara.csc.nl.org.business.dto.WorkCentersDTO;
import com.beshara.csc.nl.org.business.entity.IMinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.IOrgCatsEntityKey;
import com.beshara.csc.nl.org.business.entity.IWorkCentersEntityKey;
import com.beshara.csc.nl.org.business.entity.MinistriesEntityKey;
import com.beshara.csc.nl.org.business.entity.OrgEntityKeyFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ejb.FinderException;

import javax.persistence.Query;


public class EmpInternalExperienceDAO extends EmpBaseDAO {

    public EmpInternalExperienceDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new EmpInternalExperienceDAO();
    }
    
    
    
    @Override
    /**<code>select o from EmpInternalExperienceEntity o</code>.
     * @return List
     */
    public List<IEmpInternalExperienceDTO> getAll() throws DataBaseException, SharedApplicationException {
        
        try{
        ArrayList<IEmpInternalExperienceDTO> arrayList = new ArrayList<IEmpInternalExperienceDTO>();
        List<EmpInternalExperienceEntity> list =
            EM().createNamedQuery("EmpInternalExperienceEntity.findAll").getResultList();
            
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
        for (EmpInternalExperienceEntity empInternalExperience : list) {
            arrayList.add(EmpDTOFactory.createEmpInternalExperienceDTO(empInternalExperience));
        }
        return arrayList;
            
            
        }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
    }
        
    }
        
        
        @Override
        /**
         * Create a New EmpInternalExperienceEntity
         * @param empInternalExperienceDTO
         * @return EmpInternalExperienceDTO
         */
        public IEmpInternalExperienceDTO add (IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException {
            try{

            EmpInternalExperienceEntity empInternalExperienceEntity = new EmpInternalExperienceEntity();

            IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)empInternalExperienceDTO1;

           // empInternalExperienceEntity.setSerial(((IEmpInternalExperienceEntityKey)empInternalExperienceDTO.getCode()).getSerial());
                Long serial=findMaxId();
                empInternalExperienceEntity.setSerial(serial);
                
            empInternalExperienceEntity.setCivilId(empInternalExperienceDTO.getCivilId());
            empInternalExperienceEntity.setActionDate(empInternalExperienceDTO.getActionDate());
            empInternalExperienceEntity.setPisJobCode(empInternalExperienceDTO.getPisJobCode());
            empInternalExperienceEntity.setPisWrkCode(empInternalExperienceDTO.getPisWrkCode());
            empInternalExperienceEntity.setJobDetail(empInternalExperienceDTO.getJobDetail());
            empInternalExperienceEntity.setRevFlag(empInternalExperienceDTO.getRevFlag());
            empInternalExperienceEntity.setRecordSourceFlag(empInternalExperienceDTO.getRecordSourceFlag());
            empInternalExperienceEntity.setAuditStatus(empInternalExperienceDTO.getAuditStatus());
            empInternalExperienceEntity.setTabrecSerial(empInternalExperienceDTO.getTabrecSerial());
            empInternalExperienceEntity.setCreatedBy(empInternalExperienceDTO.getCreatedBy());
            empInternalExperienceEntity.setCreatedDate(empInternalExperienceDTO.getCreatedDate());
            empInternalExperienceEntity.setLastUpdatedBy(empInternalExperienceDTO.getLastUpdatedBy());
            empInternalExperienceEntity.setLastUpdatedDate(empInternalExperienceDTO.getLastUpdatedDate());

            if (empInternalExperienceDTO.getWorkCenterDTO() != null) {
                IWorkCentersEntityKey wrkEk = (IWorkCentersEntityKey)empInternalExperienceDTO.getWorkCenterDTO().getCode();
              //  WorkCentersEntity workCentersEntity =  em.find(WorkCentersEntity.class, (IWorkCentersEntityKey)(empInternalExperienceDTO.getWorkCenterDTO().getCode()));
                if (wrkEk.getMinCode()==null ||  wrkEk.getWrkCode()== null){
                    throw new ItemNotFoundException();
                }else{
                empInternalExperienceEntity.setWrkCode(wrkEk.getWrkCode());
                empInternalExperienceEntity.setMinCode(wrkEk.getMinCode()); 
                }
            }
            if (empInternalExperienceDTO.getJobsDTO() != null) {
                IJobsEntityKey jobEk = (IJobsEntityKey)empInternalExperienceDTO.getJobsDTO().getCode();
                //JobsEntity jobsEntity = em.find(JobsEntity.class, (IJobsEntityKey)(empInternalExperienceDTO.getJobsDTO().getCode()));
                if (jobEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setJobCode(jobEk.getJobCode());
            }
            if (empInternalExperienceDTO.getSalElementGuidesDTO() != null) {
             //   SalElementGuidesEntity salElementGuidesEntity = em.find(SalElementGuidesEntity.class, (ISalElementGuidesEntityKey)empInternalExperienceDTO.getSalElementGuidesDTO().getCode());
                ISalElementGuidesEntityKey salEk = (ISalElementGuidesEntityKey)empInternalExperienceDTO.getSalElementGuidesDTO().getCode();
                if (salEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setElmguideCode(salEk.getElmguideCode());
            }
            if (empInternalExperienceDTO.getTrxTypesDTO() != null) {
                TrxTypesEntity trxTypesEntity =
                    EM().find(TrxTypesEntity.class, (ITrxTypesEntityKey)(empInternalExperienceDTO.getTrxTypesDTO().getCode()));
                if (trxTypesEntity == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setTrxTypesEntity(trxTypesEntity);
            }
            if (empInternalExperienceDTO.getBgtProgramsDTO() != null) {
               // BgtProgramsEntity bgtProgramsEntity = em.find(BgtProgramsEntity.class, (IBgtProgramsEntityKey)(empInternalExperienceDTO.getBgtProgramsDTO().getCode()));
               IBgtProgramsEntityKey pEk = (IBgtProgramsEntityKey)empInternalExperienceDTO.getBgtProgramsDTO().getCode();
                if (pEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setBgtprgCode(pEk.getPrgCode());
            }
            if (empInternalExperienceDTO.getBgtTypesDTO() != null) {
               // BgtTypesEntity bgtTypesEntity = em.find(BgtTypesEntity.class, (IBgtTypesEntityKey)(empInternalExperienceDTO.getBgtTypesDTO().getCode()));
               IBgtTypesEntityKey typeEk = (IBgtTypesEntityKey)empInternalExperienceDTO.getBgtTypesDTO().getCode();
                if (typeEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setBgttypeCode(typeEk.getTypeCode());
            }
            //this.persistEntity(empInternalExperienceEntity);
                
            doAdd(empInternalExperienceEntity);
            // Set PK after creation
                
            empInternalExperienceDTO.setCode(EmpEntityKeyFactory.createEmpInternalExperienceEntityKey(serial));
                
            return empInternalExperienceDTO;
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }

        }
        
        
        
        @Override
        /**
         * Update an Existing EmpInternalExperienceEntity
         * @param empInternalExperienceDTO
         * @return Boolean
         */
        public Boolean update(IBasicDTO empInternalExperienceDTO1) throws DataBaseException , SharedApplicationException {
            
            try{

            IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)empInternalExperienceDTO1;

            EmpInternalExperienceEntity empInternalExperienceEntity =
                EM().find(EmpInternalExperienceEntity.class, (IEmpInternalExperienceEntityKey)empInternalExperienceDTO.getCode());

            empInternalExperienceEntity.setCivilId(empInternalExperienceDTO.getCivilId());
            empInternalExperienceEntity.setActionDate(empInternalExperienceDTO.getActionDate());
            empInternalExperienceEntity.setPisJobCode(empInternalExperienceDTO.getPisJobCode());
            empInternalExperienceEntity.setPisWrkCode(empInternalExperienceDTO.getPisWrkCode());
            empInternalExperienceEntity.setJobDetail(empInternalExperienceDTO.getJobDetail());
            empInternalExperienceEntity.setRevFlag(empInternalExperienceDTO.getRevFlag());
            empInternalExperienceEntity.setRecordSourceFlag(empInternalExperienceDTO.getRecordSourceFlag());
            empInternalExperienceEntity.setAuditStatus(empInternalExperienceDTO.getAuditStatus());
            empInternalExperienceEntity.setTabrecSerial(empInternalExperienceDTO.getTabrecSerial());
            empInternalExperienceEntity.setCreatedBy(empInternalExperienceDTO.getCreatedBy());
            empInternalExperienceEntity.setCreatedDate(empInternalExperienceDTO.getCreatedDate());
            empInternalExperienceEntity.setLastUpdatedBy(empInternalExperienceDTO.getLastUpdatedBy());
            empInternalExperienceEntity.setLastUpdatedDate(empInternalExperienceDTO.getLastUpdatedDate());

            if (empInternalExperienceDTO.getWorkCenterDTO() != null) {
             //   WorkCentersEntity workCentersEntity =  em.find(WorkCentersEntity.class, (IWorkCentersEntityKey)(empInternalExperienceDTO.getWorkCenterDTO().getCode()));
             IWorkCentersEntityKey wrkEk = (IWorkCentersEntityKey)empInternalExperienceDTO.getWorkCenterDTO().getCode();
             if (wrkEk.getMinCode()==null ||  wrkEk.getWrkCode()== null){
                 throw new ItemNotFoundException();
             }else{
             empInternalExperienceEntity.setWrkCode(wrkEk.getWrkCode());
             empInternalExperienceEntity.setMinCode(wrkEk.getMinCode()); 
             }
            }
            if (empInternalExperienceDTO.getJobsDTO() != null) {
               // JobsEntity jobsEntity =  em.find(JobsEntity.class, (IJobsEntityKey)(empInternalExperienceDTO.getJobsDTO().getCode()));
               IJobsEntityKey jobEk = (IJobsEntityKey)empInternalExperienceDTO.getJobsDTO().getCode();
                if (jobEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setJobCode(jobEk.getJobCode());
            }
            if (empInternalExperienceDTO.getSalElementGuidesDTO() != null) {
               // SalElementGuidesEntity salElementGuidesEntity = em.find(SalElementGuidesEntity.class, (ISalElementGuidesEntityKey)empInternalExperienceDTO.getSalElementGuidesDTO().getCode());
               ISalElementGuidesEntityKey salEk = (ISalElementGuidesEntityKey)empInternalExperienceDTO.getSalElementGuidesDTO().getCode(); 
                if (salEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setElmguideCode(salEk.getElmguideCode());
            }
            if (empInternalExperienceDTO.getTrxTypesDTO() != null) {
                TrxTypesEntity trxTypesEntity =
                    EM().find(TrxTypesEntity.class, (ITrxTypesEntityKey)(empInternalExperienceDTO.getTrxTypesDTO().getCode()));
                if (trxTypesEntity == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setTrxTypesEntity(trxTypesEntity);
            }
            if (empInternalExperienceDTO.getBgtProgramsDTO() != null) {
              //  BgtProgramsEntity bgtProgramsEntity = em.find(BgtProgramsEntity.class, (IBgtProgramsEntityKey)(empInternalExperienceDTO.getBgtProgramsDTO().getCode()));
                IBgtProgramsEntityKey pEk = (IBgtProgramsEntityKey)empInternalExperienceDTO.getBgtProgramsDTO().getCode();
                if (pEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setBgtprgCode(pEk.getPrgCode());
            }
            if (empInternalExperienceDTO.getBgtTypesDTO() != null) {
              //  BgtTypesEntity bgtTypesEntity =  em.find(BgtTypesEntity.class, (IBgtTypesEntityKey)(empInternalExperienceDTO.getBgtTypesDTO().getCode()));
              IBgtTypesEntityKey typesEk = (IBgtTypesEntityKey)empInternalExperienceDTO.getBgtTypesDTO().getCode();
                if (typesEk == null)
                    throw new ItemNotFoundException();
                empInternalExperienceEntity.setBgttypeCode(typesEk.getTypeCode());
            }
                doUpdate(empInternalExperienceEntity);
            return Boolean.TRUE;
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        }
        
        
        @Override
        /**
         * Remove an Existing EmpInternalExperienceEntity
         * @param empInternalExperienceDTO
         * @return Boolean
         */
        public Boolean remove(IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException {
            
            try{

            IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)empInternalExperienceDTO1;

            EmpInternalExperienceEntity empInternalExperienceEntity =
                EM().find(EmpInternalExperienceEntity.class, (IEmpInternalExperienceEntityKey)empInternalExperienceDTO.getCode());

            if (empInternalExperienceEntity == null) {
                throw new FinderException();
            }
          //  em.remove(empInternalExperienceEntity);
          doRemove(empInternalExperienceEntity);
            return Boolean.TRUE;
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        }
        
        
        @Override
        /**
         * Get EmpInternalExperienceEntity By Primary Key
         * @param id
         * @return EmpInternalExperienceDTO
         */
        public IEmpInternalExperienceDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
            
            try{

            IEmpInternalExperienceEntityKey id = (IEmpInternalExperienceEntityKey)id1;

            EmpInternalExperienceEntity empInternalExperienceEntity = EM().find(EmpInternalExperienceEntity.class, id);
            if (empInternalExperienceEntity == null) {
                throw new ItemNotFoundException();
            }
            IEmpInternalExperienceDTO empInternalExperienceDTO =
                EmpDTOFactory.createEmpInternalExperienceDTO(empInternalExperienceEntity);
            return empInternalExperienceDTO;
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        }
        
        
        
        /**
         * Search for EmpInternalExperienceEntity
         * <br>
         * @return List
         */
        public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
            
            try{
            return super.search(basicDTO);
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        }
        
        
        
        /**
         * Get the MaxId of AbilitiesEntity
         * <br>
         * @return Object
         */
        public Long findNewId() throws DataBaseException, SharedApplicationException { 
            
            try{
            Long id = (Long)EM().createNamedQuery("EmpInternalExperienceEntity.findNewId").getSingleResult();
                if (id == null) {
                    return Long.valueOf(1);
                } else {
                    return id + 1L;
                }
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        }
        
        
        
        public List<IBasicDTO> getAllByCivilIdAndMinCode(Long civilId, Long minCode) throws DataBaseException, SharedApplicationException {
            
            try{
            List list =
                EM().createNamedQuery("EmpInternalExperienceEntity.getAllByCivilIdAndMinCode").setParameter("civilId",
                                                                                                          civilId).setParameter("minCode",
                                                                                                                                minCode).getResultList();

            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();

            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            for (Object entity : list) {
                arrayList.add(EmpDTOFactory.createEmpInternalExperienceDTO((EmpInternalExperienceEntity)entity));
            }
            return arrayList;
                
            }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        }
    /*
     * getAllBySearchCriteria 
     * by A.Nour story CSC-18120 work stream C AOE
     * */    
    public List<IBasicDTO> getAllBySearchCriteria(IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException {
        /*select EXP.SERIAL , EXP.CIVIL_ID , EXP.MIN_CODE , MIN.MIN_NAME , CAT.CAT_CODE , CAT.CAT_NAME , EXP.ACTION_DATE , EXP.JOB_CODE , JOB.JOB_NAME ,
        EXP.PIS_JOB_CODE , EXP.ELMGUIDE_CODE , GUID3.EMLGUIDE_NAME || ' - ' || GUID2.EMLGUIDE_NAME || ' - ' || GUID.EMLGUIDE_NAME AS ELMGUIDE_NAME ,  
        EXP.WRK_CODE , CENTER.WRK_NAME , EXP.PIS_WRK_CODE , EXP.JOB_DETAIL , EXP.TRXTYPE_CODE , TRX_TYPE.TRXTYPE_NAME , EXP.REV_FLAG , 
        EXP.BGTTYPE_CODE , BGT_TYPE.TYPE_NAME , EXP.BGTPRG_CODE , BGT_PROG.PRG_NAME , EXP.RECORD_SOURCE_FLAG
        FROM HR_EMP_INTERNAL_EXPERIENCE EXP 
        LEFT JOIN NL_ORG_MINISTRIES MIN ON EXP.MIN_CODE = MIN.MIN_CODE
        LEFT JOIN NL_ORG_CATS CAT ON CAT.CAT_CODE = MIN.CAT_CODE
        LEFT JOIN NL_JOB_JOBS JOB ON JOB.JOB_CODE = EXP.JOB_CODE 
        LEFT JOIN HR_SAL_ELEMENT_GUIDES GUID ON GUID.ELMGUIDE_CODE = EXP.ELMGUIDE_CODE
        LEFT JOIN HR_SAL_ELEMENT_GUIDES GUID2 ON GUID2.ELMGUIDE_CODE = GUID.ELMGUIDE_CODE_DEGREE
        LEFT JOIN HR_SAL_ELEMENT_GUIDES GUID3 ON GUID3.ELMGUIDE_CODE = GUID2.ELMGUIDE_CODE_DEGREE
        LEFT JOIN NL_ORG_WORK_CENTERS CENTER ON CENTER.WRK_CODE = EXP.WRK_CODE
        LEFT JOIN HR_EMP_TRX_TYPES TRX_TYPE ON TRX_TYPE.TRXTYPE_CODE = EXP.TRXTYPE_CODE
        LEFT JOIN HR_BGT_TYPES BGT_TYPE ON BGT_TYPE.TYPE_CODE = EXP.BGTTYPE_CODE
        LEFT JOIN TEMP_HR_BGT_PROGRAMS BGT_PROG ON BGT_PROG.PRG_CODE = EXP.BGTPRG_CODE
        
        WHERE EXP.CIVIL_ID = '224050300151' */
        IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)empInternalExperienceDTO1;

        try {
            StringBuffer stringQuery = new StringBuffer(" select EXP.SERIAL , EXP.CIVIL_ID , GET_NAME(EXP.CIVIL_ID) , ");
            stringQuery.append(" EXP.MIN_CODE , MIN.MIN_NAME , CAT.CAT_CODE , CAT.CAT_NAME , EXP.ACTION_DATE , ");
            stringQuery.append(" EXP.JOB_CODE , JOB.JOB_NAME , EXP.PIS_JOB_CODE , EXP.ELMGUIDE_CODE , " );
            stringQuery.append(" GUID3.EMLGUIDE_NAME || ' - ' || GUID2.EMLGUIDE_NAME || ' - ' || GUID.EMLGUIDE_NAME AS ELMGUIDE_NAME ,  ");
            stringQuery.append(" EXP.WRK_CODE , CENTER.WRK_NAME , EXP.PIS_WRK_CODE , EXP.JOB_DETAIL , EXP.TRXTYPE_CODE , TRX_TYPE.TRXTYPE_NAME , EXP.REV_FLAG ,  ");
            stringQuery.append(" EXP.BGTTYPE_CODE , BGT_TYPE.TYPE_NAME , EXP.BGTPRG_CODE , BGT_PROG.PRG_NAME , EXP.RECORD_SOURCE_FLAG ");
            
            stringQuery.append(" ,EXP.AOE_AUDIT_BY , EXP.AOE_AUDIT_DATE , EXP.AOE_AUDIT_FLAG ");
            stringQuery.append(" ,EXP.AOE_APPROVED_BY , EXP.AOE_APPROVED_DATE , EXP.AOE_APPROVED_FLAG ");
            
            stringQuery.append(" FROM HR_EMP_INTERNAL_EXPERIENCE EXP ");
            stringQuery.append(" LEFT JOIN NL_ORG_MINISTRIES MIN ON EXP.MIN_CODE = MIN.MIN_CODE ");
            stringQuery.append(" LEFT JOIN NL_ORG_CATS CAT ON CAT.CAT_CODE = MIN.CAT_CODE ");
            stringQuery.append(" LEFT JOIN NL_JOB_JOBS JOB ON JOB.JOB_CODE = EXP.JOB_CODE  ");
            stringQuery.append(" LEFT JOIN HR_SAL_ELEMENT_GUIDES GUID ON GUID.ELMGUIDE_CODE = EXP.ELMGUIDE_CODE ");
            stringQuery.append(" LEFT JOIN HR_SAL_ELEMENT_GUIDES GUID2 ON GUID2.ELMGUIDE_CODE = GUID.ELMGUIDE_CODE_DEGREE ");
            stringQuery.append(" LEFT JOIN HR_SAL_ELEMENT_GUIDES GUID3 ON GUID3.ELMGUIDE_CODE = GUID2.ELMGUIDE_CODE_DEGREE ");
            stringQuery.append(" LEFT JOIN NL_ORG_WORK_CENTERS CENTER ON (CENTER.WRK_CODE = EXP.WRK_CODE AND CENTER.MIN_CODE = EXP.MIN_CODE ) ");
            stringQuery.append(" LEFT JOIN HR_EMP_TRX_TYPES TRX_TYPE ON TRX_TYPE.TRXTYPE_CODE = EXP.TRXTYPE_CODE ");
            stringQuery.append(" LEFT JOIN HR_BGT_TYPES BGT_TYPE ON BGT_TYPE.TYPE_CODE = EXP.BGTTYPE_CODE ");
            stringQuery.append(" LEFT JOIN HR_BGT_PROGRAMS BGT_PROG ON BGT_PROG.PRG_CODE = EXP.BGTPRG_CODE ");
            stringQuery.append(" WHERE 1 = 1 ");
            if(empInternalExperienceDTO.getCivilId() != null){
                stringQuery.append(" AND EXP.CIVIL_ID = ' "+empInternalExperienceDTO.getCivilId()+"'");
            }
            System.out.println("::::  getAllBySearchCriteria >>>  stringQuery = " + stringQuery.toString());
            Query query = EM().createNativeQuery(stringQuery.toString());
            query.setHint("toplink.refresh", "true");            
            List<Vector> list = query.getResultList();

            if (list == null || list.size() == 0)
                throw new ItemNotFoundException();

            ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
            IEmpInternalExperienceDTO empIntExpDTO = null;
            for (Vector row : list) {
                empIntExpDTO = new EmpInternalExperienceDTO();
                Long serial = (row.get(0) != null) ? ((BigDecimal)row.get(0)).longValue() : null;
                empIntExpDTO.setCode(EmpEntityKeyFactory.createEmpInternalExperienceEntityKey(serial) );
                Long civilId = (row.get(1) != null) ? ((BigDecimal)row.get(1)).longValue() : null;
                empIntExpDTO.setCivilId(civilId);
                Long minCode = (row.get(3) != null) ? ((BigDecimal)row.get(3)).longValue() : null;
                String minName = (row.get(4) != null) ? ((String)row.get(4)) : null;
                Long catCode = (row.get(5) != null) ? ((BigDecimal)row.get(5)).longValue() : null;
                String catName = (row.get(6) != null) ? ((String)row.get(6)) : null;
                IMinistriesDTO  minDTO = new MinistriesDTO();
                if(minCode != null){
                    IMinistriesEntityKey minEk = OrgEntityKeyFactory.createMinistriesEntityKey(minCode); 
                    minDTO.setCode(minEk);
                    minDTO.setName(( (catName == null) ? "" : catName )+(( minName == null)? "" : " - " +minName ) );
                    if(catCode != null){
                        ICatsDTO catDTO = new CatsDTO();
                        IOrgCatsEntityKey catEk = OrgEntityKeyFactory.createOrgCatsEntityKey(catCode); 
                        catDTO.setCode(catEk);
                        catDTO.setName(catName);
                        minDTO.setCatsDTO(catDTO);
                    }
                }
                empIntExpDTO.setMinDTO(minDTO);
                Timestamp acitonDate = (row.get(7) != null) ? ((Timestamp)row.get(7)) : null;
                empIntExpDTO.setActionDate(acitonDate);

                String jobCode = (row.get(8) != null) ? ((String)row.get(8)) : null;
                String jobName = (row.get(9) != null) ? ((String)row.get(9)) : null;
                IJobsDTO jobDTO = new JobsDTO();
                if(jobCode != null){
                    IJobsEntityKey jobEk = JobEntityKeyFactory.createJobsEntityKey(jobCode); 
                    jobDTO.setCode(jobEk);
                    jobDTO.setName(jobName);
                }
                empIntExpDTO.setJobsDTO(jobDTO);
                String pisJobCode = (row.get(10) != null) ? ((String)row.get(10)) : null;
                empIntExpDTO.setPisJobCode(pisJobCode);
                Long elmGuideCode = (row.get(11) != null) ? ((BigDecimal)row.get(11)).longValue() : null;
                String elmGuideName = (row.get(12) != null) ? ((String)row.get(12)) : null;
                ISalElementGuidesDTO salElementGuidesDTO = new SalElementGuidesDTO();
                if(elmGuideCode != null){
                    ISalElementGuidesEntityKey salEk = SalEntityKeyFactory.createSalElementGuidesEntityKey(elmGuideCode); 
                    salElementGuidesDTO.setCode(salEk);
                    salElementGuidesDTO.setName(elmGuideName);
                }
                empIntExpDTO.setSalElementGuidesDTO(salElementGuidesDTO);
//                Float elmGuideValue = (row.get(4) != null) ? ((BigDecimal)row.get(4)).floatValue() : null;
//                String elmGuideComment = (row.get(5) != null) ? ((String)row.get(5)) : null;
//                dto =
//            (SalElementGuidesDTO)SalDTOFactory.createSalElementGuidesDTO(elmGuideCode, elmGuideName, fromDate, untilDate,
//                                                             elmGuideValue, elmGuideComment);
                String wrkCode = (row.get(13) != null) ? ((String)row.get(13)) : null;
                String wrkName = (row.get(14) != null) ? ((String)row.get(14)) : null;
                IWorkCentersDTO workCentersDTO = new WorkCentersDTO();
                if(minCode != null && wrkCode != null){
                    IWorkCentersEntityKey wrkEk = OrgEntityKeyFactory.createWorkCentersEntityKey(minCode, wrkCode); 
                    workCentersDTO.setCode(wrkEk);
                    workCentersDTO.setName(wrkName);
                }
                empIntExpDTO.setWorkCenterDTO(workCentersDTO);
                String pisWorkCode = (row.get(15) != null) ? ((String)row.get(15)) : null;
                empIntExpDTO.setPisWrkCode(pisWorkCode);
                String jobDetail = (row.get(16) != null) ? ((String)row.get(16)) : null;
                empIntExpDTO.setJobDetail(jobDetail);

                Long trxTypeCode = (row.get(17) != null) ? ((BigDecimal)row.get(17)).longValue() : null;
                String trxTypeName = (row.get(18) != null) ? ((String)row.get(18)) : null;
                ITrxTypesDTO trxTypeDTO = new TrxTypesDTO();
                if(trxTypeCode != null){
                    ITrxTypesEntityKey trxTypeEk = EmpEntityKeyFactory.createTrxTypesEntityKey(trxTypeCode); 
                    trxTypeDTO.setCode(trxTypeEk);
                    trxTypeDTO.setName(trxTypeName);
                }
                empIntExpDTO.setTrxTypesDTO(trxTypeDTO);

                String bgtProgCode = (row.get(22) != null) ? ((String)row.get(22)) : null;
                String bgtProgName = (row.get(23) != null) ? ((String)row.get(23)) : null;
                IBgtProgramsDTO bgtProgramsDTO = new BgtProgramsDTO();
                if(bgtProgCode != null){
                    IBgtProgramsEntityKey bgtProgEk = BgtEntityKeyFactory.createBgtProgramsEntityKey(bgtProgCode); 
                    bgtProgramsDTO.setCode(bgtProgEk);
                    bgtProgramsDTO.setName(bgtProgName);
                }
                empIntExpDTO.setBgtProgramsDTO(bgtProgramsDTO);
                
                
                if(row.get(25) != null){
                        empIntExpDTO.setAoeAuditBy((String)row.get(25));
                    }
                if(row.get(26) != null){
                        empIntExpDTO.setAoeAuditDate(new Date(((Timestamp)row.get(26)).getTime()));
                    }
                if(row.get(27) != null){
                        empIntExpDTO.setAoeAuditFlag(((BigDecimal)row.get(27)).longValue());
                    }
                if(row.get(28) != null){
                        empIntExpDTO.setAoeApprovedBy((String)row.get(28));
                    }
                if(row.get(29) != null){
                        empIntExpDTO.setAoeApprovedDate(new Date(((Timestamp)row.get(29)).getTime()));
                    }
                if(row.get(30) != null){
                        empIntExpDTO.setAoeApprovedFlag(((BigDecimal)row.get(30)).longValue());
                    }



                if(empIntExpDTO.getAoeApprovedBy()!=null){

                    try {
                        empIntExpDTO.setAoeApprovedBy_name(EmpClientFactory.getEmployeesClient().getUserName(Long.valueOf(empIntExpDTO.getAoeApprovedBy())));
                    } catch (DataBaseException e) {
                    } catch (SharedApplicationException e) {
                    }
                }
                
                if(empIntExpDTO.getAoeAuditBy()!=null){

                    try {
                        empIntExpDTO.setAoeAuditBy_name(EmpClientFactory.getEmployeesClient().getUserName(Long.valueOf(empIntExpDTO.getAoeAuditBy())));
                    } catch (DataBaseException e) {
                    } catch (SharedApplicationException e) {
                    }
                }

                arrayList.add(empIntExpDTO);
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

    //==============================Start CSC-18251==========================//

    public Boolean aoeReviewInternalExperiancesByMin(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                           SharedApplicationException {
        try {
            if (empInternalExperienceDTOList_ != null && !empInternalExperienceDTOList_.isEmpty()) {
                for (IBasicDTO iBasicDTO : empInternalExperienceDTOList_) {
                    IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)iBasicDTO;
                    EmpInternalExperienceEntity empInternalExperienceEntity =
                        EM().find(EmpInternalExperienceEntity.class, empInternalExperienceDTO.getCode());


                    //=====Start CSC-18251=============//
                    empInternalExperienceEntity.setAoeAuditBy(CSCSecurityInfoHelper.getUserCodeFromRequest().toString());
                    empInternalExperienceEntity.setAoeAuditDate(SharedUtils.getCurrentDate());
                    empInternalExperienceEntity.setAoeAuditFlag(1L);
                    //=====End CSC-18251===============//

                    doUpdate(empInternalExperienceEntity);
                }
            }
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

    public Boolean aoeApproveInternalExperiancesByDiwaan(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                               SharedApplicationException {
        try {
            if (empInternalExperienceDTOList_ != null && !empInternalExperienceDTOList_.isEmpty()) {
                for (IBasicDTO iBasicDTO : empInternalExperienceDTOList_) {
                    IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)iBasicDTO;
                    EmpInternalExperienceEntity empInternalExperienceEntity =
                        EM().find(EmpInternalExperienceEntity.class, empInternalExperienceDTO.getCode());


                    //=====Start CSC-18251=============//
                    empInternalExperienceEntity.setAoeApprovedBy(CSCSecurityInfoHelper.getUserCodeFromRequest().toString());
                    empInternalExperienceEntity.setAoeApprovedDate(SharedUtils.getCurrentDate());
                    empInternalExperienceEntity.setAoeApprovedFlag(1L);


                    doUpdate(empInternalExperienceEntity);
                }
            }
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

    public Boolean aoeCancelReviewInternalExperiancesByMin(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                                 SharedApplicationException {
        try {
            if (empInternalExperienceDTOList_ != null && !empInternalExperienceDTOList_.isEmpty()) {
                for (IBasicDTO iBasicDTO : empInternalExperienceDTOList_) {


                    IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)iBasicDTO;
                    EmpInternalExperienceEntity empInternalExperienceEntity =
                        EM().find(EmpInternalExperienceEntity.class, empInternalExperienceDTO.getCode());


                    //=====Start CSC-18251=============//
                    empInternalExperienceEntity.setAoeAuditBy(null);
                    empInternalExperienceEntity.setAoeAuditDate(null);
                    empInternalExperienceEntity.setAoeAuditFlag(0L);
                    //=====End CSC-18251===============//

                    doUpdate(empInternalExperienceEntity);
                }
            }
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

    public Boolean aoeCancelApproveInternalExperiancesByDiwaan(List<IBasicDTO> empInternalExperienceDTOList_) throws DataBaseException,
                                                                                                                     SharedApplicationException {
        try {
            if (empInternalExperienceDTOList_ != null && !empInternalExperienceDTOList_.isEmpty()) {
                for (IBasicDTO iBasicDTO : empInternalExperienceDTOList_) {
                    IEmpInternalExperienceDTO empInternalExperienceDTO =
                        (IEmpInternalExperienceDTO)iBasicDTO;
                    EmpInternalExperienceEntity empInternalExperienceEntity =
                        EM().find(EmpInternalExperienceEntity.class, empInternalExperienceDTO.getCode());


                    //=====Start CSC-18251=============//
                    empInternalExperienceEntity.setAoeApprovedBy(null);
                    empInternalExperienceEntity.setAoeApprovedDate(null);
                    empInternalExperienceEntity.setAoeApprovedFlag(0L);


                    doUpdate(empInternalExperienceEntity);
                }
            }
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
    //==============================End CSC-18251==========================//



    public IEmpInternalExperienceDTO addForAOE (IBasicDTO empInternalExperienceDTO1) throws DataBaseException, SharedApplicationException {
        try{

        EmpInternalExperienceEntity empInternalExperienceEntity = new EmpInternalExperienceEntity();

        IEmpInternalExperienceDTO empInternalExperienceDTO = (IEmpInternalExperienceDTO)empInternalExperienceDTO1;

       // empInternalExperienceEntity.setSerial(((IEmpInternalExperienceEntityKey)empInternalExperienceDTO.getCode()).getSerial());
            Long serial=findMaxId();
            empInternalExperienceEntity.setSerial(serial);
            
        empInternalExperienceEntity.setCivilId(empInternalExperienceDTO.getCivilId());
        empInternalExperienceEntity.setActionDate(empInternalExperienceDTO.getActionDate());
        empInternalExperienceEntity.setPisJobCode(empInternalExperienceDTO.getPisJobCode());
        empInternalExperienceEntity.setPisWrkCode(empInternalExperienceDTO.getPisWrkCode());
        empInternalExperienceEntity.setJobDetail(empInternalExperienceDTO.getJobDetail());
        empInternalExperienceEntity.setRevFlag(empInternalExperienceDTO.getRevFlag());
        empInternalExperienceEntity.setRecordSourceFlag(empInternalExperienceDTO.getRecordSourceFlag());
        empInternalExperienceEntity.setAuditStatus(empInternalExperienceDTO.getAuditStatus());
        empInternalExperienceEntity.setTabrecSerial(empInternalExperienceDTO.getTabrecSerial());
        empInternalExperienceEntity.setCreatedBy(empInternalExperienceDTO.getCreatedBy());
        empInternalExperienceEntity.setCreatedDate(empInternalExperienceDTO.getCreatedDate());
        empInternalExperienceEntity.setLastUpdatedBy(empInternalExperienceDTO.getLastUpdatedBy());
        empInternalExperienceEntity.setLastUpdatedDate(empInternalExperienceDTO.getLastUpdatedDate());

        if (empInternalExperienceDTO.getWorkCenterDTO() != null) {
            IWorkCentersEntityKey wrkEk = (IWorkCentersEntityKey)empInternalExperienceDTO.getWorkCenterDTO().getCode();
          //  WorkCentersEntity workCentersEntity =  em.find(WorkCentersEntity.class, (IWorkCentersEntityKey)(empInternalExperienceDTO.getWorkCenterDTO().getCode()));
               if(wrkEk.getWrkCode()!= null){
            empInternalExperienceEntity.setWrkCode(wrkEk.getWrkCode());
             }
        }

            if (empInternalExperienceDTO.getMinDTO() != null) {

                MinistriesEntityKey minEnKey = (MinistriesEntityKey)empInternalExperienceDTO.getMinDTO().getCode();

                if (minEnKey.getMinCode() != null)
                    empInternalExperienceEntity.setMinCode(minEnKey.getMinCode());
            }

            if (empInternalExperienceDTO.getJobsDTO() != null) {
            IJobsEntityKey jobEk = (IJobsEntityKey)empInternalExperienceDTO.getJobsDTO().getCode();
            //JobsEntity jobsEntity = em.find(JobsEntity.class, (IJobsEntityKey)(empInternalExperienceDTO.getJobsDTO().getCode()));
            if (jobEk != null)
            empInternalExperienceEntity.setJobCode(jobEk.getJobCode());
        }
        if (empInternalExperienceDTO.getSalElementGuidesDTO() != null) {
         //   SalElementGuidesEntity salElementGuidesEntity = em.find(SalElementGuidesEntity.class, (ISalElementGuidesEntityKey)empInternalExperienceDTO.getSalElementGuidesDTO().getCode());
            ISalElementGuidesEntityKey salEk = (ISalElementGuidesEntityKey)empInternalExperienceDTO.getSalElementGuidesDTO().getCode();
            if (salEk != null)
            empInternalExperienceEntity.setElmguideCode(salEk.getElmguideCode());
        }
        if (empInternalExperienceDTO.getTrxTypesDTO() != null) {
            TrxTypesEntity trxTypesEntity =
                EM().find(TrxTypesEntity.class, (ITrxTypesEntityKey)(empInternalExperienceDTO.getTrxTypesDTO().getCode()));
            if (trxTypesEntity != null)
            empInternalExperienceEntity.setTrxTypesEntity(trxTypesEntity);
        }
        if (empInternalExperienceDTO.getBgtProgramsDTO() != null) {
           // BgtProgramsEntity bgtProgramsEntity = em.find(BgtProgramsEntity.class, (IBgtProgramsEntityKey)(empInternalExperienceDTO.getBgtProgramsDTO().getCode()));
           IBgtProgramsEntityKey pEk = (IBgtProgramsEntityKey)empInternalExperienceDTO.getBgtProgramsDTO().getCode();
            if (pEk != null)
            empInternalExperienceEntity.setBgtprgCode(pEk.getPrgCode());
        }
        if (empInternalExperienceDTO.getBgtTypesDTO() != null) {
           // BgtTypesEntity bgtTypesEntity = em.find(BgtTypesEntity.class, (IBgtTypesEntityKey)(empInternalExperienceDTO.getBgtTypesDTO().getCode()));
           IBgtTypesEntityKey typeEk = (IBgtTypesEntityKey)empInternalExperienceDTO.getBgtTypesDTO().getCode();
            if (typeEk != null)
            empInternalExperienceEntity.setBgttypeCode(typeEk.getTypeCode());
        }
        //this.persistEntity(empInternalExperienceEntity);
            
        doAdd(empInternalExperienceEntity);
        // Set PK after creation
            
        empInternalExperienceDTO.setCode(EmpEntityKeyFactory.createEmpInternalExperienceEntityKey(serial));
            
        return empInternalExperienceDTO;
            
        }catch (Exception e) {
        e = wrapIntoDataBaseException(e);
        if (e instanceof DataBaseException) {
            throw (DataBaseException)e;
        } else {
            throw (SharedApplicationException)e;
        }
    }

    }
    
    /**<code>select o from EmpInternalExperienceEntity o</code>.
     * @return List
     */
    public List<IEmpInternalExperienceDTO> getAllByCivilId(Long civilId) throws DataBaseException, SharedApplicationException {
        
        try{
        ArrayList<IEmpInternalExperienceDTO> arrayList = new ArrayList<IEmpInternalExperienceDTO>();
        List<EmpInternalExperienceEntity> list =
            EM().createNamedQuery("EmpInternalExperienceEntity.getAllByCivilId").setParameter("civilId",
                                                                                                          civilId).getResultList();
            
            if (list == null || list.size() <= 0) {
                throw new NoResultException();
            }
        for (EmpInternalExperienceEntity empInternalExperience : list) {
            arrayList.add(EmpDTOFactory.createEmpInternalExperienceDTO(empInternalExperience));
        }
        return arrayList;
            
            
        }catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
    }
        
    }
}
