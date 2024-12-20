package com.selenium.page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import Utils.Utiles;

public class CompraGamerHomePageRami {
	WebDriver driver;

	/* Constructor que en este caso utiliza el driver enviado por parámetro */

	public CompraGamerHomePageRami(WebDriver ldriver) {
		driver = ldriver;
	}

	// ***** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH

	// Identificar elementos por su localizador
	
	@FindBy(xpath = "//span[normalize-space()='person_outline']")
	private WebElement personaIconIngresar;

	@FindBy(xpath = "//span[normalize-space()='Crear cuenta']")
	private WebElement crearCuentaButton;

	// Elementos de los campos para registrar una cuenta

	@FindBy(xpath = "//input[@id='mat-input-2']")
	private WebElement inputNombre;

	@FindBy(xpath = "//input[@id='mat-input-3']")
	private WebElement inputApellido;

	@FindBy(xpath = "//input[@id='mat-input-4']")
	private WebElement inputEmail;

	@FindBy(xpath = "//input[@id='mat-input-5']")
	private WebElement inputCodArea;

	@FindBy(xpath = "//input[@id='mat-input-6']")
	private WebElement inputNumTelefono;

	@FindBy(xpath = "//input[@id='mat-input-7']")
	private WebElement inputContrasenia;

	@FindBy(xpath = "//input[@id='mat-input-8']")
	private WebElement inputRepContrasenia;

	@FindBy(xpath = "//span[normalize-space()='Registrarme']")
	private WebElement RegistrarmeButton;

	@FindBy(xpath = "//p[contains(text(),'Tu cuenta se ha creado con éxito. Por favor iniciá')]")
	private WebElement mensajeExito;
	
	@FindBy(xpath = "//span[normalize-space()='Aceptar']")
	private WebElement AceptarButton;
	
	// Elementos para iniciar sesión
	
	//input[@id='mat-input-25']
	@FindBy(xpath = "//input[@id='mat-input-9']")
	private WebElement inputLoginEmail;
	
	@FindBy(xpath = "//span[normalize-space()='Continuar']")
	private WebElement ContinuarButton;
	
	@FindBy(xpath = "//input[@id='mat-input-10']")
	private WebElement inputLoginContrasenia;
	
	@FindBy(xpath = "//span[normalize-space()='Ingresar']")
	private WebElement IniciarSesionButton;
	
	@FindBy(xpath = "//mat-icon[normalize-space()='person_outline']")
	private WebElement personaIconLogin;
	
	//mat-icon[normalize-space()='exit_to_app']
	//span[normalize-space()='Cerrar sesión']
	@FindBy(xpath = "//mat-icon[normalize-space()='exit_to_app']")
	private WebElement cerrarSesionButton;
	
	@FindBy(xpath = "//button[@role='menuitem']//mat-icon[@role='img'][normalize-space()='person_outline']")
	private WebElement MiCuentaButton;
	
	// Métodos para esperas dinámicas
	private void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Métodos para interactuar con los elementos
	public boolean isIconPersonaVisible() {
		Reporter.log("Validar que el icono Persona Ingresar se visualiza", true);
		return personaIconIngresar.isDisplayed();
	}

	public void clickPersonaIcon() {
		Reporter.log("Localizar y comprobar que el icono Persona Ingresar se muestra", true);
		Assert.assertTrue(isIconPersonaVisible(), "La caja de búsqueda no está visible");
		waitForElementToBeVisible(personaIconIngresar, 10); 
		Reporter.log("Hacemos click en el ícono", true);
		personaIconIngresar.click();
	}

	public void clickCrearCuentaButton() {
		Reporter.log("Validar que el botón 'Crear cuenta' está visible y hacer clic en él", true);
		Assert.assertTrue(crearCuentaButton.isDisplayed(), "El botón 'Crear cuenta' no está visible");
		waitForElementToBeClickable(crearCuentaButton, 10);
		crearCuentaButton.click();
		Reporter.log("Se hizo clic en el botón 'Crear cuenta'", true);
	}

	public void registrarCuenta(String nombre, String apellido, String email, String codArea, String numTelefono,
			String contrasenia, String repContrasenia) {
		Reporter.log("Ingresando datos para el registro", true);

		Reporter.log("Ingresar nombre: " + nombre, true);
		nombre = nombre + Utiles.getFechaActual();
		inputNombre.sendKeys(nombre);

		Reporter.log("Ingresar apellido: " + apellido, true);
		apellido = apellido + Utiles.getFechaActual();
		inputApellido.sendKeys(apellido);

		Reporter.log("Ingresar email: " + email, true);
		email = Utiles.getFechaActual() + email;
		inputEmail.sendKeys(email);

		Reporter.log("Ingresar código de área: " + codArea, true);
		inputCodArea.sendKeys(codArea);

		Reporter.log("Ingresar número de teléfono: " + numTelefono, true);
		inputNumTelefono.sendKeys(numTelefono);

		Reporter.log("Ingresar contraseña: " + contrasenia, true);
		inputContrasenia.sendKeys(contrasenia);

		Reporter.log("Reingresar contraseña: " + contrasenia, true);
		inputRepContrasenia.sendKeys(contrasenia);

		Reporter.log("Todos los datos han sido ingresados correctamente", true);
	}

	public void clickRegistrarmeButton() {
		Reporter.log("Validar que el botón 'Registrarme' está visible", true);
		Assert.assertTrue(RegistrarmeButton.isDisplayed(), "El botón 'Registrarme' no está visible");

		Reporter.log("Verificar si el botón 'Registrarme' está habilitado", true);
		if (!RegistrarmeButton.isEnabled()) {
			Reporter.log("El botón 'Registrarme' no está habilitado. Verificar campos de entrada.", true);
			throw new IllegalStateException("El botón 'Registrarme' no está habilitado. Revisa las validaciones.");
		}

		Reporter.log("Esperar que el botón 'Registrarme' sea clickeable", true);
		waitForElementToBeClickable(RegistrarmeButton, 10);

		RegistrarmeButton.click();
		Reporter.log("Se hizo clic en el botón 'Registrarme'", true);
	}

	public boolean verificarMensajeExito(int timeoutInSeconds) {
		Reporter.log("Esperar a que el mensaje de éxito sea visible", true);
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
			wait.until(ExpectedConditions.visibilityOf(mensajeExito));
			Reporter.log("Mensaje de éxito visible: " + mensajeExito.getText(), true);
			return true;
		} catch (Exception e) {
			Reporter.log("No se encontró el mensaje de éxito", true);
			return false;
		}
	}
	
	// Método para hacer clic en el botón "Aceptar"
	public void clickAceptarButton() {
	    Reporter.log("Validar que el botón 'Aceptar' está visible", true);
	    waitForElementToBeVisible(AceptarButton, 10); 
	    Assert.assertTrue(AceptarButton.isDisplayed(), "El botón 'Aceptar' no está visible");
	    
	    Reporter.log("Haciendo clic en el botón 'Aceptar'", true);
	    waitForElementToBeClickable(AceptarButton, 10); 
	    AceptarButton.click();
	    Reporter.log("Se hizo clic en el botón 'Aceptar'", true);
	}

	
	// Método para iniciar sesión con dos pasos
	public void iniciarSesion(String email, String contrasenia) {
	    Reporter.log("Ingresando email para iniciar sesión: " + email, true);

	    // Paso 1: Ingresar el email y hacer clic en "Continuar"
	    waitForElementToBeVisible(inputLoginEmail, 10);
	    inputLoginEmail.sendKeys(email);
	    Reporter.log("Email ingresado correctamente", true);

	    Reporter.log("Haciendo clic en el botón 'Continuar'", true);
	    waitForElementToBeClickable(ContinuarButton, 10);
	    ContinuarButton.click();

	    // Paso 2: Ingresar la contraseña y hacer clic en "Iniciar sesión"
	    Reporter.log("Esperando el campo de contraseña", true);
	    waitForElementToBeVisible(inputLoginContrasenia, 10);
	    inputLoginContrasenia.sendKeys(contrasenia);
	    Reporter.log("Contraseña ingresada correctamente", true);

	    Reporter.log("Haciendo clic en el botón 'Iniciar sesión'", true);
	    waitForElementToBeClickable(IniciarSesionButton, 10);
	    IniciarSesionButton.click();
	    Reporter.log("Se completó el flujo de inicio de sesión", true);
	}
	
	public void clickIconoPersona() {
	    Reporter.log("Haciendo clic en el ícono 'person_outline'", true);
	    waitForElementToBeVisible(personaIconLogin, 10);
	    personaIconLogin.click();
	    Reporter.log("Se hizo clic en el ícono 'person_outline'", true);
	}
	
	public CompraGamerAccountPage clickMiCuenta() {
	    Reporter.log("Haciendo clic en 'Mi Cuenta' después de abrir el menú", true);
	    waitForElementToBeClickable(MiCuentaButton, 10);
	    MiCuentaButton.click();
	    Reporter.log("Redirigiendo a la página 'Mi Cuenta'", true);
	    return new CompraGamerAccountPage(driver);
	}

	
	public CompraGamerAccountPage clickMiCuentaButton() {
	    Reporter.log("Validar que el botón 'Mi Cuenta' está visible y hacer clic", true);
	    waitForElementToBeClickable(MiCuentaButton, 10);
	    Assert.assertTrue(MiCuentaButton.isDisplayed(), "El botón 'Mi Cuenta' no está visible");

	    Reporter.log("Haciendo clic en el botón 'Mi Cuenta'", true);
	    MiCuentaButton.click();

	    Reporter.log("Navegando a la página de 'Mi Cuenta'", true);
	    return new CompraGamerAccountPage(driver); // Retornar una instancia de la página de cuenta
	}
	
}
