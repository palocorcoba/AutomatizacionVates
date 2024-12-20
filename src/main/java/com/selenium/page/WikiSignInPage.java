package com.selenium.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WikiSignInPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    // Constantes
    private static final String EXPECTED_USERNAME_PLACEHOLDER = "Escribe tu nombre de usuario";
    private static final String EXPECTED_PASSWORD_PLACEHOLDER = "Escribe tu nombre de contraseña";
    private static final int WAIT_TIMEOUT_SECONDS = 20;
    
    // Constructor mejorado
    public WikiSignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        PageFactory.initElements(driver, this);
    }
    
    // Localizadores mejorados usando mejores prácticas de XPath
    @FindBy(id = "firstHeading")
    private WebElement firstHeading;
    
    @FindBy(xpath = "//input[@name='wpName']")
    private WebElement inputUsuario;
    
    @FindBy(xpath = "//input[@name='wpPassword']")
    private WebElement inputPassword;
    
    @FindBy(id = "wpLoginAttempt")
    private WebElement accessButton;
    
    @FindBy(xpath = "//div[contains(@class, 'mw-userpage')]")
    private WebElement userPageElement;
    
    @FindBy(xpath = "//a[@title='Especial:RestablecerContraseña']")
    private WebElement restorePassword;
    
    @FindBy(id = "mw-createaccount-join")
    private WebElement buttonCreateAccount;
    
    // Métodos de verificación mejorados
    public boolean verificarPlaceholderUsuario() {
        try {
            wait.until(ExpectedConditions.visibilityOf(inputUsuario));
            // Usar getDomProperty en lugar de getAttribute
            String actualPlaceholder = inputUsuario.getDomProperty("placeholder");
            Reporter.log("Placeholder de usuario encontrado: '" + actualPlaceholder + "'", true);
            return EXPECTED_USERNAME_PLACEHOLDER.equalsIgnoreCase(actualPlaceholder);
        } catch (Exception e) {
            Reporter.log("Error al verificar placeholder de usuario: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean verificarPlaceholderPassword() {
        try {
            wait.until(ExpectedConditions.visibilityOf(inputPassword));
            // Usar getDomProperty en lugar de getAttribute
            String actualPlaceholder = inputPassword.getDomProperty("placeholder");
            Reporter.log("Placeholder de contraseña encontrado: '" + actualPlaceholder + "'", true);
            return EXPECTED_PASSWORD_PLACEHOLDER.equalsIgnoreCase(actualPlaceholder);
        } catch (Exception e) {
            Reporter.log("Error al verificar placeholder de contraseña: " + e.getMessage(), true);
            return false;
        }
    }

    // Métodos de interacción mejorados
    public void ingresarUsuario(String usuario) {
        Reporter.log("Ingresando el nombre de usuario: " + usuario, true);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inputUsuario));
            element.clear();
            element.sendKeys(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al ingresar el usuario: " + e.getMessage(), e);
        }
    }

    public void ingresarPassword(String password) {
        Reporter.log("Ingresando la contraseña", true);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(inputPassword));
            element.clear();
            element.sendKeys(password);
        } catch (Exception e) {
            throw new RuntimeException("Error al ingresar la contraseña: " + e.getMessage(), e);
        }
    }

    public void hacerClickEnAcceso() {
        Reporter.log("Haciendo clic en el botón de acceso", true);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(accessButton));
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Error al hacer clic en el botón de acceso: " + e.getMessage(), e);
        }
    }
    
    // Nuevos métodos de validación
    public boolean verificarElementosPostLogin() {
        try {
            wait.until(ExpectedConditions.visibilityOf(userPageElement));
            return userPageElement.isDisplayed();
        } catch (Exception e) {
            Reporter.log("Error al verificar elementos post-login: " + e.getMessage(), true);
            return false;
        }
    }
    
    public boolean verificarLoginExitoso() {
        return driver.getCurrentUrl().contains("/wiki/Special:MyPage") && 
               verificarElementosPostLogin();
    }

    public void realizarLoginCompleto(String usuario, String password) {
        ingresarUsuario(usuario);
        ingresarPassword(password);
        hacerClickEnAcceso();
        if (!verificarLoginExitoso()) {
            throw new RuntimeException("El login no se completó exitosamente");
        }
    }
    
    // Métodos de utilidad
    public boolean estaPaginaLoginCargada() {
        try {
            return wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(inputUsuario),
                ExpectedConditions.visibilityOf(inputPassword),
                ExpectedConditions.visibilityOf(accessButton)
            ));
        } catch (Exception e) {
            return false;
        }
    }
}

