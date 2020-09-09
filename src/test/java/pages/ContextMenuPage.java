package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertEquals;

public class ContextMenuPage extends BasePage {
    public static final By CONTEXT_MENU_LOCATOR = By.id("hot-spot");
    Actions actions;

    public ContextMenuPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("http://the-internet.herokuapp.com/context_menu");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_LOCATOR));
    }

    public void rightClickOnContextMenu() {
        actions = new Actions(driver);
        actions.contextClick(driver.findElement(CONTEXT_MENU_LOCATOR)).perform();
    }

    public void alertIsAccept() {
        try {
            driverWait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            Assert.fail("Alert is not appeared");
        }
        Alert alert = driver.switchTo().alert();
        String textAlert = alert.getText();
        alert.accept();
        assertEquals(textAlert, "You selected a context menu");
    }
}
