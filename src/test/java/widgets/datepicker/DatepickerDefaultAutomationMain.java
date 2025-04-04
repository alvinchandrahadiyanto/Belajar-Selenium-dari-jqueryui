package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatepickerDefaultAutomationMain {

    private static WebDriver driver;
    private static final String URL = "https://jqueryui.com/datepicker/#default";

    public static void main(String[] args) {
        setUp();
        verifyDefaultDatepicker();
        selectSpecificDate();
        tearDown();
    }

    public static void setUp() {
        // Set the path to your ChromeDriver executable
        // You might need to adjust this based on your system
        driver = new ChromeDriver();
        driver.get(URL);

        // Switch to the iframe containing the actual demo
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        driver.switchTo().frame(iframe);
    }

    public static void verifyDefaultDatepicker() {
        WebElement datepickerInput = driver.findElement(By.id("datepicker"));

        // Click on the input field to open the datepicker
        datepickerInput.click();

        // Verify that the datepicker element is visible
        WebElement datepickerDiv = driver.findElement(By.id("ui-datepicker-div"));
        Assert.assertTrue(datepickerDiv.isDisplayed(), "Datepicker should be visible after clicking the input field.");

        // Example: Verify the current month (you might need to adjust the locator)
        WebElement currentMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
        System.out.println("Current Month: " + currentMonth.getText());

        // Example: Verify the current year (you might need to adjust the locator)
        WebElement currentYear = driver.findElement(By.xpath("//span[@class='ui-datepicker-year']"));
        System.out.println("Current Year: " + currentYear.getText());

        System.out.println("Default datepicker verification passed.");
    }

    public static void selectSpecificDate() {
        WebElement datepickerInput = driver.findElement(By.id("datepicker"));
        datepickerInput.click();

        int dayToSelect = 18; // Example: Select the 18th of the current month

        // Find the table containing the calendar days
        WebElement datepickerTable = driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']"));

        // Find all the day cells in the table
        java.util.List<WebElement> dayCells = datepickerTable.findElements(By.tagName("td"));

        for (WebElement cell : dayCells) {
            try {
                WebElement link = cell.findElement(By.tagName("a"));
                if (link.getText().equals(String.valueOf(dayToSelect))) {
                    link.click();
                    break;
                }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                // Some cells might not have a link (e.g., empty days)
                continue;
            }
        }

        // Verify that the selected date is now in the input field
        Assert.assertEquals(datepickerInput.getAttribute("value"), getCurrentMonthDayYearFormatted(dayToSelect), "Selected date should match the input field value.");
        System.out.println("Successfully selected date: " + datepickerInput.getAttribute("value"));
    }

    // Helper method to get the current month, day, and year in the expected format (e.g., "MM/DD/YYYY")
    private static String getCurrentMonthDayYearFormatted(int day) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate specificDate = currentDate.withDayOfMonth(day);
        return specificDate.format(formatter);
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
