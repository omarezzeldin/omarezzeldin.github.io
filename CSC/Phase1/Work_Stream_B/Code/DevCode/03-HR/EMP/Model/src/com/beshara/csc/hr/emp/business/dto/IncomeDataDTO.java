package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.IncomeDataEntity;

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
public class IncomeDataDTO extends BasicDTO implements IIncomeDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long catincomeCode;
    private Long incomeCode;
    private Long inccatCode;
    private Timestamp fromDate;
    private Long value;
    private String calcFunction;
    /** 
     * IncomeDataDTO Default Constructor */
    public IncomeDataDTO() {        super();
    }    /** 
     * @param incomeDataEntity 
     */
    public IncomeDataDTO(IncomeDataEntity incomeDataEntity) {        this.civilId = incomeDataEntity.getCivilId();
        this.catincomeCode = incomeDataEntity.getCatincomeCode();
        this.incomeCode = incomeDataEntity.getIncomeCode();
        this.inccatCode = incomeDataEntity.getInccatCode();
        this.fromDate = incomeDataEntity.getFromDate();
        this.value = incomeDataEntity.getValue();
        this.calcFunction = incomeDataEntity.getCalcFunction();
    }    /** 
     * @return Long 
     */
    public Long getCivilId() {        return civilId;
    }    /** 
     * @return Long 
     */
    public Long getCatincomeCode() {        return catincomeCode;
    }    /** 
     * @return Long 
     */
    public Long getIncomeCode() {        return incomeCode;
    }    /** 
     * @return Long 
     */
    public Long getInccatCode() {        return inccatCode;
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
     * @param catincomeCode 
     */
    public void setCatincomeCode(Long catincomeCode) {        this.catincomeCode = catincomeCode;
    }    /** 
     * @param incomeCode 
     */
    public void setIncomeCode(Long incomeCode) {        this.incomeCode = incomeCode;
    }    /** 
     * @param inccatCode 
     */
    public void setInccatCode(Long inccatCode) {        this.inccatCode = inccatCode;
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
