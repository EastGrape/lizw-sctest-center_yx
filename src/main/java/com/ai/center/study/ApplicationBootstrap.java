package com.ai.center.study;


import com.asiainfo.cass.agentsdk.endecryption.db.AsiaAgentSdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Properties;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApplicationBootstrap{

	// SDK本地配置模式
	private static final String SDK_CONFIG_MODE = "local";

	// SDK配置目录JVM参数名
	private static final String SDK_CONFIG_DIR_PROPERTY = "dcAgentConfigDir";

	// SDK配置目录默认值
	private static final String DEFAULT_SDK_CONFIG_DIR = "/app/aigaweb/lizwtest/config";

	// 解密热词缓存开关配置名
	private static final String DECRYPT_CACHE_ENABLED = "agent-sdk-decrypt-cache-enabled";

	// 解密热词缓存容量配置名
	private static final String DECRYPT_CACHE_MAX_SIZE = "agent-sdk-decrypt-cache-max-size";

	// 解密热词缓存访问过期时间配置名
	private static final String DECRYPT_CACHE_EXPIRE_AFTER_ACCESS_MS = "agent-sdk-decrypt-cache-expire-after-access-ms";

	// 解密热词缓存文本长度上限配置名
	private static final String DECRYPT_CACHE_TEXT_MAX_LENGTH = "agent-sdk-decrypt-cache-text-max-length";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ApplicationBootstrap.class, args);
		// 使用java -DdcAgentConfigDir=/app/xxxx -jar 你的jar包  第二个参数 默认值，写服务器默认路径
		String configDir = System.getProperty(SDK_CONFIG_DIR_PROPERTY, DEFAULT_SDK_CONFIG_DIR);
		Properties config = buildSdkConfig(configDir);
		// 执行一次性初始化SDK
		AsiaAgentSdk.init(config);
		// 待加密明文
		String content = "测试加密";
		System.out.println("============2222等值加密==============");
		String a = AsiaAgentSdk.encrypt(content);
		System.out.println("密文:" + a);
		System.out.println("明文:" + AsiaAgentSdk.decrypt(a));
		System.out.println("============模糊加密==============");
		content = "测试";
		String b = AsiaAgentSdk.encryptByLikeQuery(content);
		System.out.println("明文：" + content + "，密文:" + b);
		content = "%测试";
		b = AsiaAgentSdk.encryptByLikeQuery(content);
		System.out.println("明文：" + content + "，密文:" + b);
		content = "测试%";
		b = AsiaAgentSdk.encryptByLikeQuery(content);
		System.out.println("明文：" + content + "，密文:" + b);
		content = "测%试";
		b = AsiaAgentSdk.encryptByLikeQuery(content);
		System.out.println("明文：" + content + "，密文:" + b);
		content = "%测试%";
		b = AsiaAgentSdk.encryptByLikeQuery(content);
		System.out.println("明文：" + content + "，密文:" + b);
	}

	/**
	 * 构建SDK初始化配置
	 * <p>
	 * 默认开启解密热词缓存，相同密文重复解密时可直接复用已解出的明文；同名JVM参数可覆盖默认性能参数。
	 *
	 * @Author xiangqi
	 * @date 2026-06-05 20:24
	 * @Param configDir SDK配置目录
	 * @Return java.util.Properties
	 */
	static Properties buildSdkConfig(String configDir) {
		Properties config = new Properties();
		config.put("agent-sdk-config-mode", SDK_CONFIG_MODE);
		config.put("agent-sdk-config-dir", configDir);
		putSdkConfig(config, DECRYPT_CACHE_ENABLED, "true");
		putSdkConfig(config, DECRYPT_CACHE_MAX_SIZE, "20000");
		putSdkConfig(config, DECRYPT_CACHE_EXPIRE_AFTER_ACCESS_MS, "300000");
		putSdkConfig(config, DECRYPT_CACHE_TEXT_MAX_LENGTH, "2048");
		return config;
	}

	/**
	 * 写入可由JVM参数覆盖的SDK配置
	 * <p>
	 * 性能参数默认适配离线压测场景，部署时可通过-D参数按数据重复率和堆内存大小调整。
	 *
	 * @Author xiangqi
	 * @date 2026-06-05 20:24
	 * @Param config SDK初始化配置
	 * @Param key SDK配置名
	 * @Param defaultValue 默认配置值
	 */
	private static void putSdkConfig(Properties config, String key, String defaultValue) {
		config.put(key, System.getProperty(key, defaultValue));
	}


}
