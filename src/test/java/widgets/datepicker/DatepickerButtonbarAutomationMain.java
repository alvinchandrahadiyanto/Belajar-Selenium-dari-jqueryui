package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DatepickerButtonbarAutomationMain {

    public static void main(String[] args) {
        // Set up the ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/datepicker/#buttonbar";
        String expectedTitle = "jQuery UI Datepicker - Display button bar";

        try {
            driver.get(url);

            // Verify the title of the page
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match.");
            System.out.println("Page title verified successfully: " + actualTitle);

            // Switch to the iframe containing the datepicker example
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);
            System.out.println("Switched to the iframe.");

            // Find the datepicker input field
            WebElement datepickerInput = driver.findElement(By.id("datepicker"));
            datepickerInput.click(); // Open the datepicker
            System.out.println("Datepicker input field clicked.");

            // Verify if the button bar is displayed (check for the presence of buttons)
            try {
                WebElement todayButton = driver.findElement(By.xpath("//button[@class='ui-datepicker-current ui-state-default ui-corner-all']"));
                WebElement doneButton = driver.findElement(By.xpath("//button[@class='ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all']"));
                Assert.assertTrue(todayButton.isDisplayed(), "'Today' button is not displayed.");
                Assert.assertTrue(doneButton.isDisplayed(), "'Done' button is not displayed.");
                System.out.println("Button bar with 'Today' and 'Done' buttons is displayed.");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                Assert.fail("Button bar elements not found: " + e.getMessage());
            }

            // Switch back to the main content
            driver.switchTo().defaultContent();
            System.out.println("Switched back to the main content.");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        }
    }
}
