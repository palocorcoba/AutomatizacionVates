package com.selenium.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import Utils.Utiles;

public class CompraGamerHomePage {
	WebDriver driver;

	/* Contructor que en este caso utiliza el driver enviado por parametro */

	public CompraGamerHomePage(WebDriver ldriver) {
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
	
	@FindBy(xpath = "//p[contains(text(),'Tu cuenta se ha creado con �xito. Por favor inici�')]")
	private WebElement mensajeExito;
	
	// M�todos para esperas din�micas
	private void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// M�todos para interactuar con los elementos
	public boolean isIconPersonaVisible() {
		Reporter.log("Validar que el icono Persona Ingresar se visualiza", true);
		return personaIconIngresar.isDisplayed();
	}

	public void clickPersonaIcon() {
		Reporter.log("Localizar y comprobar que el icono Persona Ingresar se muestra", true);
		Assert.assertTrue(isIconPersonaVisible(), "La caja de b�squeda no est� visible");
		waitForElementToBeVisible(personaIconIngresar, 10); // Espera din�mica de 10 segundos
		Reporter.log("Hacemos click en el �cono", true);
		personaIconIngresar.click();
		;
	}

	public void clickCrearCuentaButton() {
		Reporter.log("Validar que el bot�n 'Crear cuenta' est� visible y hacer clic en �l", true);
		Assert.assertTrue(crearCuentaButton.isDisplayed(), "El bot�n 'Crear cuenta' no est� visible");
		waitForElementToBeClickable(crearCuentaButton, 10);
		crearCuentaButton.click();
		Reporter.log("Se hizo clic en el bot�n 'Crear cuenta'", true);
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

		Reporter.log("Ingresar c�digo de �rea: " + codArea, true);
		inputCodArea.sendKeys(codArea);

		Reporter.log("Ingresar n�mero de tel�fono: " + numTelefono, true);
		inputNumTelefono.sendKeys(numTelefono);

		Reporter.log("Ingresar contrase�a: " + contrasenia, true);
		inputContrasenia.sendKeys(contrasenia);

		Reporter.log("Reingresar contrase�a: " + contrasenia, true);
		inputRepContrasenia.sendKeys(contrasenia);

		Reporter.log("Todos los datos han sido ingresados correctamente", true);
	}

	public void clickRegistrarmeButton() {
		Reporter.log("Validar que el bot�n 'Registrarme' est� visible", true);
		Assert.assertTrue(RegistrarmeButton.isDisplayed(), "El bot�n 'Registrarme' no est� visible");

		Reporter.log("Verificar si el bot�n 'Registrarme' est� habilitado", true);
		if (!RegistrarmeButton.isEnabled()) {
			Reporter.log("El bot�n 'Registrarme' no est� habilitado. Verificar campos de entrada.", true);
			throw new IllegalStateException("El bot�n 'Registrarme' no est� habilitado. Revisa las validaciones.");
		}

		Reporter.log("Esperar que el bot�n 'Registrarme' sea clickeable", true);
		waitForElementToBeClickable(RegistrarmeButton, 10);

		RegistrarmeButton.click();
		Reporter.log("Se hizo clic en el bot�n 'Registrarme'", true);
	}
	
	public boolean verificarMensajeExito(int timeoutInSeconds) {
	    Reporter.log("Esperar a que el mensaje de �xito sea visible", true);
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	        wait.until(ExpectedConditions.visibilityOf(mensajeExito));
	        Reporter.log("Mensaje de �xito visible: " + mensajeExito.getText(), true);
	        return true;
	    } catch (Exception e) {
	        Reporter.log("No se encontr� el mensaje de �xito", true);
	        return false;
	    }
	}

}
