package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class IFrameTest extends BaseTest {

    @Test
    public void checkIFrameText() {
        iFramePage.openPage();
        assertEquals(iFramePage.getTextInIFrame(), "Your content goes here.");
    }
}
