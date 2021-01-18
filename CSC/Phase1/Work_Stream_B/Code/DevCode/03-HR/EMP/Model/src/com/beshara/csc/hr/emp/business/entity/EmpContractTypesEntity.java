
package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * @author       Beshara Group
 * @author       Hany Omar
 * @version      1.0
 * @since        30/11/2016
 */
@Entity
@NamedQueries( { @NamedQuery(name = "EmpContractTypesEntity.findAll",
                             query = "select o from EmpContractTypesEntity o")})
@Table(name = "HR_EMP_CONTRACT_TYPES")
@IdClass(IEmpContractTypesEntityKey.class)

public class EmpContractTypesEntity extends BasicEntity {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "CNTTYPE_CODE", nullable = false)
    private Long cntTypeCode;
    @Column(name = "CNTTYPE_NAME", nullable = false)
    private String cntTypeName;
    @Column(name = "TABREC_SERIAL", nullable = true)
    private Long tabrecSerial;

    public EmpContractTypesEntity() {

    }

    public void setCntTypeCode(Long cntTypeCode) {
        this.cntTypeCode = cntTypeCode;
    }

    public Long getCntTypeCode() {
        return cntTypeCode;
    }

    public void setCntTypeName(String cntTypeName) {
        this.cntTypeName = cntTypeName;
    }

    public String getCntTypeName() {
        return cntTypeName;
    }

    public void setTabrecSerial(Long tabrecSerial) {
        this.tabrecSerial = tabrecSerial;
    }

    public Long getTabrecSerial() {
        return tabrecSerial;
    }
}
