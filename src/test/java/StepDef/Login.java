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

public class Login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Open browser")
    public void openBrowser() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @And("Open saucedemo Web App")
    public void openSaucedemoWebApp() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(1000);
    }

    @And("Located on saucedemo website")
    public void locatedOnSaucedemoWebsite() {
        driver.findElement(By.id("login_button_container")).isDisplayed();
    }

    @When("The Users input with registered username")
    public void theUsersInputWithRegisteredUsername() throws InterruptedException {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
    }

    @And("The Users input with registered Password")
    public void theUsersInputWithRegisteredPassword() throws InterruptedException {
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
    }

    @And("The User click button Login")
    public void theUserClickButtonLogin() throws InterruptedException {
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(1000);
    }

    @Then("user is on dashboard page")
    public void userIsOnDashboardPage() {
        String dashboardPageAssert = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(dashboardPageAssert, "Products");
        driver.close();
    }

    @When("The Users input with random or unregistered username")
    public void theUsersInputWithRandomOrUnregisteredUsername() throws InterruptedException {
        driver.findElement(By.name("user-name")).sendKeys("test_user");
        Thread.sleep(1000);
    }

    @And("The Users input with random or unregistered password")
    public void theUsersInputWithRandomOrUnregisteredPassword() throws InterruptedException {
        driver.findElement(By.name("password")).sendKeys("secret_number");
        Thread.sleep(1000);
    }

    @Then("user get error message")
    public void userGetErrorMessage() throws InterruptedException {
        String ErrorLogin;
        ErrorLogin = driver.findElement(By.xpath("//*[@id='login_button_container']/div/form/div[3]")).getText();
        Assert.assertEquals(ErrorLogin, "Epic sadface: Username and password do not match any user in this service");
        Thread.sleep(1000);
        driver.close();
    }
}