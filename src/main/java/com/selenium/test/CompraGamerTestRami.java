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
import org.testng.asserts.SoftAssert;

import com.selenium.driver.DriverFactory;
import com.selenium.page.CompraGamerAccountPage;
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

	@Test(dataProvider = "datos", description = "Validar registro, inicio de sesión y redirección a página de Mi Cuenta con datos vacíos")
	public void ValidarRegistroInicioSesionYDatosVacios(String nombre, String apellido, String email, String codArea,
	        String numTelefono, String contrasenia, String repContrasenia) throws Exception {

	    Reporter.log("Cargando la página de Compra Gamer", true);
	    CompraGamerHomePageRami homepage = PageFactory.initElements(driver, CompraGamerHomePageRami.class);

	    // Registro del usuario
	    homepage.clickPersonaIcon();
	    homepage.clickCrearCuentaButton();
	    homepage.registrarCuenta(nombre, apellido, email, codArea, numTelefono, contrasenia, repContrasenia);
	    homepage.clickRegistrarmeButton();

	    Assert.assertTrue(homepage.verificarMensajeExito(5), "El mensaje de éxito no apareció.");
	    homepage.clickAceptarButton();

	    // Inicio de sesión
	    homepage.iniciarSesion(email, contrasenia);

	    // Navegación a 'Mi Cuenta'
	    homepage.clickIconoPersona();
	    CompraGamerAccountPage accountPage = homepage.clickMiCuenta();

	    // Validar que la página de Mi Cuenta se ha cargado
	    Assert.assertTrue(accountPage.isAccountPageLoaded(), "La página 'Mi Cuenta' no se cargó correctamente.");

	    // **Uso de softAssert
	    SoftAssert softAssert = new SoftAssert();

	    Reporter.log("Validando los mensajes 'No tenés...' en la página de Mi Cuenta", true);

	    softAssert.assertTrue(accountPage.isMensajeSinFacturasVisible(), 
	            "El mensaje 'No tenés facturas cargadas' no está visible.");

	    softAssert.assertTrue(accountPage.isMensajeSinDomiciliosVisible(), 
	            "El mensaje 'No tenés domicilios cargados' no está visible.");

	    softAssert.assertTrue(accountPage.isMensajeSinCuentasVisible(), 
	            "El mensaje 'No tenés cuentas cargadas' no está visible.");

	    softAssert.assertTrue(accountPage.isMensajeSinReservasVisible(), 
	            "El mensaje 'Aun no tenés reservas hechas' no está visible.");

	    Reporter.log("Se validaron los mensajes de 'No tenés...', reportando fallas si las hay", true);

	    // Al final del test, verificar todas las aserciones
	    softAssert.assertAll();
	}


}
