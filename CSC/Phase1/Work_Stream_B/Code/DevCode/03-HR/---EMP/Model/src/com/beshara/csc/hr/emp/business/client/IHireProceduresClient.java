package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Interface Contains a set of Methods Which Should be Implemented * with Different Implementation ( SessionBean , Message Driven Bean , RMI Service etc ... ) * and Generated through The Client Factory Class . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Sherif El-Rabbat 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry
 * @version 1.0
 * @since 03/09/2007
 */
public interface IHireProceduresClient extends IBasicClient {
    /**
     * get code and name for all Hire procedures * @return List
     * @throws DataBaseException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException;

    /**
     * Get all procedures valid for an existing employee * @param genderType
     * @param nationalityType
     * @return list
     * @throws DataBaseException
     */
    public List<IBasicDTO> getValidHireProceduresForEmployee(Long genderType,
                                                             Long nationalityType) throws DataBaseException,
                                                                                          SharedApplicationException;

    public List<IBasicDTO> getAllRelatedByHireProcdure(Long hireProcdureCode) throws DataBaseException,
                                                                                     SharedApplicationException;
    
    public boolean duplicatedHireProcdureName(String name,Long hireProcdureCode) throws DataBaseException, 
                                                                                         SharedApplicationException;
  
}
