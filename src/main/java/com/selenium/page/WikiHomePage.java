package com.selenium.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;
import org.testng.Assert;

public class WikiHomePage {
	
	WebDriver driver;

	//Contructor que en este caso utiliza el driver enviado por parametro/

	public WikiHomePage(WebDriver ldriver) {
		driver = ldriver;
	}

	// *** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH

	@FindBy(id = "js-link-box-es")
	private WebElement idiomaEspaniol;
	@FindBy(id = "searchInput")
	private WebElement caja;
	@FindBy(id = "searchLanguage")
	private WebElement languageCombo;
	
	
	public void ClickEnEspaniol() throws Exception {
		idiomaEspaniol.click();

	}
	
	public String getCaja() {
		Reporter.log("Obtiene el contenido de la caja de busqueda", true);
		return caja.getText();
	}
	public boolean SeVisualizaCaja() {
		Reporter.log("Validar que exista la caja de busqueda", true);
		return caja.isDisplayed();
	}

	
	
	public void IngresarDatoCajaBusqueda ( String dato)
	{
		Reporter.log("Localizar y comprobar que la caja de busqueda se muestra", true);
		Assert.assertTrue((caja.isDisplayed()), "La caja de busqueda no se visualiza");
		Reporter.log("Ingresar la palabra " + dato, true);
		caja.sendKeys(dato);
		Reporter.log("Presionar Enter", true);
		caja.sendKeys(Keys.ENTER);
	}
	
}