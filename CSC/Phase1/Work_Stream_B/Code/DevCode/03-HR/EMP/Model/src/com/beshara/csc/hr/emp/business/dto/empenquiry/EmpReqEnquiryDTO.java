package com.beshara.csc.hr.emp.business.dto.empenquiry;


import com.beshara.base.dto.BasicDTO;
import com.beshara.base.paging.IPagingRequestDTO;

import com.beshara.csc.hr.emp.business.dto.EmpDTO;

import java.sql.Timestamp;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author A.Elmasry
 * @version 1.0
 * @since 20/03/2019
 */
public class EmpReqEnquiryDTO extends EmpDTO implements IEmpReqEnquiryDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

   
    private Long realCivilId;
    private String fullName;
    private String hireTypeName;
    private String candMinName;
    private Long concernedMinCode;
    private String concernedMinName;
    private Timestamp requestArrivalDate;
    private String hireStageName;
    private Long minCode;
    private IPagingRequestDTO pagingRequestDTO;
    
    public EmpReqEnquiryDTO(){
        
    }

    public void setRealCivilId(Long realCivilId) {
        this.realCivilId = realCivilId;
    }

    public Long getRealCivilId() {
        return realCivilId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setHireTypeName(String hireType) {
        this.hireTypeName = hireType;
    }

    public String getHireTypeName() {
        return hireTypeName;
    }

    public void setCandMinName(String candMinName) {
        this.candMinName = candMinName;
    }

    public String getCandMinName() {
        return candMinName;
    }

    public void setConcernedMinCode(Long concernedMinCode) {
        this.concernedMinCode = concernedMinCode;
    }

    public Long getConcernedMinCode() {
        return concernedMinCode;
    }

    public void setConcernedMinName(String concernedMinName) {
        this.concernedMinName = concernedMinName;
    }

    public String getConcernedMinName() {
        return concernedMinName;
    }

    public void setRequestArrivalDate(Timestamp requestArrivalDate) {
        this.requestArrivalDate = requestArrivalDate;
    }

    public Timestamp getRequestArrivalDate() {
        return requestArrivalDate;
    }

    public void setHireStageName(String status) {
        this.hireStageName = status;
    }

    public String getHireStageName() {
        return hireStageName;
    }

    public void setMinCode(Long minCode) {
        this.minCode = minCode;
    }

    public Long getMinCode() {
        return minCode;
    }

    public void setPagingRequestDTO(IPagingRequestDTO pagingRequestDTO) {
        this.pagingRequestDTO = pagingRequestDTO;
    }

    public IPagingRequestDTO getPagingRequestDTO() {
        return pagingRequestDTO;
    }
}
