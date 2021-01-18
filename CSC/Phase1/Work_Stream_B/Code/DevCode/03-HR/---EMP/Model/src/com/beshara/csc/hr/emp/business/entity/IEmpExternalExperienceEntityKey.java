package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.IEntityKey;

import java.math.BigDecimal;


public interface IEmpExternalExperienceEntityKey extends IEntityKey {
    public int hashCode();

    public BigDecimal getSerial();
}
