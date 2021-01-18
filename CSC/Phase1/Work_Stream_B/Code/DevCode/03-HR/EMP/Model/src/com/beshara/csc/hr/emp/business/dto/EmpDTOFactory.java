package com.beshara.csc.hr.emp.business.dto;

//import com.beshara.csc.hr.emp.business.dto.EmployeeDocumentsDTO;
//import com.beshara.csc.hr.emp.business.dto.EmployeeProceduresDTO;
//import com.beshara.csc.hr.emp.business.dto.IEmployeeProceduresDTO;
//import com.beshara.csc.hr.emp.business.entity.DocumentTypesEntity;
//import com.beshara.csc.hr.emp.business.entity.EmployeeDocumentsEntity;
//import com.beshara.csc.hr.emp.business.entity.EmployeeProceduresEntity;
import com.beshara.csc.hr.emp.business.dto.empenquiry.EmpReqEnquiryDTO;
import com.beshara.csc.hr.emp.business.dto.empenquiry.IEmpReqEnquiryDTO;
import com.beshara.csc.hr.emp.business.dto.shared.ISimpleEmployeesDTO;
import com.beshara.csc.hr.emp.business.dto.shared.SimpleEmployeesDTO;
import com.beshara.csc.hr.emp.business.entity.AdminAssignmentsEntity;
import com.beshara.csc.hr.emp.business.entity.AllEmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.AssignReasonsEntity;
import com.beshara.csc.hr.emp.business.entity.AssignStatusEntity;
import com.beshara.csc.hr.emp.business.entity.AssignTypesEntity;
import com.beshara.csc.hr.emp.business.entity.DegreeDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidateStatusEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCandidatesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpCndSalaryElementsEntity;
import com.beshara.csc.hr.emp.business.entity.EmpEmployeeHistoriesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpExternalExperienceEntity;
import com.beshara.csc.hr.emp.business.entity.EmpExtraDataTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpInternalExperienceEntity;
import com.beshara.csc.hr.emp.business.entity.EmpReasonDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmpReasonTypesEntity;
import com.beshara.csc.hr.emp.business.entity.EmpWorkPermitsEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeeExtraDataEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeeHchildCatsEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeeNchildCatsEntity;
import com.beshara.csc.hr.emp.business.entity.EmployeesEntity;
import com.beshara.csc.hr.emp.business.entity.HireProceduresEntity;
import com.beshara.csc.hr.emp.business.entity.HireStagesEntity;
import com.beshara.csc.hr.emp.business.entity.HireStatusEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeConditionsEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypeProcedureEntity;
import com.beshara.csc.hr.emp.business.entity.HireTypesEntity;
import com.beshara.csc.hr.emp.business.entity.IncomeDataEntity;
import com.beshara.csc.hr.emp.business.entity.ProcedureResultsEntity;
import com.beshara.csc.hr.emp.business.entity.RaiseDataEntity;
import com.beshara.csc.hr.emp.business.entity.RaiseDataLogEntity;
import com.beshara.csc.hr.emp.business.entity.RequiredDocumentsEntity;
import com.beshara.csc.hr.emp.business.entity.TrxTypesEntity;


public class EmpDTOFactory {
    public static IAdminAssignmentsDTO createAdminAssignmentsDTO() {
        return new AdminAssignmentsDTO();
    }


    public static IAdminAssignmentsDTO createAdminAssignmentsDTO(AdminAssignmentsEntity adminAssignmentsEntity) {
        return new AdminAssignmentsDTO(adminAssignmentsEntity);
    }

    public static IAllEmployeesDTO createAllEmployeesDTO() {
        return new AllEmployeesDTO();
    }

    public static IAllEmployeesDTO createAllEmployeesDTO(AllEmployeesEntity allEmployeesEntity) {
        return new AllEmployeesDTO(allEmployeesEntity);
    }

    public static IAssignmentsSearchDTO createAssignmentsSearchDTO() {
        return new AssignmentsSearchDTO();
    }

    public static IAssignReasonsDTO createAssignReasonsDTO() {
        return new AssignReasonsDTO();
    }

    public static IAssignReasonsDTO createAssignReasonsDTO(Long code, String name) {
        return new AssignReasonsDTO(code, name);
    }

    public static IAssignReasonsDTO createAssignReasonsDTO(AssignReasonsEntity assignReasonsEntity) {
        return new AssignReasonsDTO(assignReasonsEntity);
    }

    public static IAssignStatusDTO createAssignStatusDTO() {
        return new AssignStatusDTO();
    }

    public static IAssignStatusDTO createAssignStatusDTO(Long code, String name) {
        return new AssignStatusDTO(code, name);
    }

    public static IAssignStatusDTO createAssignStatusDTO(AssignStatusEntity assignStatusEntity) {
        return new AssignStatusDTO(assignStatusEntity);
    }

    public static IAssignTypesDTO createAssignTypesDTO() {
        return new AssignTypesDTO();
    }

    public static IAssignTypesDTO createAssignTypesDTO(Long code, String name) {
        return new AssignTypesDTO(code, name);
    }

    public static IAssignTypesDTO createAssignTypesDTO(AssignTypesEntity assignTypesEntity) {
        return new AssignTypesDTO(assignTypesEntity);
    }

    public static IChangeBudgetTypeDTO createChangeBudgetTypeDTO() {
        return new ChangeBudgetTypeDTO();
    }

    public static IDegreeDataDTO createDegreeDataDTO() {
        return new DegreeDataDTO();
    }

    public static IDegreeDataDTO createDegreeDataDTO(DegreeDataEntity degreeDataEntity) {
        return new DegreeDataDTO(degreeDataEntity);
    }

    //    public static IDocumentTypesDTO createDocumentTypesDTO() {
    //        return new DocumentTypesDTO();
    //    }
    //
    //    public static IDocumentTypesDTO createDocumentTypesDTO(Long doctypeCode, String doctypeName) {
    //        return new DocumentTypesDTO(doctypeCode, doctypeName);
    //    }
    //
    //    public static IDocumentTypesDTO createDocumentTypesDTO(DocumentTypesEntity documentTypesEntity) {
    //        return new DocumentTypesDTO(documentTypesEntity);
    //    }

    public static IHireTypeProcedureDTO createHireTypeProcedureDTO() {
        return new HireTypeProcedureDTO();
    }

    public static IHireTypeProcedureDTO createHireTypeProcedureDTO(HireTypeProcedureEntity hireTypeProcedureEntity) {
        return new HireTypeProcedureDTO(hireTypeProcedureEntity);
    }

    public static IEmpDTO createEmpDTO() {
        return new EmpDTO();
    }

    public static IEmpEmployeeHistoriesDTO createEmpEmployeeHistoriesDTO() {
        return new EmpEmployeeHistoriesDTO();
    }

    public static IEmpEmployeeHistoriesDTO createEmpEmployeeHistoriesDTO(EmpEmployeeHistoriesEntity empEmployeeHistoriesEntity) {
        return new EmpEmployeeHistoriesDTO(empEmployeeHistoriesEntity);
    }

    public static IEmpEmployeeSearchDTO createEmpEmployeeSearchDTO() {
        return new EmpEmployeeSearchDTO();
    }

    //    public static IEmployeeDocumentsDTO createEmployeeDocumentsDTO() {
    //        return new EmployeeDocumentsDTO();
    //    }
    //
    //    public static IEmployeeDocumentsDTO createEmployeeDocumentsDTO(EmployeeDocumentsEntity employeeDocumentsEntity) {
    //        return new EmployeeDocumentsDTO(employeeDocumentsEntity);
    //    }

    public static IEmployeeHchildCatsDTO createEmployeeHchildCatsDTO() {
        return new EmployeeHchildCatsDTO();
    }

    public static IEmployeeHchildCatsDTO createEmployeeHchildCatsDTO(EmployeeHchildCatsEntity employeeHchildCatsEntity) {
        return new EmployeeHchildCatsDTO(employeeHchildCatsEntity);
    }

    public static IEmployeeNchildCatsDTO createEmployeeNchildCatsDTO() {
        return new EmployeeNchildCatsDTO();
    }

    public static IEmployeeNchildCatsDTO createEmployeeNchildCatsDTO(EmployeeNchildCatsEntity employeeNchildCatsEntity) {
        return new EmployeeNchildCatsDTO(employeeNchildCatsEntity);
    }

    //    public static IEmployeeProceduresDTO createEmployeeProceduresDTO() {
    //        return new EmployeeProceduresDTO();
    //    }
    //
    //    public static IEmployeeProceduresDTO createEmployeeProceduresDTO(EmployeeProceduresEntity employeeProceduresEntity) {
    //        return new EmployeeProceduresDTO(employeeProceduresEntity);
    //    }

    public static IEmployeesDTO createEmployeesDTO() {
        return new EmployeesDTO();
    }

    public static IEmpCandidateStatusDTO createEmpCandidateStatusDTO() {
        return new EmpCandidateStatusDTO();

    }

    public static IEmpCandidateStatusDTO createEmpCandidateStatusDTO(EmpCandidateStatusEntity empCandidateStatusEntity) {
        return new EmpCandidateStatusDTO(empCandidateStatusEntity);
    }

    @Deprecated
    public static IEmployeesDTO createEmployeesDTO(EmployeesEntity employeesEntity) {
        return new EmployeesDTO(employeesEntity);
    }

    public static IEmployeesDTO createSimpleEmployeesDTO(EmployeesEntity employeesEntity) {
        return new EmployeesDTO(employeesEntity, true);
    }

    public static IEmployeesDTO createEmployeesDTO(EmployeesEntity employeesEntity, boolean isSimple) {
        return new EmployeesDTO(employeesEntity, true);
    }
   
    

    public static IEmployeeSearchDTO createEmployeeSearchDTO() {
        return new EmployeeSearchDTO();
    }

    public static IHireProceduresDTO createHireProceduresDTO() {
        return new HireProceduresDTO();
    }

    public static IHireProceduresDTO createHireProceduresDTO(Long code, String name) {
        return new HireProceduresDTO(code, name);
    }

    public static IHireProceduresDTO createHireProceduresDTO(HireProceduresEntity hireProceduresEntity) {
        return new HireProceduresDTO(hireProceduresEntity);
    }

    public static IHireStagesDTO createHireStagesDTO() {
        return new HireStagesDTO();
    }

    public static IHireStagesDTO createHireStagesDTO(HireStagesEntity hireStagesEntity) {
        return new HireStagesDTO(hireStagesEntity);
    }

    public static IHireStagesDTO createHireStagesDTO(Long code, String name) {
        return new HireStagesDTO(code, name);
    }

    public static IHireStatusDTO createHireStatusDTO() {
        return new HireStatusDTO();
    }

    public static IHireStatusDTO createHireStatusDTO(HireStatusEntity hireStatusEntity) {
        return new HireStatusDTO(hireStatusEntity);
    }

    public static IHireStatusDTO createHireStatusDTO(Long code, String name) {
        return new HireStatusDTO(code, name);
    }

    public static IHireTypesDTO createHireTypesDTO() {
        return new HireTypesDTO();
    }

    public static IHireTypesDTO createHireTypesDTO(Long code, String name) {
        return new HireTypesDTO(code, name);
    }

    public static IHireTypesDTO createHireTypesDTO(HireTypesEntity hireTypesEntity) {
        return new HireTypesDTO(hireTypesEntity);
    }

    public static IIncomeDataDTO createIncomeDataDTO() {
        return new IncomeDataDTO();
    }

    public static IIncomeDataDTO createIncomeDataDTO(IncomeDataEntity incomeDataEntity) {
        return new IncomeDataDTO(incomeDataEntity);
    }

    public static IProcedureResultsDTO createProcedureResultsDTO() {
        return new ProcedureResultsDTO();
    }

    public static IProcedureResultsDTO createProcedureResultsDTO(ProcedureResultsEntity procedureResultsEntity) {
        return new ProcedureResultsDTO(procedureResultsEntity);
    }

    public static IRaiseDataDTO createRaiseDataDTO() {
        return new RaiseDataDTO();
    }

    public static IRaiseDataDTO createRaiseDataDTO(RaiseDataEntity raiseDataEntity) {
        return new RaiseDataDTO(raiseDataEntity);
    }

    public static IRaiseDataLogDTO createRaiseDataLogDTO() {
        return new RaiseDataLogDTO();
    }

    public static IRaiseDataLogDTO createRaiseDataLogDTO(RaiseDataLogEntity raiseDataLogEntity) {
        return new RaiseDataLogDTO(raiseDataLogEntity);
    }

    public static IRequiredDocumentsDTO createRequiredDocumentsDTO() {
        return new RequiredDocumentsDTO();
    }

    public static IRequiredDocumentsDTO createRequiredDocumentsDTO(RequiredDocumentsEntity requiredDocumentsEntity) {
        return new RequiredDocumentsDTO(requiredDocumentsEntity);
    }

    public static ISearchHireProceduresDTO createSearchHireProceduresDTO() {
        return new SearchHireProceduresDTO();
    }

    public static ISearchHireTypeDTO createSearchHireTypeDTO() {
        return new SearchHireTypeDTO();
    }

    public static ITrxTypesDTO createTrxTypesDTO() {
        return new TrxTypesDTO();
    }

    public static ITrxTypesDTO createTrxTypesDTO(TrxTypesEntity trxTypesEntity) {
        return new TrxTypesDTO(trxTypesEntity);
    }

    public static IEmpExtraDataTypesDTO createEmpExtraDataTypesDTO() {
        return new EmpExtraDataTypesDTO();
    }

    public static IEmpExtraDataValueDTO createEmpExtraDataValueDTO() {
        return new EmpExtraDataValueDTO();
    }

    public static IEmpExtraDataTypesDTO createEmpExtraDataTypesDTO(EmpExtraDataTypesEntity extraDataTypesEntity) {
        return new EmpExtraDataTypesDTO(extraDataTypesEntity);
    }

    public static IEmployeeExtraDataDTO createEmployeeExtraDataDTO() {
        return new EmployeeExtraDataDTO();
    }

    public static IEmployeeExtraDataDTO createEmployeeExtraDataDTO(EmployeeExtraDataEntity employeeExtraDataEntity) {
        return new EmployeeExtraDataDTO(employeeExtraDataEntity);
    }

    public static IEmployeeExtraDataSearchDTO createEmployeeExtraDataSearchDTO() {
        return new EmployeeExtraDataSearchDTO();
    }

    public static IEmpReasonTypesDTO createEmpReasonTypesDTO() {
        return new EmpReasonTypesDTO();
    }

    public static IEmpReasonTypesDTO createEmpReasonTypesDTO(EmpReasonTypesEntity empReasonTypesEntity) {
        return new EmpReasonTypesDTO(empReasonTypesEntity);
    }

    public static IEmpReasonDataDTO createEmpReasonDataDTO() {
        return new EmpReasonDataDTO();
    }

    public static IEmpReasonDataDTO createEmpReasonDataDTO(EmpReasonDataEntity empReasonDataEntity) {
        return new EmpReasonDataDTO(empReasonDataEntity);
    }

    public static IEmpInternalExperienceDTO createEmpInternalExperienceDTO() {
        return new EmpInternalExperienceDTO();
    }

    public static IEmpInternalExperienceDTO createEmpInternalExperienceDTO(EmpInternalExperienceEntity empInternalExperienceEntity) {
        return new EmpInternalExperienceDTO(empInternalExperienceEntity);
    }

    public static IEmpExternalExperienceDTO createEmpExternalExperienceDTO() {
        return new EmpExternalExperienceDTO();
    }

    public static IEmpExternalExperienceDTO createEmpExternalExperienceDTO(EmpExternalExperienceEntity empExternalExperienceEntity) {
        return new EmpExternalExperienceDTO(empExternalExperienceEntity);
    }

    public static IEmpExternalExperienceSearchDTO createEmpEmpExternalExperienceSearchDTO() {
        return new EmpExternalExperienceSearchDTO();
    }

    public static IHireTypeConditionsDTO createHireTypeConditionsDTO() {
        return new HireTypeConditionsDTO();
    }

    public static IHireTypeConditionsDTO createHireTypeConditionsDTO(HireTypeConditionsEntity hireTypeConditionsEntity) {
        return new HireTypeConditionsDTO(hireTypeConditionsEntity);
    }

    public static IEmpCandidateDocumentsDTO createEmpCandidateDocumentsDTO() {
        return new EmpCandidateDocumentsDTO();
    }

    public static IEmpCandidateDocumentsDTO createEmpCandidateDocumentsDTO(EmpCandidateDocumentsEntity empCandidateDocumentsEntity) {
        return new EmpCandidateDocumentsDTO(empCandidateDocumentsEntity);
    }

    public static IEmpCandidateExtraDataDTO createEmpCandidateExtraDataDTO() {
        return new EmpCandidateExtraDataDTO();
    }

    public static IEmpCandidateExtraDataDTO createEmpCandidateExtraDataDTO(EmpCandidateExtraDataEntity empCandidateExtraDataEntity) {
        return new EmpCandidateExtraDataDTO(empCandidateExtraDataEntity);
    }

    public static IEmpCandidateProceduresDTO createEmpCandidateProceduresDTO() {
        return new EmpCandidateProceduresDTO();
    }

    public static IEmpCandidateProceduresDTO createEmpCandidateProceduresDTO(EmpCandidateProceduresEntity empCandidateProceduresEntity) {
        return new EmpCandidateProceduresDTO(empCandidateProceduresEntity);
    }

    public static IEmpCandidatesDTO createEmpCandidatesDTO() {
        return new EmpCandidatesDTO();
    }

    public static IEmpCandidatesDTO createEmpCandidatesDTO(EmpCandidatesEntity empCandidatesEntity) {
        return new EmpCandidatesDTO(empCandidatesEntity);
    }

    public static IEmpCndSalaryElementsDTO createEmpCndSalaryElementsDTO() {
        return new EmpCndSalaryElementsDTO();
    }

    public static IEmpCndSalaryElementsDTO createEmpCndSalaryElementsDTO(EmpCndSalaryElementsEntity empCndSalaryElementsEntity) {
        return new EmpCndSalaryElementsDTO(empCndSalaryElementsEntity);
    }

    public static IEmpCandidateParent createEmpCandidateParent() {
        return new EmpCandidateParent();
    }

    public static ISimpleEmployeesDTO createSimpleEmployeesDTO() {
        return new SimpleEmployeesDTO();
    }

    public static IEmpContractTypesDTO createEmpContractTypesDTO() {
        return new EmpContractTypesDTO();
    }

    public static IEmpContractTypesDTO createEmpContractTypesDTO(Long code, String name) {
        return new EmpContractTypesDTO(code, name);
    }
    /*-------------------------------Added by o.ezzeldin----------------------------*/

    public static IEmployeesDTO createEmployeesDTOCivilRealCivil(EmployeesEntity employeesEntity) {

        return new EmployeesDTO(employeesEntity.getCivilId(), employeesEntity.getRealCivilId());
    }
    /*-------------------------------Added by o.ezzeldin----------------------------*/
    
    
    public static IEmpWorkPermitsDTO createEmpWorkPermitsDTO (EmpWorkPermitsEntity empWorkPermitsEntity) {
        return new EmpWorkPermitsDTO(empWorkPermitsEntity);
    }
    
    public static IEmpWorkPermitsDTO createEmpWorkPermitsDTO () {
        return new EmpWorkPermitsDTO();
    }
    
    public static IEmployeesDTO createEmployeesDTO(boolean withMinCode,EmployeesEntity employeesEntity)
    {
        return new EmployeesDTO (true,employeesEntity);
    } 
    
    
    
public static IEmpCriminalStatusDTO createEmpCriminalStatusDTO() {
        
 return new EmpCriminalStatusDTO () ;
    } 

public static IEmpMoiWsCriminalDTO createEmpMoiWsCriminalDTO() {
        
 return new EmpMoiWsCriminalDTO () ;
    } 

public static IEmpMinEdtypesDTO createEmpMinEdtypesDTO() {
        
 return new EmpMinEdtypesDTO () ;
    }

    public static IEmpReqEnquiryDTO createEmpReqEnquiryDTO() {
        return new EmpReqEnquiryDTO();
    }

}