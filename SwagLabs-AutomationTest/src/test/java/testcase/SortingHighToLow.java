package testcase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

import java.time.Duration;

public class SortingHighToLow {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void a_loginTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        //explicit wait untuk menunggu element terlihat di layar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

        //assertion 1 : cek current URL apakah sudah sesuai dengan URL Login Page
        Assert.assertEquals(loginPage.getCurrentURL(),"https://www.saucedemo.com/");

        //assertion 2 : cek apakah username field, password field, dan login button muncul
        loginPage.usernameFieldIsDisplayed();
        loginPage.passwordFieldIsDisplayed();
        loginPage.loginButtonIsDisplayed();

        //method action element
        loginPage.inputUsername("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        //assertion 3 : cek current URL apakah sudah sesuai dengan URL Home Page
        Assert.assertEquals(homePage.getCurrentURL(),"https://www.saucedemo.com/inventory.html");

        //assertion 4 : cek dashboard teks apakah sudah sesuai dengan ekspektasi
        Assert.assertEquals(homePage.getDashboardText(),"Products");

        //assertion 5 : cek apakah icon shopping cart muncul
        homePage.shoppingCartIconIsDisplayed();
    }

    @Test
    public void b_mainTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        HomePage homePage = new HomePage(driver);

        //explicit wait untuk menunggu element terlihat di layar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='title']")));

        //method action element
        homePage.clickSortingSelect();
        homePage.clickHighToLow();

        //mengubah string ke float
        float price_first_product= Float.parseFloat(homePage.getPriceFirstProduct());
        float price_second_product= Float.parseFloat(homePage.getPriceSecondProduct());

        //assertion 6 : cek apakah produk pertama memiliki harga yang lebih besar daripada produk kedua
        Assert.assertTrue(price_first_product>price_second_product);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
