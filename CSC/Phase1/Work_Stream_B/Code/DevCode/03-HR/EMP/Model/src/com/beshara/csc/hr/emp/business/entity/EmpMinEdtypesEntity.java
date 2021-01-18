package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;
import com.beshara.csc.hr.emp.business.entity.org.EmpMinistriesEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author       Beshara Group
 * @author       CappuchinoTeam
 * @version      1.0
 * @since        27/12/2015
 */

@Entity
@NamedQueries({
@NamedQuery(name = "EmpMinEdtypesEntity.findAll", query = "select o from EmpMinEdtypesEntity o"),
@NamedQuery(name = "EmpMinEdtypesEntity.findNewId", query = "select MAX(o.minCode) from EmpMinEdtypesEntity o"),
@NamedQuery(name = "EmpMinEdtypesEntity.getByMinCode", query = "select o from EmpMinEdtypesEntity o where o.minCode =:minCode")

})
@Table(name = "HR_EMP_MIN_EDTYPES")
@IdClass(IEmpMinEdtypesEntityKey.class)

public class EmpMinEdtypesEntity extends BasicEntity {

	@SuppressWarnings("compatibility:-4485906751177687898")
	private static final long serialVersionUID = 1L;


@Id
@Column(name="MIN_CODE", nullable=false)
private Long minCode;
@Id
@Column(name="EXTDATTYPE_CODE", nullable=false)
private Long extdattypeCode;
@ManyToOne 
@JoinColumn(name="EXTDATTYPE_CODE", referencedColumnName="EXTDATTYPE_CODE" , insertable = false , updatable = false)
private EmpExtraDataTypesEntity empExtraDataTypesEntity;
@ManyToOne 
@JoinColumn(name="MIN_CODE", referencedColumnName="MIN_CODE" , insertable = false , updatable = false)
private EmpMinistriesEntity empMinistriesEntity;
 

    /**
     * EmpMinEdtypesEntity Default Constructor
     */    
    public EmpMinEdtypesEntity() {
    }


/**
* @return Long
*/
public Long getMinCode() {
   return minCode;
}
/**
* @return Long
*/
public Long getExtdattypeCode() {
   return extdattypeCode;
}


/**
* @param minCode
*/
public void setMinCode(Long minCode) {
   this.minCode=minCode;
}
/**
* @param extdattypeCode
*/
public void setExtdattypeCode(Long extdattypeCode) {
   this.extdattypeCode=extdattypeCode;
}


    public void setEmpExtraDataTypesEntity(EmpExtraDataTypesEntity empExtraDataTypesEntity) {
        this.empExtraDataTypesEntity = empExtraDataTypesEntity;
    }

    public EmpExtraDataTypesEntity getEmpExtraDataTypesEntity() {
        return empExtraDataTypesEntity;
    }

    public void setEmpMinistriesEntity(EmpMinistriesEntity empMinistriesEntity) {
        this.empMinistriesEntity = empMinistriesEntity;
    }

    public EmpMinistriesEntity getEmpMinistriesEntity() {
        return empMinistriesEntity;
    }
}
