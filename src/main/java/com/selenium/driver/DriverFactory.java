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

public class DriverFactory {
	private enum browsers {
		EDGE, FIREFOX, CHROME
	}

	public static WebDriver LevantarBrowser(WebDriver driver, String URL, String browserName) {

//		String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
//		String URL = context.getCurrentXmlTest().getParameter("Url");

		switch (browsers.valueOf(browserName)) {
		case CHROME: // Using WebDriver
		{
			System.setProperty("webdriver.chrome.driver", "src\\resources\\chromedriver.exe");
			Reporter.log("Abro browser", true);
			driver = new ChromeDriver();
			break;
		}
		case FIREFOX:// Using WebDriver
		{
			System.setProperty("webdriver.gecko.driver", "src\\resources\\geckodriver.exe");
            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // Especificar la ubicación del binario de Firefox si no está en el PATH
            firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

            Reporter.log("Abriendo navegador Firefox");
            driver = new FirefoxDriver(firefoxOptions);
            break;
		}
		case EDGE:// Using WebDriver
		{
			System.setProperty("webdriver.edge.driver", "src/resources/msedgedriver.exe");
			driver = new EdgeDriver();
			Reporter.log("Abro browser", true);
			break;
		}

		default:
			Reporter.log("No selecciono ningun browser correcto, se le asignara Chrome", true);
			System.setProperty("webdriver.chrome.driver", "src/resources/msedgedriver.exe");
			Reporter.log("Abro browser", true);
			driver = new ChromeDriver();
			break;

		}
		driver.manage().window().maximize();
		driver.get(URL);
		return driver;

	}

	public static void FinalizarBrowser(WebDriver driver) {
		Reporter.log("Cerrando el browser", true);
		driver.quit();
		driver = null;
	}

}