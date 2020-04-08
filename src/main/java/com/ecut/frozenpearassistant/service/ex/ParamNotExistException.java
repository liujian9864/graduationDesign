package com.ecut.frozenpearassistant.service.ex;

public class ParamNotExistException extends ServiceException{
    private static final long serialVersionUID = 5156778717593377564L;

    public ParamNotExistException() {
        super();
    }

    public ParamNotExistException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ParamNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamNotExistException(String message) {
        super(message);
    }

    public ParamNotExistException(Throwable cause) {
        super(cause);
    }
}
