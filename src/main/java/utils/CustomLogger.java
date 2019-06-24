package utils;

import config.DriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CustomLogger {
    private static final Logger LOGGER;
    public final static String EMPTY_STRING = "%ПУСТОЕ ЗНАЧЕНИЕ%";
    public final static String NON_EMPTY_STRING = "%НЕПУСТОЕ ЗНАЧЕНИЕ%";
    public final static String DISPLAYED = "%ОТОБРАЖАЕТСЯ%";
    public final static String NOT_DISPLAYED = "%НЕ ОТОБРАЖАЕТСЯ%";

    static {
        LOGGER = LoggerFactory.getLogger(CustomLogger.class);
    }

    @Step("{0}")
    public static void info(String message) {
        LOGGER.info(message);
    }

    @Step("Получена ошибка {0}")
    public static void warn(String message) {
        LOGGER.warn(message);
        attachScreenshot();
        attachPageSource();
    }

    @Step("{0}")
    public static void fail(String message) {
        LOGGER.error(message);
        attachScreenshot();
        attachPageSource();
        throw new AssertionError(message);
    }

    @Step("{0}")
    public static void fail(String message, Throwable e) {
        LOGGER.error(message, e);
        attachScreenshot();
        attachPageSource();
        throw new AssertionError(message, e);
    }

    public static void equals(String paramText, Object checked, Object expected) {
        if (EMPTY_STRING.equals(expected)) {
            if (checked == null || checked.equals("")) {
                info(String.format("Элемент '%s' не имеет текста", paramText));
            } else {
                warn(String.format("Элемент '%s' неожиданно имеет текст, зафиксировано значение '%s'", paramText,
                        checked));
            }
            return;
        }

        if (NON_EMPTY_STRING.equals(expected)) {
            if (checked == null || checked.equals("")) {
                warn(String.format("Элемент '%s' не имеет текста", paramText));
            } else {
                info(String.format("Элемент '%s' неожиданно имеет текст, зафиксировано значение '%s'", paramText,
                        checked));
            }
            return;
        }

        if (DISPLAYED.equals(expected)) {
            if ((boolean) checked) {
                info(String.format("Элемент '%s' отображается", paramText));
            } else {
                fail(String.format("Элемент '%s' не отображается", paramText));
            }
            return;
        }

        if (NOT_DISPLAYED.equals(expected)) {
            if ((boolean) checked) {
                fail(String.format("Элемент '%s' отображается", paramText));
            } else {
                info(String.format("Элемент '%s' не отображается", paramText));
            }
            return;
        }

        if (Objects.equals(checked, expected)) {
            info(String.format("Параметр '%s' ожидаемо равен '%s'", paramText, String.valueOf(checked)));

        } else {
            warn(String.format("Параметр '%s' равен '%s', ожидалось: '%s'", paramText, String.valueOf(checked),
                            String.valueOf(expected)));
        }

    }

    @Attachment(value = "Page screenshot", type = "image/jpg")
    public static byte[] attachScreenshot() {
        try {
            return ((TakesScreenshot) DriverManager.get()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    @Attachment(value = "Page source", type = "text/xml")
    public static byte[] attachPageSource() {
        try {
            return DriverManager.get().getPageSource().getBytes();
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
