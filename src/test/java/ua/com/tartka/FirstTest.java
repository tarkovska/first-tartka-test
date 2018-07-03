package ua.com.tartka;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nataliya_Tarkovska on 1/18/2018.
 */
public class FirstTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
      //  System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://tartka.com.ua/ru/");
    }

    @Test
    public void search(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement searchResult = driver.findElement(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a[@href = 'http://tartka.com.ua/ru/product/braslet-etno/']"));
        Assert.assertEquals(true, searchResult.isDisplayed());
        driver.quit();
    }

    @Test
    public void searchList(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        driver.quit();
    }

    @Test
    public void viewCart(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        String searchResult = searchList.get(0).getText();
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        WebElement addToCart = driver.findElement(By.name("add-to-cart"));
        addToCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//div[@class = 'woocommerce-message']/a[@href = 'http://tartka.com.ua/ru/cart/']"));
        goToCart.click();
        WebElement cart = driver.findElement(By.xpath("//div[@class = 'product-name-inner']/a"));
        Assert.assertEquals(searchResult, cart.getText());
        driver.quit();
    }

    @Test
    public void buy(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        String searchResult = searchList.get(0).getText();
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        WebElement addToCart = driver.findElement(By.name("add-to-cart"));
        addToCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//div[@class = 'woocommerce-message']/a[@href = 'http://tartka.com.ua/ru/cart/']"));
        goToCart.click();
        WebElement order = driver.findElement(By.xpath("//table[@class = 'shop_table shop_table_responsive cart woocommerce-cart-form__contents']/tbody/tr[2]/td/a"));
        order.click();
        WebElement name = driver.findElement(By.id("billing_first_name"));
        name.click();
        name.sendKeys("Vasiliy");
        WebElement surname = driver.findElement(By.id("billing_last_name"));
        surname.click();
        surname.sendKeys("Ivanov");
        WebElement email = driver.findElement(By.id("billing_email"));
        email.click();
        email.sendKeys("Vasiliy@gmail.com");
        WebElement phone = driver.findElement(By.id("billing_phone"));
        phone.click();
        phone.sendKeys("0958686868686868686868");
        WebElement terms = driver.findElement(By.id("terms"));
        terms.sendKeys(Keys.SPACE);
        WebElement placeOrder = driver.findElement(By.id("place_order"));
        placeOrder.sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        WebElement notification = driver.findElement(By.xpath("//div[@class='woocommerce-order']/p"));
        Assert.assertEquals(notification.getText(), "Спасибо. Ваш заказ был принят.");
        driver.quit();
    }

    @Test
    public void buy_negative_name(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        String searchResult = searchList.get(0).getText();
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        WebElement addToCart = driver.findElement(By.name("add-to-cart"));
        addToCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//div[@class = 'woocommerce-message']/a[@href = 'http://tartka.com.ua/ru/cart/']"));
        goToCart.click();
        WebElement order = driver.findElement(By.xpath("//table[@class = 'shop_table shop_table_responsive cart woocommerce-cart-form__contents']/tbody/tr[2]/td/a"));
        order.click();
        WebElement surname = driver.findElement(By.id("billing_last_name"));
        surname.click();
        surname.sendKeys("Ivanov");
        WebElement email = driver.findElement(By.id("billing_email"));
        email.click();
        email.sendKeys("Vasiliy@gmail.com");
        WebElement phone = driver.findElement(By.id("billing_phone"));
        phone.click();
        phone.sendKeys("0958686868686868686868");
        WebElement terms = driver.findElement(By.id("terms"));
        terms.sendKeys(Keys.SPACE);
        WebElement placeOrder = driver.findElement(By.id("place_order"));
        placeOrder.sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        WebElement notification = driver.findElement(By.xpath("//div[@class='woocommerce-NoticeGroup woocommerce-NoticeGroup-checkout']/ul/li"));
        Assert.assertEquals(notification.getText(), "Имя является обязательным полем");
        driver.quit();
    }

    @Test
    public void buy_negative_surname(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        String searchResult = searchList.get(0).getText();
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        WebElement addToCart = driver.findElement(By.name("add-to-cart"));
        addToCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//div[@class = 'woocommerce-message']/a[@href = 'http://tartka.com.ua/ru/cart/']"));
        goToCart.click();
        WebElement order = driver.findElement(By.xpath("//table[@class = 'shop_table shop_table_responsive cart woocommerce-cart-form__contents']/tbody/tr[2]/td/a"));
        order.click();
        WebElement name = driver.findElement(By.id("billing_first_name"));
        name.click();
        name.sendKeys("Vasiliy");
        WebElement email = driver.findElement(By.id("billing_email"));
        email.click();
        email.sendKeys("Vasiliy@gmail.com");
        WebElement phone = driver.findElement(By.id("billing_phone"));
        phone.click();
        phone.sendKeys("0958686868686868686868");
        WebElement terms = driver.findElement(By.id("terms"));
        terms.sendKeys(Keys.SPACE);
        WebElement placeOrder = driver.findElement(By.id("place_order"));
        placeOrder.sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        WebElement notification = driver.findElement(By.xpath("//div[@class='woocommerce-NoticeGroup woocommerce-NoticeGroup-checkout']/ul/li"));
        Assert.assertEquals(notification.getText(), "Фамилия является обязательным полем");
        driver.quit();
    }

    @Test
    public void buy_negative_email(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        String searchResult = searchList.get(0).getText();
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        WebElement addToCart = driver.findElement(By.name("add-to-cart"));
        addToCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//div[@class = 'woocommerce-message']/a[@href = 'http://tartka.com.ua/ru/cart/']"));
        goToCart.click();
        WebElement order = driver.findElement(By.xpath("//table[@class = 'shop_table shop_table_responsive cart woocommerce-cart-form__contents']/tbody/tr[2]/td/a"));
        order.click();
        WebElement name = driver.findElement(By.id("billing_first_name"));
        name.click();
        name.sendKeys("Vasiliy");
        WebElement surname = driver.findElement(By.id("billing_last_name"));
        surname.click();
        surname.sendKeys("Ivanov");
        WebElement phone = driver.findElement(By.id("billing_phone"));
        phone.click();
        phone.sendKeys("0958686868686868686868");
        WebElement terms = driver.findElement(By.id("terms"));
        terms.sendKeys(Keys.SPACE);
        WebElement placeOrder = driver.findElement(By.id("place_order"));
        placeOrder.sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        WebElement notification = driver.findElement(By.xpath("//div[@class='woocommerce-NoticeGroup woocommerce-NoticeGroup-checkout']/ul/li"));
        Assert.assertEquals(notification.getText(), "Электронный адрес является обязательным полем");
        driver.quit();
    }

    @Test
    public void buy_negative_phone(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement search = driver.findElement(By.xpath("//div[@class = 'form-search-right']/input[@type = 'text']"));
        search.click();
        search.sendKeys("сутаж");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchFinish = driver.findElement(By.xpath("//div[@class = 'search-view-more']/a[@href = 'http://tartka.com.ua?s=сутаж&post_type=product']"));
        searchFinish.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> searchList = driver.findElements(By.xpath("//div[@class = 'product-info']/h3[@class = 'product-name p-font']/a"));
        String searchResult = searchList.get(0).getText();
        System.out.println(searchList.get(0).getText());
        searchList.get(0).click();
        WebElement addToCart = driver.findElement(By.name("add-to-cart"));
        addToCart.click();
        WebElement goToCart = driver.findElement(By.xpath("//div[@class = 'woocommerce-message']/a[@href = 'http://tartka.com.ua/ru/cart/']"));
        goToCart.click();
        WebElement order = driver.findElement(By.xpath("//table[@class = 'shop_table shop_table_responsive cart woocommerce-cart-form__contents']/tbody/tr[2]/td/a"));
        order.click();
        WebElement name = driver.findElement(By.id("billing_first_name"));
        name.click();
        name.sendKeys("Vasiliy");
        WebElement surname = driver.findElement(By.id("billing_last_name"));
        surname.click();
        surname.sendKeys("Ivanov");
        WebElement email = driver.findElement(By.id("billing_email"));
        email.click();
        email.sendKeys("Vasiliy@gmail.com");
        WebElement terms = driver.findElement(By.id("terms"));
        terms.sendKeys(Keys.SPACE);
        WebElement placeOrder = driver.findElement(By.id("place_order"));
        placeOrder.sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        WebElement notification = driver.findElement(By.xpath("//div[@class='woocommerce-NoticeGroup woocommerce-NoticeGroup-checkout']/ul/li"));
        Assert.assertEquals(notification.getText(), "Телефон является обязательным полем");
        driver.quit();
    }
    @Test
    public void search_tag(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement catalog = driver.findElement(By.xpath("//div[@class = 'menu-wrapper']/ul/li[@id = 'menu-item-2880']/a"));
        catalog.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement searchTag = driver.findElement(By.xpath("//div[@class = 'tagcloud']//*[contains(text(),'декор')]"));
        searchTag.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement tag = driver.findElement(By.xpath("//div[@class = 'product-info']/h3/a"));
        tag.click();
        WebElement tag1 = driver.findElement(By.xpath("//div[@class = 'product_meta']//*[contains(text(),'декор')]"));
        Assert.assertEquals(tag1.getText(), "декор");
        driver.quit();
    }
}
