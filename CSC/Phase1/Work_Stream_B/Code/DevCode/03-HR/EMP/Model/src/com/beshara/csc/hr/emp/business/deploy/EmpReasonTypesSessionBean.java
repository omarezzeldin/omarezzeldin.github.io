package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dao.EmpReasonTypesDAO;
import com.beshara.csc.hr.emp.business.entity.EmpReasonTypesEntity;
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
@Stateless(name = "EmpReasonTypesSession", mappedName = "Emp-Model-EmpReasonTypesSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class EmpReasonTypesSessionBean extends EmpBaseSessionBean implements EmpReasonTypesSession {


    /**
     * JobsSession Default Constructor
     */
    public EmpReasonTypesSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return EmpReasonTypesEntity.class;
    }
    
    @Override
    protected EmpReasonTypesDAO DAO(){
        return (EmpReasonTypesDAO)super.DAO();
    }
    
    public List<IBasicDTO> getAll() throws SharedApplicationException, 
                                           DataBaseException {
        return super.getAll();
    }

    /**
     * @param empReasonTypesDTO
     * @return EmpReasonTypesDTO
     */
     //@Auditable
    public IBasicDTO add(IBasicDTO empReasonTypesDTO) throws SharedApplicationException, 
                                                             DataBaseException {
        return DAO().add(empReasonTypesDTO);
    }

    /**
     * @param empReasonTypesDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean update(IBasicDTO empReasonTypesDTO) throws SharedApplicationException, 
                                                              DataBaseException {
        return DAO().update(empReasonTypesDTO);
    }

    /**
     * @param empReasonTypesDTO
     * @return Boolean
     */
     //@Auditable
    public Boolean remove(IBasicDTO empReasonTypesDTO) throws SharedApplicationException, 
                                                              DataBaseException {
        return DAO().remove(empReasonTypesDTO);
    }

    /**
     * @param id
     * @return EmpReasonTypesDTO
     */
    public IBasicDTO getById(IEntityKey id) throws SharedApplicationException, 
                                                   DataBaseException {
        return DAO().getById(id);
    }
    //@Auditable
    public List remove(List<IBasicDTO> list) throws SharedApplicationException, 
                                                    DataBaseException {
        return super.remove(list);
    }

    public List<IBasicDTO> search(IBasicDTO basicDTO) throws SharedApplicationException, 
                                                             DataBaseException {
        return DAO().search(basicDTO);
    }
}
