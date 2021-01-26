package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenFirefoxBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("login.url"));
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("btn-login"));
        webElement.click();
    }

    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("inp-username"));
        webElement.sendKeys(username);
        Thread.sleep(2000);
        webElement = webDriver.findElement(By.id("inp-password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }
    @When("^I provide name as \"([^\"]*)\"" )
    public void iProvideBrandName(String name) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(name);
        Thread.sleep(2000);
    }

    @Then("^name should be \"([^\"]*)\"$")
    public void nameShouldBe(String name) throws Throwable {
        WebElement webElement = webDriver.findElement(By.id("dd_user"));
        assertThat(webElement.getText()).isEqualTo(name);
    }

    @When("^I navigate to brand page$")
    public void iNavigateToBrandHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("brand.url"));
    }
    @When("^I click on create brand button$")
    public void iClickOnCreateBrandButton() throws Throwable {
        Thread.sleep(4000);
        WebElement webElement = webDriver.findElement(By.id("btn-create"));
        webElement.click();
    }
    @When("^I click on submit brand button$")
    public void iClickOnSubmitBrandButton() throws Throwable {
        Thread.sleep(5000);
        WebElement webElement = webDriver.findElement(By.id("btn-submit"));
        webElement.click();
        Thread.sleep(4000);
    }

    @Given("^any user logged in$")
    public void userLoggedIn() {
        webDriver.findElement(By.id("logged-in-username"));
    }

    @When("^Open dropdown menu$")
    public void openDropDownMenu() throws InterruptedException {
        WebElement webElement = webDriver.findElement(By.id("logged-in-username"));
        Thread.sleep(1000);
        webElement.click();
        Thread.sleep(10000);
    }

    @When("^click logout button$")
    public void clickLogoutButton() {
        WebElement webElement = webDriver.findElement(By.id("link-logout"));
        webElement.click();
    }

    @Then("^user logged out$")
    public void userLoggedOut() {
        webDriver.findElement(By.id("inp-username"));
        webDriver.findElement(By.id("inp-password"));
    }

    @After
    public void quitBrowser() {
        webDriver.quit();
    }
}
