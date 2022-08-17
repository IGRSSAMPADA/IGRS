package com.wipro.igrs.report.util;

/**
 * 
 * ===========================================================================
 * File           :   IGRSException.java
 * Description    :   Represents the Exception Class

 * Author         :   Prasanthan
 * Created Date   :   Nov 26, 2007

 * ===========================================================================
 */
public class IGRSException extends Exception {

    private String errorCode = null; // Error code for the exception.


    /**
     * Constructs a new IGRSException.
     */
    public IGRSException() {
        super();
    }

    /**
     * Constructs a new IGRSException with the specified error
     * message.
     *
     * @param   message   the error message.
     */
    public IGRSException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructs a new IGRSException with the specified error
     * message.
     *
     * @param   message   the error message.
     */
    public IGRSException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new IGRSException with the specified exception
     * message.
     *
     * @param   throwable   The parent Throwable.
     */
    public IGRSException(Throwable throwable) {
        super(throwable);
    }

    /**
     * Constructs a new IGRSException with the specified exception
     * message.
     *
     * @param msg    the error message
     * @param cause  the exception or error that caused this exception to be
     * thrown
     */
    public IGRSException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     *  Returns the error code of the IGRSException object.
     *  @return String
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     *  Sets the errorCode in the IGRSException object.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
