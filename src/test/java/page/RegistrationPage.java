package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private final By loginLocator = By.name("login");
    private final By emailLocator = By.name("email");
    private final By passwordLocator = By.name("password");
    private final By passwordConfirmLocator = By.name("password_confirm");
    private final By sexLocator = By.name("sex");
    private final By newsLocator = By.name("news");
    private final By successLocator = By.xpath("//span[text()='Congratulations! You have been successfully registered. Now you can ']");
    private final By failureLocator = By.className("error");
    private final By loginButtonLocator = By.linkText("login");
    private final By registrationButtonLocator = By.xpath("//input[@type='submit']");

    private final static Logger log = Logger.getLogger(RegistrationPage.class);

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public RegistrationPage fillInLogin(String login) {
        log.info("Enter in the Login field: \"" + login + "\"");
        writeText(loginLocator, login);
        return this;
    }

    public RegistrationPage fillInEmail(String email) {
        log.info("Enter in the Email field: \"" + email + "\"");
        writeText(emailLocator, email);
        return this;
    }

    public RegistrationPage fillInPassword(String password) {
        log.info("Enter in the Password field: \"" + password + "\"");
        writeText(passwordLocator, password);
        return this;
    }

    public RegistrationPage fillInPasswordConfirm(String passwordConfirm) {
        log.info("Enter in the Confirm password field: \"" + passwordConfirm + "\"");
        writeText(passwordConfirmLocator, passwordConfirm);
        return this;
    }

    public RegistrationPage isRegistrationCorrect() {
        log.info("Check that the registration was a success.");
        isElementDisplayed(successLocator);
        return this;
    }

    public RegistrationPage isRegistrationIncorrect() {
        log.info("Check that the registration was a failure.");
        isElementDisplayed(failureLocator);
        return this;
    }

    public RegistrationPage goToLoginFromSuccessRegistration() {
        log.info("Open Login page from Success registration page.");
        click(loginButtonLocator);
        return this;
    }

    public RegistrationPage clickRegistrationButton() {
        log.info("Click on Register button.");
        click(registrationButtonLocator);
        return this;
    }

    public RegistrationPage checkPageIsCorrect() {
        log.info("Check that Registration page was opened.");
        isElementDisplayed(loginLocator);
        isElementDisplayed(emailLocator);
        isElementDisplayed(passwordLocator);
        isElementDisplayed(passwordConfirmLocator);
        isElementDisplayed(sexLocator);
        isElementDisplayed(newsLocator);
        return this;
    }
}
