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
import page.*;

import java.time.Duration;

public class Checkout {
    WebDriver driver;
    String firstProductName;
    String secondProductName;

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
    public void b_addToCartTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        YourInformationPage yourInformationPage = new YourInformationPage(driver);

        //explicit wait untuk menunggu element terlihat di layar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='title']")));

        //method action element
        homePage.clickAddToCartFirstProduct();
        homePage.clickAddToCartSecondProduct();

        firstProductName=homePage.firstProductName;
        secondProductName=homePage.secondProductName;

        //assertion 6 : cek apakah jumlah produk yang dimasukkan dalam keranjang telah sesuai
        int productsInCart = Integer.parseInt(homePage.getCartBadge());
        Assert.assertEquals(productsInCart, homePage.jumlahClickAddToCartButton);

        //method action element
        homePage.clickShoppingCart();

        //explicit wait untuk menunggu element terlihat di layar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='title']")));

        //assertion 7 : cek current URL apakah sudah sesuai dengan Cart Page
        Assert.assertEquals(cartPage.getCurrentURL(),"https://www.saucedemo.com/cart.html");

        //assertion 8 : cek dashboard teks apakah sudah sesuai dengan ekspektasi
        Assert.assertEquals(cartPage.getDashboardText(),"Your Cart");

        //assertion 9 : cek apakah produk dalam Cart sudah sesuai
        Assert.assertEquals(cartPage.getFirstProductInCart(),firstProductName);
        Assert.assertEquals(cartPage.getSecondProductInCart(),secondProductName);

        //method action element
        cartPage.clickCheckoutButton();

        //assertion 10 : cek current URL apakah sudah sesuai dengan Your Information Page
        Assert.assertEquals(yourInformationPage.getCurrentURL(),"https://www.saucedemo.com/checkout-step-one.html");
    }

    @Test
    public void c_yourInformationTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        YourInformationPage yourInformationPage = new YourInformationPage(driver);
        OverviewPage overviewPage = new OverviewPage(driver);

        //explicit wait untuk menunggu element terlihat di layar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='title']")));

        //assertion 11 : cek dashboard teks apakah sudah sesuai dengan ekspektasi
        Assert.assertEquals(yourInformationPage.getDashboardText(),"Checkout: Your Information");

        //assertion 12 : cek cek apakah form muncul
        yourInformationPage.formIsDisplayed();

        //method action element
        yourInformationPage.inputFirstName("Gilang");
        yourInformationPage.inputLastName("Zhanuardy");
        yourInformationPage.inputPostalCode("15510");
        yourInformationPage.clickContinueButton();

        //assertion 13 : cek current URL apakah sudah sesuai dengan Your Overview Page
        Assert.assertEquals(overviewPage.getCurrentURL(),"https://www.saucedemo.com/checkout-step-two.html");
    }

    @Test
    public void d_overviewTest(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        OverviewPage overviewPage = new OverviewPage(driver);
        CompletePage completePage = new CompletePage(driver);

        //explicit wait untuk menunggu element terlihat di layar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-test='title']")));

        //assertion 14 : cek dashboard teks apakah sudah sesuai dengan ekspektasi
        Assert.assertEquals(overviewPage.getDashboardText(),"Checkout: Overview");

        //assertion 15 : cek apakah produk sudah sesuai
        Assert.assertEquals(overviewPage.getFirstProductInCart(),firstProductName);
        Assert.assertEquals(overviewPage.getSecondProductInCart(),secondProductName);

        //method action element
        overviewPage.clickFinishButton();

        //assertion 16 : cek current URL apakah sudah sesuai dengan Your Complete Page
        Assert.assertEquals(completePage.getCurrentURL(),"https://www.saucedemo.com/checkout-complete.html");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
