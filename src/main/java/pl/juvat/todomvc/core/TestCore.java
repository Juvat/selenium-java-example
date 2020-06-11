package pl.juvat.todomvc.core;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import pl.juvat.todomvc.config.Config;
import pl.juvat.todomvc.listeners.TestListener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

/**
 * @author Kamil on 11.06.2020
 */
@Slf4j
@Listeners(TestListener.class)
public abstract class TestCore {

    @Getter
    private static String baseUrl;
    private static Capabilities capabilities;
    @Getter
    protected WebDriver driver;

    @BeforeSuite
    public void initTestSuite() throws IOException {
        WebDriverManager.chromedriver().setup();
        final var config = new Config();
        baseUrl = config.getProperty("siteUrl");
        capabilities = config.getCapabilities();
        allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                .put("Environment", config.getProperty("env"))
                .put("URL", baseUrl)
                .build(), "target/allure-results/");
        log.info("Running tests against: {}", baseUrl);
    }

    @BeforeClass
    public void initWebDriver() {
        if (driver == null) {
            driver = new ChromeDriver(setOptions(capabilities));
            setWindowSize();
            setTimeouts();
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private void setWindowSize() {
        driver.manage().window().maximize();
    }

    private void setTimeouts() {
        final var timeouts = driver.manage().timeouts();
        timeouts.pageLoadTimeout(Constants.WAIT_TIMEOUT, TimeUnit.SECONDS);
        timeouts.implicitlyWait(Constants.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    private ChromeOptions setOptions(final Capabilities capabilities) {
        final var options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        return options.merge(capabilities);
    }
}
