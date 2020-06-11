package pl.juvat.todomvc.web;

import lombok.AccessLevel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.juvat.todomvc.core.Constants;

/**
 * @author Kamil on 11.06.2020
 */
abstract class Web {

    @Getter(AccessLevel.PROTECTED)
    private static final int TIMEOUT = Constants.WAIT_TIMEOUT;
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    Web(final WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, TIMEOUT);
    }

}
