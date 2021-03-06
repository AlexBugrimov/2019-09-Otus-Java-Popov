package ru.otus.hw05.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/** "Запускалка теста", которая принимает класс с тестами, анализирует аннотации и выполняет методы. */
public class TestRunner {

    public static void run(Class<?> clazz) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Start tests for class: " + clazz.getName());
        // переменные для сбора статистики
        int passedTests = 0;
        // анализ методов
        List<Method> methodsBefore = TestHelper.getAnnotatedMethods(clazz, Before.class);
        List<Method> methodsAfter = TestHelper.getAnnotatedMethods(clazz, After.class);
        List<Method> methodsTest = TestHelper.getAnnotatedMethods(clazz, Test.class);
        // прогоняем тесты
        for(Method test: methodsTest) {
            Object instance = clazz.getConstructor().newInstance();
            String errorMessage = null;
            try {
                TestHelper.invokeMethods(instance, methodsBefore);
                test.invoke(instance);
                passedTests++;
            } catch (Exception e) {
                errorMessage = e.getCause().getMessage();
            } finally {
                TestHelper.invokeMethods(instance, methodsAfter);
            }
            System.out.println(String.format("Test: %s - %s",
                    TestHelper.getTestName(test), errorMessage==null ? "PASSED" : "FAILED"));
            if (errorMessage!=null) {
                System.out.println(errorMessage);
            }
        }
        // печатаем статистику
        System.out.println(String.format("All tests: %d, passed: %d, failed: %d",
                methodsTest.size(),passedTests,methodsTest.size()-passedTests));
    }
}
