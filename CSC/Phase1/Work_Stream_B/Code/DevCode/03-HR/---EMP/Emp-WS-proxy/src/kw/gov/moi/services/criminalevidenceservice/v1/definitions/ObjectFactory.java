
package kw.gov.moi.services.criminalevidenceservice.v1.definitions;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import kw.gov.moi.services.criminalevidenceservice.v1.types.MoiGoodBehaviorCertificateDataIn;
import kw.gov.moi.services.criminalevidenceservice.v1.types.MoiGoodBehaviorCertificateDataOut;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kw.gov.moi.services.criminalevidenceservice.v1.definitions package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _InquireGoodBehaviorCertificateData_QNAME = new QName("http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions", "InquireGoodBehaviorCertificateData");
    private final static QName _InquireGoodBehaviorCertificateDataResponse_QNAME = new QName("http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions", "InquireGoodBehaviorCertificateDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kw.gov.moi.services.criminalevidenceservice.v1.definitions
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoiGoodBehaviorCertificateDataIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions", name = "InquireGoodBehaviorCertificateData")
    public JAXBElement<MoiGoodBehaviorCertificateDataIn> createInquireGoodBehaviorCertificateData(MoiGoodBehaviorCertificateDataIn value) {
        return new JAXBElement<MoiGoodBehaviorCertificateDataIn>(_InquireGoodBehaviorCertificateData_QNAME, MoiGoodBehaviorCertificateDataIn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoiGoodBehaviorCertificateDataOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.moi.gov.kw/Services/CriminalEvidenceService/V1/Definitions", name = "InquireGoodBehaviorCertificateDataResponse")
    public JAXBElement<MoiGoodBehaviorCertificateDataOut> createInquireGoodBehaviorCertificateDataResponse(MoiGoodBehaviorCertificateDataOut value) {
        return new JAXBElement<MoiGoodBehaviorCertificateDataOut>(_InquireGoodBehaviorCertificateDataResponse_QNAME, MoiGoodBehaviorCertificateDataOut.class, null, value);
    }

}
