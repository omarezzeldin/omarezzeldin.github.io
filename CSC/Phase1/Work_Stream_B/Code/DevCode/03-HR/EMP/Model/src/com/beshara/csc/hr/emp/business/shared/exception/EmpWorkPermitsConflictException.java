package com.beshara.csc.hr.emp.business.shared.exception;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

public class EmpWorkPermitsConflictException extends SharedApplicationException {

    /**
     *
     * Added By O.Drwish
     * B.H Team
     */
    @SuppressWarnings("compatibility:-3560771547058691166")

    private static final long serialVersionUID = 1L;
    public static final String DEF_MSG = "EmpWorkPermitsConflictException";
    public static final String MSG_KEY = "EmpWorkPermitsConflictException";
    public static final String BDL_KEY = "EmpWorkPermitsConflictException";

    public EmpWorkPermitsConflictException(String message, Throwable e, String bundleKey, String messageKey) {
        super(message, e, bundleKey, messageKey);
    }


    public EmpWorkPermitsConflictException(String message, Throwable e, String messageKey) {
        super(message, e, BDL_KEY, messageKey);
    }


    public EmpWorkPermitsConflictException(String message, Throwable e) {
        super(message, e, BDL_KEY, MSG_KEY);
    }


    public EmpWorkPermitsConflictException(Throwable e, String bundleKey, String messageKey) {
        super(DEF_MSG, e, bundleKey, messageKey);
    }


    public EmpWorkPermitsConflictException(Throwable e, String messageKey) {
        super(DEF_MSG, e, BDL_KEY, messageKey);
    }


    public EmpWorkPermitsConflictException(Throwable e) {
        super(DEF_MSG, e, BDL_KEY, MSG_KEY);
    }


    public EmpWorkPermitsConflictException(String message, String bundleKey, String messageKey) {
        super(message, bundleKey, messageKey);
    }


    public EmpWorkPermitsConflictException(String message, String messageKey) {
        super(message, BDL_KEY, messageKey);
    }


    public EmpWorkPermitsConflictException(String message) {
        super(message, BDL_KEY, MSG_KEY);
    }


    public EmpWorkPermitsConflictException() {

        super(DEF_MSG, BDL_KEY, MSG_KEY);

    }
}

