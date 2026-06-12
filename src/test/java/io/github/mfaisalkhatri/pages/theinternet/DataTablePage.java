package io.github.mfaisalkhatri.pages.theinternet;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DataTablePage {

    private final Page page;

    public DataTablePage (final Page page) {
        this.page = page;
    }

    public Locator tableOne () {
        return this.page.getByRole (AriaRole.TABLE)
            .first ();
    }

    public Locator tableTwo () {
        return this.page.getByRole (AriaRole.TABLE)
            .nth (1);
    }

    public Locator getTotalRowsInTable (final Locator table) {
        return table.locator ("tbody")
            .getByRole (AriaRole.ROW);
    }

    public List<String> getAllRecordsFromTable (final Locator table) {
        return table.locator ("tbody")
            .getByRole (AriaRole.ROW)
            .allInnerTexts ();
    }

    public Locator getColumnHeadersOfTable (final Locator table) {
        return table.getByRole (AriaRole.COLUMNHEADER);
    }

    public Locator getCell (final Locator table, final int rowNumber, final int columnNumber) {
        return table.locator ("tbody")
            .getByRole (AriaRole.ROW)
            .nth (rowNumber)
            .locator ("td")
            .nth (columnNumber);
    }
}
