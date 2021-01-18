package com.beshara.csc.hr.emp.business.client;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;


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
public class EmpClientFactoryOld {

    //    public static Hashtable env = new Hashtable();
    //    public static Properties prop = null;
    //    static {
    //        try { // Load the jndi.properties file and get the enviroment 
    //            prop = loadParams("com.beshara.csc.hr.emp.business.resources.jndi");
    //            env.put(Context.INITIAL_CONTEXT_FACTORY, 
    //                    (String)prop.get("INITIAL_CONTEXT_FACTORY"));
    //            env.put(Context.PROVIDER_URL, (String)prop.get("PROVIDER_URL"));
    //            env.put(Context.SECURITY_PRINCIPAL, 
    //                    (String)prop.get("SECURITY_PRINCIPAL"));
    //            env.put(Context.SECURITY_CREDENTIALS, 
    //                    (String)prop.get("SECURITY_CREDENTIALS"));
    //        } catch (Exception ex) {
    //            System.out.println(" Fatal Error : Couldn't Read Properties file : " + 
    //                               ex.toString());
    //        }
    //    }

    /** 
     * AllEmployeesClientFactory Default Constructor */
    public EmpClientFactoryOld() {
    }

    /** 
     * @return IAllEmployeesClient 
     */
    public static IAllEmployeesClient getAllEmployeesClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            AllEmployeesSession allEmployeesSession = 
        //                (AllEmployeesSession)serviceLocator.lookup("AllEmployeesSession", 
        //                                                           AllEmployeesSession.class);
        //            if (allEmployeesSession != null) {
        //                return new AllEmployeesClientImpl(allEmployeesSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IDocumentTypesClient 
     */
    public static IDocumentTypesClient getDocumentTypesClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            DocumentTypesSession documentTypesSession = 
        //                (DocumentTypesSession)serviceLocator.lookup("DocumentTypesSession", 
        //                                                            DocumentTypesSession.class);
        //            if (documentTypesSession != null) {
        //                return new DocumentTypesClientImpl(documentTypesSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IEmployeesClient 
     */
    public static IEmployeesClient getEmployeesClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            EmployeesSession employeesSession = 
        //                (EmployeesSession)serviceLocator.lookup("EmployeesSession", 
        //                                                        EmployeesSession.class);
        //            if (employeesSession != null) {
        //                return new EmployeesClientImpl(employeesSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IEmployeeDocumentsClient 
     */
//    public static IEmployeeDocumentsClient getEmployeeDocumentsClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            EmployeeDocumentsSession employeeDocumentsSession = 
        //                (EmployeeDocumentsSession)serviceLocator.lookup("EmployeeDocumentsSession", 
        //                                                                EmployeeDocumentsSession.class);
        //            if (employeeDocumentsSession != null) {
        //                return new EmployeeDocumentsClientImpl(employeeDocumentsSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
//        return null;
//    }

    /** 
     * @return IEmployeeProceduresClient 
     */
//    public static IEmployeeProceduresClient getEmployeeProceduresClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            EmployeeProceduresSession employeeProceduresSession = 
        //                (EmployeeProceduresSession)serviceLocator.lookup("EmployeeProceduresSession", 
        //                                                                 EmployeeProceduresSession.class);
        //            if (employeeProceduresSession != null) {
        //                return new EmployeeProceduresClientImpl(employeeProceduresSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
//        return null;
//    }

    /** 
     * @return IHireProceduresClient 
     */
    public static IHireProceduresClient getHireProceduresClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            HireProceduresSession hireProceduresSession = 
        //                (HireProceduresSession)serviceLocator.lookup("HireProceduresSession", 
        //                                                             HireProceduresSession.class);
        //            if (hireProceduresSession != null) {
        //                return new HireProceduresClientImpl(hireProceduresSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IHireStagesClient 
     */
    public static IHireStagesClient getHireStagesClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            HireStagesSession hireStagesSession = 
        //                (HireStagesSession)serviceLocator.lookup("HireStagesSession", 
        //                                                         HireStagesSession.class);
        //            if (hireStagesSession != null) {
        //                return new HireStagesClientImpl(hireStagesSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IHireStatusClient 
     */
    public static IHireStatusClient getHireStatusClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            HireStatusSession hireStatusSession = 
        //                (HireStatusSession)serviceLocator.lookup("HireStatusSession", 
        //                                                         HireStatusSession.class);
        //            if (hireStatusSession != null) {
        //                return new HireStatusClientImpl(hireStatusSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IHireTypesClient 
     */
    public static IHireTypesClient getHireTypesClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            HireTypesSession hireTypesSession = 
        //                (HireTypesSession)serviceLocator.lookup("HireTypesSession", 
        //                                                        HireTypesSession.class);
        //            if (hireTypesSession != null) {
        //                return new HireTypesClientImpl(hireTypesSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IProcedureResultsClient 
     */
    public static IProcedureResultsClient getProcedureResultsClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            ProcedureResultsSession procedureResultsSession = 
        //                (ProcedureResultsSession)serviceLocator.lookup("ProcedureResultsSession", 
        //                                                               ProcedureResultsSession.class);
        //            if (procedureResultsSession != null) {
        //                return new ProcedureResultsClientImpl(procedureResultsSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IRequiredDocumentsClient 
     */
    public static IRequiredDocumentsClient getRequiredDocumentsClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            RequiredDocumentsSession requiredDocumentsSession = 
        //                (RequiredDocumentsSession)serviceLocator.lookup("RequiredDocumentsSession", 
        //                                                                RequiredDocumentsSession.class);
        //            if (requiredDocumentsSession != null) {
        //                return new RequiredDocumentsClientImpl(requiredDocumentsSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * @return IEmployeeExtraDataClient 
     */
    public static IEmployeeExtraDataClient getEmployeeExtraDataClient() {
        //        ServiceLocator serviceLocator;
        //        try {
        //            if (prop == null) {
        //                serviceLocator = ServiceLocator.getInstance();
        //            } else {
        //                serviceLocator = ServiceLocator.getInstance(env);
        //            }
        //            EmployeeExtraDataSession employeesSession = 
        //                (EmployeeExtraDataSession)serviceLocator.lookup("EmployeeExtraDataSession", 
        //                                                                EmployeeExtraDataSession.class);
        //            if (employeesSession != null) {
        //                return new EmployeeExtraDataClientImpl(employeesSession);
        //            }
        //        } catch (NamingException e) {
        //            System.out.println(e);
        //        }
        return null;
    }

    /** 
     * This method Loads a ResourceBundle and creates Properties from IiItI.I * @param file 
     * @return Properties 
     * @throws IOException 
     */
    public static Properties loadParams(String file) throws IOException { // Loads a ResourceBundle and creates Properties from it 
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration en = bundle.getKeys();
        String key = null;
        while (en.hasMoreElements()) {
            key = (String)en.nextElement();
            prop.put(key, bundle.getObject(key));
        }
        return prop;
    }
}
