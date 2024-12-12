package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;
import com.selenium.page.GoogleHomePage;
import com.selenium.page.GoogleResultPage;


public class googleTest {
	WebDriver driver;

	@BeforeMethod
	public void Precondicion(ITestContext context)  {
		String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
		String URL = context.getCurrentXmlTest().getParameter("Url");
		driver = DriverFactory.LevantarBrowser(driver, URL, browserName);

	}

	@AfterMethod
	public void Postcondicion() {
		DriverFactory.FinalizarBrowser(driver);
	}
	
	@DataProvider(name = "datos")
	public Object[][] createData() {
	return new Object[][] {
	 { "Shakira"},
	{ "Messi"},
	{ "Arjona"},
	{ "La Oreja de Van Gogh"}
	};
	}
	

	@Test(dataProvider = "datos", description = "Validar que las búsquedas en Google funcionan")
	public void ValidarGoogleSearch(String dato) throws Exception {
		Reporter.log("Página de Google cargada", true);
		GoogleHomePage homepage = PageFactory.initElements(driver, GoogleHomePage.class);

		Reporter.log("Verificar que la barra de búsqueda se esté mostrando", true);
		Assert.assertTrue(homepage.seVisualizaBarraBusqueda(), "No se visualizó la barra de búsqueda");


		Reporter.log("Ingresar el texto '" + dato + "'", true);
		homepage.enterSearchQuery(dato);

		Reporter.log("Presionar Enter para buscar", true);
		homepage.presionarEnterEnBarraBusqueda();

		Thread.sleep(3000);

		GoogleResultPage resultadoPageGoogle = PageFactory.initElements(driver, GoogleResultPage.class);

		Reporter.log("Verificar que el título del primer resultado contenga el texto buscado", true);
		String firstResult = resultadoPageGoogle.getFirstResultTitle();
		Reporter.log("Texto del primer resultado: " + firstResult, true); // Esto te permitirá ver qué texto contiene

		Assert.assertTrue(firstResult.contains(dato), "El texto"+ dato +"no se encontró en el primer resultado");

		Reporter.log("Verificar que las estadísticas de resultados estén visibles", true);
		Assert.assertNotNull(resultadoPageGoogle.getResultStats(), "No se visualizaron las estadísticas de resultados");
		
		  
	    Thread.sleep(500); 
	    Reporter.log("Espera");
	    resultadoPageGoogle.click3Result();
	    
	    
	
	    Thread.sleep(500);
	   
	    Assert.assertTrue(driver.getTitle().toLowerCase().contains(dato.toLowerCase()), 
	        "El título de la página no contiene" + dato);
	}
	
	
	@Test(description = "opcion1")
	public void BuscarYValidarOtraPaginaChrome() throws Exception {
		Reporter.log("Página de Google cargada", true);
		GoogleHomePage homepage = PageFactory.initElements(driver, GoogleHomePage.class);

		Reporter.log("Verificar que la barra de búsqueda se esté mostrando", true);
		Assert.assertTrue(homepage.seVisualizaBarraBusqueda(), "No se visualizó la barra de búsqueda");

		String dato = "Selenium";

		Reporter.log("Ingresar el texto '" + dato + "'", true);
		homepage.enterSearchQuery(dato);

		Reporter.log("Presionar Enter para buscar", true);
		homepage.presionarEnterEnBarraBusqueda();

		Thread.sleep(3000);

		GoogleResultPage resultadoPageGoogle = PageFactory.initElements(driver, GoogleResultPage.class);

		Reporter.log("Verificar que el título del primer resultado contenga el texto buscado", true);
		String firstResult = resultadoPageGoogle.getFirstResultTitle();
		Reporter.log("Texto del primer resultado: " + firstResult, true); // Esto te permitirá ver qué texto contiene

		Assert.assertTrue(firstResult.contains(dato), "El texto"+ dato +"no se encontró en el primer resultado");

		Reporter.log("Verificar que las estadísticas de resultados estén visibles", true);
		Assert.assertNotNull(resultadoPageGoogle.getResultStats(), "No se visualizaron las estadísticas de resultados");
		
		
	    Thread.sleep(500); 
	    Reporter.log("Espera");
	    resultadoPageGoogle.click3Result();
	    
	    
	    Thread.sleep(500);
	   
	    Assert.assertTrue(driver.getTitle().toLowerCase().contains(dato.toLowerCase()), 
	        "El título de la página no contiene" + dato);
	}

	
}