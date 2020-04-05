package com.ecut.frozenpearassistant.orm.mapper;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.orm.entity.ProductEntity;
import com.ecut.frozenpearassistant.param.AddressParam;
import com.ecut.frozenpearassistant.param.Page;
import com.ecut.frozenpearassistant.param.ProductParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理收货地址数据的持久层接口
 */
@Mapper
@Repository
public interface ProductMapper {
	
	/**
	 * 插入商品数据
	 * @param productParam 商品数据
	 * @return 受影响的行数
	 */
	Integer insert(ProductParam productParam);

	/**
	 * 插入商品图片
	 * @param productParam 商品数据
	 * @return 受影响的行数
	 */

	Integer updateImage(ProductParam productParam);
	
	/**
	 * 根据收货地址id删除数据
	 * @param addressId 收货地址id
	 * @return 受影响的行数
	 */
	Integer deleteByAddressId(String addressId);
	
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
	 * 根据商品id，查询商品详情
	 * @param productId 商品id
	 * @return 商品信息
	 */
	ProductEntity findByProductId(String productId);

	/**
	 * 分页查询商品详情
	 * @param page
	 * @return 分页数据
	 */
	List<ProductEntity> queryByPage(Page page);

	Integer queryAllCount(Page page);


}






