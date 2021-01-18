package com.beshara.csc.hr.emp.business.client;


import com.beshara.common.factory.Factory;

import java.util.HashMap;
import java.util.Map;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Represents The Factory Which Generates appropriate Implementation * Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public abstract class EmpClientFactory {

    private static EmpClientFactory instance;

    private IAdminAssignmentsClient adminAssignmentsClient;
    private IAllEmployeesClient allEmployeesClient;
    private IEmpCandidateStatusClient empCandidateStatusClient;
    private IAssignReasonsClient assignReasonsClient;
    private IAssignStatusClient assignStatusClient;
    private IAssignTypesClient assignTypesClient;
    private IDocumentTypesClient documentTypesClient;
    private IEmployeesClient employeesClient;
    private IEmpCandidatesClient empCandidatesClient;
    private IEmpCndSalaryElementsClient empCndSalaryElementsClient;
    private IHireTypeConditionsClient hireTypeConditionsClient;

//    private IEmployeeDocumentsClient employeeDocumentsClient;
//    private IEmployeeProceduresClient employeeProceduresClient;
    private IHireProceduresClient hireProceduresClient;
    private IHireStagesClient hireStagesClient;
    private IHireStatusClient hireStatusClient;
    private IHireTypesClient hireTypesClient;
    private IProcedureResultsClient procedureResultsClient;
    private IRequiredDocumentsClient requiredDocumentsClient;
    private ITrxTypesClient trxTypesClient;
    private IDegreeDataClient degreeDataClient;
    private IEmployeeHchildCatsClient employeeHchildCatsClient;
    private IEmployeeNchildCatsClient employeeNchildCatsClient;
    private IIncomeDataClient incomeDataClient;
    private IRaiseDataClient raiseDataClient;
    private IRaiseDataLogClient raiseDataLogClient;
    private IEmployeesCMTClient employeesCMTClient;
    private IEmpEmployeeHistoriesClient empEmployeeHistoriesClient;
    private IEmpExtraDataTypesClient empExtraDataTypesClient;
    private IEmployeeExtraDataClient employeeExtraDataClient;
    private IEmpReasonTypesClient empReasonTypesClient;
    private IEmpReasonDataClient empReasonDataClient;
    private IEmpInternalExperienceClient empInternalExperienceClient;
    private IEmpExternalExperienceClient empExternalExperienceClient;
    private IHireTypeProcedureClient hireTypeProcedureClient;
    private IEmpCandidateProceduresClient empCandidateProceduresClient;
    private IEmpCandidateExtraDataClient empCandidateExtraDataClient;
    private IEmployeeExtraDataCMTClient employeesExtraDataCMTClient;
    
    
    private IEmpWorkPermitsClient empWorkPermitsClient ;
    //private IEmpCandidatesCMTClient empCandidatesCMTClient;

    //private static SessionBeanProvider sessionBeanProvider;

    private Map<Long, IEmployeesClient> employeesClientForMinistries = new HashMap<Long, IEmployeesClient>();

    private Map<Long, IEmployeesCMTClient> employeesCMTClientForMinistries = new HashMap<Long, IEmployeesCMTClient>();

    private Map<Long, IEmployeeExtraDataClient> employeeExtraDataClientForMinistries =
        new HashMap<Long, IEmployeeExtraDataClient>();

    private Map<Long, IEmployeeExtraDataCMTClient> employeeExtraDataCMTClientForMinistries =
        new HashMap<Long, IEmployeeExtraDataCMTClient>();


    public EmpClientFactory() {
    }

    public static EmpClientFactory getInstance() {
        if (instance == null) {
            if (Factory.containsInstance(EmpClientFactory.class)) {
                instance = (EmpClientFactory)Factory.getImplInstance(EmpClientFactory.class);
            } else {
                instance = new EmpSessionBeanClientFactory();
            }
        }
        return instance;
    }

    public static IAdminAssignmentsClient getAdminAssignmentsClient() {
        return getInstance().getAdminAssignmentsClientImpl();
    }

    public static IAllEmployeesClient getAllEmployeesClient() {
        return getInstance().getAllEmployeesClientImpl();
    }

    public static IEmpCandidateStatusClient getEmpCandidateStatusClient() {
        return getInstance().getEmpCandidateStatusClientImpl();
    }

    public IEmpCandidateStatusClient getEmpCandidateStatusClientImpl() {
        if (empCandidateStatusClient == null) {
            empCandidateStatusClient = createEmpCandidateStatusClient();
        }

        return empCandidateStatusClient;
    }


    public static IAssignReasonsClient getAssignReasonsClient() {
        return getInstance().getAssignReasonsClientImpl();
    }

    public static IAssignStatusClient getAssignStatusClient() {
        return getInstance().getAssignStatusClientImpl();
    }

    public static IAssignTypesClient getAssignTypesClient() {
        return getInstance().getAssignTypesClientImpl();
    }

//    public static IDocumentTypesClient getDocumentTypesClient() {
//        return getInstance().getDocumentTypesClientImpl();
//    }

    public static IEmployeesClient getEmployeesClient() {
        return getInstance().getEmployeesClientImpl();
    }


    public static IEmpCandidatesClient getEmpCandidatesClient() {
        return getInstance().getEmpCandidatesClientImpl();
    }

    public static IEmpCndSalaryElementsClient getEmpCndSalaryElementsClient() {
        return getInstance().getEmpCndSalaryElementsClientImpl();
    }


    public static IEmployeesClient getEmployeesClient(Long ministryCode) {
        return getInstance().getEmployeesClientImplForMinistry(ministryCode);
    }

//    public static IEmployeeDocumentsClient getEmployeeDocumentsClient() {
//        return getInstance().getEmployeeDocumentsClientImpl();
//    }

//    public static IEmployeeProceduresClient getEmployeeProceduresClient() {
//        return getInstance().getEmployeeProceduresClientImpl();
//    }

    public static IHireProceduresClient getHireProceduresClient() {
        return getInstance().getHireProceduresClientImpl();
    }

    public static IHireStagesClient getHireStagesClient() {
        return getInstance().getHireStagesClientImpl();
    }

    public static IHireStatusClient getHireStatusClient() {
        return getInstance().getHireStatusClientImpl();
    }

    public static IHireTypesClient getHireTypesClient() {
        return getInstance().getHireTypesClientImpl();
    }

    public static IProcedureResultsClient getProcedureResultsClient() {
        return getInstance().getProcedureResultsClientImpl();
    }

    public static IRequiredDocumentsClient getRequiredDocumentsClient() {
        return getInstance().getRequiredDocumentsClientImpl();
    }

    public static IHireTypeProcedureClient getHireTypeProcedureClient() {
        return getInstance().getHireTypeProcedureClientImpl();
    }

    public static ITrxTypesClient getTrxTypesClient() {
        return getInstance().getTrxTypesClientImpl();
    }

    public static IDegreeDataClient getDegreeDataClient() {
        return getInstance().getDegreeDataClientImpl();
    }

    public static IEmployeeHchildCatsClient getEmployeeHchildCatsClient() {
        return getInstance().getEmployeeHchildCatsClientImpl();
    }

    public static IEmployeeNchildCatsClient getEmployeeNchildCatsClient() {
        return getInstance().getEmployeeNchildCatsClientImpl();
    }

    public static IIncomeDataClient getIncomeDataClient() {
        return getInstance().getIncomeDataClientImpl();
    }

    public static IRaiseDataClient getRaiseDataClient() {
        return getInstance().getRaiseDataClientImpl();
    }

    public static IRaiseDataLogClient getRaiseDataLogClient() {
        return getInstance().getRaiseDataLogClientImpl();
    }

    public static IEmployeesCMTClient getEmployeesCMTClient() {
        return getInstance().getEmployeesCMTClientImpl();
    }

    public static IEmployeesCMTClient getEmployeesCMTClient(Long ministryCode) {
        return getInstance().getEmployeesCMTClientImplForMinistry(ministryCode);
    }

    public static IEmpEmployeeHistoriesClient getEmpEmployeeHistoriesClient() {
        return getInstance().getEmpEmployeeHistoriesClientImpl();
    }

    public static IEmpExtraDataTypesClient getEmpExtraDataTypesClient() {
        return getInstance().getEmpExtraDataTypesClientImpl();
    }

    public static IEmployeeExtraDataClient getEmployeeExtraDataClient() {
        return getInstance().getEmployeeExtraDataClientImpl();
    }

    public static IEmployeeExtraDataClient getEmployeeExtraDataClient(Long ministryCode) {
        return getInstance().getEmployeeExtraDataClientImplForMinistry(ministryCode);
    }

    public static IEmployeeExtraDataCMTClient getEmployeeExtraDataCMTClient() {
        return getInstance().getEmployeeExtraDataCMTClientImpl();
    }

    public static IEmployeeExtraDataCMTClient getEmployeeExtraDataCMTClientForMinistry(Long ministryCode) {
        return getInstance().getEmployeeExtraDataCMTClientImplForMinistry(ministryCode);
    }

    public static IEmpReasonTypesClient getEmpReasonTypesClient() {
        return getInstance().getEmpReasonTypesClientImpl();
    }

    public static IEmpReasonDataClient getEmpReasonDataClient() {
        return getInstance().getEmpReasonDataClientImpl();
    }

    public static IEmpInternalExperienceClient getEmpInternalExperienceClient() {
        return getInstance().getEmpInternalExperienceClientImpl();
    }

    public static IEmpExternalExperienceClient getEmpExternalExperienceClient() {
        return getInstance().getEmpExternalExperienceClientImpl();
    }

//    public static IEmpCandidatesCMTClient getEmpCandidatesCMTClient() {
//        return getInstance().getEmpCandidatesCMTClientImpl();
//    }
    //**************************************************************************

    public IAdminAssignmentsClient getAdminAssignmentsClientImpl() {
        if (adminAssignmentsClient == null) {
            adminAssignmentsClient = createAdminAssignmentsClient();
        }

        return adminAssignmentsClient;
    }

    public IAllEmployeesClient getAllEmployeesClientImpl() {
        if (allEmployeesClient == null) {
            allEmployeesClient = createAllEmployeesClient();
        }

        return allEmployeesClient;
    }
    public static IHireTypeConditionsClient getHireTypeConditionsClient() {
        return getInstance().getHireTypeConditionsClientImpl();
    }

    public IEmployeesClient getEmployeesClientImplForMinistry(Long ministryCode) {
        synchronized (employeesClientForMinistries) {
            IEmployeesClient clientInstance = employeesClientForMinistries.get(ministryCode);
            if (clientInstance == null) {
                clientInstance = createEmployeesClientForMinistry(ministryCode);
                employeesClientForMinistries.put(ministryCode, clientInstance);
            }
            return clientInstance;
        }
    }

    public IAssignReasonsClient getAssignReasonsClientImpl() {
        if (assignReasonsClient == null) {
            assignReasonsClient = createAssignReasonsClient();
        }

        return assignReasonsClient;
    }

    public IAssignStatusClient getAssignStatusClientImpl() {
        if (assignStatusClient == null) {
            assignStatusClient = createAssignStatusClient();
        }

        return assignStatusClient;
    }

    public IAssignTypesClient getAssignTypesClientImpl() {
        if (assignTypesClient == null) {
            assignTypesClient = createAssignTypesClient();
        }

        return assignTypesClient;
    }

//    public IDocumentTypesClient getDocumentTypesClientImpl() {
//        if (documentTypesClient == null) {
//            documentTypesClient = createDocumentTypesClient();
//        }
//
//        return documentTypesClient;
//    }

    public IEmployeesClient getEmployeesClientImpl() {
        if (employeesClient == null) {
            employeesClient = createEmployeesClient();
        }

        return employeesClient;
    }


    public IEmpCandidatesClient getEmpCandidatesClientImpl() {
        if (empCandidatesClient == null) {
            empCandidatesClient = createEmpCandidatesClient();
        }

        return empCandidatesClient;
    }

    public IEmpCndSalaryElementsClient getEmpCndSalaryElementsClientImpl() {
        if (empCndSalaryElementsClient == null) {
            empCndSalaryElementsClient = createEmpCndSalaryElementsClient();
        }

        return empCndSalaryElementsClient;
    }

    public IHireTypeConditionsClient getHireTypeConditionsClientImpl() {
        if (hireTypeConditionsClient == null) {
            hireTypeConditionsClient = createHireTypeConditionsClient();
        }

        return hireTypeConditionsClient;
    }


//    public IEmployeeDocumentsClient getEmployeeDocumentsClientImpl() {
//        if (employeeDocumentsClient == null) {
//            employeeDocumentsClient = createEmployeeDocumentsClient();
//        }
//
//        return employeeDocumentsClient;
//    }

//    public IEmployeeProceduresClient getEmployeeProceduresClientImpl() {
//        if (employeeProceduresClient == null) {
//            employeeProceduresClient = createEmployeeProceduresClient();
//        }
//
//        return employeeProceduresClient;
//    }

    public IHireProceduresClient getHireProceduresClientImpl() {
        if (hireProceduresClient == null) {
            hireProceduresClient = createHireProceduresClient();
        }

        return hireProceduresClient;
    }

    public IHireStagesClient getHireStagesClientImpl() {
        if (hireStagesClient == null) {
            hireStagesClient = createHireStagesClient();
        }

        return hireStagesClient;
    }

    public IHireStatusClient getHireStatusClientImpl() {
        if (hireStatusClient == null) {
            hireStatusClient = createHireStatusClient();
        }

        return hireStatusClient;
    }

    public IHireTypesClient getHireTypesClientImpl() {
        if (hireTypesClient == null) {
            hireTypesClient = createHireTypesClient();
        }

        return hireTypesClient;
    }

    public IProcedureResultsClient getProcedureResultsClientImpl() {
        if (procedureResultsClient == null) {
            procedureResultsClient = createProcedureResultsClient();
        }

        return procedureResultsClient;
    }

    public IRequiredDocumentsClient getRequiredDocumentsClientImpl() {
        if (requiredDocumentsClient == null) {
            requiredDocumentsClient = createRequiredDocumentsClient();
        }

        return requiredDocumentsClient;
    }

    public IHireTypeProcedureClient getHireTypeProcedureClientImpl() {
        if (hireTypeProcedureClient == null) {
            hireTypeProcedureClient = createHireTypeProcedureClient();
        }

        return hireTypeProcedureClient;
    }

    public ITrxTypesClient getTrxTypesClientImpl() {
        if (trxTypesClient == null) {
            trxTypesClient = createTrxTypesClient();
        }

        return trxTypesClient;
    }

    public IDegreeDataClient getDegreeDataClientImpl() {
        if (degreeDataClient == null) {
            degreeDataClient = createDegreeDataClient();
        }

        return degreeDataClient;
    }

    public IEmployeeHchildCatsClient getEmployeeHchildCatsClientImpl() {
        if (employeeHchildCatsClient == null) {
            employeeHchildCatsClient = createEmployeeHchildCatsClient();
        }

        return employeeHchildCatsClient;
    }

    public IEmployeeNchildCatsClient getEmployeeNchildCatsClientImpl() {
        if (employeeNchildCatsClient == null) {
            employeeNchildCatsClient = createEmployeeNchildCatsClient();
        }

        return employeeNchildCatsClient;
    }

    public IIncomeDataClient getIncomeDataClientImpl() {
        if (incomeDataClient == null) {
            incomeDataClient = createIncomeDataClient();
        }

        return incomeDataClient;
    }

    public IRaiseDataClient getRaiseDataClientImpl() {
        if (raiseDataClient == null) {
            raiseDataClient = createRaiseDataClient();
        }

        return raiseDataClient;
    }

    public IRaiseDataLogClient getRaiseDataLogClientImpl() {
        if (raiseDataLogClient == null) {
            raiseDataLogClient = createRaiseDataLogClient();
        }

        return raiseDataLogClient;
    }

    public IEmployeesCMTClient getEmployeesCMTClientImpl() {
        if (employeesCMTClient == null) {
            employeesCMTClient = createEmployeesCMTClient();
        }

        return employeesCMTClient;
    }

    public IEmployeesCMTClient getEmployeesCMTClientImplForMinistry(Long ministryCode) {
        synchronized (employeesCMTClientForMinistries) {
            IEmployeesCMTClient clientInstance = employeesCMTClientForMinistries.get(ministryCode);
            if (clientInstance == null) {
                clientInstance = createEmployeesCMTClientForMinistry(ministryCode);
                employeesCMTClientForMinistries.put(ministryCode, clientInstance);
            }
            return clientInstance;
        }
    }

    public IEmpEmployeeHistoriesClient getEmpEmployeeHistoriesClientImpl() {
        if (empEmployeeHistoriesClient == null) {
            empEmployeeHistoriesClient = createEmpEmployeeHistoriesClient();
        }

        return empEmployeeHistoriesClient;
    }

    public IEmpExtraDataTypesClient getEmpExtraDataTypesClientImpl() {
        if (empExtraDataTypesClient == null) {
            empExtraDataTypesClient = createEmpExtraDataTypesClient();
        }

        return empExtraDataTypesClient;
    }

    public IEmployeeExtraDataClient getEmployeeExtraDataClientImpl() {
        if (employeeExtraDataClient == null) {
            employeeExtraDataClient = createEmployeeExtraDataClient();
        }

        return employeeExtraDataClient;
    }

    public IEmployeeExtraDataClient getEmployeeExtraDataClientImplForMinistry(Long ministryCode) {
        synchronized (employeeExtraDataClientForMinistries) {
            IEmployeeExtraDataClient clientInstance = employeeExtraDataClientForMinistries.get(ministryCode);
            if (clientInstance == null) {
                clientInstance = createEmployeeExtraDataClientForMinistry(ministryCode);
                employeeExtraDataClientForMinistries.put(ministryCode, clientInstance);
            }
            return clientInstance;
        }
    }

    public IEmpReasonTypesClient getEmpReasonTypesClientImpl() {
        if (empReasonTypesClient == null) {
            empReasonTypesClient = createEmpReasonTypesClient();
        }

        return empReasonTypesClient;
    }

    public IEmpReasonDataClient getEmpReasonDataClientImpl() {
        if (empReasonDataClient == null) {
            empReasonDataClient = createEmpReasonDataClient();
        }

        return empReasonDataClient;
    }

    public IEmpInternalExperienceClient getEmpInternalExperienceClientImpl() {
        if (empInternalExperienceClient == null) {
            empInternalExperienceClient = createEmpInternalExperienceClient();
        }

        return empInternalExperienceClient;
    }

    public IEmpExternalExperienceClient getEmpExternalExperienceClientImpl() {
        if (empExternalExperienceClient == null) {
            empExternalExperienceClient = createEmpExternalExperienceClient();
        }

        return empExternalExperienceClient;
    }

    public IEmployeeExtraDataCMTClient getEmployeeExtraDataCMTClientImpl() {
        if (employeesExtraDataCMTClient == null) {
            employeesExtraDataCMTClient = createEmployeeExtraDataCMTClient();
        }

        return employeesExtraDataCMTClient;
    }


    public IEmployeeExtraDataCMTClient getEmployeeExtraDataCMTClientImplForMinistry(Long ministryCode) {
        synchronized (employeeExtraDataCMTClientForMinistries) {
            IEmployeeExtraDataCMTClient clientInstance = employeeExtraDataCMTClientForMinistries.get(ministryCode);
            if (clientInstance == null) {
                clientInstance = createEmployeeExtraDataCMTClientForMinistry(ministryCode);
                employeeExtraDataCMTClientForMinistries.put(ministryCode, clientInstance);
            }
            return clientInstance;
        }
    }

//    public IEmpCandidatesCMTClient getEmpCandidatesCMTClientImpl() {
//        if (empCandidatesCMTClient == null) {
//            empCandidatesCMTClient = createEmpCandidatesCMTClient();
//        }
//
//        return empCandidatesCMTClient;
//    }

   
    //**************************************************************************

    protected abstract IAdminAssignmentsClient createAdminAssignmentsClient();

//    protected abstract IEmpCandidatesCMTClient createEmpCandidatesCMTClient();

    protected abstract IAllEmployeesClient createAllEmployeesClient();

    protected abstract IEmpCandidateStatusClient createEmpCandidateStatusClient();

    protected abstract IAssignReasonsClient createAssignReasonsClient();

    protected abstract IAssignStatusClient createAssignStatusClient();

    protected abstract IAssignTypesClient createAssignTypesClient();

//    protected abstract IDocumentTypesClient createDocumentTypesClient();

    protected abstract IEmployeesClient createEmployeesClient();

    protected abstract IEmpCandidatesClient createEmpCandidatesClient();

    protected abstract IEmpCndSalaryElementsClient createEmpCndSalaryElementsClient();


//    protected abstract IEmployeeDocumentsClient createEmployeeDocumentsClient();

    protected abstract IHireTypeConditionsClient createHireTypeConditionsClient();

//    protected abstract IEmployeeProceduresClient createEmployeeProceduresClient();

    protected abstract IHireProceduresClient createHireProceduresClient();

    protected abstract IHireStagesClient createHireStagesClient();

    protected abstract IHireStatusClient createHireStatusClient();

    protected abstract IHireTypesClient createHireTypesClient();

    protected abstract IProcedureResultsClient createProcedureResultsClient();

    protected abstract IRequiredDocumentsClient createRequiredDocumentsClient();

    protected abstract IHireTypeProcedureClient createHireTypeProcedureClient();

    protected abstract ITrxTypesClient createTrxTypesClient();

    protected abstract IDegreeDataClient createDegreeDataClient();

    protected abstract IEmployeeHchildCatsClient createEmployeeHchildCatsClient();

    protected abstract IEmployeeNchildCatsClient createEmployeeNchildCatsClient();

    protected abstract IIncomeDataClient createIncomeDataClient();

    protected abstract IRaiseDataClient createRaiseDataClient();

    protected abstract IRaiseDataLogClient createRaiseDataLogClient();

    protected abstract IEmployeesCMTClient createEmployeesCMTClient();

    protected abstract IEmpEmployeeHistoriesClient createEmpEmployeeHistoriesClient();

    protected abstract IEmpExtraDataTypesClient createEmpExtraDataTypesClient();

    protected abstract IEmployeeExtraDataClient createEmployeeExtraDataClient();

    protected abstract IEmpReasonTypesClient createEmpReasonTypesClient();

    protected abstract IEmpReasonDataClient createEmpReasonDataClient();

    protected abstract IEmpInternalExperienceClient createEmpInternalExperienceClient();

    protected abstract IEmpExternalExperienceClient createEmpExternalExperienceClient();

    //**************************************************************************

    protected abstract IEmployeesClient createEmployeesClientForMinistry(Long ministryCode);

    protected abstract IEmployeesCMTClient createEmployeesCMTClientForMinistry(Long ministryCode);

    protected abstract IEmployeeExtraDataClient createEmployeeExtraDataClientForMinistry(Long ministryCode);

    protected abstract IEmployeeExtraDataCMTClient createEmployeeExtraDataCMTClient();

    protected abstract IEmployeeExtraDataCMTClient createEmployeeExtraDataCMTClientForMinistry(Long ministryCode);


    ////////////////

    private IEmpCandidateDocumentsClient empCandidateDocumentsClient;

    protected abstract IEmpCandidateDocumentsClient createEmpCandidateDocumentsClient();

    public IEmpCandidateDocumentsClient getEmpCandidateDocumentsClientImpl() {
        if (empCandidateDocumentsClient == null) {
            empCandidateDocumentsClient = createEmpCandidateDocumentsClient();
        }

        return empCandidateDocumentsClient;
    }

    public static IEmpCandidateDocumentsClient getEmpCandidateDocumentsClient() {
        return getInstance().getEmpCandidateDocumentsClientImpl();
    }

    public static IEmpCandidateExtraDataClient getEmpCandidateExtraDataClient() {
        return getInstance().getEmpCandidateExtraDataClientImpl();
    }

    public IEmpCandidateExtraDataClient getEmpCandidateExtraDataClientImpl() {
        if (empCandidateExtraDataClient == null) {
            empCandidateExtraDataClient = createEmpCandidateExtraDataClient();
        }

        return empCandidateExtraDataClient;
    }

    protected abstract IEmpCandidateExtraDataClient createEmpCandidateExtraDataClient();


    public static IEmpCandidateProceduresClient getEmpCandidateProceduresClient() {
        return getInstance().getEmpCandidateProceduresClientImpl();
    }

    public IEmpCandidateProceduresClient getEmpCandidateProceduresClientImpl() {
        if (empCandidateProceduresClient == null) {
            empCandidateProceduresClient = createEmpCandidateProceduresClient();
        }

        return empCandidateProceduresClient;
    }

    protected abstract IEmpCandidateProceduresClient createEmpCandidateProceduresClient();

    public static IEmpWorkPermitsClient getEmpWorkPermitsClient(){
        return getInstance().getEmpWorkPermitsClientImpl();
    }
    
    
    public IEmpWorkPermitsClient getEmpWorkPermitsClientImpl(){
        if(empWorkPermitsClient==null) {
            empWorkPermitsClient=createEmpWorkPermitsClient();
        }
   return empWorkPermitsClient ;
    }
    protected abstract IEmpWorkPermitsClient createEmpWorkPermitsClient ();


/**
* @return IEmpCriminalStatusClient
*/
public static IEmpCriminalStatusClient getEmpCriminalStatusClient() {
        
 return new EmpCriminalStatusClientImpl () ;
    } 

/**
* @return IEmpMoiWsCriminalClient
*/
public static IEmpMoiWsCriminalClient getEmpMoiWsCriminalClient() {
        
 return new EmpMoiWsCriminalClientImpl () ;
    } 

    public static IEmpMinEdtypesClient getEmpMinEdtypesClient() {
            
     return new EmpMinEdtypesClientImpl () ;
        } 
}