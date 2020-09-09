package tests;

import org.testng.annotations.Test;

public class ContextMenuTest extends BaseTest {

    @Test
    public void checkContextMenu() {
        contextMenuPage.openPage();
        contextMenuPage.rightClickOnContextMenu();
        contextMenuPage.alertIsAccept();
    }
}
