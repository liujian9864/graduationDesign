package com.ecut.frozenpearassistant.orm.mapper;

import com.ecut.frozenpearassistant.orm.entity.OrdersEntity;
import com.ecut.frozenpearassistant.param.OrdersParam;
import com.ecut.frozenpearassistant.param.ProductParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * (Orders)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-07 22:01:26
 */
@Mapper
@Repository
public interface OrdersMapper {



    /**
     * 通过实体作为筛选条件查询
     *
     * @param orders 实例对象
     * @return 对象列表
     */
    List<OrdersEntity> queryAll(OrdersParam orders);

    /**
     * 通过id作为筛选条件查询
     *
     * @param orderId 实例对象
     * @return 对象列表
     */
    OrdersEntity queryById(String orderId);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(OrdersParam orders);

    /**
     * 修改数据
     *
     * @param ordersParam 实例对象
     * @return 影响行数
     */
    Integer updateStatus(OrdersParam ordersParam);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(String orderId);

}