package com.wedul.javajunit5studyjunit.extensions;

import com.wedul.javajunit5studyjunit.SlowTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

/**
 * 테스트 시작과 끝의 시간을 측정하고 늦는 경우에 Slow 태그를 붙이라고 권고 해주는 확장팩
 */
public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final String START_TIME = "START_TIME";
    private long threshHold = 1000L;

    public FindSlowTestExtension() {}

    public FindSlowTestExtension(long threshHold) {
        this.threshHold = threshHold;
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method requiredTestMethod = context.getRequiredTestMethod();
        String testMethodName = requiredTestMethod.getName();
        SlowTest slowTest = requiredTestMethod.getAnnotation(SlowTest.class);

        ExtensionContext.Store store = getStore(context);

        long startTime = store.remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;
        if (null == slowTest && duration > threshHold) {
            System.out.printf("PLEASE consider mark method [%s] with @SlowTest\n", testMethodName);
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        ExtensionContext.Store store = getStore(context);
        store.put(START_TIME, System.currentTimeMillis());
    }
}
