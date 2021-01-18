package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.dao.HireStatusDAO;
import com.beshara.csc.hr.emp.business.entity.HireStatusEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;
import com.beshara.csc.sharedutils.business.util.SharedUtils;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents the Business Object Wrapper ( as Session Bean ) for Business Component IpIuIbIlIiIsIhIiInIgI.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "HireStatusSession", mappedName = "Emp-Model-HireStatusSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HireStatusSessionBean extends EmpBaseSessionBean implements HireStatusSession { //@PersistenceContext ( unitName = "Emp" ) 


    /** 
     * JobsSession Default Constructor */
    public HireStatusSessionBean() {
        super();
    }
    
    
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HireStatusEntity.class;
    }

    @Override
    protected HireStatusDAO DAO() {
        return (HireStatusDAO)super.DAO();
    }
    
    
    /** 
     * Get all HireStatus Code And name * @return list 
     * @throws RemoteException 
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws SharedApplicationException, 
                                            DataBaseException {
        try{
        return DAO().getCodeName();
        }catch (ItemNotFoundException e) {
             throw new NoResultException();
         }catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

   
   

    /** 
     * list all HireStatus match search code * @param code 
     * @return List of IHireStatusDTO 
     * @throws RemoteException 
     * @throws ItemNotFoundException 
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri,Object code) throws DataBaseException, 
                                                            SharedApplicationException {

        if (code instanceof Long) {
            try {
                return DAO().searchByCode(code);
            } catch (ItemNotFoundException e) {
             throw new NoResultException();
         }catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
        } else {
            throw new InvalidDataEntryException();
        }
    }

    /** 
     * Remove List of type HireStatus * @param list 
     * @return list 
     * @throws RemoteException 
     * @throws SharedApplicationException 
     * @throws DataBaseException 
     */
     //@Auditable
    public List remove(IRequestInfoDTO ri,List<IBasicDTO> list) throws  
                                                                     SharedApplicationException, 
                                                                     DataBaseException {
        List resultList = new ArrayList();
        
        boolean transactionBegun = isTransactionBegun();
        if(transactionBegun){
            suspendTransaction();   
        }
        
        try{
        for (IBasicDTO basicDTO: list) {
            try {
                beginTransaction();
                super.remove(basicDTO);
                commitTransaction();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(basicDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(basicDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (SharedApplicationException e) {
                e.printStackTrace();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(basicDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                resultList.add(resultDTO);
                rollbackTransaction();
            } catch (TransactionException e) {
                e.printStackTrace();
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setCurrentObject(basicDTO);
                resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                resultList.add(resultDTO);
            }
        }
        return resultList;
    }        
        finally{
            if(transactionBegun){
                suspendTransaction();
            }
        }
    }                                                              
   
}
