package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends PageBase {

    @FindBy(id = "searchval")
    public WebElement searchField;

    @FindBy(xpath = "//button[@value='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//a[starts-with(@class, 'block font-semibold')]")
    public List<WebElement> listOfSearchItems;

    @FindBy(xpath = "//li[@class='rounded-r-md inline-block leading-4 align-top']")
    public WebElement goToPageRightArrow;

    @FindBy(id = "buyButton")
    public WebElement addToCartButton;

    @FindBy(xpath = "//div[@data-testid='amount-in-cart']")
    public WebElement itemAddedToCartMessage;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement viewCartButton;

    @FindBy(xpath = "//span[@class='itemDescription description']//a")
    public WebElement addedItemInCart;

    @FindBy(xpath = "//button[@class='emptyCartButton btn btn-mini btn-ui pull-right']")
    public WebElement emptyCartButton;

    @FindBy(xpath = "//p[@id='empty-cart-body']")
    public WebElement emptyCartPopup;

    @FindBy(xpath = "//footer//button[@type='button'][1]")
    public WebElement emptyCartButtonFromPopup;

    @FindBy(xpath = "//p[@class='header-1']")
    public WebElement cartIsEmptyMessage;

}