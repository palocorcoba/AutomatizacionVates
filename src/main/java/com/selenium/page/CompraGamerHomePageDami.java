package com.selenium.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utils.Utiles;

public class CompraGamerHomePageDami {
	WebDriver driver;

	/* Contructor que en este caso utiliza el driver enviado por parametro */

	public CompraGamerHomePageDami(WebDriver ldriver) {
		driver = ldriver;
	}

	// ***** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH

	// Identificar elementos por su localizador
	
	@FindBy(xpath = "//input[contains(@id, 'mat-input')]")
	private WebElement searchInputElement;
	
	@FindBy(className = "cg__fw-400")
	private WebElement primerResultado;
	
	@FindBy(xpath = "//span[normalize-space()='person_outline']")
	private WebElement personaIconIngresar;

	@FindBy(xpath = "//span[normalize-space()='Crear cuenta']")
	private WebElement crearCuentaButton;
	
	// Elementos para iniciar sesión
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement inputEmailLogin;
	
	@FindBy(xpath = "//button[@class='continue mdc-button mdc-button--raised mat-mdc-raised-button mat-primary mat-mdc-button-base']//span[@class='mat-mdc-button-touch-target']")
	private WebElement clickContinuarLogin;
	
	@FindBy(xpath = "//*[@formcontrolname='contrasenia']")
	private WebElement inputPasswordLogin;
	
	@FindBy(xpath = "//button[@class='continue mdc-button mdc-button--raised mat-mdc-raised-button mat-primary mat-mdc-button-base']//span[@class='mat-mdc-button-touch-target']")
	private WebElement btnIngresar;
	
	@FindBy(xpath = "(//div[contains(@class, 'product-card')]//a[contains(@class, 'product-card__button')])[1]")
	private WebElement btnAgregarCarrito;
	
	// Métodos para esperas dinámicas
	
	private void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public boolean waitForSearchInput() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(searchInputElement));
		return searchInputElement.isDisplayed();
	}

	// Métodos para interactuar con los elementos - Registrar usuario
	
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
	
	// Métodos para Iniciar Sesión
	
	public void ingresarMail(String mail) {
		Reporter.log("Ingresar email: " + mail, true);
		inputEmailLogin.sendKeys(mail);
	}
	
	public void clickContinuar() {
		Reporter.log("Validar que el botón 'Continuar' esté visible", true);
		Assert.assertTrue(clickContinuarLogin.isDisplayed(), "El botón 'Registrarme' no está visible");

		Reporter.log("Verificar si el botón 'Continuar' está habilitado", true);
		if (!clickContinuarLogin.isEnabled()) {
			Reporter.log("El botón 'Continuar' no está habilitado. Verificar campos de entrada.", true);
			throw new IllegalStateException("El botón 'Continuar' no está habilitado. Revisa las validaciones.");
		}

		Reporter.log("Esperar que el botón 'Continuar' sea clickeable", true);
		waitForElementToBeClickable(clickContinuarLogin, 10);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement overlay = driver.findElement(By.className("cdk-overlay-backdrop"));

		Actions actions = new Actions(driver);
		actions.moveToElement(clickContinuarLogin).click().perform();
		Reporter.log("Se hizo clic en el botón 'Continuar'", true);
	}
	
	public void ingresarPassword(String password) {
		Reporter.log("Ingresar password: " + password, true);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(inputPasswordLogin));
		Actions actions = new Actions(driver);
		actions.moveToElement(inputPasswordLogin).click().perform();
		inputPasswordLogin.sendKeys(password);
	}
	
	public void clickIngresar() {
		Reporter.log("Validar que el botón 'Ingresar' esté visible", true);
		Assert.assertTrue(btnIngresar.isDisplayed(), "El botón 'Ingresar' no está visible");

		Reporter.log("Verificar si el botón 'Ingresar' está habilitado", true);
		if (!btnIngresar.isEnabled()) {
			Reporter.log("El botón 'Ingresar' no está habilitado. Verificar campos de entrada.", true);
			throw new IllegalStateException("El botón 'Ingresar' no está habilitado. Revisa las validaciones.");
		}

		Reporter.log("Esperar que el botón 'Ingresar' sea clickeable", true);
		waitForElementToBeClickable(btnIngresar, 10);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnIngresar);
		Reporter.log("Se hizo clic en el botón 'Ingresar'", true);
	}
	
	// Métodos para buscar producto
	
	public void BuscarProducto(String producto) {
		Reporter.log("Localizar y comprobar que la caja de busqueda se muestra", true);
		Assert.assertTrue((searchInputElement.isDisplayed()), "La caja de busqueda no se visualiza");
		Reporter.log("Ingresar la palabra " + producto, true);
		searchInputElement.sendKeys(producto);
		Reporter.log("Presionar Enter", true);
		searchInputElement.sendKeys(Keys.ENTER);
		waitForElementToBeVisible(btnAgregarCarrito, 10);
	}

}
