package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class SendDraftMail {
    WebDriverWait wait;
    WebDriver driver;

    public SendDraftMail(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendLetter;

    @FindBy(xpath = "//div[@name='to']")
    private WebElement receiverField;

    @FindBy(xpath = "//div[@class='mail-Compose-Field-Input']/input[@type='text']")
    private WebElement topicField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textField;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private WebElement sentLetters;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftLetters;

    public LogOut checkDraftLettersFolder(String topic) {

        draftLetters.click();
        checkFolders(topic);
        return new LogOut(this.driver);
    }

    public LogOut checkSentLettersFolder(String topic) {
        sentLetters.click();
        checkFolders(topic);

        return new LogOut(this.driver);
    }

    public boolean checkFolders(String topic) {
        List<WebElement> listOfLetters = driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']"));
        for (WebElement draftLetter : listOfLetters) {
            if (draftLetter.getText().contains(topic)) {
                return false;
            }
        }
        return true;
    }
}
