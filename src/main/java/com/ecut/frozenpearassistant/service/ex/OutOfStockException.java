package com.ecut.frozenpearassistant.service.ex;

/**
 * 商品库存不足的异常
 */
public class OutOfStockException extends ServiceException {

	private static final long serialVersionUID = -5352456079398791666L;

	public OutOfStockException() {
		super();
	}

	public OutOfStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OutOfStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutOfStockException(String message) {
		super(message);
	}

	public OutOfStockException(Throwable cause) {
		super(cause);
	}

}
