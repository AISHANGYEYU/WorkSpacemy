package com.easytest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easytest.entity.enums.PageSize;
import com.easytest.entity.query.UserInfoCopy1Query;
import com.easytest.entity.po.UserInfoCopy1;
import com.easytest.entity.vo.PaginationResultVO;
import com.easytest.entity.query.SimplePage;
import com.easytest.mappers.UserInfoCopy1Mapper;
import com.easytest.service.UserInfoCopy1Service;
import com.easytest.utils.StringTools;


/**
 * 用户信息 业务接口实现
 */
@Service("userInfoCopy1Service")
public class UserInfoCopy1ServiceImpl implements UserInfoCopy1Service {

	@Resource
	private UserInfoCopy1Mapper<UserInfoCopy1, UserInfoCopy1Query> userInfoCopy1Mapper;

	/**
	 * 根据条件查询列表
	 */
	@Override
	public List<UserInfoCopy1> findListByParam(UserInfoCopy1Query param) {
		return this.userInfoCopy1Mapper.selectList(param);
	}

	/**
	 * 根据条件查询列表
	 */
	@Override
	public Integer findCountByParam(UserInfoCopy1Query param) {
		return this.userInfoCopy1Mapper.selectCount(param);
	}

	/**
	 * 分页查询方法
	 */
	@Override
	public PaginationResultVO<UserInfoCopy1> findListByPage(UserInfoCopy1Query param) {
		int count = this.findCountByParam(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();

		SimplePage page = new SimplePage(param.getPageNo(), count, pageSize);
		param.setSimplePage(page);
		List<UserInfoCopy1> list = this.findListByParam(param);
		PaginationResultVO<UserInfoCopy1> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
	 * 新增
	 */
	@Override
	public Integer add(UserInfoCopy1 bean) {
		return this.userInfoCopy1Mapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	@Override
	public Integer addBatch(List<UserInfoCopy1> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoCopy1Mapper.insertBatch(listBean);
	}

	/**
	 * 批量新增或者修改
	 */
	@Override
	public Integer addOrUpdateBatch(List<UserInfoCopy1> listBean) {
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.userInfoCopy1Mapper.insertOrUpdateBatch(listBean);
	}

	/**
	 * 多条件更新
	 */
	@Override
	public Integer updateByParam(UserInfoCopy1 bean, UserInfoCopy1Query param) {
		StringTools.checkParam(param);
		return this.userInfoCopy1Mapper.updateByParam(bean, param);
	}

	/**
	 * 多条件删除
	 */
	@Override
	public Integer deleteByParam(UserInfoCopy1Query param) {
		StringTools.checkParam(param);
		return this.userInfoCopy1Mapper.deleteByParam(param);
	}

	/**
	 * 根据UserId获取对象
	 */
	@Override
	public UserInfoCopy1 getUserInfoCopy1ByUserId(Integer userId) {
		return this.userInfoCopy1Mapper.selectByUserId(userId);
	}

	/**
	 * 根据UserId修改
	 */
	@Override
	public Integer updateUserInfoCopy1ByUserId(UserInfoCopy1 bean, Integer userId) {
		return this.userInfoCopy1Mapper.updateByUserId(bean, userId);
	}

	/**
	 * 根据UserId删除
	 */
	@Override
	public Integer deleteUserInfoCopy1ByUserId(Integer userId) {
		return this.userInfoCopy1Mapper.deleteByUserId(userId);
	}
}