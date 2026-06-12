package io.github.mfaisalkhatri.tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import io.github.mfaisalkhatri.pages.theinternet.DataTablePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataTableTests {

    private Playwright    playwright;
    private Page          page;
    private DataTablePage dataTablePage;

    @BeforeTest
    public void setup () {
        this.playwright = Playwright.create ();
        final Browser browser = this.playwright.chromium ()
            .launch (new BrowserType.LaunchOptions ().setHeadless (false)
                .setChannel ("chrome"));
        this.page = browser.newPage ();
        this.dataTablePage = new DataTablePage (this.page);
    }

    @Test
    public void testRowNumbers () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        assertThat (this.dataTablePage.getTotalRowsInTable (this.dataTablePage.tableOne ())).hasCount (4);
        assertThat (this.dataTablePage.getTotalRowsInTable (this.dataTablePage.tableTwo ())).hasCount (4);
    }

    @Test
    public void testColumnNumbers () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        assertThat (this.dataTablePage.getColumnHeadersOfTable (this.dataTablePage.tableOne ())).hasCount (6);
        assertThat (this.dataTablePage.getColumnHeadersOfTable (this.dataTablePage.tableTwo ())).hasCount (6);

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

    @Test
    public void testTableData () {
        this.page.navigate ("https://the-internet.herokuapp.com/tables");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 0)).hasText ("Smith");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 1)).hasText ("John");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 2)).hasText ("jsmith@gmail.com");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 3)).hasText ("$50.00");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 4)).hasText (
            "http://www.jsmith.com");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 5).getByRole (AriaRole.LINK).first ()).hasAttribute ("href", "#edit");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableOne (), 0, 5).getByRole (AriaRole.LINK).nth(1)).hasAttribute ("href", "#delete");

        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableTwo (), 3, 1)).hasText ("Tim");
        assertThat (this.dataTablePage.getCell (this.dataTablePage.tableTwo (), 3, 1)).hasText ("Tim");
    }

    @AfterTest
    public void tearDown () {
        this.page.close ();
        this.playwright.close ();
    }
}