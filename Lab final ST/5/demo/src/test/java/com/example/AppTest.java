package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    WebDriver driver;
    @BeforeTest
    public void test() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @BeforeMethod
    public void open() throws InterruptedException {
        // open url
        driver.get("https://www.puriholidayresort.com/");
        Thread.sleep(3000);
        // cancel the popup
        driver.findElement(By.xpath("//*[@id=\"myModal_home\"]/div/div/div[1]/button")).click();
        Thread.sleep(1000);
    }
    @Test(priority = 0)
    public void testCase1() throws InterruptedException {
        // click the in date
        driver.findElement(By.xpath("//*[@id=\"arr_datepicker\"]")).click();
        Thread.sleep(1000);
        // select month
        Select inMonth = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
        inMonth.selectByVisibleText("Jun");
        Thread.sleep(1000);
        // select the date
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[5]/a")).click();
        Thread.sleep(1000);
        // click the out date
        driver.findElement(By.xpath("//*[@id=\"dep_datepicker\"]")).click();
        Thread.sleep(1000);
        // select month
        Select outMonth = new Select(driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/div/div/select[1]")));
        outMonth.selectByVisibleText("Jun");
        Thread.sleep(1000);
        // select date
        driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[2]/td[7]/a")).click();
        Thread.sleep(1000);
        // click book now button
        driver.findElement(By.xpath("/html/body/section[1]/div/div/div/div/form/div[2]/button")).click();
        Thread.sleep(3000);
        // check the label
        String label = driver.findElement(By.xpath("/html/body/div[6]/div/div[3]/div[3]/div/div/div[1]")).getText();
        if(label.contains("Property Information")) {
            System.out.println("\"Property Information\" label was found");
        }
        else {
            System.out.println("Property Information\" label is not found");
        }
    }
    @Test(priority = 1)
    public void testCase2() throws InterruptedException {
        // hover the Rooms & Suites
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li[2]/a"))).perform();
        // choose Royal Suite
        driver.findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li[2]/ul/li[2]/a")).click();
        Thread.sleep(3000);
        // click Amenities
        driver.findElement(By.xpath("/html/body/section[2]/div/div/div/div[5]/div/section/div/h3[2]")).click();
        Thread.sleep(1000);
        // check the label
        String label = driver.findElement(By.xpath("/html/body/section[2]/div/div/div/div[5]/div/section/div/p[2]")).getText();
        if(label.contains("Price")) {
            System.out.println("The label \"Price\" is available on the page");
        }
        else {
            System.out.println("The label \"Price\" is not available on the page");
        }
    }
    @Test(priority = 2)
    public void testCase3() throws InterruptedException {
        // hover About Puri
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li[1]/a"))).perform();
        // choose Chilika Lake
        driver.findElement(By.xpath("//*[@id=\"cssmenu\"]/ul/li[1]/ul/li[7]/a")).click();
        Thread.sleep(3000);
        // click the textbox and input Excellent
        driver.findElement(By.xpath("//textarea[starts-with(@id, 'taWRLTitle')]")).sendKeys("Excellent");
        Thread.sleep(1000);
        // click continue
        driver.findElement(By.xpath("//input[starts-with(@id, 'taWRLContinue')]")).click();
    }
    @AfterTest
    public void quit() {
        // quit the driver
        driver.quit();
    }
}