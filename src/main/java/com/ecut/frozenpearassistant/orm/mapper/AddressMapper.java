package com.ecut.frozenpearassistant.orm.mapper;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.param.AddressParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理收货地址数据的持久层接口
 */
@Mapper
@Repository
public interface AddressMapper {
	
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(AddressParam address);
	
	/**
	 * 根据收货地址id删除数据
	 * @param addressId 收货地址id
	 * @return 受影响的行数
	 */
	Integer deleteByAid(String addressId);
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param addressId 即将设置为默认的收货地址的id
	 * @return 受影响的行数
	 */
	Integer updateDefault(String addressId);

	/**
	 * 将某用户的所有收货地址全部设置为非默认
	 * @param userId 用户的id
	 * @return 受影响的行数
	 */
	Integer updateNonDefault(String userId);

	/**
	 * 统计某用户的收货地址数据的数量
	 * @param userId 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	Integer countByUid(String userId);
	
	/**
	 * 查询某用户的收货地址列表
	 * @param userId 用户的id
	 * @return 该用户的收货地址列表
	 */
	List<AddressEntity> findByUid(String userId);
	
	/**
	 * 根据收货地址id，查询收货地址详情
	 * @param addressId 收货地址id
	 * @return 匹配的收货地址详情，如果没有匹配的数据，则返回null
	 */
	AddressEntity findByAid(String addressId);
	

}






