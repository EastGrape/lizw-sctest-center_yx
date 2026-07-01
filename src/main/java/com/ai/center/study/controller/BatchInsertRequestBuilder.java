package com.ai.center.study.controller;

import com.ai.center.study.service.dto.SecurityPerformanceTestRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 批量插入请求扩展工具
 * <p>
 * 用于固定数量压测入口复用外部报文：50条入口取前50条，500/1000/10000条入口按前100条循环复制。
 *
 * @author xiangqi
 * @date 2026-06-30 17:31
 */
public final class BatchInsertRequestBuilder {

    // 50条压测入口需要的最小外部报文数量
    private static final int FIFTY_TEMPLATE_SIZE = 50;

    // 大批量压测入口需要的最小外部报文模板数量
    private static final int LARGE_BATCH_TEMPLATE_SIZE = 100;

    private BatchInsertRequestBuilder() {
    }

    /**
     * 构造固定数量批量插入请求
     * <p>
     * 50条使用前50条报文；500、1000、10000条以前100条报文为模板循环复制，保证Service仍按100条批处理提交。
     *
     * @Author xiangqi
     * @date 2026-06-30 17:31
     * @Param requests 外部传入请求报文
     * @Param targetSize 目标批量条数
     * @Return java.util.List
     */
    static List<SecurityPerformanceTestRequest> build(List<SecurityPerformanceTestRequest> requests, int targetSize) {
        int templateSize = templateSize(targetSize);
        validateRequests(requests, targetSize, templateSize);

        List<SecurityPerformanceTestRequest> batchRequests = new ArrayList<SecurityPerformanceTestRequest>(targetSize);
        for (int i = 0; i < targetSize; i++) {
            batchRequests.add(copy(requests.get(i % templateSize)));
        }
        return batchRequests;
    }

    private static int templateSize(int targetSize) {
        if (targetSize == 50) {
            return FIFTY_TEMPLATE_SIZE;
        }
        if (targetSize == 500 || targetSize == 1000 || targetSize == 10000) {
            return LARGE_BATCH_TEMPLATE_SIZE;
        }
        throw new IllegalArgumentException("不支持的批量插入目标条数: " + targetSize);
    }

    private static void validateRequests(List<SecurityPerformanceTestRequest> requests, int targetSize, int templateSize) {
        if (requests == null) {
            throw new IllegalArgumentException("批量插入请求不能为空");
        }
        if (requests.size() < templateSize) {
            throw new IllegalArgumentException(
                    "批量插入" + targetSize + "条至少需要传入" + templateSize + "条报文，当前条数: " + requests.size());
        }
    }

    private static SecurityPerformanceTestRequest copy(SecurityPerformanceTestRequest source) {
        SecurityPerformanceTestRequest target = new SecurityPerformanceTestRequest();
        target.setIndivCustId(source.getIndivCustId());
        target.setBaseCustId(source.getBaseCustId());
        target.setCustName(source.getCustName());
        target.setCustAddress(source.getCustAddress());
        target.setCustCertType(source.getCustCertType());
        target.setCustCertCode(source.getCustCertCode());
        target.setCustCertAddress(source.getCustCertAddress());
        target.setState(source.getState());
        target.setDoneCode(source.getDoneCode());
        target.setDoneDate(copyDate(source.getDoneDate()));
        target.setEffectiveDate(copyDate(source.getEffectiveDate()));
        target.setExpireDate(copyDate(source.getExpireDate()));
        target.setRegionId(source.getRegionId());
        target.setCountyId(source.getCountyId());
        target.setPhoneNumber(source.getPhoneNumber());
        return target;
    }

    private static Date copyDate(Date source) {
        return source == null ? null : new Date(source.getTime());
    }

    /**
     * 校验客户地址更新请求
     * <p>
     * 根据ID更新必须带where条件和目标地址，避免空请求进入数据库更新链路。
     *
     * @Author xiangqi
     * @date 2026-06-30 18:20
     * @Param request 客户地址更新请求
     */
    public static void validate(SecurityPerformanceTestRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("更新请求不能为空");
        }
        if (request.getIndivCustId() == null) {
            throw new IllegalArgumentException("indivCustId不能为空");
        }
        if (request.getCustAddress() == null || request.getCustAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("custAddress不能为空");
        }
    }
}
