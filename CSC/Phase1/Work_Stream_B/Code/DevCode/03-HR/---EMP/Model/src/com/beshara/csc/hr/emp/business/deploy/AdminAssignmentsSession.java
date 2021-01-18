package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
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
public interface AdminAssignmentsSession extends BasicSession {
    /**
     * using to change assignment status * @param addminAssignmentDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    //    public Boolean endAssignment(IBasicDTO addminAssignmentDTO,
    //                                 IBasicDTO basicDecisionDTO) throws RemoteException,
    //                                                                    SharedApplicationException,
    //                                                                    DataBaseException;

    /**
     * using to change assignment status * @param addminAssignmentDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    //    public Boolean endAssignment(IBasicDTO addminAssignmentDTO) throws RemoteException,
    //                                                                       SharedApplicationException,
    //                                                                       DataBaseException;

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    public Boolean addDecision(IBasicDTO adminAssignmentsDTO1,
    //                               IBasicDTO basicDecisionDTO) throws RemoteException,
    //                                                                  SharedApplicationException,
    //                                                                  DataBaseException;

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    public Boolean executeAssignment(IBasicDTO adminAssignmentsDTO1,
    //                                     IBasicDTO basicDecisionDTO) throws RemoteException,
    //                                                                        SharedApplicationException,
    //                                                                        DataBaseException;

    /**
     * get all assignment to end this assignment * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    public List<IBasicDTO> getAllToEndAssignment() throws RemoteException,
    //                                                          SharedApplicationException;
    //
    /**
     * get all assignment to approve * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    //    public List<IBasicDTO> getAllToApprove() throws RemoteException,
    //                                                    SharedApplicationException;

    /**
     * search in all employees to approve assignment * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    //    public List<IBasicDTO> searchToApproveAssignment(IBasicDTO basicDTO) throws RemoteException,
    //                                                                                SharedApplicationException,
    //                                                                                DataBaseException;

    /**
     * search in all employees to end assignment * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    //    public List<IBasicDTO> searchEndAssignment(IBasicDTO basicDTO) throws RemoteException,
    //                                                                          SharedApplicationException,
    //                                                                          DataBaseException;

    //    public Boolean executeAdminAssignments(IBasicDTO adminAssignmentsDTO1) throws RemoteException,
    //                                                                                  SharedApplicationException,
    //                                                                                  DataBaseException;

    //    public Boolean executeEndAssignment(IBasicDTO basicDTO) throws RemoteException,
    //                                                                   SharedApplicationException,
    //                                                                   DataBaseException;

    //    public Long getTableRecordSerial(IEntityKey id1) throws RemoteException;


    //***********************************

    public Boolean endAssignment(IRequestInfoDTO requestInfo, IBasicDTO addminAssignmentDTO,
                                 IBasicDTO basicDecisionDTO) throws RemoteException, SharedApplicationException,
                                                                    DataBaseException;

    public Boolean endAssignment(IRequestInfoDTO requestInfo, IBasicDTO addminAssignmentDTO) throws RemoteException,
                                                                                                    SharedApplicationException,
                                                                                                    DataBaseException;

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean addDecision(IRequestInfoDTO requestInfo, IBasicDTO adminAssignmentsDTO1,
                               IBasicDTO basicDecisionDTO) throws RemoteException, SharedApplicationException,
                                                                  DataBaseException;

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public Boolean executeAssignment(IRequestInfoDTO requestInfo, IBasicDTO adminAssignmentsDTO1,
                                     IBasicDTO basicDecisionDTO) throws RemoteException, SharedApplicationException,
                                                                        DataBaseException;

    /**
     * get all assignment to end this assignment * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToEndAssignment(IRequestInfoDTO requestInfo) throws RemoteException,
                                                                                     SharedApplicationException,
                                                                                     DataBaseException;

    /**
     * get all assignment to approve * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToApprove(IRequestInfoDTO requestInfo) throws RemoteException,
                                                                               SharedApplicationException,
                                                                               DataBaseException;

    /**
     * search in all employees to approve assignment * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchToApproveAssignment(IRequestInfoDTO requestInfo,
                                                     IBasicDTO basicDTO) throws RemoteException,
                                                                                SharedApplicationException,
                                                                                DataBaseException;

    /**
     * search in all employees to end assignment * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchEndAssignment(IRequestInfoDTO requestInfo, IBasicDTO basicDTO) throws RemoteException,
                                                                                                       SharedApplicationException,
                                                                                                       DataBaseException;

    public Boolean executeAdminAssignments(IRequestInfoDTO requestInfo,
                                           IBasicDTO adminAssignmentsDTO1) throws RemoteException,
                                                                                  SharedApplicationException,
                                                                                  DataBaseException;

    public Boolean executeEndAssignment(IRequestInfoDTO requestInfo, IBasicDTO basicDTO) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;

    public Long getTableRecordSerial(IRequestInfoDTO requestInfo, IEntityKey id1) throws RemoteException,
                                                                                         SharedApplicationException,
                                                                                         DataBaseException;

    public IBasicDTO addForADC(IRequestInfoDTO ri, IBasicDTO basicDTO) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;

    public Boolean getPreviosAssignment(IRequestInfoDTO ri, Long realCivilID) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public List<IAdminAssignmentsDTO> getAllData(IRequestInfoDTO ri) throws RemoteException, DataBaseException,
                                                          SharedApplicationException, NoResultException;
}
