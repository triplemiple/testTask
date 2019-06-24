package config;

import exceptions.DriverNotInitializedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class DriverManager {

    private static ThreadLocal<WebDriver> driverContainer = new ThreadLocal<>();

    private static Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static Marker marker = MarkerFactory.getMarker("DRIVER_MANAGER");

    private final static String CHROME_DRIVER_PATH = "src/test/resources/chromedriver.exe";
    private final static String FIREFOX_DRIVER_PATH = "src/test/resources/geckodriver.exe";

    private final static long TIMEOUT = 10;

    public static WebDriver setUp(String browser) {
        logger.debug(marker, "Obtaining driver for {}", browser);
        WebDriver driver = driverContainer.get();
        if (driver != null) {
            logger.debug(marker, "Driver present, returning: {}", driver);
            return driver;
        }

        logger.debug(marker, "Driver doesn't present creating a new one: browser: \"{}\"", browser);

        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);
                driver = new FirefoxDriver();
                break;
        }

        driverContainer.set(driver);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driverContainer.get();
    }

    public synchronized static WebDriver get() {
        return Optional.ofNullable(driverContainer.get()).orElseThrow(DriverNotInitializedException::new);
    }

    public static void destroySession() {
        driverContainer.get().quit();
        driverContainer.set(null);
    }
}
