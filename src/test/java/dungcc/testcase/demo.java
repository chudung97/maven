package dungcc.testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class demo {
    private WebDriver driver;

    public void sleepInSecond(long timeoutlnSecond) {
        try {
            Thread.sleep(timeoutlnSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyValueOfTextboxSearch(String text) {
        Boolean value = true;
        List<WebElement> elements = driver.findElements(By.xpath("//div[@role='option']"));
        for (WebElement element : elements) {
            if (!element.getText().equals(text)) {
                value = false;
            }
        }
        return value;
    }

    @Test
    public void demoToGG() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://react.salesbox.com/sign-in?mode=login");
        String title = driver.getTitle();
        System.out.println(title);
        driver.findElement(By.xpath("//input[@title='Tìm kiếm']")).sendKeys("Demo with selenium");
        this.sleepInSecond(1);
        this.verifyValueOfTextboxSearch("Demo with selenium");
        driver.quit();

    }
}
