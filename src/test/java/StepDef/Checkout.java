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

public class Checkout {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";

    @Given("Open browser Apps")
    public void openBrowserApps() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @And("Open saucedemo Website")
    public void openSaucedemoWebsite() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(1000);
    }

    @And("Located on saucedemo web")
    public void locatedOnSaucedemoWeb() {
        driver.findElement(By.id("login_button_container")).isDisplayed();
    }

    @When("Users input username and Password and then login")
    public void usersInputUsernameAndPasswordAndThenLogin() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();
    }

    @And("Users click Add to cart Button to buy one or more product")
    public void usersClickAddToCartButtonToBuyOneOrMoreProduct() {
        driver.findElement(By.name("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("User click icon cart")
    public void userClickIconCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    @And("User click checkout button")
    public void userClickCheckoutButton() {
        driver.findElement(By.name("checkout")).click();
    }

    @And("Input Firstname, Lastname and Zip Code")
    public void inputFirstnameLastnameAndZipCode() {
        driver.findElement(By.name("firstName")).sendKeys("Ryan");
        driver.findElement(By.name("lastName")).sendKeys("Hardian");
        driver.findElement(By.name("postalCode")).sendKeys("77566");
    }

    @And("Click Continue Button")
    public void clickContinueButton() {
        driver.findElement(By.name("continue")).click();
    }

    @And("Click Finish")
    public void clickFinish() {
        driver.findElement(By.id("finish")).click();
    }

    @Then("user is on checkout complete page")
    public void userIsOnCheckoutCompletePage() {
        String checkoutCompletePage;
        checkoutCompletePage = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
        Assert.assertEquals(checkoutCompletePage, "Checkout: Complete!");
        driver.close();
    }
}