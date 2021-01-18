package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.EmpExtraDataTypesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpExtraDataTypesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.querybuilder.QueryConditionBuilder;

import java.util.ArrayList;
import java.util.List;


public class EmpExtraDataTypesDAO extends EmpBaseDAO {
    public EmpExtraDataTypesDAO() {
        super();
    }
    @Override
    protected BaseDAO newInstance() {
        return new EmpExtraDataTypesDAO() ;
    }
    
    @Override
    public List<IBasicDTO> getAll() throws  DataBaseException, SharedApplicationException {
        try{
            ArrayList arrayList = new ArrayList();
            List<EmpExtraDataTypesEntity> list = EM().createNamedQuery("EmpExtraDataTypesEntity.findAll").getResultList();
            
        for (EmpExtraDataTypesEntity empExtraDataTypes : list) {
            arrayList.add((IBasicDTO)EmpDTOFactory.createEmpExtraDataTypesDTO(empExtraDataTypes));
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
     * Get the MaxId of AbilitiesEntity
     * <br>
     * @return Object
     */
    public Long findNewId() throws DataBaseException,SharedApplicationException {
        try{
        Long id = (Long)EM().createNamedQuery("EmpExtraDataTypesEntity.findNewId").getSingleResult();
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
     * Create a New EmpExtraDataTypesEntity
     * @param empExtraDataTypesDTO
     * @return EmpExtraDataTypesDTO
     */
    @Override
    public EmpExtraDataTypesDTO add(IBasicDTO empExtraDataTypesDTO1) throws DataBaseException, SharedApplicationException  {
        try{
        EmpExtraDataTypesEntity empExtraDataTypesEntity = new EmpExtraDataTypesEntity();
        EmpExtraDataTypesDTO empExtraDataTypesDTO = (EmpExtraDataTypesDTO)empExtraDataTypesDTO1;
        empExtraDataTypesEntity.setExtdattypeCode(((IEmpExtraDataTypesEntityKey)empExtraDataTypesDTO.getCode()).getExtdattypeCode());
        empExtraDataTypesEntity.setExtdattypeName(empExtraDataTypesDTO.getExtdattypeName());
        empExtraDataTypesEntity.setStatus(empExtraDataTypesDTO.getStatus());
        empExtraDataTypesEntity.setCreatedBy(empExtraDataTypesDTO.getCreatedBy());
        empExtraDataTypesEntity.setCreatedDate(empExtraDataTypesDTO.getCreatedDate());
        empExtraDataTypesEntity.setAuditStatus(empExtraDataTypesDTO.getAuditStatus());
        empExtraDataTypesEntity.setTabrecSerial(empExtraDataTypesDTO.getTabrecSerial());
        doAdd(empExtraDataTypesEntity);
        // Set PK after creation
        return empExtraDataTypesDTO;
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
     * Update an Existing EmpExtraDataTypesEntity
     * @param empExtraDataTypesDTO
     * @return Boolean
     */
    @Override
    public Boolean update(IBasicDTO empExtraDataTypesDTO1) throws DataBaseException, SharedApplicationException{
        try{
        EmpExtraDataTypesDTO empExtraDataTypesDTO = (EmpExtraDataTypesDTO)empExtraDataTypesDTO1;

        EmpExtraDataTypesEntity empExtraDataTypesEntity =
            EM().find(EmpExtraDataTypesEntity.class, (IEmpExtraDataTypesEntityKey)empExtraDataTypesDTO.getCode());

        empExtraDataTypesEntity.setExtdattypeCode(((IEmpExtraDataTypesEntityKey)empExtraDataTypesDTO.getCode()).getExtdattypeCode());
        empExtraDataTypesEntity.setExtdattypeName(empExtraDataTypesDTO.getExtdattypeName());
        empExtraDataTypesEntity.setStatus(empExtraDataTypesDTO.getStatus());
        empExtraDataTypesEntity.setCreatedBy(empExtraDataTypesDTO.getCreatedBy());
        empExtraDataTypesEntity.setCreatedDate(empExtraDataTypesDTO.getCreatedDate());
        empExtraDataTypesEntity.setLastUpdatedBy(empExtraDataTypesDTO.getLastUpdatedBy());
        empExtraDataTypesEntity.setLastUpdatedDate(empExtraDataTypesDTO.getLastUpdatedDate());
        empExtraDataTypesEntity.setAuditStatus(empExtraDataTypesDTO.getAuditStatus());
        empExtraDataTypesEntity.setTabrecSerial(empExtraDataTypesDTO.getTabrecSerial());

doUpdate(empExtraDataTypesEntity);
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
     * Remove an Existing EmpExtraDataTypesEntity
     * @param empExtraDataTypesDTO
     * @return Boolean
     */
    @Override
    public Boolean remove(IBasicDTO empExtraDataTypesDTO1) throws DataBaseException, SharedApplicationException{
        try{
        EmpExtraDataTypesDTO empExtraDataTypesDTO = (EmpExtraDataTypesDTO)empExtraDataTypesDTO1;

        EmpExtraDataTypesEntity empExtraDataTypesEntity =
            EM().find(EmpExtraDataTypesEntity.class, (IEmpExtraDataTypesEntityKey)empExtraDataTypesDTO.getCode());

        if (empExtraDataTypesEntity == null) {
            throw new ItemNotFoundException();
        }
      //  EM().remove(empExtraDataTypesEntity);
            doRemove(empExtraDataTypesEntity);
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
     * Get EmpExtraDataTypesEntity By Primary Key
     * @param id
     * @return EmpExtraDataTypesDTO
     */
    public EmpExtraDataTypesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException{
        try{
        IEmpExtraDataTypesEntityKey id = (IEmpExtraDataTypesEntityKey)id1;

        EmpExtraDataTypesEntity empExtraDataTypesEntity =EM().find(EmpExtraDataTypesEntity.class, id);
        if (empExtraDataTypesEntity == null) {
            throw new ItemNotFoundException();
        }
        EmpExtraDataTypesDTO empExtraDataTypesDTO = new EmpExtraDataTypesDTO(empExtraDataTypesEntity);
        return empExtraDataTypesDTO;
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
     * Search for EmpExtraDataTypesEntity
     * <br>
     * @return List
     */
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException{
        try{
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

    
    
    
    public List<IBasicDTO> getNotLinkedToMin(Long minCode, Long status , Long searchType , String searchValue) throws DataBaseException, SharedApplicationException {
        ArrayList<IBasicDTO> arrayList = new ArrayList<IBasicDTO>();
        StringBuilder sb = new StringBuilder("");
        sb.append(" select * from HR_EMP_EXTRA_DATA_TYPES typ where TYP.EXTDATTYPE_CODE not in ");
        sb.append("  ( select mintyp.EXTDATTYPE_CODE from HR_EMP_MIN_EDTYPES mintyp where MINTYP.MIN_CODE = ");
        sb.append(minCode);
        sb.append( ")" );
        if (status != null) {
          sb.append(" AND typ.STATUS = " + status );
        }
        
        if(searchType!=null){
            if(searchType.equals(0L))
            sb.append(" AND typ.EXTDATTYPE_CODE = ").append(searchValue);
            else if(searchType.equals(1L))
             sb.append(" AND ").append(QueryConditionBuilder.getNativeSqlSimilarCharsCondition("typ.EXTDATTYPE_NAME",searchValue));   
            }
        System.out.println("getAllNotLinkedToMin -> " + sb.toString());
        List<EmpExtraDataTypesEntity> list =
            EM().createNativeQuery(sb.toString(), EmpExtraDataTypesEntity.class).getResultList();
        for (EmpExtraDataTypesEntity empExtraDatatypes : list) {
            arrayList.add(EmpDTOFactory.createEmpExtraDataTypesDTO(empExtraDatatypes));
        }
        return arrayList;
    }
    
}
