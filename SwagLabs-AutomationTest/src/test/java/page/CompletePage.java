package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.xpath;

public class CompletePage {
    //atribut untuk  masing-masing element
    WebDriver driver;

    By dashboard_text = xpath("//*[@data-test='title']");
    By complete_icon = xpath("//*[@data-test='pony-express']");
    By thankyou_text = xpath("//*[@data-test='complete-header']");
    By description_text = xpath("//*[@data-test='complete-text']");
    By back_button = xpath("//*[@data-test='back-to-products']");

    //method untuk action
    public CompletePage(WebDriver driver){
        this.driver=driver;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getDashboardText(){
        return driver.findElement(dashboard_text).getText();
    }

    public void completeIconIsDisplayed(){
        driver.findElement(complete_icon).isDisplayed();
    }

    public String getThankYou_text(){
        return driver.findElement(thankyou_text).getText();
    }

    public String getDescription_text(){
        return driver.findElement(description_text).getText();
    }

    public void clickBackButton(){
        driver.findElement(back_button).click();
    }
}
