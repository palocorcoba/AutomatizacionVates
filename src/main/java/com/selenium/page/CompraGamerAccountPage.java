package com.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class CompraGamerAccountPage {
    WebDriver driver;

    /* Constructor */
    public CompraGamerAccountPage(WebDriver ldriver) {
        driver = ldriver;
        PageFactory.initElements(driver, this);
    }
    
    

    // ***** IDENTIFICAMOS LOS ELEMENTOS DE LA PÁGINA DE CUENTA *****
    @FindBy(xpath = "//h1[normalize-space()='Mi cuenta']")
    private WebElement tituloMiCuenta;

    @FindBy(xpath = "//p[@class='no-facturas ng-star-inserted']")
    private WebElement mensajeSinFacturas;

    @FindBy(xpath = "//div[normalize-space()='No tenés domicilios cargados.']")
    private WebElement mensajeSinDomicilios;

    @FindBy(xpath = "//div[normalize-space()='No tenés cuentas cargadas.']")
    private WebElement mensajeSinCuentas;

    @FindBy(xpath = "//p[@class='ng-star-inserted']")
    private WebElement mensajeSinReservas;

    // Métodos para esperas dinámicas
    private void waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Validar que la página de cuenta se ha cargado
    public boolean isAccountPageLoaded() {
        Reporter.log("Validar que la página 'Mi Cuenta' se ha cargado correctamente", true);
        try {
            waitForElementToBeVisible(tituloMiCuenta, 10); 
            return tituloMiCuenta.isDisplayed();
        } catch (Exception e) {
            Reporter.log("Error: La página 'Mi Cuenta' no se cargó correctamente: " + e.getMessage(), true);
            return false;
        }
    }


    // Métodos para validar los mensajes "No tenés..."
    public boolean isMensajeSinFacturasVisible() {
        Reporter.log("Validar que el mensaje 'No tenés facturas cargadas' está visible", true);
        waitForElementToBeVisible(mensajeSinFacturas, 10);
        return mensajeSinFacturas.isDisplayed();
    }

    public boolean isMensajeSinDomiciliosVisible() {
        Reporter.log("Validar que el mensaje 'No tenés domicilios cargados' está visible", true);
        waitForElementToBeVisible(mensajeSinDomicilios, 10);
        return mensajeSinDomicilios.isDisplayed();
    }

    public boolean isMensajeSinCuentasVisible() {
        Reporter.log("Validar que el mensaje 'No tenés cuentas cargadas' está visible", true);
        waitForElementToBeVisible(mensajeSinCuentas, 10);
        return mensajeSinCuentas.isDisplayed();
    }

    public boolean isMensajeSinReservasVisible() {
        Reporter.log("Validar que el mensaje 'Aun no tenés reservas hechas' está visible", true);
        waitForElementToBeVisible(mensajeSinReservas, 10);
        return mensajeSinReservas.isDisplayed();
    }
}
