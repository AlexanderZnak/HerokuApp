import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckHerokuApp {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void addRemoveElements() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[@onclick='addElement()']")).click();
        driver.findElement(By.xpath("//button[@onclick='addElement()']")).click();
        driver.findElement(By.className("added-manually")).click();
        Assert.assertEquals(driver.findElements(By.tagName("button")).size(), 2);
    }

    @Test
    public void checkBoxes() {
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        Assert.assertFalse(driver.findElements(By.xpath("//input[@type='checkbox']")).get(0).isSelected());
        driver.findElements(By.xpath("//input['chechbox']")).get(0).click();
        Assert.assertTrue(driver.findElements(By.xpath("//input[@type='checkbox']")).get(0).isSelected());
        Assert.assertTrue(driver.findElements(By.xpath("//input[@type='checkbox']")).get(1).isSelected());
        driver.findElements(By.xpath("//input['chechbox']")).get(1).click();
        Assert.assertFalse(driver.findElements(By.xpath("//input[@type='checkbox']")).get(1).isSelected());
    }

    @Test
    public void dropDown() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        Assert.assertTrue(driver.findElement(By.xpath("//option[@value='1']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//option[@value='2']")).isDisplayed());
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByValue("1");
        Assert.assertTrue(driver.findElement(By.xpath("//option[@value='1']")).isSelected());
        select.selectByValue("2");
        Assert.assertTrue(driver.findElement(By.xpath("//option[@value='2']")).isSelected());
    }

    @Test
    public void hovers() {
        driver.get("http://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//body//div[2]//div[1]//div[1]//img"))).build().perform();
        Assert.assertEquals(driver.findElement(By.xpath("//body//div[2]//div[1]//div[1]//div//h5")).getText(), "name: user1");
        driver.findElement(By.xpath("//a[@href='/users/1']")).click();
        String error = driver.findElement(By.xpath("//h1['Not Found']")).getText();
        Assert.assertEquals(error, "Not Found");
        driver.navigate().back();
        actions.moveToElement(driver.findElement(By.xpath("//body//div[2]//div[1]//div[2]//img"))).build().perform();
        Assert.assertEquals(driver.findElement(By.xpath("//body//div[2]//div[1]//div[2]//div//h5")).getText(), "name: user2");
        driver.findElement(By.xpath("//a[@href='/users/2']")).click();
        Assert.assertEquals(error, "Not Found");
        driver.navigate().back();
        actions.moveToElement(driver.findElement(By.xpath("//body//div[2]//div[1]//div[3]//img"))).build().perform();
        Assert.assertEquals(driver.findElement(By.xpath("//body//div[2]//div[1]//div[3]//div//h5")).getText(), "name: user3");
        driver.findElement(By.xpath("//a[@href='/users/3']")).click();
        Assert.assertEquals(error, "Not Found");
    }

    @Test
    public void inputs() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement element = driver.findElement(By.xpath("//input[@type='number']"));
        element.sendKeys("e", Keys.ARROW_UP);
        Assert.assertEquals(element.getAttribute("value"), "1");
        element.clear();
        element.sendKeys("e", Keys.ARROW_DOWN);
        Assert.assertEquals(element.getAttribute("value"), "-1");
        element.clear();
        element.sendKeys("15", Keys.ARROW_UP);
        Assert.assertEquals(element.getAttribute("value"), "16");
        element.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(element.getAttribute("value"), "15");
        element.clear();
        element.sendKeys("+15", Keys.ARROW_UP);
        Assert.assertEquals(element.getAttribute("value"), "16");
        element.clear();
        element.sendKeys("+15", Keys.ARROW_DOWN);
        Assert.assertEquals(element.getAttribute("value"), "14");
        element.clear();
        element.sendKeys("-15", Keys.ARROW_UP);
        Assert.assertEquals(element.getAttribute("value"), "-14");
        element.clear();
        element.sendKeys("-15", Keys.ARROW_DOWN);
        Assert.assertEquals(element.getAttribute("value"), "-16");
        element.clear();
        element.sendKeys(Keys.ARROW_UP);
        Assert.assertEquals(element.getAttribute("value"), "1");
        element.clear();
        element.sendKeys(Keys.ARROW_DOWN);
        Assert.assertEquals(element.getAttribute("value"), "-1");
    }

    @Test
    public void notificationMessage() {
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        driver.findElement(By.xpath("//a[@href='/notification_message']")).click();
        String s = driver.findElement(By.xpath("//div[@data-alert='']")).getText();
        String one = "Action successful\n×";
        Assert.assertEquals(s, one);
    }

    @Test
    public void dataTables() {
        driver.get("http://the-internet.herokuapp.com/tables#delete");
        Assert.assertEquals(driver.findElement(By.xpath("//table[1]//tr[1]//td[2]")).getText(), "John");
        Assert.assertEquals(driver.findElement(By.xpath("//table[1]//tr[2]//td[4]")).getText(), "$51.00");
        Assert.assertEquals(driver.findElement(By.xpath("//table[1]//tr[3]//td[1]")).getText(), "Doe");
        Assert.assertEquals(driver.findElement(By.xpath("//table[1]//tr[4]//td[6]//a[1]")).getText(), "edit");
        Assert.assertEquals(driver.findElement(By.xpath("//table[2]//tr[1]//td[1]")).getAttribute("class"), "last-name");
    }

    @Test
    public void tapos() {
        driver.get("http://the-internet.herokuapp.com/typos");
        String text = "Sometimes you'll see a typo, other times you won't.";
        Assert.assertEquals(driver.findElement(By.xpath("//div[2]//p[2]")).getText(), text);
    }

    @AfterMethod (alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
