package com.beshara.csc.hr.emp.business.deploy;


//import com.beshara.base.dataauditing.Auditable;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.BasicEntity;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.base.transaction.TransactionException;
import com.beshara.csc.hr.emp.business.client.EmpClientFactory;
import com.beshara.csc.hr.emp.business.dao.HireProceduresDAO;
import com.beshara.csc.hr.emp.business.dto.EmpDTOFactory;
import com.beshara.csc.hr.emp.business.dto.HireProceduresDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.IHireStagesDTO;
import com.beshara.csc.hr.emp.business.entity.EmpEntityKeyFactory;
import com.beshara.csc.hr.emp.business.entity.HireProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.IHireStagesEntityKey;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.InvalidDataEntryException;
import com.beshara.csc.sharedutils.business.exception.ItemNotFoundException;
import com.beshara.csc.sharedutils.business.exception.NoResultException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;
import com.beshara.csc.sharedutils.business.util.IEMPConstant;

import java.rmi.RemoteException;

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
@Stateless(name = "HireProceduresSession", mappedName = "Emp-Model-HireProceduresSessionBean")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote
public class HireProceduresSessionBean extends EmpBaseSessionBean implements HireProceduresSession { //@PersistenceContext ( unitName = "Emp" )

    /**
     * JobsSession Default Constructor */
    public HireProceduresSessionBean() {
        super();
    }


    @Override
    protected Class<? extends BasicEntity> getEntityClass() {
        return HireProceduresEntity.class;
    }

    @Override
    protected HireProceduresDAO DAO() {
        return (HireProceduresDAO)super.DAO();
    }

    /**
     * Get all HireProcedures match search criteria * @param basicDTO
     * @return List
     * @throws RemoteException
     * @throws SharedApplicationException
     */
    public List<IBasicDTO> search(IRequestInfoDTO ri, IBasicDTO basicDTO) throws DataBaseException,
                                                                                 SharedApplicationException {

        try {
            return DAO().search(basicDTO);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }


    /**
     * Get all Hire procedures code and name * @return List
     * @throws RemoteException
     */
    public List<IBasicDTO> getCodeName(IRequestInfoDTO ri) throws DataBaseException, SharedApplicationException {
        try {
            return DAO().getCodeName();
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }

    }

    /**
     * Get all procedures valid for an existing employee * @param genderType
     * @param nationalityType
     * @return list
     *
     */
    public List<IBasicDTO> getValidHireProceduresForEmployee(IRequestInfoDTO ri, Long genderType,
                                                             Long nationalityType) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return DAO().getValidHireProceduresForEmployee(genderType, nationalityType);
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }


    }

    /**
     * to update employee stage * @param genderType
     * @param nationalityType
     * @throws RemoteException
     * @throws ItemNotFoundException
     */
     //@Auditable
    private void updateEmployeeStage(Long genderType, Long nationalityType) throws DataBaseException,
                                                                                   SharedApplicationException {

        try {
            IEmpEmployeeSearchDTO search = EmpDTOFactory.createEmpEmployeeSearchDTO();
            search.setNationalityType(nationalityType);
            search.setGenderType(genderType);
            search.setHirestageNotEqualCanceldOrEmployment(true);

            for (IBasicDTO basicEmployee : EmpClientFactory.getEmployeesClient().simpleSearch(search)) {
                IEmployeesDTO dto = (IEmployeesDTO)basicEmployee;
                IHireStagesDTO stage = EmpDTOFactory.createHireStagesDTO();
                Long oldStage = ((IHireStagesEntityKey)dto.getHireStageDTO().getCode()).getHirstageCode();
                if (oldStage.equals(IEMPConstant.EMP_STAGE_WAITING_EMPLOYMENT)) {
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_COMPLETE_DOC));
                    dto.setHireStageDTO(stage);
                }
                if (oldStage.equals(IEMPConstant.EMP_STAGE_COMPLETE_PROC)) { //set default stage
                    stage.setCode(EmpEntityKeyFactory.createHireStagesEntityKey(IEMPConstant.EMP_STAGE_DEFAULT));
                    dto.setHireStageDTO(stage);
                }
                EmpClientFactory.getEmployeesClient().update(dto);
            }
        } catch (ItemNotFoundException e) {
            throw new NoResultException();
        } catch (TransactionException e) {
            throw wrapIntoDataBaseException(e);
        } catch (SharedApplicationException e) {
            rollbackTransaction();
            throw e;
        } catch (Exception e) {
            rollbackTransaction();
            throw wrapIntoDataBaseException(e);
        }
    }

    public List<IBasicDTO> getAllRelatedByHireProcdure(IRequestInfoDTO ri,
                                                       Long hireProcdureCode) throws DataBaseException,
                                                                                     SharedApplicationException {
        return DAO().getAllRelatedByHireProcdure(hireProcdureCode);
    }

    public boolean duplicatedHireProcdureName(IRequestInfoDTO ri,String name,Long hireProcdureCode) throws
                                                                                        DataBaseException, 
    SharedApplicationException{
        
        return DAO().duplicatedHireProcdureName( name, hireProcdureCode);

        }

    //@Auditable
    public IBasicDTO add(IRequestInfoDTO ri, IBasicDTO hireProcdureDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
      HireProceduresDTO hireProcdureDTO1 =(HireProceduresDTO)hireProcdureDTO;
      if(DAO().duplicatedHireProcdureName( hireProcdureDTO1.getName(), null)){
              throw new InvalidDataEntryException();
          }
       return super.add(ri, hireProcdureDTO1);    
       
                                                                                       }
 
    //@Auditable
    public Boolean update(IRequestInfoDTO ri, IBasicDTO hireProcdureDTO) throws DataBaseException,
                                                                                       SharedApplicationException {
      HireProceduresDTO hireProcdureDTO1 =(HireProceduresDTO)hireProcdureDTO;
      if(DAO().duplicatedHireProcdureName( hireProcdureDTO1.getName(),Long.parseLong(hireProcdureDTO1.getCode().getKey()))){
              throw new InvalidDataEntryException();
          }
       return super.update(ri, hireProcdureDTO1);    
 
}
}
