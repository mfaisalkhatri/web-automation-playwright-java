package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

import static org.testng.Assert.assertEquals;

public class ScreenshotTests extends BaseTest{

    @Test
    public void takeScreenshotTest() {
        final String websiteLink = "https://the-internet.herokuapp.com/";
        final Page page = this.browserManager.getPage();
        page.navigate(websiteLink);

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd_hh:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./screenshots/screenshot_" + dateFormat.format(new Date()) + ".png")));


        assertEquals(page.url(), websiteLink);

        final String pageTitle = page.title();
        assertEquals(pageTitle, "The Internet");
    }
}
