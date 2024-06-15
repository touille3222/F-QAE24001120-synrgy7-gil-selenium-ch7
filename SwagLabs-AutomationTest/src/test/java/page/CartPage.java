package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class CartPage {
    //atribut untuk  masing-masing element
    WebDriver driver;

    By dashboard_text = xpath("//*[@data-test='title']");
    By first_productincart = xpath("(//*[@data-test='inventory-item-name'])[1]");
    By second_productincart = xpath("(//*[@data-test='inventory-item-name'])[2]");
    By checkout_button = xpath("//*[@data-test='checkout']");

    //method untuk action
    public CartPage(WebDriver driver){
        this.driver=driver;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getDashboardText(){
        return driver.findElement(dashboard_text).getText();
    }

    public String getFirstProductInCart(){
        return driver.findElement(first_productincart).getText();
    }

    public String getSecondProductInCart(){
        return driver.findElement(second_productincart).getText();
    }

    public void clickCheckoutButton(){
        driver.findElement(checkout_button).click();
    }
}
