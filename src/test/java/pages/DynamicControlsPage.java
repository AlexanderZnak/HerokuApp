package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DynamicControlsPage extends BasePage {
    public static final By REMOVE_BUTTON = By.xpath("//button[contains(text(),'Remove')]");
    public static final By ADD_BUTTON = By.xpath("//button[contains(text(),'Add')]");
    public static final By CHECKBOX = By.id("checkbox");
    public static final By MESSAGE = By.id("message");
    public static final By ENABLE_BUTTON = By.xpath("//button[contains(text(),'Enable')]");
    public static final By DISABLE_BUTTON = By.xpath("//button[contains(text(),'Disable')]");
    public static final By INPUT = By.cssSelector("[type='text']");

    public DynamicControlsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(REMOVE_BUTTON));
    }

    public boolean checkboxIsDisplayed() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE));
        } catch (TimeoutException e) {
            Assert.fail("Message is not appeared");
        }
        try {
            return driver.findElement(CHECKBOX).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void removeCheckbox() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(REMOVE_BUTTON));
        driver.findElement(REMOVE_BUTTON).click();
    }

    public void addCheckbox() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(ADD_BUTTON));
        driver.findElement(ADD_BUTTON).click();
    }

    public void enableInput() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(ENABLE_BUTTON));
        driver.findElement(ENABLE_BUTTON).click();
    }

    public void disableInput() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(DISABLE_BUTTON));
        driver.findElement(DISABLE_BUTTON).click();
    }

    public boolean inputIsEnabled() {
        try {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE));
        } catch (TimeoutException e) {
            Assert.fail("Message is not appeared");
        }
        try {
            return driver.findElement(INPUT).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
