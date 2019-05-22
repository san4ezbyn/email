package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewLetter {

    private String NEW_LETTER_TOPIC = "AT-WD-№";

    WebDriverWait wait;
    WebDriver driver;

    public NewLetter(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@class='user2']")
    private static WebElement correctUser;

    @FindBy(xpath = "//a[contains(text(),'Почта')]")
    private WebElement pochtaButton;

    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text'][contains(text(),'Написать')]")
    private WebElement writeNewLetter;

    @FindBy(xpath = "//div[@name='to']")
    private WebElement receiverField;

    @FindBy(xpath = "//div[@class='mail-Compose-Field-Input']/input[@type='text']")
    private WebElement topicField;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement textField;

    @FindBy(xpath = "mail-MessageSnippet-FromText")
    private List<WebElement> listOfLetters;

    @FindBy(xpath = "//span[contains(text(),'как черновик')]")
    private WebElement saveAsDraft;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftLetters;

    @FindBy(xpath = "//div[@class='mail-MessageSnippet-Wrapper']")
    private List<WebElement> listOfDraftLetter;

    @FindBy(xpath = "//span[@title][contains(text(),'AT-WD-№')]")
    private WebElement checkTopic;

    @FindBy(xpath = "//div[@class='cke_contents cke_reset']//*[contains(text(),'SOME TEXT FOR LETTER')]")
    private WebElement checkText;

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendLetter;

    public SendDraftMail newLetter(String receiver, String topic, String text) {

        correctUser.click();
        pochtaButton.click();
        writeNewLetter.click();
        receiverField.sendKeys(receiver);
        topicField.sendKeys(topic);
        textField.sendKeys(text);
        saveAsDraft.click();
        draftLetters.click();

        return new SendDraftMail(this.driver);
    }

    public boolean findLetter() {
        for (WebElement letter : listOfLetters) {
            letter.getText();
            if (letter.getText().contains(NEW_LETTER_TOPIC)) {

                return true;
            }
        }
        return false;
    }

    public boolean findDraft() {
        for (WebElement draft : listOfDraftLetter) {
            if (draft.getText().contains(NEW_LETTER_TOPIC)) {

                return true;
            }
        }
        return false;
    }

    public boolean verifyDraftIsCorrect(String receiver, String topic, String text) {
        for (WebElement draft : listOfDraftLetter) {
            if (draft.getText().contains(topic) || draft.getText().contains(receiver) || draft.getText().contains(text)) {
                draft.click();
                sendLetter.click();

                return true;
            }
        }
        return false;
    }
}
