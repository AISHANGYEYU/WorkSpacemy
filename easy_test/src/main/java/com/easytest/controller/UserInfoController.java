package com.easytest.controller;

import java.lang.reflect.Field;
import java.util.List;

import com.easytest.annotation.GlobalInterceptor;
import com.easytest.annotation.VerifyParam;
import com.easytest.entity.po.Address;
import com.easytest.entity.query.UserInfoQuery;
import com.easytest.entity.po.UserInfo;
import com.easytest.entity.vo.ResponseVO;
import com.easytest.service.UserInfoService;
import com.easytest.service.impl.UserInfoServiceImpl;
import com.easytest.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息 Controller
 */
@RestController("userInfoController")
@RequestMapping("/userInfo")
public class UserInfoController extends ABaseController{

	@Resource
	private UserInfoService userInfoService;
	/**
	 * 根据条件分页查询
	 */

	@RequestMapping("/loadDataList")
	public ResponseVO loadDataList(UserInfoQuery query){
		return getSuccessResponseVO(userInfoService.findListByPage(query));
	}
	/**
	 * 新增
	 */
	@GlobalInterceptor(checkParams = true)
	@RequestMapping("/add")
	public ResponseVO add(@VerifyParam(required = true) UserInfo bean) {
		userInfoService.add(bean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增
	 */
	@RequestMapping("/addBatch")
	public ResponseVO addBatch(@RequestBody List<UserInfo> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 批量新增/修改
	 */
	@RequestMapping("/addOrUpdateBatch")
	public ResponseVO addOrUpdateBatch(@RequestBody List<UserInfo> listBean) {
		userInfoService.addBatch(listBean);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId查询对象
	 */

	@RequestMapping("/getUserInfoByUserId")
	public ResponseVO getUserInfoByUserId(@VerifyParam(required = true) Integer userId) {
		return getSuccessResponseVO(userInfoService.getUserInfoByUserId(userId));
	}

	/**
	 * 根据UserId修改对象
	 */
	@RequestMapping("/updateUserInfoByUserId")
	public ResponseVO updateUserInfoByUserId(UserInfo bean,Integer userId) {
		userInfoService.updateUserInfoByUserId(bean,userId);
		return getSuccessResponseVO(null);
	}

	/**
	 * 根据UserId删除
	 */
	@RequestMapping("/deleteUserInfoByUserId")
	public ResponseVO deleteUserInfoByUserId(Integer userId) {
		userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}
	public void sleep(String gg){
		System.out.println("睡觉"+gg);
	}
	public void sleep(String gg,Integer i){
		System.out.println("睡觉");
	}
	public static void main(String[] args) throws ClassNotFoundException {

		Class aClass = Class.forName("com.easytest.entity.po.UserInfo");
		Field[] declaredFields = aClass.getDeclaredFields();
		for (Field declaredField : declaredFields) {
			System.out.println(declaredField);
		}
	}

	@RequestMapping("/test1")
	public ResponseVO testCookie( HttpServletResponse response) {
		Cookie cookie = new Cookie("test","hhhh");
		response.addCookie(cookie);
//		userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}
	@RequestMapping("/test2")
	public ResponseVO testCookie2( HttpServletRequest request ) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals("test")){
				System.out.println(name);
				break;
			}


		}
//		userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}


	@RequestMapping("/test3")
	public ResponseVO test3(Address address) {
		UserInfo userInfo = new UserInfo();
		userInfo.setAddress(address);
//		userInfoService.deleteUserInfoByUserId(userId);
		return getSuccessResponseVO(null);
	}
}