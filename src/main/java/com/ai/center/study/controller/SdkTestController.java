package com.ai.center.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ai.center.study.domain.IndivCustEntity;
import com.ai.center.study.domain.JsonBean;

import com.ai.center.study.service.SecurityPerformanceSDKTestSv;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;




@RestController
public class SdkTestController {
	
	
   
	@Autowired
	private SecurityPerformanceSDKTestSv securityPerformanceSDKTestSv;
    
    
	//安全数据接口调用，单条插入
	@RequestMapping(path="dev/SecuritySDKTest/insertone",method = {RequestMethod.GET,RequestMethod.POST})
	public Object test(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.insertIndivCust(request);
	}
	//安全数据接口调用，批量插入，每100条插入一次
	@RequestMapping(path="dev/SecuritySDKTest/insertBatch",method = {RequestMethod.GET,RequestMethod.POST})
	public Object batchInsertIndivCust(@RequestBody List<SecurityPerformanceTestRequest> requests) throws Exception{

		return securityPerformanceSDKTestSv.batchInsertIndivCust(requests);
	}
	//安全数据接口调用，根据id单条获取
	@RequestMapping(path="dev/SecuritySDKTest/getone",method = {RequestMethod.GET,RequestMethod.POST})
	public Object testGetOne(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getCustomerTest(request);
	}
	//不带条件返回单条单加密字段
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerColumnLimitOne",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerColumnLimitOne() throws Exception{

		return securityPerformanceSDKTestSv.getCustomerColumnLimitOne();
	}
	//不带条件返回单条单加密字段
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerColumnLimit1k",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerColumnLimit1k() throws Exception{

		return securityPerformanceSDKTestSv.getCustomerColumnLimit1k();
	}
	
	//带custname条件返回单条单加密字段
	@RequestMapping(path="dev/SecuritySDKTest/getOneByCustName",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustName(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getOneByCustName(request);
	}
	//带custname条件返回1K
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerLimit1kByCustName",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustName(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getCustomerLimit1kByCustName(request);
	}
	//带多个条件返回单条单加密字段
	@RequestMapping(path="dev/SecuritySDKTest/getOneByCustNameAndCertCode",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustNameAndCertCode(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getOneByCustNameAndCertCode(request);
	}
	//带多个条件条件返回1K
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerLimit1kByCustNameAndCertCode",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustNameAndCertCode(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getCustomerLimit1kByCustNameAndCertCode(request);
	}
	
	//带单个条件like
	@RequestMapping(path="dev/SecuritySDKTest/getOneByCustNameLike",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustNameLike(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getOneByCustNameLike(request);
	}
	//带多个条件like
	@RequestMapping(path="dev/SecuritySDKTest/getCustomerLimit1kByCustNameLike",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustNameLike(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceSDKTestSv.getCustomerLimit1kByCustNameLike(request);
	}
}
