package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Finish {

    WebDriverWait wait;
    WebDriver driver;

    public Finish(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }
}
