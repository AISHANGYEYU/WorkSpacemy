package com.easytest.service;

import java.util.List;

import com.easytest.entity.query.UserInfoCopy1Query;
import com.easytest.entity.po.UserInfoCopy1;
import com.easytest.entity.vo.PaginationResultVO;


/**
 * 用户信息 业务接口
 */
public interface UserInfoCopy1Service {

	/**
	 * 根据条件查询列表
	 */
	List<UserInfoCopy1> findListByParam(UserInfoCopy1Query param);

	/**
	 * 根据条件查询列表
	 */
	Integer findCountByParam(UserInfoCopy1Query param);

	/**
	 * 分页查询
	 */
	PaginationResultVO<UserInfoCopy1> findListByPage(UserInfoCopy1Query param);

	/**
	 * 新增
	 */
	Integer add(UserInfoCopy1 bean);

	/**
	 * 批量新增
	 */
	Integer addBatch(List<UserInfoCopy1> listBean);

	/**
	 * 批量新增/修改
	 */
	Integer addOrUpdateBatch(List<UserInfoCopy1> listBean);

	/**
	 * 多条件更新
	 */
	Integer updateByParam(UserInfoCopy1 bean,UserInfoCopy1Query param);

	/**
	 * 多条件删除
	 */
	Integer deleteByParam(UserInfoCopy1Query param);

	/**
	 * 根据UserId查询对象
	 */
	UserInfoCopy1 getUserInfoCopy1ByUserId(Integer userId);


	/**
	 * 根据UserId修改
	 */
	Integer updateUserInfoCopy1ByUserId(UserInfoCopy1 bean,Integer userId);


	/**
	 * 根据UserId删除
	 */
	Integer deleteUserInfoCopy1ByUserId(Integer userId);

}