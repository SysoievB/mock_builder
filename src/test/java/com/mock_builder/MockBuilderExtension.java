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

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MockBuilder.class)) {
                MockBuilder mockBuilderAnnotation = method.getAnnotation(MockBuilder.class);
                String builderMethodName = mockBuilderAnnotation.builderMethodName();
                Builder builderAnnotation = method.getAnnotation(Builder.class);
                String builderMethodNameLombok = builderAnnotation.builderMethodName();
                if (!builderMethodName.isBlank()) {
                    //todo  to set value from builderMethodName to builderMethodNameLombok here
                }
            }
        }
    }
}
