package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = "//input[@class='mail-Compose-Field-Input-Controller js-compose-field js-editor-tabfocus-prev']")
    private WebElement topicField;

    @FindBy(xpath = "//div[@class='cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
    private WebElement textField;

    @FindBy(xpath = "//span[contains(text(),'Отправленные')]")
    private WebElement sentLetters;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    private WebElement draftLetters;

    @FindBy(xpath = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_body js-message-snippet-body']")
    private List<WebElement> listOfDraftLetter;

    @FindBy(xpath = "mail-MessageSnippet-FromText")
    private List<WebElement> listOfLetters;

    public LogOut sendDraftLetter(){


        draftLetters.click();
        checkDraftFolderIsEmpty();
        sentLetters.click();
        checkLetterInSentLettersFolder();



        return new LogOut(this.driver);
    }

    public boolean checkDraftFolderIsEmpty(){
       for (WebElement draftLetter:listOfDraftLetter){
           if (draftLetter.getText().contains("AT-WD task")){
               return false;
           }
       }
        return true;
    }
    public boolean checkLetterInSentLettersFolder(){
        for (WebElement letter:listOfLetters){
            if (letter.getText().contains("AT-WD task")){
                return true;
            }
        }
        return false;
    }


}
