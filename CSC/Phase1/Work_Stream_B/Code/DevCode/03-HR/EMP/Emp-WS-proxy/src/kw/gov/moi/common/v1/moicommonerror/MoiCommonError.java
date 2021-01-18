
package kw.gov.moi.common.v1.moicommonerror;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RaisedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CausedBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ErrorText" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="Severity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "raisedBy",
    "code",
    "causedBy",
    "errorText",
    "severity"
})
@XmlRootElement(name = "MoiCommonError")
public class MoiCommonError {

    @XmlElement(name = "RaisedBy", required = true)
    protected String raisedBy;
    @XmlElement(name = "Code", required = true)
    protected String code;
    @XmlElement(name = "CausedBy", required = true)
    protected String causedBy;
    @XmlElement(name = "ErrorText", required = true)
    protected List<String> errorText;
    @XmlElement(name = "Severity")
    protected Integer severity;

    /**
     * Gets the value of the raisedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaisedBy() {
        return raisedBy;
    }

    /**
     * Sets the value of the raisedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaisedBy(String value) {
        this.raisedBy = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the causedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausedBy() {
        return causedBy;
    }

    /**
     * Sets the value of the causedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausedBy(String value) {
        this.causedBy = value;
    }

    /**
     * Gets the value of the errorText property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errorText property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrorText().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getErrorText() {
        if (errorText == null) {
            errorText = new ArrayList<String>();
        }
        return this.errorText;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeverity(Integer value) {
        this.severity = value;
    }

}
