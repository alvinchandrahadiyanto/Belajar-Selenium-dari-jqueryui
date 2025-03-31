package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RangeMaxSliderMain {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#rangemax";
        driver.get(url);

        try {
            // Wait for the iframe to be present and switch to it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='demo-frame']")));
            driver.switchTo().frame(iframe);

            // Wait for the slider to be present
            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("slider-range-max")));
            // Locate the handle using a more specific XPath based on the slider's ID
            WebElement handle = driver.findElement(By.xpath("//div[@id='slider-range-max']/span"));
            WebElement amountInput = driver.findElement(By.id("amount"));

            // Initial value check
            String initialValue = amountInput.getAttribute("value");
            System.out.println("Initial slider value: " + initialValue);
            if (!initialValue.equals("2")) {
                System.err.println("Initial value is incorrect: Expected '2', but got '" + initialValue + "'");
            } else {
                System.out.println("Initial value check passed.");
            }

            // Get min, max, and current values
            String minValueStr = slider.getAttribute("aria-valuemin");
            String maxValueStr = slider.getAttribute("aria-valuemax");
            String currentValueStr = handle.getAttribute("aria-valuenow");

            if (minValueStr != null && maxValueStr != null && currentValueStr != null) {
                int minValue = Integer.parseInt(minValueStr);
                int maxValue = Integer.parseInt(maxValueStr);
                int currentValue = Integer.parseInt(currentValueStr);
                int targetValue = 7;

                int sliderWidth = slider.getSize().getWidth();
                double pixelPerUnit = (double) sliderWidth / (maxValue - minValue);
                int pixelsToMove = (int) Math.round((targetValue - currentValue) * pixelPerUnit);

                Actions actions = new Actions(driver);
                actions.dragAndDropBy(handle, pixelsToMove, 0).perform();

                // Verify the updated value
                String updatedValue = amountInput.getAttribute("value");
                System.out.println("Slider value after moving to " + targetValue + ": " + updatedValue);
                if (!updatedValue.equals(String.valueOf(targetValue))) {
                    System.err.println("Slider value not updated correctly: Expected '" + targetValue + "', but got '" + updatedValue + "'");
                } else {
                    System.out.println("Slider value updated correctly to " + targetValue + ".");
                }

                // Move the slider to the maximum value
                int moveToMax = (int) Math.round((maxValue - Integer.parseInt(handle.getAttribute("aria-valuenow"))) * pixelPerUnit);
                actions.dragAndDropBy(handle, moveToMax, 0).perform();
                String maxValueReached = amountInput.getAttribute("value");
                System.out.println("Slider value after moving to maximum (" + maxValue + "): " + maxValueReached);
                if (!maxValueReached.equals(String.valueOf(maxValue))) {
                    System.err.println("Slider not moved to maximum correctly: Expected '" + maxValue + "', but got '" + maxValueReached + "'");
                } else {
                    System.out.println("Slider moved to maximum value (" + maxValue + ") correctly.");
                }
            } else {
                System.err.println("Could not retrieve aria-valuemin, aria-valuemax, or aria-valuenow attributes.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.switchTo().defaultContent();
            driver.quit();
        }
    }
}