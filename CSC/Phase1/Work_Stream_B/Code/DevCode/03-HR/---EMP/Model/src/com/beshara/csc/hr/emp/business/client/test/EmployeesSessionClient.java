package com.beshara.csc.hr.emp.business.client.test;


import com.beshara.base.requestinfo.impl.RequestInfoDTO;
import com.beshara.csc.base.security.CSCSecurityInfoHelper;
import com.beshara.csc.hr.emp.business.deploy.EmployeesSession;

import java.util.Hashtable;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class EmployeesSessionClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            EmployeesSession employeesSession =
                (EmployeesSession)context.lookup("Emp-Model-EmployeesSessionBean#com.beshara.csc.hr.emp.business.deploy.EmployeesSession");            

            Long minCode = CSCSecurityInfoHelper.getLoggedInMinistryCodeFromRequest();
            
            Long count = employeesSession.countEmployeesByJobAndMin(new RequestInfoDTO(), "00", minCode);
            System.out.println("count ::" + count);
     
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
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
