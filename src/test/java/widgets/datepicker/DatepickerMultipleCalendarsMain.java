package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DatepickerMultipleCalendarsMain {

    public static void main(String[] args) {
        WebDriver driver = null;
        final String url = "https://jqueryui.com/datepicker/#multiple-calendars";
        final String expectedTitle = "jQuery UI multiple-calendars";

        try {
            // Set up ChromeDriver path (replace with your actual path)
            driver = new ChromeDriver();
            driver.get(url);

            // Switch to the iframe containing the demo
            WebElement demoFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(demoFrame);

            // Find the datepicker input field and click it to open the calendar(s)
            WebElement datepickerInput = driver.findElement(By.id("datepicker"));
            datepickerInput.click();

            // Verify that three month containers are displayed
            java.util.List<WebElement> monthContainers = driver.findElements(By.xpath("//div[@id='ui-datepicker-div']/div[@class='ui-datepicker-group']"));
            Assert.assertEquals(monthContainers.size(), 3, "Should display three months");
            System.out.println("Successfully verified that three months are displayed.");

            // Switch back to the main content to verify the title
            driver.switchTo().defaultContent();
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Page title should be 'jQuery UI multiple-calendars'");
            System.out.println("Successfully verified that the page title is: " + actualTitle);

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