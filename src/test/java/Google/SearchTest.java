package Google;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SearchTest {
    @Test
    public void testSearchText() {

        openURL();
        enterText("Selenium automates browsers");
        assertResultsSize(10);
        assertFirstResultText("Selenium - Web Browser Automation");
        clickOnResultLink("Selenium - Web Browser Automation");
        assertOpenURL();
    }

    SelenideElement searchField = $(By.name("q"));
    SelenideElement firstResult = $(".srg .g .rc .r>a");
    ElementsCollection searchResult = $$(".srg .g .rc .r>a");

    private void openURL() {
        open("http://google.com/ncr");
    }

    private void enterText(String searchText) {
        searchField.setValue(searchText).pressEnter();
    }

   private void assertResultsSize(int expectedSize) {
    searchResult.shouldHave(size(expectedSize));
    }

    private void assertFirstResultText(String expectedText) {
        firstResult.shouldHave(text(expectedText));
    }

    private void clickOnResultLink(String expectedText) {
        searchResult.findBy(text(expectedText)).click();
    }

    private void assertOpenURL() {
        url().equals("http://www.seleniumhq.org/");
    }
}
