package com.ecut.frozenpearassistant.controller.api;

import com.ecut.frozenpearassistant.controller.api.ex.*;
import com.ecut.frozenpearassistant.orm.entity.UserEntity;
import com.ecut.frozenpearassistant.param.UserParam;
import com.ecut.frozenpearassistant.service.UserService;
import com.ecut.frozenpearassistant.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController extends BaseController{
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public JsonResult<UserEntity> login(@RequestBody UserParam userParam, HttpSession session){
        // 调用业务层对象执行登录
        UserEntity data = userService.login(userParam);
        // 将uid和username存入到session中
        session.setAttribute("userId", data.getUserId());
        session.setAttribute("userName", data.getUserName());
        // 响应结果
        return new JsonResult<>(SUCCESS,data);
    }

    @PostMapping("/reg")
    public JsonResult<Void> reg(@RequestBody UserParam userParam){
        userService.reg(userParam);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("/getUserById")
    public JsonResult<UserEntity> getByUid(HttpSession session) {
        // 从session中获取uid
        String userId = (String)session.getAttribute("userId");
        // 调用业务层对象获取数据
        UserEntity data = userService.getByUserId(userId);
        // 返回成功及获取到的数据
        return new JsonResult<>(SUCCESS,data);
    }

    @PostMapping("/change_info")
    public JsonResult<Void> changeInfo(@RequestBody UserParam user, HttpSession session) {
        // 从session中获取uid和username
        String userId = (String)session.getAttribute("userId");
        String userName = (String)session.getAttribute("userName");
        // 调用业务层对象执行修改用户资料
        userService.changeInfo(userId, user);
        // 返回
        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("/change_password")
    public JsonResult<Void> changePassword(@RequestBody UserParam user, HttpSession session) {
        // 从session中获取uid和username
        String userId = (String)session.getAttribute("userId");
        String userName = (String)session.getAttribute("userName");
        // 调用业务层对象执行修改密码
        userService.changePassword(userId, user);
        // 返回成功
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

    @PostMapping("/change_avatar")
    public JsonResult<String> changeAvatar(
            @RequestParam("file") MultipartFile file,
            HttpSession session) {
        // 判断用户上传是否上传了头像文件，或头像文件是否有效
        if (file.isEmpty()) {
            throw new FileEmptyException("请选择需要上传的头像文件，并且，不可以使用空文件");
        }

        // 判断头像文件的大小是否超标
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException(
                    "不可以使用超过" + AVATAR_MAX_SIZE / 1024 + "KB的头像文件");
        }

        // 判断上传的文件类型是否超标
        if (!AVATAR_CONTENT_TYPES.contains(file.getContentType()) ) {
            throw new FileTypeException(
                    "不支持上传" + file.getContentType() + "类型的文件作为头像！允许上传的类型有：" + AVATAR_CONTENT_TYPES);
        }

        // 保存头像文件的文件夹的名称
        String dir = FILEPATH+"avatar";
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
                    "读写数据时出现错误",e);
        }

        // 将头像更新到数据库中
        String userId = (String)session.getAttribute("userId");
        String userName = (String)session.getAttribute("userName");
        String avatar = "imgs/avatar/" + filename;
        userService.changeAvatar(userId, avatar);

        // 返回
        return new JsonResult<>(SUCCESS, avatar);
    }

}
