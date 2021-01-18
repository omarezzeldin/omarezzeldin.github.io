package com.beshara.csc.hr.emp.business.entity;


import com.beshara.base.entity.IEntityKey;

import java.sql.Timestamp;


public interface IAllEmployeesEntityKey extends IEntityKey {
    public int hashCode();

    public Long getCivilId();

    public Timestamp getTrxDatetime();

}
