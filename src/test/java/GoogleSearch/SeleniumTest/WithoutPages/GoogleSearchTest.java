package GoogleSearch.SeleniumTest.WithoutPages;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;
import static org.junit.Assert.assertTrue;
import static selenium.core.CustomConditions.sizeOf;

public class GoogleSearchTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
    }

    //@AfterClass
    //public static void teardown() {
      //  driver.quit();
    //}

    @Test
    public void testSearchText() {

        openGoogle();
        searchText("Selenium automates browsers");
        assertResultsSize(10);
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        assertFirstResult(0, "Selenium - Web Browser Automation");
        clickOnNthLink(0);
       assertOpenedURL("Selenium - Web Browser Automation");
    }

    //public static List<WebElement> results(){
    List<WebElement> results = driver.findElements(By.cssSelector(".srg>.g"));
    //return results;}

    private void openGoogle() {
        driver.get("http://google.com/ncr");
    }

    private void searchText(String searchText) {
        driver.findElement(By.name("q")).sendKeys(searchText + Keys.ENTER);
    }

    private void assertResultsSize(int expectedSize) {
        sizeOf((By.cssSelector(".srg>.g")), expectedSize);
    }

    private void assertFirstResult(int index, String expectedText) {
        assertTrue(results.get(index).getText().contains(expectedText));
        //wait.until(textToBePresentInElementLocated(By.cssSelector(results + ":nth-child(1)"), expectedText));
    }

    private void clickOnNthLink(int resultLink) {
        results.get(resultLink).findElement(By.cssSelector(".r>a")).sendKeys(Keys.ENTER);
    }

    private void assertOpenedURL(String expectedTitle) {
        assertEquals(driver.getTitle(), expectedTitle);
        //wait.until(titleContains(expectedTitle));
    }
}

