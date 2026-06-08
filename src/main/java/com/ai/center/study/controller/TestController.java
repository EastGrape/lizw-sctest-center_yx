package com.ai.center.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.center.study.service.SecurityPerformanceTestSv;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;





@RestController
public class TestController {



	@Autowired
	private SecurityPerformanceTestSv securityPerformanceTestSv;


	//安全数据接口调用，单条插入
	@RequestMapping(path="dev/SecurityTest/insertone",method = {RequestMethod.GET,RequestMethod.POST})
	public Object test(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.insertIndivCust(request);
	}
	//安全数据接口调用，批量插入，每100条插入一次
	@RequestMapping(path="dev/SecurityTest/insertBatch",method = {RequestMethod.GET,RequestMethod.POST})
	public Object batchInsertIndivCust(@RequestBody List<SecurityPerformanceTestRequest> requests) throws Exception{

		return securityPerformanceTestSv.batchInsertIndivCust(requests);
	}
	//不带条件返回单条单加密字段
	@RequestMapping(path="dev/SecurityTest/getCustomerColumnLimitOne",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerColumnLimitOne() throws Exception{

		return securityPerformanceTestSv.getCustomerColumnLimitOne();
	}
	//不带条件返回单条单加密字段
	@RequestMapping(path="dev/SecurityTest/getCustomerColumnLimit1k",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerColumnLimit1k() throws Exception{

		return securityPerformanceTestSv.getCustomerColumnLimit1k();
	}

	//带custname条件返回单条单加密字段
	@RequestMapping(path="dev/SecurityTest/getOneByPhoneNumber",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustName(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.getOneByCustName(request);
	}
	//带custname条件返回1K
	@RequestMapping(path="dev/SecurityTest/getCustomerLimit1kByPhoneNumber",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustName(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.getCustomerLimit1kByCustName(request);
	}
	//带多个条件返回单条单加密字段
	@RequestMapping(path="dev/SecurityTest/getOneByFourColumn",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustNameAndCertCode(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.getOneByCustNameAndCertCode(request);
	}
	//带多个条件条件返回1K
	@RequestMapping(path="dev/SecurityTest/getCustomerLimit1kByFourColumn",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustNameAndCertCode(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.getCustomerLimit1kByCustNameAndCertCode(request);
	}

	//带单个条件like
	@RequestMapping(path="dev/SecurityTest/getOneByCustAddressLike",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getOneByCustNameLike(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.getOneByCustNameLike(request);
	}
	//带多个条件like
	@RequestMapping(path="dev/SecurityTest/getCustomerLimit1kByCustAddresseLike",method = {RequestMethod.GET,RequestMethod.POST})
	public Object getCustomerLimit1kByCustNameLike(@RequestBody SecurityPerformanceTestRequest request) throws Exception{

		return securityPerformanceTestSv.getCustomerLimit1kByCustNameLike(request);
	}
}
