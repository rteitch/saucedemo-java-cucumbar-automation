package StepDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Logout {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("user login")
    public void userLogin() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        Thread.sleep(1000);
        driver.findElement(By.id("login_button_container")).isDisplayed();
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(1000);
    }

    @When("user tap navigation button")
    public void userTapNavigationButton() throws InterruptedException {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
    }

    @And("user click logout button")
    public void userClickLogoutButton() throws InterruptedException {
        driver.findElement(By.id("menu_button_container")).isDisplayed();
        driver.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
    }

    @Then("user back to login page")
    public void userBackToLoginPage() throws InterruptedException {
        //driver.findElement(By.id("login_button_container")).isDisplayed();
        String loginPage = driver.findElement(By.xpath("//*[@id='root']/div/div[1]")).getText();
        Assert.assertEquals(loginPage, "Swag Labs");
        Thread.sleep(1000);
        driver.close();
    }
}