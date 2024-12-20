package com.selenium.CompraGamer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CGamerResultPage {

	@FindBy(xpath = "//body/cgw-root/cgw-core/div/mat-sidenav-container[@class='mat-drawer-container mat-sidenav-container sidenav-container']/mat-sidenav-content[@class='mat-drawer-content mat-sidenav-content']/div[@class='extra-height']/main[@role='main']/cgw-dashboard-arma-pc[@class='ng-star-inserted']/div[@class='row justify-content-center fondo']/div[@class='col-12 col-md-11 container']/div[@class='ng-star-inserted']/div[@class='row']/div[@class='col-8']/div[@class='ng-star-inserted']/div[@class='grid']/cgw-product-card[1]/div[1]")
	private WebElement primerBoton;

	// @FindBy(xpath = "//img[@alt='icono Placa de Video sin seleccionar']")
	// private WebElement btnPlacaVideo;

	@FindBy(xpath = "//button[@id='mat-button-toggle-4-button']//span[@class='mat-button-toggle-label-content']")
	private WebElement btnSiguiente;

	@FindBy(xpath = "//span[@class='mat-mdc-menu-item-text']")
	private WebElement btnFinalizar;

	@FindBy(xpath = "//button[@id='mat-button-toggle-5-button']//mat-icon[@role='img'][normalize-space()='arrow_drop_down']")
	private WebElement btnDesplegable;
	
	@FindBy(xpath = "//span[contains(text(),'Ir al carrito')]")
	private WebElement btnCarrito;

	// PRIMER BOTON
	public WebElement getPrimerBoton() {
		return primerBoton;
	}

	public boolean mostrarPrimerBoton() {
		return primerBoton.isDisplayed();
	}

	public void clickPrimerBoton() {
		primerBoton.click();
	}

	// PLACA DE VIDEO
	/*
	 * public WebElement getBtnPlacaVideo() { return btnPlacaVideo; }
	 * 
	 * public boolean mostrarBtnPlacaVideo() { return btnPlacaVideo.isDisplayed(); }
	 * 
	 * public void clickBtnPlacaVideo() { btnPlacaVideo.click(); }
	 */

	// BOTON SIGUIENTE
	public WebElement getBtnSiguiente() {
		return btnSiguiente;
	}

	public boolean mostrarBtnSiguiente() {
		return btnSiguiente.isDisplayed();
	}

	public void clickBtnSiguiente() {
		btnSiguiente.click();
	}

	// BOTON FINALIZAR
	public WebElement getBtnFinalizar() {
		return btnFinalizar;
	}

	public boolean mostrarBtnFinalizar() {
		return btnFinalizar.isDisplayed();
	}

	public void clickBtnFinalizar() {
		btnFinalizar.click();
	}

	// BOTON DESPLEGABLE

	public WebElement getBtnDesplegable() {
		return btnDesplegable;
	}

	public boolean mostrarBtnDesplegable() {
		return btnDesplegable.isDisplayed();
	}
	
	public void clickBtnDesplegable() {
		btnDesplegable.click();
	}
	
	// BOTON CARRITO
	
	public WebElement getBtnCarrito() {
		return btnCarrito;
	}

	public boolean mostrarBtnCarrito() {
		return btnCarrito.isDisplayed();
	}
	
	public void clickBtnCarrito() {
		btnCarrito.click();
	}
}
