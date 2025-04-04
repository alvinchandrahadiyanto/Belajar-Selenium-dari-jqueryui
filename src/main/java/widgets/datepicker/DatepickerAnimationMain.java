package widgets.datepicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DatepickerAnimationMain {

    public static void main(String[] args) throws InterruptedException {
        // Set up ChromeDriver path (replace with your actual path)
        WebDriver driver = new ChromeDriver();
        String url = "https://jqueryui.com/datepicker/#animation";
        driver.get(url);

        try {
            // Switch to the iframe containing the demo
            WebElement demoFrame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
            driver.switchTo().frame(demoFrame);

            WebElement datepickerInput = driver.findElement(By.id("datepicker"));
            WebElement animationSelectElement = driver.findElement(By.id("anim"));
            Select animationDropdown = new Select(animationSelectElement);

            // Verify the default animation option
            WebElement defaultOption = animationDropdown.getFirstSelectedOption();
            System.out.println("Default animation: Value = " + defaultOption.getAttribute("value") + ", Text = " + defaultOption.getText());
            assert defaultOption.getAttribute("value").equals("show") : "Default animation should be 'show'";
            assert defaultOption.getText().equals("Show (default)") : "Default animation text is incorrect";

            // Select different animation options and interact with the datepicker
            selectAndTestAnimation(driver, "slideDown");
            selectAndTestAnimation(driver, "fadeIn");
            selectAndTestAnimation(driver, "blind");
            selectAndTestAnimation(driver, "bounce");
            selectAndTestAnimation(driver, "clip");
            selectAndTestAnimation(driver, "drop");
            selectAndTestAnimation(driver, "fold");
            selectAndTestAnimation(driver, "slide");
            selectAndTestAnimation(driver, ""); // None option

        } finally {
            // Switch back to the main content (out of the iframe)
            driver.switchTo().defaultContent();
            // Close the browser
            driver.quit();
        }
    }

    public static void selectAndTestAnimation(WebDriver driver, String animationValue) throws InterruptedException {
        WebElement animationSelectElement = driver.findElement(By.id("anim"));
        Select animationDropdown = new Select(animationSelectElement);
        animationDropdown.selectByValue(animationValue);

        WebElement datepickerInput = driver.findElement(By.id("datepicker"));
        datepickerInput.click(); // Open the datepicker
        System.out.println("Selected animation: " + animationDropdown.getFirstSelectedOption().getText());

        // You can add further checks here to see if the animation is applied.
        // For a basic example, we'll just wait briefly.
        Thread.sleep(500); // Adjust as needed

        // Click outside the datepicker to close it (if it doesn't close automatically)
        driver.findElement(By.tagName("body")).click();
        Thread.sleep(200); // Small delay after closing
    }
}
