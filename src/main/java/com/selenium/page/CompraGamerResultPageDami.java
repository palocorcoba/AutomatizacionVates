package com.selenium.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class CompraGamerResultPageDami {

	WebDriver driver;

	public CompraGamerResultPageDami(WebDriver ldriver) {
		driver = ldriver;
	}

	// Elementos

	@FindBy(xpath = "(//div[contains(@class, 'product-card')]//a[contains(@class, 'product-card__button')])[1]")
	private WebElement btnAgregarCarrito;

	@FindBy(xpath = "//span[normalize-space()='shopping_cart']")
	private WebElement btnCarrito;

	// Métodos

	private void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	private void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void clickAgregarCarritoButton() {
		Reporter.log("Validar que el botón 'Agregar a carrito' esté visible y hacer clic en él", true);
		Assert.assertTrue(btnAgregarCarrito.isDisplayed(), "El botón 'Agregar a carrito' no está visible");
		waitForElementToBeClickable(btnAgregarCarrito, 10);
		btnAgregarCarrito.click();
		Reporter.log("Se hizo clic en el botón 'Agregar a carrito'", true);
	}

	public void clickCarritoButton() {
		Reporter.log("Validar que el botón 'Carrito' esté visible y hacer clic en él", true);
		Assert.assertTrue(btnCarrito.isDisplayed(), "El botón 'Carrito' no está visible");
		waitForElementToBeClickable(btnCarrito, 10);
		btnCarrito.click();
		Reporter.log("Se hizo clic en el botón 'Carrito'", true);
	}

}
