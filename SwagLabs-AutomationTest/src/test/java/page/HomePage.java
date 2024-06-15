package page;

import org.openqa.selenium.*;

import java.util.Arrays;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class HomePage {
    //atribut untuk  masing-masing element
    WebDriver driver;

    By dashboard_text = xpath("//*[@data-test='title']");
    By shoppingcart_icon = xpath("//*[@data-test='shopping-cart-link']");
    By sorting_select = xpath("//*[@data-test='product-sort-container']");
    By hightolow = xpath("//*[@value='hilo']");
    By first_product = xpath("(//*[@data-test='inventory-item-price'])[1]");
    By second_product = xpath("(//*[@data-test='inventory-item-price'])[2]");
    By addtocartfirstproduct_button = xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']");
    By addtocartsecondproduct_button = xpath("//button[@data-test='add-to-cart-sauce-labs-bike-light']");
    By cart_badge = xpath("//*[@data-test='shopping-cart-badge']");
    By first_product_name = xpath("(//*[@data-test='inventory-item-name'])[1]");
    By second_product_name = xpath("(//*[@data-test='inventory-item-name'])[2]");

    public int jumlahClickAddToCartButton;
    public String firstProductName;
    public String secondProductName;

    //method untuk action
    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getDashboardText(){
        return driver.findElement(dashboard_text).getText();
    }

    public void shoppingCartIconIsDisplayed(){
        driver.findElement(shoppingcart_icon).isDisplayed();
    }

    public void clickSortingSelect(){
        driver.findElement(sorting_select).click();
    }

    public void clickHighToLow(){
        driver.findElement(hightolow).click();
    }

    public String getPriceFirstProduct(){
        WebElement element = driver.findElement(first_product);

        // Get the text of the element
        String elementText = element.getText();

        // Split the text into words
        String[] words = elementText.split(" ");

        // Create a StringBuilder to store the modified text
        StringBuilder modifiedText = new StringBuilder();

        // Loop through each word, remove the first letter, and append to StringBuilder
        for (String word : words) {
            if (word.length() > 1) {
                modifiedText.append(word.substring(1)).append(" ");
            } else {
                // In case the word is a single letter, we just skip it or handle it accordingly
                modifiedText.append(" ");
            }
        }

        // Convert StringBuilder to String and trim the trailing space
        String price = modifiedText.toString().trim();

        return price;
    }

    public String getPriceSecondProduct(){
        WebElement element = driver.findElement(second_product);

        // Get the text of the element
        String elementText = element.getText();

        // Split the text into words
        String[] words = elementText.split(" ");

        // Create a StringBuilder to store the modified text
        StringBuilder modifiedText = new StringBuilder();

        // Loop through each word, remove the first letter, and append to StringBuilder
        for (String word : words) {
            if (word.length() > 1) {
                modifiedText.append(word.substring(1)).append(" ");
            } else {
                // In case the word is a single letter, we just skip it or handle it accordingly
                modifiedText.append(" ");
            }
        }

        // Convert StringBuilder to String and trim the trailing space
        String price = modifiedText.toString().trim();

        return price;
    }

    public void clickAddToCartFirstProduct(){
        driver.findElement(addtocartfirstproduct_button).click();
        jumlahClickAddToCartButton++;
        firstProductName = driver.findElement(first_product_name).getText();
    }

    public void clickAddToCartSecondProduct(){
        driver.findElement(addtocartsecondproduct_button).click();
        jumlahClickAddToCartButton++;
        secondProductName = driver.findElement(second_product_name).getText();
    }

    public String getCartBadge(){
        return driver.findElement(cart_badge).getText();
    }

    public void clickShoppingCart(){
        driver.findElement(shoppingcart_icon).click();
    }
}
