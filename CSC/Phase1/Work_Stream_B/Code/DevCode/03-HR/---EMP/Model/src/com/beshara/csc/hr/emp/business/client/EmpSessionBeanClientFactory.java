package com.beshara.csc.hr.emp.business.client;


public class EmpSessionBeanClientFactory extends EmpClientFactory {

    public EmpSessionBeanClientFactory() {
    }

    protected IAdminAssignmentsClient createAdminAssignmentsClient() {
        return new AdminAssignmentsClientImpl();
    }

    protected IAllEmployeesClient createAllEmployeesClient() {
        return new AllEmployeesClientImpl();
    }

    protected IEmpCandidateStatusClient createEmpCandidateStatusClient() {
        return new EmpCandidateStatusClientImpl();
    }

    protected IAssignReasonsClient createAssignReasonsClient() {
        return new AssignReasonsClientImpl();
    }

    protected IAssignStatusClient createAssignStatusClient() {
        return new AssignStatusClientImpl();
        //return new AssignStatusClientImpl(SessionBeanProviderHelper.getContextSession(AssignStatusSession.class));
    }

    protected IAssignTypesClient createAssignTypesClient() {
        return new AssignTypesClientImpl();
    }

    //    protected IDocumentTypesClient createDocumentTypesClient() {
    //        return new DocumentTypesClientImpl();
    //       // return new DocumentTypesClientImpl(SessionBeanProviderHelper.getContextSession(DocumentTypesSession.class));
    //    }

    protected IEmployeesClient createEmployeesClient() {
        return new EmployeesClientImpl();
        // return new EmployeesClientImpl(SessionBeanProviderHelper.getContextSession(EmployeesSession.class));
    }

    //    protected IEmployeeDocumentsClient createEmployeeDocumentsClient() {
    //        return new EmployeeDocumentsClientImpl();
    //        //return new EmployeeDocumentsClientImpl(SessionBeanProviderHelper.getContextSession(EmployeeDocumentsSession.class));
    //    }

    //    protected IEmployeeProceduresClient createEmployeeProceduresClient() {
    //        return new EmployeeProceduresClientImpl();
    //    }

    protected IHireProceduresClient createHireProceduresClient() {
        return new HireProceduresClientImpl();
    }

    protected IHireStagesClient createHireStagesClient() {
        return new HireStagesClientImpl();
    }

    protected IHireStatusClient createHireStatusClient() {
        return new HireStatusClientImpl();
    }

    protected IHireTypesClient createHireTypesClient() {
        return new HireTypesClientImpl();
    }

    protected IProcedureResultsClient createProcedureResultsClient() {
        return new ProcedureResultsClientImpl();
    }

    protected IRequiredDocumentsClient createRequiredDocumentsClient() {
        return new RequiredDocumentsClientImpl();
    }

    //    protected IHireTypeProcedureClient createHireTypeProcedureClient() {
    //        return new HireTypeProcedureClientImpl(SessionBeanProviderHelper.getContextSession(HireTypeProcedureSession.class));
    //    }

    protected ITrxTypesClient createTrxTypesClient() {
        return new TrxTypesClientImpl();
    }

    protected IDegreeDataClient createDegreeDataClient() {
        return new DegreeDataClientImpl();
    }

    protected IEmployeeHchildCatsClient createEmployeeHchildCatsClient() {
        return new EmployeeHchildCatsClientImpl();
        //return new EmployeeHchildCatsClientImpl(SessionBeanProviderHelper.getContextSession(EmployeeHchildCatsSession.class));
    }

    protected IEmployeeNchildCatsClient createEmployeeNchildCatsClient() {
        return new EmployeeNchildCatsClientImpl();
    }

    protected IIncomeDataClient createIncomeDataClient() {
        return new IncomeDataClientImpl();
    }

    protected IRaiseDataClient createRaiseDataClient() {
        return new RaiseDataClientImpl();
    }

    protected IRaiseDataLogClient createRaiseDataLogClient() {
        return new RaiseDataLogClientImpl();
        //return new RaiseDataLogClientImpl(SessionBeanProviderHelper.getContextSession(RaiseDataLogSession.class));
    }

    protected IEmployeesCMTClient createEmployeesCMTClient() {

        return new EmployeesCMTClientImpl();
    }

    protected IEmpEmployeeHistoriesClient createEmpEmployeeHistoriesClient() {
        return new EmpEmployeeHistoriesClientImpl();
        //return new EmpEmployeeHistoriesClientImpl(SessionBeanProviderHelper.getContextSession(EmpEmployeeHistoriesSession.class));
    }

    protected IEmpExtraDataTypesClient createEmpExtraDataTypesClient() {
        return new EmpExtraDataTypesClientImpl();
        //return new EmpExtraDataTypesClientImpl(SessionBeanProviderHelper.getContextSession(EmpExtraDataTypesSession.class));
    }

    protected IEmployeeExtraDataClient createEmployeeExtraDataClient() {
        return new EmployeeExtraDataClientImpl();
    }

    protected IEmpReasonTypesClient createEmpReasonTypesClient() {
        return new EmpReasonTypesClientImpl();
    }

    protected IEmpReasonDataClient createEmpReasonDataClient() {
        return new EmpReasonDataClientImpl();
    }

    protected IEmpInternalExperienceClient createEmpInternalExperienceClient() {
        return new EmpInternalExperienceClientImpl();
    }

    protected IEmpExternalExperienceClient createEmpExternalExperienceClient() {
        return new EmpExternalExperienceClientImpl();
    }

    protected IEmployeesClient createEmployeesClientForMinistry(Long ministryCode) {
        return new EmployeesClientImpl();
        //return new EmployeesClientImpl(SessionBeanProviderHelper.getLocationContextSession(ministryCode.toString(),
        //                                                                                     EmployeesSession.class));
    }

    protected IEmployeesCMTClient createEmployeesCMTClientForMinistry(Long ministryCode) {

        return new EmployeesCMTClientImpl();
    }

    protected IEmployeeExtraDataClient createEmployeeExtraDataClientForMinistry(Long ministryCode) {
        return new EmployeeExtraDataClientImpl();
    }

    protected IEmployeeExtraDataCMTClient createEmployeeExtraDataCMTClient() {
        return new EmployeeExtraDataCMTClientImpl();
    }

    protected IEmployeeExtraDataCMTClient createEmployeeExtraDataCMTClientForMinistry(Long ministryCode) {
        return new EmployeeExtraDataCMTClientImpl();
    }

    protected IEmpCandidateDocumentsClient createEmpCandidateDocumentsClient() {
        return new EmpCandidateDocumentsClientImpl();
    }

    protected IEmpCandidateExtraDataClient createEmpCandidateExtraDataClient() {
        return new EmpCandidateExtraDataClientImpl();
        //return new EmpCandidateExtraDataClientImpl(SessionBeanProviderHelper.getContextSession(EmpCandidateExtraDataSession.class));
    }

    protected IEmpCandidateProceduresClient createEmpCandidateProceduresClient() {
        return new EmpCandidateProceduresClientImpl();
    }

    protected IEmpCndSalaryElementsClient createEmpCndSalaryElementsClient() {
        return new EmpCndSalaryElementsClientImpl();
    }

    protected IEmpCandidatesClient createEmpCandidatesClient() {
        return new EmpCandidatesClientImpl();
    }

    //    protected IEmpCandidatesCMTClient createEmpCandidatesCMTClient() {
    //        return new EmpCandidatesCMTClientImpl(SessionBeanProviderHelper.getContextSession(EmpCandidatesCMTSession.class));
    //    }

    protected IHireTypeConditionsClient createHireTypeConditionsClient() {
        return new HireTypeConditionsClientImpl();
    }
    //
    //    @Override
    //    protected IHireTypesClient createHireTypesClient() {
    //        return new HireTypesClientImpl();
    //    }

    @Override
    protected IHireTypeProcedureClient createHireTypeProcedureClient() {
        return new HireTypeProcedureClientImpl();
    }


    @Override
    protected IEmpWorkPermitsClient createEmpWorkPermitsClient() {
        return new EmpWorkPermitsClientImpl() ;
    }
}
