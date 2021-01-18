package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.dao.DegreeDataDAO;
import com.beshara.csc.hr.emp.business.entity.DegreeDataEntity;

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
@Stateless(name = "DegreeDataSession", mappedName = "Emp-Model-DegreeDataSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class DegreeDataSessionBean extends EmpBaseSessionBean implements DegreeDataSession { //@PersistenceContext ( unitName = "Emp" ) 
    
    /** 
     * JobsSession Default Constructor */
    public DegreeDataSessionBean() {
        super();
    }
    
    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return DegreeDataEntity.class;
    }

    @Override
    protected DegreeDataDAO DAO() {
        return (DegreeDataDAO)super.DAO();
    }
}
