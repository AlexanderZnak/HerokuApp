package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DynamicControlsTest extends BaseTest {

    @Test
    public void checkboxIsRemoved() {
        dynamicControlsPage.openPage();
        dynamicControlsPage.removeCheckbox();
        assertFalse(dynamicControlsPage.checkboxIsDisplayed());
    }

    @Test
    public void checkboxIsAdded() {
        dynamicControlsPage.openPage();
        dynamicControlsPage.removeCheckbox();
        dynamicControlsPage.addCheckbox();
        assertTrue(dynamicControlsPage.checkboxIsDisplayed());
    }

    @Test
    public void inputEnabled() {
        dynamicControlsPage.openPage();
        dynamicControlsPage.enableInput();
        assertTrue(dynamicControlsPage.inputIsEnabled());
    }

    @Test
    public void inputDisabled() {
        dynamicControlsPage.openPage();
        dynamicControlsPage.enableInput();
        dynamicControlsPage.disableInput();
        assertFalse(dynamicControlsPage.inputIsEnabled());
    }
}
