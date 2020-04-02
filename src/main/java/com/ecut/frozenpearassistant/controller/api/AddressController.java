package com.ecut.frozenpearassistant.controller.api;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.param.AddressParam;
import com.ecut.frozenpearassistant.service.AddressService;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping("addnew")
	public JsonResult<Void> addnew(
			AddressParam address, HttpSession session) {
		// 从session中获取uid和username
		String userId = (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		// 执行增加收货地址
		addressService.addnew(userId, userName, address);
		// 响应成功
		return new JsonResult<>(SUCCESS);
	}
	
	@GetMapping("/")
	public JsonResult<List<AddressEntity>> getByUid(HttpSession session) {
		// 从session中获取uid
		String userId = (String)session.getAttribute("userId");
		// 调用业务对象执行获取数据
		List<AddressEntity> data = addressService.getByUid(userId);
		// 响应：成功，数据
		return new JsonResult<>(SUCCESS, data);
	}

	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(
		@PathVariable("aid") String aid,
		HttpSession session) {
		// 从session中获取uid和username
		String userId = (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		// 调用业务层对象执行
		addressService.setDefault(aid, userId, userName);
		// 响应
		return new JsonResult<>(SUCCESS);
	}
	
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> delete(
			@PathVariable("aid") String aid,
			HttpSession session) {
		// 从session中获取uid和username
		String userId = (String)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		// 调用业务层对象执行
		addressService.delete(aid, userId, userName);
		// 响应
		return new JsonResult<>(SUCCESS);
	}
	
}









