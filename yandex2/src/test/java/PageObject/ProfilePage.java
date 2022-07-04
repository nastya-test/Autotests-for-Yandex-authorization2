package PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    private WebDriver driver;

    //Локатор для информации о логине
    @FindBy(className ="personal-info-login")
    private WebElement infoLogin;

    //Локатор для меню
    @FindBy(className = "user-pic__image")
    private WebElement menu;

    //Локатор для выхода
    @FindBy(className = "legouser__menu-item_action_exit")
    private WebElement exit;

    //Штука для браузера
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Метод для клика
    public ProfilePage clickField(WebElement element) {
        element.click();
        return this;
    };

    //Метод для получения информации о логине
    public String getUserLogin() {
        String userLogin = infoLogin.getText();
        return userLogin; }


    @Step("Проверка выхода")
    public ProfilePage Exit() {
        clickField(menu);
        clickField(exit);
        return this;
    }

}
