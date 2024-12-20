package com.selenium.test;

import com.selenium.page.WikiPortadaPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.driver.DriverFactory;

public class WikiPortadaTest {
    WebDriver driver;

    // Configuración previa al test
    @BeforeMethod
    public void Precondicion(ITestContext context) {
        String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
        String URL = context.getCurrentXmlTest().getParameter("Url");
        driver = DriverFactory.LevantarBrowser(driver, URL, browserName);
    }

    // Finalización después del test
    @AfterMethod
    public void Postcondicion() {
        DriverFactory.FinalizarBrowser(driver);
    }

    // Datos para pruebas con DataProvider
    @DataProvider(name = "elementosEstructura")
    public Object[][] elementosEstructura() {
        return new Object[][]{
                {"Header"},
                {"Body"},
                {"Footer"}
        };
    }


    @Test(dataProvider = "elementosEstructura", description = "Validar que las secciones principales de Wikipedia están visibles")
    public void ValidarEstructuraWikipedia(String seccion) {
        Reporter.log("Iniciando prueba para validar la visibilidad de la sección: " + seccion, true);

        WikiPortadaPage portadaPage = PageFactory.initElements(driver, WikiPortadaPage.class);

        switch (seccion) {
            case "Header":
                Reporter.log("Verificando elementos del Header...", true);
                Assert.assertTrue(portadaPage.isHeaderContainerVisible(), "El contenedor del Header no está visible.");
                Assert.assertTrue(portadaPage.isHeaderLogoVisible(), "El logo del Header no está visible.");
                Assert.assertTrue(portadaPage.isHeaderSearchVisible(), "La caja de búsqueda del Header no está visible.");
                Assert.assertTrue(portadaPage.isHeaderSearchButtonVisible(), "El botón de búsqueda del Header no está visible.");
                Assert.assertTrue(portadaPage.isHeaderMenuVisible(), "El menú de usuario del Header no está visible.");
                break;

            case "Body":
                Reporter.log("Verificando elementos del Body...", true);
                Assert.assertTrue(portadaPage.isBodyContainerVisible(), "El contenedor del Body no está visible.");
                Assert.assertTrue(portadaPage.isBodyTopBannerVisible(), "El banner del Body que dice Bienvenidos a Wikipedia no está visible.");
                Assert.assertTrue(portadaPage.isBodyContentVisible(), "El contenido principal del Body no está visible.");
                Assert.assertTrue(portadaPage.isBodyAparienciaSectionVisible(), "La sección de Apariencia del Body no está visible.");
                break;

            case "Footer":
                Reporter.log("Verificando elementos del Footer...", true);
                Assert.assertTrue(portadaPage.isFooterContainerVisible(), "El contenedor del Footer no está visible.");
                Assert.assertTrue(portadaPage.isFooterImagesVisible(), "Las imágenes del Footer no están visibles.");
                break;

            default:
                Assert.fail("Sección desconocida: " + seccion);
        }

        Reporter.log("La sección " + seccion + " fue validada correctamente.", true);
    }
    
    @Test(description = "Validar que se pueda hacer clic en 'Acceder'")
    public void testClickOnAccess() {
        WikiPortadaPage portadaPage = PageFactory.initElements(driver, WikiPortadaPage.class);
        portadaPage.clickOnAccess();
    }
}
