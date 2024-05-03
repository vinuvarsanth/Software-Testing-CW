package com.example;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
    WebDriver driver;
    WebElement search;
    FileInputStream fs;
    XSSFWorkbook work;
    XSSFSheet sheet;
    XSSFRow row;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void navigate() throws IOException {
        driver.get("https://www.firstcry.com/");
        search = driver.findElement(By.xpath("//*[@id=\"search_box\"]"));
        fs = new FileInputStream("C:\\Selenium\\dbankdemo.xlsx");
        work = new XSSFWorkbook(fs);
        sheet = work.getSheet("TOY");
        row = sheet.getRow(0);
    }

    @Test(priority = 0)
    public void testCase1() throws InterruptedException {
        String text = row.getCell(0).getStringCellValue();
        search.clear();
        search.sendKeys(text);
        Thread.sleep(1000);
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String curr = driver.getCurrentUrl();
        if (curr.contains("kids-toys")) {
            System.out.println("The current URL contains the word \"kids-toys\"");
        } else {
            System.out.println("The current URL does not contains the word \"kids-toys\"");
        }
    }

    @Test(priority = 1)
    public void testCase2() throws InterruptedException {
        String text = row.getCell(1).getStringCellValue();
        search.clear();
        search.sendKeys(text);
        Thread.sleep(1000);
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(
                By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]/a/div"))
                .click();
        Thread.sleep(2000);
        String label = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
        if (label.contains("Ethnic Wear")) {
            System.out.println("The page contains the label \"Ethnic Wear\"");
        } else {
            System.out.println("The page does not contains the label \"Ethnic Wear\"");
        }
    }

    @Test(priority = 2)
    public void testCase3() throws InterruptedException {
        String text = row.getCell(2).getStringCellValue();
        search.clear();
        search.sendKeys(text);
        Thread.sleep(1000);
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        String title = driver.getTitle();
        System.out.println(title);
        String first = driver.findElement(By.xpath("//*[@id=\"maindiv\"]/div[1]/div/div[1]/div[2]")).getText();
        driver.findElement(By.xpath("//*[@id=\"maindiv\"]/div[1]/div/div[1]")).click();
        String curr = driver.getWindowHandle();
        Thread.sleep(3000);
        for (String w : driver.getWindowHandles()) {
            if (!w.equals(curr)) {
                driver.switchTo().window(w);
                break;
            }
        }
        String currProduct = driver.findElement(By.xpath("//*[@id=\"prod_name\"]")).getText();
        if (currProduct.equals(first)) {
            System.out.println("The getText value and the displayed product value are matched");
        } else {
            System.out.println("The getText value and the displayed product value are not matched");
        }
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }
}