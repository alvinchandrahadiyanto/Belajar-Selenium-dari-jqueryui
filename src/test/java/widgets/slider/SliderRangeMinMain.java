package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SliderRangeMinMain {

    public static void main(String[] args) {
        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#rangemin";
        driver.get(url);

        try {
            // Switch to the iframe containing the slider
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            WebElement slider = driver.findElement(By.id("slider-range-min"));
            WebElement sliderHandle = driver.findElement(By.xpath("//div[@id='slider-range-min']/span"));
            WebElement amountInput = driver.findElement(By.id("amount"));

            // Get initial value
            String initialAmount = amountInput.getAttribute("value");
            int initialSliderPosition = sliderHandle.getLocation().getX();

            // Perform drag and drop to change the slider value
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(sliderHandle, 50, 0).perform(); // Move the handle 50 pixels to the right

            // Get the updated value
            String updatedAmount = amountInput.getAttribute("value");
            int updatedSliderPosition = sliderHandle.getLocation().getX();

            // Verify that the value and slider position have changed
            Assert.assertNotEquals(initialAmount, updatedAmount, "Amount should have changed.");
            Assert.assertNotEquals(initialSliderPosition, updatedSliderPosition, "Slider position should have changed.");

            // Extract the numerical value from the amount string
            String updatedAmountValueStr = updatedAmount.replace("$", "");
            int updatedAmountValue = Integer.parseInt(updatedAmountValueStr);

            // You can add more specific assertions based on the slider's min, max, and expected behavior
            Assert.assertTrue(updatedAmountValue > 37, "Slider value should be greater than the initial value.");
            Assert.assertTrue(updatedAmountValue >= 1 && updatedAmountValue <= 700, "Slider value should be within the allowed range.");

            System.out.println("Initial Amount: " + initialAmount);
            System.out.println("Updated Amount: " + updatedAmount);
            System.out.println("Initial Slider Position: " + initialSliderPosition);
            System.out.println("Updated Slider Position: " + updatedSliderPosition);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}