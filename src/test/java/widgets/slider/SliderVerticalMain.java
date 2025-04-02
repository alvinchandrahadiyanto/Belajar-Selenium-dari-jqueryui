package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SliderVerticalMain {

    public static void main(String[] args) {
        // Set up the ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#slider-vertical";
        driver.get(url);

        try {
            // Switch to the iframe containing the slider
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            WebElement slider = driver.findElement(By.id("slider-vertical"));
            WebElement handle = driver.findElement(By.xpath("//div[@id='slider-vertical']/span"));
            WebElement amountInput = driver.findElement(By.id("amount"));

            // Get initial value
            int initialValue = Integer.parseInt(amountInput.getAttribute("value"));
            System.out.println("Initial slider value: " + initialValue);
            Assert.assertEquals(initialValue, 60, "Initial slider value is not as expected.");

            // Move the slider handle up (decrease value)
            int yOffsetUp = -50; // Adjust the offset as needed
            new Actions(driver).dragAndDropBy(handle, 0, yOffsetUp).build().perform();
            int newValueUp = Integer.parseInt(amountInput.getAttribute("value"));
            System.out.println("Slider value after moving up: " + newValueUp);
            Assert.assertTrue(newValueUp < initialValue, "Slider value did not decrease after moving up.");

            // Move the slider handle down (increase value)
            int yOffsetDown = 100; // Adjust the offset as needed
            new Actions(driver).dragAndDropBy(handle, 0, yOffsetDown).build().perform();
            int newValueDown = Integer.parseInt(amountInput.getAttribute("value"));
            System.out.println("Slider value after moving down: " + newValueDown);
            Assert.assertTrue(newValueDown > newValueUp, "Slider value did not increase after moving down.");

            // Try to move to the maximum value
            int yOffsetMax = -200; // Adjust based on slider height
            new Actions(driver).dragAndDropBy(handle, 0, yOffsetMax).build().perform();
            int maxValue = Integer.parseInt(amountInput.getAttribute("value"));
            System.out.println("Slider value after attempting to move to max: " + maxValue);
            Assert.assertEquals(maxValue, 100, "Slider did not reach the maximum value.");

            // Try to move to the minimum value
            int yOffsetMin = 300; // Adjust based on slider height
            new Actions(driver).dragAndDropBy(handle, 0, yOffsetMin).build().perform();
            int minValue = Integer.parseInt(amountInput.getAttribute("value"));
            System.out.println("Slider value after attempting to move to min: " + minValue);
            Assert.assertEquals(minValue, 0, "Slider did not reach the minimum value.");

            System.out.println("Vertical slider functionality test completed successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred during the test: " + e.getMessage());
        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}