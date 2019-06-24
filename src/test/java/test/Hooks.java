package test;

import config.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.CustomLogger;

import java.lang.reflect.Method;

public class Hooks {

    private static final String APP_URL = "http://autotest-b8ns9mw7.bfg-soft.ru/";

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp(String browser) {
        DriverManager.setUp(browser);
        DriverManager.get().get(APP_URL);
    }

    @BeforeMethod
    public void logTestStart(Method method) {
        Test annotation = method.getDeclaredAnnotation(Test.class);
        CustomLogger.info(String.format("Начало выполнение теста '%s'", annotation.testName()));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.destroySession();
    }

    @AfterMethod
    public void logTestFinished(Method method) {
        Test annotation = method.getDeclaredAnnotation(Test.class);
        CustomLogger.info(String.format("Закончено выполнение теста '%s'", annotation.testName()));
    }
}
