package dungcc.base;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class ValidateHelper {

    private WebDriver driver;
    private WebDriverWait wait;
    private int timeoutWaitForPageLoaded = 30;

    JavascriptExecutor js;
    public ValidateHelper(WebDriver driver)
    {
        this.driver =driver;
        wait = new WebDriverWait(driver,5);
        js = (JavascriptExecutor) driver;
    }
    public boolean verifyUrl(String url)
    {
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);

        return driver.getCurrentUrl().contains(url); //True/False
    }
    public boolean verifyElementText(By element, String textValue){
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).getText().equals(textValue); //True/False
    }

    public boolean verifyElementExist(By element){
        //Tạo list lưu tất cả đối tượng WebElement
        List<WebElement> listElement = driver.findElements(element);

        int total = listElement.size();

        if(total > 0){
            return true;
        }

        return false;
    }

    public void setText(By element , String value)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElement(By element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    public void selectOptionByText(By element, String text)
    {
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void readExcel(String path, String sheetName) throws Exception{
        File file = new File(path);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        //creating workbook instance that refers to .xls filex
        Workbook wb = new XSSFWorkbook(inputStream);
    }
    public void waitForPageLoaded(){
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }

    }
}
