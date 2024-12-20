package com.selenium.CompraGamer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import com.selenium.driver.DriverFactory;

public class testCompraGamer {
    WebDriver driver;

    @BeforeMethod
    public void PreCondicion(ITestContext context) {
        String navegador = context.getCurrentXmlTest().getParameter("NombreNavegador");
        String url = context.getCurrentXmlTest().getParameter("Url");

        driver = DriverFactory.LevantarBrowser(driver, url, navegador);
    }

    @AfterMethod
    public void PostCondicion() {
        DriverFactory.FinalizarBrowser(driver);
    }

    @Test(description = "Armado de PC en CompraGamer")
    public void busquedaCompraGamer() throws Exception {
        Reporter.log("Página de Compra Gamer cargada", true);

        // INICIALIZACIÓN DE VARIABLES
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        CGamerHomePage homePage = PageFactory.initElements(driver, CGamerHomePage.class);
        CGamerResultPage resultPage = PageFactory.initElements(driver, CGamerResultPage.class);

        // BOTÓN DE "ARMAR TU PC"
        Reporter.log("Esperando a que el botón 'Armar tu PC' sea visible...", true);
        WebElement botonArmarPC = homePage.getBotonArmarPC();
        wait.until(ExpectedConditions.visibilityOf(botonArmarPC));
        Assert.assertTrue(homePage.mostrarBoton(), "No se visualiza el botón de armar tu PC");
        Reporter.log("El botón 'Armar tu PC' está visible.", true);

        homePage.clickBotonArmarPC();

        // COMPONENTES A SELECCIONAR
        String[] componentes = {"Microprocesador", "Placa Madre", "Cooler", "Placa de video", "Memoria RAM", "Disco duro", "Fuente", "Gabinete", "Monitor"}; 

        // SELECCIONAR COMPONENTES Y HACER CLIC EN SIGUIENTE
        for (int i = 0; i < componentes.length; i++) {
            Reporter.log("Seleccionando " + componentes[i] + " (Iteración " + (i + 1) + ")...", true);

            Reporter.log("Esperando a que el botón del componente '" + componentes[i] + "' sea visible...", true);
            wait.until(ExpectedConditions.visibilityOf(resultPage.getPrimerBoton()));

            Assert.assertTrue(resultPage.mostrarPrimerBoton(), "No se visualiza el botón del componente: " + componentes[i]);
            Reporter.log("El botón del componente '" + componentes[i] + "' está visible.", true);

            Reporter.log("Haciendo clic en el botón del componente '" + componentes[i] + "'...", true);
            resultPage.clickPrimerBoton();

            // BOTON SIGUIENTE
            if (i >= 3 && i < componentes.length - 3) { 
                Reporter.log("Esperando a que el botón 'Siguiente' sea visible...", true);
                wait.until(ExpectedConditions.visibilityOf(resultPage.getBtnSiguiente()));

                Assert.assertTrue(resultPage.mostrarBtnSiguiente(), "No se visualiza el botón 'Siguiente'");
                Reporter.log("El botón 'Siguiente' está visible.", true);

                Reporter.log("Haciendo clic en el botón 'Siguiente'...", true);
                resultPage.clickBtnSiguiente();
                Reporter.log("Se hizo clic en el botón 'Siguiente'.", true);
            } else if (i >= componentes.length - 2) { 
                Reporter.log("No se hará clic en el botón 'Siguiente' en esta iteración.", true);
            }

            Reporter.log("Se seleccionó correctamente el componente: " + componentes[i] + ".", true);
        }
        
        //Thread.sleep(15000);
        
        // FINALIZADA LA SELECCION DE COMPONENTES Y LLEVANDO LA COMPRA AL CARRITO
        
        //BOTON DESPLEGABLE
        Reporter.log("Esperando a que el boton desplegable sea visible");
        wait.until(ExpectedConditions.visibilityOf(resultPage.getBtnDesplegable()));
        
        Assert.assertTrue(resultPage.mostrarBtnDesplegable(), "No se visualiza el boton desplegable");
        Reporter.log("El boton desplegable esta visible", true);
        
        Reporter.log("Haciendo clic en el botón desplegable...", true);
        resultPage.clickBtnDesplegable();
        
        
        //BOTON FINALIZAR
        Reporter.log("Esperando a que el boton 'Finalizar' sea visible");
        wait.until(ExpectedConditions.visibilityOf(resultPage.getBtnFinalizar()));
        
        Assert.assertTrue(resultPage.mostrarBtnFinalizar(), "No se visualiza el boton 'Finalizar'");
        Reporter.log("El boton 'Finalizar' esta visible", true);
        
        Reporter.log("Haciendo clic en el botón 'Finalizar'...", true);
        resultPage.clickBtnFinalizar();
        
        // BOTON CARRITO
        
        Reporter.log("Esperando a que el boton del carrito sea visible");
        wait.until(ExpectedConditions.visibilityOf(resultPage.getBtnCarrito()));
        
        Assert.assertTrue(resultPage.mostrarBtnCarrito(), "No se visualiza el boton del carrito");
        Reporter.log("El boton del carrito esta visible", true);
        
        Reporter.log("Haciendo clic en el botón del carrito...", true);
        resultPage.clickBtnCarrito();
        
        //Thread.sleep(15000);
    }
}