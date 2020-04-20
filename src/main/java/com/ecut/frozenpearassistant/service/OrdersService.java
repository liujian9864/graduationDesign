package com.ecut.frozenpearassistant.service;

import com.ecut.frozenpearassistant.orm.entity.AddressEntity;
import com.ecut.frozenpearassistant.orm.entity.OrdersEntity;
import com.ecut.frozenpearassistant.orm.entity.ProductEntity;
import com.ecut.frozenpearassistant.orm.mapper.AddressMapper;
import com.ecut.frozenpearassistant.orm.mapper.OrdersMapper;
import com.ecut.frozenpearassistant.orm.mapper.ProductMapper;
import com.ecut.frozenpearassistant.param.OrdersParam;
import com.ecut.frozenpearassistant.param.ProductParam;
import com.ecut.frozenpearassistant.service.ex.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<OrdersEntity> ordersEntities=this.ordersMapper.queryAll(ordersParam);
        List<OrdersEntity> ordersEntityList=new ArrayList<>();
        if(ordersEntities !=null){
            for(OrdersEntity ordersEntity:ordersEntities){
                ProductEntity productEntity=productMapper.findByProductId(ordersEntity.getProductId());
                ordersEntity.setTitle(productEntity.getTitle());
                ordersEntity.setImage(productEntity.getImage());
                ordersEntityList.add(ordersEntity);
            }
        }
        return ordersEntityList;
    }


    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 实例对象
     */
    @Transactional
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
        orders.setStatus("1");
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createDate = sdf.format(date);
        orders.setOrdertime(createDate);
        BeanUtils.copyProperties(orders,ordersEntity);

        Integer rows=ordersMapper.insert(orders);
        ProductParam productParam = new ProductParam();
        productParam.setProductId(orders.getProductId());
        //商品设置为暂时下架
        productParam.setStatus("1");
        productMapper.updateStatusById(productParam);
        if (rows!=1){
            throw  new InsertException("订单生成失败");
        }
        return ordersEntity;
    }
    @Transactional
    public OrdersEntity exit(OrdersParam ordersParam) {
        OrdersEntity ordersEntity=ordersMapper.queryById(ordersParam.getOrderId());
        if (ordersEntity==null){
            throw new OrderNotFoundException("订单查询失败，订单不存在");
        }
        //订单设置为注销
        ordersParam.setStatus("0");

        Integer rows=ordersMapper.updateStatus(ordersParam);
        ProductParam productParam = new ProductParam();
        productParam.setProductId(ordersParam.getProductId());
        //商品设置为上架
        productParam.setStatus("1");
        productMapper.updateStatusById(productParam);
        if (rows!=1){
            throw  new InsertException("订单取消失败");
        }
        return ordersEntity;
    }

    @Transactional
    public OrdersEntity payment(OrdersParam orders) {
        OrdersEntity ordersEntity=ordersMapper.queryById(orders.getOrderId());
        if (ordersEntity==null){
            throw new OrderNotFoundException("订单查询失败，订单不存在");
        }
        ProductParam productParam = new ProductParam();
        productParam.setProductId(ordersEntity.getProductId());
        //商品设置为代发货
        productParam.setStatus("2");
        productMapper.updateStatusById(productParam);
        //订单设置为已付款
        orders.setStatus("2");
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String payDate = sdf.format(date);
        orders.setPaytime(payDate);
        ordersEntity.setStatus(orders.getStatus());
        ordersEntity.setPaytime(orders.getPaytime());
        Integer rows=ordersMapper.updateStatus(orders);
        if (rows!=1){
            throw  new UpdateException("订单状态修改失败");
        }
        return ordersEntity;
    }

    /**
     * 修改数据状态
     *
     * @param ordersParam 实例对象
     * @return 实例对象
     */
    public void updateStatus(OrdersParam ordersParam) {
        ordersMapper.updateStatus(ordersParam);
    }

}