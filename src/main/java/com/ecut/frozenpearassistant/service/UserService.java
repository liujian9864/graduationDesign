package com.ecut.frozenpearassistant.service;

import com.ecut.frozenpearassistant.orm.entity.UserEntity;
import com.ecut.frozenpearassistant.orm.mapper.UserMapper;
import com.ecut.frozenpearassistant.param.UserParam;
import com.ecut.frozenpearassistant.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserEntity login(UserParam userParam) {
        // 根据参数username查询用户数据
        UserEntity entity = userMapper.findByUsername(userParam.getUserName());
        // 判断查询结果是否为null
        if (entity == null) {
            // 是：用户名不存在，抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }
        // 判断查询结果中的密码与Password是否不匹配
        if (!entity.getUserPw().equals(userParam.getUserPw())) {
            // 是：密码错误，抛出PasswordNotMatchException
            throw new PasswordNotMatchException("密码错误");
        }

        // 将查询结果返回
        return entity;
    }

    public void reg(UserParam user) {
        // 根据参数user中的username查询用户数据
        String username = user.getUserName();
        UserEntity entity = userMapper.findByUsername(username);
        // 判断查询结果是否不为null
        if (entity != null) {
            // 用户名已经被占用，不允许注册，抛出UsernameDuplicateException
            throw new UsernameDuplicateException(
                    "您尝试注册的用户名(" + username + ")已经被占用");
        }
        user.setUserId(UUID.randomUUID().toString());
        // 执行注册，获取返回的受影响的行数
        Integer rows = userMapper.insert(user);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 抛出InsertException
            throw new InsertException(
                    "插入数据时出现未知错误，请联系系统管理员");
        }
    }

    public UserEntity getByUserId(String userId) {
        // 根据参数uid查询用户数据
        UserEntity entity = userMapper.findByUserId(userId);
        // 判断查询结果是否为null
        if (entity == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("尝试访问的用户数据不存在[1]");
        }

        // 返回对象
        return entity;
    }

    public void changeInfo(String userId,  UserParam user) {
        // 根据参数uid查询用户数据
        UserEntity entity = userMapper.findByUserId(userId);
        // 判断查询结果是否为null
        if (entity == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("尝试访问的用户数据不存在[1]");
        }
        // 将参数uid封装到参数user的uid属性中
        user.setUserId(userId);
        // 执行更新用户数据，并获取返回值
        Integer rows = userMapper.updateInfo(user);
        // 判断返回的受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException(
                    "更新数据时出现未知错误，请联系系统管理员");
        }
    }

    public void changePassword(String userId,UserParam userParam) {

        // 根据参数uid查询用户数据
        UserEntity entity = userMapper.findByUserId(userId);
        // 判断查询结果是否为null
        if (entity == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("尝试访问的用户数据不存在[1]");
        }

        // 判断查询结果中的密码与输入是否不一致
        if (!entity.getUserPw().equals(userParam.getUserPw())) {
            // 是：抛出PasswordNotMatchException
            throw new PasswordNotMatchException("原密码错误");
        }
        userParam.setUserId(userId);
        // 执行更新密码：updatePassword(uid,newMd5Password,username,new Date())，并获取返回的受影响的行数
        Integer rows = userMapper.updatePassword(userParam);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException(
                    "更新数据时出现未知错误，请联系系统管理员");
        }
    }

    public void changeAvatar(String userId, String avatar) {
        // 根据参数uid查询用户数据
        UserEntity result = userMapper.findByUserId(userId);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("尝试访问的用户数据不存在[1]");
        }
        UserParam userParam = new UserParam();
        userParam.setUserId(userId);
        userParam.setAvatar(avatar);
        // 执行更新头像
        Integer rows = userMapper.updateAvatar(userParam);
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException(
                    "更新数据时出现未知错误，请联系系统管理员");
        }

    }

}
