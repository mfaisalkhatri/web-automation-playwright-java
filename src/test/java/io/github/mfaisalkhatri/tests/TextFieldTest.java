package io.github.mfaisalkhatri.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TextFieldTest {

    private Playwright playwright;
    private Page page;


    @BeforeClass
    public void setup() {
        this.playwright = Playwright.create();
        final Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        this.page = browser.newPage();
    }

    @Test
    public void locateFirstNameByLabel() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator firstNameField = page.getByLabel("First name");
        firstNameField.fill("Faisal");
    }

    @Test
    public void locateFirstNameByPlaceholder() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator firstNameField = page.getByPlaceholder("Your first name *");
        firstNameField.fill("John");
    }

    @Test
    public void locateFirstNameByRole() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator firstNameField = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First name"));
        firstNameField.fill("Tom");
    }

    @Test
    public void testFocusOnField() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator emailAddressField = page.getByLabel("Email address");
        emailAddressField.focus();
        assertThat(emailAddressField).isFocused();
    }

    @Test
    public void testGetValuesFromTextField() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator emailAddressField = page.getByLabel("Email address");

        String emailAddress = "faisal.k@demo.com";
        emailAddressField.fill(emailAddress);

        String emailValue = emailAddressField.inputValue();
        System.out.println(emailValue);

        assertThat(emailAddressField).hasValue(emailValue);
    }

    @Test
    public void testClearFieldValues() {
        page.navigate("https://practicesoftwaretesting.com/contact");
        Locator messageField = page.getByLabel("Message *");
        String messageOne = "This is the first message";
        messageField.fill(messageOne);

        messageField.clear();
        String messageTwo = "This is the second message";
        messageField.fill(messageTwo);

        assertThat(messageField).hasValue(messageTwo);
    }

    @Test
    public void testKeyPress() {
        page.navigate("https://the-internet.herokuapp.com/key_presses");
        Locator textBox = page.locator("#target");
        textBox.press("Alt");
        Locator resultText = page.locator("p#result");

        assertThat(resultText).containsText("ALT");

        textBox.press("Shift");
        assertThat(resultText).containsText("SHIFT");

        textBox.press("N");
        assertThat(resultText).containsText("N");

        textBox.press("9");
        assertThat(resultText).containsText("9");
    }
}
