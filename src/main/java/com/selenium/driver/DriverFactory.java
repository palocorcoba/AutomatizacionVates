package com.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class DriverFactory { private enum browsers {
    EDGE, FIREFOX, CHROME
}

public static WebDriver LevantarBrowser(WebDriver driver, String URL, String browserName) {
    if (browserName == null || browserName.trim().isEmpty()) {
        Reporter.log("El par�metro 'browserName' es nulo o vac�o. Se utilizar� el navegador por defecto: CHROME");
        browserName = "CHROME";
    }

    try {
        switch (browsers.valueOf(browserName.toUpperCase())) {
            case CHROME: 
                System.setProperty("webdriver.chrome.driver", "src\\Resources\\chromedriver.exe");
                Reporter.log("Abriendo navegador Chrome");
                driver = new ChromeDriver();
                break;

            case FIREFOX: 
                System.setProperty("webdriver.gecko.driver", "src\\Resources\\geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                Reporter.log("Abriendo navegador Firefox");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case EDGE:
                System.setProperty("webdriver.edge.driver", "src\\Resources\\msedgedriver.exe");
                Reporter.log("Abriendo navegador Edge");
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("El navegador especificado no est� soportado: " + browserName);
        }
    } catch (IllegalArgumentException e) {
        Reporter.log("El navegador especificado no es v�lido. Se usar� Chrome por defecto.");
        System.setProperty("webdriver.chrome.driver", "src\\Resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    driver.manage().window().maximize();
    driver.get(URL);
    return driver;
}

public static void FinalizarBrowser(WebDriver driver) {
    if (driver != null) {
        Reporter.log("Cerrando el navegador");
        driver.quit();
    }
}

}