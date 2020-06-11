package pl.juvat.todomvc.browser;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Kamil on 11.06.2020
 */
public class BrowserLocalStorage implements LocalStorage {

    private final ExecuteMethod executeMethod;

    public BrowserLocalStorage(final WebDriver driver) {
        executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
    }

    @Override
    public String getItem(final String key) {
        final Map<String, String> args = ImmutableMap.of("key", key);
        return (String) executeMethod.execute(DriverCommand.GET_LOCAL_STORAGE_ITEM, args);
    }

    @Override
    public Set<String> keySet() {
        @SuppressWarnings("unchecked") final Collection<String> result = (Collection<String>) executeMethod
                .execute(DriverCommand.GET_LOCAL_STORAGE_KEYS, null);
        return new HashSet<>(result);
    }

    @Override
    public void setItem(final String key, final String value) {
        final Map<String, String> args = ImmutableMap.of("key", key, "value", value);
        executeMethod.execute(DriverCommand.SET_LOCAL_STORAGE_ITEM, args);
    }

    @Override
    public String removeItem(final String key) {
        final Map<String, String> args = ImmutableMap.of("key", key);
        return (String) executeMethod.execute(DriverCommand.REMOVE_LOCAL_STORAGE_ITEM, args);
    }

    @Override
    public void clear() {
        executeMethod.execute(DriverCommand.CLEAR_LOCAL_STORAGE, null);
    }

    @Override
    public int size() {
        final Object response = executeMethod.execute(DriverCommand.GET_LOCAL_STORAGE_SIZE, null);
        return Integer.parseInt(response.toString());
    }

}
