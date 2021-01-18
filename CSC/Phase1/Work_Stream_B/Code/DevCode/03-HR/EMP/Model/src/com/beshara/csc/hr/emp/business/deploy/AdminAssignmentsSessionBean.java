package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.gn.grs.business.client.GrsClientFactory;
import com.beshara.csc.gn.grs.business.entity.IConditionsEntityKey;
import com.beshara.csc.hr.emp.business.dao.AdminAssignmentsDAO;
import com.beshara.csc.hr.emp.business.dao.AssignTypesDAO;
import com.beshara.csc.hr.emp.business.dao.EmployeesDAO;
import com.beshara.csc.hr.emp.business.dto.AdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.IAdminAssignmentsDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignStatusDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignTypesDTO;
import com.beshara.csc.hr.emp.business.dto.IAssignmentsSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.AdminAssignmentsEntity;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.IAssignTypesEntityKey;
import com.beshara.csc.hr.emp.business.entity.IEmployeesEntityKey;
import com.beshara.csc.hr.emp.business.shared.exception.ConfilectAssignmentDateException;
import com.beshara.csc.nl.job.business.entity.IJobsEntityKey;
import com.beshara.csc.nl.reg.business.client.RegClientFactory;
import com.beshara.csc.nl.reg.business.dto.IDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsDTO;
import com.beshara.csc.nl.reg.business.dto.IEmpDecisionsSearchDTO;
import com.beshara.csc.nl.reg.business.dto.RegDTOFactory;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.exception.emp.InvalidWorkCenterException;
import com.beshara.csc.sharedutils.business.exception.emp.NoDecisionFoundException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;
import com.beshara.csc.sharedutils.business.util.ISystemConstant;

import java.rmi.RemoteException;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

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
@Stateless(name = "AdminAssignmentsSession", mappedName = "Emp-Model-AdminAssignmentsSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class AdminAssignmentsSessionBean extends EmpBaseSessionBean implements AdminAssignmentsSession { //@PersistenceContext ( unitName = "Emp" )

    private EmployeesSessionBean employeesBean;

    /**
     * JobsSession Default Constructor */
    public AdminAssignmentsSessionBean() {
        employeesBean = new EmployeesSessionBean();
    }

    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return AdminAssignmentsEntity.class;
    }

    @Override
    protected AdminAssignmentsDAO DAO() {
        return (AdminAssignmentsDAO)super.DAO();
    }

    /**
     * Add new AdminAssignments * @param adminAssignmentsDTO
     * @return IBasicDTO
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO adminAssignmentsDTO) throws SharedApplicationException,
                                                                                   DataBaseException {

        try {
            AssignTypesDAO asignTypes = (AssignTypesDAO)super.newDAOInstance(AssignTypesEntity.class);
            IAssignTypesDTO type =
                (IAssignTypesDTO)asignTypes.getById(((IAdminAssignmentsDTO)adminAssignmentsDTO).getAssignTypesDTO().getCode());
            if (type.getConditionsDTO() != null) {
                if (GrsClientFactory.getConditionsClient().checkCivilIdOnCondition(((IConditionsEntityKey)type.getConditionsDTO().getCode()).getConditionCode(),
                                                                                   ((IEmployeesEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getEmployeesDTO().getCode()).getCivilId()).equals(Boolean.FALSE))
                    throw new InvalidDataEntryException();
            }

            EmployeesDAO employeesDAO = (EmployeesDAO)super.newDAOInstance(EmployeesEntity.class);
            Long assigntypeCode = ((IAssignTypesEntityKey)type.getCode()).getAsstypeCode();
            Long civilID =
                ((IEmployeesEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getEmployeesDTO().getCode()).getCivilId();
            IEmployeesDTO empDTO =
                (IEmployeesDTO)employeesDAO.getById(EmpEntityKeyFactory.createEmployeesEntityKey(civilID));

            if (assigntypeCode != null && assigntypeCode.equals(IEMPConstant.ADMIN_ASSIGNMENT_WITH_PRIVILEGE_TYPE))
                if (((IAdminAssignmentsDTO)adminAssignmentsDTO).getWorkCentersDTO().getCode().getKey().toString().equals(empDTO.getWorkCenterKey()))
                    throw new InvalidWorkCenterException();
            employeesBean.checkLegelJobDTO(ri, civilID,
                                           (IJobsEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getJobsDTO().getCode());
            IAssignStatusDTO assignStatusDTO = EmpDTOFactory.createAssignStatusDTO();
            assignStatusDTO.setCode(EmpEntityKeyFactory.createAssignStatusEntityKey(IEMPConstant.ASSIGNMENT_UNDER_INVESTIGATE));
            ((IAdminAssignmentsDTO)adminAssignmentsDTO).setAssignStatusDTO(assignStatusDTO);
            return super.add(adminAssignmentsDTO);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * Update an exist AdminAssignments * @param adminAssignmentsDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public Boolean update(IRequestInfoDTO ri, IBasicDTO adminAssignmentsDTO) throws SharedApplicationException,
                                                                                    DataBaseException {
        AssignTypesDAO asignTypes = (AssignTypesDAO)super.newDAOInstance(AssignTypesEntity.class);
        IAssignTypesDTO type =
            (IAssignTypesDTO)asignTypes.getById(((IAdminAssignmentsDTO)adminAssignmentsDTO).getAssignTypesDTO().getCode());
        if (((IAdminAssignmentsDTO)adminAssignmentsDTO).getAssignTypesDTO().getConditionsDTO() != null) {
            if (GrsClientFactory.getConditionsClient().checkCivilIdOnCondition(((IConditionsEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getAssignTypesDTO().getConditionsDTO().getCode()).getConditionCode(),
                                                                               ((IEmployeesEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getEmployeesDTO().getCode()).getCivilId()).equals(Boolean.FALSE))
                throw new InvalidDataEntryException();
        }
        EmployeesDAO employeesDAO = (EmployeesDAO)super.newDAOInstance(EmployeesEntity.class);

        Long assigntypeCode = ((IAssignTypesEntityKey)type.getCode()).getAsstypeCode();
        Long civilID =
            ((IEmployeesEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getEmployeesDTO().getCode()).getCivilId();
        IEmployeesDTO empDTO =
            (IEmployeesDTO)employeesDAO.getById(EmpEntityKeyFactory.createEmployeesEntityKey(civilID));

        if (assigntypeCode != null && assigntypeCode.equals(IEMPConstant.ADMIN_ASSIGNMENT_WITH_PRIVILEGE_TYPE))
            if (((IAdminAssignmentsDTO)adminAssignmentsDTO).getWorkCentersDTO().getCode().getKey().toString().equals(empDTO.getWorkCenterKey()))
                throw new InvalidWorkCenterException();
        employeesBean.checkLegelJobDTO(ri, civilID,
                                       (IJobsEntityKey)((IAdminAssignmentsDTO)adminAssignmentsDTO).getJobsDTO().getCode());
        return super.update(adminAssignmentsDTO);
    }

    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                 DataBaseException {
        return DAO().search(basicDTO);
    }

    //@Auditable
    public Boolean executeEndAssignment(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                       DataBaseException {

        try {
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)basicDTO;
            Long tabRefSerial = DAO().getTableRecordSerial(adminAssignmentsDTO.getCode());
            IEmpDecisionsSearchDTO empDecisionsSearchDTO = RegDTOFactory.createEmpDecisionsSearchDTO();
            empDecisionsSearchDTO.setTableName(ISystemConstant.TABLE_HR_EMP_ADMIN_ASSIGNMENTS);
            empDecisionsSearchDTO.setTabrecSerialRef(tabRefSerial);
            empDecisionsSearchDTO.setCivilId(((IEmployeesEntityKey)adminAssignmentsDTO.getEmployeesDTO().getCode()).getCivilId());
            IBasicDTO decisionBasicDTO = null;
            try {
                decisionBasicDTO =
                        RegClientFactory.getDecisionsClient().getDecisionUsingEmpDecisionTabRecSerialRef(tabRefSerial);
                //RegClientFactory.getEmpDecisionsClient().search(empDecisionsSearchDTO);

            } catch (Exception e) {
                e.printStackTrace();
                throw new NoDecisionFoundException();
            }
            adminAssignmentsDTO.getAssignStatusDTO().setCode(EmpEntityKeyFactory.createAssignStatusEntityKey(IEMPConstant.END_ASSIGNMENT_DONE));
            return this.update(adminAssignmentsDTO);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * using to change assignment status ( End Addignment ) * @param basicDTO
     * @param basicDecisionDTO
     * @return
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public Boolean endAssignment(IRequestInfoDTO ri, IBasicDTO basicDTO,
                                 IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {

        try {
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)basicDTO;
            Long tabRefSerial = DAO().getTableRecordSerial(adminAssignmentsDTO.getCode());

            IDecisionsDTO decisionsDTO = (IDecisionsDTO)basicDecisionDTO;

            List<IBasicDTO> empDecisionDTOList = new ArrayList<IBasicDTO>();
            IEmpDecisionsDTO empDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
            empDecisionsDTO.setEmployeesDTO(adminAssignmentsDTO.getEmployeesDTO());
            empDecisionsDTO.setTabrecSerialRef(tabRefSerial);
            System.out.println("tab rec serial>>" + tabRefSerial);
            empDecisionsDTO.setTableName(ISystemConstant.TABLE_HR_EMP_ADMIN_ASSIGNMENTS);
            empDecisionDTOList.add(empDecisionsDTO);
            decisionsDTO.setEmpDecisionsDTOList(empDecisionDTOList);
            beginTransaction();
            //ADD DECISION TO EMPLOYEE
            decisionsDTO = (IDecisionsDTO)RegClientFactory.getDecisionsClient().add(decisionsDTO, false);
            commitTransaction();
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (DataBaseException e) {
            rollbackTransaction();
            throw e;
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw new SharedApplicationException();
        } catch (TransactionException e) {
            rollbackTransaction();
            throw new TransactionException();
        }
        return Boolean.TRUE;
    }

    /**
     * using to change assignment status ( End Addignment ) * @param basicDTO
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
     //@Auditable
    public Boolean endAssignment(IRequestInfoDTO ri, IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException {

        IAdminAssignmentsDTO adminAssignmentsDTO = (AdminAssignmentsDTO)basicDTO;
        Long realCivilID = adminAssignmentsDTO.getEmployeesDTO().getRealCivilId();

        Date assignmentDate = adminAssignmentsDTO.getUntilDate();
        String currntAssignment = adminAssignmentsDTO.getCode().getKey();
        if (DAO().checkConfilectedDateAssignmentExcludeCurrent(assignmentDate, realCivilID, currntAssignment)) {
            throw new ConfilectAssignmentDateException();
        }
        ((IAdminAssignmentsDTO)basicDTO).getAssignStatusDTO().setCode(EmpEntityKeyFactory.createAssignStatusEntityKey(IEMPConstant.END_ASSIGNMENT_DONE));
        return DAO().update(basicDTO);

    }

    /**
     * Add Decision to employee to add assignment ( Execute Assignment ) * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public Boolean addDecision(IRequestInfoDTO ri, IBasicDTO adminAssignmentsDTO1,
                               IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        try {
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)adminAssignmentsDTO1;
            List<IBasicDTO> empDecisionDTOList = new ArrayList<IBasicDTO>();
            IEmpDecisionsDTO empDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
            Long tabRefSerial = DAO().getTableRecordSerial(adminAssignmentsDTO.getCode());
            empDecisionsDTO.setEmployeesDTO(adminAssignmentsDTO.getEmployeesDTO());
            empDecisionsDTO.setTabrecSerialRef(tabRefSerial);
            empDecisionsDTO.setTableName(ISystemConstant.TABLE_HR_EMP_ADMIN_ASSIGNMENTS);
            empDecisionDTOList.add(empDecisionsDTO);
            IDecisionsDTO decisionDTO = (IDecisionsDTO)basicDecisionDTO;
            decisionDTO.setEmpDecisionsDTOList(empDecisionDTOList);
            beginTransaction();
            //ADD DECISION TO EMPLOYEE
            decisionDTO = (IDecisionsDTO)RegClientFactory.getDecisionsClient().add(decisionDTO, false);
            commitTransaction();
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (DataBaseException e) {
            rollbackTransaction();
            throw e;
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (TransactionException e) {
            rollbackTransaction();
            throw new TransactionException();
        }
        return Boolean.TRUE;
    }
    /*
    * Added By Nora
    * */
    //@Auditable
    public Boolean executeAdminAssignments(IRequestInfoDTO ri,
                                           IBasicDTO adminAssignmentsDTO1) throws SharedApplicationException,
                                                                                  DataBaseException {

        IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)adminAssignmentsDTO1;
        Long tabRefSerial = DAO().getTableRecordSerial(adminAssignmentsDTO.getCode());
        IEmpDecisionsSearchDTO empDecisionsSearchDTO = RegDTOFactory.createEmpDecisionsSearchDTO();
        empDecisionsSearchDTO.setTableName(ISystemConstant.TABLE_HR_EMP_ADMIN_ASSIGNMENTS);
        empDecisionsSearchDTO.setTabrecSerialRef(tabRefSerial);
        empDecisionsSearchDTO.setCivilId(((IEmployeesEntityKey)adminAssignmentsDTO.getEmployeesDTO().getCode()).getCivilId());
        IBasicDTO basicDTO = null;
        try {
            basicDTO = RegClientFactory.getDecisionsClient().getDecisionUsingEmpDecisionTabRecSerialRef(tabRefSerial);
            //RegClientFactory.getEmpDecisionsClient().search(empDecisionsSearchDTO);

        } catch (Exception e) {
            e.printStackTrace();
            throw new NoDecisionFoundException();
        }
        //        if (basicDTO == null || basicDTO.getCode() == null) {
        //            throw new NoDecisionFoundException();
        //        }
        ((IAdminAssignmentsDTO)adminAssignmentsDTO).getAssignStatusDTO().setCode(EmpEntityKeyFactory.createAssignStatusEntityKey(IEMPConstant.ASSIGNMENT_DONE));
        return this.update(adminAssignmentsDTO);
    }

    /**
     * Add Decision to employee to add assignment ( Execute Assignment ) * @param adminAssignmentsDTO1
     * @return Boolean
     * @throws RemoteException
     * @throws SharedApplicationException
     */
     //@Auditable
    public Boolean executeAssignment(IRequestInfoDTO ri, IBasicDTO adminAssignmentsDTO1,
                                     IBasicDTO basicDecisionDTO) throws SharedApplicationException, DataBaseException {
        try {
            IAdminAssignmentsDTO adminAssignmentsDTO = (IAdminAssignmentsDTO)adminAssignmentsDTO1;
            List<IBasicDTO> empDecisionDTOList = new ArrayList<IBasicDTO>();
            IEmpDecisionsDTO empDecisionsDTO = RegDTOFactory.createEmpDecisionsDTO();
            empDecisionsDTO.setEmployeesDTO(adminAssignmentsDTO.getEmployeesDTO());
            empDecisionsDTO.setTabrecSerialRef(adminAssignmentsDTO.getTabrecSerial());
            empDecisionsDTO.setTableName(ISystemConstant.TABLE_HR_EMP_ADMIN_ASSIGNMENTS);
            empDecisionDTOList.add(empDecisionsDTO);
            IDecisionsDTO decisionDTO = (IDecisionsDTO)basicDecisionDTO;
            decisionDTO.setEmpDecisionsDTOList(empDecisionDTOList);
            beginTransaction();
            //ADD DECISION TO EMPLOYEE
            decisionDTO = (IDecisionsDTO)RegClientFactory.getDecisionsClient().add(decisionDTO, false);
            //UPDATE ADMINASSIGNMENT WITH NEW STATUS
            adminAssignmentsDTO.getAssignStatusDTO().setCode(EmpEntityKeyFactory.createAssignStatusEntityKey(IEMPConstant.ASSIGNMENT_DONE));
            this.update(adminAssignmentsDTO);
            commitTransaction();
        } catch (ItemNotFoundException e) {
            rollbackTransaction();
            throw new InvalidDataEntryException();
        } catch (DataBaseException e) {
            rollbackTransaction();
            throw e;
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw new SharedApplicationException();
        } catch (TransactionException e) {
            rollbackTransaction();
            throw new TransactionException();
        }
        return Boolean.TRUE;
    }

    /**
     * get all assignment to end this assignment * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToEndAssignment(IRequestInfoDTO ri) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return DAO().getAllUsingStatusFlag(IEMPConstant.ASSIGNMENT_DONE);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * search in all employees to end assignment * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchEndAssignment(IRequestInfoDTO ri,
                                               IBasicDTO basicDTO) throws SharedApplicationException,
                                                                          DataBaseException {
        try {
            IAssignmentsSearchDTO dto = (IAssignmentsSearchDTO)basicDTO;
            dto.setAssstatusCode(IEMPConstant.ASSIGNMENT_DONE);
            return DAO().search(dto);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * get all assignment to approve * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> getAllToApprove(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().getAllUsingStatusFlag(IEMPConstant.ASSIGNMENT_UNDER_INVESTIGATE);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    /**
     * search in all employees to approve assignment * @param basicDTO
     * @return list
     * @throws RemoteException
     * @throws SharedApplicationException
     * @throws DataBaseException
     */
    public List<IBasicDTO> searchToApproveAssignment(IRequestInfoDTO ri,
                                                     IBasicDTO basicDTO) throws SharedApplicationException,
                                                                                DataBaseException {
        try {
            IAssignmentsSearchDTO dto = (IAssignmentsSearchDTO)basicDTO;
            dto.setAssstatusCode(IEMPConstant.END_ASSIGNMENT_DONE);
            return DAO().search(dto);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Long getTableRecordSerial(IRequestInfoDTO ri, IEntityKey id1) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return DAO().getTableRecordSerial(id1);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }
    //@Auditable
    public IBasicDTO addForADC(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                              SharedApplicationException {

        IAdminAssignmentsDTO adminAssignmentsDTO = (AdminAssignmentsDTO)basicDTO;
        Long realCivilID = adminAssignmentsDTO.getEmployeesDTO().getRealCivilId();

        Date assignmentDate = adminAssignmentsDTO.getFromDate();
        if (DAO().checkConfilectedDateAssignment(assignmentDate, realCivilID)) {
            throw new ConfilectAssignmentDateException();
        }
        if (adminAssignmentsDTO.getUntilDate() != null) {
            assignmentDate = adminAssignmentsDTO.getUntilDate();
            if (DAO().checkConfilectedDateAssignment(assignmentDate, realCivilID)) {
                throw new ConfilectAssignmentDateException();
            }
        }

        try {
            return DAO().add(basicDTO);
        } catch (Exception e) {
            e = wrapIntoDataBaseException(e);
            if (e instanceof DataBaseException) {
                throw (DataBaseException)e;
            } else {
                throw (SharedApplicationException)e;
            }
        }
    }

    public Boolean getPreviosAssignment(IRequestInfoDTO ri, Long realCivilID) throws DataBaseException,
                                                                                     SharedApplicationException {
        return DAO().getPreviosAssignment(realCivilID);

    }


    public List<IAdminAssignmentsDTO> getAllData(IRequestInfoDTO ri) throws DataBaseException,
                                                                            SharedApplicationException,
                                                                            NoResultException {
        return DAO().getAllData();
    }
}
