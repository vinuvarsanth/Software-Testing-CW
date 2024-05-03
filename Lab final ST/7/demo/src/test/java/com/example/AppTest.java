package com.example;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.abhibus.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test
    public void Testcase1() throws Exception {
        driver.findElement(By.xpath("//*[@id=\"train-link\"]/span[2]")).click();
        Thread.sleep(3000);
        String url = driver.getCurrentUrl();
        if (url.contains("trains")) {
            System.out.println("The url contains the keyword");
        } else {
            System.out.println("The url does not contains the url");
        }
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[1]/a/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"login-link\"]/span[2]")).click();
        Thread.sleep(3000);
        String value = driver.findElement(By.xpath("//*[@id=\"login-heading\"]/div[1]/h4")).getText();
        if (value.equals("Login to AbhiBus")) {
            System.out.println("The given value equals");
        } else {
            System.out.println("The given value does not equals");
        }
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\vinuv\\Desktop\\Lab final ST\\7\\demo\\Screenshot.png";
        FileUtils.copyFile(source, new File(path));
    }

    @Test
    public void Testcase2() throws Exception {
        FileInputStream fs = new FileInputStream("C:\\Selenium\\dbankdemo.xlsx");
        XSSFWorkbook work = new XSSFWorkbook(fs);
        XSSFSheet sheet = work.getSheet("part");
        XSSFRow row = sheet.getRow(0);
        String from = row.getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id=\"search-from\"]/div/div/div/div/div[2]/input")).sendKeys(from);
        Thread.sleep(3000);
        String to = row.getCell(1).getStringCellValue();
        driver.findElement(By.xpath("//*[@id=\"search-to\"]/div/div/div/div/div[2]/input")).sendKeys(to);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[2]/div/div/div/div[11]/a")).click();
        Thread.sleep(3000);
        String url = driver.getCurrentUrl();
        if ((url.contains("Mumbai") && (url.contains("Pune")))) {
            System.out.println("It contains the keyword");
        } else {
            System.out.println("It does not contains the keyword");
        }
    }

    @Test
    public void Testcase3() throws Exception {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        j.executeScript("window.scrollBy(2,4000)");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[1]/div/div/div/div/button[5]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"footer-routes\"]/div/div[2]/div/div/div/div[7]/a")).click();
        Thread.sleep(3000);
        String keyword = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/h2")).getText();
        if (keyword.contains("Media")) {
            System.out.println("It contains the keyword");
        } else {
            System.out.println("It does not contains the keyword");
        }
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\vinuv\\Desktop\\Lab final ST\\7\\demo\\sample.png";
        FileUtils.copyFile(source, new File(path));
    }

    @AfterMethod
    public void end() throws Exception {
        driver.quit();
    }
}