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
	 * 设置商品住状态
	 * @param productParam 商品的id
	 * @return 受影响的行数
	 */
	Integer updateStatusById(ProductParam productParam);


	/**
	 * 根据商品id，查询商品详情
	 * @param productId 商品id
	 * @return 商品信息
	 */
	ProductEntity findByProductId(String productId);

	/**
	 * 根据用户id，查询商品详情
	 * @param userId 商品id
	 * @return 商品信息
	 */
	List<ProductEntity> findByUserId(String userId);

	/**
	 * 分页查询商品详情
	 * @param page
	 * @return 分页数据
	 */
	List<ProductEntity> queryByPage(Page page);

	Integer queryAllCount(Page page);


}






