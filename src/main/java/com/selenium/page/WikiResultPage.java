package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WikiResultPage {
	WebDriver driver;
	WebDriverWait wait;

	/* Contructor que en este caso utiliza el driver enviado por parametro */

	public WikiResultPage(WebDriver ldriver) {
		driver = ldriver;
	}
//*** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH

	@FindBy(id = "firstHeading")
	private WebElement lblTitulo;

	@FindBy(id = "searchInput")
	private WebElement txtCaja;

	@FindBy(xpath = "//div/div[1]/p[1]")
	private WebElement lblparrafo;

	@FindBy(id = "n-randompage")
	private WebElement linkPagAleat;

	@FindBy(xpath = "//li[@id='n-help']/a[1]/span[1]")
	private WebElement btnayuda;

	public String getTitulo(String palabra) throws Exception {
		Thread.sleep(2000);
		Reporter.log(" el titulo que se muestra es" + lblTitulo.getText(), true);
		return lblTitulo.getText();
	}
}
