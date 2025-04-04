package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DatepickerDropdownAutomationMain {

    public static void main(String[] args) {
        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String websiteUrl = "https://jqueryui.com/datepicker/#dropdown-month-year";
        driver.get(websiteUrl);

        try {
            // Switch to the iframe containing the datepicker if necessary
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            // Find the datepicker input field and click to open the calendar
            WebElement datepickerInput = driver.findElement(By.id("datepicker"));
            datepickerInput.click();

            // Verify that the month and year dropdowns are present
            WebElement monthDropdownElement = driver.findElement(By.className("ui-datepicker-month"));
            Assert.assertTrue(monthDropdownElement.isDisplayed(), "Month dropdown is not displayed.");

            WebElement yearDropdownElement = driver.findElement(By.className("ui-datepicker-year"));
            Assert.assertTrue(yearDropdownElement.isDisplayed(), "Year dropdown is not displayed.");

            // Select a month from the dropdown
            Select monthDropdown = new Select(monthDropdownElement);
            String selectedMonth = "Dec";
            monthDropdown.selectByVisibleText(selectedMonth);
            Assert.assertEquals(monthDropdown.getFirstSelectedOption().getText(), selectedMonth, "Selected month is incorrect.");

            // Select a year from the dropdown
            Select yearDropdown = new Select(yearDropdownElement);
            String selectedYear = "2023";
            yearDropdown.selectByVisibleText(selectedYear);
            Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(), selectedYear, "Selected year is incorrect.");

            // Optionally, you can select a day as well
            WebElement dayToSelect = driver.findElement(By.xpath("//a[contains(@class, 'ui-state-default') and text()='25']"));
            dayToSelect.click();

            // After selecting the date, you can assert the value in the input field
            String expectedDate = "12/25/2023"; // Month/Day/Year format
            Assert.assertEquals(datepickerInput.getAttribute("value"), expectedDate, "Selected date in input field is incorrect.");

            // Switch back to the main content if you switched to an iframe
            driver.switchTo().defaultContent();

            System.out.println("Datepicker dropdown test completed successfully!");

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}