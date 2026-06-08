package com.ai.center.study.service;

/**
 * SDK加解密操作边界
 * <p>
 * 用于隔离静态SDK调用和会话式SDK调用，业务字段规则只依赖这个最小接口。
 *
 * @author xiangqi
 * @date 2026-06-08 18:16
 */
public interface SdkCryptoOperations {

    String encrypt(String plainText);

    String encrypt(String plainText, boolean supportLike);

    String encryptByLikeQuery(String plainText);

    String decrypt(String cipherText);
}
