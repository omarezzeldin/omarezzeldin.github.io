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
 * This Class Manipulate the Persistence Methods of RaiseData Entity.
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
@NamedQuery(name = "RaiseDataEntity.findAll", query = "select o from RaiseDataEntity o"),
@NamedQuery(name = "RaiseDataEntity.findNewId", query = "select MAX(o.civilId) from RaiseDataEntity o")
})
@Table(name = "HR_EMP_RAISE_DATA")
public class RaiseDataEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;

    @Id
@Column(name="CIVIL_ID", nullable=false)
private Long civilId;
@Column(name="FINCADRE_CODE", nullable=true)
private Long fincadreCode;
@Column(name="FINGROUP_CODE", nullable=true)
private Long fingroupCode;
@Column(name="FINDEGREE_CODE", nullable=true)
private Long findegreeCode;
@Column(name="FINRAISE_CODE", nullable=false)
private Long finraiseCode;
@Column(name="DEGREASON_CODE", nullable=true)
private Long degreasonCode;
@Column(name="FROM_DATE", nullable=false)
private Timestamp fromDate;
@Column(name="VALUE", nullable=true)
private Long value;
@Column(name="CALC_FUNCTION", nullable=true)
private String calcFunction;
 

    /**
     * RaiseDataEntity Default Constructor
     */    
    public RaiseDataEntity() {
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
public Long getFincadreCode() {
   return fincadreCode;
}
/**
* @return Long
*/
public Long getFingroupCode() {
   return fingroupCode;
}
/**
* @return Long
*/
public Long getFindegreeCode() {
   return findegreeCode;
}
/**
* @return Long
*/
public Long getFinraiseCode() {
   return finraiseCode;
}
/**
* @return Long
*/
public Long getDegreasonCode() {
   return degreasonCode;
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
* @param fincadreCode
*/
public void setFincadreCode(Long fincadreCode) {
   this.fincadreCode=fincadreCode;
}
/**
* @param fingroupCode
*/
public void setFingroupCode(Long fingroupCode) {
   this.fingroupCode=fingroupCode;
}
/**
* @param findegreeCode
*/
public void setFindegreeCode(Long findegreeCode) {
   this.findegreeCode=findegreeCode;
}
/**
* @param finraiseCode
*/
public void setFinraiseCode(Long finraiseCode) {
   this.finraiseCode=finraiseCode;
}
/**
* @param degreasonCode
*/
public void setDegreasonCode(Long degreasonCode) {
   this.degreasonCode=degreasonCode;
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
