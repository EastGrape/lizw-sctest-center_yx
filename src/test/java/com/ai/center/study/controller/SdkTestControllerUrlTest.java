package com.ai.center.study.controller;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * SDK测试Controller路径规则测试
 * <p>
 * 验证当前新项目URL集合保持不变，不补回原项目缺失接口。
 *
 * @author xiangqi
 * @date 2026-06-08 17:32
 */
public class SdkTestControllerUrlTest {

    /**
     * SDK测试接口路径保持当前新项目集合
     * <p>
     * 业务字段规则在Service内变化，Controller URL不随字段语义重命名。
     *
     * @Author xiangqi
     * @date 2026-06-08 17:32
     */
    @Test
    public void sdkControllerKeepsCurrentUrlSet() {
        Set<String> actualPaths = requestPaths();
        Set<String> expectedPaths = new TreeSet<>(Arrays.asList(
                "dev/SecuritySDKTest/insertone",
                "dev/SecuritySDKTest/insertBatch",
                "dev/SecuritySDKTest/getCustomerColumnLimitOne",
                "dev/SecuritySDKTest/getCustomerColumnLimit1k",
                "dev/SecuritySDKTest/getOneByPhoneNumber",
                "dev/SecuritySDKTest/getCustomerLimit1kByPhoneNumber",
                "dev/SecuritySDKTest/getOneByFourColumn",
                "dev/SecuritySDKTest/getCustomerLimit1kByFourColumn",
                "dev/SecuritySDKTest/getOneByCustAddressLike",
                "dev/SecuritySDKTest/getCustomerLimit1kByCustAddresseLike"
        ));

        assertEquals(expectedPaths, actualPaths);
    }

    private Set<String> requestPaths() {
        Set<String> paths = new TreeSet<>();
        for (Method method : SdkTestController.class.getDeclaredMethods()) {
            RequestMapping mapping = method.getAnnotation(RequestMapping.class);
            if (mapping == null) {
                continue;
            }
            paths.addAll(Arrays.asList(mapping.path()));
        }
        return paths;
    }
}
