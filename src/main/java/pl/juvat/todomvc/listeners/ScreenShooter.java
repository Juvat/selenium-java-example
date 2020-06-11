package pl.juvat.todomvc.listeners;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.qameta.allure.Allure;
import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import pl.juvat.todomvc.core.TestCore;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Kamil on 11.06.2020
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenShooter {

    private static ScreenShooter instance;

    @Synchronized
    public static ScreenShooter getInstance() {
        if (instance == null) {
            instance = new ScreenShooter();
        }
        return instance;
    }

    public void addFullPageScreenshotToReport(final Object currentClass) {
        try {
            log.info("Taking full page screenshot for: {}", currentClass.toString());
            final var driver = ((TestCore) currentClass).getDriver();
            final var image = Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE_CHROME).getImage();

            @Cleanup var outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            @Cleanup var inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            Allure.addAttachment("Page Screenshot", inputStream);
            Allure.addAttachment("URL", driver.getCurrentUrl());
        } catch (IOException e) {
            log.error("Error writing image", e);
        }
    }

}
