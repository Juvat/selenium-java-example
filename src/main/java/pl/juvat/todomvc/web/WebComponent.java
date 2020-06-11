package pl.juvat.todomvc.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * @author Kamil on 11.06.2020
 */
public abstract class WebComponent extends Web {

    private final WebElement parent;

    public WebComponent(final WebDriver driver, final WebElement parent) {
        super(driver);
        this.parent = parent;
        initWebElements();
    }

    private void initWebElements() {
        final ElementLocatorFactory factory = new AjaxElementLocatorFactory(parent, getTIMEOUT());
        PageFactory.initElements(factory, this);
    }

}
