package com.beshara.csc.hr.emp.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.sql.Timestamp;

import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.beshara.base.entity.BasicEntity;

/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

@Entity
@NamedQueries({
@NamedQuery(name = "EmpMoiWsCriminalEntity.findAll", query = "select o from EmpMoiWsCriminalEntity o"),
@NamedQuery(name = "EmpMoiWsCriminalEntity.findNewId", query = "select MAX(o.crmWsSerial) from EmpMoiWsCriminalEntity o")
,
   @NamedQuery(name = "EmpMoiWsCriminalEntity.searchByCode", query = "select o from EmpMoiWsCriminalEntity o where o.crmWsSerial=:code")


})
@Table(name = "HR_EMP_MOI_WS_CRIMINAL")
@IdClass(IEmpMoiWsCriminalEntityKey.class)

public class EmpMoiWsCriminalEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


@Id
@Column(name="CRM_WS_SERIAL", nullable=false)
private Long crmWsSerial;
@Column(name="USER_CODE", nullable=false)
private Long userCode;
@Column(name="CIVIL_ID", nullable=false)
private Long civilId;
@Column(name="CRMSTATUS_CODE", nullable=false)
private String crmstatusCode;
@Column(name="LAST_LOGIN_DATE", nullable=false)
private Timestamp lastLoginDate;
//@ManyToOne 
//@JoinColumn(name="CIVIL_ID", referencedColumnName="CIVIL_ID")
//private EmpCitizensResidentsEntity empCitizensResidentsEntity;
//@ManyToOne 
//@JoinColumn(name="CRMSTATUS_CODE", referencedColumnName="CRMSTATUS_CODE")
//private EmpCriminalStatusEntity empCriminalStatusEntity;
//@ManyToOne 
//@JoinColumn(name="USER_CODE", referencedColumnName="USER_CODE")
//private EmpUsersEntity empUsersEntity;
 

    /**
     * EmpMoiWsCriminalEntity Default Constructor
     */    
    public EmpMoiWsCriminalEntity() {
    }


/**
* @return Long
*/
public Long getCrmWsSerial() {
   return crmWsSerial;
}
/**
* @return Long
*/
public Long getUserCode() {
   return userCode;
}
/**
* @return Long
*/
public Long getCivilId() {
   return civilId;
}
/**
* @return String
*/
public String getCrmstatusCode() {
   return crmstatusCode;
}
/**
* @return Timestamp
*/
public Timestamp getLastLoginDate() {
   return lastLoginDate;
}


/**
* @param crmWsSerial
*/
public void setCrmWsSerial(Long crmWsSerial) {
   this.crmWsSerial=crmWsSerial;
}
/**
* @param userCode
*/
public void setUserCode(Long userCode) {
   this.userCode=userCode;
}
/**
* @param civilId
*/
public void setCivilId(Long civilId) {
   this.civilId=civilId;
}
/**
* @param crmstatusCode
*/
public void setCrmstatusCode(String crmstatusCode) {
   this.crmstatusCode=crmstatusCode;
}
/**
* @param lastLoginDate
*/
public void setLastLoginDate(Timestamp lastLoginDate) {
   this.lastLoginDate=lastLoginDate;
}


}
