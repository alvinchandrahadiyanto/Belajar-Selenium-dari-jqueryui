package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CustomSliderAutomationMain {

    public static void main(String[] args) {
        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#custom-handle";
        driver.get(url);

        try {
            // Switch to the iframe containing the slider demo
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            WebElement slider = driver.findElement(By.id("slider"));
            WebElement customHandle = driver.findElement(By.id("custom-handle"));

            // Get the initial value from the handle
            String initialValue = customHandle.getText();
            System.out.println("Initial slider value: " + initialValue);

            // Perform a drag action to change the slider value
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(customHandle, 50, 0).perform(); // Drag the handle 50 pixels to the right

            // Get the updated value from the handle
            String updatedValue = customHandle.getText();
            System.out.println("Updated slider value: " + updatedValue);

            // You can add assertions here to verify the value changed as expected.
            // For example, if the initial value was '50' (default) and dragging right increases it:
            try {
                int initial = Integer.parseInt(initialValue);
                int updated = Integer.parseInt(updatedValue);
                Assert.assertTrue(updated > initial, "Slider value should have increased after dragging right.");
                System.out.println("Assertion passed: Slider value increased.");
            } catch (NumberFormatException e) {
                System.err.println("Could not parse slider values as integers: " + e.getMessage());
            } catch (AssertionError e) {
                System.err.println("Assertion failed: " + e.getMessage());
            }

            // You can further test by dragging left and verifying the value decreases.
            actions.dragAndDropBy(customHandle, -30, 0).perform();
            String decreasedValue = customHandle.getText();
            System.out.println("Decreased slider value: " + decreasedValue);
            try {
                int updated = Integer.parseInt(updatedValue);
                int decreased = Integer.parseInt(decreasedValue);
                Assert.assertTrue(decreased < updated, "Slider value should have decreased after dragging left.");
                System.out.println("Assertion passed: Slider value decreased.");
            } catch (NumberFormatException e) {
                System.err.println("Could not parse slider values as integers: " + e.getMessage());
            } catch (AssertionError e) {
                System.err.println("Assertion failed: " + e.getMessage());
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