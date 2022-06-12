import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Script2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\omina\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        //Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String expectedTitle = "Welcome to Duotify!";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
        driver.findElement(By.id("hideLogin")).click();
        WebElement username = driver.findElement(By.name("username"));
        WebElement firstName =driver.findElement(By.id("firstName"));
        WebElement lastName =driver.findElement(By.id("lastName"));
        WebElement email =driver.findElement(By.id("email"));
        WebElement confirmEmail =driver.findElement(By.id("email2"));
        WebElement password =driver.findElement(By.id("password"));
        WebElement confirmPassword =driver.findElement(By.id("password2"));
        String nameUser = randomName();
        username.sendKeys(nameUser);
        String givenName = randomName();
        firstName.sendKeys(givenName);
        String sureName = "Olimjonova";
        lastName.sendKeys(sureName);
        String randomEmail = randomEmail2();
        email.sendKeys(randomEmail);
        confirmEmail.sendKeys(randomEmail);
        String randomPassword = "Dildora123";
        password.sendKeys(randomPassword);
        confirmPassword.sendKeys(randomPassword+ Keys.ENTER);
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?";
        Assert.assertEquals(actualUrl,expectedUrl);
        String matchesActual = driver.findElement(By.id("nameFirstAndLast")).getText();
        String matchesExpected = givenName +" "+sureName;
        Assert.assertEquals(matchesActual,matchesExpected);
        Thread.sleep(2000);
        driver.findElement(By.id("nameFirstAndLast")).click();
        String actualUserInfo = driver.findElement(By.className("userInfo")).getText();
        Assert.assertEquals(actualUserInfo,matchesExpected);
        driver.findElement(By.id("rafael")).click();
        Thread.sleep(1000);
        String actualUrl2 = driver.getCurrentUrl();
       String expectedUrl2 = "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php";
      Assert.assertEquals(actualUrl2,expectedUrl2);
      WebElement loginName =  driver.findElement(By.id("loginUsername"));
        loginName.sendKeys(nameUser);
       WebElement loginPassword = driver.findElement(By.id("loginPassword"));
       loginPassword.sendKeys(randomPassword);
       driver.findElement(By.name("loginButton")).click();
        String expectedText = "You Might Also Like";
        Thread.sleep(1000);
       Assert.assertTrue(driver.getPageSource().contains(expectedText));
       driver.findElement(By.id("nameFirstAndLast")).click();
       driver.findElement(By.id("rafael")).click();
        // driver.close();
        driver.quit();


    }
    public static String randomName(){
        String name = "";
        char ch2 = (char)(65+Math.random()*26);
        name+=ch2;
        for (int i = 0; i <= 8; i++) {
            char ch = (char)(97+Math.random()*26);
            name=name+ch;
        }
        return name;
    }
    public static String randomEmail2() {
        String name = "";
        for (int i = 0; i <= 7; i++) {
            char ch = (char) (97 + Math.random() * 27);
            name = name + ch;
        }
        return name + "@gmail.com";
    }
}
