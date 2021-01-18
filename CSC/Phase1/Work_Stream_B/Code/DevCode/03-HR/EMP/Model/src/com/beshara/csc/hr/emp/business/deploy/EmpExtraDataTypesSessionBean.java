package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.EmpExtraDataTypesDAO;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Represents the Business Object Wrapper (as Session Bean ) for Business Component publishing.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author     Beshara Group
 * @author     Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Stateless(name = "EmpExtraDataTypesSession", mappedName = "Emp-Model-EmpExtraDataTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpExtraDataTypesSessionBean extends EmpBaseSessionBean implements EmpExtraDataTypesSession {

    /**
     * JobsSession Default Constructor
     */
    public EmpExtraDataTypesSessionBean() {
      super();
    }

    protected EmpExtraDataTypesDAO DAO() {
        return (EmpExtraDataTypesDAO)super.DAO();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpExtraDataTypesEntity.class;
    }
    /**
     * @return List
     */
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws  SharedApplicationException, 
                                           DataBaseException {
        return DAO().getAll();
    }

    /**
     * @param empExtraDataTypesDTO
     * @return EmpExtraDataTypesDTO
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri,IBasicDTO empExtraDataTypesDTO) throws  SharedApplicationException, 
                                                                DataBaseException {
        return super.add(empExtraDataTypesDTO);
    }

    /**
     * @param empExtraDataTypesDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean update(IRequestInfoDTO ri,IBasicDTO empExtraDataTypesDTO) throws  SharedApplicationException, 
                                                                 DataBaseException {
        return DAO().update(empExtraDataTypesDTO);
    }

    /**
     * @param empExtraDataTypesDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean remove(IRequestInfoDTO ri,IBasicDTO empExtraDataTypesDTO) throws  SharedApplicationException, 
                                                                 DataBaseException {
        return DAO().remove(empExtraDataTypesDTO);
    }

    /**
     * @param id
     * @return EmpExtraDataTypesDTO
     */
    public IBasicDTO getById(IRequestInfoDTO ri,IEntityKey id) throws  SharedApplicationException, 
                                                   DataBaseException {
        return DAO().getById(id);
    }
    //@Auditable
    public List remove(IRequestInfoDTO ri,List<IBasicDTO> list) throws  SharedApplicationException, 
                                                    DataBaseException {
        return super.remove(list);
    }

    public List<IBasicDTO> search(IRequestInfoDTO ri,IBasicDTO basicDTO) throws  SharedApplicationException, 
                                                             DataBaseException {
        return DAO().search(basicDTO);
    }

   
   public List<IBasicDTO> getNotLinkedToMin(IRequestInfoDTO ri , Long minCode, Long status , Long searchType , String searchValue) throws DataBaseException, SharedApplicationException {
        return DAO().getNotLinkedToMin(minCode, status , searchType , searchValue);
    }
    
            
}
