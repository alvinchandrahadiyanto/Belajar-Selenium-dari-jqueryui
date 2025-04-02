package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SliderStepsAutomationMain {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#steps";

        try {
            // Set up ChromeDriver path (replace with your actual path)
            driver.get(url);

            // Switch to the iframe containing the slider demo
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            WebElement slider = driver.findElement(By.id("slider"));
            WebElement amountInput = driver.findElement(By.id("amount"));

            // Get the initial value
            String initialAmount = amountInput.getAttribute("value");
            Assert.assertEquals(initialAmount, "$100", "Initial amount is incorrect.");
            System.out.println("Initial amount verified: " + initialAmount);

            // Calculate the width of the slider
            int sliderWidth = slider.getSize().getWidth();

            // Calculate the step size in pixels
            int minValue = 0;
            int maxValue = 500;
            int stepValue = 50;
            double steps = (double) (maxValue - minValue) / stepValue;
            double pixelPerStep = (double) sliderWidth / steps;

            Actions actions = new Actions(driver);

            // Move the slider to the first step (value 50)
            actions.moveToElement(slider, (int) (pixelPerStep * (50 - minValue) / stepValue), 0).click().perform();
            String amountAfterFirstStep = amountInput.getAttribute("value");
            Assert.assertEquals(amountAfterFirstStep, "$50", "Amount after first step is incorrect.");
            System.out.println("Amount after first step verified: " + amountAfterFirstStep);

            // Move the slider to the third step (value 150)
            actions.moveToElement(slider, (int) (pixelPerStep * (150 - minValue) / stepValue), 0).click().perform();
            String amountAfterThirdStep = amountInput.getAttribute("value");
            Assert.assertEquals(amountAfterThirdStep, "$150", "Amount after third step is incorrect.");
            System.out.println("Amount after third step verified: " + amountAfterThirdStep);

            // Move the slider to the maximum value (500)
            actions.moveToElement(slider, sliderWidth, 0).click().perform();
            String amountAtMaximum = amountInput.getAttribute("value");
            Assert.assertEquals(amountAtMaximum, "$500", "Amount at maximum value is incorrect.");
            System.out.println("Amount at maximum value verified: " + amountAtMaximum);

            // Move the slider to the minimum value (0)
            actions.moveToElement(slider, 0, 0).click().perform();
            String amountAtMinimum = amountInput.getAttribute("value");
            Assert.assertEquals(amountAtMinimum, "$0", "Amount at minimum value is incorrect.");
            System.out.println("Amount at minimum value verified: " + amountAtMinimum);

            System.out.println("Slider steps automation completed successfully.");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
