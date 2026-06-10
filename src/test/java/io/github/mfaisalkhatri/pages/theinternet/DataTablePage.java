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
        return this.page.getByRole (AriaRole.TABLE).first ();
    }

    public Locator tableTwo () {
        return this.page.getByRole (AriaRole.TABLE).nth (1);
    }

    public int getTotalRowsInTable (final int tableNumber) {
        final Locator table = switch (tableNumber) {
            case 1 -> tableOne ();
            case 2 -> tableTwo ();
            default -> throw new IllegalStateException ("Invalid Table Number: " + tableNumber);
        };
        return table.getByRole(AriaRole.ROW)
            .count ();
    }

}
