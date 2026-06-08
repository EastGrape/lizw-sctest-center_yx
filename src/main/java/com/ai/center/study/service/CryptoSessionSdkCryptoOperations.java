package com.ai.center.study.service;

import com.asiainfo.cass.agentsdk.endecryption.db.CryptoSession;

import java.util.Objects;

/**
 * SDK会话式加解密操作适配器
 * <p>
 * 批量业务场景先打开一次CryptoSession，再通过该适配器复用同一个会话完成字段加解密。
 *
 * @author xiangqi
 * @date 2026-06-08 18:16
 */
public class CryptoSessionSdkCryptoOperations implements SdkCryptoOperations {

    // SDK会话实例，批量循环内所有加解密操作复用它
    private final CryptoSession cryptoSession;

    public CryptoSessionSdkCryptoOperations(CryptoSession cryptoSession) {
        this.cryptoSession = Objects.requireNonNull(cryptoSession, "cryptoSession");
    }

    @Override
    public String encrypt(String plainText) {
        return cryptoSession.encrypt(plainText);
    }

    @Override
    public String encrypt(String plainText, boolean supportLike) {
        return cryptoSession.encrypt(plainText, supportLike);
    }

    @Override
    public String encryptByLikeQuery(String plainText) {
        return cryptoSession.encryptByLikeQuery(plainText);
    }

    @Override
    public String decrypt(String cipherText) {
        return cryptoSession.decrypt(cipherText);
    }
}
