package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import services.*;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;

public class TestsRunner {
    private static WebDriver driver;
    private static String URL = "https://passport.yandex.by";
    private static String PAGE_TITLE = "Яндекс";


    private LaunchAndLogIn launchAndLogIn;
    private NewLetter newLetter;
    private SendDraftMail sendDraftMail;
    private LogOut logOut;
    private Finish finish;

    private static String RECEIVER = "fake@gmale.com";
    private static String TOPIC = "AT-WD task";
    private static String TEXT = "SOME TEXT FOR LETTER";

    @BeforeClass
    private void init() throws InterruptedException {
        setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        launchAndLogIn = new LaunchAndLogIn(driver);
    }

    @Test
    private void login() throws InterruptedException {
        newLetter = launchAndLogIn.startAndLogIn();
        Assert.assertTrue(launchAndLogIn.userAccount());
    }

    @Test
    private void newLetterSavedInDraft() {
        sendDraftMail = newLetter.newLetter(RECEIVER, TOPIC, TEXT);
        Assert.assertTrue(newLetter.findDraft());
    }

    @Test
    private void verifyAndSendDraft() {
        Assert.assertTrue(newLetter.verifyDraftIsCorrect(RECEIVER, TOPIC, TEXT));
    }

    @Test(dependsOnMethods = "verifyAndSendDraft")
    private void checkFoldersDraftAndSentForLetter() throws InterruptedException {
        logOut = sendDraftMail.sendDraftLetter();
        Assert.assertTrue(sendDraftMail.checkLetterInSentLettersFolder());
    }

    @Test(dependsOnMethods = "checkFoldersDraftAndSentForLetter")
    private void logingOut() {
        finish = logOut.logOut();
        Assert.assertEquals(logOut.getStartPageTitel(), PAGE_TITLE);
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
