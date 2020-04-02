package com.ecut.frozenpearassistant.service;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.orm.entity.District;
import com.ecut.frozenpearassistant.orm.mapper.AddressMapper;
import com.ecut.frozenpearassistant.param.AddressParam;
import com.ecut.frozenpearassistant.service.ex.*;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 处理收货地址数据的业务层实现类
 */
@Service
public class AddressService {

	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private DistrictService districtService;
	

	private Integer maxCount=5;
	
	public void addnew(String userId, String username, AddressParam address) {
		// 根据参数uid查询该用户的收货地址的数量
		Integer count = countByUid(userId);
		// 判断数量是否达到上限(>=5)
		if (count >= maxCount) {
			// 是：抛出AddressCountLimitException
			throw new AddressCountLimitException(
				"收货地址数量已经达到上限(" + maxCount + ")");
		}

		// 判断数量是否为0
		// 是：isDefault=1
		// 否：isDefault=0
		Integer isDefault = count == 0 ? 1 : 0;

		// 补全数据：uid
		address.setUserId(userId);
		address.setAddressId(UUID.randomUUID().toString().replace("-","").replace("a","")
				.replace("b","").replace("c","").replace("d","")
				.replace("e","").replace("f","").replace("0","").substring(0,10));

		// 补全数据：is_default
		address.setIsDefault(isDefault);
		// 补全数据：省/市/区的名称
		String provinceName = getDistrictName(address.getProvinceCode());
		String cityName = getDistrictName(address.getCityCode());
		String areaName = getDistrictName(address.getAreaCode());
		address.setProvinceName(provinceName);
		address.setCityName(cityName);
		address.setAreaName(areaName);
		// 补全数据：4项日志
		Date now = new Date();

		// 执行插入数据，获取返回值
		insert(address);
	}

	public List<AddressEntity> getByUid(String userId) {
		List<AddressEntity> list = findByUid(userId);
		for (AddressEntity address : list) {
			address.setUserId(null);
			address.setProvinceCode(null);
			address.setCityCode(null);
			address.setAreaCode(null);
		}
		return list;
	}

	public void setDefault(String aid, String uid, String username) {
		// 根据参数aid查询收货地址数据
		AddressEntity result = findByAid(aid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException(
				"尝试访问的收货地址数据不存在");
		}

		// 判断查询结果中的uid与参数uid是否不一致(使用equals)
		if (!result.getUserId().equals(uid)) {
			// 是：AccessDeniedException
			throw new AccessDeniedException("非法访问");
		}

		// 将该用户的所有收货地址设置为非默认，并获取返回值
		updateNonDefault(uid);

		// 将指定的收货地址设置为默认，并获取返回值
		updateDefault(aid, username, new Date());
	}

	public void delete(String aid, String uid, String username) {
		// 根据参数aid查询收货地址数据
		AddressEntity result = findByAid(aid);
		// 判断查询结果是否为null
		if (result == null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException(
				"尝试访问的收货地址数据不存在");
		}

		// 判断查询结果中的uid与参数uid是否不一致
		if (!result.getUserId().equals(uid)) {
			// 是：AccessDeniedException
			throw new AccessDeniedException(
				"非法访问");
		}

		// 执行删除，获取返回的受影响行数
		deleteByAid(aid);

	}
	
	public AddressEntity getByAid(String aid) {
		AddressEntity result = findByAid(aid);
		if (result == null) {
			throw new AddressNotFoundException(
				"尝试访问的收货地址数据不存在");
		}
		
		return result;
	}
	
	/**
	 * 根据省/市/区的代号，获取省/市/区的名称
	 * @param districtCode 省/市/区的代号
	 * @return 匹配的省/市/区的名称，如果没有匹配的数据，则返回null
	 */
	private String getDistrictName(String districtCode) {
		District district = districtService.getByCode(districtCode);
		if (district != null) {
			return district.getName();
		}
		return null;
	}
	
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 */
	private void insert(AddressParam address) {
		Integer rows = addressMapper.insert(address);
		if (rows != 1) {
			throw new InsertException("增加收货地址数据时出现未知错误，请联系系统管理员");
		}
	}
	
	/**
	 * 根据收货地址id删除数据
	 * @param aid 收货地址id
	 */
	private void deleteByAid(String aid) {
		Integer rows = addressMapper.deleteByAid(aid);
		if (rows != 1) {
			throw new DeleteException("删除收货地址数据时出现未知错误，请联系系统管理员");
		}
	}

	/**
	 * 将指定的收货地址设置为默认
	 * @param aid 收货地址的id
	 * @param username 当前登录的用户名
	 * @param date 执行操作时的时间
	 */
	private void updateDefault(String aid, String username, Date date) {
		// 执行修改
		Integer rows = addressMapper.updateDefault(aid);
		// 判断返回值是否不为1
		if (rows != 1) {
			// 是：抛出UpdateException
			throw new UpdateException("更新数据时出现未知错误，请联系系统管理员！[2]");
		}
	}
	
	/**
	 * 将某用户的所有收货地址全部设置为非默认
	 * @param uid 用户的id
	 */
	private void updateNonDefault(String uid) {
		Integer rows = addressMapper.updateNonDefault(uid);
		if (rows < 1) {
			throw new UpdateException("更新数据时出现未知错误，请联系系统管理员！[2]");
		}
	}

	/**
	 * 统计某用户的收货地址数据的数量
	 * @param userId 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	private Integer countByUid(String userId) {
		return addressMapper.countByUid(userId);
	}
	
	/**
	 * 查询某用户的收货地址列表
	 * @param uid 用户的id
	 * @return 该用户的收货地址列表
	 */
	private List<AddressEntity> findByUid(String uid) {
		return addressMapper.findByUid(uid);
	}
	
	/**
	 * 根据收货地址id，查询收货地址详情
	 * @param aid 收货地址id
	 * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
	 */
	private AddressEntity findByAid(String aid) {
		if (aid == null) {
			return null;
		}
		AddressEntity result = addressMapper.findByAid(aid);
		return result;
	}
	
	/**
	 * 查询最近修改的收货地址详情
	 * @param uid 收货地址归属的用户的id
	 * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
	 */
//	private Address findLastModified(Integer uid) {
//		return addressMapper.findLastModified(uid);
//	}
	
}





