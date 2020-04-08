package com.ecut.frozenpearassistant.controller.api;

import com.ecut.frozenpearassistant.orm.entity.OrdersEntity;
import com.ecut.frozenpearassistant.param.OrdersParam;
import com.ecut.frozenpearassistant.service.OrdersService;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.ecut.frozenpearassistant.controller.api.BaseController.SUCCESS;

/**
 * (Orders)表控制层
 *
 * @author makejava
 * @since 2020-04-07 22:01:35
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Autowired
    private OrdersService ordersService;

    /**
     * 查询数据
     *
     * @return 单条数据
     */
    @GetMapping("selectAll")
    public JsonResult<List<OrdersEntity>> selectAll( HttpSession session) {
        // 从session中获取uid
        String userId = (String)session.getAttribute("userId");
        OrdersParam ordersParam=new OrdersParam();
        ordersParam.setUserId(userId);
        List<OrdersEntity> data=ordersService.queryAll(ordersParam);
        return new JsonResult<>(SUCCESS,data);
    }
    /**
     * 生成订单
     *
     * @return 单条数据
     */
    @PostMapping("create")
    public JsonResult<OrdersEntity> insert(@RequestBody OrdersParam ordersParam, HttpSession session) {
        // 从session中获取uid
        String userId = (String)session.getAttribute("userId");
        ordersParam.setUserId(userId);
        OrdersEntity data=ordersService.insert(ordersParam);
        return new JsonResult<>(SUCCESS,data);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @PostMapping("update")
    public JsonResult<OrdersEntity> update(@RequestBody OrdersParam ordersParam, HttpSession session) {
        OrdersEntity data=ordersService.payment(ordersParam);
        return new JsonResult<>(SUCCESS,data);
    }

    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @PostMapping("exitOrder")
    public JsonResult<OrdersEntity> exit(@RequestBody OrdersParam ordersParam, HttpSession session) {
        OrdersEntity data=ordersService.exit(ordersParam);
        return new JsonResult<>(SUCCESS,data);
    }

}