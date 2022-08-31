package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        return getDriver(null);
    }

    public static WebDriver getDriver(String browser) {
        if (drivers.get() == null) {
            if (browser == null) {
                browser = ConfigReader.getProperty("browser");
            }

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver());
                    break;
                case "headlessChrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    WebDriverManager.chromedriver().setup();
                    drivers.set(new ChromeDriver(chromeOptions));
                    break;
                default:
                    System.out.println("Invalid browser");
                    break;
            }
        }

        return drivers.get();
    }

    public static void quitDriver() {
        if (drivers.get() != null) {
            drivers.get().quit();
            drivers.remove();
        }
    }

}