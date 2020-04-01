package com.ecut.frozenpearassistant.controller.api.ex;

/**
 * 上传的文件类型超出了限制
 */
public class FileTypeException extends FileUploadException {

	private static final long serialVersionUID = -3691055657464755670L;

	public FileTypeException() {
		super();
	}

	public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileTypeException(String message) {
		super(message);
	}

	public FileTypeException(Throwable cause) {
		super(cause);
	}

}
