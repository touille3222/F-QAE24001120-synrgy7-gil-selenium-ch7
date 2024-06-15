package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    //atribut untuk  masing-masing element
    WebDriver driver;

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");
    By errorWarning = By.xpath("//*[@data-test='error']");

    //method untuk action
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void usernameFieldIsDisplayed(){
        driver.findElement(usernameField).isDisplayed();
    }

    public void passwordFieldIsDisplayed(){
        driver.findElement(passwordField).isDisplayed();
    }

    public void loginButtonIsDisplayed(){
        driver.findElement(loginButton).isDisplayed();
    }

    public void errorWarningIsDisplayed(){
        driver.findElement(errorWarning).isDisplayed();
    }

    public String errorGetText(){
        return driver.findElement(errorWarning).getText();
    }
}
