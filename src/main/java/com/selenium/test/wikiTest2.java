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
import com.selenium.page.WikiHomePage;
import com.selenium.page.WikiResultPage;

public class wikiTest2 {
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
	        {"Messi"},
	        {"Selenium"}
	    };
	}

	@Test(dataProvider = "datos", description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarWikipedia2(String dato) throws Exception {
		Reporter.log("Caso de prueba", true);
		Reporter.log("Localizar la caja de busqueda del home de wiki", true);
		WikiHomePage homepage = PageFactory.initElements(driver, WikiHomePage.class);

		Reporter.log("Verificar que la caja de busqueda se este mostrando", true);
		Assert.assertTrue((homepage.SeVisualizaCaja()), "No se visualizo la caja");

		Reporter.log("Ingresar el texto " + dato, true);
		homepage.IngresarDatoCajaBusqueda(dato);

		WikiResultPage resultadoPage = PageFactory.initElements(driver, WikiResultPage.class);

		Assert.assertTrue((resultadoPage.getTitulo(dato).contains(dato)), "no se encontro " + dato);
	}
	/**Ramiro 
	 Fecha: 13/12/24
	 Hora: 8:48
	 **/
	@Test(description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarBusquedaWikipedia() throws Exception {

		Reporter.log("P�gina de Wikipedia cargada", true);
		WebElement searchInput = driver.findElement(By.id("searchInput"));
		Assert.assertTrue(searchInput.isDisplayed());
		Reporter.log("Campo de b�squeda encontrado", true);
		searchInput.sendKeys("Selenium");
		Reporter.log("Texto 'Selenium' ingresado en el campo de b�squeda", true);

		searchInput.submit();
		Reporter.log("Formulario de b�squeda enviado", true);
		
		Thread.sleep(5000);
		WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
		Reporter.log("Texto encontrado en la p�gina: " + tituloResultado.getText(), true);

		System.out.println("Texto encontrado " + tituloResultado.getText());
		Assert.assertTrue(tituloResultado.isDisplayed());
		Reporter.log("T�tulo del resultado de b�squeda es visible", true);
		Reporter.log("Prueba en Chrome finalizada", true);
		driver.close();
	}

	@Test(dataProvider = "datos", description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarWikipediaRamiro(String dato) throws Exception {
		Reporter.log("Caso de prueba Fanny Montoya", true);
		Reporter.log("Localizar la caja de busqueda del home de wiki", true);
		WikiHomePage homepage = PageFactory.initElements(driver, WikiHomePage.class);

		Reporter.log("Verificar que la caja de busqueda se este mostrando", true);
		Assert.assertTrue((homepage.SeVisualizaCaja()), "No se visualizo la caja");

		Reporter.log("Ingresar el texto " + dato, true);
		homepage.IngresarDatoCajaBusqueda(dato);

		WikiResultPage resultadoPage = PageFactory.initElements(driver, WikiResultPage.class);

		Assert.assertTrue((resultadoPage.getTitulo(dato).contains(dato)), "no se encontro " + dato);
	}
	
	
}