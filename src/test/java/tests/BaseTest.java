package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;
    ContextMenuPage contextMenuPage;
    DynamicControlsPage dynamicControlsPage;
    FileUploaderPage fileUploaderPage;
    IFramePage iFramePage;
    FileDownloaderPage fileDownloaderPage;


    @BeforeMethod(alwaysRun = true)
    public void setAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        File file = new File("src/test/resources");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", file.getAbsolutePath());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        contextMenuPage = new ContextMenuPage(driver);
        dynamicControlsPage = new DynamicControlsPage(driver);
        fileUploaderPage = new FileUploaderPage(driver);
        iFramePage = new IFramePage(driver);
        fileDownloaderPage = new FileDownloaderPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}
