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
import com.selenium.page.CompraGamerHomePage;
import com.selenium.page.GoogleHomePage;
import com.selenium.page.GoogleResultPage;

/**Ramiro 
Fecha: 13/12/24
Hora: 10:08
Motivo: Agrego base para los Tests de Compra Gamer y test ValidarRegistrarCompraGamer 
**/

public class CompraGamerTest {
	WebDriver driver;

	@BeforeMethod
	public void Precondicion(ITestContext context) {
		String navegador = context.getCurrentXmlTest().getParameter("NombreNavegador");
		String url = context.getCurrentXmlTest().getParameter("Url");
		driver = DriverFactory.LevantarBrowser(driver, url, navegador);
	}

	@AfterMethod
	public void Postcondicion() {
		DriverFactory.FinalizarBrowser(driver);
	}

	@DataProvider(name = "datos")
	public Object[][] createData() {
		return new Object[][] {
				{ "Juan3", "P�rez3", "juan3@test.com", "11", "12345678", "Probando1234", "Probando1234" },
				{ "Ana1", "G�mez1", "ana1.gomez@test.com", "15", "87654321", "Contrase�a123", "Contrase�a123" },
				{ "Leo1", "Messi1", "leoMessi@test.com", "15", "266434123", "MessiCrack123", "MessiCrack123" }, };
	}

	@Test(dataProvider = "datos", description = "Validar que el Registrar Usuario por Compra Gamer funcione")

	public void ValidarRegistrarCompraGamer(String nombre, String apellido, String email, String codArea,
			String numTelefono, String contrasenia, String repContrasenia) throws Exception {

		Reporter.log("Cargando la p�gina de CompraGamer", true);
		// Inicializar la p�gina de inicio
		CompraGamerHomePage homepage = PageFactory.initElements(driver, CompraGamerHomePage.class);

		Reporter.log("Validar que el �cono Persona Ingresar est� visible", true);
		Assert.assertTrue(homepage.isIconPersonaVisible(), "El �cono no esta visible");

		// Clickear �cono Persona Ingresar
		homepage.clickPersonaIcon();

		// Clickear el bot�n "Crear cuenta"
		homepage.clickCrearCuentaButton();

		// Registrar una cuenta
		Reporter.log("Registrando una nueva cuenta", true);
		homepage.registrarCuenta(nombre, apellido, email, codArea, numTelefono, contrasenia, repContrasenia);

		// Validar y hacer clic en el bot�n "Registrarme"
		homepage.clickRegistrarmeButton();

		Reporter.log("Registro completado exitosamente", true);

		// Verificar que aparece el mensaje de �xito
		boolean mensajeExitoVisible = homepage.verificarMensajeExito(1); // Timeout de 1 segundos
		Assert.assertTrue(mensajeExitoVisible, "El mensaje de �xito no apareci� despu�s del registro.");
		Reporter.log("El usuario se registr� correctamente y el mensaje se mostr�.", true);
	}
}
