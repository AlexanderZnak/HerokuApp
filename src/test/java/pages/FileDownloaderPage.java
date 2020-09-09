package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class FileDownloaderPage extends BasePage {
    public static final By LOCATOR_CONTENT_FOR_CHECK = By.id("content");
    String downloadFile = "//a[contains(text(),'%s')]";

    public FileDownloaderPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("http://the-internet.herokuapp.com/download");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(LOCATOR_CONTENT_FOR_CHECK));
    }

    public void downloadFile(String fileName) {
        driver.findElement(By.xpath(String.format(downloadFile, fileName))).click();
    }

    public int timeOut(String fileName) {
        int i = 1000;
        while (!getConfirmationOfDownloadedFile(fileName)) {
            i++;
        }
        return i;
    }

    public boolean getConfirmationOfDownloadedFile(String fileName) {
        File folder = new File("src/test/resources");
        File[] listOfFiles = folder.listFiles();
        boolean fileIs = false;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().equals(fileName)) {
                fileIs = true;
            }
        }
        return fileIs;

    }

}
