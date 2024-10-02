package com.mock_builder;

import lombok.Builder;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class MockBuilderExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Object testInstance = context.getRequiredTestInstance();
        Class<?> testClass = testInstance.getClass();

        /*for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MockBuilder.class)) {
                //set during runtime
                MockBuilder annotation = method.getAnnotation(MockBuilder.class);
                String builderMethodName = annotation.builderMethodName();
                //builder set during compile-time(SOURCE)
                Builder builder = method.getAnnotation(Builder.class);
                String builderMethodNameLombok = builder.builderMethodName();

                if (!builderMethodName.isEmpty()) {
                    builderMethodNameLombok = builderMethodName;
                    invokeBuilderMethod(testInstance, builderMethodNameLombok);
                }
            }
        }*/
    }

    private void invokeBuilderMethod(Object testInstance, String builderMethodName) throws Exception {
        Method builderMethod = testInstance.getClass().getMethod(builderMethodName);
        builderMethod.setAccessible(true);
        builderMethod.invoke(testInstance);
    }
}
