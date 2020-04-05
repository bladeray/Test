package test;

import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import java.util.Date;

public class E2eTests extends BaseTest {

    private final static Logger log = Logger.getLogger(E2eTests.class);

    @Test
    public void oldUser() {
        printTestDescription("Authorization with incorrect data. \n" +
                "Authorization with correct data.");

        printStep("Open Login page. Enter incorrect Login and Password and click on Login button. Verify that errors have appeared.");
        loginPage.goTo()
                .fillInLogin(correctData.getKey())
                .fillInPassword(String.valueOf(new Date().getTime()))
                .clickLoginButton()
                .isLoginIncorrect();

        printStep("Enter correct Login and Password and click on Login button. Verify that success page appeared.");
        loginPage.fillInLogin(correctData.getKey())
                .fillInPassword(correctData.getValue())
                .clickLoginButton()
                .isLoginCorrect();
    }

    @Test
    public void newUser() {
        final String correctNewLogin = String.valueOf(new Date().getTime());

        printTestDescription("Registration with incorrect data. \n" +
                "Registration with correct data. \n" +
                "Authorization with correct data.");

        printStep("Open Registration page.");
        loginPage.goTo()
                .clickRegistrationButton();

        printStep("Verify that Registration page has opened. Enter incorrect registration data and click on Register button. Verify that errors have appeared.");
        registrationPage.checkPageIsCorrect()
                .fillInLogin(String.valueOf(new Date().getTime()))
                .fillInEmail("com")
                .fillInPassword("123456")
                .fillInPasswordConfirm("12345")
                .clickRegistrationButton()
                .isRegistrationIncorrect();

        printStep("Enter correct registration data and click on Register button. Verify that success page appeared.");
        registrationPage.fillInLogin(correctNewLogin)
                .fillInEmail("test@test.com")
                .fillInPassword(correctData.getValue())
                .fillInPasswordConfirm(correctData.getValue())
                .clickRegistrationButton()
                .isRegistrationCorrect();

        printStep("Open Login page. Enter correct new authorization data and click on Login button. Verify that success page appeared.");
        registrationPage.goToLoginFromSuccessRegistration();

        loginPage.goTo()
                .fillInLogin(correctNewLogin)
                .fillInPassword(correctData.getValue())
                .clickLoginButton()
                .isLoginCorrect();
    }
}
