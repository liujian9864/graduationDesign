package com.ecut.frozenpearassistant.orm.mapper;

import com.ecut.frozenpearassistant.orm.entity.UserEntity;
import com.ecut.frozenpearassistant.param.UserParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 处理用户数据的持久层接口
 */
@Mapper
@Repository
public interface UserMapper {

	/**
	 * 插入用户数据
	 * @param userParam 用户数据
	 * @return 受影响的行数
	 */
	Integer insert(UserParam userParam);
	
	/**
	 * 更新个人资料
	 * @param userParam 封装了需要更新的数据及用户id的对象
	 * @return 受影响的行数
	 */
	Integer updateInfo(UserParam userParam);
	
	/**
	 * 更新头像
	 * @param userParam
	 * @return 受影响的行数
	 */
	Integer updateAvatar(UserParam userParam);

	/**
	 * 更新密码
	 * @param userParam
	 * @return 受影响的行数
	 */
	Integer updatePassword(UserParam userParam);

	/**
	 * 根据用户名查询用户数据详情
	 * @param userName 用户名
	 * @return 匹配的用户数据详情，如果没有匹配的数据，则返回null
	 */
	UserEntity findByUsername(String userName);

	/**
	 * 根据用户id查询用户数据详情
	 * @param userId 用户id
	 * @return 匹配的用户数据详情，如果没有匹配的数据，则返回null
	 */
	UserEntity findByUserId(String userId);
	
}










