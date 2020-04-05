package com.ecut.frozenpearassistant.orm.mapper;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.orm.entity.MessageEntity;
import com.ecut.frozenpearassistant.orm.entity.ProductEntity;
import com.ecut.frozenpearassistant.param.MessageParam;
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
public interface MessageMapper {
	
	/**
	 * 插入商品数据
	 * @param messageParam 商品数据
	 * @return 受影响的行数
	 */
	Integer insert(MessageParam messageParam);

	/**
	 * 分页查询商品详情
	 * @param page
	 * @return 分页数据
	 */
	List<MessageEntity> queryByPage(Page page);

	Integer queryAllCount(Page page);


}






