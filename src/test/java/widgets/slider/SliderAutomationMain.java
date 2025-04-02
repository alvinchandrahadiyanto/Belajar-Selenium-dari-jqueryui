package widgets.slider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SliderAutomationMain {
// Error
    public static void main(String[] args) {
        // Set up ChromeDriver path (adjust as needed)
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String url = "https://jqueryui.com/slider/#hotelrooms";

        try {
            driver.get(url);

            // Switch to the iframe containing the slider
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@class='demo-frame']")));
            driver.switchTo().frame(iframe);

            WebElement slider = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("slider")));
            WebElement sliderHandle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='slider']/span")));
            WebElement minBedsSelect = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("minbeds")));

            Actions actions = new Actions(driver);

            // Test moving the slider to different positions and verifying the select dropdown
            moveSliderAndVerify(actions, driver, wait, slider, sliderHandle, minBedsSelect, 1);
            moveSliderAndVerify(actions, driver, wait, slider, sliderHandle, minBedsSelect, 3);
            moveSliderAndVerify(actions, driver, wait, slider, sliderHandle, minBedsSelect, 6);

            // Test changing the select dropdown and verifying the slider position
            selectOptionAndVerifySlider(driver, wait, minBedsSelect, sliderHandle, 2);
            selectOptionAndVerifySlider(driver, wait, minBedsSelect, sliderHandle, 5);

            System.out.println("All test cases completed successfully!");

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void moveSliderAndVerify(Actions actions, WebDriver driver, WebDriverWait wait, WebElement slider, WebElement sliderHandle, WebElement minBedsSelect, int targetValue) {
        int sliderWidth = slider.getSize().getWidth();
        double pixelOffset = (double) (targetValue - 1) / 5 * sliderWidth;

        // For the initial value of 1, add a small offset to ensure movement
        if (targetValue == 1) {
            pixelOffset = 5; // Adjust this value as needed
        }

        actions.dragAndDropBy(sliderHandle, (int) pixelOffset - sliderHandle.getLocation().getX() + slider.getLocation().getX(), 0).perform();

        wait.until(ExpectedConditions.attributeToBe(sliderHandle, "aria-valuenow", String.valueOf(targetValue)));

        wait.until(ExpectedConditions.textToBe(By.xpath("//select[@id='minbeds']/option[@selected]"), String.valueOf(targetValue)));
        String selectedOption = minBedsSelect.findElement(By.xpath("./option[@selected]")).getText();
        Assert.assertEquals(selectedOption, String.valueOf(targetValue), "Slider not correctly moved to " + targetValue + " beds.");
        System.out.println("Successfully moved slider to " + targetValue + " beds.");
    }

    private static void selectOptionAndVerifySlider(WebDriver driver, WebDriverWait wait, WebElement minBedsSelect, WebElement sliderHandle, int targetValue) {
        minBedsSelect.findElement(By.xpath("./option[" + targetValue + "]")).click();
        wait.until(ExpectedConditions.attributeToBe(sliderHandle, "aria-valuenow", String.valueOf(targetValue)));
        String sliderValue = sliderHandle.getAttribute("aria-valuenow");
        Assert.assertEquals(sliderValue, String.valueOf(targetValue), "Slider handle not correctly positioned for " + targetValue + " beds.");
        System.out.println("Successfully selected " + targetValue + " beds from dropdown.");
    }
}