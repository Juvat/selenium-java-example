package pl.juvat.todomvc.config;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author Kamil on 11.06.2020
 */
@Slf4j
public final class YamlConfigLoader {

    static HashMap<String, String> getConfig() {
        try (final InputStream inputStream = YamlConfigLoader.class.getResourceAsStream("/config.yml")) {
            return new Yaml().load(inputStream);
        } catch (final IOException e) {
            log.error("Exception thrown when trying to create config", e);
        }
        return null;
    }

}
