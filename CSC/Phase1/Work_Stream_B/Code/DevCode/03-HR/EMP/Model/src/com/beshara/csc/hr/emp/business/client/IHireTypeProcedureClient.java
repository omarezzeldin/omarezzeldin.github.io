package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IHireProceduresDTO;
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
public interface IHireTypeProcedureClient extends IBasicClient {
    /**
     * get procedures not related to Hire Type * @param hireTypesEntityKey
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IHireProceduresDTO> getAvailableProcedures(IEntityKey hireTypesEntityKey) throws DataBaseException,
                                                                                                 SharedApplicationException;

    public List<IHireProceduresDTO> getAvailableProcedures() throws DataBaseException, SharedApplicationException;

    public List<IHireProceduresDTO> getActiveAvailableProcedures() throws DataBaseException,
                                                                          SharedApplicationException;

    /**
     * filter document types not related to Hire Type * @param hireTypeCode
     * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    //    public List<IDocumentTypesDTO> filterAvailableDocuments(IEntityKey hireTypeCode,
    //                                                            String name) throws DataBaseException,
    //                                                                                SharedApplicationException;
    //
    //    /**
    //     * filter document types not related to Hire Type * @param hireTypeCode
    //     * @param code
    //     * @return List
    //     * @throws DataBaseException
    //     * @throws SharedApplicationException
    //     */
    //    public List<IDocumentTypesDTO> filterAvailableDocuments(IEntityKey hireTypeCode,
    //                                                            Long code) throws DataBaseException,
    //                                                                              SharedApplicationException;

    public Long checkBeforeDeleteProcedure(Long hireProcedure, Long hireType) throws DataBaseException;

    public List<IHireProceduresDTO> filterAvailableProcedures(IEntityKey hireTypeCode,
                                                              String name) throws DataBaseException,
                                                                                  SharedApplicationException;


    public List<IHireProceduresDTO> filterAvailableProcedures(IEntityKey hireTypeCode,
                                                              Long code) throws DataBaseException,
                                                                                SharedApplicationException;

}
