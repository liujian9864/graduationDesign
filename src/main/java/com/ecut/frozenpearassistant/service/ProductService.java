package com.ecut.frozenpearassistant.service;

import com.ecut.frozenpearassistant.orm.entity.PageProduct;
import com.ecut.frozenpearassistant.orm.entity.ProductEntity;
import com.ecut.frozenpearassistant.orm.entity.UserEntity;
import com.ecut.frozenpearassistant.orm.mapper.ProductMapper;
import com.ecut.frozenpearassistant.param.Page;
import com.ecut.frozenpearassistant.param.ProductParam;
import com.ecut.frozenpearassistant.service.ex.*;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService{

    @Autowired
    private ProductMapper productMapper;

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

    public void addNew(ProductParam productParam){

        Integer rows = productMapper.insert(productParam);
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
}
