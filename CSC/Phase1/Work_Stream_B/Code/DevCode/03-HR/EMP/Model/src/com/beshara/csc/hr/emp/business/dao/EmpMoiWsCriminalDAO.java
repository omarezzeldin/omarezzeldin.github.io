package com.beshara.csc.hr.emp.business.dao;


import com.beshara.base.dao.BaseDAO;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IEmpMoiWsCriminalDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmpMoiWsCriminalEntity;
import com.beshara.csc.hr.emp.business.entity.IEmpMoiWsCriminalEntityKey;
import com.beshara.csc.hr.emp.business.facade.EmpEntityConverter;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Query;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

public class EmpMoiWsCriminalDAO extends EmpBaseDAO  {

  /**
   * EmpMoiWsCriminalDAO Default Constructor
   */
    public EmpMoiWsCriminalDAO() {
	   super();
    }
    
	
	@Override
    protected BaseDAO newInstance() {
        return new EmpMoiWsCriminalDAO();
    }
	

    /**<code>select o from EmpMoiWsCriminalEntity o</code>.
     * @return List
     */
    public List<IEmpMoiWsCriminalDTO> getAll() throws DataBaseException, SharedApplicationException {
	try{
       ArrayList arrayList = new ArrayList();
        List<EmpMoiWsCriminalEntity> list = EM().createNamedQuery("EmpMoiWsCriminalEntity.findAll").getResultList();
        for (EmpMoiWsCriminalEntity empMoiWsCriminal : list )  {
            arrayList.add(EmpEntityConverter.getEmpMoiWsCriminalDTO(empMoiWsCriminal));
			
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
     * Create a New EmpMoiWsCriminalEntity
     * @param empMoiWsCriminalDTO
     * @return IBasicDTO
     */
    public IBasicDTO add(IBasicDTO empMoiWsCriminalDTO1) throws DataBaseException, SharedApplicationException {
        try{
            EmpMoiWsCriminalEntity  empMoiWsCriminalEntity = new EmpMoiWsCriminalEntity();
            
            IEmpMoiWsCriminalDTO empMoiWsCriminalDTO = (IEmpMoiWsCriminalDTO) empMoiWsCriminalDTO1;
            
            Long serialCode = findMaxId();
empMoiWsCriminalEntity.setCrmWsSerial(serialCode); 
empMoiWsCriminalEntity.setUserCode(empMoiWsCriminalDTO.getUserCode()); 
empMoiWsCriminalEntity.setCivilId(empMoiWsCriminalDTO.getCivilId()); 
empMoiWsCriminalEntity.setCrmstatusCode(empMoiWsCriminalDTO.getCrmstatusCode()); 
empMoiWsCriminalEntity.setLastLoginDate(empMoiWsCriminalDTO.getLastLoginDate()); 

            
            doAdd(empMoiWsCriminalEntity);
			IEmpMoiWsCriminalEntityKey ek = EmpEntityKeyFactory.createEmpMoiWsCriminalEntityKey(empMoiWsCriminalEntity.getCrmWsSerial());
empMoiWsCriminalDTO.setCode( ek );

            // Set PK after creation
            return empMoiWsCriminalDTO;
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
     * Update an Existing EmpMoiWsCriminalEntity
     * @param empMoiWsCriminalDTO
     * @return Boolean
     */
    public Boolean update(IBasicDTO empMoiWsCriminalDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IEmpMoiWsCriminalDTO empMoiWsCriminalDTO = (IEmpMoiWsCriminalDTO) empMoiWsCriminalDTO1;
        
        EmpMoiWsCriminalEntity  empMoiWsCriminalEntity = EM().find(EmpMoiWsCriminalEntity.class, (IEmpMoiWsCriminalEntityKey) empMoiWsCriminalDTO.getCode());
        
        empMoiWsCriminalEntity.setUserCode(empMoiWsCriminalDTO.getUserCode()); 
empMoiWsCriminalEntity.setCivilId(empMoiWsCriminalDTO.getCivilId()); 
empMoiWsCriminalEntity.setCrmstatusCode(empMoiWsCriminalDTO.getCrmstatusCode()); 
empMoiWsCriminalEntity.setLastLoginDate(empMoiWsCriminalDTO.getLastLoginDate()); 

        doUpdate(empMoiWsCriminalEntity);
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
     * Remove an Existing EmpMoiWsCriminalEntity
     * @param empMoiWsCriminalDTO
     * @return Boolean
     */
    public Boolean remove(IBasicDTO empMoiWsCriminalDTO1) throws DataBaseException, SharedApplicationException {
        try{
        IEmpMoiWsCriminalDTO empMoiWsCriminalDTO = (IEmpMoiWsCriminalDTO) empMoiWsCriminalDTO1;
        
        EmpMoiWsCriminalEntity  empMoiWsCriminalEntity = EM().find(EmpMoiWsCriminalEntity.class, (IEmpMoiWsCriminalEntityKey) empMoiWsCriminalDTO.getCode());
        
        if (empMoiWsCriminalEntity == null)  {
             throw new ItemNotFoundException();   
        }
        doRemove(empMoiWsCriminalEntity);
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
     * Get EmpMoiWsCriminalEntity By Primary Key
     * @param id
     * @return EmpMoiWsCriminalDTO
     */    
    public IBasicDTO getById(IEntityKey id1) throws DataBaseException, SharedApplicationException {
        try{
        IEmpMoiWsCriminalEntityKey id = (IEmpMoiWsCriminalEntityKey) id1;
        
        EmpMoiWsCriminalEntity empMoiWsCriminalEntity = EM().find(EmpMoiWsCriminalEntity.class, id);
        if (empMoiWsCriminalEntity == null)  {
             throw new ItemNotFoundException();   
        }
        IEmpMoiWsCriminalDTO empMoiWsCriminalDTO = EmpEntityConverter.getEmpMoiWsCriminalDTO(empMoiWsCriminalEntity);
        return empMoiWsCriminalDTO;
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
     * Get the MaxId of EmpMoiWsCriminalEntity
     * <br>
     * @return Object
     */
       public Long findNewId() throws DataBaseException, SharedApplicationException {
        try {
            Long id = (Long)EM().createNamedQuery("EmpMoiWsCriminalEntity.findNewId").getSingleResult();
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
     * Get all by code equal code * @param code
     * @return list
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> searchByCode(Object code) throws DataBaseException, SharedApplicationException {
        try {
            ArrayList arrayList = new ArrayList();
            List<EmpMoiWsCriminalEntity> list =
                EM().createNamedQuery("EmpMoiWsCriminalEntity.searchByCode").setParameter("code",
                                                                                   code).getResultList();
            for (EmpMoiWsCriminalEntity entity : list) {
                arrayList.add(EmpEntityConverter.getEmpMoiWsCriminalDTO(entity));
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

	
    public Long getCriminalCount(Long civilId) throws DataBaseException, SharedApplicationException {

        Long count =0L;
        try {  
            StringBuilder qryStr = new StringBuilder();

            qryStr.append(" select count (1) ");
            qryStr.append(" FROM HR_EMP_MOI_WS_CRIMINAL  ");
            qryStr.append("  WHERE CIVIL_ID =  " + civilId);
            qryStr.append("  AND trunc(LAST_LOGIN_DATE) = trunc(SYSDATE) ");
            
            System.out.println("qryString" + qryStr.toString());
            Query q = EM().createNativeQuery(qryStr.toString());
            q.setHint("toplink.refresh", "true");
            Vector row = (Vector)q.getSingleResult();
            count = Long.valueOf(row.get(0).toString());
        } catch (Exception e) {
            e.printStackTrace();
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
        return count;
    }	
	
	


}
