package Google;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class GoogleSearchTest {
    @Test
    public void testSearchText() {

        openGoogle();
        searchText("Selenium automates browsers");
        assertResultsSize(10);
        assertNthResult(0, "Selenium - Web Browser Automation");
        followNthLink(0);
        assertOpenedURL("http://www.seleniumhq.org/");
    }

    SelenideElement searchField = $(By.name("q"));
    ElementsCollection results = $$(".srg>.g");

    private void openGoogle() {
        open("http://google.com/ncr");
    }

    private void searchText(String searchText) {
        searchField.setValue(searchText).pressEnter();
    }

    private void assertResultsSize(int expectedSize) {
        results.shouldHave(size(expectedSize));
    }

    private void assertNthResult(int resultNumber, String expectedText) {
        results.get(resultNumber).shouldHave(text(expectedText));
    }

    private void followNthLink(int resultLink) {
        results.get(resultLink).$(".r>a").click();
    }

    private void assertOpenedURL(String expectedURL) {
        url().equals(expectedURL);
    }
}
