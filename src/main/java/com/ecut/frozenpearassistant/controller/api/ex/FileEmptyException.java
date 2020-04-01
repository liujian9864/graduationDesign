package com.ecut.frozenpearassistant.controller.api.ex;

/**
 * 上传的文件为空的异常，可能客户端并没有提交文件，或文件为0字节
 */
public class FileEmptyException extends FileUploadException {

	private static final long serialVersionUID = 1241017634099485103L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
	}

}
