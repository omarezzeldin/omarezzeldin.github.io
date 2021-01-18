package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.BasicDTO;
import com.beshara.csc.hr.emp.business.entity.DegreeDataEntity;

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
public class DegreeDataDTO extends BasicDTO implements IDegreeDataDTO {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    private Long civilId;
    private Long fincadreCode;
    private Long fingroupCode;
    private Long findegreeCode;
    private Timestamp degreeDate;
    private Long status;
    /** 
     * DegreeDataDTO Default Constructor */
    public DegreeDataDTO() {        super();
    }    /** 
     * @param degreeDataEntity 
     */
    public DegreeDataDTO(DegreeDataEntity degreeDataEntity) {        this.civilId = degreeDataEntity.getCivilId();
        this.fincadreCode = degreeDataEntity.getFincadreCode();
        this.fingroupCode = degreeDataEntity.getFingroupCode();
        this.findegreeCode = degreeDataEntity.getFindegreeCode();
        this.degreeDate = degreeDataEntity.getDegreeDate();
        this.status = degreeDataEntity.getStatus();
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
     * @return Timestamp 
     */
    public Timestamp getDegreeDate() {        return degreeDate;
    }    /** 
     * @return Long 
     */
    public Long getStatus() {        return status;
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
     * @param degreeDate 
     */
    public void setDegreeDate(Timestamp degreeDate) {        this.degreeDate = degreeDate;
    }    /** 
     * @param status 
     */
    public void setStatus(Long status) {        this.status = status;
    }}
