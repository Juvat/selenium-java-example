package pl.juvat.todomvc.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * @author Kamil on 11.06.2020
 */
public abstract class WebPage extends Web {

    public WebPage(final WebDriver driver) {
        super(driver);
        initWebElements();
    }

    private void initWebElements() {
        final ElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, getTIMEOUT());
        PageFactory.initElements(factory, this);
    }

}
