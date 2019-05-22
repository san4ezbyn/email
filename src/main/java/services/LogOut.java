package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOut {

    WebDriverWait wait;
    WebDriver driver;

    public LogOut(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@class='mail-User-Name'][contains(text(),'alexbyn')]")
    private WebElement userAccount;

    public Finish logOut() {

        userAccount.click();
        WebElement leaveYandexServices = driver.findElement(By.xpath("//a[@data-metric='Sign out of Yandex services'][contains(text(),'Выйти из сервисов Яндекса')]"));
        leaveYandexServices.click();
        return new Finish(this.driver);
    }

    public String getStartPageTitel() {
        return this.driver.getTitle();
    }

}
