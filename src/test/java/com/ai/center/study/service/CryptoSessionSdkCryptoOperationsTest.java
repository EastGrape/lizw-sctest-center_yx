package com.ai.center.study.service;

import com.asiainfo.cass.agentsdk.endecryption.db.CryptoSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * SDK会话操作适配器测试
 * <p>
 * 用假的CryptoSession验证业务层加解密操作走会话实例，避免批量场景退回静态SDK调用。
 *
 * @author xiangqi
 * @date 2026-06-08 18:16
 */
public class CryptoSessionSdkCryptoOperationsTest {

    /**
     * SDK操作委托给同一个会话实例
     * <p>
     * 批量业务循环中应复用一次openCryptoSession创建出的会话，字段级操作只通过该会话完成。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     */
    @Test
    public void delegatesCryptoOperationsToCryptoSession() {
        RecordingCryptoSession session = new RecordingCryptoSession();
        CryptoSessionSdkCryptoOperations operations = new CryptoSessionSdkCryptoOperations(session);

        assertEquals("SE(张三)", operations.encrypt("张三"));
        assertEquals("SE_LIKE(浙江省杭州市)", operations.encrypt("浙江省杭州市", true));
        assertEquals("SLIKE(%浙江省%)", operations.encryptByLikeQuery("%浙江省%"));
        assertEquals("SD(C_ADDRESS)", operations.decrypt("C_ADDRESS"));

        assertEquals("session.encrypt:张三", session.calls.get(0));
        assertEquals("session.encryptLike:浙江省杭州市", session.calls.get(1));
        assertEquals("session.encryptByLikeQuery:%浙江省%", session.calls.get(2));
        assertEquals("session.decrypt:C_ADDRESS", session.calls.get(3));
        assertEquals(4, session.calls.size());
    }

    /**
     * SDK会话记录桩
     * <p>
     * 记录会话实例收到的加解密调用，用于证明适配器没有绕过CryptoSession。
     *
     * @author xiangqi
     * @date 2026-06-08 18:16
     */
    private static class RecordingCryptoSession implements CryptoSession {

        // 会话实例收到的调用记录
        private final List<String> calls = new ArrayList<>();

        @Override
        public void close() {
        }

        @Override
        public String encryptByLikeQuery(String plainText) {
            calls.add("session.encryptByLikeQuery:" + plainText);
            return "SLIKE(" + plainText + ")";
        }

        @Override
        public String decrypt(String cipherText) {
            calls.add("session.decrypt:" + cipherText);
            return "SD(" + cipherText + ")";
        }

        @Override
        public String encrypt(String plainText, boolean supportLike) {
            calls.add(supportLike ? "session.encryptLike:" + plainText : "session.encryptNoLike:" + plainText);
            return supportLike ? "SE_LIKE(" + plainText + ")" : "SE_NO_LIKE(" + plainText + ")";
        }

        @Override
        public String encrypt(String plainText) {
            calls.add("session.encrypt:" + plainText);
            return "SE(" + plainText + ")";
        }
    }
}
