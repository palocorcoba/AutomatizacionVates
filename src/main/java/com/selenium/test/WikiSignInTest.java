package com.selenium.test;

import com.selenium.page.WikiSignInPage;
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

public class WikiSignInTest {
    private WebDriver driver;
    private WikiSignInPage signInPage;

    @BeforeMethod
    public void precondicion(ITestContext context) {
        String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
        String URL = context.getCurrentXmlTest().getParameter("Url");
        driver = DriverFactory.LevantarBrowser(driver, URL, browserName);
        signInPage = PageFactory.initElements(driver, WikiSignInPage.class);
    }

    @AfterMethod
    public void postcondicion() {
        if (driver != null) {
            DriverFactory.FinalizarBrowser(driver);
        }
    }

    @Test(description = "Validar login exitoso en Wikipedia")
    public void testLoginExitoso() {
        Reporter.log("Iniciando prueba de login exitoso en Wikipedia", true);
        
        try {
            // Validar placeholders
            Assert.assertTrue(signInPage.verificarPlaceholderUsuario(), 
                "El placeholder del usuario no es el esperado");
            Assert.assertTrue(signInPage.verificarPlaceholderPassword(), 
                "El placeholder de la contraseña no es el esperado");
            
            // Realizar login
            String username = "WikiFranDummy";
            String password = "nY-B3GAEme.mA6e";
            
            Reporter.log("Intentando login con usuario: " + username, true);
            
            signInPage.ingresarUsuario(username);
            signInPage.ingresarPassword(password);
            signInPage.hacerClickEnAcceso();
            
            // Validar redirección o estado posterior al login
            String expectedTitle = "Mi cuenta";
            Assert.assertTrue(driver.getTitle().contains(expectedTitle), 
                "No se redirigió correctamente a la página esperada");
            
            Reporter.log("Login completado exitosamente", true);
            
        } catch (Exception e) {
            Reporter.log("Error durante la ejecución del test: " + e.getMessage(), true);
            Assert.fail("El test de login falló: " + e.getMessage());
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"WikiFranDummy", "nY-B3GAEme.mA6e", true},
            {"usuarioInvalido", "passwordInvalida", false},
            {"", "nY-B3GAEme.mA6e", false},
            {"WikiFranDummy", "", false}
        };
    }

    @Test(dataProvider = "loginData", description = "Validar diferentes escenarios de login")
    public void testMultiplesEscenariosLogin(String username, String password, boolean expectedResult) {
        Reporter.log("Probando login con usuario: " + username, true);
        
        try {
            signInPage.ingresarUsuario(username);
            signInPage.ingresarPassword(password);
            signInPage.hacerClickEnAcceso();
            
            if (expectedResult) {
                String expectedTitle = "Mi cuenta";
                Assert.assertTrue(driver.getTitle().contains(expectedTitle),
                    "No se redirigió correctamente a la página esperada");
            }
            
        } catch (Exception e) {
            if (expectedResult) {
                Assert.fail("El test falló para un caso que debería ser exitoso: " + e.getMessage());
            }
        }
    }
}