package com.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;
import com.selenium.page.CompraGamerCartPageDami;
import com.selenium.page.CompraGamerHomePageDami;
import com.selenium.page.CompraGamerResultPageDami;

public class CompraGamerTestDami {
	
	WebDriver driver;

	@BeforeMethod
	public void Precondicion(ITestContext testContext) {
		Reporter.log("Dirigirse a https://compragamer.com/");
		String url = testContext.getCurrentXmlTest().getParameter("Url");
		String navegador = testContext.getCurrentXmlTest().getParameter("NombreNavegador");
		driver = DriverFactory.LevantarBrowser(driver, url, navegador);
	}

	@AfterMethod
	public void Postcondicion() {
		DriverFactory.FinalizarBrowser(driver);
	}
	
	@DataProvider(name = "datos")
	public Object[][] createData(){
		return new Object[][] {
			{"pruebatestingfinal@gmail.com", "PruebaTesting123", "Ryzen 7 5700"},
		};
	}
	
	@Test(dataProvider = "datos", description = "Validar las búsquedas en CompraGamer")
	public void ValidarCompraGamer(String mail, String password, String dato) throws Exception {
		
		Reporter.log("Caso de prueba: Realizar un pedido de " + dato, true);
		CompraGamerHomePageDami homePage = PageFactory.initElements(driver, CompraGamerHomePageDami.class);

		// Iniciar sesión
		
	    Reporter.log("Iniciar sesión con el usuario recién creado");
	    Reporter.log("Validar que el Ícono Persona Ingresar esté visible", true);
		Assert.assertTrue(homePage.isIconPersonaVisible(), "El ícono no está visible");
		homePage.clickPersonaIcon();
	    homePage.ingresarMail(mail);
	    homePage.clickContinuar();
	    homePage.ingresarPassword(password);
	    homePage.clickIngresar();
		
		// Buscar producto
		
	    Reporter.log("Verificar que el searchInput se esté mostrando", true);
		Assert.assertTrue((homePage.waitForSearchInput()), "No se visualizó el txtBusqueda");
		
		Reporter.log("Ingresar el texto " + dato, true);
		homePage.BuscarProducto(dato);
		
		CompraGamerResultPageDami resultPage = PageFactory.initElements(driver, CompraGamerResultPageDami.class);
		
		Reporter.log("Clickear en el botón agregar a carrito", true);
		resultPage.clickAgregarCarritoButton();
		
		Reporter.log("Ir al carrito", true);
		resultPage.clickCarritoButton();
		
		CompraGamerCartPageDami cartPage = PageFactory.initElements(driver, CompraGamerCartPageDami.class);
		
		Reporter.log("Seleccionar entrega a domiclio", true);
		cartPage.clickDomicilioButton();
		
		Reporter.log("Confirmar la compra haciendo click en 'COMPRAR'", true);
		cartPage.clickComprarButton();
		
		cartPage.validarErrorFaltanDatos();
		Reporter.log("No se permite realizar la compra por falta de cuenta de facturación", true);
		
	}
}
