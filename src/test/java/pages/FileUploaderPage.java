package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class FileUploaderPage extends BasePage {
    public static final By UPLOAD_BUTTON = By.id("file-submit");
    public static final By SELECT_FILE_BY_BUTTON = By.id("file-upload");
    public static final By SELECT_FILE_BY_DRAG_DROP_UPLOAD = By.id("drag-drop-upload");
    public static final By NAME_OF_UPLOADED_FILE_BY_BUTTON = By.id("uploaded-files");
    public static final By NAME_OF_UPLOADED_FILE_BY_DRAG_DROP = By.cssSelector("[class='dz-filename']");

    public FileUploaderPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("http://the-internet.herokuapp.com/upload");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(UPLOAD_BUTTON));
    }

    public void selectFileByButton(String filePath) {
        File file = new File(filePath);
        driver.findElement(SELECT_FILE_BY_BUTTON).sendKeys(file.getAbsolutePath());
    }

    public void selectFileByDragDrop(String filePath) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        File file = new File(filePath);
        String jsDropFile =
                "var target = arguments[0]," +
                        "    offsetX = arguments[1]," +
                        "    offsetY = arguments[2]," +
                        "    document = target.ownerDocument || document," +
                        "    window = document.defaultView || window;" +
                        "" +
                        "var input = document.createElement('INPUT');" +
                        "input.type = 'file';" +
                        "input.style.display = 'none';" +
                        "input.onchange = function () {" +
                        "  var rect = target.getBoundingClientRect()," +
                        "      x = rect.left + (offsetX || (rect.width >> 1))," +
                        "      y = rect.top + (offsetY || (rect.height >> 1))," +
                        "      dataTransfer = { files: this.files };" +
                        "" +
                        "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
                        "    var evt = document.createEvent('MouseEvent');" +
                        "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
                        "    evt.dataTransfer = dataTransfer;" +
                        "    target.dispatchEvent(evt);" +
                        "  });" +
                        "" +
                        "  setTimeout(function () { document.body.removeChild(input); }, 25);" +
                        "};" +
                        "document.body.appendChild(input);" +
                        "return input;";

        WebElement input = (WebElement) jse.executeScript(jsDropFile, driver.findElement(SELECT_FILE_BY_DRAG_DROP_UPLOAD), 0, 0);
        input.sendKeys(file.getAbsolutePath());
        driverWait.until(ExpectedConditions.stalenessOf(input));
    }

    public void uploadFile() {
        driver.findElement(UPLOAD_BUTTON).click();
    }

    public String getNameForSelectByButton() {
        return driver.findElement(NAME_OF_UPLOADED_FILE_BY_BUTTON).getText();
    }

    public String getNameForSelectByDragDrop() {
        return driver.findElement(NAME_OF_UPLOADED_FILE_BY_DRAG_DROP).getText();
    }
}
