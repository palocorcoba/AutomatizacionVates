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

public class GoogleResultPage {

	  WebDriver driver;

	    
	    public GoogleResultPage(WebDriver ldriver) {
	        this.driver = ldriver;
	    }

	    // * IDENTIFICAMOS LOS ELEMENTOS POR SU LOCATOR *

	  
	    @FindBy(xpath = "(//h3[@class='LC20lb MBeuO DKV0Md'])[1]")
		private WebElement firstResultTitle;
	    
	    @FindBy(name = "q")
	    private WebElement searchBox;

	    @FindBy(id = "result-stats")
	    private WebElement resultStats;
	    
	    

	    @FindBy(xpath = "(//h3[@class='LC20lb MBeuO DKV0Md'])[3]")
		private WebElement thirdResult;
	    

	    // * MÉTODOS PARA INTERACTUAR CON LOS ELEMENTOS *
	   
	    
	    public void click3Result() {
	    	this.firstResultTitle.click();
	    	
	    }
	    
	    
	    public String getFirstResultTitle() {
	        Reporter.log("Obteniendo el título del primer resultado", true);
	        return firstResultTitle.getText();
	    }

	    public String getResultStats() {
	        Reporter.log("Obteniendo las estadísticas de los resultados", true);
	        return resultStats.getText();
	    }
}