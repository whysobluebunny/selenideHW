package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;

public class MarketAfterSearch {
    String sortButton = "//div[@data-apiary-widget-name='@MarketNode/SortPanel']//*[text()='по цене']";
    String loadingIMG = "//*[@data-zone-name='SearchResultsPaged']//div[@role='main']/div[not(@data-apiary-widget-name)]";
    String showAll = "//*[text()='Название видеокарты']/..//*[text()='Показать всё']";
    String videocardSelector = "//*[text()='Название видеокарты']/..//*[contains(.,'XXX')]";
    String modelSearch = "//*[@name='Поле поиска']";
    String resultPrice = "//*[@data-apiary-widget-name='@MarketNode/SearchPartition']//div[@data-zone-name='price']";

    @Step("Получить цену результата")
    public double getResultPrice(){
        return Double.parseDouble($x(resultPrice).getText().replaceAll("\\D+", ""));
    }

    @Step("Выбрать модель {model} ")
    public MarketAfterSearch selectModel(String model, boolean expandSearch){
        if(expandSearch)
            $x(showAll).click();

        $x(modelSearch).clear();
        $x(modelSearch).sendKeys(model);

        $x(videocardSelector.replace("XXX", model)).click();

        $x(sortButton).click();
        $x(loadingIMG).should(Condition.disappear);

        return this;
    }

    @Step("Отменить выбор модели {model} ")
    public MarketAfterSearch unselectModel(String model){
        $x(videocardSelector.replace("XXX", model)).click();
        return this;
    }

    @Step("Проверить, что {higherS} дороже {lowerS}")
    public MarketAfterSearch comparePrices(String higherS, String lowerS){
        double lower = selectModel(lowerS, true).getResultPrice();
        unselectModel(lowerS);
        double higher = selectModel(higherS, false).getResultPrice();

        Assertions.assertTrue(lower < higher, lowerS + " не дешевле " + higherS);
        return this;
    }
}
