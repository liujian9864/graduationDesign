package com.ecut.frozenpearassistant.service.ex;

/**
 * 添加到购物车中的商品已经达到上限
 */
public class ProductCountLimitException extends ServiceException {

	private static final long serialVersionUID = -7910708903035803498L;

	public ProductCountLimitException() {
		super();
	}

	public ProductCountLimitException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductCountLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductCountLimitException(String message) {
		super(message);
	}

	public ProductCountLimitException(Throwable cause) {
		super(cause);
	}

}
