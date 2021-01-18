package com.beshara.csc.hr.emp.business.shared.exception;

import com.beshara.csc.sharedutils.business.exception.SharedApplicationException;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ConfilectAssignmentDateException extends SharedApplicationException {


    public static final String DEF_MSG = "ConfilectAssignmentDateException";
    public static final String MSG_KEY = "ConfilectAssignmentDateException";
    public static final String BDL_KEY = "ConfilectAssignmentDateException";
    @SuppressWarnings("compatibility:-4891459692792314531")
    private static final long serialVersionUID = 1L;

    public ConfilectAssignmentDateException(String message, Throwable e, String bundleKey, String messageKey) {
        super(message, e, bundleKey, messageKey);
    }


    public ConfilectAssignmentDateException(String message, Throwable e, String messageKey) {
        super(message, e, BDL_KEY, messageKey);
    }


    public ConfilectAssignmentDateException(String message, Throwable e) {
        super(message, e, BDL_KEY, MSG_KEY);
    }


    public ConfilectAssignmentDateException(Throwable e, String bundleKey, String messageKey) {
        super(DEF_MSG, e, bundleKey, messageKey);
    }


    public ConfilectAssignmentDateException(Throwable e, String messageKey) {
        super(DEF_MSG, e, BDL_KEY, messageKey);
    }


    public ConfilectAssignmentDateException(Throwable e) {
        super(DEF_MSG, e, BDL_KEY, MSG_KEY);
    }


    public ConfilectAssignmentDateException(String message, String bundleKey, String messageKey) {
        super(message, bundleKey, messageKey);
    }


    public ConfilectAssignmentDateException(String message, String messageKey) {
        super(message, BDL_KEY, messageKey);
    }


    public ConfilectAssignmentDateException() {
        super(DEF_MSG, BDL_KEY, MSG_KEY);
    }

    public ConfilectAssignmentDateException(String message) {

        super(message, BDL_KEY, MSG_KEY);

    }
}
