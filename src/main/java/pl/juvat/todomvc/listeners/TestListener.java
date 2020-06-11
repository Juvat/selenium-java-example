package pl.juvat.todomvc.listeners;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import pl.juvat.todomvc.core.TestCore;

/**
 * @author Kamil on 11.06.2020
 */
@Slf4j
public final class TestListener extends TestListenerAdapter {

    private final ScreenShooter screenShooter = ScreenShooter.getInstance();

    @Override
    public void onTestFailure(final ITestResult tr) {
        log.error("---- TEST FAILED ----", tr.getThrowable());
        screenShooter.addFullPageScreenshotToReport(tr.getInstance());
        Allure.addAttachment("Console Logs", collectBrowserConsoleLogs(tr.getInstance()));
    }

    @Override
    public void onTestSuccess(final ITestResult tr) {
        log.info("---- TEST PASSED -----");
    }

    private String collectBrowserConsoleLogs(final Object currentClass) {
        var builder = new StringBuilder();
        var driver = ((TestCore) currentClass).getDriver();
        var logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (var logEntry : logEntries) {
            builder.append(logEntry.getMessage());
            builder.append("\n=============================================\n");
        }
        return builder.toString();
    }

}
