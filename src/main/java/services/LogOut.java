package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//a[@data-metric='Sign out of Yandex services'][contains(text(),'Выйти из сервисов Яндекса')]")
    private WebElement leaveYabdexServices;

    public Finish logOut() {

        userAccount.click();

        /*Select dropdown = new Select(driver.findElement(By.xpath("//a[@data-metric='Sign out of Yandex services'][contains(text(),'Выйти из сервисов Яндекса')]")));
        dropdown.selectByVisibleText("Выйти из сервисов Яндекса");*/


        leaveYabdexServices.click();

        return new Finish(this.driver);
    }

    public String getStartPageTitel()
    {
        return this.driver.getTitle();
    }

}
