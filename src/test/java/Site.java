import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Site {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("C:/Users/Саша/IdeaProjects/HerokuApp/src/test/resources/lesson7.html");
    }

    @Test
    public void findTable() {
        Assert.assertTrue(driver.findElement(By.id("t01")).isDisplayed());
    }

    @Test
    public void checkName() {
        Assert.assertEquals(driver.findElement(By.xpath("//tr[1]//th[1]")).getText(), "First name");
    }

    @Test
    public void checkCaption() {
        Assert.assertEquals(driver.findElement(By.tagName("caption")).getText(), "Just a sheet");
    }

    @Test
    public void checkInput() {
        WebElement element = driver.findElement(By.id("inp"));
        element.sendKeys("blabla");
        Assert.assertEquals(element.getAttribute("value"), "blabla");
    }

    @Test
    public void checkBox() {
        WebElement check1 = driver.findElement(By.id("student1"));
        WebElement check2 = driver.findElement(By.id("student2"));
        WebElement check3 = driver.findElement(By.id("student3"));
        Assert.assertTrue(check1.isEnabled());
        Assert.assertTrue(check2.isEnabled());
        Assert.assertTrue(check3.isEnabled());
        check1.click();
        Assert.assertTrue(check1.isSelected());
        check1.click();
        Assert.assertFalse(check1.isSelected());
    }

    @Test
    public void dropDown() {
        WebElement element = driver.findElement(By.xpath("//option[1]"));
        WebElement element2 = driver.findElement(By.xpath("//option[2]"));
        WebElement element3 = driver.findElement(By.xpath("//option[3]"));
        Assert.assertTrue(element.isEnabled());
        Assert.assertTrue(element2.isEnabled());
        Assert.assertTrue(element3.isEnabled());
        WebElement elemSelect = driver.findElement(By.id("sel"));
        Select select = new Select(elemSelect);
        select.selectByVisibleText("Petya");
        Assert.assertTrue(element3.isSelected());
    }

    @Test
    public void checkButton() {
        WebElement element = driver.findElement(By.className("button"));
        element.click();
        Alert alert = driver.switchTo().alert();
        String s = alert.getText();
        alert.accept();
        Assert.assertEquals(s, "Hello");
    }

    @Test
    public void checkImgAndLink() {
        WebElement element = driver.findElement(By.xpath("//img"));
        element.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://teachmeskills.by/");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
