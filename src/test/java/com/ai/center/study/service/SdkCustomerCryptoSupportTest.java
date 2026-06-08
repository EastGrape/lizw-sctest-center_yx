package com.ai.center.study.service;

import com.ai.center.study.domain.IndivCustEntity;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * SDK客户敏感字段加解密规则测试
 * <p>
 * 用假的SDK操作对象验证业务字段规则，避免单测连接外部数据库或依赖真实SDK密钥。
 *
 * @author xiangqi
 * @date 2026-06-08 17:32
 */
public class SdkCustomerCryptoSupportTest {

    /**
     * 单字段等值查询只加密客户地址
     * <p>
     * 当前Controller路径仍保留PhoneNumber命名，但SDK业务规则实际对应cust_address。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void encryptSingleColumnConditionUsesCustAddressWithLikeSupport() {
        SdkCustomerCryptoSupport support = new SdkCustomerCryptoSupport();
        RecordingCryptoOperations crypto = new RecordingCryptoOperations();
        SecurityPerformanceTestRequest request = buildRequest();

        String cipherText = support.encryptSingleColumnCondition(crypto, request);

        assertEquals("E_LIKE(浙江省杭州市)", cipherText);
        assertEquals("encryptLike:浙江省杭州市", crypto.calls.get(0));
        assertEquals(1, crypto.calls.size());
    }

    /**
     * 多字段规则只让客户地址支持模糊加密
     * <p>
     * cust_name、phone_number、cust_cert_code走普通等值加密，cust_address走支持LIKE的等值加密。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void encryptSensitiveFieldsEncryptsFourFieldsAndOnlyAddressSupportsLike() {
        SdkCustomerCryptoSupport support = new SdkCustomerCryptoSupport();
        RecordingCryptoOperations crypto = new RecordingCryptoOperations();
        SecurityPerformanceTestRequest request = buildRequest();

        EncryptedCustomerFields fields = support.encryptSensitiveFields(crypto, request);

        assertEquals("E(张三)", fields.getCustName());
        assertEquals("E_LIKE(浙江省杭州市)", fields.getCustAddress());
        assertEquals("E(13800000000)", fields.getPhoneNumber());
        assertEquals("E(330100199001011234)", fields.getCustCertCode());
        assertEquals("encrypt:张三", crypto.calls.get(0));
        assertEquals("encryptLike:浙江省杭州市", crypto.calls.get(1));
        assertEquals("encrypt:13800000000", crypto.calls.get(2));
        assertEquals("encrypt:330100199001011234", crypto.calls.get(3));
        assertEquals(4, crypto.calls.size());
    }

    /**
     * 模糊查询入参交由SDK模糊加密
     * <p>
     * SQL层仍使用LIKE占位符，Java层先把明文地址包成包含查询模式。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void encryptAddressLikeConditionWrapsCustAddressBeforeSdkLikeEncryption() {
        SdkCustomerCryptoSupport support = new SdkCustomerCryptoSupport();
        RecordingCryptoOperations crypto = new RecordingCryptoOperations();
        SecurityPerformanceTestRequest request = buildRequest();

        String cipherText = support.encryptAddressLikeCondition(crypto, request);

        assertEquals("LIKE(%浙江省杭州市%)", cipherText);
        assertEquals("encryptByLikeQuery:%浙江省杭州市%", crypto.calls.get(0));
        assertEquals(1, crypto.calls.size());
    }

    /**
     * 单字段返回结果只解密客户地址
     * <p>
     * 单字段接口返回时不应误动客户姓名、手机号和证件号。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void decryptSingleColumnOnlyDecryptsCustAddress() {
        SdkCustomerCryptoSupport support = new SdkCustomerCryptoSupport();
        RecordingCryptoOperations crypto = new RecordingCryptoOperations();
        IndivCustEntity entity = buildCipherEntity();

        support.decryptSingleSensitiveField(crypto, entity);

        assertEquals("C_NAME", entity.getCustName());
        assertEquals("D(C_ADDRESS)", entity.getCustAddress());
        assertEquals("C_PHONE", entity.getPhoneNumber());
        assertEquals("C_CERT", entity.getCustCertCode());
        assertEquals("ADDR", entity.getCustCertAddress());
        assertEquals("decrypt:C_ADDRESS", crypto.calls.get(0));
        assertEquals(1, crypto.calls.size());
    }

    /**
     * 多字段返回结果解密四个新敏感字段
     * <p>
     * cust_cert_address不属于新规则字段，必须保持数据库原值。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void decryptSensitiveFieldsDecryptsFourFieldsAndKeepsCertAddress() {
        SdkCustomerCryptoSupport support = new SdkCustomerCryptoSupport();
        RecordingCryptoOperations crypto = new RecordingCryptoOperations();
        IndivCustEntity entity = buildCipherEntity();

        support.decryptSensitiveFields(crypto, entity);

        assertEquals("D(C_NAME)", entity.getCustName());
        assertEquals("D(C_ADDRESS)", entity.getCustAddress());
        assertEquals("D(C_PHONE)", entity.getPhoneNumber());
        assertEquals("D(C_CERT)", entity.getCustCertCode());
        assertEquals("ADDR", entity.getCustCertAddress());
        assertEquals(4, crypto.calls.size());
    }

    /**
     * 空白密文返回原值
     * <p>
     * 查询结果中空白字符串没有解密意义，不能调用SDK增加异常风险。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void decryptBlankTextReturnsOriginalValueWithoutCallingSdk() {
        SdkCustomerCryptoSupport support = new SdkCustomerCryptoSupport();
        RecordingCryptoOperations crypto = new RecordingCryptoOperations();
        IndivCustEntity entity = new IndivCustEntity();
        entity.setCustAddress("  ");

        support.decryptSingleSensitiveField(crypto, entity);

        assertEquals("  ", entity.getCustAddress());
        assertTrue(crypto.calls.isEmpty());
    }

    private SecurityPerformanceTestRequest buildRequest() {
        SecurityPerformanceTestRequest request = new SecurityPerformanceTestRequest();
        request.setCustName("张三");
        request.setCustAddress("浙江省杭州市");
        request.setPhoneNumber("13800000000");
        request.setCustCertCode("330100199001011234");
        request.setCustCertAddress("不加密地址");
        return request;
    }

    private IndivCustEntity buildCipherEntity() {
        IndivCustEntity entity = new IndivCustEntity();
        entity.setCustName("C_NAME");
        entity.setCustAddress("C_ADDRESS");
        entity.setPhoneNumber("C_PHONE");
        entity.setCustCertCode("C_CERT");
        entity.setCustCertAddress("ADDR");
        return entity;
    }

    /**
     * SDK操作记录桩
     * <p>
     * 记录字段加解密调用顺序和参数，用于验证业务规则是否传递到SDK边界。
     *
     * @author xiangqi
     * @date 2026-06-08 17:32
     */
    private static class RecordingCryptoOperations implements SdkCryptoOperations {

        // SDK调用记录
        private final List<String> calls = new ArrayList<>();

        @Override
        public String encrypt(String plainText) {
            calls.add("encrypt:" + plainText);
            return "E(" + plainText + ")";
        }

        @Override
        public String encrypt(String plainText, boolean supportLike) {
            calls.add(supportLike ? "encryptLike:" + plainText : "encryptNoLike:" + plainText);
            return supportLike ? "E_LIKE(" + plainText + ")" : "E_NO_LIKE(" + plainText + ")";
        }

        @Override
        public String encryptByLikeQuery(String plainText) {
            calls.add("encryptByLikeQuery:" + plainText);
            return "LIKE(" + plainText + ")";
        }

        @Override
        public String decrypt(String cipherText) {
            calls.add("decrypt:" + cipherText);
            return "D(" + cipherText + ")";
        }
    }
}
