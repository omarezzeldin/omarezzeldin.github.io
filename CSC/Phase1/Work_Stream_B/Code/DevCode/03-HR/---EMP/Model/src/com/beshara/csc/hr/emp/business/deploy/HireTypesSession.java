package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Remote Interface Contains All the Methods which are Implemented By Session Bean . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
@Remote
public interface HireTypesSession extends BasicSession {
    /**
     * Get all Hire Types code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                  SharedApplicationException;

    /**
     * Get all First Level Hire Types code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getFirstLevelHireTypes(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                             SharedApplicationException;

    /**
     * Get all document for an existing hire type and valid for an employee * @param hireTypeCode
     * @param genderType
     * @param nationalityType
     * @return list
     * @throws RemoteException
     */
    public List<IBasicDTO> getValidDocumentsForEmployee(IRequestInfoDTO ri, Long hireTypeCode, Long genderType,
                                                        Long nationalityType) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public List<IHireTypesDTO> getAllSorted(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                                       SharedApplicationException;

    public List<IHireTypesDTO> getRelatedByCenterTwzef(IRequestInfoDTO ri, Long parentHireType,
                                                       Long minCode) throws RemoteException, DataBaseException,
                                                                            SharedApplicationException;

    public List<IHireTypesDTO> getEmpHireTypesAdd(IRequestInfoDTO ri, Long minCode) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public IBasicDTO joinCondition(IRequestInfoDTO ri, IJoinCondTargetDTO finishReasonDTO) throws RemoteException,
                                                                                                  SharedApplicationException,
                                                                                                  DataBaseException;

    public IBasicDTO cancelCondition(IRequestInfoDTO ri, IJoinCondTargetDTO finishReasonDTO) throws RemoteException,
                                                                                                    SharedApplicationException,
                                                                                                    DataBaseException;

    public List<Long> getHireTyperecserialByHireType(IRequestInfoDTO ri, Long hiretype) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    public List<IBasicDTO> getByTreeLevelHireTypes(IRequestInfoDTO ri, Long treeLevel) throws RemoteException,
                                                                                              DataBaseException,
                                                                                              SharedApplicationException;

}
