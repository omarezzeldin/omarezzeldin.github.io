package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * <b>Description:</b>
 * <br>&nbsp;&nbsp;&nbsp;
 * This Class Manipulate the Persistence Methods of IncomeData Entity.
 * <br><br>
 * <b>Development Environment :</b>
 * <br>&nbsp;
 * Oracle JDeveloper 10g (10.1.3.3.0.4157)
 * <br><br>
 * <b>Creation/Modification History :</b>
 * <br>&nbsp;&nbsp;&nbsp;
 *    Code Generator    03-SEP-2007     Created
 * <br>&nbsp;&nbsp;&nbsp;
 *    Developer Name  06-SEP-2007   Updated
 *  <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 *     - Add Javadoc Comments to Methods.
 *
 * @author       Beshara Group
 * @author       Ahmed Sabry, Sherif El-Rabbat, Taha El-Fitiany
 * @version      1.0
 * @since        03/09/2007
 */

@Entity
@NamedQueries({
@NamedQuery(name = "IncomeDataEntity.findAll", query = "select o from IncomeDataEntity o"),
@NamedQuery(name = "IncomeDataEntity.findNewId", query = "select MAX(o.civilId) from IncomeDataEntity o")
})
@Table(name = "HR_EMP_INCOME_DATA")
public class IncomeDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
@Column(name="CIVIL_ID", nullable=true)
private Long civilId;
@Column(name="CATINCOME_CODE", nullable=true)
private Long catincomeCode;
@Column(name="INCOME_CODE", nullable=true)
private Long incomeCode;
@Column(name="INCCAT_CODE", nullable=true)
private Long inccatCode;
@Column(name="FROM_DATE", nullable=true)
private Timestamp fromDate;
@Column(name="VALUE", nullable=true)
private Long value;
@Column(name="CALC_FUNCTION", nullable=true)
private String calcFunction;
 

    /**
     * IncomeDataEntity Default Constructor
     */    
    public IncomeDataEntity() {
    }


/**
* @return Long
*/
public Long getCivilId() {
   return civilId;
}
/**
* @return Long
*/
public Long getCatincomeCode() {
   return catincomeCode;
}
/**
* @return Long
*/
public Long getIncomeCode() {
   return incomeCode;
}
/**
* @return Long
*/
public Long getInccatCode() {
   return inccatCode;
}
/**
* @return Timestamp
*/
public Timestamp getFromDate() {
   return fromDate;
}
/**
* @return Long
*/
public Long getValue() {
   return value;
}
/**
* @return String
*/
public String getCalcFunction() {
   return calcFunction;
}


/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param catincomeCode
*/
public void setCatincomeCode(Long catincomeCode) {
   this.catincomeCode=catincomeCode;
}
/**
* @param incomeCode
*/
public void setIncomeCode(Long incomeCode) {
   this.incomeCode=incomeCode;
}
/**
* @param inccatCode
*/
public void setInccatCode(Long inccatCode) {
   this.inccatCode=inccatCode;
}
/**
* @param fromDate
*/
public void setFromDate(Timestamp fromDate) {
   this.fromDate=fromDate;
}
/**
* @param value
*/
public void setValue(Long value) {
   this.value=value;
}
/**
* @param calcFunction
*/
public void setCalcFunction(String calcFunction) {
   this.calcFunction=calcFunction;
}


}
