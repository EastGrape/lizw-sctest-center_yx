package com.ai.center.study.service;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ai.center.study.domain.IndivCustEntity;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;



@Service
@Transactional
public class SecurityPerformanceSDKTestSv {
    final static String aiga_tns = "jdbc:postgresql://10.179.95.94:6432/teu?targetServerType=master&binaryTransfer=False&forceBinary=False&reWriteBatchedInserts=true&grammar=oracle&prepareThreshold=0";
    final static String aiga_user = "teu1";
    final static String aiga_passwd = "dt_encry_test1ENC#";
    //不带条件返回单条单加密字段
	public IndivCustEntity getCustomerColumnLimitOne() throws SQLException{
		String result="";
		//【修复：改用?占位符，防SQL注入】
	    String sql="select * from cm_indiv_customer_lizw_574 a limit 1";
	    Connection conn = null;
	    PreparedStatement psCon = null;
	    ResultSet rs = null;
	    IndivCustEntity entity = null;
	    try{
	        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
	        psCon = conn.prepareStatement(sql);
	        // 给占位符赋值
	        rs = psCon.executeQuery();

	        while(rs.next()){
	        	entity = new IndivCustEntity();
	            // ========== 字段赋值：数据库字段 → Entity属性 ==========
	            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
	            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
	            entity.setCustName(rs.getString("cust_name"));//客户名称，加密改造点
	            entity.setCustStatus(rs.getInt("cust_status"));
	            entity.setCustZipcode(rs.getString("cust_zipcode"));
	            entity.setCustAddress(rs.getString("cust_address"));//客户地址，加密改造点
	            entity.setCustLevel(rs.getInt("cust_level"));
	            entity.setCustCertType(rs.getInt("cust_cert_type"));
	            entity.setCustCertCode(rs.getString("cust_cert_code"));//身份证件，加密改造点
	            entity.setCustCertAddress(rs.getString("cust_cert_address"));//身份证件地址，加密改造点
	            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
	            entity.setGender(rs.getInt("gender"));
	            entity.setLunar(rs.getInt("lunar"));
	            entity.setBirthday(rs.getDate("birthday"));
	            entity.setOccupation(rs.getInt("occupation"));
	            entity.setRealNameFlag(rs.getInt("real_name_flag"));
	            entity.setContractNo(rs.getString("contract_no"));
	            entity.setCharacterDesc(rs.getString("character_desc"));
	            entity.setMarryStatus(rs.getInt("marry_status"));
	            entity.setVipNo(rs.getString("vip_no"));
	            entity.setAllowAgent(rs.getInt("allow_agent"));
	            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
	            entity.setEntryChnl(rs.getInt("entry_chnl"));
	            entity.setState(rs.getString("state"));
	            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
	            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
	            entity.setOpId(rs.getBigDecimal("op_id"));
	            entity.setOrgId(rs.getBigDecimal("org_id"));
	            entity.setDoneCode(rs.getBigDecimal("done_code"));
	            entity.setCreateDate(rs.getDate("create_date"));
	            entity.setDoneDate(rs.getDate("done_date"));
	            entity.setEffectiveDate(rs.getDate("effective_date"));
	            entity.setExpireDate(rs.getDate("expire_date"));
	            entity.setRegionId(rs.getString("region_id"));
	            entity.setCountyId(rs.getString("county_id"));
	            entity.setNotes(rs.getString("notes"));
	            entity.setOldCustType(rs.getInt("old_cust_type"));
	            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
	            entity.setUniCustId(rs.getString("uni_cust_id"));
	            entity.setUniPartyId(rs.getString("uni_party_id"));
	        }
	    }finally {
	        // 关闭资源，防止连接泄露
	        if(rs != null) rs.close();
	        if(psCon != null) psCon.close();
	        if(conn != null) conn.close();
	    }
	    return entity;
	}
	   //不带条件返回单条1000加密字段
		public List<IndivCustEntity> getCustomerColumnLimit1k() throws SQLException{
			String result="";
			//【修复：改用?占位符，防SQL注入】
		    String sql="select * from cm_indiv_customer_lizw_574 a limit 1000";
		    Connection conn = null;
		    PreparedStatement psCon = null;
		    ResultSet rs = null;
		    List<IndivCustEntity> entitys = new ArrayList<>();
		    try{
		        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
		        psCon = conn.prepareStatement(sql);
		        // 给占位符赋值
		        rs = psCon.executeQuery();

		        while(rs.next()){
		        	IndivCustEntity entity = new IndivCustEntity();
		            // ========== 字段赋值：数据库字段 → Entity属性 ==========
		            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
		            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
		            entity.setCustName(rs.getString("cust_name"));
		            entity.setCustStatus(rs.getInt("cust_status"));
		            entity.setCustZipcode(rs.getString("cust_zipcode"));
		            entity.setCustAddress(rs.getString("cust_address"));
		            entity.setCustLevel(rs.getInt("cust_level"));
		            entity.setCustCertType(rs.getInt("cust_cert_type"));
		            entity.setCustCertCode(rs.getString("cust_cert_code"));
		            entity.setCustCertAddress(rs.getString("cust_cert_address"));
		            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
		            entity.setGender(rs.getInt("gender"));
		            entity.setLunar(rs.getInt("lunar"));
		            entity.setBirthday(rs.getDate("birthday"));
		            entity.setOccupation(rs.getInt("occupation"));
		            entity.setRealNameFlag(rs.getInt("real_name_flag"));
		            entity.setContractNo(rs.getString("contract_no"));
		            entity.setCharacterDesc(rs.getString("character_desc"));
		            entity.setMarryStatus(rs.getInt("marry_status"));
		            entity.setVipNo(rs.getString("vip_no"));
		            entity.setAllowAgent(rs.getInt("allow_agent"));
		            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
		            entity.setEntryChnl(rs.getInt("entry_chnl"));
		            entity.setState(rs.getString("state"));
		            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
		            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
		            entity.setOpId(rs.getBigDecimal("op_id"));
		            entity.setOrgId(rs.getBigDecimal("org_id"));
		            entity.setDoneCode(rs.getBigDecimal("done_code"));
		            entity.setCreateDate(rs.getDate("create_date"));
		            entity.setDoneDate(rs.getDate("done_date"));
		            entity.setEffectiveDate(rs.getDate("effective_date"));
		            entity.setExpireDate(rs.getDate("expire_date"));
		            entity.setRegionId(rs.getString("region_id"));
		            entity.setCountyId(rs.getString("county_id"));
		            entity.setNotes(rs.getString("notes"));
		            entity.setOldCustType(rs.getInt("old_cust_type"));
		            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
		            entity.setUniCustId(rs.getString("uni_cust_id"));
		            entity.setUniPartyId(rs.getString("uni_party_id"));
		            entitys.add(entity);
		        }
		    }finally {
		        // 关闭资源，防止连接泄露
		        if(rs != null) rs.close();
		        if(psCon != null) psCon.close();
		        if(conn != null) conn.close();
		    }
		    return entitys;
		}
    //带等于加密字段返回单条单加密字段
	public IndivCustEntity getOneByCustName(SecurityPerformanceTestRequest request) throws SQLException{
		String result="";
		//【修复：改用?占位符，防SQL注入】
	    String sql="select * from cm_indiv_customer_lizw_574 a where cust_name=? limit 1";
	    Connection conn = null;
	    PreparedStatement psCon = null;
	    ResultSet rs = null;
	    IndivCustEntity entity = null;
	    try{
	        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
	        psCon = conn.prepareStatement(sql);
	        psCon.setString(1, request.getCustName());
	        // 给占位符赋值
	        rs = psCon.executeQuery();

	        while(rs.next()){
	        	entity = new IndivCustEntity();
	            // ========== 字段赋值：数据库字段 → Entity属性 ==========
	            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
	            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
	            entity.setCustName(rs.getString("cust_name"));
	            entity.setCustStatus(rs.getInt("cust_status"));
	            entity.setCustZipcode(rs.getString("cust_zipcode"));
	            entity.setCustAddress(rs.getString("cust_address"));
	            entity.setCustLevel(rs.getInt("cust_level"));
	            entity.setCustCertType(rs.getInt("cust_cert_type"));
	            entity.setCustCertCode(rs.getString("cust_cert_code"));
	            entity.setCustCertAddress(rs.getString("cust_cert_address"));
	            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
	            entity.setGender(rs.getInt("gender"));
	            entity.setLunar(rs.getInt("lunar"));
	            entity.setBirthday(rs.getDate("birthday"));
	            entity.setOccupation(rs.getInt("occupation"));
	            entity.setRealNameFlag(rs.getInt("real_name_flag"));
	            entity.setContractNo(rs.getString("contract_no"));
	            entity.setCharacterDesc(rs.getString("character_desc"));
	            entity.setMarryStatus(rs.getInt("marry_status"));
	            entity.setVipNo(rs.getString("vip_no"));
	            entity.setAllowAgent(rs.getInt("allow_agent"));
	            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
	            entity.setEntryChnl(rs.getInt("entry_chnl"));
	            entity.setState(rs.getString("state"));
	            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
	            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
	            entity.setOpId(rs.getBigDecimal("op_id"));
	            entity.setOrgId(rs.getBigDecimal("org_id"));
	            entity.setDoneCode(rs.getBigDecimal("done_code"));
	            entity.setCreateDate(rs.getDate("create_date"));
	            entity.setDoneDate(rs.getDate("done_date"));
	            entity.setEffectiveDate(rs.getDate("effective_date"));
	            entity.setExpireDate(rs.getDate("expire_date"));
	            entity.setRegionId(rs.getString("region_id"));
	            entity.setCountyId(rs.getString("county_id"));
	            entity.setNotes(rs.getString("notes"));
	            entity.setOldCustType(rs.getInt("old_cust_type"));
	            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
	            entity.setUniCustId(rs.getString("uni_cust_id"));
	            entity.setUniPartyId(rs.getString("uni_party_id"));
	        }
	    }finally {
	        // 关闭资源，防止连接泄露
	        if(rs != null) rs.close();
	        if(psCon != null) psCon.close();
	        if(conn != null) conn.close();
	    }
	    return entity;
	}
    //带等于单加密字段返回单条1000加密字段
	public List<IndivCustEntity> getCustomerLimit1kByCustName(SecurityPerformanceTestRequest request) throws SQLException{
		String result="";
		//【修复：改用?占位符，防SQL注入】
	    String sql="select * from cm_indiv_customer_lizw_574 a where cust_name=? limit 1000";
	    Connection conn = null;
	    PreparedStatement psCon = null;
	    ResultSet rs = null;
	    List<IndivCustEntity> entitys = new ArrayList<>();
	    try{
	        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
	        psCon = conn.prepareStatement(sql);
	        // 给占位符赋值
	        psCon.setString(1, request.getCustName());
	        rs = psCon.executeQuery();

	        while(rs.next()){
	        	IndivCustEntity entity = new IndivCustEntity();
	            // ========== 字段赋值：数据库字段 → Entity属性 ==========
	            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
	            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
	            entity.setCustName(rs.getString("cust_name"));
	            entity.setCustStatus(rs.getInt("cust_status"));
	            entity.setCustZipcode(rs.getString("cust_zipcode"));
	            entity.setCustAddress(rs.getString("cust_address"));
	            entity.setCustLevel(rs.getInt("cust_level"));
	            entity.setCustCertType(rs.getInt("cust_cert_type"));
	            entity.setCustCertCode(rs.getString("cust_cert_code"));
	            entity.setCustCertAddress(rs.getString("cust_cert_address"));
	            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
	            entity.setGender(rs.getInt("gender"));
	            entity.setLunar(rs.getInt("lunar"));
	            entity.setBirthday(rs.getDate("birthday"));
	            entity.setOccupation(rs.getInt("occupation"));
	            entity.setRealNameFlag(rs.getInt("real_name_flag"));
	            entity.setContractNo(rs.getString("contract_no"));
	            entity.setCharacterDesc(rs.getString("character_desc"));
	            entity.setMarryStatus(rs.getInt("marry_status"));
	            entity.setVipNo(rs.getString("vip_no"));
	            entity.setAllowAgent(rs.getInt("allow_agent"));
	            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
	            entity.setEntryChnl(rs.getInt("entry_chnl"));
	            entity.setState(rs.getString("state"));
	            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
	            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
	            entity.setOpId(rs.getBigDecimal("op_id"));
	            entity.setOrgId(rs.getBigDecimal("org_id"));
	            entity.setDoneCode(rs.getBigDecimal("done_code"));
	            entity.setCreateDate(rs.getDate("create_date"));
	            entity.setDoneDate(rs.getDate("done_date"));
	            entity.setEffectiveDate(rs.getDate("effective_date"));
	            entity.setExpireDate(rs.getDate("expire_date"));
	            entity.setRegionId(rs.getString("region_id"));
	            entity.setCountyId(rs.getString("county_id"));
	            entity.setNotes(rs.getString("notes"));
	            entity.setOldCustType(rs.getInt("old_cust_type"));
	            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
	            entity.setUniCustId(rs.getString("uni_cust_id"));
	            entity.setUniPartyId(rs.getString("uni_party_id"));
	            entitys.add(entity);
	        }
	    }finally {
	        // 关闭资源，防止连接泄露
	        if(rs != null) rs.close();
	        if(psCon != null) psCon.close();
	        if(conn != null) conn.close();
	    }
	    return entitys;
	}

	 //带等于多个加密字段返回单条单加密字段
		public IndivCustEntity getOneByCustNameAndCertCode(SecurityPerformanceTestRequest request) throws SQLException{
			String result="";
			//【修复：改用?占位符，防SQL注入】
		    String sql="select * from cm_indiv_customer_lizw_574 a where cust_name=? and cust_cert_code=? limit 1";
		    Connection conn = null;
		    PreparedStatement psCon = null;
		    ResultSet rs = null;
		    IndivCustEntity entity = null;
		    try{
		        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
		        psCon = conn.prepareStatement(sql);
		        psCon.setString(1, request.getCustName());
		        psCon.setString(2, request.getCustCertCode());
		        // 给占位符赋值
		        rs = psCon.executeQuery();

		        while(rs.next()){
		        	entity = new IndivCustEntity();
		            // ========== 字段赋值：数据库字段 → Entity属性 ==========
		            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
		            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
		            entity.setCustName(rs.getString("cust_name"));
		            entity.setCustStatus(rs.getInt("cust_status"));
		            entity.setCustZipcode(rs.getString("cust_zipcode"));
		            entity.setCustAddress(rs.getString("cust_address"));
		            entity.setCustLevel(rs.getInt("cust_level"));
		            entity.setCustCertType(rs.getInt("cust_cert_type"));
		            entity.setCustCertCode(rs.getString("cust_cert_code"));
		            entity.setCustCertAddress(rs.getString("cust_cert_address"));
		            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
		            entity.setGender(rs.getInt("gender"));
		            entity.setLunar(rs.getInt("lunar"));
		            entity.setBirthday(rs.getDate("birthday"));
		            entity.setOccupation(rs.getInt("occupation"));
		            entity.setRealNameFlag(rs.getInt("real_name_flag"));
		            entity.setContractNo(rs.getString("contract_no"));
		            entity.setCharacterDesc(rs.getString("character_desc"));
		            entity.setMarryStatus(rs.getInt("marry_status"));
		            entity.setVipNo(rs.getString("vip_no"));
		            entity.setAllowAgent(rs.getInt("allow_agent"));
		            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
		            entity.setEntryChnl(rs.getInt("entry_chnl"));
		            entity.setState(rs.getString("state"));
		            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
		            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
		            entity.setOpId(rs.getBigDecimal("op_id"));
		            entity.setOrgId(rs.getBigDecimal("org_id"));
		            entity.setDoneCode(rs.getBigDecimal("done_code"));
		            entity.setCreateDate(rs.getDate("create_date"));
		            entity.setDoneDate(rs.getDate("done_date"));
		            entity.setEffectiveDate(rs.getDate("effective_date"));
		            entity.setExpireDate(rs.getDate("expire_date"));
		            entity.setRegionId(rs.getString("region_id"));
		            entity.setCountyId(rs.getString("county_id"));
		            entity.setNotes(rs.getString("notes"));
		            entity.setOldCustType(rs.getInt("old_cust_type"));
		            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
		            entity.setUniCustId(rs.getString("uni_cust_id"));
		            entity.setUniPartyId(rs.getString("uni_party_id"));
		        }
		    }finally {
		        // 关闭资源，防止连接泄露
		        if(rs != null) rs.close();
		        if(psCon != null) psCon.close();
		        if(conn != null) conn.close();
		    }
		    return entity;
		}

	    //带等于多加密字段返回单条1000加密字段
		public List<IndivCustEntity> getCustomerLimit1kByCustNameAndCertCode(SecurityPerformanceTestRequest request) throws SQLException{
			String result="";
			//【修复：改用?占位符，防SQL注入】
		    String sql="select * from cm_indiv_customer_lizw_574 a where cust_name=? and cust_cert_code=? limit 1000";
		    Connection conn = null;
		    PreparedStatement psCon = null;
		    ResultSet rs = null;
		    List<IndivCustEntity> entitys = new ArrayList<>();
		    try{
		        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
		        psCon = conn.prepareStatement(sql);
		        // 给占位符赋值
		        psCon.setString(1, request.getCustCertCode());
		        rs = psCon.executeQuery();

		        while(rs.next()){
		        	IndivCustEntity entity = new IndivCustEntity();
		            // ========== 字段赋值：数据库字段 → Entity属性 ==========
		            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
		            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
			        entity.setCustName(rs.getString("cust_name"));
		            entity.setCustStatus(rs.getInt("cust_status"));
		            entity.setCustZipcode(rs.getString("cust_zipcode"));
			        entity.setCustAddress(rs.getString("cust_address"));
		            entity.setCustLevel(rs.getInt("cust_level"));
		            entity.setCustCertType(rs.getInt("cust_cert_type"));
			        entity.setCustCertCode(rs.getString("cust_cert_code"));
			        entity.setCustCertAddress(rs.getString("cust_cert_address"));
		            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
		            entity.setGender(rs.getInt("gender"));
		            entity.setLunar(rs.getInt("lunar"));
		            entity.setBirthday(rs.getDate("birthday"));
		            entity.setOccupation(rs.getInt("occupation"));
		            entity.setRealNameFlag(rs.getInt("real_name_flag"));
		            entity.setContractNo(rs.getString("contract_no"));
		            entity.setCharacterDesc(rs.getString("character_desc"));
		            entity.setMarryStatus(rs.getInt("marry_status"));
		            entity.setVipNo(rs.getString("vip_no"));
		            entity.setAllowAgent(rs.getInt("allow_agent"));
		            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
		            entity.setEntryChnl(rs.getInt("entry_chnl"));
		            entity.setState(rs.getString("state"));
		            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
		            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
		            entity.setOpId(rs.getBigDecimal("op_id"));
		            entity.setOrgId(rs.getBigDecimal("org_id"));
		            entity.setDoneCode(rs.getBigDecimal("done_code"));
		            entity.setCreateDate(rs.getDate("create_date"));
		            entity.setDoneDate(rs.getDate("done_date"));
		            entity.setEffectiveDate(rs.getDate("effective_date"));
		            entity.setExpireDate(rs.getDate("expire_date"));
		            entity.setRegionId(rs.getString("region_id"));
		            entity.setCountyId(rs.getString("county_id"));
		            entity.setNotes(rs.getString("notes"));
		            entity.setOldCustType(rs.getInt("old_cust_type"));
		            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
		            entity.setUniCustId(rs.getString("uni_cust_id"));
		            entity.setUniPartyId(rs.getString("uni_party_id"));
		            entitys.add(entity);
		        }
		    }finally {
		        // 关闭资源，防止连接泄露
		        if(rs != null) rs.close();
		        if(psCon != null) psCon.close();
		        if(conn != null) conn.close();
		    }
		    return entitys;
		}

		// 带模糊查询单个加密字段返回单条
		public IndivCustEntity getOneByCustNameLike(SecurityPerformanceTestRequest request) throws SQLException{
		    String sql="select * from cm_indiv_customer_lizw_574 a where cust_name like ? limit 1 ";
		    Connection conn = null;
		    PreparedStatement psCon = null;
		    ResultSet rs = null;
		    IndivCustEntity entity = null;
		    try{
		        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
		        psCon = conn.prepareStatement(sql);

		        // 模糊查询关键：前后加 %
		        psCon.setString(1, "%" + request.getCustName() + "%");

		        rs = psCon.executeQuery();

		        if(rs.next()){ // 查单条用 if 不用 while
		        	entity = new IndivCustEntity();
		            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
		            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
			        entity.setCustName(rs.getString("cust_name"));
		            entity.setCustStatus(rs.getInt("cust_status"));
		            entity.setCustZipcode(rs.getString("cust_zipcode"));
			        entity.setCustAddress(rs.getString("cust_address"));
		            entity.setCustLevel(rs.getInt("cust_level"));
		            entity.setCustCertType(rs.getInt("cust_cert_type"));
			        entity.setCustCertCode(rs.getString("cust_cert_code"));
			        entity.setCustCertAddress(rs.getString("cust_cert_address"));
		            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
		            entity.setGender(rs.getInt("gender"));
		            entity.setLunar(rs.getInt("lunar"));
		            entity.setBirthday(rs.getDate("birthday"));
		            entity.setOccupation(rs.getInt("occupation"));
		            entity.setRealNameFlag(rs.getInt("real_name_flag"));
		            entity.setContractNo(rs.getString("contract_no"));
		            entity.setCharacterDesc(rs.getString("character_desc"));
		            entity.setMarryStatus(rs.getInt("marry_status"));
		            entity.setVipNo(rs.getString("vip_no"));
		            entity.setAllowAgent(rs.getInt("allow_agent"));
		            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
		            entity.setEntryChnl(rs.getInt("entry_chnl"));
		            entity.setState(rs.getString("state"));
		            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
		            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
		            entity.setOpId(rs.getBigDecimal("op_id"));
		            entity.setOrgId(rs.getBigDecimal("org_id"));
		            entity.setDoneCode(rs.getBigDecimal("done_code"));
		            entity.setCreateDate(rs.getDate("create_date"));
		            entity.setDoneDate(rs.getDate("done_date"));
		            entity.setEffectiveDate(rs.getDate("effective_date"));
		            entity.setExpireDate(rs.getDate("expire_date"));
		            entity.setRegionId(rs.getString("region_id"));
		            entity.setCountyId(rs.getString("county_id"));
		            entity.setNotes(rs.getString("notes"));
		            entity.setOldCustType(rs.getInt("old_cust_type"));
		            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
		            entity.setUniCustId(rs.getString("uni_cust_id"));
		            entity.setUniPartyId(rs.getString("uni_party_id"));
		        }
		    }finally {
		        if(rs != null) rs.close();
		        if(psCon != null) psCon.close();
		        if(conn != null) conn.close();
		    }
		    return entity;
		}

	    //模糊查询加密字段返回1000
		public List<IndivCustEntity> getCustomerLimit1kByCustNameLike(SecurityPerformanceTestRequest request) throws SQLException{
			String result="";
			//【修复：改用?占位符，防SQL注入】
		    String sql="select * from cm_indiv_customer_lizw_574 a where cust_name like ?  limit 1000";
		    Connection conn = null;
		    PreparedStatement psCon = null;
		    ResultSet rs = null;
		    List<IndivCustEntity> entitys = new ArrayList<>();
		    try{
		        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
		        psCon = conn.prepareStatement(sql);
		        // 给占位符赋值
			    psCon.setString(1, "%" + request.getCustName() + "%");
		        rs = psCon.executeQuery();

		        while(rs.next()){
		        	IndivCustEntity entity = new IndivCustEntity();
		            // ========== 字段赋值：数据库字段 → Entity属性 ==========
		            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
		            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
			        entity.setCustName(rs.getString("cust_name"));
		            entity.setCustStatus(rs.getInt("cust_status"));
		            entity.setCustZipcode(rs.getString("cust_zipcode"));
			        entity.setCustAddress(rs.getString("cust_address"));
		            entity.setCustLevel(rs.getInt("cust_level"));
		            entity.setCustCertType(rs.getInt("cust_cert_type"));
			        entity.setCustCertCode(rs.getString("cust_cert_code"));
			        entity.setCustCertAddress(rs.getString("cust_cert_address"));
		            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
		            entity.setGender(rs.getInt("gender"));
		            entity.setLunar(rs.getInt("lunar"));
		            entity.setBirthday(rs.getDate("birthday"));
		            entity.setOccupation(rs.getInt("occupation"));
		            entity.setRealNameFlag(rs.getInt("real_name_flag"));
		            entity.setContractNo(rs.getString("contract_no"));
		            entity.setCharacterDesc(rs.getString("character_desc"));
		            entity.setMarryStatus(rs.getInt("marry_status"));
		            entity.setVipNo(rs.getString("vip_no"));
		            entity.setAllowAgent(rs.getInt("allow_agent"));
		            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
		            entity.setEntryChnl(rs.getInt("entry_chnl"));
		            entity.setState(rs.getString("state"));
		            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
		            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
		            entity.setOpId(rs.getBigDecimal("op_id"));
		            entity.setOrgId(rs.getBigDecimal("org_id"));
		            entity.setDoneCode(rs.getBigDecimal("done_code"));
		            entity.setCreateDate(rs.getDate("create_date"));
		            entity.setDoneDate(rs.getDate("done_date"));
		            entity.setEffectiveDate(rs.getDate("effective_date"));
		            entity.setExpireDate(rs.getDate("expire_date"));
		            entity.setRegionId(rs.getString("region_id"));
		            entity.setCountyId(rs.getString("county_id"));
		            entity.setNotes(rs.getString("notes"));
		            entity.setOldCustType(rs.getInt("old_cust_type"));
		            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
		            entity.setUniCustId(rs.getString("uni_cust_id"));
		            entity.setUniPartyId(rs.getString("uni_party_id"));
		            entitys.add(entity);
		        }
		    }finally {
		        // 关闭资源，防止连接泄露
		        if(rs != null) rs.close();
		        if(psCon != null) psCon.close();
		        if(conn != null) conn.close();
		    }
		    return entitys;
		}
		public IndivCustEntity getCustomerTest(SecurityPerformanceTestRequest request) throws SQLException{

			//【修复：改用?占位符，防SQL注入】
		    String sql="select * from cm_indiv_customer_lizw_574 a where indiv_Cust_id=?";
		    Connection conn = null;
		    PreparedStatement psCon = null;
		    ResultSet rs = null;
		    IndivCustEntity entity = null;
		    try{
		        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
		        psCon = conn.prepareStatement(sql);
		        // 给占位符赋值
		        psCon.setBigDecimal(1, request.getIndivCustId());
		        rs = psCon.executeQuery();

		        while(rs.next()){
		            // 实例化实体
		            entity = new IndivCustEntity();
		            // ========== 字段赋值：数据库字段 → Entity属性 ==========
		            entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
		            entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
		            entity.setCustName(rs.getString("cust_name"));
		            entity.setCustStatus(rs.getInt("cust_status"));
		            entity.setCustZipcode(rs.getString("cust_zipcode"));
		            entity.setCustAddress(rs.getString("cust_address"));
		            entity.setCustLevel(rs.getInt("cust_level"));
		            entity.setCustCertType(rs.getInt("cust_cert_type"));
		            entity.setCustCertCode(rs.getString("cust_cert_code"));
		            entity.setCustCertAddress(rs.getString("cust_cert_address"));
		            entity.setCustCertExpire(rs.getDate("cust_cert_expire"));
		            entity.setGender(rs.getInt("gender"));
		            entity.setLunar(rs.getInt("lunar"));
		            entity.setBirthday(rs.getDate("birthday"));
		            entity.setOccupation(rs.getInt("occupation"));
		            entity.setRealNameFlag(rs.getInt("real_name_flag"));
		            entity.setContractNo(rs.getString("contract_no"));
		            entity.setCharacterDesc(rs.getString("character_desc"));
		            entity.setMarryStatus(rs.getInt("marry_status"));
		            entity.setVipNo(rs.getString("vip_no"));
		            entity.setAllowAgent(rs.getInt("allow_agent"));
		            entity.setAdminFamilyId(rs.getBigDecimal("admin_family_id"));
		            entity.setEntryChnl(rs.getInt("entry_chnl"));
		            entity.setState(rs.getString("state"));
		            entity.setCreateOpId(rs.getBigDecimal("create_op_id"));
		            entity.setCreateOrgId(rs.getBigDecimal("create_org_id"));
		            entity.setOpId(rs.getBigDecimal("op_id"));
		            entity.setOrgId(rs.getBigDecimal("org_id"));
		            entity.setDoneCode(rs.getBigDecimal("done_code"));
		            entity.setCreateDate(rs.getDate("create_date"));
		            entity.setDoneDate(rs.getDate("done_date"));
		            entity.setEffectiveDate(rs.getDate("effective_date"));
		            entity.setExpireDate(rs.getDate("expire_date"));
		            entity.setRegionId(rs.getString("region_id"));
		            entity.setCountyId(rs.getString("county_id"));
		            entity.setNotes(rs.getString("notes"));
		            entity.setOldCustType(rs.getInt("old_cust_type"));
		            entity.setCustModifyDate(rs.getDate("cust_modify_date"));
		            entity.setUniCustId(rs.getString("uni_cust_id"));
		            entity.setUniPartyId(rs.getString("uni_party_id"));
		        }
		    }finally {
		        // 关闭资源，防止连接泄露
		        if(rs != null) rs.close();
		        if(psCon != null) psCon.close();
		        if(conn != null) conn.close();
		    }
		    return entity;
		}

	/**
	 * 单条数据插入方法
	 * @param entity 要插入的客户实体
	 * @return 受影响行数（1=成功，0=失败）
	 * @throws SQLException
	 */
	public int insertIndivCust(SecurityPerformanceTestRequest entity) throws SQLException {
	    // 插入 SQL（字段按你的表结构写全）
	    String sql = "INSERT INTO cm_indiv_customer_lizw_574 (" +
	            "indiv_cust_id, base_cust_id, cust_name, cust_status, cust_zipcode, cust_address, " +
	            "cust_level, cust_cert_type, cust_cert_code, cust_cert_address, cust_cert_expire, " +
	            "gender, lunar, birthday, occupation, real_name_flag, contract_no, character_desc, " +
	            "marry_status, vip_no, allow_agent, admin_family_id, entry_chnl, state, create_op_id, " +
	            "create_org_id, op_id, org_id, done_code, create_date, done_date, effective_date, " +
	            "expire_date, region_id, county_id, notes, old_cust_type, cust_modify_date, uni_cust_id, uni_party_id " +
	            ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    int rows = 0;

	    try {
	        // 获取连接（你原来的配置）
	        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
	        pstmt = conn.prepareStatement(sql);
	        int index = 1;
	        long id = ThreadLocalRandom.current().nextLong(1000000000L,10000000000L);
	        // ===================== 开始按顺序赋值 =====================
	        pstmt.setBigDecimal(index++, new BigDecimal(id));
	        pstmt.setBigDecimal(index++, entity.getBaseCustId());
		    pstmt.setString(index++, entity.getCustName());//
	        pstmt.setInt(index++, entity.getCustStatus());
	        pstmt.setString(index++, entity.getCustZipcode());
	        pstmt.setString(index++, entity.getCustAddress());//

	        pstmt.setInt(index++, entity.getCustLevel());
	        pstmt.setInt(index++, entity.getCustCertType());
	        pstmt.setString(index++, entity.getCustCertCode());//
	        pstmt.setString(index++, entity.getCustCertAddress());//
	        pstmt.setDate(index++, entity.getCustCertExpire() == null ? null : new java.sql.Date(entity.getCustCertExpire().getTime()));

	        pstmt.setInt(index++, entity.getGender());
	        pstmt.setInt(index++, entity.getLunar());
	        pstmt.setDate(index++, entity.getBirthday() == null ? null : new java.sql.Date(entity.getBirthday().getTime()));
	        pstmt.setInt(index++, entity.getOccupation());
	        pstmt.setInt(index++, entity.getRealNameFlag());
	        pstmt.setString(index++, entity.getContractNo());
	        pstmt.setString(index++, entity.getCharacterDesc());

	        pstmt.setInt(index++, entity.getMarryStatus());
	        pstmt.setString(index++, entity.getVipNo());
	        pstmt.setInt(index++, entity.getAllowAgent());
	        pstmt.setBigDecimal(index++, entity.getAdminFamilyId());
	        pstmt.setInt(index++, entity.getEntryChnl());
	        pstmt.setString(index++, entity.getState());

	        pstmt.setBigDecimal(index++, entity.getCreateOpId());
	        pstmt.setBigDecimal(index++, entity.getCreateOrgId());
	        pstmt.setBigDecimal(index++, entity.getOpId());
	        pstmt.setBigDecimal(index++, entity.getOrgId());
	        pstmt.setBigDecimal(index++, entity.getDoneCode());

	        pstmt.setDate(index++, entity.getCreateDate() == null ? null : new java.sql.Date(entity.getCreateDate().getTime()));
	        pstmt.setDate(index++, entity.getDoneDate() == null ? null : new java.sql.Date(entity.getDoneDate().getTime()));
	        pstmt.setDate(index++, entity.getEffectiveDate() == null ? null : new java.sql.Date(entity.getEffectiveDate().getTime()));
	        pstmt.setDate(index++, entity.getExpireDate() == null ? null : new java.sql.Date(entity.getExpireDate().getTime()));

	        pstmt.setString(index++, entity.getRegionId());
	        pstmt.setString(index++, entity.getCountyId());
	        pstmt.setString(index++, entity.getNotes());
	        pstmt.setInt(index++, entity.getOldCustType());
	        pstmt.setDate(index++, entity.getCustModifyDate() == null ? null : new java.sql.Date(entity.getCustModifyDate().getTime()));
	        pstmt.setString(index++, entity.getUniCustId());
	        pstmt.setString(index++, entity.getUniPartyId());

	        // 执行插入
	        rows = pstmt.executeUpdate();

	    } finally {
	        // 关闭资源
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return rows;
	}
	/**
	 * 批量插入方法（每100条提交一次，高性能）
	 * @param list 客户实体列表
	 * @return 总成功条数
	 * @throws SQLException
	 */
	public int batchInsertIndivCust(List<SecurityPerformanceTestRequest> list) throws SQLException {
	    // 插入 SQL（和你原来完全一致）
	    String sql = "INSERT INTO cm_indiv_customer_lizw_574 (" +
	            "indiv_cust_id, base_cust_id, cust_name, cust_status, cust_zipcode, cust_address, " +
	            "cust_level, cust_cert_type, cust_cert_code, cust_cert_address, cust_cert_expire, " +
	            "gender, lunar, birthday, occupation, real_name_flag, contract_no, character_desc, " +
	            "marry_status, vip_no, allow_agent, admin_family_id, entry_chnl, state, create_op_id, " +
	            "create_org_id, op_id, org_id, done_code, create_date, done_date, effective_date, " +
	            "expire_date, region_id, county_id, notes, old_cust_type, cust_modify_date, uni_cust_id, uni_party_id " +
	            ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    int totalRows = 0;

	    try {
	        conn = DriverManager.getConnection(aiga_tns, aiga_user, aiga_passwd);
	        // 关闭自动提交，开启批量模式
	        conn.setAutoCommit(false);
	        pstmt = conn.prepareStatement(sql);

	        int batchSize = 100; // 每100条提交一次
	        int count = 0;

	        for (SecurityPerformanceTestRequest entity : list) {
	            int index = 1;
	            // 生成唯一10位ID
	            long id = ThreadLocalRandom.current().nextLong(1000000000L, 10000000000L);

	            // ===================== 按顺序赋值（和你原来完全一样） =====================
	            pstmt.setBigDecimal(index++, new BigDecimal(id));
	            pstmt.setBigDecimal(index++, entity.getBaseCustId());
	            pstmt.setString(index++, entity.getCustName());
	            pstmt.setInt(index++, entity.getCustStatus());
	            pstmt.setString(index++, entity.getCustZipcode());
	            pstmt.setString(index++, entity.getCustAddress());

	            pstmt.setInt(index++, entity.getCustLevel());
	            pstmt.setInt(index++, entity.getCustCertType());
		        pstmt.setString(index++, entity.getCustCertCode());//
		        pstmt.setString(index++, entity.getCustCertAddress());//
	            pstmt.setDate(index++, entity.getCustCertExpire() == null ? null : new java.sql.Date(entity.getCustCertExpire().getTime()));

	            pstmt.setInt(index++, entity.getGender());
	            pstmt.setInt(index++, entity.getLunar());
	            pstmt.setDate(index++, entity.getBirthday() == null ? null : new java.sql.Date(entity.getBirthday().getTime()));
	            pstmt.setInt(index++, entity.getOccupation());
	            pstmt.setInt(index++, entity.getRealNameFlag());
	            pstmt.setString(index++, entity.getContractNo());
	            pstmt.setString(index++, entity.getCharacterDesc());

	            pstmt.setInt(index++, entity.getMarryStatus());
	            pstmt.setString(index++, entity.getVipNo());
	            pstmt.setInt(index++, entity.getAllowAgent());
	            pstmt.setBigDecimal(index++, entity.getAdminFamilyId());
	            pstmt.setInt(index++, entity.getEntryChnl());
	            pstmt.setString(index++, entity.getState());

	            pstmt.setBigDecimal(index++, entity.getCreateOpId());
	            pstmt.setBigDecimal(index++, entity.getCreateOrgId());
	            pstmt.setBigDecimal(index++, entity.getOpId());
	            pstmt.setBigDecimal(index++, entity.getOrgId());
	            pstmt.setBigDecimal(index++, entity.getDoneCode());

	            pstmt.setDate(index++, entity.getCreateDate() == null ? null : new java.sql.Date(entity.getCreateDate().getTime()));
	            pstmt.setDate(index++, entity.getDoneDate() == null ? null : new java.sql.Date(entity.getDoneDate().getTime()));
	            pstmt.setDate(index++, entity.getEffectiveDate() == null ? null : new java.sql.Date(entity.getEffectiveDate().getTime()));
	            pstmt.setDate(index++, entity.getExpireDate() == null ? null : new java.sql.Date(entity.getExpireDate().getTime()));

	            pstmt.setString(index++, entity.getRegionId());
	            pstmt.setString(index++, entity.getCountyId());
	            pstmt.setString(index++, entity.getNotes());
	            pstmt.setInt(index++, entity.getOldCustType());
	            pstmt.setDate(index++, entity.getCustModifyDate() == null ? null : new java.sql.Date(entity.getCustModifyDate().getTime()));
	            pstmt.setString(index++, entity.getUniCustId());
	            pstmt.setString(index++, entity.getUniPartyId());

	            // 添加到批处理
	            pstmt.addBatch();
	            count++;
	            totalRows++;

	            // 每100条执行一次批量提交
	            if (count % batchSize == 0) {
	                pstmt.executeBatch();
	                conn.commit();
	                count = 0;
	            }
	        }

	        // 最后不足100条的剩余数据提交
	        if (count > 0) {
	            pstmt.executeBatch();
	            conn.commit();
	        }

	    } catch (SQLException e) {
	        if (conn != null) conn.rollback(); // 失败回滚
	        throw e;
	    } finally {
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return totalRows;
	}

}
