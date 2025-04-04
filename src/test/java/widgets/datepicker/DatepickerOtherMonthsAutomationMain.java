package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class DatepickerOtherMonthsAutomationMain {

    public static void main(String[] args) {
        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String websiteURL = "https://jqueryui.com/datepicker/#other-months";
        driver.get(websiteURL);

        try {
            // Switch to the iframe containing the datepicker demo
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            // **Test Case 1: Verify that the datepicker shows days from other months**
            System.out.println("Starting Test Case 1: Verify Datepicker Shows Other Months");
            WebElement datepickerInput1 = driver.findElement(By.id("datepicker"));
            datepickerInput1.click();

            WebElement datepicker1 = driver.findElement(By.id("ui-datepicker-div"));
            java.util.List<WebElement> otherMonthDays = datepicker1.findElements(By.className("ui-datepicker-other-month"));
            Assert.assertTrue(otherMonthDays.size() > 0, "Test Case 1 Failed: Datepicker should display days from other months.");
            for (WebElement day : otherMonthDays) {
                Assert.assertTrue(day.isDisplayed(), "Test Case 1 Failed: A day from another month is not visible.");
            }
            System.out.println("Test Case 1 Passed: Datepicker shows days from other months.");
            datepickerInput1.click(); // Close the datepicker

            // **Test Case 2: Verify that a date can be selected from another month**
            System.out.println("\nStarting Test Case 2: Verify Selection of Date from Other Month");
            WebElement datepickerInput2 = driver.findElement(By.id("datepicker"));
            datepickerInput2.click();

            WebElement datepicker2 = driver.findElement(By.id("ui-datepicker-div"));
            WebElement currentMonthElement = driver.findElement(By.className("ui-datepicker-month"));
            String currentMonth = currentMonthElement.getText();

            java.util.List<WebElement> previousMonthDays = datepicker2.findElements(By.xpath("//td[@class=' ui-datepicker-other-month ui-datepicker-unselectable']/a"));
            if (!previousMonthDays.isEmpty()) {
                WebElement dayToClick = previousMonthDays.get(0);
                String selectedDayText = dayToClick.getText();
                dayToClick.click();

                String selectedDate = datepickerInput2.getAttribute("value");
                Assert.assertFalse(selectedDate.contains(currentMonth), "Test Case 2 Failed: Selecting a day from another month should change the month in the input field.");
                System.out.println("Test Case 2 Passed: Selected date from another month: " + selectedDate);

                // Optionally, test selecting from the next month (requires clicking the "Next" button)
                datepickerInput2.click(); // Open again
                WebElement nextButton = driver.findElement(By.xpath("//a[@data-handler='next']"));
                nextButton.click();
                java.util.List<WebElement> nextMonthDays = datepicker2.findElements(By.xpath("//td[@class=' ui-datepicker-other-month ui-datepicker-unselectable']/a"));
                if (!nextMonthDays.isEmpty()) {
                    nextMonthDays.get(0).click();
                    String selectedNextMonthDate = datepickerInput2.getAttribute("value");
                    System.out.println("Selected date from next month: " + selectedNextMonthDate);
                } else {
                    System.out.println("No days from the next month are visible to select in this context.");
                }
            } else {
                System.out.println("Test Case 2 Inconclusive: No days from the previous month are visible to select.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
