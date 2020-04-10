package com.ecut.frozenpearassistant.controller.api;

import com.ecut.frozenpearassistant.controller.api.ex.*;
import com.ecut.frozenpearassistant.orm.entity.PageMessage;
import com.ecut.frozenpearassistant.orm.entity.PageProduct;
import com.ecut.frozenpearassistant.orm.entity.ProductEntity;
import com.ecut.frozenpearassistant.param.MessageParam;
import com.ecut.frozenpearassistant.param.ProductParam;
import com.ecut.frozenpearassistant.service.ProductService;
import com.ecut.frozenpearassistant.service.ex.ParamNotExistException;
import com.ecut.frozenpearassistant.service.ex.UserNotFoundException;
import com.ecut.frozenpearassistant.util.JsonResult;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findByPage")
    public JsonResult<PageProduct> List(ProductParam productParam){
        PageProduct data=productService.queryByPage(productParam);
        return new JsonResult<>(SUCCESS,data);
    }

    @GetMapping("/showMessage")
    public JsonResult<PageMessage> showMessage(MessageParam messageParam, HttpSession session){
        String productId=(String)session.getAttribute("productId");
        messageParam.setProductId(productId);
        PageMessage data=productService.showMessage(messageParam);
        return new JsonResult<>(SUCCESS,data);
    }

    @GetMapping("/findByProductId")
    public JsonResult<ProductEntity> findByProductId(HttpSession session){
        String productId = (String)session.getAttribute("productId");
        ProductEntity data=productService.findByProductId(productId);
        return new JsonResult<>(SUCCESS,data);
    }

    @GetMapping("/findByUserId")
    public JsonResult<List<ProductEntity>> findByUserId(HttpSession session){
        String userId = (String)session.getAttribute("userId");
        List<ProductEntity> data=productService.findByUserId(userId);
        return new JsonResult<>(SUCCESS,data);
    }

    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@RequestBody ProductParam productParam, HttpSession session){
        productParam.setProductId(UUID.randomUUID().toString());
        productParam.setStatus("1");
        String userId=(String)session.getAttribute("userId");
        session.setAttribute("productId",productParam.getProductId());
        session.setAttribute("productType",productParam.getProductType());
        productParam.setUserId(userId);
        productService.addNew(productParam);
        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("/addMessage")
    public JsonResult<Void> addMessage(@RequestBody MessageParam messageParam, HttpSession session){
        String productId=(String)session.getAttribute("productId");
        String userId=(String)session.getAttribute("userId");
        if(userId==null || "".equals(userId)){
            throw new UserNotFoundException();
        }
        messageParam.setProductId(productId);
        messageParam.setUserId(userId);
        productService.addMessage(messageParam);
        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("/UpdateStatus")
    public JsonResult<Void> updateStatus(@RequestBody ProductParam productParam){
        if (productParam==null){
            throw new ParamNotExistException("输入的参数为空!");
        }
        productService.updateStatus(productParam);
        return new JsonResult<>(SUCCESS);
    }

    /**
     * 上传的头像的最大大小
     */
    public static final int AVATAR_MAX_SIZE = 700 * 1024;
    /**
     * 允许上传的头像文件的类型
     */
    public static final List<String> AVATAR_CONTENT_TYPES = new ArrayList<>();

    static {
        AVATAR_CONTENT_TYPES.add("image/jpeg");
        AVATAR_CONTENT_TYPES.add("image/png");
    }

    @PostMapping("/addnewPic")
    public JsonResult<String> addnewPic(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        // 判断用户上传是否上传了头像文件，或头像文件是否有效
        String productId = (String)session.getAttribute("productId");;
        String type = (String)session.getAttribute("productType");
        if (file.isEmpty()) {
            throw new FileEmptyException("请选择需要上传的图片文件，并且，不可以使用空文件");
        }

        // 判断头像文件的大小是否超标
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException(
                    "不可以使用超过" + AVATAR_MAX_SIZE / 1024 + "KB的图片文件");
        }

        // 判断上传的文件类型是否超标
        if (!AVATAR_CONTENT_TYPES.contains(file.getContentType()) ) {
            throw new FileTypeException(
                    "不支持上传" + file.getContentType() + "类型的图片！允许上传的类型有：" + AVATAR_CONTENT_TYPES);
        }

        // 保存头像文件的文件夹的名称
        String dir = FILEPATH+type;
        // 确定保存头像文件的文件夹的路径
//        String pathname = session.getServletContext().getRealPath(dir);
        String pathname = dir;
        // 保存头像文件的文件夹
        File parent = new File(pathname);
        // 确保文件夹是存在的
        if (!parent.exists()) {
            parent.mkdirs();
        }

        // 获取上传的头像文件的原始文件名
        String originalFilename = file.getOriginalFilename();
        // 处理扩展名
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        // 文件名
        String filename = System.currentTimeMillis() + "" + System.nanoTime() % 100000 + suffix;

        // 保存头像的目标文件，即：将用户上传的头像保存为哪个文件
        File dest = new File(parent, filename);
        // 保存用户上传的头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException(
                    "文件可能已经被移动，无法访问文件的数据");
        } catch (IOException e) {
            throw new FileIOException(
                    "读写数据时出现错误");
        }

        // 将头像更新到数据库中
        String image = "imgs/"+type+"/" + filename;
        productService.addNewPic(productId, image);

        // 返回
        return new JsonResult<>(SUCCESS);
    }


}
