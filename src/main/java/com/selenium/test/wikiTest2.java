package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;
import com.selenium.page.WikiHomePage;
import com.selenium.page.WikiResultPage;

public class wikiTest2 {
	WebDriver driver;

	@BeforeMethod
	public void Precondicion() {
		String url = "http://wikipedia.org";
		String navegador = "CHROME";
		//String navegador = "FIREFOX";
//		String navegador = "EDGE";
		driver = DriverFactory.LevantarBrowser(driver, url, navegador);
	}

	@AfterMethod
	public void Postcondicion() {
		DriverFactory.FinalizarBrowser(driver);
	}

	@Test(description = "Validar que las busquedas en Wikipedia funcionan")
	public void ValidarWikipedia2() throws Exception {
		Reporter.log("Caso de prueba Fanny Montoya y Mariana", true);
		Reporter.log("Localizar la caja de busqueda del home de wiki", true);
		WikiHomePage homepage = PageFactory.initElements(driver, WikiHomePage.class);

		Reporter.log("Verificar que la caja de busqueda se este mostrando", true);
		Assert.assertTrue((homepage.SeVisualizaCaja()), "No se visualizo la caja");

		String dato = "Messi";

		Reporter.log("Ingresar el texto " + dato, true);
		homepage.IngresarDatoCajaBusqueda(dato);

		WikiResultPage resultadoPage = PageFactory.initElements(driver, WikiResultPage.class);

		Assert.assertTrue((resultadoPage.getTitulo(dato).contains(dato)), "no se encontro " + dato);
	}
}