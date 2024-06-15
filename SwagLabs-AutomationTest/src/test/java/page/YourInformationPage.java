package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class YourInformationPage {
    //atribut untuk  masing-masing element
    WebDriver driver;

    By dashboard_text = xpath("//*[@data-test='title']");
    By Form = xpath("//*[@class='checkout_info']");
    By firstNameField = id("first-name");
    By lastNameField = id("last-name");
    By postalCodeField = id("postal-code");
    By continue_button = xpath("//*[@data-test='continue']");

    //method untuk action
    public YourInformationPage(WebDriver driver){
        this.driver=driver;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getDashboardText(){
        return driver.findElement(dashboard_text).getText();
    }

    public void formIsDisplayed(){
        driver.findElement(Form).isDisplayed();
    }

    public void inputFirstName(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void inputLastName(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void inputPostalCode(String postalCode){
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void clickContinueButton(){
        driver.findElement(continue_button).click();
    }
}
