package com.beshara.csc.hr.emp.business.entity;

import com.beshara.base.entity.IEntityKey;

public interface IEmployeeSearchEntityKey extends IEntityKey {
    public int hashCode();

    public Long getCivilId();

}
