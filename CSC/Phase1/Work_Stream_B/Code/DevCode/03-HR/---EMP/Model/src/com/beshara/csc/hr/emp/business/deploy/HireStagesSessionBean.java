package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.dto.ResultDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.HireStagesDAO;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
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
@Stateless(name = "HireStagesSession", mappedName = "Emp-Model-HireStagesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HireStagesSessionBean extends EmpBaseSessionBean implements HireStagesSession { //@PersistenceContext ( unitName = "Emp" ) 

    /** 
     * JobsSession Default Constructor */
    public HireStagesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HireStagesEntity.class;
    }
    
    @Override
    protected HireStagesDAO DAO(){
        return (HireStagesDAO)super.DAO();
    }
    
    /** 
     * Get all HireStages code and name * @return list 
     * @throws RemoteException 
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException,SharedApplicationException {
        return DAO().getCodeName();
    }

    /** 
     * Get all HireStages match search code * @param code 
     * @return List of IHireStagesDTO 
     * @throws RemoteException 
     * @throws ItemNotFoundException 
     */
    public List<IBasicDTO> searchByCode(IRequestInfoDTO ri,Object code) throws DataBaseException, 
                                                            SharedApplicationException {

        if (code instanceof Long) {
        return DAO().searchByCode(code);
        } else {
            throw new InvalidDataEntryException();
        }
    }

    /** 
     * Remove List of type HireStages * @param list 
     * @return List 
     * @throws RemoteException 
     * @throws SharedApplicationException 
     * @throws DataBaseException 
     */
     //@Auditable
    public List remove(IRequestInfoDTO ri,List<IBasicDTO> list) throws SharedApplicationException, 
                                                    DataBaseException {
        boolean transactionBegun = isTransactionBegun();
        try{
            List resultList = new ArrayList();
            
            if(transactionBegun) {
                suspendTransaction();
            }
            
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
            if(transactionBegun) {
                suspendTransaction();
            }
        }
    }
}
