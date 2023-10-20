package io.github.mfaisalkhatri.pages.guru99demosite;


import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static org.testng.Assert.assertEquals;

public class ButtonsPage {

    private final Page page;

    public ButtonsPage(final Page page) {
        this.page = page;
    }

    private Locator alertBtn() {
        return this.page.locator("#authentication > button");
    }

    public void doubleClickAndCheckAlertText(final String text) {
        this.page.onDialog(dialog -> {
            assertEquals(dialog.message(), text);
            dialog.dismiss();
        });

        alertBtn().dblclick();
    }

}
