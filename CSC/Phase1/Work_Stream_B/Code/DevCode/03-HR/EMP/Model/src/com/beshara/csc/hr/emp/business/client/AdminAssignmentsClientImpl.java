package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.csc.hr.emp.business.deploy.AdminAssignmentsSession;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
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
public class AdminAssignmentsClientImpl extends BaseClientImpl3 implements IAdminAssignmentsClient {

    /**
     * @param AdminAssignmentsSession
     */
    public AdminAssignmentsClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return AdminAssignmentsSession.class;
    }

    protected AdminAssignmentsSession SESSION() {
        return (AdminAssignmentsSession)super.SESSION();
    }

    /**
     * using to change assignment status * @param addminAssignmentDTO
     * @return Boolean
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public Boolean endAssignment(IBasicDTO addminAssignmentDTO,
                                 IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().endAssignment(RI(), addminAssignmentDTO, basicDecisionDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * using to change assignment status * @param addminAssignmentDTO
     * @return Boolean
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public Boolean endAssignment(IBasicDTO addminAssignmentDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().endAssignment(RI(), addminAssignmentDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean addDecision(IBasicDTO adminAssignmentsDTO1,
                               IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().addDecision(RI(), adminAssignmentsDTO1, basicDecisionDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * Add Decision to employee to add assignment * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public Boolean executeAssignment(IBasicDTO adminAssignmentsDTO1,
                                     IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().executeAssignment(RI(), adminAssignmentsDTO1, basicDecisionDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get all assignment to end this assignment * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToEndAssignment() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllToEndAssignment(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * get all assignment to approve * @return List
     * @throws DataBaseException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToApprove() throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().getAllToApprove(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    /**
     * search in all employees to approve assignment * @param basicDTO
     * @return list
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchToApproveAssignment(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException {
        try {
            return SESSION().searchToApproveAssignment(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    /**
     * search in all employees to end assignment * @param basicDTO
     * @return list
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchEndAssignment(IBasicDTO basicDTO) throws SharedApplicationException,
                                                                          DataBaseException {
        try {
            return SESSION().searchEndAssignment(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean executeEndAssignment(IBasicDTO basicDTO) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().executeEndAssignment(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean executeAdminAssignments(IBasicDTO adminAssignmentsDTO1) throws SharedApplicationException,
                                                                                  DataBaseException {
        try {
            return SESSION().executeAdminAssignments(RI(), adminAssignmentsDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Long getTableRecordSerial(IEntityKey id1) throws DataBaseException {
        try {
            return SESSION().getTableRecordSerial(RI(), id1);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO addForADC(IBasicDTO basicDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().addForADC(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean getPreviosAssignment(Long realCivilID) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getPreviosAssignment(RI(), realCivilID);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IAdminAssignmentsDTO> getAllData() throws DataBaseException, SharedApplicationException,
                                                          NoResultException {
        try {
            return SESSION().getAllData(RI());
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
}
    }

}
