package com.selenium.CompraGamer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CGamerHomePage {
    WebDriver driver;

    @FindBy(xpath = "//body/cgw-root[1]/cgw-core[1]/div[1]/mat-sidenav-container[1]/mat-sidenav-content[1]/cgw-navbar[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]")
    private WebElement botonArmarPC;

    
    public CGamerHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBotonArmarPC() {
        return botonArmarPC;
    }

    public boolean mostrarBoton() {
        return botonArmarPC.isDisplayed();
    }

    public void clickBotonArmarPC() {
        botonArmarPC.click();
    }

    
}
