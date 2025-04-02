package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class VerticalRangeSliderMain {

    public static void main(String[] args) {
        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#range-vertical";
        driver.get(url);

        try {
            // Switch to the iframe containing the slider
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            WebElement sliderRange = driver.findElement(By.id("slider-range"));
            WebElement sliderHandle1 = driver.findElement(By.xpath("//div[@id='slider-range']/span[1]"));
            WebElement sliderHandle2 = driver.findElement(By.xpath("//div[@id='slider-range']/span[2]"));
            WebElement amountInput = driver.findElement(By.id("amount"));

            Actions actions = new Actions(driver);

            // Get initial values
            int initialValue1 = Integer.parseInt(amountInput.getAttribute("value").split(" - ")[0].replace("$", ""));
            int initialValue2 = Integer.parseInt(amountInput.getAttribute("value").split(" - ")[1].replace("$", ""));
            System.out.println("Initial Values: $" + initialValue1 + " - $" + initialValue2);
            Assert.assertEquals(initialValue1, 17);
            Assert.assertEquals(initialValue2, 67);

            // Move the first slider handle down (increasing the value)
            actions.dragAndDropBy(sliderHandle1, 0, 50).perform();
            int newValue1 = Integer.parseInt(amountInput.getAttribute("value").split(" - ")[0].replace("$", ""));
            System.out.println("Value 1 after moving down: $" + newValue1);
            Assert.assertTrue(newValue1 > initialValue1, "First slider value should increase.");

            // Move the second slider handle up (decreasing the value)
            actions.dragAndDropBy(sliderHandle2, 0, -30).perform();
            int newValue2 = Integer.parseInt(amountInput.getAttribute("value").split(" - ")[1].replace("$", ""));
            System.out.println("Value 2 after moving up: $" + newValue2);
            Assert.assertTrue(newValue2 < initialValue2, "Second slider value should decrease.");

            // Move the first slider handle up (decreasing the value)
            actions.dragAndDropBy(sliderHandle1, 0, -20).perform();
            int finalValue1 = Integer.parseInt(amountInput.getAttribute("value").split(" - ")[0].replace("$", ""));
            System.out.println("Final Value 1: $" + finalValue1);

            // Move the second slider handle down (increasing the value)
            actions.dragAndDropBy(sliderHandle2, 0, 40).perform();
            int finalValue2 = Integer.parseInt(amountInput.getAttribute("value").split(" - ")[1].replace("$", ""));
            System.out.println("Final Value 2: $" + finalValue2);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
