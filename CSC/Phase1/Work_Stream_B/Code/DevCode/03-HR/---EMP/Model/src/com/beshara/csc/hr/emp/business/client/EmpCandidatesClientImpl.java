package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.deploy.EmpCandidatesSession;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.ParametersDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.TerminationReturnType;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.FinderException;


public class EmpCandidatesClientImpl extends BaseClientImpl3 implements IEmpCandidatesClient {


    public EmpCandidatesClientImpl() {
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return EmpCandidatesSession.class;
    }

    protected EmpCandidatesSession SESSION() {
        return (EmpCandidatesSession)super.SESSION();
    }

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws DataBaseException
     */
    public Boolean isMinistryFileNoExist(String ministryFileNo) throws DataBaseException {
        try {
            return SESSION().isMinistryFileNoExist(RI(), ministryFileNo);
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            return SESSION().getByHireStageWithPaging(RI(), _searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO, int manageId) throws DataBaseException,
                                                                                                  SharedApplicationException {
        try {
            return SESSION().getByHireStageWithPaging(RI(), _searchDTO, manageId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> filterByHireType(Long hirtypeCode) throws DataBaseException {
        try {
            return SESSION().filterByHireType(RI(), hirtypeCode);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> filterByHireTypeWithMin(Long hirtypeCode, Long minCode) throws DataBaseException {
        try {
            return SESSION().filterByHireTypeWithMin(RI(), hirtypeCode, minCode);
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> filterByAllHireTypes() throws DataBaseException {
        try {
            return SESSION().filterByAllHireTypes(RI());
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }


    public List<IBasicDTO> filterByAllHireTypesWithMin(Long minCode) throws DataBaseException {
        try {
            return SESSION().filterByAllHireTypesWithMin(RI(), minCode);
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public List<IBasicDTO> filterByAllHireTypesForHireExecute() throws DataBaseException {
        try {
            return SESSION().filterByAllHireTypesForHireExecute(RI());
        } catch (Exception e) {

            throw getWrappedException(e);
        }
    }

    /**
     * filter employees by hire types for employee hire execution
     * @param hirtypeCode
     * @return
     * @throws RemoteException
     * @throws FinderException
     * @author Dina
     */
    public List<IBasicDTO> filterByHireTypeForHireExecute(Long hirtypeCode, Long loggedMin) throws DataBaseException {
        try {
            return SESSION().filterByHireTypeForHireExecute(RI(), hirtypeCode, loggedMin);
        } catch (Exception e) {

            throw getWrappedException(e);
        }
    }


    public List<IBasicDTO> getFilterEmpWaitingForHireDecWithDiffStage(IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                             SharedApplicationException {
        try {

            return SESSION().getFilterEmpWaitingForHireDecWithDiffStage(RI(), empEmployeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IBasicDTO empEmployeeSearchDTO) throws DataBaseException,
                                                                                                          SharedApplicationException {
        try {
            return SESSION().getFilterEmployeeWaitingForHireDecision(RI(), empEmployeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IEmpCandidatesDTO getByCndCodeAndSequnc(IEntityKey id1) throws DataBaseException,
                                                                          SharedApplicationException {


        try {
            return SESSION().getByCndCodeAndSequnc(RI(), id1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IPagingResponseDTO searchEmployeeToCompleteData(IBasicDTO employeeSearchDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            return SESSION().searchEmployeeToCompleteData(RI(), employeeSearchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO updateEmp(IBasicDTO empCandidateDTO1) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().updateEmpCMT(RI(), empCandidateDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    
    public IBasicDTO updateEmpData(IBasicDTO empCandidateDTO1) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().updateEmpData(RI(), empCandidateDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO addNewSeqForCandidate(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                              SharedApplicationException {


        try {
            return SESSION().addNewSeqForCandidate(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }


    }

    public IBasicDTO adoptionOfFinal(IBasicDTO empCandidatesDTO1) throws DataBaseException,
                                                                         SharedApplicationException {
        try {
            return SESSION().AdoptionOfFinal(RI(), empCandidatesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public boolean checkEmployeeByExtraDataType(Long civilId, Long extDatatype,Long candidateCode) throws DataBaseException,
                                                                                       SharedApplicationException {

        try {
            return SESSION().checkEmployeeByExtraDataType(RI(), civilId, extDatatype,candidateCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public boolean suggestNewJob(IBasicDTO empCandidatesDTO, IBasicDTO extraDataDTO) throws DataBaseException,
                                                                                            SharedApplicationException {
        try {
            return SESSION().suggestNewJob(RI(), empCandidatesDTO, extraDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO proceedContractPersonData(IBasicDTO empCandidatesDTO,
                                               IBasicDTO extraDataDTO) throws DataBaseException,
                                                                              SharedApplicationException {
        try {
            return SESSION().proceedContractPersonData(RI(), empCandidatesDTO, extraDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO updateEmpCandCMT(IBasicDTO employeesDTO1) throws SharedApplicationException, DataBaseException {
        try {
            return SESSION().updateEmpCandCMT(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO updateEmpCancelCandidate(IBasicDTO employeesDTO1) throws SharedApplicationException,
                                                                              DataBaseException {
        try {
            return SESSION().updateEmpCancelCandidate(RI(), employeesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    //
    //    public boolean suggestAndConvertToDewan( IBasicDTO empCandidatesDTO,
    //                                            IBasicDTO extraDataDTO) throws  DataBaseException,
    //    SharedApplicationException{
    //        try {
    //            return SESSION().suggestAndConvertToDewan(RI(),empCandidatesDTO,extraDataDTO);
    //        }  catch (SharedApplicationException e) {
    //            throw e;
    //        } catch (Exception e) {
    //            throw getWrappedException(e);
    //        }
    //
    //    }

    public IBasicDTO applyJobSuggestion(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().applyJobSuggestion(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO proceedCRSPersonData(IBasicDTO empCandidatesDTO,
                                          List<IEmpCandidateExtraDataDTO> extraDataDTO) throws DataBaseException,
                                                                                               SharedApplicationException {
        try {
            return SESSION().proceedCRSPersonData(RI(), empCandidatesDTO, extraDataDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public IBasicDTO applyCRSSuggestion(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                           SharedApplicationException {
        try {
            return SESSION().applyCRSSuggestion(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean approveFinancialDegreeFatwa(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().approveFinancialDegreeFatwa(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean fromFatwaToChoiceDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().fromFatwaToChoiceDept(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean fromOrderToChoiceDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().fromOrderToChoiceDept(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean approveJobOrderDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().approveJobOrderDept(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    
    public boolean saveJobOrderDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                          SharedApplicationException {
        try {
            return SESSION().saveJobOrderDept(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public boolean fromChoiceToOrder(IBasicDTO empCandidatesDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().fromChoiceToOrder(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }
    
    
    public boolean fromChoiceToFatwa(IBasicDTO empCandidatesDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().fromChoiceToFatwa(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public boolean returnFromChoiceToOrg(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().returnFromChoiceToOrg(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean approveDataChoiceDept(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().approveDataChoiceDept(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IEmpCandidatesDTO> checkIfCitizenIsCandidate(Long civilId) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().checkIfCitizenIsCandidate(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public IBasicDTO addCandidateInfo(IBasicDTO empCandidatesDTO1) throws SharedApplicationException,
                                                                          DataBaseException {
        try {
            return SESSION().addCandidateInfo(RI(), empCandidatesDTO1);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public int isFilNumRedundant(Long minCode, String minFileNo, Long civilId) throws DataBaseException,
                                                                                      SharedApplicationException {
        try {
            return SESSION().isFilNumRedundant(RI(), minCode, minFileNo, civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> filterByAllHireTypeForHireExecute(Long loggedMin) throws DataBaseException,
                                                                                    SharedApplicationException {

        try {
            return SESSION().filterByAllHireTypeForHireExecute(RI(), loggedMin);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean executeEmployment(IBasicDTO dto) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().executeEmployment(RI(), dto);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean validateChildRaise(IEmpCandidatesDTO cndDTO) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().validateChildRaise(RI(), cndDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public List<IBasicDTO> searchForHireExecute(Long loggedMin, Long civilId, String name,
                                                Long hireTypeKey) throws DataBaseException,
                                                                         SharedApplicationException {

        try {
            return SESSION().searchForHireExecute(RI(), loggedMin, civilId, name, hireTypeKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> checkDecisionHire(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                       SharedApplicationException {

        try {
            return SESSION().checkDecisionHire(RI(), empCandidatesList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean applyDecisionForHire(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                  SharedApplicationException {
        try {
            return SESSION().applyDecisionForHire(RI(), empCandidatesList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> checkApplyHire(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                    SharedApplicationException {
        try {
            return SESSION().checkApplyHire(RI(), empCandidatesList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean applyHire(List<IBasicDTO> empCandidatesList) throws DataBaseException, SharedApplicationException {
        try {
            return SESSION().applyHire(RI(), empCandidatesList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public List<IBasicDTO> searchForSpecificEmployeeByLoggedMin(IBasicDTO basicDTO) throws DataBaseException,
                                                                                           SharedApplicationException {

        try {
            return SESSION().searchForSpecificEmployeeByLoggedMin(RI(), basicDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List<IBasicDTO> getOtherCandByCivils(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                          SharedApplicationException {
        try {
            return SESSION().getOtherCandByCivils(RI(), empCandidatesList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public List<IBasicDTO> checkConditions(List<IBasicDTO> empCandidatesList) throws DataBaseException,
                                                                                     SharedApplicationException {
        try {
            return SESSION().checkConditions(RI(), empCandidatesList);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }


    public boolean checkEmployeeInNeed(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().checkEmployeeInNeed(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public List getAllRequests(IEmployeeSearchDTO searchDTO) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getAllRequests(RI(), searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Long countCitizensRegisteredOnQual(Long qualificationKey) throws DataBaseException,
                                                                            SharedApplicationException {
        try {
            return SESSION().countCitizensRegisteredOnQual(RI(), qualificationKey);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public boolean approveDataByCivilService(IBasicDTO empCandidatesDTO) throws DataBaseException,
                                                                                SharedApplicationException {
        try {
            return SESSION().approveDataByCivilService(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public void addNewEmpVacBalance(IEmpCandidatesDTO empCandidatesDTO) throws DataBaseException,
                                                                               SharedApplicationException {
        try {
            SESSION().addNewEmpVacBalance(RI(), empCandidatesDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }

    public Boolean executeFinanciaPackage(IEmpCandidatesDTO dto, Long elementGuide,
                                          Long eqDegreeCode) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().executeFinanciaPackage(RI(), dto, elementGuide, eqDegreeCode);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }

    public Long getCandidateCode(Long civilId) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().getCandidateCode(RI(), civilId);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }

    }


    public String queryCrimnalStatus(Long realCivil) throws DataBaseException, SharedApplicationException {

        try {
            return SESSION().queryCrimnalStatus(RI(), realCivil);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    
    public Long getHirDateCount(Long candidateCode) throws DataBaseException, SharedApplicationException{
        
            try {
                return SESSION().getHirDateCount(RI(), candidateCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            }
        }
    
    public IPagingResponseDTO getAllCandidateDataWithPaging(IBasicDTO _searchDTO) throws DataBaseException,
                                                                                              SharedApplicationException {
        try {
            return SESSION().getAllCandidateDataWithPaging(RI(), _searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        }
    }
    
    public String getCandidateName(IBasicDTO searchDTO) throws DataBaseException,
                                                                                SharedApplicationException{
        try {
            return SESSION().getCandidateName(RI(), searchDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        } 
    }
    
    public String getPreviosNotes(IBasicDTO empDTO) throws DataBaseException,
                                                                                SharedApplicationException{
        try {
            return SESSION().getPreviosNotes(RI(), empDTO);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        } 
    }
    
    public  TerminationReturnType terminate(ParametersDTO dto) throws DataBaseException,
                                                                        SharedApplicationException{
        try {
            return SESSION().terminate(RI(), dto);
        } catch (SharedApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw getWrappedException(e);
        } 
    }
    
    public Boolean chkWrkCenterStatus(Long minCode, String wrkCode) throws DataBaseException,
    SharedApplicationException{
            try {
                return SESSION().chkWrkCenterStatus(RI(), minCode,wrkCode);
            } catch (SharedApplicationException e) {
                throw e;
            } catch (Exception e) {
                throw getWrappedException(e);
            } 
        }
}
