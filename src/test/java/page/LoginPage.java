package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final String SITE_URL = "http://test.mutc.ru/cgi-bin/bug.pl?action=login";

    private final By loginLocator = By.name("login");
    private final By passwordLocator = By.name("password");
    private final By loginButtonLocator = By.xpath("//input[@type='submit']");
    private final By registrationButtonLocator = By.linkText("Register a new user");
    private final By successLocator = By.xpath("//span[text()='Successfully authorized!']");
    private final By failureLocator = By.className("error");

    private final static Logger log = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goTo() {
        log.info("Open Login page.");
        driver.get(SITE_URL);
        return this;
    }

    public LoginPage fillInLogin(String login) {
        log.info("Enter in the Login field: \"" + login + "\"");
        writeText(loginLocator, login);
        return this;
    }

    public LoginPage fillInPassword(String password) {
        log.info("Enter in the Password field: \"" + password + "\"");
        writeText(passwordLocator, password);
        return this;
    }

    public LoginPage clickLoginButton() {
        log.info("Click on Login button.");
        click(loginButtonLocator);
        return this;
    }

    public LoginPage clickRegistrationButton() {
        log.info("Open Registration page.");
        click(registrationButtonLocator);
        return this;
    }

    public LoginPage isLoginCorrect() {
        log.info("Check that the authorization was a success.");
        isElementDisplayed(successLocator);
        return this;
    }

    public LoginPage isLoginIncorrect() {
        log.info("Check that the authorization was a failure.");
        isElementDisplayed(failureLocator);
        return this;
    }
}
