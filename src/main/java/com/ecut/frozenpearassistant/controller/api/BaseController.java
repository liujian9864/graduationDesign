package com.ecut.frozenpearassistant.controller.api;

import com.ecut.frozenpearassistant.controller.api.ex.*;
import com.ecut.frozenpearassistant.service.ex.*;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类
 */
public abstract class BaseController {
	
	/**
	 * 操作成功时的状态码
	 */
	public static final int SUCCESS = 2000;
	/**
	 * 图片上传路径
	 */
	public static final String FILEPATH = "G:\\lj\\我的\\毕设文件\\myevaluate\\src\\main\\resources\\static\\imgs\\";

	@ExceptionHandler({ServiceException.class, FileUploadException.class})
	public JsonResult<Void> handleException(Throwable ex) {
		// 创建响应结果对象
		JsonResult<Void> jsonResult = new JsonResult<>(ex);

		// 逐一判断异常类型
		if (ex instanceof UsernameDuplicateException) {
			// 4000-用户名冲突异常，例如：注册时，用户名已经被占用
			jsonResult.setState(4000);
		} else if (ex instanceof UserNotFoundException) {
			// 4001-用户数据不存在
			jsonResult.setState(4001);
		} else if (ex instanceof PasswordNotMatchException) {
			// 4002-验证密码失败
			jsonResult.setState(4002);
		} else if (ex instanceof AddressCountLimitException) {
			// 4003-收货地址的数量达到上限的异常
			jsonResult.setState(4003);
		} else if (ex instanceof AccessDeniedException) {
			// 4004-拒绝访问，例如尝试访问他人的数据
			jsonResult.setState(4004);
		} else if (ex instanceof AddressNotFoundException) {
			// 4005-收货地址数据不存在的异常
			jsonResult.setState(4005);
		} else if (ex instanceof ProductNotFoundException) {
			// 4006-商品数据不存在的异常
			jsonResult.setState(4006);
		} else if (ex instanceof InsertException) {
			// 5000-插入数据异常
			jsonResult.setState(5000);
		} else if (ex instanceof UpdateException) {
			// 5001-更新数据失败
			jsonResult.setState(5001);
		} else if (ex instanceof DeleteException) {
			// 5002-删除数据失败
			jsonResult.setState(5001);
		} else if (ex instanceof FileEmptyException) {
			// 6000-上传的文件为空的异常
			jsonResult.setState(6000);
		} else if (ex instanceof FileSizeException) {
			// 6001-上传的文件大小超出限制
			jsonResult.setState(6001);
		} else if (ex instanceof FileTypeException) {
			// 6002-上传的文件类型超出了限制
			jsonResult.setState(6002);
		} else if (ex instanceof FileStateException) {
			// 6003-上传的文件状态异常
			jsonResult.setState(6003);
		} else if (ex instanceof FileIOException) {
			// 6004-上传文件文件时出现读写错误
			jsonResult.setState(6004);
		} else if (ex instanceof FileIOException) {
			// 6004-上传文件文件时出现读写错误
			jsonResult.setState(6004);
		}

		return jsonResult;
	}
	
}
