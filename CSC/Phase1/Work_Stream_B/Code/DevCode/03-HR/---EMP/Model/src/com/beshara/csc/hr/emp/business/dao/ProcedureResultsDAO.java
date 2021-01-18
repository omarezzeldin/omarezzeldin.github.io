package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IProcedureResultsDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IProcedureResultsEntityKey;
import com.beshara.csc.hr.emp.business.entity.ProcedureResultsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;


public class ProcedureResultsDAO extends EmpBaseDAO {
    public ProcedureResultsDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new ProcedureResultsDAO();
    }
    
    public List<IProcedureResultsDTO> getAll() throws DataBaseException, SharedApplicationException {
        
        try{
            ArrayList arrayList = new ArrayList();
            List<ProcedureResultsEntity> list = EM().createNamedQuery("ProcedureResultsEntity.findAll").getResultList();
            for (ProcedureResultsEntity procedureResults : list) {
                arrayList.add(EmpDTOFactory.createProcedureResultsDTO(procedureResults));
            }
            return arrayList;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        try{
            Long id = (Long)EM().createNamedQuery("ProcedureResultsEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id + 1L;
            }
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public IProcedureResultsDTO add(IBasicDTO procedureResultsDTO1) throws DataBaseException, SharedApplicationException {
        
        try{
            ProcedureResultsEntity procedureResultsEntity = new ProcedureResultsEntity();
            IProcedureResultsDTO procedureResultsDTO = (IProcedureResultsDTO)procedureResultsDTO1;
            Long proresultCode = findMaxId();
            
            procedureResultsDTO.setCode(EmpEntityKeyFactory.createProcedureResultsEntityKey(proresultCode));
            
            procedureResultsEntity.setProresultCode(proresultCode);
            procedureResultsEntity.setProresultName(procedureResultsDTO.getName());
            procedureResultsEntity.setCreatedBy(procedureResultsDTO.getCreatedBy());
            procedureResultsEntity.setCreatedDate(procedureResultsDTO.getCreatedDate());
            procedureResultsEntity.setAuditStatus(procedureResultsDTO.getAuditStatus());
            procedureResultsEntity.setTabrecSerial(procedureResultsDTO.getTabrecSerial());
            this.doAdd(procedureResultsEntity);
            // Set PK after creation
            return procedureResultsDTO;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public Boolean update(IBasicDTO procedureResultsDTO1) throws DataBaseException, SharedApplicationException {
        
        try{
            IProcedureResultsDTO procedureResultsDTO = (IProcedureResultsDTO)procedureResultsDTO1;
            ProcedureResultsEntity procedureResultsEntity =
                EM().find(ProcedureResultsEntity.class, (IProcedureResultsEntityKey)procedureResultsDTO.getCode());
            procedureResultsEntity.setProresultCode(((IProcedureResultsEntityKey)procedureResultsDTO.getCode()).getProresultCode());
            procedureResultsEntity.setProresultName(procedureResultsDTO.getName());
            procedureResultsEntity.setCreatedBy(procedureResultsDTO.getCreatedBy());
            procedureResultsEntity.setCreatedDate(procedureResultsDTO.getCreatedDate());
            procedureResultsEntity.setLastUpdatedBy(procedureResultsDTO.getLastUpdatedBy());
            procedureResultsEntity.setLastUpdatedDate(procedureResultsDTO.getLastUpdatedDate());
            procedureResultsEntity.setAuditStatus(procedureResultsDTO.getAuditStatus());
            procedureResultsEntity.setTabrecSerial(procedureResultsDTO.getTabrecSerial());
            //updated by Ismael for dataAudit requirements
            doUpdate(procedureResultsEntity);
            return Boolean.TRUE;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public Boolean remove(IBasicDTO procedureResultsDTO1) throws DataBaseException, SharedApplicationException {
        try{
            IProcedureResultsDTO procedureResultsDTO = (IProcedureResultsDTO)procedureResultsDTO1;
            ProcedureResultsEntity procedureResultsEntity =
                EM().find(ProcedureResultsEntity.class, (IProcedureResultsEntityKey)procedureResultsDTO.getCode());
            if (procedureResultsEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(procedureResultsEntity);
            return Boolean.TRUE;   
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public IProcedureResultsDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
            IProcedureResultsEntityKey id = (IProcedureResultsEntityKey)id1;
            ProcedureResultsEntity procedureResultsEntity = EM().find(ProcedureResultsEntity.class, id);
            if (procedureResultsEntity == null) {
                throw new ItemNotFoundException();
            }
            IProcedureResultsDTO procedureResultsDTO = EmpDTOFactory.createProcedureResultsDTO(procedureResultsEntity);
            return procedureResultsDTO;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        return super.search(basicDTO);
    }
}
