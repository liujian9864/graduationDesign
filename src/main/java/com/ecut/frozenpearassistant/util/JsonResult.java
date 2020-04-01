package com.ecut.frozenpearassistant.util;

/**
 * 表示控制器响应的操作结果
 * 
 * @param <T> 向客户端响应的数据的类型
 */
public class JsonResult<T> {

	private Integer state;
	private String message;
	private T data;

	public JsonResult() {
		super();
	}

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}

	public JsonResult(Throwable ex) {
		super();
		this.message = ex.getMessage();
	}

	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
