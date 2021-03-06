
package com.beshara.csc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import kw.gov.moi.common.v1.moiheader.MoiHeader;
import kw.gov.moi.services.criminalevidenceservice.v1.types.MoiGoodBehaviorCertificateDataIn;
import kw.gov.moi.services.criminalevidenceservice.v1.types.MoiGoodBehaviorCertificateDataOut;


/**
 * This class was generated by the JAX-WS RI.
 * Oracle JAX-WS 2.1.5
 * Generated source version: 2.1
 * 
 */
@WebService(name = "CriminalEvidenceService", targetNamespace = "http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    kw.gov.moi.common.v1.moicommonerror.ObjectFactory.class,
    kw.gov.moi.common.v1.moiheader.ObjectFactory.class,
    kw.gov.moi.services.criminalevidenceservice.v1.definitions.ObjectFactory.class,
    kw.gov.moi.services.criminalevidenceservice.v1.types.ObjectFactory.class
})
public interface CriminalEvidenceService {


    /**
     * 
     * @param inquireGoodBehaviorCertificateDataRequestParam
     * @param moiHeader
     * @return
     *     returns kw.gov.moi.services.criminalevidenceservice.v1.types.MoiGoodBehaviorCertificateDataOut
     * @throws InquireGoodBehaviorCertificateDataFaultMsg
     */
    @WebMethod(action = "http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/inquireGoodBehaviorCertificateData")
    @WebResult(name = "InquireGoodBehaviorCertificateDataResponse", targetNamespace = "http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions", partName = "InquireGoodBehaviorCertificateDataResponseParam")
    public MoiGoodBehaviorCertificateDataOut inquireGoodBehaviorCertificateData(
        @WebParam(name = "MoiHeader", targetNamespace = "http://www.moi.gov.kw/Common/V1/MoiHeader", header = true, mode = WebParam.Mode.INOUT, partName = "MoiHeader")
        Holder<MoiHeader> moiHeader,
        @WebParam(name = "InquireGoodBehaviorCertificateData", targetNamespace = "http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions", partName = "InquireGoodBehaviorCertificateDataRequestParam")
        MoiGoodBehaviorCertificateDataIn inquireGoodBehaviorCertificateDataRequestParam)
        throws InquireGoodBehaviorCertificateDataFaultMsg
    ;

}
