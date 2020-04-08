package com.ecut.frozenpearassistant.service;

import com.ecut.frozenpearassistant.orm.entity.*;
import com.ecut.frozenpearassistant.orm.mapper.MessageMapper;
import com.ecut.frozenpearassistant.orm.mapper.ProductMapper;
import com.ecut.frozenpearassistant.orm.mapper.UserMapper;
import com.ecut.frozenpearassistant.param.MessageParam;
import com.ecut.frozenpearassistant.param.Page;
import com.ecut.frozenpearassistant.param.ProductParam;
import com.ecut.frozenpearassistant.service.ex.*;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService{

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    public PageProduct queryByPage(ProductParam productParam){
        Page pages=new Page();
        pages.setCurrentPage(productParam.getPage());
        pages.setPageSize(productParam.getRows());
        pages.setSearchKey(productParam.getSearchKey());
        pages.setProductType(productParam.getProductType());
        pages.setTotalCount(productMapper.queryAllCount(pages));
        List<ProductEntity> productEntityList=productMapper.queryByPage(pages);
        PageProduct pageProduct=new PageProduct();
        pageProduct.setProducts(productEntityList);
        pageProduct.setCurrentPage(pages.getCurrentPage());
        pageProduct.setTotalPage(pages.getTotalPage());
        return pageProduct;
    }

    public PageMessage showMessage(MessageParam messageParam){
        Page pages=new Page();
        pages.setCurrentPage(messageParam.getPage());
        pages.setPageSize(messageParam.getRows());
        pages.setSearchKey(messageParam.getProductId());
        pages.setTotalCount(messageMapper.queryAllCount(pages));
        List<MessageEntity> messageEntityList=messageMapper.queryByPage(pages);
        PageMessage pageMessage=new PageMessage();
        pageMessage.setMessages(messageEntityList);
        pageMessage.setCurrentPage(pages.getCurrentPage());
        pageMessage.setTotalPage(pages.getTotalPage());
        return pageMessage;
    }

    public ProductEntity findByProductId(String productId){
        ProductEntity productEntity=productMapper.findByProductId(productId);
        if(productEntity.getUserId()!=null && !"".equals(productEntity.getUserId())){
            String phone=userMapper.findByUserId(productEntity.getUserId()).getPhone();
            if(phone!=null && !"".equals(phone)){
                productEntity.setPhone(phone);
            }
        }
        return productEntity;
    }
    public List<ProductEntity> findByUserId(String userId){
        List<ProductEntity> productEntities=productMapper.findByUserId(userId);
        if(productEntities == null ){
            throw new UpdateException("根据用户查询发布商品错误");
        }
        return productEntities;
    }

    public void addNew(ProductParam productParam){

        Integer rows = productMapper.insert(productParam);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 抛出InsertException
            throw new InsertException(
                    "插入数据时出现未知错误，请联系系统管理员");
        }
    }
    public void addMessage(MessageParam messageParam){
        messageParam.setMessageId(UUID.randomUUID().toString());
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取String类型的时间
        String createdate = sdf.format(date);

        messageParam.setDate(createdate);

        Integer rows = messageMapper.insert(messageParam);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 抛出InsertException
            throw new InsertException(
                    "插入数据时出现未知错误，请联系系统管理员");
        }
    }

    public void addNewPic(String productId,String image){
        ProductEntity productEntity=productMapper.findByProductId(productId);
        if (productEntity==null){
            // 是：抛出UserNotFoundException
            throw new AddressNotFoundException("尝试访问的用户数据不存在[1]");
        }
        ProductParam productParam=new ProductParam();
        productParam.setProductId(productId);
        productParam.setImage(image);
        Integer rows=productMapper.updateImage(productParam);
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException(
                    "更新数据时出现未知错误，请联系系统管理员");
        }
    }

    public void updateStatus(ProductParam productParam){
        Integer rows=productMapper.updateStatusById(productParam);
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException(
                    "更新数据时出现未知错误，请联系系统管理员");
        }
    }
}
