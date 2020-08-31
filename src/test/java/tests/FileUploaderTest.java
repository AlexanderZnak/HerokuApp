package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class FileUploaderTest extends BaseTest {

    @Test
    public void uploadFileViaButton() {
        fileUploaderPage.openPage();
        fileUploaderPage.selectFileByButton("src/test/resources/chromedriver.exe");
        fileUploaderPage.uploadFile();
        assertEquals(fileUploaderPage.getNameForSelectByButton(), "chromedriver.exe");
    }

    @Test
    public void uploadFileViaDragDrop() {
        fileUploaderPage.openPage();
        fileUploaderPage.selectFileByDragDrop("src/test/resources/chromedriver.exe");
        assertEquals(fileUploaderPage.getNameForSelectByDragDrop(), "chromedriver.exe");
    }
}
