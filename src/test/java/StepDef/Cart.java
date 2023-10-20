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

public class Cart {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Open browser Web")
    public void openBrowserWeb() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @And("Open saucedemo Web")
    public void openSaucedemoWeb() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(1000);
    }

    @And("Located on saucedemo web app")
    public void locatedOnSaucedemoWebApp() {
        driver.findElement(By.id("login_button_container")).isDisplayed();
    }

    @When("User input username and Password and then login")
    public void userInputUsernameAndPasswordAndThenLogin() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }

    @And("User click icon cart without click Add to cart Button")
    public void userClickIconCartWithoutClickAddToCartButton() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    @And("User click Checkout button")
    public void userClickCheckoutButton() {
        driver.findElement(By.name("checkout")).click();
    }

    @And("user not Input Firstname and Lastname and zipcode")
    public void userNotInputFirstnameAndLastnameAndZipcode() {
        driver.findElement(By.name("firstName")).sendKeys("");
        driver.findElement(By.name("lastName")).sendKeys("");
        driver.findElement(By.name("postalCode")).sendKeys("");
    }

    @And("Click Button Continue")
    public void clickContinueButton() {
        driver.findElement(By.name("continue")).click();
    }

    @Then("user get error messages")
    public void userGetErrorMessages() {
        String checkoutYourInformation;
        checkoutYourInformation= driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/div/div[4]")).getText();
        Assert.assertEquals(checkoutYourInformation, "Error: First Name is required");
        driver.close();
    }
}