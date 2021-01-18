package com.beshara.csc.hr.emp.business.dto;


import com.beshara.base.dto.TreeDTO;
import com.beshara.csc.hr.emp.business.entity.EmpContractTypesEntityKey;


/**
 * @author Hany Omar
 * @version 1.0
 * @since 30/11/2016
 */
public class EmpContractTypesDTO extends TreeDTO implements IEmpContractTypesDTO {

    @SuppressWarnings("compatibility:-4485906751177687898")
    private static final long serialVersionUID = 1L;

    public EmpContractTypesDTO() {
        super();
    }

    public EmpContractTypesDTO(Long code, String name) {
        this.setCode(new EmpContractTypesEntityKey(code));
        this.setName(name);
    }

}
