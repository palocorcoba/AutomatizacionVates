package com.selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class GoogleHomePage {
	

    WebDriver driver;

    // Constructor para que utiliza el driver enviado por parámetro
    public GoogleHomePage(WebDriver ldriver) {
        this.driver = ldriver;
    }

    // * IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR, EJEMPLO ID O XPATH *

    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(id = "hplogo")
    private WebElement googleLogo;

    @FindBy(linkText = "Gmail")
    private WebElement gmailLink;

    @FindBy(linkText = "Imágenes")
    private WebElement imagesLink;

    // * MÉTODOS PARA INTERACTUAR CON LOS ELEMENTOS *
    
    
    
    public void enterSearchQuery(String query) {
        Reporter.log("Ingresar el texto '" + query + "' en la barra de búsqueda", true);
        searchBox.clear(); 
        searchBox.sendKeys(query);
    }

    public void ingresarTextoEnBarraBusqueda(String texto) {
        Reporter.log("Localizar y comprobar que la barra de búsqueda se muestra", true);
        Assert.assertTrue(searchBox.isDisplayed(), "La barra de búsqueda no está visible");
        Reporter.log("Ingresar el texto: " + texto, true);
        searchBox.sendKeys(texto);
    }

    public void presionarEnterEnBarraBusqueda() {
        Reporter.log("Presionar Enter en la barra de búsqueda", true);
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean seVisualizaBarraBusqueda() {
        Reporter.log("Validar que la barra de búsqueda esté visible", true);
        return searchBox.isDisplayed();
    }

    public void clickEnBotonBuscar() {
        Reporter.log("Hacer clic en el botón de búsqueda", true);
        Assert.assertTrue(searchButton.isDisplayed(), "El botón de búsqueda no está visible");
        searchButton.click();
    }

    public boolean seVisualizaLogoGoogle() {
        Reporter.log("Validar que el logo de Google esté visible", true);
        return googleLogo.isDisplayed();
    }

}