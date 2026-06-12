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
        assertThat (this.dataTablePage.getTotalRowsInTable (this.dataTablePage.tableOne ())).hasCount (5);
        assertThat (this.dataTablePage.getTotalRowsInTable (this.dataTablePage.tableTwo ())).hasCount (5);
    }

    @Test
    public void testPrintTableRecords () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        System.out.println ("Printing records of Table 1");
        this.dataTablePage.tableRecords (1);
        System.out.println ("Printing records of Table 2");
        this.dataTablePage.tableRecords (2);
    }

//    @Test
//    public void testTableOneColumnHeaders () {
//        this.page.navigate ("https://the-internet.herokuapp.com/tables");
//        assertEquals (this.dataTablePage.getColumnsOfTableOne (),
//            "Last Name\tFirst Name\tEmail\tDue\tWeb Site\tAction");
//
//    }

    @AfterMethod
    public void tearDown () {
        this.page.close ();
        this.playwright.close ();
    }

}
