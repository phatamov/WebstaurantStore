package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.SearchPage;
import utilities.ConfigReader;
import utilities.Driver;
import org.assertj.core.api.SoftAssertions;

import java.util.LinkedList;
import java.util.List;

public class SearchStepDefinitions extends SearchPage {

    private SoftAssertions softAssertions = new SoftAssertions();
    List<String> listOfWrongTitles = new LinkedList<>();
    String url = ConfigReader.getProperty("url");
    String titleOfLastItem;

    @Given("The user is on the homepage")
    public void theUserIsOnTheHomepage() {
        Driver.getDriver().get(url);
    }

    @When("User searches for {string} from Homepage Search Bar")
    public void userSearchesForStainlessWorkTableFromHomepageSearchBar(String word) {
        searchField.sendKeys(word);
        searchButton.click();
    }

    @Then("Verify every product in search results has the word {string} in its title")
    public void verifyEveryProductInSearchResultsHasTheWordTableInItsTitle(String word) throws Exception {
        do {
            for (WebElement each : listOfSearchItems) {
                if (!each.getText().toLowerCase().contains(word.toLowerCase())) {
                    listOfWrongTitles.add(each.getText() + "\n");
                }
                softAssertions.assertThat(each.getText().toLowerCase().contains(word.toLowerCase()));
            }
            goToPageRightArrow.click();
        } while (Driver.getDriver().getPageSource().contains("last page"));
        softAssertions.assertAll();
        System.out.println("List of items which doesn't contain word 'table' in its title:\n" + listOfWrongTitles);
    }

    @When("User adds the last of found items from search results to Cart")
    public void userAddsTheLastOfFoundItemsFromSearchResultsToCart() {
        titleOfLastItem = listOfSearchItems.get(listOfSearchItems.size() - 1).getText();
        listOfSearchItems.get(listOfSearchItems.size() - 1).click();
        addToCartButton.click();
        Assert.assertTrue(itemAddedToCartMessage.isDisplayed());
    }

    @Then("Verify the item is added to Cart")
    public void verifyTheItemIsAddedToCart() {
        viewCartButton.click();
        Assert.assertEquals(titleOfLastItem, addedItemInCart.getText());
    }

    @When("User clicks on Empty Cart button from View Cart page")
    public void userClicksOnEmptyCartButtonFromViewCartPage() {
        emptyCartButton.click();
        Assert.assertTrue(emptyCartPopup.isDisplayed());
        Assert.assertEquals("Are you sure you want to empty your cart?", emptyCartPopup.getText());
        emptyCartButtonFromPopup.click();
    }

    @Then("Verify Cart is empty")
    public void verifyCartIsEmpty() {
        Assert.assertTrue(cartIsEmptyMessage.isDisplayed());
        Assert.assertEquals("Your cart is empty.", cartIsEmptyMessage.getText());
    }

}