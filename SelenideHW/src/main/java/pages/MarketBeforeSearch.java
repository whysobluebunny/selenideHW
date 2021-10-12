package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class MarketBeforeSearch {
    private String catalogButton = "//*[@data-apiary-widget-name='@MarketNode/HeaderCatalogEntrypoint']";
    private String subcategoryButton = "//*[@data-apiary-widget-name='@MarketNode/NavigationTree']//*[text()='XXX']";
    private String categoryButton = "//*[@data-apiary-widget-name='@MarketNode/HeaderCatalog']//*[text()='XXX']";

    @Step("Перейти в подкатегорию '{category}' категории '{subcategory}'")
    public MarketAfterSearch goToVideoCards(String category, String subcategory){
        $x(catalogButton).click();
        $x(categoryButton.replace("XXX", category)).hover();
        $x(subcategoryButton.replace("XXX", subcategory)).click();

        return page(MarketAfterSearch.class);
    }
}
