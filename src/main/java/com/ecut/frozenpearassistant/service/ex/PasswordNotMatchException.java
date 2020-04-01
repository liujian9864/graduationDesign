package com.ecut.frozenpearassistant.service.ex;

/**
 * 验证密码失败
 */
public class PasswordNotMatchException extends ServiceException {

	private static final long serialVersionUID = 1903639604855086304L;

	public PasswordNotMatchException() {
		super();
	}

	public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordNotMatchException(String message) {
		super(message);
	}

	public PasswordNotMatchException(Throwable cause) {
		super(cause);
	}

}
