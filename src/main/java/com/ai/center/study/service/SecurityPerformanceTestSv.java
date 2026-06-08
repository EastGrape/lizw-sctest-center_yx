package com.ai.center.study.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.ai.center.study.domain.IndivCustEntity;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class SecurityPerformanceTestSv {
	final static String aiga_tns = "jdbc:postgresql://10.179.95.94:6432/teu?targetServerType=master&binaryTransfer=False&forceBinary=False&reWriteBatchedInserts=true&grammar=oracle&prepareThreshold=0";
	final static String aiga_user = "teu1";
	final static String aiga_passwd = "dt_encry_test1ENC#";
	//不带条件返回单条单加密字段
	public IndivCustEntity getCustomerColumnLimitOne() throws SQLException{

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a limit 1";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		IndivCustEntity entity = null;
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			// 给占位符赋值
			rs = psCon.executeQuery();

			while(rs.next()){
				entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a limit 1000";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		List<IndivCustEntity> entitys = new ArrayList<>();
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			// 给占位符赋值
			rs = psCon.executeQuery();

			while(rs.next()){
				IndivCustEntity entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a where phone_number=? limit 1";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		IndivCustEntity entity = null;
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			psCon.setString(1, request.getPhoneNumber());
			// 给占位符赋值
			rs = psCon.executeQuery();

			while(rs.next()){
				entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a where phone_number=? limit 1000";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		List<IndivCustEntity> entitys = new ArrayList<>();
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			// 给占位符赋值
			psCon.setString(1, request.getPhoneNumber());
			rs = psCon.executeQuery();

			while(rs.next()){
				IndivCustEntity entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a where cust_name=? and cust_address=? and cust_cert_code=? and phone_number=? limit 1";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		IndivCustEntity entity = null;
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			psCon.setString(1, request.getCustName());
			psCon.setString(2, request.getCustAddress());
			psCon.setString(3, request.getCustCertCode());
			psCon.setString(4, request.getPhoneNumber());
			// 给占位符赋值
			rs = psCon.executeQuery();

			while(rs.next()){
				entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a where cust_name=? and cust_address=? and cust_cert_code=? and phone_number=?  limit 1000";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		List<IndivCustEntity> entitys = new ArrayList<>();
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			// 给占位符赋值
			psCon.setString(1, request.getCustName());
			psCon.setString(2, request.getCustAddress());
			psCon.setString(3, request.getCustCertCode());
			psCon.setString(4, request.getPhoneNumber());
			rs = psCon.executeQuery();

			while(rs.next()){
				IndivCustEntity entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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
		String sql="select * from cm_indiv_customer_yx_574 a where cust_address like ? limit 1 ";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		IndivCustEntity entity = null;
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);

			// 模糊查询关键：前后加 %
			psCon.setString(1, "%" + request.getCustAddress() + "%");

			rs = psCon.executeQuery();

			if(rs.next()){ // 查单条用 if 不用 while
				entity = new IndivCustEntity();
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

		//【修复：改用?占位符，防SQL注入】
		String sql="select * from cm_indiv_customer_yx_574 a where cust_address like ?  limit 1000";
		Connection conn = null;
		PreparedStatement psCon = null;
		ResultSet rs = null;
		List<IndivCustEntity> entitys = new ArrayList<>();
		try{
			conn = dataSource.getConnection();
			psCon = conn.prepareStatement(sql);
			// 给占位符赋值
			psCon.setString(1, "%" + request.getCustAddress() + "%");
			rs = psCon.executeQuery();

			while(rs.next()){
				IndivCustEntity entity = new IndivCustEntity();
				// ========== 字段赋值：数据库字段 → Entity属性 ==========
				entity.setIndivCustId(rs.getBigDecimal("indiv_cust_id"));
				entity.setBaseCustId(rs.getBigDecimal("base_cust_id"));
				entity.setCustName(rs.getString("cust_name"));
				entity.setCustAddress(rs.getString("cust_address"));
				entity.setCustCertType(rs.getInt("cust_cert_type"));
				entity.setCustCertCode(rs.getString("cust_cert_code"));
				entity.setCustCertAddress(rs.getString("cust_cert_address"));
				entity.setState(rs.getString("state"));
				entity.setDoneCode(rs.getBigDecimal("done_code"));
				entity.setDoneDate(rs.getDate("done_date"));
				entity.setEffectiveDate(rs.getDate("effective_date"));
				entity.setExpireDate(rs.getDate("expire_date"));
				entity.setRegionId(rs.getString("region_id"));
				entity.setCountyId(rs.getString("county_id"));
				entity.setPhoneNumber(rs.getString("phone_number"));
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

	/**
	 * 单条数据插入方法
	 * @param entity 要插入的客户实体
	 * @return 受影响行数（1=成功，0=失败）
	 * @throws SQLException
	 */
	public int insertIndivCust(SecurityPerformanceTestRequest entity) throws SQLException {
		// 插入 SQL（字段按你的表结构写全）
		String sql = "INSERT INTO cm_indiv_customer_yx_574 (" +
				"indiv_cust_id, base_cust_id, cust_name, cust_address, " +
				"cust_cert_type, cust_cert_code, cust_cert_address, state, " +
				"done_code, done_date, effective_date, expire_date, " +
				"region_id, county_id, phone_number " +
				") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			// 获取连接（你原来的配置）
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			long id = ThreadLocalRandom.current().nextLong(1000000000000L, 10000000000000L);
			// ===================== 开始按顺序赋值 =====================
			pstmt.setBigDecimal(index++, new BigDecimal(id));
			pstmt.setBigDecimal(index++, entity.getBaseCustId());
			pstmt.setString(index++, entity.getCustName());
			pstmt.setString(index++, entity.getCustAddress());
			pstmt.setInt(index++, entity.getCustCertType());
			pstmt.setString(index++, entity.getCustCertCode());
			pstmt.setString(index++, entity.getCustCertAddress());
			pstmt.setString(index++, entity.getState());
			pstmt.setBigDecimal(index++, entity.getDoneCode());
			pstmt.setDate(index++, entity.getDoneDate() == null ? null : new java.sql.Date(entity.getDoneDate().getTime()));
			pstmt.setDate(index++, entity.getEffectiveDate() == null ? null : new java.sql.Date(entity.getEffectiveDate().getTime()));
			pstmt.setDate(index++, entity.getExpireDate() == null ? null : new java.sql.Date(entity.getExpireDate().getTime()));
			pstmt.setString(index++, entity.getRegionId());
			pstmt.setString(index++, entity.getCountyId());
			pstmt.setString(index++, entity.getPhoneNumber());
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
		String sql = "INSERT INTO cm_indiv_customer_yx_574 (" +
				"indiv_cust_id, base_cust_id, cust_name, cust_address, " +
				"cust_cert_type, cust_cert_code, cust_cert_address, state, " +
				"done_code, done_date, effective_date, expire_date, " +
				"region_id, county_id, phone_number " +
				") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		int totalRows = 0;

		try {
			conn = dataSource.getConnection();
			// 关闭自动提交，开启批量模式
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);

			int batchSize = 100; // 每100条提交一次
			int count = 0;

			for (SecurityPerformanceTestRequest entity : list) {
				int index = 1;
				// 生成唯一10位ID
				long id = ThreadLocalRandom.current().nextLong(1000000000000L, 10000000000000L);

				// ===================== 按顺序赋值（和你原来完全一样） =====================
				pstmt.setBigDecimal(index++, new BigDecimal(id));
				pstmt.setBigDecimal(index++, entity.getBaseCustId());
				pstmt.setString(index++, entity.getCustName());
				pstmt.setString(index++, entity.getCustAddress());
				pstmt.setInt(index++, entity.getCustCertType());
				pstmt.setString(index++, entity.getCustCertCode());
				pstmt.setString(index++, entity.getCustCertAddress());
				pstmt.setString(index++, entity.getState());
				pstmt.setBigDecimal(index++, entity.getDoneCode());
				pstmt.setDate(index++, entity.getDoneDate() == null ? null : new java.sql.Date(entity.getDoneDate().getTime()));
				pstmt.setDate(index++, entity.getEffectiveDate() == null ? null : new java.sql.Date(entity.getEffectiveDate().getTime()));
				pstmt.setDate(index++, entity.getExpireDate() == null ? null : new java.sql.Date(entity.getExpireDate().getTime()));
				pstmt.setString(index++, entity.getRegionId());
				pstmt.setString(index++, entity.getCountyId());
				pstmt.setString(index++, entity.getPhoneNumber());

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

	// 全局定义连接池（只初始化一次，不要放在循环里）
	private static HikariDataSource dataSource;

	static {
		try {
			// 初始化连接池配置
			HikariConfig config = new HikariConfig();
			// Oracle 驱动
			config.setDriverClassName("org.postgresql.Driver");
			// 你的连接信息
			config.setJdbcUrl(aiga_tns);       // 数据库URL
			config.setUsername(aiga_user);     // 用户名
			config.setPassword(aiga_passwd);   // 密码

			// 连接池基础配置（生产可用）
			config.setMinimumIdle(5);          // 最小空闲连接
			config.setMaximumPoolSize(20);     // 最大连接数
			config.setConnectionTimeout(3000); // 获取连接超时时间
			config.setIdleTimeout(60000);      // 空闲超时
			config.setMaxLifetime(180000);     // 连接最大生命周期

			// 创建连接池
			dataSource = new HikariDataSource(config);

			// 验证连接
			try (Connection conn = dataSource.getConnection()) {
				if (!conn.isValid(1000)) {
					throw new RuntimeException("数据库连接池初始化失败：连接无效");
				}
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("数据库连接池初始化失败：" + e.getMessage());
		}
	}
	public static void main(String args[]) throws SQLException{
		SecurityPerformanceTestSv sv=new SecurityPerformanceTestSv();
		IndivCustEntity result=sv.getCustomerColumnLimitOne();
		System.out.print("lizw"+result);
	}
}
