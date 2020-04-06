package page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;

    private final static Logger log = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    public void waitVisibility(By elementBy) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
        } catch (Exception e) {
            String eMes = "Timeout for visibility of element with locator " + elementBy;
            log.error(eMes + "\n" + e.getMessage());
            throw new RuntimeException(eMes);
        }
    }

    public void isElementDisplayed(By elementBy) {
        waitVisibility(elementBy);
        try {
            Assert.assertTrue(driver.findElement(elementBy).isDisplayed());
        } catch (Exception e) {
            String eMes = "Element with locator " + elementBy + " is not displayed.";
            log.error(eMes + "\n" + e.getMessage());
            throw new RuntimeException(eMes);
        }

    }

    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        WebElement element = driver.findElement(elementBy);
        element.clear();
        element.sendKeys(text);
    }
}
