package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.deploy.RequiredDocumentsSession;
import com.beshara.csc.inf.business.dto.IInfDocumentTypesDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.util.List;


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
public class RequiredDocumentsClientImpl extends BaseClientImpl3 implements IRequiredDocumentsClient {


    public RequiredDocumentsClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return RequiredDocumentsSession.class;
    }

    @Override
    protected RequiredDocumentsSession SESSION() {
        return (RequiredDocumentsSession)super.SESSION();
    }

    /**
     * get document types not related to Hire Type * @param hireTypesEntityKey
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> getAvailableDocuments(IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {
            return SESSION().getAvailableDocuments(RI(), hireTypesEntityKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    /**
     * get document types not related to Hire Type * @param hireTypesEntityKey
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> getAvailableDocuments() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAvailableDocuments(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    /**
     * filter document types not related to Hire Type * @param hireTypeCode
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> filterAvailableDocuments(IEntityKey hireTypeCode,
                                                               String name) throws DataBaseException,
                                                                                   SharedApplicationException {
        try {
            return SESSION().filterAvailableDocuments(RI(), hireTypeCode, name);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * filter document types not related to Hire Type * @param hireTypeCode
     * @param code
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IInfDocumentTypesDTO> filterAvailableDocuments(IEntityKey hireTypeCode,
                                                               Long code) throws DataBaseException,
                                                                                 SharedApplicationException {
        try {
            return SESSION().filterAvailableDocuments(RI(), hireTypeCode, code);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }


    public Long checkBeforeDeleteDocument(Long docType, Long hireType) throws DataBaseException {
        try {
            return SESSION().checkBeforeDeleteDocument(RI(), docType, hireType);
        } catch (SharedApplicationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> getDocumentsByHireType(Long hireTypeCode) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().getDocumentsByHireType(RI(), hireTypeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

}
