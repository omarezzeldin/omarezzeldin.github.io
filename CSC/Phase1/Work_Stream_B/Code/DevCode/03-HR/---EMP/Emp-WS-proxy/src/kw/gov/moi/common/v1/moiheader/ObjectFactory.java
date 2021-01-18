
package kw.gov.moi.common.v1.moiheader;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kw.gov.moi.common.v1.moiheader package. 
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

    private final static QName _MoiHeaderApplication_QNAME = new QName("", "Application");
    private final static QName _MoiHeaderDepartment_QNAME = new QName("", "Department");
    private final static QName _MoiHeaderRequestor_QNAME = new QName("", "Requestor");
    private final static QName _MoiHeaderEntity_QNAME = new QName("", "Entity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kw.gov.moi.common.v1.moiheader
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MoiHeader }
     * 
     */
    public MoiHeader createMoiHeader() {
        return new MoiHeader();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Application", scope = MoiHeader.class)
    public JAXBElement<String> createMoiHeaderApplication(String value) {
        return new JAXBElement<String>(_MoiHeaderApplication_QNAME, String.class, MoiHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Department", scope = MoiHeader.class)
    public JAXBElement<String> createMoiHeaderDepartment(String value) {
        return new JAXBElement<String>(_MoiHeaderDepartment_QNAME, String.class, MoiHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Requestor", scope = MoiHeader.class)
    public JAXBElement<String> createMoiHeaderRequestor(String value) {
        return new JAXBElement<String>(_MoiHeaderRequestor_QNAME, String.class, MoiHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Entity", scope = MoiHeader.class)
    public JAXBElement<String> createMoiHeaderEntity(String value) {
        return new JAXBElement<String>(_MoiHeaderEntity_QNAME, String.class, MoiHeader.class, value);
    }

}
