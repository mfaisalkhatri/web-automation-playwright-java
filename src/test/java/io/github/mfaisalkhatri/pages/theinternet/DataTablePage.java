package io.github.mfaisalkhatri.pages.theinternet;

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
        return table.getByRole (AriaRole.ROW);
    }

    //    public int getTotalRowsInTable (final int tableNumber) {
    //        final Locator table = switch (tableNumber) {
    //            case 1 -> tableOne ();
    //            case 2 -> tableTwo ();
    //            default -> throw new IllegalStateException ("Invalid Table Number: " + tableNumber);
    //        };
    //
    //    }

    public void tableRecords (final int tableNumber) {
        final Locator table = switch (tableNumber) {
            case 1 -> tableOne ();
            case 2 -> tableTwo ();
            default -> throw new IllegalStateException ("Invalid Table Number: " + tableNumber);
        };

        final Locator rows = table.getByRole (AriaRole.ROW);
        for (int i = 1; i < rows.count (); i++) {
            System.out.println (rows.nth (i)
                .innerText ());
        }
    }

    public void getColumnsOfTableOne () {
        final Locator columnHeader = tableOne ().getByRole (AriaRole.COLUMNHEADER);
        for (int i = 0; i < columnHeader.count (); i++) {
            columnHeader.nth (i)
                .innerText ();
        }
    }

    private static Locator getRows (final Locator rows) {
        return rows;
    }

}
