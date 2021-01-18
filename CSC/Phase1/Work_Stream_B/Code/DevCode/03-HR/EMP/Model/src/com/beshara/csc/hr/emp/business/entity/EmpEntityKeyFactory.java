package com.beshara.csc.hr.emp.business.entity;

import java.math.BigDecimal;

import java.sql.Timestamp;


public class EmpEntityKeyFactory {
    public static IAdminAssignmentsEntityKey createAdminAssignmentsEntityKey() {
        return new AdminAssignmentsEntityKey();
    }

    public static IAdminAssignmentsEntityKey createAdminAssignmentsEntityKey(Long admassignmentSerial) {
        return new AdminAssignmentsEntityKey(admassignmentSerial);
    }

    public static IAllEmployeesEntityKey createAllEmployeesEntityKey() {
        return new AllEmployeesEntityKey();
    }

    public static IAllEmployeesEntityKey createAllEmployeesEntityKey(Long civilId, Timestamp trxDatetime) {
        return new AllEmployeesEntityKey(civilId, trxDatetime);
    }

    public static IAssignReasonsEntityKey createAssignReasonsEntityKey() {
        return new AssignReasonsEntityKey();
    }

    public static IAssignReasonsEntityKey createAssignReasonsEntityKey(Long assreasonCode) {
        return new AssignReasonsEntityKey(assreasonCode);
    }

    public static IAssignStatusEntityKey createAssignStatusEntityKey() {
        return new AssignStatusEntityKey();
    }

    public static IAssignStatusEntityKey createAssignStatusEntityKey(Long assstatusCode) {
        return new AssignStatusEntityKey(assstatusCode);
    }

    public static IAssignTypesEntityKey createAssignTypesEntityKey() {
        return new AssignTypesEntityKey();
    }

    public static IAssignTypesEntityKey createAssignTypesEntityKey(Long asstypeCode) {
        return new AssignTypesEntityKey(asstypeCode);
    }

//    public static IDocumentTypesEntityKey createDocumentTypesEntityKey() {
//        return new DocumentTypesEntityKey();
//    }

//    public static IDocumentTypesEntityKey createDocumentTypesEntityKey(Long doctypeCode) {
//        return new DocumentTypesEntityKey(doctypeCode);
//    }

    public static IEmpEmployeeHistoriesEntityKey createEmpEmployeeHistoriesEntityKey() {
        return new EmpEmployeeHistoriesEntityKey();
    }

    public static IEmpEmployeeHistoriesEntityKey createEmpEmployeeHistoriesEntityKey(Long serial) {
        return new EmpEmployeeHistoriesEntityKey(serial);
    }

//    public static IEmployeeDocumentsEntityKey createEmployeeDocumentsEntityKey() {
//        return new EmployeeDocumentsEntityKey();
//    }
//
//    public static IEmployeeDocumentsEntityKey createEmployeeDocumentsEntityKey(Long civilId, Long doctypeCode) {
//        return new EmployeeDocumentsEntityKey(civilId, doctypeCode);
//    }
//
//    public static IEmployeeProceduresEntityKey createEmployeeProceduresEntityKey() {
//        return new EmployeeProceduresEntityKey();
//    }
//
//    public static IEmployeeProceduresEntityKey createEmployeeProceduresEntityKey(Long civilId, Long hirprocedureCode) {
//        return new EmployeeProceduresEntityKey(civilId, hirprocedureCode);
//    }

    public static IEmployeesEntityKey createEmployeesEntityKey() {
        return new EmployeesEntityKey();
    }

    public static IEmployeesEntityKey createEmployeesEntityKey(Long civilId) {
        return new EmployeesEntityKey(civilId);
    }

    public static IHireProceduresEntityKey createHireProceduresEntityKey() {
        return new HireProceduresEntityKey();
    }

    public static IHireProceduresEntityKey createHireProceduresEntityKey(Long hirprocedureCode) {
        return new HireProceduresEntityKey(hirprocedureCode);
    }

    public static IHireStagesEntityKey createHireStagesEntityKey() {
        return new HireStagesEntityKey();
    }

    public static IHireStagesEntityKey createHireStagesEntityKey(Long hirstageCode) {
        return new HireStagesEntityKey(hirstageCode);
    }

    public static IHireStatusEntityKey createHireStatusEntityKey() {
        return new HireStatusEntityKey();
    }

    public static IHireStatusEntityKey createHireStatusEntityKey(Long hirstatusCode) {
        return new HireStatusEntityKey(hirstatusCode);
    }

    public static IHireTypesEntityKey createHireTypesEntityKey() {
        return new HireTypesEntityKey();
    }

    public static IHireTypesEntityKey createHireTypesEntityKey(Long hirtypeCode) {
        return new HireTypesEntityKey(hirtypeCode);
    }

    public static IProcedureResultsEntityKey createProcedureResultsEntityKey() {
        return new ProcedureResultsEntityKey();
    }

    public static IProcedureResultsEntityKey createProcedureResultsEntityKey(Long proresultCode) {
        return new ProcedureResultsEntityKey(proresultCode);
    }

    public static IRequiredDocumentsEntityKey createRequiredDocumentsEntityKey() {
        return new RequiredDocumentsEntityKey();
    }

    public static IRequiredDocumentsEntityKey createRequiredDocumentsEntityKey(Long hirtypeCode, Long doctypeCode) {
        return new RequiredDocumentsEntityKey(hirtypeCode, doctypeCode);
    }

    public static IHireTypeProcedureEntityKey createHireTypeProcedureEntityKey() {
        return new HireTypeProcedureEntityKey();
    }

    public static IHireTypeProcedureEntityKey createHireTypeProcedureEntityKey(Long hirtypeCode,
                                                                               Long hirprocedureCode) {
        return new HireTypeProcedureEntityKey(hirtypeCode, hirprocedureCode);
    }


    public static IHireTypeConditionsEntityKey createHireTypeConditionsEntityKey() {
        return new HireTypeConditionsEntityKey();
    }

    public static IHireTypeConditionsEntityKey createHireTypeConditionsEntityKey(Long conditionCode) {
        return new HireTypeConditionsEntityKey(conditionCode);
    }

    public static ITrxTypesEntityKey createTrxTypesEntityKey() {
        return new TrxTypesEntityKey();
    }

    public static ITrxTypesEntityKey createTrxTypesEntityKey(Long trxtypeCode) {
        return new TrxTypesEntityKey(trxtypeCode);
    }

    public static IEmpExtraDataTypesEntityKey createEmpExtraDataTypesEntityKey() {
        return new EmpExtraDataTypesEntityKey();
    }

    public static IEmpExtraDataTypesEntityKey createEmpExtraDataTypesEntityKey(Long extdattypeCode) {
        return new EmpExtraDataTypesEntityKey(extdattypeCode);
    }

    public static IEmployeeExtraDataEntityKey createEmployeeExtraDataEntityKey() {
        return new EmployeeExtraDataEntityKey();
    }

    public static IEmployeeExtraDataEntityKey createEmployeeExtraDataEntityKey(Long serial) {
        return new EmployeeExtraDataEntityKey(serial);
    }

    public static IEmpReasonTypesEntityKey createEmpReasonTypesEntityKey() {
        return new EmpReasonTypesEntityKey();
    }

    public static IEmpReasonTypesEntityKey createEmpReasonTypesEntityKey(Long restypeCode) {
        return new EmpReasonTypesEntityKey(restypeCode);
    }

    public static IEmpReasonDataEntityKey createEmpReasonDataEntityKey() {
        return new EmpReasonDataEntityKey();
    }

    public static IEmpReasonDataEntityKey createEmpReasonDataEntityKey(Long restypeCode, Long resdatSerial) {
        return new EmpReasonDataEntityKey(restypeCode, resdatSerial);
    }

    public static IEmpInternalExperienceEntityKey createEmpInternalExperienceEntityKey() {
        return new EmpInternalExperienceEntityKey();
    }

    public static IEmpInternalExperienceEntityKey createEmpInternalExperienceEntityKey(Long serial) {
        return new EmpInternalExperienceEntityKey(serial);
    }

    public static IEmpExternalExperienceEntityKey createEmpExternalExperienceEntityKey(BigDecimal serial) {
        return new EmpExternalExperienceEntityKey(serial);
    }

    public static IEmpCandidateStatusEntityKey createEmpCandidateStatusEntityKey() {
        return new EmpCandidateStatusEntityKey();
    }

    public static IEmpCandidateStatusEntityKey createEmpCandidateStatusEntityKey(Long cndstatusCode) {
        return new EmpCandidateStatusEntityKey(cndstatusCode);
    }

    public static IEmpCandidateDocumentsEntityKey createEmpCandidateDocumentsEntityKey() {
        return new EmpCandidateDocumentsEntityKey();
    }

    public static IEmpCandidateDocumentsEntityKey createEmpCandidateDocumentsEntityKey(Long candidateCode,
                                                                                       Long candidateCodeSeq,
                                                                                       Long cnddocSerial) {
        return new EmpCandidateDocumentsEntityKey(candidateCode, candidateCodeSeq, cnddocSerial);
    }

    public static IEmpCandidateExtraDataEntityKey createEmpCandidateExtraDataEntityKey() {
        return new EmpCandidateExtraDataEntityKey();
    }

    public static IEmpCandidateExtraDataEntityKey createEmpCandidateExtraDataEntityKey(Long serial) {
        return new EmpCandidateExtraDataEntityKey(serial);
    }

    public static IEmpCandidateProceduresEntityKey createEmpCandidateProceduresEntityKey() {
        return new EmpCandidateProceduresEntityKey();
    }

    public static IEmpCandidateProceduresEntityKey createEmpCandidateProceduresEntityKey(Long candidateCode,
                                                                                         Long candidateCodeSeq,
                                                                                         Long hirProcedureCode) {
        return new EmpCandidateProceduresEntityKey(candidateCode, candidateCodeSeq, hirProcedureCode);
    }

    public static IEmpCndSalaryElementsEntityKey createEmpCndSalaryElementsEntityKey() {
        return new EmpCndSalaryElementsEntityKey();
    }

    public static IEmpCndSalaryElementsEntityKey createEmpCndSalaryElementsEntityKey(Long cndSalelmSerial) {
        return new EmpCndSalaryElementsEntityKey(cndSalelmSerial);
    }

    public static IEmpCandidatesEntityKey createEmpCandidatesEntityKey() {
        return new EmpCandidatesEntityKey();
    }

    public static IEmpCandidatesEntityKey createEmpCandidatesEntityKey(Long candidateCode, Long candidateCodeSeq) {
        return new EmpCandidatesEntityKey(candidateCode, candidateCodeSeq);
    }
    
    public static IEmployeeSearchEntityKey createEmployeeSearchEntityKey(Long civilId) {
        return new EmployeeSearchEntityKey(civilId);
    }
    
    public static IEmpWorkPermitsEntityKey createEmpWorkPermitsEntityKey(Long serial)
    {
        return new EmpWorkPermitsEntityKey (serial);
        
        }
    
/**
* @return EmpCriminalStatusEntitykey
*/
public static IEmpCriminalStatusEntityKey createEmpCriminalStatusEntityKey() {
        
 return new EmpCriminalStatusEntityKey () ;
    } 

public static IEmpCriminalStatusEntityKey createEmpCriminalStatusEntityKey( String crmstatusCode ) {
        
 return new EmpCriminalStatusEntityKey (crmstatusCode ) ;
    } 

/**
* @return EmpMoiWsCriminalEntitykey
*/
public static IEmpMoiWsCriminalEntityKey createEmpMoiWsCriminalEntityKey() {
        
 return new EmpMoiWsCriminalEntityKey () ;
    } 

public static IEmpMoiWsCriminalEntityKey createEmpMoiWsCriminalEntityKey( Long crmWsSerial ) {
        
 return new EmpMoiWsCriminalEntityKey (crmWsSerial ) ;
    } 

/**
* @return EmpMinEdtypesEntitykey
*/
public static IEmpMinEdtypesEntityKey createEmpMinEdtypesEntityKey() {
        
 return new EmpMinEdtypesEntityKey () ;
    } 

public static IEmpMinEdtypesEntityKey createEmpMinEdtypesEntityKey( Long minCode,  Long extdattypeCode ) {
        
 return new EmpMinEdtypesEntityKey (minCode, extdattypeCode ) ;
    } 

}