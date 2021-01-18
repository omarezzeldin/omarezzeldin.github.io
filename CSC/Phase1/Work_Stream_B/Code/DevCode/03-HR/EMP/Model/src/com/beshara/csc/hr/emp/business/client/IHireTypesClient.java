package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.FinderException;


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
public interface IHireTypesClient extends IBasicClient {
    /**
     * get code and name for all Hire Types * @return List
     * @throws DataBaseException
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException;

    /**
     * get First Level Hire Types * @return List
     * @throws DataBaseException
     */
    public List<IBasicDTO> getFirstLevelHireTypes() throws DataBaseException;


    /**
     * Get all document for an existing hire type and valid for an employee * @param hireTypeCode
     * @param genderType
     * @param nationalityType
     * @return list
     * @throws DataBaseException
     */
    public List<IBasicDTO> getValidDocumentsForEmployee(Long hireTypeCode, Long genderType,
                                                        Long nationalityType) throws DataBaseException;

    public List<IHireTypesDTO> getAllSorted() throws RemoteException, FinderException;

    public List<IHireTypesDTO> getRelatedByCenterTwzef(Long parentHireType,
                                                       Long minCode) throws SharedApplicationException,
                                                                            DataBaseException;

    public List<IHireTypesDTO> getEmpHireTypesAdd(Long minCode) throws DataBaseException, SharedApplicationException;

    public IBasicDTO joinCondition(IJoinCondTargetDTO finishReasonDTO) throws DataBaseException,
                                                                              SharedApplicationException;

    public List<Long> getHireTyperecserialByHireType(Long hiretype) throws DataBaseException,
                                                                           SharedApplicationException;

    public IBasicDTO cancelCondition(IJoinCondTargetDTO finishReasonDTO) throws DataBaseException,
                                                                                SharedApplicationException;

    public List<IBasicDTO> getByTreeLevelHireTypes(Long treeLevel) throws DataBaseException,
                                                                          SharedApplicationException;
}
