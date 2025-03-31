package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DefaultSlider {

    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        // You might need to adjust this based on your system and where you have ChromeDriver installed
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/";
        driver.get(url);

        try {
            // Switch to the iframe containing the slider demo
            WebElement demoFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(demoFrame);

            WebElement slider = driver.findElement(By.id("slider"));
            WebElement sliderHandle = driver.findElement(By.xpath("//div[@id='slider']/span"));

            // Get the initial position of the slider handle
            int initialXOffset = sliderHandle.getLocation().getX();

            // Create an Actions object for performing advanced interactions
            Actions actions = new Actions(driver);

            // Move the slider handle to the right by a certain offset (e.g., 50 pixels)
            actions.dragAndDropBy(sliderHandle, 50, 0).perform();

            // Verify that the handle has moved (you can add more specific assertions here
            // based on the expected behavior of the slider)
            int finalXOffset = sliderHandle.getLocation().getX();
            if (finalXOffset > initialXOffset) {
                System.out.println("Slider handle moved successfully.");
            } else {
                System.out.println("Slider handle did not move as expected.");
            }

            // You can add more interactions here, like moving it further left or right,
            // and add assertions to check the slider's value if it were displayed.

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        }
    }
}