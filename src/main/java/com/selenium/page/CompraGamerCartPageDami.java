package com.selenium.page;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class CompraGamerCartPageDami {
	
	WebDriver driver;

	public CompraGamerCartPageDami(WebDriver ldriver) {
		driver = ldriver;
	}
	
	// Elementos
	
	@FindBy(xpath = "//div[contains(@class, 'mat-ripple card')]")
	private WebElement btnDomicilio;
	
	@FindBy(xpath = "(//div[contains(@class, 'mat-mdc-tooltip-trigger')])[last()]")
	private WebElement btnComprar;
	
	@FindBy(xpath = "//p[contains(text(),'Tenés que agregar una cuenta de facturación para c')]")
	private WebElement txtDatosFaltantes;
	
	// Metodos
	
	private void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickDomicilioButton(){
		Reporter.log("Validar que el botón 'Domicilio' esté visible y hacer clic en él", true);
		waitForElementToBeClickable(btnDomicilio, 20);
		Assert.assertTrue(btnDomicilio.isDisplayed(), "El botón 'Domicilio' no está visible");
		btnDomicilio.click();
		Reporter.log("Se hizo clic en el botón 'Domicilio'", true);
	}
	
	public void clickComprarButton(){
		Reporter.log("Validar que el botón 'Comprar' esté visible y hacer clic en él", true);
		waitForElementToBeClickable(btnComprar, 20);
		Assert.assertTrue(btnComprar.isDisplayed(), "El botón 'Comprar' no está visible");
		btnComprar.click();
		Reporter.log("Se hizo clic en el botón 'Comprar'", true);
	}
	
	public void validarErrorFaltanDatos() {
		Reporter.log("Validar que no se permita comprar porque falta cuenta de facturación", true);
		waitForElementToBeVisible(txtDatosFaltantes, 10);
		Assert.assertTrue(txtDatosFaltantes.getText().contains("agregar una cuenta de facturación"), "ERROR, se permite comprar igualmente");
	}
	
}
