package com.ai.center.study.service;

import com.ai.center.study.domain.IndivCustEntity;
import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;

/**
 * SDK客户敏感字段加解密规则工具类
 * <p>
 * 集中维护压测接口中客户姓名、客户地址、手机号、证件号的SDK加解密字段规则。
 *
 * @author xiangqi
 * @date 2026-06-08 18:16
 */
public class SdkCustomerCryptoSupport {

    /**
     * 加密单字段等值查询条件
     * <p>
     * 当前Controller路径名保留PhoneNumber，但业务字段规则按cust_address等值条件处理，并支持LIKE索引。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     * @Param crypto SDK加解密操作
     * @Param request 查询请求
     * @Return java.lang.String
     */
    public String encryptSingleColumnCondition(SdkCryptoOperations crypto, SecurityPerformanceTestRequest request) {
        return crypto.encrypt(request.getCustAddress(), true);
    }

    /**
     * 加密四个客户敏感字段
     * <p>
     * cust_address需要支持LIKE索引，其余字段走普通等值加密。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     * @Param crypto SDK加解密操作
     * @Param request 查询请求
     * @Return com.ai.center.study.service.EncryptedCustomerFields
     */
    public EncryptedCustomerFields encryptSensitiveFields(SdkCryptoOperations crypto, SecurityPerformanceTestRequest request) {
        return new EncryptedCustomerFields(
                crypto.encrypt(request.getCustName()),
                crypto.encrypt(request.getCustAddress(), true),
                crypto.encrypt(request.getPhoneNumber()),
                crypto.encrypt(request.getCustCertCode())
        );
    }

    /**
     * 加密客户地址LIKE查询条件
     * <p>
     * SQL层保留LIKE占位符，Java层先拼接查询模式再交给SDK模糊查询加密接口。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     * @Param crypto SDK加解密操作
     * @Param request 查询请求
     * @Return java.lang.String
     */
    public String encryptAddressLikeCondition(SdkCryptoOperations crypto, SecurityPerformanceTestRequest request) {
        return crypto.encryptByLikeQuery("%" + request.getCustAddress() + "%");
    }

    /**
     * 解密单个客户敏感字段
     * <p>
     * 单字段查询结果只处理cust_address，其他字段保持数据库返回值。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     * @Param crypto SDK加解密操作
     * @Param entity 客户实体
     */
    public void decryptSingleSensitiveField(SdkCryptoOperations crypto, IndivCustEntity entity) {
        entity.setCustAddress(decryptIfPresent(crypto, entity.getCustAddress()));
    }

    /**
     * 解密四个客户敏感字段
     * <p>
     * 只处理SDK压测规则中的四个字段，cust_cert_address等非规则字段保持数据库返回值。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     * @Param crypto SDK加解密操作
     * @Param entity 客户实体
     */
    public void decryptSensitiveFields(SdkCryptoOperations crypto, IndivCustEntity entity) {
        entity.setCustName(decryptIfPresent(crypto, entity.getCustName()));
        entity.setCustAddress(decryptIfPresent(crypto, entity.getCustAddress()));
        entity.setPhoneNumber(decryptIfPresent(crypto, entity.getPhoneNumber()));
        entity.setCustCertCode(decryptIfPresent(crypto, entity.getCustCertCode()));
    }

    /**
     * 有值时解密密文
     * <p>
     * 空值和空白字符串没有解密意义，直接保留原值，避免批量结果中脏数据触发SDK异常。
     *
     * @Author xiangqi
     * @date 2026-06-08 18:16
     * @Param crypto SDK加解密操作
     * @Param cipherText 待解密文本
     * @Return java.lang.String
     */
    private String decryptIfPresent(SdkCryptoOperations crypto, String cipherText) {
        if (cipherText == null || cipherText.trim().isEmpty()) {
            return cipherText;
        }
        return crypto.decrypt(cipherText);
    }
}
