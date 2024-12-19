package com.selenium.page;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utils.Utiles;


public class WikiHomeBsAsPage {
	WebDriver driver;

    // Constructor que recibe el WebDriver
    public WikiHomeBsAsPage(WebDriver ldriver) {
		driver = ldriver;
    }
    
    
 // ***** IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR EJEMPLO ID O XPATH

    // Elemento del menú superior izquierdo
    @FindBy(xpath = "//input[@id='vector-main-menu-dropdown-checkbox']")
    private WebElement menuIzquierdo;

    // Elemento para "Notificar un error"
    @FindBy(xpath = "//li[@id='n-bug_in_article']")
    private WebElement notificarError;

    @FindBy(xpath = "//span[contains(text(),'Informar del error')]")
    private WebElement informarElError;
  
    @FindBy(xpath = "//textarea[@id='ooui-4']")
    private WebElement textodelMensaje;
  
    @FindBy(xpath = "//input[@id='ooui-5']")
    private WebElement firma;
    
    @FindBy(xpath = "//span[@class='oo-ui-widget oo-ui-widget-enabled oo-ui-buttonElement oo-ui-labelElement oo-ui-flaggedElement-progressive oo-ui-buttonWidget oo-ui-actionWidget oo-ui-buttonElement-framed']//a[@role='button']")
    private WebElement botonAceptar;
  
  
  
 
    
    @FindBy(xpath = "//a[span[@class='oo-ui-labelElement-label' and text()='Voy a arreglarlo']]")
    private WebElement voyAArreglarlo;
    
    @FindBy(xpath = "a//span[@class='oo-ui-labelElement-label' and text()='Empezar a editar']")
    private WebElement empezarAEditar1; 
    
  //*[@id="mw-teleport-target"]/div[3]/div/div[1]/div[2]/div[3]/div/span[2]
    
    // Área de texto del script
    @FindBy(xpath = "//textarea[@id='wpTextbox1']")
    private WebElement textoScript;
    // @FindBy(xpath = "//*[@id=\"editform\"]/div[2]/div[3]/div[1]/div[2]/div[1]")
    
    // Campo para el resumen
    @FindBy(xpath = "//input[@id='wpSummary']")
    private WebElement inputResumen;
    
 // Botón para guardar
    @FindBy(xpath = "//input[@id='wpSave']")
    private WebElement botonGuardar;
    
    //@FindBy(xpath = "//span[@class='mw-page-title-main' and text()='Informes de error']")
    //private WebElement tituloInformeDeError;
    
    @FindBy(xpath = "//*[@id=\"content\"]/header")
    private WebElement tituloInformeDeError;
    
  //*[@id="content"]/header

    // Método para verificar si el título está presente
    public boolean isTituloInformeDeErrorPresente() {
        return tituloInformeDeError.isDisplayed();
    }


    


    public void clickVoyAArreglarlo() {
        try {
           
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       
        voyAArreglarlo.click();
    }
    
    public void clickEmpezarAEditar() { 
    	
    	empezarAEditar1.click();
    	try {
            
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       try {
        	
        	
            // Intentamos hacer clic usando el método estándar
            if (empezarAEditar1.isDisplayed() && empezarAEditar1.isEnabled()) {
                empezarAEditar1.click();
                System.out.println("Clic realizado con WebElement.click()");
            } else {
                throw new IllegalStateException("El botón 'Empezar a editar' no está interactuable.");
            }
        } catch (Exception e) {
            // Si ocurre algún error, usamos JavascriptExecutor como respaldo
            System.out.println("WebElement.click() falló. Intentando con JavascriptExecutor...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", empezarAEditar1);
            System.out.println("Clic realizado con JavascriptExecutor.");
        }
    }
    
    
    public void escribirEnScript(String texto) { //VER ademas de decirle donde tiene que  escribir 
    	 
        if (textoScript.isDisplayed() && textoScript.isEnabled()) {
        	textoScript.sendKeys(texto); 
        }
    }

    
    
    
    public void escribirResumen(String texto) {
        inputResumen.sendKeys(texto);
    }

  
    
    public void guardar() { //VER
         
          

    }


    public void esperarVentanaModal() {
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
 // Métodos para interactuar con los elementos
    public void clickMenuIzquierdo() {
        menuIzquierdo.click();
    }

    public void clickNotificarError() {
        notificarError.click();
    }
    
    
    
 
    public void clickVoyAInformarElerror() {
        try {
           
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        informarElError.click();
    }
    
    public void escribirMensaje(String texto) {
    	
    	try {
            
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	Reporter.log("Escribiendo mensaje1");
    	textodelMensaje.sendKeys(texto);
    	Reporter.log("Mensaje escrito");
    	
    }
    
 public void escribirfirma(String texto) {
    	
    	try {
            
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	Reporter.log("Escribiendo firma");
    	firma.sendKeys(texto);
    	Reporter.log("Firma escrita");
    	
    }
 
 public void clickAceptar() {
 Reporter.log("Clickeando aceptar");
 botonAceptar.click();
 try {
     
     Thread.sleep(5000); 
 } catch (InterruptedException e) {
     e.printStackTrace();
 } 
 
 }





  

    
   

}
