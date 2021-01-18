
package com.beshara.csc;

import javax.xml.ws.WebFault;
import kw.gov.moi.common.v1.moicommonerror.MoiCommonError;


/**
 * This class was generated by the JAX-WS RI.
 * Oracle JAX-WS 2.1.5
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "MoiCommonError", targetNamespace = "http://www.moi.gov.kw/Common/V1/MoiCommonError")
public class InquireGoodBehaviorCertificateDataFaultMsg
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MoiCommonError faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public InquireGoodBehaviorCertificateDataFaultMsg(String message, MoiCommonError faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public InquireGoodBehaviorCertificateDataFaultMsg(String message, MoiCommonError faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: kw.gov.moi.common.v1.moicommonerror.MoiCommonError
     */
    public MoiCommonError getFaultInfo() {
        return faultInfo;
    }

}
