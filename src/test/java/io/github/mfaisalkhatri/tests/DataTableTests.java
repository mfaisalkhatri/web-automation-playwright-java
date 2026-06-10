package io.github.mfaisalkhatri.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.mfaisalkhatri.pages.theinternet.DataTablePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataTableTests {

    private Playwright playwright;
    private Page       page;
    private DataTablePage dataTablePage;

    @BeforeMethod
    public void setup () {
        this.playwright = Playwright.create ();
        final Browser browser = this.playwright.chromium ()
            .launch (new BrowserType.LaunchOptions ().setHeadless (false)
                .setChannel ("chrome"));
        this.page = browser.newPage ();
        this.dataTablePage = new DataTablePage (this.page);
    }
    @Test
    public void testRowNumber() {
        this.page.navigate("https://the-internet.herokuapp.com/tables");
        assertEquals(this.dataTablePage.getTotalRowsInTable (1),5);
        assertEquals(this.dataTablePage.getTotalRowsInTable (2),5);
    }

    @AfterMethod
    public void tearDown () {
        this.page.close ();
        this.playwright.close ();
    }

}
