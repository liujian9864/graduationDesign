package com.ecut.frozenpearassistant.controller.api.ex;

/**
 * 上传文件文件时出现读写错误
 */
public class FileIOException extends FileUploadException {

	private static final long serialVersionUID = 1007256760091647496L;

	public FileIOException() {
		super();
	}

	public FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileIOException(String message) {
		super(message);
	}

	public FileIOException(Throwable cause) {
		super(cause);
	}

}
