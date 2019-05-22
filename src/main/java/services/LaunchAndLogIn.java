package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LaunchAndLogIn {

    WebDriverWait wait;
    WebDriver driver;

    private static String LOGIN = "alexbyn";
    private static String PASSWORD = "lollipop18";
    private static String USER_ACCOUNT = "alexbyn";

    @FindBy(xpath = "//input[@name='login']")
    private static WebElement loginField;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement loginButtonEnter;

    @FindBy(xpath = "//input[@autocomplete='current-password']")
    private static WebElement passwordField;

    @FindBy(xpath = "//div[@class='user2']")
    private static WebElement correctUser;

    public LaunchAndLogIn(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 120);
        PageFactory.initElements(this.driver, this);
    }

    public NewLetter startAndLogIn() throws InterruptedException {

        loginField.sendKeys(LOGIN);
        loginButtonEnter.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@autocomplete='current-password']"))).sendKeys(PASSWORD);
        loginButtonEnter.click();

        return new NewLetter(this.driver);
    }

    public boolean userAccount() {
        if (correctUser.getText().equalsIgnoreCase(USER_ACCOUNT)) {
            return true;
        }
        return false;
    }
}
