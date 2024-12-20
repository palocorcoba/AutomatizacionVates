package com.selenium.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class GoogleHomePageLibros {

    WebDriver driver;

    // Constructor
    public GoogleHomePageLibros(WebDriver ldriver) {
        this.driver = ldriver;
    }

    // Elementos
    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(linkText = "Libros") // Cambiar "Videos" por "Libros"
    private WebElement librosLink;

    // Métodos
    public void enterSearchQuery(String query) {
        Reporter.log("Ingresar el texto '" + query + "' en la barra de búsqueda", true);
        Assert.assertTrue(searchBox.isDisplayed(), "La barra de búsqueda no está visible");
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public void presionarEnterEnBarraBusqueda() {
        Reporter.log("Presionar Enter en la barra de búsqueda", true);
        searchBox.sendKeys(Keys.ENTER);
    }

    public boolean seVisualizaBarraBusqueda() {
        Reporter.log("Validar que la barra de búsqueda esté visible", true);
        return searchBox.isDisplayed();
    }

    // Método para hacer clic en la pestaña de libros
    public void irASeccionLibros() {
        Reporter.log("Hacer clic en la sección de Libros", true);
        Assert.assertTrue(librosLink.isDisplayed(), "El enlace de Libros no está visible");
        librosLink.click();
    }
}
