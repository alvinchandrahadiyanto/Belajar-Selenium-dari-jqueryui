package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RangeAutomationMain {

    public static void main(String[] args) {

        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/slider/#range";
        driver.get(url);
        try {
            // Switch to the iframe containing the slider
            WebElement iframe = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(iframe);

            // Locate the slider element
            WebElement slider = driver.findElement(By.id("slider-range"));

            // Locate the handles of the slider
            WebElement handleLeft = driver.findElement(By.xpath("//div[@id='slider-range']/span[1]"));
            WebElement handleRight = driver.findElement(By.xpath("//div[@id='slider-range']/span[2]"));

            // Locate the input field to get the current range
            WebElement amountInput = driver.findElement(By.id("amount"));

            // Get the initial values
            String initialValue = amountInput.getAttribute("value");
            System.out.println("Initial range: " + initialValue);

            // Perform actions to move the left handle
            int moveLeftBy = 50; // Adjust as needed
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(handleLeft, moveLeftBy, 0).perform();

            // Get the updated value after moving the left handle
            String updatedValueLeft = amountInput.getAttribute("value");
            System.out.println("Range after moving left handle: " + updatedValueLeft);

            // Perform actions to move the right handle
            int moveRightBy = -75; // Adjust as needed (negative to move left)
            actions.dragAndDropBy(handleRight, moveRightBy, 0).perform();

            // Get the updated value after moving the right handle
            String updatedValueRight = amountInput.getAttribute("value");
            System.out.println("Range after moving right handle: " + updatedValueRight);

            // You can add assertions here if needed, but without TestNG,
            // you would typically use simple if statements for verification.
            if (!initialValue.equals(updatedValueLeft)) {
                System.out.println("Left handle moved successfully.");
            } else {
                System.out.println("Left handle movement failed.");
            }

            if (!updatedValueLeft.equals(updatedValueRight)) {
                System.out.println("Right handle moved successfully.");
            } else {
                System.out.println("Right handle movement failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}