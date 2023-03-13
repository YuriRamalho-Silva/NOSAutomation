package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

public class LandingPageSteps {

    private WebDriver driver;

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        screenshot(scenario.getName());
        driver.quit();
    }


    @Given("I go to the registration page")
    public void i_go_to_the_registration_page() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://login.nos.pt");
        Thread.sleep(20000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
        driver.findElement(By.xpath("//*[@id=\"c-right\"]/a[1]")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/authentication-username/div/div/div/div[1]/section[2]/div/span/a")).click();

    }

    @When("I fill in the Name field with {string}")
    public void i_fill_in_the_name_field_with(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"displayName-input\"]")));
        driver.findElement(By.xpath("//*[@id=\"displayName-input\"]")).sendKeys(name);

    }

    @When("I fill in the Number field with {string}")
    public void i_fill_in_the_number_field_with(String number) {
        driver.findElement(By.xpath("//div[@class='country-selected']//div[@class='icon arrow']")).click();
        driver.findElement(By.xpath("//div[@class='country-select-items-filter']//input")).sendKeys("+55");
        driver.findElement(By.xpath("//div[@class=\"country-select-items\"]/div/span")).click();
        driver.findElement(By.id("phone")).sendKeys(number);
    }

    @When("I fill in the Email field with {string}")
    public void i_fill_in_the_email_field_with(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @When("I fill in the Password field with {string}")
    public void i_fill_in_the_password_field_with(String password) {
        driver.findElement(By.id("password-input")).sendKeys(password);
    }

    @When("Accept the terms and conditions")
    public void accept_the_terms_and_conditions() {
        driver.findElement(By.xpath("//div[@class='checkbox-indicator']")).click();
    }

    @When("Click in the button to create my account")
    public void click_in_the_button_to_create_my_account() {
        driver.findElement(By.xpath("//div[@class=\"nosid-button-group\"]//input")).click();
    }

    @Then("A message for validation of my number is displayed")
    public void a_message_for_validation_of_my_number_is_displayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Precisamos de validar o seu número de telemóvel')]")));
        Assert.assertEquals("Precisamos de validar o seu número de telemóvel", driver.findElement(By.xpath("//p[contains(text(),'Precisamos de validar o seu número de telemóvel')]")).getText());

    }


    @Then("the fields number, email password and terms should be required again")
    public void theFieldsNumberEmailPasswordAndTermsShouldBeRequiredAgain() {
        Assert.assertEquals("Número de telemóvel inválido", driver.findElement(By.xpath("//div[@class='error visible'][contains(text(), \"Número de telemóvel inválido\")]")).getText());
        Assert.assertEquals("Email inválido", driver.findElement(By.xpath("//div[@class='error visible'][contains(text(), \"Email inválido\")]")).getText());
        Assert.assertEquals("Password inválida", driver.findElement(By.xpath("//div[@class='error visible'][contains(text(), \"Password inválida\")]")).getText());
        Assert.assertEquals("Para criar a conta leia e aceite os termos e condições", driver.findElement(By.xpath("//div[@class='error visible'][contains(text(), \"Para criar a conta leia e aceite os termos e condições\")]")).getText());
    }


    public void screenshot(String scenario) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String screenshotBase64 = ts.getScreenshotAs(OutputType.BASE64);


        // Salva a screenshot em um arquivo
        File screenshotFile = new File("target/Report/CucumberScenarios/" + scenario + ".jpg");
        byte[] decodedScreenshot = Base64.getDecoder().decode(screenshotBase64);
        FileOutputStream fos = new FileOutputStream(screenshotFile);
        fos.write(decodedScreenshot);
        fos.close();
    }


}
