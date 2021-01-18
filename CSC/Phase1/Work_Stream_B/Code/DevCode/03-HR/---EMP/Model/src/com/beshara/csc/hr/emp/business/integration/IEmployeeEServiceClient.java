package com.beshara.csc.hr.emp.business.integration;


import com.beshara.csc.hr.emp.business.integration.eservices.employee.IEmployeeEService;
import com.beshara.csc.hr.emp.business.integration.eservices.employee.dto.EmployeeDTO;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class IEmployeeEServiceClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            IEmployeeEService iEmployeeEService =
                (IEmployeeEService)context.lookup("EmpEmployeeEService#com.beshara.csc.hr.emp.business.integration.eservices.employee.IEmployeeEService");
            EmployeeDTO reqEmp = new EmployeeDTO();
            reqEmp.setRealCivilId(266110402164L);
            reqEmp.setRoleID(25L);
            reqEmp.setMinCode(13L);
            String usersCodes = "7636,7832,7838,7844,7850";
//            iEmployeeEService.getAllEmployeeData(reqEmp);
            iEmployeeEService.getAllUserNameByRole(25L,266110402164L,13L,usersCodes);
            System.out.println("333333333333333333");
        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
