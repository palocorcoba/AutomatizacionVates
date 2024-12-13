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
	private WebElement inputContraseña;

	@FindBy(xpath = "//input[@id='mat-input-8']")
	private WebElement inputRepContraseña;

	@FindBy(xpath = "//span[normalize-space()='Registrarme']")
	private WebElement RegistrarmeButton;
	
	@FindBy(xpath = "//p[contains(text(),'Tu cuenta se ha creado con éxito. Por favor iniciá')]")
	private WebElement mensajeExito;
	
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
		waitForElementToBeVisible(personaIconIngresar, 10); // Espera dinámica de 10 segundos
		Reporter.log("Hacemos click en el ícono", true);
		personaIconIngresar.click();
		;
	}

	public void clickCrearCuentaButton() {
		Reporter.log("Validar que el botón 'Crear cuenta' esté visible y hacer clic en él", true);
		Assert.assertTrue(crearCuentaButton.isDisplayed(), "El botón 'Crear cuenta' no está visible");
		waitForElementToBeClickable(crearCuentaButton, 10);
		crearCuentaButton.click();
		Reporter.log("Se hizo clic en el botón 'Crear cuenta'", true);
	}

	public void registrarCuenta(String nombre, String apellido, String email, String codArea, String numTelefono,
			String contraseña, String repContraseña) {
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

		Reporter.log("Ingresar contraseña: " + contraseña, true);
		inputContraseña.sendKeys(contraseña);

		Reporter.log("Reingresar contraseña: " + contraseña, true);
		inputRepContraseña.sendKeys(contraseña);

		Reporter.log("Todos los datos han sido ingresados correctamente", true);
	}

	public void clickRegistrarmeButton() {
		Reporter.log("Validar que el botón 'Registrarme' esté visible", true);
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

}
