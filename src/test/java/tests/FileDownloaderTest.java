package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class FileDownloaderTest extends BaseTest {

    @Test
    public void downloadFile() throws InterruptedException {
        fileDownloaderPage.openPage();
        fileDownloaderPage.downloadFile("logo.png");
        Thread.sleep(fileDownloaderPage.timeOut("logo.png"));
        assertTrue("File wasn't downloaded", fileDownloaderPage.getConfirmationOfDownloadedFile("logo.png"));
    }
}
