package pl.juvat.todomvc.config;

import lombok.Cleanup;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author Kamil on 11.06.2020
 */
public final class Config {

    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private final Map<String, String> config;

    public Config() {
        config = YamlConfigLoader.getConfig();
    }

    public Capabilities getCapabilities() throws IOException {
        final var capabilitiesFile = config.get("capabilities");

        final var capsProps = new Properties();
        @Cleanup final InputStream inputStream = Config.class.getResourceAsStream(capabilitiesFile);
        capsProps.load(inputStream);

        String value;
        final var capabilities = new DesiredCapabilities();
        for (final var name : capsProps.stringPropertyNames()) {
            value = capsProps.getProperty(name);
            if (TRUE.equalsIgnoreCase(value) || FALSE.equalsIgnoreCase(value)) {
                capabilities.setCapability(name, Boolean.valueOf(value));
            } else if (value.startsWith("file:")) {
                capabilities
                        .setCapability(name, createNewFile(value));
            } else {
                capabilities.setCapability(name, value);
            }
        }
        return capabilities;
    }

    private String createNewFile(final String value) throws IOException {
        return new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath();
    }

    public String getProperty(final String name) {
        return config.get(name);
    }

}
