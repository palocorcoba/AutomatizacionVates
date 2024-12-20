package com.selenium.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class WikiPortadaPage {

    WebDriver driver;
    private WebDriverWait wait;

    // Constructor con inicialización de WebDriver y WebDriverWait
    public WikiPortadaPage(WebDriver ldriver) {
        driver = ldriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera máxima de 10 segundos
    }

    // *** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR ***

    // Header Elements
    @FindBy(css = ".vector-header-container")
    private WebElement headerContainer;

    @FindBy(xpath = "//img[@class='mw-logo-icon']")
    private WebElement headerLogo;

    @FindBy(xpath = "//input[@placeholder='Buscar en Wikipedia']")
    private WebElement headerSearchInput;

    @FindBy(xpath = "//form[@id='searchform']//button[normalize-space()='Buscar']")
    private WebElement headerSearchButton;

    @FindBy(xpath = "//a[@data-mw='interface']//span[contains(text(),'Donaciones')]")
    private WebElement headerMenuDonations;

    @FindBy(xpath = "//a[@data-mw='interface']//span[contains(text(),'Crear una cuenta')]")
    private WebElement headerMenuCreateAccount;

    @FindBy(xpath = "//a[@data-mw='interface']//span[contains(text(),'Acceder')]")
    private WebElement headerMenuAccess;

    // Body Elements
    @FindBy(xpath = "//div[@class='mw-page-container']")
    private WebElement bodyPageContainer;

    @FindBy(xpath = "//div[@class='main-top']")
    private WebElement bodyTopBanner;

    @FindBy(xpath = "//div[@id='bodyContent']")
    private WebElement bodyContent;

    @FindBy(xpath = "//div[@id='vector-appearance-pinned-container']")
    private WebElement bodyAparienciaSection;

    // Footer Elements
    @FindBy(xpath = "//div[@class='mw-footer-container']")
    private WebElement footerContainer;

    @FindBy(xpath = "//footer[@id='footer']")
    private WebElement footerInfo;

    @FindBy(xpath = "//a[@href='https://wikimediafoundation.org/']")
    private WebElement footerImageWikiMediaFoundation;

    @FindBy(xpath = "//a[@href='https://www.mediawiki.org/']")
    private WebElement footerImageMediaWiki;

    // Métodos del Header

    public boolean isHeaderContainerVisible() {
        Reporter.log("Validando si el container del header está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(headerContainer));
            return headerContainer.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El container del header no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isHeaderLogoVisible() {
        Reporter.log("Validando si el logo del header está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(headerLogo));
            return headerLogo.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El logo del header no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isHeaderSearchVisible() {
        Reporter.log("Validando si la caja de búsqueda del header está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(headerSearchInput));
            return headerSearchInput.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: La caja de búsqueda del header no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isHeaderSearchButtonVisible() {
        Reporter.log("Validando si el botón de búsqueda del header está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(headerSearchButton));
            return headerSearchButton.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El botón de búsqueda del header no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isHeaderMenuVisible() {
        Reporter.log("Validando si las opciones del header están visibles...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(headerMenuDonations));
            wait.until(ExpectedConditions.visibilityOf(headerMenuCreateAccount));
            wait.until(ExpectedConditions.visibilityOf(headerMenuAccess));
            return headerMenuDonations.isDisplayed() &&
                   headerMenuCreateAccount.isDisplayed() &&
                   headerMenuAccess.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: Las opciones del header no están visibles: " + e.getMessage(), true);
            return false;
        }
    }
    
    public void clickOnAccess() {
        Reporter.log("Intentando hacer clic en el elemento que dice 'Acceder'...", true);
        try {
            // Localiza el elemento
            WebElement accessElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-mw='interface']//span[contains(text(),'Acceder')]")));

            // Verifica si el texto coincide
            String actualText = accessElement.getText();
            if (!"Acceder".equalsIgnoreCase(actualText)) {
                Reporter.log("❌ Error: El texto del elemento no coincide. Texto encontrado: '" + actualText + "', se esperaba: 'Acceder'", true);
                Assert.fail("El texto del elemento no coincide con 'Acceder'.");
            }

            Reporter.log("✅ El texto coincide. Haciendo clic en el elemento...", true);
            // Realiza el clic
            accessElement.click();
            Reporter.log("✔ Clic realizado correctamente en 'Acceder'.", true);

        } catch (Exception e) {
            Reporter.log("❌ Error al intentar hacer clic en 'Acceder': " + e.getMessage(), true);
            Assert.fail("No se pudo hacer clic en el elemento 'Acceder'. Excepción: " + e.getMessage());
        }
    }

    // Métodos del Body

    public boolean isBodyContainerVisible() {
        Reporter.log("Validando si el contenedor del body está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(bodyPageContainer));
            return bodyPageContainer.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El contenedor del body no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isBodyContentVisible() {
        Reporter.log("Validando si el contenido principal del body está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(bodyContent));
            return bodyContent.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El contenido principal del body no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isBodyTopBannerVisible() {
        Reporter.log("Validando si el banner superior del body está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(bodyTopBanner));
            return bodyTopBanner.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El banner superior del body no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isBodyAparienciaSectionVisible() {
        Reporter.log("Validando si la sección de Apariencia del body está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(bodyAparienciaSection));
            return bodyAparienciaSection.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: La sección de Apariencia del body no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    // Métodos del Footer

    public boolean isFooterContainerVisible() {
        Reporter.log("Validando si el footer está visible...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(footerContainer));
            return footerContainer.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: El footer no está visible: " + e.getMessage(), true);
            return false;
        }
    }

    public boolean isFooterImagesVisible() {
        Reporter.log("Validando si las imágenes del footer están visibles...", true);
        try {
            wait.until(ExpectedConditions.visibilityOf(footerImageWikiMediaFoundation));
            wait.until(ExpectedConditions.visibilityOf(footerImageMediaWiki));
            return footerImageWikiMediaFoundation.isDisplayed() &&
                   footerImageMediaWiki.isDisplayed();
        } catch (Exception e) {
            Reporter.log("❌ Error: Las imágenes del footer no están visibles: " + e.getMessage(), true);
            return false;
        }
    }
}

