package com.ecut.frozenpearassistant.service.ex;

/**
 * 订单数据不存在
 */
public class OrderNotFoundException extends ServiceException {

	private static final long serialVersionUID = 8046529084414757149L;

	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
	}

}
