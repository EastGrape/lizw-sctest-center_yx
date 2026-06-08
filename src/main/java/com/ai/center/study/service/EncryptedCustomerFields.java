package com.ai.center.study.service;

/**
 * 客户敏感字段加密结果
 * <p>
 * 封装四个查询条件敏感字段的SDK加密值，避免调用方依赖字段处理顺序。
 *
 * @author xiangqi
 * @date 2026-06-08 18:16
 */
public class EncryptedCustomerFields {

    // 客户姓名密文
    private final String custName;

    // 客户地址密文
    private final String custAddress;

    // 手机号码密文
    private final String phoneNumber;

    // 证件号码密文
    private final String custCertCode;

    public EncryptedCustomerFields(String custName, String custAddress, String phoneNumber, String custCertCode) {
        this.custName = custName;
        this.custAddress = custAddress;
        this.phoneNumber = phoneNumber;
        this.custCertCode = custCertCode;
    }

    public String getCustName() {
        return custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCustCertCode() {
        return custCertCode;
    }
}
