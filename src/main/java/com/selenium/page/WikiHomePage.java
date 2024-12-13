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

	
	/**Francisco 
	Fecha: 13/12/24
	Hora: 10:40
	Motivo: Modifico metodo IngresarDatoCajaBusqueda para darle mas robustez
	**/
	public void IngresarDatoCajaBusqueda(String dato) {
	    try {
	        Reporter.log("Comprobando que la caja de búsqueda esté visible...");
	        if (!caja.isDisplayed()) {
	            throw new AssertionError("La caja de búsqueda no está visible.");
	        }
	        Reporter.log("Caja de búsqueda visible. Procediendo a interactuar con ella.");

	        Reporter.log("Limpiando el contenido previo de la caja de búsqueda...");
	        caja.clear();

	        Reporter.log("Ingresando el dato: " + dato);
	        caja.sendKeys(dato);

	        Reporter.log("Presionando la tecla ENTER para buscar...");
	        caja.sendKeys(Keys.ENTER);

	    } catch (Exception e) {
	        Reporter.log("Error al intentar interactuar con la caja de búsqueda: " + e.getMessage());
	        throw new RuntimeException("Error al ingresar el dato en la caja de búsqueda", e);
	    }
	}	
}