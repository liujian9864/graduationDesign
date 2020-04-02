package com.ecut.frozenpearassistant.controller.api;

import com.ecut.frozenpearassistant.orm.entity.District;
import com.ecut.frozenpearassistant.service.DistrictService;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {

	@Autowired
	private DistrictService districtService;
	
	@GetMapping("/")
	public JsonResult<List<District>> getByParent(String parent) {
		List<District> data = districtService.getByParent(parent);
		return new JsonResult<>(SUCCESS, data);
	}
	
}







