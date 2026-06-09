package com.ai.center.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.center.study.service.SecurityPerformanceSDKTestSv;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;



/**
 * SDK加密测试接口控制器
 * <p>
 * 对外保留当前压测URL集合，具体敏感字段加解密规则由SecurityPerformanceSDKTestSv负责。
 *
 * @author xiangqi
 * @date 2026-06-08 17:40
 */
@RestController
public class SdkTestController {



	// SDK加密性能测试服务
	@Autowired
	private SecurityPerformanceSDKTestSv securityPerformanceSDKTestSv;

	//单字段单条加密写入性能
	@RequestMapping(path="dev/SecuritySDKTest/insertIndivCustOneColumn",method = {RequestMethod.GET,RequestMethod.POST})
	public Object insertIndivCustOneColumn(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.insertIndivCustOneColumn(request);
	}

	//安全数据接口调用，单条四字段加密插入
	@RequestMapping(path="dev/SecuritySDKTest/insertone",method = {RequestMethod.GET,RequestMethod.POST})
	public Object insertone(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.insertIndivCust(request);
	}
	//安全数据接口调用，批量插入，每100条插入一次
	@RequestMapping(path="dev/SecuritySDKTest/insertBatch",method = {RequestMethod.GET,RequestMethod.POST})
	public Object batchInsertIndivCust(@RequestBody List<SecurityPerformanceTestRequest> requests) throws Exception{

		return securityPerformanceSDKTestSv.batchInsertIndivCust(requests);
	}
	//不带条件返回单条，解密单字段cust_address
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerColumnLimitOne",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerColumnLimitOne() throws Exception{

		return securityPerformanceSDKTestSv.getCustomerColumnLimitOne();
	}
	//不带条件返回1000条，解密单字段cust_address
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerColumnLimit1k",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerColumnLimit1k() throws Exception{

		return securityPerformanceSDKTestSv.getCustomerColumnLimit1k();
	}

	//路径名保留PhoneNumber，实际按cust_address等值加密条件返回单条
	@RequestMapping(path="dev/SecuritySDKTest/getOneByPhoneNumber",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustName(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getOneByCustName(request);
	}
	//路径名保留PhoneNumber，实际按cust_address等值加密条件返回1000条
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerLimit1kByPhoneNumber",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustName(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getCustomerLimit1kByCustName(request);
	}
	//带四个加密字段等值条件返回单条
	@RequestMapping(path="dev/SecuritySDKTest/getOneByFourColumn",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustNameAndCertCode(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getOneByCustNameAndCertCode(request);
	}

	//带cust_address单字段LIKE条件返回单条
	@RequestMapping(path="dev/SecuritySDKTest/getOneByCustAddressLike",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustNameLike(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getOneByCustNameLike(request);
	}
	//带cust_address单字段LIKE条件返回1000条
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerLimit1kByCustAddresseLike",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustNameLike(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getCustomerLimit1kByCustNameLike(request);
	}
}
