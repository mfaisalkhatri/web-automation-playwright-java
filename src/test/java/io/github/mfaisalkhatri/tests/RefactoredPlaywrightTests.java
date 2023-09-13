package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RefactoredPlaywrightTests extends BaseTest {


    @Test
    public void websiteTest() {
        final Page page = this.browserManager.getPage();
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        final String pageTitle = page.title();
        assertEquals(pageTitle, "Your Store");
    }

}
