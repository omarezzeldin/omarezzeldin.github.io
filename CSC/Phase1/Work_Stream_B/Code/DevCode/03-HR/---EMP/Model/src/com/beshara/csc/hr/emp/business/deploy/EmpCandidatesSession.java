package com.beshara.csc.hr.emp.business.deploy;


import com.beshara.base.deploy.BasicSession;
import com.beshara.base.dto.IBasicDTO;
import com.beshara.base.entity.IEntityKey;
import com.beshara.base.paging.IPagingResponseDTO;
import com.beshara.base.requestinfo.IRequestInfoDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidateExtraDataDTO;
import com.beshara.csc.hr.emp.business.dto.IEmpCandidatesDTO;
import com.beshara.csc.hr.emp.business.dto.IEmployeeSearchDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.ParametersDTO;
import com.beshara.csc.hr.emp.business.dto.pifsswebservice.TerminationReturnType;
import com.beshara.csc.sharedutils.business.exception.DataBaseException;
import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import java.rmi.RemoteException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface EmpCandidatesSession extends BasicSession {

    /**
     * check if an minFileNo exists * @param ministryFileNo
     * @return Boolean
     * @throws RemoteException
     */
    Boolean isMinistryFileNoExist(IRequestInfoDTO ri, String ministryFileNo) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;

    //public IPagingResponseDTO getByHireStageWithPaging(IBasicDTO _searchDTO) throws RemoteException, DataBaseException, SharedApplicationException;

    public IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO ri,
                                                       IBasicDTO _searchDTO) throws RemoteException,
                                                                                    SharedApplicationException,
                                                                                    DataBaseException;

    public IPagingResponseDTO getByHireStageWithPaging(IRequestInfoDTO ri, IBasicDTO _searchDTO,
                                                       int manageId) throws RemoteException, DataBaseException,
                                                                            SharedApplicationException;

    public List<IBasicDTO> filterByAllHireTypesForHireExecute(IRequestInfoDTO ri) throws RemoteException,
                                                                                         DataBaseException,
                                                                                         SharedApplicationException;


    public List<IBasicDTO> filterByHireType(IRequestInfoDTO ri, Long hirtypeCode) throws RemoteException,
                                                                                         SharedApplicationException,
                                                                                         DataBaseException;

    public List<IBasicDTO> filterByHireTypeWithMin(IRequestInfoDTO ri, Long hirtypeCode,
                                                   Long minCode) throws RemoteException, SharedApplicationException,
                                                                        DataBaseException;

    public List<IBasicDTO> filterByAllHireTypes(IRequestInfoDTO ri) throws RemoteException, SharedApplicationException,
                                                                           DataBaseException;

    public List<IBasicDTO> filterByAllHireTypesWithMin(IRequestInfoDTO ri, Long minCode) throws RemoteException,
                                                                                                SharedApplicationException,
                                                                                                DataBaseException;

    public List<IBasicDTO> filterByHireTypeForHireExecute(IRequestInfoDTO ri, Long hirtypeCode,
                                                          Long loggedMin) throws RemoteException, DataBaseException,
                                                                                 SharedApplicationException;

    public List<IBasicDTO> getFilterEmpWaitingForHireDecWithDiffStage(IRequestInfoDTO ri,
                                                                      IBasicDTO empEmployeeSearchDTO1) throws RemoteException,
                                                                                                              DataBaseException,
                                                                                                              SharedApplicationException;

    public List<IBasicDTO> getFilterEmployeeWaitingForHireDecision(IRequestInfoDTO ri,
                                                                   IBasicDTO empEmployeeSearchDTO) throws RemoteException,
                                                                                                          SharedApplicationException,
                                                                                                          DataBaseException;

    public IPagingResponseDTO searchEmployeeToCompleteData(IRequestInfoDTO ri,
                                                        IBasicDTO employeeSearchDTO) throws RemoteException,
                                                                                            SharedApplicationException,
                                                                                            DataBaseException;


    public IEmpCandidatesDTO getByCndCodeAndSequnc(IRequestInfoDTO ri, IEntityKey id1) throws RemoteException,
                                                                                              SharedApplicationException,
                                                                                              DataBaseException;


    public IBasicDTO addNewSeqForCandidate(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                  SharedApplicationException,
                                                                                                  DataBaseException;

    public IBasicDTO AdoptionOfFinal(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO1) throws RemoteException,
                                                                                             SharedApplicationException,
                                                                                             DataBaseException;

    public boolean checkEmployeeByExtraDataType(IRequestInfoDTO ri, Long civilId,
                                                Long extDatatype,Long candidateCode) throws RemoteException, SharedApplicationException,
                                                                         DataBaseException;

    public boolean suggestNewJob(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
                                 IBasicDTO extraDataDTO) throws RemoteException, DataBaseException,
                                                                SharedApplicationException;

    public IBasicDTO proceedContractPersonData(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
                                               IBasicDTO extraDataDTO) throws RemoteException, DataBaseException,
                                                                              SharedApplicationException;

    public IBasicDTO updateEmpCandCMT(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws RemoteException,
                                                                                          SharedApplicationException,
                                                                                          DataBaseException;

    public IBasicDTO updateEmpCancelCandidate(IRequestInfoDTO ri, IBasicDTO employeesDTO1) throws RemoteException,
                                                                                                  SharedApplicationException,
                                                                                                  DataBaseException;

    public IBasicDTO updateEmpCMT(IRequestInfoDTO ri, IBasicDTO empCandidateDTO1) throws RemoteException,
                                                                                         SharedApplicationException,
                                                                                         DataBaseException;
    
    public IBasicDTO updateEmpData(IRequestInfoDTO ri, IBasicDTO empCandidateDTO1) throws RemoteException,
                                                                                         SharedApplicationException,
                                                                                         DataBaseException;
    //    public boolean suggestAndConvertToDewan(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
    //                                            IBasicDTO extraDataDTO) throws RemoteException, DataBaseException,
    //                                                                           SharedApplicationException ;

    public IBasicDTO applyJobSuggestion(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    public IBasicDTO proceedCRSPersonData(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO,
                                          List<IEmpCandidateExtraDataDTO> extraDataDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    public IBasicDTO applyCRSSuggestion(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                               DataBaseException,
                                                                                               SharedApplicationException;

    public boolean approveFinancialDegreeFatwa(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;

    public boolean fromFatwaToChoiceDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public boolean approveJobOrderDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                              DataBaseException,
                                                                                              SharedApplicationException;
    
    public boolean saveJobOrderDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                              DataBaseException,
                                                                                              SharedApplicationException;
    

    public boolean fromOrderToChoiceDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public boolean fromChoiceToOrder(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;
    
    public boolean fromChoiceToFatwa(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                            DataBaseException,
                                                                                            SharedApplicationException;

    public boolean returnFromChoiceToOrg(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public boolean approveDataChoiceDept(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;

    public boolean approveDataByCivilService(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO) throws RemoteException,
                                                                                                    DataBaseException,
                                                                                                    SharedApplicationException;

    public List<IEmpCandidatesDTO> checkIfCitizenIsCandidate(IRequestInfoDTO ri, Long civilId) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;


    public IBasicDTO addCandidateInfo(IRequestInfoDTO ri, IBasicDTO empCandidatesDTO1) throws RemoteException,
                                                                                              SharedApplicationException,
                                                                                              DataBaseException;

    public int isFilNumRedundant(IRequestInfoDTO ri, Long minCode, String minFileNo,
                                 Long civilId) throws RemoteException, DataBaseException, SharedApplicationException;

    public List<IBasicDTO> filterByAllHireTypeForHireExecute(IRequestInfoDTO ri,
                                                             Long loggedMin) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;

    public Boolean executeEmployment(IRequestInfoDTO ri, IBasicDTO dto) throws RemoteException, DataBaseException,
                                                                               SharedApplicationException;

    public boolean validateChildRaise(IRequestInfoDTO ri, IEmpCandidatesDTO cndDTO) throws DataBaseException,
                                                                                           RemoteException,
                                                                                           SharedApplicationException;

    public List<IBasicDTO> searchForHireExecute(IRequestInfoDTO ri, Long loggedMin, Long civilId, String name,
                                                Long hireTypeKey) throws RemoteException, DataBaseException,
                                                                         SharedApplicationException;

    public List<IBasicDTO> checkDecisionHire(IRequestInfoDTO ri,
                                             List<IBasicDTO> empCandidatesList) throws RemoteException,
                                                                                       DataBaseException,
                                                                                       SharedApplicationException;

    public boolean applyDecisionForHire(IRequestInfoDTO ri, List<IBasicDTO> empCandidatesList) throws RemoteException,
                                                                                                      DataBaseException,
                                                                                                      SharedApplicationException;


    public List<IBasicDTO> getOtherCandByCivils(IRequestInfoDTO ri,
                                                List<IBasicDTO> empCandidatesList) throws RemoteException,
                                                                                          DataBaseException,
                                                                                          SharedApplicationException;


    public boolean applyHire(IRequestInfoDTO ri, List<IBasicDTO> empCandidatesList) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public List<IBasicDTO> searchForSpecificEmployeeByLoggedMin(IRequestInfoDTO ri,
                                                                IBasicDTO basicDTO) throws RemoteException,
                                                                                           DataBaseException,
                                                                                           SharedApplicationException;

    public List<IBasicDTO> checkApplyHire(IRequestInfoDTO ri,
                                          List<IBasicDTO> empCandidatesList) throws RemoteException, DataBaseException,
                                                                                    SharedApplicationException;

    public List<IBasicDTO> checkConditions(IRequestInfoDTO ri,
                                           List<IBasicDTO> empCandidatesList) throws RemoteException,
                                                                                     DataBaseException,
                                                                                     SharedApplicationException;

    public boolean checkEmployeeInNeed(IRequestInfoDTO ri, Long civilId) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;


    public List getAllRequests(IRequestInfoDTO ri, IEmployeeSearchDTO searchDTO) throws RemoteException, DataBaseException,
                                                                          SharedApplicationException;

    public Long countCitizensRegisteredOnQual(IRequestInfoDTO ri, Long qualificationKey) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;
    
    public void addNewEmpVacBalance(IRequestInfoDTO ri, IEmpCandidatesDTO dto) throws RemoteException,
                                                                                                DataBaseException,
                                                                                                SharedApplicationException;
    
    public Boolean executeFinanciaPackage(IRequestInfoDTO ri, IEmpCandidatesDTO dto, Long elementGuide,
                                          Long eqDegreeCode) throws RemoteException, DataBaseException,
                                                                                SharedApplicationException;
    
    public Long getCandidateCode(IRequestInfoDTO ri,Long civilId) throws RemoteException,DataBaseException, SharedApplicationException;
    
    public String queryCrimnalStatus(IRequestInfoDTO ri,Long realCivil) throws RemoteException,DataBaseException,
                                                                          SharedApplicationException ;
    
    public Long getHirDateCount(IRequestInfoDTO ri,Long candidateCode) throws RemoteException,DataBaseException, SharedApplicationException;
    
    IPagingResponseDTO getAllCandidateDataWithPaging(IRequestInfoDTO requestInfoDTO,
                                                  IBasicDTO _searchDTO) throws RemoteException, DataBaseException,
                                                                               SharedApplicationException;
    
    public String getCandidateName(IRequestInfoDTO requestInfoDTO,IBasicDTO searchDTO) throws RemoteException,DataBaseException,
                                                                            SharedApplicationException;
    
    
    public String getPreviosNotes(IRequestInfoDTO requestInfoDTO,IBasicDTO empDTO) throws RemoteException,DataBaseException,
                                                                            SharedApplicationException;
    
    public  TerminationReturnType terminate(IRequestInfoDTO ri, ParametersDTO dto) throws RemoteException,DataBaseException,
                                                                                            SharedApplicationException;

    public Boolean chkWrkCenterStatus(IRequestInfoDTO iRequestInfoDTO,Long minCode, String wrkCode) throws RemoteException,DataBaseException,
    SharedApplicationException;
}
