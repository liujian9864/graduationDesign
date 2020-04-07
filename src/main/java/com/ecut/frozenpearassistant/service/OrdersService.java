package com.ecut.frozenpearassistant.service;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.orm.entity.OrdersEntity;
import com.ecut.frozenpearassistant.orm.mapper.AddressMapper;
import com.ecut.frozenpearassistant.orm.mapper.OrdersMapper;
import com.ecut.frozenpearassistant.orm.mapper.ProductMapper;
import com.ecut.frozenpearassistant.param.OrdersParam;
import com.ecut.frozenpearassistant.param.ProductParam;
import com.ecut.frozenpearassistant.service.ex.AddressNotFoundException;
import com.ecut.frozenpearassistant.service.ex.InsertException;
import com.ecut.frozenpearassistant.service.ex.OrderNotFoundException;
import com.ecut.frozenpearassistant.service.ex.UpdateException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Orders)表服务实现类
 *
 * @author makejava
 * @since 2020-04-07 22:01:33
 */
@Service("ordersService")
public class OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 通过ID查询数据
     *
     * @param ordersParam
     * @return 实例对象
     */
    public List<OrdersEntity> queryAll(OrdersParam ordersParam) {
        return this.ordersMapper.queryAll(ordersParam);
    }


    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    public OrdersEntity insert(OrdersParam orders) {
        AddressEntity addressEntity=addressMapper.findByAid(orders.getAddressId());
        if (addressEntity==null){
            throw new AddressNotFoundException("地址查询失败，地址不存在");
        }
        OrdersEntity ordersEntity=new OrdersEntity();
        orders.setOrderId(UUID.randomUUID().toString().replace("-","0").replace("a","1")
                .replace("b","5").replace("c","3").replace("d","2")
                .replace("e","7").replace("f","9").substring(0,18));
        orders.setRecName(addressEntity.getName());
        orders.setRecPhone(addressEntity.getPhone());
        orders.setRecProvice(addressEntity.getProvinceName());
        orders.setRecCity(addressEntity.getCityName());
        orders.setRecArea(addressEntity.getAreaName());
        orders.setRecAddress(addressEntity.getAddress());
        orders.setStatus("0");
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        orders.setOrdertime(createdate);
        BeanUtils.copyProperties(orders,ordersEntity);

        Integer rows=ordersMapper.insert(orders);
        if (rows!=1){
            throw  new InsertException("订单生成失败");
        }
        return ordersEntity;
    }

    public OrdersEntity updateStatus(OrdersParam orders) {
        OrdersEntity ordersEntity=ordersMapper.queryById(orders.getOrderId());
        if (ordersEntity==null){
            throw new OrderNotFoundException("订单查询失败，订单不存在");
        }
        ProductParam productParam = new ProductParam();
        productParam.setProductId(ordersEntity.getProductId());
        //商品设置为代发货
        productParam.setStatus("2");
        productMapper.updateStatusById(productParam);
        //订单设置为待收货
        orders.setStatus("1");
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);
        orders.setPaytime(createdate);
        ordersEntity.setStatus(orders.getStatus());
        ordersEntity.setPaytime(orders.getPaytime());
        Integer rows=ordersMapper.updateStatus(orders);
        if (rows!=1){
            throw  new UpdateException("订单状态修改失败");
        }
        return ordersEntity;
    }

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
//    public Orders update(Orders orders) {
//        this.ordersDao.update(orders);
//        return this.queryById(orders.getOrderId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    public boolean deleteById(String orderId) {
        return this.ordersMapper.deleteById(orderId) > 0;
    }
}