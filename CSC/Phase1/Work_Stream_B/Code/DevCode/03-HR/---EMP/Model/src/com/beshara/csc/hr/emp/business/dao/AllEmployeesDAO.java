package com.beshara.csc.hr.emp.business.dao;

import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAllEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.AllEmployeesEntity;

import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.IAllEmployeesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllEmployeesDAO extends EmpBaseDAO {
    public AllEmployeesDAO() {
        super();
    }

    @Override
    protected BaseDAO newInstance() {
        return new AllEmployeesDAO();
    }
    
    /**<code>select o from AllEmployeesEntity IoI<I/IcIoIdIeI>I.I
     * @return List
     */
    public List<IAllEmployeesDTO> getAll() throws DataBaseException, SharedApplicationException {
        try{
            ArrayList arrayList = new ArrayList();
            List<AllEmployeesEntity> list = EM().createNamedQuery("AllEmployeesEntity.findAll").getResultList();
            for (AllEmployeesEntity allEmployees : list) {
                arrayList.add(EmpDTOFactory.createAllEmployeesDTO(allEmployees));
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
    
    /**
     * Get the MaxId of AbilitiesEntity * <br> * @return Object
     */
    public Long findNewId() throws DataBaseException, SharedApplicationException {
        
        try{
            Long id = (Long)EM().createNamedQuery("AllEmployeesEntity.findNewId").getSingleResult();
            if (id == null) {
                return Long.valueOf(1);
            } else {
                return id+1L;
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
    
    public IAllEmployeesDTO add(IBasicDTO allEmployeesDTO1) throws DataBaseException, SharedApplicationException {
        
        try{
            AllEmployeesEntity allEmployeesEntity = new AllEmployeesEntity();
            IAllEmployeesDTO allEmployeesDTO = (IAllEmployeesDTO)allEmployeesDTO1;
            
            Long empCode = findNewId();
            allEmployeesEntity.setCivilId(empCode);
            allEmployeesDTO.setCode(EmpEntityKeyFactory.createAllEmployeesEntityKey(empCode,null));
            
            allEmployeesEntity.setTrxDatetime(((IAllEmployeesEntityKey)allEmployeesDTO.getCode()).getTrxDatetime());
            allEmployeesEntity.setMinCode(allEmployeesDTO.getMinCode());
            allEmployeesEntity.setWrkCode(allEmployeesDTO.getWrkCode());
            allEmployeesEntity.setMinistryFileNo(allEmployeesDTO.getMinistryFileNo());
            allEmployeesEntity.setCscFileNo(allEmployeesDTO.getCscFileNo());
            allEmployeesEntity.setHireDate(allEmployeesDTO.getHireDate());
            allEmployeesEntity.setStartWorkingDate(allEmployeesDTO.getStartWorkingDate());
            allEmployeesEntity.setEndJobDate(allEmployeesDTO.getEndJobDate());
            allEmployeesEntity.setHirstatusCode(allEmployeesDTO.getHirstatusCode());
            allEmployeesEntity.setHirtypeCode(allEmployeesDTO.getHirtypeCode());
            allEmployeesEntity.setDateOfNextRaise(allEmployeesDTO.getDateOfNextRaise());
            allEmployeesEntity.setTechJobCode(allEmployeesDTO.getTechJobCode());
            allEmployeesEntity.setJobCode(allEmployeesDTO.getJobCode());
            allEmployeesEntity.setBankCode(allEmployeesDTO.getBankCode());
            allEmployeesEntity.setBnkbranchCode(allEmployeesDTO.getBnkbranchCode());
            allEmployeesEntity.setAccountNo(allEmployeesDTO.getAccountNo());
            allEmployeesEntity.setActiveFlag(allEmployeesDTO.getActiveFlag());
            allEmployeesEntity.setCreatedBy(allEmployeesDTO.getCreatedBy());
            allEmployeesEntity.setCreatedDate(allEmployeesDTO.getCreatedDate());
            allEmployeesEntity.setAuditStatus(allEmployeesDTO.getAuditStatus());
            allEmployeesEntity.setTabrecSerial(allEmployeesDTO.getTabrecSerial());
            this.doAdd(allEmployeesEntity);
            // Set PK after creation
            return allEmployeesDTO;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    }
    
    public Boolean update(IBasicDTO allEmployeesDTO1) throws DataBaseException, SharedApplicationException {
        
        try{
            IAllEmployeesDTO allEmployeesDTO = (IAllEmployeesDTO)allEmployeesDTO1;
            AllEmployeesEntity allEmployeesEntity =
                EM().find(AllEmployeesEntity.class, (IAllEmployeesEntityKey)allEmployeesDTO.getCode());
            allEmployeesEntity.setCivilId(((IAllEmployeesEntityKey)allEmployeesDTO.getCode()).getCivilId());
            allEmployeesEntity.setTrxDatetime(((IAllEmployeesEntityKey)allEmployeesDTO.getCode()).getTrxDatetime());
            allEmployeesEntity.setMinCode(allEmployeesDTO.getMinCode());
            allEmployeesEntity.setWrkCode(allEmployeesDTO.getWrkCode());
            allEmployeesEntity.setMinistryFileNo(allEmployeesDTO.getMinistryFileNo());
            allEmployeesEntity.setCscFileNo(allEmployeesDTO.getCscFileNo());
            allEmployeesEntity.setHireDate(allEmployeesDTO.getHireDate());
            allEmployeesEntity.setStartWorkingDate(allEmployeesDTO.getStartWorkingDate());
            allEmployeesEntity.setEndJobDate(allEmployeesDTO.getEndJobDate());
            allEmployeesEntity.setHirstatusCode(allEmployeesDTO.getHirstatusCode());
            allEmployeesEntity.setHirtypeCode(allEmployeesDTO.getHirtypeCode());
            allEmployeesEntity.setDateOfNextRaise(allEmployeesDTO.getDateOfNextRaise());
            allEmployeesEntity.setTechJobCode(allEmployeesDTO.getTechJobCode());
            allEmployeesEntity.setJobCode(allEmployeesDTO.getJobCode());
            allEmployeesEntity.setBankCode(allEmployeesDTO.getBankCode());
            allEmployeesEntity.setBnkbranchCode(allEmployeesDTO.getBnkbranchCode());
            allEmployeesEntity.setAccountNo(allEmployeesDTO.getAccountNo());
            allEmployeesEntity.setActiveFlag(allEmployeesDTO.getActiveFlag());
            allEmployeesEntity.setCreatedBy(allEmployeesDTO.getCreatedBy());
            allEmployeesEntity.setCreatedDate(allEmployeesDTO.getCreatedDate());
            allEmployeesEntity.setLastUpdatedBy(allEmployeesDTO.getLastUpdatedBy());
            allEmployeesEntity.setLastUpdatedDate(allEmployeesDTO.getLastUpdatedDate());
            allEmployeesEntity.setAuditStatus(allEmployeesDTO.getAuditStatus());
            allEmployeesEntity.setTabrecSerial(allEmployeesDTO.getTabrecSerial());
            // updated by A.AGAMY for data audit
            doUpdate(allEmployeesEntity);
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
    public Boolean remove(IBasicDTO allEmployeesDTO1) throws DataBaseException, SharedApplicationException,ItemNotFoundException  {
        try{
            IAllEmployeesDTO allEmployeesDTO = (IAllEmployeesDTO)allEmployeesDTO1;
            AllEmployeesEntity allEmployeesEntity =
                EM().find(AllEmployeesEntity.class, (IAllEmployeesEntityKey)allEmployeesDTO.getCode());
            if (allEmployeesEntity == null) {
                throw new ItemNotFoundException();
            }
            doRemove(allEmployeesEntity);
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
    
    public IAllEmployeesDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException,ItemNotFoundException {
        
        try{
            IAllEmployeesEntityKey id = (IAllEmployeesEntityKey)id1;
            AllEmployeesEntity allEmployeesEntity = EM().find(AllEmployeesEntity.class, id);
            if (allEmployeesEntity == null) {
                throw new ItemNotFoundException();
            }
            IAllEmployeesDTO allEmployeesDTO = EmpDTOFactory.createAllEmployeesDTO(allEmployeesEntity);
            return allEmployeesDTO;
        }catch(Exception e){
                e = wrapIntoDataBaseException(e);
                if(e instanceof DataBaseException ){
                    throw (DataBaseException)e;
                }else {
                    throw (SharedApplicationException)e;
                }
            }
    } 
    public List<IBasicDTO> search(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException,ItemNotFoundException {
        return super.search(basicDTO);
    }    
}
