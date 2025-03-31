package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ColorPickerSlider {

    public static void main(String[] args) throws InterruptedException {
        // Optional: Set the path to your ChromeDriver executable
        // If ChromeDriver is in your system's PATH, you can skip this
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://jqueryui.com/slider/#colorpicker");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Wait for the iframe to be present and switch to it
            WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='demo-frame']")));
            driver.switchTo().frame(iframeElement);

            // Locate the slider handles for Red, Green, and Blue within the iframe
            WebElement redSliderHandle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#red .ui-slider-handle")));
            WebElement greenSliderHandle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#green .ui-slider-handle")));
            WebElement blueSliderHandle = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#blue .ui-slider-handle")));

            // Locate the color value spans (optional, for verification)
            WebElement redValueSpan = driver.findElement(By.xpath("//*[@id=\"red\"]/span"));
            WebElement greenValueSpan = driver.findElement(By.xpath("//*[@id=\"green\"]/span"));
            WebElement blueValueSpan = driver.findElement(By.xpath("//*[@id=\"blue\"]/span"));

            // Create an Actions object for performing drag and drop
            Actions actions = new Actions(driver);

            // --- Manipulate the Red Slider ---
            System.out.println("Moving Red slider...");
            // Move the slider by an offset (adjust as needed)
            actions.dragAndDropBy(redSliderHandle, 80, 0).perform();
            Thread.sleep(1000); // Add a small pause to observe
            System.out.println("Red Value: " + redValueSpan.getText());

            // --- Manipulate the Green Slider ---
            System.out.println("\nMoving Green slider...");
            actions.dragAndDropBy(greenSliderHandle, -50, 0).perform();
            Thread.sleep(1000);
            System.out.println("Green Value: " + greenValueSpan.getText());

            // --- Manipulate the Blue Slider ---
            System.out.println("\nMoving Blue slider...");
            actions.dragAndDropBy(blueSliderHandle, 100, 0).perform();
            Thread.sleep(1000);
            System.out.println("Blue Value: " + blueValueSpan.getText());

            // You can add more interactions or assertions here

        } finally {
            // Switch back to the main content (optional, but good practice)
            driver.switchTo().defaultContent();
            // Close the browser
            driver.quit();
        }
    }
}