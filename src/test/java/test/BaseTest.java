package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.util.Pair;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LoginPage;
import page.RegistrationPage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    private static ArrayList<String> testSteps;
    protected static int numberOfStep;

    private final static Logger log = Logger.getLogger(BaseTest.class);

    private String currentTestMethodName;
    final protected Pair<String, String> correctData = new Pair<>("raycut", "12345678");

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        testSteps = new ArrayList<>();
        numberOfStep = 0;
    }

    @AfterMethod
    public void tearDown() {
        log.info("   ################# END TEST " + currentTestMethodName + " ################# \n\n\n");
        driver.quit();
    }

    public void printTestDescription(String Description) {
        currentTestMethodName = getTestMethod();
        log.info("   ################# START TEST " + getTestMethod() + " #################");
        String[] splitted = Description.split("\n");
        for (String split : splitted) {
            log.info("    TEST DESCRIPTION:    " + split);
        }
    }

    protected void printStep(String msg) {
        log.info("@@@@ TEST STEP @@@@: " + msg);
        testSteps.add(msg);
        numberOfStep++;
    }

    private String getTestMethod() { return Thread.currentThread().getStackTrace()[3].getMethodName(); }
}
