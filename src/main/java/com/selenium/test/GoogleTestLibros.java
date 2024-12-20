package com.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;
import com.selenium.page.GoogleHomePageLibros;
import com.selenium.page.GoogleResultPageLibros;

import java.time.Duration;

public class GoogleTestLibros {

    WebDriver driver;

    @BeforeMethod
    public void Precondicion(ITestContext context) {
        // Obtiene el navegador y la URL desde el archivo testng.xml
        String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
        String URL = context.getCurrentXmlTest().getParameter("Url");

        // Si la URL está vacía o es nula, asigna un valor por defecto
        if (URL == null || URL.isEmpty()) {
            URL = "https://www.google.com"; // Valor por defecto
        }

        // Inicia el navegador
        driver = DriverFactory.LevantarBrowser(driver, URL, browserName);
    }

    @AfterMethod
    public void Postcondicion() {
        DriverFactory.FinalizarBrowser(driver);
    }

    @DataProvider(name = "datos")
    public Object[][] createData() {
        return new Object[][]{
            {"Shakira"} // Puedes cambiar esto por cualquier otro término de búsqueda
        };
    }

    @Test(dataProvider = "datos", description = "Validar que las búsquedas en Google funcionan")
    public void ValidarGoogleSearchLibros(String dato) throws Exception {
        Reporter.log("Página de Google cargada", true);
        GoogleHomePageLibros homepage = PageFactory.initElements(driver, GoogleHomePageLibros.class);

        Reporter.log("Verificar que la barra de búsqueda se esté mostrando", true);
        Assert.assertTrue(homepage.seVisualizaBarraBusqueda(), "No se visualizó la barra de búsqueda");

        Reporter.log("Ingresar el texto '" + dato + "'", true);
        homepage.enterSearchQuery(dato);

        Reporter.log("Presionar Enter para buscar", true);
        homepage.presionarEnterEnBarraBusqueda();

        // Esperar a que los resultados de búsqueda se carguen
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

        GoogleResultPageLibros resultadoPageGoogle = PageFactory.initElements(driver, GoogleResultPageLibros.class);

        // Esperar y hacer clic en la sección de libros
        resultadoPageGoogle.irASeccionLibros();

        // Obtener el texto del primer libro
        String primerLibroTitulo = resultadoPageGoogle.verificarPrimerLibro();
        Reporter.log("Título del primer libro: " + primerLibroTitulo, true); // Imprimir el título del primer libro

        // Comparar los títulos sin diferencias de mayúsculas/minúsculas ni espacios extra
        Assert.assertTrue(primerLibroTitulo.trim().toLowerCase().contains(dato.trim().toLowerCase()), 
            "El primer libro no contiene el nombre esperado: " + dato + ". Título encontrado: " + primerLibroTitulo);
    }
}