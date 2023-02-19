package org.KrasiK_SearchField;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

// Tests are based on Software Requirements Specification
//http://training.skillo-bg.com:4300/posts/all project
//Version 2020.3.2.4300
//section "Search field"

public class SearchFieldTests {

    //Login to http://training.skillo-bg.com:4030
    @Test
    public void searchField () {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("http://training.skillo-bg.com:4300/posts/all");
        WebElement login = driver.findElement(By.id("nav-link-login"));
        login.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/users/login"));

        WebElement signInTitle = driver.findElement(By.xpath("//p[text()='Sign in']"));
        wait.until(ExpectedConditions.visibilityOf(signInTitle));

        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys("krasiko");
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys("224339K");

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
        signInButton.click();

        //Search field on Profile page

        WebElement searchField = driver.findElement(By.id("search-bar"));
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys("krasik");
        WebElement userKrasik = driver.findElement(By.partialLinkText("krasik"));
        userKrasik.click();

    }

}
