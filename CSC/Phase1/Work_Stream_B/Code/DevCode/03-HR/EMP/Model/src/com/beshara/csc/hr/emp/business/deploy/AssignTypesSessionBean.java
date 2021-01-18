package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.AssignTypesDAO;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntity;
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

import org.cementj.base.trans.TransactionException;


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
@Stateless(name = "AssignTypesSession", mappedName = "Emp-Model-AssignTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class AssignTypesSessionBean extends EmpBaseSessionBean implements AssignTypesSession { //@PersistenceContext ( unitName = "Emp" ) 


    /** 
     * JobsSession Default Constructor */
    public AssignTypesSessionBean() {
    }
    
    @Override
    protected Class<? extends BasicEntity> getEntityClass(){
        return AssignTypesEntity.class;
    }

    /**
     * @return List
     */
    @Override
    protected AssignTypesDAO DAO(){
        return (AssignTypesDAO)super.DAO();
    }

    /** 
     * Get all Assign Type with code equal code parameter * @param code 
     * @return List 
     * @throws RemoteException 
     * @throws ItemNotFoundException 
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO reqInfo,Object code) throws DataBaseException , 
                                                            SharedApplicationException {

        if (code instanceof Long) {
            try {
                return DAO().searchByCode(code);
            } catch (ItemNotFoundException e) {
                throw new NoResultException();
            }
        } else {
            throw new InvalidDataEntryException();
        }
    }

    /** 
     * Get all Assign Type with name Like parameter String * @param name 
     * @return List 
     * @throws RemoteException 
     * @throws ItemNotFoundException 
     */
    public List<IBasicDTO> searchByName(IRequestInfoDTO reqInfo,String name) throws DataBaseException , 
                                                            SharedApplicationException {
            return DAO().searchByName(name);
    }

    /** 
     * delete list of type AssignType * @param list 
     * @return list 
     * @throws RemoteException 
     * @throws SharedApplicationException 
     * @throws DataBaseException 
     */
     //@Auditable
    public List remove(IRequestInfoDTO reqInfo,List<IBasicDTO> list) throws  
                                                    SharedApplicationException, 
                                                    DataBaseException {
        
        boolean transactionBegun = isTransactionBegun();
        
        try{
            if(transactionBegun){
                suspendTransaction();
            }
            List resultList = new ArrayList();
            for (IBasicDTO basicDTO: list) {
                try {
                    super.remove(basicDTO);

                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setCurrentObject(basicDTO);
                    resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                    resultList.add(resultDTO);
                } catch (ItemNotFoundException e) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setCurrentObject(basicDTO);
                    resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                    resultList.add(resultDTO);
                    rollbackTransaction();
                } catch (SharedApplicationException e) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setCurrentObject(basicDTO);
                    resultDTO.setStatus(ISystemConstant.ITEM_DELETED);
                    resultList.add(resultDTO);
                    rollbackTransaction();
                } catch (TransactionException e) {
                    ResultDTO resultDTO = new ResultDTO();
                    resultDTO.setCurrentObject(basicDTO);
                    resultDTO.setStatus(ISystemConstant.ITEM_NOT_DELETED);
                    resultDTO.setDatabaseException(new DataBaseException(SharedUtils.getExceptionMessage(e)));
                    resultList.add(resultDTO);
                }
            }
            return resultList;
        }finally{
            if(transactionBegun){
                resumeTransaction();
            }
        }

    }

    @Override
    public List<IBasicDTO> getCodeName(IRequestInfoDTO requestInfo) throws SharedApplicationException,
                                                                            DataBaseException{
        return DAO().getCodeName();
    }
}
