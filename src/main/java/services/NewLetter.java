package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NewLetter {

    WebDriverWait wait;
    WebDriver driver;

    public NewLetter(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 60);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//span[@class='user-account__name user-account__name_hasAccentLetter']")
    private static WebElement correctUser;

    @FindBy(xpath = "//a[contains(text(),'Почта')]")
    private WebElement pochtaButton;

    @FindBy(xpath = "//span[@class='mail-ComposeButton-Text'][contains(text(),'Написать')]")
    private WebElement writeNewLetter;

    @FindBy(xpath = "//div[@name='to']")
    private WebElement receiverField;

    @FindBy(xpath = "//input[@class='mail-Compose-Field-Input-Controller js-compose-field js-editor-tabfocus-prev']")
    private WebElement topicField;

    @FindBy(xpath = "//div[@class='cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
    private WebElement textField;


    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private WebElement sentLetters;

    @FindBy(xpath = "mail-MessageSnippet-FromText")
    private List<WebElement> listOfLetters;

    @FindBy(xpath = "//span[@class='mail-NestedList-Item-Name']")
    private List<WebElement> categoryList;

    @FindBy(xpath = "//span[contains(text(),'как черновик')]")
    private WebElement saveAsDraft;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftLetters;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_body js-message-snippet-body']")
    private List<WebElement> listOfDraftLetter;

    @FindBy(xpath = "//input[@value='AT-WD task']")
    private WebElement checkTopic;

    @FindBy(xpath = "//div[@class='cke_contents cke_reset']//*[contains(text(),'SOME TEXT FOR LETTER')]")
    private WebElement checkText;

    @FindBy(xpath = "//span[contains(text(),'Отправить')]")
    private WebElement sendLetter;


    public SendMail newLetter(String receiver, String topic, String text) {
        System.out.println("TEST check");
        correctUser.click();
        pochtaButton.click();
        writeNewLetter.click();
        receiverField.sendKeys(receiver);
        topicField.sendKeys(topic);
        textField.sendKeys(text);
        saveAsDraft.click();
        draftLetters.click();


        /*sendLetter.click();
        sentLetters.click();*/

        return new SendMail(this.driver);
    }

    public boolean findLetter() {
        for (WebElement letter : listOfLetters) {
            letter.getText();
            if (letter.getText().contains("AT-WD task")) {

                return true;
            }
        }
        return false;
    }

    public boolean findDraft() {
        for (WebElement draft : listOfDraftLetter) {
            if (draft.getText().contains("AT-WD task")) {

                return true;
            }
        }
        return false;
    }

    public boolean verifyDraftIsCorrect(String receiver, String topic, String text) {
        for (WebElement draft : listOfDraftLetter) {
            if (draft.getText().contains("AT-WD task")) {
                draft.click();
                if (receiverField.getText().contains(receiver) || checkTopic.getText().contains(topic) || checkText.getText().contains(text)) {
                   sendLetter.click();

                    return true;
                }
            }

        }
        return false;
    }


}
