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
import com.selenium.page.CompraGamerHomePageRami;

/**Ramiro 
Fecha: 13/12/24
Hora: 10:08
Motivo: Agrego base para los Tests de Compra Gamer y test ValidarRegistrarCompraGamer 
**/

public class CompraGamerTestRami {
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
				{ "Juan3", "Perez3", "juan3@test.com", "11", "12345678", "Probando1234", "Probando1234"}
	};
	}

	@Test(dataProvider = "datos", description = "Validar que el Registrar Usuario por Compra Gamer funcione")

	public void ValidarRegistrarCompraGamer(String nombre, String apellido, String email, String codArea,
			String numTelefono, String contrasenia, String repContrasenia) throws Exception {

		Reporter.log("Cargando la pagina de CompraGamer", true);
		// Inicializar la pagina de inicio
		CompraGamerHomePageRami homepage = PageFactory.initElements(driver, CompraGamerHomePageRami.class);

		Reporter.log("Validar que el ícono Persona Ingresar esta visible", true);
		Assert.assertTrue(homepage.isIconPersonaVisible(), "El icono no esta visible");

		// Clickear icono Persona Ingresar
		homepage.clickPersonaIcon();

		// Clickear el boton "Crear cuenta"
		homepage.clickCrearCuentaButton();

		// Registrar una cuenta
		Reporter.log("Registrando una nueva cuenta", true);
		homepage.registrarCuenta(nombre, apellido, email, codArea, numTelefono, contrasenia, repContrasenia);

		// Validar y hacer clic en el boton "Registrarme"
		homepage.clickRegistrarmeButton();

		Reporter.log("Registro completado exitosamente", true);

		// Verificar que aparece el mensaje de exito
		boolean mensajeExitoVisible = homepage.verificarMensajeExito(2); // Timeout de 2 segundos
		Assert.assertTrue(mensajeExitoVisible, "El mensaje de exito no aparecio despues del registro.");
		Reporter.log("El usuario se registro correctamente y el mensaje se mostro", true);
	}
}
