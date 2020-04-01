package com.ecut.frozenpearassistant.controller.api.ex;

/**
 * 上传的文件状态异常，例如上传过程中文件被移动，使得文件的数据不可被访问
 */
public class FileStateException extends FileUploadException {

	private static final long serialVersionUID = -7150153238399934786L;

	public FileStateException() {
		super();
	}

	public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileStateException(String message) {
		super(message);
	}

	public FileStateException(Throwable cause) {
		super(cause);
	}

}
