package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dao.ProcedureResultsDAO;
import com.beshara.csc.hr.emp.business.entity.ProcedureResultsEntity;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

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
@Stateless(name = "ProcedureResultsSession", mappedName = "Emp-Model-ProcedureResultsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class ProcedureResultsSessionBean extends EmpBaseSessionBean implements ProcedureResultsSession { //@PersistenceContext ( unitName = "Emp" ) 

    /** 
     * JobsSession Default Constructor */
    public ProcedureResultsSessionBean() {
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return ProcedureResultsEntity.class;
    }
    
    @Override
    protected ProcedureResultsDAO DAO(){
        return (ProcedureResultsDAO)super.DAO();
    }
    public List<IBasicDTO> getAll(IRequestInfoDTO ri) throws SharedApplicationException, 
                                           DataBaseException {
            return (ArrayList)DAO().getAll();
    }

    /** 
     * @param procedureResultsDTO 
     * @return IProcedureResultsDTO 
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri,IBasicDTO procedureResultsDTO) throws SharedApplicationException, 
                                                               DataBaseException {
        return DAO().add(procedureResultsDTO);
    }

    /** 
     * @param procedureResultsDTO 
     * @return Boolean 
     */
     //@Auditable
    public Boolean update(IRequestInfoDTO ri,IBasicDTO procedureResultsDTO) throws SharedApplicationException, 
                                                                DataBaseException {
        return DAO().update(procedureResultsDTO);
    }

    /** 
     * @param procedureResultsDTO 
     * @return Boolean 
     */
     //@Auditable
    public Boolean remove(IRequestInfoDTO ri,IBasicDTO procedureResultsDTO) throws SharedApplicationException, 
                                                                DataBaseException {
        return DAO().remove(procedureResultsDTO);
    }

    /** 
     * @param id 
     * @return IProcedureResultsDTO 
     */
    public IBasicDTO getById(IRequestInfoDTO ri,IEntityKey id) throws SharedApplicationException, 
                                                   DataBaseException {
        return DAO().getById(id);
    }
    //@Auditable
    public List remove(IRequestInfoDTO ri,List<IBasicDTO> list) throws SharedApplicationException, 
                                                    DataBaseException {
        return super.remove(list);
    }

    public List<IBasicDTO> search(IRequestInfoDTO ri,IBasicDTO basicDTO) throws SharedApplicationException, 
                                                             DataBaseException {
        return DAO().search(basicDTO);
    }
}
