package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.gn.grs.integration.business.joincond.IJoinCondTargetDTO;
import com.beshara.csc.hr.emp.business.deploy.HireTypesSession;
import com.beshara.csc.hr.emp.business.dto.IHireTypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.FinderException;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File . * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to Methods. * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class HireTypesClientImpl extends BaseClientImpl3 implements IHireTypesClient {

    /**
     * @param contextSession
     */
    public HireTypesClientImpl() {
        super();
    }

    /**
     * @return List
     */
    public List<IBasicDTO> getCodeName() throws DataBaseException {
        try {
            return SESSION().getCodeName(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * @return List
     */
    public List<IBasicDTO> getFirstLevelHireTypes() throws DataBaseException {
        try {
            return SESSION().getFirstLevelHireTypes(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Get all document for an existing hire type and valid for an employee * @param hireTypeCode
     * @param genderType
     * @param nationalityType
     * @return list
     * @throws DataBaseException
     */
    public List<IBasicDTO> getValidDocumentsForEmployee(Long hireTypeCode, Long genderType,
                                                        Long nationalityType) throws DataBaseException {
        try {
            return SESSION().getValidDocumentsForEmployee(RI(), hireTypeCode, genderType, nationalityType);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IHireTypesDTO> getRelatedByCenterTwzef(Long parentHireType,
                                                       Long minCode) throws SharedApplicationException,
                                                                            DataBaseException {
        try {
            return SESSION().getRelatedByCenterTwzef(RI(), parentHireType, minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IHireTypesDTO> getEmpHireTypesAdd(Long minCode) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getEmpHireTypesAdd(RI(), minCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO joinCondition(IJoinCondTargetDTO finishReasonDTO) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SESSION().joinCondition(RI(), finishReasonDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<Long> getHireTyperecserialByHireType(Long hiretype) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().getHireTyperecserialByHireType(RI(), hiretype);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO cancelCondition(IJoinCondTargetDTO finishReasonDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().cancelCondition(RI(), finishReasonDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    @Override
    public List<IHireTypesDTO> getAllSorted() throws RemoteException, FinderException {
        try {
            return SESSION().getAllSorted(RI());
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e = getWrappedException(e);
            throw new RuntimeException(e);
        }
    }

    public List<IBasicDTO> getByTreeLevelHireTypes(Long treeLevel) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().getByTreeLevelHireTypes(RI(), treeLevel);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    protected Class<? extends BasicSession> getSessionInterface() {
        return HireTypesSession.class;
    }


    protected HireTypesSession SESSION() {
        return (HireTypesSession)super.SESSION();
    }
}
