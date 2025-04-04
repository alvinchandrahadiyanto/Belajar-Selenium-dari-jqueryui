package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class InlineDatePickerAutomationMain {

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            // Set up ChromeDriver path (adjust if needed)
            driver = new ChromeDriver();
            String url = "https://jqueryui.com/datepicker/#inline";
            driver.get(url);

            // Switch to the iframe containing the inline datepicker
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            // The datepicker is directly embedded in the page, so we can interact with it

            // Example: Get the current month and year displayed
            WebElement currentMonthYear = driver.findElement(By.className("ui-datepicker-title"));
            System.out.println("Current Month and Year: " + currentMonthYear.getText());

            // Example: Select a specific date (e.g., the 15th of the current month)
            WebElement dayToSelect = driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[text()='15']"));
            dayToSelect.click();

            // After selecting a date, the datepicker might disappear or remain visible depending on the implementation.
            // In this inline example, it usually remains visible.

            // You can add further assertions here to check if the date was selected correctly
            // For instance, you might check if an associated input field (if one exists in a real-world scenario)
            // has the selected date. In this specific inline demo, there's no separate input field.
            System.out.println("Selected a date.");

            // Switch back to the main content (if needed, though not strictly necessary here)
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}