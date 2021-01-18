package com.beshara.csc.hr.emp.business.client;


import com.beshara.base.client.BaseClientImpl3;
import com.beshara.base.deploy.BasicSession;
import com.beshara.csc.hr.emp.business.deploy.DegreeDataSession;


/**
 * <b>Description:</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * This Class Implements a specified Client Interface as Session Bean * and Generated through the Client Factory Class Based on the Key Returned from the Properties File I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Code Generator 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Taha El-Fitiany , Sherif El-Rabbat
 * @version 1.0
 * @since 03/09/2007
 */
public class DegreeDataClientImpl extends BaseClientImpl3 implements IDegreeDataClient {

    /** 
     * @param DegreeDataSession 
     */
    public DegreeDataClientImpl() {
        super();
    }

    @Override
    protected Class<? extends BasicSession> getSessionInterface() {
        return DegreeDataSession.class;
    }

    @Override
    protected DegreeDataSession SESSION() {
        return (DegreeDataSession)super.SESSION();
    }
}
