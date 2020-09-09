package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IFramePage extends BasePage {
    public static final By NAME_OF_HEADER = By.xpath("//h3[contains(text(),'An iFrame containing the TinyMCE WYSIWYG Editor')]");
    public static final By IFRAME_ID = By.id("mce_0_ifr");
    public static final By TEXT_IN_IFRAME = By.id("tinymce");


    public IFramePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("http://the-internet.herokuapp.com/iframe");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(NAME_OF_HEADER));
    }

    public String getTextInIFrame() {
        driver.switchTo().frame(driver.findElement(IFRAME_ID));
        return driver.findElement(TEXT_IN_IFRAME).getText();
    }


}
