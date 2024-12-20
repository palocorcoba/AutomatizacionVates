package com.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.Reporter;
import java.time.Duration;

public class GoogleResultPageLibros {

    WebDriver driver;

    public GoogleResultPageLibros(WebDriver ldriver) {
        this.driver = ldriver;
    }

    // Localizador para la sección de Libros
    private By librosTabLocator = By.xpath("//div[contains(text(),'Libros')]"); // Localizador para la pestaña "Libros"

    // Localizador para el primer libro
    private By firstBookLocator = By.xpath("//div[@class='g']//h3/a"); // Primer enlace de libro dentro de <h3>

    // Método para hacer clic en la pestaña de Libros
    public void irASeccionLibros() {
        Reporter.log("Haciendo clic en la pestaña de 'Libros'", true);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement librosTab = wait.until(ExpectedConditions.elementToBeClickable(librosTabLocator));
            librosTab.click();
            Reporter.log("Pestaña 'Libros' encontrada y clicada", true);
        } catch (Exception e) {
            Reporter.log("No se pudo hacer clic directamente en la pestaña de Libros, intentando con JavaScript", true);
            WebElement librosTab = driver.findElement(librosTabLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", librosTab);
            Reporter.log("Pestaña 'Libros' clicada usando JavaScript", true);
        }
    }

    public String verificarPrimerLibro() {
        // Esperar hasta que el primer libro sea visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));  
        try {
            WebElement primerLibro = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='LC20lb MBeuO xvfwl']"))); // Actualiza el XPath con la clase correcta
            return primerLibro.getText();
        } catch (Exception e) {
            Reporter.log("No se encontró el primer libro: " + e.getMessage(), true);
            throw new AssertionError("El primer libro no se pudo localizar.");
        }
    }
}