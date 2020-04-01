package com.ecut.frozenpearassistant.service.ex;

/**
 *删除数据异常
 */
public class DeleteException extends ServiceException {

	private static final long serialVersionUID = -8881103463792318580L;

	public DeleteException() {
		super();
	}

	public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeleteException(String message) {
		super(message);
	}

	public DeleteException(Throwable cause) {
		super(cause);
	}

}
