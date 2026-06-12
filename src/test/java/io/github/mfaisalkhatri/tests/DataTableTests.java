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

    private Playwright    playwright;
    private Page          page;
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
    public void testRowNumber () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        assertThat (this.dataTablePage.getTotalRowsInTable (this.dataTablePage.tableOne ())).hasCount (4);
        assertThat (this.dataTablePage.getTotalRowsInTable (this.dataTablePage.tableTwo ())).hasCount (4);
    }

    @Test
    public void testPrintTableRecords () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        System.out.println ("Printing records of Table 1");
        this.dataTablePage.getAllRecordsFromTable (this.dataTablePage.tableOne ())
            .forEach (System.out::println);
        System.out.println ("Printing records of Table 2");
        this.dataTablePage.getAllRecordsFromTable (this.dataTablePage.tableTwo ())
            .forEach (System.out::println);
    }

    @Test
    public void testTableOneColumnHeaders () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        final String[] expectedColumnHeaders = { "Last Name", "First Name", "Email", "Due", "Web Site", "Action" };
        assertThat (this.dataTablePage.getColumnHeadersOfTable (this.dataTablePage.tableOne ())).hasText (
            expectedColumnHeaders);
    }



    @AfterMethod
    public void tearDown () {
        this.page.close ();
        this.playwright.close ();
    }

}
