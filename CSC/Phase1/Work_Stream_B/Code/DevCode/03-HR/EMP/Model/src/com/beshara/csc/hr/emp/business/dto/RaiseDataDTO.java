package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.RaiseDataEntity;

import java.sql.Timestamp;

/**
 * This Class Represents the Data Transfer Object which used across the Applications Architecture Layers I.I * <br><br> * <b>Development Environment :</b> * <br>&nbsp ;
 * Oracle JDeveloper 10g ( 10.1.3.3.0.4157 ) * <br><br> * <b>Creation/Modification History :</b> * <br>&nbsp ; &nbsp ; &nbsp ;
 * Taha Fitiany 03-SEP-2007 Created * <br>&nbsp ; &nbsp ; &nbsp ;
 * Developer Name 06-SEP-2007 Updated * <br>&nbsp ; &nbsp ; &nbsp ; &nbsp ; &nbsp ;
 * - Add Javadoc Comments to IMIeItIhIoIdIsI.I * * @author Beshara Group
 * @author Ahmed Sabry , Sherif El-Rabbat , Taha El-Fitiany
 * @version 1.0
 * @since 03/09/2007
 */
public class RaiseDataDTO extends BasicDTO implements IRaiseDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long fincadreCode;
    private Long fingroupCode;
    private Long findegreeCode;
    private Long finraiseCode;
    private Long degreasonCode;
    private Timestamp fromDate;
    private Long value;
    private String calcFunction;
    /** 
     * RaiseDataDTO Default Constructor */
    public RaiseDataDTO() {        super();
    }    /** 
     * @param raiseDataEntity 
     */
    public RaiseDataDTO(RaiseDataEntity raiseDataEntity) {        this.civilId = raiseDataEntity.getCivilId();
        this.fincadreCode = raiseDataEntity.getFincadreCode();
        this.fingroupCode = raiseDataEntity.getFingroupCode();
        this.findegreeCode = raiseDataEntity.getFindegreeCode();
        this.finraiseCode = raiseDataEntity.getFinraiseCode();
        this.degreasonCode = raiseDataEntity.getDegreasonCode();
        this.fromDate = raiseDataEntity.getFromDate();
        this.value = raiseDataEntity.getValue();
        this.calcFunction = raiseDataEntity.getCalcFunction();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getFincadreCode() {        return fincadreCode;
    }    /** 
     * @return Long 
     */
    public Long getFingroupCode() {        return fingroupCode;
    }    /** 
     * @return Long 
     */
    public Long getFindegreeCode() {        return findegreeCode;
    }    /** 
     * @return Long 
     */
    public Long getFinraiseCode() {        return finraiseCode;
    }    /** 
     * @return Long 
     */
    public Long getDegreasonCode() {        return degreasonCode;
    }    /** 
     * @return Timestamp 
     */
    public Timestamp getFromDate() {        return fromDate;
    }    /** 
     * @return Long 
     */
    public Long getValue() {        return value;
    }    /** 
     * @return String 
     */
    public String getCalcFunction() {        return calcFunction;
    }    /** 
     * @param civilId 
     */
    public void setCivilId(Long civilId) {        this.civilId = civilId;
    }    /** 
     * @param fincadreCode 
     */
    public void setFincadreCode(Long fincadreCode) {        this.fincadreCode = fincadreCode;
    }    /** 
     * @param fingroupCode 
     */
    public void setFingroupCode(Long fingroupCode) {        this.fingroupCode = fingroupCode;
    }    /** 
     * @param findegreeCode 
     */
    public void setFindegreeCode(Long findegreeCode) {        this.findegreeCode = findegreeCode;
    }    /** 
     * @param finraiseCode 
     */
    public void setFinraiseCode(Long finraiseCode) {        this.finraiseCode = finraiseCode;
    }    /** 
     * @param degreasonCode 
     */
    public void setDegreasonCode(Long degreasonCode) {        this.degreasonCode = degreasonCode;
    }    /** 
     * @param fromDate 
     */
    public void setFromDate(Timestamp fromDate) {        this.fromDate = fromDate;
    }    /** 
     * @param value 
     */
    public void setValue(Long value) {        this.value = value;
    }    /** 
     * @param calcFunction 
     */
    public void setCalcFunction(String calcFunction) {        this.calcFunction = calcFunction;
    }}
