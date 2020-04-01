package com.ecut.frozenpearassistant.service.ex;

/**
 * 收货地址的数量达到上限的异常
 */
public class AddressCountLimitException extends ServiceException {

	private static final long serialVersionUID = -634112102870076215L;

	public AddressCountLimitException() {
		super();
	}

	public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressCountLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressCountLimitException(String message) {
		super(message);
	}

	public AddressCountLimitException(Throwable cause) {
		super(cause);
	}

}
