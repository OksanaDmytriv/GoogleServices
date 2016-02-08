package Google;

import com.codeborne.selenide.SelenideElement;
import org.junit.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchTest {
    @Test
    public void testSearchText() {
        openURL();
enterText("Selenium automates browsers").pressEnter();

    }

    SelenideElement searchField = $("#gs_htif0");
    private void openURL() {
        open("http://google.com/ncr");
    }

    public SelenideElement enterText(String newText) {
        searchField.doubleClick();
        return searchField.find(cssClass("editing")).$(".edit").setValue(newText);
    }


}
