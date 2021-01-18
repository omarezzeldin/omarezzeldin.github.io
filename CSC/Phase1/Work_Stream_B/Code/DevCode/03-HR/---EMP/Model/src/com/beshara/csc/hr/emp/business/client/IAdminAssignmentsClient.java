package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.IBasicClient;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

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
public interface IAdminAssignmentsClient extends IBasicClient {
    /**
     * using to end assignment * @param addminAssignmentDTO
     * @return Boolean
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public Boolean endAssignment(IBasicDTO addminAssignmentDTO) throws SharedApplicationException, DataBaseException;

    /**
     * * @param basicDTO
     * @param basicDecisionDTO
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public Boolean endAssignment(IBasicDTO basicDTO, IBasicDTO basicDecisionDTO) throws RemoteException,
                                                                                        SharedApplicationException,
                                                                                        DataBaseException;

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean addDecision(IBasicDTO adminAssignmentsDTO1,
                               IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException;

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean executeAssignment(IBasicDTO adminAssignmentsDTO1,
                                     IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException;

    /**
     * get all assignment to end this assignment * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToEndAssignment() throws DataBaseException, SharedApplicationException;

    /**
     * get all assignment to approve * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToApprove() throws DataBaseException, SharedApplicationException;

    /**
     * search in all employees to approve assignment * @param basicDTO
     * @return list
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchToApproveAssignment(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException;

    /**
     * search in all employees to end assignment * @param basicDTO
     * @return list
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchEndAssignment(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                          DataBaseException;

    public Boolean executeEndAssignment(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException;

    public Boolean executeAdminAssignments(IBasicDTO adminAssignmentsDTO1) throws SharedApplicationException,
                                                                                  DataBaseException;

    public Long getTableRecordSerial(IEntityKey id1) throws DataBaseException;

    public IBasicDTO addForADC(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException;

    public Boolean getPreviosAssignment(Long realCivilID) throws DataBaseException, SharedApplicationException;
    
    public List<IAdminAssignmentsDTO> getAllData() throws DataBaseException, SharedApplicationException,
                                                      NoResultException ;
}
